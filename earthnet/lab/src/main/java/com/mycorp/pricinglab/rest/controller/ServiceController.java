package com.vzwcorp.pricinglab.rest.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.vzwcorp.pricinglab.constants.OfferStatus;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import com.vzwcorp.pricinglab.constants.SearchStrategy;
import com.vzwcorp.pricinglab.constants.ServiceStatus;
import com.vzwcorp.pricinglab.helper.ProductHelper;
import com.vzwcorp.pricinglab.loader.profile.lookup.repository.RefGridPlabCustRepository;
import com.vzwcorp.pricinglab.loader.profile.lookup.repository.RefRgPlabConvRepository;
import com.vzwcorp.pricinglab.loader.profile.ubsr.repository.SubCustAcctMdnRepository;
import com.vzwcorp.pricinglab.mq.JMSSender;
import com.vzwcorp.pricinglab.profile.lookup.vo.RefRgPlabConv;
import com.vzwcorp.pricinglab.profile.vo.SubCustAcctMdn;
import com.vzwcorp.pricinglab.service.DistributedService;
import com.vzwcorp.pricinglab.service.ProductManager;
import com.vzwcorp.pricinglab.service.ServiceManager;
import com.vzwcorp.pricinglab.service.VispService;
import com.vzwcorp.pricinglab.utility.PricingLabUtility;
import com.vzwcorp.pricinglab.vo.*;
import com.vzwcorp.pricinglab.vo.repository.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;

@RestController
@Transactional("blTransactionManager")
public class ServiceController {

	public static long DefaultRatingGroup= 3300;
	
	static Logger logger = LogManager.getLogger(ServiceController.class);

	@Autowired
	OfferRepository offerRepository;

	@Autowired
	ServiceRepository repository;

	@Autowired
	RuleRepository ruleRepository;

	@Autowired
	RatingGroupRepository ratingGroupRepository;

	@Autowired
	ApplicationRepository appRepository;

	@Autowired
	VispService vispService;

	@Autowired
	ServiceManager serviceManager;
	
	@Autowired
	ServiceInstanceRepository serviceInstanceRepository;
	
	@Autowired
	PlabCustRepository plabCustRepository;

	@Autowired
	RefGridPlabCustRepository refGridPlabCustRepository;
	
	@Autowired
	RefRgPlabConvRepository refRgPlabConvRepository;

	@Autowired
    RefQoSRepository refQosRepository;

	@Autowired
	QOSRepository qosRepository;

	@Autowired
	VerizonEntityRepository verizonEntityRepository;

	@Autowired
	SubCustAcctMdnRepository subCustAcctMdnRepository;
	
    @Autowired
    OfferInstanceRepository offerInstanceRepository;

	@Autowired
	ProductManager productManager;

	@Autowired
	ProductHelper helper;
	
	@Autowired(required=false)
	JMSSender jmsSender; 

	ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	DistributedService distributedService;
	
	@Value("${safetymode.enabled:false}")
	boolean isSafetyModeEnabled;



