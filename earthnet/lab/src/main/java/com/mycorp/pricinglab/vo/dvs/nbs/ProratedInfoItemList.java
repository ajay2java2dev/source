//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.09.13 at 01:37:28 PM EDT 
//


package com.vzwcorp.pricinglab.vo.dvs.nbs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProratedInfoItemList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProratedInfoItemList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="proratedInfoItem" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="prorateAdvancedIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="prorateEffectiveDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="prorateEndDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="proratePrice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="proratePromotion1Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="proratePromotion1Discount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="proratePromotion2Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="proratePromotion2Discount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="proratePromotion3Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="proratePromotion3Discount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="prorateNetPrice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element ref="{}periodIndicator" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProratedInfoItemList", propOrder = {
    "proratedInfoItem"
})
public class ProratedInfoItemList {

    protected List<ProratedInfoItemList.ProratedInfoItem> proratedInfoItem;

    /**
     * Gets the value of the proratedInfoItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the proratedInfoItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProratedInfoItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProratedInfoItemList.ProratedInfoItem }
     * 
     * 
     */
    public List<ProratedInfoItemList.ProratedInfoItem> getProratedInfoItem() {
        if (proratedInfoItem == null) {
            proratedInfoItem = new ArrayList<ProratedInfoItemList.ProratedInfoItem>();
        }
        return this.proratedInfoItem;
    }


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
     *         &lt;element name="prorateAdvancedIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="prorateEffectiveDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="prorateEndDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="proratePrice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="proratePromotion1Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="proratePromotion1Discount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="proratePromotion2Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="proratePromotion2Discount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="proratePromotion3Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="proratePromotion3Discount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="prorateNetPrice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element ref="{}periodIndicator" minOccurs="0"/>
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
        "prorateAdvancedIndicator",
        "prorateEffectiveDate",
        "prorateEndDate",
        "proratePrice",
        "proratePromotion1Description",
        "proratePromotion1Discount",
        "proratePromotion2Description",
        "proratePromotion2Discount",
        "proratePromotion3Description",
        "proratePromotion3Discount",
        "prorateNetPrice",
        "periodIndicator"
    })
    public static class ProratedInfoItem {

        protected String prorateAdvancedIndicator;
        protected String prorateEffectiveDate;
        protected String prorateEndDate;
        protected String proratePrice;
        protected String proratePromotion1Description;
        protected String proratePromotion1Discount;
        protected String proratePromotion2Description;
        protected String proratePromotion2Discount;
        protected String proratePromotion3Description;
        protected String proratePromotion3Discount;
        protected String prorateNetPrice;
        protected String periodIndicator;

        /**
         * Gets the value of the prorateAdvancedIndicator property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProrateAdvancedIndicator() {
            return prorateAdvancedIndicator;
        }

        /**
         * Sets the value of the prorateAdvancedIndicator property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProrateAdvancedIndicator(String value) {
            this.prorateAdvancedIndicator = value;
        }

        /**
         * Gets the value of the prorateEffectiveDate property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProrateEffectiveDate() {
            return prorateEffectiveDate;
        }

        /**
         * Sets the value of the prorateEffectiveDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProrateEffectiveDate(String value) {
            this.prorateEffectiveDate = value;
        }

        /**
         * Gets the value of the prorateEndDate property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProrateEndDate() {
            return prorateEndDate;
        }

        /**
         * Sets the value of the prorateEndDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProrateEndDate(String value) {
            this.prorateEndDate = value;
        }

        /**
         * Gets the value of the proratePrice property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProratePrice() {
            return proratePrice;
        }

        /**
         * Sets the value of the proratePrice property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProratePrice(String value) {
            this.proratePrice = value;
        }

        /**
         * Gets the value of the proratePromotion1Description property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProratePromotion1Description() {
            return proratePromotion1Description;
        }

        /**
         * Sets the value of the proratePromotion1Description property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProratePromotion1Description(String value) {
            this.proratePromotion1Description = value;
        }

        /**
         * Gets the value of the proratePromotion1Discount property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProratePromotion1Discount() {
            return proratePromotion1Discount;
        }

        /**
         * Sets the value of the proratePromotion1Discount property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProratePromotion1Discount(String value) {
            this.proratePromotion1Discount = value;
        }

        /**
         * Gets the value of the proratePromotion2Description property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProratePromotion2Description() {
            return proratePromotion2Description;
        }

        /**
         * Sets the value of the proratePromotion2Description property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProratePromotion2Description(String value) {
            this.proratePromotion2Description = value;
        }

        /**
         * Gets the value of the proratePromotion2Discount property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProratePromotion2Discount() {
            return proratePromotion2Discount;
        }

        /**
         * Sets the value of the proratePromotion2Discount property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProratePromotion2Discount(String value) {
            this.proratePromotion2Discount = value;
        }

        /**
         * Gets the value of the proratePromotion3Description property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProratePromotion3Description() {
            return proratePromotion3Description;
        }

        /**
         * Sets the value of the proratePromotion3Description property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProratePromotion3Description(String value) {
            this.proratePromotion3Description = value;
        }

        /**
         * Gets the value of the proratePromotion3Discount property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProratePromotion3Discount() {
            return proratePromotion3Discount;
        }

        /**
         * Sets the value of the proratePromotion3Discount property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProratePromotion3Discount(String value) {
            this.proratePromotion3Discount = value;
        }

        /**
         * Gets the value of the prorateNetPrice property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProrateNetPrice() {
            return prorateNetPrice;
        }

        /**
         * Sets the value of the prorateNetPrice property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProrateNetPrice(String value) {
            this.prorateNetPrice = value;
        }

        /**
         * Gets the value of the periodIndicator property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPeriodIndicator() {
            return periodIndicator;
        }

        /**
         * Sets the value of the periodIndicator property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPeriodIndicator(String value) {
            this.periodIndicator = value;
        }

    }

}