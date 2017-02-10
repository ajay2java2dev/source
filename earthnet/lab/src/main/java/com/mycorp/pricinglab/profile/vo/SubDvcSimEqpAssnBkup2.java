package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;

public class SubDvcSimEqpAssnBkup2 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="DEVICE_ID_SIM")
	public String deviceIdSim;

	public String getDeviceIdSim() {
		return deviceIdSim;
}
	public void setDeviceIdSim(String deviceIdSim) {
		this.deviceIdSim = deviceIdSim;
	}

	@Column(name="DEVICE_ID_TYP_SIM")
	public String deviceIdTypSim;

	public String getDeviceIdTypSim() {
		return deviceIdTypSim;
}
	public void setDeviceIdTypSim(String deviceIdTypSim) {
		this.deviceIdTypSim = deviceIdTypSim;
	}

	@Column(name="DEVICE_ASSN_EFF_TS")
	public String deviceAssnEffTs;

	public String getDeviceAssnEffTs() {
		return deviceAssnEffTs;
}
	public void setDeviceAssnEffTs(String deviceAssnEffTs) {
		this.deviceAssnEffTs = deviceAssnEffTs;
	}

	@Column(name="DEVICE_ID_EQP")
	public String deviceIdEqp;

	public String getDeviceIdEqp() {
		return deviceIdEqp;
}
	public void setDeviceIdEqp(String deviceIdEqp) {
		this.deviceIdEqp = deviceIdEqp;
	}

	@Column(name="DEVICE_ID_TYP_EQP")
	public String deviceIdTypEqp;

	public String getDeviceIdTypEqp() {
		return deviceIdTypEqp;
}
	public void setDeviceIdTypEqp(String deviceIdTypEqp) {
		this.deviceIdTypEqp = deviceIdTypEqp;
	}

	@Column(name="PROD_ID")
	public Long prodId;

	public Long getProdId() {
		return prodId;
}
	public void setProdId(Long prodId) {
		this.prodId = prodId;
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

	@Column(name="DEVICE_ASSN_EFF_DT")
	public Timestamp deviceAssnEffDt;

	public Timestamp getDeviceAssnEffDt() {
		return deviceAssnEffDt;
}
	public void setDeviceAssnEffDt(Timestamp deviceAssnEffDt) {
		this.deviceAssnEffDt = deviceAssnEffDt;
	}

}