/**
 * 
 */
package com.vzwcorp.pricinglab.quartz;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vzwcorp.pricinglab.constants.OneMessageType;
import com.vzwcorp.pricinglab.loader.profile.ubsr.repository.SubCustBlCycleRepository;
import com.vzwcorp.pricinglab.profile.vo.SubCustBlCycle;
import com.vzwcorp.pricinglab.service.GridService;
import com.vzwcorp.pricinglab.service.ServiceManager;
import com.vzwcorp.pricinglab.vo.PlabCust;

/**
 * @author kovelde
 *
 */

@Service
public class PLabJobScheduler {

	public static String ONE_MESSAGE_TYPE_WELCOME = "ONE_MESSAGE_TYPE_WELCOME";
	public static String ONE_MESSAGE_TYPE_EXP7DAYS = "ONE_MESSAGE_TYPE_EXP7DAYS";
	public static String ONE_MESSAGE_TYPE_EXP1DAY = "ONE_MESSAGE_TYPE_EXP1DAY";
	public static String ONE_MESSAGE_TYPE_EXP = "ONE_MESSAGE_TYPE_EXP";
	public static String ONE_MESSAGE_TYPE_EARLYTERM = "ONE_MESSAGE_TYPE_EARLYTERM";
	static Logger logger = LogManager.getLogger(PLabJobScheduler.class);

	@Value("${job.simulate}")
	private Boolean simulateJobs;

	@Value("${billing.job.hour:23}")
	private Integer billingJobHour;

	@Value("${billing.job.minute:0}")
	private Integer billingJobMinute;

	@Value("${billing.eoc.job.minute:59}")
	private Integer billingEOCJobMinute;

	@Value("${onemessage.job.hour:0}")
	private Integer oneMessageJobHour;

	@Value("${onemessage.job.minute:1}")
	private Integer oneMessageJobMinute;

	@Autowired
	Scheduler jobScheduler;

	@Autowired
	SubCustBlCycleRepository subCustBlCycleRepository;

	@Autowired
	ServiceManager serviceManager;

	@Autowired
	GridService gridService;

	/**
	 * Creates the required jobs for customer
	 * 
	 * BillDay is the end day of the customer bill cycle, on that day billing
	 * should be generated
	 * 
	 * 1. If the bill cycle no =10 
	 * 	a. Bill cycle start date = Oct 11 
	 * 	b. Bill Cycle end date = Nov 10 [bill cycle cut off for usage is NOV 10th 23:59:59]
	 * 
	 * @param cust
	 * @throws Exception
	 */
	public void createScheduledJobsForCustomer(PlabCust cust, List<String> enrolledMdns) {

		if (cust == null) {
			logger.error("Customer is null, Not creating jobs !");
			return;
		}

		String billCycleDay = gridService.getCustomerBillCycle(cust.getCustIdNo(), cust.getAcctNo());

		logger.debug("createScheduledJobsForCustomer : custId : {}, acctNo : {} , MDN {} , billCycleDay : {} ",
				cust.getCustIdNo(), cust.getAcctNo(), cust.getMdn(), billCycleDay);
		
		/*
		 * SubCustBlCycle subCustBlCycle =
		 * subCustBlCycleRepository.findActiveBlCycleForCustomer(cust.
		 * getCustIdNo()); if(subCustBlCycle == null ||
		 * subCustBlCycle.getBlCycNo() == null ||
		 * subCustBlCycle.getBlCycNo().equals("0")){ logger.
		 * error("No BillCycle details found, so not creating the jobs for custID : "
		 * + cust.getCustIdNo()); return; } billCycleDay=
		 * subCustBlCycle.getBlCycNo();
		 */

		int billDay = Integer.parseInt(billCycleDay);
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		int today = cal.get(Calendar.DAY_OF_MONTH);
		int billingHour = billingJobHour;
		int messageHour = oneMessageJobHour;

		if (today > billDay) {
			++month;
		}

		try {
			createBillingJob(cust, 1, month, billDay, billingHour, billingJobMinute);
			createBillingJob(cust, 2, ++month, billDay, billingHour, billingJobMinute);
			createBillingJob(cust, 3, ++month, billDay, billingHour, billingJobMinute);
			// Do not create extra job if the offer is accepted on first day of
			// the bill cycle
			if (today != (billDay + 1)) {
				createBillingJob(cust, 4, ++month, billDay, billingHour, billingJobMinute);
			}

			createOneMessageJob(cust, enrolledMdns, 1, month, billDay - 6, messageHour, oneMessageJobMinute,
					ONE_MESSAGE_TYPE_EXP7DAYS);
			createOneMessageJob(cust, enrolledMdns, 2, month, billDay, messageHour, oneMessageJobMinute,
					ONE_MESSAGE_TYPE_EXP1DAY);

			// Job No: 5 is for terminate
			createBillingJob(cust, 5, month, billDay, billingHour, billingEOCJobMinute);

		} catch (Exception e) {
			logger.error("Exception while creating the jobs for custID : " + cust.getCustIdNo(), e);
		}
	}

