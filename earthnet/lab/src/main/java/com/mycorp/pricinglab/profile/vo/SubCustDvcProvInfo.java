package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="SUB_CUST_DVC_PROV_INFO")
@IdClass(SubCustDvcProvInfoPK.class)
public class SubCustDvcProvInfo implements Serializable {
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

	@Column(name="CUST_DVC_CRT_TS")
	public String custDvcCrtTs;

	public String getCustDvcCrtTs() {
		return custDvcCrtTs;
}
	public void setCustDvcCrtTs(String custDvcCrtTs) {
		this.custDvcCrtTs = custDvcCrtTs;
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

	@Column(name="ACTV_REQ_DT")
	public Timestamp actvReqDt;

	public Timestamp getActvReqDt() {
		return actvReqDt;
}
	public void setActvReqDt(Timestamp actvReqDt) {
		this.actvReqDt = actvReqDt;
	}

	@Column(name="SW_ACTV_TMSTAMP")
	public String swActvTmstamp;

	public String getSwActvTmstamp() {
		return swActvTmstamp;
}
	public void setSwActvTmstamp(String swActvTmstamp) {
		this.swActvTmstamp = swActvTmstamp;
	}

	@Column(name="DISCONN_REQ_DT")
	public Timestamp disconnReqDt;

	public Timestamp getDisconnReqDt() {
		return disconnReqDt;
}
	public void setDisconnReqDt(Timestamp disconnReqDt) {
		this.disconnReqDt = disconnReqDt;
	}

	@Column(name="SW_DISC_TMSTAMP")
	public String swDiscTmstamp;

	public String getSwDiscTmstamp() {
		return swDiscTmstamp;
}
	public void setSwDiscTmstamp(String swDiscTmstamp) {
		this.swDiscTmstamp = swDiscTmstamp;
	}

	@Column(name="RATE_CENTER_ID")
	public String rateCenterId;

	public String getRateCenterId() {
		return rateCenterId;
}
	public void setRateCenterId(String rateCenterId) {
		this.rateCenterId = rateCenterId;
	}

	@Column(name="ST_CD")
	public String stCd;

	public String getStCd() {
		return stCd;
}
	public void setStCd(String stCd) {
		this.stCd = stCd;
	}

	@Column(name="HANDICAP_IND")
	public String handicapInd;

	public String getHandicapInd() {
		return handicapInd;
}
	public void setHandicapInd(String handicapInd) {
		this.handicapInd = handicapInd;
	}

	@Column(name="PROV_TECHNOL_TYP")
	public String provTechnolTyp;

	public String getProvTechnolTyp() {
		return provTechnolTyp;
}
	public void setProvTechnolTyp(String provTechnolTyp) {
		this.provTechnolTyp = provTechnolTyp;
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