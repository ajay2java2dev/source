package com.vzwcorp.pricinglab.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import com.vzwcorp.pricinglab.vo.*;

import java.math.BigDecimal;
import java.util.*;

public class CreateProduct {
	public static void main(String[] args) {
		createSocialPlanProduct("amr");
	}

	static String HostUrl=CreateOffers.HostUrl;
	private static void createSocialPlanProduct(String userName) {
		try {
			Service service = createSocialPlanService(userName);

			
			ObjectMapper mapper = new ObjectMapper();
			ObjectWriter writer= mapper.writerWithView(Views.VispView.class);
			String jsonProduct= writer.writeValueAsString(service);
			//String jsonProduct = mapper.writeValueAsString(service);
			System.out.println(jsonProduct);

			Map<String, String> params = new HashMap<String, String>();
			params.put("serviceObject", jsonProduct);
			// Invoke VISP web service to create the service
			RestClient.postRequest(HostUrl+"//service", params);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Service createSocialPlanService(String userName) {
		Service service = new Service();
		service.setAllowance(-1); // Unlimited
		service.setName("social plan offer- social Apps");
		service.setPriority(10);
		service.setCharge(0);
		service.setState("deployed");
		service.setSpoID("SPO 100");
		service.setUserName(userName);		
		//service.setLastUpdate(Calendar.getInstance().getTime());
			
		Set <Application> apps= new HashSet<Application>();
		apps.add(new Application("Facebook","FB"));
		apps.add(new Application("Twitter","TW"));
		apps.add(new Application("Snap Chat","SNAPCHAT"));
		apps.add(new Application("Netflix","NETFLIX"));
		service.setMaxNumberOfApps(2);
		service.setAddOn(true);
		service.setAppsClassification(apps);
		
		
/*		Rule usageRule2 = new Rule("classification",Rule.classficationRuleType, Rule.byTimeExpressionType, "between 6PM and 6AM");
		
		usageRule2.addAttribute("multiplier", 1);
		service.addRule(usageRule2);
		
		// a VISP System rule to periodically update JITR
		Rule periodicUpdateRule = new Rule("periodic-update",Rule.reportingRuleType, Rule.byVolumeExpressionType, "32");
		periodicUpdateRule.addAttribute("hostURL", HostUrl+"//service"); // JITR Webserver  URL
		periodicUpdateRule.addAttribute("eventType", Event.PeriodicUpdate);
		periodicUpdateRule.addAttribute("unit", "MBytes");
		service.addRule(periodicUpdateRule);

		Rule periodicUpdateRule1 = new Rule("periodic-update",Rule.reportingRuleType, Rule.byTimeExpressionType, "every 10 minutes");
		periodicUpdateRule1.addAttribute("hostURL", "http:\\localhost:8080//service"); // JITR Webserver  URL
		periodicUpdateRule1.addAttribute("eventType", Event.PeriodicUpdate);
		service.addRule(periodicUpdateRule1);
		
		Rule usageRule3= new Rule("qos",Rule.qosRuleType, Rule.byTimeExpressionType, "between 6PM and 6AM");
		usageRule3.addAttribute("bandwidth", 5000000);
		service.addRule(usageRule3);*/
		
		return service;
	}

	public static Service createSocialPlan2GService(String userName) {
		Service service = new Service();
		service.setAllowance(2000000000); // Unlimited
		service.setName("social plan offer- 2G");
		service.setPriority(1);
		service.setCharge(50);
		service.setUserName(userName);		
		//service.setLastUpdate(Calendar.getInstance().getTime());
		service.setAddOn(false);		
		service.setState("deployed");
		service.setSpoID("SPO 200");
		
		return service;
	}	
	
	public static Service createNightSurferService(String userName) {
		Service service = new Service();
		service.setAllowance(-1); // Unlimited
		service.setName("NightSurferPlan");
		service.setPriority(10);
		service.setCharge(10);
		service.setUserName(userName);		
		//service.setLastUpdate(Calendar.getInstance().getTime());
		service.setAddOn(true);
		Set <TimeFrame> times= new HashSet<TimeFrame>();
		times.add(new TimeFrame( "14:45:00 EST", "15:10:00 EST"));
		service.setTimeClassification(times);
		service.setState("deployed");
		service.setSpoID("SPO 300");
			
		return service;
	}	

	public static Service createSpeedTierService(String name, int bandwidth, int charge, String userName,String offerId) {
		Service service = new Service();
		service.setAllowance(-1); // Unlimited
		service.setName(name);
		service.setCharge(charge);
		service.setUserName(userName);
		//service.setLastUpdate(Calendar.getInstance().getTime());
		service.setAddOn(false);
		service.setState("deployed");
		service.setSpoID("180099999");
		service.setCreatedBy(PlabConstants.DEFAULT_CREATED_BY_CPI);
		service.setDateCreated(new Date());
		Offer offer = new Offer();
		offer.setOfferId(Long.parseLong(offerId));
		service.setOffer(offer);

		Set <QoS> qos= new HashSet<QoS>();
        qos.add(new QoS("FAST","FAST",new BigDecimal(1), "",""));
        qos.add(new QoS("FASTER","FASTER",new BigDecimal(2), "",""));
        qos.add(new QoS("FASTEST","FASTEST",new BigDecimal(3), "",""));
		service.setQos(qos);
		
		return service;
	}		
	
	public static void saveService(Service service) {
		try{
			ObjectMapper mapper = new ObjectMapper();
			ObjectWriter writer= mapper.writerWithView(Views.CPIView.class);
			String jsonProduct= writer.writeValueAsString(service);
			System.out.println(jsonProduct);
	
			Map<String, String> params = new HashMap<String, String>();
			params.put("serviceObject", jsonProduct);
			String serviceID= RestClient.postRequest(HostUrl+"//service/cpi", params);
			System.out.println("serviceID: "+ serviceID);
			service.setServiceId(Long.parseLong(serviceID));
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
}
