package com.vzwcorp.pricinglab.profile.rbm.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="CUSTPRODUCTTARIFFDETAILS")
@IdClass(CustproducttariffdetailsPK.class)
public class Custproducttariffdetails implements Serializable {
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

	@Column(name="TARIFF_ID")
	public Long tariffId;

	public Long getTariffId() {
		return tariffId;
}
	public void setTariffId(Long tariffId) {
		this.tariffId = tariffId;
	}

	@Column(name="PRODUCT_QUANTITY")
	public Long productQuantity;

	public Long getProductQuantity() {
		return productQuantity;
}
	public void setProductQuantity(Long productQuantity) {
		this.productQuantity = productQuantity;
	}

	@Column(name="END_DAT")
	public Timestamp endDat;

	public Timestamp getEndDat() {
		return endDat;
}
	public void setEndDat(Timestamp endDat) {
		this.endDat = endDat;
	}

	@Column(name="ADDITIONS_QUANTITY")
	public Long additionsQuantity;

	public Long getAdditionsQuantity() {
		return additionsQuantity;
}
	public void setAdditionsQuantity(Long additionsQuantity) {
		this.additionsQuantity = additionsQuantity;
	}

	@Column(name="TERMINATIONS_QUANTITY")
	public Long terminationsQuantity;

	public Long getTerminationsQuantity() {
		return terminationsQuantity;
}
	public void setTerminationsQuantity(Long terminationsQuantity) {
		this.terminationsQuantity = terminationsQuantity;
	}

	@Column(name="COMPETITOR_TARIFF_ID")
	public Long competitorTariffId;

	public Long getCompetitorTariffId() {
		return competitorTariffId;
}
	public void setCompetitorTariffId(Long competitorTariffId) {
		this.competitorTariffId = competitorTariffId;
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

	@Column(name="PRORATE_INITIAL_PERIOD_BOO")
	public String prorateInitialPeriodBoo;

	public String getProrateInitialPeriodBoo() {
		return prorateInitialPeriodBoo;
}
	public void setProrateInitialPeriodBoo(String prorateInitialPeriodBoo) {
		this.prorateInitialPeriodBoo = prorateInitialPeriodBoo;
	}

}