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
 * <p>Java class for SplitBlRequestDetailsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SplitBlRequestDetailsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mdn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mtnEffDt" type="{http://www.vzw.com/namespaces/scmplus}Date" minOccurs="0"/>
 *         &lt;element name="reqStatEffTs" type="{http://www.vzw.com/namespaces/scmplus}DateTime" minOccurs="0"/>
 *         &lt;element name="sptBlReqCrtTs" type="{http://www.vzw.com/namespaces/scmplus}DateTime" minOccurs="0"/>
 *         &lt;element name="sptBlReqStatCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SplitBlRequestDetailsType", propOrder = {
    "mdn",
    "mtnEffDt",
    "reqStatEffTs",
    "sptBlReqCrtTs",
    "sptBlReqStatCd"
})
public class SplitBlRequestDetailsType {

    protected String mdn;
    protected String mtnEffDt;
    protected String reqStatEffTs;
    protected String sptBlReqCrtTs;
    protected String sptBlReqStatCd;

    /**
     * Gets the value of the mdn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMdn() {
        return mdn;
    }

    /**
     * Sets the value of the mdn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMdn(String value) {
        this.mdn = value;
    }

    /**
     * Gets the value of the mtnEffDt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMtnEffDt() {
        return mtnEffDt;
    }

    /**
     * Sets the value of the mtnEffDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMtnEffDt(String value) {
        this.mtnEffDt = value;
    }

    /**
     * Gets the value of the reqStatEffTs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReqStatEffTs() {
        return reqStatEffTs;
    }

    /**
     * Sets the value of the reqStatEffTs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReqStatEffTs(String value) {
        this.reqStatEffTs = value;
    }

    /**
     * Gets the value of the sptBlReqCrtTs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSptBlReqCrtTs() {
        return sptBlReqCrtTs;
    }

    /**
     * Sets the value of the sptBlReqCrtTs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSptBlReqCrtTs(String value) {
        this.sptBlReqCrtTs = value;
    }

    /**
     * Gets the value of the sptBlReqStatCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSptBlReqStatCd() {
        return sptBlReqStatCd;
    }

    /**
     * Sets the value of the sptBlReqStatCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSptBlReqStatCd(String value) {
        this.sptBlReqStatCd = value;
    }

}