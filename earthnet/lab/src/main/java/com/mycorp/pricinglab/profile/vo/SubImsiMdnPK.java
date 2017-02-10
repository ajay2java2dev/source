package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubImsiMdnPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="IMSI_VZW")
	protected String	imsiVzw;

	@Column(name="IMSI_MDN_EFF_TS_A")
	protected Timestamp	imsiMdnEffTsA;

	public SubImsiMdnPK() {}

	public SubImsiMdnPK(String imsiVzw, Timestamp imsiMdnEffTsA){
		 this.imsiVzw = imsiVzw;
		 this.imsiMdnEffTsA = imsiMdnEffTsA;
	}

	public String getImsiVzw() {
		return this.imsiVzw;
	}

	public void setImsiVzw(String imsiVzw) {
		this.imsiVzw = imsiVzw;
	}

	public Timestamp getImsiMdnEffTsA() {
		return this.imsiMdnEffTsA;
	}

	public void setImsiMdnEffTsA(Timestamp imsiMdnEffTsA) {
		this.imsiMdnEffTsA = imsiMdnEffTsA;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SubImsiMdnPK)) {
			return false;
		}
		SubImsiMdnPK castOther = (SubImsiMdnPK)other;
		return this.imsiVzw.equals(castOther.imsiVzw) && this.imsiMdnEffTsA.equals(castOther.imsiMdnEffTsA);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.imsiVzw.hashCode();
		hash = hash * prime + this.imsiMdnEffTsA.hashCode();
		return hash;
	}

}
