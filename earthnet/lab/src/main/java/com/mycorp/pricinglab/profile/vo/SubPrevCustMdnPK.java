package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubPrevCustMdnPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CUST_ID_NO")
	protected Long	custIdNo;

	@Column(name="MTN_EFF_DT")
	protected Timestamp	mtnEffDt;

	@Column(name="EFF_TMSTAMP")
	protected Timestamp	effTmstamp;

	@Column(name="MDN")
	protected String	mdn;

	public SubPrevCustMdnPK() {}

	public SubPrevCustMdnPK(Long custIdNo, Timestamp mtnEffDt, Timestamp effTmstamp, String mdn){
		 this.custIdNo = custIdNo;
		 this.mtnEffDt = mtnEffDt;
		 this.effTmstamp = effTmstamp;
		 this.mdn = mdn;
	}

	public Long getCustIdNo() {
		return this.custIdNo;
	}

	public void setCustIdNo(Long custIdNo) {
		this.custIdNo = custIdNo;
	}

	public Timestamp getMtnEffDt() {
		return this.mtnEffDt;
	}

	public void setMtnEffDt(Timestamp mtnEffDt) {
		this.mtnEffDt = mtnEffDt;
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
		if (!(other instanceof SubPrevCustMdnPK)) {
			return false;
		}
		SubPrevCustMdnPK castOther = (SubPrevCustMdnPK)other;
		return this.custIdNo == castOther.custIdNo && this.mtnEffDt.equals(castOther.mtnEffDt) && this.effTmstamp.equals(castOther.effTmstamp) && this.mdn.equals(castOther.mdn);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.custIdNo ^ (this.custIdNo >>> 32)));
		hash = hash * prime + this.mtnEffDt.hashCode();
		hash = hash * prime + this.effTmstamp.hashCode();
		hash = hash * prime + this.mdn.hashCode();
		return hash;
	}

}
