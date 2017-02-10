package com.vzwcorp.pricinglab.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import com.vzwcorp.pricinglab.helper.ServiceHelper;
import com.vzwcorp.pricinglab.utility.PricingLabUtility;
import com.vzwcorp.pricinglab.vo.*;
import com.vzwcorp.pricinglab.vo.repository.*;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URI;
import java.util.*;

//import org.springframework.stereotype.Service;

@org.springframework.stereotype.Service
@Configuration
public class ServiceManager {

	static Logger logger = LogManager.getLogger(ServiceManager.class);


	@Value("${baseService.suffix}")
	String baseServiceSuffix;

	@Autowired
	RatingGroupRepository ratingGroupRepository;

	@Value("${visp.url}")
	String vispURL;

	@Value("${plab.url}")
	String plabURL;

	@Autowired
	ServiceRepository repository;

	@Autowired
	ApplicationRepository appRepository;

	@Autowired
	TimeFrameRepository timeFrameRepository;

	@Autowired
	QOSRepository qosRepository;

	@Autowired
	RefQoSRepository refQoSRepository;

	@Autowired
	ServiceHelper helper;

	@Autowired
	PricingLabUtility pricingLabUtility;
	
	/**
	 * Update CPI Service
	 * @param existingService
	 * @param newService
     */
	public void updateCPIService (Service existingService,Service newService) {

		if (existingService!=null && newService!=null) {

			//Update basic service properties - CPI Content
			helper.createOrUpdateService (existingService,newService);
			repository.saveAndFlush(existingService);

			//Update Service Question for QoS.
			helper.createOrUpdatedServiceQuestionForQoS(existingService,newService);
			repository.saveAndFlush(existingService);

			//Update Service Question for Timeframes
			helper.createOrUpdatedServiceQuestionForTimeframe(existingService,newService);
			repository.saveAndFlush(existingService);

			if (existingService.getTimeClassification()!=null && !existingService.getTimeClassification().isEmpty()) {
				//only add this if there exists a time classification.
				addDefaultOptions(existingService); // for night surfer add default options too.
			}

			//Update Service Question for Apps
			helper.createOrUpdatedServiceQuestionForApps(existingService,newService);
			repository.saveAndFlush(existingService);

			//Finally add the default rules.
			boolean isReportingRuleTypeExists = false;
			if (existingService.getRules()!=null) {
				for (Rule rule : existingService.getRules()) {
					//check if periodic rule exists.
					if (Rule.reportingRuleType.equalsIgnoreCase(rule.getRuleType())) {
						isReportingRuleTypeExists = true;
						break;
					}
				}
			}
			if (!isReportingRuleTypeExists) {
				addDefaultRules(existingService);
			}

		} else {
			logger.error("Service not updated. Service JSON or existing service doesn't exist.");
		}
	}

