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
 * <p>Java class for SharePlanPotentialNAFDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SharePlanPotentialNAFDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sharePlanDevice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nafSpecialFeatureOfferingId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SharePlanPotentialNAFDetail", propOrder = {
    "sharePlanDevice",
    "nafSpecialFeatureOfferingId"
})
public class SharePlanPotentialNAFDetail {

    protected String sharePlanDevice;
    protected String nafSpecialFeatureOfferingId;

    /**
     * Gets the value of the sharePlanDevice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSharePlanDevice() {
        return sharePlanDevice;
    }

    /**
     * Sets the value of the sharePlanDevice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSharePlanDevice(String value) {
        this.sharePlanDevice = value;
    }

    /**
     * Gets the value of the nafSpecialFeatureOfferingId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNafSpecialFeatureOfferingId() {
        return nafSpecialFeatureOfferingId;
    }

    /**
     * Sets the value of the nafSpecialFeatureOfferingId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNafSpecialFeatureOfferingId(String value) {
        this.nafSpecialFeatureOfferingId = value;
    }

}