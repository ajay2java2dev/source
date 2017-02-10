package com.vzwcorp.pricinglab.profile.rbm.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@Table(name="ACCHASPOLICYINSTANCE")
@IdClass(AcchaspolicyinstancePK.class)
public class Acchaspolicyinstance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ACCOUNT_NUM")
	public String accountNum;

	public String getAccountNum() {
		return accountNum;
}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	@Id
	@Column(name="POLICY_SEQ")
	public Long policySeq;

	public Long getPolicySeq() {
		return policySeq;
}
	public void setPolicySeq(Long policySeq) {
		this.policySeq = policySeq;
	}

	@Column(name="POLICY_ID")
	public Long policyId;

	public Long getPolicyId() {
		return policyId;
}
	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}

	@Column(name="POLICY_INSTANCE_NAME")
	public String policyInstanceName;

	public String getPolicyInstanceName() {
		return policyInstanceName;
}
	public void setPolicyInstanceName(String policyInstanceName) {
		this.policyInstanceName = policyInstanceName;
	}

	@Id
	@Column(name="DOMAIN_ID")
	public Long domainId;

	public Long getDomainId() {
		return domainId;
}
	public void setDomainId(Long domainId) {
		this.domainId = domainId;
	}

	@Column(name="START_DTM")
	public Timestamp startDtm;

	public Timestamp getStartDtm() {
		return startDtm;
}
	public void setStartDtm(Timestamp startDtm) {
		this.startDtm = startDtm;
	}

	@Column(name="EVENT_SOURCE")
	public String eventSource;

	public String getEventSource() {
		return eventSource;
}
	public void setEventSource(String eventSource) {
		this.eventSource = eventSource;
	}

	@Column(name="EVENT_TYPE_ID")
	public Long eventTypeId;

	public Long getEventTypeId() {
		return eventTypeId;
}
	public void setEventTypeId(Long eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	@Column(name="END_DTM")
	public Timestamp endDtm;

	public Timestamp getEndDtm() {
		return endDtm;
}
	public void setEndDtm(Timestamp endDtm) {
		this.endDtm = endDtm;
	}

	@Column(name="DIARY_OVERRIDDEN_BOO")
	public String diaryOverriddenBoo;

	public String getDiaryOverriddenBoo() {
		return diaryOverriddenBoo;
}
	public void setDiaryOverriddenBoo(String diaryOverriddenBoo) {
		this.diaryOverriddenBoo = diaryOverriddenBoo;
	}

	@Column(name="ACTION_OVERRIDDEN_BOO")
	public String actionOverriddenBoo;

	public String getActionOverriddenBoo() {
		return actionOverriddenBoo;
}
	public void setActionOverriddenBoo(String actionOverriddenBoo) {
		this.actionOverriddenBoo = actionOverriddenBoo;
	}

}