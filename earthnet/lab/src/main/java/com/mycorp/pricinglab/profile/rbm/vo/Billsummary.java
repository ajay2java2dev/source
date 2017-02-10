package com.vzwcorp.pricinglab.profile.rbm.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the BILLSUMMARY database table.
 * 
 */
@Entity
@NamedQuery(name="Billsummary.findAll", query="SELECT b FROM Billsummary b")
@IdClass(BillsummaryPK.class)
public class Billsummary implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ACCOUNT_NUM")
	private String accountNum;
	
	@Id
	@Column(name="BILL_SEQ")
	private long billSeq;

	@Id
	@Column(name="BILL_VERSION")
	private long billVersion;


	public String getAccountNum() {
		return this.accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public long getBillSeq() {
		return this.billSeq;
	}
	public void setBillSeq(long billSeq) {
		this.billSeq = billSeq;
	}
	public long getBillVersion() {
		return this.billVersion;
	}
	public void setBillVersion(long billVersion) {
		this.billVersion = billVersion;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="ACTUAL_BILL_DTM")
	private Date actualBillDtm;

	@Temporal(TemporalType.DATE)
	@Column(name="ADFG_PROCESSED_DTM")
	private Date adfgProcessedDtm;

	@Column(name="ADJUSTMENTS_MNY")
	private BigDecimal adjustmentsMny;

	@Temporal(TemporalType.DATE)
	@Column(name="ARCHIVE_DAT")
	private Date archiveDat;

	@Column(name="BALANCE_FWD_MNY")
	private BigDecimal balanceFwdMny;

	@Column(name="BALANCE_OUT_MNY")
	private BigDecimal balanceOutMny;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_DTM")
	private Date billDtm;

	@Column(name="BILL_HANDLING_CODE")
	private String billHandlingCode;

	@Column(name="BILL_LABEL")
	private String billLabel;

	@Column(name="BILL_SETTLED_BOO")
	private String billSettledBoo;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_SETTLED_DTM")
	private Date billSettledDtm;

	@Column(name="BILL_STATUS")
	private BigDecimal billStatus;

	@Column(name="BILL_TYPE_ID")
	private BigDecimal billTypeId;

	@Column(name="BILLED_WITH_BILL_SEQ")
	private BigDecimal billedWithBillSeq;

	@Column(name="BILLING_OFFER_ID")
	private BigDecimal billingOfferId;

	@Temporal(TemporalType.DATE)
	@Column(name="CANCELLATION_DTM")
	private Date cancellationDtm;

	@Temporal(TemporalType.DATE)
	@Column(name="CANCELLATION_REQUEST_DAT")
	private Date cancellationRequestDat;

	@Column(name="CANCELLED_REASON_TXT")
	private String cancelledReasonTxt;

	@Column(name="CHARGE_SEQ")
	private BigDecimal chargeSeq;

	@Column(name="CHASE_DEBT_MNY")
	private BigDecimal chaseDebtMny;

	@Column(name="COLLECTION_NOTIFY_BOO")
	private String collectionNotifyBoo;

	@Temporal(TemporalType.DATE)
	@Column(name="CREDIT_ADJ_BILLED_TO_DTM")
	private Date creditAdjBilledToDtm;

	@Column(name="CURRENT_DISPUTE_MNY")
	private BigDecimal currentDisputeMny;

	@Temporal(TemporalType.DATE)
	@Column(name="DEBIT_ADJ_BILLED_TO_DTM")
	private Date debitAdjBilledToDtm;

	@Temporal(TemporalType.DATE)
	@Column(name="DEBT_START_DAT")
	private Date debtStartDat;

	@Column(name="DEFERRED_CHARGES_TOT")
	private BigDecimal deferredChargesTot;

	@Column(name="DISCOUNTED_RERATING_BOO")
	private String discountedReratingBoo;

	@Column(name="DOMAIN_ID")
	private BigDecimal domainId;

	@Column(name="EBD_EMAIL_STATUS")
	private String ebdEmailStatus;

	@Column(name="EVENT_ARCHIVE_STATUS")
	private BigDecimal eventArchiveStatus;

	@Temporal(TemporalType.DATE)
	@Column(name="EVENT_BILLED_TO_DTM")
	private Date eventBilledToDtm;

	@Column(name="EVENT_DEL_NO_ARCHIVE_BOO")
	private String eventDelNoArchiveBoo;

	@Column(name="EVENT_MASKED_BOO")
	private String eventMaskedBoo;

	@Column(name="EVENT_SEQ")
	private BigDecimal eventSeq;

	@Column(name="FAILED_PAYMENTS_MNY")
	private BigDecimal failedPaymentsMny;

	@Column(name="INFO_CURRENCY_CODE")
	private String infoCurrencyCode;

	@Column(name="INFO_INVOICE_MNY")
	private BigDecimal infoInvoiceMny;

	@Column(name="INVOICE_NET_MNY")
	private BigDecimal invoiceNetMny;

	@Column(name="INVOICE_NUM")
	private String invoiceNum;

	@Column(name="INVOICE_NUM_STATUS")
	private BigDecimal invoiceNumStatus;

	@Column(name="INVOICE_REGISTER_ID")
	private BigDecimal invoiceRegisterId;

	@Column(name="INVOICE_TAX_MNY")
	private BigDecimal invoiceTaxMny;

	@Column(name="LATE_FEE_MNY")
	private BigDecimal lateFeeMny;

	@Temporal(TemporalType.DATE)
	@Column(name="LOYALTY_TRANS_BILLED_TO_DTM")
	private Date loyaltyTransBilledToDtm;

	@Temporal(TemporalType.DATE)
	@Column(name="MAX_BILLED_EVENT_DTM")
	private Date maxBilledEventDtm;

	@Temporal(TemporalType.DATE)
	@Column(name="MAX_RERATED_EVENT_DTM")
	private Date maxReratedEventDtm;

	@Temporal(TemporalType.DATE)
	@Column(name="MIN_BILLED_EVENT_DTM")
	private Date minBilledEventDtm;

	@Temporal(TemporalType.DATE)
	@Column(name="MIN_RERATED_EVENT_DTM")
	private Date minReratedEventDtm;

	@Column(name="MODIFICATION_DAYS")
	private BigDecimal modificationDays;

	@Column(name="OCR_NUM")
	private String ocrNum;

	@Column(name="OEFR_CREATED_BOO")
	private String oefrCreatedBoo;

	@Temporal(TemporalType.DATE)
	@Column(name="OTC_BILLED_TO_DTM")
	private Date otcBilledToDtm;

	@Column(name="OTC_MNY")
	private BigDecimal otcMny;

	@Column(name="OUTSTANDING_DEBT_MNY")
	private BigDecimal outstandingDebtMny;

	@Temporal(TemporalType.DATE)
	@Column(name="OVERRIDE_BILL_DTM")
	private Date overrideBillDtm;

	@Column(name="OVERWRITTEN_DEF_CHARGE_TOT")
	private BigDecimal overwrittenDefChargeTot;

	@Column(name="PA_DP_INVOICE_BILL_MNY")
	private BigDecimal paDpInvoiceBillMny;

	@Column(name="PA_FED_INVOICE_BILL_MNY")
	private BigDecimal paFedInvoiceBillMny;

	@Column(name="PA_FED_INVOICE_DUE_MNY")
	private BigDecimal paFedInvoiceDueMny;

	@Column(name="PA_PAYMENT_MNY")
	private BigDecimal paPaymentMny;

	@Temporal(TemporalType.DATE)
	@Column(name="PAYMENT_DUE_DAT")
	private Date paymentDueDat;

	@Column(name="PAYMENTS_MNY")
	private BigDecimal paymentsMny;

	@Column(name="POINTS_ADJUSTED")
	private BigDecimal pointsAdjusted;

	@Column(name="POINTS_EARNED")
	private BigDecimal pointsEarned;

	@Column(name="POINTS_FWD")
	private BigDecimal pointsFwd;

	@Column(name="POINTS_OUT")
	private BigDecimal pointsOut;

	@Column(name="POINTS_REDEEMED")
	private BigDecimal pointsRedeemed;

	@Column(name="RANDOM_HASH")
	private BigDecimal randomHash;

	@Column(name="REFUNDS_MNY")
	private BigDecimal refundsMny;

	@Column(name="REPLACES_INVOICE_NUM")
	private String replacesInvoiceNum;

	@Column(name="SETTLEUP_CHARGES_TOT")
	private BigDecimal settleupChargesTot;

	@Temporal(TemporalType.DATE)
	@Column(name="START_OF_BILL_DTM")
	private Date startOfBillDtm;

	@Column(name="STATEMENT_BOO")
	private String statementBoo;

	@Temporal(TemporalType.DATE)
	@Column(name="TAX_POINT_DAT")
	private Date taxPointDat;

	@Temporal(TemporalType.DATE)
	@Column(name="TRANS_BILLED_TO_DTM")
	private Date transBilledToDtm;

	@Column(name="UST_CURRENCY_CODE")
	private String ustCurrencyCode;

	public Billsummary() {
	}

	public Date getActualBillDtm() {
		return this.actualBillDtm;
	}

	public void setActualBillDtm(Date actualBillDtm) {
		this.actualBillDtm = actualBillDtm;
	}

	public Date getAdfgProcessedDtm() {
		return this.adfgProcessedDtm;
	}

	public void setAdfgProcessedDtm(Date adfgProcessedDtm) {
		this.adfgProcessedDtm = adfgProcessedDtm;
	}

	public BigDecimal getAdjustmentsMny() {
		return this.adjustmentsMny;
	}

	public void setAdjustmentsMny(BigDecimal adjustmentsMny) {
		this.adjustmentsMny = adjustmentsMny;
	}

	public Date getArchiveDat() {
		return this.archiveDat;
	}

	public void setArchiveDat(Date archiveDat) {
		this.archiveDat = archiveDat;
	}

	public BigDecimal getBalanceFwdMny() {
		return this.balanceFwdMny;
	}

	public void setBalanceFwdMny(BigDecimal balanceFwdMny) {
		this.balanceFwdMny = balanceFwdMny;
	}

	public BigDecimal getBalanceOutMny() {
		return this.balanceOutMny;
	}

	public void setBalanceOutMny(BigDecimal balanceOutMny) {
		this.balanceOutMny = balanceOutMny;
	}

	public Date getBillDtm() {
		return this.billDtm;
	}

	public void setBillDtm(Date billDtm) {
		this.billDtm = billDtm;
	}

	public String getBillHandlingCode() {
		return this.billHandlingCode;
	}

	public void setBillHandlingCode(String billHandlingCode) {
		this.billHandlingCode = billHandlingCode;
	}

	public String getBillLabel() {
		return this.billLabel;
	}

	public void setBillLabel(String billLabel) {
		this.billLabel = billLabel;
	}

	public String getBillSettledBoo() {
		return this.billSettledBoo;
	}

	public void setBillSettledBoo(String billSettledBoo) {
		this.billSettledBoo = billSettledBoo;
	}

	public Date getBillSettledDtm() {
		return this.billSettledDtm;
	}

	public void setBillSettledDtm(Date billSettledDtm) {
		this.billSettledDtm = billSettledDtm;
	}

	public BigDecimal getBillStatus() {
		return this.billStatus;
	}

	public void setBillStatus(BigDecimal billStatus) {
		this.billStatus = billStatus;
	}

	public BigDecimal getBillTypeId() {
		return this.billTypeId;
	}

	public void setBillTypeId(BigDecimal billTypeId) {
		this.billTypeId = billTypeId;
	}

	public BigDecimal getBilledWithBillSeq() {
		return this.billedWithBillSeq;
	}

	public void setBilledWithBillSeq(BigDecimal billedWithBillSeq) {
		this.billedWithBillSeq = billedWithBillSeq;
	}

	public BigDecimal getBillingOfferId() {
		return this.billingOfferId;
	}

	public void setBillingOfferId(BigDecimal billingOfferId) {
		this.billingOfferId = billingOfferId;
	}

	public Date getCancellationDtm() {
		return this.cancellationDtm;
	}

	public void setCancellationDtm(Date cancellationDtm) {
		this.cancellationDtm = cancellationDtm;
	}

	public Date getCancellationRequestDat() {
		return this.cancellationRequestDat;
	}

	public void setCancellationRequestDat(Date cancellationRequestDat) {
		this.cancellationRequestDat = cancellationRequestDat;
	}

	public String getCancelledReasonTxt() {
		return this.cancelledReasonTxt;
	}

	public void setCancelledReasonTxt(String cancelledReasonTxt) {
		this.cancelledReasonTxt = cancelledReasonTxt;
	}

	public BigDecimal getChargeSeq() {
		return this.chargeSeq;
	}

	public void setChargeSeq(BigDecimal chargeSeq) {
		this.chargeSeq = chargeSeq;
	}

	public BigDecimal getChaseDebtMny() {
		return this.chaseDebtMny;
	}

	public void setChaseDebtMny(BigDecimal chaseDebtMny) {
		this.chaseDebtMny = chaseDebtMny;
	}

	public String getCollectionNotifyBoo() {
		return this.collectionNotifyBoo;
	}

	public void setCollectionNotifyBoo(String collectionNotifyBoo) {
		this.collectionNotifyBoo = collectionNotifyBoo;
	}

	public Date getCreditAdjBilledToDtm() {
		return this.creditAdjBilledToDtm;
	}

	public void setCreditAdjBilledToDtm(Date creditAdjBilledToDtm) {
		this.creditAdjBilledToDtm = creditAdjBilledToDtm;
	}

	public BigDecimal getCurrentDisputeMny() {
		return this.currentDisputeMny;
	}

	public void setCurrentDisputeMny(BigDecimal currentDisputeMny) {
		this.currentDisputeMny = currentDisputeMny;
	}

	public Date getDebitAdjBilledToDtm() {
		return this.debitAdjBilledToDtm;
	}

	public void setDebitAdjBilledToDtm(Date debitAdjBilledToDtm) {
		this.debitAdjBilledToDtm = debitAdjBilledToDtm;
	}

	public Date getDebtStartDat() {
		return this.debtStartDat;
	}

	public void setDebtStartDat(Date debtStartDat) {
		this.debtStartDat = debtStartDat;
	}

	public BigDecimal getDeferredChargesTot() {
		return this.deferredChargesTot;
	}

	public void setDeferredChargesTot(BigDecimal deferredChargesTot) {
		this.deferredChargesTot = deferredChargesTot;
	}

	public String getDiscountedReratingBoo() {
		return this.discountedReratingBoo;
	}

	public void setDiscountedReratingBoo(String discountedReratingBoo) {
		this.discountedReratingBoo = discountedReratingBoo;
	}

	public BigDecimal getDomainId() {
		return this.domainId;
	}

	public void setDomainId(BigDecimal domainId) {
		this.domainId = domainId;
	}

	public String getEbdEmailStatus() {
		return this.ebdEmailStatus;
	}

	public void setEbdEmailStatus(String ebdEmailStatus) {
		this.ebdEmailStatus = ebdEmailStatus;
	}

	public BigDecimal getEventArchiveStatus() {
		return this.eventArchiveStatus;
	}

	public void setEventArchiveStatus(BigDecimal eventArchiveStatus) {
		this.eventArchiveStatus = eventArchiveStatus;
	}

	public Date getEventBilledToDtm() {
		return this.eventBilledToDtm;
	}

	public void setEventBilledToDtm(Date eventBilledToDtm) {
		this.eventBilledToDtm = eventBilledToDtm;
	}

	public String getEventDelNoArchiveBoo() {
		return this.eventDelNoArchiveBoo;
	}

	public void setEventDelNoArchiveBoo(String eventDelNoArchiveBoo) {
		this.eventDelNoArchiveBoo = eventDelNoArchiveBoo;
	}

	public String getEventMaskedBoo() {
		return this.eventMaskedBoo;
	}

	public void setEventMaskedBoo(String eventMaskedBoo) {
		this.eventMaskedBoo = eventMaskedBoo;
	}

	public BigDecimal getEventSeq() {
		return this.eventSeq;
	}

	public void setEventSeq(BigDecimal eventSeq) {
		this.eventSeq = eventSeq;
	}

	public BigDecimal getFailedPaymentsMny() {
		return this.failedPaymentsMny;
	}

	public void setFailedPaymentsMny(BigDecimal failedPaymentsMny) {
		this.failedPaymentsMny = failedPaymentsMny;
	}

	public String getInfoCurrencyCode() {
		return this.infoCurrencyCode;
	}

	public void setInfoCurrencyCode(String infoCurrencyCode) {
		this.infoCurrencyCode = infoCurrencyCode;
	}

	public BigDecimal getInfoInvoiceMny() {
		return this.infoInvoiceMny;
	}

	public void setInfoInvoiceMny(BigDecimal infoInvoiceMny) {
		this.infoInvoiceMny = infoInvoiceMny;
	}

	public BigDecimal getInvoiceNetMny() {
		return this.invoiceNetMny;
	}

	public void setInvoiceNetMny(BigDecimal invoiceNetMny) {
		this.invoiceNetMny = invoiceNetMny;
	}

	public String getInvoiceNum() {
		return this.invoiceNum;
	}

	public void setInvoiceNum(String invoiceNum) {
		this.invoiceNum = invoiceNum;
	}

	public BigDecimal getInvoiceNumStatus() {
		return this.invoiceNumStatus;
	}

	public void setInvoiceNumStatus(BigDecimal invoiceNumStatus) {
		this.invoiceNumStatus = invoiceNumStatus;
	}

	public BigDecimal getInvoiceRegisterId() {
		return this.invoiceRegisterId;
	}

	public void setInvoiceRegisterId(BigDecimal invoiceRegisterId) {
		this.invoiceRegisterId = invoiceRegisterId;
	}

	public BigDecimal getInvoiceTaxMny() {
		return this.invoiceTaxMny;
	}

	public void setInvoiceTaxMny(BigDecimal invoiceTaxMny) {
		this.invoiceTaxMny = invoiceTaxMny;
	}

	public BigDecimal getLateFeeMny() {
		return this.lateFeeMny;
	}

	public void setLateFeeMny(BigDecimal lateFeeMny) {
		this.lateFeeMny = lateFeeMny;
	}

	public Date getLoyaltyTransBilledToDtm() {
		return this.loyaltyTransBilledToDtm;
	}

	public void setLoyaltyTransBilledToDtm(Date loyaltyTransBilledToDtm) {
		this.loyaltyTransBilledToDtm = loyaltyTransBilledToDtm;
	}

	public Date getMaxBilledEventDtm() {
		return this.maxBilledEventDtm;
	}

	public void setMaxBilledEventDtm(Date maxBilledEventDtm) {
		this.maxBilledEventDtm = maxBilledEventDtm;
	}

	public Date getMaxReratedEventDtm() {
		return this.maxReratedEventDtm;
	}

	public void setMaxReratedEventDtm(Date maxReratedEventDtm) {
		this.maxReratedEventDtm = maxReratedEventDtm;
	}

	public Date getMinBilledEventDtm() {
		return this.minBilledEventDtm;
	}

	public void setMinBilledEventDtm(Date minBilledEventDtm) {
		this.minBilledEventDtm = minBilledEventDtm;
	}

	public Date getMinReratedEventDtm() {
		return this.minReratedEventDtm;
	}

	public void setMinReratedEventDtm(Date minReratedEventDtm) {
		this.minReratedEventDtm = minReratedEventDtm;
	}

	public BigDecimal getModificationDays() {
		return this.modificationDays;
	}

	public void setModificationDays(BigDecimal modificationDays) {
		this.modificationDays = modificationDays;
	}

	public String getOcrNum() {
		return this.ocrNum;
	}

	public void setOcrNum(String ocrNum) {
		this.ocrNum = ocrNum;
	}

	public String getOefrCreatedBoo() {
		return this.oefrCreatedBoo;
	}

	public void setOefrCreatedBoo(String oefrCreatedBoo) {
		this.oefrCreatedBoo = oefrCreatedBoo;
	}

	public Date getOtcBilledToDtm() {
		return this.otcBilledToDtm;
	}

	public void setOtcBilledToDtm(Date otcBilledToDtm) {
		this.otcBilledToDtm = otcBilledToDtm;
	}

	public BigDecimal getOtcMny() {
		return this.otcMny;
	}

	public void setOtcMny(BigDecimal otcMny) {
		this.otcMny = otcMny;
	}

	public BigDecimal getOutstandingDebtMny() {
		return this.outstandingDebtMny;
	}

	public void setOutstandingDebtMny(BigDecimal outstandingDebtMny) {
		this.outstandingDebtMny = outstandingDebtMny;
	}

	public Date getOverrideBillDtm() {
		return this.overrideBillDtm;
	}

	public void setOverrideBillDtm(Date overrideBillDtm) {
		this.overrideBillDtm = overrideBillDtm;
	}

	public BigDecimal getOverwrittenDefChargeTot() {
		return this.overwrittenDefChargeTot;
	}

	public void setOverwrittenDefChargeTot(BigDecimal overwrittenDefChargeTot) {
		this.overwrittenDefChargeTot = overwrittenDefChargeTot;
	}

	public BigDecimal getPaDpInvoiceBillMny() {
		return this.paDpInvoiceBillMny;
	}

	public void setPaDpInvoiceBillMny(BigDecimal paDpInvoiceBillMny) {
		this.paDpInvoiceBillMny = paDpInvoiceBillMny;
	}

	public BigDecimal getPaFedInvoiceBillMny() {
		return this.paFedInvoiceBillMny;
	}

	public void setPaFedInvoiceBillMny(BigDecimal paFedInvoiceBillMny) {
		this.paFedInvoiceBillMny = paFedInvoiceBillMny;
	}

	public BigDecimal getPaFedInvoiceDueMny() {
		return this.paFedInvoiceDueMny;
	}

	public void setPaFedInvoiceDueMny(BigDecimal paFedInvoiceDueMny) {
		this.paFedInvoiceDueMny = paFedInvoiceDueMny;
	}

	public BigDecimal getPaPaymentMny() {
		return this.paPaymentMny;
	}

	public void setPaPaymentMny(BigDecimal paPaymentMny) {
		this.paPaymentMny = paPaymentMny;
	}

	public Date getPaymentDueDat() {
		return this.paymentDueDat;
	}

	public void setPaymentDueDat(Date paymentDueDat) {
		this.paymentDueDat = paymentDueDat;
	}

	public BigDecimal getPaymentsMny() {
		return this.paymentsMny;
	}

	public void setPaymentsMny(BigDecimal paymentsMny) {
		this.paymentsMny = paymentsMny;
	}

	public BigDecimal getPointsAdjusted() {
		return this.pointsAdjusted;
	}

	public void setPointsAdjusted(BigDecimal pointsAdjusted) {
		this.pointsAdjusted = pointsAdjusted;
	}

	public BigDecimal getPointsEarned() {
		return this.pointsEarned;
	}

	public void setPointsEarned(BigDecimal pointsEarned) {
		this.pointsEarned = pointsEarned;
	}

	public BigDecimal getPointsFwd() {
		return this.pointsFwd;
	}

	public void setPointsFwd(BigDecimal pointsFwd) {
		this.pointsFwd = pointsFwd;
	}

	public BigDecimal getPointsOut() {
		return this.pointsOut;
	}

	public void setPointsOut(BigDecimal pointsOut) {
		this.pointsOut = pointsOut;
	}

	public BigDecimal getPointsRedeemed() {
		return this.pointsRedeemed;
	}

	public void setPointsRedeemed(BigDecimal pointsRedeemed) {
		this.pointsRedeemed = pointsRedeemed;
	}

	public BigDecimal getRandomHash() {
		return this.randomHash;
	}

	public void setRandomHash(BigDecimal randomHash) {
		this.randomHash = randomHash;
	}

	public BigDecimal getRefundsMny() {
		return this.refundsMny;
	}

	public void setRefundsMny(BigDecimal refundsMny) {
		this.refundsMny = refundsMny;
	}

	public String getReplacesInvoiceNum() {
		return this.replacesInvoiceNum;
	}

	public void setReplacesInvoiceNum(String replacesInvoiceNum) {
		this.replacesInvoiceNum = replacesInvoiceNum;
	}

	public BigDecimal getSettleupChargesTot() {
		return this.settleupChargesTot;
	}

	public void setSettleupChargesTot(BigDecimal settleupChargesTot) {
		this.settleupChargesTot = settleupChargesTot;
	}

	public Date getStartOfBillDtm() {
		return this.startOfBillDtm;
	}

	public void setStartOfBillDtm(Date startOfBillDtm) {
		this.startOfBillDtm = startOfBillDtm;
	}

	public String getStatementBoo() {
		return this.statementBoo;
	}

	public void setStatementBoo(String statementBoo) {
		this.statementBoo = statementBoo;
	}

	public Date getTaxPointDat() {
		return this.taxPointDat;
	}

	public void setTaxPointDat(Date taxPointDat) {
		this.taxPointDat = taxPointDat;
	}

	public Date getTransBilledToDtm() {
		return this.transBilledToDtm;
	}

	public void setTransBilledToDtm(Date transBilledToDtm) {
		this.transBilledToDtm = transBilledToDtm;
	}

	public String getUstCurrencyCode() {
		return this.ustCurrencyCode;
	}

	public void setUstCurrencyCode(String ustCurrencyCode) {
		this.ustCurrencyCode = ustCurrencyCode;
	}

}