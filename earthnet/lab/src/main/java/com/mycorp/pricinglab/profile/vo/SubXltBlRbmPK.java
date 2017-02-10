package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubXltBlRbmPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="BL_ID_VALUE")
	protected String	blIdValue;

	@Column(name="RBM_ID_TYP")
	protected String	rbmIdTyp;

	@Column(name="CUST_ID_NO")
	protected Long	custIdNo;

	@Column(name="ACCT_NO")
	protected Long	acctNo;

	@Column(name="EFF_TMSTAMP")
	protected Timestamp	effTmstamp;

	@Column(name="MDN")
	protected String	mdn;

	@Column(name="RBM_ID_VALUE")
	protected String	rbmIdValue;

	@Column(name="BL_ID_TYP")
	protected String	blIdTyp;

	public SubXltBlRbmPK() {}

	public SubXltBlRbmPK(String blIdValue, String rbmIdTyp, Long custIdNo, Long acctNo, Timestamp effTmstamp, String mdn, String rbmIdValue, String blIdTyp){
		 this.blIdValue = blIdValue;
		 this.rbmIdTyp = rbmIdTyp;
		 this.custIdNo = custIdNo;
		 this.acctNo = acctNo;
		 this.effTmstamp = effTmstamp;
		 this.mdn = mdn;
		 this.rbmIdValue = rbmIdValue;
		 this.blIdTyp = blIdTyp;
	}

	public String getBlIdValue() {
		return this.blIdValue;
	}

	public void setBlIdValue(String blIdValue) {
		this.blIdValue = blIdValue;
	}

	public String getRbmIdTyp() {
		return this.rbmIdTyp;
	}

	public void setRbmIdTyp(String rbmIdTyp) {
		this.rbmIdTyp = rbmIdTyp;
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

	public String getRbmIdValue() {
		return this.rbmIdValue;
	}

	public void setRbmIdValue(String rbmIdValue) {
		this.rbmIdValue = rbmIdValue;
	}

	public String getBlIdTyp() {
		return this.blIdTyp;
	}

	public void setBlIdTyp(String blIdTyp) {
		this.blIdTyp = blIdTyp;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SubXltBlRbmPK)) {
			return false;
		}
		SubXltBlRbmPK castOther = (SubXltBlRbmPK)other;
		return this.blIdValue.equals(castOther.blIdValue) && this.rbmIdTyp.equals(castOther.rbmIdTyp) && this.custIdNo == castOther.custIdNo && this.acctNo == castOther.acctNo && this.effTmstamp.equals(castOther.effTmstamp) && this.mdn.equals(castOther.mdn) && this.rbmIdValue.equals(castOther.rbmIdValue) && this.blIdTyp.equals(castOther.blIdTyp);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.blIdValue.hashCode();
		hash = hash * prime + this.rbmIdTyp.hashCode();
		hash = hash * prime + ((int) (this.custIdNo ^ (this.custIdNo >>> 32)));
		hash = hash * prime + ((int) (this.acctNo ^ (this.acctNo >>> 32)));
		hash = hash * prime + this.effTmstamp.hashCode();
		hash = hash * prime + this.mdn.hashCode();
		hash = hash * prime + this.rbmIdValue.hashCode();
		hash = hash * prime + this.blIdTyp.hashCode();
		return hash;
	}

}
