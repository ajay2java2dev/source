package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="SUB_MDN_MIN")
@IdClass(SubMdnMinPK.class)
public class SubMdnMin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NPA")
	public String npa;

	public String getNpa() {
		return npa;
}
	public void setNpa(String npa) {
		this.npa = npa;
	}

	@Id
	@Column(name="NXX")
	public String nxx;

	public String getNxx() {
		return nxx;
}
	public void setNxx(String nxx) {
		this.nxx = nxx;
	}

	@Id
	@Column(name="TLN")
	public String tln;

	public String getTln() {
		return tln;
}
	public void setTln(String tln) {
		this.tln = tln;
	}

	@Id
	@Column(name="MDN_MIN_EFF_DT_A")
	public Timestamp mdnMinEffDtA;

	public Timestamp getMdnMinEffDtA() {
		return mdnMinEffDtA;
}
	public void setMdnMinEffDtA(Timestamp mdnMinEffDtA) {
		this.mdnMinEffDtA = mdnMinEffDtA;
	}

	@Column(name="MIN_CARRIER")
	public String minCarrier;

	public String getMinCarrier() {
		return minCarrier;
}
	public void setMinCarrier(String minCarrier) {
		this.minCarrier = minCarrier;
	}

	@Column(name="MIN_L_DIG")
	public String minLDig;

	public String getMinLDig() {
		return minLDig;
}
	public void setMinLDig(String minLDig) {
		this.minLDig = minLDig;
	}

	@Column(name="MIN_REST_OF_L")
	public String minRestOfL;

	public String getMinRestOfL() {
		return minRestOfL;
}
	public void setMinRestOfL(String minRestOfL) {
		this.minRestOfL = minRestOfL;
	}

	@Column(name="MDN_VIS_INST_CD")
	public String mdnVisInstCd;

	public String getMdnVisInstCd() {
		return mdnVisInstCd;
}
	public void setMdnVisInstCd(String mdnVisInstCd) {
		this.mdnVisInstCd = mdnVisInstCd;
	}

	@Column(name="HOME_SBID")
	public String homeSbid;

	public String getHomeSbid() {
		return homeSbid;
}
	public void setHomeSbid(String homeSbid) {
		this.homeSbid = homeSbid;
	}

	@Column(name="HOME_SBID_INST_CD")
	public String homeSbidInstCd;

	public String getHomeSbidInstCd() {
		return homeSbidInstCd;
}
	public void setHomeSbidInstCd(String homeSbidInstCd) {
		this.homeSbidInstCd = homeSbidInstCd;
	}

	@Column(name="PREPAID_IND")
	public String prepaidInd;

	public String getPrepaidInd() {
		return prepaidInd;
}
	public void setPrepaidInd(String prepaidInd) {
		this.prepaidInd = prepaidInd;
	}

	@Column(name="RECON_TMSTAMP")
	public Timestamp reconTmstamp;

	public Timestamp getReconTmstamp() {
		return reconTmstamp;
}
	public void setReconTmstamp(Timestamp reconTmstamp) {
		this.reconTmstamp = reconTmstamp;
	}

	@Column(name="MDN_MIN_END_DT_A")
	public Timestamp mdnMinEndDtA;

	public Timestamp getMdnMinEndDtA() {
		return mdnMinEndDtA;
}
	public void setMdnMinEndDtA(Timestamp mdnMinEndDtA) {
		this.mdnMinEndDtA = mdnMinEndDtA;
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

	@Column(name="MDN_MIN_EFF_DT")
	public Timestamp mdnMinEffDt;

	public Timestamp getMdnMinEffDt() {
		return mdnMinEffDt;
}
	public void setMdnMinEffDt(Timestamp mdnMinEffDt) {
		this.mdnMinEffDt = mdnMinEffDt;
	}

	@Column(name="MDN_MIN_END_DT")
	public Timestamp mdnMinEndDt;

	public Timestamp getMdnMinEndDt() {
		return mdnMinEndDt;
}
	public void setMdnMinEndDt(Timestamp mdnMinEndDt) {
		this.mdnMinEndDt = mdnMinEndDt;
	}

}