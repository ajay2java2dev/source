package com.vzwcorp.pricinglab.profile.rbm.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="ACCOUNT")
@IdClass(AccountPK.class)
public class Account implements Serializable {
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

	@Column(name="CUSTOMER_REF")
	public String customerRef;

	public String getCustomerRef() {
		return customerRef;
}
	public void setCustomerRef(String customerRef) {
		this.customerRef = customerRef;
	}

	@Id
	@Column(name="BILLING_STATUS")
	public String billingStatus;

	public String getBillingStatus() {
		return billingStatus;
}
	public void setBillingStatus(String billingStatus) {
		this.billingStatus = billingStatus;
	}

	@Id
	@Column(name="NEXT_BILL_DTM")
	public Timestamp nextBillDtm;

	public Timestamp getNextBillDtm() {
		return nextBillDtm;
}
	public void setNextBillDtm(Timestamp nextBillDtm) {
		this.nextBillDtm = nextBillDtm;
	}

	@Id
	@Column(name="RANDOM_HASH")
	public Long randomHash;

	public Long getRandomHash() {
		return randomHash;
}
	public void setRandomHash(Long randomHash) {
		this.randomHash = randomHash;
	}

	@Column(name="TOTAL_BILLED_TOT")
	public Long totalBilledTot;

	public Long getTotalBilledTot() {
		return totalBilledTot;
}
	public void setTotalBilledTot(Long totalBilledTot) {
		this.totalBilledTot = totalBilledTot;
	}

	@Column(name="TOTAL_PAID_TOT")
	public Long totalPaidTot;

	public Long getTotalPaidTot() {
		return totalPaidTot;
}
	public void setTotalPaidTot(Long totalPaidTot) {
		this.totalPaidTot = totalPaidTot;
	}

	@Column(name="TOTAL_POINTS_EARNED")
	public Long totalPointsEarned;

	public Long getTotalPointsEarned() {
		return totalPointsEarned;
}
	public void setTotalPointsEarned(Long totalPointsEarned) {
		this.totalPointsEarned = totalPointsEarned;
	}

	@Column(name="TOTAL_POINTS_REDEEMED")
	public Long totalPointsRedeemed;

	public Long getTotalPointsRedeemed() {
		return totalPointsRedeemed;
}
	public void setTotalPointsRedeemed(Long totalPointsRedeemed) {
		this.totalPointsRedeemed = totalPointsRedeemed;
	}

	@Column(name="BILLING_DRIVER")
	public String billingDriver;

	public String getBillingDriver() {
		return billingDriver;
}
	public void setBillingDriver(String billingDriver) {
		this.billingDriver = billingDriver;
	}

	@Column(name="CURRENCY_CODE")
	public String currencyCode;

	public String getCurrencyCode() {
		return currencyCode;
}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	@Column(name="BILL_EVENT_SEQ")
	public Long billEventSeq;

	public Long getBillEventSeq() {
		return billEventSeq;
}
	public void setBillEventSeq(Long billEventSeq) {
		this.billEventSeq = billEventSeq;
	}

	@Column(name="LEGAL_DEBT_TOT")
	public Long legalDebtTot;

	public Long getLegalDebtTot() {
		return legalDebtTot;
}
	public void setLegalDebtTot(Long legalDebtTot) {
		this.legalDebtTot = legalDebtTot;
	}

	@Column(name="CHASABLE_DEBT_TOT")
	public Long chasableDebtTot;

	public Long getChasableDebtTot() {
		return chasableDebtTot;
}
	public void setChasableDebtTot(Long chasableDebtTot) {
		this.chasableDebtTot = chasableDebtTot;
	}

	@Column(name="ACTIVE_DISPUTES_TOT")
	public Long activeDisputesTot;

	public Long getActiveDisputesTot() {
		return activeDisputesTot;
}
	public void setActiveDisputesTot(Long activeDisputesTot) {
		this.activeDisputesTot = activeDisputesTot;
	}

	@Column(name="TOTAL_ALLOCATED_TOT")
	public Long totalAllocatedTot;

	public Long getTotalAllocatedTot() {
		return totalAllocatedTot;
}
	public void setTotalAllocatedTot(Long totalAllocatedTot) {
		this.totalAllocatedTot = totalAllocatedTot;
	}

	@Column(name="BILLS_SINCE_STATEMENT")
	public Long billsSinceStatement;

	public Long getBillsSinceStatement() {
		return billsSinceStatement;
}
	public void setBillsSinceStatement(Long billsSinceStatement) {
		this.billsSinceStatement = billsSinceStatement;
	}

	@Column(name="INVOICING_CO_ID")
	public Long invoicingCoId;

	public Long getInvoicingCoId() {
		return invoicingCoId;
}
	public void setInvoicingCoId(Long invoicingCoId) {
		this.invoicingCoId = invoicingCoId;
	}

	@Column(name="DELETE_EVENTS_ON_BILLING_BOO")
	public String deleteEventsOnBillingBoo;

	public String getDeleteEventsOnBillingBoo() {
		return deleteEventsOnBillingBoo;
}
	public void setDeleteEventsOnBillingBoo(String deleteEventsOnBillingBoo) {
		this.deleteEventsOnBillingBoo = deleteEventsOnBillingBoo;
	}

	@Column(name="PERMISSION_ID")
	public Long permissionId;

	public Long getPermissionId() {
		return permissionId;
}
	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	@Column(name="BILL_CHARGE_SEQ")
	public Long billChargeSeq;

