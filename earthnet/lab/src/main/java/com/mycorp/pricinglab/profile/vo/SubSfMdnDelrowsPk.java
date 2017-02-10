
package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubSfMdnDelrowsPk implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="EFF_DT")
	protected Timestamp	effDt;

	@Column(name="CUST_ID_NO")
	protected Long	custIdNo;

	@Column(name="ACCT_NO")
	protected Long	acctNo;

	@Column(name="MDN")
	protected String	mdn;

	@Column(name="SF_OFFERING_ID")
	protected Long	sfOfferingId;

	
	public Timestamp getEffDt() {
		return this.effDt;
	}

	public void setEffDt(Timestamp effDt) {
		this.effDt = effDt;
	}

	public Long getCustIdNo() {
		return this.custIdNo;
	}

	public void setCustIdNo(Long custIdNo) {
		this.custIdNo = custIdNo;
	}

	public Long getAcctNo() {
		return this.acctNo;
	}

	public void setAcctNo(Long acctNo) {
		this.acctNo = acctNo;
	}

	public String getMdn() {
		return this.mdn;
	}

	public void setMdn(String mdn) {
		this.mdn = mdn;
	}

	public Long getSfOfferingId() {
		return this.sfOfferingId;
	}

	public void setSfOfferingId(Long sfOfferingId) {
		this.sfOfferingId = sfOfferingId;
	}


}

