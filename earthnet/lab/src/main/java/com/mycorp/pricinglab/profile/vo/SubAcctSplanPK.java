package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubAcctSplanPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="SPLAN_ID_NO")
	protected Long	splanIdNo;

	@Column(name="CUST_ID_NO")
	protected Long	custIdNo;

	@Column(name="ACCT_NO")
	protected Long	acctNo;

	public SubAcctSplanPK() {}

	public SubAcctSplanPK(Long splanIdNo, Long custIdNo, Long acctNo){
		 this.splanIdNo = splanIdNo;
		 this.custIdNo = custIdNo;
		 this.acctNo = acctNo;
	}

	public Long getSplanIdNo() {
		return this.splanIdNo;
	}

	public void setSplanIdNo(Long splanIdNo) {
		this.splanIdNo = splanIdNo;
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

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SubAcctSplanPK)) {
			return false;
		}
		SubAcctSplanPK castOther = (SubAcctSplanPK)other;
		return this.splanIdNo == castOther.splanIdNo && this.custIdNo == castOther.custIdNo && this.acctNo == castOther.acctNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.splanIdNo ^ (this.splanIdNo >>> 32)));
		hash = hash * prime + ((int) (this.custIdNo ^ (this.custIdNo >>> 32)));
		hash = hash * prime + ((int) (this.acctNo ^ (this.acctNo >>> 32)));
		return hash;
	}

}
