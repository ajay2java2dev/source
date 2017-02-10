package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;

public class SubPendingCycleChangeTest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CUST_ID_NO")
	public Long custIdNo;

	public Long getCustIdNo() {
		return custIdNo;
}
	public void setCustIdNo(Long custIdNo) {
		this.custIdNo = custIdNo;
	}

	@Column(name="EFF_DT")
	public Timestamp effDt;

	public Timestamp getEffDt() {
		return effDt;
}
	public void setEffDt(Timestamp effDt) {
		this.effDt = effDt;
	}

	@Column(name="NEW_BL_CYC_NO")
	public String newBlCycNo;

	public String getNewBlCycNo() {
		return newBlCycNo;
}
	public void setNewBlCycNo(String newBlCycNo) {
		this.newBlCycNo = newBlCycNo;
	}

	@Column(name="OLD_BL_CYC_NO")
	public String oldBlCycNo;

	public String getOldBlCycNo() {
		return oldBlCycNo;
}
	public void setOldBlCycNo(String oldBlCycNo) {
		this.oldBlCycNo = oldBlCycNo;
	}

	@Column(name="UBSR_USERID")
	public String ubsrUserid;

	public String getUbsrUserid() {
		return ubsrUserid;
}
	public void setUbsrUserid(String ubsrUserid) {
		this.ubsrUserid = ubsrUserid;
	}

	@Column(name="UBSR_TMSTAMP")
	public Timestamp ubsrTmstamp;

	public Timestamp getUbsrTmstamp() {
		return ubsrTmstamp;
}
	public void setUbsrTmstamp(Timestamp ubsrTmstamp) {
		this.ubsrTmstamp = ubsrTmstamp;
	}

	@Column(name="STATUS")
	public String status;

	public String getStatus() {
		return status;
}
	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name="RBA_FLOW")
	public String rbaFlow;

	public String getRbaFlow() {
		return rbaFlow;
}
	public void setRbaFlow(String rbaFlow) {
		this.rbaFlow = rbaFlow;
	}

	@Column(name="NEXT_BILL_DATE")
	public Timestamp nextBillDate;

	public Timestamp getNextBillDate() {
		return nextBillDate;
}
	public void setNextBillDate(Timestamp nextBillDate) {
		this.nextBillDate = nextBillDate;
	}

	@Column(name="ORIGINAL_RECORD")
	public String originalRecord;

	public String getOriginalRecord() {
		return originalRecord;
}
	public void setOriginalRecord(String originalRecord) {
		this.originalRecord = originalRecord;
	}

	@Column(name="RECON_REQ")
	public String reconReq;

	public String getReconReq() {
		return reconReq;
}
	public void setReconReq(String reconReq) {
		this.reconReq = reconReq;
	}

}