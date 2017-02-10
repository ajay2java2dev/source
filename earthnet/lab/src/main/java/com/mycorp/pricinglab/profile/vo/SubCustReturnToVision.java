package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="SUB_CUST_RETURN_TO_VISION")
@IdClass(SubCustReturnToVisionPK.class)
public class SubCustReturnToVision implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CUST_ID_NO")
	public Long custIdNo;

	public Long getCustIdNo() {
		return custIdNo;
}
	public void setCustIdNo(Long custIdNo) {
		this.custIdNo = custIdNo;
	}

	@Column(name="RETURN_TS")
	public Timestamp returnTs;

	public Timestamp getReturnTs() {
		return returnTs;
}
	public void setReturnTs(Timestamp returnTs) {
		this.returnTs = returnTs;
	}

	@Column(name="REQ_USER_ID")
	public String reqUserId;

	public String getReqUserId() {
		return reqUserId;
}
	public void setReqUserId(String reqUserId) {
		this.reqUserId = reqUserId;
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

	@Column(name="REQ_PROCESS_TS")
	public Timestamp reqProcessTs;

	public Timestamp getReqProcessTs() {
		return reqProcessTs;
}
	public void setReqProcessTs(Timestamp reqProcessTs) {
		this.reqProcessTs = reqProcessTs;
	}

	@Column(name="SUCCESS_IND")
	public String successInd;

	public String getSuccessInd() {
		return successInd;
}
	public void setSuccessInd(String successInd) {
		this.successInd = successInd;
	}

	@Column(name="ERR_CODE")
	public String errCode;

	public String getErrCode() {
		return errCode;
}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	@Column(name="ERR_MESSAGE_TXT")
	public String errMessageTxt;

	public String getErrMessageTxt() {
		return errMessageTxt;
}
	public void setErrMessageTxt(String errMessageTxt) {
		this.errMessageTxt = errMessageTxt;
	}

	@Column(name="HOME_SUBSCRIBER")
	public String homeSubscriber;

	public String getHomeSubscriber() {
		return homeSubscriber;
}
	public void setHomeSubscriber(String homeSubscriber) {
		this.homeSubscriber = homeSubscriber;
	}

}