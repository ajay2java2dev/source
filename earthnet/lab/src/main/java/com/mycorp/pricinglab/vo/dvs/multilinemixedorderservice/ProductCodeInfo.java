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
 * <p>Java class for ProductCodeInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProductCodeInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pctOff" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sharePlanMonthlyAccessCharge" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sharePlanECPDAdditionalDiscountAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductCodeInfo", propOrder = {
    "productId",
    "productName",
    "pctOff",
    "sharePlanMonthlyAccessCharge",
    "sharePlanECPDAdditionalDiscountAmount"
})
public class ProductCodeInfo {

    protected String productId;
    protected String productName;
    protected String pctOff;
    protected String sharePlanMonthlyAccessCharge;
    protected String sharePlanECPDAdditionalDiscountAmount;

    /**
     * Gets the value of the productId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductId() {
        return productId;
    }

    /**
     * Sets the value of the productId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductId(String value) {
        this.productId = value;
    }

    /**
     * Gets the value of the productName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the value of the productName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductName(String value) {
        this.productName = value;
    }

    /**
     * Gets the value of the pctOff property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPctOff() {
        return pctOff;
    }

    /**
     * Sets the value of the pctOff property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPctOff(String value) {
        this.pctOff = value;
    }

    /**
     * Gets the value of the sharePlanMonthlyAccessCharge property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSharePlanMonthlyAccessCharge() {
        return sharePlanMonthlyAccessCharge;
    }

    /**
     * Sets the value of the sharePlanMonthlyAccessCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSharePlanMonthlyAccessCharge(String value) {
        this.sharePlanMonthlyAccessCharge = value;
    }

    /**
     * Gets the value of the sharePlanECPDAdditionalDiscountAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSharePlanECPDAdditionalDiscountAmount() {
        return sharePlanECPDAdditionalDiscountAmount;
    }

    /**
     * Sets the value of the sharePlanECPDAdditionalDiscountAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSharePlanECPDAdditionalDiscountAmount(String value) {
        this.sharePlanECPDAdditionalDiscountAmount = value;
    }

}