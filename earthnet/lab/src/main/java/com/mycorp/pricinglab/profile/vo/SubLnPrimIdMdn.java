package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="SUB_LN_PRIM_ID_MDN")
@IdClass(SubLnPrimIdMdnPK.class)
public class SubLnPrimIdMdn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="LN_OF_SVC_ID_NO_P2")
	public Long lnOfSvcIdNoP2;

	public Long getLnOfSvcIdNoP2() {
		return lnOfSvcIdNoP2;
}
	public void setLnOfSvcIdNoP2(Long lnOfSvcIdNoP2) {
		this.lnOfSvcIdNoP2 = lnOfSvcIdNoP2;
	}

	@Id
	@Column(name="LN_MDN_EFF_TS_A")
	public Timestamp lnMdnEffTsA;

	public Timestamp getLnMdnEffTsA() {
		return lnMdnEffTsA;
}
	public void setLnMdnEffTsA(Timestamp lnMdnEffTsA) {
		this.lnMdnEffTsA = lnMdnEffTsA;
	}

	@Id
	@Column(name="LN_OF_SVC_ID_NO_P1")
	public Long lnOfSvcIdNoP1;

	public Long getLnOfSvcIdNoP1() {
		return lnOfSvcIdNoP1;
}
	public void setLnOfSvcIdNoP1(Long lnOfSvcIdNoP1) {
		this.lnOfSvcIdNoP1 = lnOfSvcIdNoP1;
	}

	@Column(name="NPA")
	public String npa;

	public String getNpa() {
		return npa;
}
	public void setNpa(String npa) {
		this.npa = npa;
	}

	@Column(name="NXX")
	public String nxx;

	public String getNxx() {
		return nxx;
}
	public void setNxx(String nxx) {
		this.nxx = nxx;
	}

	@Column(name="TLN")
	public String tln;

	public String getTln() {
		return tln;
}
	public void setTln(String tln) {
		this.tln = tln;
	}

	@Column(name="LN_MDN_END_TS_A")
	public Timestamp lnMdnEndTsA;

	public Timestamp getLnMdnEndTsA() {
		return lnMdnEndTsA;
}
	public void setLnMdnEndTsA(Timestamp lnMdnEndTsA) {
		this.lnMdnEndTsA = lnMdnEndTsA;
	}

	@Column(name="DB_USERID")
	public String dbUserid;

	public String getDbUserid() {
		return dbUserid;
}
	public void setDbUserid(String dbUserid) {
		this.dbUserid = dbUserid;
	}

	@Column(name="DB_TMSTAMP")
	public Timestamp dbTmstamp;

	public Timestamp getDbTmstamp() {
		return dbTmstamp;
}
	public void setDbTmstamp(Timestamp dbTmstamp) {
		this.dbTmstamp = dbTmstamp;
	}

	@Id
	@Column(name="VISION_SOURCE")
	public String visionSource;

	public String getVisionSource() {
		return visionSource;
}
	public void setVisionSource(String visionSource) {
		this.visionSource = visionSource;
	}

	@Column(name="UB_TIMESTMP")
	public Timestamp ubTimestmp;

	public Timestamp getUbTimestmp() {
		return ubTimestmp;
}
	public void setUbTimestmp(Timestamp ubTimestmp) {
		this.ubTimestmp = ubTimestmp;
	}

	@Column(name="MDN")
	public String mdn;

	public String getMdn() {
		return mdn;
}
	public void setMdn(String mdn) {
		this.mdn = mdn;
	}

	@Column(name="LN_MDN_EFF_TS")
	public Timestamp lnMdnEffTs;

	public Timestamp getLnMdnEffTs() {
		return lnMdnEffTs;
}
	public void setLnMdnEffTs(Timestamp lnMdnEffTs) {
		this.lnMdnEffTs = lnMdnEffTs;
	}

	@Column(name="LN_MDN_END_TS")
	public Timestamp lnMdnEndTs;

	public Timestamp getLnMdnEndTs() {
		return lnMdnEndTs;
}
	public void setLnMdnEndTs(Timestamp lnMdnEndTs) {
		this.lnMdnEndTs = lnMdnEndTs;
	}

}