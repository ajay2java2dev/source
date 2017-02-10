//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.31 at 10:31:22 AM EDT 
//


package com.vzwcorp.pricinglab.vo.mcs.ezcheckout;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *                 Street Address
 *             
 * 
 * <p>Java class for streetAddressType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="streetAddressType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="location" type="{http://www.vzw.com/namespaces/scmplus}locationType" minOccurs="0"/>
 *         &lt;element name="Addr" type="{http://www.vzw.com/namespaces/scmplus}addrLineType" minOccurs="0"/>
 *         &lt;element name="Addr2" type="{http://www.vzw.com/namespaces/scmplus}addrLineType" minOccurs="0"/>
 *         &lt;element name="Addr3" type="{http://www.vzw.com/namespaces/scmplus}addrLineType" minOccurs="0"/>
 *         &lt;element name="City" type="{http://www.vzw.com/namespaces/scmplus}cityNameType" minOccurs="0"/>
 *         &lt;element name="StCd" type="{http://www.vzw.com/namespaces/scmplus}stateCodeType" minOccurs="0"/>
 *         &lt;element name="ZipCd" type="{http://www.vzw.com/namespaces/scmplus}zipCodeType" minOccurs="0"/>
 *         &lt;element name="CountryCode" type="{http://www.vzw.com/namespaces/scmplus}countryCodeType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "streetAddressType", propOrder = {
    "location",
    "addr",
    "addr2",
    "addr3",
    "city",
    "stCd",
    "zipCd",
    "countryCode"
})
public class StreetAddressType {

    protected LocationType location;
    @XmlElement(name = "Addr")
    protected String addr;
    @XmlElement(name = "Addr2")
    protected String addr2;
    @XmlElement(name = "Addr3")
    protected String addr3;
    @XmlElement(name = "City")
    protected String city;
    @XmlElement(name = "StCd")
    protected String stCd;
    @XmlElement(name = "ZipCd")
    protected String zipCd;
    @XmlElement(name = "CountryCode")
    protected String countryCode;

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link LocationType }
     *     
     */
    public LocationType getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocationType }
     *     
     */
    public void setLocation(LocationType value) {
        this.location = value;
    }

    /**
     * Gets the value of the addr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddr() {
        return addr;
    }

    /**
     * Sets the value of the addr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddr(String value) {
        this.addr = value;
    }

    /**
     * Gets the value of the addr2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddr2() {
        return addr2;
    }

    /**
     * Sets the value of the addr2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddr2(String value) {
        this.addr2 = value;
    }

    /**
     * Gets the value of the addr3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddr3() {
        return addr3;
    }

    /**
     * Sets the value of the addr3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddr3(String value) {
        this.addr3 = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the stCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStCd() {
        return stCd;
    }

    /**
     * Sets the value of the stCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStCd(String value) {
        this.stCd = value;
    }

    /**
     * Gets the value of the zipCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZipCd() {
        return zipCd;
    }

    /**
     * Sets the value of the zipCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZipCd(String value) {
        this.zipCd = value;
    }

    /**
     * Gets the value of the countryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the value of the countryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryCode(String value) {
        this.countryCode = value;
    }

}