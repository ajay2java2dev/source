/**
 * 
 */
package com.vzwcorp.pricinglab.vo;

import java.util.Date;

/**
 * @author kovelde
 *
 */
public class OneMessageDetails {

	private String custFirstName;
	private String emailAddress;
	private String custIdAccount;
	private String mdnList;
	private String speed;
	private Date expiryDate;
	private String oldPlan;
	private String surveyURL;

	public String getCustFirstName() {
		return custFirstName;
	}

	public void setCustFirstName(String custFirstName) {
		this.custFirstName = custFirstName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getCustIdAccount() {
		return custIdAccount;
	}

	public void setCustIdAccount(String custIdAccount) {
		this.custIdAccount = custIdAccount;
	}

	public String getMdnList() {
		return mdnList;
	}

	public void setMdnList(String mdnList) {
		this.mdnList = mdnList;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getOldPlan() {
		return oldPlan;
	}

	public void setOldPlan(String oldPlan) {
		this.oldPlan = oldPlan;
	}

	public String getSurveyURL() {
		return surveyURL;
	}

	public void setSurveyURL(String surveyURL) {
		this.surveyURL = surveyURL;
	}

}
