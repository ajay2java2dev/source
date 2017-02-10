package com.vzwcorp.pricinglab.profile.rbm.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CustproducttariffdetailsPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="START_DAT")
	protected Timestamp	startDat;

	@Column(name="CUSTOMER_REF")
	protected String	customerRef;

	@Column(name="PRODUCT_SEQ")
	protected Long	productSeq;

	@Column(name="DOMAIN_ID")
	protected Long	domainId;

	public CustproducttariffdetailsPK() {}

	public CustproducttariffdetailsPK(Timestamp startDat, String customerRef, Long productSeq, Long domainId){
		 this.startDat = startDat;
		 this.customerRef = customerRef;
		 this.productSeq = productSeq;
		 this.domainId = domainId;
	}

	public Timestamp getStartDat() {
		return this.startDat;
	}

	public void setStartDat(Timestamp startDat) {
		this.startDat = startDat;
	}

	public String getCustomerRef() {
		return this.customerRef;
	}

	public void setCustomerRef(String customerRef) {
		this.customerRef = customerRef;
	}

	public Long getProductSeq() {
		return this.productSeq;
	}

	public void setProductSeq(Long productSeq) {
		this.productSeq = productSeq;
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
		if (!(other instanceof CustproducttariffdetailsPK)) {
			return false;
		}
		CustproducttariffdetailsPK castOther = (CustproducttariffdetailsPK)other;
		return this.startDat.equals(castOther.startDat) && this.customerRef.equals(castOther.customerRef) && this.productSeq == castOther.productSeq && this.domainId == castOther.domainId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.startDat.hashCode();
		hash = hash * prime + this.customerRef.hashCode();
		hash = hash * prime + ((int) (this.productSeq ^ (this.productSeq >>> 32)));
		hash = hash * prime + ((int) (this.domainId ^ (this.domainId >>> 32)));
		return hash;
	}

}
