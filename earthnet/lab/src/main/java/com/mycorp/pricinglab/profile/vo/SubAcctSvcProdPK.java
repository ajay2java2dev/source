package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubAcctSvcProdPK implements Serializable {
	private static final long serialVersionUID = 1L;

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

	public SubAcctSvcProdPK() {}

	public SubAcctSvcProdPK(Long custIdNo, Long acctNo, Long svcProdId, Long svcProdUniqueId, Timestamp baSvcProdEffTs){
		 this.custIdNo = custIdNo;
		 this.acctNo = acctNo;
		 this.svcProdId = svcProdId;
		 this.svcProdUniqueId = svcProdUniqueId;
		 this.baSvcProdEffTs = baSvcProdEffTs;
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
		if (!(other instanceof SubAcctSvcProdPK)) {
			return false;
		}
		SubAcctSvcProdPK castOther = (SubAcctSvcProdPK)other;
		return this.custIdNo == castOther.custIdNo && this.acctNo == castOther.acctNo && this.svcProdId == castOther.svcProdId && this.svcProdUniqueId == castOther.svcProdUniqueId && this.baSvcProdEffTs.equals(castOther.baSvcProdEffTs);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.custIdNo ^ (this.custIdNo >>> 32)));
		hash = hash * prime + ((int) (this.acctNo ^ (this.acctNo >>> 32)));
		hash = hash * prime + ((int) (this.svcProdId ^ (this.svcProdId >>> 32)));
		hash = hash * prime + ((int) (this.svcProdUniqueId ^ (this.svcProdUniqueId >>> 32)));
		hash = hash * prime + this.baSvcProdEffTs.hashCode();
		return hash;
	}

}
