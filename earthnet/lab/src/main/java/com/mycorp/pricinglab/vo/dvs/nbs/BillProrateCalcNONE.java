//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.09.13 at 01:37:28 PM EDT 
//


package com.vzwcorp.pricinglab.vo.dvs.nbs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}serviceError" minOccurs="0"/>
 *         &lt;element name="billCycleStartDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billCycleEndDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nextBillCycleStartDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nextBillCycleEndDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partialBillIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="languageIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="splanNextBillAccessTotal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="splanNewMonthlyAccessTotal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="splanPreviousMonthlyAccessTotal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountOccNextBillTotal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountEquipmentChargeNextBillTotal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountEquipmentChargeNewMonthlyTotal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountEquipmentChargePreviousMonthlyTotal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nextMonthGrandTotal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newMonthGrandTotal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prevMonthGrandTotal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nextTaxGrandTotal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newTaxGrandTotal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mobileFirstTotalNext" type="{}MobileFirstTotalDetails" minOccurs="0"/>
 *         &lt;element name="mobileFirstTotalNew" type="{}MobileFirstTotalDetails" minOccurs="0"/>
 *         &lt;element name="datedTransType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="splanInfoList" type="{}PlanInfoList" minOccurs="0"/>
 *         &lt;element name="prorateCalcList" type="{}ProrateCalcList" minOccurs="0"/>
 *         &lt;element ref="{}accountOccInfo" minOccurs="0"/>
 *         &lt;element name="serviceProductOfferInfoList" type="{}ServiceProductOfferInfoList" minOccurs="0"/>
 *         &lt;element name="accountEquipmentChargeInfoList" type="{}EquipmentChargeInfoList" minOccurs="0"/>
 *         &lt;element name="oneMessageTransactionType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{}emailAddress1" minOccurs="0"/>
 *         &lt;element ref="{}emailAddress2" minOccurs="0"/>
 *         &lt;element ref="{}orderNumber" minOccurs="0"/>
 *         &lt;element ref="{}lorNumber" minOccurs="0"/>
 *         &lt;element ref="{}posOrderId" minOccurs="0"/>
 *         &lt;element name="suppressedTransactionTypeList" type="{}SuppressedTransactionTypeList" minOccurs="0"/>
 *         &lt;element name="sharePlanAndSPOTaxInfo" type="{}taxInfo" minOccurs="0"/>
 *         &lt;element name="pPlanSFOLineListTaxInfo" type="{}taxInfo" minOccurs="0"/>
 *         &lt;element name="totalTaxInfo" type="{}taxInfo" minOccurs="0"/>
 *         &lt;element name="nextBillTimeStamp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newMonthlyBillDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountDataOverageInfoOut" type="{}DataOverageInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "serviceError",
    "billCycleStartDate",
    "billCycleEndDate",
    "nextBillCycleStartDate",
    "nextBillCycleEndDate",
    "partialBillIndicator",
    "languageIndicator",
    "splanNextBillAccessTotal",
    "splanNewMonthlyAccessTotal",
    "splanPreviousMonthlyAccessTotal",
    "accountOccNextBillTotal",
    "accountEquipmentChargeNextBillTotal",
    "accountEquipmentChargeNewMonthlyTotal",
    "accountEquipmentChargePreviousMonthlyTotal",
    "nextMonthGrandTotal",
    "newMonthGrandTotal",
    "prevMonthGrandTotal",
    "nextTaxGrandTotal",
    "newTaxGrandTotal",
    "mobileFirstTotalNext",
    "mobileFirstTotalNew",
    "datedTransType",
    "splanInfoList",
    "prorateCalcList",
    "accountOccInfo",
    "serviceProductOfferInfoList",
    "accountEquipmentChargeInfoList",
    "oneMessageTransactionType",
    "emailAddress1",
    "emailAddress2",
    "orderNumber",
    "lorNumber",
    "posOrderId",
    "suppressedTransactionTypeList",
    "sharePlanAndSPOTaxInfo",
    "pPlanSFOLineListTaxInfo",
    "totalTaxInfo",
    "nextBillTimeStamp",
    "newMonthlyBillDate",
    "accountDataOverageInfoOut"
})
@XmlRootElement(name = "billProrateCalc_NONE")
public class BillProrateCalcNONE {

