//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.31 at 10:31:22 AM EDT 
//


package com.vzwcorp.pricinglab.vo.mcs.ezcheckout;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CustActvMtnHistDetailsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustActvMtnHistDetailsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="actvMtnEffDt" type="{http://www.vzw.com/namespaces/scmplus}Date" minOccurs="0"/>
 *         &lt;element name="actvMtnEndDt" type="{http://www.vzw.com/namespaces/scmplus}Date" minOccurs="0"/>
 *         &lt;element name="actvMtnhistQty" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustActvMtnHistDetailsType", propOrder = {
    "actvMtnEffDt",
    "actvMtnEndDt",
    "actvMtnhistQty"
})
public class CustActvMtnHistDetailsType {

    protected String actvMtnEffDt;
    protected String actvMtnEndDt;
    protected Short actvMtnhistQty;

    /**
     * Gets the value of the actvMtnEffDt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActvMtnEffDt() {
        return actvMtnEffDt;
    }

    /**
     * Sets the value of the actvMtnEffDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActvMtnEffDt(String value) {
        this.actvMtnEffDt = value;
    }

    /**
     * Gets the value of the actvMtnEndDt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActvMtnEndDt() {
        return actvMtnEndDt;
    }

    /**
     * Sets the value of the actvMtnEndDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActvMtnEndDt(String value) {
        this.actvMtnEndDt = value;
    }

    /**
     * Gets the value of the actvMtnhistQty property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getActvMtnhistQty() {
        return actvMtnhistQty;
    }

    /**
     * Sets the value of the actvMtnhistQty property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setActvMtnhistQty(Short value) {
        this.actvMtnhistQty = value;
    }

}