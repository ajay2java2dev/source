package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;

public class SubCustBlCycleTest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CUST_ID_NO")
	public Long custIdNo;

	public Long getCustIdNo() {
		return custIdNo;
}
	public void setCustIdNo(Long custIdNo) {
		this.custIdNo = custIdNo;
	}

	@Column(name="BL_CYC_NO")
	public String blCycNo;

	public String getBlCycNo() {
		return blCycNo;
}
	public void setBlCycNo(String blCycNo) {
		this.blCycNo = blCycNo;
	}

	@Column(name="BL_TYP_CD")
	public String blTypCd;

	public String getBlTypCd() {
		return blTypCd;
}
	public void setBlTypCd(String blTypCd) {
		this.blTypCd = blTypCd;
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

	@Column(name="DELETE_IND")
	public String deleteInd;

	public String getDeleteInd() {
		return deleteInd;
}
	public void setDeleteInd(String deleteInd) {
		this.deleteInd = deleteInd;
	}

	@Column(name="RBM_POP_IND")
	public String rbmPopInd;

	public String getRbmPopInd() {
		return rbmPopInd;
}
	public void setRbmPopInd(String rbmPopInd) {
		this.rbmPopInd = rbmPopInd;
	}

}