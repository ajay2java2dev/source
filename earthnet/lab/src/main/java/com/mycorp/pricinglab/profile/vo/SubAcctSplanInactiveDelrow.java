package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;

public class SubAcctSplanInactiveDelrow implements Serializable {
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

	@Column(name="SPLAN_ID_NO")
	public Long splanIdNo;

	public Long getSplanIdNo() {
		return splanIdNo;
}
	public void setSplanIdNo(Long splanIdNo) {
		this.splanIdNo = splanIdNo;
	}

	@Column(name="BA_LN_ALL_INACT_EFF_DT")
	public String baLnAllInactEffDt;

	public String getBaLnAllInactEffDt() {
		return baLnAllInactEffDt;
}
	public void setBaLnAllInactEffDt(String baLnAllInactEffDt) {
		this.baLnAllInactEffDt = baLnAllInactEffDt;
	}

	@Column(name="BA_LN_SP_INACT_CRT_DT")
	public String baLnSpInactCrtDt;

	public String getBaLnSpInactCrtDt() {
		return baLnSpInactCrtDt;
}
	public void setBaLnSpInactCrtDt(String baLnSpInactCrtDt) {
		this.baLnSpInactCrtDt = baLnSpInactCrtDt;
	}

	@Column(name="BA_LN_ALL_INACT_END_DT")
	public String baLnAllInactEndDt;

	public String getBaLnAllInactEndDt() {
		return baLnAllInactEndDt;
}
	public void setBaLnAllInactEndDt(String baLnAllInactEndDt) {
		this.baLnAllInactEndDt = baLnAllInactEndDt;
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

	@Column(name="RBM_POP_IND")
	public String rbmPopInd;

	public String getRbmPopInd() {
		return rbmPopInd;
}
	public void setRbmPopInd(String rbmPopInd) {
		this.rbmPopInd = rbmPopInd;
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

	@Column(name="DELETED_DT")
	public Timestamp deletedDt;

	public Timestamp getDeletedDt() {
		return deletedDt;
}
	public void setDeletedDt(Timestamp deletedDt) {
		this.deletedDt = deletedDt;
	}

}