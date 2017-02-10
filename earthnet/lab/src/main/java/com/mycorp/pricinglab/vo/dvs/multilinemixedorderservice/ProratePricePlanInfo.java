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
 * <p>Java class for ProratePricePlanInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProratePricePlanInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="planAdvIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="planName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="planEffectiveDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="planEndDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="planPrice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="planPromoDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="planPromoDisc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="planPartialMonth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="planIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="planDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="allowanceListCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{}allowanceList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProratePricePlanInfo", propOrder = {
    "planAdvIndicator",
    "planName",
    "planEffectiveDate",
    "planEndDate",
    "planPrice",
    "planPromoDesc",
    "planPromoDisc",
    "planPartialMonth",
    "planIndicator",
    "planDesc",
    "allowanceListCount",
    "allowanceList"
})
public class ProratePricePlanInfo {

    protected String planAdvIndicator;
    protected String planName;
    protected String planEffectiveDate;
    protected String planEndDate;
    protected String planPrice;
    protected String planPromoDesc;
    protected String planPromoDisc;
    protected String planPartialMonth;
    protected String planIndicator;
    protected String planDesc;
    protected String allowanceListCount;
    protected AllowanceList allowanceList;

    /**
     * Gets the value of the planAdvIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanAdvIndicator() {
        return planAdvIndicator;
    }

    /**
     * Sets the value of the planAdvIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanAdvIndicator(String value) {
        this.planAdvIndicator = value;
    }

    /**
     * Gets the value of the planName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanName() {
        return planName;
    }

    /**
     * Sets the value of the planName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanName(String value) {
        this.planName = value;
    }

    /**
     * Gets the value of the planEffectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanEffectiveDate() {
        return planEffectiveDate;
    }

    /**
     * Sets the value of the planEffectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanEffectiveDate(String value) {
        this.planEffectiveDate = value;
    }

    /**
     * Gets the value of the planEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanEndDate() {
        return planEndDate;
    }

    /**
     * Sets the value of the planEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanEndDate(String value) {
        this.planEndDate = value;
    }

    /**
     * Gets the value of the planPrice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanPrice() {
        return planPrice;
    }

    /**
     * Sets the value of the planPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanPrice(String value) {
        this.planPrice = value;
    }

    /**
     * Gets the value of the planPromoDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanPromoDesc() {
        return planPromoDesc;
    }

    /**
     * Sets the value of the planPromoDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanPromoDesc(String value) {
        this.planPromoDesc = value;
    }

    /**
     * Gets the value of the planPromoDisc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanPromoDisc() {
        return planPromoDisc;
    }

    /**
     * Sets the value of the planPromoDisc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanPromoDisc(String value) {
        this.planPromoDisc = value;
    }

    /**
     * Gets the value of the planPartialMonth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanPartialMonth() {
        return planPartialMonth;
    }

    /**
     * Sets the value of the planPartialMonth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanPartialMonth(String value) {
        this.planPartialMonth = value;
    }

    /**
     * Gets the value of the planIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanIndicator() {
        return planIndicator;
    }

    /**
     * Sets the value of the planIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanIndicator(String value) {
        this.planIndicator = value;
    }

    /**
     * Gets the value of the planDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanDesc() {
        return planDesc;
    }

    /**
     * Sets the value of the planDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanDesc(String value) {
        this.planDesc = value;
    }

    /**
     * Gets the value of the allowanceListCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllowanceListCount() {
        return allowanceListCount;
    }

    /**
     * Sets the value of the allowanceListCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllowanceListCount(String value) {
        this.allowanceListCount = value;
    }

    /**
     * Gets the value of the allowanceList property.
     * 
     * @return
     *     possible object is
     *     {@link AllowanceList }
     *     
     */
    public AllowanceList getAllowanceList() {
        return allowanceList;
    }

    /**
     * Sets the value of the allowanceList property.
     * 
     * @param value
     *     allowed object is
     *     {@link AllowanceList }
     *     
     */
    public void setAllowanceList(AllowanceList value) {
        this.allowanceList = value;
    }

}