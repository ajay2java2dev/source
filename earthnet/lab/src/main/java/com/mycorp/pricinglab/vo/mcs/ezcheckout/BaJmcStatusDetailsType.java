//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.31 at 10:31:22 AM EDT 
//


package com.vzwcorp.pricinglab.vo.mcs.ezcheckout;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BaJmcStatusDetailsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BaJmcStatusDetailsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="baJmcStatEffTs" type="{http://www.vzw.com/namespaces/scmplus}DateTime" minOccurs="0"/>
 *         &lt;element name="jmcId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="jmcStatDscVzw" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaJmcStatusDetailsType", propOrder = {
    "baJmcStatEffTs",
    "jmcId",
    "jmcStatDscVzw"
})
public class BaJmcStatusDetailsType {

    protected String baJmcStatEffTs;
    protected String jmcId;
    protected String jmcStatDscVzw;

    /**
     * Gets the value of the baJmcStatEffTs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaJmcStatEffTs() {
        return baJmcStatEffTs;
    }

    /**
     * Sets the value of the baJmcStatEffTs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaJmcStatEffTs(String value) {
        this.baJmcStatEffTs = value;
    }

    /**
     * Gets the value of the jmcId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJmcId() {
        return jmcId;
    }

    /**
     * Sets the value of the jmcId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJmcId(String value) {
        this.jmcId = value;
    }

    /**
     * Gets the value of the jmcStatDscVzw property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJmcStatDscVzw() {
        return jmcStatDscVzw;
    }

    /**
     * Sets the value of the jmcStatDscVzw property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJmcStatDscVzw(String value) {
        this.jmcStatDscVzw = value;
    }

}