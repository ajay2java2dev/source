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
 * <p>Java class for GlobalRoamFeature complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GlobalRoamFeature">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="featureId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastUsageDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastUsageTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataUsed" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataUsedUnitOfMeasure" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalCost" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mdnLastUsageDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mdnLastUsageTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mdnDataUsed" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mdnDataCost" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shareIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="barColor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="barPercent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalAllowance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="allowanceUnitOfMeasure" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalRemainingAllowance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="allowanceType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="overageQty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="overageQtyUnitOfMeasure" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceProductId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rateOfferId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="warning" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="overageRateAmt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{}dataRateGroupList"/>
 *         &lt;element name="serviceProductEffectiveDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceProductEndDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GlobalRoamFeature", propOrder = {
    "featureId",
    "description",
    "lastUsageDate",
    "lastUsageTime",
    "dataUsed",
    "dataUsedUnitOfMeasure",
    "totalCost",
    "mdnLastUsageDate",
    "mdnLastUsageTime",
    "mdnDataUsed",
    "mdnDataCost",
    "shareIndicator",
    "barColor",
    "barPercent",
    "totalAllowance",
    "allowanceUnitOfMeasure",
    "totalRemainingAllowance",
    "allowanceType",
    "overageQty",
    "overageQtyUnitOfMeasure",
    "serviceProductId",
    "rateOfferId",
    "warning",
    "overageRateAmt",
    "dataRateGroupList",
    "serviceProductEffectiveDate",
    "serviceProductEndDate"
})
public class GlobalRoamFeature {

    protected String featureId;
    protected String description;
    protected String lastUsageDate;
    protected String lastUsageTime;
    protected String dataUsed;
    protected String dataUsedUnitOfMeasure;
    protected String totalCost;
    protected String mdnLastUsageDate;
    protected String mdnLastUsageTime;
    protected String mdnDataUsed;
    protected String mdnDataCost;
    protected String shareIndicator;
    protected String barColor;
    protected String barPercent;
    protected String totalAllowance;
    protected String allowanceUnitOfMeasure;
    protected String totalRemainingAllowance;
    protected String allowanceType;
    protected String overageQty;
    protected String overageQtyUnitOfMeasure;
    protected String serviceProductId;
    protected String rateOfferId;
    protected String warning;
    protected String overageRateAmt;
    @XmlElement(required = true)
    protected DataRateGroupList dataRateGroupList;
    protected String serviceProductEffectiveDate;
    protected String serviceProductEndDate;

