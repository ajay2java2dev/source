//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.09.13 at 01:37:28 PM EDT 
//


package com.vzwcorp.pricinglab.vo.dvs.nbs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}lineValidationForOrderService_validateProcessForCreateOrder" minOccurs="0"/>
 *         &lt;element ref="{}retrieveProduct_byCorrelationID" minOccurs="0"/>
 *         &lt;element ref="{}billProrateCalc_NONE" minOccurs="0"/>
 *         &lt;element name="suppressedTransactionTypeList" type="{}SuppressedTransactionTypeList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "lineValidationForOrderServiceValidateProcessForCreateOrder",
    "retrieveProductByCorrelationID",
    "billProrateCalcNONE",
    "suppressedTransactionTypeList"
})
@XmlRootElement(name = "serviceResponse")
public class ServiceResponse {

    @XmlElement(name = "lineValidationForOrderService_validateProcessForCreateOrder")
    protected LineValidationForOrderServiceValidateProcessForCreateOrder lineValidationForOrderServiceValidateProcessForCreateOrder;
    @XmlElement(name = "retrieveProduct_byCorrelationID")
    protected RetrieveProductByCorrelationID retrieveProductByCorrelationID;
    @XmlElement(name = "billProrateCalc_NONE")
    protected BillProrateCalcNONE billProrateCalcNONE;
    protected SuppressedTransactionTypeList suppressedTransactionTypeList;

    /**
     * Gets the value of the lineValidationForOrderServiceValidateProcessForCreateOrder property.
     * 
     * @return
     *     possible object is
     *     {@link LineValidationForOrderServiceValidateProcessForCreateOrder }
     *     
     */
    public LineValidationForOrderServiceValidateProcessForCreateOrder getLineValidationForOrderServiceValidateProcessForCreateOrder() {
        return lineValidationForOrderServiceValidateProcessForCreateOrder;
    }

    /**
     * Sets the value of the lineValidationForOrderServiceValidateProcessForCreateOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link LineValidationForOrderServiceValidateProcessForCreateOrder }
     *     
     */
    public void setLineValidationForOrderServiceValidateProcessForCreateOrder(LineValidationForOrderServiceValidateProcessForCreateOrder value) {
        this.lineValidationForOrderServiceValidateProcessForCreateOrder = value;
    }

    /**
     * Gets the value of the retrieveProductByCorrelationID property.
     * 
     * @return
     *     possible object is
     *     {@link RetrieveProductByCorrelationID }
     *     
     */
    public RetrieveProductByCorrelationID getRetrieveProductByCorrelationID() {
        return retrieveProductByCorrelationID;
    }

    /**
     * Sets the value of the retrieveProductByCorrelationID property.
     * 
     * @param value
     *     allowed object is
     *     {@link RetrieveProductByCorrelationID }
     *     
     */
    public void setRetrieveProductByCorrelationID(RetrieveProductByCorrelationID value) {
        this.retrieveProductByCorrelationID = value;
    }

    /**
     * Gets the value of the billProrateCalcNONE property.
     * 
     * @return
     *     possible object is
     *     {@link BillProrateCalcNONE }
     *     
     */
    public BillProrateCalcNONE getBillProrateCalcNONE() {
        return billProrateCalcNONE;
    }

    /**
     * Sets the value of the billProrateCalcNONE property.
     * 
     * @param value
     *     allowed object is
     *     {@link BillProrateCalcNONE }
     *     
     */
    public void setBillProrateCalcNONE(BillProrateCalcNONE value) {
        this.billProrateCalcNONE = value;
    }

    /**
     * Gets the value of the suppressedTransactionTypeList property.
     * 
     * @return
     *     possible object is
     *     {@link SuppressedTransactionTypeList }
     *     
     */
    public SuppressedTransactionTypeList getSuppressedTransactionTypeList() {
        return suppressedTransactionTypeList;
    }

    /**
     * Sets the value of the suppressedTransactionTypeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SuppressedTransactionTypeList }
     *     
     */
    public void setSuppressedTransactionTypeList(SuppressedTransactionTypeList value) {
        this.suppressedTransactionTypeList = value;
    }

}