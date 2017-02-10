package com.vzwcorp.pricinglab.service;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import com.vzwcorp.pricinglab.constants.DvsConstants;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import com.vzwcorp.pricinglab.loader.profile.ubsr.repository.SubCustBlCycleRepository;
import com.vzwcorp.pricinglab.utility.PricingLabUtility;
import com.vzwcorp.pricinglab.vo.ChoiceInstance;
import com.vzwcorp.pricinglab.vo.Offer;
import com.vzwcorp.pricinglab.vo.OfferInstance;
import com.vzwcorp.pricinglab.vo.PlabCust;
import com.vzwcorp.pricinglab.vo.QoS;
import com.vzwcorp.pricinglab.vo.ServiceInstance;
import com.vzwcorp.pricinglab.vo.dvs.nbs.BillProrateCalcNONE;
import com.vzwcorp.pricinglab.vo.dvs.nbs.MobileFirstTotalDetails;
import com.vzwcorp.pricinglab.vo.productvalidation.CustAccountInfo;
import com.vzwcorp.pricinglab.vo.productvalidation.MtnAccountInfo;
import com.vzwcorp.pricinglab.vo.productvalidation.MtnList;
import com.vzwcorp.pricinglab.vo.productvalidation.ProductOfferInfo;
import com.vzwcorp.pricinglab.vo.productvalidation.Service;
import com.vzwcorp.pricinglab.vo.productvalidation.ServiceBody;
import com.vzwcorp.pricinglab.vo.productvalidation.ServiceHeader;
import com.vzwcorp.pricinglab.vo.productvalidation.ServiceProductOffers;
import com.vzwcorp.pricinglab.vo.productvalidation.ServiceRequest;
import com.vzwcorp.pricinglab.vo.productvalidation.ServiceResponse;
import com.vzwcorp.pricinglab.vo.repository.ChoiceInstanceRepository;
import com.vzwcorp.pricinglab.vo.repository.ChoiceRepository;
import com.vzwcorp.pricinglab.vo.repository.OfferInstanceRepository;
import com.vzwcorp.pricinglab.vo.repository.PlabCustRepository;
import com.vzwcorp.pricinglab.vo.repository.ServiceInstanceRepository;
import com.vzwcorp.pricinglab.vo.repository.ServiceRepository;

@Configuration
public class DvsManager {

	@Value("${dvsApi.url}")
	String dvsAPIURL;
	@Value("${dvs.userid}")
	String dvsUserId;
	@Value("${dvs.password}")
	String dvsPassword;
	
	static Logger logger = LogManager.getLogger(DvsManager.class);
	
	@Autowired
	PlabCustRepository plabCustRepository;
	
	@Autowired
	ServiceRepository serviceRepository;
	
	@Autowired
	ChoiceInstanceRepository choiceInstanceRepository;
	
	@Autowired
	ProductManager productManager;
	
	@Autowired
	McsManager mcsManager;
	
	@Autowired
	ChoiceRepository choiceRepository;
	
	@Autowired
	SubCustBlCycleRepository subCustBlCycleRepository;
	
	@Value("${http.connection.timeout}")
	Integer connectionTimeOut;
	
	@Value("${http.connection.request.timeout}")
	Integer connectionRequestTimeOut;
	
	@Value("${http.connection.socket.timeout}")
	Integer connectionSocketTimeOut;
	
	@Autowired
	PricingLabUtility pricingLabUtility;
	
	@Autowired
	ServiceInstanceRepository serviceInstanceRepository;
	
