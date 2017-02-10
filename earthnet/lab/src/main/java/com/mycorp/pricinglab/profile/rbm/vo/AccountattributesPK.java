package com.vzwcorp.pricinglab.profile.rbm.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AccountattributesPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACCOUNT_NUM")
	protected String	accountNum;

	@Column(name="DOMAIN_ID")
	protected Long	domainId;

	public AccountattributesPK() {}

	public AccountattributesPK(String accountNum, Long domainId){
		 this.accountNum = accountNum;
		 this.domainId = domainId;
	}

	public String getAccountNum() {
		return this.accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
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
		if (!(other instanceof AccountattributesPK)) {
			return false;
		}
		AccountattributesPK castOther = (AccountattributesPK)other;
		return this.accountNum.equals(castOther.accountNum) && this.domainId == castOther.domainId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.accountNum.hashCode();
		hash = hash * prime + ((int) (this.domainId ^ (this.domainId >>> 32)));
		return hash;
	}

}
