

package com.vzwcorp.pricinglab.profile.lookup.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RefVisionInstanceLkupPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="VISION_ID")
	protected String	visionId;

	public RefVisionInstanceLkupPK() {}

	public RefVisionInstanceLkupPK(String visionId){
		 this.visionId = visionId;
	}

	public String getvisionId() {
		return this.visionId;
	}

	public void setvisionId(String visionId) {
		this.visionId = visionId;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RefVisionInstanceLkupPK)) {
			return false;
		}
		RefVisionInstanceLkupPK castOther = (RefVisionInstanceLkupPK)other;
		return this.visionId == castOther.visionId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		//hash = hash * prime + ((int) (this.visionId ^ (this.visionId >>> 32)));
		return hash;
	}

}