	public Long getBillChargeSeq() {
		return billChargeSeq;
}
	public void setBillChargeSeq(Long billChargeSeq) {
		this.billChargeSeq = billChargeSeq;
	}

	@Column(name="ALLOW_PROD_CHANGES_FROM_DAT")
	public Timestamp allowProdChangesFromDat;

	public Timestamp getAllowProdChangesFromDat() {
		return allowProdChangesFromDat;
}
	public void setAllowProdChangesFromDat(Timestamp allowProdChangesFromDat) {
		this.allowProdChangesFromDat = allowProdChangesFromDat;
	}

	@Column(name="TAX_INCLUSIVE_BOO")
	public String taxInclusiveBoo;

	public String getTaxInclusiveBoo() {
		return taxInclusiveBoo;
}
	public void setTaxInclusiveBoo(String taxInclusiveBoo) {
		this.taxInclusiveBoo = taxInclusiveBoo;
	}

	@Column(name="ACCOUNT_NUM_UPPER")
	public String accountNumUpper;

	public String getAccountNumUpper() {
		return accountNumUpper;
}
	public void setAccountNumUpper(String accountNumUpper) {
		this.accountNumUpper = accountNumUpper;
	}

	@Column(name="PREPAY_BOO")
	public String prepayBoo;

	public String getPrepayBoo() {
		return prepayBoo;
}
	public void setPrepayBoo(String prepayBoo) {
		this.prepayBoo = prepayBoo;
	}

	@Column(name="UNBILLED_ADJUSTMENT_MNY")
	public Long unbilledAdjustmentMny;

	public Long getUnbilledAdjustmentMny() {
		return unbilledAdjustmentMny;
}
	public void setUnbilledAdjustmentMny(Long unbilledAdjustmentMny) {
		this.unbilledAdjustmentMny = unbilledAdjustmentMny;
	}

	@Column(name="INTERNAL_ACCOUNT_BOO")
	public String internalAccountBoo;

	public String getInternalAccountBoo() {
		return internalAccountBoo;
}
	public void setInternalAccountBoo(String internalAccountBoo) {
		this.internalAccountBoo = internalAccountBoo;
	}

	@Column(name="EVENTS_PER_DAY")
	public Long eventsPerDay;

