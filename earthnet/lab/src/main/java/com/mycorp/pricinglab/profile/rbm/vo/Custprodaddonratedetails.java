package com.vzwcorp.pricinglab.profile.rbm.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="CUSTPRODADDONRATEDETAILS")
@IdClass(CustprodaddonratedetailsPK.class)
public class Custprodaddonratedetails implements Serializable {
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
	@Column(name="DOMAIN_ID")
	public Long domainId;

	public Long getDomainId() {
		return domainId;
}
	public void setDomainId(Long domainId) {
		this.domainId = domainId;
	}

	@Id
	@Column(name="ADD_ON_RATE_SEQ")
	public Long addOnRateSeq;

	public Long getAddOnRateSeq() {
		return addOnRateSeq;
}
	public void setAddOnRateSeq(Long addOnRateSeq) {
		this.addOnRateSeq = addOnRateSeq;
	}

	@Column(name="START_DTM")
	public Timestamp startDtm;

	public Timestamp getStartDtm() {
		return startDtm;
}
	public void setStartDtm(Timestamp startDtm) {
		this.startDtm = startDtm;
	}

	@Column(name="PARENT_PRODUCT_SEQ")
	public Long parentProductSeq;

	public Long getParentProductSeq() {
		return parentProductSeq;
}
	public void setParentProductSeq(Long parentProductSeq) {
		this.parentProductSeq = parentProductSeq;
	}

	@Column(name="RATING_TARIFF_ID")
	public Long ratingTariffId;

	public Long getRatingTariffId() {
		return ratingTariffId;
}
	public void setRatingTariffId(Long ratingTariffId) {
		this.ratingTariffId = ratingTariffId;
	}

	@Column(name="REGUIDING_RATE_BOO")
	public String reguidingRateBoo;

	public String getReguidingRateBoo() {
		return reguidingRateBoo;
}
	public void setReguidingRateBoo(String reguidingRateBoo) {
		this.reguidingRateBoo = reguidingRateBoo;
	}

	@Column(name="CREATED_DTM")
	public Timestamp createdDtm;

	public Timestamp getCreatedDtm() {
		return createdDtm;
}
	public void setCreatedDtm(Timestamp createdDtm) {
		this.createdDtm = createdDtm;
	}

	@Column(name="END_DTM")
	public Timestamp endDtm;

	public Timestamp getEndDtm() {
		return endDtm;
}
	public void setEndDtm(Timestamp endDtm) {
		this.endDtm = endDtm;
	}

	@Column(name="EVENT_TYPE_ID")
	public Long eventTypeId;

	public Long getEventTypeId() {
		return eventTypeId;
}
	public void setEventTypeId(Long eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	@Column(name="EVENT_SOURCE")
	public String eventSource;

	public String getEventSource() {
		return eventSource;
}
	public void setEventSource(String eventSource) {
		this.eventSource = eventSource;
	}

	@Column(name="REGUIDING_RULE_NUM")
	public Long reguidingRuleNum;

	public Long getReguidingRuleNum() {
		return reguidingRuleNum;
}
	public void setReguidingRuleNum(Long reguidingRuleNum) {
		this.reguidingRuleNum = reguidingRuleNum;
	}

}