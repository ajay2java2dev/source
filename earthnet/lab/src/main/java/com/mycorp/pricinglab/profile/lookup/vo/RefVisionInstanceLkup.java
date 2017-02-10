package com.vzwcorp.pricinglab.profile.lookup.vo;

import java.math.BigDecimal;
import javax.persistence.*;


/**
 * The persistent class for the REF_VISION_INSTANCE_LKUP database table.
 * 
 */

@Table(name="REF_VISION_INSTANCE_LKUP",schema = "MZADMIN")
@Entity
@IdClass(RefVisionInstanceLkupPK.class)
public class RefVisionInstanceLkup {

	@Id
	@Column(name="VISION_ID")
	private String visionId;

	@Column(name="UB_INSTANCE")
	private String ubInstance;

	@Column(name="BILLER_ID")
	private String billerId;

	@Column(name="TIMEZONE")
	private String timezone;

	@Column(name="JITR_INSTANCE")
	private String jitrInstance;

	@Column(name="VIS_INST_NAME")
	private String visInstName;

	@Column(name="BILLING_SYSTEM_ID")
	private BigDecimal billingSystemId;

	@Column(name="TIMEZONE_ST")
	private String timezoneSt;


	public RefVisionInstanceLkup() {
	}

	public String getVisionId() {
		return this.visionId;
	}

	public void setVisionId(String visionId) {
		this.visionId = visionId;
	}

	public String getBillerId() {
		return this.billerId;
	}

	public void setBillerId(String billerId) {
		this.billerId = billerId;
	}

	public BigDecimal getBillingSystemId() {
		return this.billingSystemId;
	}

	public void setBillingSystemId(BigDecimal billingSystemId) {
		this.billingSystemId = billingSystemId;
	}

	public String getJitrInstance() {
		return this.jitrInstance;
	}

	public void setJitrInstance(String jitrInstance) {
		this.jitrInstance = jitrInstance;
	}

	public String getTimezone() {
		return this.timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getUbInstance() {
		return this.ubInstance;
	}

	public void setUbInstance(String ubInstance) {
		this.ubInstance = ubInstance;
	}

	public String getVisInstName() {
		return this.visInstName;
	}

	public void setVisInstName(String visInstName) {
		this.visInstName = visInstName;
	}

	public String getTimezoneSt() {
		return timezoneSt;
	}

	public void setTimezoneSt(String timezoneSt) {
		this.timezoneSt = timezoneSt;
	}


}