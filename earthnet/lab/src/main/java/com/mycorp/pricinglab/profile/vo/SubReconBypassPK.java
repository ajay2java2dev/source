package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubReconBypassPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CUST_ID_NO")
	protected Long	custIdNo;

	@Column(name="ACCT_NO")
	protected Long	acctNo;

	@Column(name="EFF_TMSTAMP")
	protected Timestamp	effTmstamp;

	@Column(name="MDN")
	protected String	mdn;

	public SubReconBypassPK() {}

	public SubReconBypassPK(Long custIdNo, Long acctNo, Timestamp effTmstamp, String mdn){
		 this.custIdNo = custIdNo;
		 this.acctNo = acctNo;
		 this.effTmstamp = effTmstamp;
		 this.mdn = mdn;
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

	public Timestamp getEffTmstamp() {
		return this.effTmstamp;
	}

	public void setEffTmstamp(Timestamp effTmstamp) {
		this.effTmstamp = effTmstamp;
	}

	public String getMdn() {
		return this.mdn;
	}

	public void setMdn(String mdn) {
		this.mdn = mdn;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SubReconBypassPK)) {
			return false;
		}
		SubReconBypassPK castOther = (SubReconBypassPK)other;
		return this.custIdNo == castOther.custIdNo && this.acctNo == castOther.acctNo && this.effTmstamp.equals(castOther.effTmstamp) && this.mdn.equals(castOther.mdn);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.custIdNo ^ (this.custIdNo >>> 32)));
		hash = hash * prime + ((int) (this.acctNo ^ (this.acctNo >>> 32)));
		hash = hash * prime + this.effTmstamp.hashCode();
		hash = hash * prime + this.mdn.hashCode();
		return hash;
	}

}
