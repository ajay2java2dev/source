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
 * <p>Java class for PortInDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PortInDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="portInInfo" type="{}PortInInfo" minOccurs="0"/>
 *         &lt;element name="billName" type="{}Name" minOccurs="0"/>
 *         &lt;element name="billAddress" type="{}Address" minOccurs="0"/>
 *         &lt;element name="lineItemInfo" type="{}LineItemInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PortInDetail", propOrder = {
    "portInInfo",
    "billName",
    "billAddress",
    "lineItemInfo"
})
public class PortInDetail {

    protected PortInInfo portInInfo;
    protected Name billName;
    protected Address billAddress;
    protected LineItemInfo lineItemInfo;

    /**
     * Gets the value of the portInInfo property.
     * 
     * @return
     *     possible object is
     *     {@link PortInInfo }
     *     
     */
    public PortInInfo getPortInInfo() {
        return portInInfo;
    }

    /**
     * Sets the value of the portInInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PortInInfo }
     *     
     */
    public void setPortInInfo(PortInInfo value) {
        this.portInInfo = value;
    }

    /**
     * Gets the value of the billName property.
     * 
     * @return
     *     possible object is
     *     {@link Name }
     *     
     */
    public Name getBillName() {
        return billName;
    }

    /**
     * Sets the value of the billName property.
     * 
     * @param value
     *     allowed object is
     *     {@link Name }
     *     
     */
    public void setBillName(Name value) {
        this.billName = value;
    }

    /**
     * Gets the value of the billAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getBillAddress() {
        return billAddress;
    }

    /**
     * Sets the value of the billAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setBillAddress(Address value) {
        this.billAddress = value;
    }

    /**
     * Gets the value of the lineItemInfo property.
     * 
     * @return
     *     possible object is
     *     {@link LineItemInfo }
     *     
     */
    public LineItemInfo getLineItemInfo() {
        return lineItemInfo;
    }

    /**
     * Sets the value of the lineItemInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link LineItemInfo }
     *     
     */
    public void setLineItemInfo(LineItemInfo value) {
        this.lineItemInfo = value;
    }

}