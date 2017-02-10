//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.20 at 04:17:33 PM EDT 
//


package com.vzwcorp.pricinglab.vo.dvs.retrievecustomerprofile;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DnsFlags complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DnsFlags">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="directMailInd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="teleMarketInd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="txtMsgInd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emailInd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="surveyInd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="updateAllMtnFlags" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cpniOptOutInd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="businessMarketingReportsOptOutInd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="relevantMobileAdvertisingOptOutInd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="socialMediaAddressIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{}multiChannelMarketingInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DnsFlags", propOrder = {
    "directMailInd",
    "teleMarketInd",
    "txtMsgInd",
    "emailInd",
    "surveyInd",
    "updateAllMtnFlags",
    "cpniOptOutInd",
    "businessMarketingReportsOptOutInd",
    "relevantMobileAdvertisingOptOutInd",
    "socialMediaAddressIndicator",
    "multiChannelMarketingInfo"
})
public class DnsFlags {

    @XmlElement(defaultValue = "")
    protected String directMailInd;
    @XmlElement(defaultValue = "")
    protected String teleMarketInd;
    @XmlElement(defaultValue = "")
    protected String txtMsgInd;
    @XmlElement(defaultValue = "")
    protected String emailInd;
    @XmlElement(defaultValue = "")
    protected String surveyInd;
    @XmlElement(defaultValue = "")
    protected String updateAllMtnFlags;
    @XmlElement(defaultValue = "")
    protected String cpniOptOutInd;
    @XmlElement(defaultValue = "")
    protected String businessMarketingReportsOptOutInd;
    @XmlElement(defaultValue = "")
    protected String relevantMobileAdvertisingOptOutInd;
    @XmlElement(defaultValue = "")
    protected String socialMediaAddressIndicator;
    protected MultiChannelMarketingInfo multiChannelMarketingInfo;

    /**
     * Gets the value of the directMailInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirectMailInd() {
        return directMailInd;
    }

    /**
     * Sets the value of the directMailInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirectMailInd(String value) {
        this.directMailInd = value;
    }

    /**
     * Gets the value of the teleMarketInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTeleMarketInd() {
        return teleMarketInd;
    }

    /**
     * Sets the value of the teleMarketInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTeleMarketInd(String value) {
        this.teleMarketInd = value;
    }

    /**
     * Gets the value of the txtMsgInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTxtMsgInd() {
        return txtMsgInd;
    }

    /**
     * Sets the value of the txtMsgInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTxtMsgInd(String value) {
        this.txtMsgInd = value;
    }

    /**
     * Gets the value of the emailInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailInd() {
        return emailInd;
    }

    /**
     * Sets the value of the emailInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailInd(String value) {
        this.emailInd = value;
    }

    /**
     * Gets the value of the surveyInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSurveyInd() {
        return surveyInd;
    }

    /**
     * Sets the value of the surveyInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSurveyInd(String value) {
        this.surveyInd = value;
    }

    /**
     * Gets the value of the updateAllMtnFlags property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdateAllMtnFlags() {
        return updateAllMtnFlags;
    }

    /**
     * Sets the value of the updateAllMtnFlags property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdateAllMtnFlags(String value) {
        this.updateAllMtnFlags = value;
    }

    /**
     * Gets the value of the cpniOptOutInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpniOptOutInd() {
        return cpniOptOutInd;
    }

    /**
     * Sets the value of the cpniOptOutInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpniOptOutInd(String value) {
        this.cpniOptOutInd = value;
    }

    /**
     * Gets the value of the businessMarketingReportsOptOutInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessMarketingReportsOptOutInd() {
        return businessMarketingReportsOptOutInd;
    }

    /**
     * Sets the value of the businessMarketingReportsOptOutInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessMarketingReportsOptOutInd(String value) {
        this.businessMarketingReportsOptOutInd = value;
    }

    /**
     * Gets the value of the relevantMobileAdvertisingOptOutInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelevantMobileAdvertisingOptOutInd() {
        return relevantMobileAdvertisingOptOutInd;
    }

    /**
     * Sets the value of the relevantMobileAdvertisingOptOutInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelevantMobileAdvertisingOptOutInd(String value) {
        this.relevantMobileAdvertisingOptOutInd = value;
    }

    /**
     * Gets the value of the socialMediaAddressIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSocialMediaAddressIndicator() {
        return socialMediaAddressIndicator;
    }

    /**
     * Sets the value of the socialMediaAddressIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSocialMediaAddressIndicator(String value) {
        this.socialMediaAddressIndicator = value;
    }

    /**
     * Gets the value of the multiChannelMarketingInfo property.
     * 
     * @return
     *     possible object is
     *     {@link MultiChannelMarketingInfo }
     *     
     */
    public MultiChannelMarketingInfo getMultiChannelMarketingInfo() {
        return multiChannelMarketingInfo;
    }

    /**
     * Sets the value of the multiChannelMarketingInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultiChannelMarketingInfo }
     *     
     */
    public void setMultiChannelMarketingInfo(MultiChannelMarketingInfo value) {
        this.multiChannelMarketingInfo = value;
    }

}