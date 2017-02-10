package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="SUB_IMSI_MDN_TNI_RSS")
@IdClass(SubImsiMdnTniRssPK.class)
public class SubImsiMdnTniRss implements Serializable {
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
	@Column(name="IMSI_MDN_EFF_DT")
	public Timestamp imsiMdnEffDt;

	public Timestamp getImsiMdnEffDt() {
		return imsiMdnEffDt;
}
	public void setImsiMdnEffDt(Timestamp imsiMdnEffDt) {
		this.imsiMdnEffDt = imsiMdnEffDt;
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

	@Column(name="ETNI_STATUS_CD")
	public String etniStatusCd;

	public String getEtniStatusCd() {
		return etniStatusCd;
}
	public void setEtniStatusCd(String etniStatusCd) {
		this.etniStatusCd = etniStatusCd;
	}

	@Column(name="IMSI_MDN_END_DT")
	public Timestamp imsiMdnEndDt;

	public Timestamp getImsiMdnEndDt() {
		return imsiMdnEndDt;
}
	public void setImsiMdnEndDt(Timestamp imsiMdnEndDt) {
		this.imsiMdnEndDt = imsiMdnEndDt;
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

	@Column(name="MDN")
	public String mdn;

	public String getMdn() {
		return mdn;
}
	public void setMdn(String mdn) {
		this.mdn = mdn;
	}

}