    protected ServiceError serviceError;
    protected String billCycleStartDate;
    protected String billCycleEndDate;
    protected String nextBillCycleStartDate;
    protected String nextBillCycleEndDate;
    protected String partialBillIndicator;
    protected String languageIndicator;
    protected String splanNextBillAccessTotal;
    protected String splanNewMonthlyAccessTotal;
    protected String splanPreviousMonthlyAccessTotal;
    protected String accountOccNextBillTotal;
    protected String accountEquipmentChargeNextBillTotal;
    protected String accountEquipmentChargeNewMonthlyTotal;
    protected String accountEquipmentChargePreviousMonthlyTotal;
    protected String nextMonthGrandTotal;
    protected String newMonthGrandTotal;
    protected String prevMonthGrandTotal;
    protected String nextTaxGrandTotal;
    protected String newTaxGrandTotal;
    protected MobileFirstTotalDetails mobileFirstTotalNext;
    protected MobileFirstTotalDetails mobileFirstTotalNew;
    protected String datedTransType;
    protected PlanInfoList splanInfoList;
    protected ProrateCalcList prorateCalcList;
    protected OCCInfo accountOccInfo;
    protected ServiceProductOfferInfoList serviceProductOfferInfoList;
    protected EquipmentChargeInfoList accountEquipmentChargeInfoList;
    protected String oneMessageTransactionType;
    protected String emailAddress1;
    protected String emailAddress2;
    protected String orderNumber;
    protected String lorNumber;
    protected String posOrderId;
    protected SuppressedTransactionTypeList suppressedTransactionTypeList;
    protected TaxInfo sharePlanAndSPOTaxInfo;
    protected TaxInfo pPlanSFOLineListTaxInfo;
    protected TaxInfo totalTaxInfo;
    protected String nextBillTimeStamp;
    protected String newMonthlyBillDate;
    protected DataOverageInfo accountDataOverageInfoOut;

    /**
     * Gets the value of the serviceError property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceError }
     *     
     */
    public ServiceError getServiceError() {
        return serviceError;
    }

    /**
     * Sets the value of the serviceError property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceError }
     *     
     */
    public void setServiceError(ServiceError value) {
        this.serviceError = value;
    }

