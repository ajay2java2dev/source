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
 * <p>Java class for EcpdSplitBillInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EcpdSplitBillInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="splitBillEligibilityIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="splitBillCorpAuthorizationStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="splitBillBlanketApproveIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="splitBillFromEcpdId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="splitBillFromEligibilityIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="splitBillToEligibilityIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="splitBillCorpEcpdId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="splitBillNumPhoneActv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="splitBillIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EcpdSplitBillInfo", propOrder = {
    "splitBillEligibilityIndicator",
    "splitBillCorpAuthorizationStatus",
    "splitBillBlanketApproveIndicator",
    "splitBillFromEcpdId",
    "splitBillFromEligibilityIndicator",
    "splitBillToEligibilityIndicator",
    "splitBillCorpEcpdId",
    "splitBillNumPhoneActv",
    "splitBillIndicator"
})
public class EcpdSplitBillInfo {

    protected String splitBillEligibilityIndicator;
    protected String splitBillCorpAuthorizationStatus;
    protected String splitBillBlanketApproveIndicator;
    protected String splitBillFromEcpdId;
    protected String splitBillFromEligibilityIndicator;
    protected String splitBillToEligibilityIndicator;
    protected String splitBillCorpEcpdId;
    protected String splitBillNumPhoneActv;
    protected String splitBillIndicator;

    /**
     * Gets the value of the splitBillEligibilityIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSplitBillEligibilityIndicator() {
        return splitBillEligibilityIndicator;
    }

    /**
     * Sets the value of the splitBillEligibilityIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSplitBillEligibilityIndicator(String value) {
        this.splitBillEligibilityIndicator = value;
    }

    /**
     * Gets the value of the splitBillCorpAuthorizationStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSplitBillCorpAuthorizationStatus() {
        return splitBillCorpAuthorizationStatus;
    }

    /**
     * Sets the value of the splitBillCorpAuthorizationStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSplitBillCorpAuthorizationStatus(String value) {
        this.splitBillCorpAuthorizationStatus = value;
    }

    /**
     * Gets the value of the splitBillBlanketApproveIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSplitBillBlanketApproveIndicator() {
        return splitBillBlanketApproveIndicator;
    }

    /**
     * Sets the value of the splitBillBlanketApproveIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSplitBillBlanketApproveIndicator(String value) {
        this.splitBillBlanketApproveIndicator = value;
    }

    /**
     * Gets the value of the splitBillFromEcpdId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSplitBillFromEcpdId() {
        return splitBillFromEcpdId;
    }

    /**
     * Sets the value of the splitBillFromEcpdId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSplitBillFromEcpdId(String value) {
        this.splitBillFromEcpdId = value;
    }

    /**
     * Gets the value of the splitBillFromEligibilityIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSplitBillFromEligibilityIndicator() {
        return splitBillFromEligibilityIndicator;
    }

    /**
     * Sets the value of the splitBillFromEligibilityIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSplitBillFromEligibilityIndicator(String value) {
        this.splitBillFromEligibilityIndicator = value;
    }

    /**
     * Gets the value of the splitBillToEligibilityIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSplitBillToEligibilityIndicator() {
        return splitBillToEligibilityIndicator;
    }

    /**
     * Sets the value of the splitBillToEligibilityIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSplitBillToEligibilityIndicator(String value) {
        this.splitBillToEligibilityIndicator = value;
    }

    /**
     * Gets the value of the splitBillCorpEcpdId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSplitBillCorpEcpdId() {
        return splitBillCorpEcpdId;
    }

    /**
     * Sets the value of the splitBillCorpEcpdId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSplitBillCorpEcpdId(String value) {
        this.splitBillCorpEcpdId = value;
    }

    /**
     * Gets the value of the splitBillNumPhoneActv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSplitBillNumPhoneActv() {
        return splitBillNumPhoneActv;
    }

    /**
     * Sets the value of the splitBillNumPhoneActv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSplitBillNumPhoneActv(String value) {
        this.splitBillNumPhoneActv = value;
    }

    /**
     * Gets the value of the splitBillIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSplitBillIndicator() {
        return splitBillIndicator;
    }

    /**
     * Sets the value of the splitBillIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSplitBillIndicator(String value) {
        this.splitBillIndicator = value;
    }

}