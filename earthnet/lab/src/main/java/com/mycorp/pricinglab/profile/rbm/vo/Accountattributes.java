package com.vzwcorp.pricinglab.profile.rbm.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="ACCOUNTATTRIBUTES")
@IdClass(AccountattributesPK.class)
public class Accountattributes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ACCOUNT_NUM")
	public String accountNum;

	public String getAccountNum() {
		return accountNum;
}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	@Id
	@Column(name="DOMAIN_ID")
	public Long domainId;

	public Long getDomainId() {
		return domainId;
}
	public void setDomainId(Long domainId) {
		this.domainId = domainId;
	}

	@Column(name="LEGACY_BILL_CYCLE_CODE")
	public String legacyBillCycleCode;

	public String getLegacyBillCycleCode() {
		return legacyBillCycleCode;
}
	public void setLegacyBillCycleCode(String legacyBillCycleCode) {
		this.legacyBillCycleCode = legacyBillCycleCode;
	}

}