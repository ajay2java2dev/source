package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubSpoParticipateLnPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="LN_OF_SVC_ID_NO_P1")
	protected Long	lnOfSvcIdNoP1;

	@Column(name="LN_OF_SVC_ID_NO_P2")
	protected Long	lnOfSvcIdNoP2;

	@Column(name="BA_SVC_PROD_LN_EFF_TS")
	protected Timestamp	baSvcProdLnEffTs;

	@Column(name="CUST_ID_NO")
	protected Long	custIdNo;

	@Column(name="ACCT_NO")
	protected Long	acctNo;

	@Column(name="SVC_PROD_ID")
	protected Long	svcProdId;

	@Column(name="SVC_PROD_UNIQUE_ID")
	protected Long	svcProdUniqueId;

	@Column(name="BA_SVC_PROD_EFF_TS")
	protected Timestamp	baSvcProdEffTs;

	public SubSpoParticipateLnPK() {}

	public SubSpoParticipateLnPK(Long lnOfSvcIdNoP1, Long lnOfSvcIdNoP2, Timestamp baSvcProdLnEffTs, Long custIdNo, Long acctNo, Long svcProdId, Long svcProdUniqueId, Timestamp baSvcProdEffTs){
		 this.lnOfSvcIdNoP1 = lnOfSvcIdNoP1;
		 this.lnOfSvcIdNoP2 = lnOfSvcIdNoP2;
		 this.baSvcProdLnEffTs = baSvcProdLnEffTs;
		 this.custIdNo = custIdNo;
		 this.acctNo = acctNo;
		 this.svcProdId = svcProdId;
		 this.svcProdUniqueId = svcProdUniqueId;
		 this.baSvcProdEffTs = baSvcProdEffTs;
	}

	public Long getLnOfSvcIdNoP1() {
		return this.lnOfSvcIdNoP1;
	}

	public void setLnOfSvcIdNoP1(Long lnOfSvcIdNoP1) {
		this.lnOfSvcIdNoP1 = lnOfSvcIdNoP1;
	}

	public Long getLnOfSvcIdNoP2() {
		return this.lnOfSvcIdNoP2;
	}

	public void setLnOfSvcIdNoP2(Long lnOfSvcIdNoP2) {
		this.lnOfSvcIdNoP2 = lnOfSvcIdNoP2;
	}

	public Timestamp getBaSvcProdLnEffTs() {
		return this.baSvcProdLnEffTs;
	}

	public void setBaSvcProdLnEffTs(Timestamp baSvcProdLnEffTs) {
		this.baSvcProdLnEffTs = baSvcProdLnEffTs;
	}

	public Long getCustIdNo() {
		return this.custIdNo;
	}

	public void setCustIdNo(Long custIdNo) {
		this.custIdNo = custIdNo;
	}

	public Long getAcctNo() {
		return this.acctNo;
	}

	public void setAcctNo(Long acctNo) {
		this.acctNo = acctNo;
	}

	public Long getSvcProdId() {
		return this.svcProdId;
	}

	public void setSvcProdId(Long svcProdId) {
		this.svcProdId = svcProdId;
	}

	public Long getSvcProdUniqueId() {
		return this.svcProdUniqueId;
	}

	public void setSvcProdUniqueId(Long svcProdUniqueId) {
		this.svcProdUniqueId = svcProdUniqueId;
	}

	public Timestamp getBaSvcProdEffTs() {
		return this.baSvcProdEffTs;
	}

	public void setBaSvcProdEffTs(Timestamp baSvcProdEffTs) {
		this.baSvcProdEffTs = baSvcProdEffTs;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SubSpoParticipateLnPK)) {
			return false;
		}
		SubSpoParticipateLnPK castOther = (SubSpoParticipateLnPK)other;
		return this.lnOfSvcIdNoP1 == castOther.lnOfSvcIdNoP1 && this.lnOfSvcIdNoP2 == castOther.lnOfSvcIdNoP2 && this.baSvcProdLnEffTs.equals(castOther.baSvcProdLnEffTs) && this.custIdNo == castOther.custIdNo && this.acctNo == castOther.acctNo && this.svcProdId == castOther.svcProdId && this.svcProdUniqueId == castOther.svcProdUniqueId && this.baSvcProdEffTs.equals(castOther.baSvcProdEffTs);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.lnOfSvcIdNoP1 ^ (this.lnOfSvcIdNoP1 >>> 32)));
		hash = hash * prime + ((int) (this.lnOfSvcIdNoP2 ^ (this.lnOfSvcIdNoP2 >>> 32)));
		hash = hash * prime + this.baSvcProdLnEffTs.hashCode();
		hash = hash * prime + ((int) (this.custIdNo ^ (this.custIdNo >>> 32)));
		hash = hash * prime + ((int) (this.acctNo ^ (this.acctNo >>> 32)));
		hash = hash * prime + ((int) (this.svcProdId ^ (this.svcProdId >>> 32)));
		hash = hash * prime + ((int) (this.svcProdUniqueId ^ (this.svcProdUniqueId >>> 32)));
		hash = hash * prime + this.baSvcProdEffTs.hashCode();
		return hash;
	}

}
