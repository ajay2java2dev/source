package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="SUB_SPO_PARTICIPATE_LN")
@IdClass(SubSpoParticipateLnPK.class)
public class SubSpoParticipateLn implements Serializable {
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
	@Column(name="SVC_PROD_ID")
	public Long svcProdId;

	public Long getSvcProdId() {
		return svcProdId;
}
	public void setSvcProdId(Long svcProdId) {
		this.svcProdId = svcProdId;
	}

	@Id
	@Column(name="BA_SVC_PROD_EFF_TS")
	public Timestamp baSvcProdEffTs;

	public Timestamp getBaSvcProdEffTs() {
		return baSvcProdEffTs;
}
	public void setBaSvcProdEffTs(Timestamp baSvcProdEffTs) {
		this.baSvcProdEffTs = baSvcProdEffTs;
	}

	@Id
	@Column(name="SVC_PROD_UNIQUE_ID")
	public Long svcProdUniqueId;

	public Long getSvcProdUniqueId() {
		return svcProdUniqueId;
}
	public void setSvcProdUniqueId(Long svcProdUniqueId) {
		this.svcProdUniqueId = svcProdUniqueId;
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
	@Column(name="BA_SVC_PROD_LN_EFF_TS")
	public Timestamp baSvcProdLnEffTs;

	public Timestamp getBaSvcProdLnEffTs() {
		return baSvcProdLnEffTs;
}
	public void setBaSvcProdLnEffTs(Timestamp baSvcProdLnEffTs) {
		this.baSvcProdLnEffTs = baSvcProdLnEffTs;
	}

	@Column(name="CREATE_TS")
	public Timestamp createTs;

	public Timestamp getCreateTs() {
		return createTs;
}
	public void setCreateTs(Timestamp createTs) {
		this.createTs = createTs;
	}

	@Column(name="BA_SVC_PROD_LN_END_TS")
	public Timestamp baSvcProdLnEndTs;

	public Timestamp getBaSvcProdLnEndTs() {
		return baSvcProdLnEndTs;
}
	public void setBaSvcProdLnEndTs(Timestamp baSvcProdLnEndTs) {
		this.baSvcProdLnEndTs = baSvcProdLnEndTs;
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

}