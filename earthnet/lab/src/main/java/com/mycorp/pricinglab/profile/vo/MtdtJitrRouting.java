package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the MTDT_JITR_ROUTING database table.
 * 
 */
@Entity
@Table(name="MTDT_JITR_ROUTING")
public class MtdtJitrRouting implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CUST_ID_NO")
	private long custIdNo;

	@Column(name="BILLER_ID")
	private String billerId;

	@Column(name="JITR_DOMAIN_GROUP_ID")
	private BigDecimal jitrDomainGroupId;

	@Column(name="JITR_DOMAIN_ID")
	private BigDecimal jitrDomainId;

	@Column(name="JITR_INSTANCE")
	private String jitrInstance;

	@Column(name="JITR_ROUTING_EFF_DT")
	private Timestamp jitrRoutingEffDt;

	@Column(name="JITR_ROUTING_END_DT")
	private Timestamp jitrRoutingEndDt;

	@Column(name="UBSR_TMSTAMP")
	private Timestamp ubsrTmstamp;

	@Column(name="UBSR_USERID")
	private String ubsrUserid;

	public MtdtJitrRouting() {
	}

	public long getCustIdNo() {
		return this.custIdNo;
	}

	public void setCustIdNo(long custIdNo) {
		this.custIdNo = custIdNo;
	}

	public String getBillerId() {
		return this.billerId;
	}

	public void setBillerId(String billerId) {
		this.billerId = billerId;
	}

	public BigDecimal getJitrDomainGroupId() {
		return this.jitrDomainGroupId;
	}

	public void setJitrDomainGroupId(BigDecimal jitrDomainGroupId) {
		this.jitrDomainGroupId = jitrDomainGroupId;
	}

	public BigDecimal getJitrDomainId() {
		return this.jitrDomainId;
	}

	public void setJitrDomainId(BigDecimal jitrDomainId) {
		this.jitrDomainId = jitrDomainId;
	}

	public String getJitrInstance() {
		return this.jitrInstance;
	}

	public void setJitrInstance(String jitrInstance) {
		this.jitrInstance = jitrInstance;
	}

	public Timestamp getJitrRoutingEffDt() {
		return this.jitrRoutingEffDt;
	}

	public void setJitrRoutingEffDt(Timestamp jitrRoutingEffDt) {
		this.jitrRoutingEffDt = jitrRoutingEffDt;
	}

	public Timestamp getJitrRoutingEndDt() {
		return this.jitrRoutingEndDt;
	}

	public void setJitrRoutingEndDt(Timestamp jitrRoutingEndDt) {
		this.jitrRoutingEndDt = jitrRoutingEndDt;
	}

	public Timestamp getUbsrTmstamp() {
		return this.ubsrTmstamp;
	}

	public void setUbsrTmstamp(Timestamp ubsrTmstamp) {
		this.ubsrTmstamp = ubsrTmstamp;
	}

	public String getUbsrUserid() {
		return this.ubsrUserid;
	}

	public void setUbsrUserid(String ubsrUserid) {
		this.ubsrUserid = ubsrUserid;
	}

}