package com.vzwcorp.pricinglab.profile.rbm.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CustomerPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CUSTOMER_REF")
	protected String	customerRef;

	public CustomerPK() {}

	public CustomerPK(String customerRef){
		 this.customerRef = customerRef;
	}

	public String getCustomerRef() {
		return this.customerRef;
	}

	public void setCustomerRef(String customerRef) {
		this.customerRef = customerRef;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CustomerPK)) {
			return false;
		}
		CustomerPK castOther = (CustomerPK)other;
		return this.customerRef.equals(castOther.customerRef);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.customerRef.hashCode();
		return hash;
	}

}
