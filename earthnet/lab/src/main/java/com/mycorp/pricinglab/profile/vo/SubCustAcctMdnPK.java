package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubCustAcctMdnPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="BL_TYP_CD")
	protected String	blTypCd;

	@Column(name="EFF_DT")
	protected Timestamp	effDt;

	/*@Column(name="CURR_PRI_IND")
	protected String	currPriInd;*/

	@Column(name="CUST_MTN_STAT_CD")
	protected String	custMtnStatCd;

	@Column(name="CUST_ID_NO")
	protected Long	custIdNo;

	@Column(name="ACCT_NO")
	protected Long	acctNo;

	@Column(name="MDN")
	protected String	mdn;

	public SubCustAcctMdnPK() {}

	public SubCustAcctMdnPK(String blTypCd, Timestamp effDt, String currPriInd, String custMtnStatCd, Long custIdNo, Long acctNo, String mdn){
		 this.blTypCd = blTypCd;
		 this.effDt = effDt;
		 //this.currPriInd = currPriInd;
		 this.custMtnStatCd = custMtnStatCd;
		 this.custIdNo = custIdNo;
		 this.acctNo = acctNo;
		 this.mdn = mdn;
	}

	public String getBlTypCd() {
		return this.blTypCd;
	}

	public void setBlTypCd(String blTypCd) {
		this.blTypCd = blTypCd;
	}

	public Timestamp getEffDt() {
		return this.effDt;
	}

	public void setEffDt(Timestamp effDt) {
		this.effDt = effDt;
	}

	/*public String getCurrPriInd() {
		return this.currPriInd;
	}

	public void setCurrPriInd(String currPriInd) {
		this.currPriInd = currPriInd;
	}*/

	public String getCustMtnStatCd() {
		return this.custMtnStatCd;
	}

	public void setCustMtnStatCd(String custMtnStatCd) {
		this.custMtnStatCd = custMtnStatCd;
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

	public String getMdn() {
		return this.mdn;
	}

	public void setMdn(String mdn) {
		this.mdn = mdn;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SubCustAcctMdnPK)) {
			return false;
		}
		SubCustAcctMdnPK castOther = (SubCustAcctMdnPK)other;
		//return this.blTypCd.equals(castOther.blTypCd) && this.effDt.equals(castOther.effDt) && this.currPriInd.equals(castOther.currPriInd) && this.custMtnStatCd.equals(castOther.custMtnStatCd) && this.custIdNo == castOther.custIdNo && this.acctNo == castOther.acctNo && this.mdn.equals(castOther.mdn);
		return this.blTypCd.equals(castOther.blTypCd) && this.effDt.equals(castOther.effDt) &&  this.custMtnStatCd.equals(castOther.custMtnStatCd) && this.custIdNo == castOther.custIdNo && this.acctNo == castOther.acctNo && this.mdn.equals(castOther.mdn);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.blTypCd.hashCode();
		hash = hash * prime + this.effDt.hashCode();
		//hash = hash * prime + this.currPriInd.hashCode();
		hash = hash * prime + this.custMtnStatCd.hashCode();
		hash = hash * prime + ((int) (this.custIdNo ^ (this.custIdNo >>> 32)));
		hash = hash * prime + ((int) (this.acctNo ^ (this.acctNo >>> 32)));
		hash = hash * prime + this.mdn.hashCode();
		return hash;
	}

}
