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
 * <p>Java class for DiscountInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DiscountInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="promoPercentageOff" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="promoDollarAmountOff" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="promoOverridePrice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DiscountInfo", propOrder = {
    "promoPercentageOff",
    "promoDollarAmountOff",
    "promoOverridePrice"
})
public class DiscountInfo {

    protected String promoPercentageOff;
    protected String promoDollarAmountOff;
    protected String promoOverridePrice;

    /**
     * Gets the value of the promoPercentageOff property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPromoPercentageOff() {
        return promoPercentageOff;
    }

    /**
     * Sets the value of the promoPercentageOff property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPromoPercentageOff(String value) {
        this.promoPercentageOff = value;
    }

    /**
     * Gets the value of the promoDollarAmountOff property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPromoDollarAmountOff() {
        return promoDollarAmountOff;
    }

    /**
     * Sets the value of the promoDollarAmountOff property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPromoDollarAmountOff(String value) {
        this.promoDollarAmountOff = value;
    }

    /**
     * Gets the value of the promoOverridePrice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPromoOverridePrice() {
        return promoOverridePrice;
    }

    /**
     * Sets the value of the promoOverridePrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPromoOverridePrice(String value) {
        this.promoOverridePrice = value;
    }

}