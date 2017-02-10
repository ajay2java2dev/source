package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the REF_VIS_XLT_RATING_TRANS_MAP database table.
 * 
 */
@Embeddable
public class RefVisXltRatingTransMapPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="BL_SYSTEM")
	private String blSystem;

	@Column(name="BL_ID_TYP")
	private String blIdTyp;

	@Column(name="BL_ID_VALUE")
	private long blIdValue;

	@Column(name="RBM_ID_TYP")
	private String rbmIdTyp;

	public RefVisXltRatingTransMapPK() {
	}
	public String getBlSystem() {
		return this.blSystem;
	}
	public void setBlSystem(String blSystem) {
		this.blSystem = blSystem;
	}
	public String getBlIdTyp() {
		return this.blIdTyp;
	}
	public void setBlIdTyp(String blIdTyp) {
		this.blIdTyp = blIdTyp;
	}
	public long getBlIdValue() {
		return this.blIdValue;
	}
	public void setBlIdValue(long blIdValue) {
		this.blIdValue = blIdValue;
	}
	public String getRbmIdTyp() {
		return this.rbmIdTyp;
	}
	public void setRbmIdTyp(String rbmIdTyp) {
		this.rbmIdTyp = rbmIdTyp;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RefVisXltRatingTransMapPK)) {
			return false;
		}
		RefVisXltRatingTransMapPK castOther = (RefVisXltRatingTransMapPK)other;
		return 
			this.blSystem.equals(castOther.blSystem)
			&& this.blIdTyp.equals(castOther.blIdTyp)
			&& (this.blIdValue == castOther.blIdValue)
			&& this.rbmIdTyp.equals(castOther.rbmIdTyp);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.blSystem.hashCode();
		hash = hash * prime + this.blIdTyp.hashCode();
		hash = hash * prime + ((int) (this.blIdValue ^ (this.blIdValue >>> 32)));
		hash = hash * prime + this.rbmIdTyp.hashCode();
		
		return hash;
	}
}