package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


/**
 * The persistent class for the REF_VIS_XLT_RATING_TRANS_MAP database table.
 * 
 */
@Entity
@Table(name="REF_VIS_XLT_RATING_TRANS_MAP" , schema="MZADMIN")
@IdClass(RefVisXltRatingTransMapPK.class)
public class RefVisXltRatingTransMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="BL_SYSTEM")
	private String blSystem;
	
	@Id
	@Column(name="BL_ID_TYP")
	private String blIdTyp;

	@Id
	@Column(name="BL_ID_VALUE")
	private long blIdValue;

	@Id
	@Column(name="RBM_ID_TYP")
	private String rbmIdTyp;


	@Column(name="RBM_ID_VALUE")
	private String rbmIdValue;

	@Column(name="UB_TIMESTMP")
	private Timestamp ubTimestmp;

	@Column(name="UBSR_TMSTAMP")
	private Timestamp ubsrTmstamp;

	@Column(name="UBSR_USERID")
	private String ubsrUserid;
	
	//	***************************************************
	/*
	@OneToMany(mappedBy = "refVisXltRatingTransMap", cascade = CascadeType.ALL)
	private List<RefVisRatingProdHier>  refVisRatingProdHier;
	
	public List<RefVisRatingProdHier> getRefVisRatingProdHier() {
		return refVisRatingProdHier;
	}
	public void setRefVisRatingProdHier(
			List<RefVisRatingProdHier> refVisRatingProdHier) {
		this.refVisRatingProdHier = refVisRatingProdHier;
	}
		*/
	
	//***************************************** 
	
	


	public String getBlSystem() {
		return this.blSystem;
	}
	public void setBlSystem(String blSystem) {
		this.blSystem = blSystem;
	}
	public String getBlIdTyp() {
		return this.blIdTyp;
	}
	public void setBlIdTyp(String blIdTyp) {
		this.blIdTyp = blIdTyp;
	}
	public long getBlIdValue() {
		return this.blIdValue;
	}
	public void setBlIdValue(long blIdValue) {
		this.blIdValue = blIdValue;
	}
	public String getRbmIdTyp() {
		return this.rbmIdTyp;
	}
	public void setRbmIdTyp(String rbmIdTyp) {
		this.rbmIdTyp = rbmIdTyp;
	}



	public RefVisXltRatingTransMap() {
	}


	public String getRbmIdValue() {
		return this.rbmIdValue;
	}

	public void setRbmIdValue(String rbmIdValue) {
		this.rbmIdValue = rbmIdValue;
	}

	public Timestamp getUbTimestmp() {
		return this.ubTimestmp;
	}

	public void setUbTimestmp(Timestamp ubTimestmp) {
		this.ubTimestmp = ubTimestmp;
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