//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.18 at 05:00:26 PM EDT 
//


package com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BillAccountPaymentDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BillAccountPaymentDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="currentBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pastDueBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="arPastDueBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="balance30days" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="balance60days" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="balance90days" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eCustomerIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eCustomerDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="balance120days" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adjustedBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalUnAdjustedBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adjustedCurrentBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adjustedPastDueBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IBPEnrollIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currentArAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currentCumulativeBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="writeOffBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postWriteOffBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="writeOffDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unbilledPaymentAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unbilledAdjustAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ibpEnrollDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="invoiceDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="arPostDueDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="outstandingBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unbilledOccAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentsSince" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pastDueDays" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="achAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentApplReqDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creditBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creditBalanceToAP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="securityDepositToAP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billTotalRemitAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentDueDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalArAmt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="d30ArAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="d60ArAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="d90ArAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="d120ArAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="securityDepositAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="d30CumulativeAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="d60CumulativeAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="d90CumulativeAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="d120CumulativeAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="recentActivityAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BillAccountPaymentDetail", propOrder = {
    "currentBalance",
    "pastDueBalance",
    "arPastDueBalance",
    "balance30Days",
    "balance60Days",
    "balance90Days",
    "eCustomerIndicator",
    "eCustomerDate",
    "balance120Days",
    "adjustedBalance",
    "totalUnAdjustedBalance",
    "adjustedCurrentBalance",
    "adjustedPastDueBalance",
    "ibpEnrollIndicator",
    "currentArAmount",
    "currentCumulativeBalance",
    "writeOffBalance",
    "postWriteOffBalance",
    "writeOffDate",
    "unbilledPaymentAmount",
    "unbilledAdjustAmount",
    "ibpEnrollDescription",
    "invoiceDate",
    "arPostDueDate",
    "outstandingBalance",
    "unbilledOccAmount",
    "paymentsSince",
    "pastDueDays",
    "achAmount",
    "paymentApplReqDate",
    "creditBalance",
    "creditBalanceToAP",
    "securityDepositToAP",
    "billTotalRemitAmount",
    "paymentDueDate",
    "totalArAmt",
    "d30ArAmount",
    "d60ArAmount",
    "d90ArAmount",
    "d120ArAmount",
    "securityDepositAmount",
    "d30CumulativeAmount",
    "d60CumulativeAmount",
    "d90CumulativeAmount",
    "d120CumulativeAmount",
    "recentActivityAmount"
})
public class BillAccountPaymentDetail {

    protected String currentBalance;
    protected String pastDueBalance;
    protected String arPastDueBalance;
    @XmlElement(name = "balance30days")
    protected String balance30Days;
    @XmlElement(name = "balance60days")
    protected String balance60Days;
    @XmlElement(name = "balance90days")
    protected String balance90Days;
    protected String eCustomerIndicator;
    protected String eCustomerDate;
    @XmlElement(name = "balance120days")
    protected String balance120Days;
    protected String adjustedBalance;
    protected String totalUnAdjustedBalance;
    protected String adjustedCurrentBalance;
    protected String adjustedPastDueBalance;
    @XmlElement(name = "IBPEnrollIndicator")
    protected String ibpEnrollIndicator;
    protected String currentArAmount;
    protected String currentCumulativeBalance;
    protected String writeOffBalance;
    protected String postWriteOffBalance;
    protected String writeOffDate;
    protected String unbilledPaymentAmount;
    protected String unbilledAdjustAmount;
    protected String ibpEnrollDescription;
    protected String invoiceDate;
    protected String arPostDueDate;
    protected String outstandingBalance;
    protected String unbilledOccAmount;
    protected String paymentsSince;
    protected String pastDueDays;
    protected String achAmount;
    protected String paymentApplReqDate;
    protected String creditBalance;
    protected String creditBalanceToAP;
    protected String securityDepositToAP;
    protected String billTotalRemitAmount;
    protected String paymentDueDate;
    protected String totalArAmt;
    protected String d30ArAmount;
    protected String d60ArAmount;
    protected String d90ArAmount;
    protected String d120ArAmount;
    protected String securityDepositAmount;
    protected String d30CumulativeAmount;
    protected String d60CumulativeAmount;
    protected String d90CumulativeAmount;
    protected String d120CumulativeAmount;
    protected String recentActivityAmount;

