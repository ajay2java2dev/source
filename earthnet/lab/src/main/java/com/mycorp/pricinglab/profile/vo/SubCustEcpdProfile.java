package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="SUB_CUST_ECPD_PROFILE")
@IdClass(SubCustEcpdProfilePK.class)
public class SubCustEcpdProfile implements Serializable {
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

	@Column(name="ECPD_PROFILE_ID")
	public Long ecpdProfileId;

	public Long getEcpdProfileId() {
		return ecpdProfileId;
}
	public void setEcpdProfileId(Long ecpdProfileId) {
		this.ecpdProfileId = ecpdProfileId;
	}

	@Column(name="LIABILITY_TYP_CD")
	public String liabilityTypCd;

	public String getLiabilityTypCd() {
		return liabilityTypCd;
}
	public void setLiabilityTypCd(String liabilityTypCd) {
		this.liabilityTypCd = liabilityTypCd;
	}

	@Id
	@Column(name="CUST_ECPD_EFF_DT")
	public Timestamp custEcpdEffDt;

	public Timestamp getCustEcpdEffDt() {
		return custEcpdEffDt;
}
	public void setCustEcpdEffDt(Timestamp custEcpdEffDt) {
		this.custEcpdEffDt = custEcpdEffDt;
	}

	@Column(name="CUST_ECPD_END_DT")
	public Timestamp custEcpdEndDt;

	public Timestamp getCustEcpdEndDt() {
		return custEcpdEndDt;
}
	public void setCustEcpdEndDt(Timestamp custEcpdEndDt) {
		this.custEcpdEndDt = custEcpdEndDt;
	}

	@Column(name="SUPRS_RECONN_NOTIF")
	public String suprsReconnNotif;

	public String getSuprsReconnNotif() {
		return suprsReconnNotif;
}
	public void setSuprsReconnNotif(String suprsReconnNotif) {
		this.suprsReconnNotif = suprsReconnNotif;
	}

	@Column(name="UBSR_USERID")
	public String ubsrUserid;

	public String getUbsrUserid() {
		return ubsrUserid;
}
	public void setUbsrUserid(String ubsrUserid) {
		this.ubsrUserid = ubsrUserid;
	}

	@Column(name="UBSR_TIMESTAMP")
	public Timestamp ubsrTimestamp;

	public Timestamp getUbsrTimestamp() {
		return ubsrTimestamp;
}
	public void setUbsrTimestamp(Timestamp ubsrTimestamp) {
		this.ubsrTimestamp = ubsrTimestamp;
	}

}