	@Autowired
	OfferInstanceRepository offerInstanceRepository;
	
	
	public Map<String,String> productValidation(String customer_id_no,String account_no,List<PlabCust> plabMemberList,Map<String,List<String>> mdnSpoList,String spoTobeRemoved,String actionIndicator) {
	
	JAXBContext jaxbContext;
	DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	Map<String, String> productValidationXml = new HashMap<>();
	Map<String,String> custProfileInfo = retrieveCustProfile(plabMemberList.get(0).getMdn());	//memberlist not expected to be null
	Map<String,String> returnDataProdValidation = new HashMap<>();
	String sharePlanId=null;
	String response=null;
	String errorInCustProfile=null;
	StringWriter sWriter = new StringWriter();
	String custTypeCode=null;
	
	logger.debug("Product validation call");
	
	if(custProfileInfo!=null && !custProfileInfo.isEmpty()){
		errorInCustProfile = custProfileInfo.get(DvsConstants.ERROR_IN_RETRIEVE_CUST_PROFILE);
		
		if(errorInCustProfile==null){
		sharePlanId = custProfileInfo.get("sharePlanID");
		returnDataProdValidation.put(DvsConstants.RETRIEVE_CUSTINFO_KEY, custProfileInfo.get(DvsConstants.RETRIEVE_CUSTINFO_KEY));
		custTypeCode = custProfileInfo.get("custTypeCode");
		}
		
	}
	
	if(errorInCustProfile==null){
	try {
		jaxbContext = JAXBContext.newInstance(DvsConstants.PRODUCT_VALIDATION_PACKAGE);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		com.vzwcorp.pricinglab.vo.productvalidation.Service service = new Service();
		ServiceBody serviceBody = new ServiceBody();
		ServiceRequest serviceRequest = new ServiceRequest();
		ServiceHeader serviceHeader = new ServiceHeader();	
		ServiceResponse serviceResponse = new ServiceResponse();
		MtnList mtnList = new MtnList();
			
		serviceHeader.setBillingSys(DvsConstants.BILLING_SYSTEM);
		serviceHeader.setClientId(DvsConstants.CLIENT_ID);
		serviceHeader.setUserId(dvsUserId);
		serviceHeader.setPassword(dvsPassword);
		serviceHeader.setServiceName(DvsConstants.PRODUCT_VALIDATION_SERVICE_NAME);	
				
		serviceRequest.setSubServiceName(DvsConstants.PRODUCT_VALIDATION_SUBSERVICE_NAME);
		serviceRequest.setMtnInputCount(Integer.toString(plabMemberList.size()));
		
		CustAccountInfo custAccountInfo = new CustAccountInfo();
		custAccountInfo.setOrderType(DvsConstants.ORDER_TYPE);											
		custAccountInfo.setTransactionDate(sdf.format(new Timestamp(new Date().getTime())));			
		custAccountInfo.setCustomerId(customer_id_no);
		custAccountInfo.setAccountNumber(account_no);
		custAccountInfo.setCustomerTypeCode(custTypeCode);

		//TODO : To be tested again.
	   for(PlabCust plabCust : plabMemberList){
			List<String> spoList = mdnSpoList.get(plabCust.getMdn());
			if (spoList== null || spoList.isEmpty())
				  continue;
			
			 MtnAccountInfo mtnAccountInfo = new MtnAccountInfo();
			 mtnAccountInfo.setMtnOrderType(DvsConstants.MTN_ORDER_TYPE);                                                               
			 mtnAccountInfo.setMtn(plabCust.getMdn());
			 if(sharePlanId==null){
				   sharePlanId="99999";
				   logger.debug("shareplanID is 99999");
			 }
			 mtnAccountInfo.setSharePlanId(sharePlanId);     
			 mtnAccountInfo.setFeaturesCount("0");           
			 
			 ServiceProductOffers serviceProductOffers = new ServiceProductOffers();
			 spoList = mdnSpoList.get(plabCust.getMdn());                                                    //mdnSpoList cannot be empty
			 if(spoList!=null && !spoList.isEmpty()){
				   int serviceProductOffersCount=0;
				   for(String spo : spoList){
						  if(spoTobeRemoved!=null && !spoTobeRemoved.isEmpty()){
								 if(spo.equals(spoTobeRemoved)){
										ProductOfferInfo productOfferInfo = setSpoForDeletionInProductValidation(spo,plabCust.getMdn(),actionIndicator);
										serviceProductOffers.getServiceProductOffersInfo().add(productOfferInfo);
										serviceProductOffersCount=serviceProductOffersCount+1;
										break;
								 }
						  }else{                            
								 ProductOfferInfo productOfferInfo = setSpoForDeletionInProductValidation(spo,plabCust.getMdn(),actionIndicator);
								 serviceProductOffers.getServiceProductOffersInfo().add(productOfferInfo);  
								 serviceProductOffersCount=serviceProductOffersCount+1;
						  }
				   }                    
					mtnAccountInfo.setServiceProductOffersCount(Integer.toString(serviceProductOffersCount));       
			 }
			 
			 mtnAccountInfo.setServiceProductOffers(serviceProductOffers);
			 mtnList.getMtnInfo().add(mtnAccountInfo);
	  }
		
		serviceRequest.setCustAccountInfo(custAccountInfo);
		serviceRequest.setMtnList(mtnList);
		serviceBody.setServiceRequest(serviceRequest);
		service.setServiceBody(serviceBody);
		service.setServiceHeader(serviceHeader);
		
		marshaller.marshal(service, sWriter);
		productValidationXml.put("xmlreqdoc", sWriter.toString());
		
		//logger.debug("service request in productValidation "+sWriter);		
		logger.debug("Invoke productValidation service in dvs");	
		//response = issueDvsRequest(productValidationXml);
		//response = issueDvsReqUrlEncoded(dvsAPIURL.trim(), productValidationXml);
		response = pricingLabUtility.postHttpRequestWithURLParams(dvsAPIURL.trim(), productValidationXml);
		
		if(response!=null && !response.isEmpty() && !response.startsWith("Error")){
			service = (com.vzwcorp.pricinglab.vo.productvalidation.Service)unmarshaller.unmarshal(new StringReader(response));
			sWriter = new StringWriter();
			marshaller.marshal(service, sWriter);
			logger.debug("service response in productValidation "+sWriter.toString());
			
			if(service!=null && service.getServiceHeader().getErrorMsg()!=null && !service.getServiceHeader().getErrorMsg().isEmpty()){	
				returnDataProdValidation.put(DvsConstants.ERROR_IN_PRODUCTVALIDATION, service.getServiceHeader().getErrorMsg());
			}
			
			else{
				if(service.getServiceBody()!=null){										//get product correlation id
					serviceResponse = service.getServiceBody().getServiceResponse();
					if(serviceResponse.getMtnOutputList()!=null){
						List<MtnAccountInfo> mtnAccountInfoList = serviceResponse.getMtnOutputList().getMtnInfo();
						if(mtnAccountInfoList!=null && !mtnAccountInfoList.isEmpty()){
							for(MtnAccountInfo mtnAccountInfo : mtnAccountInfoList){
								returnDataProdValidation.put(mtnAccountInfo.getMtn(),mtnAccountInfo.getProductCorrelationId());
							}
						}
					}
				}
			}
		}
		else{
			returnDataProdValidation.put(DvsConstants.ERROR_IN_PRODUCTVALIDATION, response);
		}
		
	} catch (Exception e) {
		//e.printStackTrace();
		logger.error("Exception in DVS Manager (productValidation()) : ",e.getMessage(),e); 
		returnDataProdValidation.remove(DvsConstants.RETRIEVE_CUSTINFO_KEY);
		returnDataProdValidation.put(DvsConstants.ERROR_IN_PRODUCTVALIDATION, e.getMessage());
	}
	}
	else{
		returnDataProdValidation.put(DvsConstants.ERROR_IN_RETRIEVE_CUST_PROFILE, errorInCustProfile);
	}
	
	return returnDataProdValidation;
	}
	
