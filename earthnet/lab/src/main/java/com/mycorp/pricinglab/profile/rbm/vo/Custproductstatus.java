package com.vzwcorp.pricinglab.profile.rbm.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="CUSTPRODUCTSTATUS")
@IdClass(CustproductstatusPK.class)
public class Custproductstatus implements Serializable {
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
	@Column(name="EFFECTIVE_DTM")
	public Timestamp effectiveDtm;

	public Timestamp getEffectiveDtm() {
		return effectiveDtm;
}
	public void setEffectiveDtm(Timestamp effectiveDtm) {
		this.effectiveDtm = effectiveDtm;
	}

	@Column(name="PRODUCT_STATUS")
	public String productStatus;

	public String getProductStatus() {
		return productStatus;
}
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	@Column(name="STATUS_REASON_TXT")
	public String statusReasonTxt;

	public String getStatusReasonTxt() {
		return statusReasonTxt;
}
	public void setStatusReasonTxt(String statusReasonTxt) {
		this.statusReasonTxt = statusReasonTxt;
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

}