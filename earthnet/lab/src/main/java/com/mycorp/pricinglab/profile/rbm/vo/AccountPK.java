package com.vzwcorp.pricinglab.profile.rbm.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AccountPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACCOUNT_NUM")
	protected String	accountNum;

	@Column(name="NEXT_BILL_DTM")
	protected Timestamp	nextBillDtm;

	@Column(name="BILLING_STATUS")
	protected String	billingStatus;

	@Column(name="RANDOM_HASH")
	protected Long	randomHash;

	@Column(name="UPRE_ACTION_BOO")
	protected String	upreActionBoo;

	@Column(name="DOMAIN_ID")
	protected Long	domainId;

	@Column(name="EXTERNAL_BALANCE_LIID")
	protected Long	externalBalanceLiid;

	@Column(name="UPRE_NEXT_BILL_DTM")
	protected Timestamp	upreNextBillDtm;

	public AccountPK() {}

	public AccountPK(String accountNum, Timestamp nextBillDtm, String billingStatus, Long randomHash, String upreActionBoo, Long domainId, Long externalBalanceLiid, Timestamp upreNextBillDtm){
		 this.accountNum = accountNum;
		 this.nextBillDtm = nextBillDtm;
		 this.billingStatus = billingStatus;
		 this.randomHash = randomHash;
		 this.upreActionBoo = upreActionBoo;
		 this.domainId = domainId;
		 this.externalBalanceLiid = externalBalanceLiid;
		 this.upreNextBillDtm = upreNextBillDtm;
	}

	public String getAccountNum() {
		return this.accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public Timestamp getNextBillDtm() {
		return this.nextBillDtm;
	}

	public void setNextBillDtm(Timestamp nextBillDtm) {
		this.nextBillDtm = nextBillDtm;
	}

	public String getBillingStatus() {
		return this.billingStatus;
	}

	public void setBillingStatus(String billingStatus) {
		this.billingStatus = billingStatus;
	}

	public Long getRandomHash() {
		return this.randomHash;
	}

	public void setRandomHash(Long randomHash) {
		this.randomHash = randomHash;
	}

	public String getUpreActionBoo() {
		return this.upreActionBoo;
	}

	public void setUpreActionBoo(String upreActionBoo) {
		this.upreActionBoo = upreActionBoo;
	}

	public Long getDomainId() {
		return this.domainId;
	}

	public void setDomainId(Long domainId) {
		this.domainId = domainId;
	}

	public Long getExternalBalanceLiid() {
		return this.externalBalanceLiid;
	}

	public void setExternalBalanceLiid(Long externalBalanceLiid) {
		this.externalBalanceLiid = externalBalanceLiid;
	}

	public Timestamp getUpreNextBillDtm() {
		return this.upreNextBillDtm;
	}

	public void setUpreNextBillDtm(Timestamp upreNextBillDtm) {
		this.upreNextBillDtm = upreNextBillDtm;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AccountPK)) {
			return false;
		}
		AccountPK castOther = (AccountPK)other;
		return this.accountNum.equals(castOther.accountNum) && this.nextBillDtm.equals(castOther.nextBillDtm) && this.billingStatus.equals(castOther.billingStatus) && this.randomHash == castOther.randomHash && this.upreActionBoo.equals(castOther.upreActionBoo) && this.domainId == castOther.domainId && this.externalBalanceLiid == castOther.externalBalanceLiid && this.upreNextBillDtm.equals(castOther.upreNextBillDtm);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.accountNum.hashCode();
		hash = hash * prime + this.nextBillDtm.hashCode();
		hash = hash * prime + this.billingStatus.hashCode();
		hash = hash * prime + ((int) (this.randomHash ^ (this.randomHash >>> 32)));
		hash = hash * prime + this.upreActionBoo.hashCode();
		hash = hash * prime + ((int) (this.domainId ^ (this.domainId >>> 32)));
		hash = hash * prime + ((int) (this.externalBalanceLiid ^ (this.externalBalanceLiid >>> 32)));
		hash = hash * prime + this.upreNextBillDtm.hashCode();
		return hash;
	}

}
