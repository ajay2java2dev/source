package com.vzwcorp.pricinglab.profile.rbm.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the BILLSUMMARY database table.
 * 
 */
@Embeddable
public class BillsummaryPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ACCOUNT_NUM")
	private String accountNum;

	@Column(name="BILL_SEQ")
	private long billSeq;

	@Column(name="BILL_VERSION")
	private long billVersion;

	public BillsummaryPK() {
	}
	public String getAccountNum() {
		return this.accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public long getBillSeq() {
		return this.billSeq;
	}
	public void setBillSeq(long billSeq) {
		this.billSeq = billSeq;
	}
	public long getBillVersion() {
		return this.billVersion;
	}
	public void setBillVersion(long billVersion) {
		this.billVersion = billVersion;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BillsummaryPK)) {
			return false;
		}
		BillsummaryPK castOther = (BillsummaryPK)other;
		return 
			this.accountNum.equals(castOther.accountNum)
			&& (this.billSeq == castOther.billSeq)
			&& (this.billVersion == castOther.billVersion);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.accountNum.hashCode();
		hash = hash * prime + ((int) (this.billSeq ^ (this.billSeq >>> 32)));
		hash = hash * prime + ((int) (this.billVersion ^ (this.billVersion >>> 32)));
		
		return hash;
	}
}