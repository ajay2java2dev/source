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
 * <p>Java class for DeviceSimInfoDetailsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DeviceSimInfoDetailsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deviceID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceIDType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="euimid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IMSI_VF" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IMSI_VZW" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeviceSimInfoDetailsType", propOrder = {
    "deviceID",
    "deviceIDType",
    "euimid",
    "imsivf",
    "imsivzw"
})
public class DeviceSimInfoDetailsType {

    protected String deviceID;
    protected String deviceIDType;
    protected String euimid;
    @XmlElement(name = "IMSI_VF")
    protected String imsivf;
    @XmlElement(name = "IMSI_VZW")
    protected String imsivzw;

    /**
     * Gets the value of the deviceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceID() {
        return deviceID;
    }

    /**
     * Sets the value of the deviceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceID(String value) {
        this.deviceID = value;
    }

    /**
     * Gets the value of the deviceIDType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceIDType() {
        return deviceIDType;
    }

    /**
     * Sets the value of the deviceIDType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceIDType(String value) {
        this.deviceIDType = value;
    }

    /**
     * Gets the value of the euimid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEuimid() {
        return euimid;
    }

    /**
     * Sets the value of the euimid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEuimid(String value) {
        this.euimid = value;
    }

    /**
     * Gets the value of the imsivf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIMSIVF() {
        return imsivf;
    }

    /**
     * Sets the value of the imsivf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIMSIVF(String value) {
        this.imsivf = value;
    }

    /**
     * Gets the value of the imsivzw property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIMSIVZW() {
        return imsivzw;
    }

    /**
     * Sets the value of the imsivzw property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIMSIVZW(String value) {
        this.imsivzw = value;
    }

}