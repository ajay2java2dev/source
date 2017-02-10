package com.vzwcorp.pricinglab.service;

import com.hazelcast.core.IMap;
import com.vzwcorp.pricinglab.aspects.LoggingAspect;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import com.vzwcorp.pricinglab.loader.profile.ubsr.repository.PlabUsageRepository;
import com.vzwcorp.pricinglab.loader.profile.lookup.repository.RefVisionInstanceLkupRepository;
import com.vzwcorp.pricinglab.loader.profile.ubsr.repository.SubImsiMdnRepository;
import com.vzwcorp.pricinglab.profile.vo.PlabUsage;
import com.vzwcorp.pricinglab.profile.lookup.vo.RefVisionInstanceLkup;
import com.vzwcorp.pricinglab.profile.vo.SubImsiMdn;
import com.vzwcorp.pricinglab.vo.*;
import com.vzwcorp.pricinglab.vo.repository.BillingInfoRepository;
import com.vzwcorp.pricinglab.vo.repository.ServiceInstanceRepository;
import com.vzwcorp.pricinglab.vo.repository.ServiceRepository;
import com.vzwcorp.pricinglab.vo.repository.UsageRecordRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@org.springframework.stereotype.Service
@Configuration
@PropertySource("classpath:application.properties")
public class UsageManager {

    static Logger logger = LogManager.getLogger(UsageManager.class);

    @Autowired
    RefVisionInstanceLkupRepository refRepo;

    @Autowired
    ServiceInstanceRepository serviceInstanceRepository;

    @Autowired
    UsageRecordRepository usageRecordRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    BillingInfoRepository billingInfoRepository;

    @Autowired
    SubImsiMdnRepository subImsiMdnRepository;

    private static Map<String, Integer> counter = new HashMap<>();

    @Autowired
    PlabUsageRepository plabUsageRepo;
    
    @Autowired
    ServiceManager serviceManager;

    @Autowired
    DistributedService distributedService;

    @Value("${tdr.outfile.location}")
    private String tdrOutFileLocation;

    @Value("${loader.Jitr_Instance}")
    private String jitrInstance;