    /**
     * Gets the value of the featureId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFeatureId() {
        return featureId;
    }

    /**
     * Sets the value of the featureId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFeatureId(String value) {
        this.featureId = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the lastUsageDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastUsageDate() {
        return lastUsageDate;
    }

    /**
     * Sets the value of the lastUsageDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastUsageDate(String value) {
        this.lastUsageDate = value;
    }

    /**
     * Gets the value of the lastUsageTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastUsageTime() {
        return lastUsageTime;
    }

    /**
     * Sets the value of the lastUsageTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastUsageTime(String value) {
        this.lastUsageTime = value;
    }

    /**
     * Gets the value of the dataUsed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataUsed() {
        return dataUsed;
    }

    /**
     * Sets the value of the dataUsed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataUsed(String value) {
        this.dataUsed = value;
    }

    /**
     * Gets the value of the dataUsedUnitOfMeasure property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataUsedUnitOfMeasure() {
        return dataUsedUnitOfMeasure;
    }

    /**
     * Sets the value of the dataUsedUnitOfMeasure property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataUsedUnitOfMeasure(String value) {
        this.dataUsedUnitOfMeasure = value;
    }

    /**
     * Gets the value of the totalCost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalCost() {
        return totalCost;
    }

    /**
     * Sets the value of the totalCost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalCost(String value) {
        this.totalCost = value;
    }

    /**
     * Gets the value of the mdnLastUsageDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMdnLastUsageDate() {
        return mdnLastUsageDate;
    }

    /**
     * Sets the value of the mdnLastUsageDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMdnLastUsageDate(String value) {
        this.mdnLastUsageDate = value;
    }

    /**
     * Gets the value of the mdnLastUsageTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMdnLastUsageTime() {
        return mdnLastUsageTime;
    }

    /**
     * Sets the value of the mdnLastUsageTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMdnLastUsageTime(String value) {
        this.mdnLastUsageTime = value;
    }

    /**
     * Gets the value of the mdnDataUsed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMdnDataUsed() {
        return mdnDataUsed;
    }

    /**
     * Sets the value of the mdnDataUsed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMdnDataUsed(String value) {
        this.mdnDataUsed = value;
    }

    /**
     * Gets the value of the mdnDataCost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMdnDataCost() {
        return mdnDataCost;
    }

    /**
     * Sets the value of the mdnDataCost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMdnDataCost(String value) {
        this.mdnDataCost = value;
    }

    /**
     * Gets the value of the shareIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShareIndicator() {
        return shareIndicator;
    }

    /**
     * Sets the value of the shareIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShareIndicator(String value) {
        this.shareIndicator = value;
    }

    /**
     * Gets the value of the barColor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBarColor() {
        return barColor;
    }

    /**
     * Sets the value of the barColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBarColor(String value) {
        this.barColor = value;
    }

    /**
     * Gets the value of the barPercent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBarPercent() {
        return barPercent;
    }

    /**
     * Sets the value of the barPercent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBarPercent(String value) {
        this.barPercent = value;
    }

    /**
     * Gets the value of the totalAllowance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalAllowance() {
        return totalAllowance;
    }

    /**
     * Sets the value of the totalAllowance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalAllowance(String value) {
        this.totalAllowance = value;
    }

    /**
     * Gets the value of the allowanceUnitOfMeasure property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllowanceUnitOfMeasure() {
        return allowanceUnitOfMeasure;
    }

    /**
     * Sets the value of the allowanceUnitOfMeasure property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllowanceUnitOfMeasure(String value) {
        this.allowanceUnitOfMeasure = value;
    }

    /**
     * Gets the value of the totalRemainingAllowance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalRemainingAllowance() {
        return totalRemainingAllowance;
    }

    /**
     * Sets the value of the totalRemainingAllowance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalRemainingAllowance(String value) {
        this.totalRemainingAllowance = value;
    }

    /**
     * Gets the value of the allowanceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllowanceType() {
        return allowanceType;
    }

    /**
     * Sets the value of the allowanceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllowanceType(String value) {
        this.allowanceType = value;
    }

    /**
     * Gets the value of the overageQty property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOverageQty() {
        return overageQty;
    }

    /**
     * Sets the value of the overageQty property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOverageQty(String value) {
        this.overageQty = value;
    }

    /**
     * Gets the value of the overageQtyUnitOfMeasure property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOverageQtyUnitOfMeasure() {
        return overageQtyUnitOfMeasure;
    }

    /**
     * Sets the value of the overageQtyUnitOfMeasure property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOverageQtyUnitOfMeasure(String value) {
        this.overageQtyUnitOfMeasure = value;
    }

    /**
     * Gets the value of the serviceProductId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceProductId() {
        return serviceProductId;
    }

    /**
     * Sets the value of the serviceProductId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceProductId(String value) {
        this.serviceProductId = value;
    }

    /**
     * Gets the value of the rateOfferId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateOfferId() {
        return rateOfferId;
    }

    /**
     * Sets the value of the rateOfferId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateOfferId(String value) {
        this.rateOfferId = value;
    }

    /**
     * Gets the value of the warning property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWarning() {
        return warning;
    }

    /**
     * Sets the value of the warning property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWarning(String value) {
        this.warning = value;
    }

    /**
     * Gets the value of the overageRateAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOverageRateAmt() {
        return overageRateAmt;
    }

    /**
     * Sets the value of the overageRateAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOverageRateAmt(String value) {
        this.overageRateAmt = value;
    }

    /**
     * Gets the value of the dataRateGroupList property.
     * 
     * @return
     *     possible object is
     *     {@link DataRateGroupList }
     *     
     */
    public DataRateGroupList getDataRateGroupList() {
        return dataRateGroupList;
    }

    /**
     * Sets the value of the dataRateGroupList property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataRateGroupList }
     *     
     */
    public void setDataRateGroupList(DataRateGroupList value) {
        this.dataRateGroupList = value;
    }

    /**
     * Gets the value of the serviceProductEffectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceProductEffectiveDate() {
        return serviceProductEffectiveDate;
    }

    /**
     * Sets the value of the serviceProductEffectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceProductEffectiveDate(String value) {
        this.serviceProductEffectiveDate = value;
    }

    /**
     * Gets the value of the serviceProductEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceProductEndDate() {
        return serviceProductEndDate;
    }

    /**
     * Sets the value of the serviceProductEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceProductEndDate(String value) {
        this.serviceProductEndDate = value;
    }

}