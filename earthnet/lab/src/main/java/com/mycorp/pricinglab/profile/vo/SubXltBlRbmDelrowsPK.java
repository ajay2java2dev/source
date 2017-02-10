package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubXltBlRbmDelrowsPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="BL_ID_VALUE")
	protected String	blIdValue;

	@Column(name="RBM_ID_TYP")
	protected String	rbmIdTyp;

	@Column(name="CUST_ID_NO")
	protected Long	custIdNo;

	@Column(name="ACCT_NO")
	protected Long	acctNo;

	@Column(name="EFF_TMSTAMP")
	protected Timestamp	effTmstamp;

	@Column(name="MDN")
	protected String	mdn;

	@Column(name="RBM_ID_VALUE")
	protected String	rbmIdValue;

	@Column(name="BL_ID_TYP")
	protected String	blIdTyp;

	
	public String getBlIdValue() {
		return this.blIdValue;
	}

	public void setBlIdValue(String blIdValue) {
		this.blIdValue = blIdValue;
	}

	public String getRbmIdTyp() {
		return this.rbmIdTyp;
	}

	public void setRbmIdTyp(String rbmIdTyp) {
		this.rbmIdTyp = rbmIdTyp;
	}

	public Long getCustIdNo() {
		return this.custIdNo;
	}

	public void setCustIdNo(Long custIdNo) {
		this.custIdNo = custIdNo;
	}

	public Long getAcctNo() {
		return this.acctNo;
	}

	public void setAcctNo(Long acctNo) {
		this.acctNo = acctNo;
	}

	public Timestamp getEffTmstamp() {
		return this.effTmstamp;
	}

	public void setEffTmstamp(Timestamp effTmstamp) {
		this.effTmstamp = effTmstamp;
	}

	public String getMdn() {
		return this.mdn;
	}

	public void setMdn(String mdn) {
		this.mdn = mdn;
	}

	public String getRbmIdValue() {
		return this.rbmIdValue;
	}

	public void setRbmIdValue(String rbmIdValue) {
		this.rbmIdValue = rbmIdValue;
	}

	public String getBlIdTyp() {
		return this.blIdTyp;
	}

	public void setBlIdTyp(String blIdTyp) {
		this.blIdTyp = blIdTyp;
	}

}
