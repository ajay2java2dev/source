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
 * <p>Java class for SmsInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SmsInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lastSmsMsgDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastSmsMsgTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numDomSmsReceived" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numDomSmsSent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numIntlSmsReceived" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numIntlSmsSent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numInSmsReceived" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numInsmsSent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numGpSmsReceived" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numGpSmsSent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="globalSmsReceivedCost" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="globalSmsSentCost" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="globalSmsTotalCost" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SmsInfo", propOrder = {
    "lastSmsMsgDate",
    "lastSmsMsgTime",
    "numDomSmsReceived",
    "numDomSmsSent",
    "numIntlSmsReceived",
    "numIntlSmsSent",
    "numInSmsReceived",
    "numInsmsSent",
    "numGpSmsReceived",
    "numGpSmsSent",
    "globalSmsReceivedCost",
    "globalSmsSentCost",
    "globalSmsTotalCost"
})
public class SmsInfo {

    protected String lastSmsMsgDate;
    protected String lastSmsMsgTime;
    protected String numDomSmsReceived;
    protected String numDomSmsSent;
    protected String numIntlSmsReceived;
    protected String numIntlSmsSent;
    protected String numInSmsReceived;
    protected String numInsmsSent;
    protected String numGpSmsReceived;
    protected String numGpSmsSent;
    protected String globalSmsReceivedCost;
    protected String globalSmsSentCost;
    protected String globalSmsTotalCost;

    /**
     * Gets the value of the lastSmsMsgDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastSmsMsgDate() {
        return lastSmsMsgDate;
    }

    /**
     * Sets the value of the lastSmsMsgDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastSmsMsgDate(String value) {
        this.lastSmsMsgDate = value;
    }

    /**
     * Gets the value of the lastSmsMsgTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastSmsMsgTime() {
        return lastSmsMsgTime;
    }

    /**
     * Sets the value of the lastSmsMsgTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastSmsMsgTime(String value) {
        this.lastSmsMsgTime = value;
    }

    /**
     * Gets the value of the numDomSmsReceived property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumDomSmsReceived() {
        return numDomSmsReceived;
    }

    /**
     * Sets the value of the numDomSmsReceived property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumDomSmsReceived(String value) {
        this.numDomSmsReceived = value;
    }

    /**
     * Gets the value of the numDomSmsSent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumDomSmsSent() {
        return numDomSmsSent;
    }

    /**
     * Sets the value of the numDomSmsSent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumDomSmsSent(String value) {
        this.numDomSmsSent = value;
    }

    /**
     * Gets the value of the numIntlSmsReceived property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumIntlSmsReceived() {
        return numIntlSmsReceived;
    }

    /**
     * Sets the value of the numIntlSmsReceived property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumIntlSmsReceived(String value) {
        this.numIntlSmsReceived = value;
    }

    /**
     * Gets the value of the numIntlSmsSent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumIntlSmsSent() {
        return numIntlSmsSent;
    }

    /**
     * Sets the value of the numIntlSmsSent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumIntlSmsSent(String value) {
        this.numIntlSmsSent = value;
    }

    /**
     * Gets the value of the numInSmsReceived property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumInSmsReceived() {
        return numInSmsReceived;
    }

    /**
     * Sets the value of the numInSmsReceived property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumInSmsReceived(String value) {
        this.numInSmsReceived = value;
    }

    /**
     * Gets the value of the numInsmsSent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumInsmsSent() {
        return numInsmsSent;
    }

    /**
     * Sets the value of the numInsmsSent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumInsmsSent(String value) {
        this.numInsmsSent = value;
    }

    /**
     * Gets the value of the numGpSmsReceived property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumGpSmsReceived() {
        return numGpSmsReceived;
    }

    /**
     * Sets the value of the numGpSmsReceived property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumGpSmsReceived(String value) {
        this.numGpSmsReceived = value;
    }

    /**
     * Gets the value of the numGpSmsSent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumGpSmsSent() {
        return numGpSmsSent;
    }

    /**
     * Sets the value of the numGpSmsSent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumGpSmsSent(String value) {
        this.numGpSmsSent = value;
    }

    /**
     * Gets the value of the globalSmsReceivedCost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGlobalSmsReceivedCost() {
        return globalSmsReceivedCost;
    }

    /**
     * Sets the value of the globalSmsReceivedCost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGlobalSmsReceivedCost(String value) {
        this.globalSmsReceivedCost = value;
    }

    /**
     * Gets the value of the globalSmsSentCost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGlobalSmsSentCost() {
        return globalSmsSentCost;
    }

    /**
     * Sets the value of the globalSmsSentCost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGlobalSmsSentCost(String value) {
        this.globalSmsSentCost = value;
    }

    /**
     * Gets the value of the globalSmsTotalCost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGlobalSmsTotalCost() {
        return globalSmsTotalCost;
    }

    /**
     * Sets the value of the globalSmsTotalCost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGlobalSmsTotalCost(String value) {
        this.globalSmsTotalCost = value;
    }

}