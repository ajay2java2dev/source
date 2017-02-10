package com.vzwcorp.pricinglab.profile.rbm.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CustomertypePK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CUSTOMER_TYPE_ID")
	protected Long	customerTypeId;

	public CustomertypePK() {}

	public CustomertypePK(Long customerTypeId){
		 this.customerTypeId = customerTypeId;
	}

	public Long getCustomerTypeId() {
		return this.customerTypeId;
	}

	public void setCustomerTypeId(Long customerTypeId) {
		this.customerTypeId = customerTypeId;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CustomertypePK)) {
			return false;
		}
		CustomertypePK castOther = (CustomertypePK)other;
		return this.customerTypeId == castOther.customerTypeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.customerTypeId ^ (this.customerTypeId >>> 32)));
		return hash;
	}

}
