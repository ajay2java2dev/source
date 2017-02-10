//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.09.13 at 01:37:28 PM EDT 
//


package com.vzwcorp.pricinglab.vo.dvs.nbs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for productItems complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="productItems">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productCorrelationId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lineOfServiceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productOfferCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{}listOfProductOffers" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "productItems", propOrder = {
    "productCorrelationId",
    "customerId",
    "accountNumber",
    "lineOfServiceId",
    "productOfferCount",
    "listOfProductOffers"
})
public class ProductItems {

    protected String productCorrelationId;
    protected String customerId;
    protected String accountNumber;
    protected String lineOfServiceId;
    protected String productOfferCount;
    protected ListOfProductOffers listOfProductOffers;

    /**
     * Gets the value of the productCorrelationId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCorrelationId() {
        return productCorrelationId;
    }

    /**
     * Sets the value of the productCorrelationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCorrelationId(String value) {
        this.productCorrelationId = value;
    }

    /**
     * Gets the value of the customerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Sets the value of the customerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerId(String value) {
        this.customerId = value;
    }

    /**
     * Gets the value of the accountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the value of the accountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountNumber(String value) {
        this.accountNumber = value;
    }

    /**
     * Gets the value of the lineOfServiceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLineOfServiceId() {
        return lineOfServiceId;
    }

    /**
     * Sets the value of the lineOfServiceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLineOfServiceId(String value) {
        this.lineOfServiceId = value;
    }

    /**
     * Gets the value of the productOfferCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductOfferCount() {
        return productOfferCount;
    }

    /**
     * Sets the value of the productOfferCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductOfferCount(String value) {
        this.productOfferCount = value;
    }

    /**
     * Gets the value of the listOfProductOffers property.
     * 
     * @return
     *     possible object is
     *     {@link ListOfProductOffers }
     *     
     */
    public ListOfProductOffers getListOfProductOffers() {
        return listOfProductOffers;
    }

    /**
     * Sets the value of the listOfProductOffers property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfProductOffers }
     *     
     */
    public void setListOfProductOffers(ListOfProductOffers value) {
        this.listOfProductOffers = value;
    }

}