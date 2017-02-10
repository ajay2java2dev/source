package com.vzwcorp.pricinglab.service;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import com.vzwcorp.pricinglab.constants.PlabConstants;
import com.vzwcorp.pricinglab.utility.PricingLabUtility;
import com.vzwcorp.pricinglab.vo.Choice;
import com.vzwcorp.pricinglab.vo.ChoiceInstance;
import com.vzwcorp.pricinglab.vo.ChoiceInstanceHistory;
import com.vzwcorp.pricinglab.vo.Offer;
import com.vzwcorp.pricinglab.vo.OfferInstance;
import com.vzwcorp.pricinglab.vo.PlabCust;
import com.vzwcorp.pricinglab.vo.QoS;
import com.vzwcorp.pricinglab.vo.Rule;
import com.vzwcorp.pricinglab.vo.RuleInstance;
import com.vzwcorp.pricinglab.vo.VerizonEntity;
import com.vzwcorp.pricinglab.vo.mcs.ezcheckout.Category;
import com.vzwcorp.pricinglab.vo.mcs.ezcheckout.ItemDetails;
import com.vzwcorp.pricinglab.vo.mcs.ezcheckout.ObjectFactory;
import com.vzwcorp.pricinglab.vo.mcs.ezcheckout.PricePlanPackageType;
import com.vzwcorp.pricinglab.vo.mcs.ezcheckout.ReqEZCheckout;
import com.vzwcorp.pricinglab.vo.mcs.ezcheckout.RespEZCheckout;
import com.vzwcorp.pricinglab.vo.repository.ChoiceInstanceHistoryRepository;
import com.vzwcorp.pricinglab.vo.repository.ChoiceInstanceRepository;
import com.vzwcorp.pricinglab.vo.repository.ServiceInstanceRepository;

@Configuration
public class McsManager {
	
	@Autowired
	ServiceInstanceRepository serviceInstanceRepository;
	
	@Autowired
	DvsManager dvsManager;
	
	@Value("${mcsApi.url}")
	String mcsURL;
	
	@Autowired
	ChoiceInstanceRepository choiceInstanceRepository;
	
	@Autowired
	ChoiceInstanceHistoryRepository choiceInstanceHistoryRepository;
	
	@Autowired
	PricingLabUtility pricingLabUtility;
	
	static Logger logger = LogManager.getLogger(McsManager.class);
	
	SimpleDateFormat sdfTemp = new SimpleDateFormat("MM/dd/yyyy");
		
	public List<Double> calcCharge(Map<Date,String> billMap,Date startDt,Date endDt,double fastChargePerDay,double fasterChargePerDay,double fastestChargePerDay){
		double fastCharge =0.0;
	 	double fasterCharge =0.0;
	 	double fastestCharge =0.0;
	 	
	 	List<Double> returnList = new ArrayList<Double>();
	 	
		 Calendar startCalender = Calendar.getInstance();
		 	startCalender.setTime(startDt);
		 Calendar endCalender = Calendar.getInstance();
		 	endCalender.setTime(endDt);	
	
		for(;startCalender.compareTo(endCalender)<0;startCalender.add(Calendar.DATE,1))
		{
			String tempSpeedTier = billMap.get(startCalender.getTime());
			logger.debug("billSpeedTier:"+startCalender.getTime()+":"+tempSpeedTier);
			
			if(tempSpeedTier==null){										//if speed not available for that day
				String tempPrevious = getSpeedfromPreviousDay(billMap,startCalender.getTime());
				 if(tempPrevious!=null){									//get speed from previous day
					 if(tempPrevious.toUpperCase().equals(PlabConstants.SPEED_FASTEST))
						 fastestCharge = fastestCharge+fastestChargePerDay;
						else if(tempPrevious.toUpperCase().equals(PlabConstants.SPEED_FASTER))
							fasterCharge = fasterCharge+fasterChargePerDay;
						else if(tempPrevious.toUpperCase().equals(PlabConstants.SPEED_FAST))
							fastCharge = fastCharge+fastChargePerDay; 
				 }	
			}																//if speed available for that day								
			else if(tempSpeedTier.toUpperCase().equals(PlabConstants.SPEED_FASTEST))
				fastestCharge = fastestCharge+fastestChargePerDay;
			else if(tempSpeedTier.toUpperCase().equals(PlabConstants.SPEED_FASTER))
				fasterCharge = fasterCharge+fasterChargePerDay;
			else if(tempSpeedTier.toUpperCase().equals(PlabConstants.SPEED_FAST))
				fastCharge = fastCharge+fastChargePerDay;
			
		}	
		returnList.add(fastCharge);
		returnList.add(fasterCharge);
		returnList.add(fastestCharge);
		return returnList;
		
	}
	
