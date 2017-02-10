package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="SUB_DVC_SIM_INFO")
@IdClass(SubDvcSimInfoPK.class)
public class SubDvcSimInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="DEVICE_ID")
	public String deviceId;

	public String getDeviceId() {
		return deviceId;
}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Id
	@Column(name="DEVICE_ID_TYP")
	public String deviceIdTyp;

	public String getDeviceIdTyp() {
		return deviceIdTyp;
}
	public void setDeviceIdTyp(String deviceIdTyp) {
		this.deviceIdTyp = deviceIdTyp;
	}

	@Column(name="IMSI_VZW")
	public String imsiVzw;

	public String getImsiVzw() {
		return imsiVzw;
}
	public void setImsiVzw(String imsiVzw) {
		this.imsiVzw = imsiVzw;
	}

	@Column(name="IMSI_VF")
	public String imsiVf;

	public String getImsiVf() {
		return imsiVf;
}
	public void setImsiVf(String imsiVf) {
		this.imsiVf = imsiVf;
	}

	@Column(name="EUIMID")
	public String euimid;

	public String getEuimid() {
		return euimid;
}
	public void setEuimid(String euimid) {
		this.euimid = euimid;
	}

	@Column(name="SIM_INFO_END_DT")
	public Timestamp simInfoEndDt;

	public Timestamp getSimInfoEndDt() {
		return simInfoEndDt;
}
	public void setSimInfoEndDt(Timestamp simInfoEndDt) {
		this.simInfoEndDt = simInfoEndDt;
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