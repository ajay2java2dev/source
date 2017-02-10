package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="SUB_CUST_DVC_EQP_TRANS")
@IdClass(SubCustDvcEqpTransPK.class)
public class SubCustDvcEqpTrans implements Serializable {
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
	public String mtnEffDt;

	public String getMtnEffDt() {
		return mtnEffDt;
}
	public void setMtnEffDt(String mtnEffDt) {
		this.mtnEffDt = mtnEffDt;
	}

	@Column(name="DVC_EQP_TRANS_TS")
	public String dvcEqpTransTs;

	public String getDvcEqpTransTs() {
		return dvcEqpTransTs;
}
	public void setDvcEqpTransTs(String dvcEqpTransTs) {
		this.dvcEqpTransTs = dvcEqpTransTs;
	}

	@Column(name="DEVICE_ID")
	public String deviceId;

	public String getDeviceId() {
		return deviceId;
}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Column(name="DEVICE_ID_TYP")
	public String deviceIdTyp;

	public String getDeviceIdTyp() {
		return deviceIdTyp;
}
	public void setDeviceIdTyp(String deviceIdTyp) {
		this.deviceIdTyp = deviceIdTyp;
	}

	@Column(name="PROD_ID")
	public Long prodId;

	public Long getProdId() {
		return prodId;
}
	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	@Column(name="TERMNL_OPT_CD")
	public String termnlOptCd;

	public String getTermnlOptCd() {
		return termnlOptCd;
}
	public void setTermnlOptCd(String termnlOptCd) {
		this.termnlOptCd = termnlOptCd;
	}

	@Column(name="WARR_TERM_CD")
	public String warrTermCd;

	public String getWarrTermCd() {
		return warrTermCd;
}
	public void setWarrTermCd(String warrTermCd) {
		this.warrTermCd = warrTermCd;
	}

	@Column(name="WARR_START_DT")
	public Timestamp warrStartDt;

	public Timestamp getWarrStartDt() {
		return warrStartDt;
}
	public void setWarrStartDt(Timestamp warrStartDt) {
		this.warrStartDt = warrStartDt;
	}

	@Column(name="PHONE_TYP_CD")
	public String phoneTypCd;

	public String getPhoneTypCd() {
		return phoneTypCd;
}
	public void setPhoneTypCd(String phoneTypCd) {
		this.phoneTypCd = phoneTypCd;
	}

	@Column(name="DVC_TRANS_RSN_CD")
	public String dvcTransRsnCd;

	public String getDvcTransRsnCd() {
		return dvcTransRsnCd;
}
	public void setDvcTransRsnCd(String dvcTransRsnCd) {
		this.dvcTransRsnCd = dvcTransRsnCd;
	}

	@Column(name="DEVICE_TYP")
	public String deviceTyp;

	public String getDeviceTyp() {
		return deviceTyp;
}
	public void setDeviceTyp(String deviceTyp) {
		this.deviceTyp = deviceTyp;
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

}