	public void createEarlyTerminationJobs(PlabCust cust) {

		try {

			String billCycleDay = gridService.getCustomerBillCycle(cust.getCustIdNo(), cust.getAcctNo());

			logger.debug("createEarlyTerminationJobs : custId : {}, acctNo : {} , MDN {} , billCycleDay : {} ",
					cust.getCustIdNo(), cust.getAcctNo(), cust.getMdn(), billCycleDay);

			/*
			 * SubCustBlCycle subCustBlCycle =
			 * subCustBlCycleRepository.findActiveBlCycleForCustomer(cust.
			 * getCustIdNo()); if(subCustBlCycle == null ||
			 * subCustBlCycle.getBlCycNo() == null ||
			 * subCustBlCycle.getBlCycNo().equals("0")){ logger.
			 * error("No BillCycle details found, so not creating the jobs for custID : "
			 * + cust.getCustIdNo()); return; } billCycleDay=
			 * subCustBlCycle.getBlCycNo();
			 */

			int billDay = Integer.parseInt(billCycleDay);
			Calendar cal = Calendar.getInstance();
			int month = cal.get(Calendar.MONTH);
			int today = cal.get(Calendar.DAY_OF_MONTH);
			int billingHour = billingJobHour;

			// Delete all existing jobs
			deleteJobsForCustomer(cust);

			if (today > billDay) {
				++month;
			}
			// Create EOC Terminate Job
			// Job No: 6 is for early terminate
			createBillingJob(cust, 6, month, billDay, billingHour, billingEOCJobMinute);

		} catch (Exception e) {
			logger.error("Exception in createEarlyTerminationJobs  for custID : " + cust.getCustIdNo(), e);
		}

	}

	public void deleteJobsForCustomer(PlabCust cust) {
		if (cust == null) {
			logger.debug("deleteJobsForCustomer called with cust as null , nothing to delete");
			return;
		}
		String custJobKey = cust.getCustIdNo() + "_" + cust.getAcctNo() + "_" + cust.getMdn();
		try {

			List<JobKey> deleteJobsList = new ArrayList<JobKey>();
			StringBuilder jobNames = new StringBuilder();
			for (String jobGroupName : jobScheduler.getJobGroupNames()) {
				GroupMatcher<JobKey> groupMatcher = GroupMatcher.groupEquals(jobGroupName);
				Set<JobKey> jobKeys = jobScheduler.getJobKeys(groupMatcher);
				if (jobKeys != null && !jobKeys.isEmpty()) {
					for (JobKey jobKey : jobKeys) {
						if (jobKey.getName().contains(custJobKey)) {
							jobNames.append(jobKey.getName() + " ");
							deleteJobsList.add(jobKey);
						}
					}
				}
			}

			if (!deleteJobsList.isEmpty()) {
				// Delete all the jobs for this customer
				logger.debug("Deleting the jobs for customer : " + custJobKey + " Jobs : " + jobNames);
				jobScheduler.deleteJobs(deleteJobsList);
				logger.debug("Succesfully Deleted all the jobs for customer : " + custJobKey);
			} else {
				logger.debug("No Active jobs found for customer : " + custJobKey);
			}

		} catch (SchedulerException e) {
			logger.error("Exception in deleteJobsForCustomer for customer :   " + custJobKey, e);
		}
	}

