package com.vzwcorp.pricinglab.profile.rbm.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="CUSTOMERTYPE")
@IdClass(CustomertypePK.class)
public class Customertype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CUSTOMER_TYPE_ID")
	public Long customerTypeId;

	public Long getCustomerTypeId() {
		return customerTypeId;
}
	public void setCustomerTypeId(Long customerTypeId) {
		this.customerTypeId = customerTypeId;
	}

	@Column(name="CUSTOMER_TYPE_NAME")
	public String customerTypeName;

	public String getCustomerTypeName() {
		return customerTypeName;
}
	public void setCustomerTypeName(String customerTypeName) {
		this.customerTypeName = customerTypeName;
	}

	@Column(name="CUSTOMER_TYPE_DESC")
	public String customerTypeDesc;

	public String getCustomerTypeDesc() {
		return customerTypeDesc;
}
	public void setCustomerTypeDesc(String customerTypeDesc) {
		this.customerTypeDesc = customerTypeDesc;
	}

}