    /**
     * Gets the value of the billCycleStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillCycleStartDate() {
        return billCycleStartDate;
    }

    /**
     * Sets the value of the billCycleStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillCycleStartDate(String value) {
        this.billCycleStartDate = value;
    }

    /**
     * Gets the value of the billCycleEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillCycleEndDate() {
        return billCycleEndDate;
    }

    /**
     * Sets the value of the billCycleEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillCycleEndDate(String value) {
        this.billCycleEndDate = value;
    }

    /**
     * Gets the value of the nextBillCycleStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextBillCycleStartDate() {
        return nextBillCycleStartDate;
    }

    /**
     * Sets the value of the nextBillCycleStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextBillCycleStartDate(String value) {
        this.nextBillCycleStartDate = value;
    }

    /**
     * Gets the value of the nextBillCycleEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextBillCycleEndDate() {
        return nextBillCycleEndDate;
    }

    /**
     * Sets the value of the nextBillCycleEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextBillCycleEndDate(String value) {
        this.nextBillCycleEndDate = value;
    }

    /**
     * Gets the value of the partialBillIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartialBillIndicator() {
        return partialBillIndicator;
    }

    /**
     * Sets the value of the partialBillIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartialBillIndicator(String value) {
        this.partialBillIndicator = value;
    }

    /**
     * Gets the value of the languageIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguageIndicator() {
        return languageIndicator;
    }

    /**
     * Sets the value of the languageIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguageIndicator(String value) {
        this.languageIndicator = value;
    }

    /**
     * Gets the value of the splanNextBillAccessTotal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSplanNextBillAccessTotal() {
        return splanNextBillAccessTotal;
    }

    /**
     * Sets the value of the splanNextBillAccessTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSplanNextBillAccessTotal(String value) {
        this.splanNextBillAccessTotal = value;
    }

    /**
     * Gets the value of the splanNewMonthlyAccessTotal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSplanNewMonthlyAccessTotal() {
        return splanNewMonthlyAccessTotal;
    }

    /**
     * Sets the value of the splanNewMonthlyAccessTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSplanNewMonthlyAccessTotal(String value) {
        this.splanNewMonthlyAccessTotal = value;
    }

    /**
     * Gets the value of the splanPreviousMonthlyAccessTotal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSplanPreviousMonthlyAccessTotal() {
        return splanPreviousMonthlyAccessTotal;
    }

    /**
     * Sets the value of the splanPreviousMonthlyAccessTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSplanPreviousMonthlyAccessTotal(String value) {
        this.splanPreviousMonthlyAccessTotal = value;
    }

    /**
     * Gets the value of the accountOccNextBillTotal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountOccNextBillTotal() {
        return accountOccNextBillTotal;
    }

    /**
     * Sets the value of the accountOccNextBillTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountOccNextBillTotal(String value) {
        this.accountOccNextBillTotal = value;
    }

    /**
     * Gets the value of the accountEquipmentChargeNextBillTotal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountEquipmentChargeNextBillTotal() {
        return accountEquipmentChargeNextBillTotal;
    }

    /**
     * Sets the value of the accountEquipmentChargeNextBillTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountEquipmentChargeNextBillTotal(String value) {
        this.accountEquipmentChargeNextBillTotal = value;
    }

    /**
     * Gets the value of the accountEquipmentChargeNewMonthlyTotal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountEquipmentChargeNewMonthlyTotal() {
        return accountEquipmentChargeNewMonthlyTotal;
    }

    /**
     * Sets the value of the accountEquipmentChargeNewMonthlyTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountEquipmentChargeNewMonthlyTotal(String value) {
        this.accountEquipmentChargeNewMonthlyTotal = value;
    }

    /**
     * Gets the value of the accountEquipmentChargePreviousMonthlyTotal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountEquipmentChargePreviousMonthlyTotal() {
        return accountEquipmentChargePreviousMonthlyTotal;
    }

    /**
     * Sets the value of the accountEquipmentChargePreviousMonthlyTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountEquipmentChargePreviousMonthlyTotal(String value) {
        this.accountEquipmentChargePreviousMonthlyTotal = value;
    }

    /**
     * Gets the value of the nextMonthGrandTotal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextMonthGrandTotal() {
        return nextMonthGrandTotal;
    }

    /**
     * Sets the value of the nextMonthGrandTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextMonthGrandTotal(String value) {
        this.nextMonthGrandTotal = value;
    }

    /**
     * Gets the value of the newMonthGrandTotal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewMonthGrandTotal() {
        return newMonthGrandTotal;
    }

    /**
     * Sets the value of the newMonthGrandTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewMonthGrandTotal(String value) {
        this.newMonthGrandTotal = value;
    }

    /**
     * Gets the value of the prevMonthGrandTotal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrevMonthGrandTotal() {
        return prevMonthGrandTotal;
    }

    /**
     * Sets the value of the prevMonthGrandTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrevMonthGrandTotal(String value) {
        this.prevMonthGrandTotal = value;
    }

    /**
     * Gets the value of the nextTaxGrandTotal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextTaxGrandTotal() {
        return nextTaxGrandTotal;
    }

    /**
     * Sets the value of the nextTaxGrandTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextTaxGrandTotal(String value) {
        this.nextTaxGrandTotal = value;
    }

    /**
     * Gets the value of the newTaxGrandTotal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewTaxGrandTotal() {
        return newTaxGrandTotal;
    }

    /**
     * Sets the value of the newTaxGrandTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewTaxGrandTotal(String value) {
        this.newTaxGrandTotal = value;
    }

    /**
     * Gets the value of the mobileFirstTotalNext property.
     * 
     * @return
     *     possible object is
     *     {@link MobileFirstTotalDetails }
     *     
     */
    public MobileFirstTotalDetails getMobileFirstTotalNext() {
        return mobileFirstTotalNext;
    }

