//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.20 at 12:05:21 PM EDT 
//


package com.vzwcorp.pricinglab.vo.pounddata;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TextAllowanceBar complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TextAllowanceBar">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="barSequence" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="featureTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="barColor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="barPercent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalUsed" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalAllowed" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalRemaining" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="allowanceType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="allowanceSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="warningCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TextAllowanceBar", propOrder = {
    "barSequence",
    "featureTypeCode",
    "barColor",
    "barPercent",
    "totalUsed",
    "totalAllowed",
    "totalRemaining",
    "allowanceType",
    "allowanceSource",
    "warningCode"
})
public class TextAllowanceBar {

    protected String barSequence;
    protected String featureTypeCode;
    protected String barColor;
    protected String barPercent;
    protected String totalUsed;
    protected String totalAllowed;
    protected String totalRemaining;
    protected String allowanceType;
    protected String allowanceSource;
    protected String warningCode;

    /**
     * Gets the value of the barSequence property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBarSequence() {
        return barSequence;
    }

    /**
     * Sets the value of the barSequence property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBarSequence(String value) {
        this.barSequence = value;
    }

    /**
     * Gets the value of the featureTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFeatureTypeCode() {
        return featureTypeCode;
    }

    /**
     * Sets the value of the featureTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFeatureTypeCode(String value) {
        this.featureTypeCode = value;
    }

    /**
     * Gets the value of the barColor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBarColor() {
        return barColor;
    }

    /**
     * Sets the value of the barColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBarColor(String value) {
        this.barColor = value;
    }

    /**
     * Gets the value of the barPercent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBarPercent() {
        return barPercent;
    }

    /**
     * Sets the value of the barPercent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBarPercent(String value) {
        this.barPercent = value;
    }

    /**
     * Gets the value of the totalUsed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalUsed() {
        return totalUsed;
    }

    /**
     * Sets the value of the totalUsed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalUsed(String value) {
        this.totalUsed = value;
    }

    /**
     * Gets the value of the totalAllowed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalAllowed() {
        return totalAllowed;
    }

    /**
     * Sets the value of the totalAllowed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalAllowed(String value) {
        this.totalAllowed = value;
    }

    /**
     * Gets the value of the totalRemaining property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalRemaining() {
        return totalRemaining;
    }

    /**
     * Sets the value of the totalRemaining property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalRemaining(String value) {
        this.totalRemaining = value;
    }

    /**
     * Gets the value of the allowanceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllowanceType() {
        return allowanceType;
    }

    /**
     * Sets the value of the allowanceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllowanceType(String value) {
        this.allowanceType = value;
    }

    /**
     * Gets the value of the allowanceSource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllowanceSource() {
        return allowanceSource;
    }

    /**
     * Sets the value of the allowanceSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllowanceSource(String value) {
        this.allowanceSource = value;
    }

    /**
     * Gets the value of the warningCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWarningCode() {
        return warningCode;
    }

    /**
     * Sets the value of the warningCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWarningCode(String value) {
        this.warningCode = value;
    }

}