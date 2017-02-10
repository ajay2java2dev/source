package com.vzwcorp.pricinglab.profile.rbm.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CusteventsourcePK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="START_DTM")
	protected Timestamp	startDtm;

	@Column(name="EVENT_SOURCE")
	protected String	eventSource;

	@Column(name="EVENT_TYPE_ID")
	protected Long	eventTypeId;

	@Column(name="CUSTOMER_REF")
	protected String	customerRef;

	@Column(name="PRODUCT_SEQ")
	protected Long	productSeq;

	@Column(name="DOMAIN_ID")
	protected Long	domainId;

	public CusteventsourcePK() {}

	public CusteventsourcePK(Timestamp startDtm, String eventSource, Long eventTypeId, String customerRef, Long productSeq, Long domainId){
		 this.startDtm = startDtm;
		 this.eventSource = eventSource;
		 this.eventTypeId = eventTypeId;
		 this.customerRef = customerRef;
		 this.productSeq = productSeq;
		 this.domainId = domainId;
	}

	public Timestamp getStartDtm() {
		return this.startDtm;
	}

	public void setStartDtm(Timestamp startDtm) {
		this.startDtm = startDtm;
	}

	public String getEventSource() {
		return this.eventSource;
	}

	public void setEventSource(String eventSource) {
		this.eventSource = eventSource;
	}

	public Long getEventTypeId() {
		return this.eventTypeId;
	}

	public void setEventTypeId(Long eventTypeId) {
		this.eventTypeId = eventTypeId;
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
		if (!(other instanceof CusteventsourcePK)) {
			return false;
		}
		CusteventsourcePK castOther = (CusteventsourcePK)other;
		return this.startDtm.equals(castOther.startDtm) && this.eventSource.equals(castOther.eventSource) && this.eventTypeId == castOther.eventTypeId && this.customerRef.equals(castOther.customerRef) && this.productSeq == castOther.productSeq && this.domainId == castOther.domainId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.startDtm.hashCode();
		hash = hash * prime + this.eventSource.hashCode();
		hash = hash * prime + ((int) (this.eventTypeId ^ (this.eventTypeId >>> 32)));
		hash = hash * prime + this.customerRef.hashCode();
		hash = hash * prime + ((int) (this.productSeq ^ (this.productSeq >>> 32)));
		hash = hash * prime + ((int) (this.domainId ^ (this.domainId >>> 32)));
		return hash;
	}

}
