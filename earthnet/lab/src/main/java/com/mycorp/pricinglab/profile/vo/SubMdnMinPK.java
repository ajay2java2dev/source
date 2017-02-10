package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubMdnMinPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="TLN")
	protected String	tln;

	@Column(name="NPA")
	protected String	npa;

	@Column(name="MDN_MIN_EFF_DT_A")
	protected Timestamp	mdnMinEffDtA;

	@Column(name="NXX")
	protected String	nxx;

	public SubMdnMinPK() {}

	public SubMdnMinPK(String tln, String npa, Timestamp mdnMinEffDtA, String nxx){
		 this.tln = tln;
		 this.npa = npa;
		 this.mdnMinEffDtA = mdnMinEffDtA;
		 this.nxx = nxx;
	}

	public String getTln() {
		return this.tln;
	}

	public void setTln(String tln) {
		this.tln = tln;
	}

	public String getNpa() {
		return this.npa;
	}

	public void setNpa(String npa) {
		this.npa = npa;
	}

	public Timestamp getMdnMinEffDtA() {
		return this.mdnMinEffDtA;
	}

	public void setMdnMinEffDtA(Timestamp mdnMinEffDtA) {
		this.mdnMinEffDtA = mdnMinEffDtA;
	}

	public String getNxx() {
		return this.nxx;
	}

	public void setNxx(String nxx) {
		this.nxx = nxx;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SubMdnMinPK)) {
			return false;
		}
		SubMdnMinPK castOther = (SubMdnMinPK)other;
		return this.tln.equals(castOther.tln) && this.npa.equals(castOther.npa) && this.mdnMinEffDtA.equals(castOther.mdnMinEffDtA) && this.nxx.equals(castOther.nxx);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.tln.hashCode();
		hash = hash * prime + this.npa.hashCode();
		hash = hash * prime + this.mdnMinEffDtA.hashCode();
		hash = hash * prime + this.nxx.hashCode();
		return hash;
	}

}
