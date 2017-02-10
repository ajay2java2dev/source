package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubCustDvcEqpTransPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CUST_ID_NO")
	protected Long	custIdNo;

	@Column(name="ACCT_NO")
	protected Long	acctNo;

	@Column(name="MDN")
	protected String	mdn;

	public SubCustDvcEqpTransPK() {}

	public SubCustDvcEqpTransPK(Long custIdNo, Long acctNo, String mdn){
		 this.custIdNo = custIdNo;
		 this.acctNo = acctNo;
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
		if (!(other instanceof SubCustDvcEqpTransPK)) {
			return false;
		}
		SubCustDvcEqpTransPK castOther = (SubCustDvcEqpTransPK)other;
		return this.custIdNo == castOther.custIdNo && this.acctNo == castOther.acctNo && this.mdn.equals(castOther.mdn);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.custIdNo ^ (this.custIdNo >>> 32)));
		hash = hash * prime + ((int) (this.acctNo ^ (this.acctNo >>> 32)));
		hash = hash * prime + this.mdn.hashCode();
		return hash;
	}

}
