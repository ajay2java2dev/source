package com.vzwcorp.pricinglab.loader.util;

public class CpfContext {
	long customerId, accountId;
	Integer ubsrDataSourceKey = -1;

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public Integer getUbsrDataSourceKey() {
		return ubsrDataSourceKey;
	}

	public void setUbsrDataSourceKey(Integer ubsrDataSourceKey) {
		this.ubsrDataSourceKey = ubsrDataSourceKey;
	}

}
