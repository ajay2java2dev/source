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
 * <p>Java class for MessageUsageItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MessageUsageItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mdn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="smsInfo" type="{}SmsInfo" minOccurs="0"/>
 *         &lt;element name="mmsInfo" type="{}MmsInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageUsageItem", propOrder = {
    "mdn",
    "smsInfo",
    "mmsInfo"
})
public class MessageUsageItem {

    protected String mdn;
    protected SmsInfo smsInfo;
    protected MmsInfo mmsInfo;

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
     * Gets the value of the smsInfo property.
     * 
     * @return
     *     possible object is
     *     {@link SmsInfo }
     *     
     */
    public SmsInfo getSmsInfo() {
        return smsInfo;
    }

    /**
     * Sets the value of the smsInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link SmsInfo }
     *     
     */
    public void setSmsInfo(SmsInfo value) {
        this.smsInfo = value;
    }

    /**
     * Gets the value of the mmsInfo property.
     * 
     * @return
     *     possible object is
     *     {@link MmsInfo }
     *     
     */
    public MmsInfo getMmsInfo() {
        return mmsInfo;
    }

    /**
     * Sets the value of the mmsInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link MmsInfo }
     *     
     */
    public void setMmsInfo(MmsInfo value) {
        this.mmsInfo = value;
    }

}