package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the REF_VIS_RATING_PROD_HIER database table.
 * 
 */
@Embeddable
public class RefVisRatingProdHierPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="RBM_PARENT_CD")
	private String rbmParentCd;

	@Column(name="RBM_PARENT_VALUE")
	private String rbmParentValue;

	@Column(name="RBM_CHILD_CD")
	private String rbmChildCd;

	@Column(name="RBM_CHILD_VALUE")
	private String rbmChildValue;

	public RefVisRatingProdHierPK() {
	}
	public String getRbmParentCd() {
		return this.rbmParentCd;
	}
	public void setRbmParentCd(String rbmParentCd) {
		this.rbmParentCd = rbmParentCd;
	}
	public String getRbmParentValue() {
		return this.rbmParentValue;
	}
	public void setRbmParentValue(String rbmParentValue) {
		this.rbmParentValue = rbmParentValue;
	}
	public String getRbmChildCd() {
		return this.rbmChildCd;
	}
	public void setRbmChildCd(String rbmChildCd) {
		this.rbmChildCd = rbmChildCd;
	}
	public String getRbmChildValue() {
		return this.rbmChildValue;
	}
	public void setRbmChildValue(String rbmChildValue) {
		this.rbmChildValue = rbmChildValue;
	}

	/*
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RefVisRatingProdHierPK)) {
			return false;
		}
		RefVisRatingProdHierPK castOther = (RefVisRatingProdHierPK)other;
		return 
			this.rbmParentCd.equals(castOther.rbmParentCd)
			&& this.rbmParentValue.equals(castOther.rbmParentValue)
			&& this.rbmChildCd.equals(castOther.rbmChildCd)
			&& this.rbmChildValue.equals(castOther.rbmChildValue);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.rbmParentCd.hashCode();
		hash = hash * prime + this.rbmParentValue.hashCode();
		hash = hash * prime + this.rbmChildCd.hashCode();
		hash = hash * prime + this.rbmChildValue.hashCode();
		
		System.out.println("has code  = " + hash);
		
		return hash;
	}*/
}