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
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element name="Node" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;choice>
 *                     &lt;element ref="{http://www.vzw.com/namespaces/scmplus}UsageDate"/>
 *                     &lt;element ref="{http://www.vzw.com/namespaces/scmplus}PurchaseName"/>
 *                     &lt;element ref="{http://www.vzw.com/namespaces/scmplus}Mdn"/>
 *                   &lt;/choice>
 *                   &lt;element ref="{http://www.vzw.com/namespaces/scmplus}RatingType" minOccurs="0"/>
 *                   &lt;element ref="{http://www.vzw.com/namespaces/scmplus}FulfillmentID" minOccurs="0"/>
 *                   &lt;element ref="{http://www.vzw.com/namespaces/scmplus}ShortDescription" minOccurs="0"/>
 *                   &lt;element ref="{http://www.vzw.com/namespaces/scmplus}ServiceID" minOccurs="0"/>
 *                   &lt;sequence>
 *                     &lt;element ref="{http://www.vzw.com/namespaces/scmplus}UnitInfo" maxOccurs="unbounded"/>
 *                   &lt;/sequence>
 *                 &lt;/sequence>
 *                 &lt;attribute name="Seq" use="required" type="{http://www.w3.org/2001/XMLSchema}long" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="Seq" use="required" type="{http://www.w3.org/2001/XMLSchema}long" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "node"
})
@XmlRootElement(name = "UsageGroup")
public class UsageGroup {

    @XmlElement(name = "Node")
    protected List<UsageGroup.Node> node;
    @XmlAttribute(name = "Seq", required = true)
    protected long seq;

    /**
     * Gets the value of the node property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the node property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UsageGroup.Node }
     * 
     * 
     */
    public List<UsageGroup.Node> getNode() {
        if (node == null) {
            node = new ArrayList<UsageGroup.Node>();
        }
        return this.node;
    }

    /**
     * Gets the value of the seq property.
     * 
     */
    public long getSeq() {
        return seq;
    }

    /**
     * Sets the value of the seq property.
     * 
     */
    public void setSeq(long value) {
        this.seq = value;
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
     *         &lt;choice>
     *           &lt;element ref="{http://www.vzw.com/namespaces/scmplus}UsageDate"/>
     *           &lt;element ref="{http://www.vzw.com/namespaces/scmplus}PurchaseName"/>
     *           &lt;element ref="{http://www.vzw.com/namespaces/scmplus}Mdn"/>
     *         &lt;/choice>
     *         &lt;element ref="{http://www.vzw.com/namespaces/scmplus}RatingType" minOccurs="0"/>
     *         &lt;element ref="{http://www.vzw.com/namespaces/scmplus}FulfillmentID" minOccurs="0"/>
     *         &lt;element ref="{http://www.vzw.com/namespaces/scmplus}ShortDescription" minOccurs="0"/>
     *         &lt;element ref="{http://www.vzw.com/namespaces/scmplus}ServiceID" minOccurs="0"/>
     *         &lt;sequence>
     *           &lt;element ref="{http://www.vzw.com/namespaces/scmplus}UnitInfo" maxOccurs="unbounded"/>
     *         &lt;/sequence>
     *       &lt;/sequence>
     *       &lt;attribute name="Seq" use="required" type="{http://www.w3.org/2001/XMLSchema}long" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "usageDate",
        "purchaseName",
        "mdn",
        "ratingType",
        "fulfillmentID",
        "shortDescription",
        "serviceID",
        "unitInfo"
    })
    public static class Node {

        @XmlElement(name = "UsageDate")
        protected String usageDate;
        @XmlElement(name = "PurchaseName")
        protected String purchaseName;
        @XmlElement(name = "Mdn")
        protected Long mdn;
        @XmlElement(name = "RatingType")
        protected String ratingType;
        @XmlElement(name = "FulfillmentID")
        protected String fulfillmentID;
        @XmlElement(name = "ShortDescription")
        protected String shortDescription;
        @XmlElement(name = "ServiceID")
        protected String serviceID;
        @XmlElement(name = "UnitInfo", required = true)
        protected List<UnitInfo> unitInfo;
        @XmlAttribute(name = "Seq", required = true)
        protected long seq;

        /**
         * Gets the value of the usageDate property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUsageDate() {
            return usageDate;
        }

        /**
         * Sets the value of the usageDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUsageDate(String value) {
            this.usageDate = value;
        }

        /**
         * Gets the value of the purchaseName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPurchaseName() {
            return purchaseName;
        }

        /**
         * Sets the value of the purchaseName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPurchaseName(String value) {
            this.purchaseName = value;
        }

        /**
         * Gets the value of the mdn property.
         * 
         * @return
         *     possible object is
         *     {@link Long }
         *     
         */
        public Long getMdn() {
            return mdn;
        }

        /**
         * Sets the value of the mdn property.
         * 
         * @param value
         *     allowed object is
         *     {@link Long }
         *     
         */
        public void setMdn(Long value) {
            this.mdn = value;
        }

        /**
         * Gets the value of the ratingType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRatingType() {
            return ratingType;
        }

        /**
         * Sets the value of the ratingType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRatingType(String value) {
            this.ratingType = value;
        }

        /**
         * Gets the value of the fulfillmentID property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFulfillmentID() {
            return fulfillmentID;
        }

        /**
         * Sets the value of the fulfillmentID property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFulfillmentID(String value) {
            this.fulfillmentID = value;
        }

        /**
         * Gets the value of the shortDescription property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getShortDescription() {
            return shortDescription;
        }

        /**
         * Sets the value of the shortDescription property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setShortDescription(String value) {
            this.shortDescription = value;
        }

        /**
         * Gets the value of the serviceID property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getServiceID() {
            return serviceID;
        }

        /**
         * Sets the value of the serviceID property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setServiceID(String value) {
            this.serviceID = value;
        }

        /**
         * Gets the value of the unitInfo property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the unitInfo property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getUnitInfo().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link UnitInfo }
         * 
         * 
         */
        public List<UnitInfo> getUnitInfo() {
            if (unitInfo == null) {
                unitInfo = new ArrayList<UnitInfo>();
            }
            return this.unitInfo;
        }

        /**
         * Gets the value of the seq property.
         * 
         */
        public long getSeq() {
            return seq;
        }

        /**
         * Sets the value of the seq property.
         * 
         */
        public void setSeq(long value) {
            this.seq = value;
        }

    }

}