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
 * <p>Java class for AuthUserMaintDetailsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AuthUserMaintDetailsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="authContactTyp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="authEndDt" type="{http://www.vzw.com/namespaces/scmplus}Date" minOccurs="0"/>
 *         &lt;element name="authFirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="authLastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createTmstamp" type="{http://www.vzw.com/namespaces/scmplus}DateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthUserMaintDetailsType", propOrder = {
    "authContactTyp",
    "authEndDt",
    "authFirstName",
    "authLastName",
    "createTmstamp"
})
public class AuthUserMaintDetailsType {

    protected String authContactTyp;
    protected String authEndDt;
    protected String authFirstName;
    protected String authLastName;
    protected String createTmstamp;

    /**
     * Gets the value of the authContactTyp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthContactTyp() {
        return authContactTyp;
    }

    /**
     * Sets the value of the authContactTyp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthContactTyp(String value) {
        this.authContactTyp = value;
    }

    /**
     * Gets the value of the authEndDt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthEndDt() {
        return authEndDt;
    }

    /**
     * Sets the value of the authEndDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthEndDt(String value) {
        this.authEndDt = value;
    }

    /**
     * Gets the value of the authFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthFirstName() {
        return authFirstName;
    }

    /**
     * Sets the value of the authFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthFirstName(String value) {
        this.authFirstName = value;
    }

    /**
     * Gets the value of the authLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthLastName() {
        return authLastName;
    }

    /**
     * Sets the value of the authLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthLastName(String value) {
        this.authLastName = value;
    }

    /**
     * Gets the value of the createTmstamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateTmstamp() {
        return createTmstamp;
    }

    /**
     * Sets the value of the createTmstamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateTmstamp(String value) {
        this.createTmstamp = value;
    }

}