//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.20 at 12:05:21 PM EDT 
//


package com.vzwcorp.pricinglab.vo.pounddata;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for VoiceUsageItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VoiceUsageItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mdn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="minutesInfo" type="{}MinutesInfo" minOccurs="0"/>
 *         &lt;element name="globalMinutesInfo" type="{}GlobalMinutesInfo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VoiceUsageItem", propOrder = {
    "mdn",
    "minutesInfo",
    "globalMinutesInfo"
})
public class VoiceUsageItem {

    protected String mdn;
    protected MinutesInfo minutesInfo;
    @XmlElement(required = true)
    protected GlobalMinutesInfo globalMinutesInfo;

    /**
     * Gets the value of the mdn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMdn() {
        return mdn;
    }

    /**
     * Sets the value of the mdn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMdn(String value) {
        this.mdn = value;
    }

    /**
     * Gets the value of the minutesInfo property.
     * 
     * @return
     *     possible object is
     *     {@link MinutesInfo }
     *     
     */
    public MinutesInfo getMinutesInfo() {
        return minutesInfo;
    }

    /**
     * Sets the value of the minutesInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link MinutesInfo }
     *     
     */
    public void setMinutesInfo(MinutesInfo value) {
        this.minutesInfo = value;
    }

    /**
     * Gets the value of the globalMinutesInfo property.
     * 
     * @return
     *     possible object is
     *     {@link GlobalMinutesInfo }
     *     
     */
    public GlobalMinutesInfo getGlobalMinutesInfo() {
        return globalMinutesInfo;
    }

    /**
     * Sets the value of the globalMinutesInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link GlobalMinutesInfo }
     *     
     */
    public void setGlobalMinutesInfo(GlobalMinutesInfo value) {
        this.globalMinutesInfo = value;
    }

}