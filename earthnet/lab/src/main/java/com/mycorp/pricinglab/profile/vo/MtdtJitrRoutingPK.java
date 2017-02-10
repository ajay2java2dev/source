package com.vzwcorp.pricinglab.profile.vo;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;


@Embeddable
public class MtdtJitrRoutingPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CUST_ID_NO")
	private long custIdNo;

	public MtdtJitrRoutingPK() {}

	public MtdtJitrRoutingPK(long custIdNo) {
		this.custIdNo = custIdNo;
	}

	public long getCustIdNo() {
		return custIdNo;
	}

	public void setCustIdNo(long custIdNo) {
		this.custIdNo = custIdNo;
	}
}