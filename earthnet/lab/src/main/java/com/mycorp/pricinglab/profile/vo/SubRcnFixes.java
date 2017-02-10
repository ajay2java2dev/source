package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="SUB_RCN_FIXES")
@IdClass(SubRcnFixesPK.class)
public class SubRcnFixes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="RECON_FIXES_ID")
	public Long reconFixesId;

	public Long getReconFixesId() {
		return reconFixesId;
}
	public void setReconFixesId(Long reconFixesId) {
		this.reconFixesId = reconFixesId;
	}

	@Column(name="AUDIT_ID")
	public Long auditId;

	public Long getAuditId() {
		return auditId;
}
	public void setAuditId(Long auditId) {
		this.auditId = auditId;
	}

	@Column(name="CUST_ID_NO")
	public Long custIdNo;

	public Long getCustIdNo() {
		return custIdNo;
}
	public void setCustIdNo(Long custIdNo) {
		this.custIdNo = custIdNo;
	}

	@Column(name="ACCT_NO")
	public Long acctNo;

	public Long getAcctNo() {
		return acctNo;
}
	public void setAcctNo(Long acctNo) {
		this.acctNo = acctNo;
	}

	@Column(name="MDN")
	public String mdn;

	public String getMdn() {
		return mdn;
}
	public void setMdn(String mdn) {
		this.mdn = mdn;
	}

	@Column(name="PROCESS_TYPE")
	public String processType;

	public String getProcessType() {
		return processType;
}
	public void setProcessType(String processType) {
		this.processType = processType;
	}

	@Column(name="RECON_TSTZ")
	public Timestamp reconTstz;

	public Timestamp getReconTstz() {
		return reconTstz;
}
	public void setReconTstz(Timestamp reconTstz) {
		this.reconTstz = reconTstz;
	}

	@Column(name="BL_CYC_NO")
	public String blCycNo;

	public String getBlCycNo() {
		return blCycNo;
}
	public void setBlCycNo(String blCycNo) {
		this.blCycNo = blCycNo;
	}

	@Column(name="UBSR_TABLE")
	public String ubsrTable;

	public String getUbsrTable() {
		return ubsrTable;
}
	public void setUbsrTable(String ubsrTable) {
		this.ubsrTable = ubsrTable;
	}

	@Column(name="NEW_ROW_VALUE")
	public String newRowValue;

	public String getNewRowValue() {
		return newRowValue;
}
	public void setNewRowValue(String newRowValue) {
		this.newRowValue = newRowValue;
	}

	@Column(name="OLD_ROW_VALUE")
	public String oldRowValue;

	public String getOldRowValue() {
		return oldRowValue;
}
	public void setOldRowValue(String oldRowValue) {
		this.oldRowValue = oldRowValue;
	}

	@Column(name="OPERATION")
	public String operation;

	public String getOperation() {
		return operation;
}
	public void setOperation(String operation) {
		this.operation = operation;
	}

	@Column(name="BL_SYSTEM")
	public String blSystem;

	public String getBlSystem() {
		return blSystem;
}
	public void setBlSystem(String blSystem) {
		this.blSystem = blSystem;
	}

	@Column(name="NONUBSR_TABLES")
	public String nonubsrTables;

	public String getNonubsrTables() {
		return nonubsrTables;
}
	public void setNonubsrTables(String nonubsrTables) {
		this.nonubsrTables = nonubsrTables;
	}

	@Column(name="STATUS")
	public String status;

	public String getStatus() {
		return status;
}
	public void setStatus(String status) {
		this.status = status;
	}

}