	public Long getEventsPerDay() {
		return eventsPerDay;
}
	public void setEventsPerDay(Long eventsPerDay) {
		this.eventsPerDay = eventsPerDay;
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

	@Column(name="ACCOUNT_LOCK_VERSION")
	public Long accountLockVersion;

	public Long getAccountLockVersion() {
		return accountLockVersion;
}
	public void setAccountLockVersion(Long accountLockVersion) {
		this.accountLockVersion = accountLockVersion;
	}

	@Column(name="TRANS_BILLED_TO_DTM")
	public Timestamp transBilledToDtm;

	public Timestamp getTransBilledToDtm() {
		return transBilledToDtm;
}
	public void setTransBilledToDtm(Timestamp transBilledToDtm) {
		this.transBilledToDtm = transBilledToDtm;
	}

	@Column(name="EVENT_BILLED_TO_DTM")
	public Timestamp eventBilledToDtm;

	public Timestamp getEventBilledToDtm() {
		return eventBilledToDtm;
}
	public void setEventBilledToDtm(Timestamp eventBilledToDtm) {
		this.eventBilledToDtm = eventBilledToDtm;
	}

	@Column(name="DEPOSIT_MNY")
	public Long depositMny;

	public Long getDepositMny() {
		return depositMny;
}
	public void setDepositMny(Long depositMny) {
		this.depositMny = depositMny;
	}

	@Column(name="LAST_BILL_SEQ")
	public Long lastBillSeq;

	public Long getLastBillSeq() {
		return lastBillSeq;
}
	public void setLastBillSeq(Long lastBillSeq) {
		this.lastBillSeq = lastBillSeq;
	}

	@Column(name="LAST_BILL_DTM")
	public Timestamp lastBillDtm;

	public Timestamp getLastBillDtm() {
		return lastBillDtm;
}
	public void setLastBillDtm(Timestamp lastBillDtm) {
		this.lastBillDtm = lastBillDtm;
	}

	@Column(name="LAST_BILL_MNY")
	public Long lastBillMny;

	public Long getLastBillMny() {
		return lastBillMny;
}
	public void setLastBillMny(Long lastBillMny) {
		this.lastBillMny = lastBillMny;
	}

	@Column(name="LAST_BILL_POINTS")
	public Long lastBillPoints;

	public Long getLastBillPoints() {
		return lastBillPoints;
}
	public void setLastBillPoints(Long lastBillPoints) {
		this.lastBillPoints = lastBillPoints;
	}

	@Column(name="LAST_DEBT_ANALYSIS_DTM")
	public Timestamp lastDebtAnalysisDtm;

	public Timestamp getLastDebtAnalysisDtm() {
		return lastDebtAnalysisDtm;
}
	public void setLastDebtAnalysisDtm(Timestamp lastDebtAnalysisDtm) {
		this.lastDebtAnalysisDtm = lastDebtAnalysisDtm;
	}

	@Column(name="CREDIT_ADJ_BILLED_TO_DTM")
	public Timestamp creditAdjBilledToDtm;

	public Timestamp getCreditAdjBilledToDtm() {
		return creditAdjBilledToDtm;
}
	public void setCreditAdjBilledToDtm(Timestamp creditAdjBilledToDtm) {
		this.creditAdjBilledToDtm = creditAdjBilledToDtm;
	}

	@Column(name="DEBIT_ADJ_BILLED_TO_DTM")
	public Timestamp debitAdjBilledToDtm;

	public Timestamp getDebitAdjBilledToDtm() {
		return debitAdjBilledToDtm;
}
	public void setDebitAdjBilledToDtm(Timestamp debitAdjBilledToDtm) {
		this.debitAdjBilledToDtm = debitAdjBilledToDtm;
	}

	@Column(name="LOYALTY_TRANS_BILLED_TO_DTM")
	public Timestamp loyaltyTransBilledToDtm;

	public Timestamp getLoyaltyTransBilledToDtm() {
		return loyaltyTransBilledToDtm;
}
	public void setLoyaltyTransBilledToDtm(Timestamp loyaltyTransBilledToDtm) {
		this.loyaltyTransBilledToDtm = loyaltyTransBilledToDtm;
	}

	@Column(name="INFO_CURRENCY_CODE")
	public String infoCurrencyCode;

	public String getInfoCurrencyCode() {
		return infoCurrencyCode;
}
	public void setInfoCurrencyCode(String infoCurrencyCode) {
		this.infoCurrencyCode = infoCurrencyCode;
	}

	@Column(name="TERMINATION_REASON_ID")
	public Long terminationReasonId;

	public Long getTerminationReasonId() {
		return terminationReasonId;
}
	public void setTerminationReasonId(Long terminationReasonId) {
		this.terminationReasonId = terminationReasonId;
	}

	@Column(name="ACCOUNT_NAME")
	public String accountName;

	public String getAccountName() {
		return accountName;
}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@Column(name="DON_PACK_DISC_BILLED_TO_DTM")
	public Timestamp donPackDiscBilledToDtm;

	public Timestamp getDonPackDiscBilledToDtm() {
		return donPackDiscBilledToDtm;
}
	public void setDonPackDiscBilledToDtm(Timestamp donPackDiscBilledToDtm) {
		this.donPackDiscBilledToDtm = donPackDiscBilledToDtm;
	}

	@Column(name="DON_EVENT_DISC_BILLED_TO_DTM")
	public Timestamp donEventDiscBilledToDtm;

	public Timestamp getDonEventDiscBilledToDtm() {
		return donEventDiscBilledToDtm;
}
	public void setDonEventDiscBilledToDtm(Timestamp donEventDiscBilledToDtm) {
		this.donEventDiscBilledToDtm = donEventDiscBilledToDtm;
	}

	@Column(name="TERMINATION_DAT")
	public Timestamp terminationDat;

	public Timestamp getTerminationDat() {
		return terminationDat;
}
	public void setTerminationDat(Timestamp terminationDat) {
		this.terminationDat = terminationDat;
	}

	@Column(name="DEFAULT_CPS_ID")
	public Long defaultCpsId;

	public Long getDefaultCpsId() {
		return defaultCpsId;
}
	public void setDefaultCpsId(Long defaultCpsId) {
		this.defaultCpsId = defaultCpsId;
	}

	@Column(name="LAST_BILLED_DEPOSIT_MNY")
	public Long lastBilledDepositMny;

	public Long getLastBilledDepositMny() {
		return lastBilledDepositMny;
}
	public void setLastBilledDepositMny(Long lastBilledDepositMny) {
		this.lastBilledDepositMny = lastBilledDepositMny;
	}

	@Column(name="UST_ACCOUNT_CLASS_ID")
	public Long ustAccountClassId;

	public Long getUstAccountClassId() {
		return ustAccountClassId;
}
	public void setUstAccountClassId(Long ustAccountClassId) {
		this.ustAccountClassId = ustAccountClassId;
	}

	@Column(name="NEXT_BAND_SUMMARY_DAT")
	public Timestamp nextBandSummaryDat;

	public Timestamp getNextBandSummaryDat() {
		return nextBandSummaryDat;
}
	public void setNextBandSummaryDat(Timestamp nextBandSummaryDat) {
		this.nextBandSummaryDat = nextBandSummaryDat;
	}

	@Column(name="NEXT_DEBT_ACTION_DAT")
	public Timestamp nextDebtActionDat;

	public Timestamp getNextDebtActionDat() {
		return nextDebtActionDat;
}
	public void setNextDebtActionDat(Timestamp nextDebtActionDat) {
		this.nextDebtActionDat = nextDebtActionDat;
	}

	@Column(name="DEBT_ANALYSIS_REQUIRED_DTM")
	public Timestamp debtAnalysisRequiredDtm;

	public Timestamp getDebtAnalysisRequiredDtm() {
		return debtAnalysisRequiredDtm;
}
	public void setDebtAnalysisRequiredDtm(Timestamp debtAnalysisRequiredDtm) {
		this.debtAnalysisRequiredDtm = debtAnalysisRequiredDtm;
	}

	@Column(name="LAST_BAND_SUMMARY_DTM")
	public Timestamp lastBandSummaryDtm;

	public Timestamp getLastBandSummaryDtm() {
		return lastBandSummaryDtm;
}
	public void setLastBandSummaryDtm(Timestamp lastBandSummaryDtm) {
		this.lastBandSummaryDtm = lastBandSummaryDtm;
	}

	@Column(name="LAST_DEBT_ACTION_DTM")
	public Timestamp lastDebtActionDtm;

	public Timestamp getLastDebtActionDtm() {
		return lastDebtActionDtm;
}
	public void setLastDebtActionDtm(Timestamp lastDebtActionDtm) {
		this.lastDebtActionDtm = lastDebtActionDtm;
	}

	@Column(name="EARLIEST_PROD_CHANGE_DAT")
	public Timestamp earliestProdChangeDat;

	public Timestamp getEarliestProdChangeDat() {
		return earliestProdChangeDat;
}
	public void setEarliestProdChangeDat(Timestamp earliestProdChangeDat) {
		this.earliestProdChangeDat = earliestProdChangeDat;
	}

	@Column(name="ACC_ACT_THOLD_SET_ID")
	public Long accActTholdSetId;

	public Long getAccActTholdSetId() {
		return accActTholdSetId;
}
	public void setAccActTholdSetId(Long accActTholdSetId) {
		this.accActTholdSetId = accActTholdSetId;
	}

	@Column(name="EXTERNAL_ACTION_1_ID")
	public Long externalAction1Id;

	public Long getExternalAction1Id() {
		return externalAction1Id;
}
	public void setExternalAction1Id(Long externalAction1Id) {
		this.externalAction1Id = externalAction1Id;
	}

	@Column(name="THRESHOLD_1_TOTAL_TYPE")
	public Long threshold1TotalType;

	public Long getThreshold1TotalType() {
		return threshold1TotalType;
}
	public void setThreshold1TotalType(Long threshold1TotalType) {
		this.threshold1TotalType = threshold1TotalType;
	}

	@Column(name="THRESHOLD_1_VALUE_MNY")
	public Long threshold1ValueMny;

	public Long getThreshold1ValueMny() {
		return threshold1ValueMny;
}
	public void setThreshold1ValueMny(Long threshold1ValueMny) {
		this.threshold1ValueMny = threshold1ValueMny;
	}

	@Column(name="THRESHOLD_1_DIRECTION")
	public Long threshold1Direction;

	public Long getThreshold1Direction() {
		return threshold1Direction;
}
	public void setThreshold1Direction(Long threshold1Direction) {
		this.threshold1Direction = threshold1Direction;
	}

	@Column(name="ACTION_THRESHOLD_1_OWNER")
	public Long actionThreshold1Owner;

	public Long getActionThreshold1Owner() {
		return actionThreshold1Owner;
}
	public void setActionThreshold1Owner(Long actionThreshold1Owner) {
		this.actionThreshold1Owner = actionThreshold1Owner;
	}

	@Column(name="EXTERNAL_ACTION_2_ID")
	public Long externalAction2Id;

	public Long getExternalAction2Id() {
		return externalAction2Id;
}
	public void setExternalAction2Id(Long externalAction2Id) {
		this.externalAction2Id = externalAction2Id;
	}

	@Column(name="THRESHOLD_2_TOTAL_TYPE")
	public Long threshold2TotalType;

	public Long getThreshold2TotalType() {
		return threshold2TotalType;
}
	public void setThreshold2TotalType(Long threshold2TotalType) {
		this.threshold2TotalType = threshold2TotalType;
	}

	@Column(name="THRESHOLD_2_VALUE_MNY")
	public Long threshold2ValueMny;

	public Long getThreshold2ValueMny() {
		return threshold2ValueMny;
}
	public void setThreshold2ValueMny(Long threshold2ValueMny) {
		this.threshold2ValueMny = threshold2ValueMny;
	}

	@Column(name="THRESHOLD_2_DIRECTION")
	public Long threshold2Direction;

	public Long getThreshold2Direction() {
		return threshold2Direction;
}
	public void setThreshold2Direction(Long threshold2Direction) {
		this.threshold2Direction = threshold2Direction;
	}

	@Column(name="ACTION_THRESHOLD_2_OWNER")
	public Long actionThreshold2Owner;

	public Long getActionThreshold2Owner() {
		return actionThreshold2Owner;
}
	public void setActionThreshold2Owner(Long actionThreshold2Owner) {
		this.actionThreshold2Owner = actionThreshold2Owner;
	}

	@Column(name="EXTERNAL_ACTION_3_ID")
	public Long externalAction3Id;

	public Long getExternalAction3Id() {
		return externalAction3Id;
}
	public void setExternalAction3Id(Long externalAction3Id) {
		this.externalAction3Id = externalAction3Id;
	}

	@Column(name="THRESHOLD_3_TOTAL_TYPE")
	public Long threshold3TotalType;

	public Long getThreshold3TotalType() {
		return threshold3TotalType;
}
	public void setThreshold3TotalType(Long threshold3TotalType) {
		this.threshold3TotalType = threshold3TotalType;
	}

	@Column(name="THRESHOLD_3_VALUE_MNY")
	public Long threshold3ValueMny;

	public Long getThreshold3ValueMny() {
		return threshold3ValueMny;
}
	public void setThreshold3ValueMny(Long threshold3ValueMny) {
		this.threshold3ValueMny = threshold3ValueMny;
	}

	@Column(name="THRESHOLD_3_DIRECTION")
	public Long threshold3Direction;

	public Long getThreshold3Direction() {
		return threshold3Direction;
}
	public void setThreshold3Direction(Long threshold3Direction) {
		this.threshold3Direction = threshold3Direction;
	}

	@Column(name="ACTION_THRESHOLD_3_OWNER")
	public Long actionThreshold3Owner;

	public Long getActionThreshold3Owner() {
		return actionThreshold3Owner;
}
	public void setActionThreshold3Owner(Long actionThreshold3Owner) {
		this.actionThreshold3Owner = actionThreshold3Owner;
	}

	@Column(name="EXTERNAL_ACTION_4_ID")
	public Long externalAction4Id;

	public Long getExternalAction4Id() {
		return externalAction4Id;
}
	public void setExternalAction4Id(Long externalAction4Id) {
		this.externalAction4Id = externalAction4Id;
	}

	@Column(name="THRESHOLD_4_TOTAL_TYPE")
	public Long threshold4TotalType;

	public Long getThreshold4TotalType() {
		return threshold4TotalType;
}
	public void setThreshold4TotalType(Long threshold4TotalType) {
		this.threshold4TotalType = threshold4TotalType;
	}

	@Column(name="THRESHOLD_4_VALUE_MNY")
	public Long threshold4ValueMny;

	public Long getThreshold4ValueMny() {
		return threshold4ValueMny;
}
	public void setThreshold4ValueMny(Long threshold4ValueMny) {
		this.threshold4ValueMny = threshold4ValueMny;
	}

	@Column(name="THRESHOLD_4_DIRECTION")
	public Long threshold4Direction;

	public Long getThreshold4Direction() {
		return threshold4Direction;
}
	public void setThreshold4Direction(Long threshold4Direction) {
		this.threshold4Direction = threshold4Direction;
	}

	@Column(name="ACTION_THRESHOLD_4_OWNER")
	public Long actionThreshold4Owner;

	public Long getActionThreshold4Owner() {
		return actionThreshold4Owner;
}
	public void setActionThreshold4Owner(Long actionThreshold4Owner) {
		this.actionThreshold4Owner = actionThreshold4Owner;
	}

	@Column(name="EXTERNAL_ACTION_5_ID")
	public Long externalAction5Id;

	public Long getExternalAction5Id() {
		return externalAction5Id;
}
	public void setExternalAction5Id(Long externalAction5Id) {
		this.externalAction5Id = externalAction5Id;
	}

	@Column(name="THRESHOLD_5_TOTAL_TYPE")
	public Long threshold5TotalType;

	public Long getThreshold5TotalType() {
		return threshold5TotalType;
}
	public void setThreshold5TotalType(Long threshold5TotalType) {
		this.threshold5TotalType = threshold5TotalType;
	}

	@Column(name="THRESHOLD_5_VALUE_MNY")
	public Long threshold5ValueMny;

	public Long getThreshold5ValueMny() {
		return threshold5ValueMny;
}
	public void setThreshold5ValueMny(Long threshold5ValueMny) {
		this.threshold5ValueMny = threshold5ValueMny;
	}

	@Column(name="THRESHOLD_5_DIRECTION")
	public Long threshold5Direction;

	public Long getThreshold5Direction() {
		return threshold5Direction;
}
	public void setThreshold5Direction(Long threshold5Direction) {
		this.threshold5Direction = threshold5Direction;
	}

	@Column(name="ACTION_THRESHOLD_5_OWNER")
	public Long actionThreshold5Owner;

	public Long getActionThreshold5Owner() {
		return actionThreshold5Owner;
}
	public void setActionThreshold5Owner(Long actionThreshold5Owner) {
		this.actionThreshold5Owner = actionThreshold5Owner;
	}

	@Column(name="EXTERNAL_ACTION_6_ID")
	public Long externalAction6Id;

	public Long getExternalAction6Id() {
		return externalAction6Id;
}
	public void setExternalAction6Id(Long externalAction6Id) {
		this.externalAction6Id = externalAction6Id;
	}

	@Column(name="THRESHOLD_6_TOTAL_TYPE")
	public Long threshold6TotalType;

	public Long getThreshold6TotalType() {
		return threshold6TotalType;
}
	public void setThreshold6TotalType(Long threshold6TotalType) {
		this.threshold6TotalType = threshold6TotalType;
	}

	@Column(name="THRESHOLD_6_VALUE_MNY")
	public Long threshold6ValueMny;

	public Long getThreshold6ValueMny() {
		return threshold6ValueMny;
}
	public void setThreshold6ValueMny(Long threshold6ValueMny) {
		this.threshold6ValueMny = threshold6ValueMny;
	}

	@Column(name="THRESHOLD_6_DIRECTION")
	public Long threshold6Direction;

	public Long getThreshold6Direction() {
		return threshold6Direction;
}
	public void setThreshold6Direction(Long threshold6Direction) {
		this.threshold6Direction = threshold6Direction;
	}

	@Column(name="ACTION_THRESHOLD_6_OWNER")
	public Long actionThreshold6Owner;

	public Long getActionThreshold6Owner() {
		return actionThreshold6Owner;
}
	public void setActionThreshold6Owner(Long actionThreshold6Owner) {
		this.actionThreshold6Owner = actionThreshold6Owner;
	}

	@Column(name="HOLIDAY_PROFILE_ID")
	public Long holidayProfileId;

	public Long getHolidayProfileId() {
		return holidayProfileId;
}
	public void setHolidayProfileId(Long holidayProfileId) {
		this.holidayProfileId = holidayProfileId;
	}

	@Column(name="CUSTOMER_CATEGORY")
	public Long customerCategory;

	public Long getCustomerCategory() {
		return customerCategory;
}
	public void setCustomerCategory(Long customerCategory) {
		this.customerCategory = customerCategory;
	}

	@Id
	@Column(name="EXTERNAL_BALANCE_LIID")
	public Long externalBalanceLiid;

	public Long getExternalBalanceLiid() {
		return externalBalanceLiid;
}
	public void setExternalBalanceLiid(Long externalBalanceLiid) {
		this.externalBalanceLiid = externalBalanceLiid;
	}

	@Column(name="OUT_OF_SYNC_COUNTER")
	public Long outOfSyncCounter;

	public Long getOutOfSyncCounter() {
		return outOfSyncCounter;
}
	public void setOutOfSyncCounter(Long outOfSyncCounter) {
		this.outOfSyncCounter = outOfSyncCounter;
	}

	@Column(name="OUT_OF_SYNC_MNY")
	public Long outOfSyncMny;

	public Long getOutOfSyncMny() {
		return outOfSyncMny;
}
	public void setOutOfSyncMny(Long outOfSyncMny) {
		this.outOfSyncMny = outOfSyncMny;
	}

	@Column(name="OUT_OF_SYNC_DTM")
	public Timestamp outOfSyncDtm;

	public Timestamp getOutOfSyncDtm() {
		return outOfSyncDtm;
}
	public void setOutOfSyncDtm(Timestamp outOfSyncDtm) {
		this.outOfSyncDtm = outOfSyncDtm;
	}

	@Column(name="TEMPLATE_REF")
	public String templateRef;

	public String getTemplateRef() {
		return templateRef;
}
	public void setTemplateRef(String templateRef) {
		this.templateRef = templateRef;
	}

	@Column(name="STRUCTURAL_INTEGER_REF")
	public Long structuralIntegerRef;

	public Long getStructuralIntegerRef() {
		return structuralIntegerRef;
}
	public void setStructuralIntegerRef(Long structuralIntegerRef) {
		this.structuralIntegerRef = structuralIntegerRef;
	}

	@Column(name="EVENT_STORE_TRANSITION_SEQ")
	public Long eventStoreTransitionSeq;

	public Long getEventStoreTransitionSeq() {
		return eventStoreTransitionSeq;
}
	public void setEventStoreTransitionSeq(Long eventStoreTransitionSeq) {
		this.eventStoreTransitionSeq = eventStoreTransitionSeq;
	}

	@Column(name="FAST_CACHE_SEQ")
	public Long fastCacheSeq;

	public Long getFastCacheSeq() {
		return fastCacheSeq;
}
	public void setFastCacheSeq(Long fastCacheSeq) {
		this.fastCacheSeq = fastCacheSeq;
	}

	@Column(name="LANGUAGE_ID")
	public Long languageId;

	public Long getLanguageId() {
		return languageId;
}
	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}

