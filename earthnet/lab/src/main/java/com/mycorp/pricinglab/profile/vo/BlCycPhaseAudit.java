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
 * The persistent class for the BL_CYC_PHASE_AUDIT database table.
 * 
 */
@Entity
@Table(name="BL_CYC_PHASE_AUDIT")
@NamedQuery(name="BlCycPhaseAudit.findAll", query="SELECT b FROM BlCycPhaseAudit b")
@IdClass(BlCycPhaseAuditPK.class)
public class BlCycPhaseAudit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CYC_MTH_YR")
	private String cycMthYr;

	@Id
	@Column(name="BL_CYC_NO")
	private String blCycNo;

	@Id
	@Column(name="BL_STREAM_CD")
	private String blStreamCd;

	@Id
	@Column(name="PHASE_TYP")
	private String phaseTyp;

	@Id
	@Column(name="JOB_ITERATION_NO")
	private long jobIterationNo;

	@Id
	@Column(name="RPT_TYP_CD")
	private String rptTypCd;

	@Id
	@Column(name="RPT_ATTR_CD")
	private String rptAttrCd;

	@Column(name="RPT_ATTR_ID_NO")
	private long rptAttrIdNo;

	@Id
	@Column(name="RPT_COL_NAME_CD")
	private String rptColNameCd;

	@Id
	@Column(name="VISION_SOURCE")
	private String visionSource;

	
	@Column(name="DB_TMSTAMP")
	private Timestamp dbTmstamp;

	@Column(name="DB_USERID")
	private String dbUserid;

	@Column(name="RPT_COL_TYP_CD")
	private String rptColTypCd;

	@Column(name="RPT_COL_VALUE")
	private BigDecimal rptColValue;

	public BlCycPhaseAudit() {
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
	
	public void setDbTmstamp(Timestamp dbTmstamp) {
		this.dbTmstamp = dbTmstamp;
	}

	public String getDbUserid() {
		return this.dbUserid;
	}

	public void setDbUserid(String dbUserid) {
		this.dbUserid = dbUserid;
	}

	public String getRptColTypCd() {
		return this.rptColTypCd;
	}

	public void setRptColTypCd(String rptColTypCd) {
		this.rptColTypCd = rptColTypCd;
	}

	public BigDecimal getRptColValue() {
		return this.rptColValue;
	}

	public void setRptColValue(BigDecimal rptColValue) {
		this.rptColValue = rptColValue;
	}

}