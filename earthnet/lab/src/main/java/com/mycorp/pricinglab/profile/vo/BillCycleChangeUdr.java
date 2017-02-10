package com.vzwcorp.pricinglab.profile.vo;

import java.util.Date;
import java.util.Map;



public class BillCycleChangeUdr {
	
				
	 //SUB_PENDING_CYCLE_CHANGE table fields.
	String custIdNo;
	String effDtStr;
	String newBlCycNo;
	String oldBlCycNo;
	String ubsrUserId;
	String ubsrTmstampStr; //YYYY-MM-DD HH24:MI:SS
	String status;
	String rbaFlow;
    String nextBillDateStr; //YYYY-MM-DD
    String originalRecord;
    String reconReq;    

    //MTDT_JITR_ROUTING table related fields
    String custJiTRInstance;
    String domainId;
    String domainGroupId;
    String billerId;

    //Internal Helper Fields.
    String trDate; 
    String cpfTranId; 
    boolean isCustOnHomeJitrInstance; 
    boolean isODRRequired;
    boolean isODRCompleted; //To keep track if customer has already been inserted in ODR.
    
    
    Date effDt;
    Date nextBillDate;
    Date ubsrTmstamp; 
    
    Map<String, UBSRAccount> ubsrAccounts; //key=ACCT_NO Example=1
    Map<String, RBMAccount> rbmAccounts;   //key=ACCOUNT_NUM from RBM. Example="123456789-1"

    
    
    public String getCustIdNo() {
		return custIdNo;
	}
	public void setCustIdNo(String custIdNo) {
		this.custIdNo = custIdNo;
	}
	public String getEffDtStr() {
		return effDtStr;
	}
	public void setEffDtStr(String effDtStr) {
		this.effDtStr = effDtStr;
	}
	public String getNewBlCycNo() {
		return newBlCycNo;
	}
	public void setNewBlCycNo(String newBlCycNo) {
		this.newBlCycNo = newBlCycNo;
	}
	public String getOldBlCycNo() {
		return oldBlCycNo;
	}
	public void setOldBlCycNo(String oldBlCycNo) {
		this.oldBlCycNo = oldBlCycNo;
	}
	public String getUbsrUserId() {
		return ubsrUserId;
	}
	public void setUbsrUserId(String ubsrUserId) {
		this.ubsrUserId = ubsrUserId;
	}
	public String getUbsrTmstampStr() {
		return ubsrTmstampStr;
	}
	public void setUbsrTmstampStr(String ubsrTmstampStr) {
		this.ubsrTmstampStr = ubsrTmstampStr;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRbaFlow() {
		return rbaFlow;
	}
	public void setRbaFlow(String rbaFlow) {
		this.rbaFlow = rbaFlow;
	}
	public String getNextBillDateStr() {
		return nextBillDateStr;
	}
	public void setNextBillDateStr(String nextBillDateStr) {
		this.nextBillDateStr = nextBillDateStr;
	}
	public String getOriginalRecord() {
		return originalRecord;
	}
	public void setOriginalRecord(String originalRecord) {
		this.originalRecord = originalRecord;
	}
	public String getReconReq() {
		return reconReq;
	}
	public void setReconReq(String reconReq) {
		this.reconReq = reconReq;
	}
	public String getCustJiTRInstance() {
		return custJiTRInstance;
	}
	public void setCustJiTRInstance(String custJiTRInstance) {
		this.custJiTRInstance = custJiTRInstance;
	}
	public String getDomainId() {
		return domainId;
	}
	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
	public String getDomainGroupId() {
		return domainGroupId;
	}
	public void setDomainGroupId(String domainGroupId) {
		this.domainGroupId = domainGroupId;
	}
	public String getBillerId() {
		return billerId;
	}
	public void setBillerId(String billerId) {
		this.billerId = billerId;
	}
	public String getTrDate() {
		return trDate;
	}
	public void setTrDate(String trDate) {
		this.trDate = trDate;
	}
	public String getCpfTranId() {
		return cpfTranId;
	}
	public void setCpfTranId(String cpfTranId) {
		this.cpfTranId = cpfTranId;
	}
	public boolean isCustOnHomeJitrInstance() {
		return isCustOnHomeJitrInstance;
	}
	public void setCustOnHomeJitrInstance(boolean isCustOnHomeJitrInstance) {
		this.isCustOnHomeJitrInstance = isCustOnHomeJitrInstance;
	}
	public boolean isODRRequired() {
		return isODRRequired;
	}
	public void setODRRequired(boolean isODRRequired) {
		this.isODRRequired = isODRRequired;
	}
	public boolean isODRCompleted() {
		return isODRCompleted;
	}
	public void setODRCompleted(boolean isODRCompleted) {
		this.isODRCompleted = isODRCompleted;
	}
	public boolean isNewCustomer() {
		return isNewCustomer;
	}
	public void setNewCustomer(boolean isNewCustomer) {
		this.isNewCustomer = isNewCustomer;
	}
	public boolean isTransactionSuccessful() {
		return isTransactionSuccessful;
	}
	public void setTransactionSuccessful(boolean isTransactionSuccessful) {
		this.isTransactionSuccessful = isTransactionSuccessful;
	}
	public Date getEffDt() {
		return effDt;
	}
	public void setEffDt(Date effDt) {
		this.effDt = effDt;
	}
	public Date getNextBillDate() {
		return nextBillDate;
	}
	public void setNextBillDate(Date nextBillDate) {
		this.nextBillDate = nextBillDate;
	}
	public Date getUbsrTmstamp() {
		return ubsrTmstamp;
	}
	public void setUbsrTmstamp(Date ubsrTmstamp) {
		this.ubsrTmstamp = ubsrTmstamp;
	}
	public Map<String, UBSRAccount> getUbsrAccounts() {
		return ubsrAccounts;
	}
	public void setUbsrAccounts(Map<String, UBSRAccount> ubsrAccounts) {
		this.ubsrAccounts = ubsrAccounts;
	}
	public Map<String, RBMAccount> getRbmAccounts() {
		return rbmAccounts;
	}
	public void setRbmAccounts(Map<String, RBMAccount> rbmAccounts) {
		this.rbmAccounts = rbmAccounts;
	}
	boolean isNewCustomer; //Tells us if the cycle change is for a new customer. 
    boolean isTransactionSuccessful;
    
   
    /*LoaderError error;    //To hold any error information that will be used by the error handler functions.
    String codeData;
    EDR_CPF_Message receivedMsg;
    String errorCode : optional; 
    String msgData: optional;*/
	

}
