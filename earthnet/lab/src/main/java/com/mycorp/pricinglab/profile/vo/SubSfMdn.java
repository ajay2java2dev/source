package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="SUB_SF_MDN")
@IdClass(SubSfMdnPK.class)
public class SubSfMdn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CUST_ID_NO")
	public Long custIdNo;

	public Long getCustIdNo() {
		return custIdNo;
}
	public void setCustIdNo(Long custIdNo) {
		this.custIdNo = custIdNo;
	}

	@Id
	@Column(name="ACCT_NO")
	public Long acctNo;

	public Long getAcctNo() {
		return acctNo;
}
	public void setAcctNo(Long acctNo) {
		this.acctNo = acctNo;
	}

	@Id
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

	@Id
	@Column(name="SF_OFFERING_ID")
	public Long sfOfferingId;

	public Long getSfOfferingId() {
		return sfOfferingId;
}
	public void setSfOfferingId(Long sfOfferingId) {
		this.sfOfferingId = sfOfferingId;
	}

	@Id
	@Column(name="EFF_DT")
	public Timestamp effDt;

	public Timestamp getEffDt() {
		return effDt;
}
	public void setEffDt(Timestamp effDt) {
		this.effDt = effDt;
	}

	@Column(name="EFF_TMSTAMP")
	public Timestamp effTmstamp;

	public Timestamp getEffTmstamp() {
		return effTmstamp;
}
	public void setEffTmstamp(Timestamp effTmstamp) {
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
	public Timestamp endTmstamp;

	public Timestamp getEndTmstamp() {
		return endTmstamp;
}
	public void setEndTmstamp(Timestamp endTmstamp) {
		this.endTmstamp = endTmstamp;
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

	@Column(name="UBSR_TMSTAMP")
	public Timestamp ubsrTmstamp;

	public Timestamp getUbsrTmstamp() {
		return ubsrTmstamp;
}
	public void setUbsrTmstamp(Timestamp ubsrTmstamp) {
		this.ubsrTmstamp = ubsrTmstamp;
	}

	@Column(name="BILLING_RECON_TSTZ")
	public Timestamp billingReconTstz;

	public Timestamp getBillingReconTstz() {
		return billingReconTstz;
}
	public void setBillingReconTstz(Timestamp billingReconTstz) {
		this.billingReconTstz = billingReconTstz;
	}

	@Column(name="RATING_RECON_TSTZ")
	public Timestamp ratingReconTstz;

	public Timestamp getRatingReconTstz() {
		return ratingReconTstz;
}
	public void setRatingReconTstz(Timestamp ratingReconTstz) {
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