	@Column(name="LC_PROFILE_ID")
	public Long lcProfileId;

	public Long getLcProfileId() {
		return lcProfileId;
}
	public void setLcProfileId(Long lcProfileId) {
		this.lcProfileId = lcProfileId;
	}

	@Column(name="COLLECTION_EFFECTIVE_DTM")
	public Timestamp collectionEffectiveDtm;

	public Timestamp getCollectionEffectiveDtm() {
		return collectionEffectiveDtm;
}
	public void setCollectionEffectiveDtm(Timestamp collectionEffectiveDtm) {
		this.collectionEffectiveDtm = collectionEffectiveDtm;
	}

	@Column(name="COLLECTION_EXPIRATION_DTM")
	public Timestamp collectionExpirationDtm;

	public Timestamp getCollectionExpirationDtm() {
		return collectionExpirationDtm;
}
	public void setCollectionExpirationDtm(Timestamp collectionExpirationDtm) {
		this.collectionExpirationDtm = collectionExpirationDtm;
	}

	@Column(name="DISABLE_REALTIME_BILL_BOO")
	public String disableRealtimeBillBoo;

	public String getDisableRealtimeBillBoo() {
		return disableRealtimeBillBoo;
}
	public void setDisableRealtimeBillBoo(String disableRealtimeBillBoo) {
		this.disableRealtimeBillBoo = disableRealtimeBillBoo;
	}

