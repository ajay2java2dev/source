package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubImsiMdnTniRssPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="IMSI_VZW")
	protected String	imsiVzw;

	@Column(name="IMSI_MDN_EFF_DT")
	protected Timestamp	imsiMdnEffDt;

	public SubImsiMdnTniRssPK() {}

	public SubImsiMdnTniRssPK(String imsiVzw, Timestamp imsiMdnEffDt){
		 this.imsiVzw = imsiVzw;
		 this.imsiMdnEffDt = imsiMdnEffDt;
	}

	public String getImsiVzw() {
		return this.imsiVzw;
	}

	public void setImsiVzw(String imsiVzw) {
		this.imsiVzw = imsiVzw;
	}

	public Timestamp getImsiMdnEffDt() {
		return this.imsiMdnEffDt;
	}

	public void setImsiMdnEffDt(Timestamp imsiMdnEffDt) {
		this.imsiMdnEffDt = imsiMdnEffDt;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SubImsiMdnTniRssPK)) {
			return false;
		}
		SubImsiMdnTniRssPK castOther = (SubImsiMdnTniRssPK)other;
		return this.imsiVzw.equals(castOther.imsiVzw) && this.imsiMdnEffDt.equals(castOther.imsiMdnEffDt);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.imsiVzw.hashCode();
		hash = hash * prime + this.imsiMdnEffDt.hashCode();
		return hash;
	}

}
