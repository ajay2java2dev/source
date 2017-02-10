package com.vzwcorp.pricinglab.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.vzwcorp.pricinglab.PricingLabApplication;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PricingLabApplication.class)
@WebAppConfiguration
public class OffercontrollerTest {

	static Logger logger = LogManager.getLogger(OffercontrollerTest.class);

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
    @Autowired
    Scheduler jobScheduler;

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	//@Test
//	public void testOffers() throws Exception {
//		MvcResult result = mockMvc.perform(post("/offers")).andReturn();
//
//		String content = result.getResponse().getContentAsString();
//
//		logger.info(content);
//	}
	
	@Test
	public void test(){
		try {
			String custJobKey = "281435397_1_6173204600";
			List<String> custJobNames = new ArrayList<String>();
			for (String jobGroupName : jobScheduler.getJobGroupNames()) {
				GroupMatcher<JobKey> groupMatcher = GroupMatcher.groupEquals(jobGroupName);
				for (JobKey jobKey : jobScheduler.getJobKeys(groupMatcher)) {
					if(jobKey.getName().contains(custJobKey)){
						custJobNames.add(jobKey.getName());
					}
				}
			}
			System.out.println(custJobNames);
		} catch (SchedulerException e) {

		}
	}
}
