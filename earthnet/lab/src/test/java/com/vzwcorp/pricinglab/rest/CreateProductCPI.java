package com.vzwcorp.pricinglab.rest;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vzwcorp.pricinglab.vo.*;

import java.math.BigDecimal;
import java.util.*;


public class CreateProductCPI {
	static ObjectMapper mapper = new ObjectMapper();
	
	public static void main(String[] args) {
		Service service= createInitSocialPlanService();
	//	updateService(service,"draft");
		service.setSpoID("spod id 123");
		updateService(service,"deployed");
	}
	
private static void updateServiceToDeployed(Service service) {
		// TODO Auto-generated method stub
		
	}

private static void updateService(Service service, String state) {
		try{
			service.setState(state);
			ObjectWriter writer= mapper.writerWithView(Views.CPIView.class);
			String jsonProduct= writer.writeValueAsString(service);
			System.out.println(jsonProduct);
	
			Map<String, String> params = new HashMap<String, String>();
			params.put("serviceObject", jsonProduct);
			// Invoke VISP web service to create the service
			String serviceID= RestClient.postRequest(HostUrl+"//service/cpi", params);
			System.out.println("serviceID: "+ serviceID);
			service.setServiceId(Long.parseLong(serviceID));
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}

	static String HostUrl=CreateOffers.HostUrl;
//	static String HostUrl="http://localhost:8080";

	private static Service createInitSocialPlanService() {
		try {
			Service service = createSocialPlanServiceObject();
			ObjectWriter writer= mapper.writerWithView(Views.CPIView.class);
			String jsonProduct= writer.writeValueAsString(service);
			System.out.println(jsonProduct);

			Map<String, String> params = new HashMap<String, String>();
			params.put("serviceObject", jsonProduct);
			// Invoke VISP web service to create the service
			String serviceID= RestClient.postRequest(HostUrl+"//service/cpi", params);
			System.out.println("serviceID: "+ serviceID);
			service.setServiceId(Long.parseLong(serviceID));
			return service;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static List<Service> listServices() {
		try {
			
			String listJson= RestClient.getRequest(HostUrl+"//services/cpi");
			System.out.println(listJson);
			ObjectReader reader= mapper.readerWithView(Views.CPIView.class).withType(Service.class);
			MappingIterator services= (MappingIterator) reader.readValues(listJson);
			while ( services.hasNext()){
				Service service= (Service) services.next();
			}
			System.out.println(listJson);
			return (List<Service>) services;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}	

	public static Service createSocialPlanServiceObject() {
		Service service = new Service();
		service.setAllowance(-1); // Unlimited
		service.setName("night surfer");
		service.setCharge(30);
		service.setState("initial");
		service.setPriority(3);
		service.setUserName("amr");		
		//service.setLastUpdate(Calendar.getInstance().getTime());
		Set <Application> apps= new HashSet<Application>();
		apps.add(new Application("Facebook","FB"));
		apps.add(new Application("Twitter","TW"));
		apps.add(new Application("Snap Chat","SNAPCHAT"));
		service.setAppsClassification(apps);
		//service.setCharge(50);
		
		Set <TimeFrame> times= new HashSet<TimeFrame>();
		times.add(new TimeFrame( "18:00:00 GMT", "06:00:00 GMT"));
		service.setTimeClassification(times);

		Set <QoS> qos= new HashSet<QoS>();
		qos.add(new QoS("512 Mpbs","SPEED TIER1",new BigDecimal(1), "7 AM", " 7 PM"));
		service.setQos(qos);
		
		return service;
	}

	

}
