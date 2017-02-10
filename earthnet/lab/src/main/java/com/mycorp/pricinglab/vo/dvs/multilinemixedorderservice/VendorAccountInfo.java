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
 * <p>Java class for VendorAccountInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VendorAccountInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="payment_term_no_of_days" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lateFeeIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lateFeeAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountRepEmailInfo" type="{}EmailInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VendorAccountInfo", propOrder = {
    "paymentTermNoOfDays",
    "lateFeeIndicator",
    "lateFeeAmount",
    "accountRepEmailInfo"
})
public class VendorAccountInfo {

    @XmlElement(name = "payment_term_no_of_days")
    protected String paymentTermNoOfDays;
    protected String lateFeeIndicator;
    protected String lateFeeAmount;
    protected EmailInfo accountRepEmailInfo;

    /**
     * Gets the value of the paymentTermNoOfDays property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentTermNoOfDays() {
        return paymentTermNoOfDays;
    }

    /**
     * Sets the value of the paymentTermNoOfDays property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentTermNoOfDays(String value) {
        this.paymentTermNoOfDays = value;
    }

    /**
     * Gets the value of the lateFeeIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLateFeeIndicator() {
        return lateFeeIndicator;
    }

    /**
     * Sets the value of the lateFeeIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLateFeeIndicator(String value) {
        this.lateFeeIndicator = value;
    }

    /**
     * Gets the value of the lateFeeAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLateFeeAmount() {
        return lateFeeAmount;
    }

    /**
     * Sets the value of the lateFeeAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLateFeeAmount(String value) {
        this.lateFeeAmount = value;
    }

    /**
     * Gets the value of the accountRepEmailInfo property.
     * 
     * @return
     *     possible object is
     *     {@link EmailInfo }
     *     
     */
    public EmailInfo getAccountRepEmailInfo() {
        return accountRepEmailInfo;
    }

    /**
     * Sets the value of the accountRepEmailInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmailInfo }
     *     
     */
    public void setAccountRepEmailInfo(EmailInfo value) {
        this.accountRepEmailInfo = value;
    }

}