	public ServiceController () {mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);}

	// Get a Service based on rating group id.
	@RequestMapping(value = "/service/ratinggroup/{ratingGroupId}", method = RequestMethod.GET)
	public String getServiceByRatingGroup(@PathVariable("ratingGroupId") String ratingGroupId) {
		logger.debug("ServiceController getServiceByRatingGroup ratingGroupId is " + ratingGroupId);

		RatingGroup ratingGroup = new RatingGroup();
		ratingGroup.setRatingGroupId(Long.parseLong(ratingGroupId));
		Service service = repository.findByRatingGroup(ratingGroup);

		if (service == null){
			logger.debug("ServiceController getServiceByRatingGroup() : returning null");
			return null;
		}

		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		ObjectWriter writer = mapper.writerWithView(Views.CPIView.class);
		String jsonInString = null;
		try {
			jsonInString = writer.writeValueAsString(service);
		} catch (JsonProcessingException e) {
			logger.error("Error in getServiceByRatingGroup () : ", e.getMessage(),e);
		}
		logger.debug("ServiceController getServiceByRatingGroup jsonInString is " + jsonInString);
		return jsonInString;

	}

	// Get a Service
	@RequestMapping(value = "/service/{serviceId}", method = RequestMethod.GET)
	public String getService(@PathVariable("serviceId") String serviceId) {
		logger.debug("ServiceController getService serviceId is " + serviceId);

		Service service = repository.findByServiceId(Long.parseLong(serviceId));

		if (service == null){
			logger.debug("ServiceController getService() :  returning null");
			return null;
		}

		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		ObjectWriter writer = mapper.writerWithView(Views.CPIView.class);
		String jsonInString = null;
		try {
			jsonInString = writer.writeValueAsString(service);
		} catch (JsonProcessingException e) {
			logger.error("Error in getServiceByRatingGroup () : ", e.getMessage(),e);
		}
		logger.debug("ServiceController getService() : jsonInString"+ jsonInString);
		return jsonInString;

	}
	
	
	/**
	 * Get Service rules
	 * 
	 * @param serviceId
	 * @return
	 */
	@RequestMapping(value = "/service/rules/{serviceId}", method = RequestMethod.GET)
	public String getServiceRules(@PathVariable("serviceId") String serviceId) {
		logger.debug("ServiceController getServiceRules serviceId is " + serviceId);

		Service service = repository.findByServiceId(Long.parseLong(serviceId));

		if (service == null){
			logger.debug("ServiceController getServiceRules() :  service not found for id : " +serviceId);
			return "Service Not found !";
		}

		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		ObjectWriter writer = mapper.writerWithView(Views.VispSupportView.class);
		String jsonInString = null;
		try {
			jsonInString = writer.writeValueAsString(service.getRules());
		} catch (JsonProcessingException e) {
			logger.error("Error in getServiceByRatingGroup () : ", e.getMessage(),e);
		}
		logger.debug("ServiceController getService() : jsonInString"+ jsonInString);
		return jsonInString;

	}
	
	/**
	 * *Update service rules with the expression
	 * @param serviceId
	 * @param rules - Array of ruleId-Expression
	 * @return
	 */
	@RequestMapping(value = "/service/rules/update/{serviceId}/{rules}", method = RequestMethod.POST)
	public String updateServiceRules(@PathVariable("serviceId") String serviceId, @PathVariable("rules") String[] rules){
		
		logger.debug("ServiceController updateServiceRules() :  serviceId : {} , rules : {} ", serviceId, rules);
		
		Service service = repository.findByServiceId(Long.parseLong(serviceId));

		if (service == null){
			logger.debug("ServiceController updateServiceRules() :  returning null");
			return "Service Not found !";
		}

		
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		ObjectWriter writer = mapper.writerWithView(Views.VispView.class);
		String jsonString = null;
		
		Map<String, String> ruleExpMap = new HashMap<String, String>();
		
		for(String ruleValue : rules){
			String ruleEntry[] = ruleValue.split("-");
			ruleExpMap.put(ruleEntry[0], ruleEntry[1]);
		}
		
		List<Rule> ruleList = service.getRules();
		
		for(Rule rule: ruleList){
			String expression = ruleExpMap.get(rule.getRuleId()+"");
			if(expression != null && !expression.isEmpty()){
				rule.setExpression(expression);
			}
		}
		
		service = repository.saveAndFlush(service);
		
		try {
			//service.setVispServiceID(null);
			service.setServiceType(serviceManager.getServiceType(service));
			jsonString = writer.writeValueAsString(service);
		} catch (JsonProcessingException e) {
			logger.error("Error in updateServiceRules () : ", e.getMessage(),e);
		}
		logger.debug("ServiceController updateServiceRules() : jsonInString"+ jsonString);
		return jsonString;
	}
	

	// Get a Service
	@RequestMapping("/service/spoId/{spoId}")
	public String getServiceBySpoID(HttpServletRequest request,@PathVariable("spoId") String spoId) {
		try {
			logger.info("Request from : {} ",request.getRemoteAddr());
			logger.info("ServiceController getServiceBySpoID spoId is " + spoId);

			List<Service> services = repository.findAllServicesBySpoID(spoId);
			if (services == null || (services!=null && services.isEmpty())){
				logger.debug("ServiceController getServiceBySpoID() : returning null");
				return null;
			}

			Service service = services.get(0);
			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			ObjectWriter writer = mapper.writerWithView(Views.CPIView.class);
			String jsonInString = writer.writeValueAsString(service);
			logger.debug("ServiceController getServiceBySpoID() : jsonInString"+ jsonInString);
			return jsonInString;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("Error in getServiceBySpoID () : ", e.getMessage(),e);
		}

		return null;
	}

	/**
	 * Get Service by SPO ID
	 * @param spoId
	 * @return
	 */
	@RequestMapping(value = "/service/offers/spo",method = RequestMethod.POST)
	public ResponseEntity getOffersBySpoID(@RequestParam(value = "spoId",defaultValue = "999999")String spoId
			,@RequestParam(value = "type",defaultValue = "map")String type) {
		try {
			logger.debug("ServiceController getServiceBySpoID spoId is " + spoId);
			logger.debug("ServiceController getServiceBySpoID type is " + type);
			
			List<Service> services = repository.findAllOfferServicesBySpoID(spoId);
			if (services == null || (services!=null && services.isEmpty())) {
				logger.debug("getOffersBySpoID() : offers/services by spo id not found for spo :"+spoId);
				return new ResponseEntity("offers/services by spo id not found for spo :"+spoId,HttpStatus.BAD_REQUEST);
			}

			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			ObjectWriter writer = mapper.writerWithView(Views.CPIView.class);
			Map<Long,String> offerList;
			List<Offer> offers = null;
			String jsonInString = null;
			if ("map".equalsIgnoreCase(type)) {
				offerList = new HashMap<Long,String>();
				for (Service service : services) {
					if (service!=null && service.getOffer()!=null && service.getOffer().getOfferId()!=null){
						offerList.put(service.getOffer().getOfferId(), service.getOffer().getName());
					} else {
						logger.error("Service : " + service.getServiceId());
					}
				}
				jsonInString = writer.writeValueAsString(offerList);
			} else if ("list".equalsIgnoreCase(type)){
				offers = new ArrayList<>();
				for (Service service : services) {
					if (service!=null){
						Offer offer = service.getOffer();
						if (offer!=null) {
							offers.add(offer);
						}
					} else {
						logger.error("Service : " + service.getServiceId());
					}
				}
				jsonInString = writer.writeValueAsString(offers);
			}
			logger.debug("ServiceController getServiceBySpoID() : "+jsonInString);
			return new ResponseEntity(jsonInString,HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in ServiceController --> getServiceBySpoID. spoId :"+spoId,e.getMessage(),e);
		}
		logger.debug("Error in ServiceController --> getServiceBySpoID. spoId :"+spoId);
		return new ResponseEntity("Error in ServiceController --> getServiceBySpoID. spoId :"+spoId,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Get Offer ID's by service state
	 * @param state
	 * @return
	 */
	@RequestMapping(value = "/service/offers/state",method = RequestMethod.POST)
	public ResponseEntity getOffersByServiceState(@RequestParam(value = "state",defaultValue = "draft")String state) {
		try {
			logger.debug("Retrive service/offers by state : " + state);

			List<Service> services = repository.findByState(state);
			if (services == null || (services!=null && services.isEmpty())) {
				logger.debug("no offers/services with given state found :" + state);
				return new ResponseEntity("no offers/services with given state found :" + state,HttpStatus.BAD_REQUEST);
			}

			Map<Long,Long> serviceMap = new HashMap<Long,Long>();
			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			ObjectWriter writer = mapper.writerWithView(Views.CPIView.class);

			for (Service service : services) {
				if (service.getOffer()!=null && service.getOffer().getOfferId() != null
						&& service.getServiceId()!=null) {
					serviceMap.put(service.getOffer().getOfferId(), service.getServiceId());
				} else {
					logger.error("Service : " + service.getServiceId() +", doesn't have an offer id mapped");
				}
			}

			String jsonInString = writer.writeValueAsString(serviceMap);
			logger.debug("getOffersByServiceState() : "+jsonInString);
			return new ResponseEntity(jsonInString,HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error in ServiceController --> getOfferByServiceState. State :"+state,e.getMessage(),e);
		}
		logger.debug("Error in ServiceController --> getOfferByServiceState. State :"+state);
		return new ResponseEntity("Error in ServiceController --> getOfferByServiceState. State :"+state,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Get Offer details with service id.
	 * @param serviceId
	 * @return
     */
	@RequestMapping(value = "/service/offer", method = RequestMethod.POST)
	public ResponseEntity getOfferByService(@RequestParam("serviceId") String serviceId) {

		logger.debug("ServiceController getOfferByServiceId :" + serviceId);
		StringBuilder buildResponse = new StringBuilder();
		Service service = null;

		try {

			if (serviceId == null) {
				logger.debug("getOfferByServiceId() : Service ID is null or empty.");
				return new ResponseEntity("Service ID is null or empty.",HttpStatus.BAD_REQUEST);
			}

			try {
				service	= repository.findByServiceId(Long.parseLong(serviceId));
				if (service == null) {
					logger.debug("getOfferByServiceId() : Service is null.");
					return new ResponseEntity("Service is null.", HttpStatus.BAD_REQUEST);
				}
			} catch (Exception ex) {
				buildResponse.append("Exception finding Service for service id : " + serviceId);
				logger.error(buildResponse.toString(),ex.getMessage(),ex);
				return new ResponseEntity(buildResponse.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
			}

			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			ObjectWriter writer = mapper.writerWithView(Views.CPIView.class);
			String jsonInString = writer.writeValueAsString(service.getOffer());
			logger.debug(jsonInString);
			return new ResponseEntity(jsonInString,HttpStatus.OK);

		} catch (Exception e) {
			buildResponse.append("Exception getOfferByService.");
			logger.error(buildResponse.toString(),e.getMessage(),e);
			return new ResponseEntity(buildResponse.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// List all Service
	@RequestMapping("/services")
	public String getServices() {

		String jsonInString = " Error";
		try {
			logger.debug("ServiceController getServices");

			List<Service> services = repository.findAll();
			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			ObjectWriter writer = mapper.writerWithView(Views.CPIView.class);
			jsonInString = writer.writeValueAsString(services);
		} catch (Exception e) {
			logger.error("Error in ServiceController --> getServices()",e.getMessage(),e);
			//e.printStackTrace();
		}
		logger.debug("getServices() : "+jsonInString);
		return jsonInString;
	}

	// Create a service
	@Deprecated
	@RequestMapping(value = "/service", method = RequestMethod.POST)
	public Service addService(@RequestParam(value = "serviceObject", defaultValue = "0") String jsonProduct) {

		logger.debug("ServiceController addService : jsonProduct : " + jsonProduct);

		Service service = null;
		try {
			long t0 = System.currentTimeMillis();
			service = mapper.readValue(jsonProduct, Service.class);
			long t1 = System.currentTimeMillis();
			logger.debug(" reading a service json in: " + (t1 - t0));

			service.setDateCreated(new Date());
			service = repository.saveAndFlush(service);
			logger.debug(" Service created, id=" + service.getServiceId());

			/*
			 * // Create Service in RBM String serviceId =
			 * rbmService.createProduct(service); System.out.println(
			 * " RBM Service created, id=" + serviceId) ;
			 */
			// Add VISP service
			String serviceId = vispService.addService(service);
			logger.debug(" VISP Service created, id=" + serviceId);

		} catch (JsonParseException e) {
			logger.error("ServiceController - addService() ");
		} catch (JsonMappingException e) {
			logger.error("Error in ServiceController --> addService()",e.getMessage(),e);
			//e.printStackTrace();
		} catch (IOException e) {
			logger.error("Error in ServiceController --> addService()",e.getMessage(),e);
			//e.printStackTrace();
		}
		return service;
	}

	
	@RequestMapping(value = "/admin/service/classification/bytime/{serviceInstanceId}/{expression}", method = RequestMethod.POST)
	public String updateServiceByTimeRule(@PathVariable("serviceInstanceId") Long serviceInstanceId,
									  @PathVariable("expression") String expression){
		
		logger.debug("updateServiceByTimeRule() : serviceInstanceId"+serviceInstanceId);
		logger.debug("updateServiceByTimeRule() : expression"+expression);
		
		ServiceInstance 	serviceInstance= serviceInstanceRepository.findOne(serviceInstanceId);
		List<RuleInstance> rules= serviceInstance.getRules();
		for ( RuleInstance rule: rules){
			if ( rule.getRuleType().equals(Rule.classficationRuleType) && rule.getExpressionType().equals(Rule.byTimeExpressionType))
				rule.setExpression(expression);
		}
		serviceManager.updateVispServiceToDevice(serviceInstance);
		serviceInstanceRepository.saveAndFlush(serviceInstance);
		logger.debug("updateServiceByTimeRule() : expression : {} ",expression);
		return expression;

	}	
	
	
	@RequestMapping(value = "/admin/service/qos/{serviceInstanceId}/{speed}", method = RequestMethod.POST)
	public String updateServiceByQOS(@PathVariable("serviceInstanceId") Long serviceInstanceId,
									  @PathVariable("speed") String speed){
		
		logger.debug("updateServiceByQOS() : serviceInstanceID : {} , speed : {} ",serviceInstanceId,speed);
		
		ServiceInstance 	serviceInstance= serviceInstanceRepository.findOne(serviceInstanceId);
		List<RuleInstance> rules= serviceInstance.getRules();
		for ( RuleInstance rule: rules){
			if ( rule.getRuleType().equals(Rule.qosRuleType))
				rule.getAttributes().put("bandwidth", speed);
		}
		serviceManager.updateVispServiceToDevice(serviceInstance);
		serviceInstanceRepository.saveAndFlush(serviceInstance);
		logger.debug("updateServiceByQOS() : speed : {} ",speed);
		return speed;

	}		
	
	@RequestMapping(value = "/admin/service/reporting/{serviceInstanceId}/{volume}", method = RequestMethod.POST)
	public String updateServiceRepoting(@PathVariable("serviceInstanceId") Long serviceInstanceId,
									  @PathVariable("volume") String volume ){
		
		logger.debug("updateServiceRepoting() : serviceInstanceID : {} , volume : {} ",serviceInstanceId,volume);
				
		ServiceInstance 	serviceInstance= serviceInstanceRepository.findOne(serviceInstanceId);
		List<RuleInstance> rules= serviceInstance.getRules();
		for ( RuleInstance rule: rules){
			if ( rule.getRuleType().equals(Rule.reportingRuleType))
				rule.setExpression(volume.toString());
		}
		serviceManager.updateVispServiceToDevice(serviceInstance);
		serviceInstanceRepository.saveAndFlush(serviceInstance);
		logger.debug("updateServiceRepoting() : "+volume);
		return volume;

	}

    /**
     * Update Service and Offer State
     * @param serviceId
     * @param state
     * @param rbmId
     * @param spoId
     * @return
     */
	@RequestMapping(value = "/service/update/{serviceId}", method = RequestMethod.POST)
	public String updateServiceByState(@PathVariable("serviceId") String serviceId,
								@RequestParam(value = "state", defaultValue = "0") String state,
								@RequestParam(value = "rbmId", defaultValue = "0") String rbmId,
								@RequestParam(value = "spoId", defaultValue = "0") String spoId) {

		logger.debug("ServiceController updateService. Service ID : {} , state : {}, rbmId : {}, spoId : {} ",serviceId, state, rbmId, spoId);

		// Service service = repository.findOne(serviceId);

		Service service = repository.findByServiceId(Long.valueOf(serviceId));
		List<RefRgPlabConv> refRgPlabConvList = new ArrayList<>();

		if (service == null){
			logger.debug("invalid service");
			return "invalid service";
		}
		// if ( service.getState().equals("deployed"))
		// return "service already deployed";
		if (!spoId.equals("0"))
			service.setSpoID(spoId);
		if (!rbmId.equals("0"))
			service.setRbmProductID(rbmId);
		if (!state.equals("0"))
			service.setState(state);

		//Only if the service is deployed
		boolean updateOfferStatus = false;
		if (service!=null) {
			if (service.getRbmProductID()!=null
					&& ServiceStatus.DEPLOYED.getServiceStatus().equalsIgnoreCase(service.getState())) {
				//check if all the services are deployed for the offerid for this service.
				List<Service> services = repository.findByOffer(service.getOffer());
				if (services != null && !services.isEmpty()) {
					for (Service serviceTemp : services) {
						if (ServiceStatus.DRAFT.equals(serviceTemp.getState())) {
							break;
						} else {
							updateOfferStatus = true;

							RefRgPlabConv refRgPlabConv = createRefrgPlabConv(service.getRatingGroup().getRatingGroupId());
							refRgPlabConvRepository.save(refRgPlabConv);
							distributedService.getHazelcastInstance().getQueue("REF_RG_CONV_"+helper.findMyDataCenterSuffix())
									.addItemListener(new RefGridPlabConvParam(service.getRatingGroup().getRatingGroupId()),true);
							//refRgPlabConvList.add(refRgPlabConv);

							serviceManager.createVispService(service);
						}
					}

					//update offer state as well.
					if (updateOfferStatus) {
						service.getOffer().setState(OfferStatus.DEPLOYED.name());
					} else {
						service.getOffer().setState(OfferStatus.DRAFT.name());
					}
					
					/*if(refRgPlabConvList!=null)
						refRgPlabConvRepository.save(refRgPlabConvList);	*/					
				}
			} else if (ServiceStatus.SUBMITTED.getServiceStatus().equalsIgnoreCase(service.getState())) {
				service.getOffer().setState(OfferStatus.SUBMITTED.name());
			} else {
				service.getOffer().setState(state);
			}
		}

		repository.saveAndFlush(service);
/*
		Commented as of 10/11/2016 - Visp service call is made above. Not required here.
		if (service.getState().toLowerCase().equals("deployed") && service.getSpoID() != null) {
			serviceManager.createVispService(service);
		}
*/		logger.debug("ServiceController updateService() : success");
		return "success";
	}

	public RefRgPlabConv createRefrgPlabConv(Long ApnRtGrpNoMax) {
		RefRgPlabConv refRgPlabConv = new RefRgPlabConv();
		refRgPlabConv.setApnName(PlabConstants.VZWINTERNET);
		refRgPlabConv.setApnRtGrpNoMax(ApnRtGrpNoMax);
		refRgPlabConv.setApnRtGrpNoMin(ApnRtGrpNoMax);
		refRgPlabConv.setDataCatCd(PlabConstants.PLAB);
		return refRgPlabConv;
	}

	/**
	 * Update Service for the offer
	 * @param jsonBody
	 * @param jsonProduct
     * @return
     */
	@RequestMapping(value = "/service/draft/update/cpi", method = RequestMethod.POST)
	public ResponseEntity updateServiceCPI(@RequestBody(required = false)String jsonBody, @RequestParam(value = "serviceObject", defaultValue = "0") String jsonProduct) {

		logger.debug("ServiceController updateServiceCPI ...");
		Service service = null;

		try {
			long t0 = System.currentTimeMillis();

			//Parse JSON
			ObjectReader reader = mapper.readerWithView(Views.CPIView.class);
			reader = reader.forType(Service.class);

			if (jsonProduct==null || jsonProduct.equalsIgnoreCase("0")) {
				service = reader.readValue(jsonBody);
				logger.debug("Update Service, jsonBody : " + jsonBody);
			} else {
				service = reader.readValue(jsonProduct);
				logger.debug("Update Service, jsonProduct :" + jsonProduct);
			}

			//Find Service
			Service existingService = repository.findByServiceId(service.getServiceId());
            String serviceState = existingService.getState();
			if (existingService!=null && !("deployed".equalsIgnoreCase(serviceState.toLowerCase())
                    || "pending deployment".equalsIgnoreCase(serviceState.toLowerCase()))) {

				serviceManager.updateCPIService(existingService,service);

				//finally update the service
				repository.saveAndFlush(existingService);

			} else {
				logger.debug("Service ID not found or service is already deployed/pending deployment. Service id : " + service.getServiceId().toString());
				return new ResponseEntity("Service ID not found or service is already deployed/pending deployment. Service id : " + service.getServiceId().toString(), HttpStatus.OK);
			}
		} catch (JsonParseException e) {
			logger.error("Parse Exception : " + e.getMessage(),e);
			return new ResponseEntity("Parse Exception : ", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (JsonMappingException e) {
			logger.error("Mapping Exception : " + e.getMessage(),e);
			return new ResponseEntity("Mapping Exception : ", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			logger.error("IO Exception : " + e.getMessage(), e);
			return new ResponseEntity("IO Exception : ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		//TODO : Set RatingGroup id instead of the service id.
		//return service.getRatingGroup().getRatingGroupId().toString();
		logger.debug("updateServiceCPI() : serviceId :"+service.getServiceId().toString());
		return new ResponseEntity(service.getServiceId().toString(), HttpStatus.OK);
	}


	// Create a service
	@RequestMapping(value = "/service/cpi", method = RequestMethod.POST)
	public String addServiceCPI(@RequestBody(required = false)String jsonBody, @RequestParam(value = "serviceObject", defaultValue = "0") String jsonProduct) {

		serviceManager.getDefaultService();
		Service service = null;

		logger.debug("ServiceController addServiceCPI");

		try {
			long t0 = System.currentTimeMillis();
			//ObjectReader reader = mapper.readerWithView(Views.CPIView.class).withType(Service.class);
			ObjectReader reader = mapper.readerWithView(Views.CPIView.class);
			reader = reader.forType(Service.class);

			if (jsonProduct==null || jsonProduct.equalsIgnoreCase("0")) {
                logger.debug("Add Service, Json_Body : " + jsonBody);
				service = reader.readValue(jsonBody);

			} else {
                logger.debug("Add Service, Json_Param : " + jsonBody);
				service = reader.readValue(jsonProduct);
			}

			logger.debug("Service json read is "+ service);

			logger.debug(" reading a service json in: " + (System.currentTimeMillis() - t0));

			serviceManager.mapCPIService(service);

			service.setDateCreated(new Date());
			//service.setLastUpdate(new Date());
			service.setRatingGroup(new RatingGroup()); // autoseq next val > 10000

			//FIXME : Filter conditions - Remove if not required.
			if (service.getAllowance() == 9999) {
				service.setAllowance(-1);
			}
			//any service apart from base service need to have higher value than 1.
			if (!service.getName().toLowerCase().contains("baseservice")) {
				service.setPriority(3);
			}

			if (service.getQos()!=null && !service.getQos().isEmpty()) {
				for (QoS qos : service.getQos()) {
					if (qos.getSpeed()!=null && !qos.getSpeed().trim().isEmpty()) {
						qos.setSpeed(qos.getSpeed().toUpperCase());
					}
				}
			}

			repository.saveAndFlush(service);

			logger.debug(" Service created, id=" + service.getServiceId());

			if (service.getState().equals("deployed") && service.getSpoID() != null) {
				serviceManager.createVispService(service);
			}

		} catch (JsonParseException e) {
			logger.error("Parse Exception : " + e.getMessage(),e);
		} catch (JsonMappingException e) {
			logger.error("Mapping Exception : " + e.getMessage(),e);
		} catch (IOException e) {
			logger.error("IO Exception : " + e.getMessage(), e);
		}
		//TODO : Set RatingGroup id instead of the service id.
		//return service.getRatingGroup().getRatingGroupId().toString();
		logger.debug("addServiceCPI() : serviceId :"+service.getServiceId().toString());
		return service.getServiceId().toString();
	}

	/*
	 * Default service is created one time with rating group 3300
	 */

	// List all Service
	@RequestMapping("/services/visp/resend")
	public String resendVispServices() {

		logger.debug("ServiceController resendVispServices");

		List<Service> services = repository.findAll();

		try {

			for ( Service service: services){
				serviceManager.createVispService(service);
				repository.saveAndFlush(service);
			}			
		} catch (Exception e) {
			logger.error("Error in resendVispServices",e.getMessage(),e);
		}
		logger.debug("resendVispServices : Error");
		return "Error ";
	}
	
	@RequestMapping("/services/visp/recreate/{serviceId}")
	public String reCreateVispServices(@PathVariable("serviceId") Long serviceId) {

		logger.debug("ServiceController resendVispServices");

		Service service = repository.findOne(serviceId);
		service.setVispServiceID(null);
		try {
				serviceManager.createVispService(service);
				repository.saveAndFlush(service);
		
		} catch (Exception e) {
			logger.error("Error in resendVispServices",e.getMessage(),e);
		}
		logger.debug("resendVispServices : Error");
		return "Error ";
	}
	

	// List all Service
	@RequestMapping("/services/cpi")
	public String getCPIServices() {

		logger.debug("ServiceController getCPIServices");

		List<Service> services = repository.findAll();

		try {
			ObjectWriter writer = mapper.writerWithView(Views.CPIView.class);
			String jsonService = writer.writeValueAsString(services);
			logger.debug("ServiceController jsonService" + jsonService);
			return jsonService;
		} catch (JsonProcessingException e) {
			logger.error("Error in getCPIServices",e.getMessage(),e);
		}
		logger.debug("getCPIServices() : Error");
		return "Error ";
	}

	// Delete a service
	@RequestMapping(value = "/service", method = RequestMethod.DELETE)
	public ResponseEntity removeProduct(@RequestParam(value = "serviceid", defaultValue = "0") String serviceId) {

		// Service service = repository.findOne(serviceId);
		logger.debug("ServiceController removeProduct() serviceId " + serviceId);
		Service service = null;
		if (serviceId!=null && !serviceId.trim().isEmpty() && serviceId !="0") {
			service = repository.findByServiceId(Long.parseLong(serviceId));
			if (service != null) {
				repository.delete(service);
				logger.debug("removeProduct : Removed service : " + serviceId);
				return new ResponseEntity("Removed service : " + serviceId,HttpStatus.OK);
			} else {
				logger.debug("removeProduct : Service not found :  " + serviceId);
				return new ResponseEntity("Service not found : " + serviceId,HttpStatus.NOT_FOUND);
			}
		}
		logger.debug("Invalid service : " + serviceId);
		return new ResponseEntity("Invalid service : " + serviceId,HttpStatus.BAD_REQUEST);
	}

	// Simulate VISP addService
	@RequestMapping(value = "/service/visp", method = RequestMethod.POST)
	public String vispAddService() {

		return "{ \"vsipSeviceInstanceid\": \"12345\",}";
	}

	// Associate a Rule to a service
	@RequestMapping(value = "/service/rule", method = RequestMethod.PUT)
	public Service addRuleToProduct(@RequestParam(value = "serviceid", defaultValue = "0") String serviceId,
									@RequestParam(value = "ruleid", defaultValue = "0") String ruleId) {

		// Service service = repository.findOne(serviceId);
		// Rule rule = ruleRepository.findOne(ruleId);

		logger.debug("ServiceController addRuleToProduct serviceId : {} , ruleId : {}" , serviceId,ruleId);

		Service service = repository.findByServiceId(Long.valueOf(serviceId));
		Rule rule = ruleRepository.findByRuleId(Long.valueOf(ruleId));

		service.addRule(rule);
		repository.saveAndFlush(service);
		logger.debug("addRuleToProduct : Successfully persisted");
		return service;
	}

	// Deassociate a Rule to a service
	@RequestMapping(value = "/service/rule", method = RequestMethod.DELETE)
	public Service removeRuleFromProduct(@RequestParam(value = "serviceid", defaultValue = "0") String serviceId,
										 @RequestParam(value = "ruleid", defaultValue = "0") String ruleId) {

		// Service service = repository.findOne(serviceId);
		// Rule rule = ruleRepository.findOne(ruleId);

		logger.debug("ServiceController removeRuleFromProduct serviceId " + serviceId);
		logger.debug("ServiceController removeRuleFromProduct ruleId " + ruleId);

		Service service = repository.findByServiceId(Long.valueOf(serviceId));
		Rule rule = ruleRepository.findByRuleId(Long.valueOf(ruleId));

		service.removeRule(rule);
		repository.saveAndFlush(service);
		logger.debug("removeRuleFromProduct : Successfully persisted");
		return service;
	}

	// Create an application
	@RequestMapping(value = "/service/application", method = RequestMethod.POST)
	public Application addApplication(
			@RequestParam(value = "applicationObject", defaultValue = "0") String jsonApplication) {

		logger.debug("ServiceController addApplication , " + jsonApplication);

		Application app = null;
		try {
			long t0 = System.currentTimeMillis();
			mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
			ObjectReader reader = mapper.readerWithView(Views.InternalView.class);
			reader = reader.forType(Application.class);
			app = reader.readValue(jsonApplication);
			app.setServices(new ArrayList<Service>());
			long t1 = System.currentTimeMillis();
			logger.debug(" reading a service json in: " + (t1 - t0));
			Application currentApp = appRepository.findByName(app.getName());
			if (currentApp != null)
				app.setApplicationId(currentApp.getApplicationId());

			app = appRepository.saveAndFlush(app);
			logger.debug("ServiceController addApplication successfully persisted ");
		} catch (JsonParseException e) {
			logger.error("Error in ServiceController --> addApplication()",e.getMessage(),e);
			//e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			logger.error("Error in ServiceController --> addApplication()",e.getMessage(),e);
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Error in ServiceController --> addApplication()",e.getMessage(),e);
			//e.printStackTrace();
		}

		return app;
	}

	// Create an application
	@RequestMapping(value = "/service/qos/create", method = RequestMethod.POST)
	public RefQoS addQoS(
			@RequestParam(value = "qosObject", defaultValue = "0") String jsonQos) {

		logger.debug("ServiceController addQos , " + jsonQos);

		RefQoS qos = null;
		try {
			long t0 = System.currentTimeMillis();
			mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
			ObjectReader reader = mapper.readerWithView(Views.InternalView.class);
			reader = reader.forType(RefQoS.class);
			qos = reader.readValue(jsonQos);
            RefQoS qosTemp = refQosRepository.findByName(qos.getVispName());
            if (qosTemp==null) {
                qosTemp = new RefQoS(qos.getName(),qos.getVispName(),qos.getType());
            }

            qosTemp.setVispName(qos.getVispName());
			qosTemp.setDefaultColorCodeHex(qos.getDefaultColorCodeHex());
            qosTemp.setName(qos.getName());
            qosTemp.setCreateDate(qos.getCreateDate());
            qosTemp.setCreatedBy(qos.getCreatedBy());
            qosTemp.setType(qos.getType());
            refQosRepository.saveAndFlush(qosTemp);
            logger.debug("ServiceController addQos successfully persisted ");
		} catch (JsonParseException e) {
			logger.error("Error in ServiceController --> addQoS()",e.getMessage(),e);
			//e.printStackTrace();
		} catch (JsonMappingException e) {
			logger.error("Error in ServiceController --> addQoS()",e.getMessage(),e);
			//e.printStackTrace();
		} catch (IOException e) {
			logger.error("Error in ServiceController --> addQoS()",e.getMessage(),e);
			//e.printStackTrace();
		}

		return qos;
	}

	// List all Applications
	@RequestMapping("/service/applications")
	public String getApplications() {

		logger.debug("ServiceController getApplications ");

		List<Application> applications = appRepository.findAll();

		ObjectWriter writer = mapper.writerWithView(Views.CPIView.class);
		String jsonProduct = null;
		try {
			jsonProduct = writer.writeValueAsString(applications);
		} catch (JsonProcessingException e) {
			logger.error("Error in ServiceController --> getApplications()",e.getMessage(),e);
			//e.printStackTrace();
		}
		logger.debug("ServiceController getApplications "+jsonProduct);
		return jsonProduct;
		/*
		 * for (Application application : applications) { try { String
		 * jsonInString = mapper.writeValueAsString(application);
		 * System.out.println(jsonInString); } catch (JsonProcessingException e)
		 * { // TODO Auto-generated catch block e.printStackTrace(); } }
		 *
		 * return applications;
		 */
	}

    // List all Applications
    @RequestMapping("/service/qos/list")
    public String getQoS() {

        logger.debug("ServiceController getQoS ");

        Iterable<RefQoS> refQoSIterable = refQosRepository.findAll();

        ObjectWriter writer = mapper.writerWithView(Views.CPIView.class);
        String jsonProduct = null;
        try {
            jsonProduct = writer.writeValueAsString(refQoSIterable);
        } catch (JsonProcessingException e) {
    		logger.error("Error in ServiceController --> getQoS()",e.getMessage(),e);
            //e.printStackTrace();
        }
        logger.debug("ServiceController getQoS "+jsonProduct);
        return jsonProduct;
    }

	@RequestMapping("/")
	public String index() {
		return "This is from ServiceController";
	}

	/**
	 * Persist plab customer information from csv
	 */
	@RequestMapping(value = "/service/invite/{offerId}", method = RequestMethod.POST)
	public ResponseEntity saveInvite(@PathVariable("offerId") Long offerId, @RequestParam(value = "csv") MultipartFile[] csvFiles) {

		logger.debug("ServiceController saveInvites for the offerID : " + offerId);
		int savedCustomers =0;
		int customerDataLength = 0;

		Offer offer = null;
		List<String> invalidCustList = null;
		List<String> enrolledCustList = null;

		if ((offerId == null || offerId <= 0) && csvFiles == null){
			logger.debug("Invalid request. CSV & Offer ID not in request.");
			return new ResponseEntity("Invalid request. CSV & Offer ID not in request.", HttpStatus.BAD_REQUEST);
		} else if (offerId != null){
			offer = offerRepository.findByOfferId(offerId);
			if(offer==null) {
				logger.info("Offer not found ", HttpStatus.NOT_FOUND);
				return new ResponseEntity("Invalid request. Offer doesn't exist in PLAB.", HttpStatus.BAD_REQUEST);
			}
		}

		if(csvFiles!=null && csvFiles.length>0){
			logger.debug("Length of the csvFile "+csvFiles.length);

			invalidCustList = new ArrayList<>();
			enrolledCustList = new ArrayList<>();

			for(int j=0;j<csvFiles.length;j++){

				try {
					long t0 = System.currentTimeMillis();
					byte[] byteArr = csvFiles[j].getBytes();
					String fileData = new String(byteArr);
					String[] custData = fileData.split("\n");
					customerDataLength = custData.length;

					for(int i = 0; i < custData.length; i++){

						String[] fieldVals = custData[i].split(",");
						String customerId = fieldVals[0];
						String acctNum = fieldVals[1];
						String mdn = fieldVals[2];

						if (customerId!=null)
							customerId = customerId.trim();
						if (acctNum!=null)
							acctNum = acctNum.trim();
						if (mdn!=null)
							mdn=mdn.trim();

						boolean enrolled = productManager.customerWithActiveOfferInstance(Long.valueOf(customerId), Long.valueOf(acctNum));
						if(enrolled){
							logger.debug("SaveInvite for : custId : "+Long.valueOf(customerId)+",acctNo : "+ Long.valueOf(acctNum) + "did not work because the customer is actively enrolled. Please terminate before you try again");
							enrolledCustList.add(new StringBuilder(customerId).append("-").append(acctNum).toString());
						}else{
							logger.debug("SaveInvite for : custId : "+Long.valueOf(customerId)+",acctNo : "+ Long.valueOf(acctNum));
							List<SubCustAcctMdn> accountMembers = null;

							//Grid calls updated.
							accountMembers = productManager.getCustomerAccountDetails(SearchStrategy.FIND_ACTIVE_CUSTOMER_DETAILS_FROM_GRID,
									Long.valueOf(customerId), Long.valueOf(acctNum));
							if (accountMembers == null || (accountMembers!=null && accountMembers.isEmpty())) {
								accountMembers = productManager.getCustomerAccountDetails(SearchStrategy.FIND_ACTIVE_CUSTOMER_DETAILS_FROM_DB,
										Long.valueOf(customerId), Long.valueOf(acctNum));
							}

							if(accountMembers!=null && !accountMembers.isEmpty()){
								for (SubCustAcctMdn subCustAcctMdn : accountMembers) {
									productManager.createOrUpdatePricingLabCustomers(Long.parseLong(customerId), Long.parseLong(acctNum),subCustAcctMdn.getMdn(), offer,subCustAcctMdn.getMtnEffDt());
								}
								savedCustomers=savedCustomers+1;
							}
							else{
								logger.debug("Customers not found in subCustAcctMDn cannot be invited");
								invalidCustList.add(new StringBuilder(customerId).append("-").append(acctNum).toString());
								//return new ResponseEntity("Customers not found in subCustAcctMDn cannot be invited", HttpStatus.BAD_REQUEST);
							}
						}
					}

					long t1 = System.currentTimeMillis();
					logger.debug(" Reading csv for invite in: " + (t1 - t0));

				} catch (Exception e) {
					e.printStackTrace();
					logger.error("Exception persisting the csv in plab customer table"+e.getMessage());
					return new ResponseEntity("Exception persisting the csv ", HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}

			if(savedCustomers > 0 && savedCustomers==customerDataLength){
				logger.debug("Saved successfully ");
				return new ResponseEntity("Saved successfully ", HttpStatus.OK);
			} else {
				if (savedCustomers == 0) {
					logger.debug("Customers actively enrolled cannot be re-invited. Please terminate and try again.");
					return new ResponseEntity("Customers actively enrolled cannot be re-invited. Please terminate and try again.", HttpStatus.BAD_REQUEST);
				} else {
					StringBuilder buildMultiStatusMsg = new StringBuilder("Not all of them could be invited. Please note that customers actively enrolled cannot be invited again.Please terminate and try again");
					if (invalidCustList!=null && !invalidCustList.isEmpty()) {
						buildMultiStatusMsg.append(" Accounts not found in database : ").append(Arrays.toString(invalidCustList.toArray())).append(".");
					}
					if (enrolledCustList!=null && !enrolledCustList.isEmpty()) {
						buildMultiStatusMsg.append(" Already Enrolled Accounts  : ").append(Arrays.toString(enrolledCustList.toArray())).append(".");
					}
					logger.error(buildMultiStatusMsg.toString());
					return new ResponseEntity("Accounts were not invited. "+buildMultiStatusMsg.toString(), HttpStatus.MULTI_STATUS);
				}
			}
		} else {
			logger.debug("No file received ");
			return new ResponseEntity("No file received ", HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 * Get all invites for the given offer id
	 * @param offerId
	 * @return
     */
	@RequestMapping(value = "/service/invite/{offerId}", method = RequestMethod.GET)
	public ResponseEntity getInvite(@PathVariable("offerId") Long offerId) {

		logger.debug("ServiceController getInvite for the offerID : " + offerId);

		Offer offer = null;
		if (offerId == null || offerId <= 0) {
			logger.debug("Invalid OfferId ");
			return new ResponseEntity("Invalid OfferId ", HttpStatus.BAD_REQUEST);
		}

		offer = offerRepository.findByOfferId(offerId);

		if (offer == null) {
			logger.debug("offer not found");
			return new ResponseEntity("offer not found ", HttpStatus.NOT_FOUND);
		}

		ObjectMapper writer = new ObjectMapper();

		try {
			String jsonInString = writer.writeValueAsString(offer.getPlabcustomers());
			logger.debug(jsonInString);
			return new ResponseEntity(jsonInString, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("get invite parsing error : " + e.getMessage());
			return new ResponseEntity("Error parsing list to json ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Delete all invites.
	 * @param offerId
	 * @return
     */
	@RequestMapping(value = "/service/invite/{offerId}", method = RequestMethod.DELETE)
	public ResponseEntity deleteInvite(@PathVariable("offerId") Long offerId) {

		logger.debug("deleteInvite() : offerID : {} ",offerId);

		Offer offer = null;
		List<PlabCust> newList = null;

		if (offerId == null || offerId <= 0){
			logger.debug("Invalid OfferId ");
			return new ResponseEntity("Invalid OfferId ", HttpStatus.BAD_REQUEST);
		}

		offer = offerRepository.findByOfferId(offerId);

		if (offer == null){
			logger.debug("offer not found");
			return new ResponseEntity("offer not found ", HttpStatus.NOT_FOUND);
		}


		logger.debug("ServiceController deleteInvite for the offerID : " + offerId);

		newList = plabCustRepository.findByOfferAndIndicator(offerId, new Long[]{-2L, -1L});

		plabCustRepository.delete(newList);
		logger.debug("deleted successfully");
		return new ResponseEntity("deleted successfully", HttpStatus.OK);
	}
	
	/**
	 *Service scheduler
	 * 
	 * @return
     */
	@RequestMapping(value = "/service/schedule", method = RequestMethod.POST)
	public ResponseEntity updateServiceWithVispServiceId() {
		
		logger.debug("ServiceController updateServiceWithVispServiceId called ");

		List<Service> serviceList = repository.findByVispServiceIDIsNull();

		if (serviceList != null){
			for(Service service : serviceList){
				serviceManager.createVispService(service);								
			}
			logger.debug("updated services with visp serviceId successfully");
			return new ResponseEntity("updated services with visp serviceId successfully", HttpStatus.OK);
		}else{
			logger.debug("No service found with vispserviceid=null ");
			return new ResponseEntity("No service found with vispserviceid=null ", HttpStatus.NOT_FOUND);
		}
			
	}
	
	/**
	 *Service scheduler
	 * 
	 * @return
     */
	@RequestMapping(value = "/serviceinstance/schedule", method = RequestMethod.POST)
	public ResponseEntity updateServiceInstanceWithVispServiceId() {
		
		logger.debug("ServiceController updateServiceInstanceWithVispServiceId called ");

		List<ServiceInstance> serviceInstanceList = serviceInstanceRepository.findByVispServiceInstanceIdIsNull();

		if (serviceInstanceList != null){
			for(ServiceInstance serviceInstance : serviceInstanceList){
				serviceManager.addVispServiceToDevice(serviceInstance);								
			}
			logger.debug("updated serviceinstances with visp serviceinstanceId successfully");
			return new ResponseEntity("updated serviceinstances with visp serviceinstanceId successfully", HttpStatus.OK);
		}else{
			logger.debug("No serviceinstance found with vispserviceinstanceid=null ");
			return new ResponseEntity("No serviceinstance found with vispserviceinstanceid=null ", HttpStatus.NOT_FOUND);
		}
			
	}
	
	@RequestMapping(value = "serviceinstance/update/{serviceInstId}/{vispServInstId}", method = RequestMethod.POST)
	public String updateVispServiceId(@PathVariable("serviceInstId") Long serviceInstId,
			@PathVariable("vispServInstId") String vispServInstId) {

		logger.debug("ServiceController updateVispServiceId called with serviceInstId : {} , vispServInstId : {} ", serviceInstId, vispServInstId);
		
		ServiceInstance serviceInstance = serviceInstanceRepository.findOne(serviceInstId);

		if (serviceInstance == null) {
			logger.debug("Service Instance Not Found ! for serviceInstId : {} " , serviceInstId);
			return "Service Instance Not Found !";
		}

		serviceInstance.setVispServiceInstanceId(vispServInstId);

		serviceInstanceRepository.saveAndFlush(serviceInstance);

		logger.debug("Successfully updated  serviceInstId : {} with vispServInstId : {} ", serviceInstId, vispServInstId);
		
		return "Success";
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "throttle/{custid}/{acctnbr}", method = RequestMethod.POST)
	public ResponseEntity throttleService(@PathVariable("custid") Long customerID,
			@PathVariable("acctnbr") Long accountNumber) {
		logger.debug("throttleService() : customerID : {} , accountNumber : {}  ",customerID,accountNumber);
		return updateThrottling(customerID, accountNumber, true);

	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "unthrottle/{custid}/{acctnbr}", method = RequestMethod.POST)
	public ResponseEntity unThrottleService(@PathVariable("custid") Long customerID,
			@PathVariable("acctnbr") Long accountNumber) {
		logger.debug("unThrottleService() : customerID : {}, accountNumber : {}  ",customerID,accountNumber);
		return updateThrottling(customerID, accountNumber, false);

	}

	/**
	 * 
	 * 
	 * @param customerID
	 * @param accountNumber
	 * @param throttle
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResponseEntity updateThrottling(Long customerID, Long accountNumber, boolean throttle) {

		logger.debug("updateThrottling() : customerID : {} , accountNumber : {} , throttle : {} ", customerID,
				accountNumber, throttle);

		if(!isSafetyModeEnabled){
			return new ResponseEntity("Safety Mode changes not enabled", HttpStatus.NOT_IMPLEMENTED);
		}
		String expressionValue = null;
		if (throttle) {
			expressionValue = PlabConstants.RULE_EXPRESSION_SAFETY_MODE;
		} else {
			expressionValue = PlabConstants.RULE_EXPRESSION_NORMAL_MODE;
		}

		try {
			Long[] indicator = { PlabConstants.ENROLLED };
			// Get offer Id
			Long offerId = plabCustRepository.findOfferIdByCustIdNoAndAcctNoAndIndicator(customerID, accountNumber,
					indicator);
			
			if (offerId == null) {
				logger.debug("Customer not found / active for Customer id : {} , accountNumber : {} ", customerID,
						accountNumber);
				return new ResponseEntity("Customer not found / active !", HttpStatus.NOT_FOUND);
			}
			// Get offer instances for current offer for this customer
			List<OfferInstance> instanceList = offerInstanceRepository.findOfferInstancesByCustomerAccount(offerId,
					customerID, accountNumber);

			if (instanceList == null || instanceList.isEmpty()) {
				logger.debug("Customer not found / active for Customer id : {} , accountNumber : {} ", customerID,
						accountNumber);
				return new ResponseEntity("Customer not found / active !", HttpStatus.NOT_FOUND);
			}
			
			Rule speedRule = null;
			logger.debug("Updating speed to : {} , for Customer id : {} , accountNumber : {} ", expressionValue,
					customerID, accountNumber);
			// Get Service Instances
			boolean hasSameQOSForAllLines = true;

			for (OfferInstance offerInstance : instanceList) {
				List<ServiceInstance> serviceInstanceList = serviceInstanceRepository
						.findLatestByOfferInstance(offerInstance.getOfferInstanceId());
				for (ServiceInstance serviceInstance : serviceInstanceList) {
					String serviceType = serviceManager.getServiceType(serviceInstance.getService());
					if ("BaseService".equals(serviceType)) {
						List<RuleInstance> ruleInstances = serviceInstance.getRules();

						// Get the rule for the current base service instance
						List<Rule> serviceRules = serviceInstance.getService().getRules();

						// Get QOS rule for base service
						for (Rule rule : serviceRules) {
							if (rule.getRuleType().equals(Rule.qosRuleType)
									&& rule.getExpressionType().equals(Rule.bySpeedExpressionType)) {
								speedRule = rule;
								logger.debug(
										"Found QOS by Speed Rule for Base Service with rule Id : {} , for Customer id : {} , "
												+ ", accountNumber : {} ",
										rule.getRuleId(), customerID, accountNumber);
								break;
							}
						}

						// If No matching service rule found, create a rule -
						// Typically done only once with the first call
						if (speedRule == null) {
							logger.debug(
									"QOS by Speed Rule for Base Service not found for Customer id : {} , "
											+ "for Customer id , accountNumber : {} , Creating a new rule ...",
									customerID, accountNumber);
							speedRule = createBaseServiceQOSRule(serviceInstance);
						}

						// Find QOS Rule Instance
						RuleInstance baseQOSRuleInstance = null;
						if (ruleInstances != null && !ruleInstances.isEmpty()) {
							for (RuleInstance rule : ruleInstances) {
								if (rule.getRuleType().equals(Rule.qosRuleType)
										&& rule.getExpressionType().equals(Rule.bySpeedExpressionType)) {
									logger.debug(
											"Found QOS by Speed Rule instance for Base Service with "
													+ "RuleInstance Id : {} with expression : {} for Service InstanceId : {} , for Customer id : {} , "
													+ " accountNumber : {} ",
											rule.getRuleInstanceId(), rule.getExpression(),
											serviceInstance.getServiceInstanceId(), customerID, accountNumber);
									baseQOSRuleInstance = rule;
									break;
								}
							}
						}

						if (baseQOSRuleInstance == null) {
							logger.debug(
									"QOS by Speed Rule instance not found for Base Service for Service InstanceId : {} , for Customer id : {} , "
											+ " accountNumber : {} , adding a new Rule Instance... ",
									serviceInstance.getServiceInstanceId(), customerID, accountNumber);
							RuleInstance ruleInstance = new RuleInstance(speedRule, serviceInstance);
							ruleInstance.setEndDate(PricingLabUtility.getDefaultEndTimeStamp());
							ruleInstance.setExpression(expressionValue);
							serviceInstance.getRules().add(ruleInstance);
							hasSameQOSForAllLines = false;
						} else {
							// If the request is for the same type of update,
							// then don't make changes
							if (expressionValue.equalsIgnoreCase(baseQOSRuleInstance.getExpression())) {
								continue;
							} else {
								hasSameQOSForAllLines = false;
								baseQOSRuleInstance.setExpression(expressionValue);
							}
						}
						
						// 1. Send update to VISP
						logger.debug(
								"Updating VISP for Service InstanceId : {} , Customer id : {} , accountNumber : {} ",
								serviceInstance.getServiceInstanceId(), customerID, accountNumber);
						serviceManager.updateVispServiceToDevice(serviceInstance);

						// 2. Save in PLAB DB.
						logger.debug(
								"Updating PLAB with VISP Service InstanceId : {} for ServiceInstanceId : {} , Customer id : {} , accountNumber : {} ",
								serviceInstance.getVispServiceInstanceId(), serviceInstance.getServiceInstanceId(), customerID, accountNumber);
						serviceInstanceRepository.saveAndFlush(serviceInstance);

						logger.debug(
								"Successfully updated PLAB and VISP speed to {} for Service InstanceId : {} , Customer id : {} , accountNumber : {} ",
								expressionValue, serviceInstance.getServiceInstanceId(), customerID, accountNumber);
					} else if(!"NightSurfer".equals(serviceType)){
						logger.debug("Safetymode is not supported for service : {} for Customer id : {} , accountNumber : {} ", serviceType,
								customerID, accountNumber);
						return new ResponseEntity("Safetymode is not supported for service : " + serviceType, HttpStatus.NOT_ACCEPTABLE);
					}
				}
			}
			
			if (hasSameQOSForAllLines) {
				logger.debug(
						"All MDNs for the Customer are already on : {} for Customer id : {} , "
								+ "accountNumber : {} , So no changes were made !",
						expressionValue, customerID, accountNumber);
				return new ResponseEntity(
						"All MDNs for the Customer are already on " + expressionValue + " , So no changes were made !",
						HttpStatus.OK);
			}

			// 2. Send MQ Message to AGS Queue
			sendAgsDTLMessage(customerID, accountNumber, throttle);

		} catch (Exception e) {
			logger.error("Exception in method updateThrottling for customerID : " + customerID + " accountNumber : "
					+ accountNumber, e);
			return new ResponseEntity("Failed to Update customer speed !", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.debug("Updated customer speed successfully to :" + expressionValue);
		return new ResponseEntity("Updated customer speed successfully to : " + expressionValue, HttpStatus.OK);
	}

	/**
	 * Creates QOS rule for Base Service
	 * 
	 * @param serviceInstance
	 * @return
	 */
	private Rule createBaseServiceQOSRule(ServiceInstance serviceInstance) {
		logger.debug("createBaseServiceQOSRule() : " + serviceInstance.getServiceInstanceId());
		Rule speedRule = new Rule();
		speedRule.setCreatedBy(PlabConstants.DEFAULT_CREATED_BY_USER);
		speedRule.setDateCreated(new Date());
		speedRule.setRuleType(Rule.qosRuleType);
		speedRule.setName(PlabConstants.BASE_QOS_RULE_NAME);
		speedRule.setExpressionType(Rule.bySpeedExpressionType);
		speedRule.setExpression(PlabConstants.RULE_EXPRESSION_NORMAL_MODE);
		speedRule.setService(serviceInstance.getService());
		speedRule = ruleRepository.saveAndFlush(speedRule);
		logger.debug("New QOS Rule created successfully with Id : {}  for Service id : {} ", speedRule.getRuleId(),
				serviceInstance.getService().getServiceId());
		return speedRule;
	}
	
	public void sendAgsDTLMessage(Long customerID, Long accountNumber, boolean throttle){
		String transactionId = UUID.randomUUID().toString();
		logger.debug("Sending DTL Message, for Customer id : {} , accountNumber : {} ", customerID, accountNumber);
		jmsSender.sendAgsDTLMessage(getAgsDTLMessage(customerID, accountNumber, throttle, transactionId),
				transactionId);
	}
	
	public String getAgsDTLMessage(Long customerID, Long accountNumber, boolean throttle, String transactionId) {

		String _0_transactionId = "PLAB:" + transactionId;;
		Math.random();
		String _1_applicationName = "#APNPLB";

		// Format -> 20 chars
		// (9 chars - custId)-(9 chars accountNumber with 0 padded left)' '
		// (followed by a space)
		String _2_custId = customerID + "-" + StringUtils.leftPad(accountNumber + "", 9, "0") + " ";
		String _3_cos = "ALPG1DTL";
		String _4_event;
		if (throttle) {
			_4_event = "DTLON  ";
		} else {
			_4_event = "DTLOFF ";
		}

		StringBuilder epcohTime = new StringBuilder(new Date().getTime() + "");
		epcohTime.insert(10, "T");
		String _5_epochTime = epcohTime.toString();
		String message = MessageFormat.format("000001MSGID={0}{1}ACT{2}COS{3}       CIDGBYEVT{4}TIM{5}UNIBYTLNGENG",
				_0_transactionId, _1_applicationName, _2_custId, _3_cos, _4_event, _5_epochTime);

		return message;
	}
	
	/**
	 * Checks if all the MDNS associated to the customer is on SafetyMode or not.
	 * @param customerID
	 * @param accountNumber
	 * @return
	 */
	public boolean isCustomerAccountOnSafetyMode(Long customerID, Long accountNumber) {
		
		boolean allMdnsOnSafetyMode = true;
		
		try{
		List<OfferInstance> instanceList = offerInstanceRepository.findOfferInstancesByCustomer(customerID,
				accountNumber);

		if (instanceList == null || instanceList.isEmpty()) {
			return false;
		}

		

		for (OfferInstance offerInstance : instanceList) {
			List<ServiceInstance> serviceInstanceList = serviceInstanceRepository
					.findLatestByOfferInstance(offerInstance.getOfferInstanceId());
			for (ServiceInstance serviceInstance : serviceInstanceList) {
				String serviceType = serviceManager.getServiceType(serviceInstance.getService());
				if ("BaseService".equals(serviceType)) {
					List<RuleInstance> ruleInstances = serviceInstance.getRules();
					boolean qosBySpeedFound = false;
					if (ruleInstances != null && !ruleInstances.isEmpty()) {
						for (RuleInstance rule : ruleInstances) {
							if (rule.getRuleType().equals(Rule.qosRuleType)
									&& rule.getExpressionType().equals(Rule.bySpeedExpressionType)) {
								qosBySpeedFound = true;
								if (!"SAFETYMODE".equalsIgnoreCase(rule.getExpression())) {
									logger.debug(
											"Found Rule Instance with NORMALMODE for Base Service with ruleInstanceId : {} , serviceInstance : {} , for Customer id : {} , "
													+ ", accountNumber : {} ",
											rule.getRuleInstanceId(), serviceInstance.getServiceInstanceId(),
											customerID, accountNumber);
									allMdnsOnSafetyMode = false;
									break;
								}
							}
						}
						if (!qosBySpeedFound) {
							logger.debug(
									"QOS by Speed Rule not found for Base Service with serviceInstance : {} , for Customer id : {} , "
											+ ", accountNumber : {} ",
									serviceInstance.getServiceInstanceId(), customerID, accountNumber);
							allMdnsOnSafetyMode = false;
							break;
						}
					} else {
						logger.debug(
								"No Rule Instances found for serviceInstance : {} , for Customer id : {} , "
										+ ", accountNumber : {} ",
								serviceInstance.getServiceInstanceId(), customerID, accountNumber);
						allMdnsOnSafetyMode = false;
						break;
					}

				}
			}
		}
		}catch (Exception e) {
			logger.debug(
					"Exception in isCustomerAccountOnSafetyMode for Customer id : {} , "
							+ ", accountNumber : {} ",
					customerID, accountNumber, e);
			return false;
		}

		return allMdnsOnSafetyMode;

	}
	
		
}
