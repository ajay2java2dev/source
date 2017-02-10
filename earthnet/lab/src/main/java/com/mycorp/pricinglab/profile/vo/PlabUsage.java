package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="PLAB_USAGE")
@IdClass(PlabUsagePK.class)
public class PlabUsage implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(name="CUST_ID_NO")
	public Long custIdNo;
	
	@Column(name="ACCT_NO")
	public Long acctNo;
	
	@Column(name="MDN")
	public String mdn;
	
	@Id
	@Column(name="GLOBAL_RECORD_ID")
	public String globalRecordId;
	
	@Column(name="START_DATE_TS_UTC")
	public Timestamp startDateTsUtc;
	
	@Column(name="GMT_OFFSET")
	public Long gmtOffset;
	
	@Column(name="USAGE_REC")	
	public String usageRec;


	@Column(name="UBSR_USERID")
	public String ubsrUserId;

	@Column(name="UBSR_TMPSTAMP")
	public Timestamp ubsrTS;

	public Long getCustIdNo() {
		return custIdNo;
	}

	public void setCustIdNo(Long custIdNo) {
		this.custIdNo = custIdNo;
	}

	public Long getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(Long acctNo) {
		this.acctNo = acctNo;
	}

	public String getMdn() {
		return mdn;
	}

	public void setMdn(String mdn) {
		this.mdn = mdn;
	}

	public String getGlobalRecordId() {
		return globalRecordId;
	}

	public void setGlobalRecordId(String globalRecordId) {
		this.globalRecordId = globalRecordId;
	}

	public Timestamp getStartDateTsUtc() {
		return startDateTsUtc;
	}

	public void setStartDateTsUtc(Timestamp startDateTsUtc) {
		this.startDateTsUtc = startDateTsUtc;
	}

	public Long getGmtOffset() {
		return gmtOffset;
	}

	public void setGmtOffset(Long gmtOffset) {
		this.gmtOffset = gmtOffset;
	}

	public String getUsageRec() {
		return usageRec;
	}

	public void setUsageRec(String usageRec) {
		this.usageRec = usageRec;
	}

	public String getUbsruserId() {
		return ubsrUserId;
	}

	public void setUbsruserId(String ubsrUserId) {
		this.ubsrUserId = ubsrUserId;
	}

	public Timestamp getUbsrTS() {
		return ubsrTS;
	}

	public void setUbsrTS(Timestamp ubsrTS) {
		this.ubsrTS = ubsrTS;
	}
	
	public String getPrimaryKey(){
		return this.custIdNo+this.acctNo+this.mdn;
	}

}