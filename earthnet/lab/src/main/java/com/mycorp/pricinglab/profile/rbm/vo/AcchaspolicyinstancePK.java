package com.vzwcorp.pricinglab.profile.rbm.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AcchaspolicyinstancePK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACCOUNT_NUM")
	protected String	accountNum;

	@Column(name="POLICY_SEQ")
	protected Long	policySeq;

	@Column(name="DOMAIN_ID")
	protected Long	domainId;

	public AcchaspolicyinstancePK() {}

	public AcchaspolicyinstancePK(String accountNum, Long policySeq, Long domainId){
		 this.accountNum = accountNum;
		 this.policySeq = policySeq;
		 this.domainId = domainId;
	}

	public String getAccountNum() {
		return this.accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public Long getPolicySeq() {
		return this.policySeq;
	}

	public void setPolicySeq(Long policySeq) {
		this.policySeq = policySeq;
	}

	public Long getDomainId() {
		return this.domainId;
	}

	public void setDomainId(Long domainId) {
		this.domainId = domainId;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AcchaspolicyinstancePK)) {
			return false;
		}
		AcchaspolicyinstancePK castOther = (AcchaspolicyinstancePK)other;
		return this.accountNum.equals(castOther.accountNum) && this.policySeq == castOther.policySeq && this.domainId == castOther.domainId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.accountNum.hashCode();
		hash = hash * prime + ((int) (this.policySeq ^ (this.policySeq >>> 32)));
		hash = hash * prime + ((int) (this.domainId ^ (this.domainId >>> 32)));
		return hash;
	}

}
