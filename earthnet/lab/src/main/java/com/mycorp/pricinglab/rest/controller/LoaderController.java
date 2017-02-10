package com.vzwcorp.pricinglab.rest.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vzwcorp.pricinglab.constants.DvsConstants;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import com.vzwcorp.pricinglab.helper.ProductHelper;
import com.vzwcorp.pricinglab.service.DvsManager;
import com.vzwcorp.pricinglab.service.GridService;
import com.vzwcorp.pricinglab.service.ProductManager;
import com.vzwcorp.pricinglab.service.ServiceManager;
import com.vzwcorp.pricinglab.utility.PricingLabUtility;
import com.vzwcorp.pricinglab.vo.LoaderRequest;
import com.vzwcorp.pricinglab.vo.Offer;
import com.vzwcorp.pricinglab.vo.OfferInstance;
import com.vzwcorp.pricinglab.vo.PlabCust;
import com.vzwcorp.pricinglab.vo.Rule;
import com.vzwcorp.pricinglab.vo.RuleInstance;
import com.vzwcorp.pricinglab.vo.Service;
import com.vzwcorp.pricinglab.vo.ServiceInstance;
import com.vzwcorp.pricinglab.vo.VerizonEntity;
import com.vzwcorp.pricinglab.vo.repository.ChoiceInstanceRepository;
import com.vzwcorp.pricinglab.vo.repository.OfferInstanceRepository;
import com.vzwcorp.pricinglab.vo.repository.PlabCustRepository;
import com.vzwcorp.pricinglab.vo.repository.RuleInstanceRepository;
import com.vzwcorp.pricinglab.vo.repository.ServiceInstanceRepository;
import com.vzwcorp.pricinglab.vo.repository.ServiceRepository;
import com.vzwcorp.pricinglab.vo.repository.VerizonEntityRepository;

/**
 * <p>Controller to assist loader in the following activities :
 * <ol>
 *     <li>SPO Provisioning to MDN</li>
 *     <li>Activate/Deactivate/Suspend/ChangeBillCycle</li>
 *     <li>Change an MDN</li>
 *     <li>Safety Mode</li>
 *     <li>Change MDN</li>
 * </ol>
 * </p>
 */

@RestController
@Transactional("blTransactionManager")
public class LoaderController {
	@Autowired
	ServiceRepository serviceRepository;
	
	@Autowired
	PlabCustRepository plabCustRepository;
	
	@Autowired
	ProductManager productManager;
	
	@Autowired
	VerizonEntityRepository vzwEntityRepository;
	
	@Autowired
    OfferInstanceRepository offerInstanceRepository;
	
	@Autowired
	ServiceInstanceRepository serviceInstanceRepository;
	
	@Autowired
	ServiceManager serviceManager;
	
	@Autowired
	RuleInstanceRepository ruleInstanceRepository;
	
	@Autowired
	ChoiceInstanceRepository choiceInstanceRepository;
	
	@Autowired
	PricingLabUtility utility;
	 
	@Autowired
	DvsManager dvsManager;
	 
	@Value("${plab.url}")
	String plabURL;
	
	@Autowired
	OfferController offerController;
	
	@Autowired
	ProductHelper productHelper;
	
	@Autowired
	GridService gridService;
	
	@Autowired
	ServiceController serviceController;
		
	private static Logger logger = LogManager.getLogger(LoaderController.class);
	
	public static String LOADER_DATE_FORMAT = "yyyy-MM-dd";
	
