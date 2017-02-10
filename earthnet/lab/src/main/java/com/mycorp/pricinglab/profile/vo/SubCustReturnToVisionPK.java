package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubCustReturnToVisionPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CUST_ID_NO")
	protected Long	custIdNo;

	public SubCustReturnToVisionPK() {}

	public SubCustReturnToVisionPK(Long custIdNo){
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
		if (!(other instanceof SubCustReturnToVisionPK)) {
			return false;
		}
		SubCustReturnToVisionPK castOther = (SubCustReturnToVisionPK)other;
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