	/**
	 * Helper method for Service Manager -> addServiceCPI()
	 * CPI to send JSON to the
	 * @param service
	 */
	public void mapCPIService(Service service) {

		logger.debug("ServiceManager  mapCPIService");

		Set<Application> oldApplications = service.getAppsClassification();
		Set<Application> applications = oldApplications;
		if (oldApplications != null) {
			applications = new HashSet<Application>();
			for (Application app : oldApplications) {
				Application newApp = appRepository.findByName(app.getName());

				applications.add(newApp);
			}
			service.setAppsClassification(applications);
		}

		logger.debug("ServiceManager  mapCPIService service.setAppsClassification(applications) finished");

		try {
			if (applications != null) {
				String s = "";
				int i = 0;
				for (Application app : applications)
					if (i++ < service.getMaxNumberOfApps())
						s += app.getVispName() + " , ";

				if (s != null && !s.equals("")) {
					int y = s.lastIndexOf(",");
					s = s.substring(0, y - 1);
				} else {
					s = "";
				}

				if (s!=null && !s.trim().isEmpty()) {
					Rule rule = new Rule("classification", Rule.classficationRuleType, Rule.byApplicationExpressionType, s);
					rule.setDateCreated(new Date());
					service.addRule(rule);
				}

				logger.debug("ServiceManager  mapCPIService service.addRule");

				ServiceQuestion question = new ServiceQuestion();
				question.setMaxSelectedOptions(service.getMaxNumberOfApps());
				question.setDateCreated(new Date());

				List<Choice> options = new ArrayList<Choice>();

				for (Application app : applications) {
					Choice o = new Choice();
					o.setTitle(app.getName());
					o.setSelected(false);
					o.setServiceQuestion(question);
					options.add(o);//
				}

				if (options != null && !options.isEmpty()) {
					question.setChoice(options);
					question.setSelectionType("Dropdown");
					question.setService(service);
					Set<ServiceQuestion> uiSelections = service.getOptions();
					if (uiSelections == null)
						uiSelections = new HashSet<ServiceQuestion>();
					uiSelections.add(question);
					service.setOptions(uiSelections);
				}

				logger.debug("ServiceManager  mapCPIService service.setOptions finished");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		//Time Frames
		Set<TimeFrame> timeFrames = service.getTimeClassification();

		if (timeFrames != null) {
		    //adding default options
            addDefaultOptions(service);

			for (TimeFrame t : timeFrames) {
				t.setDateCreated(new Date());
				t = timeFrameRepository.saveAndFlush(t);
			}

			for (TimeFrame timeFrame : timeFrames) {
				Rule rule = new Rule("classification", Rule.classficationRuleType, Rule.byTimeExpressionType,
						"between " + timeFrame.getFrom() + " and " + timeFrame.getTo());
				rule.setDateCreated(new Date());
				service.addRule(rule);
			}
		}

		Set<QoS> qoss = service.getQos();

		if (qoss != null) {
			for (QoS s : qoss)
				qosRepository.saveAndFlush(s);
			ServiceQuestion question = new ServiceQuestion();
			question.setMaxSelectedOptions(1);
			question.setDateCreated(new Date());
			List<Choice> options = new ArrayList<Choice>();
			int h=0;
			for (QoS qos : qoss) {

				RefQoS qosRef = refQoSRepository.findByName(qos.getName());

				Rule rule = null;

				//Choice
				Choice o = new Choice();
				o.setTitle(qos.getName());
				if (h==0) {
					o.setSelected(true);
				} else {
					o.setSelected(false);
				}
				o.setColorCode(qosRef.getDefaultColorCodeHex());
				o.setServiceQuestion(question);
				options.add(o);


				if ( h==0 ) {
					if (qos.getTimeFrame() == null || qos.getTimeFrame().getFrom() == null) {
						rule = new Rule(qos.getName(), Rule.qosRuleType, Rule.byTimeExpressionType,
								"between 00:00:00 EST and 23:59:59 EST");
					} else {
						rule = new Rule(qos.getName(), Rule.qosRuleType, Rule.byTimeExpressionType,
								"between " + qos.getTimeFrame().getFrom() + " and " + qos.getTimeFrame().getTo());
					}

					//TODO : Check bandwidth is FAST/FASTER/FASTEST
					if (qos.getSpeed()!=null && !qos.getSpeed().trim().isEmpty()) {
						rule.addAttribute("bandwidth", qos.getSpeed().toUpperCase());
					}
					rule.setDateCreated(new Date());
					service.addRule(rule);
				}
				h++;
			}


			if (options!=null && !options.isEmpty()) {
				question.setChoice(options);
				//question.setSelectionType("Dropdown");
				//if selection type isnt specified in the request json then selection type defaults to slider
				String selectionType =null;
				if(service.getOptions()!=null)
				 selectionType = service.getOptions().iterator().next().getSelectionType();
				if(selectionType!=null && !selectionType.isEmpty())
					question.setSelectionType(selectionType);
				else
					question.setSelectionType(PlabConstants.SPEED_SELECTION_TYPE);
				question.setService(service);
				Set<ServiceQuestion> uiSelections = service.getOptions();
				if (uiSelections == null)
					uiSelections = new HashSet<ServiceQuestion>();
				uiSelections.add(question);
				service.setOptions(uiSelections);
			}

		}

		addDefaultRules(service); //Add Reporting Rules

		for (Rule rule : service.getRules()) {
			rule.setDateCreated(new Date());
			rule.setService(service);
		}
		return;

	}

    /**
     * add default options.
     * @param service
     */
	public void addDefaultOptions (Service service) {
        if (service!=null) {
        	boolean isDefaultOptionsExists = false;

			Set<ServiceQuestion> serviceQuestions = service.getOptions();
			if (serviceQuestions != null && !serviceQuestions.isEmpty()) {
				for (ServiceQuestion serviceQuestion : serviceQuestions) {
					if ("Toggle".equalsIgnoreCase(serviceQuestion.getTitle())
							&& "ToggleButton".equalsIgnoreCase(serviceQuestion.getSelectionType())) {
						isDefaultOptionsExists = true;
						break;
					}
				}
			}

			if (!isDefaultOptionsExists) {
				ServiceQuestion question = new ServiceQuestion();
				question.setDateCreated(new Date());
				List<Choice> choices = new ArrayList<>();

				Choice choice = new Choice();
				choice.setSelected(false);
				choice.setServiceQuestion(question);
				choice.setTitle("Toggle");
				choices.add(choice);

				question.setSelectionType("ToggleButton");
				question.setChoice(choices);
				question.setService(service);

				Set<ServiceQuestion> uiSelections = service.getOptions();
				if (uiSelections == null)
					uiSelections = new HashSet<ServiceQuestion>();
				uiSelections.add(question);

				service.setOptions(uiSelections);
			}
        }
    }

    /**
     * Default service to a new service
     * @param service
     */
	public void addDefaultRules(Service service) {
		service.setHostURL(plabURL + "/usage");
		Rule periodicUpdateRule = new Rule("periodic-update", Rule.reportingRuleType, Rule.byVolumeExpressionType,"32000000");
		//periodicUpdateRule.addAttribute("hostURL", plabURL + "/usage"); // JITR
		//periodicUpdateRule.addAttribute("eventType", Event.PeriodicUpdate); //commented after discussion with amr.
		periodicUpdateRule.addAttribute("unit", "Bytes");
		//periodicUpdateRule.addAttribute("multiplier", "1"); // Not required in this & to be part of Rules
		//periodicUpdateRule.addAttribute("measurment-method", "");
		periodicUpdateRule.setService(service);
		periodicUpdateRule.setDateCreated(new Date());
		service.addRule(periodicUpdateRule);
		return;
	}

	public void mapServiceToCPI(Service service) {
		// TODO Auto-generated method stub

	}

	public String getServiceType (Service service){
		//Get Service Types as per name
		if ( service.getName().toLowerCase().contains("baseservice"))
			return "BaseService";
		if ( service.getName().toLowerCase().contains("surfer"))
			return "NightSurfer";
		if ( service.getName().toLowerCase().contains("speed"))
			return "SpeedTier";

		//or Get Service Types as per rule
		List<Rule> rules = service.getRules();
		for (Rule rule : rules) {
			if (rule.getRuleType().equals(Rule.qosRuleType) && (rule.getExpressionType().equals(Rule.bySpeedExpressionType) || rule.getExpressionType().equals(Rule.byTimeExpressionType))) {
				return "SpeedTier";
			} else if (rule.getRuleType().equals(Rule.classficationRuleType) && rule.getExpressionType().equals(Rule.byTimeExpressionType)) {
				return "NightSurfer";
			}
		}
		return "";
	}

	public int createVispService(Service service) {

		try {

			service.setServiceType(getServiceType(service));
			//Filter default rule set for VISP
			List<Rule> vispRuleSet = new ArrayList<Rule>();

			for (Rule rule : service.getRules()) {
				//Update Speed Tier rule expression type and expression
				if (Rule.qosRuleType.equalsIgnoreCase(rule.getRuleType()) &&
						rule.getExpressionType()!=null && Rule.byTimeExpressionType.equalsIgnoreCase(rule.getExpressionType())
						&& service.getName()!=null && service.getName().toLowerCase().contains("speed")) {
					rule.setExpressionType(Rule.bySpeedExpressionType);
					String bandwidth = rule.getAttributes().get("bandwidth");
					rule.setExpression(bandwidth!=null?bandwidth.toUpperCase():null);
				}

				if (!Rule.qosEOCRuleType.equalsIgnoreCase(rule.getRuleType())) {
					vispRuleSet.add(rule);
				}
			}

			service.setRules(vispRuleSet);

			ObjectMapper mapper = new ObjectMapper();
			ObjectWriter writer = mapper.writerWithView(Views.VispView.class);
			String jsonProduct = writer.writeValueAsString(service);
			// String jsonProduct = mapper.writeValueAsString(service);
			logger.debug(jsonProduct);

			Map<String, String> params = new HashMap<String, String>();
			params.put("serviceObject", jsonProduct);
			// Invoke VISP web service to create the service
			logger.debug("Sending JSON object to VISP: (" +vispURL+")  "+  jsonProduct);
			//String response = RestClient.postRequest(vispURL+"/service/create", jsonProduct);
			String response = pricingLabUtility.postRequest(vispURL+"/service/create", jsonProduct,ContentType.APPLICATION_JSON);
			// {"Service-correlation-id":"f6c626ca-f04c-4b62-8e10-80e0b74ecf40"}
			int x = 0;
			for (int i = 0; i < 3; i++) {
				x = response.indexOf('"', x);
				x++;
			}

			int y = response.indexOf('"', x);
			logger.debug("Received ServiceID from VISP: " + response.substring(x, y));

			service.setVispServiceID(response.substring(x, y));
		} catch (Exception e) {
			logger.error("Exception in createVispService () : ", e.getMessage(), e);
			return -1;
		}
		return 0;
	}

	public int addVispServiceToDevice(ServiceInstance serviceInstance) {

		serviceInstance.getService().setServiceType(getServiceType(serviceInstance.service));
		try {
			ObjectMapper mapper = new ObjectMapper();
			ObjectWriter writer = mapper.writerWithView(Views.VispView.class);

            if (serviceInstance!=null) {
                if ("VISP_9999".equalsIgnoreCase(serviceInstance.getVispServiceInstanceId())) {
                    serviceInstance.setVispServiceInstanceId(null);
                }
            }

            ServiceInstance newServiceInstance = suppressClassificationRuleTypeForNS(serviceInstance);
			String jsonProduct = writer.writeValueAsString(newServiceInstance);
			// String jsonProduct = mapper.writeValueAsString(service);
			logger.debug(jsonProduct);

			Map<String, String> params = new HashMap<String, String>();
			params.put("serviceObject", jsonProduct);
			// Invoke VISP web service to create the service
			logger.debug("addServiceToDevice for mdn : "+serviceInstance.getVerizonEntity().getMdn()+" - sending JSON object to VISP: (" +vispURL+")  "+  jsonProduct);
			//String response = RestClient.postRequest(vispURL+"/service/mdn/"+ serviceInstance.getVerizonEntity().getMdn(), jsonProduct);
			String response = pricingLabUtility.postRequest(vispURL+"/service/mdn/"+ serviceInstance.getVerizonEntity().getMdn(), jsonProduct,ContentType.APPLICATION_JSON);
            if (response==null || response.isEmpty()) {
                serviceInstance.setVispServiceInstanceId("VISP_9999");
            }
			logger.debug("addServiceToDevice VISP response : "+ response);
			// {"Service-correlation-id":"f6c626ca-f04c-4b62-8e10-80e0b74ecf40"}
			int x = 0;
			if (response!=null && !response.isEmpty()) {
				if(response.startsWith("Error") || response.contains("Error")){
					serviceInstance.setVispServiceInstanceId("VISP_9999");
				}else{
					for (int i = 0; i < 3; i++) {
						x = response.indexOf('"', x);
						x++;
					}

					int y = response.indexOf('"', x);
					logger.debug("Received Service Instance ID from VISP : " + response.substring(x, y));
					serviceInstance.setVispServiceInstanceId(response.substring(x, y));
				}
			}
		} catch (Exception e) {
			serviceInstance.setVispServiceInstanceId(Long.toString(serviceInstance.getServiceInstanceId()));
			return -1;
		}
		return 0;
	}

	public int updateVispServiceToDevice(ServiceInstance serviceInstance) {

		try {
            if (serviceInstance != null) {
                if (serviceInstance.getVispServiceInstanceId() == null || serviceInstance.getVispServiceInstanceId().isEmpty()
                        || "VISP_9999".equalsIgnoreCase(serviceInstance.getVispServiceInstanceId())
                        ) {
                    logger.debug("removeServiceFromDevice call not made. VISP Service Instance is still Empty or is set to Default(VISP_9999).");
                    return -1;
                }
            }
			serviceInstance.getService().setServiceType(getServiceType(serviceInstance.service));
			ObjectMapper mapper = new ObjectMapper();
			ObjectWriter writer = mapper.writerWithView(Views.VispView.class);
			ServiceInstance newServiceInstance = suppressClassificationRuleTypeForNS(serviceInstance);
			String jsonProduct = writer.writeValueAsString(newServiceInstance);
			logger.trace(jsonProduct);

			Map<String, String> params = new HashMap<String, String>();
			params.put("serviceObject", jsonProduct);

			// Invoke VISP web service to create the service
			logger.debug("Request -> updateVispServiceToDevice : "+serviceInstance.getVerizonEntity().getMdn()+" - sending JSON object to VISP: (" +vispURL+")  "+  jsonProduct);
			//String response = RestClient.postRequest(vispURL+"/service/mdn/"+ serviceInstance.getVerizonEntity().getMdn(), jsonProduct);
			String response = pricingLabUtility.postRequest(vispURL+"/service/mdn/"+ serviceInstance.getVerizonEntity().getMdn(), jsonProduct,ContentType.APPLICATION_JSON);
			logger.debug("Response : "+ response);

			int x = 0;
			if(response!=null && !response.isEmpty()){
				if(response.startsWith("Error") || response.contains("Error")){
					//serviceInstance.setVispServiceInstanceId("VISP_9999"); //retain the original dont set it to visp_9999
				}else{
					for (int i = 0; i < 3; i++) {
						x = response.indexOf('"', x);
						x++;
					}

					int y = response.indexOf('"', x);
					logger.debug("Received ServiceID from VISP: " + response.substring(x, y));

					serviceInstance.setVispServiceInstanceId(response.substring(x, y));
				}
			}
		} catch (Exception e) {
			e.getMessage();
			//logger.error("Exception in updateVispServiceToDevice() ",e.getMessage(),e);
			return 0;
		}
		return 0;
	}

/*	public int removeServiceFromDevice(ServiceInstance serviceInstance) {
		try {
			serviceInstance.getService().setServiceType(getServiceType(serviceInstance.service));
			Map<String, String> params = new HashMap<String, String>();
			params.put("vispSeviceInstanceid", serviceInstance.getVispServiceInstanceId());
			logger.debug("removeServiceFromDevice for mdn : "+serviceInstance.getVerizonEntity().getMdn()+" - sending JSON object to VISP: (" +vispURL+")  "+  serviceInstance +"  "+ serviceInstance.getVispServiceInstanceId());
			String response = RestClient.deleteRequest(vispURL+"/service/mdn/"+ serviceInstance.getVerizonEntity().getMdn(), params);
			logger.debug("removeServiceToDevice VISP response: "+ response);
			logger.debug("revceived ServiceID from VISP: " + response);

		} catch (Exception e) {
			//serviceInstance.setVispServiceInstanceId(Long.toString(serviceInstance.getServiceInstanceId()));
			logger.error("Exception in updateVispServiceToDevice() ",e.getMessage(),e);
			return -1;
		}
		return 0;		
	}*/
	
	public int removeServiceFromDevice(ServiceInstance serviceInstance) {
		try {
			if (serviceInstance != null) {
				if (serviceInstance.getVispServiceInstanceId() == null || serviceInstance.getVispServiceInstanceId().isEmpty()
						|| "VISP_9999".equalsIgnoreCase(serviceInstance.getVispServiceInstanceId())
						) {
					logger.debug("removeServiceFromDevice call not made. VISP Service Instance is still Empty or is set to Default(VISP_9999).");
					return -1;
				}
			}
			serviceInstance.getService().setServiceType(getServiceType(serviceInstance.service));			
			logger.debug("removeServiceFromDevice for mdn : "+serviceInstance.getVerizonEntity().getMdn()+" - sending JSON object to VISP: (" +vispURL+")  "+  serviceInstance +"  "+ serviceInstance.getVispServiceInstanceId());
			pricingLabUtility.deleteRequest(vispURL+"/service/mdn/"+serviceInstance.getVerizonEntity().getMdn(),"{   \n\"vispServiceInstanceId\": \""+serviceInstance.getVispServiceInstanceId()+"\" \n} ",ContentType.APPLICATION_JSON);

		} catch (Exception e) {
			//serviceInstance.setVispServiceInstanceId(Long.toString(serviceInstance.getServiceInstanceId()));
			logger.error("Exception in removeServiceFromDevice() ",e.getMessage(),e);
			return -1;
		}
		return 0;		
	}
	
	public class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {
	    public static final String METHOD_NAME = "DELETE";
	 
	    public String getMethod() {
	    
	        return METHOD_NAME;
	    }
	 
	    public HttpDeleteWithBody( String uri) {
	        super();
	        setURI(URI.create(uri));
	    }
	 
	    public HttpDeleteWithBody(final URI uri) {
	        super();
	        setURI(uri);
	    }
	 
	    public HttpDeleteWithBody() {
	        super();
	    }
	}

	public Service getDefaultService() {

		RatingGroup r= new RatingGroup();
		r.setRatingGroupId((long) 3300);

		Service defaultService= repository.findByRatingGroup(r);

		if ( defaultService == null){

			RatingGroup r1 = ratingGroupRepository.saveAndFlush(r);

			defaultService= new Service();
			defaultService.setDateCreated(new Date());
			//defaultService.setLastUpdate(new Date());
			defaultService.setAddOn(false);
			defaultService.setAllowance(Long.MAX_VALUE-100);
			defaultService.setName("BaseService"+baseServiceSuffix);
			defaultService.setPriority(1);
			defaultService.setRatingGroup(r1);

			addDefaultRules(defaultService);
			repository.saveAndFlush(defaultService);

			createVispService(defaultService);
		}
		return defaultService;

	}
	
    public ServiceInstance suppressClassificationRuleTypeForNS(ServiceInstance serviceInstance){
    	if(serviceInstance!=null){
    		if(serviceInstance.getServiceType().equalsIgnoreCase(PlabConstants.NIGHT_SURFER_SERVICE_TYPE)){
    			
    			logger.debug("In suppressClassificationRuleTypeForNS()");
    			
    			ServiceInstance newServiceInstance = new ServiceInstance();
    			newServiceInstance.setAllowance(serviceInstance.getAllowance());
    			newServiceInstance.setVispServiceID(serviceInstance.getVispServiceID());
    			newServiceInstance.setPriority(serviceInstance.getPriority());
    			newServiceInstance.setVispServiceInstanceId(serviceInstance.getVispServiceInstanceId());   	
    			newServiceInstance.setService(serviceInstance.getService());
    			List<RuleInstance> ruleInstanceList = 	serviceInstance.getRules();
    			List<RuleInstance> newRuleInstanceList = new ArrayList<>();
    			if(ruleInstanceList!=null && !ruleInstanceList.isEmpty()){
    				for(RuleInstance ruleInstance : ruleInstanceList){
    					if(!(ruleInstance.getRuleType().equalsIgnoreCase(Rule.classficationRuleType) && ruleInstance.getExpressionType().equalsIgnoreCase(Rule.byTimeExpressionType))){
    						newRuleInstanceList.add(ruleInstance);
    					}
    				}
    				newServiceInstance.setRules(newRuleInstanceList);
    				return newServiceInstance;
    			}
    			else{
    				logger.error("suppressClassificationRuleTypeForNS() : Returning original serviceInstance with id : "+serviceInstance.getServiceInstanceId()+" since it doesnt contain rule instances");
    				 return serviceInstance;
    			}
    		}
    		else{
    			logger.debug("suppressClassificationRuleTypeForNS() : Returning original serviceInstance since serviceInstance with id : "+serviceInstance.getServiceInstanceId()+" isn't night surfer");
    			return serviceInstance;
    		}
    	}
    	else{
    		logger.error("suppressClassificationRuleTypeForNS() : ServiceInstance is null");
    		return null;
    	}
    }

}
