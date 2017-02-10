//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.18 at 05:00:26 PM EDT 
//


package com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for VaultCheckStorageResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VaultCheckStorageResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="allowFeatureChange" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currentStorage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="storageOverLimit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="recommendedFeatureCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VaultCheckStorageResponse", propOrder = {
    "allowFeatureChange",
    "currentStorage",
    "storageOverLimit",
    "recommendedFeatureCode"
})
public class VaultCheckStorageResponse {

    protected String allowFeatureChange;
    protected String currentStorage;
    protected String storageOverLimit;
    protected String recommendedFeatureCode;

    /**
     * Gets the value of the allowFeatureChange property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllowFeatureChange() {
        return allowFeatureChange;
    }

    /**
     * Sets the value of the allowFeatureChange property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllowFeatureChange(String value) {
        this.allowFeatureChange = value;
    }

    /**
     * Gets the value of the currentStorage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentStorage() {
        return currentStorage;
    }

    /**
     * Sets the value of the currentStorage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentStorage(String value) {
        this.currentStorage = value;
    }

    /**
     * Gets the value of the storageOverLimit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStorageOverLimit() {
        return storageOverLimit;
    }

    /**
     * Sets the value of the storageOverLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStorageOverLimit(String value) {
        this.storageOverLimit = value;
    }

    /**
     * Gets the value of the recommendedFeatureCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecommendedFeatureCode() {
        return recommendedFeatureCode;
    }

    /**
     * Sets the value of the recommendedFeatureCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecommendedFeatureCode(String value) {
        this.recommendedFeatureCode = value;
    }

}