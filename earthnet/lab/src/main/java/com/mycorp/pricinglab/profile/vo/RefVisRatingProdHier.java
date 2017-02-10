package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the REF_VIS_RATING_PROD_HIER database table.
 * 
 */
@Entity
@Table(name="REF_VIS_RATING_PROD_HIER", schema="MZADMIN")
@IdClass(RefVisRatingProdHierPK.class) // added new 
@NamedQuery(name="RefVisRatingProdHier.findAll", query="SELECT r FROM RefVisRatingProdHier r")
public class RefVisRatingProdHier implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@Column(name="RBM_PARENT_CD")
	private String rbmParentCd;

	@Id
	@Column(name="RBM_PARENT_VALUE")
	private String rbmParentValue;

	@Id
	@Column(name="RBM_CHILD_CD")
	private String rbmChildCd;

	@Id
	@Column(name="RBM_CHILD_VALUE")
	private String rbmChildValue;

	public String getRbmParentCd() {
		return this.rbmParentCd;
	}
	public void setRbmParentCd(String rbmParentCd) {
		this.rbmParentCd = rbmParentCd;
	}
	public String getRbmParentValue() {
		return this.rbmParentValue;
	}
	public void setRbmParentValue(String rbmParentValue) {
		this.rbmParentValue = rbmParentValue;
	}
	public String getRbmChildCd() {
		return this.rbmChildCd;
	}
	public void setRbmChildCd(String rbmChildCd) {
		this.rbmChildCd = rbmChildCd;
	}
	public String getRbmChildValue() {
		return this.rbmChildValue;
	}
	public void setRbmChildValue(String rbmChildValue) {
		this.rbmChildValue = rbmChildValue;
	}
	
	
	/*@ManyToOne
	//@JoinColumns({
			//@JoinColumn(name = "RBM_PARENT_CD", referencedColumnName = "RBM_ID_TYP" , insertable = false, updatable = false),
			//@JoinColumn(name = "RBM_PARENT_VALUE", referencedColumnName = "RBM_ID_VALUE" , insertable = false, updatable = false) })
	private RefVisXltRatingTransMap refVisXltRatingTransMap;*/

	
	
	
	/*@ManyToOne(fetch = FetchType.EAGER)
	@JoinTable(name="REF_VIS_XLT_RATING_TRANS_MAP", joinColumns={@JoinColumn(name="RBM_ID_TYP", referencedColumnName="RBM_PARENT_CD")	})
	private RefVisXltRatingTransMap  refVisXltRatingTransMap;
	
	public RefVisXltRatingTransMap getRefVisXltRatingTransMap() {
		return refVisXltRatingTransMap;
	}
	public void setRefVisRatingProdHier(
			RefVisXltRatingTransMap refVisXltRatingTransMap) {
		this.refVisXltRatingTransMap = refVisXltRatingTransMap;
	}*/
	//@JoinColumn(name="", referencedColumnName="RBM_ID_VALUE")})
	
	// ********************************

	@Column(name="EVENT_TYPE_ID")
	private BigDecimal eventTypeId;

	@Column(name="UBSR_TMSTAMP")
	private Timestamp ubsrTmstamp;

	@Column(name="UBSR_USERID")
	private String ubsrUserid;

	public RefVisRatingProdHier() {
	}


	public BigDecimal getEventTypeId() {
		return this.eventTypeId;
	}

	public void setEventTypeId(BigDecimal eventTypeId) {
		this.eventTypeId = eventTypeId;
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