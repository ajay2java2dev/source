package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubCustEcpdProfilePK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CUST_ID_NO")
	protected Long	custIdNo;

	@Column(name="CUST_ECPD_EFF_DT")
	protected Timestamp	custEcpdEffDt;

	public SubCustEcpdProfilePK() {}

	public SubCustEcpdProfilePK(Long custIdNo, Timestamp custEcpdEffDt){
		 this.custIdNo = custIdNo;
		 this.custEcpdEffDt = custEcpdEffDt;
	}

	public Long getCustIdNo() {
		return this.custIdNo;
	}

	public void setCustIdNo(Long custIdNo) {
		this.custIdNo = custIdNo;
	}

	public Timestamp getCustEcpdEffDt() {
		return this.custEcpdEffDt;
	}

	public void setCustEcpdEffDt(Timestamp custEcpdEffDt) {
		this.custEcpdEffDt = custEcpdEffDt;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SubCustEcpdProfilePK)) {
			return false;
		}
		SubCustEcpdProfilePK castOther = (SubCustEcpdProfilePK)other;
		return this.custIdNo == castOther.custIdNo && this.custEcpdEffDt.equals(castOther.custEcpdEffDt);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.custIdNo ^ (this.custIdNo >>> 32)));
		hash = hash * prime + this.custEcpdEffDt.hashCode();
		return hash;
	}

}
