package com.vzwcorp.pricinglab.controller;

import com.vzwcorp.pricinglab.PricingLabApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

@RunWith(SpringJUnit4ClassRunner.class) // 1
@SpringApplicationConfiguration(classes = PricingLabApplication.class) // 2
@WebAppConfiguration // 3
@IntegrationTest("server.port:0") // 4

public class VerizonEntityControllerTest {

	@Value("${local.server.port}")
	private int port;

	private URL base;
	private RestTemplate template;

	static Logger logger = LogManager.getLogger(VerizonEntityControllerTest.class);

	// fetch=FetchType.EAGER, cascade = CascadeType.ALL

	// @Test
	// public void testentity() throws Exception {
	//
	// this.base = new URL("http://localhost:" + port + "/entity?id=2");
	// template = new TestRestTemplate();
	//
	// ResponseEntity<VerizonEntity> response =
	// template.getForEntity(base.toString(), VerizonEntity.class);
	//
	// logger.debug("====================" + response.getBody().getMdn());
	//
	// }

//	 @Test
//	 public void testentities() throws Exception {
//	
//	 this.base = new URL("http://localhost:" + port + "/entities");
//	 template = new TestRestTemplate();
//	
//	 ResponseEntity<VerizonEntity> response =
//	 template.getForEntity(base.toString(), VerizonEntity.class);
//	
//	 logger.debug("====================" + response.getBody().getMdn());
//	
//	 }

//
//	@Test
//	public void testentity() throws Exception {
//
//		this.base = new URL("http://localhost:" + port + "/entity");
//		template = new TestRestTemplate();
//
//		VerizonEntity verizonentity = new VerizonEntity();
//		verizonentity.setAccount("bb");
//		verizonentity.setCustomer("aa");
//
//		RestTemplate rest = new TestRestTemplate();
//
//		ResponseEntity<VerizonEntity> response = rest.postForEntity("http://localhost:" + port + "/entity", verizonentity, VerizonEntity.class,Collections.EMPTY_MAP);
//
//		logger.debug("====================" + response.getBody());
//
//	}
	
	
//	 @Test
//	 public void testRemoveentities() throws Exception {
//	
//	 this.base = new URL("http://localhost:" + port + "/removeentity?entityid=21");
//	 template = new TestRestTemplate();
//	
//	 ResponseEntity<VerizonEntity> response =
//	 template.getForEntity(base.toString(), VerizonEntity.class);
//	
//	 logger.debug("====================" + response.getBody().getMdn());
//	
//	 }
}
