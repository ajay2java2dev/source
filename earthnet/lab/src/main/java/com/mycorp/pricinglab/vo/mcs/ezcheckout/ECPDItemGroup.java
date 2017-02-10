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
 *         &lt;element name="ItemList">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://www.vzw.com/namespaces/scmplus}ItemID" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{http://www.vzw.com/namespaces/scmplus}Action"/>
 *         &lt;choice>
 *           &lt;sequence>
 *             &lt;element ref="{http://www.vzw.com/namespaces/scmplus}AllAccounts" minOccurs="0"/>
 *             &lt;element ref="{http://www.vzw.com/namespaces/scmplus}AllMdns" minOccurs="0"/>
 *             &lt;element ref="{http://www.vzw.com/namespaces/scmplus}MdnAutoAssign" minOccurs="0"/>
 *           &lt;/sequence>
 *           &lt;sequence>
 *             &lt;element name="AccountList" minOccurs="0">
 *               &lt;complexType>
 *                 &lt;complexContent>
 *                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                     &lt;sequence>
 *                       &lt;element ref="{http://www.vzw.com/namespaces/scmplus}Account" maxOccurs="unbounded"/>
 *                     &lt;/sequence>
 *                   &lt;/restriction>
 *                 &lt;/complexContent>
 *               &lt;/complexType>
 *             &lt;/element>
 *             &lt;element name="MdnList" minOccurs="0">
 *               &lt;complexType>
 *                 &lt;complexContent>
 *                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                     &lt;sequence>
 *                       &lt;element ref="{http://www.vzw.com/namespaces/scmplus}Mdn" maxOccurs="unbounded"/>
 *                     &lt;/sequence>
 *                   &lt;/restriction>
 *                 &lt;/complexContent>
 *               &lt;/complexType>
 *             &lt;/element>
 *           &lt;/sequence>
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
@XmlType(name = "", propOrder = {
    "itemList",
    "action",
    "allAccounts",
    "allMdns",
    "mdnAutoAssign",
    "accountList",
    "mdnList"
})
@XmlRootElement(name = "ECPDItemGroup")
public class ECPDItemGroup {

    @XmlElement(name = "ItemList", required = true)
    protected ECPDItemGroup.ItemList itemList;
    @XmlElement(name = "Action", required = true)
    protected String action;
    @XmlElement(name = "AllAccounts")
    protected Boolean allAccounts;
    @XmlElement(name = "AllMdns")
    protected Boolean allMdns;
    @XmlElement(name = "MdnAutoAssign")
    protected Boolean mdnAutoAssign;
    @XmlElement(name = "AccountList")
    protected ECPDItemGroup.AccountList accountList;
    @XmlElement(name = "MdnList")
    protected ECPDItemGroup.MdnList mdnList;

    /**
     * Gets the value of the itemList property.
     * 
     * @return
     *     possible object is
     *     {@link ECPDItemGroup.ItemList }
     *     
     */
    public ECPDItemGroup.ItemList getItemList() {
        return itemList;
    }

    /**
     * Sets the value of the itemList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ECPDItemGroup.ItemList }
     *     
     */
    public void setItemList(ECPDItemGroup.ItemList value) {
        this.itemList = value;
    }

    /**
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAction(String value) {
        this.action = value;
    }

    /**
     * Gets the value of the allAccounts property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAllAccounts() {
        return allAccounts;
    }

    /**
     * Sets the value of the allAccounts property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllAccounts(Boolean value) {
        this.allAccounts = value;
    }

    /**
     * Gets the value of the allMdns property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAllMdns() {
        return allMdns;
    }

    /**
     * Sets the value of the allMdns property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllMdns(Boolean value) {
        this.allMdns = value;
    }

    /**
     * Gets the value of the mdnAutoAssign property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMdnAutoAssign() {
        return mdnAutoAssign;
    }

    /**
     * Sets the value of the mdnAutoAssign property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMdnAutoAssign(Boolean value) {
        this.mdnAutoAssign = value;
    }

    /**
     * Gets the value of the accountList property.
     * 
     * @return
     *     possible object is
     *     {@link ECPDItemGroup.AccountList }
     *     
     */
    public ECPDItemGroup.AccountList getAccountList() {
        return accountList;
    }

    /**
     * Sets the value of the accountList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ECPDItemGroup.AccountList }
     *     
     */
    public void setAccountList(ECPDItemGroup.AccountList value) {
        this.accountList = value;
    }

    /**
     * Gets the value of the mdnList property.
     * 
     * @return
     *     possible object is
     *     {@link ECPDItemGroup.MdnList }
     *     
     */
    public ECPDItemGroup.MdnList getMdnList() {
        return mdnList;
    }

    /**
     * Sets the value of the mdnList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ECPDItemGroup.MdnList }
     *     
     */
    public void setMdnList(ECPDItemGroup.MdnList value) {
        this.mdnList = value;
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
     *         &lt;element ref="{http://www.vzw.com/namespaces/scmplus}Account" maxOccurs="unbounded"/>
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
        "account"
    })
    public static class AccountList {

        @XmlElement(name = "Account", required = true)
        protected List<Account> account;

        /**
         * Gets the value of the account property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the account property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAccount().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Account }
         * 
         * 
         */
        public List<Account> getAccount() {
            if (account == null) {
                account = new ArrayList<Account>();
            }
            return this.account;
        }

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
     *         &lt;element ref="{http://www.vzw.com/namespaces/scmplus}ItemID" maxOccurs="unbounded"/>
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
        "itemID"
    })
    public static class ItemList {

        @XmlElement(name = "ItemID", required = true)
        protected List<String> itemID;

        /**
         * Gets the value of the itemID property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the itemID property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getItemID().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getItemID() {
            if (itemID == null) {
                itemID = new ArrayList<String>();
            }
            return this.itemID;
        }

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
     *         &lt;element ref="{http://www.vzw.com/namespaces/scmplus}Mdn" maxOccurs="unbounded"/>
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
        "mdn"
    })
    public static class MdnList {

        @XmlElement(name = "Mdn", type = Long.class)
        protected List<Long> mdn;

        /**
         * Gets the value of the mdn property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the mdn property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getMdn().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Long }
         * 
         * 
         */
        public List<Long> getMdn() {
            if (mdn == null) {
                mdn = new ArrayList<Long>();
            }
            return this.mdn;
        }

    }

}