    public void processUsage(UsageRecord usageRecord) throws Exception {

        DateFormat dtf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date dt = new Date();

        String tdrHeader = "RecordType,"
                + "ServedIMSI,"
                + "IMSI-MCC-MNC,"
                + "ServedMSISDN,"
                + "StartTime,"
                + "StopTime,"
                + "ServedPDPPDNAddress,"
                + "LocalSequenceNumber,"
                + "RecordSequenceNumber,"
                + "CauseForRecClosing,"
                + "ChargingID,"
                + "GMT-Offset,"
                + "ThreeGPP2-BSID,"
                + "UserLocationInformation,"
                + "ServedIMEISV,"
                + "Served3gpp2ESN,"
                + "Served3gpp2MEID,"
                + "UserEquipmentValue,"
                + "UserEquipmentType,"
                + "ServingNodePLMNIdentifier,"
                + "Charging-Group-ID,"
                + "AccessPoint-Name-NI,"
                + "PGWAddress,"
                + "ServingNodeAddress,"
                + "NodeID,"
                + "ServingNodeType,"
                + "OriginatingULI,"
                + "OriginatingBSID,"
                + "OriginatingChangeCount,"
                + "SGWAddress,"
                + "ChargingGatewayFunctionHost";

        String defaultTDR = "\"LTE SDR\","
                + "\":imsiVal\","
                + "\"311480\","
                + "\"" + usageRecord.getMdn() + "\","
                + "\"" + dtf.format(dt) + "\","
                + "\"" + dtf.format(dt) + "\","
                + "\"2600:100C:B225:4DF4:0:52:605A:3C01,100.85.93.180\","
                + "\"1160284605\","
                + "\"3\","
                + "\"timeLimit\","
                + "\"876239262\","
                + "\"0\","
                + "\"\","
                + "\"TAI-311480-8404-ECGI-311480-02069503\","
                + "\"9900057798245200\","
                + "\"\","
                + "\"\","
                + "\"\","
                + "\"\","
                + "\"311480\","
                + "\"0000000000000000\","
                + "\"vzwinternet\","
                + "\"2001:4888:200:1000:406:200:0:0\","
                + "\"199.223.102.228\","
                + "\"ELSSTX13PNH\","
                + "\"2\","
                + "\"TAI-311480-8404-ECGI-311480-02076b02\","
                + "\"\","
                + "\"0\","
                + "\"\","
                + "\"cgf1.CARTN03.vzims.com\"";

        String usageHeader = "ServiceSeqNum,"
                + "RatingGroup,"
                + "AFChargingIdentifier,"
                + "FromMobileBytes,"
                + "ToMobileBytes,"
                + "TimeOfFirstUsage,"
                + "TimeOfLastUsage,"
                + "QOS-Class-Identifier,"
                + "bearerChargingID,"
                + "changeCondition,"
                + "APNAggregateMaxBitRateUL,"
                + "APNAggregateMaxBitrateDL";

        String tdrRecord = null;
        String tdr = null;
        String writeTdr = null;

        if (usageRecord != null) {

            String mdn = usageRecord.getMdn();
            //logger.debug("Received usage record for mdn : {} ",mdn);

            //add to thread-context
            ThreadContext.put(LoggingAspect.EVENT_PROP_MDN,mdn);

            usageRecord.setDateCreated(new Date());
            BillingInfo bill = usageRecord.getBillingInfo();

            if(bill!=null) {
                if (bill.getFirsttimeofUsage()!=null && bill.getLasttimeofUsage()!=null) {
                    logger.debug("FirstTime of Usage : {}, LastTime of Usage : {}", dtf.format(bill.getFirsttimeofUsage()), dtf.format(bill.getLasttimeofUsage()));
                }
                logger.debug("vispServiceId : {}, vispServiceInstanceId : {}", bill.getVispServiceID(), bill.getVispServiceInstanceID());
            }

            List<ServiceInstance> vispServiceInstances = serviceInstanceRepository.findLatestByVispServiceInstanceId(bill.getVispServiceInstanceID());
            ServiceInstance vispServiceInstance = null;
            if (vispServiceInstances!=null && !vispServiceInstances.isEmpty()) {
                vispServiceInstance = vispServiceInstances.get(0);
            }

            if (vispServiceInstance == null) {
                throw new UnsupportedOperationException("vispServiceInstance not found for id : " + bill.getVispServiceInstanceID());
            }

            long deltaBytesNew  = usageRecord.getBillingInfo().getTotalBytes() - vispServiceInstance.getCounter();

            logger.debug("MDN : {}, Total bytes : {} , Counter : {} , delta : {}",mdn,
                    usageRecord.getBillingInfo().getTotalBytes(),vispServiceInstance.getCounter(),deltaBytesNew);

            if (deltaBytesNew < 0) {
                //Reset visp service instance if the eventType : residualUsage & after the tdr xml is generated.
                String eventType = usageRecord.getEventType();
                if (eventType!=null && PlabConstants.RESIDUAL_USAGE.equalsIgnoreCase(eventType)) {
                    logger.debug("Received event type : {}. Resetting the counter for service instance : {}. ", eventType, vispServiceInstance.getServiceInstanceId());
                    logger.debug("Previous counter values : counter : {} , fastCounter : {}, fasterCounter : {}, fastestCounter : {} "
                            ,vispServiceInstance.getCounter(),vispServiceInstance.getFastCounter(),
                            vispServiceInstance.getFasterCounter(),vispServiceInstance.getFastestCounter());

                    vispServiceInstance.setCounter(0);
                    vispServiceInstance.setFastCounter(0);
                    vispServiceInstance.setFasterCounter(0);
                    vispServiceInstance.setFastestCounter(0);

                    serviceInstanceRepository.saveAndFlush(vispServiceInstance);
                }
                logger.debug("Usage record not processed as delta is less than or equal to zero.");
                return; // don't proceed further if deltabytes is less than zero.
            }

            //delta value not expected in json. Hence set here
            bill.setDeltaBytes(deltaBytesNew);
            vispServiceInstance.setCounter(bill.getTotalBytes());
            
			// For Speed tier, set the counters
			if ("SpeedTier".equals(serviceManager.getServiceType(vispServiceInstance.getService()))) {
				List<ChoiceInstance> choiceInstances = vispServiceInstance.getOfferInstance().getChoiceInstances();
				String selectedChoice = null;
				if (choiceInstances != null && !choiceInstances.isEmpty()) {
					for (ChoiceInstance choiceInstance : choiceInstances) {
						if (choiceInstance.getSelected().booleanValue()) {
							selectedChoice = choiceInstance.getChoice().getTitle();
							break;
						}
					}
				}

				if (PlabConstants.SPEED_FAST.equalsIgnoreCase(selectedChoice)) {
					vispServiceInstance.setFastCounter(bill.getDeltaBytes()+vispServiceInstance.getFastCounter());
				} else if (PlabConstants.SPEED_FASTER.equalsIgnoreCase(selectedChoice)) {
					vispServiceInstance.setFasterCounter(bill.getDeltaBytes()+vispServiceInstance.getFasterCounter());
				} else if (PlabConstants.SPEED_FASTEST.equalsIgnoreCase(selectedChoice)) {
					vispServiceInstance.setFastestCounter(bill.getDeltaBytes()+vispServiceInstance.getFastestCounter());
				}
			}

            bill.setRatingGroup(vispServiceInstance.getService().getRatingGroup().getRatingGroupId());
            bill.setUsageRecord(usageRecord);
            bill.setServiceInstance(vispServiceInstance);
            bill.setDateCreated(new Date());
            usageRecord.setBillingInfo(bill);

            logger.debug("UsageManager Billing Info Updated. Generating TDR record for MDN : " + mdn);

            StringBuilder usageRecStr = new StringBuilder();

            //Total usage bytes distribution (1/4) form upload / download
            Long deltaBytes = usageRecord.getBillingInfo().getDeltaBytes();
            Long totalBytes = usageRecord.getBillingInfo().getTotalBytes();
            Double fromMobileBytes = null;
            Double toMobileBytes = null;
            if (deltaBytes != null && deltaBytes.longValue() != 0) {
                fromMobileBytes = (deltaBytes * 0.25);
                toMobileBytes = (deltaBytes * 0.75);
            } else {
                fromMobileBytes = (totalBytes * 0.25);
                toMobileBytes = (totalBytes * 0.75);
            }

            if (usageRecord.getSeqNum() == null) {
                Integer count = counter.get("count");
                if (count == null) {
                    counter.put("count", 1);
                } else {
                    int temp = counter.get("count");
                    counter.put("count", ++temp);
                }
                usageRecord.setSeqNum(String.valueOf(counter.get("count")));
            }

            /* save the current info */
            //billingInfoRepository.saveAndFlush(bill);
            usageRecordRepository.saveAndFlush(usageRecord); //merge usagerecord to hibernate context
            serviceInstanceRepository.saveAndFlush(vispServiceInstance);

            usageRecStr.append(usageRecord.getSeqNum() + ",");
            usageRecStr.append(String.valueOf(usageRecord.getBillingInfo().getRatingGroup()) + ",");
            usageRecStr.append("\"\",");
            usageRecStr.append(String.valueOf(fromMobileBytes.longValue()) + ",");
            usageRecStr.append(String.valueOf(toMobileBytes.longValue()) + ",");
            usageRecStr.append("1970-01-01T00:00:00" + ",");
            usageRecStr.append(dtf.format(usageRecord.getBillingInfo().getLasttimeofUsage()) + ",");
            usageRecStr.append("\"\",");
            usageRecStr.append("\"\",");
            usageRecStr.append("\"\",");
            usageRecStr.append("\"\",");
            usageRecStr.append("\"\"");
            usageRecStr.append("\n");

            logger.debug("Searching for last tdr record in UBSR.PLAB_USAGE for MDN : " + mdn);

            PlabUsage plabtdr=null;
            if (distributedService.getHazelcastInstance().getMap("PLAB_USAGE_MAP") != null) {
                VerizonEntity entity = vispServiceInstance.getVerizonEntity();

                //add info to the logs.
                ThreadContext.put(LoggingAspect.EVENT_PROP_CUSTOMER_ID, entity.getCustIdNo()!=null?entity.getCustIdNo().toString():null);
                ThreadContext.put(LoggingAspect.EVENT_PROP_ACCOUNT_NUMBER, entity.getAcctNo()!=null?entity.getAcctNo().toString():null);

                plabtdr = (PlabUsage)distributedService.getHazelcastInstance().getMap("PLAB_USAGE_MAP").get(entity.getCustIdNo() + entity.getAcctNo() + mdn);
            }
            if (plabtdr == null) {
                plabtdr = plabUsageRepo.findByMdn(mdn);
            }
            String usageRecordXML = "";
            String usage = usageHeader + "\n" + usageRecStr.toString();

            logger.debug("Generating usage record for MDN : " + mdn);

            usageRecordXML = generateUsageRecordXML(usage);
            if (usageRecordXML.equalsIgnoreCase("error")) {
                logger.debug("Unable to Generate usage record for MDN : " + mdn);
            } else {
                logger.debug("Generated usage record for MDN : " + mdn);
            }

            if (plabtdr != null) {
                String latestTDR = plabtdr.getUsageRec();
                logger.debug("Found last tdr record for MDN : " + mdn);
                tdr = tdrHeader + "\n" + latestTDR;
                tdrRecord = generateTDRXML(tdr, usageRecordXML,usageRecord, vispServiceInstance);
                logger.debug("Generating TDR.");
                if (tdrRecord.equalsIgnoreCase("error")) {
                    logger.debug("Exception in generating tdr.");
                } else {
                    logger.debug("TDR Record generated for MDN : " + mdn);
                }
            } else {

                List<SubImsiMdn> subImsiMdnList = subImsiMdnRepository.findByMdn(usageRecord.getMdn());
                SubImsiMdn subImsiMdn = null;
                String imsiVal = "311480069336045"; //some defaut value
                if (subImsiMdnList != null && !subImsiMdnList.isEmpty()) {
                    subImsiMdn = subImsiMdnList.get(0);
                    imsiVal = subImsiMdn.getImsiVzw();
                }

                defaultTDR = defaultTDR.replaceFirst(":imsiVal",imsiVal);

                logger.debug("No last tdr record found for MDN : " + mdn + " in UBSR.PLAB_USAGE thus generating default tdr");
                tdr = tdrHeader + "\n" + defaultTDR;
                tdrRecord = generateTDRXML(tdr, usageRecordXML,usageRecord, vispServiceInstance);
                logger.debug("Generated default tdr");
            }

            //Reset visp service instance if the eventType : residualUsage & after the tdr xml is generated.
            String eventType = usageRecord.getEventType();
            if (eventType!=null && PlabConstants.RESIDUAL_USAGE.equalsIgnoreCase(eventType)) {
                logger.debug("Received event type : {}. Resetting the counter for service instance : {}. ", eventType, vispServiceInstance.getServiceInstanceId());
                logger.debug("Previous counter values : counter : {} , fastCounter : {}, fasterCounter : {}, fastestCounter : {} "
                        ,vispServiceInstance.getCounter(),vispServiceInstance.getFastCounter(),
                        vispServiceInstance.getFasterCounter(),vispServiceInstance.getFastestCounter());

                vispServiceInstance.setCounter(0);
                vispServiceInstance.setFastCounter(0);
                vispServiceInstance.setFasterCounter(0);
                vispServiceInstance.setFastestCounter(0);

                serviceInstanceRepository.saveAndFlush(vispServiceInstance);
            }

        } else {
            logger.debug("Received empty usage record.");
        }
    }