	public Calendar getCalendarInstance(Date inputDate){
		Calendar calendarInstance = Calendar.getInstance();
		calendarInstance.setTime(inputDate);
		return calendarInstance;
		
	}
	
	public List getChargePerDaySpeed(Set<QoS> qosList,String month,String year){
		List<Double> chargePerDay = new ArrayList<>();
		Calendar c = Calendar.getInstance();
		int monthMaxDays = getMonthMaxDays(month, year);
		if(monthMaxDays!=0){
			for(QoS qos: qosList){
				logger.debug("charge from qos : "+qos.getChargePerDay());
				if(qos.getName().equalsIgnoreCase(PlabConstants.SPEED_FAST))
					chargePerDay.add(qos.getChargePerDay().doubleValue()/monthMaxDays);
				else if(qos.getName().equalsIgnoreCase(PlabConstants.SPEED_FASTER))
					chargePerDay.add(qos.getChargePerDay().doubleValue()/monthMaxDays);
				else if(qos.getName().equalsIgnoreCase(PlabConstants.SPEED_FASTEST))
					chargePerDay.add(qos.getChargePerDay().doubleValue()/monthMaxDays);
			}
		}
		Collections.sort(chargePerDay);
		return chargePerDay;
		
	}
	
	public List<Double> calcChargeNoHistory(List<Double> chargePerDay,List<ChoiceInstance> choiceInstanceList,long billableDays){
		String billableSpeed=null;
		if(choiceInstanceList!=null && !choiceInstanceList.isEmpty()){
			for(ChoiceInstance choiceInstance:choiceInstanceList){
				if(choiceInstance.getSelected().booleanValue())
					billableSpeed=choiceInstance.getChoice().getTitle();
			}
		}
		//String billableSpeed=choiceInstanceList.get(0).getChoice().getTitle();	
		if(billableSpeed!=null){
		List<Double> returnList = new ArrayList<Double>();
		if(billableSpeed.equalsIgnoreCase(PlabConstants.SPEED_FAST))
			returnList.add(chargePerDay.get(0)*(double)billableDays);
		else
			returnList.add(0.0);			
		if(billableSpeed.equalsIgnoreCase(PlabConstants.SPEED_FASTER))
			returnList.add(chargePerDay.get(1)*(double)billableDays);
		else
			returnList.add(0.0);	
		if(billableSpeed.equalsIgnoreCase(PlabConstants.SPEED_FASTEST))
			returnList.add(chargePerDay.get(2)*(double)billableDays);
		else
			returnList.add(0.0);	
		
		for(double x : returnList)
			logger.debug("calcChargeNoHistory : "+x);
		return returnList;
		}
		else 
			return null;
	}
	
