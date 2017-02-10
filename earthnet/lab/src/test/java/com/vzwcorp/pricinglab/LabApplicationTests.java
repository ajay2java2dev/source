package com.vzwcorp.pricinglab;

import com.vzwcorp.pricinglab.helper.ProductHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PricingLabApplication.class)
public class PricingLabApplicationTests {

	static Logger logger = LogManager.getLogger(PricingLabApplicationTests.class);
	@Autowired
	ProductHelper helper;


	@Test
	public void contextLoads() {

	}

	@Test
	public void testHelperMethods () {
		//product helper...
		String timeZone = helper.getCustomerTimeZone(988600643L);
		assert (timeZone!=null);
		logger.debug("Timezone for customer id {}:  {}",988600643L,timeZone);

		//service helper...
	}

	@Test
	public void testUtilityMethods () {

	}

	@Test
	public void testServiceInstances () {

	}

}
