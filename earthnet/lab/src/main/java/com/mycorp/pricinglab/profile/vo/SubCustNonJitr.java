package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="SUB_CUST_NON_JITR")
@IdClass(SubCustNonJitrPK.class)
public class SubCustNonJitr implements Serializable {
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

	@Column(name="CUST_TYP_CD")
	public String custTypCd;

	public String getCustTypCd() {
		return custTypCd;
}
	public void setCustTypCd(String custTypCd) {
		this.custTypCd = custTypCd;
	}

	@Column(name="CUST_CLASS_CD")
	public String custClassCd;

	public String getCustClassCd() {
		return custClassCd;
}
	public void setCustClassCd(String custClassCd) {
		this.custClassCd = custClassCd;
	}

	@Column(name="BSNS_CONSUMER_CD")
	public String bsnsConsumerCd;

	public String getBsnsConsumerCd() {
		return bsnsConsumerCd;
}
	public void setBsnsConsumerCd(String bsnsConsumerCd) {
		this.bsnsConsumerCd = bsnsConsumerCd;
	}

	@Column(name="SUPRS_CALL_TN_IND")
	public String suprsCallTnInd;

	public String getSuprsCallTnInd() {
		return suprsCallTnInd;
}
	public void setSuprsCallTnInd(String suprsCallTnInd) {
		this.suprsCallTnInd = suprsCallTnInd;
	}

	@Column(name="INCMP_CALL_CHG_IND")
	public String incmpCallChgInd;

	public String getIncmpCallChgInd() {
		return incmpCallChgInd;
}
	public void setIncmpCallChgInd(String incmpCallChgInd) {
		this.incmpCallChgInd = incmpCallChgInd;
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