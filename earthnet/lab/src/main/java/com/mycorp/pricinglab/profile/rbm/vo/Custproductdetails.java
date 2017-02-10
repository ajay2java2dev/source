package com.vzwcorp.pricinglab.profile.rbm.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="CUSTPRODUCTDETAILS")
@IdClass(CustproductdetailsPK.class)
public class Custproductdetails implements Serializable {
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

	@Id
	@Column(name="PRODUCT_SEQ")
	public Long productSeq;

	public Long getProductSeq() {
		return productSeq;
}
	public void setProductSeq(Long productSeq) {
		this.productSeq = productSeq;
	}

	@Id
	@Column(name="START_DAT")
	public Timestamp startDat;

	public Timestamp getStartDat() {
		return startDat;
}
	public void setStartDat(Timestamp startDat) {
		this.startDat = startDat;
	}

	@Column(name="END_DAT")
	public Timestamp endDat;

	public Timestamp getEndDat() {
		return endDat;
}
	public void setEndDat(Timestamp endDat) {
		this.endDat = endDat;
	}

	@Column(name="ACCOUNT_NUM")
	public String accountNum;

	public String getAccountNum() {
		return accountNum;
}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	@Column(name="BUDGET_CENTRE_SEQ")
	public Long budgetCentreSeq;

	public Long getBudgetCentreSeq() {
		return budgetCentreSeq;
}
	public void setBudgetCentreSeq(Long budgetCentreSeq) {
		this.budgetCentreSeq = budgetCentreSeq;
	}

	@Column(name="PRODUCT_LABEL")
	public String productLabel;

	public String getProductLabel() {
		return productLabel;
}
	public void setProductLabel(String productLabel) {
		this.productLabel = productLabel;
	}

	@Column(name="CUST_PRODUCT_CONTACT_SEQ")
	public Long custProductContactSeq;

	public Long getCustProductContactSeq() {
		return custProductContactSeq;
}
	public void setCustProductContactSeq(Long custProductContactSeq) {
		this.custProductContactSeq = custProductContactSeq;
	}

	@Column(name="CONTRACT_SEQ")
	public Long contractSeq;

	public Long getContractSeq() {
		return contractSeq;
}
	public void setContractSeq(Long contractSeq) {
		this.contractSeq = contractSeq;
	}

	@Column(name="CPS_ID")
	public Long cpsId;

	public Long getCpsId() {
		return cpsId;
}
	public void setCpsId(Long cpsId) {
		this.cpsId = cpsId;
	}

	@Column(name="TAX_EXEMPT_REF")
	public String taxExemptRef;

	public String getTaxExemptRef() {
		return taxExemptRef;
}
	public void setTaxExemptRef(String taxExemptRef) {
		this.taxExemptRef = taxExemptRef;
	}

	@Column(name="TAX_EXEMPT_TXT")
	public String taxExemptTxt;

	public String getTaxExemptTxt() {
		return taxExemptTxt;
}
	public void setTaxExemptTxt(String taxExemptTxt) {
		this.taxExemptTxt = taxExemptTxt;
	}

	@Column(name="DEFAULT_EVENT_SOURCE")
	public String defaultEventSource;

	public String getDefaultEventSource() {
		return defaultEventSource;
}
	public void setDefaultEventSource(String defaultEventSource) {
		this.defaultEventSource = defaultEventSource;
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

	@Column(name="BUDGET_PAYMENT_PLAN_ID")
	public Long budgetPaymentPlanId;

	public Long getBudgetPaymentPlanId() {
		return budgetPaymentPlanId;
}
	public void setBudgetPaymentPlanId(Long budgetPaymentPlanId) {
		this.budgetPaymentPlanId = budgetPaymentPlanId;
	}

}