package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;

public class SubSfMdnRam implements Serializable {
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
	public String mtnEffDt;

	public String getMtnEffDt() {
		return mtnEffDt;
}
	public void setMtnEffDt(String mtnEffDt) {
		this.mtnEffDt = mtnEffDt;
	}

	@Column(name="SF_OFFERING_ID")
	public Long sfOfferingId;

	public Long getSfOfferingId() {
		return sfOfferingId;
}
	public void setSfOfferingId(Long sfOfferingId) {
		this.sfOfferingId = sfOfferingId;
	}

	@Column(name="EFF_DT")
	public Timestamp effDt;

	public Timestamp getEffDt() {
		return effDt;
}
	public void setEffDt(Timestamp effDt) {
		this.effDt = effDt;
	}

	@Column(name="EFF_TMSTAMP")
	public String effTmstamp;

	public String getEffTmstamp() {
		return effTmstamp;
}
	public void setEffTmstamp(String effTmstamp) {
		this.effTmstamp = effTmstamp;
	}

	@Column(name="END_DT")
	public Timestamp endDt;

	public Timestamp getEndDt() {
		return endDt;
}
	public void setEndDt(Timestamp endDt) {
		this.endDt = endDt;
	}

	@Column(name="END_TMSTAMP")
	public String endTmstamp;

	public String getEndTmstamp() {
		return endTmstamp;
}
	public void setEndTmstamp(String endTmstamp) {
		this.endTmstamp = endTmstamp;
	}

	@Column(name="CREATE_DT")
	public String createDt;

	public String getCreateDt() {
		return createDt;
}
	public void setCreateDt(String createDt) {
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

}