package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubLnPrimIdMdnPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="LN_OF_SVC_ID_NO_P1")
	protected Long	lnOfSvcIdNoP1;

	@Column(name="LN_MDN_EFF_TS_A")
	protected Timestamp	lnMdnEffTsA;

	@Column(name="LN_OF_SVC_ID_NO_P2")
	protected Long	lnOfSvcIdNoP2;

	@Column(name="VISION_SOURCE")
	protected String	visionSource;

	public SubLnPrimIdMdnPK() {}

	public SubLnPrimIdMdnPK(Long lnOfSvcIdNoP1, Timestamp lnMdnEffTsA, Long lnOfSvcIdNoP2, String visionSource){
		 this.lnOfSvcIdNoP1 = lnOfSvcIdNoP1;
		 this.lnMdnEffTsA = lnMdnEffTsA;
		 this.lnOfSvcIdNoP2 = lnOfSvcIdNoP2;
		 this.visionSource = visionSource;
	}

	public Long getLnOfSvcIdNoP1() {
		return this.lnOfSvcIdNoP1;
	}

	public void setLnOfSvcIdNoP1(Long lnOfSvcIdNoP1) {
		this.lnOfSvcIdNoP1 = lnOfSvcIdNoP1;
	}

	public Timestamp getLnMdnEffTsA() {
		return this.lnMdnEffTsA;
	}

	public void setLnMdnEffTsA(Timestamp lnMdnEffTsA) {
		this.lnMdnEffTsA = lnMdnEffTsA;
	}

	public Long getLnOfSvcIdNoP2() {
		return this.lnOfSvcIdNoP2;
	}

	public void setLnOfSvcIdNoP2(Long lnOfSvcIdNoP2) {
		this.lnOfSvcIdNoP2 = lnOfSvcIdNoP2;
	}

	public String getVisionSource() {
		return this.visionSource;
	}

	public void setVisionSource(String visionSource) {
		this.visionSource = visionSource;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SubLnPrimIdMdnPK)) {
			return false;
		}
		SubLnPrimIdMdnPK castOther = (SubLnPrimIdMdnPK)other;
		return this.lnOfSvcIdNoP1 == castOther.lnOfSvcIdNoP1 && this.lnMdnEffTsA.equals(castOther.lnMdnEffTsA) && this.lnOfSvcIdNoP2 == castOther.lnOfSvcIdNoP2 && this.visionSource.equals(castOther.visionSource);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.lnOfSvcIdNoP1 ^ (this.lnOfSvcIdNoP1 >>> 32)));
		hash = hash * prime + this.lnMdnEffTsA.hashCode();
		hash = hash * prime + ((int) (this.lnOfSvcIdNoP2 ^ (this.lnOfSvcIdNoP2 >>> 32)));
		hash = hash * prime + this.visionSource.hashCode();
		return hash;
	}

}