	public Map<String,String> retrieveCustProfile(String mdn) {
		logger.debug("Retrieving customer profile information");

		Map<String, String> retrieveCustProfileXml = new HashMap<>();
		Map<String,String> returnDataRetrCustProfile = new HashMap<>();
		StringWriter sWriter = new StringWriter();

		com.vzwcorp.pricinglab.vo.dvs.retrievecustomerprofile.Service service = new com.vzwcorp.pricinglab.vo.dvs.retrievecustomerprofile.Service();
		com.vzwcorp.pricinglab.vo.dvs.retrievecustomerprofile.ServiceHeader serviceHeader = new com.vzwcorp.pricinglab.vo.dvs.retrievecustomerprofile.ServiceHeader();

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(DvsConstants.RETRIEVE_CUST_PROFILE_PACKAGE);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			serviceHeader.setBillingSys(DvsConstants.RETRIEVE_CUST_PROFILE_BILLING_SYSTEM);
			serviceHeader.setClientId(DvsConstants.CLIENT_ID);
			serviceHeader.setUserId(dvsUserId);
			serviceHeader.setPassword(dvsPassword);
			serviceHeader.setServiceName(DvsConstants.RETRIEVE_CUST_PROFILE_SERVICE_NAME);
			service.setServiceHeader(serviceHeader);

			com.vzwcorp.pricinglab.vo.dvs.retrievecustomerprofile.ServiceBody serviceBody = new com.vzwcorp.pricinglab.vo.dvs.retrievecustomerprofile.ServiceBody();
			com.vzwcorp.pricinglab.vo.dvs.retrievecustomerprofile.ServiceRequest serviceRequest = new com.vzwcorp.pricinglab.vo.dvs.retrievecustomerprofile.ServiceRequest();
			List<com.vzwcorp.pricinglab.vo.dvs.retrievecustomerprofile.SharePlanInfo> sharePlanInfo= null;

			serviceRequest.setSubServiceName(DvsConstants.RETRIEVE_CUST_PROFILE_SUBSERVICE_NAME);
			serviceRequest.setMtn(mdn);

			serviceBody.setServiceRequest(serviceRequest);
			service.setServiceBody(serviceBody);

			marshaller.marshal(service, sWriter);

			retrieveCustProfileXml.put("xmlreqdoc", sWriter.toString());

			//logger.debug("service request in retrievecustpofile"+sWriter.toString());		
			logger.debug("Invoke retrieveCustomerProfile service in dvs");

			String response = pricingLabUtility.postHttpRequestWithURLParams(dvsAPIURL.trim(), retrieveCustProfileXml);
			
			if(response!=null && !response.isEmpty() && !response.startsWith("Error")){
				
				returnDataRetrCustProfile.put(DvsConstants.RETRIEVE_CUSTINFO_KEY, response);
				
				service = (com.vzwcorp.pricinglab.vo.dvs.retrievecustomerprofile.Service)unmarshaller.unmarshal(new StringReader(response));
				sWriter = new StringWriter();
				marshaller.marshal(service, sWriter); 	//this is needed for formatted sysout
				logger.debug("service response in retrieveCustomerProfile"+sWriter.toString());
				
				if(service.getServiceHeader().getErrorCode().equals("00")){
					serviceBody = service.getServiceBody();	
					
					List<com.vzwcorp.pricinglab.vo.dvs.retrievecustomerprofile.CustomerInfo> customerInfoList =serviceBody.getServiceResponse().getCustomersList().getCustomerInfo();
					if(customerInfoList!=null && !customerInfoList.isEmpty()){
						for(com.vzwcorp.pricinglab.vo.dvs.retrievecustomerprofile.CustomerInfo custInfo :customerInfoList){
							if(custInfo.getBillAccountInfo()!=null && !custInfo.getBillAccountInfo().isEmpty())	{
								sharePlanInfo = custInfo.getBillAccountInfo().get(0).getSharePlanList().getSharePlanInfo();
								returnDataRetrCustProfile.put("custTypeCode",custInfo.getCustomerTypeCode());
								
							}

							if(sharePlanInfo!=null && !sharePlanInfo.isEmpty())
								returnDataRetrCustProfile.put("sharePlanID", sharePlanInfo.get(0).getSharePlanId());	
						}
					}
				}
				
				else{
					returnDataRetrCustProfile.put(DvsConstants.ERROR_IN_RETRIEVE_CUST_PROFILE, service.getServiceHeader().getErrorMsg());
				}
			}else{
				returnDataRetrCustProfile.put(DvsConstants.ERROR_IN_RETRIEVE_CUST_PROFILE, response);
			}

		} catch (JAXBException e) {
			//e.printStackTrace();
			logger.error("Exception in DVS Manager (retrieveCustProfile()) : ",e.getMessage(),e); 
			returnDataRetrCustProfile.put(DvsConstants.ERROR_IN_RETRIEVE_CUST_PROFILE,e.getMessage());
			return returnDataRetrCustProfile;
		}

