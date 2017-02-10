//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.18 at 05:00:26 PM EDT 
//


package com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CampaignManagerInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CampaignManagerInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="earlyUpgradeIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="edgeUpgradeIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="earlyUpgradeCampaignCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="earlyUpgradeCampaignList">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="campaignId" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="errorInfo" type="{}CommonErrors" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CampaignManagerInfo", propOrder = {
    "earlyUpgradeIndicator",
    "edgeUpgradeIndicator",
    "earlyUpgradeCampaignCount",
    "earlyUpgradeCampaignList",
    "errorInfo"
})
public class CampaignManagerInfo {

    protected String earlyUpgradeIndicator;
    protected String edgeUpgradeIndicator;
    protected String earlyUpgradeCampaignCount;
    @XmlElement(required = true)
    protected CampaignManagerInfo.EarlyUpgradeCampaignList earlyUpgradeCampaignList;
    protected CommonErrors errorInfo;

    /**
     * Gets the value of the earlyUpgradeIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEarlyUpgradeIndicator() {
        return earlyUpgradeIndicator;
    }

    /**
     * Sets the value of the earlyUpgradeIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEarlyUpgradeIndicator(String value) {
        this.earlyUpgradeIndicator = value;
    }

    /**
     * Gets the value of the edgeUpgradeIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEdgeUpgradeIndicator() {
        return edgeUpgradeIndicator;
    }

    /**
     * Sets the value of the edgeUpgradeIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEdgeUpgradeIndicator(String value) {
        this.edgeUpgradeIndicator = value;
    }

    /**
     * Gets the value of the earlyUpgradeCampaignCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEarlyUpgradeCampaignCount() {
        return earlyUpgradeCampaignCount;
    }

    /**
     * Sets the value of the earlyUpgradeCampaignCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEarlyUpgradeCampaignCount(String value) {
        this.earlyUpgradeCampaignCount = value;
    }

    /**
     * Gets the value of the earlyUpgradeCampaignList property.
     * 
     * @return
     *     possible object is
     *     {@link CampaignManagerInfo.EarlyUpgradeCampaignList }
     *     
     */
    public CampaignManagerInfo.EarlyUpgradeCampaignList getEarlyUpgradeCampaignList() {
        return earlyUpgradeCampaignList;
    }

    /**
     * Sets the value of the earlyUpgradeCampaignList property.
     * 
     * @param value
     *     allowed object is
     *     {@link CampaignManagerInfo.EarlyUpgradeCampaignList }
     *     
     */
    public void setEarlyUpgradeCampaignList(CampaignManagerInfo.EarlyUpgradeCampaignList value) {
        this.earlyUpgradeCampaignList = value;
    }

    /**
     * Gets the value of the errorInfo property.
     * 
     * @return
     *     possible object is
     *     {@link CommonErrors }
     *     
     */
    public CommonErrors getErrorInfo() {
        return errorInfo;
    }

    /**
     * Sets the value of the errorInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CommonErrors }
     *     
     */
    public void setErrorInfo(CommonErrors value) {
        this.errorInfo = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="campaignId" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "campaignId"
    })
    public static class EarlyUpgradeCampaignList {

        protected List<String> campaignId;

        /**
         * Gets the value of the campaignId property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the campaignId property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCampaignId().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getCampaignId() {
            if (campaignId == null) {
                campaignId = new ArrayList<String>();
            }
            return this.campaignId;
        }

    }

}