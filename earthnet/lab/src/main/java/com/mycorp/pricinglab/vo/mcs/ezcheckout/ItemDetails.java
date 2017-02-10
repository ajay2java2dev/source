package com.vzwcorp.pricinglab.vo.mcs.ezcheckout;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "itemID",
    "vendorID",
    "contentID",
    "contentType",
    "contentVendorID",
    "pricePlanPackage",
    "serviceID",
    "purchaseName",
    "vendorSKU",
    "contentSize"
})
public  class ItemDetails {

    @XmlElement(name = "ItemID")
    protected String itemID;
    @XmlElement(name = "VendorID")
    protected String vendorID;
    @XmlElement(name = "ContentID")
    protected String contentID;
    @XmlElement(name = "ContentType")
    protected String contentType;
    @XmlElement(name = "ContentVendorID")
    protected String contentVendorID;
    @XmlElement(name = "PricePlanPackage")
    protected PricePlanPackageType pricePlanPackage;
    @XmlElement(name = "ServiceID")
    protected String serviceID;
    @XmlElement(name = "PurchaseName")
    protected String purchaseName;
    @XmlElement(name = "VendorSKU")
    protected String vendorSKU;
    @XmlElement(name = "ContentSize")
    protected Long contentSize;

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
     * Gets the value of the vendorID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVendorID() {
        return vendorID;
    }

    /**
     * Sets the value of the vendorID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVendorID(String value) {
        this.vendorID = value;
    }

    /**
     * Gets the value of the contentID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentID() {
        return contentID;
    }

    /**
     * Sets the value of the contentID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentID(String value) {
        this.contentID = value;
    }

    /**
     * Gets the value of the contentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Sets the value of the contentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentType(String value) {
        this.contentType = value;
    }

    /**
     * Gets the value of the contentVendorID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentVendorID() {
        return contentVendorID;
    }

    /**
     * Sets the value of the contentVendorID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentVendorID(String value) {
        this.contentVendorID = value;
    }

    /**
     * Gets the value of the pricePlanPackage property.
     * 
     * @return
     *     possible object is
     *     {@link PricePlanPackageType }
     *     
     */
    public PricePlanPackageType getPricePlanPackage() {
        return pricePlanPackage;
    }

    /**
     * Sets the value of the pricePlanPackage property.
     * 
     * @param value
     *     allowed object is
     *     {@link PricePlanPackageType }
     *     
     */
    public void setPricePlanPackage(PricePlanPackageType value) {
        this.pricePlanPackage = value;
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
     * Gets the value of the vendorSKU property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVendorSKU() {
        return vendorSKU;
    }

    /**
     * Sets the value of the vendorSKU property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVendorSKU(String value) {
        this.vendorSKU = value;
    }

    /**
     * Gets the value of the contentSize property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getContentSize() {
        return contentSize;
    }

    /**
     * Sets the value of the contentSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setContentSize(Long value) {
        this.contentSize = value;
    }

}