    private void writeTDRtoFile(String writeTdr) {
        RefVisionInstanceLkup reflookup = refRepo.findByJitrInstance(jitrInstance);
        String ubinstance = reflookup.getUbInstance();
        Date todaysDate = new Date();
        DateFormat df = new SimpleDateFormat("yyyyMMdd_hhmmss");
        String strDate = df.format(todaysDate);
        char recCount = writeTdr.charAt(writeTdr.trim().length() - 1);
        String fileName = tdrOutFileLocation + "JITR_PLB_" + ubinstance + "_" + strDate + "_" + recCount;
        File tdrOutFile = new File(fileName);
        try {
            FileWriter fw = new FileWriter(tdrOutFile, true);
            fw.write(writeTdr);
            fw.close();
            logger.debug("TDR Record generated to : " + tdrOutFileLocation);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * Generate Service Flow XML to append to TDR
     *
     * @param inputString
     * @return
     */
    private String generateUsageRecordXML(String inputString) {
        BufferedReader reader = new BufferedReader(new StringReader(inputString));
        StringBuffer serviceFlow = new StringBuffer();
        String readLine = null;
        StringTokenizer tokenizer = null;
        boolean isHeader = true;
        int count = 0;
        String output = null;
        List<String> headers = new ArrayList<>();
        serviceFlow.append("\t\t<Service-Flow>\n");
        try {
            while ((readLine = reader.readLine()) != null) {
                tokenizer = new StringTokenizer(readLine, ",");
                if (isHeader) {
                    isHeader = false;
                    while (tokenizer.hasMoreTokens()) {
                        headers.add(tokenizer.nextToken());
                    }
                } else {
                    count = 0;
                    while (tokenizer.hasMoreTokens()) {
                        serviceFlow.append("\t\t\t<");
                        serviceFlow.append(headers.get(count));
                        serviceFlow.append(">");
                        serviceFlow.append(tokenizer.nextToken().replaceAll("\"", ""));
                        serviceFlow.append("</");
                        serviceFlow.append(headers.get(count));
                        serviceFlow.append(">");
                        serviceFlow.append("\n");
                        count++;
                    }
                }
            }
            serviceFlow.append("\t\t</Service-Flow>");
            output = serviceFlow.toString();
        } catch (IOException e) {
            output = "error";
            logger.error(e.getMessage(), e);
        }
        return output;
    }

    /**
     * Generate TDR XML for the USAGE Record received.
     * @param inputTDRString
     * @param usageRecXML
     * @param usageRecord
     * @param serviceInstance
     * @return
     */
    private String generateTDRXML(String inputTDRString, String usageRecXML,UsageRecord usageRecord, ServiceInstance serviceInstance) {

        DateFormat dtf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        BufferedReader readerTDR = new BufferedReader(new StringReader(inputTDRString));
        StringBuilder xml = new StringBuilder();
        boolean isHeader = true;
        String readLine = null;
        String[] tokenizerTDR = null;
        StringTokenizer tokenizerTDR1 = null;
        int count = 0;
        int entryCount = 1;
        String output = null;
        List<String> headersTDR = new ArrayList<>();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<TDRDoc xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"tdr2.9.xsd\">\n");
        try {
            while ((readLine = readerTDR.readLine()) != null) {

                if (isHeader) {
                    isHeader = false;
                    tokenizerTDR1 = new StringTokenizer(readLine, ",");
                    while (tokenizerTDR1.hasMoreTokens()) {
                        headersTDR.add(tokenizerTDR1.nextToken());
                    }
                } else {
                    tokenizerTDR = StringUtils.substringsBetween(readLine, "\"", "\"");
                    count = 0;
                    xml.append("\t<TDRRec RecCountNum=\"");
                    xml.append(entryCount);
                    xml.append("\">");
                    xml.append("\n");
                    for (int i = 0; i < tokenizerTDR.length; i++) {
                        xml.append("\t\t<");
                        xml.append(headersTDR.get(count));
                        xml.append(">");

                        //modified to billing firsttimeofUsage and lasttimeofUsage
                        if ("StartTime".equalsIgnoreCase(headersTDR.get(count))) {
                        	if ( serviceInstance.getLastTimeOfUsage() != null)
                        		xml.append(dtf.format(serviceInstance.getLastTimeOfUsage()));
                        	else
                        		xml.append(dtf.format(usageRecord.getBillingInfo().getFirsttimeofUsage()));
                             serviceInstance.setLastTimeOfUsage(usageRecord.getBillingInfo().getLasttimeofUsage());
                            //xml.append(dtf.format(usageRecord.getBillingInfo().getFirsttimeofUsage()));  // Amr
                        } else if ("StopTime".equalsIgnoreCase(headersTDR.get(count))) {
                            xml.append(dtf.format(usageRecord.getBillingInfo().getLasttimeofUsage()));
                        } else if ("GMT-Offset".equalsIgnoreCase(headersTDR.get(count))) {
                            xml.append(0);
                        } else if ("OriginatingChangeCount".equalsIgnoreCase(headersTDR.get(count))) {
                            String originatingChangeCountStr = tokenizerTDR[i];
                            if (originatingChangeCountStr!=null && !originatingChangeCountStr.isEmpty()){
                                xml.append(originatingChangeCountStr);
                            } else {
                                xml.append(0);//default value
                            }
                        } else if ("ChargingGatewayFunctionHost".equalsIgnoreCase(headersTDR.get(count))) {
                            String chargingGatewayFunctionHost = tokenizerTDR[i];
                            if (chargingGatewayFunctionHost!=null && !chargingGatewayFunctionHost.isEmpty()){
                                xml.append(chargingGatewayFunctionHost);
                            } else {
                                xml.append("cgf1.CARTN03.vzims.com");//default value
                            }
                        } else {
                            xml.append(tokenizerTDR[i]);
                        }

                        xml.append("</");
                        xml.append(headersTDR.get(count));
                        xml.append(">");
                        xml.append("\n");
                        count++;
                    }
                    if (!StringUtils.isEmpty(usageRecXML)) {
                        xml.append(usageRecXML);
                    }
                    xml.append("\n\t</TDRRec>");
                    xml.append("\n");
                    entryCount++;
                }
            }
            xml.append("\t<TDRDoc.End Count=\"" + (entryCount - 1) + "\"/>" + "\n" + "</TDRDoc>\n");


            RefVisionInstanceLkup reflookup = refRepo.findByJitrInstance(jitrInstance);
            String ubinstance = reflookup.getUbInstance();
            Date todaysDate = new Date();
            DateFormat df = new SimpleDateFormat("yyyyMMdd_hhmmss.SSSSSSSSS");
            String strDate = df.format(todaysDate);
            String fileName = tdrOutFileLocation + "UB.PLAB01.LTH.TDP.V.PLAB01..TDRP01.." + strDate;

            File tdrOutFile = new File(fileName);
            try {
                FileWriter fw = new FileWriter(tdrOutFile, true);
                fw.write(xml.toString());
                fw.close();
                //logger.debug("TDR Record generated to : " + tdrOutFileLocation);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
            output = "Success";

            //As discussed with Amr on Dec05 - To be printed in logs.
            logger.debug("XML file path : {}",fileName);
            logger.debug("XML Content : {} ",xml.toString());

        } catch (IOException e) {
            output = "error";
            logger.error(e.getMessage(), e);
        } catch (IndexOutOfBoundsException iob) {
            output = "error";
            logger.error(iob.getMessage(), iob);
        }
        return output;
    }
}