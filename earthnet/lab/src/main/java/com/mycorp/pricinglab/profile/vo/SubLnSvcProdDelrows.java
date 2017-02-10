package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="SUB_LN_SVC_PROD_DELROWS")
@IdClass(SubLnSvcProdDelrowsPK.class)
public class SubLnSvcProdDelrows implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SVC_PROD_ID")
	public Long svcProdId;

	public Long getSvcProdId() {
		return svcProdId;
}
	public void setSvcProdId(Long svcProdId) {
		this.svcProdId = svcProdId;
	}

	@Column(name="LN_SVC_PROD_EFF_TS")
	public String lnSvcProdEffTs;

	public String getLnSvcProdEffTs() {
		return lnSvcProdEffTs;
}
	public void setLnSvcProdEffTs(String lnSvcProdEffTs) {
		this.lnSvcProdEffTs = lnSvcProdEffTs;
	}

	@Column(name="LN_SVC_PROD_END_TS")
	public String lnSvcProdEndTs;

	public String getLnSvcProdEndTs() {
		return lnSvcProdEndTs;
}
	public void setLnSvcProdEndTs(String lnSvcProdEndTs) {
		this.lnSvcProdEndTs = lnSvcProdEndTs;
	}

	@Id
	@Column(name="LN_OF_SVC_ID_NO_P1")
	public Long lnOfSvcIdNoP1;

	public Long getLnOfSvcIdNoP1() {
		return lnOfSvcIdNoP1;
}
	public void setLnOfSvcIdNoP1(Long lnOfSvcIdNoP1) {
		this.lnOfSvcIdNoP1 = lnOfSvcIdNoP1;
	}

	@Id
	@Column(name="LN_OF_SVC_ID_NO_P2")
	public Long lnOfSvcIdNoP2;

	public Long getLnOfSvcIdNoP2() {
		return lnOfSvcIdNoP2;
}
	public void setLnOfSvcIdNoP2(Long lnOfSvcIdNoP2) {
		this.lnOfSvcIdNoP2 = lnOfSvcIdNoP2;
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

	@Column(name="CREATE_TS")
	public String createTs;

	public String getCreateTs() {
		return createTs;
}
	public void setCreateTs(String createTs) {
		this.createTs = createTs;
	}

	@Id
	@Column(name="LN_SVC_PROD_UNIQUE_ID")
	public Long lnSvcProdUniqueId;

	public Long getLnSvcProdUniqueId() {
		return lnSvcProdUniqueId;
}
	public void setLnSvcProdUniqueId(Long lnSvcProdUniqueId) {
		this.lnSvcProdUniqueId = lnSvcProdUniqueId;
	}

	@Column(name="ORIG_SVC_PROD_EFF_TS")
	public String origSvcProdEffTs;

	public String getOrigSvcProdEffTs() {
		return origSvcProdEffTs;
}
	public void setOrigSvcProdEffTs(String origSvcProdEffTs) {
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

	@Column(name="DELETE_DT")
	public Timestamp deleteDt;

	public Timestamp getDeleteDt() {
		return deleteDt;
}
	public void setDeleteDt(Timestamp deleteDt) {
		this.deleteDt = deleteDt;
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

}