	public List<CustomerJobDetails> listAllJobs() {

		List<CustomerJobDetails> jobDetailList = new ArrayList<CustomerJobDetails>();

		try {
			for (String jobGroupName : jobScheduler.getJobGroupNames()) {
				GroupMatcher<JobKey> groupMatcher = GroupMatcher.groupEquals(jobGroupName);
				Set<JobKey> jobKeys = jobScheduler.getJobKeys(groupMatcher);
				if (jobKeys != null && !jobKeys.isEmpty()) {
					for (JobKey jobKey : jobKeys) {
						CustomerJobDetails custJobDetails = getCustomerJobDetails(jobKey);
						jobDetailList.add(custJobDetails);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in listAllJobs : ", e);
		}

		return jobDetailList;

	}

	private CustomerJobDetails getCustomerJobDetails(JobKey jobKey) {
		CustomerJobDetails jobDetails = new CustomerJobDetails();
		String jobName = jobKey.getName();

		jobDetails.setJobName(jobName);

		// Bill Cycle Job-1_280402487_1_2246884338_2016-10-01
		// 17:25:55_1475260538675
		// One Message Notification Job-2_280402487_1_2246884338_2016-10-01
		// 17:25:55_1475260538675

		String values[] = jobName.split("_");
		try {
			int i = 0;
			jobDetails.setJobType(values[i++]);
			jobDetails.setCustId(values[i++]);
			jobDetails.setCustAccNo(values[i++]);
			jobDetails.setMdn(values[i++]);
			jobDetails.setScheduledTime(values[i++]);
		} catch (Exception e) {
		}

		return jobDetails;
	}

	public List<CustomerJobDetails> getJobDetailsFor(String custId, String accNo) {
		List<CustomerJobDetails> jobDetailList = new ArrayList<CustomerJobDetails>();
		String custJobKey = custId + "_" + accNo;
		try {
			for (String jobGroupName : jobScheduler.getJobGroupNames()) {
				GroupMatcher<JobKey> groupMatcher = GroupMatcher.groupEquals(jobGroupName);
				Set<JobKey> jobKeys = jobScheduler.getJobKeys(groupMatcher);
				if (jobKeys != null && !jobKeys.isEmpty()) {
					for (JobKey jobKey : jobKeys) {
						if (jobKey.getName().contains(custJobKey)) {
							CustomerJobDetails jobDetails = getCustomerJobDetails(jobKey);
							jobDetailList.add(jobDetails);
						}
					}
				}
			}

		} catch (Exception e) {
			logger.error("Exception in getJobDetailsFor for customer :   " + custJobKey, e);
		}

		return jobDetailList;
	}

	/**
	 * 
	 * @param cust
	 * @param jobNo
	 * @param month
	 * @param day
	 * @throws Exception
	 */
	public void createBillingJob(PlabCust cust, int jobNo, int month, int day, int hour, int minute) throws Exception {

		Trigger trigger = createTriggerForBilling(cust, jobNo, month, day, hour, minute);

		String jobGroupName = "Billing";
		String uniqueJobName = jobNo + "_" + cust.getCustIdNo() + "_" + cust.getAcctNo() + "_" + cust.getMdn() + "_"
				+ CustomerJobDetails.JOB_DATE_FORMAT.format(trigger.getStartTime()) + "_" + System.currentTimeMillis();

		Map<String, String> jobDataAsMap = new HashMap<String, String>();
		jobDataAsMap.put("mdn", cust.getMdn());
		jobDataAsMap.put("custId", cust.getCustIdNo() + "");
		jobDataAsMap.put("offerId", cust.getOffer().getOfferId() + "");

		Calendar cal = Calendar.getInstance();
		cal.setTime(trigger.getStartTime());
		jobDataAsMap.put("billYear", cal.get(Calendar.YEAR) + "");

		int billingMonth = cal.get(Calendar.MONTH);
		if (day != 1) {
			--billingMonth;
		}

		jobDataAsMap.put("billMonth", billingMonth + "");

		String jobDesc = "";
		if (jobNo == 5) {
			// EOC Terminate Job
			jobDataAsMap.put("billingJobType", "Terminate");
			jobDesc = "Terminate Bill Cycle Job for Customer";
			uniqueJobName = "Terminate Bill Cycle Job-" + uniqueJobName;
		} else if (jobNo == 6) {
			// Early Terminate
			jobDataAsMap.put("billingJobType", "EarlyTerminate");
			jobDesc = "Terminate Bill Cycle Job for Customer";
			uniqueJobName = "Terminate Bill Cycle Job-" + uniqueJobName;
		} else {
			jobDataAsMap.put("billingJobType", "Billing");
			jobDesc = "Billing Job for Customer";
			uniqueJobName = "Bill Cycle Job-" + uniqueJobName;
		}

		JobDetail job = newJob(BillingJob.class).withIdentity(uniqueJobName, jobGroupName)
				.usingJobData(new JobDataMap(jobDataAsMap)).withDescription(jobDesc).build();

		Date scheduledDate = jobScheduler.scheduleJob(job, trigger);

		logger.debug("Created Job to run at : " + scheduledDate + " with Job name : " + uniqueJobName);
	}

	/**
	 * 
	 * @param cust
	 * @param month
	 * @param day
	 * @param messageType
	 * @throws Exception
	 */
	public void createOneMessageJob(PlabCust cust, List<String> enrolledMdns, int jobNo, int month, int day, int hour,
			int minute, String messageType) throws Exception {

		Trigger trigger = createTriggerForOneMessage(cust, jobNo, month, day, hour, minute);

		String jobGroupName = "OneMessage";
		String uniqueJobName = "One Message Notification Job-" + jobNo + "_" + cust.getCustIdNo() + "_"
				+ cust.getAcctNo() + "_" + cust.getMdn() + "_"
				+ CustomerJobDetails.JOB_DATE_FORMAT.format(trigger.getStartTime()) + "_" + System.currentTimeMillis();

		String selectedServiceType = "";

		for (com.vzwcorp.pricinglab.vo.Service service : cust.getOffer().getServices()) {
			String serviceType = serviceManager.getServiceType(service);
			if (!serviceType.equals("BaseService")) {
				selectedServiceType = serviceType;
				break;
			}
		}

		String oneMessageType = getOneMessageTypeByService(selectedServiceType, messageType);

		Map<String, String> jobDataAsMap = new HashMap<String, String>();
		jobDataAsMap.put("customerID", cust.getCustIdNo().toString());
		jobDataAsMap.put("custAccountNumber", cust.getAcctNo().toString());
		jobDataAsMap.put("primaryMdn", cust.getMdn());
		jobDataAsMap.put("messageType", oneMessageType);
		StringBuilder stringBuilder = new StringBuilder();
		for (String mdn : enrolledMdns) {
			stringBuilder.append("," + mdn);
		}
		jobDataAsMap.put("mdnList", stringBuilder.toString().replaceFirst(",", ""));

		String jobDesc = "One Message Job for Customer";
		JobDetail job = newJob(OneMessageJob.class).withIdentity(uniqueJobName, jobGroupName)
				.usingJobData(new JobDataMap(jobDataAsMap)).withDescription(jobDesc).build();

		Date scheduledDate = jobScheduler.scheduleJob(job, trigger);

		logger.debug("Created Job to run at : " + scheduledDate + " with Job name : " + uniqueJobName);
	}

	/**
	 * 
	 * @param cust
	 * @param month
	 * @param day
	 * @return
	 */
	private Trigger createTriggerForBilling(PlabCust cust, int jobNo, int month, int day, int hour, int minute) {

		Date date = null;
		SimpleTrigger trigger = null;

		if (simulateJobs) {
			date = getDateToTest(jobNo, 1);
			trigger = (SimpleTrigger) newTrigger()
					.withIdentity("billing-trigger:" + cust.getMdn() + "(" + jobNo + ")_" + System.currentTimeMillis(),
							"Billing")
					.startAt(date).withSchedule(simpleSchedule().withMisfireHandlingInstructionFireNow()).build();
		} else {
			date = getDate(month, day, hour, minute);
			trigger = (SimpleTrigger) newTrigger()
					.withIdentity("billing-trigger:" + cust.getMdn() + "(" + jobNo + ")_" + System.currentTimeMillis(),
							"Billing")
					.startAt(date).withSchedule(simpleSchedule().withMisfireHandlingInstructionFireNow()).build();
		}

		return trigger;
	}

	/**
	 * 
	 * @param cust
	 * @param month
	 * @param day
	 * @return
	 */
	private Trigger createTriggerForOneMessage(PlabCust cust, int jobNo, int month, int day, int hour, int minute) {

		Date date = getDate(month, day, hour, minute);

		if (simulateJobs) {
			date = getDateToTest(jobNo, 1);
		}

		SimpleTrigger trigger = (SimpleTrigger) newTrigger()
				.withIdentity("onemessage-trigger:" + cust.getMdn() + "(" + jobNo + ")_" + System.currentTimeMillis(),
						"OneMessage")
				.startAt(date).withSchedule(simpleSchedule().withMisfireHandlingInstructionFireNow()).build();

		return trigger;
	}

	public Date getDate(int month, int day, int hour, int minute) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, 0);
		cal.setTimeZone(TimeZone.getTimeZone("EST"));

		return cal.getTime();
	}

	private Date getDateToTest(int jobNo, int type) {
		Calendar cal = Calendar.getInstance();
		int min = cal.get(Calendar.MINUTE);
		if (type == 1) {
			cal.set(Calendar.MINUTE, min + (jobNo * 5));
		} else {
			cal.set(Calendar.MINUTE, min + 6 + (jobNo * 5));
		}
		cal.set(Calendar.SECOND, 0);

		return cal.getTime();

	}

	private String getOneMessageTypeByService(String selectedServiceType, String messageType) {

		if ("NightSurfer".equals(selectedServiceType)) {
			if (ONE_MESSAGE_TYPE_WELCOME.equals(messageType)) {
				return OneMessageType.NightSurferWelcome.toString();
			} else if (ONE_MESSAGE_TYPE_EXP1DAY.equals(messageType)) {
				return OneMessageType.NightSurferExp1Day.toString();
			} else if (ONE_MESSAGE_TYPE_EXP7DAYS.equals(messageType)) {
				return OneMessageType.NightSurferExp7Days.toString();
			} else if (ONE_MESSAGE_TYPE_EXP.equals(messageType)) {
				return OneMessageType.NightSurferExpire.toString();
			} else if (ONE_MESSAGE_TYPE_EARLYTERM.equals(messageType)) {
				return OneMessageType.NightSurferEarlyTerm.toString();
			}
		} else if ("SpeedTier".equals(selectedServiceType)) {
			if (ONE_MESSAGE_TYPE_WELCOME.equals(messageType)) {
				return OneMessageType.SelectYourSpeedWelcome.toString();
			} else if (ONE_MESSAGE_TYPE_EXP1DAY.equals(messageType)) {
				return OneMessageType.SelectYourSpeedExp1Day.toString();
			} else if (ONE_MESSAGE_TYPE_EXP7DAYS.equals(messageType)) {
				return OneMessageType.SelectYourSpeedExp7Days.toString();
			} else if (ONE_MESSAGE_TYPE_EXP.equals(messageType)) {
				return OneMessageType.SelectYourSpeedExpire.toString();
			} else if (ONE_MESSAGE_TYPE_EARLYTERM.equals(messageType)) {
				return OneMessageType.SelectYourSpeedEarlyTerm.toString();
			}
		}

		return "";
	}

}
