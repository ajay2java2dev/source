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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * BL_ACCT_SPLAN_IDS-Type
 * 
 * <p>Java class for BL_ACCT_SPLAN_ID-Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BL_ACCT_SPLAN_ID-Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.vzw.com/namespaces/scmplus}SPLAN_ID"/>
 *         &lt;element ref="{http://www.vzw.com/namespaces/scmplus}SPlan_EffectiveDate"/>
 *         &lt;element ref="{http://www.vzw.com/namespaces/scmplus}SPlan_EndDate"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BL_ACCT_SPLAN_ID-Type", propOrder = {
    "splanid",
    "sPlanEffectiveDate",
    "sPlanEndDate"
})
public class BLACCTSPLANIDType {

    @XmlElement(name = "SPLAN_ID", required = true)
    protected String splanid;
    @XmlElement(name = "SPlan_EffectiveDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar sPlanEffectiveDate;
    @XmlElement(name = "SPlan_EndDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar sPlanEndDate;

    /**
     * Gets the value of the splanid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSPLANID() {
        return splanid;
    }

    /**
     * Sets the value of the splanid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSPLANID(String value) {
        this.splanid = value;
    }

    /**
     * Gets the value of the sPlanEffectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSPlanEffectiveDate() {
        return sPlanEffectiveDate;
    }

    /**
     * Sets the value of the sPlanEffectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSPlanEffectiveDate(XMLGregorianCalendar value) {
        this.sPlanEffectiveDate = value;
    }

    /**
     * Gets the value of the sPlanEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSPlanEndDate() {
        return sPlanEndDate;
    }

    /**
     * Sets the value of the sPlanEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSPlanEndDate(XMLGregorianCalendar value) {
        this.sPlanEndDate = value;
    }

}