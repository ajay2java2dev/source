//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.09.13 at 01:37:28 PM EDT 
//


package com.vzwcorp.pricinglab.vo.dvs.nbs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PromoSpecialFeatureOfferingInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PromoSpecialFeatureOfferingInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="promoSpecialFeatureOfferingId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="specialFeatureOfferingMonthlyAccessCharge" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="specialFeatureOfferingDiscountAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PromoSpecialFeatureOfferingInfo", propOrder = {
    "promoSpecialFeatureOfferingId",
    "specialFeatureOfferingMonthlyAccessCharge",
    "specialFeatureOfferingDiscountAmount"
})
public class PromoSpecialFeatureOfferingInfo {

    protected String promoSpecialFeatureOfferingId;
    protected String specialFeatureOfferingMonthlyAccessCharge;
    protected String specialFeatureOfferingDiscountAmount;

    /**
     * Gets the value of the promoSpecialFeatureOfferingId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPromoSpecialFeatureOfferingId() {
        return promoSpecialFeatureOfferingId;
    }

    /**
     * Sets the value of the promoSpecialFeatureOfferingId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPromoSpecialFeatureOfferingId(String value) {
        this.promoSpecialFeatureOfferingId = value;
    }

    /**
     * Gets the value of the specialFeatureOfferingMonthlyAccessCharge property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpecialFeatureOfferingMonthlyAccessCharge() {
        return specialFeatureOfferingMonthlyAccessCharge;
    }

    /**
     * Sets the value of the specialFeatureOfferingMonthlyAccessCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpecialFeatureOfferingMonthlyAccessCharge(String value) {
        this.specialFeatureOfferingMonthlyAccessCharge = value;
    }

    /**
     * Gets the value of the specialFeatureOfferingDiscountAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpecialFeatureOfferingDiscountAmount() {
        return specialFeatureOfferingDiscountAmount;
    }

    /**
     * Sets the value of the specialFeatureOfferingDiscountAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpecialFeatureOfferingDiscountAmount(String value) {
        this.specialFeatureOfferingDiscountAmount = value;
    }

}