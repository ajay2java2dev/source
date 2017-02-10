package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="SUB_IMSI_MDN")
@IdClass(SubImsiMdnPK.class)
public class SubImsiMdn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="IMSI_VZW")
	public String imsiVzw;

	public String getImsiVzw() {
		return imsiVzw;
}
	public void setImsiVzw(String imsiVzw) {
		this.imsiVzw = imsiVzw;
	}

	@Id
	@Column(name="IMSI_MDN_EFF_TS_A")
	public Timestamp imsiMdnEffTsA;

	public Timestamp getImsiMdnEffTsA() {
		return imsiMdnEffTsA;
}
	public void setImsiMdnEffTsA(Timestamp imsiMdnEffTsA) {
		this.imsiMdnEffTsA = imsiMdnEffTsA;
	}

	@Column(name="IMSI_VF")
	public String imsiVf;

	public String getImsiVf() {
		return imsiVf;
}
	public void setImsiVf(String imsiVf) {
		this.imsiVf = imsiVf;
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

	@Column(name="IMSI_MDN_END_DT_A")
	public Timestamp imsiMdnEndDtA;

	public Timestamp getImsiMdnEndDtA() {
		return imsiMdnEndDtA;
}
	public void setImsiMdnEndDtA(Timestamp imsiMdnEndDtA) {
		this.imsiMdnEndDtA = imsiMdnEndDtA;
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

	@Column(name="RECON_TMSTAMP")
	public Timestamp reconTmstamp;

	public Timestamp getReconTmstamp() {
		return reconTmstamp;
}
	public void setReconTmstamp(Timestamp reconTmstamp) {
		this.reconTmstamp = reconTmstamp;
	}

	@Column(name="IMSI_MDN_EFF_DT")
	public Timestamp imsiMdnEffDt;

	public Timestamp getImsiMdnEffDt() {
		return imsiMdnEffDt;
}
	public void setImsiMdnEffDt(Timestamp imsiMdnEffDt) {
		this.imsiMdnEffDt = imsiMdnEffDt;
	}

	@Column(name="IMSI_MDN_END_DT")
	public Timestamp imsiMdnEndDt;

	public Timestamp getImsiMdnEndDt() {
		return imsiMdnEndDt;
}
	public void setImsiMdnEndDt(Timestamp imsiMdnEndDt) {
		this.imsiMdnEndDt = imsiMdnEndDt;
	}

	@Column(name="MDN")
	public String mdn;

	public String getMdn() {
		return mdn;
}
	public void setMdn(String mdn) {
		this.mdn = mdn;
	}

}