package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;

public class SubPrevCustMdnDelrows implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CUST_ID_NO")
	public Long custIdNo;

	public Long getCustIdNo() {
		return custIdNo;
}
	public void setCustIdNo(Long custIdNo) {
		this.custIdNo = custIdNo;
	}

	@Column(name="MDN")
	public String mdn;

	public String getMdn() {
		return mdn;
}
	public void setMdn(String mdn) {
		this.mdn = mdn;
	}

	@Column(name="MTN_EFF_DT")
	public Timestamp mtnEffDt;

	public Timestamp getMtnEffDt() {
		return mtnEffDt;
}
	public void setMtnEffDt(Timestamp mtnEffDt) {
		this.mtnEffDt = mtnEffDt;
	}

	@Column(name="EFF_TMSTAMP")
	public Timestamp effTmstamp;

	public Timestamp getEffTmstamp() {
		return effTmstamp;
}
	public void setEffTmstamp(Timestamp effTmstamp) {
		this.effTmstamp = effTmstamp;
	}

	@Column(name="PREV_CUST_ID_NO")
	public Long prevCustIdNo;

	public Long getPrevCustIdNo() {
		return prevCustIdNo;
}
	public void setPrevCustIdNo(Long prevCustIdNo) {
		this.prevCustIdNo = prevCustIdNo;
	}

	@Column(name="PREV_MDN")
	public String prevMdn;

	public String getPrevMdn() {
		return prevMdn;
}
	public void setPrevMdn(String prevMdn) {
		this.prevMdn = prevMdn;
	}

	@Column(name="PREV_MTN_EFF_DT")
	public Timestamp prevMtnEffDt;

	public Timestamp getPrevMtnEffDt() {
		return prevMtnEffDt;
}
	public void setPrevMtnEffDt(Timestamp prevMtnEffDt) {
		this.prevMtnEffDt = prevMtnEffDt;
	}

	@Column(name="PREV_RSHIP_TYP_CD")
	public String prevRshipTypCd;

	public String getPrevRshipTypCd() {
		return prevRshipTypCd;
}
	public void setPrevRshipTypCd(String prevRshipTypCd) {
		this.prevRshipTypCd = prevRshipTypCd;
	}

	@Column(name="UBSR_USERID")
	public String ubsrUserid;

	public String getUbsrUserid() {
		return ubsrUserid;
}
	public void setUbsrUserid(String ubsrUserid) {
		this.ubsrUserid = ubsrUserid;
	}

	@Column(name="UBSR_TMSTAMP")
	public Timestamp ubsrTmstamp;

	public Timestamp getUbsrTmstamp() {
		return ubsrTmstamp;
}
	public void setUbsrTmstamp(Timestamp ubsrTmstamp) {
		this.ubsrTmstamp = ubsrTmstamp;
	}

	@Column(name="DELETED_DT")
	public Timestamp deletedDt;

	public Timestamp getDeletedDt() {
		return deletedDt;
}
	public void setDeletedDt(Timestamp deletedDt) {
		this.deletedDt = deletedDt;
	}

}