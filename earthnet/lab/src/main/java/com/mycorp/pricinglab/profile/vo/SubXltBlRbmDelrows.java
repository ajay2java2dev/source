package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@Table(name="SUB_XLT_BL_RBM_DELROWS" , schema="UBSR")
@IdClass(SubXltBlRbmDelrowsPK.class)
public class SubXltBlRbmDelrows implements Serializable {
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

	@Id
	@Column(name="BL_ID_TYP")
	public String blIdTyp;

	public String getBlIdTyp() {
		return blIdTyp;
}
	public void setBlIdTyp(String blIdTyp) {
		this.blIdTyp = blIdTyp;
	}

	@Id
	@Column(name="BL_ID_VALUE")
	public String blIdValue;

	public String getBlIdValue() {
		return blIdValue;
}
	public void setBlIdValue(String blIdValue) {
		this.blIdValue = blIdValue;
	}

	@Id
	@Column(name="RBM_ID_TYP")
	public String rbmIdTyp;

	public String getRbmIdTyp() {
		return rbmIdTyp;
}
	public void setRbmIdTyp(String rbmIdTyp) {
		this.rbmIdTyp = rbmIdTyp;
	}

	@Id
	@Column(name="RBM_ID_VALUE")
	public String rbmIdValue;

	public String getRbmIdValue() {
		return rbmIdValue;
}
	public void setRbmIdValue(String rbmIdValue) {
		this.rbmIdValue = rbmIdValue;
	}

	@Id
	@Column(name="EFF_TMSTAMP")
	public Timestamp effTmstamp;

	public Timestamp getEffTmstamp() {
		return effTmstamp;
}
	public void setEffTmstamp(Timestamp effTmstamp) {
		this.effTmstamp = effTmstamp;
	}

	@Column(name="END_TMSTAMP")
	public Timestamp endTmstamp;

	public Timestamp getEndTmstamp() {
		return endTmstamp;
}
	public void setEndTmstamp(Timestamp endTmstamp) {
		this.endTmstamp = endTmstamp;
	}

	@Column(name="RBM_SEQUENCE_ID")
	public String rbmSequenceId;

	public String getRbmSequenceId() {
		return rbmSequenceId;
}
	public void setRbmSequenceId(String rbmSequenceId) {
		this.rbmSequenceId = rbmSequenceId;
	}

	@Column(name="RBM_EFF_TMSTAMP")
	public Timestamp rbmEffTmstamp;

	public Timestamp getRbmEffTmstamp() {
		return rbmEffTmstamp;
}
	public void setRbmEffTmstamp(Timestamp rbmEffTmstamp) {
		this.rbmEffTmstamp = rbmEffTmstamp;
	}

	@Column(name="RBM_END_TMSTAMP")
	public Timestamp rbmEndTmstamp;

	public Timestamp getRbmEndTmstamp() {
		return rbmEndTmstamp;
}
	public void setRbmEndTmstamp(Timestamp rbmEndTmstamp) {
		this.rbmEndTmstamp = rbmEndTmstamp;
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

	@Column(name="DELETED_DT")
	public Timestamp deletedDt;

	public Timestamp getDeletedDt() {
		return deletedDt;
}
	public void setDeletedDt(Timestamp deletedDt) {
		this.deletedDt = deletedDt;
	}

}