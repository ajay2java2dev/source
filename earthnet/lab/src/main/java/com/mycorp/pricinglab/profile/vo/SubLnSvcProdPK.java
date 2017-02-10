package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubLnSvcProdPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="LN_OF_SVC_ID_NO_P1")
	protected Long	lnOfSvcIdNoP1;

	@Column(name="LN_OF_SVC_ID_NO_P2")
	protected Long	lnOfSvcIdNoP2;

	@Column(name="SVC_PROD_ID")
	protected Long	svcProdId;

	@Column(name="LN_SVC_PROD_UNIQUE_ID")
	protected Long	lnSvcProdUniqueId;

	public SubLnSvcProdPK() {}

	public SubLnSvcProdPK(Long lnOfSvcIdNoP1, Long lnOfSvcIdNoP2, Long svcProdId, Long lnSvcProdUniqueId){
		 this.lnOfSvcIdNoP1 = lnOfSvcIdNoP1;
		 this.lnOfSvcIdNoP2 = lnOfSvcIdNoP2;
		 this.svcProdId = svcProdId;
		 this.lnSvcProdUniqueId = lnSvcProdUniqueId;
	}

	public Long getLnOfSvcIdNoP1() {
		return this.lnOfSvcIdNoP1;
	}

	public void setLnOfSvcIdNoP1(Long lnOfSvcIdNoP1) {
		this.lnOfSvcIdNoP1 = lnOfSvcIdNoP1;
	}

	public Long getLnOfSvcIdNoP2() {
		return this.lnOfSvcIdNoP2;
	}

	public void setLnOfSvcIdNoP2(Long lnOfSvcIdNoP2) {
		this.lnOfSvcIdNoP2 = lnOfSvcIdNoP2;
	}

	public Long getSvcProdId() {
		return this.svcProdId;
	}

	public void setSvcProdId(Long svcProdId) {
		this.svcProdId = svcProdId;
	}

	public Long getLnSvcProdUniqueId() {
		return this.lnSvcProdUniqueId;
	}

	public void setLnSvcProdUniqueId(Long lnSvcProdUniqueId) {
		this.lnSvcProdUniqueId = lnSvcProdUniqueId;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SubLnSvcProdPK)) {
			return false;
		}
		SubLnSvcProdPK castOther = (SubLnSvcProdPK)other;
		return this.lnOfSvcIdNoP1 == castOther.lnOfSvcIdNoP1 && this.lnOfSvcIdNoP2 == castOther.lnOfSvcIdNoP2 && this.svcProdId == castOther.svcProdId && this.lnSvcProdUniqueId == castOther.lnSvcProdUniqueId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.lnOfSvcIdNoP1 ^ (this.lnOfSvcIdNoP1 >>> 32)));
		hash = hash * prime + ((int) (this.lnOfSvcIdNoP2 ^ (this.lnOfSvcIdNoP2 >>> 32)));
		hash = hash * prime + ((int) (this.svcProdId ^ (this.svcProdId >>> 32)));
		hash = hash * prime + ((int) (this.lnSvcProdUniqueId ^ (this.lnSvcProdUniqueId >>> 32)));
		return hash;
	}

}