    /**
     * Sets the value of the mobileFirstTotalNext property.
     * 
     * @param value
     *     allowed object is
     *     {@link MobileFirstTotalDetails }
     *     
     */
    public void setMobileFirstTotalNext(MobileFirstTotalDetails value) {
        this.mobileFirstTotalNext = value;
    }

    /**
     * Gets the value of the mobileFirstTotalNew property.
     * 
     * @return
     *     possible object is
     *     {@link MobileFirstTotalDetails }
     *     
     */
    public MobileFirstTotalDetails getMobileFirstTotalNew() {
        return mobileFirstTotalNew;
    }

    /**
     * Sets the value of the mobileFirstTotalNew property.
     * 
     * @param value
     *     allowed object is
     *     {@link MobileFirstTotalDetails }
     *     
     */
    public void setMobileFirstTotalNew(MobileFirstTotalDetails value) {
        this.mobileFirstTotalNew = value;
    }

    /**
     * Gets the value of the datedTransType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatedTransType() {
        return datedTransType;
    }

    /**
     * Sets the value of the datedTransType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatedTransType(String value) {
        this.datedTransType = value;
    }

    /**
     * Gets the value of the splanInfoList property.
     * 
     * @return
     *     possible object is
     *     {@link PlanInfoList }
     *     
     */
    public PlanInfoList getSplanInfoList() {
        return splanInfoList;
    }

    /**
     * Sets the value of the splanInfoList property.
     * 
     * @param value
     *     allowed object is
     *     {@link PlanInfoList }
     *     
     */
    public void setSplanInfoList(PlanInfoList value) {
        this.splanInfoList = value;
    }

    /**
     * Gets the value of the prorateCalcList property.
     * 
     * @return
     *     possible object is
     *     {@link ProrateCalcList }
     *     
     */
    public ProrateCalcList getProrateCalcList() {
        return prorateCalcList;
    }

    /**
     * Sets the value of the prorateCalcList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProrateCalcList }
     *     
     */
    public void setProrateCalcList(ProrateCalcList value) {
        this.prorateCalcList = value;
    }

    /**
     * Gets the value of the accountOccInfo property.
     * 
     * @return
     *     possible object is
     *     {@link OCCInfo }
     *     
     */
    public OCCInfo getAccountOccInfo() {
        return accountOccInfo;
    }

    /**
     * Sets the value of the accountOccInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link OCCInfo }
     *     
     */
    public void setAccountOccInfo(OCCInfo value) {
        this.accountOccInfo = value;
    }

    /**
     * Gets the value of the serviceProductOfferInfoList property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceProductOfferInfoList }
     *     
     */
    public ServiceProductOfferInfoList getServiceProductOfferInfoList() {
        return serviceProductOfferInfoList;
    }

    /**
     * Sets the value of the serviceProductOfferInfoList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceProductOfferInfoList }
     *     
     */
    public void setServiceProductOfferInfoList(ServiceProductOfferInfoList value) {
        this.serviceProductOfferInfoList = value;
    }

    /**
     * Gets the value of the accountEquipmentChargeInfoList property.
     * 
     * @return
     *     possible object is
     *     {@link EquipmentChargeInfoList }
     *     
     */
    public EquipmentChargeInfoList getAccountEquipmentChargeInfoList() {
        return accountEquipmentChargeInfoList;
    }

    /**
     * Sets the value of the accountEquipmentChargeInfoList property.
     * 
     * @param value
     *     allowed object is
     *     {@link EquipmentChargeInfoList }
     *     
     */
    public void setAccountEquipmentChargeInfoList(EquipmentChargeInfoList value) {
        this.accountEquipmentChargeInfoList = value;
    }

