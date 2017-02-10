/**
 * 
 */
package com.vzwcorp.pricinglab.quartz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author kovelde
 *
 */
public class CustomerJobDetails implements Comparable<CustomerJobDetails> {

	public static SimpleDateFormat JOB_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private String custId;
	private String custAccNo;
	private String mdn;
	private String jobType;
	private String displayJobType;
	private String jobName;
	private String scheduledTime;

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustAccNo() {
		return custAccNo;
	}

	public void setCustAccNo(String custAccNo) {
		this.custAccNo = custAccNo;
	}

	public String getMdn() {
		return mdn;
	}

	public void setMdn(String mdn) {
		this.mdn = mdn;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(String scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	public String getDisplayJobType() {

		if (jobType != null && !jobType.isEmpty()) {

			String scheduledMonth = "";
			try {
				Date date = JOB_DATE_FORMAT.parse(scheduledTime);
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				scheduledMonth = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
			} catch (ParseException e) {
			}

			if (jobType.contains("Terminate Bill Cycle Job")) {
				displayJobType = "Terminate Services at End Of Cycle";
			} else if (jobType.contains("Bill Cycle Job")) {
				displayJobType = "Generate Bill for " + scheduledMonth;
			} else if (jobType.contains("One Message Notification Job-1")) {
				displayJobType = "Expiry Notification before 7 days";
			} else if (jobType.contains("One Message Notification Job-2")) {
				displayJobType = "Expiry Notification before 1 day";
			}

		}

		return displayJobType;
	}

	public void setDisplayJobType(String displayJobType) {
		this.displayJobType = displayJobType;
	}

	@Override
	public int compareTo(CustomerJobDetails otherObj) {

		String myne = custId + custAccNo + mdn + scheduledTime + jobType;
		String other = otherObj.getCustId() + otherObj.getCustAccNo() + otherObj.getMdn() + otherObj.getScheduledTime()
				+ otherObj.getJobType();

		return myne.compareTo(other);
	}

}
