package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the BL_CYC_PHASE_AUDIT database table.
 * 
 */
@Embeddable
public class BlCycPhaseAuditPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CYC_MTH_YR")
	private String cycMthYr;

	@Column(name="BL_CYC_NO")
	private String blCycNo;

	@Column(name="BL_STREAM_CD")
	private String blStreamCd;

	@Column(name="PHASE_TYP")
	private String phaseTyp;

	@Column(name="JOB_ITERATION_NO")
	private long jobIterationNo;

	@Column(name="RPT_TYP_CD")
	private String rptTypCd;

	@Column(name="RPT_ATTR_CD")
	private String rptAttrCd;

	@Column(name="RPT_ATTR_ID_NO")
	private long rptAttrIdNo;

	@Column(name="RPT_COL_NAME_CD")
	private String rptColNameCd;

	@Column(name="VISION_SOURCE")
	private String visionSource;

	public BlCycPhaseAuditPK() {
	}
	public String getCycMthYr() {
		return this.cycMthYr;
	}
	public void setCycMthYr(String cycMthYr) {
		this.cycMthYr = cycMthYr;
	}
	public String getBlCycNo() {
		return this.blCycNo;
	}
	public void setBlCycNo(String blCycNo) {
		this.blCycNo = blCycNo;
	}
	public String getBlStreamCd() {
		return this.blStreamCd;
	}
	public void setBlStreamCd(String blStreamCd) {
		this.blStreamCd = blStreamCd;
	}
	public String getPhaseTyp() {
		return this.phaseTyp;
	}
	public void setPhaseTyp(String phaseTyp) {
		this.phaseTyp = phaseTyp;
	}
	public long getJobIterationNo() {
		return this.jobIterationNo;
	}
	public void setJobIterationNo(long jobIterationNo) {
		this.jobIterationNo = jobIterationNo;
	}
	public String getRptTypCd() {
		return this.rptTypCd;
	}
	public void setRptTypCd(String rptTypCd) {
		this.rptTypCd = rptTypCd;
	}
	public String getRptAttrCd() {
		return this.rptAttrCd;
	}
	public void setRptAttrCd(String rptAttrCd) {
		this.rptAttrCd = rptAttrCd;
	}
	public long getRptAttrIdNo() {
		return this.rptAttrIdNo;
	}
	public void setRptAttrIdNo(long rptAttrIdNo) {
		this.rptAttrIdNo = rptAttrIdNo;
	}
	public String getRptColNameCd() {
		return this.rptColNameCd;
	}
	public void setRptColNameCd(String rptColNameCd) {
		this.rptColNameCd = rptColNameCd;
	}
	public String getVisionSource() {
		return this.visionSource;
	}
	public void setVisionSource(String visionSource) {
		this.visionSource = visionSource;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BlCycPhaseAuditPK)) {
			return false;
		}
		BlCycPhaseAuditPK castOther = (BlCycPhaseAuditPK)other;
		return 
			this.cycMthYr.equals(castOther.cycMthYr)
			&& this.blCycNo.equals(castOther.blCycNo)
			&& this.blStreamCd.equals(castOther.blStreamCd)
			&& this.phaseTyp.equals(castOther.phaseTyp)
			&& (this.jobIterationNo == castOther.jobIterationNo)
			&& this.rptTypCd.equals(castOther.rptTypCd)
			&& this.rptAttrCd.equals(castOther.rptAttrCd)
			&& (this.rptAttrIdNo == castOther.rptAttrIdNo)
			&& this.rptColNameCd.equals(castOther.rptColNameCd)
			&& this.visionSource.equals(castOther.visionSource);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cycMthYr.hashCode();
		hash = hash * prime + this.blCycNo.hashCode();
		hash = hash * prime + this.blStreamCd.hashCode();
		hash = hash * prime + this.phaseTyp.hashCode();
		hash = hash * prime + ((int) (this.jobIterationNo ^ (this.jobIterationNo >>> 32)));
		hash = hash * prime + this.rptTypCd.hashCode();
		hash = hash * prime + this.rptAttrCd.hashCode();
		hash = hash * prime + ((int) (this.rptAttrIdNo ^ (this.rptAttrIdNo >>> 32)));
		hash = hash * prime + this.rptColNameCd.hashCode();
		hash = hash * prime + this.visionSource.hashCode();
		
		return hash;
	}
}