    /**
     * Gets the value of the oneMessageTransactionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOneMessageTransactionType() {
        return oneMessageTransactionType;
    }

    /**
     * Sets the value of the oneMessageTransactionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOneMessageTransactionType(String value) {
        this.oneMessageTransactionType = value;
    }

    /**
     * Gets the value of the emailAddress1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailAddress1() {
        return emailAddress1;
    }

    /**
     * Sets the value of the emailAddress1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailAddress1(String value) {
        this.emailAddress1 = value;
    }

    /**
     * Gets the value of the emailAddress2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailAddress2() {
        return emailAddress2;
    }

    /**
     * Sets the value of the emailAddress2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailAddress2(String value) {
        this.emailAddress2 = value;
    }

    /**
     * Gets the value of the orderNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * Sets the value of the orderNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderNumber(String value) {
        this.orderNumber = value;
    }

    /**
     * Gets the value of the lorNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLorNumber() {
        return lorNumber;
    }

    /**
     * Sets the value of the lorNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLorNumber(String value) {
        this.lorNumber = value;
    }

    /**
     * Gets the value of the posOrderId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPosOrderId() {
        return posOrderId;
    }

    /**
     * Sets the value of the posOrderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPosOrderId(String value) {
        this.posOrderId = value;
    }

    /**
     * Gets the value of the suppressedTransactionTypeList property.
     * 
     * @return
     *     possible object is
     *     {@link SuppressedTransactionTypeList }
     *     
     */
    public SuppressedTransactionTypeList getSuppressedTransactionTypeList() {
        return suppressedTransactionTypeList;
    }

    /**
     * Sets the value of the suppressedTransactionTypeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SuppressedTransactionTypeList }
     *     
     */
    public void setSuppressedTransactionTypeList(SuppressedTransactionTypeList value) {
        this.suppressedTransactionTypeList = value;
    }

    /**
     * Gets the value of the sharePlanAndSPOTaxInfo property.
     * 
     * @return
     *     possible object is
     *     {@link TaxInfo }
     *     
     */
    public TaxInfo getSharePlanAndSPOTaxInfo() {
        return sharePlanAndSPOTaxInfo;
    }

    /**
     * Sets the value of the sharePlanAndSPOTaxInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxInfo }
     *     
     */
    public void setSharePlanAndSPOTaxInfo(TaxInfo value) {
        this.sharePlanAndSPOTaxInfo = value;
    }

    /**
     * Gets the value of the pPlanSFOLineListTaxInfo property.
     * 
     * @return
     *     possible object is
     *     {@link TaxInfo }
     *     
     */
    public TaxInfo getPPlanSFOLineListTaxInfo() {
        return pPlanSFOLineListTaxInfo;
    }

    /**
     * Sets the value of the pPlanSFOLineListTaxInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxInfo }
     *     
     */
    public void setPPlanSFOLineListTaxInfo(TaxInfo value) {
        this.pPlanSFOLineListTaxInfo = value;
    }

    /**
     * Gets the value of the totalTaxInfo property.
     * 
     * @return
     *     possible object is
     *     {@link TaxInfo }
     *     
     */
    public TaxInfo getTotalTaxInfo() {
        return totalTaxInfo;
    }

    /**
     * Sets the value of the totalTaxInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxInfo }
     *     
     */
    public void setTotalTaxInfo(TaxInfo value) {
        this.totalTaxInfo = value;
    }

    /**
     * Gets the value of the nextBillTimeStamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextBillTimeStamp() {
        return nextBillTimeStamp;
    }

    /**
     * Sets the value of the nextBillTimeStamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextBillTimeStamp(String value) {
        this.nextBillTimeStamp = value;
    }

    /**
     * Gets the value of the newMonthlyBillDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewMonthlyBillDate() {
        return newMonthlyBillDate;
    }

    /**
     * Sets the value of the newMonthlyBillDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewMonthlyBillDate(String value) {
        this.newMonthlyBillDate = value;
    }

    /**
     * Gets the value of the accountDataOverageInfoOut property.
     * 
     * @return
     *     possible object is
     *     {@link DataOverageInfo }
     *     
     */
    public DataOverageInfo getAccountDataOverageInfoOut() {
        return accountDataOverageInfoOut;
    }

    /**
     * Sets the value of the accountDataOverageInfoOut property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataOverageInfo }
     *     
     */
    public void setAccountDataOverageInfoOut(DataOverageInfo value) {
        this.accountDataOverageInfoOut = value;
    }

}