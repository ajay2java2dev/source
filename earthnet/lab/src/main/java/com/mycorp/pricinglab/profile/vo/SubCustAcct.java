package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SUB_CUST_ACCT")
public class SubCustAcct implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	SubCustAcctPK id = new SubCustAcctPK();

	@Column(name = "PREFER_LANG_CD")
	public String preferLangCd;

	public String getPreferLangCd() {
		return preferLangCd;
	}

	public void setPreferLangCd(String preferLangCd) {
		this.preferLangCd = preferLangCd;
	}

	@Column(name = "PREFER_LANG_DT")
	public Timestamp preferLangDt;

	public Timestamp getPreferLangDt() {
		return preferLangDt;
	}

	public void setPreferLangDt(Timestamp preferLangDt) {
		this.preferLangDt = preferLangDt;
	}

	@Column(name = "BL_ACCT_STAT_CD")
	public String blAcctStatCd;

	public String getBlAcctStatCd() {
		return blAcctStatCd;
	}

	public void setBlAcctStatCd(String blAcctStatCd) {
		this.blAcctStatCd = blAcctStatCd;
	}

	@Column(name = "ESTAB_DT")
	public Timestamp estabDt;

	public Timestamp getEstabDt() {
		return estabDt;
	}

	public void setEstabDt(Timestamp estabDt) {
		this.estabDt = estabDt;
	}

	@Column(name = "UBSR_USERID")
	public String ubsrUserid;

	public String getUbsrUserid() {
		return ubsrUserid;
	}

	public void setUbsrUserid(String ubsrUserid) {
		this.ubsrUserid = ubsrUserid;
	}

	@Column(name = "UBSR_TMSTAMP")
	public Timestamp ubsrTmstamp;

	public Timestamp getUbsrTmstamp() {
		return ubsrTmstamp;
	}

	public void setUbsrTmstamp(Timestamp ubsrTmstamp) {
		this.ubsrTmstamp = ubsrTmstamp;
	}

	@Column(name = "BILLING_RECON_TSTZ")
	public String billingReconTstz;

	public String getBillingReconTstz() {
		return billingReconTstz;
	}

	public void setBillingReconTstz(String billingReconTstz) {
		this.billingReconTstz = billingReconTstz;
	}

	@Column(name = "RATING_RECON_TSTZ")
	public String ratingReconTstz;

	public String getRatingReconTstz() {
		return ratingReconTstz;
	}

	public void setRatingReconTstz(String ratingReconTstz) {
		this.ratingReconTstz = ratingReconTstz;
	}

	@Column(name = "DELETE_IND")
	public String deleteInd;

	public String getDeleteInd() {
		return deleteInd;
	}

	public void setDeleteInd(String deleteInd) {
		this.deleteInd = deleteInd;
	}

	@Column(name = "RBM_POP_IND")
	public String rbmPopInd;

	public String getRbmPopInd() {
		return rbmPopInd;
	}

	public void setRbmPopInd(String rbmPopInd) {
		this.rbmPopInd = rbmPopInd;
	}

	@Column(name = "HUP_BYPASS_CD")
	public String hupBypassCd;

	public String getHupBypassCd() {
		return hupBypassCd;
	}

	public void setHupBypassCd(String hupBypassCd) {
		this.hupBypassCd = hupBypassCd;
	}

	@JoinColumn(name = "CUST_ID_NO", insertable = false, updatable = false)
	@ManyToOne()
	private SubCustomer subCustomer;

	public void setId(SubCustAcctPK id) {
		this.id = id;
	}

	public SubCustAcctPK getId() {
		return id;
	}
}