	@Column(name="UNBILLED_REVENUE_CHANGE")
	public Long unbilledRevenueChange;

	public Long getUnbilledRevenueChange() {
		return unbilledRevenueChange;
}
	public void setUnbilledRevenueChange(Long unbilledRevenueChange) {
		this.unbilledRevenueChange = unbilledRevenueChange;
	}

	@Id
	@Column(name="UPRE_ACTION_BOO")
	public String upreActionBoo;

	public String getUpreActionBoo() {
		return upreActionBoo;
}
	public void setUpreActionBoo(String upreActionBoo) {
		this.upreActionBoo = upreActionBoo;
	}

	@Id
	@Column(name="UPRE_NEXT_BILL_DTM")
	public Timestamp upreNextBillDtm;

	public Timestamp getUpreNextBillDtm() {
		return upreNextBillDtm;
}
	public void setUpreNextBillDtm(Timestamp upreNextBillDtm) {
		this.upreNextBillDtm = upreNextBillDtm;
	}

	@Column(name="NEXT_UPRE_SEQ")
	public Long nextUpreSeq;

	public Long getNextUpreSeq() {
		return nextUpreSeq;
}
	public void setNextUpreSeq(Long nextUpreSeq) {
		this.nextUpreSeq = nextUpreSeq;
	}

