package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubAcctTypPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="BL_TYP_CD")
	protected String	blTypCd;

	@Column(name="CURR_PRI_IND")
	protected String	currPriInd;

	@Column(name="CUST_ID_NO")
	protected Long	custIdNo;

	@Column(name="ACCT_NO")
	protected Long	acctNo;

	@Column(name="BL_TYP_STAT_CD")
	protected String	blTypStatCd;

	public SubAcctTypPK() {}

	public SubAcctTypPK(String blTypCd, String currPriInd, Long custIdNo, Long acctNo, String blTypStatCd){
		 this.blTypCd = blTypCd;
		 this.currPriInd = currPriInd;
		 this.custIdNo = custIdNo;
		 this.acctNo = acctNo;
		 this.blTypStatCd = blTypStatCd;
	}

	public String getBlTypCd() {
		return this.blTypCd;
	}

	public void setBlTypCd(String blTypCd) {
		this.blTypCd = blTypCd;
	}

	public String getCurrPriInd() {
		return this.currPriInd;
	}

	public void setCurrPriInd(String currPriInd) {
		this.currPriInd = currPriInd;
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

	public String getBlTypStatCd() {
		return this.blTypStatCd;
	}

	public void setBlTypStatCd(String blTypStatCd) {
		this.blTypStatCd = blTypStatCd;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SubAcctTypPK)) {
			return false;
		}
		SubAcctTypPK castOther = (SubAcctTypPK)other;
		return this.blTypCd.equals(castOther.blTypCd) && this.currPriInd.equals(castOther.currPriInd) && this.custIdNo == castOther.custIdNo && this.acctNo == castOther.acctNo && this.blTypStatCd.equals(castOther.blTypStatCd);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.blTypCd.hashCode();
		hash = hash * prime + this.currPriInd.hashCode();
		hash = hash * prime + ((int) (this.custIdNo ^ (this.custIdNo >>> 32)));
		hash = hash * prime + ((int) (this.acctNo ^ (this.acctNo >>> 32)));
		hash = hash * prime + this.blTypStatCd.hashCode();
		return hash;
	}

}
