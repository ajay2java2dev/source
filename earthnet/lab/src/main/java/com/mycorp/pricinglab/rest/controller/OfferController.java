package com.vzwcorp.pricinglab.rest.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.vzwcorp.pricinglab.aspects.LoggingAspect;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.vzwcorp.pricinglab.constants.DvsConstants;
import com.vzwcorp.pricinglab.constants.OfferStatus;
import com.vzwcorp.pricinglab.constants.OneMessageType;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import com.vzwcorp.pricinglab.constants.SearchStrategy;
import com.vzwcorp.pricinglab.helper.ProductHelper;
import com.vzwcorp.pricinglab.helper.ServiceHelper;
import com.vzwcorp.pricinglab.loader.profile.lookup.repository.RefGridPlabCustRepository;
import com.vzwcorp.pricinglab.loader.profile.ubsr.repository.SubAcctSplanRepository;
import com.vzwcorp.pricinglab.loader.profile.ubsr.repository.SubCustAcctMdnRepository;
import com.vzwcorp.pricinglab.loader.profile.ubsr.repository.SubCustBlCycleRepository;
import com.vzwcorp.pricinglab.loader.profile.ubsr.repository.SubXltBlRbmRepository;
import com.vzwcorp.pricinglab.profile.lookup.vo.RefGridPlabCust;
import com.vzwcorp.pricinglab.profile.lookup.vo.RefGridPlabCustPK;
import com.vzwcorp.pricinglab.profile.vo.SubCustAcctMdn;
import com.vzwcorp.pricinglab.quartz.PLabJobScheduler;
import com.vzwcorp.pricinglab.service.DistributedService;
import com.vzwcorp.pricinglab.service.DvsManager;
import com.vzwcorp.pricinglab.service.McsManager;
import com.vzwcorp.pricinglab.service.OneMessageService;
import com.vzwcorp.pricinglab.service.ProductManager;
import com.vzwcorp.pricinglab.service.ServiceManager;
import com.vzwcorp.pricinglab.utility.PricingLabUtility;
import com.vzwcorp.pricinglab.vo.AdminOfferInstance;
import com.vzwcorp.pricinglab.vo.Application;
import com.vzwcorp.pricinglab.vo.BillingInfo;
import com.vzwcorp.pricinglab.vo.Choice;
import com.vzwcorp.pricinglab.vo.ChoiceInstance;
import com.vzwcorp.pricinglab.vo.ChoiceInstanceHistory;
import com.vzwcorp.pricinglab.vo.CustomerDeviceInfo;
import com.vzwcorp.pricinglab.vo.Feedback;
import com.vzwcorp.pricinglab.vo.Offer;
import com.vzwcorp.pricinglab.vo.OfferInstance;
import com.vzwcorp.pricinglab.vo.OfferMdnOption;
import com.vzwcorp.pricinglab.vo.PlabCust;
import com.vzwcorp.pricinglab.vo.QoS;
import com.vzwcorp.pricinglab.vo.RefQoS;
import com.vzwcorp.pricinglab.vo.ResponseInfo;
import com.vzwcorp.pricinglab.vo.Rule;
import com.vzwcorp.pricinglab.vo.RuleInstance;
import com.vzwcorp.pricinglab.vo.Service;
import com.vzwcorp.pricinglab.vo.ServiceInstance;
import com.vzwcorp.pricinglab.vo.ServiceQuestion;
import com.vzwcorp.pricinglab.vo.SimplePage;
import com.vzwcorp.pricinglab.vo.Survey;
import com.vzwcorp.pricinglab.vo.SurveyAnswer;
import com.vzwcorp.pricinglab.vo.SurveyQuestion;
import com.vzwcorp.pricinglab.vo.UsageRecord;
import com.vzwcorp.pricinglab.vo.VerizonEntity;
import com.vzwcorp.pricinglab.vo.Views;
import com.vzwcorp.pricinglab.vo.pounddata.AdditionalUsageProductAllowances;
import com.vzwcorp.pricinglab.vo.pounddata.AdditionalUsageProductDataItemList;
import com.vzwcorp.pricinglab.vo.pounddata.AdditionalUsageProductDetails;
import com.vzwcorp.pricinglab.vo.pounddata.AdditionalUsageProductList;
import com.vzwcorp.pricinglab.vo.repository.ApplicationRepository;
import com.vzwcorp.pricinglab.vo.repository.BillingInfoRepository;
import com.vzwcorp.pricinglab.vo.repository.ChoiceInstanceHistoryRepository;
import com.vzwcorp.pricinglab.vo.repository.ChoiceInstanceRepository;
import com.vzwcorp.pricinglab.vo.repository.ChoiceRepository;
import com.vzwcorp.pricinglab.vo.repository.CustomerDeviceInfoRepository;
import com.vzwcorp.pricinglab.vo.repository.FeedbackRepository;
import com.vzwcorp.pricinglab.vo.repository.OfferInstanceRepository;
import com.vzwcorp.pricinglab.vo.repository.OfferRepository;
import com.vzwcorp.pricinglab.vo.repository.PlabCustRepository;
import com.vzwcorp.pricinglab.vo.repository.RefQoSRepository;
import com.vzwcorp.pricinglab.vo.repository.RuleInstanceRepository;
import com.vzwcorp.pricinglab.vo.repository.RuleRepository;
import com.vzwcorp.pricinglab.vo.repository.SelectionPageRepository;
import com.vzwcorp.pricinglab.vo.repository.ServiceAnswerRepository;
import com.vzwcorp.pricinglab.vo.repository.ServiceInstanceRepository;
import com.vzwcorp.pricinglab.vo.repository.ServiceRepository;
import com.vzwcorp.pricinglab.vo.repository.SimplePageRepository;
import com.vzwcorp.pricinglab.vo.repository.SurveyAnswerRepository;
import com.vzwcorp.pricinglab.vo.repository.SurveyQuestionRepository;
import com.vzwcorp.pricinglab.vo.repository.SurveyRepository;
import com.vzwcorp.pricinglab.vo.repository.UsageRecordRepository;
import com.vzwcorp.pricinglab.vo.repository.VerizonEntityRepository;

@RestController
@Transactional("blTransactionManager")
public class OfferController {

    static Logger logger = LogManager.getLogger(OfferController.class);
    private static final int ACCEPTED = 1;
    private static final int DECLINED = -1;
    private static final int NO_STATUS = 0;

    @Value("${dvs.spo.enabled}")
    boolean dvs_spo_enabled;

    @Value("${loader.1.0.enabled}")
    boolean loaderOneEnabled;
    
    @Value("${provisioning.spo}")
    String provisioningSpo;
    
    @Value("${pounddata.time.format}")
    String poundDataTimeFormat;
    
    @Value("${preacceptoffer.lock.period.seconds:30}")
    long preAcceptOfferLockPeriod;
    
	@Value("${postacceptoffer.lock.period.seconds:90}")
	long postAcceptOfferLockPeriod;

    @Autowired
    OfferRepository repository;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    OfferInstanceRepository offerInstanceRepository;

    @Autowired
    VerizonEntityRepository vzwEntityRepository;

    @Autowired
    SelectionPageRepository selectionPageRepository;

    @Autowired
    SimplePageRepository pageRepository;

    @Autowired
    ServiceInstanceRepository serviceInstanceRepository;

    @Autowired
    ChoiceRepository choiceRepository;

    @Autowired
    ChoiceInstanceRepository choiceInstanceRepository;

    @Autowired
    ServiceAnswerRepository serviceAnswerRepository;

    @Autowired
    BillingInfoRepository billingInfoRepository;

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    SurveyQuestionRepository surveyQuestionRepository;

    @Autowired
    SurveyAnswerRepository surveyAnswerRepository;

    @Autowired
    ServiceManager serviceManager;

    @Autowired
    PlabCustRepository plabCustRepository;

    @Autowired
    RefGridPlabCustRepository refGridPlabCustRepository;

    @Autowired
    ApplicationRepository appRepository;

    @Autowired
    RuleInstanceRepository ruleInstanceRepository;

    @Autowired
    RuleRepository ruleRepository;

    @Autowired
    SubCustAcctMdnRepository subCustAcctMdnRepository;

    @Autowired
    SubXltBlRbmRepository subXltBlRbmRepository;

    @Autowired
    FeedbackRepository feedbackRepository;

    @Autowired
    PricingLabUtility utility;

    @Autowired
    ProductManager productManager;

    @Autowired
    DvsManager dvsManager;

    @Autowired
    UsageRecordRepository usageRecordRepository;

    @Autowired
	CustomerDeviceInfoRepository customerDeviceInfoRepository;

	@Autowired
	ChoiceInstanceHistoryRepository choiceInstanceHistoryRepository;

	@Autowired
	SubCustBlCycleRepository subCustBlCycleRepository;

	@Autowired
	SubAcctSplanRepository subAcctSplanRepository;

	@Autowired
	McsManager mcsManager;

    @Autowired
    ProductHelper productHelper;
    
    @Autowired
    OneMessageService oneMessageService;
    
    @Autowired
    PLabJobScheduler jobScheduler;
    
	@Autowired
	DistributedService distributedService;

