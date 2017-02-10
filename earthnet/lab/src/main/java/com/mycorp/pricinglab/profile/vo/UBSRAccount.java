package com.vzwcorp.pricinglab.profile.vo;

public class UBSRAccount {
	
	
	    String acctNo;
	    String rbmAccountNum; //Holds the ubsr account value with rbm format. [custIdNo-acctNo] -> "123456789-1"
	    public String getAcctNo() {
			return acctNo;
		}
		public void setAcctNo(String acctNo) {
			this.acctNo = acctNo;
		}
		public String getRbmAccountNum() {
			return rbmAccountNum;
		}
		public void setRbmAccountNum(String rbmAccountNum) {
			this.rbmAccountNum = rbmAccountNum;
		}
		
	

}