	  @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/loader/eventHandler", method = RequestMethod.POST)
	  public ResponseEntity eventHandler(@RequestBody String loaderRequestJson) {
		  
		  ResponseEntity responseEntity  = null;
		  LoaderRequest loaderJsonReceived=null;
		  
		  logger.debug("EventHandler Json received is : "+loaderRequestJson);
		  ObjectMapper mapper = productHelper.getDefaultObjectMapper();
			
		  try {
			  loaderJsonReceived = mapper.readValue(loaderRequestJson, new TypeReference<LoaderRequest>() {
              });
          } catch (Exception ex) {
        	  ex.printStackTrace();
              logger.debug("Unable to parse loaderRequestJson!");
              return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Unable to parse loaderRequestJson!", PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
          }
		 
		  if(loaderJsonReceived.getEventType()!=null && !loaderJsonReceived.getEventType().isEmpty()){
			  if(loaderJsonReceived.getEventType().equals(PlabConstants.LINE_ACTIVATE)){
				  responseEntity = lineActivate(loaderJsonReceived);				
			  }else if(loaderJsonReceived.getEventType().equals(PlabConstants.DP_CUST_BL_CYCLE)){
				  responseEntity = changeBillCycle(loaderJsonReceived);
			  }else if(loaderJsonReceived.getEventType().equals(PlabConstants.CHANGE)){
				  return new ResponseEntity(utility.createResponseInfo(HttpStatus.OK,"Request Ignored by PLAB",PlabConstants.STATUS_SUCCESS),HttpStatus.OK);
			  }else if(loaderJsonReceived.getEventType().equals(PlabConstants.LINE_DE_ACTIVATE)){
				  responseEntity = deActivate(loaderJsonReceived);
			  }else if(loaderJsonReceived.getEventType().equals(PlabConstants.SUSPEND)){
				  responseEntity = suspend(loaderJsonReceived);
			  }else if(loaderJsonReceived.getEventType().equals(PlabConstants.RE_ASSIGN)){
				  responseEntity = reAssign(loaderJsonReceived);
			  }else if(loaderJsonReceived.getEventType().equals(PlabConstants.TRANSFER)){
				  responseEntity = transfer(loaderJsonReceived);
			  } else if(loaderJsonReceived.getEventType().equals(PlabConstants.RECONNECT)){
				  responseEntity = reconnect(loaderJsonReceived);
			  }else if(loaderJsonReceived.getEventType().equals(PlabConstants.DP_LN_SVC_PROD)){
				  return new ResponseEntity(utility.createResponseInfo(HttpStatus.OK,"Request Ignored by PLAB",PlabConstants.STATUS_SUCCESS),HttpStatus.OK);
			  } 
			  return responseEntity;
		  }else{
			  logger.debug("Event type cannot be null or empty");
			  return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Event type cannot be null or empty", PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
		  }
	  }
	  

