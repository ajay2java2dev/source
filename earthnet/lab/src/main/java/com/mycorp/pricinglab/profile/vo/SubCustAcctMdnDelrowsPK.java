package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class SubCustAcctMdnDelrowsPK implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Column(name="CUST_ID_NO")
	public Long custIdNo;

	public Long getCustIdNo() {
		return custIdNo;
}
	public void setCustIdNo(Long custIdNo) {
		this.custIdNo = custIdNo;
	}

	@Column(name="ACCT_NO")
	public Long acctNo;

	public Long getAcctNo() {
		return acctNo;
}
	public void setAcctNo(Long acctNo) {
		this.acctNo = acctNo;
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

	@Column(name="BL_TYP_CD")
	public String blTypCd;

	public String getBlTypCd() {
		return blTypCd;
}
	public void setBlTypCd(String blTypCd) {
		this.blTypCd = blTypCd;
	}

	@Column(name="CURR_PRI_IND")
	public String currPriInd;

	public String getCurrPriInd() {
		return currPriInd;
}
	public void setCurrPriInd(String currPriInd) {
		this.currPriInd = currPriInd;
	}

	@Column(name="EFF_DT")
	public Timestamp effDt;

	public Timestamp getEffDt() {
		return effDt;
}
	public void setEffDt(Timestamp effDt) {
		this.effDt = effDt;
	}

	@Column(name="CUST_MTN_STAT_CD")
	public String custMtnStatCd;

	public String getCustMtnStatCd() {
		return custMtnStatCd;
}
	public void setCustMtnStatCd(String custMtnStatCd) {
		this.custMtnStatCd = custMtnStatCd;
	}

	@Column(name="CREATE_DT")
	public Timestamp createDt;

	public Timestamp getCreateDt() {
		return createDt;
}
	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	
	@Column(name="UBSR_USERID")
	public String ubsrUserid;

	public String getUbsrUserid() {
		return ubsrUserid;
}
	public void setUbsrUserid(String ubsrUserid) {
		this.ubsrUserid = ubsrUserid;
	}

	
	@Column(name="DELETE_IND")
	public String deleteInd;

	public String getDeleteInd() {
		return deleteInd;
}
	public void setDeleteInd(String deleteInd) {
		this.deleteInd = deleteInd;
	}


}