    /**
     * Gets the value of the currentBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentBalance() {
        return currentBalance;
    }

    /**
     * Sets the value of the currentBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentBalance(String value) {
        this.currentBalance = value;
    }

    /**
     * Gets the value of the pastDueBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPastDueBalance() {
        return pastDueBalance;
    }

    /**
     * Sets the value of the pastDueBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPastDueBalance(String value) {
        this.pastDueBalance = value;
    }

    /**
     * Gets the value of the arPastDueBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArPastDueBalance() {
        return arPastDueBalance;
    }

    /**
     * Sets the value of the arPastDueBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArPastDueBalance(String value) {
        this.arPastDueBalance = value;
    }

    /**
     * Gets the value of the balance30Days property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBalance30Days() {
        return balance30Days;
    }

    /**
     * Sets the value of the balance30Days property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBalance30Days(String value) {
        this.balance30Days = value;
    }

    /**
     * Gets the value of the balance60Days property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBalance60Days() {
        return balance60Days;
    }

    /**
     * Sets the value of the balance60Days property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBalance60Days(String value) {
        this.balance60Days = value;
    }

    /**
     * Gets the value of the balance90Days property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBalance90Days() {
        return balance90Days;
    }

    /**
     * Sets the value of the balance90Days property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBalance90Days(String value) {
        this.balance90Days = value;
    }

    /**
     * Gets the value of the eCustomerIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getECustomerIndicator() {
        return eCustomerIndicator;
    }

    /**
     * Sets the value of the eCustomerIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setECustomerIndicator(String value) {
        this.eCustomerIndicator = value;
    }

    /**
     * Gets the value of the eCustomerDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getECustomerDate() {
        return eCustomerDate;
    }

    /**
     * Sets the value of the eCustomerDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setECustomerDate(String value) {
        this.eCustomerDate = value;
    }

    /**
     * Gets the value of the balance120Days property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBalance120Days() {
        return balance120Days;
    }

    /**
     * Sets the value of the balance120Days property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBalance120Days(String value) {
        this.balance120Days = value;
    }

    /**
     * Gets the value of the adjustedBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdjustedBalance() {
        return adjustedBalance;
    }

    /**
     * Sets the value of the adjustedBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdjustedBalance(String value) {
        this.adjustedBalance = value;
    }

    /**
     * Gets the value of the totalUnAdjustedBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalUnAdjustedBalance() {
        return totalUnAdjustedBalance;
    }

    /**
     * Sets the value of the totalUnAdjustedBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalUnAdjustedBalance(String value) {
        this.totalUnAdjustedBalance = value;
    }

    /**
     * Gets the value of the adjustedCurrentBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdjustedCurrentBalance() {
        return adjustedCurrentBalance;
    }

    /**
     * Sets the value of the adjustedCurrentBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdjustedCurrentBalance(String value) {
        this.adjustedCurrentBalance = value;
    }

    /**
     * Gets the value of the adjustedPastDueBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdjustedPastDueBalance() {
        return adjustedPastDueBalance;
    }

    /**
     * Sets the value of the adjustedPastDueBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdjustedPastDueBalance(String value) {
        this.adjustedPastDueBalance = value;
    }

    /**
     * Gets the value of the ibpEnrollIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIBPEnrollIndicator() {
        return ibpEnrollIndicator;
    }

    /**
     * Sets the value of the ibpEnrollIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIBPEnrollIndicator(String value) {
        this.ibpEnrollIndicator = value;
    }

    /**
     * Gets the value of the currentArAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentArAmount() {
        return currentArAmount;
    }

    /**
     * Sets the value of the currentArAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentArAmount(String value) {
        this.currentArAmount = value;
    }

    /**
     * Gets the value of the currentCumulativeBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentCumulativeBalance() {
        return currentCumulativeBalance;
    }

    /**
     * Sets the value of the currentCumulativeBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentCumulativeBalance(String value) {
        this.currentCumulativeBalance = value;
    }

    /**
     * Gets the value of the writeOffBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWriteOffBalance() {
        return writeOffBalance;
    }

    /**
     * Sets the value of the writeOffBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWriteOffBalance(String value) {
        this.writeOffBalance = value;
    }

    /**
     * Gets the value of the postWriteOffBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostWriteOffBalance() {
        return postWriteOffBalance;
    }

    /**
     * Sets the value of the postWriteOffBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostWriteOffBalance(String value) {
        this.postWriteOffBalance = value;
    }

    /**
     * Gets the value of the writeOffDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWriteOffDate() {
        return writeOffDate;
    }

    /**
     * Sets the value of the writeOffDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWriteOffDate(String value) {
        this.writeOffDate = value;
    }

    /**
     * Gets the value of the unbilledPaymentAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnbilledPaymentAmount() {
        return unbilledPaymentAmount;
    }

    /**
     * Sets the value of the unbilledPaymentAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnbilledPaymentAmount(String value) {
        this.unbilledPaymentAmount = value;
    }

    /**
     * Gets the value of the unbilledAdjustAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnbilledAdjustAmount() {
        return unbilledAdjustAmount;
    }

    /**
     * Sets the value of the unbilledAdjustAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnbilledAdjustAmount(String value) {
        this.unbilledAdjustAmount = value;
    }

    /**
     * Gets the value of the ibpEnrollDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIbpEnrollDescription() {
        return ibpEnrollDescription;
    }

    /**
     * Sets the value of the ibpEnrollDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIbpEnrollDescription(String value) {
        this.ibpEnrollDescription = value;
    }

    /**
     * Gets the value of the invoiceDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvoiceDate() {
        return invoiceDate;
    }

    /**
     * Sets the value of the invoiceDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvoiceDate(String value) {
        this.invoiceDate = value;
    }

    /**
     * Gets the value of the arPostDueDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArPostDueDate() {
        return arPostDueDate;
    }

    /**
     * Sets the value of the arPostDueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArPostDueDate(String value) {
        this.arPostDueDate = value;
    }

    /**
     * Gets the value of the outstandingBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutstandingBalance() {
        return outstandingBalance;
    }

    /**
     * Sets the value of the outstandingBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutstandingBalance(String value) {
        this.outstandingBalance = value;
    }

    /**
     * Gets the value of the unbilledOccAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnbilledOccAmount() {
        return unbilledOccAmount;
    }

    /**
     * Sets the value of the unbilledOccAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnbilledOccAmount(String value) {
        this.unbilledOccAmount = value;
    }

    /**
     * Gets the value of the paymentsSince property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentsSince() {
        return paymentsSince;
    }

    /**
     * Sets the value of the paymentsSince property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentsSince(String value) {
        this.paymentsSince = value;
    }

    /**
     * Gets the value of the pastDueDays property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPastDueDays() {
        return pastDueDays;
    }

    /**
     * Sets the value of the pastDueDays property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPastDueDays(String value) {
        this.pastDueDays = value;
    }

    /**
     * Gets the value of the achAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAchAmount() {
        return achAmount;
    }

    /**
     * Sets the value of the achAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAchAmount(String value) {
        this.achAmount = value;
    }

    /**
     * Gets the value of the paymentApplReqDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentApplReqDate() {
        return paymentApplReqDate;
    }

    /**
     * Sets the value of the paymentApplReqDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentApplReqDate(String value) {
        this.paymentApplReqDate = value;
    }

    /**
     * Gets the value of the creditBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditBalance() {
        return creditBalance;
    }

    /**
     * Sets the value of the creditBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditBalance(String value) {
        this.creditBalance = value;
    }

    /**
     * Gets the value of the creditBalanceToAP property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditBalanceToAP() {
        return creditBalanceToAP;
    }

    /**
     * Sets the value of the creditBalanceToAP property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditBalanceToAP(String value) {
        this.creditBalanceToAP = value;
    }

    /**
     * Gets the value of the securityDepositToAP property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecurityDepositToAP() {
        return securityDepositToAP;
    }

    /**
     * Sets the value of the securityDepositToAP property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecurityDepositToAP(String value) {
        this.securityDepositToAP = value;
    }

    /**
     * Gets the value of the billTotalRemitAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillTotalRemitAmount() {
        return billTotalRemitAmount;
    }

    /**
     * Sets the value of the billTotalRemitAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillTotalRemitAmount(String value) {
        this.billTotalRemitAmount = value;
    }

    /**
     * Gets the value of the paymentDueDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentDueDate() {
        return paymentDueDate;
    }

    /**
     * Sets the value of the paymentDueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentDueDate(String value) {
        this.paymentDueDate = value;
    }

    /**
     * Gets the value of the totalArAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalArAmt() {
        return totalArAmt;
    }

    /**
     * Sets the value of the totalArAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalArAmt(String value) {
        this.totalArAmt = value;
    }

    /**
     * Gets the value of the d30ArAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getD30ArAmount() {
        return d30ArAmount;
    }

    /**
     * Sets the value of the d30ArAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setD30ArAmount(String value) {
        this.d30ArAmount = value;
    }

    /**
     * Gets the value of the d60ArAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getD60ArAmount() {
        return d60ArAmount;
    }

    /**
     * Sets the value of the d60ArAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setD60ArAmount(String value) {
        this.d60ArAmount = value;
    }

    /**
     * Gets the value of the d90ArAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getD90ArAmount() {
        return d90ArAmount;
    }

    /**
     * Sets the value of the d90ArAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setD90ArAmount(String value) {
        this.d90ArAmount = value;
    }

    /**
     * Gets the value of the d120ArAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getD120ArAmount() {
        return d120ArAmount;
    }

    /**
     * Sets the value of the d120ArAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setD120ArAmount(String value) {
        this.d120ArAmount = value;
    }

    /**
     * Gets the value of the securityDepositAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecurityDepositAmount() {
        return securityDepositAmount;
    }

    /**
     * Sets the value of the securityDepositAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecurityDepositAmount(String value) {
        this.securityDepositAmount = value;
    }

    /**
     * Gets the value of the d30CumulativeAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getD30CumulativeAmount() {
        return d30CumulativeAmount;
    }

    /**
     * Sets the value of the d30CumulativeAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setD30CumulativeAmount(String value) {
        this.d30CumulativeAmount = value;
    }

    /**
     * Gets the value of the d60CumulativeAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getD60CumulativeAmount() {
        return d60CumulativeAmount;
    }

    /**
     * Sets the value of the d60CumulativeAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setD60CumulativeAmount(String value) {
        this.d60CumulativeAmount = value;
    }

    /**
     * Gets the value of the d90CumulativeAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getD90CumulativeAmount() {
        return d90CumulativeAmount;
    }

    /**
     * Sets the value of the d90CumulativeAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setD90CumulativeAmount(String value) {
        this.d90CumulativeAmount = value;
    }

    /**
     * Gets the value of the d120CumulativeAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getD120CumulativeAmount() {
        return d120CumulativeAmount;
    }

    /**
     * Sets the value of the d120CumulativeAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setD120CumulativeAmount(String value) {
        this.d120CumulativeAmount = value;
    }

    /**
     * Gets the value of the recentActivityAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecentActivityAmount() {
        return recentActivityAmount;
    }

    /**
     * Sets the value of the recentActivityAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecentActivityAmount(String value) {
        this.recentActivityAmount = value;
    }

}