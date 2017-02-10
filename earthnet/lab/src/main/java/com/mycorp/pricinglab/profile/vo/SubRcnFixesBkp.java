package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;

import javax.persistence.Column;

public class SubRcnFixesBkp implements Serializable {
	private static final long serialVersionUID = 1L;

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

	@Column(name="RECON_SYSTEM")
	public String reconSystem;

	public String getReconSystem() {
		return reconSystem;
}
	public void setReconSystem(String reconSystem) {
		this.reconSystem = reconSystem;
	}

	@Column(name="RECON_TSTZ")
	public String reconTstz;

	public String getReconTstz() {
		return reconTstz;
}
	public void setReconTstz(String reconTstz) {
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

	@Column(name="BL_SYSTEM")
	public String blSystem;

	public String getBlSystem() {
		return blSystem;
}
	public void setBlSystem(String blSystem) {
		this.blSystem = blSystem;
	}

	@Column(name="RBM_TABLES")
	public String rbmTables;

	public String getRbmTables() {
		return rbmTables;
}
	public void setRbmTables(String rbmTables) {
		this.rbmTables = rbmTables;
	}

}