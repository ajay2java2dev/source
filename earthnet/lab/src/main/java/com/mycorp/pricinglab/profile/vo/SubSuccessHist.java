package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="SUB_SUCCESS_HIST")
@IdClass(SubSuccessHistPK.class)
public class SubSuccessHist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SUCCESS_HIST_ID")
	public Long successHistId;

	public Long getSuccessHistId() {
		return successHistId;
}
	public void setSuccessHistId(Long successHistId) {
		this.successHistId = successHistId;
	}

	@Column(name="CREATE_TS")
	public Timestamp createTs;

	public Timestamp getCreateTs() {
		return createTs;
}
	public void setCreateTs(Timestamp createTs) {
		this.createTs = createTs;
	}

	@Column(name="AUDIT_ID")
	public Long auditId;

	public Long getAuditId() {
		return auditId;
}
	public void setAuditId(Long auditId) {
		this.auditId = auditId;
	}

	@Column(name="TXN_ID")
	public Long txnId;

	public Long getTxnId() {
		return txnId;
}
	public void setTxnId(Long txnId) {
		this.txnId = txnId;
	}

	@Column(name="USER_ID")
	public String userId;

	public String getUserId() {
		return userId;
}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name="CUST_ID_NO")
	public String custIdNo;

	public String getCustIdNo() {
		return custIdNo;
}
	public void setCustIdNo(String custIdNo) {
		this.custIdNo = custIdNo;
	}

	@Column(name="ACCT_NO")
	public String acctNo;

	public String getAcctNo() {
		return acctNo;
}
	public void setAcctNo(String acctNo) {
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

	@Column(name="CPF_TRAN_ID")
	public String cpfTranId;

	public String getCpfTranId() {
		return cpfTranId;
}
	public void setCpfTranId(String cpfTranId) {
		this.cpfTranId = cpfTranId;
	}

	@Column(name="MSG_TYPE")
	public String msgType;

	public String getMsgType() {
		return msgType;
}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	@Column(name="MSG_ACTION")
	public String msgAction;

	public String getMsgAction() {
		return msgAction;
}
	public void setMsgAction(String msgAction) {
		this.msgAction = msgAction;
	}

	@Column(name="MSG_DATA")
	public String msgData;

	public String getMsgData() {
		return msgData;
}
	public void setMsgData(String msgData) {
		this.msgData = msgData;
	}

	@Column(name="SOURCE")
	public String source;

	public String getSource() {
		return source;
}
	public void setSource(String source) {
		this.source = source;
	}

}