	/*
     *process only for spo mapped to mdn
    * what if there is a recon. we dont receive those events. How do we handle thse scenarios?
    *Requirement for Night surfer as given by amr : just activate the base on new mdn;its the mdn's choice to activate the night surfer.
    *
    *to-do :: unthrottle
    *	   :: schedule jobs if needed
    */
	  @SuppressWarnings({ "rawtypes", "unchecked" })
    public ResponseEntity lineActivate (LoaderRequest loaderRequest) { 
    	
    	if(loaderRequest.getCustomerId()==null){
    		logger.debug("Customer ID cannot be null or empty for deActivating a line");
    		return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Customer ID cannot be null or empty for activating a line", PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
    	}else if(loaderRequest.getAccountNumber()==null){
    		logger.debug("Account Number cannot be null or empty for activating a line");
    		return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Account Number cannot be null or empty for activating a line", PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
    	}else if(loaderRequest.getMdn()==null || loaderRequest.getMdn().isEmpty()){
    		logger.debug("Mdn cannot be null or empty for activating a line");
    		return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Mdn cannot be null or empty for activating a line", PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
    	}else if(loaderRequest.getMdn_effective_date()==null ){
    		logger.debug("Mdn effective date cannot be null or empty for activating a line");
    		return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Mdn effective date cannot be null or empty for activating a line", PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
    	}
    	
    	logger.info("lineActivate() called for custId : {},account# : {},mdn : {},mtnEffDate : {}",loaderRequest.getCustomerId(),loaderRequest.getAccountNumber(),loaderRequest.getMdn(),loaderRequest.getMdn_effective_date());
    	
    	VerizonEntity vzEntity = null;
    	Service service =null;
    	Map<String,String> attributesMap = new HashMap<>();
    	List<PlabCust> plabCustList = new ArrayList<>();
    	Service serviceForserviceInstance =null;

    	Long[] indicator = {PlabConstants.ENROLLED};  	
    	List<PlabCust> activeplabCustList = plabCustRepository.findByCustIdNoAndAcctNoAndIndicator(loaderRequest.getCustomerId(),loaderRequest.getAccountNumber(), indicator);

    	if(activeplabCustList!=null && !activeplabCustList.isEmpty()){
    		Offer offer = activeplabCustList.get(0).getOffer();	

    		service = productManager.getService(offer);

    		if(service!=null){
    			
    			String serviceType = serviceManager.getServiceType(service);
    			
    			if(serviceType!=null && !serviceType.isEmpty()){
    				
    				if(serviceType.equalsIgnoreCase(PlabConstants.SPEED_TIER_SERVICE_TYPE)){
						attributesMap.put(PlabConstants.QOS_RULETYPE_BANDWIDTH,PlabConstants.SPEED_FAST);
						serviceForserviceInstance = service;
    				}else{
    					serviceForserviceInstance = serviceManager.getDefaultService();
    				}
    				Timestamp mdneffDt = productManager.parseStringToTimestamp(loaderRequest.getMdn_effective_date(), LOADER_DATE_FORMAT);
    				Map<String,Object> entitiesList = productManager.createOrUpdatePricingLabCustomers(loaderRequest.getCustomerId(),loaderRequest.getAccountNumber(),loaderRequest.getMdn(),offer, mdneffDt);
    				if(entitiesList!=null && !entitiesList.isEmpty()){
    					
    					boolean isCustomerOnSafetyMode = serviceController.isCustomerAccountOnSafetyMode(loaderRequest.getCustomerId(),loaderRequest.getAccountNumber());
    					
    					vzEntity = (VerizonEntity)entitiesList.get(PlabConstants.VERIZON_ENTITY);
    					PlabCust plabCust = (PlabCust)entitiesList.get(PlabConstants.PLAB_CUST);
    					plabCustList.add(plabCust);

    					OfferInstance offerInstance = productManager.createNewOfferInstanceOrUpdateTcOfExisting(offer, vzEntity, 1, null);
    					offerInstance.setServiceInstances(new ArrayList<ServiceInstance>()); 
    					ServiceInstance serviceInstance = productManager.createServiceInstance(serviceForserviceInstance, vzEntity, offerInstance, attributesMap, isCustomerOnSafetyMode); 
    					serviceInstance = productManager.addEOCRuleInstance(serviceInstance,vzEntity.getCustIdNo(),null);	
    					offerInstance.getServiceInstances().add(serviceInstance);
    					serviceInstanceRepository.saveAndFlush(serviceInstance);
    					serviceManager.addVispServiceToDevice(serviceInstance);
    					productManager.createDefaultChoiceInstance(offer, offerInstance,service,serviceType);
    					productManager.updatePlabCustIndicator(loaderRequest.getMdn(), plabCustList, PlabConstants.ENROLLED);
    					productManager.createOrUpdateRefGridPlabCust(loaderRequest.getCustomerId(), loaderRequest.getAccountNumber(), loaderRequest.getMdn(), PlabConstants.ENROLLED, mdneffDt, null);
    					gridService.update(loaderRequest.getCustomerId(), loaderRequest.getAccountNumber(), loaderRequest.getMdn(), PlabConstants.ENROLLED);
    					offerInstanceRepository.saveAndFlush(offerInstance);
    					logger.debug("Plab and Visp activation done for mdn : "+loaderRequest.getMdn());       					
    					Map<String,List<String>> mdnSpoList = productManager.getSpoOfMdn(plabCustList, false);
    					ResponseEntity responseEntity = productManager.issueMlmo(loaderRequest.getCustomerId().toString(), loaderRequest.getAccountNumber().toString(),plabCustList,mdnSpoList,null,DvsConstants.ACTION_INDICATOR_ACCEPT); 
    					if(isCustomerOnSafetyMode){
    						serviceController.sendAgsDTLMessage(loaderRequest.getCustomerId(),loaderRequest.getAccountNumber(), true);
    					}
    					return responseEntity;
    					
    				}else{
    					logger.debug("Line activation was not successful.Error creating entities for mdn : "+loaderRequest.getMdn());
    					return new ResponseEntity(utility.createResponseInfo(HttpStatus.INTERNAL_SERVER_ERROR, "Line activation was not successful.Error creating entities for mdn : "+loaderRequest.getMdn(), PlabConstants.STATUS_ERROR),HttpStatus.INTERNAL_SERVER_ERROR);
    				}
    			}else{
    				logger.debug("Line activation was not successful for mdn : "+loaderRequest.getMdn()+" as ServiceType cannot be empty for offer "+offer);
    				return new ResponseEntity(utility.createResponseInfo(HttpStatus.INTERNAL_SERVER_ERROR, "Line activation was not successful for mdn : "+loaderRequest.getMdn()+"as ServiceType cannot be empty for offer "+offer, PlabConstants.STATUS_ERROR),HttpStatus.INTERNAL_SERVER_ERROR);
    			}
    		}else{
    			logger.debug("Line activation was not successful for mdn : "+loaderRequest.getMdn()+" as no service was found for the offer : "+offer);
    			return new ResponseEntity(utility.createResponseInfo(HttpStatus.INTERNAL_SERVER_ERROR, "Line activation was not successful for mdn : "+loaderRequest.getMdn()+"as no service was found for the offer : "+offer, PlabConstants.STATUS_ERROR),HttpStatus.INTERNAL_SERVER_ERROR);
    		}
    	}else{
    		logger.debug("Account is not active plab customer. Hence mdn : "+loaderRequest.getMdn()+" cannot be activated");
    		return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Account is not active plab customer. Hence mdn : "+loaderRequest.getMdn()+" cannot be activated", PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
    	}

    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
    public ResponseEntity deActivate (LoaderRequest loaderRequest) {
    	
       	if(loaderRequest.getCustomerId()==null){
       		logger.debug("Customer ID cannot be null or empty for deActivating a line");
    		return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Customer ID cannot be null or empty for deActivating a line", PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
    	}else if(loaderRequest.getAccountNumber()==null){
    		logger.debug("Account Number cannot be null or empty for deActivating a line");
    		return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Account Number cannot be null or empty for deActivating a line", PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
    	}else if(loaderRequest.getMdn()==null || loaderRequest.getMdn().isEmpty()){
    		logger.debug("Mdn cannot be null or empty for deActivating a line");
    		return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Mdn cannot be null or empty for deActivating a line", PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
    	}
    	
    	logger.info("deActivate() called for custId : {},account# : {},mdn : {}",loaderRequest.getCustomerId(),loaderRequest.getAccountNumber(),loaderRequest.getMdn());
    	
    	Long[] indicator = {PlabConstants.ENROLLED};  	
    	List<PlabCust> activeplabCustList = plabCustRepository.findByCustIdNoAndAcctNoAndIndicator(loaderRequest.getCustomerId(),loaderRequest.getAccountNumber(), indicator);

    	if(activeplabCustList!=null && !activeplabCustList.isEmpty()){
    		Offer offer = activeplabCustList.get(0).getOffer();	
    		ResponseEntity responseEntity= offerController.removeCustomerFromPilot(offer.getOfferId(), loaderRequest.getMdn(), false);
    		return responseEntity;
    	}else{
    		logger.debug("No active plab account for the given custId : "+loaderRequest.getCustomerId()+" and account# : "+loaderRequest.getAccountNumber());
    		return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "No active plab account for the given custId : "+loaderRequest.getCustomerId()+" and account# : "+loaderRequest.getAccountNumber(), PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
    	}
    	
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
    public ResponseEntity suspend (LoaderRequest loaderRequest) {
    	
     	if(loaderRequest.getCustomerId()==null){
     		logger.debug("Customer ID cannot be null or empty for suspending a line");
    		return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Customer ID cannot be null or empty for suspending a line", PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
    	}else if(loaderRequest.getAccountNumber()==null){
    		logger.debug("Account Number cannot be null or empty for suspending a line");
    		return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Account Number cannot be null or empty for suspending a line", PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
    	}else if(loaderRequest.getMdn()==null || loaderRequest.getMdn().isEmpty()){
    		logger.debug("Mdn cannot be null or empty for suspending a line");
    		return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Mdn cannot be null or empty for suspending a line", PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
    	}
    	
    	logger.info("suspend() called for custId : {},account# : {},mdn : {}",loaderRequest.getCustomerId(),loaderRequest.getAccountNumber(),loaderRequest.getMdn());
    	
     	Long[] indicator = {PlabConstants.ENROLLED};  	
    	List<PlabCust> activeplabCustList = plabCustRepository.findByCustIdNoAndAcctNoAndIndicator(loaderRequest.getCustomerId(),loaderRequest.getAccountNumber(), indicator);

    	if(activeplabCustList!=null && !activeplabCustList.isEmpty()){
    		Offer offer = activeplabCustList.get(0).getOffer();	
    		ResponseEntity responseEntity= offerController.removeCustomerFromPilot(offer.getOfferId(), loaderRequest.getMdn(), false);
    		return responseEntity;
    	}else{
    		logger.debug("No active plab account for the given custId : "+loaderRequest.getCustomerId()+" and account# : "+loaderRequest.getAccountNumber());
    		return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "No active plab account for the given custId : "+loaderRequest.getMdn()+" and account# : "+loaderRequest.getAccountNumber(), PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
    	}
    }
    
	 @SuppressWarnings({ "rawtypes", "unchecked" })
    public ResponseEntity reAssign (LoaderRequest loaderRequest) {
    	
    	if(loaderRequest.getCustomerId()==null){
    		logger.debug("Customer ID cannot be null or empty for reassigning a line");
    		return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Customer ID cannot be null or empty for reassigning a line", PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
    	}else if(loaderRequest.getAccountNumber()==null){
    		logger.debug("Account Number cannot be null or empty for reassigning a line");
    		return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Account Number cannot be null or empty for reassigning a line", PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
    	}else if(loaderRequest.getMdn()==null || loaderRequest.getMdn().isEmpty()){
    		logger.debug("Mdn cannot be null or empty for reassigning a line");
    		return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Mdn cannot be null or empty for reassigning a line", PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
    	}
    	
    	
    	logger.info("reAssign() called for custId : {},account# : {},mdn : {}",loaderRequest.getCustomerId(),loaderRequest.getAccountNumber(),loaderRequest.getMdn());
    	
     	Long[] indicator = {PlabConstants.ENROLLED};  	
    	List<PlabCust> activeplabCustList = plabCustRepository.findByCustIdNoAndAcctNoAndIndicator(loaderRequest.getCustomerId(),loaderRequest.getAccountNumber(), indicator);

    	if(activeplabCustList!=null && !activeplabCustList.isEmpty()){
    		Offer offer = activeplabCustList.get(0).getOffer();	
    		ResponseEntity responseEntity= offerController.removeCustomerFromPilot(offer.getOfferId(), loaderRequest.getMdn(), false);
    		return responseEntity;
    	}else{
    		logger.debug("No active plab account for the given custId : "+loaderRequest.getCustomerId()+" and account# : "+loaderRequest.getAccountNumber());
    		return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "No active plab account for the given custId : "+loaderRequest.getCustomerId()+" and account# : "+loaderRequest.getAccountNumber(), PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
    	}
    }
    
	@SuppressWarnings({ "rawtypes", "unchecked" })	 
    public ResponseEntity transfer(LoaderRequest loaderRequest) {
    		
    	if(loaderRequest.getCustomerId()==null){
    		logger.debug("Customer ID cannot be null or empty for transfering a line");
    		return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Customer ID cannot be null or empty for transfering a line", PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
    	}else if(loaderRequest.getAccountNumber()==null){
    		logger.debug("Account Number cannot be null or empty for transfering a line");
    		return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Account Number cannot be null or empty for transfering a line", PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
    	}else if(loaderRequest.getMdn()==null || loaderRequest.getMdn().isEmpty()){
    		logger.debug("Mdn cannot be null or empty for transfering a line");
    		return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Mdn cannot be null or empty for transfering a line", PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
    	}
    	
    	logger.info("transfer() called for custId : {},account# : {},mdn : {}",loaderRequest.getCustomerId(),loaderRequest.getAccountNumber(),loaderRequest.getMdn());
    	
     	Long[] indicator = {PlabConstants.ENROLLED};  	
    	List<PlabCust> activeplabCustList = plabCustRepository.findByCustIdNoAndAcctNoAndIndicator(loaderRequest.getCustomerId(),loaderRequest.getAccountNumber(), indicator);

    	if(activeplabCustList!=null && !activeplabCustList.isEmpty()){
    		Offer offer = activeplabCustList.get(0).getOffer();	
    		ResponseEntity responseEntity= offerController.removeCustomerFromPilot(offer.getOfferId(), loaderRequest.getMdn(), false);
    		return responseEntity;
    	}else{
    		logger.debug("No active plab account for the given custId : "+loaderRequest.getCustomerId()+" and account# : "+loaderRequest.getAccountNumber());
    		return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "No active plab account for the given custId : "+loaderRequest.getCustomerId()+" and account# : "+loaderRequest.getAccountNumber(), PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
    	}
    }
	
	/**
	 * For now, we are just calling removeCustomerFromPilot, till the requirements are finalized.
	 * @param loaderJsonReceived
	 * @return
	 */
	private ResponseEntity changeBillCycle(LoaderRequest loaderRequest) {
    	logger.info("changeBillCycle() called for custId : {},billCycleNo : {}",loaderRequest.getCustomerId(),loaderRequest.getBl_cyc_no());
    	
    	if(loaderRequest.getCustomerId()==null){
    		return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Customer ID cannot be null or empty for changeBillCycle", PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
    	}else if(loaderRequest.getBl_cyc_no()==null){
    		return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "BL_CYC_NO Number cannot be null or empty for changeBillCycle", PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
    	}
    	
     	Long[] indicator = {PlabConstants.ENROLLED};  	
    	List<PlabCust> activeplabCustList = plabCustRepository.findByCustIdNoAndAcctNoAndIndicator(loaderRequest.getCustomerId(),loaderRequest.getAccountNumber(), indicator);

    	if(activeplabCustList!=null && !activeplabCustList.isEmpty()){
    		Offer offer = activeplabCustList.get(0).getOffer();	
    		ResponseEntity responseEntity= offerController.removeCustomerFromPilot(offer.getOfferId(), loaderRequest.getMdn(), false);
    		return responseEntity;
    	}else{
    		logger.debug("No active plab account for the given custId : "+loaderRequest.getCustomerId()+" and account# : "+loaderRequest.getAccountNumber());
    		return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "No active plab account for the given custId : "+loaderRequest.getCustomerId()+" and account# : "+loaderRequest.getAccountNumber(), PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
    	}
	}
    
    /*Questions : 
     * Can we change the bill cycle for customers who exited the plan but are pending eoc?
     * update the schedulers once the bill cycle is updated
     * 
     */
	 /* @SuppressWarnings({ "rawtypes", "unchecked" })
     public ResponseEntity changeBillCycle (LoaderRequest loaderRequest) {
    	logger.info("changeBillCycle() called for custId : {},billCycleNo : {}",loaderRequest.getCustomerId(),loaderRequest.getBl_cyc_no());
    	
    	if(loaderRequest.getCustomerId()==null){
    		return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Customer ID cannot be null or empty for changeBillCycle", PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
    	}else if(loaderRequest.getBl_cyc_no()==null){
    		return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "BL_CYC_NO Number cannot be null or empty for changeBillCycle", PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
    	}
    	
    	List<RuleInstance >ruleInstanceList = null;
   	 	SimpleDateFormat sdfTemp = new SimpleDateFormat(LOADER_DATE_FORMAT);	
   	 	Date billCycleEndDate = null;
   	 	int i=0;
    	
    			try {
  				  billCycleEndDate = sdfTemp.parse((Calendar.getInstance().get(Calendar.MONTH) + 2) + "/" + (Long.valueOf(loaderRequest.getBl_cyc_no())-1) + "/" + Calendar.getInstance().get(Calendar.YEAR) + " 00:00:01");
  				
  				Long[] indicator = {PlabConstants.ENROLLED};
  	    		List<PlabCust> plabCustList = plabCustRepository.findByCustIdNoAndIndicator(Long.valueOf(loaderRequest.getCustomerId()), indicator);
  	    		
  	    		if(plabCustList!=null && !plabCustList.isEmpty()){
  	    			
  	    			Offer offer = plabCustList.get(0).getOffer();
  	    			
  	    			for(PlabCust plabCust : plabCustList){
  	    				
  	    				OfferInstance offerInstance = productManager.getValidOfferInstance(plabCust.getMdn(), offer.getOfferId(), null);
  	    				
  	    				List<ServiceInstance> serviceInstanceList = serviceInstanceRepository.findLatestByOfferInstance(offerInstance.getOfferInstanceId());
  	    			  	    				
  	    				if(serviceInstanceList!=null && !serviceInstanceList.isEmpty()){
  	    					for(ServiceInstance serviceInstance : serviceInstanceList){
  	    						ruleInstanceList = serviceInstance.getRules();
  	    						if(ruleInstanceList!=null && !ruleInstanceList.isEmpty()){
  	    							for(RuleInstance ruleInstance : ruleInstanceList){
  	    								if(Rule.qosEOCRuleType.equalsIgnoreCase(ruleInstance.getRuleType())){
  	    									String timeZone = productHelper.getCustomerTimeZone(loaderRequest.getCustomerId());
  	    									  ruleInstance.setExpression(PricingLabUtility.getEOCExpression(billCycleEndDate, timeZone)); //ask ajay regarding timezone
  	    									  ruleInstanceRepository.saveAndFlush(ruleInstance);
  	    									  int j = serviceManager.updateVispServiceToDevice(serviceInstance);
  	    			    					  i=i+j;
  	    								}
  	    							}
  	    						}
  	    					}    					
  	    				}
  	    				else{
  	    	    			return new ResponseEntity("No serviceInstances found for the given custIdNo", HttpStatus.NOT_FOUND);
  	    	    		}	    				
  	    			}   				
  	    		}else{
  	    			return new ResponseEntity("No Plab customer found for the mdn and spoId", HttpStatus.NOT_FOUND);
  	    		}
  			} catch (Exception e) {
  				e.printStackTrace();
  			}
    		if(i==0){
      		  return new ResponseEntity("Bill Cycle information updated for all serviceInstances", HttpStatus.OK);
      			}
      		else{
      		  return new ResponseEntity("Bill Cycle information hasn't been updated for all serviceInstances", HttpStatus.INTERNAL_SERVER_ERROR);
      			}	
    		}*/	
	    
	    @SuppressWarnings({ "rawtypes", "unchecked" })
		private ResponseEntity reconnect(LoaderRequest loaderRequest) {
			
	    	if(loaderRequest.getCustomerId()==null){
	    		logger.debug("Customer ID cannot be null or empty for transfering a line");
	    		return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Customer ID cannot be null or empty for reconnecting a line", PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
	    	}else if(loaderRequest.getAccountNumber()==null){
	    		logger.debug("Account Number cannot be null or empty for transfering a line");
	    		return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Account Number cannot be null or empty for reconnecting a line", PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
	    	}else if(loaderRequest.getMdn()==null || loaderRequest.getMdn().isEmpty()){
	    		logger.debug("Mdn cannot be null or empty for transfering a line");
	    		return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "Mdn cannot be null or empty for reconnecting a line", PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
	    	}
	    	
	    	logger.info("reconnect() called for custId : {},account# : {},mdn : {}",loaderRequest.getCustomerId(),loaderRequest.getAccountNumber(),loaderRequest.getMdn());
	    	
	     	Long[] indicator = {PlabConstants.ENROLLED};  	
	    	List<PlabCust> activeplabCustList = plabCustRepository.findByCustIdNoAndAcctNoAndIndicator(loaderRequest.getCustomerId(),loaderRequest.getAccountNumber(), indicator);

	    	if(activeplabCustList!=null && !activeplabCustList.isEmpty()){
	    		Offer offer = activeplabCustList.get(0).getOffer();	
	    		ResponseEntity responseEntity= offerController.removeCustomerFromPilot(offer.getOfferId(), loaderRequest.getMdn(), false);
	    		return responseEntity;
	    	}else{
	    		logger.debug("No active plab account for the given custId : "+loaderRequest.getCustomerId()+" and account# : "+loaderRequest.getAccountNumber());
	    		return new ResponseEntity(utility.createResponseInfo(HttpStatus.BAD_REQUEST, "No active plab account for the given custId : "+loaderRequest.getCustomerId()+" and account# : "+loaderRequest.getAccountNumber(), PlabConstants.STATUS_ERROR),HttpStatus.BAD_REQUEST);
	    	}
	    
		}
    
}
