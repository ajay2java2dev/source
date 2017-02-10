//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.18 at 05:00:26 PM EDT 
//


package com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CustLoanDataInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustLoanDataInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="installmentTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerLoanCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountLoanCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ssnLoanCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="installmentLoanNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="installmentLoanStatusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="installmentLoanInd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loanSalesDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loanAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalPrincipalUnpaid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="edgeUpOriginalCost" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pastTradeInRestrictionTimePeriod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numberOfInstallmentsBilled" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numberOfInstallmentsToBill" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="edgeUpRequiredPercentage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loanTermMonthsQty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustLoanDataInfo", propOrder = {
    "installmentTypeCode",
    "customerLoanCount",
    "accountLoanCount",
    "ssnLoanCount",
    "installmentLoanNumber",
    "installmentLoanStatusCode",
    "installmentLoanInd",
    "loanSalesDate",
    "loanAmount",
    "totalPrincipalUnpaid",
    "edgeUpOriginalCost",
    "pastTradeInRestrictionTimePeriod",
    "numberOfInstallmentsBilled",
    "numberOfInstallmentsToBill",
    "edgeUpRequiredPercentage",
    "loanTermMonthsQty"
})
public class CustLoanDataInfo {

    protected String installmentTypeCode;
    protected String customerLoanCount;
    protected String accountLoanCount;
    protected String ssnLoanCount;
    protected String installmentLoanNumber;
    protected String installmentLoanStatusCode;
    protected String installmentLoanInd;
    protected String loanSalesDate;
    protected String loanAmount;
    protected String totalPrincipalUnpaid;
    protected String edgeUpOriginalCost;
    protected String pastTradeInRestrictionTimePeriod;
    protected String numberOfInstallmentsBilled;
    protected String numberOfInstallmentsToBill;
    protected String edgeUpRequiredPercentage;
    protected String loanTermMonthsQty;

    /**
     * Gets the value of the installmentTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstallmentTypeCode() {
        return installmentTypeCode;
    }

    /**
     * Sets the value of the installmentTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstallmentTypeCode(String value) {
        this.installmentTypeCode = value;
    }

    /**
     * Gets the value of the customerLoanCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerLoanCount() {
        return customerLoanCount;
    }

    /**
     * Sets the value of the customerLoanCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerLoanCount(String value) {
        this.customerLoanCount = value;
    }

    /**
     * Gets the value of the accountLoanCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountLoanCount() {
        return accountLoanCount;
    }

    /**
     * Sets the value of the accountLoanCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountLoanCount(String value) {
        this.accountLoanCount = value;
    }

    /**
     * Gets the value of the ssnLoanCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSsnLoanCount() {
        return ssnLoanCount;
    }

    /**
     * Sets the value of the ssnLoanCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSsnLoanCount(String value) {
        this.ssnLoanCount = value;
    }

    /**
     * Gets the value of the installmentLoanNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstallmentLoanNumber() {
        return installmentLoanNumber;
    }

    /**
     * Sets the value of the installmentLoanNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstallmentLoanNumber(String value) {
        this.installmentLoanNumber = value;
    }

    /**
     * Gets the value of the installmentLoanStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstallmentLoanStatusCode() {
        return installmentLoanStatusCode;
    }

    /**
     * Sets the value of the installmentLoanStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstallmentLoanStatusCode(String value) {
        this.installmentLoanStatusCode = value;
    }

    /**
     * Gets the value of the installmentLoanInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstallmentLoanInd() {
        return installmentLoanInd;
    }

    /**
     * Sets the value of the installmentLoanInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstallmentLoanInd(String value) {
        this.installmentLoanInd = value;
    }

    /**
     * Gets the value of the loanSalesDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoanSalesDate() {
        return loanSalesDate;
    }

    /**
     * Sets the value of the loanSalesDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoanSalesDate(String value) {
        this.loanSalesDate = value;
    }

    /**
     * Gets the value of the loanAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoanAmount() {
        return loanAmount;
    }

    /**
     * Sets the value of the loanAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoanAmount(String value) {
        this.loanAmount = value;
    }

    /**
     * Gets the value of the totalPrincipalUnpaid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalPrincipalUnpaid() {
        return totalPrincipalUnpaid;
    }

    /**
     * Sets the value of the totalPrincipalUnpaid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalPrincipalUnpaid(String value) {
        this.totalPrincipalUnpaid = value;
    }

    /**
     * Gets the value of the edgeUpOriginalCost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEdgeUpOriginalCost() {
        return edgeUpOriginalCost;
    }

    /**
     * Sets the value of the edgeUpOriginalCost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEdgeUpOriginalCost(String value) {
        this.edgeUpOriginalCost = value;
    }

    /**
     * Gets the value of the pastTradeInRestrictionTimePeriod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPastTradeInRestrictionTimePeriod() {
        return pastTradeInRestrictionTimePeriod;
    }

    /**
     * Sets the value of the pastTradeInRestrictionTimePeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPastTradeInRestrictionTimePeriod(String value) {
        this.pastTradeInRestrictionTimePeriod = value;
    }

    /**
     * Gets the value of the numberOfInstallmentsBilled property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumberOfInstallmentsBilled() {
        return numberOfInstallmentsBilled;
    }

    /**
     * Sets the value of the numberOfInstallmentsBilled property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumberOfInstallmentsBilled(String value) {
        this.numberOfInstallmentsBilled = value;
    }

    /**
     * Gets the value of the numberOfInstallmentsToBill property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumberOfInstallmentsToBill() {
        return numberOfInstallmentsToBill;
    }

    /**
     * Sets the value of the numberOfInstallmentsToBill property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumberOfInstallmentsToBill(String value) {
        this.numberOfInstallmentsToBill = value;
    }

    /**
     * Gets the value of the edgeUpRequiredPercentage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEdgeUpRequiredPercentage() {
        return edgeUpRequiredPercentage;
    }

    /**
     * Sets the value of the edgeUpRequiredPercentage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEdgeUpRequiredPercentage(String value) {
        this.edgeUpRequiredPercentage = value;
    }

    /**
     * Gets the value of the loanTermMonthsQty property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoanTermMonthsQty() {
        return loanTermMonthsQty;
    }

    /**
     * Sets the value of the loanTermMonthsQty property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoanTermMonthsQty(String value) {
        this.loanTermMonthsQty = value;
    }

}