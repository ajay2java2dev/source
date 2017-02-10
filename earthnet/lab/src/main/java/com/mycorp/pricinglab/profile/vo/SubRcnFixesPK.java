package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubRcnFixesPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="RECON_FIXES_ID")
	protected Long	reconFixesId;

	public SubRcnFixesPK() {}

	public SubRcnFixesPK(Long reconFixesId){
		 this.reconFixesId = reconFixesId;
	}

	public Long getReconFixesId() {
		return this.reconFixesId;
	}

	public void setReconFixesId(Long reconFixesId) {
		this.reconFixesId = reconFixesId;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SubRcnFixesPK)) {
			return false;
		}
		SubRcnFixesPK castOther = (SubRcnFixesPK)other;
		return this.reconFixesId == castOther.reconFixesId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.reconFixesId ^ (this.reconFixesId >>> 32)));
		return hash;
	}

}
