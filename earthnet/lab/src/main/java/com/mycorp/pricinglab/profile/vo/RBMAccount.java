package com.vzwcorp.pricinglab.profile.vo;

import java.util.Date;

public class RBMAccount {
	
	
	String nextBillDateStr;
	 Date nextBillDate;
	 boolean isInMigration; 
	 String nbdCompareResult; //Next Bill Date compare result. Used for the SENT logic in Bill Cycle Change Workflow.
	
	 String accountNum;
	 public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public String getNextBillDateStr() {
		return nextBillDateStr;
	}
	public void setNextBillDateStr(String nextBillDateStr) {
		this.nextBillDateStr = nextBillDateStr;
	}
	public Date getNextBillDate() {
		return nextBillDate;
	}
	public void setNextBillDate(Date nextBillDate) {
		this.nextBillDate = nextBillDate;
	}
	public boolean isInMigration() {
		return isInMigration;
	}
	public void setInMigration(boolean isInMigration) {
		this.isInMigration = isInMigration;
	}
	public String getNbdCompareResult() {
		return nbdCompareResult;
	}
	public void setNbdCompareResult(String nbdCompareResult) {
		this.nbdCompareResult = nbdCompareResult;
	}
	

}
