package com.vzwcorp.pricinglab.profile.rbm.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="CUSTOMER")
@IdClass(CustomerPK.class)
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CUSTOMER_REF")
	public String customerRef;

	public String getCustomerRef() {
		return customerRef;
}
	public void setCustomerRef(String customerRef) {
		this.customerRef = customerRef;
	}

	@Column(name="CUSTOMER_TYPE_ID")
	public Long customerTypeId;

	public Long getCustomerTypeId() {
		return customerTypeId;
}
	public void setCustomerTypeId(Long customerTypeId) {
		this.customerTypeId = customerTypeId;
	}

	@Column(name="RANDOM_HASH")
	public Long randomHash;

	public Long getRandomHash() {
		return randomHash;
}
	public void setRandomHash(Long randomHash) {
		this.randomHash = randomHash;
	}

	@Column(name="ROOT_CUSTOMER_REF")
	public String rootCustomerRef;

	public String getRootCustomerRef() {
		return rootCustomerRef;
}
	public void setRootCustomerRef(String rootCustomerRef) {
		this.rootCustomerRef = rootCustomerRef;
	}

	@Column(name="PERMISSION_ID")
	public Long permissionId;

	public Long getPermissionId() {
		return permissionId;
}
	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	@Column(name="CONCATENATE_BILLS_BOO")
	public String concatenateBillsBoo;

	public String getConcatenateBillsBoo() {
		return concatenateBillsBoo;
}
	public void setConcatenateBillsBoo(String concatenateBillsBoo) {
		this.concatenateBillsBoo = concatenateBillsBoo;
	}

	@Column(name="DESCENDANT_CUSTOMER_COUNT")
	public Long descendantCustomerCount;

	public Long getDescendantCustomerCount() {
		return descendantCustomerCount;
}
	public void setDescendantCustomerCount(Long descendantCustomerCount) {
		this.descendantCustomerCount = descendantCustomerCount;
	}

	@Column(name="CHILD_CUSTOMER_COUNT")
	public Long childCustomerCount;

	public Long getChildCustomerCount() {
		return childCustomerCount;
}
	public void setChildCustomerCount(Long childCustomerCount) {
		this.childCustomerCount = childCustomerCount;
	}

	@Column(name="ACCOUNT_COUNT")
	public Long accountCount;

	public Long getAccountCount() {
		return accountCount;
}
	public void setAccountCount(Long accountCount) {
		this.accountCount = accountCount;
	}

	@Column(name="CUSTOMER_REF_UPPER")
	public String customerRefUpper;

	public String getCustomerRefUpper() {
		return customerRefUpper;
}
	public void setCustomerRefUpper(String customerRefUpper) {
		this.customerRefUpper = customerRefUpper;
	}

	@Column(name="BILLING_DRIVER_BOO")
	public String billingDriverBoo;

	public String getBillingDriverBoo() {
		return billingDriverBoo;
}
	public void setBillingDriverBoo(String billingDriverBoo) {
		this.billingDriverBoo = billingDriverBoo;
	}

	@Column(name="BILLING_CUSTOMER_REF")
	public String billingCustomerRef;

	public String getBillingCustomerRef() {
		return billingCustomerRef;
}
	public void setBillingCustomerRef(String billingCustomerRef) {
		this.billingCustomerRef = billingCustomerRef;
	}

	@Column(name="HIERARCHY_LEVEL_NUM")
	public Long hierarchyLevelNum;

	public Long getHierarchyLevelNum() {
		return hierarchyLevelNum;
}
	public void setHierarchyLevelNum(Long hierarchyLevelNum) {
		this.hierarchyLevelNum = hierarchyLevelNum;
	}

	@Column(name="DOMAIN_ID")
	public Long domainId;

	public Long getDomainId() {
		return domainId;
}
	public void setDomainId(Long domainId) {
		this.domainId = domainId;
	}

	@Column(name="TEMPLATE_REF_CUST_BOO")
	public String templateRefCustBoo;

	public String getTemplateRefCustBoo() {
		return templateRefCustBoo;
}
	public void setTemplateRefCustBoo(String templateRefCustBoo) {
		this.templateRefCustBoo = templateRefCustBoo;
	}

	@Column(name="BILLING_CUSTOMER_DOMAIN_ID")
	public Long billingCustomerDomainId;

	public Long getBillingCustomerDomainId() {
		return billingCustomerDomainId;
}
	public void setBillingCustomerDomainId(Long billingCustomerDomainId) {
		this.billingCustomerDomainId = billingCustomerDomainId;
	}

	@Column(name="CUSTOMER_CONTACT_SEQ")
	public Long customerContactSeq;

	public Long getCustomerContactSeq() {
		return customerContactSeq;
}
	public void setCustomerContactSeq(Long customerContactSeq) {
		this.customerContactSeq = customerContactSeq;
	}

	@Column(name="COMPANY_NAME")
	public String companyName;

	public String getCompanyName() {
		return companyName;
}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name="COMPANY_NAME_SDX")
	public String companyNameSdx;

	public String getCompanyNameSdx() {
		return companyNameSdx;
}
	public void setCompanyNameSdx(String companyNameSdx) {
		this.companyNameSdx = companyNameSdx;
	}

	@Column(name="TAX_EXEMPT_REF")
	public String taxExemptRef;

	public String getTaxExemptRef() {
		return taxExemptRef;
}
	public void setTaxExemptRef(String taxExemptRef) {
		this.taxExemptRef = taxExemptRef;
	}

	@Column(name="PARENT_CUSTOMER_REF")
	public String parentCustomerRef;

	public String getParentCustomerRef() {
		return parentCustomerRef;
}
	public void setParentCustomerRef(String parentCustomerRef) {
		this.parentCustomerRef = parentCustomerRef;
	}

	@Column(name="PROVIDER_ID")
	public Long providerId;

	public Long getProviderId() {
		return providerId;
}
	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	@Column(name="PASSWORD")
	public String password;

	public String getPassword() {
		return password;
}
	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="BILL_PERIOD")
	public Long billPeriod;

	public Long getBillPeriod() {
		return billPeriod;
}
	public void setBillPeriod(Long billPeriod) {
		this.billPeriod = billPeriod;
	}

	@Column(name="BILL_PERIOD_UNITS")
	public String billPeriodUnits;

	public String getBillPeriodUnits() {
		return billPeriodUnits;
}
	public void setBillPeriodUnits(String billPeriodUnits) {
		this.billPeriodUnits = billPeriodUnits;
	}

	@Column(name="NEXT_BILL_DTM")
	public Timestamp nextBillDtm;

	public Timestamp getNextBillDtm() {
		return nextBillDtm;
}
	public void setNextBillDtm(Timestamp nextBillDtm) {
		this.nextBillDtm = nextBillDtm;
	}

	@Column(name="BILLS_PER_STATEMENT")
	public Long billsPerStatement;

	public Long getBillsPerStatement() {
		return billsPerStatement;
}
	public void setBillsPerStatement(Long billsPerStatement) {
		this.billsPerStatement = billsPerStatement;
	}

	@Column(name="VAT_REGISTRATION")
	public String vatRegistration;

	public String getVatRegistration() {
		return vatRegistration;
}
	public void setVatRegistration(String vatRegistration) {
		this.vatRegistration = vatRegistration;
	}

	@Column(name="COMPANY_NAME_UPPER")
	public String companyNameUpper;

	public String getCompanyNameUpper() {
		return companyNameUpper;
}
	public void setCompanyNameUpper(String companyNameUpper) {
		this.companyNameUpper = companyNameUpper;
	}

	@Column(name="SUMMARY_CONTACT_SEQ")
	public Long summaryContactSeq;

	public Long getSummaryContactSeq() {
		return summaryContactSeq;
}
	public void setSummaryContactSeq(Long summaryContactSeq) {
		this.summaryContactSeq = summaryContactSeq;
	}

	@Column(name="SUMMARY_CURRENCY_CODE")
	public String summaryCurrencyCode;

	public String getSummaryCurrencyCode() {
		return summaryCurrencyCode;
}
	public void setSummaryCurrencyCode(String summaryCurrencyCode) {
		this.summaryCurrencyCode = summaryCurrencyCode;
	}

	@Column(name="SUMMARY_STYLE_ID")
	public Long summaryStyleId;

	public Long getSummaryStyleId() {
		return summaryStyleId;
}
	public void setSummaryStyleId(Long summaryStyleId) {
		this.summaryStyleId = summaryStyleId;
	}

	@Column(name="SUMMARY_BILL_HANDLING_CODE")
	public String summaryBillHandlingCode;

	public String getSummaryBillHandlingCode() {
		return summaryBillHandlingCode;
}
	public void setSummaryBillHandlingCode(String summaryBillHandlingCode) {
		this.summaryBillHandlingCode = summaryBillHandlingCode;
	}

	@Column(name="INVOICING_CO_ID")
	public Long invoicingCoId;

	public Long getInvoicingCoId() {
		return invoicingCoId;
}
	public void setInvoicingCoId(Long invoicingCoId) {
		this.invoicingCoId = invoicingCoId;
	}

	@Column(name="MARKET_SEGMENT_ID")
	public Long marketSegmentId;

	public Long getMarketSegmentId() {
		return marketSegmentId;
}
	public void setMarketSegmentId(Long marketSegmentId) {
		this.marketSegmentId = marketSegmentId;
	}

	@Column(name="FAST_CACHE_BOO")
	public String fastCacheBoo;

	public String getFastCacheBoo() {
		return fastCacheBoo;
}
	public void setFastCacheBoo(String fastCacheBoo) {
		this.fastCacheBoo = fastCacheBoo;
	}

	@Column(name="IRBS_VERSION")
	public Long irbsVersion;

	public Long getIrbsVersion() {
		return irbsVersion;
}
	public void setIrbsVersion(Long irbsVersion) {
		this.irbsVersion = irbsVersion;
	}

	@Column(name="INHOST_VERSION")
	public Long inhostVersion;

	public Long getInhostVersion() {
		return inhostVersion;
}
	public void setInhostVersion(Long inhostVersion) {
		this.inhostVersion = inhostVersion;
	}

	@Column(name="CUSTOMER_CATEGORY")
	public Long customerCategory;

	public Long getCustomerCategory() {
		return customerCategory;
}
	public void setCustomerCategory(Long customerCategory) {
		this.customerCategory = customerCategory;
	}

	@Column(name="MACHINE_NAME")
	public String machineName;

	public String getMachineName() {
		return machineName;
}
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	@Column(name="TRANSITION_DTM")
	public Timestamp transitionDtm;

	public Timestamp getTransitionDtm() {
		return transitionDtm;
}
	public void setTransitionDtm(Timestamp transitionDtm) {
		this.transitionDtm = transitionDtm;
	}

	@Column(name="INHOST_VERSION_SYNC")
	public Long inhostVersionSync;

	public Long getInhostVersionSync() {
		return inhostVersionSync;
}
	public void setInhostVersionSync(Long inhostVersionSync) {
		this.inhostVersionSync = inhostVersionSync;
	}

	@Column(name="INHOST_VERSION_SYNC_DTM")
	public Timestamp inhostVersionSyncDtm;

	public Timestamp getInhostVersionSyncDtm() {
		return inhostVersionSyncDtm;
}
	public void setInhostVersionSyncDtm(Timestamp inhostVersionSyncDtm) {
		this.inhostVersionSyncDtm = inhostVersionSyncDtm;
	}

	@Column(name="LOGICALLY_DELETED_BOO")
	public String logicallyDeletedBoo;

	public String getLogicallyDeletedBoo() {
		return logicallyDeletedBoo;
}
	public void setLogicallyDeletedBoo(String logicallyDeletedBoo) {
		this.logicallyDeletedBoo = logicallyDeletedBoo;
	}

	@Column(name="TRANSFER_INDICATOR")
	public Long transferIndicator;

	public Long getTransferIndicator() {
		return transferIndicator;
}
	public void setTransferIndicator(Long transferIndicator) {
		this.transferIndicator = transferIndicator;
	}

	@Column(name="BILL_DAY_OF_THE_MONTH")
	public Long billDayOfTheMonth;

	public Long getBillDayOfTheMonth() {
		return billDayOfTheMonth;
}
	public void setBillDayOfTheMonth(Long billDayOfTheMonth) {
		this.billDayOfTheMonth = billDayOfTheMonth;
	}

}