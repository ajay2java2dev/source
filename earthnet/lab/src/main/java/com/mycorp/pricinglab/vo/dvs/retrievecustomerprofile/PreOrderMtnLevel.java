//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.20 at 04:17:33 PM EDT 
//


package com.vzwcorp.pricinglab.vo.dvs.retrievecustomerprofile;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PreOrderMtnLevel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PreOrderMtnLevel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mtn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mtnOrderType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mtnOrderState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mtnOrderStateDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="buddyMtn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mtnOrderCreateDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="installmentLoanNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="installmentAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="agreementTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PreOrderMtnLevel", propOrder = {
    "mtn",
    "mtnOrderType",
    "mtnOrderState",
    "mtnOrderStateDate",
    "buddyMtn",
    "mtnOrderCreateDate",
    "installmentLoanNumber",
    "installmentAmount",
    "agreementTypeCode"
})
public class PreOrderMtnLevel {

    protected String mtn;
    protected String mtnOrderType;
    protected String mtnOrderState;
    protected String mtnOrderStateDate;
    protected String buddyMtn;
    protected String mtnOrderCreateDate;
    protected String installmentLoanNumber;
    protected String installmentAmount;
    protected String agreementTypeCode;

    /**
     * Gets the value of the mtn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMtn() {
        return mtn;
    }

    /**
     * Sets the value of the mtn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMtn(String value) {
        this.mtn = value;
    }

    /**
     * Gets the value of the mtnOrderType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMtnOrderType() {
        return mtnOrderType;
    }

    /**
     * Sets the value of the mtnOrderType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMtnOrderType(String value) {
        this.mtnOrderType = value;
    }

    /**
     * Gets the value of the mtnOrderState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMtnOrderState() {
        return mtnOrderState;
    }

    /**
     * Sets the value of the mtnOrderState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMtnOrderState(String value) {
        this.mtnOrderState = value;
    }

    /**
     * Gets the value of the mtnOrderStateDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMtnOrderStateDate() {
        return mtnOrderStateDate;
    }

    /**
     * Sets the value of the mtnOrderStateDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMtnOrderStateDate(String value) {
        this.mtnOrderStateDate = value;
    }

    /**
     * Gets the value of the buddyMtn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuddyMtn() {
        return buddyMtn;
    }

    /**
     * Sets the value of the buddyMtn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuddyMtn(String value) {
        this.buddyMtn = value;
    }

    /**
     * Gets the value of the mtnOrderCreateDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMtnOrderCreateDate() {
        return mtnOrderCreateDate;
    }

    /**
     * Sets the value of the mtnOrderCreateDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMtnOrderCreateDate(String value) {
        this.mtnOrderCreateDate = value;
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
     * Gets the value of the installmentAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstallmentAmount() {
        return installmentAmount;
    }

    /**
     * Sets the value of the installmentAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstallmentAmount(String value) {
        this.installmentAmount = value;
    }

    /**
     * Gets the value of the agreementTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgreementTypeCode() {
        return agreementTypeCode;
    }

    /**
     * Sets the value of the agreementTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgreementTypeCode(String value) {
        this.agreementTypeCode = value;
    }

}