    ObjectMapper mapper = new ObjectMapper();
    public OfferController () {mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);}
    
    @Autowired
    RefQoSRepository  refQoSRepository;
    
    Map<String, RefQoS> qosMap = new HashMap<String, RefQoS>();
 
    // CPI VIEW - Get an Offer
    //FIXME : GET to post but then also will need to change addOffer rest signature
    @RequestMapping(value = "/offer", method = RequestMethod.GET)
    public ResponseEntity getOffer(@RequestParam(value = "id", defaultValue = "0") String id
            , @RequestParam(value = "state", defaultValue = "all") String state) {

        logger.debug("OfferController getOffer() offer id is " + id);
        logger.debug("OfferController getOffer() state is " + state);
        Offer offer = null;
        List<Offer> offerList = null;

        if (state != null && !state.isEmpty() && !state.equalsIgnoreCase("all")) {
            offerList = repository.findByState(state);
        } else {
            offer = repository.findByOfferId(Long.parseLong(id));
        }

        if (offer != null || offerList != null) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            mapper.setSerializationInclusion(Include.NON_NULL);
            mapper.setDateFormat(df);
            ObjectWriter writer = mapper.writerWithView(Views.CPIView.class);
            try {

            	//sorting choices as per the sequence:
				if (offer.getSurveys()!=null) {
					for (Survey survey : offer.getSurveys()) {
						if(survey.getQuestions()!=null) {
							for (SurveyQuestion question : survey.getQuestions()) {
								if (question.getChoice()!=null && !question.getChoice().isEmpty()) {
									Collections.sort(question.getChoice(), OfferMdnOption.CHOICE_COMPARATOR);
								}
							}
						}
					}
				}

                String jsonInString = null;
                if (offer != null) {
                    jsonInString = writer.writeValueAsString(offer);
                } else if (offerList != null && !offerList.isEmpty()) {
                    jsonInString = writer.writeValueAsString(offerList);
                }
                logger.debug("OfferController getOffer() response json : "+jsonInString);
                return new ResponseEntity(jsonInString, HttpStatus.OK);
            } catch (JsonProcessingException e) {
                logger.error("Exception OfferController - getOffer() ): ", e.getMessage(), e);
                return new ResponseEntity("Exception OfferController - getOffers())! ", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    // Get an Offer
    @RequestMapping(value = "/admin/offers", method = RequestMethod.GET)
    public String getAdminOffers() {

        logger.debug("OfferController getAdminOffers ");

        List<Offer> offers = repository.findAll();

        if (offers != null && !offers.isEmpty()) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            ObjectMapper mapper = new ObjectMapper();
            mapper.setDateFormat(df);
            ObjectWriter writer = mapper.writerWithView(Views.PlabAdminView.class);
            try {
                String jsonInString = writer.writeValueAsString(offers);
                logger.debug("getAdminOffers() "+jsonInString); 
                return jsonInString;
            } catch (JsonProcessingException e) {
                logger.error("Exception OfferController - getAdminOffers() ): ", e.getMessage(), e);
                return null;
            }
        }
        return null;
    }

    // Get an Offer
    @RequestMapping(value = "/admin/serviceinstances/{offerInstanceId}", method = RequestMethod.GET)
    public String getAdminServiceInstancess(@PathVariable("offerInstanceId") Long offerInstanceId) {
    	logger.debug("getAdminServiceInstancess() for offerInstanceId "+offerInstanceId);
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(df);
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        ObjectWriter writer = mapper.writerWithView(Views.PlabAdminView.class);
    	if(offerInstanceId > 0){
    		try {
    			OfferInstance offerInstance = offerInstanceRepository.findOne(offerInstanceId);
                String jsonInString = writer.writeValueAsString(offerInstance.getServiceInstances());
                logger.debug("getAdminServiceInstancess() : "+jsonInString); 
                return jsonInString;
            } catch (Exception e) {
                logger.error("Exception OfferController - getAdminServiceInstancess() : ", e.getMessage(), e);
                return e.getMessage();
            }
    	} else {
    		try {
    			List<ServiceInstance> serviceInstances = serviceInstanceRepository.findByActiveOfferInstance();
                String jsonInString = writer.writeValueAsString(serviceInstances);
                logger.debug("getAdminServiceInstancess() : "+jsonInString); 
                return jsonInString;
    		} catch (Exception e) {
                logger.error("Exception OfferController - getAdminServiceInstancess() : ", e.getMessage(), e);
                return e.getMessage();
            }
    	}
    }

    // Get an Offer
    @RequestMapping(value = "/admin/offerinstances", method = RequestMethod.GET)
	public String getAdminOfferInstancess(@RequestParam(required = false, value = "offerId") Long offerId, @RequestParam(required = false, value = "mdn") String mdn,
			@RequestParam(required = false, value = "start") Integer start,
			@RequestParam(required = false, value = "length") Integer length,
			@RequestParam(required = false, value = "draw") Integer draw) {

		logger.debug(
				"OfferController getAdminOfferInstancess() offerId : {}, mdn : {}, start : {} , length : {} , draw : {}  ",
				offerId, mdn, start, length, draw);

		Page<Object> pageList = null;
		String jsonInString = null;
		int size = 50;
		int page = 0;

		if (start != null) {
			page = (int) (Math.ceil(start / length));
		}

		if (length != null) {
			size = length;
		}

		PageRequest pageReq = new PageRequest(page, size);
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put("draw", draw);

		if ((offerId == null || offerId <= 0) && (mdn == null || mdn.isEmpty())) {
			pageList = offerInstanceRepository.findAllForAdmin(pageReq);
		} else if (offerId != null && (mdn == null || mdn.isEmpty())) {
			pageList = offerInstanceRepository.findByOfferForAdmin(offerId, pageReq);
		} else if (offerId == null && mdn != null && !mdn.isEmpty()) {
			pageList = offerInstanceRepository.findAllByMdnForAdmin(mdn, pageReq);
		} else if (offerId != null && mdn != null && !mdn.isEmpty()) {
			pageList = offerInstanceRepository.findByOfferAndMdnForAdmin(offerId, mdn, pageReq);
		}

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(df);
		ObjectWriter writer = mapper.writer();
		try {

			if (pageList == null || pageList.getContent() == null || pageList.getContent().isEmpty()) {
				respMap.put("data", new ArrayList<AdminOfferInstance>());
				respMap.put("recordsFiltered", 0);
				respMap.put("recordsTotal", 0);

				jsonInString = writer.writeValueAsString(respMap);
				logger.debug("getAdminOfferInstancess() Response : " + jsonInString);
				return jsonInString;
			}

			List<AdminOfferInstance> adminOfferInstanceList = getAdminOfferInstanceList(pageList.getContent());

			respMap.put("data", adminOfferInstanceList);
			respMap.put("recordsFiltered", pageList.getTotalElements());
			respMap.put("recordsTotal", pageList.getTotalElements());

			jsonInString = writer.writeValueAsString(respMap);
			logger.debug("getAdminOfferInstancess() Response : " + jsonInString);
			return jsonInString;
		} catch (JsonProcessingException e) {
			logger.error("Exception OfferController - getAdminOfferInstancess() ): " + e.getMessage(), e);
			return null;
		}
	}

    private List<AdminOfferInstance> getAdminOfferInstanceList(List<Object> list) {
    	List<AdminOfferInstance> offrInstList = new ArrayList<AdminOfferInstance>();
    	for(Object obj : list){
    		Object[] data = (Object[]) obj;
    		//oi.offerInstanceId as offerInstanceId, ofr.offerId as offerId, ofr.name as offerName, vze.custIdNo as custIdNo, vze.acctNo as acctNo, vze.mdn as mdn, oi.dateCreated as dateCreated, oi.endTmstamp
    		int i=0;
    		Long offerInstanceId = (Long) data[i++];
			Long offerId= (Long) data[i++];
			String offerName= (String) data[i++];
			Long custIdNo= (Long) data[i++];
			Long acctNo= (Long) data[i++];
			String mdn= (String) data[i++];;
			Date dateCreated= (Date) data[i++];
			Timestamp endTmstamp= (Timestamp) data[i++];
			offrInstList.add(new AdminOfferInstance(offerInstanceId, offerId, offerName, custIdNo, acctNo, mdn, dateCreated, endTmstamp));
    	}
		return offrInstList;
	}

	/**
     * Terminating offer instance.
     *
     * @param offerInstanceId
     * @return
     */
    @RequestMapping(value = "/offer/instance/terminate/{offerInstanceId}", method = RequestMethod.POST)
    public ResponseEntity terminateOfferInstance(@PathVariable("offerInstanceId") Long offerInstanceId) {

        logger.debug("OfferController terminateOfferInstance for offerInstanceId "+offerInstanceId);
        try {

            if (offerInstanceId == null || offerInstanceId == 0) {
                return new ResponseEntity("terminateOfferInstance() : Exception while terminating offer instance.", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            OfferInstance offerInstance = offerInstanceRepository.findOne(offerInstanceId);
            for (ServiceInstance serviceInstance : offerInstance.getServiceInstances()) {
                serviceManager.removeServiceFromDevice(serviceInstance);
            }
            offerInstance.setEndTmstamp(new Timestamp(new Date().getTime()));
            //offerInstance.setState("Terminated");
            offerInstanceRepository.saveAndFlush(offerInstance);
            logger.debug("OfferController terminateOfferInstance() : terminated successfully"); 
        } catch (Exception ex) {
            logger.error("terminateOfferInstance() : Exception while terminating offer instance.", ex.getMessage(), ex);
            return new ResponseEntity("terminateOfferInstance () : Exception while terminating offer instance.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity("0", HttpStatus.OK);
    }

	/*
	 *removeProvisioningSpoOnly is set to true only if this api is called at EOC after customer voluntarily terminates. In other scenarios it should be set to false.
	 */

	@RequestMapping(value = "/offer/cust/terminate/{offerId}/{mdn}/{removeProvisioningSpoOnly}", method = RequestMethod.POST)
	public ResponseEntity removeCustomerFromPilot(@PathVariable("offerId") Long offerId,
			@PathVariable("mdn") String mdn,@PathVariable("removeProvisioningSpoOnly") boolean removeProvisioningSpoOnly){
		if (offerId == null) {
			logger.debug("removeCustomerFromPilot() : Offer : " + offerId + " not available in the request");
			return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST,"OfferId needs to be given in the request ",PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
		}
		if (mdn == null || mdn.isEmpty()) {
			logger.debug("removeCustomerFromPilot () : MDN : " + mdn + " not available");
			return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST,"mdn needs to be given in the request ",PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
		}

		logger.debug("OfferController removeCustomerFromPilot offerId : " + offerId);
		logger.debug("OfferController removeCustomerFromPilot mdn : " + mdn);
		logger.debug("OfferController removeCustomerFromPilot removeProvisioningSpoOnly : "+removeProvisioningSpoOnly);
		Offer offer = repository.findByOfferId(offerId);

		if(offer==null){
			logger.debug("Offer not found for the given offerId:"+offerId);
			return new ResponseEntity(utility.createResponseInfo(HttpStatus.NOT_FOUND,"Offer not found for the given offerId:"+offerId,PlabConstants.STATUS_ERROR),HttpStatus.NOT_FOUND);
		}
		else{
			VerizonEntity vzEntityOrig = vzwEntityRepository.findByMdn(mdn);

            if(vzEntityOrig==null){
            	logger.debug("VerizonEntity not found for the given mdn:"+mdn);
				return new ResponseEntity(utility.createResponseInfo(HttpStatus.NOT_FOUND,"VerizonEntity not found for the given mdn:"+mdn,PlabConstants.STATUS_ERROR),HttpStatus.NOT_FOUND);
            }
			else{
				Long[] indicators = {PlabConstants.ENROLLED,PlabConstants.PRE_ACCEPT,PlabConstants.INVITED_IND,PlabConstants.EARLY_TERMINATE_PENDING_EOC,PlabConstants.EARLY_TERMINATE_VISION_REMOVED};			//EARLY_TERMINATE_PENDING_EOC is added to remove base service instances in the case of early terminate;;
				List<PlabCust> plabCustList = plabCustRepository.findByOfferAndCustIdNoAndAcctNoAndIndicator(offerId, vzEntityOrig.getCustIdNo(), vzEntityOrig.getAcctNo(), indicators);
				logger.debug("removeCustomerFromPilot() : plabCust in the account : "+plabCustList.size());

				if(plabCustList==null || plabCustList.isEmpty()) {
					logger.debug("No PlabCust found for the given mdn and offerID:" + offerId);
					return new ResponseEntity(utility.createResponseInfo(HttpStatus.NOT_FOUND,"No PlabCust found for the given mdn and offerID:" + offerId,PlabConstants.STATUS_ERROR),HttpStatus.NOT_FOUND);
				} else {	
					String selectedServiceType = null;
					boolean plabDeletion = false;			//case where for loop of member list was not executed as pointed out by ming
					Map<String,String> response=null;
					Map<String,List<String>> mdnSpoList = productManager.getSpoOfMdn(plabCustList,false);	
					StringBuilder mdnString = new StringBuilder();
					PlabCust primaryCust = null;
						for(PlabCust eachPlabCust : plabCustList){
								VerizonEntity vzEntity=null;
								String eachPlabMdn= eachPlabCust.getMdn();
								mdnString.append(","+eachPlabMdn);
								
								if(!eachPlabCust.getMdn().equals(mdn)){
									vzEntity = vzwEntityRepository.findByMdn(eachPlabCust.getMdn());
								}else {
                                    vzEntity =vzEntityOrig;
                                    primaryCust = eachPlabCust;
                                }

                                List<OfferInstance> offerInstances = offerInstanceRepository.findByOfferAndVerizonEntity(offer.getOfferId(), vzEntity.getVerizonEntityId());
								
								if(offerInstances!=null && !offerInstances.isEmpty()){
									plabDeletion = productManager.terminateAllInstancesFromPlabAndVisp(offerInstances, eachPlabCust.getMdn(),true);
								}
								else
									logger.error("removeCustomerFromPilot() : offerinstance not found for a member whose mdn="+eachPlabMdn);
								
								productManager.updateIndicatorInPlabcustAndRefgridAndGrid(eachPlabMdn, plabCustList, PlabConstants.REMOVING,null);
								logger.error("removeCustomerFromPilot() : End dated plbCust for mdn = "+eachPlabMdn);
								
								if (selectedServiceType == null) {
									for (OfferInstance offerInst : offerInstances) {
										for (ServiceInstance si : offerInst.getServiceInstances()) {
											String serviceType = null;
											serviceType = serviceManager.getServiceType(si.getService());
											if (!serviceType.equals("BaseService")) {
												selectedServiceType = serviceType;
												break;
											}
										}
									}
								}								

							}
						if(plabDeletion){
			                // Remove all existing jobs
			                jobScheduler.deleteJobsForCustomer(primaryCust);
			                
							logger.debug("removeCustomerFromPilot() : Customers successfully end dated in plab");
						}
						
						//removed at once for all customers
							logger.debug("removeCustomerFromPilot() : Entered vision flow");
							
							if(mdnSpoList==null || mdnSpoList.isEmpty()){
								if(plabDeletion){
									logger.debug("mdnSpoList is empty.So not removed from vision");
									return new ResponseEntity(utility.createResponseInfo(HttpStatus.MULTI_STATUS,"mdnSpoList is empty.So not removed from vision",PlabConstants.STATUS_SUCCESS),HttpStatus.MULTI_STATUS);
								}
								else{
									logger.debug("mdnSpoList is empty.So not removed from vision");
									return new ResponseEntity(utility.createResponseInfo(HttpStatus.INTERNAL_SERVER_ERROR,"mdnSpoList is empty.So not removed from vision",PlabConstants.STATUS_ERROR),HttpStatus.INTERNAL_SERVER_ERROR);	
								}
							}
							
							if(removeProvisioningSpoOnly){
								response = dvsManager.productValidation(vzEntityOrig.getCustIdNo().toString(), vzEntityOrig.getAcctNo().toString(), plabCustList,mdnSpoList,provisioningSpo,DvsConstants.ACTION_INDICATOR_DELETE);
							}else{
								response = dvsManager.productValidation(vzEntityOrig.getCustIdNo().toString(), vzEntityOrig.getAcctNo().toString(), plabCustList,mdnSpoList,null,DvsConstants.ACTION_INDICATOR_DELETE);
							}

							if(response!=null && !response.isEmpty() && response.get(DvsConstants.ERROR_IN_RETRIEVE_CUST_PROFILE)!=null ){
								logger.debug(DvsConstants.ERROR_IN_RETRIEVE_CUST_PROFILE+" : "+response.get(DvsConstants.ERROR_IN_RETRIEVE_CUST_PROFILE));
								return new ResponseEntity(utility.createResponseInfo(HttpStatus.MULTI_STATUS,DvsConstants.ERROR_IN_RETRIEVE_CUST_PROFILE+" : "+response.get(DvsConstants.ERROR_IN_RETRIEVE_CUST_PROFILE),PlabConstants.STATUS_SUCCESS),HttpStatus.MULTI_STATUS);
							}
							else if(response!=null && !response.isEmpty() && response.get(DvsConstants.ERROR_IN_PRODUCTVALIDATION)!=null){
								logger.debug(DvsConstants.ERROR_IN_PRODUCTVALIDATION+" : "+response.get(DvsConstants.ERROR_IN_PRODUCTVALIDATION));
								return new ResponseEntity(utility.createResponseInfo(HttpStatus.MULTI_STATUS,DvsConstants.ERROR_IN_PRODUCTVALIDATION+" : "+response.get(DvsConstants.ERROR_IN_PRODUCTVALIDATION),PlabConstants.STATUS_SUCCESS),HttpStatus.MULTI_STATUS);
							}
							else{
								response = dvsManager.issueMlmoRequest(response, vzEntityOrig.getCustIdNo().toString(), vzEntityOrig.getAcctNo().toString());
								if(response!=null && !response.isEmpty() && response.get(DvsConstants.ERROR_IN_MLMO_REQUEST)!=null){
									logger.debug(DvsConstants.ERROR_IN_MLMO_REQUEST+" : "+response.get(DvsConstants.ERROR_IN_MLMO_REQUEST));
									return new ResponseEntity(utility.createResponseInfo(HttpStatus.MULTI_STATUS,DvsConstants.ERROR_IN_MLMO_REQUEST+" : "+response.get(DvsConstants.ERROR_IN_MLMO_REQUEST),PlabConstants.STATUS_SUCCESS),HttpStatus.MULTI_STATUS);
								}
								else{
									for(PlabCust eachPlabCustTemp : plabCustList){
										productManager.updateIndicatorInPlabcustAndRefgridAndGrid(eachPlabCustTemp.getMdn(), plabCustList, PlabConstants.REMOVED_PENDING_EOC,null);
									}
									if(plabDeletion){
										
										// Send one message about termination
						                if(selectedServiceType !=null){
						                	String oneMessageType = null;
						                	if("NightSurfer".equalsIgnoreCase(selectedServiceType)){
						                		oneMessageType = OneMessageType.NightSurferExpire.toString();
						                	}else if("SpeedTier".equalsIgnoreCase(selectedServiceType)){
						                		oneMessageType = OneMessageType.SelectYourSpeedExpire.toString();
						                	}
						                	oneMessageService.sendMessage(oneMessageType, vzEntityOrig.getCustIdNo(), vzEntityOrig.getAcctNo(), vzEntityOrig.getMdn(), mdnString.toString().replaceFirst(",", ""));
						                }
						                
										logger.debug("removeCustomerFromPilot() : Customer/s removed successfully from Pilot");
										return new ResponseEntity(utility.createResponseInfo(HttpStatus.OK,"Customer/s removed successfully from Pilot",PlabConstants.STATUS_SUCCESS),HttpStatus.OK);
									}
									else{
									logger.debug("removeCustomerFromPilot() : Customer/s removed successfully from vision but not from plab");
									return new ResponseEntity(utility.createResponseInfo(HttpStatus.MULTI_STATUS,"Customer/s removed successfully from vision but not from plab",PlabConstants.STATUS_SUCCESS),HttpStatus.MULTI_STATUS);
									}
								}
								
								
								}
					}
				}

			}

		}
	
	/*
	 *1.Invoke billing
	 *2.Terminate plab spo from vision,update indicator
	 *3.terminate instances from plab,visp,update indicators 
	 *4.Create base services
	 *5.Execute createEarlyTerminationJobs 
	 */
	@RequestMapping(value = "/offer/cust/terminate/{offerId}/{mdn}", method = RequestMethod.POST)
	public ResponseEntity earlyTerminateCustomerAndCreateBaseService(@PathVariable("offerId") Long offerId,@PathVariable("mdn") String mdn){
		boolean plabDeletion=false;	
		boolean terminateSpoVision=false;
		PlabCust primaryPlabCust=null;
		Date billCycleEndDt=null;
		StringBuilder mdnString = new StringBuilder();
		String selectedServiceType = null;
		
		if (offerId == null) {
			logger.debug("earlyTerminateCustomerAndCreateBaseService() : Offer : " + offerId + " not available in the request");
			return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST,"OfferId needs to be given in the request ",PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
		}
		if (mdn == null || mdn.isEmpty()) {
			logger.debug("earlyTerminateCustomerAndCreateBaseService() : MDN : " + mdn + " not available");
			return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST,"mdn needs to be given in the request ",PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
		}

		logger.debug("OfferController earlyTerminateCustomerAndCreateBaseService offerId : " + offerId);
		logger.debug("OfferController earlyTerminateCustomerAndCreateBaseService mdn : " + mdn);

		Offer offer = repository.findByOfferId(offerId);

		if(offer==null){
			logger.debug("Offer not found for the given offerId:"+offerId);
			return new ResponseEntity(utility.createResponseInfo(HttpStatus.NOT_FOUND,"Offer not found for the given offerId:"+offerId,PlabConstants.STATUS_ERROR),HttpStatus.NOT_FOUND);
		}
		else{
			VerizonEntity vzEntityOrig = vzwEntityRepository.findByMdn(mdn);

			if(vzEntityOrig==null){
				logger.debug("VerizonEntity not found for the given mdn:"+mdn);
				return new ResponseEntity(utility.createResponseInfo(HttpStatus.NOT_FOUND,"VerizonEntity not found for the given mdn:"+mdn,PlabConstants.STATUS_ERROR),HttpStatus.NOT_FOUND);
			}
			else{

				List<Date> billCycleDates = productManager.getSubCustBillCycleDates(vzEntityOrig.getCustIdNo(), vzEntityOrig.getAcctNo());
				if(billCycleDates!=null && !billCycleDates.isEmpty()){
					Calendar tempDate = Calendar.getInstance();
					tempDate.setTime(billCycleDates.get(1)); 
					tempDate.add(Calendar.DATE, -1); 
					billCycleEndDt = tempDate.getTime();
				}

				if(billCycleEndDt==null){
					logger.error("earlyTerminateCustomerAndCreateBaseService() : billCycle End date cannot be null here for cust_id_no : "+vzEntityOrig.getCustIdNo());
				}

				Long[] indicators = {PlabConstants.ENROLLED,PlabConstants.PRE_ACCEPT,PlabConstants.INVITED_IND};
				List<PlabCust> plabCustList = plabCustRepository.findByOfferAndCustIdNoAndAcctNoAndIndicator(offerId, vzEntityOrig.getCustIdNo(), vzEntityOrig.getAcctNo(), indicators);
				logger.debug("earlyTerminateCustomerAndCreateBaseService() : plabCust in the account : "+plabCustList.size());
				
				if(plabCustList==null ||  plabCustList.isEmpty()) {
					logger.debug("No PlabCust found for the given mdn and offerID:" + offerId);
					return new ResponseEntity(utility.createResponseInfo(HttpStatus.NOT_FOUND,"No PlabCust found for the given mdn and offerID:" + offerId,PlabConstants.STATUS_ERROR),HttpStatus.NOT_FOUND);
				} else {			
					
						logger.debug("earlyTerminateCustomerAndCreateBaseService() : Invoking customer billing for mdn : "+mdn);
						customerBilling(mdn,null,null,true);
			

					logger.debug("earlyTerminateCustomerAndCreateBaseService() : Invoking Vision flow to terminate plabSpo");
					Map<String,List<String>> mdnSpoList = productManager.getSpoOfMdn(plabCustList,true);

					if(mdnSpoList==null || mdnSpoList.isEmpty()){
						logger.error("earlyTerminateCustomerAndCreateBaseService() : mdnSpoList is empty.Plab Spo cannot be removed from vision");
					}else{
						Map<String,String> response = dvsManager.productValidation(vzEntityOrig.getCustIdNo().toString(), vzEntityOrig.getAcctNo().toString(), plabCustList,mdnSpoList,null,DvsConstants.ACTION_INDICATOR_DELETE);

						if(response.get(DvsConstants.ERROR_IN_RETRIEVE_CUST_PROFILE)!=null ){
							logger.error(DvsConstants.ERROR_IN_RETRIEVE_CUST_PROFILE+" : "+response.get(DvsConstants.ERROR_IN_RETRIEVE_CUST_PROFILE));
						}
						else if(response.get(DvsConstants.ERROR_IN_PRODUCTVALIDATION)!=null){
							logger.error(DvsConstants.ERROR_IN_PRODUCTVALIDATION+" : "+response.get(DvsConstants.ERROR_IN_PRODUCTVALIDATION));
						}					
						else{
							response = dvsManager.issueMlmoRequest(response, vzEntityOrig.getCustIdNo().toString(), vzEntityOrig.getAcctNo().toString());
							if(response.get(DvsConstants.ERROR_IN_MLMO_REQUEST)!=null){
								logger.error(DvsConstants.ERROR_IN_MLMO_REQUEST+" : "+response.get(DvsConstants.ERROR_IN_MLMO_REQUEST));
							}
							else{
								for(PlabCust eachPlabCust : plabCustList){
									productManager.updateIndicatorInPlabcustAndRefgridAndGrid(eachPlabCust.getMdn(), plabCustList, PlabConstants.EARLY_TERMINATE_VISION_REMOVED,billCycleEndDt);
									terminateSpoVision=true;
									logger.debug("earlyTerminateCustomerAndCreateBaseService() : Plab spo removed from vision.Proceeding to terminate instances in plab and create base services for all");
								}
							}
						}
					}

					for(PlabCust eachPlabCust : plabCustList){
						String eachPlabMdn= eachPlabCust.getMdn();
						mdnString.append(","+eachPlabMdn);
						VerizonEntity vzEntity=null;

						if(!eachPlabCust.getMdn().equals(mdn)){
							vzEntity = vzwEntityRepository.findByMdn(eachPlabCust.getMdn());
						}else {
							vzEntity =vzEntityOrig;
							primaryPlabCust = eachPlabCust;
						}

						List<OfferInstance> offerInstances = offerInstanceRepository.findByOfferAndVerizonEntity(offerId, vzEntity.getVerizonEntityId());
						if(offerInstances!=null && !offerInstances.isEmpty()){
							plabDeletion = productManager.terminateAllInstancesFromPlabAndVisp(offerInstances, eachPlabCust.getMdn(),true);
							if(plabDeletion){
								logger.debug("earlyTerminateCustomerAndCreateBaseService() : Terminated all instances from visp and plab for mdn : "+eachPlabCust.getMdn());
							}else{
								logger.debug("earlyTerminateCustomerAndCreateBaseService() : Error terminating instances from visp and plab for mdn : "+eachPlabCust.getMdn());
							}
						}
						else{
							logger.error("earlyTerminateCustomerAndCreateBaseService() : offerinstance not found for a member whose mdn="+eachPlabMdn);
						}

						if(plabDeletion){
							productManager.updateIndicatorInPlabcustAndRefgridAndGrid(eachPlabMdn, plabCustList, PlabConstants.EARLY_TERMINATE_PENDING_EOC,billCycleEndDt);
							logger.debug("earlyTerminateCustomerAndCreateBaseService() : End dated plabCust for mdn = "+eachPlabMdn);
							logger.debug("earlyTerminateCustomerAndCreateBaseService() : Creating base service for mdn :"+vzEntity.getMdn());
							OfferInstance newOfferInstance = productManager.createNewOfferInstanceOrUpdateTcOfExisting(offer, vzEntity, 1, null);  
							Service defaultService = serviceManager.getDefaultService();
							ServiceInstance serviceInstance = productManager.createServiceInstance(defaultService, vzEntity, newOfferInstance,null);
							serviceInstance = productManager.addEOCRuleInstance(serviceInstance,vzEntity.getCustIdNo(),null);							
							newOfferInstance.getServiceInstances().add(serviceInstance);
							ServiceInstance tempServiceInstance = serviceInstanceRepository.saveAndFlush(serviceInstance);
							  if (tempServiceInstance != null) {
                              	logger.debug("earlyTerminateCustomerAndCreateBaseService() : Calling addVispServiceToDevice on serviceInstance :"+tempServiceInstance.getServiceInstanceId());
						    		serviceManager.addVispServiceToDevice(tempServiceInstance);
                              } else {
                                  logger.error("earlyTerminateCustomerAndCreateBaseService() : Service instance not created.");
                                  return new ResponseEntity(utility.createResponseInfo(HttpStatus.EXPECTATION_FAILED, "Service Instance not createddd", PlabConstants.STATUS_ERROR), HttpStatus.EXPECTATION_FAILED);
                              }
							logger.debug("earlyTerminateCustomerAndCreateBaseService() : Created serviceInstance : "+tempServiceInstance.getServiceInstanceId()+" for mdn : "+eachPlabCust.getMdn());
							offerInstanceRepository.saveAndFlush(newOfferInstance);					
						}
						
						if (selectedServiceType == null) {
							for (OfferInstance offerInst : offerInstances) {
								for (ServiceInstance si : offerInst.getServiceInstances()) {
									String serviceType = null;
									serviceType = serviceManager.getServiceType(si.getService());
									if (!serviceType.equals("BaseService")) {
										selectedServiceType = serviceType;
										break;
									}
								}
							}
						}
					}

					if(plabDeletion){
						logger.debug("earlyTerminateCustomerAndCreateBaseService() : Calling createEarlyTerminationJobs");
						productManager.createEarlyTerminationJobs(primaryPlabCust);

						// Send one message about termination
		                if(selectedServiceType !=null){
		                	String oneMessageType = null;
		                	if("NightSurfer".equalsIgnoreCase(selectedServiceType)){
		                		oneMessageType = OneMessageType.NightSurferEarlyTerm.toString();
		                	}else if("SpeedTier".equalsIgnoreCase(selectedServiceType)){
		                		oneMessageType = OneMessageType.SelectYourSpeedEarlyTerm.toString();
		                	}
		                	oneMessageService.sendMessage(oneMessageType, vzEntityOrig.getCustIdNo(), vzEntityOrig.getAcctNo(), vzEntityOrig.getMdn(), mdnString.toString().replaceFirst(",", ""));
		                }
						
						if(terminateSpoVision){
						logger.debug("earlyTerminateCustomerAndCreateBaseService() : Early termination and provisioning base services successful for the account with mdn : "+mdn);
						return new ResponseEntity(utility.createResponseInfo(HttpStatus.OK,"Early termination and provisioning base services successful for the account",PlabConstants.STATUS_SUCCESS),HttpStatus.OK); 
						}
						else{
						logger.debug("earlyTerminateCustomerAndCreateBaseService() : Early termination and provisioning base services successful but spo termination on vision wasn't successful for the account with mdn : "+mdn);
						return new ResponseEntity(utility.createResponseInfo(HttpStatus.MULTI_STATUS,"Early termination and provisioning base services successful but spo termination on vision wasn't successful",PlabConstants.STATUS_SUCCESS),HttpStatus.MULTI_STATUS); 	
						}
					}
					else{
						logger.debug("earlyTerminateCustomerAndCreateBaseService() : Plab spo removed from vision but error terminating instances in plab.So no base service created for the account with mdn : "+mdn);
						return new ResponseEntity(utility.createResponseInfo(HttpStatus.MULTI_STATUS,"Plab spo removed from vision but error terminating instances in plab.So no base service created",PlabConstants.STATUS_SUCCESS),HttpStatus.MULTI_STATUS); 
					}
				}
			}

		}
	}

    // Get an Service Details by offerid
    @RequestMapping(value = "/offer/service", method = RequestMethod.GET)
    public ResponseEntity getServiceDetailsByOfferId(@RequestParam(value = "id", defaultValue = "0") String id) {

        logger.debug("OfferController getServiceDetailsByOfferId for offer id : " + id);
        
        if (id != null && !id.isEmpty()) {
            try {
                Offer offer = repository.findByOfferId(Long.parseLong(id));
                if (offer != null) {


                    List<Service> serviceList = serviceRepository.findByOffer(offer);

                    if (serviceList != null && !serviceList.isEmpty()) {
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        mapper.setSerializationInclusion(Include.NON_NULL);
                        mapper.setDateFormat(df);
                        ObjectWriter writer = mapper.writerWithView(Views.CPIView.class);

						/*for (Service service : serviceList) {
							if (service.getOptions()!=null && !service.getOptions().isEmpty()) {
								Set<ServiceQuestion> serviceQuestions = service.getOptions();
								for (ServiceQuestion serviceQuestion : serviceQuestions) {
									if (serviceQuestion.getChoice()!=null && !serviceQuestion.getChoice().isEmpty()) {
										Collections.sort(serviceQuestion.getChoice(), OfferMdnOption.CHOICE_COMPARATOR);
									}
								}
							}
						}*/

						for (Service service : serviceList) {
							if (service.getQos()!=null && !service.getQos().isEmpty()) {
								Set<QoS> serviceQoSs = service.getQos();
								List<QoS> qoSList = new ArrayList<QoS>(serviceQoSs);
								Collections.sort(qoSList, ServiceHelper.QOS_COMPARATOR);
								service.setQos(new LinkedHashSet<QoS>(qoSList));
							}
						}

                        try {
                            String jsonInString = writer.writeValueAsString(serviceList);
                            logger.debug("OfferController getServiceDetailsByOfferId response json : " + jsonInString);
                            return new ResponseEntity(jsonInString, HttpStatus.OK);
                        } catch (JsonProcessingException e) {
                            logger.error("Exception OfferController - getOffer() ): ", e.getMessage(), e);
                            return new ResponseEntity("Exception OfferController - getOffers())! - Parse Error", HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                    } else {
                        logger.debug("Service Details not found for offer id : " + id);
                        return new ResponseEntity("Service Details not found for offer id : " + id, HttpStatus.NOT_FOUND);
                    }
                } else {
                    logger.debug("Offer Details not found for offer id : " + id);
                    return new ResponseEntity("Offer Details not found for offer id : " + id, HttpStatus.NOT_FOUND);
                }
            } catch (Exception ex) {
                logger.error("Exception in getOfferDetails", ex.getMessage(), ex);
                return new ResponseEntity("Exception OfferController - getServiceDetailsByOfferId())! ", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        logger.debug("Offer ID is Invalid : " + id);
        return new ResponseEntity("Offer ID is Invalid : " + id, HttpStatus.BAD_REQUEST);
    }

    /**
     * Get Offer Details - Simple Pages.
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/offer/details", method = RequestMethod.GET)
    public ResponseEntity getOfferDetails(@RequestParam(value = "id", defaultValue = "0") String id) {

        logger.debug("OfferController getOfferDetails() for offer id is " + id);
        if (id != null && !id.isEmpty()) {
            try {
                Offer offer = repository.findByOfferId(Long.parseLong(id));
                if (offer != null) {

                    List<SimplePage> simplePages = offer.getSimplePages();

                    if (simplePages != null && !simplePages.isEmpty()) {
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        mapper.setSerializationInclusion(Include.NON_NULL);
                        mapper.setDateFormat(df);
                        ObjectWriter writer = mapper.writerWithView(Views.MobileFirstView.class);

                        try {
                            String jsonInString = writer.writeValueAsString(simplePages);
                            logger.debug("getOfferDetails() response json : "+jsonInString);
                            return new ResponseEntity(jsonInString, HttpStatus.OK);
                        } catch (Exception ex) {
                            logger.error("Exception OfferController - getOffer() ): ", ex.getMessage(), ex);
                            return new ResponseEntity("Exception OfferController - getOffers())! - Parse Error", HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                    } else {
                        logger.debug("Service Details not found for offer id : " + id);
                        return new ResponseEntity("Service Details not found for offer id : " + id, HttpStatus.NOT_FOUND);
                    }
                } else {
                    logger.debug("Offer Details not found for offer id : " + id);
                    return new ResponseEntity("Offer Details not found for offer id : " + id, HttpStatus.NOT_FOUND);
                }
            } catch (Exception ex) {
                logger.error("Exception in getOfferDetails", ex.getMessage(), ex);
                return new ResponseEntity("Exception OfferController - getOffers())! ", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        logger.debug("Offer ID is Invalid : " + id);
        return new ResponseEntity("Offer ID is Invalid : " + id, HttpStatus.BAD_REQUEST);
    }

    /**
     * <p>
     * Get offers by MDN.
     * @param mdn - mdn's needs to present in the Plab_Cust with Indicator (-4,-3,-2,-1 or 1).
     * @return -
     * error :
     * <ol>
     *      <li>return 206 (partial content) to show the appropriate error message.</li>
     *      <li>return 5xx (server side exception) in case an exception is thrown.</li>
     *      <li>return 4xx (client side exception) to client side exception. However currently not used as it impacts MF flows.</li>
     * </ol>
     * success :
     * <ol>
     *      <li>return 200 (OK) to success message</li>
     * </ol>
     *</p>
     *
     */
    @RequestMapping(value = "/offers", method = RequestMethod.POST)
    public ResponseEntity getOffers(@RequestParam(value = "mdn", defaultValue = "0") String mdn) {

        ObjectWriter writer = null;

        try {
            logger.debug("OfferController getOffers() MDN : " + mdn);

            Offer plabCustOffer = null;
            List<Offer> offers = new ArrayList<>();
            int totalInPilot = 0;

            //Json Formats
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            mapper.setSerializationInclusion(Include.NON_NULL);
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            mapper.setDateFormat(df);
            Timestamp currentDate = new Timestamp(new Date().getTime());

            if (mdn != null && !("0").equalsIgnoreCase(mdn)) {
                writer = mapper.writerWithView(Views.MobileFirstView.class);
                //Show only offer mapped to an MDN
                VerizonEntity entity = vzwEntityRepository.findByMdn(mdn);

                if (entity != null) {

                    Long[] indicators = {PlabConstants.INVITED_IND, PlabConstants.PRE_ACCEPT,
                            PlabConstants.ENROLLED, PlabConstants.REMOVING, PlabConstants.REMOVED_PENDING_EOC};
                    List<PlabCust> plabCusts = plabCustRepository.findByMdnAndIndicator(mdn, indicators);

                    if (plabCusts != null && !plabCusts.isEmpty()) {
                        for (PlabCust plabCust : plabCusts) {
                            if (plabCust != null) {
                                plabCustOffer = plabCust.getOffer();
                                List<String> uniqueMdn = new ArrayList<String>();
                                List<OfferInstance> offerInstances = null;

                                if (plabCustOffer == null) {
                                    String jsonInString = writer.writeValueAsString(utility.createResponseInfo(HttpStatus.PARTIAL_CONTENT,PlabConstants.MDN_NOT_ELIG, PlabConstants.STATUS_ERROR));
                                    logger.debug("getOffers() : "+jsonInString);
                                    return new ResponseEntity(jsonInString, HttpStatus.PARTIAL_CONTENT);
                                }

                                if (!(OfferStatus.DEPLOYED.getOfferState().equalsIgnoreCase(plabCustOffer.getState()))) {
                                    logger.error("Precondition failed : Offer is not deployed. Offer ID : " + plabCustOffer.getOfferId());
                                    return new ResponseEntity(utility.createResponseInfo(HttpStatus.PARTIAL_CONTENT,"Precondition failed : Offer is not deployed. Offer ID : " + plabCustOffer.getOfferId(), PlabConstants.STATUS_ERROR),HttpStatus.PARTIAL_CONTENT);
                                }

                                List<SubCustAcctMdn> accountMembers = null;

                                if (plabCustOffer.isMdnOptionEnabled() != null && plabCustOffer.isMdnOptionEnabled()) {

									//Try calling grid first.
									accountMembers = productManager.getCustomerAccountDetails(SearchStrategy.FIND_ACTIVE_CUSTOMER_DETAILS_FROM_GRID,
											entity.getCustIdNo(), entity.getAcctNo());
									if (accountMembers == null || (accountMembers!=null && accountMembers.isEmpty())) {
										accountMembers = productManager.getCustomerAccountDetails(SearchStrategy.FIND_ACTIVE_CUSTOMER_DETAILS_FROM_DB,
												entity.getCustIdNo(), entity.getAcctNo());
									}

                                    for (SubCustAcctMdn subCustAcctMdn : accountMembers) {
                                        uniqueMdn.add(subCustAcctMdn.getMdn());
                                    }
                                } else {
                                    uniqueMdn.add(mdn);
                                }
                                
                               // uniqueMdn.add(mdn);

                                if(plabCustOffer!=null)
									totalInPilot = plabCustOffer.getTotalActiveCust();
                                
                                 if(PlabConstants.ENROLLED.equals(plabCust.getIndicator())) {
                                	plabCustOffer.setEnrolled(PlabConstants.MDN_ENROL);
                                	offerInstances = offerInstanceRepository.findByOfferAndVerizonEntity(plabCustOffer.getOfferId(), entity.getVerizonEntityId());
                                	}
                                 else if (totalInPilot >= PlabConstants.MAX_PILOT_SIZE.intValue()) {
                                    plabCustOffer.setEnrolled(PlabConstants.PILOT_FULL);
                                } else {
                                    if (currentDate.after(plabCustOffer.getEndDate())) {
                                        plabCustOffer.setEnrolled(PlabConstants.PILOT_EXP);
                                    } else {
                                        //if mdn check for isEnrolled.

                                    	offerInstances = offerInstanceRepository.findByOfferAndVerizonEntity(plabCustOffer.getOfferId(), entity.getVerizonEntityId());

                                    	if (PlabConstants.PRE_ACCEPT.equals(plabCust.getIndicator())) {
                                    		plabCustOffer.setEnrolled(PlabConstants.MDN_ELIG);
                                    	}
                                    }
                                }
                                //if MDN is already enrolled - set choices from choice instance.
								/*As confirmed by Amr in Aug 10,2016, 9 AM call*/
                                List<OfferMdnOption> offerMdnOptionList = new ArrayList<>();
                                List<OfferInstance> offerInstancesTempList = null;
                                OfferInstance offerInstanceUniqueMdn = null;
                                for (String mdnMember : uniqueMdn) {
                                    /*VerizonEntity entityTemp = vzwEntityRepository.findByMdn(mdnMember);
                                    offerInstancesTempList = offerInstanceRepository.findByOfferAndVerizonEntity(plabCustOffer.getOfferId(), entityTemp.getVerizonEntityId());*/
                                	VerizonEntity entityTemp = null;
									if(mdnMember.equals(mdn)){
										entityTemp=entity;
										offerInstancesTempList = offerInstances;
									} else {
                                        entityTemp = vzwEntityRepository.findByMdn(mdnMember);
                                        if (entityTemp!=null && entityTemp.getVerizonEntityId()!=null) {
                                            offerInstancesTempList = offerInstanceRepository.findByOfferAndVerizonEntity(plabCustOffer.getOfferId(), entityTemp.getVerizonEntityId());
                                        } else {
                                            logger.info("MDN : {} is currently not part of plab and hence not shown as part of getOffers() list.",mdnMember);
                                        }
                                    }
                                    List<ChoiceInstance> choiceInstances = null;
                                    if (offerInstancesTempList != null && !offerInstancesTempList.isEmpty()) {
                                        offerInstanceUniqueMdn = offerInstancesTempList.get(0);
                                        choiceInstances = choiceInstanceRepository.findByOfferInstance(offerInstanceUniqueMdn.getOfferInstanceId());
                                    }
                                    OfferMdnOption offerMdnOption = new OfferMdnOption();
                                    offerMdnOption.setMdn(entityTemp.getMdn());
                                    List<ServiceQuestion> sqListMdnOptions = new ArrayList<>();
                                    if (choiceInstances != null && !choiceInstances.isEmpty()) {
                                        if (plabCustOffer != null && plabCustOffer.getOfferOptions() != null) {
                                            for (ServiceQuestion serviceQuestion : plabCustOffer.getOfferOptions()) {
                                                ServiceQuestion sqMdnOptions = new ServiceQuestion();
                                                List<Choice> choiceListMdnOptions = new ArrayList<Choice>();
                                                for (Choice choice : serviceQuestion.getChoice()) {
                                                    for (ChoiceInstance instance : choiceInstances) {
                                                        if (instance.getChoice().getId() == choice.getId()) {
                                                            //choice.setSelected(instance.getSelected()); //incases when choiceinstance for an mdn within the a/c doesnt(only for serviceQuestion) exist default choices will be chosen;;so choice cannot be modified by prev choice instance
                                                            Choice choiceMdnOptions = new Choice();
                                                            choiceMdnOptions.setSelected(instance.getSelected());
                                                            choiceMdnOptions.setColorCode(choice.getColorCode());
                                                            choiceMdnOptions.setDefaultChoice(choice.getDefaultChoice());
                                                            choiceMdnOptions.setId(choice.getId());
                                                            choiceMdnOptions.setTitle(choice.getTitle());
                                                            choiceMdnOptions.setServiceQuestion(serviceQuestion);
                                                            if (serviceQuestion.getService().getQos() != null) {
                                                                for (QoS qos : serviceQuestion.getService().getQos()) {
                                                                    if (qos.getName() != null && qos.getName().equalsIgnoreCase(choice.getTitle()))
                                                                        choiceMdnOptions.setChargePerDay(qos.getChargePerDay());
                                                                }
                                                            }
                                                            choiceListMdnOptions.add(choiceMdnOptions);
                                                        }
                                                    }
                                                }
                                                sqMdnOptions.setChoice(choiceListMdnOptions);
                                                sqMdnOptions.setTitle(serviceQuestion.getTitle());
                                                sqMdnOptions.setName(serviceQuestion.getName());
                                                sqMdnOptions.setSelectionType(serviceQuestion.getSelectionType());
                                                sqMdnOptions.setService(serviceQuestion.getService());
                                                sqMdnOptions.setMaxSelectedOptions(serviceQuestion.getMaxSelectedOptions());
                                                sqMdnOptions.setDateCreated(serviceQuestion.getDateCreated());
                                                sqMdnOptions.setSelectionPageId(serviceQuestion.getSelectionPageId());
                                                sqListMdnOptions.add(sqMdnOptions);
                                            }
                                        }
                                        offerMdnOption.setOfferOptions(sqListMdnOptions);
                                        offerMdnOptionList.add(offerMdnOption);

                                        //SURVEY UPDATE
                                        productManager.updateCustomerSurvey(plabCustOffer.getSurveys(),choiceInstances);


                                    } else {                    //when choiceinstances come empty set default choices
                                        if (plabCust.getOffer().getOfferOptions() != null) {
                                            for (ServiceQuestion serviceQuestionTemp : plabCust.getOffer().getOfferOptions()) {
                                                if (serviceQuestionTemp.getService().getQos() != null) {
                                                    for (QoS qos : serviceQuestionTemp.getService().getQos()) {
                                                        for (Choice choice : serviceQuestionTemp.getChoice()) {
                                                            if (choice.getTitle() != null && choice.getTitle().equalsIgnoreCase(qos.getName())) {
                                                                choice.setChargePerDay(qos.getChargePerDay());
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                        offerMdnOption.setOfferOptions(plabCust.getOffer().getOfferOptions());
                                        offerMdnOptionList.add(offerMdnOption);
                                    }
                                }
                                productManager.sortChoicesById(offerMdnOptionList);
                                plabCustOffer.setOfferMdnOptions(offerMdnOptionList);
                                plabCustOffer.setOfferOptions(null);
                            } else {
                            	logger.debug("getOffers() : "+PlabConstants.NO_PLAB_CUST_MSG); 
                                return new ResponseEntity(utility.createResponseInfo(HttpStatus.PARTIAL_CONTENT, PlabConstants.NO_PLAB_CUST_MSG, PlabConstants.STATUS_ERROR), HttpStatus.PARTIAL_CONTENT);
                            }
                            //productManager.getAndupdateSurveyAnswers(plabCustOffer.getOfferInstances());
                            offers.add(plabCustOffer);
                        }
                    } else {
                    	logger.debug("getOffers() : "+PlabConstants.NO_PLAB_CUST_MSG);
                        return new ResponseEntity(utility.createResponseInfo(HttpStatus.PARTIAL_CONTENT, PlabConstants.NO_PLAB_CUST_MSG, PlabConstants.STATUS_ERROR), HttpStatus.PARTIAL_CONTENT);
                    }
                } else {
                 	logger.debug("getOffers() : "+PlabConstants.NO_PLAB_CUST_MSG);
                    return new ResponseEntity(utility.createResponseInfo(HttpStatus.PARTIAL_CONTENT, PlabConstants.NO_PLAB_CUST_MSG, PlabConstants.STATUS_ERROR), HttpStatus.PARTIAL_CONTENT);
                }
            } else {
                writer = mapper.writerWithView(Views.CPIView.class);
                //Retreive all offers
                offers = repository.findAll();
            }

            if (offers != null && !offers.isEmpty()) {
                try {
                    String jsonInString = null;
                    if (mdn != null && !("0").equalsIgnoreCase(mdn)) {
                        Map<String, Object> respMap = utility.createResponseInfo(HttpStatus.OK, "", PlabConstants.STATUS_SUCCESS);
                        int count = 0;
                        for (Offer offer : offers) {
                            if (offers.size() > 1) {
                                respMap.put(PlabConstants.RESPONSE_CONTENT + "_" + ++count, offer);
                            } else {
                                respMap.put(PlabConstants.RESPONSE_CONTENT, offer);
                            }
                        }
                        jsonInString = writer.writeValueAsString(respMap);
                        logger.info ("Get offers JSON : " +jsonInString);
                    } else {
                        jsonInString = writer.writeValueAsString(offers);
                        logger.info ("Get offers JSON : " +jsonInString);
                    }
                    return new ResponseEntity(jsonInString, HttpStatus.OK);
                } catch (JsonProcessingException e) {
                    logger.error("Exception OfferController - getOffers() ): ", e.getMessage(), e);
                    return new ResponseEntity("Exception OfferController - getOffers())! ", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                Map<String, Object> respMap = null;
                if (mdn != null && !("0").equalsIgnoreCase(mdn)) {
                    respMap = utility.createResponseInfo(HttpStatus.PARTIAL_CONTENT, PlabConstants.NOMDN_OR_NOOFFER_MSG, PlabConstants.STATUS_ERROR);
                } else {
                    respMap = utility.createResponseInfo(HttpStatus.PARTIAL_CONTENT, PlabConstants.NO_OFFERS_MSG, PlabConstants.STATUS_ERROR);
                }
                logger.debug("getOffers() : "); 
                return new ResponseEntity(respMap, HttpStatus.PARTIAL_CONTENT);
            }
        } catch (Exception ex) {
            String jsonInString = "";
            if (mdn != null && !("0").equalsIgnoreCase(mdn)) {
                logger.error("Exception in get offers : ", ex.getMessage(), ex);
                return new ResponseEntity(utility.createResponseInfo(HttpStatus.INTERNAL_SERVER_ERROR, "Exception In get offers", PlabConstants.STATUS_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            logger.error("Exception in get offers : ", ex.getMessage(), ex);
            return new ResponseEntity("Exception in get offers", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get Surveys for a given offer and mdn
     * @param offerId
     * @param mdn
     * @return
     */
    @RequestMapping(value = "/offer/surveys/{offerId}/{mdn}",method = RequestMethod.POST)
    public ResponseEntity getOfferSurveysForMdn (@PathVariable(value = "offerId") String offerId,
                                                 @PathVariable(value = "mdn") String mdn) {
        String jsonInString = null;
        ObjectWriter writer = mapper.writerWithView(Views.MobileFirstView.class);
        //Return on error
        if (StringUtils.isEmpty(offerId) || StringUtils.isEmpty(mdn)) {
            logger.error("Bad Request : Offer ID or mdn was null");
            return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Bad Request : Offer ID or mdn was null",
                    PlabConstants.STATUS_ERROR), HttpStatus.BAD_REQUEST);
        }
        //get customer and his offer instance
        logger.debug("getOfferSurveysForMdn() for offerID : "+offerId+" mdn : "+mdn);
        VerizonEntity verizonEntity = vzwEntityRepository.findByMdn(mdn);
        List<OfferInstance> offerInstances = offerInstanceRepository.findByOfferAndVerizonEntity(Long.parseLong(offerId),verizonEntity.getVerizonEntityId());
        Offer offer = repository.findOne(Long.parseLong(offerId));

        if (verizonEntity !=null) {
            List<Survey> surveys = offer.getSurveys();
            List<Survey> updatedSurveys = new ArrayList<Survey>();
            if (surveys!=null && !surveys.isEmpty()) {

                //set updated answer for surveys answered. set counter for new survey's available
                productManager.retreiveAndUpdateSurveyAnswers(offerInstances);

                for (OfferInstance offerInstance : offerInstances) {
                    updatedSurveys.addAll(offerInstance.getOffer().getSurveys());
                }

                Map<String, Object> respMap = utility.createResponseInfo(HttpStatus.OK, "", PlabConstants.STATUS_SUCCESS);
                try {

                    respMap.put(PlabConstants.RESPONSE_CONTENT, updatedSurveys != null ? updatedSurveys : surveys);
                    jsonInString = writer.writeValueAsString(respMap);

                    //getOffers-Updated Surveys
                    logger.info("getOfferSurveysForMdn(offer id : " + offerId + " , mdn : " + mdn + " ) response json : "+jsonInString);
                    return new ResponseEntity(jsonInString, HttpStatus.OK);

                } catch (Exception ex) {
                    logger.error("Exception getOfferSurveysForMdn (failed to parse response)");
                    return new ResponseEntity(utility.createResponseInfo(HttpStatus.INTERNAL_SERVER_ERROR, "Exception getOfferSurveysForMdn (failed to parse response).",
                            PlabConstants.STATUS_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                logger.error("Not found : Surveys not available in plab for mdn.");
                return new ResponseEntity(utility.createResponseInfo(HttpStatus.NOT_FOUND, "Not found : Surveys not available in plab for mdn.",
                        PlabConstants.STATUS_ERROR), HttpStatus.NOT_FOUND);
            }
        } else {
            logger.error("Not found : Customer not available in plab.");
            return new ResponseEntity(utility.createResponseInfo(HttpStatus.NOT_FOUND, "Not found : Customer not available in plab.",
                    PlabConstants.STATUS_ERROR), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/offer/instance", method = RequestMethod.GET)
    public ResponseEntity getOfferInstanceRest(@RequestParam(value = "offerId", defaultValue = "0") String offerId,
                                               @RequestParam(value = "mdn", defaultValue = "0") String mdn) {

        logger.debug("OfferController getOfferInstanceRest() for offerID : "+offerId+" mdn "+mdn);

        if ((offerId != null && !offerId.isEmpty() && (mdn != null && !mdn.isEmpty()))) {


            Offer offer = repository.findByOfferId(Long.parseLong(offerId));
            VerizonEntity verizonEntity = vzwEntityRepository.findByMdn(mdn);
            ObjectWriter writer = mapper.writerWithView(Views.ManagedView.class);

            try {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                mapper.setSerializationInclusion(Include.NON_NULL);
                mapper.setDateFormat(df);

                if (offer != null && verizonEntity != null) {
                    List<OfferInstance> offerInstances = offerInstanceRepository.findByOfferAndVerizonEntity(offer.getOfferId(), verizonEntity.getVerizonEntityId());
                    OfferInstance offerInstance = offerInstances.get(0);
                    String jsonInString = writer.writeValueAsString(offerInstance);
                    logger.debug("OfferController getOfferInstanceRest() for offerID : "+offerId+" mdn "+mdn+" response json is : "+jsonInString);
                    return new ResponseEntity(jsonInString, HttpStatus.OK);
                } else {
                	logger.debug("Offer or VerizonEntity not found.");
                    return new ResponseEntity("Offer or VerizonEntity not found.", HttpStatus.NOT_FOUND);
                }

            } catch (JsonProcessingException e) {
                logger.error("Exception in processing Json", e.getMessage(), e);
                return new ResponseEntity("Exception is json parsing.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        logger.debug("Invalid offerid or mdn in request.");
        return new ResponseEntity("Invalid offerid or mdn in request.", HttpStatus.BAD_REQUEST);
    }

    // Update SPO ID ,RBM ID, STATE in Offer
    // Create an offer
    @RequestMapping(value = "/offer", method = RequestMethod.POST)
    public String addOffer(@RequestBody(required = false) String jsonBody,
                           @RequestParam(value = "OfferObject", defaultValue = "0") String jsonOffer) {

        logger.debug("OfferController addOffer Request json " + jsonOffer);
        
        Offer offer = null;
        try {

            if (jsonOffer != null && !jsonOffer.trim().isEmpty() && !jsonOffer.equalsIgnoreCase("0")) {
                offer = mapper.readValue(jsonOffer, Offer.class);
                logger.debug("Add offer, (request in param) : " + jsonOffer);
            } else {
                offer = mapper.readValue(jsonBody, Offer.class);
                logger.debug("Add offer, (request in body) : " + jsonBody);
            }

            if (offer != null) {

                //Save offer to generate the offer id and to make sure no ORDER OF INSERTION get's changing because of any iterations below.
                Offer savedOffer = repository.saveAndFlush(offer);

                if (savedOffer.getSurveys() != null)

                    for (Survey survey : savedOffer.getSurveys()) {
                        survey.setOffer(savedOffer);
                        for (SurveyQuestion question : survey.getQuestions()) {
                            question.setSurvey(survey);
                            if (question.getChoice() != null) {
                               /* Oct 14 2016 (Ajay):  Surveys needs not have default choice selection.
                               boolean choiceDefault = false;
                               int count = 0;
                                */
                                for (Choice choice : question.getChoice()) {
                                    choice.setSurveyQuestion(question);
                                  /*Oct 14 2016 (Ajay):  Surveys needs not have default choice selection.
                                  if (count == 0) {
                                        choiceDefault = true;
                                        choice.setDefaultChoice(choiceDefault);
                                        choice.setSelected(true);
                                    }
                                    count++;
                                    */
                                }
                                //Added Oct 14 2016 (Ajay):  Trying to order the choices as per the order of insertion
								List<Choice> orderedChoices = new LinkedList<Choice>(question.getChoice());
								question.setChoice(orderedChoices);
                            }
                        }
                    }

                if (savedOffer.getOfferOptions() != null) {
                    for (ServiceQuestion question : savedOffer.getOfferOptions()) {
                        question.setOffer(savedOffer);
                        if (question.getChoice() != null) {
                            for (Choice choice : question.getChoice()) {
                                choice.setServiceQuestion(question);
                            }
                        }
                    }
                }

                boolean isAddDefaultSimplePage = true;
                if (savedOffer.getSimplePages() != null) {
                    for (SimplePage page : savedOffer.getSimplePages()) {
                        page.setOffer(savedOffer);
                        if (isAddDefaultSimplePage && page.getType() != null
                                && PlabConstants.SIMPLE_PAGE_OFFER_OPTION_TYPE.equalsIgnoreCase(page.getType().trim())) {
                            isAddDefaultSimplePage = false;
                        }
                    }
                }

                //Fixme : Remove offerOptions page default
                if (isAddDefaultSimplePage) {
                    if (savedOffer != null && savedOffer.getName() != null) {
                        String name = savedOffer.getName();
                        name = name.toLowerCase();
                        if (name.contains("night") || name.matches("(.*)night(.*)")) {
                            productManager.addSimplePage(offer, "OfferOption", "Surf all night long.",
                                    "https://mobile-edev.vzw.com/dev03/MobileFirst/headerImg.png",
                                    "", "",
                                    "Choose the lines you want to add on Night Surfer",
                                    "",
                                    "Estimated Total: {total}/mo",
                                    "");
                        } else if (name.contains("speed") || name.matches("(.*)speed(.*)")) {
                            productManager.addSimplePage(savedOffer, "OfferOption", "All the data at your speed.",
                                    "https://mobile-edev.vzw.com/dev03/MobileFirst/headerImg.png",
                                    "", "",
                                    "Choose the lines you want to add on Speed Tier",
                                    "",
                                    "Estimated Total: {total}/mo",
                                    "");
                        } else if (name.contains("video") || name.matches("(.*)video(.*)")) {
                            productManager.addSimplePage(savedOffer, "OfferOption", "Now choose the quality.",
                                    "https://mobile-edev.vzw.com/dev03/MobileFirst/headerImg.png",
                                    "", "",
                                    "Choose the video quality you want for your line(s).",
                                    "",
                                    "",
                                    "");
                        } else {
                            productManager.addSimplePage(savedOffer, "OfferOption", "Select your options.",
                                    "https://mobile-edev.vzw.com/dev03/MobileFirst/headerImg.png",
                                    "", "",
                                    "Choose the lines you want to add on this offer",
                                    "",
                                    "Estimated Total: {total}/mo",
                                    "");
                        }
                    }
                }

                savedOffer.setDateCreated(new Date());
                repository.saveAndFlush(savedOffer);

                logger.debug("Offer created, id= {}",savedOffer.getOfferId());
                return savedOffer.getOfferId().toString();
            } else {
                logger.debug("Offer not created. Offer Json is null or empty");
                return "Offer not created. Offer Json is null or empty";
            }

        } catch (JsonParseException e) {
            logger.error("JsonParseException in AddOffer() : ", e.getMessage(), e);
        } catch (JsonMappingException e) {
            logger.error("JsonMappingException in AddOffer() : ", e.getMessage(), e);
        } catch (IOException e) {
            logger.error("IOException in AddOffer() : ", e.getMessage(), e);
        }
        logger.debug("addOffer() : Error");
        return "Error";
    }


    // associate a service to an offer
    @RequestMapping(value = "/offer/service", method = RequestMethod.POST)
    public String addServiceToOffer(@RequestParam(value = "serviceId", defaultValue = "0") String serviceId,
                                    @RequestParam(value = "offerId", defaultValue = "0") String offerId) {

        logger.debug("OfferController addServiceToOffer offerId " + offerId + " serviceId " + serviceId);

        try {

            // Offer offer = repository.findOne(offerId);

            Offer offer = repository.findByOfferId(Long.parseLong(offerId));
            Service service = serviceRepository.findByServiceId(Long.parseLong(serviceId));

            // Service service = serviceRepository.findOne(serviceId);

            service.setOffer(offer);
            List<Service> services = offer.getServices();
            if (services == null) {
                services = new ArrayList<Service>();
                offer.setServices(services);
            }

            service.setOffer(offer);
            service.setDateCreated(new Date());
            offer.setDateCreated(new Date());
            services.add(service);
            serviceRepository.saveAndFlush(service);

            repository.saveAndFlush(offer);
            logger.debug(" Offer created, id=" + offer.getOfferId());
            return offer.getOfferId().toString();

        } catch (Exception e) {
            // TODO Auto-generated catch block
        	logger.debug("addServiceToOffer() : "+e.getMessage());
            return (e.getMessage());
        }
    }

    /**
     * Accept Terms and Conditions for a given offer.
     * @param offerId
     * @param mdn
     * @param status
     * @return
     */
    // @CrossOrigin (origins = "*")
    @RequestMapping(value = "/offer/{offerId}/{mdn}/terms/{status}", method = RequestMethod.POST)
    public ResponseEntity termsAndConditions(@PathVariable("offerId") String offerId,
                                             @PathVariable("mdn") String mdn, @PathVariable("status") String status) {

        logger.debug("OfferController termsAndConditions offerId : " + offerId + " mdn : " + mdn + " status :" + status);

        if (offerId == null) {
            logger.debug("Offer " + offerId + " not available in the request");
            return new ResponseEntity<>(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Offer " + offerId + " not available in the request", PlabConstants.STATUS_ERROR), HttpStatus.BAD_REQUEST);
        }
        if (mdn == null) {
            logger.debug("MDN " + mdn + " not available in request");
            return new ResponseEntity<>(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "MDN " + mdn + " not available in the request", PlabConstants.STATUS_ERROR), HttpStatus.BAD_REQUEST);
        }
        if (status == null || (status != null && status.trim().isEmpty())) {
            logger.debug("Status sent as empty. Set as (accept/reject)");
            return new ResponseEntity<>(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Status sent as empty. Set as (accept/reject)", PlabConstants.STATUS_ERROR), HttpStatus.BAD_REQUEST);
        }
        
        boolean enrolled=false;
        try {

            Offer offer = repository.findByOfferId(Long.parseLong(offerId));
            
            VerizonEntity vzEntity = vzwEntityRepository.findByMdn(mdn);

            if (offer == null || vzEntity == null) {
                logger.debug("Offer or Customer Details not found !");
                return new ResponseEntity<>(utility.createResponseInfo(HttpStatus.NOT_FOUND, "Offer or Customer Details not found!", PlabConstants.STATUS_ERROR), HttpStatus.NOT_FOUND);
            }

            //add to thread-context
            ThreadContext.put(LoggingAspect.EVENT_PROP_CUSTOMER_ID, vzEntity.getCustIdNo()!=null?vzEntity.getCustIdNo().toString():null);
            ThreadContext.put(LoggingAspect.EVENT_PROP_ACCOUNT_NUMBER, vzEntity.getAcctNo()!=null?vzEntity.getAcctNo().toString():null);
            ThreadContext.put(LoggingAspect.EVENT_PROP_MDN,vzEntity.getMdn()!=null?vzEntity.getMdn().toString():null);
        
            //enrolled = productManager.customerActivelyEnrolledCheckinTnC(vzEntity.getCustIdNo(), vzEntity.getAcctNo());				///this is to ensure that terms and conditions are not accepted again
            
            List<PlabCust> plabCustList = plabCustRepository.findByOfferAndCustIdNoAndAcctNoLatest(Long.valueOf(offerId),vzEntity.getCustIdNo(), vzEntity.getAcctNo());
        	
        	if(plabCustList!=null && !plabCustList.isEmpty()){
        		PlabCust plabCust = plabCustList.get(0);
        		if(plabCust.getIndicator().equals(PlabConstants.PRE_ACCEPT) || plabCust.getIndicator().equals(PlabConstants.INVITED_IND)){
        			List<OfferInstance> offerInstanceList = offerInstanceRepository.findLatestOfferInstanceByVerizonEntity(vzEntity.getVerizonEntityId());
        			if(offerInstanceList!=null && !offerInstanceList.isEmpty()){
        				OfferInstance offerInstance = offerInstanceList.get(0);
        				Map<String, Object> respMap = utility.createResponseInfo(HttpStatus.OK, "T&C Accepted", PlabConstants.STATUS_SUCCESS);
                		respMap.put("Content", offerInstance.getOfferInstanceId());
                		logger.debug("OfferInstance id returned in accept is : "+ offerInstance.getOfferInstanceId());
                		return new ResponseEntity<>(respMap, HttpStatus.OK);
        			}
        		}else if(plabCust.getIndicator().equals(PlabConstants.ENROLLED)){
        			enrolled=true;
        		}
        	}
   
            if(!enrolled){         

            	//if offer already accepted for offerid and mdn.
            	if ("accept".equalsIgnoreCase(status.trim())) {

                    //Try calling grid first.
					List<SubCustAcctMdn> accountMembers = productManager.getCustomerAccountDetails(SearchStrategy.FIND_ACTIVE_CUSTOMER_DETAILS_FROM_GRID,
							vzEntity.getCustIdNo(), vzEntity.getAcctNo());
					if (accountMembers == null || (accountMembers != null && accountMembers.isEmpty())) {
						accountMembers = productManager.getCustomerAccountDetails(SearchStrategy.FIND_ACTIVE_CUSTOMER_DETAILS_FROM_DB,
								vzEntity.getCustIdNo(), vzEntity.getAcctNo());
					}

					OfferInstance offerInstance = new OfferInstance(offer, vzEntity);
            		offerInstance.setDateCreated(offerInstance.getDateCreated() != null ? offerInstance.getDateCreated() : Calendar.getInstance().getTime());
            		offerInstance.setOffer(offer);
            		offerInstance.setVerizonEntity(vzEntity);
            		offerInstance.setTermsAndConditionsStatus(ACCEPTED);
            		offerInstance.setEndTmstamp(PricingLabUtility.getDefaultEndTimeStamp());
            		offerInstanceRepository.saveAndFlush(offerInstance);
            		productManager.updateTermsAndConditionsForMembers(accountMembers,ACCEPTED,offer,offerInstance);
            		
            		Map<String, Object> respMap = utility.createResponseInfo(HttpStatus.OK, "T&C Accepted", PlabConstants.STATUS_SUCCESS);
            		respMap.put("Content", offerInstance.getOfferInstanceId());
            		logger.debug("OfferInstance id returned in accept is : "+ offerInstance.getOfferInstanceId());
            		return new ResponseEntity<>(respMap, HttpStatus.OK);

            	} else {																		//Only accept has to be added 
            		logger.debug("Please send a valid status in T&C");								
            		return new ResponseEntity<>(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Please send a valid status in T&C", PlabConstants.STATUS_ERROR), HttpStatus.BAD_REQUEST);
            	}
            }
            else{
            	logger.debug("Customer is either enrolled or has already accepted terms and conditions.Cannot accept/decline terms and conditions again unless terminated and has completed his current bill cycle");
            	return new ResponseEntity<>(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Customer is either enrolled or has already accepted terms and conditions.Cannot accept/decline terms and conditions again unless terminated and has completed his current bill cycle", PlabConstants.STATUS_ERROR), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            logger.error("Error in termsAndConditions" + ex.getMessage(), ex);
            return new ResponseEntity<>(utility.createResponseInfo(HttpStatus.INTERNAL_SERVER_ERROR, "Error in termsAndConditions", PlabConstants.STATUS_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            ThreadContext.clearAll();
        }
    }

	/**
	 * Accept offer to get the offer instance id.
	 * OfferInstance created only after T&C accepted for offer.
	 * offerOptionJson
	 *
	 * @return
	 */
	// @CrossOrigin (origins = "*")
	@RequestMapping(value = "/offer/{offerInstanceId}/accept", method = RequestMethod.POST)
	public ResponseEntity acceptOffer(@PathVariable("offerInstanceId") String offerInstanceId,
			@RequestBody(required = false) String offerOptionJson,
			@RequestParam(value="action",required= true) String action) {

		logger.debug("OfferController acceptOffer, offerinstance id : {}, action : {} , json : {} ",  offerInstanceId, action, offerOptionJson);
		boolean hasAlreadyPreAccepted = false; 
		String key = null;
		try {
			
			if(action == null || action.isEmpty()){
				return new ResponseEntity("Request Parameter - action is required", HttpStatus.BAD_REQUEST);
			}
			
			if(!action.equalsIgnoreCase("preaccept")  && !action.equalsIgnoreCase("success")  && !action.equalsIgnoreCase("failure") ){
				return new ResponseEntity("Invalid value passed in  Request Parameter - action. Valid values are preaccept/success/failure", HttpStatus.BAD_REQUEST);
			}
			
			key = "ACCEPT-OFFER-"+offerInstanceId; 
			
			distributedService.getHazelcastInstance().getLock(key).lock(postAcceptOfferLockPeriod, TimeUnit.SECONDS );
			
			if(action.equals("success")){
				return productManager.postAcceptOfferForSuccess(offerInstanceId);
			}else if(action.equals("failure")){
				return productManager.postAcceptOfferForFailure(offerInstanceId);
			}
			
			if (offerOptionJson == null || offerOptionJson.trim().isEmpty()) {
				logger.debug(
						"OfferController acceptOffer, offerinstance id : {}, action : {} : mdn offer options json should be given for preaccept ",
						offerInstanceId, action);
				return new ResponseEntity(
						utility.createResponseInfo(HttpStatus.BAD_REQUEST,
								"mdn offer options json should be given for preaccept", PlabConstants.STATUS_ERROR),
						HttpStatus.BAD_REQUEST);
			}
			
			//Check if the offer instance exists.
			OfferInstance offerInstance = offerInstanceRepository.findOne(Long.parseLong(offerInstanceId));

			if (offerInstance == null) {
				logger.debug("OfferInstance: " + offerInstanceId + " not found");
				return new ResponseEntity(utility.createResponseInfo(HttpStatus.NOT_FOUND, "OfferInstance: " + offerInstanceId + " not found", PlabConstants.STATUS_ERROR), HttpStatus.NOT_FOUND);
			}


			//Expire all previous instances for mdn apart from offer instance
			//productManager.expireAllInstanceForMdn(offerInstance);

			ObjectMapper mapper = productHelper.getDefaultObjectMapper();
			ObjectWriter writer = mapper.writerWithView(Views.MobileFirstView.class);

			List<String> enrolledMdn = new ArrayList<String>();
			List<String> mdnNoPlabSPO = new ArrayList<String>();
			OfferInstance updatedOfferInstance = null;
			ServiceQuestion updatedOptions = new ServiceQuestion();
			List<OfferMdnOption> selectedMdnList = null;
			VerizonEntity vzEntityOrig = offerInstance.getVerizonEntity();
			Offer offer = offerInstance.getOffer();
			String mdn = vzEntityOrig.getMdn();

			Long[] indicator = {PlabConstants.PRE_ACCEPT, PlabConstants.INVITED_IND};
			List<PlabCust> plabCustList = plabCustRepository.findByOfferAndCustIdNoAndAcctNoAndIndicator(offer.getOfferId(), vzEntityOrig.getCustIdNo(), vzEntityOrig.getAcctNo(), indicator);
			
			if (plabCustList != null && !plabCustList.isEmpty()) {
				for (PlabCust plabCustTemp : plabCustList) {
					if (plabCustTemp.getIndicator() != null
							&& plabCustTemp.getIndicator().equals(PlabConstants.PRE_ACCEPT)) {
						hasAlreadyPreAccepted = true;
						break;
					}
				}

				if (hasAlreadyPreAccepted) {
					// Roll back everything which was created during previous
					// pre-accept call
					logger.debug(
							"Found previous PLAB instances for  CustID : {} , "
									+ "AcctNo : {} , MDN : {} , Offerinstance id :  {} , Clearing them..",
							vzEntityOrig.getCustIdNo(), vzEntityOrig.getAcctNo(), mdn, offerInstanceId);
					productManager.postAcceptOfferForFailure(offerInstanceId);
				}
			}

			
			VerizonEntity vzEntity = null;

			boolean addOn = false;
			for (Service service : offer.getServices()) {
				if (service.isAddOn()){
					addOn = true;
					break;
				}
			}

			logger.debug("No of account members invited : " + plabCustList.size());

			try {
				//selectedMdnList = mdn list sent from Mobile first
				selectedMdnList = mapper.readValue(offerOptionJson, new TypeReference<List<OfferMdnOption>>() {
				});

				if (selectedMdnList != null && !selectedMdnList.isEmpty()) {
					
					boolean atleastOneSelected = false;
					for (OfferMdnOption mdnOption : selectedMdnList) {
						if(mdnOption.getOfferOptions()!=null &&  !mdnOption.getOfferOptions().isEmpty()){
							for(ServiceQuestion sq : mdnOption.getOfferOptions()){
								if(sq.getChoice()!=null && !sq.getChoice().isEmpty()){
									for(Choice choice : sq.getChoice()){
										if(choice.isSelected()){
											atleastOneSelected = true;
										}
									}
								}
							}
						}
					}
					
					if(atleastOneSelected){
					for (OfferMdnOption mdnOption : selectedMdnList) {
						OfferInstance newOfferInstance = null;
						productManager.updatePlabCustIndicator(mdnOption.getMdn(), plabCustList, PlabConstants.PRE_ACCEPT);
						if (!(mdnOption.getMdn().equals(mdn))) {
							vzEntity = vzwEntityRepository.findByMdn(mdnOption.getMdn());
							if(hasAlreadyPreAccepted){
								newOfferInstance = productManager.createNewOfferInstanceOrUpdateTcOfExisting(offer, vzEntity, offerInstance.getTermsAndConditionsStatus(),null);
							}else{
								List<OfferInstance> offerInstanceList = offerInstanceRepository.findByOfferAndVerizonEntity(offer.getOfferId(), vzEntity.getVerizonEntityId());
								OfferInstance offerInstanceTemp = null;
								if(offerInstanceList!=null && !offerInstanceList.isEmpty()){
									offerInstanceTemp = offerInstanceList.get(0); 													//only 1 active expected
								}
								newOfferInstance = productManager.createNewOfferInstanceOrUpdateTcOfExisting(offer, vzEntity, offerInstance.getTermsAndConditionsStatus(),offerInstanceTemp);
							}
							
						} else {
							vzEntity=vzEntityOrig;
							newOfferInstance = productManager.createNewOfferInstanceOrUpdateTcOfExisting(offer, vzEntity, offerInstance.getTermsAndConditionsStatus(), offerInstance);
							newOfferInstance.setEndTmstamp(PricingLabUtility.getDefaultEndTimeStamp());
						}
						Map<String, String> map = new HashMap<>();
						String newExp = "";
						boolean serviceInstanceCreate = false;
						for (ServiceQuestion question : mdnOption.getOfferOptions()) {
							for (Choice choice : question.getChoice()) {
								List<ChoiceInstance> choiceInstance = choiceInstanceRepository.findByChoiceAndOfferInstance(choice.getId(), newOfferInstance.getOfferInstanceId());
								if (choiceInstance != null && !choiceInstance.isEmpty()) {
									for (ChoiceInstance instance : choiceInstance) {
										if (instance.getChoice().getId().equals(choice.getId())) {
											instance.setChoice(choice);
											instance.setSelected(choice.isSelected());
											instance.setEndTmstamp(PricingLabUtility.getDefaultEndTimeStamp());
											choiceInstanceRepository.saveAndFlush(instance);
											if (choice.isSelected()) {
												enrolledMdn.add(mdnOption.getMdn());
												newExp += choice.getTitle() + ",";
												serviceInstanceCreate = true;
											}
										}
									}
								} else {
									//Create update new choice instance
									productManager.createNewChoiceInstance(newOfferInstance, choice);
									if (choice.isSelected()) {
										enrolledMdn.add(mdnOption.getMdn());
										newExp += choice.getTitle() + ",";
										if (!serviceInstanceCreate) {
											serviceInstanceCreate = true;
										}
									}
								}
							}
						}

						//Sending BaseService before Night Surfer Service
                        if(addOn){																																	//create base service
                            Service defaultService = serviceManager.getDefaultService();
                            ServiceInstance defaultServiceInstance = productManager.createServiceInstance(defaultService, vzEntity, newOfferInstance,null);
                            defaultServiceInstance = productManager.addEOCRuleInstance(defaultServiceInstance,vzEntity.getCustIdNo(),null);
                            newOfferInstance.getServiceInstances().add(defaultServiceInstance);
                            ServiceInstance tempServiceInstance = serviceInstanceRepository.saveAndFlush(defaultServiceInstance);
                            logger.debug("OfferController acceptOffer ( addOn) , adding defaultService defaultServiceInsatnceId : " + defaultServiceInstance.getServiceInstanceId());

                            offerInstanceRepository.saveAndFlush(newOfferInstance); //DO NOT PUT updatedOfferInstance = offerInstanceRepository.saveAndFlush(newOfferInstance); if not a single person opts for NS then we should return error
                        }

						if (serviceInstanceCreate) {
							
							String expression = newExp;
							newExp = newExp.toUpperCase();
														
							newOfferInstance.setServiceInstances(new ArrayList<ServiceInstance>());
							
							for (Service service : offer.getServices()) {	
								
								if (!addOn && newExp != null && !newExp.isEmpty()) {
									for (Rule rule : service.getRules()) {
										if (rule.getRuleType().equals(Rule.qosRuleType) && rule.getExpressionType().equals(Rule.bySpeedExpressionType)
												&& (newExp.contains("FAST") || newExp.contains("FASTER") || newExp.contains("FASTEST")))
											map.put(PlabConstants.QOS_RULETYPE_BANDWIDTH, newExp.substring(0, newExp.lastIndexOf(',')));
										else if (rule.getRuleType().equals(Rule.qosRuleType) && rule.getExpressionType().equals(Rule.byTimeExpressionType)
												&& (newExp.contains("SD") || newExp.contains("HD") || newExp.contains("UHD")))
											map.put(PlabConstants.QOS_RULETYPE_DV_FORMAT, newExp.substring(0, newExp.lastIndexOf(',')));
										else if (rule.getRuleType().equals(Rule.classficationRuleType) && rule.getExpressionType().equals(Rule.byApplicationExpressionType)) {
											String tempApp = "";
											for (String app : expression.split(","))
												tempApp += appRepository.findByName(app).getVispName() + ",";
											if (tempApp != null && !tempApp.isEmpty()) {
												tempApp = tempApp.substring(0, tempApp.lastIndexOf(','));
												map.put("social", tempApp);
											}
										}
									}
								}
								
								ServiceInstance serviceInstance = productManager.createServiceInstance(service, vzEntity, newOfferInstance,map);
								serviceInstance = productManager.addEOCRuleInstance(serviceInstance,vzEntity.getCustIdNo(),null);							
								newOfferInstance.getServiceInstances().add(serviceInstance);
								ServiceInstance tempServiceInstance = serviceInstanceRepository.saveAndFlush(serviceInstance);
								logger.debug("OfferController acceptOffer,Created serviceInstanceId : " + serviceInstance.getServiceInstanceId());
					
							}

							updatedOfferInstance = offerInstanceRepository.saveAndFlush(newOfferInstance);

						}else{																																			
							if(addOn){
								mdnNoPlabSPO.add(vzEntity.getMdn());
							}
						}
						
					}
					}else{
						return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST,"Atleast one member should opt for the plan/add on", PlabConstants.STATUS_ERROR), HttpStatus.BAD_REQUEST); 
					}
					
					//update total offerinstances
					int count = offerInstanceRepository.findTotalInstancesForOffer(offer.getOfferId());
					if (count != offer.getTotalActiveCust()) {
						offer.setTotalActiveCust(count);
					}
				} else {
					logger.error("OfferMDNOption format invalid. MDN's with list of options was empty.");
					return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "MDN's with list of options was empty.",
							PlabConstants.STATUS_ERROR), HttpStatus.BAD_REQUEST);
				}
			} catch (Exception ex) {
				logger.debug("Exception in accept offers. OfferMDNOption format invalid.", ex.getMessage(), ex);
				return new ResponseEntity(utility.createResponseInfo(HttpStatus.INTERNAL_SERVER_ERROR, "Exception in accept offers. " +
						"OfferMDNOption format invalid.No selected choices will be saved.",
						PlabConstants.STATUS_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		

			// Create final response format for MF
			if (updatedOfferInstance != null) {

				Map<String, Object> respMap = utility.createResponseInfo(HttpStatus.OK, "", PlabConstants.STATUS_SUCCESS);
				List<OfferMdnOption> mdnOptionList = productManager.createMdnSpoContent(updatedOfferInstance, enrolledMdn,mdnNoPlabSPO);
				if (mdnOptionList != null && !mdnOptionList.isEmpty()) {
					respMap.put(PlabConstants.RESPONSE_CONTENT, mdnOptionList);
				} else {
					Map<String, Object> respMapError = utility.createResponseInfo(HttpStatus.INTERNAL_SERVER_ERROR,
							"Error creating response content. MDN Options List could not be set.", PlabConstants.STATUS_ERROR);
					respMapError.put(PlabConstants.RESPONSE_CONTENT, mdnOptionList);
				}
				String jsonInString = writer.writeValueAsString(respMap);
                logger.info("Offer MDN option : " + jsonInString);
                
				return new ResponseEntity(jsonInString, HttpStatus.OK);

			} else {
				logger.debug("Instance not saved.");
				return new ResponseEntity(utility.createResponseInfo(HttpStatus.INTERNAL_SERVER_ERROR, PlabConstants.ERROR_INSTANCE_NOT_SAVED_MSG, PlabConstants.STATUS_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (Exception ex) {
			logger.error("Error accepting offer." + ex.getMessage(), ex);
			return new ResponseEntity(utility.createResponseInfo(HttpStatus.INTERNAL_SERVER_ERROR, PlabConstants.ERROR_ACCEPT_OFFER_MSG, PlabConstants.STATUS_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			if(key != null && distributedService.getHazelcastInstance().getLock(key).isLocked()){
				distributedService.getHazelcastInstance().getLock(key).unlock();
			}
			 ThreadContext.clearAll();
		}
	}
	
	
	/**
	 * This accept offer processes the mlmo to add the spo to the device
	 * OfferInstance created only after T&C accepted for offer.
	 * offerOptionJson
	 *
	 * @return
	 */
	// @CrossOrigin (origins = "*")
	@RequestMapping(value = "/offer/{offerInstanceId}/accept/mlmo", method = RequestMethod.POST)
	public ResponseEntity acceptOfferWithMlmo(@PathVariable("offerInstanceId") String offerInstanceId,
			@RequestBody(required = false) String offerOptionJson) {

		logger.debug("OfferController acceptOfferWithMlmo, offerinstance id : " + offerInstanceId);
        logger.debug("OfferController acceptOfferWithMlmo, json : " + offerOptionJson);

		try {
			//Check if the offer instance exists.
			OfferInstance offerInstance = offerInstanceRepository.findOne(Long.parseLong(offerInstanceId));

			if (offerInstance == null) {
				return new ResponseEntity(utility.createResponseInfo(HttpStatus.NOT_FOUND, "OfferInstance: " + offerInstanceId + " not found", PlabConstants.STATUS_ERROR), HttpStatus.NOT_FOUND);
			}

			//Expire all previous instances for mdn apart from offer instance
			//productManager.expireAllInstanceForMdn(offerInstance);

			ObjectMapper mapper = productHelper.getDefaultObjectMapper();
			ObjectWriter writer = mapper.writerWithView(Views.MobileFirstView.class);

			List<String> enrolledMdn = new ArrayList<String>();
			List<String> mdnNoPlabSPO = new ArrayList<String>();
			OfferInstance updatedOfferInstance = null;
			ServiceQuestion updatedOptions = new ServiceQuestion();
			List<OfferMdnOption> selectedMdnList = null;
			VerizonEntity vzEntityOrig = null;
			String selectedServiceType = null;
			List<PlabCust> plabCustList = null;
			
			if (offerOptionJson != null && !offerOptionJson.trim().isEmpty()) {                //json always expected for accept offer as confirmed by ajay on Aug 12 at 240 p.m

				VerizonEntity vzEntity = offerInstance.getVerizonEntity();
				String mdn = vzEntity.getMdn();
				vzEntityOrig=vzEntity;			//required coz offermdnoptionlist order isnt know
				Offer offer = offerInstance.getOffer();

				String serviceType = null;
				
				boolean addOn = false;
				for (Service service : offer.getServices()) {
					if (service.isAddOn())
						addOn = true;
					
					serviceType = serviceManager.getServiceType(service);
					if (!serviceType.equals("BaseService")) {
						selectedServiceType = serviceType;
					}
				}

				Long[] indicator = {PlabConstants.INVITED_IND};
				plabCustList = plabCustRepository.findByOfferAndCustIdNoAndAcctNoAndIndicator(offer.getOfferId(), vzEntity.getCustIdNo(), vzEntity.getAcctNo(), indicator);
				logger.debug("No of account members invited : " + plabCustList.size());

				try {
					//selectedMdnList = mdn list sent from Mobile first
					selectedMdnList = mapper.readValue(offerOptionJson, new TypeReference<List<OfferMdnOption>>() {
					});

					if (selectedMdnList != null && !selectedMdnList.isEmpty()) {

						for (OfferMdnOption mdnOption : selectedMdnList) {
                            //customer mdn's on the account.
						    //enrolledMdn.add(mdnOption.getMdn());

                            //check if the offer instance already exist
							OfferInstance newOfferInstance = null;
							if (!(mdnOption.getMdn().equals(mdn))) {
								vzEntity = vzwEntityRepository.findByMdn(mdnOption.getMdn());
								List<OfferInstance> offerInstanceList = offerInstanceRepository.findByOfferAndVerizonEntity(offer.getOfferId(), vzEntity.getVerizonEntityId()); 
								if(offerInstanceList!=null && !offerInstanceList.isEmpty()){
									OfferInstance offerInstanceTemp = offerInstanceList.get(0); 													//only 1 active expected
									newOfferInstance = productManager.createNewOfferInstanceOrUpdateTcOfExisting(offer, vzEntity, offerInstance.getTermsAndConditionsStatus(),offerInstanceTemp);
								}
								else{
									newOfferInstance = productManager.createNewOfferInstanceOrUpdateTcOfExisting(offer, vzEntity, offerInstance.getTermsAndConditionsStatus(),null);
								}
							} else {
								vzEntity=vzEntityOrig;
								newOfferInstance = productManager.createNewOfferInstanceOrUpdateTcOfExisting(offer, vzEntity, offerInstance.getTermsAndConditionsStatus(), offerInstance);
							}

                            //add to thread-context
                            ThreadContext.put(LoggingAspect.EVENT_PROP_CUSTOMER_ID, vzEntity.getCustIdNo()!=null?vzEntity.getCustIdNo().toString():null);
                            ThreadContext.put(LoggingAspect.EVENT_PROP_ACCOUNT_NUMBER, vzEntity.getAcctNo()!=null?vzEntity.getAcctNo().toString():null);
                            ThreadContext.put(LoggingAspect.EVENT_PROP_MDN,vzEntity.getMdn()!=null?vzEntity.getMdn().toString():null);

                            Map<String, String> map = new HashMap<>();
							String newExp = "";
							boolean serviceInstanceCreate = false;
							for (ServiceQuestion question : mdnOption.getOfferOptions()) {
								for (Choice choice : question.getChoice()) {
									List<ChoiceInstance> choiceInstance = choiceInstanceRepository.findByChoiceAndOfferInstance(choice.getId(), newOfferInstance.getOfferInstanceId());
									if (choiceInstance != null && !choiceInstance.isEmpty()) {
										for (ChoiceInstance instance : choiceInstance) {
											if (instance.getChoice().getId().equals(choice.getId())) {
												instance.setChoice(choice);
												instance.setSelected(choice.isSelected());
												choiceInstanceRepository.saveAndFlush(instance);
												if (choice.isSelected()) {
													productManager.updateIndicatorInPlabcustAndRefgridAndGrid(mdnOption.getMdn(), plabCustList, PlabConstants.ENROLLED,null);
													enrolledMdn.add(mdnOption.getMdn());
													newExp += choice.getTitle() + ",";
													serviceInstanceCreate = true;
												}
											}
										}
									} else {
										//Create update new choice instance
										productManager.createNewChoiceInstance(newOfferInstance, choice);
										if (choice.isSelected()) {
											productManager.updateIndicatorInPlabcustAndRefgridAndGrid(mdnOption.getMdn(), plabCustList, PlabConstants.ENROLLED,null);
											enrolledMdn.add(mdnOption.getMdn());
											newExp += choice.getTitle() + ",";
											if (!serviceInstanceCreate) {
												serviceInstanceCreate = true;
											}
										}
									}
								}
							}

							//Sending BaseService before Night Surfer Service
                            if(addOn){																																	//create base service
                                Service defaultService = serviceManager.getDefaultService();
                                ServiceInstance defaultServiceInstance = productManager.createServiceInstance(defaultService, vzEntity, newOfferInstance,null);
                                defaultServiceInstance = productManager.addEOCRuleInstance(defaultServiceInstance,vzEntity.getCustIdNo(),null);
                                newOfferInstance.getServiceInstances().add(defaultServiceInstance);
                                ServiceInstance tempServiceInstance = serviceInstanceRepository.saveAndFlush(defaultServiceInstance);
                                logger.debug("OfferController acceptOffer ( addOn) , adding defaultService defaultServiceInsatnceId : " + defaultServiceInstance.getServiceInstanceId());
                                if (tempServiceInstance != null) {
                                	logger.debug("Calling addVispServiceToDevice on serviceInstance :"+tempServiceInstance.getServiceInstanceId());
						    		serviceManager.addVispServiceToDevice(tempServiceInstance);
                                } else {
                                    logger.error("Service instance not created.");
                                    return new ResponseEntity(utility.createResponseInfo(HttpStatus.EXPECTATION_FAILED, "Service Instance not createddd", PlabConstants.STATUS_ERROR), HttpStatus.EXPECTATION_FAILED);
                                }

                                offerInstanceRepository.saveAndFlush(newOfferInstance); //DO NOT PUT updatedOfferInstance = offerInstanceRepository.saveAndFlush(newOfferInstance); if not a single person opts for NS then we should return error
                            }

							if (serviceInstanceCreate) {
								
								String expression = newExp;
								newExp = newExp.toUpperCase();
															
								newOfferInstance.setServiceInstances(new ArrayList<ServiceInstance>());
								
								for (Service service : offer.getServices()) {	
									
									if (!addOn && newExp != null && !newExp.isEmpty()) {
										for (Rule rule : service.getRules()) {
											if (rule.getRuleType().equals(Rule.qosRuleType) && rule.getExpressionType().equals(Rule.bySpeedExpressionType)
													&& (newExp.contains("FAST") || newExp.contains("FASTER") || newExp.contains("FASTEST")))
												map.put(PlabConstants.QOS_RULETYPE_BANDWIDTH, newExp.substring(0, newExp.lastIndexOf(',')));
											else if (rule.getRuleType().equals(Rule.qosRuleType) && rule.getExpressionType().equals(Rule.byTimeExpressionType)
													&& (newExp.contains("SD") || newExp.contains("HD") || newExp.contains("UHD")))
												map.put(PlabConstants.QOS_RULETYPE_DV_FORMAT, newExp.substring(0, newExp.lastIndexOf(',')));
											else if (rule.getRuleType().equals(Rule.classficationRuleType) && rule.getExpressionType().equals(Rule.byApplicationExpressionType)) {
												String tempApp = "";
												for (String app : expression.split(","))
													tempApp += appRepository.findByName(app).getVispName() + ",";
												if (tempApp != null && !tempApp.isEmpty()) {
													tempApp = tempApp.substring(0, tempApp.lastIndexOf(','));
													map.put("social", tempApp);
												}
											}
										}
									}
									
									ServiceInstance serviceInstance = productManager.createServiceInstance(service, vzEntity, newOfferInstance,map);
									serviceInstance = productManager.addEOCRuleInstance(serviceInstance,vzEntity.getCustIdNo(),null);							
									newOfferInstance.getServiceInstances().add(serviceInstance);
									ServiceInstance tempServiceInstance = serviceInstanceRepository.saveAndFlush(serviceInstance);
									logger.debug("OfferController acceptOffer,Created serviceInstanceId : " + serviceInstance.getServiceInstanceId());
						
									if (tempServiceInstance != null) {
										logger.debug("Calling addVispServiceToDevice on serviceInstance :"+tempServiceInstance.getServiceInstanceId());
							    		serviceManager.addVispServiceToDevice(tempServiceInstance);
									} else {
										logger.error("Service instance not created.");
										return new ResponseEntity(utility.createResponseInfo(HttpStatus.EXPECTATION_FAILED, "Service Instance not createddd", PlabConstants.STATUS_ERROR), HttpStatus.EXPECTATION_FAILED);
									}

								}

								updatedOfferInstance = offerInstanceRepository.saveAndFlush(newOfferInstance);

							}else{																																			
								if(addOn){
									mdnNoPlabSPO.add(vzEntity.getMdn());
								}
							}
							
						}
						
						// Schedule jobs for Primary Customer MDN
						
						if(loaderOneEnabled){
							PlabCust plabCustomer = plabCustRepository.findByOfferAndMdn(offer.getOfferId(),mdn);
							productManager.createJobsForCustomer(plabCustomer, enrolledMdn);
						}
						

						//update total offerinstances
						int count = offerInstanceRepository.findTotalInstancesForOffer(offer.getOfferId());
						if (count != offer.getTotalActiveCust()) {
							offer.setTotalActiveCust(count);
						}

						//addCustomerToAccActionSummary : add entry to ACCEXTERNALACTION table.
                        productManager.addCustomerToAccActionSummary(vzEntityOrig.getCustIdNo(),vzEntityOrig.getAcctNo());


					} else {
						logger.error("OfferMDNOption format invalid. MDN's with list of options was empty.");
						return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "MDN's with list of options was empty.",
								PlabConstants.STATUS_ERROR), HttpStatus.BAD_REQUEST);
					}
				} catch (Exception ex) {
					logger.debug("Exception in accept offers. OfferMDNOption format invalid.", ex.getMessage(), ex);
					return new ResponseEntity(utility.createResponseInfo(HttpStatus.INTERNAL_SERVER_ERROR, "Exception in accept offers. " +
							"OfferMDNOption format invalid.No selected choices will be saved.",
							PlabConstants.STATUS_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} else {
				logger.debug("mdn offer options json should be given");
				return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "mdn offer options json should be given", PlabConstants.STATUS_ERROR), HttpStatus.BAD_REQUEST);
			}

			// Create final response format for MF
			if (updatedOfferInstance != null) {

				Map<String, Object> respMap = utility.createResponseInfo(HttpStatus.OK, "", PlabConstants.STATUS_SUCCESS);
				List<OfferMdnOption> mdnOptionList = productManager.createMdnSpoContent(updatedOfferInstance, enrolledMdn,mdnNoPlabSPO);
				if (mdnOptionList != null && !mdnOptionList.isEmpty()) {
					respMap.put(PlabConstants.RESPONSE_CONTENT, mdnOptionList);
				} else {
					Map<String, Object> respMapError = utility.createResponseInfo(HttpStatus.INTERNAL_SERVER_ERROR,
							"Error creating response content. MDN Options List could not be set.", PlabConstants.STATUS_ERROR);
					respMapError.put(PlabConstants.RESPONSE_CONTENT, mdnOptionList);
				}
				String jsonInString = writer.writeValueAsString(respMap);
                logger.info("Offer MDN option : " + jsonInString);
                
                logger.debug("Invoke product validation call from accept offer ");
                Map<String,List<String>> mdnSpoList = new HashMap<>();
               if(mdnOptionList!=null && !mdnOptionList.isEmpty()){
            	   for(OfferMdnOption offerMdnOptionTemp : mdnOptionList){
            		   mdnSpoList.put(offerMdnOptionTemp.getMdn(), offerMdnOptionTemp.getSpos());
            	   }   
               }
        		Map<String,String> pvResponse = dvsManager.productValidation(vzEntityOrig.getCustIdNo().toString(), vzEntityOrig.getAcctNo().toString(), plabCustList,mdnSpoList,null,DvsConstants.ACTION_INDICATOR_ACCEPT);
        		if(pvResponse!=null && !pvResponse.isEmpty() && pvResponse.get(DvsConstants.ERROR_IN_RETRIEVE_CUST_PROFILE)!=null ){
					logger.debug(DvsConstants.ERROR_IN_RETRIEVE_CUST_PROFILE+" : "+pvResponse.get(DvsConstants.ERROR_IN_RETRIEVE_CUST_PROFILE));
					//return new ResponseEntity(utility.createResponseInfo(HttpStatus.MULTI_STATUS,DvsConstants.ERROR_IN_RETRIEVE_CUST_PROFILE+" : "+pvResponse.get(DvsConstants.ERROR_IN_RETRIEVE_CUST_PROFILE),PlabConstants.STATUS_SUCCESS),HttpStatus.MULTI_STATUS);
				}
				else if(pvResponse!=null && !pvResponse.isEmpty() && pvResponse.get(DvsConstants.ERROR_IN_PRODUCTVALIDATION)!=null){
					logger.debug(DvsConstants.ERROR_IN_PRODUCTVALIDATION+" : "+pvResponse.get(DvsConstants.ERROR_IN_PRODUCTVALIDATION));
					//return new ResponseEntity(utility.createResponseInfo(HttpStatus.MULTI_STATUS,DvsConstants.ERROR_IN_PRODUCTVALIDATION+" : "+pvResponse.get(DvsConstants.ERROR_IN_PRODUCTVALIDATION),PlabConstants.STATUS_SUCCESS),HttpStatus.MULTI_STATUS);
				}
				else{
					Map<String,String> mlmoResponse = dvsManager.issueMlmoRequest(pvResponse, vzEntityOrig.getCustIdNo().toString(), vzEntityOrig.getAcctNo().toString());
					if(mlmoResponse!=null && !mlmoResponse.isEmpty() && mlmoResponse.get(DvsConstants.ERROR_IN_MLMO_REQUEST)!=null){
						logger.debug(DvsConstants.ERROR_IN_MLMO_REQUEST+" : "+mlmoResponse.get(DvsConstants.ERROR_IN_MLMO_REQUEST));
						//return new ResponseEntity(utility.createResponseInfo(HttpStatus.MULTI_STATUS,DvsConstants.ERROR_IN_MLMO_REQUEST+" : "+mlmoResponse.get(DvsConstants.ERROR_IN_MLMO_REQUEST),PlabConstants.STATUS_SUCCESS),HttpStatus.MULTI_STATUS);
					}
				}
                // Call One message
                // Send a welcome message
                if(selectedServiceType !=null){
                	String oneMessageType = null;
                	if("NightSurfer".equalsIgnoreCase(selectedServiceType)){
                		oneMessageType = OneMessageType.NightSurferWelcome.toString();
                	}else if("SpeedTier".equalsIgnoreCase(selectedServiceType)){
                		oneMessageType = OneMessageType.SelectYourSpeedWelcome.toString();
                	}
                	StringBuilder mdnString = new StringBuilder();
            		for (String mdn : enrolledMdn) {
            			mdnString.append("," + mdn);
            		}
                	oneMessageService.sendMessage(oneMessageType, vzEntityOrig.getCustIdNo(), vzEntityOrig.getAcctNo(), vzEntityOrig.getMdn(), mdnString.toString().replaceFirst(",", ""));
                }
                
				return new ResponseEntity(jsonInString, HttpStatus.OK);

			} else {
				logger.debug("Instance not saved.");
				return new ResponseEntity(utility.createResponseInfo(HttpStatus.INTERNAL_SERVER_ERROR, PlabConstants.ERROR_INSTANCE_NOT_SAVED_MSG, PlabConstants.STATUS_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (Exception ex) {
			logger.error("Error accepting offer." + ex.getMessage(), ex);
			return new ResponseEntity(utility.createResponseInfo(HttpStatus.INTERNAL_SERVER_ERROR, PlabConstants.ERROR_ACCEPT_OFFER_MSG, PlabConstants.STATUS_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
		    ThreadContext.clearAll();
        }
    }


    /**
     * Loader can pass only the MDN and SPO ID details.
     * @param mdn
     * @param spoId
     * @return
     */
    @RequestMapping(value = "/visp/service/device/{mdn}/{spoId}",method = RequestMethod.POST)
    public ResponseEntity addOrUpdateServiceToDevice(@PathVariable("mdn")String mdn,@PathVariable("spoId")String spoId,
                                             @RequestParam(required = true)byte ops){
        Integer response = null;
        if (ops!=0 && spoId!=null && !spoId.isEmpty()
                && mdn !=null && !mdn.isEmpty()) {
            try {
            	logger.debug("OfferController addOrUpdateServiceToDevice() for mdn : "+mdn+" spoID : "+spoId);
                response = productManager.executeVispServiceToDevice(mdn,spoId,ops);
                logger.debug("OfferController addOrUpdateServiceToDevice() for mdn : "+mdn+" spoID : "+spoId+" response : "+response);
            } catch (Exception ex) {
                logger.error("Error in calling VISP Service from Loader. Operation : "+ ops + ", mdn : "+ mdn+" ,spo : " + spoId, ex.getMessage(),ex);
                return new ResponseEntity("Error in calling VISP Service from Loader. Operation : "+ ops + ", mdn : "+ mdn+
                        " ,spo : " + spoId,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            logger.error("Bad request. MDN, SPOID or the Operation (1-add,2-update,3-remove) is missing");
            return new ResponseEntity("Bad request. MDN, SPOID or the Operation (1-add,2-update,3-remove) is missing",HttpStatus.BAD_REQUEST);
        }
        logger.error("Successfully processed VISP service. Operation : "+ ops + ", mdn : "+ mdn+" ,spo : " + spoId + " ,response : " + response);
        return new ResponseEntity("Successfully processed VISP service. Operation : "+ ops + ", mdn : "+ mdn+" ,spo : " + spoId + " ,response : " + response,HttpStatus.OK);
    }


    /**
     * Admin method for processing accept offer.
     * @param options
     * @param mdn
     * @param offerId
     * @param pageId
     * @return
     */
    @RequestMapping(value = "/offer/{offerId}/{mdn}/accept/{pageId}/{options}")
    public ResponseEntity acceptOfferWithPageId(@PathVariable("options") List<Boolean> options, @PathVariable("mdn") String mdn,
                                                @PathVariable("offerId") String offerId, @PathVariable("pageId") String pageId) {

        logger.debug("OfferController acceptOfferWithPageId() for offer id : " + offerId + ", mdn : " + mdn+" pageID : "+pageId);

        int termsAndCondStatus = isTermsAndConditonsAccepted(offerId, mdn);
        //validate T&C Accepted or not
        if (termsAndCondStatus == -2 || NO_STATUS == isTermsAndConditonsAccepted(offerId, mdn)) {
            logger.error("Please accept T&C for offer for mdn : " + mdn);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please accept T&C for offer for mdn : " + mdn);
        }

        Offer offer = repository.findByOfferId(Long.getLong(offerId));
        // Offer offer= repository.findOne(offerId);

        VerizonEntity vzEntity = vzwEntityRepository.findByMdn(mdn);
        if (vzEntity == null)
            vzEntity = new VerizonEntity(mdn);
        vzwEntityRepository.saveAndFlush(vzEntity);

        if (offer == null){
        	logger.debug("acceptOfferWithPageId() : Invalid offer number !"); 
            return new ResponseEntity<>("Invalid offer number !", HttpStatus.BAD_REQUEST);
        }

        List<OfferInstance> offerInstances = offerInstanceRepository.findByOfferAndVerizonEntity(offer.getOfferId(), vzEntity.getVerizonEntityId());
        OfferInstance offerInstance = offerInstances.get(0);
        offerInstance = new OfferInstance(offer, vzEntity);
        for (Service service : offer.getServices()) {
            service.setDateCreated(new Date());
            ServiceInstance serviceInstance = new ServiceInstance(service, vzEntity, offerInstance);
            serviceInstance.setDateCreated(new Date());
            offerInstance.getServiceInstances().add(serviceInstance);
            serviceManager.addVispServiceToDevice(serviceInstance);
            serviceInstanceRepository.saveAndFlush(serviceInstance);
        }
        offerInstanceRepository.saveAndFlush(offerInstance);
        repository.saveAndFlush(offer);
        logger.debug("acceptOfferWithPageId() : "+offer); 
        return new ResponseEntity<>(HttpStatus.OK);

    }

    //update offer instance
    // @CrossOrigin (origins = "*")
    @RequestMapping(value = "/offer/update/{offerInstanceId}/{speed}", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity updateOfferInstance(@PathVariable("offerInstanceId") String offerInstanceId, @PathVariable("speed") String speed) {
        if (offerInstanceId != null && offerInstanceId != null) {

            try {
            	logger.debug("updateOfferInstance() : offerInstanceId : "+offerInstanceId);
            	logger.debug("updateOfferInstance() : speed : "+speed);
                //Get Offer Instance
                OfferInstance offerInstance = offerInstanceRepository.findOne(Long.parseLong(offerInstanceId));
                if (offerInstance == null) {
                    logger.debug("No Offer instance found ");
                    return new ResponseEntity("Offer instance is null or empty !", HttpStatus.NOT_FOUND);
                }

                ServiceInstance serviceInstance = offerInstance.getServiceInstances().get(0);

                List<RuleInstance> ruleInstances = serviceInstance.getRules();
                for (RuleInstance ruleInstance : ruleInstances) {
                    if (ruleInstance.getRuleType().equals(Rule.qosRuleType)) {
                        Map<String, String> map = ruleInstance.getAttributes();
                        map.put("bandwidth", speed);
                        ruleInstance.setAttributes(map);
                    }
                }
                serviceInstanceRepository.saveAndFlush(serviceInstance);
                serviceManager.updateVispServiceToDevice(serviceInstance);
                logger.debug("updateOfferInstance() : "+serviceInstance.getVispServiceID());
                return new ResponseEntity<String>(serviceInstance.getVispServiceID(), HttpStatus.OK);
            } catch (Exception ex) {
                logger.error("Error in termsAndConditions" + ex.getMessage(), ex);
                return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
        	logger.debug("updateOfferInstance() : Invalid parameter passed !");
            return new ResponseEntity("Invalid parameter passed !", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/offer/{offerId}/{mdn}/survey/answers", method = RequestMethod.GET)
    public ResponseEntity getSurveyAnswers(@PathVariable(value = "mdn") Long mdn,@PathVariable(value = "offerId") Long offerId) {

		logger.debug("OfferController getSurveyAnswers() for mdn : "+mdn);
		logger.debug("OfferController getSurveyAnswers() for offerId : "+offerId);

        if (mdn != null && offerId!=null) {

            VerizonEntity entity = vzwEntityRepository.findByMdn(mdn.toString());
			List<OfferInstance> offerInstances = offerInstanceRepository.findByOfferAndVerizonEntity(offerId,entity.getVerizonEntityId());
			OfferInstance offerInstance = null;
			if (offerInstances!=null && !offerInstances.isEmpty()) {
				offerInstance = offerInstances.get(0);
			}

            List<SurveyAnswer> surveyAnswers = surveyAnswerRepository.findByVerizonEntity(entity);
			for (SurveyAnswer surveyAnswer : surveyAnswers) {
				SurveyQuestion surveyQuestion = surveyAnswer.getSurveyQuestion();
				if (surveyQuestion!=null && offerInstance!=null) {
				    List<ChoiceInstance> choiceInstances = offerInstance.getChoiceInstances();
                    //update if survey answers are not updated.
                    for (Choice choice : surveyQuestion.getChoice()) {
                        for (ChoiceInstance choiceInstance : choiceInstances) {
                            if (choice.getId().equals(choiceInstance.getChoice().getId())) {
                                //update choices for given choice instance
                                choice.setSelected(choiceInstance.getSelected());
                            }
                        }
                    }
                }
			}
            if (surveyAnswers != null && !surveyAnswers.isEmpty()) {
            	ObjectMapper mapper = productHelper.getDefaultObjectMapper();
				ObjectWriter writer = mapper.writerWithView(Views.MobileFirstView.class);
                try {
                    String surveyJson = writer.writeValueAsString(surveyAnswers);
                    logger.debug("OfferController getSurveyAnswers() for mdn : "+mdn+", response json is : "+surveyJson);
                    return new ResponseEntity(surveyJson, HttpStatus.OK);
                } catch (JsonProcessingException jpe) {
                    logger.debug("Json Parse Exception", jpe.getMessage(), jpe);
                    return new ResponseEntity("Exception while parsing, mdn : " + mdn, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }
        logger.debug("Survey answers not found for mdn : " + mdn);
        return new ResponseEntity("Survey answers not found for mdn : " + mdn, HttpStatus.NOT_FOUND);
    }

    /**
     * Partial Survey Answers can be submitted from the MF.
     * On every click of next button answers will be stored on survey tables.
     * @param offerId
     * @param mdn
     * @param surveyJson
     * @return
     */
    @RequestMapping (value = "/offer/instance/{offerId}/{mdn}/partsurvey",method = RequestMethod.POST)
    public ResponseEntity submitPartialSurveyAnswers (@PathVariable(value = "offerId") Long offerId,
                                                      @PathVariable(value = "mdn") String mdn,
                                                      @RequestBody(required = true) String surveyJson) {
    	logger.debug("submitPartialSurveyAnswers() : offerID : "+offerId);
    	logger.debug("submitPartialSurveyAnswers() : mdn : "+mdn);
    	logger.debug("submitPartialSurveyAnswers() : surveyJson : "+surveyJson);

        if (offerId!=null && mdn !=null && !mdn.isEmpty()
                && surveyJson!=null && !surveyJson.isEmpty()) {
            ObjectMapper mapper = productHelper.getDefaultObjectMapper();
            List<SurveyQuestion> surveyQuestionList = null;
            //find the offer instances for given mdn and offer id.
            Offer offer = repository.findByOfferId(offerId);
            VerizonEntity entity = vzwEntityRepository.findByMdn(mdn);
            List<OfferInstance> offerInstances = offerInstanceRepository.findByOfferAndVerizonEntity(offer.getOfferId(), entity.getVerizonEntityId());

            try {
                surveyQuestionList = mapper.readValue(surveyJson, new TypeReference<List<SurveyQuestion>>() {
                });
            } catch (Exception ex) {
                logger.debug("Unable to parse surveyAnswers ! Continue to parse either choice or SurveyQuestion.");
            }


        } else {
            logger.debug("Unable to process request. Offer ID : {}, mdn : {}, JSON : {}",offerId,mdn,surveyJson);
            return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Unable to process request. Survey JSON is empty.", "Error"), HttpStatus.BAD_REQUEST);
        }
        
        logger.debug("submitPartialSurveyAnswers() : Unable to process request.");
        return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Unable to process request.", "Error"), HttpStatus.BAD_REQUEST);
    }

    /**
     * Submit Survey Answers
     *
     * @param offerId
     * @param mdn
     * @param surveyJson
     * @return
     */
    @RequestMapping(value = "/offer/instance/{offerId}/{mdn}/submitsurvey", method = RequestMethod.POST)
    public ResponseEntity submitSurveyAnswer(@PathVariable(value = "offerId") Long offerId,
                                             @PathVariable(value = "mdn") String mdn,
                                             @RequestBody(required = true) String surveyJson) {

        List<String> errors = new ArrayList<>();
        logger.debug("submitSurveyAnswer() for offerID : "+offerId+", mdn : "+mdn+",reqeust surveyJson : "+surveyJson);

        if (offerId != null && mdn != null && surveyJson != null) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            ObjectMapper mapper = new ObjectMapper();
            mapper.setDateFormat(df);

            Offer offer = repository.findByOfferId(offerId);
            VerizonEntity entity = vzwEntityRepository.findByMdn(mdn);
            List<OfferInstance> offerInstances = offerInstanceRepository.findByOfferAndVerizonEntity(offer.getOfferId(), entity.getVerizonEntityId());
            OfferInstance offerInstance = null;
            List<Survey> surveyList = null;

            if (offerInstances != null && !offerInstances.isEmpty()) {
                offerInstance = offerInstances.get(0);
                surveyList = offerInstance.getOffer().getSurveys();
            } else {
            	logger.debug("submitSurveyAnswer() : Offer Instance Not Found, Please accept Offer");
                return new ResponseEntity(utility.createResponseInfo(HttpStatus.NOT_FOUND, "Offer Instance Not Found, Please accept Offer", "Error"), HttpStatus.NOT_FOUND);
            }

            List<SurveyAnswer> surveyAnswersFromJson = null;
            List<Choice> choicesFromJson = null;
            List<SurveyQuestion> surveyQuestionsFromJson = null;
            List<Choice> choicesFromOffer = new ArrayList<Choice>();
            List<SurveyQuestion> surveyQuestionsFrmOffer = new ArrayList<SurveyQuestion>();
            int surveyQuestionsavedsize = 0;

            for (Survey survey : surveyList) {
                for (SurveyQuestion surveyQuestion : survey.getQuestions()) {
                    surveyQuestionsFrmOffer.add(surveyQuestion);
                    for (Choice choiceTemp : surveyQuestion.getChoice()) {
                        choicesFromOffer.add(choiceTemp);
                    }
                }
            }

            try {
                surveyAnswersFromJson = mapper.readValue(surveyJson, new TypeReference<List<SurveyAnswer>>() {
                });
            } catch (Exception ex) {
                logger.debug("Unable to parse surveyAnswers ! Continue to parse either choice or SurveyQuestion.");
            }
            if (surveyAnswersFromJson != null && !surveyAnswersFromJson.isEmpty()) {
                logger.debug("Entered survey answer block");
                for (SurveyAnswer surveyAnswer : surveyAnswersFromJson) {
                    for (SurveyQuestion surveyQuestion : surveyQuestionsFrmOffer) {
                        logger.debug("Comparing: " + surveyAnswer.getSurveyQuestion().getName() + ";" + surveyQuestion.getName() + ";" + surveyAnswer.getSurveyQuestion().equals(surveyQuestion.getName()));
                        if (surveyAnswer.getSurveyQuestion().getName().equals(surveyQuestion.getName())) {
                            surveyAnswer.setSurveyQuestion(surveyQuestion);
                            surveyAnswer.setDateCreated(new Timestamp(new Date().getTime()));
                            surveyAnswer.setVerizonEntity(offerInstance.getVerizonEntity());
                            surveyAnswerRepository.saveAndFlush(surveyAnswer);
                            surveyQuestionsavedsize = surveyQuestionsavedsize + 1;
                        }
                    }
                }
                if (surveyAnswersFromJson.size() != surveyQuestionsavedsize && surveyQuestionsavedsize != 0)
                    errors.add("Not all questions given could be matched for that survey,hence some/all of them could not be saved.Please check again");
                else if (surveyAnswersFromJson.size() != surveyQuestionsavedsize && surveyQuestionsavedsize == 0){
                	logger.debug("Survey Answers could not be submitted!Question mismatch");
                    return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Survey Answers could not be submitted!Question mismatch", "Error"), HttpStatus.BAD_REQUEST);
                }

            } else {                        ///enter only if prior survey answer could not be read
                try {
                    choicesFromJson = mapper.readValue(surveyJson, new TypeReference<List<Choice>>() {
                    });
                } catch (Exception ex) {
                    logger.debug("Unable to parse choices ! Continue to parse SurveyQuestion format.");
                }
                if (choicesFromJson != null && !choicesFromJson.isEmpty()) {                                                            //choice only format
                    int returnFlag = 0;
                    for (Survey survey : surveyList) {
                        for (SurveyQuestion surveyQuestion : survey.getQuestions()) {
                            int choiceInstanceSize = 0;
                            for (Choice choice : surveyQuestion.getChoice()) {
                                for (Choice choiceJson : choicesFromJson) {
                                    if (choice.getId().equals(choiceJson.getId())) {
                                        List<ChoiceInstance> existingChoiceInstanceList = null;
                                        choiceInstanceRepository.findByChoiceAndOfferInstance(choice.getId(), offerInstance.getOfferInstanceId());
                                        if (existingChoiceInstanceList != null && !existingChoiceInstanceList.isEmpty()) {            //update the existing choice if it already exists
                                            ChoiceInstance existingChoiceInstance = existingChoiceInstanceList.get(0);            //only 1 choice instance expected for a particular choice id and offerinstance
                                            existingChoiceInstance.setChoice(choice);
                                            existingChoiceInstance.setSelected(choiceJson.isSelected());
                                            existingChoiceInstance.setOfferInstance(offerInstance);
                                            choiceInstanceRepository.saveAndFlush(existingChoiceInstance);
                                            choiceInstanceSize = choiceInstanceSize + 1;
                                            returnFlag = returnFlag + 1;
                                        } else {
                                            ChoiceInstance choiceInstance = new ChoiceInstance();
                                            choiceInstance.setChoice(choice);
                                            choiceInstance.setSelected(choiceJson.isSelected());
                                            choiceInstance.setOfferInstance(offerInstance);
                                            choiceInstance.setDateCreated(new Timestamp(new Date().getTime()));
                                            choiceInstanceRepository.saveAndFlush(choiceInstance);
                                            choiceInstanceSize = choiceInstanceSize + 1;
                                            returnFlag = returnFlag + 1;
                                        }
                                    }
                                }
                            }
                            if (choiceInstanceSize != surveyQuestion.getChoice().size())    //few choices may have been saved
                                errors.add("Not all the choice instances could be persisted for " + surveyQuestion.getName().toUpperCase() + " Please check the choice ids given in the survey json'");
                        }
                    }
                    if (returnFlag == 0){                                                    //not a single choice could be saved
                    	logger.debug("Choice Answers could not be submitted!Choice Id  mismatch");
                        return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Choice Answers could not be submitted!Choice Id  mismatch", "Error"), HttpStatus.BAD_REQUEST);
                    }
                } else {                    /////enter only if prior survey answer,choice could not be read
                    try {
                        surveyQuestionsFromJson = mapper.readValue(surveyJson, new TypeReference<List<SurveyQuestion>>() {
                        });
                    } catch (Exception ex) {
                        logger.debug("Unable to parse SurveyQuestions! Please enter a valid format");
                        return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Unable to parse json", "Error"), HttpStatus.BAD_REQUEST);
                    }
                    int surveyAnswersSizeFromJson = 0;
                    int savedSurveyAnswersSize = 0;
                    int savedChoices = 0;
                    int returnFlag = 0;
                    if (surveyQuestionsFromJson != null && !surveyQuestionsFromJson.isEmpty()) {                                            //survey question format--> could contain both survey answer and choice
                        choicesFromJson = new ArrayList<Choice>();

                        for (SurveyQuestion surveyQuestionJson : surveyQuestionsFromJson) {
                            if (surveyQuestionJson.getChoice() != null) {
                                for (Choice choiceTemp : surveyQuestionJson.getChoice()) {
                                    choicesFromJson.add(choiceTemp);
                                }
                            }
                            if (surveyQuestionJson.getSurveyAnswers() != null) {
                                surveyAnswersSizeFromJson = surveyQuestionJson.getSurveyAnswers().size() + surveyAnswersSizeFromJson;
                            }
                        }

                        if (choicesFromJson != null && !choicesFromJson.isEmpty()) {
                            for (Choice choice : choicesFromOffer) {
                                for (Choice choiceJson : choicesFromJson) {
                                    if (choice.getId().equals(choiceJson.getId())) {
                                        List<ChoiceInstance> existingChoiceInstanceList = choiceInstanceRepository.findByChoiceAndOfferInstance(choice.getId(), offerInstance.getOfferInstanceId());
                                        if (existingChoiceInstanceList != null && !existingChoiceInstanceList.isEmpty()) {            //update the existing choice if it already exists
                                            ChoiceInstance existingChoiceInstance = existingChoiceInstanceList.get(0);            //only 1 choice instance expected for a particular choice id and offerinstance
                                            existingChoiceInstance.setChoice(choice);
                                            existingChoiceInstance.setSelected(choiceJson.isSelected());
                                            existingChoiceInstance.setOfferInstance(offerInstance);
                                            choiceInstanceRepository.saveAndFlush(existingChoiceInstance);
                                            savedChoices = savedChoices + 1;
                                            returnFlag = returnFlag + 1;
                                        } else {
                                            ChoiceInstance choiceInstance = new ChoiceInstance();
                                            choiceInstance.setChoice(choice);
                                            choiceInstance.setSelected(choiceJson.isSelected());
                                            choiceInstance.setOfferInstance(offerInstance);
                                            choiceInstance.setDateCreated(new Timestamp(new Date().getTime()));
                                            choiceInstanceRepository.saveAndFlush(choiceInstance);
                                            savedChoices = savedChoices + 1;
                                            returnFlag = returnFlag + 1;
                                        }
                                    }
                                }
                            }
                            if (savedChoices != 0 && choicesFromJson.size() != savedChoices) //some of the given choices saved
                                errors.add("Not all the choice instances could be persisted for surveyQuestion format.Few choice id's mismatch'");
                        }
                        if (choicesFromJson != null && !choicesFromJson.isEmpty() && savedChoices == 0)
                            errors.add("Choice Answers could not be submitted!Choice Id  mismatch'");

                        for (SurveyQuestion surveyQuestion : surveyQuestionsFrmOffer) {
                            for (SurveyQuestion surveyQuestionJson : surveyQuestionsFromJson) {
                                if ((surveyQuestion.getSelectionPageId() != null && surveyQuestion.getSelectionPageId().equals(surveyQuestionJson.getSelectionPageId()))
                                        || (surveyQuestion.getName() != null && surveyQuestion.getName().equalsIgnoreCase(surveyQuestionJson.getName()))
                                        || (surveyQuestion.getTitle() != null && surveyQuestion.getTitle().equalsIgnoreCase(surveyQuestionJson.getTitle()))) {
                                    if (surveyQuestionJson.getSurveyAnswers() != null) {
                                        for (SurveyAnswer surveyAnswer : surveyQuestionJson.getSurveyAnswers()) {
                                            List<SurveyAnswer> surveyAnswersList = surveyAnswerRepository.findByVerizonEntityAndSurveyQuestion(offerInstance.getVerizonEntity(), surveyQuestion);
                                            if (surveyAnswersList != null && !surveyAnswersList.isEmpty()) {                    //update existing survey answers if they exist
                                                SurveyAnswer existingSurveyAnswer = surveyAnswersList.get(0);
                                                existingSurveyAnswer.setCustSurveyAnswers(surveyAnswer.getCustSurveyAnswers());
                                                existingSurveyAnswer.setSurveyQuestion(surveyQuestion);
                                                existingSurveyAnswer.setVerizonEntity(offerInstance.getVerizonEntity());
                                                surveyAnswerRepository.saveAndFlush(existingSurveyAnswer);
                                                returnFlag = returnFlag + 1;
                                                savedSurveyAnswersSize = savedSurveyAnswersSize + 1;
                                            } else {
                                                surveyAnswer.setSurveyQuestion(surveyQuestion);
                                                surveyAnswer.setDateCreated(new Timestamp(new Date().getTime()));
                                                surveyAnswer.setVerizonEntity(offerInstance.getVerizonEntity());
                                                surveyAnswerRepository.saveAndFlush(surveyAnswer);
                                                returnFlag = returnFlag + 1;
                                                savedSurveyAnswersSize = savedSurveyAnswersSize + 1;
                                            }
                                        }
                                    }
                                }
                            }
                        }


                        if (surveyAnswersSizeFromJson != savedSurveyAnswersSize && surveyAnswersSizeFromJson != 0 && savedSurveyAnswersSize != 0)
                            errors.add("Not all Survey answers could be submitted!SurveyQuestion name mismatch in surveyQuestion format");

                        if (returnFlag == 0){//if a single survey answer or choice could not be saved
                        	logger.debug("Choice/Survey answers could not be submitted!Choice Id/SurveyQuestion name mismatch");
                            return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Choice/Survey answers could not be submitted!Choice Id/SurveyQuestion name mismatch", "Error"), HttpStatus.BAD_REQUEST);
                        }
                    }
                }
            }

            if (errors != null && !errors.isEmpty()) {
                ResponseInfo rI = new ResponseInfo("207", "Multi Status", "", "Error");
                Map<String, Object> respMap = utility.createResponseInfo(HttpStatus.MULTI_STATUS, "", "Success");
                respMap.put("Content", errors);
                logger.debug(respMap);
                return new ResponseEntity(respMap, HttpStatus.MULTI_STATUS);
            } else {
            	logger.debug("Survey Answers submitted!");
                return new ResponseEntity(utility.createResponseInfo(HttpStatus.OK, "Survey Answers submitted!", "Success"), HttpStatus.OK);
            }
        }
        logger.debug("Offer ID, MDN, or Survey JSON is null");
        return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Offer ID, MDN, or Survey JSON is null", "Error"), HttpStatus.BAD_REQUEST);
    }

    // Delete an offer
    @RequestMapping(value = "/offer", method = RequestMethod.DELETE)
    public ResponseEntity removeOffer(@RequestParam(value = "offerId", defaultValue = "0") String offerId,
                                      @RequestParam(value = "min", defaultValue = "0") Long min,
                                      @RequestParam(value = "max", defaultValue = "0") Long max) {

        if (offerId != null && Integer.parseInt(offerId) != 0) {
            logger.debug("OfferController removeOffer offerId " + offerId);
            
            logger.debug("OfferController removeOffer min " + min);
            logger.debug("OfferController removeOffer max " + max);

            // Offer rule = repository.findOne(offerId);
            Offer offer = repository.findByOfferId(Long.parseLong(offerId));

            if (offer != null) {
                repository.delete(offer);
            } else {
            	logger.debug("Offer not found !");
                return new ResponseEntity("Offer not found !", HttpStatus.NOT_FOUND);
            }
        } else {
            if (min != null && min.intValue() > 0
                    && max != null && max.intValue() > 0) {
                for (long i = min; i <= max; i++) {
                    // Offer rule = repository.findOne(offerId);
                    Offer offer = repository.findByOfferId(i);
                    boolean deleted = false;
                    try {
                        if (offer != null) {
                            repository.delete(offer);
                            repository.flush();
                            logger.debug("Deleted offer : " + i);
                        }
                    } catch (Exception ex) {
                        logger.debug("Exception deleting offer id : " + i, ex.getMessage(), ex);
                        continue;
                    }
                }
            }
        }
        logger.debug("Deleted offer : " + offerId);
        return new ResponseEntity("Deleted offer : " + offerId, HttpStatus.OK);
    }

    // Deassociate an Service from an offer
    @RequestMapping(value = "/offer/service", method = RequestMethod.DELETE)
    public ResponseEntity removeServiceFromOffer(@RequestParam(value = "serviceId", defaultValue = "0") String serviceId,
                                                 @RequestParam(value = "offerId", defaultValue = "0") String offerId) {

        // Offer offer = repository.findOne(offerId);
        // Service service = serviceRepository.findOne(serviceId);

        logger.debug("OfferController removeServiceFromOffer offerId " + offerId + " serviceId " + serviceId);

        Offer offer = repository.findByOfferId(Long.getLong(offerId));
        Service service = serviceRepository.findByServiceId(Long.getLong(serviceId));

        offer.getServices().remove(service);
        repository.saveAndFlush(offer);
        logger.debug("removeServiceFromOffer() : "+offer);
        return new ResponseEntity(offer, HttpStatus.OK);
    }

    /**
     * Exit offer and cascade all references for all offerinstance under an account.
     *
     * @param offerInstanceId
     * @return ResponseEntity
     */
    @RequestMapping(value = "/offer/instance/{offerInstanceId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeOfferInstance(@PathVariable(value = "offerInstanceId") Long offerInstanceId) {
    	logger.debug("removeOfferInstance() for offerInstanceId : "+offerInstanceId);
        try {
            if (offerInstanceId != null) {
                OfferInstance offerInstance = offerInstanceRepository.findOne(offerInstanceId);
                if (offerInstance != null) {
                    //Update offer instance in PLAB_CUST
                    List<PlabCust> plabCusts = plabCustRepository.findByOfferAndCustIdNoAndAcctNo(offerInstance.getOffer()
                            , offerInstance.getVerizonEntity().getCustIdNo()
                            , offerInstance.getVerizonEntity().getAcctNo());

                    for (PlabCust cust : plabCusts) {
                        cust.setIndicator(PlabConstants.INVITED_IND);

                        plabCustRepository.save(cust);

                        //Update offer instance in REF_GRID
                        RefGridPlabCustPK refGridPlabCustPK = new RefGridPlabCustPK(cust.getCustIdNo(), cust.getAcctNo(), cust.getMdn());
                        RefGridPlabCust refGridPlabCust = refGridPlabCustRepository.findOne(refGridPlabCustPK);
                        refGridPlabCust.setIndicator(PlabConstants.INVITED_IND);

                        refGridPlabCustRepository.save(refGridPlabCust);
                    }

                    //delete all second instance
                    List<OfferInstance> offerInstancesByAccount = offerInstanceRepository.
                            findOfferInstancesByCustomerAccount(offerInstance.getOffer().getOfferId(),
                                    offerInstance.getVerizonEntity().getCustIdNo(), offerInstance.getVerizonEntity().getAcctNo());

                    for (OfferInstance instance : offerInstancesByAccount) {
                        for (ServiceInstance serviceInstance : offerInstance.getServiceInstances()) {
                            serviceManager.removeServiceFromDevice(serviceInstance);
                        }
                        offerInstanceRepository.delete(instance);
                        logger.debug("Deleted offer instance : " + instance.getOfferInstanceId());
                    }

                    return new ResponseEntity(utility.createResponseInfo(HttpStatus.OK,
                            "Deleted offer instance : " + offerInstanceId, PlabConstants.STATUS_SUCCESS), HttpStatus.OK);
                } else {
                    logger.debug("Offer Instance not found ! : " + offerInstanceId);
                    return new ResponseEntity(utility.createResponseInfo(HttpStatus.NOT_FOUND,
                            "Offer Instance not found ! : " + offerInstanceId, PlabConstants.STATUS_ERROR), HttpStatus.NOT_FOUND);
                }
            } else {
                logger.debug("Offer Instance cannot be null ! : " + offerInstanceId);
                return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST,
                        "Offer Instance cannot be null ! : " + offerInstanceId, PlabConstants.STATUS_ERROR), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            logger.debug("Exception in remove offer intance, instance id : " + offerInstanceId, ex.getMessage(), ex);
            return new ResponseEntity(utility.createResponseInfo(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Exception in remove offer intance, instance id : " + offerInstanceId, PlabConstants.STATUS_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Called by CPI to update offer with survey, T&C (simple pages), and offer details (simple pages)
     *
     * @param jsonOffer
     * @return
     */
    @RequestMapping(value = "/offer/update", method = RequestMethod.POST)
    public ResponseEntity updateOffer(@RequestBody(required = true) String jsonOffer) {

        Offer offer = null;
        Offer updatedOffer = null;
        try {

            if (jsonOffer != null && !jsonOffer.trim().isEmpty()) {
                offer = mapper.readValue(jsonOffer, Offer.class);
                logger.debug("update offer, jsonOffer : " + jsonOffer);
            }

            updatedOffer = repository.findByOfferId(offer.getOfferId());

            if (offer != null && updatedOffer != null) {
                updatedOffer.setCharge(offer.getCharge());
                updatedOffer.setMdnOptionEnabled(offer.isMdnOptionEnabled());
                updatedOffer.setIsEnrolled(offer.getIsEnrolled());
                updatedOffer.setName(offer.getName());
                updatedOffer.setStartDate(offer.getStartDate());
                updatedOffer.setEndDate(offer.getEndDate());
                updatedOffer.setDateCreated(updatedOffer.getDateCreated() != null ? updatedOffer.getDateCreated() : new Date());
                List<Long> ids = new ArrayList<Long>();
                for (Service service : offer.getServices()) {
                    ids.add(service.getServiceId());
                }
                updatedOffer.setServices(serviceRepository.findAll(ids));
                Set<Application> apps = new HashSet<Application>();
                for (Service service : updatedOffer.getServices()) {
                    for (Application app : service.getAppsClassification()) {
                        Application serviceApp = appRepository.findByName(app.getName());
                        apps.add(serviceApp);
                    }
                    service.setAppsClassification(apps);

                    for (Service newService : offer.getServices()) {
                        if (service.getServiceId()!=null && newService.getServiceId()!=null &&
                                service.getServiceId().equals(newService.getServiceId().longValue())) {
                            service.setAddOn(newService.isAddOn());
                        }
                    }
                }

			/*
			 * update offer options api needs to be called to update offer options
			 */
			/*for(int i = 0; i < offer.getOfferOptions().size(); i++ ){
				ServiceQuestion sp = (ServiceQuestion) selectionPageRepository.findOne(offer.getOfferOptions().get(i).getSelectionPageId());
				sp.setOffer(updatedOffer);
				for(int j = 0; j < sp.getChoice().size(); j+=1){
					sp.getChoice().get(j).setServiceQuestion(offer.getOfferOptions().get(i));
				}
			}
			updatedOffer.setOfferOptions(offer.getOfferOptions());
			*/
                //Remove SimplePages from offer
                for (SimplePage sp : updatedOffer.getSimplePages()) {
                    sp.setOffer(null);
                }
                updatedOffer.setSimplePages(null);
                //Add updated SimplePages to Offer
                if (offer.getSimplePages() != null) {
                    for (int i = 0; i < offer.getSimplePages().size(); i++) {
                        offer.getSimplePages().get(i).setOffer(updatedOffer);
                    }
                    updatedOffer.setSimplePages(offer.getSimplePages());
                }
                //Remove Surveys from Offer
                for (Survey s : updatedOffer.getSurveys()) {
                    s.setOffer(null);
                    for (SurveyQuestion q : s.getQuestions()) {
                        q.setSurvey(null);
                        for (Choice c : q.getChoice()) {
                            c.setSurveyQuestion(null);
                        }
                    }
                }
                updatedOffer.setSurveys(null);

                //FIXME : Added below line to check order of insertion.
                repository.saveAndFlush(updatedOffer);

                //Add updated Surveys to Offer
                if (offer.getSurveys() != null) {
                    for (Survey survey : offer.getSurveys()) {
                        survey.setOffer(updatedOffer);
                        for (SurveyQuestion sQ : survey.getQuestions()) {
                            sQ.setSurvey(survey);
                            for (Choice choice : sQ.getChoice()) {
                                choice.setSurveyQuestion(sQ);
                            }
                            List<Choice> orderedChoices = new LinkedList<Choice>(sQ.getChoice());
							sQ.setChoice(orderedChoices);
                        }
                    }
                }

                updatedOffer.setSurveys(offer.getSurveys());

                repository.saveAndFlush(updatedOffer);

                logger.debug(" Offer updated, id=" + offer.getOfferId());
                return new ResponseEntity("offer updated for the given offerid: " + offer.getOfferId(), HttpStatus.OK);
            } else {
                logger.debug("Offer not updated. Offer Json is null or empty");
                return new ResponseEntity("offer not found", HttpStatus.NOT_FOUND);
            }

        } catch (JsonParseException e) {
            logger.error("JsonParseException in updateOffer() : ", e.getMessage(), e);
            return new ResponseEntity("JsonParseException in updateOffer() ", HttpStatus.BAD_REQUEST);
        } catch (JsonMappingException e) {
            logger.error("JsonMappingException in updateOffer() : ", e.getMessage(), e);
            return new ResponseEntity("JsonParseException in updateOffer() ", HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            logger.error("IOException in updateOffer() : ", e.getMessage(), e);
            return new ResponseEntity("IOException in updateOffer() ", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update offer options for a given offerid and mdn.
     *
     * @param offerId          - getOfferInstanceRest to get the offerInstanceId based on offer and mdn.
     * @param mdn
     * @param offerOptionsJson - list of options (service questions)
     * @return
     */
    @RequestMapping(value = "/offer/{offerId}/{mdn}/update/options", method = RequestMethod.POST)
    public ResponseEntity updateOfferOptions(@PathVariable("offerId") Long offerId,
                                             @PathVariable("mdn") String mdn,
                                             @RequestBody(required = true) String offerOptionsJson) {
    	
    	logger.debug("updateOfferOptions() : offerId : "+offerId);
    	logger.debug("updateOfferOptions() : mdn : "+mdn);
    	logger.debug("updateOfferOptions() : offerOptionsJson"+offerOptionsJson);
    	
        if (offerId == null || mdn == null || offerOptionsJson == null){
        	logger.debug("updateOfferOptions() : Invalid request (offerId,mdn, or offer options invalid)"); 
            return new ResponseEntity<>(utility.createResponseInfo(HttpStatus.NOT_FOUND, "Invalid request (offerId,mdn, or offer options invalid)", PlabConstants.STATUS_ERROR), HttpStatus.BAD_REQUEST);
        }
        else {
            Offer offer = repository.findByOfferId(offerId);
            VerizonEntity entity = vzwEntityRepository.findByMdn(mdn);
            List<OfferInstance> offerInstances = offerInstanceRepository.findByOfferAndVerizonEntity(offer.getOfferId(), entity.getVerizonEntityId());
            OfferInstance offerInstance = null;
            boolean addOn=false;
            for(Service service : offer.getServices()){
            	if(service.isAddOn()){
            		addOn=true;
            	}
            }

            if (offerInstances != null && !offerInstances.isEmpty()) {
                offerInstance = offerInstances.get(0);
            }

            if (offerInstance != null && offerOptionsJson != null) {
                logger.debug("UpdateOfferOptions , offerInstanceID : " + offerInstance.getOfferInstanceId() + "\njson = " + offerOptionsJson);

                List<ServiceQuestion> offerOptions = null;
                List<Choice> choices = null;
                List<OfferMdnOption> offerMdnOptionJson = null;

                //Read Offer Options (List)
                try {
                    offerMdnOptionJson = mapper.readValue(offerOptionsJson, new TypeReference<List<OfferMdnOption>>() {
                    });
                } catch (Exception ex) {
                    logger.debug("Unable to parse offerOptions ! Continue to parse only choices.");
                }
            /*
			 * extract choices from offerMdnOptions instead of choice format
			 */
                if (offerMdnOptionJson != null && !offerMdnOptionJson.isEmpty()) {
                    for (OfferMdnOption offerMdnOptionJsonTemp : offerMdnOptionJson) {
                        VerizonEntity entityEachMdn = vzwEntityRepository.findByMdn(offerMdnOptionJsonTemp.getMdn());
                        List<OfferInstance> offerInstancesTemp = offerInstanceRepository.findByOfferAndVerizonEntity(offer.getOfferId(), entityEachMdn.getVerizonEntityId());
                        if (offerInstancesTemp != null && !offerInstancesTemp.isEmpty())
                            offerInstance = offerInstancesTemp.get(0);
                        for (ServiceQuestion sqMdnOptions : offerMdnOptionJsonTemp.getOfferOptions()) {
                            choices = sqMdnOptions.getChoice();
						/*
						 * * Commented code in else block is rewritten to persist choice instances in different scenarios
						 * Case 1: create a choice instance if it doesn’t exist;
						 * Case 2 : if 2/4 choice instances exist,create the remaining two
						 * Case 3: Update the choice instances when the selected changes
						 * **** choice ids supplied should match the ones for choice ids for that offer
						 */
                            if (choices != null && !choices.isEmpty()) {
                                if (offerInstance.getOffer().getOfferOptions() != null && !offerInstance.getOffer().getOfferOptions().isEmpty()) {
                                    if (offerInstance.getChoiceInstances() == null || offerInstance.getChoiceInstances().isEmpty()) {        //choiceinstances not found for the offerinstance
                                        for (ServiceQuestion serviceQuestion : offerInstance.getOffer().getOfferOptions()) {
                                            boolean choiceIdFlag = true;
                                            for (Choice choice : serviceQuestion.getChoice()) {
                                                for (Choice choiceJson : choices) {
                                                    if (choice.getId().equals(choiceJson.getId())) {
                                                        ChoiceInstance newChoiceInstance = new ChoiceInstance();
                                                        newChoiceInstance.setSelected(choiceJson.isSelected());
                                                        newChoiceInstance.setChoice(choice);
                                                        newChoiceInstance.setOfferInstance(offerInstance);
                                                        newChoiceInstance.setDateCreated(new Timestamp(new Date().getTime()));
                                                        choiceInstanceRepository.saveAndFlush(newChoiceInstance);
                                                        choiceIdFlag = false;
                                                    }

                                                }
                                            }
                                            if (choiceIdFlag){
                                            	logger.debug("Choice ids given do not map to the offer and mdn");
                                                return new ResponseEntity("Choice ids given do not map to the offer and mdn", HttpStatus.BAD_REQUEST);
                                            }
                                        }
                                    } else {
                                        for (ServiceQuestion serviceQuestion : offerInstance.getOffer().getOfferOptions()) {        //choicesintances found for the offerinstance

                                            List<Choice> choiceList = serviceQuestion.getChoice();
                                            List<ChoiceInstance> choiceInstanceList = offerInstance.getChoiceInstances();

                                            Collection<Long> choiceIdsFromChoiceList = new ArrayList<>();
                                            Collection<Long> choiceIdsFromChoiceInstanceList = new ArrayList<>();

                                            if (choiceList != null && !choiceList.isEmpty() && choiceList.size() != offerInstance.getChoiceInstances().size()) { //all the choiceinstances do not exist

                                                for (Choice choice : choiceList)
                                                    choiceIdsFromChoiceList.add(choice.getId());

                                                for (ChoiceInstance choiceInstance : choiceInstanceList)
                                                    choiceIdsFromChoiceInstanceList.add(choiceInstance.getChoice().getId());

                                                choiceIdsFromChoiceList.removeAll(choiceIdsFromChoiceInstanceList);

                                                for (Choice choice : choiceList) {
                                                    if (choiceIdsFromChoiceList.contains(choice.getId())) {                //choiceidsfromchoicelist will never be empty or null
                                                        ChoiceInstance newChoiceInstance = new ChoiceInstance();
                                                        for (Choice choiceJson : choices) {
                                                            if (choiceJson.getId().equals(choice.getId())) {
                                                                newChoiceInstance.setChoice(choice);
                                                                newChoiceInstance.setSelected(choiceJson.isSelected());
                                                                newChoiceInstance.setOfferInstance(offerInstance);
                                                                newChoiceInstance.setDateCreated(new Timestamp(new Date().getTime()));
                                                                choiceInstanceRepository.saveAndFlush(newChoiceInstance);
                                                            }
                                                        }
                                                    } else {
                                                        for (ChoiceInstance existChoiceInstance : choiceInstanceList) {
                                                            for (Choice choiceJson : choices)
                                                                if (choiceJson.getId().equals(existChoiceInstance.getChoice().getId())) {
                                                                    //move to choiceinstancehistory first before updating the choiceinstance
                                                                    ChoiceInstanceHistory choiceInstanceHistory = new ChoiceInstanceHistory();
                                                                    choiceInstanceHistory.setChoiceInstance(existChoiceInstance);
                                                                    //choiceInstanceHistory.setChoice(existChoiceInstance.getChoice());
                                                                    //choiceInstanceHistory.setOfferInstance(existChoiceInstance.getOfferInstance());
                                                                    choiceInstanceHistory.setSelected(existChoiceInstance.getSelected());
                                                                    choiceInstanceHistory.setDateCreated(new Timestamp(new Date().getTime()));
                                                                    choiceInstanceHistoryRepository.saveAndFlush(choiceInstanceHistory);

                                                                    existChoiceInstance.setSelected(choiceJson.isSelected());
                                                                    //existChoiceInstance.setDateUpdated(new Timestamp(new Date().getTime()));
                                                                    choiceInstanceRepository.saveAndFlush(existChoiceInstance);
                                                                }
                                                        }

                                                    }
                                                }
                                            } else if (choiceList != null && !choiceList.isEmpty() && choiceList.size() == offerInstance.getChoiceInstances().size()) {    //all the choiceinstances exist																							   ///case 3: choice instances should just be updated with the given choices
                                                for (Choice choiceJson : choices)
                                                    for (ChoiceInstance choiceInstance : offerInstance.getChoiceInstances())
                                                        if (choiceInstance.getChoice().getId().equals(choiceJson.getId())) {
                                                            //move to choiceinstancehistory first before updating the choiceinstance
                                                            ChoiceInstanceHistory choiceInstanceHistory = new ChoiceInstanceHistory();
                                                            choiceInstanceHistory.setChoiceInstance(choiceInstance);
                                                            //choiceInstanceHistory.setChoice(choiceInstance.getChoice());
                                                            //choiceInstanceHistory.setOfferInstance(choiceInstance.getOfferInstance());
                                                            choiceInstanceHistory.setSelected(choiceInstance.getSelected());
                                                            choiceInstanceHistory.setDateCreated(new Timestamp(new Date().getTime()));
                                                            choiceInstanceHistoryRepository.saveAndFlush(choiceInstanceHistory);

                                                            choiceInstance.setSelected(choiceJson.isSelected());
                                                            //choiceInstance.setDateUpdated(new Timestamp(new Date().getTime()));
                                                            choiceInstanceRepository.saveAndFlush(choiceInstance);
                                                        }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        int serviceInstanceCount = 0;
                        Service defaultService = null;
                        boolean flag=false;
                        ServiceInstance serviceInstanceToBeEndDated = null;
                       List<ServiceInstance> serviceInstancesList = serviceInstanceRepository.findLatestByOfferInstance(offerInstance.getOfferInstanceId());
                        if(addOn){						//For NS
                        	 defaultService = serviceManager.getDefaultService();
                        	for (ServiceInstance sI : serviceInstancesList) {
                        		for(RuleInstance rITemp : sI.getRules()){
                        			if (rITemp.getRuleType().equals(Rule.qosEOCRuleType) && rITemp.getExpressionType().equals(Rule.byTimeExpressionType)){
                        				 serviceInstanceCount = serviceInstanceCount+1;
                        			}
                        		}
                        	}
                        	if(serviceInstanceCount==2){					//both ns and base
                        		for (ServiceInstance sI : serviceInstancesList) {
                        			for(RuleInstance rI : sI.getRules()){
                        				if (rI.getRuleType().equals(Rule.classficationRuleType) && rI.getExpressionType().equals(Rule.byTimeExpressionType)) {
                        					if(!(rI.getServiceInstance().getService().getServiceId().equals(defaultService.getServiceId()))){
                        						for (Choice c : choices) {
                        							if(!c.isSelected()){																						//toggle only one choice expected
                        								logger.debug("Invoking removeServiceFromDevice on serviceInstance id : "+rI.getServiceInstance().getServiceInstanceId());
                        								serviceManager.removeServiceFromDevice(sI);
                        								List<PlabCust> plabCustList = new ArrayList<>();
                        								Long[] indicators = {PlabConstants.ENROLLED};
                        								plabCustList.addAll(plabCustRepository.findByMdnAndIndicator(entityEachMdn.getMdn(), indicators)); 
                        								productManager.issueMlmo(entityEachMdn.getCustIdNo().toString(), entityEachMdn.getAcctNo().toString(), plabCustList, productManager.getSpoOfMdn(plabCustList, true), null, DvsConstants.ACTION_INDICATOR_DELETE);
                        								flag=true;
                        								serviceInstanceToBeEndDated=sI;
                        								break;
                        							}
                        						}
                        					}
                        				}
                        			}
                        		}
                        		//end date the instances for NS to be removed
                                if(flag){
                                	for(RuleInstance rITemp : serviceInstanceToBeEndDated.getRules()){
                                		rITemp.setEndDate(new Timestamp(new Date().getTime()));
                                		logger.debug("updateOfferOptions : End dated ruleInstance with id : "+rITemp.getRuleInstanceId()+" for mdn = "+entityEachMdn.getMdn());
                                		ruleInstanceRepository.saveAndFlush(rITemp);
                                	}

                                	serviceInstanceToBeEndDated.setEndTmstamp(new Timestamp(new Date().getTime()));
                                	logger.debug("updateOfferOptions : End dated serviceInstance with id : "+serviceInstanceToBeEndDated.getServiceInstanceId()+" for mdn = "+entityEachMdn.getMdn());
                                	serviceInstanceRepository.saveAndFlush(serviceInstanceToBeEndDated);
                                }
                        	} else if(serviceInstanceCount==1){
                        				for (Choice c : choices) {
                            				if (c.isSelected()) {
                            					List<Service> serviceList = offer.getServices();
                            					for(Service service : serviceList){
                            						ServiceInstance serviceInstance = productManager.createServiceInstance(service, entityEachMdn, offerInstance,null);
                            						serviceInstance = productManager.addEOCRuleInstance(serviceInstance,entityEachMdn.getCustIdNo(),null);							
                            						offerInstance.getServiceInstances().add(serviceInstance);
                            						ServiceInstance tempServiceInstance = serviceInstanceRepository.saveAndFlush(serviceInstance);
                            						logger.debug("OfferController acceptOffer,Created serviceInstanceId : " + serviceInstance.getServiceInstanceId());

                            						if (tempServiceInstance != null) {
                            							logger.debug("Calling addVispServiceToDevice on serviceInstance :"+tempServiceInstance.getServiceInstanceId());
                            							serviceManager.addVispServiceToDevice(tempServiceInstance);
                            							List<PlabCust> plabCustList = new ArrayList<>();
                        								Long[] indicators = {PlabConstants.ENROLLED};
                        								plabCustList.addAll(plabCustRepository.findByMdnAndIndicator(entityEachMdn.getMdn(), indicators)); 
                        								productManager.issueMlmo(entityEachMdn.getCustIdNo().toString(), entityEachMdn.getAcctNo().toString(), plabCustList, productManager.getSpoOfMdn(plabCustList, true), null, DvsConstants.ACTION_INDICATOR_ACCEPT);
                            						} else {
                            							logger.error("Service instance not created.");
                            							return new ResponseEntity(utility.createResponseInfo(HttpStatus.EXPECTATION_FAILED, "Service Instance not createddd", PlabConstants.STATUS_ERROR), HttpStatus.EXPECTATION_FAILED);
                            						}
                            					}
                            					offerInstanceRepository.saveAndFlush(offerInstance);
                            				}
                            			}
                        			}
                        }else{
                            for (ServiceInstance sI : serviceInstancesList) {																	//every instance will have atleast a base service by now; so will always enter the loop
                            	for (RuleInstance rI : sI.getRules()) {                      		 	 
                                	 if (rI.getRuleType().equals(Rule.classficationRuleType) && rI.getExpressionType().equals(Rule.byApplicationExpressionType)) {
                                        //Social
                                        // Update Expression Apps
                                        String newExp = "";
                                        for (Choice c : choices) {
                                            if (c.isSelected()) {
                                                newExp += appRepository.findByName(c.getTitle()).getVispName() + ",";
                                            }
                                        }
                                        newExp = newExp.substring(0, newExp.lastIndexOf(','));
                                        rI.setExpression(newExp);
                                        ruleInstanceRepository.saveAndFlush(rI);
                                    } else if (rI.getRuleType().equals(Rule.qosRuleType) && rI.getExpressionType().equals(Rule.bySpeedExpressionType)) {
                                        //Speed
                                        for (Choice c : choices) {
                                            if (c.isSelected()) {
                                                Map<String, String> map = rI.getAttributes();
                                                if (c.getTitle() != null) {
                                                    if (c.getTitle().equalsIgnoreCase("FAST") || c.getTitle().equalsIgnoreCase("FASTER") || c.getTitle().equalsIgnoreCase("FASTEST")){
                                                    	if(!(rI.getExpression().trim().equalsIgnoreCase(c.getTitle()))){
                                                    		map.put(PlabConstants.QOS_RULETYPE_BANDWIDTH, c.getTitle().toUpperCase());
                                                    		 rI.setAttributes(map);
                                                    		 rI.setExpression(c.getTitle().toUpperCase());
                                                    		 ruleInstanceRepository.saveAndFlush(rI);
                                                    		 logger.debug("Calling updateVispServiceToDevice on serviceInstance with id "+sI.getServiceInstanceId());
                                                    		 serviceManager.updateVispServiceToDevice(sI);
                                                    	}
                                                    }
                                                    else{
                                                        map.put(PlabConstants.QOS_RULETYPE_DV_FORMAT, c.getTitle().toUpperCase());
                                                        rI.setAttributes(map);
                                                        ruleInstanceRepository.saveAndFlush(rI);
                                                        serviceManager.updateVispServiceToDevice(sI);
                                                    }
                                                } else{
                                                	logger.debug("Please provide choice title");
                                                    return new ResponseEntity<>(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Please provide choice title", PlabConstants.STATUS_ERROR), HttpStatus.BAD_REQUEST);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    logger.debug("MdnOfferoptionsJson could not be read ");
                    return new ResponseEntity<>(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "MdnOfferoptionsJson could not be read", PlabConstants.STATUS_ERROR), HttpStatus.BAD_REQUEST);
                }
                logger.debug("offer options updated for offerInstanceId: " + offerInstance.getOfferInstanceId());
                return new ResponseEntity<>(utility.createResponseInfo(HttpStatus.OK, "offer options updated for offerInstanceId: " + offerInstance.getOfferInstanceId(), PlabConstants.STATUS_SUCCESS), HttpStatus.OK);
            } else {
                logger.debug("OfferInstance : " + offerInstance.getOfferInstanceId() + " not found.");
                return new ResponseEntity<>(utility.createResponseInfo(HttpStatus.NOT_FOUND, offerInstance.getOfferInstanceId() + "not found", PlabConstants.STATUS_ERROR), HttpStatus.NOT_FOUND);
            }
        }
    }
    /**
     * Check if termns and conditions for given mdn and offer already accepted or not
     *
     * @param offerId
     * @param mdn
     * @return
     */
    public int isTermsAndConditonsAccepted(String offerId, String mdn) {
        OfferInstance offerInstance = getOfferInstance(offerId, mdn);
        return offerInstance.getTermsAndConditionsStatus();
    }


    /**
     * Offer instance list.
     *
     * @param offerId
     * @param mdn
     * @return
     */
    private OfferInstance getOfferInstance(String offerId, String mdn) {

        if (offerId == null) {
            logger.debug("Offer " + offerId + " not available in the request");
        }
        if (mdn == null) {
            logger.debug("MDN " + mdn + " not available");
        }
        
        Offer offer = repository.findByOfferId(Long.parseLong(offerId));
        VerizonEntity vzEntity = vzwEntityRepository.findByMdn(mdn);
        List<OfferInstance> offerInstances = offerInstanceRepository.findByOfferAndVerizonEntity(offer.getOfferId(), vzEntity.getVerizonEntityId());
        return offerInstances.get(0);
    }

	@RequestMapping(value = "/customer/feedback",method = RequestMethod.POST)
	public ResponseEntity customerFeedback(@RequestBody(required = false) String jsonFeedback) {

		try {
			if (jsonFeedback != null && !jsonFeedback.trim().isEmpty()) {
				Feedback feedback = null;
				CustomerDeviceInfo cust_var = new CustomerDeviceInfo();
				feedback = mapper.readValue(jsonFeedback, Feedback.class);
				logger.debug("Customer save feedback, jsonFeedback : " + jsonFeedback);
				feedbackRepository.saveAndFlush(feedback);
				return new ResponseEntity<>(utility.createResponseInfo(HttpStatus.OK, "Feedback json saved successfully ", PlabConstants.STATUS_SUCCESS), HttpStatus.OK);
			} else {
				logger.debug("Feedback json should be provided");
				return new ResponseEntity<>(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Feedback json should be provided", PlabConstants.STATUS_ERROR), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Exception reading the feedback json" + e.getMessage());
			return new ResponseEntity<>(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Exception reading the feedback json.Please check the json", PlabConstants.STATUS_ERROR), HttpStatus.BAD_REQUEST);
		}
	}
	

	@RequestMapping(value = "/customer/feedback",method = RequestMethod.GET)
	public ResponseEntity getCustomerFeedback(@RequestParam(value = "mdn", defaultValue = "0") String mdn) {

		logger.debug("getCustomerFeedback() : get feedback for the mdn : " + mdn);

		ObjectMapper writer = new ObjectMapper();
		writer.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		
		Offer offer = null;
		if (mdn!=null && !mdn.isEmpty()) {
			if(mdn.equals("0")){
				List<Object> fbList = feedbackRepository.findForAllOffers();
				try {
					String jsonInString = writer.writeValueAsString(fbList);
					logger.debug("getCustomerFeedback() : " + jsonInString);
					return new ResponseEntity(jsonInString, HttpStatus.OK);
				} catch(JsonProcessingException ex){
					logger.error("getCustomerFeedback parsing error : " + ex.getMessage());
					return new ResponseEntity("Error parsing feedback list to json ", HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			List<CustomerDeviceInfo> customVarList = customerDeviceInfoRepository.findByLoggedInMdn(mdn);
			List<Feedback> feedbackList = new ArrayList<>();
			if(customVarList!=null && !customVarList.isEmpty()){
				for(CustomerDeviceInfo customVar : customVarList){
					Feedback feedback = customVar.getFeedback();
					feedback.setCustom_var(customVar);
					feedbackList.add(feedback);
				}
				try {
					String jsonInString = writer.writeValueAsString(feedbackList);
					logger.debug("getCustomerFeedback() : "+jsonInString);
					return new ResponseEntity(jsonInString, HttpStatus.OK);
				} catch (Exception e) {
					logger.error("get feedback parsing error : " + e.getMessage());
					return new ResponseEntity("Error parsing feedback list to json ", HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			else{
				logger.debug("No feedback found for the mdn ");
				return new ResponseEntity("No feedback found for the mdn ", HttpStatus.NOT_FOUND);
			}
		}
		else{
			logger.debug("getCustomerFeedback() : Invalid mdn ");
			return new ResponseEntity("getCustomerFeedback() : Invalid mdn ", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/offer/feedback/{offerId}", method = RequestMethod.GET)
	public ResponseEntity getOfferFeedback(@PathVariable(value = "offerId") Long offerId) {

		logger.debug("getOfferFeedback() : get feedback for the offerId : " + offerId);

		Offer offer = null;
		if (offerId != null && !offerId.equals(0)) {

			List<Feedback> feedbackList = feedbackRepository.findByOffer(offerId);

			if (feedbackList == null || feedbackList.isEmpty()) {
				logger.debug("getOfferFeedback() : Feedback not found for offerId : " + offerId);
				return new ResponseEntity("getOfferFeedback() : Feedback not found for offerId : " + offerId, HttpStatus.NOT_FOUND);
			}

			ObjectMapper writer = new ObjectMapper();
			writer.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
			try {
				String jsonInString = writer.writeValueAsString(feedbackList);
				logger.debug("getOfferFeedback() : "+jsonInString);
				return new ResponseEntity(jsonInString, HttpStatus.OK);
			} catch (Exception e) {
				logger.error("getOfferFeedback() : get feedback parsing error : " + e.getMessage());
				return new ResponseEntity("Error parsing feedback list to json ", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			logger.debug("getOfferFeedback() : getOfferFeedback Invalid Offer ID ");
			return new ResponseEntity("getOfferFeedback() : getOfferFeedback Invalid Offer ID ", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/offer/feedback", method = RequestMethod.GET)
	public ResponseEntity getAllOffersFeedback() {

		logger.debug("getAllOffersFeedback() : ");
		List<Object> responseList = feedbackRepository.findForAllOffers();

		if (responseList == null || responseList.isEmpty()) {
			logger.debug("getAllOffersFeedback() : Feedback not found");
			return new ResponseEntity("Feedback not found", HttpStatus.NOT_FOUND);
		}
		
		List<Feedback> feedbackList = new ArrayList<Feedback>();
		
		for(Object response : responseList){
			Object[] row = (Object[]) response;
			Feedback feedback  = (Feedback) row[0];
			feedback.setOfferName(row[1].toString());
			feedbackList.add(feedback);
		}

		ObjectMapper writer = new ObjectMapper();
		writer.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		try {
			String jsonInString = writer.writeValueAsString(feedbackList);
			logger.debug("getAllOffersFeedback() : "+jsonInString); 
			return new ResponseEntity(jsonInString, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("get feedback parsing error : " + e.getMessage());
			return new ResponseEntity("Error parsing feedback list to json ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	/*
	 * dvs call to retrieve pound data allowance list and store it in plab
	 */
	@RequestMapping(value = "/offer/getdetailusage/{mdn}",method = RequestMethod.POST)
	public ResponseEntity getDetailUsage(@PathVariable("mdn") String mdn) {
		
		logger.debug("getdetailUsage() for mdn : "+mdn);

		com.vzwcorp.pricinglab.vo.pounddata.Service poundDataService = new com.vzwcorp.pricinglab.vo.pounddata.Service();
		com.vzwcorp.pricinglab.vo.pounddata.ServiceBody serviceBody = new com.vzwcorp.pricinglab.vo.pounddata.ServiceBody();
		com.vzwcorp.pricinglab.vo.pounddata.ServiceHeader serviceHeader = new com.vzwcorp.pricinglab.vo.pounddata.ServiceHeader();
		serviceHeader.setServiceName("retrievePricingLabDetailUsage");
		poundDataService.setServiceHeader(serviceHeader);
		poundDataService.setServiceBody(serviceBody);
		StringWriter sWriter = new StringWriter();
		Marshaller poundDataMarshaller = null;
		Service service = null;

		try {
			
			poundDataMarshaller = dvsManager.getMarshaller(DvsConstants.POUND_DATA_PACKAGE);

			List<OfferInstance> offerInstanceList = offerInstanceRepository.findLatestOfferInstanceByMdn(mdn);

			if (offerInstanceList == null || offerInstanceList.isEmpty()) {
				serviceHeader.setErrorMsg("No active Offer Instances found for mdn : "+mdn);
				poundDataService.setServiceHeader(serviceHeader);
				poundDataMarshaller.marshal(poundDataService, sWriter);
				logger.debug("getDetailUsage() For mdn : " + mdn + "  getdetailUsage(#data) xml sent to dvs : " + sWriter.toString());
				return new ResponseEntity(sWriter.toString().substring(sWriter.toString().indexOf('\n') + 1),
						HttpStatus.NOT_FOUND);
			}

			OfferInstance offerInstance = offerInstanceList.get(0);

			List<ServiceInstance> serviceInstanceList = serviceInstanceRepository.findLatestByOfferInstance(offerInstance.getOfferInstanceId()); 

			if (serviceInstanceList == null || serviceInstanceList.isEmpty()) {
				serviceHeader.setErrorMsg("No active Service Instances found for mdn : "+mdn);
				poundDataService.setServiceHeader(serviceHeader);
				poundDataMarshaller.marshal(poundDataService, sWriter);
				logger.debug("getDetailUsage() For mdn : " + mdn + "  getdetailUsage(#data) xml sent to dvs : " + sWriter.toString());
				return new ResponseEntity(sWriter.toString().substring(sWriter.toString().indexOf('\n') + 1),
						HttpStatus.NOT_FOUND);
			}
			
			for (ServiceInstance si : serviceInstanceList) {
				if(si.getService().getRatingGroup().getRatingGroupId()!=3300){
					service = si.getService();
					break;
				}
			}
			//fix for defect when getdetail usage wasnt shown for primary mdn that is not enrolled
			if(service==null){
				VerizonEntity vzEntity = vzwEntityRepository.findByMdn(mdn);
				Long[] indicator = { PlabConstants.ENROLLED};
				List<PlabCust> plabCustListEnrolledMdn = plabCustRepository.findByCustIdNoAndAcctNoAndIndicator(vzEntity.getCustIdNo(), vzEntity.getAcctNo(), indicator);
				if(plabCustListEnrolledMdn!=null && !plabCustListEnrolledMdn.isEmpty()){
					PlabCust plabCust = plabCustListEnrolledMdn.get(0);
					service = plabCust.getOffer().getServices().get(0);
				}
			}
				if (service!=null && "SpeedTier".equals(serviceManager.getServiceType(service))) {
					return getDetailUsageForSpeedTier(offerInstance, poundDataService, poundDataMarshaller);
				} else if (service!=null && "NightSurfer".equals(serviceManager.getServiceType(service))) {
					return getDetailUsageForNightSurfer(offerInstance, poundDataService, poundDataMarshaller);
				}else {
					serviceHeader.setErrorMsg("No supported Service found for mdn : "+mdn);
					poundDataService.setServiceHeader(serviceHeader);
					poundDataMarshaller.marshal(poundDataService, sWriter);
					logger.debug("getDetailUsage() For mdn : " + mdn + "  getdetailUsage(#data) xml sent to dvs : " + sWriter.toString());
					return new ResponseEntity(sWriter.toString().substring(sWriter.toString().indexOf('\n') + 1),
							HttpStatus.NOT_FOUND);					
				}

		} catch (Exception e) {
			logger.error("Exception in getDetailUsage : for MDN : " + mdn, e);
			serviceHeader.setErrorMsg("No active plab customer found");
			poundDataService.setServiceHeader(serviceHeader);
			try {
				poundDataMarshaller.marshal(poundDataService, sWriter);
				return new ResponseEntity(sWriter.toString().substring(sWriter.toString().indexOf('\n') + 1),
						HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (JAXBException e1) {
				return new ResponseEntity("Exception in getDetailUsage : for MDN : " + e1.getMessage(),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		//logger.debug("getDetailUsage() : No Usage found for this mdn !"); 
		//return new ResponseEntity("No Usage found for this mdn !", HttpStatus.NOT_FOUND);
	}


	/**
	 * Get the detail usage for Speed Tier
	 * 
	 * @param primaryOfferInstance
	 * @param poundDataService
	 * @return
	 */
	private ResponseEntity getDetailUsageForSpeedTier(OfferInstance primaryOfferInstance,
			com.vzwcorp.pricinglab.vo.pounddata.Service poundDataService, Marshaller poundDataMarshaller) {
		
		logger.debug("getDetailUsageForSpeedTier() : primaryOfferInstance : "+primaryOfferInstance); 
		logger.debug("getDetailUsageForSpeedTier() : poundDataService : "+poundDataService); 
		
		Offer offer = null;
		List<ServiceInstance> serviceInstanceList = null;
		List<PlabCust> accountMembers = null;
		ServiceInstance servInstance = null;
		StringWriter sWriter = new StringWriter();
		//Date subCustBillCycleStartDt = null;

		com.vzwcorp.pricinglab.vo.pounddata.ServiceBody serviceBody = poundDataService.getServiceBody();
		com.vzwcorp.pricinglab.vo.pounddata.ServiceHeader serviceHeader = poundDataService.getServiceHeader();
		com.vzwcorp.pricinglab.vo.pounddata.ServiceResponse serviceResponse = new com.vzwcorp.pricinglab.vo.pounddata.ServiceResponse();
		com.vzwcorp.pricinglab.vo.pounddata.DataUsageInfo dataUsageInfo = new com.vzwcorp.pricinglab.vo.pounddata.DataUsageInfo();
		AdditionalUsageProductList additionalUsageProductList = new AdditionalUsageProductList();

		String mdn = primaryOfferInstance.getVerizonEntity().getMdn();
		Long custId = primaryOfferInstance.getVerizonEntity().getCustIdNo();
		Long acctNo = primaryOfferInstance.getVerizonEntity().getAcctNo();
		Long offerId = primaryOfferInstance.getOffer().getOfferId();

		Map<String, Long> mapBySpeed = new LinkedHashMap<String, Long>();

		logger.debug("getDetailUsageForSpeedTier : " + mdn);
		
		try {
			// Create a map with different choices for speed.
			// Includes FAST, FASTER, FASTEST
			List<ChoiceInstance> choiceInstances = primaryOfferInstance.getChoiceInstances();
			for (ChoiceInstance choiceInstance : choiceInstances) {
				//FIXME : This is a temporary fix to make sure only QoS choices FAST,FASTER and FASTEST is picked up.
				if (choiceInstance.getChoice().getServiceQuestion() != null
						&& choiceInstance.getChoice().getSurveyQuestion() == null) {
					mapBySpeed.put(choiceInstance.getChoice().getTitle().toUpperCase(), 0l);
				}
			}

			offer = primaryOfferInstance.getOffer();
		/*	List<Date> subCustbillCycleDtsList = productManager.getSubCustBillCycleDates(custId, acctNo);

			if (subCustbillCycleDtsList == null || subCustbillCycleDtsList.isEmpty()) {
				serviceHeader.setErrorMsg("Bill Cycle details not available for the customer with mdn : " + mdn);
				poundDataService.setServiceHeader(serviceHeader);
				poundDataMarshaller.marshal(poundDataService, sWriter);
				logger.debug("For mdn : " + mdn + "  getdetailUsage(#data) xml sent to dvs : " + sWriter.toString());
				return new ResponseEntity(sWriter.toString().substring(sWriter.toString().indexOf('\n') + 1),
						HttpStatus.NOT_FOUND);

			}
			subCustBillCycleStartDt = subCustbillCycleDtsList.get(0);

			Long[] indicator = { PlabConstants.ENROLLED, PlabConstants.REMOVING,
					PlabConstants.REMOVED_PENDING_EOC, PlabConstants.EARLY_TERMINATE_PENDING_EOC,
					PlabConstants.EARLY_TERMINATE_VISION_REMOVED };

			accountMembers = plabCustRepository.findByOfferAndCustIdNoAndAcctNoAndIndicatorAndEndDate(offerId, custId,
					acctNo, indicator, subCustBillCycleStartDt);*/
			
			Long[] indicator = { PlabConstants.ENROLLED, PlabConstants.REMOVING,
					PlabConstants.REMOVED_PENDING_EOC, PlabConstants.EARLY_TERMINATE_PENDING_EOC,
					PlabConstants.EARLY_TERMINATE_VISION_REMOVED };
			accountMembers = plabCustRepository.findByOfferAndCustIdNoAndAcctNoAndIndicator(offer.getOfferId(), custId, acctNo,indicator);

			if (accountMembers == null || accountMembers.isEmpty()) {
				serviceHeader.setErrorMsg("No active plab customer found");
				poundDataService.setServiceHeader(serviceHeader);
				poundDataMarshaller.marshal(poundDataService, sWriter);
				logger.debug("For mdn : " + mdn + "  getdetailUsage(#data) xml sent to dvs : " + sWriter.toString());
				return new ResponseEntity(sWriter.toString().substring(sWriter.toString().indexOf('\n') + 1),
						HttpStatus.NOT_FOUND);
			}

			AdditionalUsageProductAllowances lineUsageProductAllowances = new AdditionalUsageProductAllowances();

			AdditionalUsageProductDataItemList lineUsageProductAllowanceDataItemList = new AdditionalUsageProductDataItemList();

			lineUsageProductAllowances.setDetailType(offer.getName() + " - BY LINE");
			lineUsageProductAllowances.setProductType("MDN");
			lineUsageProductAllowances
					.setAdditionalUsageProductDataItemListCount(Long.toString(accountMembers.size()));
			lineUsageProductAllowances.setIsAddOn("N");
			lineUsageProductAllowances.setDataUsageDescription(PlabConstants.SPEED_DATA_USAGE_DESC);
			lineUsageProductAllowances.setTapToChangeMessage(PlabConstants.SPEED_TAP_TO_CHANGE_MESSAGE);
			lineUsageProductAllowances.setTapToChangeSubMessage(PlabConstants.SPEED_TAP_TO_CHANGE_SUB_MESSAGE);
			lineUsageProductAllowances.setDataHubDescription(PlabConstants.SPEED_DATA_HUB_DESCRIPTION);
			lineUsageProductAllowances.setDataHubPlanDescription(PlabConstants.SPEED_DATA_HUB_PLAN_DESCRIPTION);
			lineUsageProductAllowances
					.setDataHubUpdateDescription(PlabConstants.SPEED_DATA_HUB_UPDATE_DESCRIPTION);

			long fastCounter = 0;
			long fasterCounter = 0;
			long fastestCounter = 0;
			String serviceName = null;
			String effectiveDate = null;

			for (PlabCust plabCustAcctMdn : accountMembers) {
				OfferInstance offerInstance = productManager.getValidOfferInstance(plabCustAcctMdn.getMdn());

				if (offerInstance != null) {
					logger.debug("offerInstanceID in getdetailUsage" + offerInstance.getOfferInstanceId());
					
					serviceInstanceList = serviceInstanceRepository.findLatestByOfferInstance(offerInstance.getOfferInstanceId());

					if (serviceInstanceList != null && !serviceInstanceList.isEmpty()) {
						servInstance = null;
						for (ServiceInstance servInstanceTemp : serviceInstanceList) {
							if ("SpeedTier".equals(serviceManager.getServiceType(servInstanceTemp.getService()))) {
								servInstance = servInstanceTemp;
								serviceName = servInstance.getServiceName();
								effectiveDate = servInstance.getDateCreated().toGMTString();
								logger.debug("servInstanceID : " + servInstance.getServiceInstanceId());
								break;
							}
						}
						UsageRecord usageRecord = usageRecordRepository.findPrevUsageByMdn(plabCustAcctMdn.getMdn());

						if (servInstance != null) {

							AdditionalUsageProductDetails lineUsageProductDetails = new AdditionalUsageProductDetails();
							lineUsageProductDetails.setTitle("MDN");
							lineUsageProductDetails.setSubTitle(plabCustAcctMdn.getMdn());
							lineUsageProductDetails.setBarColor("G");
							lineUsageProductDetails.setBarPercent("0.0");
							lineUsageProductDetails.setDescription(servInstance.getServiceName());
							lineUsageProductDetails.setEffectiveDate(servInstance.getDateCreated().toGMTString());
							lineUsageProductDetails
									.setAllowance(Long.toString(servInstance.getService().getAllowance()));
							long totalUsed = 0;
							if (usageRecord != null && usageRecord.getBillingInfo() != null) {
								totalUsed = usageRecord.getBillingInfo().getTotalBytes();
								if (totalUsed != 0)
									totalUsed = totalUsed / 1024 / 1024;
							}
							lineUsageProductDetails.setTotalUsed(Long.toString(totalUsed));
							if (servInstance.getAllowance() == -1) {
								lineUsageProductDetails.setRemaining("-1");
								lineUsageProductDetails.setRemainingPercent("100.0");
							} else {
								long percentRemaining = 0;
								long remaining = servInstance.getAllowance()
										- totalUsed;
								remaining = remaining / 1024 / 1024;
								if (servInstance.getAllowance() != 0)
									percentRemaining = (remaining / servInstance.getAllowance()) * 100;
								lineUsageProductDetails.setRemaining(Long.toString(remaining));
								lineUsageProductDetails.setRemainingPercent(Long.toString(percentRemaining));
							}
							lineUsageProductDetails.setUnitOfMeasure("MB");
							lineUsageProductAllowanceDataItemList.getAdditionalUsageProductDataItem()
									.add(lineUsageProductDetails);

							lineUsageProductAllowances
									.setAdditionalUsageProductDataItemList(lineUsageProductAllowanceDataItemList);

							// Prepare the data for Speed based details

							fastCounter += servInstance.getFastCounter();
							fasterCounter += servInstance.getFasterCounter();
							fastestCounter += servInstance.getFastestCounter();
						}
					}
				}
			}
			
			additionalUsageProductList.getAdditionalUsageProductAllowances().add(lineUsageProductAllowances);

			if (fastCounter > 0) {
				mapBySpeed.put(PlabConstants.SPEED_FAST, fastCounter);
			}

			if (fasterCounter > 0) {
				mapBySpeed.put(PlabConstants.SPEED_FASTER, fasterCounter);
			}

			if (fastestCounter > 0) {
				mapBySpeed.put(PlabConstants.SPEED_FASTEST, fastestCounter);
			}

			Set<Entry<String, Long>> entrySet = mapBySpeed.entrySet();

			AdditionalUsageProductDataItemList speedProductAllowanceDataItemList = new AdditionalUsageProductDataItemList();

			AdditionalUsageProductAllowances speedUsageProductAllowances = new AdditionalUsageProductAllowances();

			speedUsageProductAllowances.setDetailType(offer.getName() + " - BY SPEED");
			speedUsageProductAllowances.setProductType("SPEED");
			speedUsageProductAllowances.setAdditionalUsageProductDataItemListCount(Integer.toString(mapBySpeed.size()));
			speedUsageProductAllowances.setIsAddOn("N");
			speedUsageProductAllowances.setDataUsageDescription(PlabConstants.SPEED_DATA_USAGE_BYSPEED_DESC);
			speedUsageProductAllowances.setTapToChangeMessage(PlabConstants.SPEED_TAP_TO_CHANGE_MESSAGE);
			speedUsageProductAllowances.setTapToChangeSubMessage(PlabConstants.SPEED_TAP_TO_CHANGE_SUB_MESSAGE);
			speedUsageProductAllowances.setDataHubDescription(PlabConstants.SPEED_DATA_HUB_DESCRIPTION);
			speedUsageProductAllowances.setDataHubPlanDescription(PlabConstants.SPEED_DATA_HUB_PLAN_DESCRIPTION);
			speedUsageProductAllowances.setDataHubUpdateDescription(PlabConstants.SPEED_DATA_HUB_UPDATE_DESCRIPTION);

			speedUsageProductAllowances.setAdditionalUsageProductDataItemList(speedProductAllowanceDataItemList);
			additionalUsageProductList.getAdditionalUsageProductAllowances().add(speedUsageProductAllowances);

			for (Entry<String, Long> speedEntry : entrySet) {

				Long counter = speedEntry.getValue();
				
				if(counter > 0){
					counter = counter / 1024 / 1024;
				}

				AdditionalUsageProductDetails speedUsageProductDetails = new AdditionalUsageProductDetails();
				speedUsageProductDetails.setTitle(speedEntry.getKey());
				String speedValue = "";
				RefQoS qos = qosMap.get(speedEntry.getKey());
               /* if (qos != null && qos.getSpeed() != null) {
                    speedValue = qos.getSpeed();
                }*/
                speedUsageProductDetails.setSubTitle(speedValue);
				speedUsageProductDetails.setBarColor("G");
				speedUsageProductDetails.setBarPercent("0.0");
				speedUsageProductDetails.setDescription(serviceName);
				speedUsageProductDetails.setEffectiveDate(effectiveDate);
				speedUsageProductDetails.setAllowance("-1");
				speedUsageProductDetails.setRemaining("-1");
				speedUsageProductDetails.setRemainingPercent("100.0");
				speedUsageProductDetails.setTotalUsed(Long.toString(counter));
				speedUsageProductDetails.setUnitOfMeasure("MB");

				speedProductAllowanceDataItemList.getAdditionalUsageProductDataItem().add(speedUsageProductDetails);
			}

			dataUsageInfo.setAdditionalUsageProductList(additionalUsageProductList);
			serviceResponse.setDataUsageInfo(dataUsageInfo);
			serviceBody.setServiceResponse(serviceResponse);
			serviceHeader.setErrorMsg("");
			poundDataService.setServiceHeader(serviceHeader);
			poundDataService.setServiceBody(serviceBody);
			poundDataMarshaller.marshal(poundDataService, sWriter);
			logger.debug("For mdn : " + mdn + " getdetailUsage(#data) xml sent to dvs : " + sWriter.toString());
			return new ResponseEntity(sWriter.toString().substring(sWriter.toString().indexOf('\n') + 1),
					HttpStatus.OK);

		}

		catch (Exception e) {
			logger.error("Exception in OfferController (getDetailUsage()) : ", e.getMessage(), e);
			serviceHeader.setErrorMsg("Exception in getDetailUsage:" + e.getMessage());
			poundDataService.setServiceHeader(serviceHeader);
			try {
				poundDataMarshaller.marshal(poundDataService, sWriter);
			} catch (JAXBException e1) {
				e1.printStackTrace();
			}
			logger.debug("For mdn : " + mdn + " getdetailUsage(#data) xml sent to dvs : " + sWriter.toString());
			return new ResponseEntity(sWriter.toString().substring(sWriter.toString().indexOf('\n') + 1),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Get the detail usage for Night Surfer
	 * 
	 * @param primaryOfferInstance
	 * @param poundDataService
	 * @return
	 */	
	private ResponseEntity getDetailUsageForNightSurfer(OfferInstance primaryOfferInstance,
			com.vzwcorp.pricinglab.vo.pounddata.Service poundDataService, Marshaller poundDataMarshaller) {
		
		logger.debug("getDetailUsageForNightSurfer() : primaryOfferInstance : "+primaryOfferInstance.getOfferInstanceId()); 
		logger.debug("getDetailUsageForNightSurfer() : poundDataService : "+poundDataService); 
		
		AdditionalUsageProductList additionalUsageProductList = null;
		Offer offer = null;
		List<ServiceInstance> serviceInstanceList = null;
		List<PlabCust> accountMembers = null;
		ServiceInstance servInstance = null;
		StringWriter sWriter = new StringWriter();
		//Date subCustBillCycleStartDt = null;

		com.vzwcorp.pricinglab.vo.pounddata.ServiceBody serviceBody = poundDataService.getServiceBody();
		com.vzwcorp.pricinglab.vo.pounddata.ServiceHeader serviceHeader = poundDataService.getServiceHeader();
		com.vzwcorp.pricinglab.vo.pounddata.ServiceResponse serviceResponse = new com.vzwcorp.pricinglab.vo.pounddata.ServiceResponse();
		com.vzwcorp.pricinglab.vo.pounddata.DataUsageInfo dataUsageInfo = new com.vzwcorp.pricinglab.vo.pounddata.DataUsageInfo();

		String mdn = primaryOfferInstance.getVerizonEntity().getMdn();
		Long custId = primaryOfferInstance.getVerizonEntity().getCustIdNo();
		Long acctNo = primaryOfferInstance.getVerizonEntity().getAcctNo();
		Long offerId = primaryOfferInstance.getOffer().getOfferId();

		try {

			logger.debug("getDetailUsageForNightSurfer : " + mdn);

			offer = primaryOfferInstance.getOffer();
			//List<Date> subCustbillCycleDtsList = productManager.getSubCustBillCycleDates(custId, acctNo);

			/*if (subCustbillCycleDtsList != null && !subCustbillCycleDtsList.isEmpty()) {
				subCustBillCycleStartDt = subCustbillCycleDtsList.get(0);

				Long[] indicator = { PlabConstants.ENROLLED, PlabConstants.REMOVING,
						PlabConstants.REMOVED_PENDING_EOC, PlabConstants.EARLY_TERMINATE_PENDING_EOC,
						PlabConstants.EARLY_TERMINATE_VISION_REMOVED };

				accountMembers = plabCustRepository.findByOfferAndCustIdNoAndAcctNoAndIndicatorAndEndDate(offerId,
						custId, acctNo, indicator, subCustBillCycleStartDt);

			/*} else {
				serviceHeader.setErrorMsg("No Plab Customer Bill Cycle found for the mdn.Please check the mdn");
				poundDataService.setServiceHeader(serviceHeader);
				poundDataMarshaller.marshal(poundDataService, sWriter);
				logger.debug("For mdn : " + mdn + " getdetailUsage(#data) xml sent to dvs : "
						+ sWriter.toString());
				return new ResponseEntity(
						sWriter.toString().substring(sWriter.toString().indexOf('\n') + 1),
						HttpStatus.NOT_FOUND);
			}*/
			
			Long[] indicator = { PlabConstants.ENROLLED, PlabConstants.REMOVING,
					PlabConstants.REMOVED_PENDING_EOC, PlabConstants.EARLY_TERMINATE_PENDING_EOC,
					PlabConstants.EARLY_TERMINATE_VISION_REMOVED };
			accountMembers = plabCustRepository.findByOfferAndCustIdNoAndAcctNoAndIndicator(offer.getOfferId(), custId, acctNo,indicator);

			additionalUsageProductList = new AdditionalUsageProductList();

			if (accountMembers != null && !accountMembers.isEmpty()) {

				AdditionalUsageProductAllowances additionaUsageProductAllowances = new AdditionalUsageProductAllowances();

				additionaUsageProductAllowances.setDetailType(offer.getName());
				additionaUsageProductAllowances.setProductType("MDN");
				additionaUsageProductAllowances
						.setAdditionalUsageProductDataItemListCount(Long.toString(accountMembers.size()));

				AdditionalUsageProductDataItemList additionalUsageProductAllowanceDataItemList = new AdditionalUsageProductDataItemList();

				for (PlabCust PlabCustAcctMdn : accountMembers) {

					OfferInstance offerInstance = productManager.getValidOfferInstance(PlabCustAcctMdn.getMdn());

					if (offerInstance != null) {
						logger.debug("offerInstanceID in getdetailUsage" + offerInstance.getOfferInstanceId());
						serviceInstanceList = serviceInstanceRepository.findLatestByOfferInstance(offerInstance.getOfferInstanceId());

						if (serviceInstanceList != null && !serviceInstanceList.isEmpty()) {
							servInstance = null;
							for (ServiceInstance servInstanceTemp : serviceInstanceList) {
								if (servInstanceTemp.getService().getRatingGroup().getRatingGroupId() != 3300) {
									servInstance = servInstanceTemp;
									logger.debug("servInstanceID : " + servInstance.getServiceInstanceId());
								}
							}
							
							if(servInstance == null){
								continue;
							}
							

							AdditionalUsageProductDetails additionalUsageProductDetails = new AdditionalUsageProductDetails();
							additionalUsageProductDetails.setTitle("MDN");
							additionalUsageProductDetails.setSubTitle(PlabCustAcctMdn.getMdn());
							additionalUsageProductDetails.setBarColor("G");
							additionalUsageProductDetails.setBarPercent("0.0");
							additionalUsageProductDetails.setDescription(servInstance.getServiceName());
							additionalUsageProductDetails.setEffectiveDate(servInstance.getDateCreated().toGMTString());
							additionalUsageProductDetails
									.setAllowance(Long.toString(servInstance.getService().getAllowance()));
							additionaUsageProductAllowances.setIsAddOn("Y");
							List<RuleInstance> ruleInstanceList = servInstance.getRules();
							if (ruleInstanceList != null && !ruleInstanceList.isEmpty()) {
								for (RuleInstance ruleInstance : ruleInstanceList) {
									if (ruleInstance.getRuleType().equalsIgnoreCase(Rule.classficationRuleType)
											&& ruleInstance.getExpressionType()
													.equalsIgnoreCase(Rule.byTimeExpressionType)) {
										String expr = getFormattedDescriptionGmttoEst(ruleInstance.getExpression());
										additionaUsageProductAllowances.setDataHubPlanDescription(expr);
										StringBuffer sb = new StringBuffer();
										sb.append(PlabConstants.NIGHTSURFER_DATA_USAGE_DESC);
										sb.append(expr.substring(expr.indexOf(" ")));
										additionaUsageProductAllowances.setDataUsageDescription(sb.toString());

									}
								}
							} else {
								serviceHeader.setErrorMsg("No Active Rule Instance found for the mdn.Please check the mdn");
								poundDataService.setServiceHeader(serviceHeader);
								poundDataMarshaller.marshal(poundDataService, sWriter);
								logger.debug("For mdn : " + mdn + " getdetailUsage(#data) xml sent to dvs : "
										+ sWriter.toString());
								return new ResponseEntity(
										sWriter.toString().substring(sWriter.toString().indexOf('\n') + 1),
										HttpStatus.NOT_FOUND);
							}
							additionaUsageProductAllowances
									.setTapToChangeMessage(PlabConstants.NIGHTSURFER_TAP_TO_CHANGE_MESSAGE);
							additionaUsageProductAllowances
									.setTapToChangeSubMessage(PlabConstants.NIGHTSURFER_TAP_TO_CHANGE_SUB_MESSAGE);
							additionaUsageProductAllowances
									.setDataHubDescription(PlabConstants.NIGHTSURFER_DATA_HUB_DESCRIPTION);
							additionaUsageProductAllowances
									.setDataHubUpdateDescription(PlabConstants.NIGHTSURFER_DATA_HUB_UPDATE_DESCRIPTION);
							long totalUsed = servInstance.getCounter();
							if (totalUsed != 0){
                                totalUsed = totalUsed / 1024 / 1024;
							}
							
							additionalUsageProductDetails.setTotalUsed(Long.toString(totalUsed));
							if(servInstance.getAllowance()==-1){					//for night surfer addon is yes
								additionalUsageProductDetails.setRemaining("-1");
								additionalUsageProductDetails.setRemainingPercent("100.0");
							}
							else{
								long percentRemaining=0;
								long remaining = servInstance.getAllowance()-totalUsed;
								remaining=remaining/1024/1024;
								if(servInstance.getAllowance()!=0)
									percentRemaining = (remaining/servInstance.getAllowance())*100;
								additionalUsageProductDetails.setRemaining(Long.toString(remaining));
								additionalUsageProductDetails.setRemainingPercent(Long.toString(percentRemaining));
							}
							additionalUsageProductDetails.setUnitOfMeasure("MB");
							additionalUsageProductAllowanceDataItemList.getAdditionalUsageProductDataItem()
									.add(additionalUsageProductDetails);
						} else {
							serviceHeader.setErrorMsg("No Active Service Instance found for the mdn.Please check the mdn");
							poundDataService.setServiceHeader(serviceHeader);
							poundDataMarshaller.marshal(poundDataService, sWriter);
							logger.debug("For mdn : " + mdn + " getdetailUsage(#data) xml sent to dvs : "
									+ sWriter.toString());
							return new ResponseEntity(
									sWriter.toString().substring(sWriter.toString().indexOf('\n') + 1),
									HttpStatus.NOT_FOUND);
						}
					} else {
						serviceHeader.setErrorMsg("No Active Offer Instance found for the mdn.Please check the mdn");
						poundDataMarshaller.marshal(poundDataService, sWriter);
						logger.debug(
								"For mdn : " + mdn + " getdetailUsage(#data) xml sent to dvs : " + sWriter.toString());
						return new ResponseEntity(sWriter.toString().substring(sWriter.toString().indexOf('\n') + 1),
								HttpStatus.NOT_FOUND);
					}
				}
				additionaUsageProductAllowances
						.setAdditionalUsageProductDataItemList(additionalUsageProductAllowanceDataItemList);
				additionalUsageProductList.getAdditionalUsageProductAllowances().add(additionaUsageProductAllowances);
				dataUsageInfo.setAdditionalUsageProductList(additionalUsageProductList);
				serviceResponse.setDataUsageInfo(dataUsageInfo);
				serviceBody.setServiceResponse(serviceResponse);
				serviceHeader.setErrorMsg("");
				poundDataService.setServiceHeader(serviceHeader);
				poundDataService.setServiceBody(serviceBody);
				poundDataMarshaller.marshal(poundDataService, sWriter);
				logger.debug("For mdn : " + mdn + " getdetailUsage(#data) xml sent to dvs : " + sWriter.toString());
				return new ResponseEntity(sWriter.toString().substring(sWriter.toString().indexOf('\n') + 1),
						HttpStatus.OK);
			} else {
				serviceHeader.setErrorMsg("No active plab customer found");
				poundDataMarshaller.marshal(poundDataService, sWriter);
				logger.debug("For mdn : " + mdn + "  getdetailUsage(#data) xml sent to dvs : " + sWriter.toString());
				return new ResponseEntity(sWriter.toString().substring(sWriter.toString().indexOf('\n') + 1),
						HttpStatus.NOT_FOUND);
			}
		}

		catch (Exception e) {
			logger.error("Exception in OfferController (getDetailUsage()) : ", e.getMessage(), e);
			serviceHeader.setErrorMsg("Exception in getDetailUsage:" + e.getMessage());
			poundDataService.setServiceHeader(serviceHeader);
			try {
				poundDataMarshaller.marshal(poundDataService, sWriter);
			} catch (JAXBException e1) {
				e1.printStackTrace();
			}
			logger.debug("For mdn : " + mdn + " getdetailUsage(#data) xml sent to dvs : " + sWriter.toString());
			return new ResponseEntity(sWriter.toString().substring(sWriter.toString().indexOf('\n') + 1),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private String getFormattedDescription(String expr){
		if(expr == null || expr.isEmpty()){
			return "";
		}
		
		if(expr.indexOf("EST") > 0){
			expr = expr.replaceAll(" EST", "");
		}
		
		String strArr[] = expr.split(" ");
		
		for(String st : strArr){
			if(!st.isEmpty() && Character.isDigit(st.charAt(0))){
				String _12hrs = PricingLabUtility.convert24HrTo12HrFormat(st, poundDataTimeFormat);
				expr = expr.replace(st, _12hrs);
			}
		}
		return expr;
	}
	
	private String getFormattedDescriptionGmttoEst(String expr){
		if(expr == null || expr.isEmpty()){
			return "";
		}
		if(expr.contains("GMT")){
			String strArr[] = expr.split(" ");
			StringBuffer returnString = new StringBuffer();
			
			for(String st : strArr){
				if(!st.isEmpty() && Character.isDigit(st.charAt(0))){
					String estString = PricingLabUtility.convertGmtToEst(st);
					logger.debug(st+";"+estString);
					String _12hrs = PricingLabUtility.convert24HrTo12HrFormat(estString, poundDataTimeFormat);
					returnString.append(_12hrs);
					returnString.append(" ");
					logger.debug(_12hrs+";"+expr);
				}else{
					returnString.append(st);
					returnString.append(" ");
				}
			}
			
			if(returnString!=null){
				expr = returnString.toString().trim();
				if(expr.indexOf("GMT") > 0){
					expr = expr.replaceAll(" GMT", "");
				}
			}
		}
		return expr;
	}

	
//reqezcheckout is done for evry mdn
	/*
	 * month starts at 0 as per calendar api of java
	 */
	
	@RequestMapping(value = "/offer/billing/{mdn}/{month}/{year}",method=RequestMethod.POST)
	public ResponseEntity customerBilling(@PathVariable("mdn") String mdn,@PathVariable("month") String month,@PathVariable("year") String year,boolean earlyTerminate){

		logger.debug("Customer Billing for mdn : "+mdn +" month : "+month+" year : "+year);	
		logger.debug("Customer Billing earlyTerminateFlag : "+earlyTerminate);	
		Date currentDt;
		Date billCycleStartDate=null;
		Date billCycleEndDate=null;
		boolean addOn = false;
		VerizonEntity vzEntity = null;
		List<PlabCust> plabCustMembersList = null;
		Map<String,String> finalResponseMap = new HashMap<>();
		
		if(mdn!=null){		
			SimpleDateFormat sdfTemp = new SimpleDateFormat("MM/dd/yyyy");	
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

			Long[] indicator = {PlabConstants.ENROLLED};

			List<PlabCust> plabCustPrimaryList = plabCustRepository.findByMdnAndIndicator(mdn, indicator);

			if(plabCustPrimaryList!=null && !plabCustPrimaryList.isEmpty()){

				PlabCust plabCustPrimary = plabCustPrimaryList.get(0);

				Offer offer = plabCustPrimary.getOffer();
				List<Choice> choiceList = productManager.getChoiceList(offer);

				List<Date> billCycleDates = null;
				if(month!=null && !month.isEmpty() && year!=null && !year.isEmpty()){
					billCycleDates=productManager.getSubCustBillCycleDatesForGivenMonth(plabCustPrimary.getCustIdNo(), plabCustPrimary.getAcctNo(),month, year);
				}
				else{
					billCycleDates = productManager.getSubCustBillCycleDates(plabCustPrimary.getCustIdNo(), plabCustPrimary.getAcctNo());
				}

				if(billCycleDates!=null && !billCycleDates.isEmpty()){

					logger.debug("subCustBlCycleStartDate:"+billCycleDates.get(0));

					billCycleStartDate = billCycleDates.get(0);
					billCycleEndDate = billCycleDates.get(1);
					plabCustMembersList = plabCustRepository.findByOfferAndCustIdNoAndAcctNoAndIndicator(offer.getOfferId(), plabCustPrimary.getCustIdNo(), plabCustPrimary.getAcctNo(), indicator);

					logger.debug("plabCustMembersList : "+plabCustMembersList.size());
				}

				if(plabCustMembersList!=null && !plabCustMembersList.isEmpty()){

					for(PlabCust plabCust : plabCustMembersList){

						logger.debug("PlabCust : "+plabCust.getMdn());
						logger.debug("plabCustEndDate:"+plabCust.getPlEndDate());

						try{

							OfferInstance offerInstance = productManager.getValidOfferInstance(plabCust.getMdn());						

							if(offerInstance!=null){  

								logger.debug("offerInstance : "+offerInstance.getOfferInstanceId());

								List<RuleInstance> ruleInstanceList = new ArrayList<>();

								List<ServiceInstance> serviceInstanceList = serviceInstanceRepository.findLatestByOfferInstance(offerInstance.getOfferInstanceId());

								if(serviceInstanceList!=null && !serviceInstanceList.isEmpty()){

									for(ServiceInstance serviceInstance : serviceInstanceList){	
										if(serviceInstance.getService().getRatingGroup().getRatingGroupId()!=3300){
											ruleInstanceList.addAll(ruleInstanceRepository.findByServiceInstance(serviceInstance));		
											addOn= serviceInstance.getService().isAddOn();
										}

									}	
									//if the ns is currently toggled off and was on earlier then billing should be generated for that mdn
									if(ruleInstanceList!=null && ruleInstanceList.isEmpty()){
										 serviceInstanceList = serviceInstanceRepository.findByOfferInstanceAndService(offerInstance.getOfferInstanceId(), offer.getServices().get(0).getServiceId());
										 if(serviceInstanceList!=null && !serviceInstanceList.isEmpty())
											 ruleInstanceList.addAll(ruleInstanceRepository.findByServiceInstance(serviceInstanceList.get(0)));		
									}
									//calculate charge
									if(ruleInstanceList!=null && !ruleInstanceList.isEmpty()){

										double charge=0;
										long billableDays=0;
										//Map<String,String> response = null;
										Date billCalcStartDt = null;
										Date billCalcEndDt = null;
										List<ChoiceInstance> choiceInstanceList=new ArrayList<>();
										List<ChoiceInstanceHistory> choiceInstanceHistList = new ArrayList<>();
										List<ChoiceInstanceHistory> choiceInstHistForStartDtList = new ArrayList<>();
										
										List<Object> billableDaysList = mcsManager.getBillableDays(billCycleStartDate,billCycleEndDate,offerInstance.getDateCreated(), offerInstance.getEndTmstamp(),earlyTerminate);
										
										if(billableDaysList!=null && !billableDaysList.isEmpty()){
											billableDays = (long)billableDaysList.get(0);
											billCalcStartDt = (Date)billableDaysList.get(1);
											billCalcEndDt = (Date)billableDaysList.get(2);

											logger.debug("billCalcStartDt:"+billCalcStartDt);
											logger.debug("billCalcEndDt:"+billCalcEndDt);	
											logger.debug("billableDays : "+billableDays);
										}
										
										
										for(RuleInstance ruleInstance:ruleInstanceList){
											if(ruleInstance.getRuleType().equals(Rule.classficationRuleType) && ruleInstance.getExpressionType().equals(Rule.byTimeExpressionType))	{			//this is addon --new line item
												charge = ruleInstance.getServiceInstance().getService().getCharge();	
												double chargePerDay = mcsManager.getChargePerDayNs(charge, month, year);
												//get billable days based on toggle on and off
												long billableDaysforNS = mcsManager.getBillableDaysForNS(billCalcStartDt, billCalcEndDt, offerInstance, choiceList);
												if(billableDaysforNS>0){
													double mcsCharge = mcsManager.calcMcsNsCharge(billableDaysforNS,chargePerDay);
													logger.debug("customerBilling() : charge in night surfer for mdn "+ plabCust.getMdn()+" is "+mcsCharge);
													finalResponseMap.putAll(mcsManager.reqEzCheckOut(mcsCharge, plabCust, addOn,offer.getName(),null));   
												}else{
													logger.debug("No charge generated for offerInstance : "+offerInstance.getOfferInstanceId()+" +mdn : "+plabCust.getMdn());
												}
											}
											else if(ruleInstance.getRuleType().equals(Rule.classficationRuleType) && ruleInstance.getExpressionType().equals(Rule.byApplicationExpressionType)){	//this is addon --new line item
												charge = ruleInstance.getServiceInstance().getService().getCharge();
												finalResponseMap.putAll(mcsManager.reqEzCheckOut(charge, plabCust, addOn, offer.getName(),null));
											}
											else if(ruleInstance.getRuleType().equals(Rule.qosRuleType) && ruleInstance.getExpressionType().equals(Rule.bySpeedExpressionType)){																			
												if(ruleInstance.getAttributes().containsValue(PlabConstants.SPEED_FAST) || ruleInstance.getAttributes().containsValue(PlabConstants.SPEED_FASTER) || ruleInstance.getAttributes().containsValue(PlabConstants.SPEED_FASTEST)){

													Set<QoS> qosList = ruleInstance.getServiceInstance().getService().getQos();					
													List<Double> chargePerDay = null;
													if(qosList!=null && !qosList.isEmpty()){
														chargePerDay = mcsManager.getChargePerDaySpeed(qosList,month,year);
														
														logger.debug("customerBilling() : chargePerDay : "+ chargePerDay.get(0)+";"+chargePerDay.get(1)+";"+chargePerDay.get(2));		//not expected to be 0
													}
														
														if(choiceList!=null && !choiceList.isEmpty()){
															for(Choice choice : choiceList){
															 choiceInstanceList.addAll(choiceInstanceRepository.findByChoiceAndOfferInstance(choice.getId(), offerInstance.getOfferInstanceId()));
															}
														}else{
															logger.debug("customerBilling() : Choice not found for the offer : "+offer.getOfferId());
															return new ResponseEntity("customerBilling() : Choice not found for the offer : ",HttpStatus.NOT_FOUND);
														}

														if(choiceInstanceList!=null && !choiceInstanceList.isEmpty()){
															for(ChoiceInstance choiceInstance : choiceInstanceList){
																choiceInstanceHistList.addAll(choiceInstanceHistoryRepository.findByChoiceInstanceAndSelectedAndDateCreatedBetween(choiceInstance, true, billCalcStartDt, billCalcEndDt));
																choiceInstHistForStartDtList.addAll(choiceInstanceHistoryRepository.findByChoiceInstanceAndSelectedAndDateCreatedBefore(choiceInstance, true, billCalcStartDt));
															}

															if(choiceInstanceHistList!=null && !choiceInstanceHistList.isEmpty()){

																logger.debug("choiceInstanceHistList size: "+choiceInstanceHistList.size());
																logger.debug("choiceInstanceList size: "+choiceInstanceList.size());

																Date junkDate  = sdf.parse((Calendar.getInstance().get(Calendar.MONTH)+2)+"/"+(99)+"/1999"+" 00:00:01");
																Map<Date,String> billMap = new HashMap<>();
																billMap.put(sdfTemp.parse(sdfTemp.format(junkDate)),"junk");										//do not remove this, it is needed

																for(ChoiceInstanceHistory choiceInstHist : choiceInstanceHistList){							

																	logger.debug("Id: "+choiceInstHist.getChoiceInstanceHistoryId());

																	Date dateTemp =  sdfTemp.parse(sdfTemp.format(choiceInstHist.getDateCreated()));

																	String currSpeedTier = choiceInstHist.getChoiceInstance().getChoice().getTitle();
																	String storedSpeedTier = billMap.get(dateTemp);

																	if(storedSpeedTier!=null){	
																		if(storedSpeedTier.equalsIgnoreCase(PlabConstants.SPEED_FASTEST)){
																			//do nothing
																		}
																		else if(storedSpeedTier.equalsIgnoreCase(PlabConstants.SPEED_FASTER)){
																			if(currSpeedTier.equalsIgnoreCase(PlabConstants.SPEED_FASTEST)){
																				billMap.put(dateTemp,currSpeedTier); 
																				storedSpeedTier = currSpeedTier;
																			}

																		}
																		else if(storedSpeedTier.equalsIgnoreCase(PlabConstants.SPEED_FAST)){
																			billMap.put(dateTemp,currSpeedTier); 
																			storedSpeedTier=currSpeedTier;
																		}
																	}
																	else{
																		billMap.put(dateTemp,currSpeedTier);
																	}

																}	
																//sysout
																Iterator it = billMap.entrySet().iterator();
																while (it.hasNext()) {
																	Map.Entry x= ((Map.Entry)it.next());
																	logger.debug("choiceinstancehistory:"+x.getKey()+";"+x.getValue());
																}
																//handle the bill calc start date;; when the retrieved choice instance history is not available for that date
																if(billMap.get(billCalcStartDt)==null){
																	//choiceInstHistForStartDtList = choiceInstanceHistoryRepository.findByChoiceInstanceAndSelectedAndDateCreatedBefore(choiceInstance, selected, fromDate)(offerInstance, true, billCalcStartDt);

																	String speedTierForStartDt=null;

																	if(choiceInstHistForStartDtList!=null && !choiceInstHistForStartDtList.isEmpty()){
																		logger.debug("bill calc start date : choiceInstHistForStartDtList size: "+choiceInstHistForStartDtList.size());
																		Collections.sort(choiceInstHistForStartDtList,Collections.reverseOrder());
																		for(ChoiceInstanceHistory cih :choiceInstHistForStartDtList ){
																			logger.debug("test descending order in choiceisntancehistorystartdt for prior dates:"+cih.getDateCreated());
																		}
																		speedTierForStartDt = choiceInstHistForStartDtList.get(0).getChoiceInstance().getChoice().getTitle();  			//no null check required

																		billMap.put(billCalcStartDt,speedTierForStartDt);

																		Date dateTobeComp = sdfTemp.parse(sdfTemp.format(choiceInstHistForStartDtList.get(0).getDateCreated()));
																		Calendar dtToBeCompCal = mcsManager.getCalendarInstance(dateTobeComp);

																		for(ChoiceInstanceHistory choiceInstanceHistory : choiceInstHistForStartDtList){
																			Date tempDate = sdfTemp.parse(sdfTemp.format(choiceInstanceHistory.getDateCreated()));
																			Calendar tempDtCal = mcsManager.getCalendarInstance(tempDate);

																			if(tempDtCal.compareTo(dtToBeCompCal)<0){
																				break;
																			}
																			else if(tempDtCal.compareTo(dtToBeCompCal)==0){
																				String tempSpeedTier = choiceInstanceHistory.getChoiceInstance().getChoice().getTitle();
																				if(speedTierForStartDt.equalsIgnoreCase(PlabConstants.SPEED_FASTEST)){
																					break;
																				}
																				else if(speedTierForStartDt.equalsIgnoreCase(PlabConstants.SPEED_FASTER)){
																					if(tempSpeedTier.equalsIgnoreCase(PlabConstants.SPEED_FASTEST))
																						billMap.put(billCalcStartDt,tempSpeedTier); 
																					speedTierForStartDt=tempSpeedTier;
																				}
																				else if(speedTierForStartDt.equalsIgnoreCase(PlabConstants.SPEED_FAST)){
																					billMap.put(billCalcStartDt,tempSpeedTier); 
																					speedTierForStartDt=tempSpeedTier;
																				}
																			}
																		}
																	}
																	else{																						
																		Collections.sort(choiceInstanceHistList);		
																		for(ChoiceInstanceHistory cih :choiceInstanceHistList ){
																			logger.debug("test ascending order in choiceInstanceHistList for after dates:"+cih.getDateCreated());
																		}
																		Date dtToBeComp =  sdfTemp.parse(sdfTemp.format(choiceInstanceHistList.get(0).getDateCreated()));
																		Calendar dtToBeCompCal = mcsManager.getCalendarInstance(dtToBeComp);
																		speedTierForStartDt = choiceInstanceHistList.get(0).getChoiceInstance().getChoice().getTitle();
																		billMap.put(billCalcStartDt,speedTierForStartDt);

																		for(ChoiceInstanceHistory choiceInstanceHistory : choiceInstanceHistList){
																			Date tempDate = sdfTemp.parse(sdfTemp.format(choiceInstanceHistory.getDateCreated()));
																			Calendar tempDtCal = mcsManager.getCalendarInstance(tempDate);
																			if(tempDtCal.compareTo(dtToBeCompCal)>0){
																				break;
																			}
																			else if(tempDtCal.compareTo(dtToBeCompCal)==0){
																				String tempSpeedTier = choiceInstanceHistory.getChoiceInstance().getChoice().getTitle();
																				if(speedTierForStartDt.equalsIgnoreCase(PlabConstants.SPEED_FASTEST)){
																					break;
																				}
																				else if(speedTierForStartDt.equalsIgnoreCase(PlabConstants.SPEED_FASTER)){
																					if(tempSpeedTier.equalsIgnoreCase(PlabConstants.SPEED_FASTEST)){
																						billMap.put(billCalcStartDt,tempSpeedTier); 
																						speedTierForStartDt=tempSpeedTier;
																					}
																				}
																				else if(speedTierForStartDt.equalsIgnoreCase(PlabConstants.SPEED_FAST)){
																					billMap.put(billCalcStartDt,tempSpeedTier);
																					speedTierForStartDt=tempSpeedTier;
																				}
																			}
																		}
																	}														 

																	Iterator it1 = billMap.entrySet().iterator();
																	while (it1.hasNext()) {
																		Map.Entry x= ((Map.Entry)it1.next());
																		logger.debug("test billMap post start date:"+x.getKey()+";"+x.getValue());
																	}
																}

																//handle choice instances
																String cIspeedTier = null;
																
																Collections.sort(choiceInstanceHistList,Collections.reverseOrder());
																																
																for(ChoiceInstance choiceInstance:choiceInstanceList){
																	if(choiceInstance.getSelected().booleanValue())
																		cIspeedTier=choiceInstance.getChoice().getTitle();
																}	 
																Date dateTemp = sdfTemp.parse(sdfTemp.format(choiceInstanceHistList.get(0).getDateCreated()));	
																logger.debug("Date updated for choice instance:"+dateTemp);

																if(cIspeedTier.equalsIgnoreCase(PlabConstants.SPEED_FASTEST))	{									//always reflects the latest choice
																	billMap.put(dateTemp ,PlabConstants.SPEED_FASTEST); 											
																}
																else if(cIspeedTier.equalsIgnoreCase(PlabConstants.SPEED_FASTER)){
																	if(billMap.get(dateTemp).equalsIgnoreCase(PlabConstants.SPEED_FAST))
																		billMap.put(dateTemp,PlabConstants.SPEED_FASTER);
																}

																billMap.remove(junkDate);

																List<Double> speedCharge =  mcsManager.calcCharge(billMap,billCalcStartDt,billCalcEndDt,chargePerDay.get(0),chargePerDay.get(1),chargePerDay.get(2)); //chargerPerDay not expected  to be null
																finalResponseMap.putAll(mcsManager.reqEzCheckOut(charge, plabCust, addOn, offer.getName(),speedCharge));
															}
															else{			
																List<Double> speedCharge =  mcsManager.calcChargeNoHistory(chargePerDay, choiceInstanceList, billableDays);
																//logger.debug("no choiceinstancehistory-->charge:"+charge);
																finalResponseMap.putAll(mcsManager.reqEzCheckOut(charge, plabCust, addOn, offer.getName(),speedCharge));
															}	
														}
														else{
															logger.debug("customerBilling() : ChoiceInstance not found for the mdn : "+plabCust.getMdn());
															return new ResponseEntity("ChoiceInstance not found for the mdn",HttpStatus.NOT_FOUND);
														}
												}
											}
										}								
										}
									else{
										logger.debug("customerBilling() : RuleInstance not found for the mdn : "+plabCust.getMdn());
										//return new ResponseEntity("customerBilling() :  RuleInstance not found for the mdn : "+plabCust.getMdn(),HttpStatus.NOT_FOUND);	
									}
								}
								else{
									logger.debug("customerBilling() : ServiceInstance not found for the mdn : "+plabCust.getMdn());
									return new ResponseEntity("customerBilling() :  ServiceInstance not found for the mdn",HttpStatus.NOT_FOUND);
								}
							}
							else{
								logger.debug("customerBilling() : Offerinstance not found for the mdn : "+plabCust.getMdn());
								return new ResponseEntity("customerBilling() :  Offerinstance not found for the mdn",HttpStatus.NOT_FOUND);
							}
						}
						catch (Exception e) {
							logger.error("customerBilling() :  Exception in OfferController (customerBilling) : ",e.getMessage(),e); 
							return new ResponseEntity("customerBilling() :  Exception in OfferController (customerBilling)",HttpStatus.INTERNAL_SERVER_ERROR);	
						}		
					}
					Iterator it = finalResponseMap.entrySet().iterator();
					StringBuffer errorBuffer = new StringBuffer();
					StringBuffer successBuffer = new StringBuffer();
					while(it.hasNext()){
						Map.Entry<String,String> entry = (Map.Entry)it.next();
						if(entry.getKey().contains("Error")){
							errorBuffer.append(entry.getValue());
						}
						else{
							successBuffer.append(entry.getValue());
						}
					}
					if(errorBuffer.toString()!=null && !errorBuffer.toString().isEmpty()){
						logger.debug("customerBilling() : "+errorBuffer.toString());
						return new ResponseEntity(errorBuffer.toString(),HttpStatus.INTERNAL_SERVER_ERROR);	
					}
					else{
					logger.debug("customerBilling() : "+successBuffer.toString());
					return new ResponseEntity(successBuffer.toString(),HttpStatus.OK);
					}
				}
				else{
					logger.debug("customerBilling() : No active plab customer found for mdn : "+mdn);
					return new ResponseEntity("No active plab customer found",HttpStatus.NOT_FOUND);
				}
			}
			else{
				logger.debug("customerBilling() : No plab customer found for mdn : "+mdn);
				return new ResponseEntity("No Plab Cust found for the given mdn",HttpStatus.NOT_FOUND);	
			}
		}
		else {
			logger.debug("customerBilling() :  mdn has to provided");
			return new ResponseEntity("customerBilling() : mdn has to provided",HttpStatus.BAD_REQUEST);	
		}
	}
	
	
	@RequestMapping(value = "/offer/nbs/{custID}/{acctNum}",method=RequestMethod.POST)
	public ResponseEntity nbs(@PathVariable("custID") String custID,@PathVariable("acctNum") String acctNum,@RequestBody String billProrateCalcXml){

		logger.debug("OfferController invoking nbs with xml : "+billProrateCalcXml);
		logger.debug("OfferController invoking nbs with custID : "+custID);
		logger.debug("OfferController invoking nbs with acctNum : "+acctNum);

		com.vzwcorp.pricinglab.vo.dvs.nbs.Service service = new com.vzwcorp.pricinglab.vo.dvs.nbs.Service();
		com.vzwcorp.pricinglab.vo.dvs.nbs.ServiceHeader serviceHeader = new com.vzwcorp.pricinglab.vo.dvs.nbs.ServiceHeader();
		com.vzwcorp.pricinglab.vo.dvs.nbs.ServiceBody serviceBody = new com.vzwcorp.pricinglab.vo.dvs.nbs.ServiceBody();
		com.vzwcorp.pricinglab.vo.dvs.nbs.ServiceResponse serviceResponse = new com.vzwcorp.pricinglab.vo.dvs.nbs.ServiceResponse();
		StringWriter sw = new StringWriter();

		if(custID!=null && !custID.isEmpty() && acctNum!=null && !acctNum.isEmpty() && billProrateCalcXml!=null && !billProrateCalcXml.isEmpty()){

			Map<String,String> response = dvsManager.billProrateCalc(custID,acctNum,billProrateCalcXml);

			if(response.get(DvsConstants.ERROR_IN_BILL_PRORATE_CALC)!=null){
				logger.error(response.get(DvsConstants.ERROR_IN_BILL_PRORATE_CALC));
				return new ResponseEntity(response.get(DvsConstants.ERROR_IN_BILL_PRORATE_CALC),HttpStatus.INTERNAL_SERVER_ERROR);
			}
			else if(response.get(DvsConstants.BILL_PRORATE_CALC_NOT_FOUND_RESPONSE)!=null){
				logger.error(response.get(DvsConstants.BILL_PRORATE_CALC_NOT_FOUND_RESPONSE));
				return new ResponseEntity(response.get(DvsConstants.BILL_PRORATE_CALC_NOT_FOUND_RESPONSE),HttpStatus.NOT_FOUND);
			}
			else if(response.get(DvsConstants.ERROR_IN_BILL_PRORATE_CALC_MOBILE_FIRST_TOTAL_NEXT)!=null){
				logger.error("BAD REQUEST : MOBILEFIRSTTOTALNEXT tag is missing "+response.get(DvsConstants.ERROR_IN_BILL_PRORATE_CALC_MOBILE_FIRST_TOTAL_NEXT));
				return new ResponseEntity(response.get(DvsConstants.ERROR_IN_BILL_PRORATE_CALC_MOBILE_FIRST_TOTAL_NEXT),HttpStatus.BAD_REQUEST);
			}
			else{
				return new ResponseEntity(response.get(DvsConstants.BILL_PRORATE_CALC_RESPONSE),HttpStatus.OK);
			}
		}
		else{ 
			service = dvsManager.setErrorMsgAndBillProrateCalcXml(serviceHeader, "CustId,AccountNum,Xml should be provided for nbs request", serviceResponse, serviceBody, service, null);
			Marshaller marshaller = dvsManager.getMarshaller(DvsConstants.NBS_PACKAGE);
			try {
				marshaller.marshal(service, sw);
			} catch (JAXBException e) {
				e.getMessage();
			}
			logger.debug("CustId,AccountNum,Xml should be provided for nbs request "+sw.toString());
			return new ResponseEntity(sw.toString(),HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/customer/history/{offerInstanceId}", method = RequestMethod.GET)
	public ResponseEntity getCustHistory(@PathVariable("offerInstanceId") Long offerInstanceId){
		if(offerInstanceId <= 0 || offerInstanceId == null){
			logger.debug("getCustHistory() : Invalid OfferInstance");
			return new ResponseEntity<>("Invalid OfferInstance", HttpStatus.BAD_REQUEST);
		}
		OfferInstance oi = offerInstanceRepository.findOne(offerInstanceId);
		if(oi == null){
			logger.debug("getCustHistory() : offerInstance Not Found");
			return new ResponseEntity<>("offerInstance Not Found", HttpStatus.NOT_FOUND);
		}
		
		logger.debug("getCustHistory() for offerInstanceId :"+offerInstanceId);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.setDateFormat(df);
        ObjectWriter writer = mapper.writerWithView(Views.PlabAdminView.class);
		String jsonString = "";
		
		ArrayList<Object> fullHist = new ArrayList<>();
		ArrayList<ChoiceInstanceHistory> cih = new ArrayList<>();
		for(ChoiceInstance ci: oi.getChoiceInstances()){
			for(ChoiceInstanceHistory cihist: ci.getChoiceInstanceHistory()){
				//if(cihist.getSelected()){
					cih.add(cihist);
				//}
			}
		}
		fullHist.addAll(cih);
		for(ChoiceInstance ci: oi.getChoiceInstances()){
			if(ci.getSelected() == true)
				fullHist.add(ci);
		}
		
		try{
			jsonString = writer.writeValueAsString(fullHist);
			logger.debug("getCustHistory() for offerInstanceId :"+offerInstanceId+" ,response json is "+jsonString);
		} catch(JsonProcessingException e) {
			logger.error("Exception parsing choiceInstanceHistory",e.getMessage(),e);
			return new ResponseEntity<>("Exception parsing choiceInstanceHistory",HttpStatus.INTERNAL_SERVER_ERROR);
		} catch(Exception ex){
			logger.error("Exception in getCustHistory",ex.getMessage(),ex);
			return new ResponseEntity<>("Exception in getCustHistory", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.debug(jsonString);
		return new ResponseEntity<String>(jsonString, HttpStatus.OK);
	}

	@RequestMapping(value = "feedback/submit/reply/{ccid}", method = RequestMethod.POST)
	public ResponseEntity saveReply(@PathVariable("ccid") Long ccid,@RequestParam( value = "reply") String replyString){
		
		logger.debug("saveReply() : ccid"+ccid);
		logger.debug("saveReply() : replyString"+replyString);
		
		if(ccid == null || ccid <= 0){
			logger.debug("saveReply() : Invalid ccid"); 
			return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Invalid ccid", PlabConstants.STATUS_ERROR), HttpStatus.BAD_REQUEST);
		}
		if(replyString == null || replyString == ""){
			logger.debug("saveReply() : replyString null or empty");
			return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "replyString null or empty", PlabConstants.STATUS_ERROR), HttpStatus.BAD_REQUEST);
		}
		Feedback feedback = feedbackRepository.findOne(ccid);
		feedback.setReplyComments(replyString);
		feedbackRepository.saveAndFlush(feedback);
		logger.debug("saveReply() : reply saved");
		return new ResponseEntity(utility.createResponseInfo(HttpStatus.OK, "reply saved", PlabConstants.STATUS_SUCCESS),HttpStatus.OK);
	}
	

	@RequestMapping(value = "/offerinstance/{offerInstanceId}/usage", method = RequestMethod.GET)
	public ResponseEntity getUsageRecords(@PathVariable("offerInstanceId")Long offerInstanceId,
										  @RequestParam(required = false, value="startTimestamp")String startTimestamp,
										  @RequestParam(required = false, value = "endTimestamp")String endTimestamp){
		if(offerInstanceId == null || offerInstanceId <= 0){
			logger.debug("getUsageRecords() : offerInstanceId not valid"); 
			return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "offerInstanceId not valid", PlabConstants.STATUS_ERROR), HttpStatus.BAD_REQUEST);
		}
		
		List<BillingInfo> billingInfo = null;
		
		if(startTimestamp == null || endTimestamp == null){
			billingInfo = billingInfoRepository.findByOfferInstanceId(offerInstanceId);
		} else {
			billingInfo = billingInfoRepository.findByOfferInstanceIdAndStartTimeAndEndTime(offerInstanceId, startTimestamp, endTimestamp);
		}
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.setDateFormat(df);
        ObjectWriter writer = mapper.writerWithView(Views.PlabAdminView.class);
		String jsonString = null;
		try{
			jsonString = writer.writeValueAsString(billingInfo);
		} catch(JsonProcessingException e){
			logger.error("getUsageRecords() : Error parsing billing info");
			return new ResponseEntity("Error Parsing Billing Info", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.debug("getUsageRecords() : "+jsonString);
		return new ResponseEntity(jsonString,HttpStatus.OK);
	}
	
	@PostConstruct
	public void initializeRefQosMap() {
		List<RefQoS> refQoSList = refQoSRepository.findAll();
		if (refQoSList != null && !refQoSList.isEmpty()) {
			for (RefQoS qos : refQoSList) {
				qosMap.put(qos.getName().toUpperCase(), qos);
			}
		}
	}

	@RequestMapping(value = "/care/offerinstance/{mdn}", method = RequestMethod.GET)
	public ResponseEntity getOfferInstanceDetails(@PathVariable("mdn")String mdn){
		if(mdn.length() != 10 || !mdn.matches("[0-9]+")){
			return new ResponseEntity<>(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Invalid Mdn", PlabConstants.STATUS_ERROR), HttpStatus.BAD_REQUEST);
		}
		List<OfferInstance> offerInstance = offerInstanceRepository.findByMdn(mdn);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        ObjectMapper mapper = new ObjectMapper();
        //mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.setDateFormat(df);
        ObjectWriter writer = mapper.writerWithView(Views.PlabAdminView.class);
		String jsonString = null;
		try {
			jsonString = writer.writeValueAsString(offerInstance);
			return new ResponseEntity(jsonString, HttpStatus.OK);
		} catch(JsonProcessingException e){
			logger.error("getOfferInstanceDetails(): Error parsing Offer Instance");
			return new ResponseEntity(utility.createResponseInfo(HttpStatus.INTERNAL_SERVER_ERROR, "Error Parsing Offer Instance", PlabConstants.STATUS_ERROR), HttpStatus.BAD_REQUEST);
		}
	}
	
/*	@RequestMapping(value = "/addSpo/{mdn}", method = RequestMethod.POST)
	public ResponseEntity addSpo(@PathVariable("mdn")String mdn,@PathVariable("offerId") Long offerId){
		if(mdn==null || mdn.isEmpty()){
			return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Invalid mdn", PlabConstants.STATUS_ERROR), HttpStatus.BAD_REQUEST);
		}
		
		if(offerId==null){
			return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Invalid offerId", PlabConstants.STATUS_ERROR), HttpStatus.BAD_REQUEST);
		}
		
		VerizonEntity vzEntity = vzwEntityRepository.findByMdn(mdn);
		ResponseEntity responseEntity = productManager.issueMlmo(vzEntity.getCustIdNo(), vzEntity.getAcctNo(),plabCustList,mdnSpoList,null,DvsConstants.ACTION_INDICATOR_ACCEPT); 	
	}*/

	/**
	 * 
	 * @author w648100 - Ramakrishna Pitla API to get servey questions.
	 * 
	 */
	
	@RequestMapping(value = "/offer/{offerId}/{mdn}/survey/questions", method = RequestMethod.POST)
	public ResponseEntity getSurveyQuestions(
			@PathVariable(value = "mdn") Long mdn,
			@PathVariable(value = "offerId") Long offerId) {
		
		
		if (mdn != null && offerId != null) {
			
			VerizonEntity entity = vzwEntityRepository.findByMdn(mdn.toString());

			if(entity == null){
				
				return new ResponseEntity("Verizon entity details not found for mdn : " + mdn,HttpStatus.NOT_FOUND);
			}
			
			List<OfferInstance> offerInstances = offerInstanceRepository.findByOfferAndVerizonEntity(offerId,entity.getVerizonEntityId());
			
			
			if(offerInstances == null ||offerInstances.isEmpty() ){
				
				//return new ResponseEntity("Offer instance details not found for mdn : " + mdn,HttpStatus.NOT_FOUND);
				logger.debug("Offer instances are not available for customers.");
			}

			OfferInstance offerInstance = null;
			List<Survey> surveyList = null;
			Offer offer = null;
			
			
			if (offerInstances != null && !offerInstances.isEmpty()) {
				offerInstance = offerInstances.get(0);
				surveyList = offerInstance.getOffer().getSurveys();	
			}else{
				
				offer = repository.findByOfferId(offerId);
				surveyList = offer.getSurveys();
			}
						           
            
            List<Choice> choicesFromOffer = new ArrayList<Choice>();
            List<SurveyQuestion> surveyQuestionsFrmOffer = new ArrayList<SurveyQuestion>();
            int surveyQuestionsavedsize = 0;

            for (Survey survey : surveyList) {
                for (SurveyQuestion surveyQuestion : survey.getQuestions()) {
                    surveyQuestionsFrmOffer.add(surveyQuestion);
                    for (Choice choiceTemp : surveyQuestion.getChoice()) {
                        choicesFromOffer.add(choiceTemp);
                    }
                }
            }
            
            if (surveyQuestionsFrmOffer != null && !surveyQuestionsFrmOffer.isEmpty()) {
				ObjectMapper mapper = productHelper.getDefaultObjectMapper();
				ObjectWriter writer = mapper
						.writerWithView(Views.MobileFirstView.class);
				
				Map<String, Object> respMap = utility.createResponseInfo(HttpStatus.OK, "", PlabConstants.STATUS_SUCCESS);
				  
				try {
					
					 respMap.put(PlabConstants.RESPONSE_CONTENT, surveyList);
					 String surveyJson  = writer.writeValueAsString(respMap);
					 
					/*String surveyJson = writer
							.writeValueAsString(surveyList);*/
					logger.debug("OfferController getSurveyQuestions() for mdn : "
							+ mdn + ", response json is : " + surveyJson);
					
					
					 
					return new ResponseEntity(surveyJson, HttpStatus.OK);
				} catch (JsonProcessingException jpe) {
					logger.debug("Json Parse Exception", jpe.getMessage(), jpe);
					return new ResponseEntity("Exception while parsing, mdn : "
							+ mdn, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		}
		return new ResponseEntity("Survey questions not found for mdn : " + mdn,HttpStatus.NOT_FOUND);
	}
	/*
	 * This api is invoke by test.jsp to add or remove spo regardless of the enrollment in plab
	 * action=add,remove
	 */
	@RequestMapping(value = "/addOrRemoveSpo/{offerId}/{mdn}/{action}", method=RequestMethod.POST)
	public ResponseEntity addOrRemoveSpo(@PathVariable(value="offerId") Long offerId,@PathVariable(value="mdn") String mdn,@PathVariable(value="action") String action){

		logger.debug("OfferController addOrRemoveSpo offerId : "+offerId);
		logger.debug("OfferController addOrRemoveSpo mdn : "+mdn);
		logger.debug("OfferController addOrRemoveSpo action : "+action);

		if(offerId ==null){
			logger.debug("OfferId cannot be null");
			return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "OfferId cannot be null", PlabConstants.STATUS_ERROR), HttpStatus.BAD_REQUEST);
		}
		if(mdn==null || mdn.isEmpty()){
			logger.debug("Please send a valid mdn");
			return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Please send a valid mdn",PlabConstants.STATUS_ERROR), HttpStatus.BAD_REQUEST);
		}
		if(action==null || action.isEmpty()){
			logger.debug("Please send a valid action : add/remove");
			return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Please send a valid action : add/remove",PlabConstants.STATUS_ERROR), HttpStatus.BAD_REQUEST);
		}

		Offer offer = repository.findByOfferId(offerId);
		if(offer==null){
			logger.debug("No offer found for the given offerId");
			return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "No offer found for the given offerId : "+offerId, PlabConstants.STATUS_ERROR), HttpStatus.BAD_REQUEST);
		}

		List<PlabCust> plabCustList = plabCustRepository.findByMdn(mdn);

		if(plabCustList==null || plabCustList.isEmpty()){
			logger.debug("No plabcust found for the given mdn : "+mdn);
			return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "No plabcust found for the given mdn : "+mdn, PlabConstants.STATUS_ERROR), HttpStatus.BAD_REQUEST);	
		}

		List<Service> serviceList = offer.getServices();
		Map<String,List<String>> mdnSpoList = new HashMap<>();
		if(serviceList!=null && !serviceList.isEmpty()){
			List<String> spoList = new ArrayList<>();
			spoList.add(serviceList.get(0).getSpoID());
			spoList.add(provisioningSpo);
			mdnSpoList.put(mdn, spoList);
		}
		ResponseEntity responseEntity = null;
		if(action.equals("add")){
			 responseEntity = productManager.issueMlmoAddOrRemoveSpo(plabCustList.get(0).getCustIdNo().toString(), plabCustList.get(0).getAcctNo().toString(), plabCustList, mdnSpoList,null, DvsConstants.ACTION_INDICATOR_ACCEPT);
		}else if(action.equals("remove")){
			 responseEntity = productManager.issueMlmoAddOrRemoveSpo(plabCustList.get(0).getCustIdNo().toString(), plabCustList.get(0).getAcctNo().toString(), plabCustList, mdnSpoList,null, DvsConstants.ACTION_INDICATOR_DELETE);
		}		
		return responseEntity;
	}
}
