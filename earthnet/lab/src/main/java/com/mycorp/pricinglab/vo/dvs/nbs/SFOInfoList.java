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
 * <p>Java class for SFOInfoList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SFOInfoList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sfoInfo" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="sfoGridIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="sfoId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="sfoDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="sfoPrice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="sfoPromotion1Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="sfoPromotion1Discount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="sfoPromotion2Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="sfoPromotion2Discount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="sfoPromotion3Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="sfoPromotion3Discount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="sfoNetPrice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="sfoProratedTotalPrice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="sfoProratedInfoItemList" type="{}ProratedInfoItemList" minOccurs="0"/>
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
@XmlType(name = "SFOInfoList", propOrder = {
    "sfoInfo"
})
public class SFOInfoList {

    protected List<SFOInfoList.SfoInfo> sfoInfo;

    /**
     * Gets the value of the sfoInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sfoInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSfoInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SFOInfoList.SfoInfo }
     * 
     * 
     */
    public List<SFOInfoList.SfoInfo> getSfoInfo() {
        if (sfoInfo == null) {
            sfoInfo = new ArrayList<SFOInfoList.SfoInfo>();
        }
        return this.sfoInfo;
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
     *         &lt;element name="sfoGridIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="sfoId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="sfoDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="sfoPrice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="sfoPromotion1Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="sfoPromotion1Discount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="sfoPromotion2Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="sfoPromotion2Discount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="sfoPromotion3Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="sfoPromotion3Discount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="sfoNetPrice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="sfoProratedTotalPrice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="sfoProratedInfoItemList" type="{}ProratedInfoItemList" minOccurs="0"/>
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
        "sfoGridIndicator",
        "sfoId",
        "sfoDescription",
        "sfoPrice",
        "sfoPromotion1Description",
        "sfoPromotion1Discount",
        "sfoPromotion2Description",
        "sfoPromotion2Discount",
        "sfoPromotion3Description",
        "sfoPromotion3Discount",
        "sfoNetPrice",
        "sfoProratedTotalPrice",
        "sfoProratedInfoItemList"
    })
    public static class SfoInfo {

        protected String sfoGridIndicator;
        protected String sfoId;
        protected String sfoDescription;
        protected String sfoPrice;
        protected String sfoPromotion1Description;
        protected String sfoPromotion1Discount;
        protected String sfoPromotion2Description;
        protected String sfoPromotion2Discount;
        protected String sfoPromotion3Description;
        protected String sfoPromotion3Discount;
        protected String sfoNetPrice;
        protected String sfoProratedTotalPrice;
        protected ProratedInfoItemList sfoProratedInfoItemList;

        /**
         * Gets the value of the sfoGridIndicator property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSfoGridIndicator() {
            return sfoGridIndicator;
        }

        /**
         * Sets the value of the sfoGridIndicator property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSfoGridIndicator(String value) {
            this.sfoGridIndicator = value;
        }

        /**
         * Gets the value of the sfoId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSfoId() {
            return sfoId;
        }

        /**
         * Sets the value of the sfoId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSfoId(String value) {
            this.sfoId = value;
        }

        /**
         * Gets the value of the sfoDescription property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSfoDescription() {
            return sfoDescription;
        }

        /**
         * Sets the value of the sfoDescription property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSfoDescription(String value) {
            this.sfoDescription = value;
        }

        /**
         * Gets the value of the sfoPrice property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSfoPrice() {
            return sfoPrice;
        }

        /**
         * Sets the value of the sfoPrice property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSfoPrice(String value) {
            this.sfoPrice = value;
        }

        /**
         * Gets the value of the sfoPromotion1Description property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSfoPromotion1Description() {
            return sfoPromotion1Description;
        }

        /**
         * Sets the value of the sfoPromotion1Description property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSfoPromotion1Description(String value) {
            this.sfoPromotion1Description = value;
        }

        /**
         * Gets the value of the sfoPromotion1Discount property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSfoPromotion1Discount() {
            return sfoPromotion1Discount;
        }

        /**
         * Sets the value of the sfoPromotion1Discount property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSfoPromotion1Discount(String value) {
            this.sfoPromotion1Discount = value;
        }

        /**
         * Gets the value of the sfoPromotion2Description property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSfoPromotion2Description() {
            return sfoPromotion2Description;
        }

        /**
         * Sets the value of the sfoPromotion2Description property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSfoPromotion2Description(String value) {
            this.sfoPromotion2Description = value;
        }

        /**
         * Gets the value of the sfoPromotion2Discount property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSfoPromotion2Discount() {
            return sfoPromotion2Discount;
        }

        /**
         * Sets the value of the sfoPromotion2Discount property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSfoPromotion2Discount(String value) {
            this.sfoPromotion2Discount = value;
        }

        /**
         * Gets the value of the sfoPromotion3Description property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSfoPromotion3Description() {
            return sfoPromotion3Description;
        }

        /**
         * Sets the value of the sfoPromotion3Description property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSfoPromotion3Description(String value) {
            this.sfoPromotion3Description = value;
        }

        /**
         * Gets the value of the sfoPromotion3Discount property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSfoPromotion3Discount() {
            return sfoPromotion3Discount;
        }

        /**
         * Sets the value of the sfoPromotion3Discount property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSfoPromotion3Discount(String value) {
            this.sfoPromotion3Discount = value;
        }

        /**
         * Gets the value of the sfoNetPrice property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSfoNetPrice() {
            return sfoNetPrice;
        }

        /**
         * Sets the value of the sfoNetPrice property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSfoNetPrice(String value) {
            this.sfoNetPrice = value;
        }

        /**
         * Gets the value of the sfoProratedTotalPrice property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSfoProratedTotalPrice() {
            return sfoProratedTotalPrice;
        }

        /**
         * Sets the value of the sfoProratedTotalPrice property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSfoProratedTotalPrice(String value) {
            this.sfoProratedTotalPrice = value;
        }

        /**
         * Gets the value of the sfoProratedInfoItemList property.
         * 
         * @return
         *     possible object is
         *     {@link ProratedInfoItemList }
         *     
         */
        public ProratedInfoItemList getSfoProratedInfoItemList() {
            return sfoProratedInfoItemList;
        }

        /**
         * Sets the value of the sfoProratedInfoItemList property.
         * 
         * @param value
         *     allowed object is
         *     {@link ProratedInfoItemList }
         *     
         */
        public void setSfoProratedInfoItemList(ProratedInfoItemList value) {
            this.sfoProratedInfoItemList = value;
        }

    }

}