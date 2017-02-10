package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="SUB_RECON_BYPASS")
@IdClass(SubReconBypassPK.class)
public class SubReconBypass implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CUST_ID_NO")
	public Long custIdNo;

	public Long getCustIdNo() {
		return custIdNo;
}
	public void setCustIdNo(Long custIdNo) {
		this.custIdNo = custIdNo;
	}

	@Id
	@Column(name="ACCT_NO")
	public Long acctNo;

	public Long getAcctNo() {
		return acctNo;
}
	public void setAcctNo(Long acctNo) {
		this.acctNo = acctNo;
	}

	@Id
	@Column(name="MDN")
	public String mdn;

	public String getMdn() {
		return mdn;
}
	public void setMdn(String mdn) {
		this.mdn = mdn;
	}

	@Id
	@Column(name="EFF_TMSTAMP")
	public Timestamp effTmstamp;

	public Timestamp getEffTmstamp() {
		return effTmstamp;
}
	public void setEffTmstamp(Timestamp effTmstamp) {
		this.effTmstamp = effTmstamp;
	}

	@Column(name="END_TMSTAMP")
	public Timestamp endTmstamp;

	public Timestamp getEndTmstamp() {
		return endTmstamp;
}
	public void setEndTmstamp(Timestamp endTmstamp) {
		this.endTmstamp = endTmstamp;
	}

	@Column(name="UBSR_USERID")
	public String ubsrUserid;

	public String getUbsrUserid() {
		return ubsrUserid;
}
	public void setUbsrUserid(String ubsrUserid) {
		this.ubsrUserid = ubsrUserid;
	}

	@Column(name="UBSR_TMSTAMP")
	public Timestamp ubsrTmstamp;

	public Timestamp getUbsrTmstamp() {
		return ubsrTmstamp;
}
	public void setUbsrTmstamp(Timestamp ubsrTmstamp) {
		this.ubsrTmstamp = ubsrTmstamp;
	}

}