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
 * <p>Java class for CustBillDetailsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustBillDetailsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="blCycNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="blTotCurChg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="blTotRemitAmt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cycMthYr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="invoiceNo" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustBillDetailsType", propOrder = {
    "blCycNo",
    "blTotCurChg",
    "blTotRemitAmt",
    "cycMthYr",
    "invoiceNo"
})
public class CustBillDetailsType {

    protected String blCycNo;
    protected String blTotCurChg;
    protected String blTotRemitAmt;
    protected String cycMthYr;
    protected BigInteger invoiceNo;

    /**
     * Gets the value of the blCycNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBlCycNo() {
        return blCycNo;
    }

    /**
     * Sets the value of the blCycNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBlCycNo(String value) {
        this.blCycNo = value;
    }

    /**
     * Gets the value of the blTotCurChg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBlTotCurChg() {
        return blTotCurChg;
    }

    /**
     * Sets the value of the blTotCurChg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBlTotCurChg(String value) {
        this.blTotCurChg = value;
    }

    /**
     * Gets the value of the blTotRemitAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBlTotRemitAmt() {
        return blTotRemitAmt;
    }

    /**
     * Sets the value of the blTotRemitAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBlTotRemitAmt(String value) {
        this.blTotRemitAmt = value;
    }

    /**
     * Gets the value of the cycMthYr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCycMthYr() {
        return cycMthYr;
    }

    /**
     * Sets the value of the cycMthYr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCycMthYr(String value) {
        this.cycMthYr = value;
    }

    /**
     * Gets the value of the invoiceNo property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getInvoiceNo() {
        return invoiceNo;
    }

    /**
     * Sets the value of the invoiceNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setInvoiceNo(BigInteger value) {
        this.invoiceNo = value;
    }

}