		return returnDataRetrCustProfile;	
	}
	
	public Map<String,String> issueMlmoRequest(Map<String,String> prodValidationRetData,String cust_Id_No,String account_No){
		
		String errorinCustProfile = prodValidationRetData.get(DvsConstants.ERROR_IN_RETRIEVE_CUST_PROFILE);
		String errorinProdValidation = prodValidationRetData.get(DvsConstants.ERROR_IN_PRODUCTVALIDATION);
		
		if(errorinCustProfile==null && errorinProdValidation==null){
			logger.debug("in MLMORequest");
			DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Map<String,String> mlmoRequestXml = new HashMap<>();
			Map<String,String> mlmoResponseData = new HashMap<>();
			StringWriter sWriter = new StringWriter();
			com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.Service service = new com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.Service();
			com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.ServiceHeader serviceHeader = new com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.ServiceHeader();
			com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.ServiceBody serviceBody = new com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.ServiceBody();
			com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.ServiceRequest serviceRequest = new com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.ServiceRequest();
			com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.CreateOrderLineItemList createOrderLineItemList = new com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.CreateOrderLineItemList();
			
			com.vzwcorp.pricinglab.vo.dvs.retrievecustomerprofile.Service serviceCustProfile = new com.vzwcorp.pricinglab.vo.dvs.retrievecustomerprofile.Service();
			com.vzwcorp.pricinglab.vo.dvs.retrievecustomerprofile.ServiceResponse serviceResponseCustProfile = new com.vzwcorp.pricinglab.vo.dvs.retrievecustomerprofile.ServiceResponse();
			List<com.vzwcorp.pricinglab.vo.dvs.retrievecustomerprofile.CustomerInfo> customerInfoList = new ArrayList<>();
			List<com.vzwcorp.pricinglab.vo.dvs.retrievecustomerprofile.MtnDetailList> mtnDetailList = new ArrayList<>();
			com.vzwcorp.pricinglab.vo.dvs.retrievecustomerprofile.AccountInfo.SharePlanList sharePlanObj = new com.vzwcorp.pricinglab.vo.dvs.retrievecustomerprofile.AccountInfo.SharePlanList();
			
			
			serviceHeader.setBillingSys(DvsConstants.BILLING_SYSTEM);
			serviceHeader.setClientId(DvsConstants.CLIENT_ID);
			serviceHeader.setUserId(dvsUserId);
			serviceHeader.setPassword(dvsPassword);
			serviceHeader.setServiceName(DvsConstants.MULTILINEMIXEDORDER_SERVICE_NAME);
			
			service.setServiceHeader(serviceHeader);
			
			
			try{
			JAXBContext jaxbContext = JAXBContext.newInstance(DvsConstants.MLMO_REQUEST);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			JAXBContext jaxbContextCustProf = JAXBContext.newInstance(DvsConstants.RETRIEVE_CUST_PROFILE_PACKAGE);
			Unmarshaller unmarshallerCustProf = jaxbContextCustProf.createUnmarshaller();
			Marshaller marshallerCustProf = jaxbContextCustProf.createMarshaller();
			marshallerCustProf.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			serviceCustProfile = (com.vzwcorp.pricinglab.vo.dvs.retrievecustomerprofile.Service)unmarshallerCustProf.unmarshal(new StringReader(prodValidationRetData.get(DvsConstants.RETRIEVE_CUSTINFO_KEY)));	
			prodValidationRetData.remove(DvsConstants.RETRIEVE_CUSTINFO_KEY);
			serviceResponseCustProfile = serviceCustProfile.getServiceBody().getServiceResponse(); ///not expected to be null ;; if null it should fail here and response should be null
			customerInfoList = serviceResponseCustProfile.getCustomersList().getCustomerInfo();
			
			if(customerInfoList!=null && !customerInfoList.isEmpty()){
				mtnDetailList = customerInfoList.get(0).getBillAccountInfo().get(0).getMtnDetailList();
				
				sharePlanObj = customerInfoList.get(0).getBillAccountInfo().get(0).getSharePlanList();
			}
									
			if(prodValidationRetData!=null && !prodValidationRetData.isEmpty() && mtnDetailList!=null && !mtnDetailList.isEmpty()){
				if(mtnDetailList.get(0)!=null){
				for(Map.Entry<String, String> entryMtnData :  prodValidationRetData.entrySet()){
					for(com.vzwcorp.pricinglab.vo.dvs.retrievecustomerprofile.MtnDetail mtnDetailCustProfile : mtnDetailList.get(0).getMtnDetail()){
						if(mtnDetailCustProfile.getMtn().equals(entryMtnData.getKey())){
							com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.CreateOrderLineItem createOrderLineItem = new com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.CreateOrderLineItem();
							createOrderLineItem.setLineItemTypeCode("M");
							com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.ActivationChangeLineItem activationChangeLineItem = new com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.ActivationChangeLineItem();
							com.vzwcorp.pricinglab.vo.dvs.retrievecustomerprofile.PricePlanInfo custProfBasicPricePlan = mtnDetailCustProfile.getBasicPricePlan();
							com.vzwcorp.pricinglab.vo.dvs.retrievecustomerprofile.PhoneInfo custProfBasicPhoneInfo = mtnDetailCustProfile.getBasicPhoneInfo();
							
							com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.LineValidationItem lineValidationItem = new com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.LineValidationItem();
							lineValidationItem.setLineItemTypeCode("M");
							lineValidationItem.setEffectiveDate(sdf.format(new Timestamp(new Date().getTime())));
							
							com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.PricePlanDetails currPricePlanDetails = new com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.PricePlanDetails();
							currPricePlanDetails.setPricePlanId(custProfBasicPricePlan.getPricePlanId());
							currPricePlanDetails.setMeaCode(mtnDetailCustProfile.getMeaCode());
							lineValidationItem.setCurPricePlanDetails(currPricePlanDetails);
							
							com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.MtnDetail currMtnDetail = new com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.MtnDetail();
							currMtnDetail.setMtn(mtnDetailCustProfile.getMtn());
							currMtnDetail.setMin(mtnDetailCustProfile.getMin());
							lineValidationItem.setCurMtnDetail(currMtnDetail);
							
							com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.PhoneInfo currPhoneInfo = new com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.PhoneInfo();
							currPhoneInfo.setDeviceId(custProfBasicPhoneInfo.getDeviceId());
							currPhoneInfo.setDeviceIdType(custProfBasicPhoneInfo.getDeviceIdType());
							lineValidationItem.setCurPhoneInfo(currPhoneInfo);
							
							com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.PricePlanDetails newPricePlanDetails = new com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.PricePlanDetails();
							newPricePlanDetails.setPricePlanId(custProfBasicPricePlan.getPricePlanId());
							newPricePlanDetails.setMeaCode(mtnDetailCustProfile.getMeaCode());
							lineValidationItem.setNewPricePlanDetails(newPricePlanDetails);
							
							com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.SharePlanInfo sharePlanInfo = new com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.SharePlanInfo();
							if(sharePlanObj!=null){
								if(sharePlanObj.getSharePlanInfo()!=null && !sharePlanObj.getSharePlanInfo().isEmpty()){
									sharePlanInfo.setOldSharePlanId(sharePlanObj.getSharePlanInfo().get(0).getSharePlanId());
									sharePlanInfo.setNewSharePlanId(sharePlanObj.getSharePlanInfo().get(0).getSharePlanId());
								}
								else{
									sharePlanInfo.setOldSharePlanId("99999");
									sharePlanInfo.setNewSharePlanId("99999");
								}
								sharePlanInfo.setComponentType(sharePlanInfo.getComponentType());	
							}					
										
							com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.LineValidationItem.SharePlanInfoList sharePlanInfoList = new com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.LineValidationItem.SharePlanInfoList();				
							sharePlanInfoList.getSharePlanInfo().add(sharePlanInfo);
							lineValidationItem.setSharePlanInfoList(sharePlanInfoList);
							
							com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.MtnDetail newMtnDetail = new com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.MtnDetail();
							newMtnDetail.setMtn(mtnDetailCustProfile.getMtn());
							newMtnDetail.setMin(mtnDetailCustProfile.getMin());
							lineValidationItem.setNewMtnDetail(newMtnDetail);
							
							com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.PhoneInfo newPhoneInfo = new com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.PhoneInfo();
							newPhoneInfo.setDeviceId(custProfBasicPhoneInfo.getDeviceId());
							newPhoneInfo.setDeviceIdType(custProfBasicPhoneInfo.getDeviceIdType());
							lineValidationItem.setNewPhoneInfo(newPhoneInfo);
							
							com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.CustomerInfo customerInfo = new com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.CustomerInfo();
							customerInfo.setCustomerId(cust_Id_No+"-"+account_No);
							lineValidationItem.setCustomerInfo(customerInfo);
							
							lineValidationItem.setProductCorrelationId(entryMtnData.getValue());
							activationChangeLineItem.setLineValidationItem(lineValidationItem);
							createOrderLineItem.setActivationChangeLineItem(activationChangeLineItem);
							createOrderLineItemList.getCreateOrderLineItem().add(createOrderLineItem);		
						}
					}
					
							
				}
			}
				serviceRequest.setCreateOrderLineItemList(createOrderLineItemList);
				serviceBody.setServiceRequest(serviceRequest);
				service.setServiceBody(serviceBody);
				
				marshaller.marshal(service, sWriter);
				
				mlmoRequestXml.put("xmlreqdoc", sWriter.toString());
				//logger.debug("mlmo mlmoRequest service xml : "+sWriter.toString());
				logger.debug("Invoke mlmoRequest service in dvs");	
				//String response = issueDvsRequest(mlmoRequestXml);
				//String response = issueDvsReqUrlEncoded(dvsAPIURL.trim(),mlmoRequestXml);
				String response = pricingLabUtility.postHttpRequestWithURLParams(dvsAPIURL.trim(), mlmoRequestXml);
				
				if(response!=null && !response.isEmpty() && response.startsWith("Error"))
					mlmoResponseData.put(DvsConstants.ERROR_IN_MLMO_REQUEST,response);
				else{
					if(response!=null && !response.isEmpty()){
						service = (com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice.Service)unmarshaller.unmarshal(new StringReader(response));
						if(service.getServiceHeader().getErrorMsg()!=null && !service.getServiceHeader().getErrorMsg().isEmpty())
							mlmoResponseData.put(DvsConstants.ERROR_IN_MLMO_REQUEST,service.getServiceHeader().getErrorMsg());
						else
							mlmoResponseData.put(DvsConstants.MLMO_RESPONSE, response);
					}
				}
				sWriter = new StringWriter();
				marshaller.marshal(service, sWriter);
				logger.debug("service response in mlmoRequest "+sWriter.toString());
			
			}
			}
			catch(Exception e){
				//e.printStackTrace();
				logger.error("Exception in DVS Manager (issueMlmoRequest()) : ",e.getMessage(),e); 
				 mlmoResponseData.put(DvsConstants.ERROR_IN_MLMO_REQUEST,e.getMessage());
				 return mlmoResponseData;
			}
			return mlmoResponseData;
		}
		else{
			return prodValidationRetData;
		}	
		
	}
	
	
	public Map<String,String> billProrateCalc(String custID,String acctNum,String billProrateCalcXml){

		BillProrateCalcNONE billProrateCalcNONE = new BillProrateCalcNONE();
		com.vzwcorp.pricinglab.vo.dvs.nbs.Service service = new com.vzwcorp.pricinglab.vo.dvs.nbs.Service();
		com.vzwcorp.pricinglab.vo.dvs.nbs.ServiceHeader serviceHeader = new com.vzwcorp.pricinglab.vo.dvs.nbs.ServiceHeader();
		com.vzwcorp.pricinglab.vo.dvs.nbs.ServiceBody serviceBody = new com.vzwcorp.pricinglab.vo.dvs.nbs.ServiceBody();
		com.vzwcorp.pricinglab.vo.dvs.nbs.ServiceResponse serviceResponse = new com.vzwcorp.pricinglab.vo.dvs.nbs.ServiceResponse();

		boolean addOn = false;
		double charge = 0.0;
		double addOnCharge=0.0;
		Map<String,String> responseMap = new HashMap<>();
		List<Double> chargePerDay = null;
		StringWriter sWriter = new StringWriter();
		Date subCustBlCycleStartDate=null;
		Date subCustBlCycleEndDate = null;
		String correlationId=null;

		Unmarshaller unmarshaller = getunMarshaller(DvsConstants.NBS_PACKAGE);
		Marshaller marshaller = getMarshaller(DvsConstants.NBS_PACKAGE);

		serviceHeader.setServiceName(DvsConstants.NBS_SERVICE_NAME);

		try {
			service = (com.vzwcorp.pricinglab.vo.dvs.nbs.Service)unmarshaller.unmarshal(new StringReader(billProrateCalcXml));
			//billProrateCalcNONE  = (BillProrateCalcNONE)unmarshaller.unmarshal(new StringReader(billProrateCalcXml));
			if(service!=null){
				correlationId =  service.getCorrelationId();
				billProrateCalcNONE = service.getBillProrateCalc_NONE();
			}


			if(billProrateCalcNONE!=null){
				logger.debug("Correlation id : "+correlationId);				
				List<Date> billCycleDates = productManager.getSubCustBillCycleDates(Long.valueOf(custID), Long.valueOf(acctNum));

				if(billCycleDates!=null && !billCycleDates.isEmpty()){
					subCustBlCycleStartDate = billCycleDates.get(0);
					subCustBlCycleEndDate = billCycleDates.get(1);
				}

				Long[] indicator = {PlabConstants.ENROLLED,PlabConstants.PRE_ACCEPT};
				List<PlabCust> plabCustList = plabCustRepository.findByCustIdNoAndAcctNoAndIndicator(Long.valueOf(custID), Long.valueOf(acctNum), indicator);

				if(plabCustList!=null && !plabCustList.isEmpty()){
					PlabCust plabCust = plabCustList.get(0);
					Offer offer = plabCust.getOffer();
					List<com.vzwcorp.pricinglab.vo.Service> services = serviceRepository.findByOffer(offer);
					if(services!=null && !services.isEmpty()){
						for(com.vzwcorp.pricinglab.vo.Service serviceTemp : services){
							//System.out.println("rating group id:"+serviceTemp.getRatingGroup().getRatingGroupId());
							if((serviceTemp.getRatingGroup().getRatingGroupId()!=3300)){
								//System.out.println("servicid:"+serviceTemp.getServiceId());
								addOn = serviceTemp.isAddOn();	
								addOnCharge = serviceTemp.getCharge();
								if(addOn)
									logger.debug("addoNCharge : "+addOnCharge);
								if(!addOn){
									Set<QoS> chargePerDaySet = serviceTemp.getQos();
									chargePerDay = mcsManager.getChargePerDaySpeed(chargePerDaySet,null,null);
									if(chargePerDay!=null && !chargePerDay.isEmpty()){
										logger.debug("chargePerday for all speed tiers : ");
										for(Double cpd : chargePerDay){
											logger.debug(cpd);
										}
									}
								}
							}
						}
					}

					for(PlabCust plabCustTemp : plabCustList){

						List<OfferInstance> offerInstanceList = offerInstanceRepository.findLatestOfferInstanceByMdn(plabCustTemp.getMdn());
						
						if(offerInstanceList!=null && !offerInstanceList.isEmpty()){
							OfferInstance offerInstance = offerInstanceList.get(0);
							System.out.println(offerInstance.getOfferInstanceId()+";"+plabCustTemp.getMdn());
							List<ServiceInstance> serviceInstanceList = serviceInstanceRepository.findLatestByOfferInstance(offerInstance.getOfferInstanceId()); 
							if(addOn){
								if(serviceInstanceList!=null && !serviceInstanceList.isEmpty()){
									for(ServiceInstance serviceInstance : serviceInstanceList){
										if(serviceInstance.getService().isAddOn()){
											charge = charge+addOnCharge;
											break;
										}
									}
									
								}					
							}
							else{
								List<ChoiceInstance> choiceInstanceList = choiceInstanceRepository.findByOfferInstanceAndEndTimestamp(offerInstance.getOfferInstanceId(),subCustBlCycleStartDate);

								if(choiceInstanceList!=null && !choiceInstanceList.isEmpty()){
									List<Object> billableDaysResponse = mcsManager.getBillableDays(subCustBlCycleStartDate,subCustBlCycleEndDate, offerInstance.getDateCreated(), offerInstance.getEndTmstamp(),false);

									if(billableDaysResponse!=null && !billableDaysResponse.isEmpty()){
										List<Double> chargePerMdnForEachspeedTier = mcsManager.calcChargeNoHistory(chargePerDay, choiceInstanceList,(long) billableDaysResponse.get(0));
										if(chargePerMdnForEachspeedTier!=null && !chargePerMdnForEachspeedTier.isEmpty()){
											for(Double chargeTemp : chargePerMdnForEachspeedTier){
												charge=charge+chargeTemp;
											}												
										}
									}
								}
								else{
									service = setErrorMsgAndBillProrateCalcXml(serviceHeader, "No Plab customer found for the given custid : "+custID, serviceResponse, serviceBody, service, billProrateCalcNONE);
									marshaller.marshal(service, sWriter);
									responseMap.put(DvsConstants.BILL_PRORATE_CALC_NOT_FOUND_RESPONSE,sWriter.toString());	
									return responseMap;
								}

							}
						}
						else{
							service = setErrorMsgAndBillProrateCalcXml(serviceHeader, "No Plab customer found for the given custid : "+custID, serviceResponse, serviceBody, service, billProrateCalcNONE);
							marshaller.marshal(service, sWriter);
							responseMap.put(DvsConstants.BILL_PRORATE_CALC_NOT_FOUND_RESPONSE,sWriter.toString());
							return responseMap;
						}
					}

					MobileFirstTotalDetails mobileFirstTotalNext = billProrateCalcNONE.getMobileFirstTotalNext();

					if(mobileFirstTotalNext==null){
						service = setErrorMsgAndBillProrateCalcXml(serviceHeader, "BAD REQUEST : MOBILEFIRSTTOTALNEXT tag is missing", serviceResponse, serviceBody, service, billProrateCalcNONE);
						marshaller.marshal(service, sWriter);
						responseMap.put(DvsConstants.ERROR_IN_BILL_PRORATE_CALC_MOBILE_FIRST_TOTAL_NEXT,sWriter.toString());
						return responseMap;
					}

					if(addOn){
						NumberFormat formatter = new DecimalFormat("0.00");
						logger.debug("final add on charge set in MtnMonthlyRecurringCharges : "+formatter.format(charge));
						mobileFirstTotalNext.setMtnMonthlyRecurringCharges(formatter.format(charge));
					}
					else{
						charge = BigDecimal.valueOf(charge).setScale(2, RoundingMode.HALF_UP).doubleValue();
						logger.debug("final speed charges set in AccountMonthlyRecurringCharges : "+charge);
						mobileFirstTotalNext.setAccountMonthlyRecurringCharges(Double.toString(charge));
					}
					billProrateCalcNONE.setMobileFirstTotalNext(mobileFirstTotalNext);
					service = setErrorMsgAndBillProrateCalcXml(serviceHeader, "", serviceResponse, serviceBody, service, billProrateCalcNONE);
					marshaller.marshal(service, sWriter);
					logger.debug("Post nbs Calculations xml for correlation id : "+correlationId+" is : "+sWriter.toString());						
					responseMap.put(DvsConstants.BILL_PRORATE_CALC_RESPONSE, sWriter.toString());

				}
				else{
					service = setErrorMsgAndBillProrateCalcXml(serviceHeader, "No Plab customer found for the given custid : "+custID, serviceResponse, serviceBody, service, billProrateCalcNONE);
					marshaller.marshal(service, sWriter);
					responseMap.put(DvsConstants.BILL_PRORATE_CALC_NOT_FOUND_RESPONSE,sWriter.toString());
				}
			}

		} catch (JAXBException e) {
			logger.error("Exception for correlationID : "+correlationId+" : "+e.getMessage());
			logger.error("Exception in DVS Manager (processBillProrateCalcXML()) : ",e.getMessage(),e);
			service = setErrorMsgAndBillProrateCalcXml(serviceHeader, "Exception for correlationID : "+correlationId+" : "+e.getMessage(), serviceResponse, serviceBody, service, billProrateCalcNONE);
			StringWriter sw = new StringWriter();
			try {
				marshaller.marshal(service, sw);
			} catch (JAXBException e1) {
				e1.getMessage();
			}
			responseMap.put(DvsConstants.ERROR_IN_BILL_PRORATE_CALC,sw.toString());
			return responseMap;
		}			
		return responseMap;	
	}
	
	
	public Unmarshaller getunMarshaller(String packageInfo){
		JAXBContext jaxbContext;
		Unmarshaller unMarshaller = null;
		try {
			jaxbContext = JAXBContext.newInstance(packageInfo);
			unMarshaller = jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			e.printStackTrace();
			logger.error("Exception in DVS Manager (getunMarshaller()) : ",e.getMessage(),e); 
		}
		
		return unMarshaller;
	}
	
	public Marshaller getMarshaller(String packageInfo){
		JAXBContext jaxbContext;
		Marshaller marshaller = null;
		try {
			jaxbContext = JAXBContext.newInstance(packageInfo);
			marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		} catch (JAXBException e) {
			e.printStackTrace();
			logger.error("Exception in DVS Manager (getMarshaller()) : ",e.getMessage(),e); 
		}
		
		return marshaller;
	}
	
	private ProductOfferInfo setSpoForDeletionInProductValidation(String spo,String mdn,String actionIndicator){
		logger.debug("IN setSpoForDeletionInProductValidation deleting spo : "+spo+" for mdn "+mdn);
		DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		ProductOfferInfo productOfferInfo = new ProductOfferInfo();
		productOfferInfo.setProductId(spo);
		productOfferInfo.setEffectiveDate(sdf.format(new Timestamp(new Date().getTime())));
		productOfferInfo.setActionIndicator(actionIndicator);
		return productOfferInfo;
	}
	
	public com.vzwcorp.pricinglab.vo.dvs.nbs.Service setErrorMsgAndBillProrateCalcXml(com.vzwcorp.pricinglab.vo.dvs.nbs.ServiceHeader serviceHeader,String errorMsg,com.vzwcorp.pricinglab.vo.dvs.nbs.ServiceResponse serviceResponse,com.vzwcorp.pricinglab.vo.dvs.nbs.ServiceBody serviceBody,	com.vzwcorp.pricinglab.vo.dvs.nbs.Service service,BillProrateCalcNONE billProrateCalcNONE){
		serviceHeader.setErrorMsg(errorMsg);
		serviceResponse.setBillProrateCalcNONE(billProrateCalcNONE);
		serviceBody.setServiceResponse(serviceResponse);
		service.setServiceHeader(serviceHeader);
		service.setServiceBody(serviceBody);
		service.setCorrelationId(null);
		service.setBillProrateCalc_NONE(null);
		return service;
	}
}
