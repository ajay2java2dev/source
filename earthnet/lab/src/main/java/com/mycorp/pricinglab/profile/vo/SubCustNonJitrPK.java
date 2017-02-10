package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubCustNonJitrPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CUST_ID_NO")
	protected Long	custIdNo;

	public SubCustNonJitrPK() {}

	public SubCustNonJitrPK(Long custIdNo){
		 this.custIdNo = custIdNo;
	}

	public Long getCustIdNo() {
		return this.custIdNo;
	}

	public void setCustIdNo(Long custIdNo) {
		this.custIdNo = custIdNo;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SubCustNonJitrPK)) {
			return false;
		}
		SubCustNonJitrPK castOther = (SubCustNonJitrPK)other;
		return this.custIdNo == castOther.custIdNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.custIdNo ^ (this.custIdNo >>> 32)));
		return hash;
	}

}
