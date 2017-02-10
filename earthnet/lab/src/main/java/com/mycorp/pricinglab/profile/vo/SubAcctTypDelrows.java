package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;

public class SubAcctTypDelrows implements Serializable {
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

	@Column(name="BL_TYP_STAT_CD")
	public String blTypStatCd;

	public String getBlTypStatCd() {
		return blTypStatCd;
}
	public void setBlTypStatCd(String blTypStatCd) {
		this.blTypStatCd = blTypStatCd;
	}

	@Column(name="EFF_DT")
	public String effDt;

	public String getEffDt() {
		return effDt;
}
	public void setEffDt(String effDt) {
		this.effDt = effDt;
	}

	@Column(name="END_DT")
	public String endDt;

	public String getEndDt() {
		return endDt;
}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}

	@Column(name="STAT_REASON_CD")
	public String statReasonCd;

	public String getStatReasonCd() {
		return statReasonCd;
}
	public void setStatReasonCd(String statReasonCd) {
		this.statReasonCd = statReasonCd;
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

	@Column(name="DELETE_IND")
	public String deleteInd;

	public String getDeleteInd() {
		return deleteInd;
}
	public void setDeleteInd(String deleteInd) {
		this.deleteInd = deleteInd;
	}

	@Column(name="BILLING_RECON_TSTZ")
	public String billingReconTstz;

	public String getBillingReconTstz() {
		return billingReconTstz;
}
	public void setBillingReconTstz(String billingReconTstz) {
		this.billingReconTstz = billingReconTstz;
	}

	@Column(name="RATING_RECON_TSTZ")
	public String ratingReconTstz;

	public String getRatingReconTstz() {
		return ratingReconTstz;
}
	public void setRatingReconTstz(String ratingReconTstz) {
		this.ratingReconTstz = ratingReconTstz;
	}

	@Column(name="LCL_CALL_SRCHG_IND")
	public String lclCallSrchgInd;

	public String getLclCallSrchgInd() {
		return lclCallSrchgInd;
}
	public void setLclCallSrchgInd(String lclCallSrchgInd) {
		this.lclCallSrchgInd = lclCallSrchgInd;
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