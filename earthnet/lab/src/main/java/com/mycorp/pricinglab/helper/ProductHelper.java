package com.vzwcorp.pricinglab.helper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.vzwcorp.pricinglab.bo.VispEntityBO;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import com.vzwcorp.pricinglab.loader.profile.ubsr.repository.MtdtJitrRoutingRepository;
import com.vzwcorp.pricinglab.loader.profile.lookup.repository.RefVisionInstanceLkupRepository;
import com.vzwcorp.pricinglab.profile.vo.MtdtJitrRouting;
import com.vzwcorp.pricinglab.profile.lookup.vo.RefVisionInstanceLkup;
import com.vzwcorp.pricinglab.service.ServiceManager;
import com.vzwcorp.pricinglab.vo.PlabCust;
import com.vzwcorp.pricinglab.vo.Service;
import com.vzwcorp.pricinglab.vo.repository.PlabCustRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by pricinglab on 9/6/2016.
 */
@Component
public class ProductHelper {

    static Logger logger = LogManager.getLogger(ProductHelper.class);

    @Autowired
    PlabCustRepository custRepository;

    @Autowired
    ServiceManager serviceManager;

    @Autowired
    MtdtJitrRoutingRepository mtdtJitrRoutingRepository;

    @Autowired
    RefVisionInstanceLkupRepository refVisionInstanceLkupRepository;

    @Value("${hostname.list:tdc,odc,sdc}")
    public String dcNameList;

    /**
     * Returns a new ObjectMapper everytime
     * @return
     */

    public ObjectMapper getDefaultObjectMapper () {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        DateFormat df = new SimpleDateFormat(PlabConstants.MAPPER_DATE_FORMAT);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.setDateFormat(df);
        return mapper;
    }


    /**
     * VISP Helper Method : Create VISP Buissness Object.
     * @param mdn
     * @param spoId
     * @param operation
     * @return
     */
    public VispEntityBO createVispBO(String mdn, String spoId, byte operation) {
        //SQL : add service to device.
        List<PlabCust> custList = custRepository.findByMdn(mdn);

        for (PlabCust cust : custList) {
            //2. Get cust services.
            List<Service> services = cust.getOffer().getServices();

            for(Service service : services) {
                //3. Service SPO which is sent by loader.
                if (StringUtils.isNotBlank(spoId) && StringUtils.isNotBlank(service.getSpoID())
                        && StringUtils.equalsIgnoreCase(spoId,service.getSpoID())) {

                    VispEntityBO vispBO = new VispEntityBO();
                    vispBO.setOperation(operation);
                    vispBO.setCust(cust);
                    //NOTE : spo is the key as to not update any other offer which customer is enrolled into.
                    vispBO.setSpoId(spoId);

                    return vispBO;
                }
            }
        }
        return null;
    }

    /**
     * VISP Helper Method : execute VISP call with help of BO.
     * @param entity
     * @return
     */
    public Integer executeVispCall(VispEntityBO entity) {
        Integer responseMain = null, responseDefault = null;
        if (entity!=null) {
            //1. Add main service.
            if (PlabConstants.ADD_SERVICE_TO_DEVICE == entity.getOperation()) {
                responseMain = serviceManager.addVispServiceToDevice(entity.getLatestServiceInstance());
                if (responseMain != -1) {
                    //2. Add default service to visp.
                    responseDefault = serviceManager.addVispServiceToDevice(entity.getLatestDefaultServiceInstance());
                }
            } else if (PlabConstants.UPDATE_SERVICE_FOR_DEVICE == entity.getOperation()) {
                responseMain = serviceManager.updateVispServiceToDevice(entity.getLatestServiceInstance());
                if (responseMain != -1) {
                    //3. Update default service on visp.
                    responseDefault = serviceManager.updateVispServiceToDevice(entity.getLatestDefaultServiceInstance());
                }
            } else if (PlabConstants.REMOVE_SERVICE_FROM_DEVICE == entity.getOperation()) {
                responseMain = serviceManager.removeServiceFromDevice(entity.getLatestServiceInstance());
                if (responseMain != -1) {
                    //4. Remove default service from visp.
                    responseDefault = serviceManager.removeServiceFromDevice(entity.getLatestDefaultServiceInstance());
                }
            }
        }
        return responseMain;
    }


