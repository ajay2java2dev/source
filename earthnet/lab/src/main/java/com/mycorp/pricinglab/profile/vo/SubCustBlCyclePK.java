package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubCustBlCyclePK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="BL_TYP_CD")
	protected String	blTypCd;

	@Column(name="BL_CYC_NO")
	protected String	blCycNo;

	@Column(name="DELETE_IND")
	protected String	deleteInd;

	@Column(name="CUST_ID_NO")
	protected Long	custIdNo;

	public SubCustBlCyclePK() {}

	public SubCustBlCyclePK(String blTypCd, String blCycNo, String deleteInd, Long custIdNo){
		 this.blTypCd = blTypCd;
		 this.blCycNo = blCycNo;
		 this.deleteInd = deleteInd;
		 this.custIdNo = custIdNo;
	}

	public String getBlTypCd() {
		return this.blTypCd;
	}

	public void setBlTypCd(String blTypCd) {
		this.blTypCd = blTypCd;
	}

	public String getBlCycNo() {
		return this.blCycNo;
	}

	public void setBlCycNo(String blCycNo) {
		this.blCycNo = blCycNo;
	}

	public String getDeleteInd() {
		return this.deleteInd;
	}

	public void setDeleteInd(String deleteInd) {
		this.deleteInd = deleteInd;
	}

	public Long getCustIdNo() {
		return this.custIdNo;
	}

	public void setCustIdNo(Long custIdNo) {
		this.custIdNo = custIdNo;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SubCustBlCyclePK)) {
			return false;
		}
		SubCustBlCyclePK castOther = (SubCustBlCyclePK)other;
		return this.blTypCd.equals(castOther.blTypCd) && this.blCycNo.equals(castOther.blCycNo) && this.deleteInd.equals(castOther.deleteInd) && this.custIdNo == castOther.custIdNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.blTypCd.hashCode();
		hash = hash * prime + this.blCycNo.hashCode();
		hash = hash * prime + this.deleteInd.hashCode();
		hash = hash * prime + ((int) (this.custIdNo ^ (this.custIdNo >>> 32)));
		return hash;
	}

}
