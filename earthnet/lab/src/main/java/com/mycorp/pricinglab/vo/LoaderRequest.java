package com.vzwcorp.pricinglab.vo;

import java.sql.Timestamp;

public class LoaderRequest{

	private Long customerId;
	private Long accountNumber;
	private String mdn;
	private String bl_Cyc_No;
	private String eventType;
	private String dataPopOperation;
	private String mdn_effective_date;
	private String bl_Typ_Cd;
	private Timestamp eff_Dt;
	private String lnOfSvcIdNoP1;
	private String lnOfSvcIdNoP2;
	private String svcProdId;

	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getMdn() {
		return mdn;
	}
	public void setMdn(String mdn) {
		this.mdn = mdn;
	}
	public String getBl_cyc_no() {
		return bl_Cyc_No;
	}
	public void setBl_cyc_no(String bl_cyc_no) {
		this.bl_Cyc_No = bl_cyc_no;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getDataPopOperation() {
		return dataPopOperation;
	}
	public void setDataPopOperation(String dataPopOperation) {
		this.dataPopOperation = dataPopOperation;
	}
	public String getMdn_effective_date() {
		return mdn_effective_date;
	}
	public void setMdn_effective_date(String mdn_effective_date) {
		this.mdn_effective_date = mdn_effective_date;
	}
	public String getBl_Typ_Cd() {
		return bl_Typ_Cd;
	}
	public void setBl_typ_cd(String bl_typ_cd) {
		this.bl_Typ_Cd = bl_typ_cd;
	}
	public Timestamp getEff_dt() {
		return eff_Dt;
	}
	public void setEff_dt(Timestamp eff_dt) {
		this.eff_Dt = eff_dt;
	}
	public String getLnOfSvcIdNoP1() {
		return lnOfSvcIdNoP1;
	}
	public void setLnOfSvcIdNoP1(String lnOfSvcIdNoP1) {
		this.lnOfSvcIdNoP1 = lnOfSvcIdNoP1;
	}
	public String getLnOfSvcIdNoP2() {
		return lnOfSvcIdNoP2;
	}
	public void setLnOfSvcIdNoP2(String lnOfSvcIdNoP2) {
		this.lnOfSvcIdNoP2 = lnOfSvcIdNoP2;
	}
	public String getSvcProdId() {
		return svcProdId;
	}
	public void setSvcProdId(String svcProdId) {
		this.svcProdId = svcProdId;
	}


}
