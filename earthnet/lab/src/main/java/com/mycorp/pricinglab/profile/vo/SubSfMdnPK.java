package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubSfMdnPK implements Serializable {
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

	public SubSfMdnPK() {}

	public SubSfMdnPK(Timestamp effDt, Long custIdNo, Long acctNo, String mdn, Long sfOfferingId){
		 this.effDt = effDt;
		 this.custIdNo = custIdNo;
		 this.acctNo = acctNo;
		 this.mdn = mdn;
		 this.sfOfferingId = sfOfferingId;
	}

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

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SubSfMdnPK)) {
			return false;
		}
		SubSfMdnPK castOther = (SubSfMdnPK)other;
		return this.effDt.equals(castOther.effDt) && this.custIdNo == castOther.custIdNo && this.acctNo == castOther.acctNo && this.mdn.equals(castOther.mdn) && this.sfOfferingId == castOther.sfOfferingId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.effDt.hashCode();
		hash = hash * prime + ((int) (this.custIdNo ^ (this.custIdNo >>> 32)));
		hash = hash * prime + ((int) (this.acctNo ^ (this.acctNo >>> 32)));
		hash = hash * prime + this.mdn.hashCode();
		hash = hash * prime + ((int) (this.sfOfferingId ^ (this.sfOfferingId >>> 32)));
		return hash;
	}

}
