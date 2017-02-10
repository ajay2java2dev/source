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
import com.vzwcorp.pricinglab.vo.RuleInstance;
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

public class RuleInstanceControllerTest {
	@Value("${local.server.port}")
	private int port;

	private URL base;
	private RestTemplate template;

	static Logger logger = LogManager.getLogger(OffercontrollerTest.class);

	@Test
	public void testGetRule() throws Exception {

		this.base = new URL("http://localhost:" + port + "/ruleinstance?id=0");
		template = new TestRestTemplate();

		ResponseEntity<RuleInstance> response = template.getForEntity(base.toString(), RuleInstance.class);

		// logger.info(response.getBody());
	}
}