    /**
     * Get Time zone as per the given customer id.
     *
     * @param custId
     * @return TimeZone
     */
    public String getCustomerTimeZone(Long custId) {

        try {

            if (custId != null) {

                Calendar calendar = Calendar.getInstance(); //get current instance to find if DST should be enabled or not.

                //List<MtdtJitrRouting> mtdtJitrRoutings = mtdtJitrRoutingRepository.findActiveMtdtByCustId(custId);
                MtdtJitrRouting mtdtJitrRoutings = mtdtJitrRoutingRepository.findByCustId(custId);
                if (mtdtJitrRoutings != null
                        //&& !mtdtJitrRoutings.isEmpty()
                        ) {

                    //MtdtJitrRouting mtdtJitrRouting = mtdtJitrRoutings.get(0);
                    RefVisionInstanceLkup refVisionInstanceLkup = refVisionInstanceLkupRepository.findByBillerId(mtdtJitrRoutings.getBillerId());

                    if (refVisionInstanceLkup != null) {

                        TimeZone timeZoneShort = TimeZone.getTimeZone(refVisionInstanceLkup.getTimezone());

                        if (timeZoneShort != null) {
                            calendar.setTimeZone(timeZoneShort);
                            if (timeZoneShort.inDaylightTime(calendar.getTime())) {
                                String timeZoneDst = timeZoneShort.getDisplayName(true, TimeZone.SHORT, Locale.getDefault(Locale.Category.DISPLAY));
                                if (timeZoneDst.contains(PlabConstants.TZ_DEFAULT_CDT)) {
                                    return PlabConstants.TZ_DEFAULT_VISP_CDT;
                                } else if (timeZoneDst.contains(PlabConstants.TZ_DEFAULT_EDT)) {
                                    return PlabConstants.TZ_DEFAULT_VISP_EDT;
                                } else if (timeZoneDst.contains(PlabConstants.TZ_DEFAULT_PDT)) {
                                    return PlabConstants.TZ_DEFAULT_VISP_PDT;
                                } else {
                                    return timeZoneDst;
                                }
                            } else {
                                return timeZoneShort.getDisplayName(false, TimeZone.SHORT, Locale.getDefault(Locale.Category.DISPLAY));
                            }
                        } else {
                            return PlabConstants.TZ_DEFAULT_EST; //default if such a timezone doesnt exists in TimeZone list.
                        }
                    } else {
                        return PlabConstants.TZ_DEFAULT_EST; // default if Customer exists but not a biller id.
                    }
                } else {
                    return null; //default timezone if customer
                }
            } else {
                return null;
            }

        } catch (Exception ex) {
            logger.error("Exception : ",ex.getMessage());
            logger.error("DB Grant might be pending on UBSR MTDT Routing table for EOC Timezone. Default set : {}",PlabConstants.TZ_DEFAULT_EST);
            return PlabConstants.TZ_DEFAULT_EST; // default if Customer exists but not a biller id.
        }
    }


    /**
     * Find my data center suffix
     * @return
     */
    public String findMyDataCenterSuffix () {
        String queueSuffix = "tdc"; //default
        try {
            List<String> dcList = Arrays.asList(dcNameList.split(","));
            String hostname = InetAddress.getLocalHost().getHostName();
            logger.debug("Hostname : {}",hostname);
            for (String dc : dcList) {
                if (hostname != null && !hostname.isEmpty() && hostname.contains(dc)) {
                    queueSuffix = dc;
                    break;
                }
            }
        }catch (Exception ex){
            logger.error("Exception in findMyDatacenterSuffix: ",ex.getMessage(),ex);
        }
        return queueSuffix;
    }

}
