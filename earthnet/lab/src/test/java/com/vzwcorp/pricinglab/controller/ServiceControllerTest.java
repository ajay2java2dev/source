package com.vzwcorp.pricinglab.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vzwcorp.pricinglab.PricingLabApplication;
import com.vzwcorp.pricinglab.config.DbConfig;
import com.vzwcorp.pricinglab.rest.controller.VerizonEntityController;
import com.vzwcorp.pricinglab.vo.Application;
import com.vzwcorp.pricinglab.vo.Offer;
import com.vzwcorp.pricinglab.vo.Service;
import com.vzwcorp.pricinglab.vo.SimplePage;
import com.vzwcorp.pricinglab.vo.VerizonEntity;
import com.vzwcorp.pricinglab.vo.Views;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;

@RunWith(SpringJUnit4ClassRunner.class) // 1
@SpringApplicationConfiguration(classes = PricingLabApplication.class) // 2
@WebAppConfiguration // 3
@IntegrationTest("server.port:0") // 4

public class ServiceControllerTest {

	@Value("${local.server.port}")
	private int port;

	private URL base;
	private RestTemplate template;

	static Logger logger = LogManager.getLogger(ServiceControllerTest.class);
	
//	 @Test
//	 public void testApplications() throws Exception {
//		 
//		 this.base = new URL("http://localhost:" + port + "/service/applications");
//		 template = new TestRestTemplate();
//		
//		 ResponseEntity<Application> response =
//		 template.getForEntity(base.toString(), Application.class);
//		
//		 logger.debug("====================" + response.getBody().getVispName());
//	 }
	
//	@Test
//	 public void testApplications() throws Exception {
//		 
//		 this.base = new URL("http://localhost:" + port + "/service/9");
//		 template = new TestRestTemplate();
//		
//		 ResponseEntity<Service> response =
//		 template.getForEntity(base.toString(), Service.class);
//		
//		 logger.debug("====================" + response.getBody());
//	 }
	
//	@Test
//	 public void testGetService() throws Exception {
//		 
//		 this.base = new URL("http://localhost:" + port + "/service/5");
//		 template = new TestRestTemplate();
//		
//		 ResponseEntity<Service> response =
//		 template.getForEntity(base.toString(), Service.class);
//		
//		 logger.debug("====================" + response.getBody());
//	 }
	
	
//	@Test
//	 public void testGetServiceBySpoID() throws Exception {
//		 
//		 this.base = new URL("http://localhost:" + port + "/service/spoId/SPO 100");
//		 template = new TestRestTemplate();
//		
//		 ResponseEntity<Service> response =
//		 template.getForEntity(base.toString(), Service.class);
//		
//		 logger.debug("====================" + response.getBody());
//	 }
	
	
//	@Test
//	 public void testGetServices() throws Exception {
//		 
//		 this.base = new URL("http://localhost:" + port + "/services");
//		 template = new TestRestTemplate();
//		
//		 ResponseEntity<Service> response =
//		 template.getForEntity(base.toString(), Service.class);
//		
//		 logger.debug("====================" + response.getBody());
//	 }
	
	
//	@Test
//	 public void testAddService() throws Exception {
//		 
//		 this.base = new URL("http://localhost:" + port + "/addService");
//		 template = new TestRestTemplate();
//		
//		 ResponseEntity<Service> response =
//		 template.getForEntity(base.toString(), Service.class);
//		
//		 logger.debug("====================" + response.getBody());
//	 }
	
	
//	@Test
//	 public void testAddServiceCPI() throws Exception {
//		 
//		 this.base = new URL("http://localhost:" + port + "/service/cpi");
//		 template = new TestRestTemplate();
//		
//		 ResponseEntity<Service> response =
//		 template.getForEntity(base.toString(), Service.class);
//		
//		 logger.debug("====================" + response.getBody());
//	 }
	
//	@Test
//	 public void testGetCPIServices() throws Exception {
//		 
//		 this.base = new URL("http://localhost:" + port + "/services/cpi");
//		 template = new TestRestTemplate();
//		
//		 ResponseEntity<Service> response =
//		 template.getForEntity(base.toString(), Service.class);
//		
//		 logger.debug("====================" + response.getBody());
//	 }
	
//	@Test
//	 public void testGetApplications() throws Exception {
//		 
//		 this.base = new URL("http://localhost:" + port + "/service/applications");
//		 template = new TestRestTemplate();
//		
//		 ResponseEntity<Service> response =
//		 template.getForEntity(base.toString(), Service.class);
//		
//		 logger.debug("====================" + response.getBody());
//	 }
	
	
//	@Test
//	 public void testAddApplication() throws Exception {
//		 
//		 this.base = new URL("http://localhost:" + port + "/service/application");
//		 template = new TestRestTemplate();
//		
//		 ResponseEntity<Service> response =
//		 template.getForEntity(base.toString(), Service.class);
//		
//		 logger.debug("====================" + response.getBody());
//	 }
	
	
	@Test
	 public void testAddRuleToProduct() throws Exception {
		 
		 this.base = new URL("http://localhost:" + port + "/service/rule");
		 template = new TestRestTemplate();
		
		 ResponseEntity<Service> response =
		 template.getForEntity(base.toString(), Service.class);
		
		 logger.debug("====================" + response.getBody());
	 }
	
	
}
