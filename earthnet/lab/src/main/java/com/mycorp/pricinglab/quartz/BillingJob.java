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
import com.vzwcorp.pricinglab.rest.controller.OfferController;

@Service
@Transactional
public class BillingJob implements Job {

	static Logger logger = LogManager.getLogger(BillingJob.class);

	OfferController offerController;

	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.debug("*************************** BILLING JOB INVOKED ******************");
		offerController = SpringContext.getApplicationContext().getBean(OfferController.class);

		JobDataMap dataMap = context.getMergedJobDataMap();

		String billJobType = dataMap.getString("billingJobType");
		String mdn = dataMap.getString("mdn");
		String offerId = dataMap.getString("offerId");
		String month = dataMap.getString("billMonth");
		String year = dataMap.getString("billYear");

		logger.debug(
				"Executing customer billing  JOB : {} , for MDN : {} , offerId : {} "
						+ "billJobType : {} , month : {} , year : {} " , context.getJobDetail().getKey().getName(),
				mdn, offerId, billJobType, month, year);

		if ("Billing".equals(billJobType)) {
			executeCustBillingCycle(mdn, month, year);
		} else if ("Terminate".equals(billJobType)) {
			terminateCustomer(Long.valueOf(offerId), mdn, false);
		} else if ("EarlyTerminate".equals(billJobType)) {
			terminateCustomer(Long.valueOf(offerId), mdn, true);
		}
		logger.debug("*************************** BILLING JOB COMPLETED ******************");
	}

	/**
	 * Execute or call the actual billing cycle logic here.
	 * 
	 * @param year
	 * @param month
	 **/
	public void executeCustBillingCycle(String mdn, String month, String year) {
		try {
			if (mdn != null) {
				logger.debug("Quartz BillingJob: Executing customer billing cycle. MDN : {} " , mdn);

				offerController.customerBilling(mdn, month, year, false);

				logger.debug("Quartz BillingJob : Executed customer billing cycle for MDN : {} " , mdn);

			} else {
				logger.error("Quartz BillingJob Executing customer billing failed for mdn : {} " , mdn);
			}
		} catch (Exception ex) {
			logger.error("Quartz BillingJob : Executing customer billing failed for mdn : " + mdn, ex);
		}
	}

	public void terminateCustomer(Long offerId, String mdn, boolean earlyTerminate) {
		try {
			logger.debug("Quartz BillingJob : Executing customer Termination Job for  MDN :  {} " , mdn
					+ " ,  for Offer Id : " + offerId);

			offerController.removeCustomerFromPilot(offerId, mdn, earlyTerminate);

			logger.debug("Quartz BillingJob : Executed customer Termination Job for  MDN : {} " , mdn
					+ " ,  for Offer Id : " + offerId);
		} catch (Exception ex) {
			logger.error("Quartz BillingJob : Executing customer billing failed for  MDN :  " + mdn
					+ " ,  for Offer Id : " + offerId, ex);
		}
	}
}