	@Column(name="COLLECTION_BALANCE_SEQ")
	public Long collectionBalanceSeq;

	public Long getCollectionBalanceSeq() {
		return collectionBalanceSeq;
}
	public void setCollectionBalanceSeq(Long collectionBalanceSeq) {
		this.collectionBalanceSeq = collectionBalanceSeq;
	}

	@Column(name="OFFER_EXISTS_FOR_CUST_BOO")
	public String offerExistsForCustBoo;

	public String getOfferExistsForCustBoo() {
		return offerExistsForCustBoo;
}
	public void setOfferExistsForCustBoo(String offerExistsForCustBoo) {
		this.offerExistsForCustBoo = offerExistsForCustBoo;
	}

	@Column(name="TOTAL_UNBILLED_OTC_TOT")
	public Long totalUnbilledOtcTot;

	public Long getTotalUnbilledOtcTot() {
		return totalUnbilledOtcTot;
}
	public void setTotalUnbilledOtcTot(Long totalUnbilledOtcTot) {
		this.totalUnbilledOtcTot = totalUnbilledOtcTot;
	}

	@Column(name="UNBILLED_REALTIME_OTC_MNY")
	public Long unbilledRealtimeOtcMny;

	public Long getUnbilledRealtimeOtcMny() {
		return unbilledRealtimeOtcMny;
}
	public void setUnbilledRealtimeOtcMny(Long unbilledRealtimeOtcMny) {
		this.unbilledRealtimeOtcMny = unbilledRealtimeOtcMny;
	}

