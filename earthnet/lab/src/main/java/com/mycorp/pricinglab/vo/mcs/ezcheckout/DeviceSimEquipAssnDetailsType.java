//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.31 at 10:31:22 AM EDT 
//


package com.vzwcorp.pricinglab.vo.mcs.ezcheckout;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DeviceSimEquipAssnDetailsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DeviceSimEquipAssnDetailsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="billSysId" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="deviceIdSim" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceIdTypeEquip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceIdTypeSim" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeviceSimEquipAssnDetailsType", propOrder = {
    "billSysId",
    "deviceIdSim",
    "deviceIdTypeEquip",
    "deviceIdTypeSim"
})
public class DeviceSimEquipAssnDetailsType {

    protected BigInteger billSysId;
    protected String deviceIdSim;
    protected String deviceIdTypeEquip;
    protected String deviceIdTypeSim;

    /**
     * Gets the value of the billSysId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBillSysId() {
        return billSysId;
    }

    /**
     * Sets the value of the billSysId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBillSysId(BigInteger value) {
        this.billSysId = value;
    }

    /**
     * Gets the value of the deviceIdSim property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceIdSim() {
        return deviceIdSim;
    }

    /**
     * Sets the value of the deviceIdSim property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceIdSim(String value) {
        this.deviceIdSim = value;
    }

    /**
     * Gets the value of the deviceIdTypeEquip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceIdTypeEquip() {
        return deviceIdTypeEquip;
    }

    /**
     * Sets the value of the deviceIdTypeEquip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceIdTypeEquip(String value) {
        this.deviceIdTypeEquip = value;
    }

    /**
     * Gets the value of the deviceIdTypeSim property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceIdTypeSim() {
        return deviceIdTypeSim;
    }

    /**
     * Sets the value of the deviceIdTypeSim property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceIdTypeSim(String value) {
        this.deviceIdTypeSim = value;
    }

}