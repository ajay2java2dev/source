//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.31 at 10:31:22 AM EDT 
//


package com.vzwcorp.pricinglab.vo.mcs.ezcheckout;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ItemDiscountSearchRespType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ItemDiscountSearchRespType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.vzw.com/namespaces/scmplus}ItemID"/>
 *         &lt;choice>
 *           &lt;element name="ItemDiscount" type="{http://www.vzw.com/namespaces/scmplus}DiscountRespType"/>
 *           &lt;element name="PPPDiscount" type="{http://www.vzw.com/namespaces/scmplus}PricePlanDiscountRespType" maxOccurs="unbounded"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ItemDiscountSearchRespType", propOrder = {
    "itemID",
    "itemDiscount",
    "pppDiscount"
})
public class ItemDiscountSearchRespType {

    @XmlElement(name = "ItemID", required = true)
    protected String itemID;
    @XmlElement(name = "ItemDiscount")
    protected DiscountRespType itemDiscount;
    @XmlElement(name = "PPPDiscount")
    protected List<PricePlanDiscountRespType> pppDiscount;

    /**
     * Gets the value of the itemID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemID() {
        return itemID;
    }

    /**
     * Sets the value of the itemID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemID(String value) {
        this.itemID = value;
    }

    /**
     * Gets the value of the itemDiscount property.
     * 
     * @return
     *     possible object is
     *     {@link DiscountRespType }
     *     
     */
    public DiscountRespType getItemDiscount() {
        return itemDiscount;
    }

    /**
     * Sets the value of the itemDiscount property.
     * 
     * @param value
     *     allowed object is
     *     {@link DiscountRespType }
     *     
     */
    public void setItemDiscount(DiscountRespType value) {
        this.itemDiscount = value;
    }

    /**
     * Gets the value of the pppDiscount property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pppDiscount property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPPPDiscount().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PricePlanDiscountRespType }
     * 
     * 
     */
    public List<PricePlanDiscountRespType> getPPPDiscount() {
        if (pppDiscount == null) {
            pppDiscount = new ArrayList<PricePlanDiscountRespType>();
        }
        return this.pppDiscount;
    }

}