	@Column(name="OTC_BILLED_TO_DTM")
	public Timestamp otcBilledToDtm;

	public Timestamp getOtcBilledToDtm() {
		return otcBilledToDtm;
}
	public void setOtcBilledToDtm(Timestamp otcBilledToDtm) {
		this.otcBilledToDtm = otcBilledToDtm;
	}

	@Column(name="UNBILLED_PAID_DEP_REQ_MNY")
	public Long unbilledPaidDepReqMny;

	public Long getUnbilledPaidDepReqMny() {
		return unbilledPaidDepReqMny;
}
	public void setUnbilledPaidDepReqMny(Long unbilledPaidDepReqMny) {
		this.unbilledPaidDepReqMny = unbilledPaidDepReqMny;
	}

	@Column(name="RECHARGE_EVENT_SOURCE")
	public String rechargeEventSource;

	public String getRechargeEventSource() {
		return rechargeEventSource;
}
	public void setRechargeEventSource(String rechargeEventSource) {
		this.rechargeEventSource = rechargeEventSource;
	}

	@Column(name="RECHARGE_EVENT_TYPE_ID")
	public Long rechargeEventTypeId;

	public Long getRechargeEventTypeId() {
		return rechargeEventTypeId;
}
	public void setRechargeEventTypeId(Long rechargeEventTypeId) {
		this.rechargeEventTypeId = rechargeEventTypeId;
	}

	@Column(name="TOTAL_DEFERRED_AMOUNT_TOT")
	public Long totalDeferredAmountTot;

	public Long getTotalDeferredAmountTot() {
		return totalDeferredAmountTot;
}
	public void setTotalDeferredAmountTot(Long totalDeferredAmountTot) {
		this.totalDeferredAmountTot = totalDeferredAmountTot;
	}

	@Column(name="OVERWRITTEN_INVOICE_MNY")
	public Long overwrittenInvoiceMny;

	public Long getOverwrittenInvoiceMny() {
		return overwrittenInvoiceMny;
}
	public void setOverwrittenInvoiceMny(Long overwrittenInvoiceMny) {
		this.overwrittenInvoiceMny = overwrittenInvoiceMny;
	}

	@Column(name="PAYMENT_PLAN_TYPE")
	public Long paymentPlanType;

	public Long getPaymentPlanType() {
		return paymentPlanType;
}
	public void setPaymentPlanType(Long paymentPlanType) {
		this.paymentPlanType = paymentPlanType;
	}

	@Column(name="TOTAL_PAID_DISCOUNT_TOT")
	public Long totalPaidDiscountTot;

	public Long getTotalPaidDiscountTot() {
		return totalPaidDiscountTot;
}
	public void setTotalPaidDiscountTot(Long totalPaidDiscountTot) {
		this.totalPaidDiscountTot = totalPaidDiscountTot;
	}

	@Column(name="PENDING_PAID_TOT")
	public Long pendingPaidTot;

	public Long getPendingPaidTot() {
		return pendingPaidTot;
}
	public void setPendingPaidTot(Long pendingPaidTot) {
		this.pendingPaidTot = pendingPaidTot;
	}

	@Column(name="LOGICALLY_DELETED_BOO")
	public String logicallyDeletedBoo;

	public String getLogicallyDeletedBoo() {
		return logicallyDeletedBoo;
}
	public void setLogicallyDeletedBoo(String logicallyDeletedBoo) {
		this.logicallyDeletedBoo = logicallyDeletedBoo;
	}

	@Column(name="OUTPUT_EVENT_TO_FILE_BOO")
	public String outputEventToFileBoo;

	public String getOutputEventToFileBoo() {
		return outputEventToFileBoo;
}
	public void setOutputEventToFileBoo(String outputEventToFileBoo) {
		this.outputEventToFileBoo = outputEventToFileBoo;
	}

