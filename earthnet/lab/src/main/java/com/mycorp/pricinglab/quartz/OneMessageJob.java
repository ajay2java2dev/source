/**
 * 
 */
package com.vzwcorp.pricinglab.quartz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vzwcorp.pricinglab.config.SpringContext;
import com.vzwcorp.pricinglab.service.OneMessageService;

/**
 * @author kovelde
 *
 */
@Service
@Transactional
public class OneMessageJob implements Job {
	static Logger logger = LogManager.getLogger(OneMessageJob.class);

	OneMessageService oneMessageService;

	public void execute(JobExecutionContext context) throws JobExecutionException {

		oneMessageService = SpringContext.getApplicationContext().getBean(OneMessageService.class);
		JobDataMap dataMap = context.getMergedJobDataMap();
		logger.debug("*************************** ONE MESSAGE JOB INVOKED ******************");
		String messageType = dataMap.getString("messageType");
		Long accountNumber = Long.valueOf(dataMap.getString("custAccountNumber"));
		Long customerID = Long.valueOf(dataMap.getString("customerID"));
		String mdn = dataMap.getString("primaryMdn");
		String enrolledMdnList = dataMap.getString("mdnList");
		try {
			logger.debug("Executing OneMessageJob : {} , for  customerID : {} ,  " + "MDN : {} , messageType : {} ",
					context.getJobDetail().getKey().getName(), customerID, mdn, messageType);

			oneMessageService.sendMessage(messageType, customerID, accountNumber, enrolledMdnList, mdn);

			logger.debug("Successfully executed OneMessageJob : {} , for  customerID : {} ,  " + "MDN : {} , messageType : {} ",
					context.getJobDetail().getKey().getName(), customerID, mdn, messageType);
		} catch (Exception e) {
			logger.error("Exception while executing OneMessageJob : {} , for  customerID : {} ,  " + "MDN : {} , messageType : {} ",
					context.getJobDetail().getKey().getName(), customerID, mdn, messageType, e);

		}
		logger.debug("*************************** ONE MESSAGE JOB COMPLETED ******************");

	}

}
