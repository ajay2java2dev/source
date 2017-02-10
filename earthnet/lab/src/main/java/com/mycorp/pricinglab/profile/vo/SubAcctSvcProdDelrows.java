package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;

public class SubAcctSvcProdDelrows implements Serializable {
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

	@Column(name="SVC_PROD_ID")
	public Long svcProdId;

	public Long getSvcProdId() {
		return svcProdId;
}
	public void setSvcProdId(Long svcProdId) {
		this.svcProdId = svcProdId;
	}

	@Column(name="BA_SVC_PROD_EFF_TS")
	public Timestamp baSvcProdEffTs;

	public Timestamp getBaSvcProdEffTs() {
		return baSvcProdEffTs;
}
	public void setBaSvcProdEffTs(Timestamp baSvcProdEffTs) {
		this.baSvcProdEffTs = baSvcProdEffTs;
	}

	@Column(name="SVC_PROD_UNIQUE_ID")
	public Long svcProdUniqueId;

	public Long getSvcProdUniqueId() {
		return svcProdUniqueId;
}
	public void setSvcProdUniqueId(Long svcProdUniqueId) {
		this.svcProdUniqueId = svcProdUniqueId;
	}

	@Column(name="SVC_PROD_EXPIRE_DT")
	public Timestamp svcProdExpireDt;

	public Timestamp getSvcProdExpireDt() {
		return svcProdExpireDt;
}
	public void setSvcProdExpireDt(Timestamp svcProdExpireDt) {
		this.svcProdExpireDt = svcProdExpireDt;
	}

	@Column(name="SVC_PROD_REINSTAT_IND")
	public String svcProdReinstatInd;

	public String getSvcProdReinstatInd() {
		return svcProdReinstatInd;
}
	public void setSvcProdReinstatInd(String svcProdReinstatInd) {
		this.svcProdReinstatInd = svcProdReinstatInd;
	}

	@Column(name="RULE_GRP_ID_LAST")
	public Long ruleGrpIdLast;

	public Long getRuleGrpIdLast() {
		return ruleGrpIdLast;
}
	public void setRuleGrpIdLast(Long ruleGrpIdLast) {
		this.ruleGrpIdLast = ruleGrpIdLast;
	}

	@Column(name="BA_SVC_PROD_END_TS")
	public Timestamp baSvcProdEndTs;

	public Timestamp getBaSvcProdEndTs() {
		return baSvcProdEndTs;
}
	public void setBaSvcProdEndTs(Timestamp baSvcProdEndTs) {
		this.baSvcProdEndTs = baSvcProdEndTs;
	}

	@Column(name="ORIG_SVC_PROD_EFF_TS")
	public Timestamp origSvcProdEffTs;

	public Timestamp getOrigSvcProdEffTs() {
		return origSvcProdEffTs;
}
	public void setOrigSvcProdEffTs(Timestamp origSvcProdEffTs) {
		this.origSvcProdEffTs = origSvcProdEffTs;
	}

	@Column(name="ORIG_SVC_PROD_UNIQ_ID")
	public Long origSvcProdUniqId;

	public Long getOrigSvcProdUniqId() {
		return origSvcProdUniqId;
}
	public void setOrigSvcProdUniqId(Long origSvcProdUniqId) {
		this.origSvcProdUniqId = origSvcProdUniqId;
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

	@Column(name="JITR_RATING_STATUS")
	public String jitrRatingStatus;

	public String getJitrRatingStatus() {
		return jitrRatingStatus;
}
	public void setJitrRatingStatus(String jitrRatingStatus) {
		this.jitrRatingStatus = jitrRatingStatus;
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