	@Column(name="LAST_TRANSFER_DTM")
	public Timestamp lastTransferDtm;

	public Timestamp getLastTransferDtm() {
		return lastTransferDtm;
}
	public void setLastTransferDtm(Timestamp lastTransferDtm) {
		this.lastTransferDtm = lastTransferDtm;
	}

	@Column(name="TRANSFER_INDICATOR")
	public Long transferIndicator;

	public Long getTransferIndicator() {
		return transferIndicator;
}
	public void setTransferIndicator(Long transferIndicator) {
		this.transferIndicator = transferIndicator;
	}

	@Column(name="EVENT_DISPATCH_PROFILE_ID")
	public Long eventDispatchProfileId;

	public Long getEventDispatchProfileId() {
		return eventDispatchProfileId;
}
	public void setEventDispatchProfileId(Long eventDispatchProfileId) {
		this.eventDispatchProfileId = eventDispatchProfileId;
	}

	@Column(name="AGGREGATE_START_DAT")
	public Timestamp aggregateStartDat;

	public Timestamp getAggregateStartDat() {
		return aggregateStartDat;
}
	public void setAggregateStartDat(Timestamp aggregateStartDat) {
		this.aggregateStartDat = aggregateStartDat;
	}

	@Column(name="DORMANCY_LC_PROFILE_ID")
	public Long dormancyLcProfileId;

	public Long getDormancyLcProfileId() {
		return dormancyLcProfileId;
}
	public void setDormancyLcProfileId(Long dormancyLcProfileId) {
		this.dormancyLcProfileId = dormancyLcProfileId;
	}

	@Column(name="PROMOTION_START_DTM")
	public Timestamp promotionStartDtm;

	public Timestamp getPromotionStartDtm() {
		return promotionStartDtm;
}
	public void setPromotionStartDtm(Timestamp promotionStartDtm) {
		this.promotionStartDtm = promotionStartDtm;
	}

	@Column(name="PROMOTION_END_DTM")
	public Timestamp promotionEndDtm;

	public Timestamp getPromotionEndDtm() {
		return promotionEndDtm;
}
	public void setPromotionEndDtm(Timestamp promotionEndDtm) {
		this.promotionEndDtm = promotionEndDtm;
	}

	@Column(name="HIGHEST_PROMOTION_SCOPE")
	public Long highestPromotionScope;

	public Long getHighestPromotionScope() {
		return highestPromotionScope;
}
	public void setHighestPromotionScope(Long highestPromotionScope) {
		this.highestPromotionScope = highestPromotionScope;
	}

	@Column(name="EXCLUDE_FROM_PROMO_BOO")
	public String excludeFromPromoBoo;

	public String getExcludeFromPromoBoo() {
		return excludeFromPromoBoo;
}
	public void setExcludeFromPromoBoo(String excludeFromPromoBoo) {
		this.excludeFromPromoBoo = excludeFromPromoBoo;
	}

	@Column(name="TOTAL_INTERNAL_PAYMENT_TOT")
	public Long totalInternalPaymentTot;

	public Long getTotalInternalPaymentTot() {
		return totalInternalPaymentTot;
}
	public void setTotalInternalPaymentTot(Long totalInternalPaymentTot) {
		this.totalInternalPaymentTot = totalInternalPaymentTot;
	}

	@Column(name="TOTAL_BILLED_INSTLMNT_TOT")
	public Long totalBilledInstlmntTot;

	public Long getTotalBilledInstlmntTot() {
		return totalBilledInstlmntTot;
}
	public void setTotalBilledInstlmntTot(Long totalBilledInstlmntTot) {
		this.totalBilledInstlmntTot = totalBilledInstlmntTot;
	}

	@Column(name="UNBILLED_TERM_INSTLMNT_MNY")
	public Long unbilledTermInstlmntMny;

	public Long getUnbilledTermInstlmntMny() {
		return unbilledTermInstlmntMny;
}
	public void setUnbilledTermInstlmntMny(Long unbilledTermInstlmntMny) {
		this.unbilledTermInstlmntMny = unbilledTermInstlmntMny;
	}

	@Column(name="TOTAL_WRITEOFF_TOT")
	public Long totalWriteoffTot;

	public Long getTotalWriteoffTot() {
		return totalWriteoffTot;
}
	public void setTotalWriteoffTot(Long totalWriteoffTot) {
		this.totalWriteoffTot = totalWriteoffTot;
	}

	@Column(name="LAST_EVENT_SEQ_NUM")
	public Long lastEventSeqNum;

	public Long getLastEventSeqNum() {
		return lastEventSeqNum;
}
	public void setLastEventSeqNum(Long lastEventSeqNum) {
		this.lastEventSeqNum = lastEventSeqNum;
	}

	@Column(name="LAST_BILL_PERIOD_NUM")
	public Long lastBillPeriodNum;

	public Long getLastBillPeriodNum() {
		return lastBillPeriodNum;
}
	public void setLastBillPeriodNum(Long lastBillPeriodNum) {
		this.lastBillPeriodNum = lastBillPeriodNum;
	}

	@Column(name="TAX_RE_ENGINEER_BOO")
	public String taxReEngineerBoo;

	public String getTaxReEngineerBoo() {
		return taxReEngineerBoo;
}
	public void setTaxReEngineerBoo(String taxReEngineerBoo) {
		this.taxReEngineerBoo = taxReEngineerBoo;
	}

}