package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubSuccessHistPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="SUCCESS_HIST_ID")
	protected Long	successHistId;

	public SubSuccessHistPK() {}

	public SubSuccessHistPK(Long successHistId){
		 this.successHistId = successHistId;
	}

	public Long getSuccessHistId() {
		return this.successHistId;
	}

	public void setSuccessHistId(Long successHistId) {
		this.successHistId = successHistId;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SubSuccessHistPK)) {
			return false;
		}
		SubSuccessHistPK castOther = (SubSuccessHistPK)other;
		return this.successHistId == castOther.successHistId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.successHistId ^ (this.successHistId >>> 32)));
		return hash;
	}

}