	private String getSpeedfromPreviousDay(Map<Date,String> billMap,Date speedForDate){
		SimpleDateFormat sdfTemp = new SimpleDateFormat("MM/dd/yyyy");
		Calendar startCalTemp = Calendar.getInstance();
		startCalTemp.setTime(speedForDate);
		 String tempPrevious=null;
		 while(tempPrevious==null){
			 try {
			tempPrevious = billMap.get(sdfTemp.parse(sdfTemp.format(startCalTemp.getTime())));
			startCalTemp.add(Calendar.DATE, -1);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		 }
		return tempPrevious;	
	}
	
	public Map<String,String> reqEzCheckOut(double charge,PlabCust plabCust,boolean addOn,String purchaseName,List<Double> speedCharge){
		Random rand = new Random();
		String transactionId = "JITRUniqueTransID"+rand.nextInt()+plabCust.getMdn();
		Map<String,String> response= new HashMap<>();
		Map<String,String> sParam = new HashMap<>();
		String responseTemp = null;
		
		try {
		Marshaller marshaller = dvsManager.getMarshaller(PlabConstants.MCS_PACKAGE_INFO);	
		Unmarshaller unMarshaller = dvsManager.getunMarshaller(PlabConstants.MCS_PACKAGE_INFO);	
		
	    StringWriter sw = new StringWriter();
		
	    ReqEZCheckout reqEZCheckout = getReqEzCheckOut(transactionId,plabCust.getCustIdNo().toString(),plabCust.getAcctNo().toString(),plabCust.getMdn());
		ItemDetails itemDetails = new ItemDetails();
		
		
		if(addOn){
		itemDetails.setVendorID("999910");											
		itemDetails.setContentID(PlabConstants.MCS_NIGHT_SURFER_CONTENT_ID);						
		itemDetails.setContentType(PlabConstants.MCS_CONTENT_TYPE); 				//for night surfer and social
		
		PricePlanPackageType pricePlanPackageType = new PricePlanPackageType();
		pricePlanPackageType.setPurchasePrice(charge);
		pricePlanPackageType.setPPPType(PlabConstants.MCS_PRICEPLAN_PACKAGE_TYPE);
		
		itemDetails.setPricePlanPackage(pricePlanPackageType);
		itemDetails.setPurchaseName(purchaseNameFormat(purchaseName)); 								
		reqEZCheckout.setItemDetails(itemDetails);
		marshaller.marshal(reqEZCheckout, sw);	
		//System.out.println("xml request to be sent to mcs : "+sw.toString());			
		sParam.put("xmlreqdoc", sw.toString());			
		//responseTemp = issueMcsRequest(sParam);
		responseTemp = pricingLabUtility.postHttpRequestWithURLParams(mcsURL.trim(), sParam);
		//System.out.println("mcs response:"+responseTemp);
		response = processReqEzCheckOutResponse(plabCust.getMdn(),responseTemp,transactionId, charge, marshaller, unMarshaller,itemDetails.getContentID(), sw,response);
		}
		else{
			if(speedCharge!=null && !speedCharge.isEmpty()){
			for(int i=0;i<speedCharge.size();i++){
			    sw = new StringWriter();
				Category category = new Category();
				if(i==0)
				category.setBundleID(PlabConstants.MCS_FAST_BUNDLE_ID);
				else if(i==1)
					category.setBundleID(PlabConstants.MCS_FASTER_BUNDLE_ID);
				else if(i==2)
					category.setBundleID(PlabConstants.MCS_FASTEST_BUNDLE_ID);
				
				PricePlanPackageType pricePlanPackageType = new PricePlanPackageType();
				pricePlanPackageType.setPurchasePrice(speedCharge.get(i));
				pricePlanPackageType.setPPPType(PlabConstants.MCS_PRICEPLAN_PACKAGE_TYPE);
				category.setVendorID("999910");
				category.setPricePlanPackage(pricePlanPackageType);
				category.setPurchaseName(purchaseNameFormat(purchaseName));
				reqEZCheckout.setCategory(category);
				marshaller.marshal(reqEZCheckout, sw);		
				//System.out.println("xml request to be sent to mcs : "+sw.toString());			
				sParam.put("xmlreqdoc", sw.toString());			
				//responseTemp = issueMcsRequest(sParam);
				responseTemp = pricingLabUtility.postHttpRequestWithURLParams(mcsURL.trim(), sParam);
				//System.out.println("mcs response:"+responseTemp);
				response = processReqEzCheckOutResponse(plabCust.getMdn(),responseTemp,transactionId, speedCharge.get(i), marshaller, unMarshaller,category.getBundleID(), sw,response);
			}			
			}
		}  		
		} catch (JAXBException e) {
			logger.error("Exception in MCS Manager (reqEzCheckOut()) : ",e.getMessage(),e); 
			response.put("Error : "+transactionId,e.getMessage());
		}
		return response;
		
	}
	
	public List<Object> getBillableDays(Date subCustBlCycleStartDate,Date subCustBlCycleEndDate,Date offerInstanceCreateDt,Timestamp offerInstanceEndDt,boolean earlyTerminate){
		Long billableDays;	
		Date billCalcStartDt;
		Date billCalcEndDt;		
		Date billCycleStartDate = subCustBlCycleStartDate;
		List<Object> returnBillableDaysList = new ArrayList<>();
		
		if(earlyTerminate){
			Date currentDate = new Date();
			Calendar calEndDt = getCalendarInstance(currentDate);
			if(offerInstanceCreateDt.before(subCustBlCycleEndDate) && (offerInstanceCreateDt.compareTo(billCycleStartDate)>=0)){ 				//enrollment within bill cycles
				billableDays=(currentDate.getTime()-offerInstanceCreateDt.getTime())/(24*60*60*1000)+1;
				billCalcStartDt=offerInstanceCreateDt;
				calEndDt.add(Calendar.DATE, 1);
				billCalcEndDt=calEndDt.getTime();
				
			}
			else{
				billableDays=(currentDate.getTime()-subCustBlCycleStartDate.getTime())/(24*60*60*1000)+1;
				billCalcStartDt = subCustBlCycleStartDate;
				calEndDt.add(Calendar.DATE, 1);
				billCalcEndDt=calEndDt.getTime();
			}
			
			/*logger.debug("billCalc Start Dt in early terminate : "+billCalcStartDt);
			logger.debug("billCalc End Dt in early terminate : "+billCalcEndDt);
			logger.debug("billable Days in early terminate : "+billableDays);*/
		}

	/*	else if(indicator.equals(PlabConstants.REMOVED_PENDING_EOC) || indicator.equals(PlabConstants.REMOVING)){									//exit
			
			if(offerInstanceCreateDt.before(subCustBlCycleEndDate) && (offerInstanceCreateDt.compareTo(billCycleStartDate)>=0)){ 				//enrollment within bill cycles
				billableDays=(offerInstanceEndDt.getTime()-offerInstanceCreateDt.getTime())/(24*60*60*1000)+1;
				billCalcStartDt=offerInstanceCreateDt;
			}
			else{
				billableDays=(offerInstanceEndDt.getTime()-billCycleStartDate.getTime())/(24*60*60*1000)+1;
				billCalcStartDt=billCycleStartDate;
			}

			Calendar calEndDt = getCalendarInstance(offerInstanceEndDt);
			calEndDt.add(Calendar.DATE, 1);

			billCalcEndDt=calEndDt.getTime();
			
			System.out.println("offerInstanceEndDt"+offerInstanceEndDt);
			System.out.println("billCalcStartDt"+billCalcStartDt);
			System.out.println("billableDays on exit; offerInstanceEndDt-start date:"+billableDays);
		}*/
		else{ 																															

			if(offerInstanceCreateDt.before(subCustBlCycleEndDate) && (offerInstanceCreateDt.compareTo(billCycleStartDate)>=0)){ 			//enrollment dt within bill cycles
				billableDays = (subCustBlCycleEndDate.getTime()-offerInstanceCreateDt.getTime())/(24*60*60*1000)+1;

				billCalcStartDt=offerInstanceCreateDt;
				billCalcEndDt=subCustBlCycleEndDate;
				
				/*System.out.println("offerInstanceCreatedt"+offerInstanceCreateDt);
				System.out.println("subCustBlCycleEndDate"+subCustBlCycleEndDate);
				System.out.println("billableDays in enrollment : billcycleend date-enrollment:"+billableDays);*/
			}
			else{																															//on-going
				billableDays =  (subCustBlCycleEndDate.getTime()-billCycleStartDate.getTime())/((24*60*60*1000));
				billCalcStartDt=billCycleStartDate;
				billCalcEndDt=subCustBlCycleEndDate;
				
		/*		System.out.println("billCycleStartDate"+billCycleStartDate);
				System.out.println("subCustBlCycleEndDate"+subCustBlCycleEndDate);
				System.out.println("billableDays in on-going;enddate-start date:"+billableDays);*/
			}
		}

		returnBillableDaysList.add(billableDays);
		returnBillableDaysList.add(billCalcStartDt);
		returnBillableDaysList.add(billCalcEndDt);



		return returnBillableDaysList;
	}
	
	public long getBillableDaysForNS(Date billCalcStartDt,Date billCalcEndDt,OfferInstance offerInstance,List<Choice> choiceList){
		List<ChoiceInstance> choiceInstanceList = new ArrayList<>();
		List<ChoiceInstanceHistory> choiceInstanceHistList = new ArrayList<>();
		List<ChoiceInstanceHistory> choiceInstHistForStartDtList = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Map<Date,String> billMap = new HashMap<>();
		long billableDays = 0;
		try{
			Date junkDate  = sdf.parse((Calendar.getInstance().get(Calendar.MONTH)+2)+"/"+(99)+"/1999"+" 00:00:01");
			billMap.put(sdfTemp.parse(sdfTemp.format(junkDate)),"junk");										//do not remove this, it is needed
			
			choiceInstanceList = getchoiceInstance(choiceList,offerInstance);
			if(choiceInstanceList!=null && !choiceInstanceList.isEmpty()){
				choiceInstanceHistList = getchoiceInstanceHistoryListBetweenGivenDates(choiceInstanceList,billCalcStartDt,billCalcEndDt);
				choiceInstHistForStartDtList = getchoiceInstanceHistoryListBeforeGivenDt(choiceInstanceList,billCalcStartDt);

				if(choiceInstanceHistList!=null && !choiceInstanceHistList.isEmpty()){
					billMap = getBillMapForChoiceInstanceHistory(choiceInstanceHistList,billMap);
					logger.debug("post cih : ");
					printBillMap(billMap);
					billMap = getBillMapForChoiceInstance(choiceInstanceList,billMap);
					logger.debug("post ci : ");
					printBillMap(billMap);
					billMap = getBillMapForStartDate(choiceInstHistForStartDtList,billMap,billCalcStartDt);
					logger.debug("post cihstart : ");
					printBillMap(billMap);
					billMap.remove(junkDate);
					billableDays = calcBillableDaysFromBillMapForNS(billMap,billCalcStartDt,billCalcEndDt);
				}else{
					billMap = getBillMapForChoiceInstance(choiceInstanceList,billMap);
					billMap.remove(junkDate);
					logger.debug("No history at all billMap is ");
					printBillMap(billMap);
					billableDays = calcBillableDaysFromBillMapForNS(billMap,billCalcStartDt,billCalcEndDt);
				}
			}
			logger.debug("billableDays for Ns for offerInstance : "+offerInstance.getOfferInstanceId()+" is "+billableDays);
			return billableDays;
		}catch(Exception e){
			e.printStackTrace();
			logger.debug("billableDays couldnot be calculated for offerInstance : "+offerInstance.getOfferInstanceId());
			return -1;
		}
	}
	
	private long calcBillableDaysFromBillMapForNS(Map<Date,String> billMap,Date startDt,Date endDt) throws ParseException{
		long billableDays=0;
		Calendar startCalender = Calendar.getInstance();
		startCalender.setTime(startDt);
		Calendar endCalender = Calendar.getInstance();
		endCalender.setTime(endDt);
		int i =32;

		for(;startCalender.compareTo(endCalender)<0;startCalender.add(Calendar.DATE,1)){
			Date dateTemp = sdfTemp.parse(sdfTemp.format(startCalender.getTime()));
			String isToggle = billMap.get(dateTemp);
			Calendar tempCal = Calendar.getInstance();
			tempCal.setTime(startCalender.getTime());
			while(isToggle==null){
				tempCal.add(Calendar.DATE, -1);
				isToggle = billMap.get(sdfTemp.parse(sdfTemp.format(tempCal.getTime())));
				i--;
				if(i==0){			//this is just to ensure while loop breaks in the worst case
					break;
				}
			}
			if("toggleOn".equalsIgnoreCase(isToggle)){
				billableDays=billableDays+1;
			}else if("toggleOffButBilled".equalsIgnoreCase(isToggle)){
				if(startCalender.equals(tempCal))
					billableDays=billableDays+1;
			}
		}
		
		return billableDays;
	}
	
	private void printBillMap(Map<Date,String> billMap){
		Iterator it1 = billMap.entrySet().iterator();
		while (it1.hasNext()) {
			Map.Entry x= ((Map.Entry)it1.next());
			logger.debug(x.getKey()+";"+x.getValue());
		}
	}
	
	private Map<Date,String> getBillMapForStartDate(List<ChoiceInstanceHistory> choiceInstHistForStartDtList,Map<Date,String> billMap,Date billCalcStartDt) throws ParseException{
		Date startDt =  sdfTemp.parse(sdfTemp.format(billCalcStartDt));
		if(billMap.get(startDt)==null){
			if(choiceInstHistForStartDtList!=null && !choiceInstHistForStartDtList.isEmpty()){
				Collections.sort(choiceInstHistForStartDtList,Collections.reverseOrder());
				for(ChoiceInstanceHistory choiceInstHist : choiceInstHistForStartDtList){		
					billMap = setToggleOnOffForGivenBillMap(choiceInstHist.getSelected(),choiceInstHist.getDateCreated(),billMap);
					if(billMap.get(startDt)!=null){
						break;
					}
				}
			}
		}
		return billMap;	
	}
	
	private Map<Date,String> getBillMapForChoiceInstance(List<ChoiceInstance> choiceInstanceList,Map<Date,String> billMap) throws ParseException{
		for(ChoiceInstance choiceInstance : choiceInstanceList){		
			billMap = setToggleOnOffForGivenBillMap(choiceInstance.getSelected(),choiceInstance.getDateCreated(),billMap);
		}
		return billMap;
	}
	
	private Map<Date,String> getBillMapForChoiceInstanceHistory(List<ChoiceInstanceHistory> choiceInstanceHistList,Map<Date,String> billMap) throws ParseException{
		Collections.sort(choiceInstanceHistList);
		for(ChoiceInstanceHistory choiceInstHist : choiceInstanceHistList){		
			billMap = setToggleOnOffForGivenBillMap(choiceInstHist.getSelected(),choiceInstHist.getDateCreated(),billMap);
		}
		return billMap;
	}
	
	private Map<Date,String> setToggleOnOffForGivenBillMap(Boolean selected,Date date,Map<Date,String> billMap) throws ParseException {
		Date dateTemp =  sdfTemp.parse(sdfTemp.format(date));
		if(selected){
			billMap.put(dateTemp, "toggleOn");
		}else{
			String isToggle = billMap.get(dateTemp);
			if(isToggle!=null && !isToggle.isEmpty() && isToggle.equalsIgnoreCase("toggleOn")){
				billMap.put(dateTemp, "toggleOffButBilled");
			}else if(isToggle!=null && !isToggle.isEmpty() && isToggle.equalsIgnoreCase("toggleOffButBilled")){
				billMap.put(dateTemp, "toggleOffButBilled");
			}else{
				billMap.put(dateTemp, "toggleOff");
			}
		}
		return billMap;
	}
	private List<ChoiceInstanceHistory> getchoiceInstanceHistoryListBetweenGivenDates(List<ChoiceInstance> choiceInstanceList,Date billCalcStartDt,Date billCalcEndDt){
		List<ChoiceInstanceHistory> choiceInstanceHistList = new ArrayList<>();
		if(choiceInstanceList!=null && !choiceInstanceList.isEmpty()){
			for(ChoiceInstance choiceInstance : choiceInstanceList){
				choiceInstanceHistList.addAll(choiceInstanceHistoryRepository.findByChoiceInstanceAndDateCreatedBetween(choiceInstance, billCalcStartDt, billCalcEndDt));
			}
		}
		return choiceInstanceHistList;
	}
	
	private List<ChoiceInstance> getchoiceInstance(List<Choice> choiceList,OfferInstance offerInstance){
		List<ChoiceInstance> choiceInstanceList = new ArrayList<>();
		if(choiceList!=null && !choiceList.isEmpty()){
			for(Choice choice : choiceList){
			 choiceInstanceList.addAll(choiceInstanceRepository.findByChoiceAndOfferInstance(choice.getId(), offerInstance.getOfferInstanceId()));
			}
		}
		return choiceInstanceList;
	}
	
	private List<ChoiceInstanceHistory> getchoiceInstanceHistoryListBeforeGivenDt(List<ChoiceInstance> choiceInstanceList,Date billCalcStartDt){
		List<ChoiceInstanceHistory> choiceInstHistForStartDtList = new ArrayList<>();
		if(choiceInstanceList!=null && !choiceInstanceList.isEmpty()){
			for(ChoiceInstance choiceInstance : choiceInstanceList){
				choiceInstHistForStartDtList.addAll(choiceInstanceHistoryRepository.findByChoiceInstanceAndDateCreatedBefore(choiceInstance, billCalcStartDt));
			}
		}
		return choiceInstHistForStartDtList;
	}
	
	public ReqEZCheckout getReqEzCheckOut(String transactionId,String custIDNo,String accountNumber,String mdn){
		ReqEZCheckout reqEZCheckout = new ReqEZCheckout();
					
		reqEZCheckout.setChannelID("138");											
		reqEZCheckout.setCallerVendorID("999910");									
		reqEZCheckout.setTransID(transactionId);									
		
		reqEZCheckout.setCustomerId(custIDNo);
		reqEZCheckout.setAccountNumber(accountNumber);
		reqEZCheckout.setMdn(mdn);
		
		return reqEZCheckout;
	}
	
	public Map<String,String> processReqEzCheckOutResponse(String mdn,String responseTemp,String transactionId,double charge,Marshaller marshaller,Unmarshaller unMarshaller,String bundleOrContentId,StringWriter sw,Map<String,String> response){
		RespEZCheckout respEZCheckout = new RespEZCheckout();
		/*System.out.println("bundleOrContentId"+bundleOrContentId);
		System.out.println("transactionId"+transactionId);*/
		try {
			if(responseTemp!=null && !responseTemp.isEmpty() && !responseTemp.startsWith("Error")){
				if(responseTemp.contains("RespEZCheckout")){
					respEZCheckout = (com.vzwcorp.pricinglab.vo.mcs.ezcheckout.RespEZCheckout)unMarshaller.unmarshal(new StringReader(responseTemp));
					if(respEZCheckout.getRespMsg().getCode()==200 && respEZCheckout.getCheckoutPrice().equals(charge)){
						logger.debug("Successful for mdn : "+mdn+" for priceplan package : "+bundleOrContentId+" : "+responseTemp);
						response.put("Success : "+bundleOrContentId+" : "+transactionId,responseTemp);
					}
					else{
						logger.debug("Error for mdn : "+mdn+" for priceplan package : "+bundleOrContentId+" : "+sw.toString());
						response.put("Error : "+bundleOrContentId+" : "+transactionId,responseTemp);
					}
				}else{
					logger.debug(responseTemp);
				}
			}
			else{
				logger.debug( "Not Successful for mdn : "+mdn+" for priceplan package : "+bundleOrContentId+" : response from MCS : "+responseTemp);
				response.put("Error : "+bundleOrContentId+" : "+transactionId,bundleOrContentId+" : "+transactionId+" : "+responseTemp+"\n");
			}
		} catch (JAXBException e) {
			logger.error("Exception in MCS Manager (processReqEzCheckOutResponse()) : ",e.getMessage(),e); 
			response.put("Error : "+transactionId,transactionId+" : "+e.getMessage());
		}
		return response;
	}
	
	public double getChargePerDayNs(double charge,String month,String year){
		int monthMaxDays = getMonthMaxDays(month,year);
		double chargePerDay =0.0;
		if(monthMaxDays!=0){
			chargePerDay = charge/monthMaxDays;
		}
		logger.debug("ChargePerDay NS : "+chargePerDay);
		return chargePerDay; 
		
	}
	
	private int getMonthMaxDays(String month,String year){
		Calendar c = Calendar.getInstance();
		int monthMaxDays = 0;
		if(month!=null && !month.isEmpty() && year!=null && !year.isEmpty()){
			int monthTemp=Integer.parseInt(month);
			int yearTemp=Integer.parseInt(year);
			c.set(Calendar.MONTH, monthTemp);
			c.set(Calendar.YEAR, yearTemp);
			monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		}
		else{
			monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		}
		logger.debug("max days in a month to calc charge Per Day : "+ monthMaxDays);
		return monthMaxDays;
	}
	
	public double calcMcsNsCharge(long billableDays,double chargePerDay){
		double charge = billableDays*chargePerDay;		
		return charge;
	}
	
	private String purchaseNameFormat(String purchaseName){

		StringBuffer returnString = new StringBuffer();
		String[] purchaseNameTemp = purchaseName.toLowerCase().split(" ");
		if(purchaseNameTemp!=null){
			for(String st : purchaseNameTemp){
				char c=Character.toUpperCase(st.charAt(0));
				returnString.append(c);
				returnString.append(st.substring(1));
				returnString.append(" ");
			}
		}
		return returnString.toString().trim();
	}
}
