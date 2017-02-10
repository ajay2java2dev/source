package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubSuAllocationPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="SERIAL_NUMBER")
	protected String	serialNumber;

	@Column(name="SU_SPECIFICATION_ID")
	protected Long	suSpecificationId;

	public SubSuAllocationPK() {}

	public SubSuAllocationPK(String serialNumber, Long suSpecificationId){
		 this.serialNumber = serialNumber;
		 this.suSpecificationId = suSpecificationId;
	}

	public String getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Long getSuSpecificationId() {
		return this.suSpecificationId;
	}

	public void setSuSpecificationId(Long suSpecificationId) {
		this.suSpecificationId = suSpecificationId;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SubSuAllocationPK)) {
			return false;
		}
		SubSuAllocationPK castOther = (SubSuAllocationPK)other;
		return this.serialNumber.equals(castOther.serialNumber) && this.suSpecificationId == castOther.suSpecificationId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.serialNumber.hashCode();
		hash = hash * prime + ((int) (this.suSpecificationId ^ (this.suSpecificationId >>> 32)));
		return hash;
	}

}
