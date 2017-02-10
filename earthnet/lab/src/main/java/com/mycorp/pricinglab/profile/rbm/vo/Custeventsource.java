package com.vzwcorp.pricinglab.profile.rbm.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="CUSTEVENTSOURCE")
@IdClass(CusteventsourcePK.class)
public class Custeventsource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CUSTOMER_REF")
	public String customerRef;

	public String getCustomerRef() {
		return customerRef;
}
	public void setCustomerRef(String customerRef) {
		this.customerRef = customerRef;
	}

	@Id
	@Column(name="PRODUCT_SEQ")
	public Long productSeq;

	public Long getProductSeq() {
		return productSeq;
}
	public void setProductSeq(Long productSeq) {
		this.productSeq = productSeq;
	}

	@Id
	@Column(name="EVENT_SOURCE")
	public String eventSource;

	public String getEventSource() {
		return eventSource;
}
	public void setEventSource(String eventSource) {
		this.eventSource = eventSource;
	}

	@Id
	@Column(name="START_DTM")
	public Timestamp startDtm;

	public Timestamp getStartDtm() {
		return startDtm;
}
	public void setStartDtm(Timestamp startDtm) {
		this.startDtm = startDtm;
	}

	@Column(name="END_DTM")
	public Timestamp endDtm;

	public Timestamp getEndDtm() {
		return endDtm;
}
	public void setEndDtm(Timestamp endDtm) {
		this.endDtm = endDtm;
	}

	@Id
	@Column(name="EVENT_TYPE_ID")
	public Long eventTypeId;

	public Long getEventTypeId() {
		return eventTypeId;
}
	public void setEventTypeId(Long eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	@Column(name="EVENT_SOURCE_LABEL")
	public String eventSourceLabel;

	public String getEventSourceLabel() {
		return eventSourceLabel;
}
	public void setEventSourceLabel(String eventSourceLabel) {
		this.eventSourceLabel = eventSourceLabel;
	}

	@Column(name="CREDIT_LIMIT_MNY")
	public Long creditLimitMny;

	public Long getCreditLimitMny() {
		return creditLimitMny;
}
	public void setCreditLimitMny(Long creditLimitMny) {
		this.creditLimitMny = creditLimitMny;
	}

	@Column(name="EVENT_SOURCE_TXT")
	public String eventSourceTxt;

	public String getEventSourceTxt() {
		return eventSourceTxt;
}
	public void setEventSourceTxt(String eventSourceTxt) {
		this.eventSourceTxt = eventSourceTxt;
	}

	@Column(name="EVENT_SOURCE_UPPER")
	public String eventSourceUpper;

	public String getEventSourceUpper() {
		return eventSourceUpper;
}
	public void setEventSourceUpper(String eventSourceUpper) {
		this.eventSourceUpper = eventSourceUpper;
	}

	@Column(name="RATING_TARIFF_ID")
	public Long ratingTariffId;

	public Long getRatingTariffId() {
		return ratingTariffId;
}
	public void setRatingTariffId(Long ratingTariffId) {
		this.ratingTariffId = ratingTariffId;
	}

	@Column(name="COMPETITOR_RATING_TARIFF_ID")
	public Long competitorRatingTariffId;

	public Long getCompetitorRatingTariffId() {
		return competitorRatingTariffId;
}
	public void setCompetitorRatingTariffId(Long competitorRatingTariffId) {
		this.competitorRatingTariffId = competitorRatingTariffId;
	}

	@Column(name="EVENT_FILTER_1_ID")
	public Long eventFilter1Id;

	public Long getEventFilter1Id() {
		return eventFilter1Id;
}
	public void setEventFilter1Id(Long eventFilter1Id) {
		this.eventFilter1Id = eventFilter1Id;
	}

	@Column(name="RECEIVE_ACCOUNT_1_NUM")
	public String receiveAccount1Num;

	public String getReceiveAccount1Num() {
		return receiveAccount1Num;
}
	public void setReceiveAccount1Num(String receiveAccount1Num) {
		this.receiveAccount1Num = receiveAccount1Num;
	}

	@Column(name="RATING_TARIFF_1_ID")
	public Long ratingTariff1Id;

	public Long getRatingTariff1Id() {
		return ratingTariff1Id;
}
	public void setRatingTariff1Id(Long ratingTariff1Id) {
		this.ratingTariff1Id = ratingTariff1Id;
	}

	@Column(name="ATTRIBUTE_NUMBER_1")
	public Long attributeNumber1;

	public Long getAttributeNumber1() {
		return attributeNumber1;
}
	public void setAttributeNumber1(Long attributeNumber1) {
		this.attributeNumber1 = attributeNumber1;
	}

	@Column(name="MATCH_TYPE_1")
	public Long matchType1;

	public Long getMatchType1() {
		return matchType1;
}
	public void setMatchType1(Long matchType1) {
		this.matchType1 = matchType1;
	}

	@Column(name="ATTRIBUTE_VALUE_1")
	public String attributeValue1;

	public String getAttributeValue1() {
		return attributeValue1;
}
	public void setAttributeValue1(String attributeValue1) {
		this.attributeValue1 = attributeValue1;
	}

	@Column(name="GUIDE_RULE_1_DESC")
	public String guideRule1Desc;

	public String getGuideRule1Desc() {
		return guideRule1Desc;
}
	public void setGuideRule1Desc(String guideRule1Desc) {
		this.guideRule1Desc = guideRule1Desc;
	}

	@Column(name="EVENT_FILTER_2_ID")
	public Long eventFilter2Id;

	public Long getEventFilter2Id() {
		return eventFilter2Id;
}
	public void setEventFilter2Id(Long eventFilter2Id) {
		this.eventFilter2Id = eventFilter2Id;
	}

	@Column(name="RECEIVE_ACCOUNT_2_NUM")
	public String receiveAccount2Num;

	public String getReceiveAccount2Num() {
		return receiveAccount2Num;
}
	public void setReceiveAccount2Num(String receiveAccount2Num) {
		this.receiveAccount2Num = receiveAccount2Num;
	}

	@Column(name="RATING_TARIFF_2_ID")
	public Long ratingTariff2Id;

	public Long getRatingTariff2Id() {
		return ratingTariff2Id;
}
	public void setRatingTariff2Id(Long ratingTariff2Id) {
		this.ratingTariff2Id = ratingTariff2Id;
	}

	@Column(name="ATTRIBUTE_NUMBER_2")
	public Long attributeNumber2;

	public Long getAttributeNumber2() {
		return attributeNumber2;
}
	public void setAttributeNumber2(Long attributeNumber2) {
		this.attributeNumber2 = attributeNumber2;
	}

	@Column(name="MATCH_TYPE_2")
	public Long matchType2;

	public Long getMatchType2() {
		return matchType2;
}
	public void setMatchType2(Long matchType2) {
		this.matchType2 = matchType2;
	}

	@Column(name="ATTRIBUTE_VALUE_2")
	public String attributeValue2;

	public String getAttributeValue2() {
		return attributeValue2;
}
	public void setAttributeValue2(String attributeValue2) {
		this.attributeValue2 = attributeValue2;
	}

	@Column(name="GUIDE_RULE_2_DESC")
	public String guideRule2Desc;

	public String getGuideRule2Desc() {
		return guideRule2Desc;
}
	public void setGuideRule2Desc(String guideRule2Desc) {
		this.guideRule2Desc = guideRule2Desc;
	}

	@Column(name="EVENT_FILTER_3_ID")
	public Long eventFilter3Id;

	public Long getEventFilter3Id() {
		return eventFilter3Id;
}
	public void setEventFilter3Id(Long eventFilter3Id) {
		this.eventFilter3Id = eventFilter3Id;
	}

	@Column(name="RECEIVE_ACCOUNT_3_NUM")
	public String receiveAccount3Num;

	public String getReceiveAccount3Num() {
		return receiveAccount3Num;
}
	public void setReceiveAccount3Num(String receiveAccount3Num) {
		this.receiveAccount3Num = receiveAccount3Num;
	}

	@Column(name="RATING_TARIFF_3_ID")
	public Long ratingTariff3Id;

	public Long getRatingTariff3Id() {
		return ratingTariff3Id;
}
	public void setRatingTariff3Id(Long ratingTariff3Id) {
		this.ratingTariff3Id = ratingTariff3Id;
	}

	@Column(name="ATTRIBUTE_NUMBER_3")
	public Long attributeNumber3;

	public Long getAttributeNumber3() {
		return attributeNumber3;
}
	public void setAttributeNumber3(Long attributeNumber3) {
		this.attributeNumber3 = attributeNumber3;
	}

	@Column(name="MATCH_TYPE_3")
	public Long matchType3;

	public Long getMatchType3() {
		return matchType3;
}
	public void setMatchType3(Long matchType3) {
		this.matchType3 = matchType3;
	}

	@Column(name="ATTRIBUTE_VALUE_3")
	public String attributeValue3;

	public String getAttributeValue3() {
		return attributeValue3;
}
	public void setAttributeValue3(String attributeValue3) {
		this.attributeValue3 = attributeValue3;
	}

	@Column(name="GUIDE_RULE_3_DESC")
	public String guideRule3Desc;

	public String getGuideRule3Desc() {
		return guideRule3Desc;
}
	public void setGuideRule3Desc(String guideRule3Desc) {
		this.guideRule3Desc = guideRule3Desc;
	}

	@Column(name="STRUCTURAL_INTEGER_REF")
	public Long structuralIntegerRef;

	public Long getStructuralIntegerRef() {
		return structuralIntegerRef;
}
	public void setStructuralIntegerRef(Long structuralIntegerRef) {
		this.structuralIntegerRef = structuralIntegerRef;
	}

	@Column(name="MASK_BILL_RULE_ID")
	public Long maskBillRuleId;

	public Long getMaskBillRuleId() {
		return maskBillRuleId;
}
	public void setMaskBillRuleId(Long maskBillRuleId) {
		this.maskBillRuleId = maskBillRuleId;
	}

	@Column(name="MASK_STORE_RULE_ID")
	public Long maskStoreRuleId;

	public Long getMaskStoreRuleId() {
		return maskStoreRuleId;
}
	public void setMaskStoreRuleId(Long maskStoreRuleId) {
		this.maskStoreRuleId = maskStoreRuleId;
	}

	@Column(name="EVENT_DELETION_MODE")
	public Long eventDeletionMode;

	public Long getEventDeletionMode() {
		return eventDeletionMode;
}
	public void setEventDeletionMode(Long eventDeletionMode) {
		this.eventDeletionMode = eventDeletionMode;
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

}