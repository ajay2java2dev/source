package com.vzwcorp.pricinglab.rest;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vzwcorp.pricinglab.vo.VerizonEntity;
import com.vzwcorp.pricinglab.vo.Views;

public class CreateEntity {

	public static void main(String[] args) {
		createMDNEntity();
	}

	private static void createMDNEntity() {
		VerizonEntity entity= new VerizonEntity();
		entity.setMdn("123456");
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter writer= mapper.writerWithView(Views.MobileFirstView.class);
		String jsonEntity;
		try {
			jsonEntity = mapper.writeValueAsString(entity);
			System.out.println(jsonEntity);

			Map<String, String> params = new HashMap<String, String>();
			params.put("entityObject", jsonEntity);
			// Invoke VISP web service to create the service
			System.out.println(RestClient.postRequest("http://localhost:8080/entity", params));			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	}
	
	
}
