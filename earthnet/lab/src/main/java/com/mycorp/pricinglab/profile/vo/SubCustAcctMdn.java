package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SUB_CUST_ACCT_MDN")
@IdClass(SubCustAcctMdnPK.class)
public class SubCustAcctMdn implements Serializable {
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
	@Column(name="BL_TYP_CD")
	public String blTypCd;

	public String getBlTypCd() {
		return blTypCd;
}
	public void setBlTypCd(String blTypCd) {
		this.blTypCd = blTypCd;
	}

	@Id
	@Column(name="CURR_PRI_IND")
	public String currPriInd;

	public String getCurrPriInd() {
		return currPriInd;
}
	public void setCurrPriInd(String currPriInd) {
		this.currPriInd = currPriInd;
	}

	@Id
	@Temporal(TemporalType.TIMESTAMP )
	@Column(name="EFF_DT")	
	public Timestamp effDt;

	public Timestamp getEffDt() {
		return effDt;
}
	public void setEffDt(Timestamp effDt) {
		this.effDt = effDt;
	}

	@Id
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

	@Column(name="END_DT")
	public Timestamp endDt;

	public Timestamp getEndDt() {
		return endDt;
}
	public void setEndDt(Timestamp endDt) {
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

	@Column(name="EFF_TIMESTAMP")
	public Timestamp effTimestamp;

	public Timestamp getEffTimestamp() {
		return effTimestamp;
}
	public void setEffTimestamp(Timestamp effTimestamp) {
		this.effTimestamp = effTimestamp;
	}

	@Column(name="END_TIMESTAMP")
	public Timestamp endTimestamp;

	public Timestamp getEndTimestamp() {
		return endTimestamp;
}
	public void setEndTimestamp(Timestamp endTimestamp) {
		this.endTimestamp = endTimestamp;
	}

	@Column(name="MIN_CARRIER")
	public String minCarrier;

	public String getMinCarrier() {
		return minCarrier;
}
	public void setMinCarrier(String minCarrier) {
		this.minCarrier = minCarrier;
	}

	@Column(name="MIN_L_DIG")
	public String minLDig;

	public String getMinLDig() {
		return minLDig;
}
	public void setMinLDig(String minLDig) {
		this.minLDig = minLDig;
	}

	@Column(name="MIN_REST_OF_L")
	public String minRestOfL;

	public String getMinRestOfL() {
		return minRestOfL;
}
	public void setMinRestOfL(String minRestOfL) {
		this.minRestOfL = minRestOfL;
	}

	@Column(name="AUTO_PORT_CD")
	public String autoPortCd;

	public String getAutoPortCd() {
		return autoPortCd;
}
	public void setAutoPortCd(String autoPortCd) {
		this.autoPortCd = autoPortCd;
	}

	@Column(name="PORT_IN_CONFIRM_CD")
	public String portInConfirmCd;

	public String getPortInConfirmCd() {
		return portInConfirmCd;
}
	public void setPortInConfirmCd(String portInConfirmCd) {
		this.portInConfirmCd = portInConfirmCd;
	}

	@Column(name="DIR_FILL_PORT_CD")
	public String dirFillPortCd;

	public String getDirFillPortCd() {
		return dirFillPortCd;
}
	public void setDirFillPortCd(String dirFillPortCd) {
		this.dirFillPortCd = dirFillPortCd;
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