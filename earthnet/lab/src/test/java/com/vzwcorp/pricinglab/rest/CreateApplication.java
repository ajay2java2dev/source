package com.vzwcorp.pricinglab.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import com.vzwcorp.pricinglab.vo.Application;
import com.vzwcorp.pricinglab.vo.Views;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CreateApplication {
	public static void main(String[] args) {
		createApplications();		
		getApplications();

	}

	public static void createApplications() {
		createApplication("Facebook","FB");
		createApplication("Twitter","TWT");
		createApplication("Snap Chat","SNAPCHAT");		
		createApplication("Amazon","AMAZON");
		createApplication("YouTube","YOUTUBE");
		createApplication("Pinterest","PINTEREST");
		createApplication("Tumbler","TUMBLR");
		createApplication("Netflix","NETFLIX");
	}

	private static void createApplication(String applicationName, String vispName) {
		try {
			Application application = new Application( applicationName, vispName);
			application.setDateCreated(new Date());
			application.setCreatedBy(PlabConstants.DEFAULT_CREATED_BY_ADMIN);

			ObjectMapper mapper = new ObjectMapper();
			ObjectWriter writer= mapper.writerWithView(Views.InternalView.class);
			String jsonProduct= writer.writeValueAsString(application);
			
			Map<String, String> params = new HashMap<String, String>();
			params.put("applicationObject", jsonProduct);			
			String s= RestClient.postRequest(CreateOffers.HostUrl+"/service/application", params);
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	private static void getApplications() {
		try {
			String s= RestClient.getRequest(CreateOffers.HostUrl+"/service/applications");
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
