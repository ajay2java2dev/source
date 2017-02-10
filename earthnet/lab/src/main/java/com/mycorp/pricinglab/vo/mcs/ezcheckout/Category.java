package com.vzwcorp.pricinglab.vo.mcs.ezcheckout;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "catID",
    "vendorID",
    "bundleID",
    "pricePlanPackage",
    "serviceID",
    "purchaseName"
})
public class Category {

    @XmlElement(name = "CatID")
    protected String catID;
    @XmlElement(name = "VendorID")
    protected String vendorID;
    @XmlElement(name = "BundleID")
    protected String bundleID;
    @XmlElement(name = "PricePlanPackage", required = true)
    protected PricePlanPackageType pricePlanPackage;
    @XmlElement(name = "ServiceID")
    protected String serviceID;
    @XmlElement(name = "PurchaseName")
    protected String purchaseName;

    /**
     * Gets the value of the catID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCatID() {
        return catID;
    }

    /**
     * Sets the value of the catID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCatID(String value) {
        this.catID = value;
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
     * Gets the value of the bundleID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBundleID() {
        return bundleID;
    }

    /**
     * Sets the value of the bundleID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBundleID(String value) {
        this.bundleID = value;
    }

    public PricePlanPackageType getPricePlanPackage() {
		return pricePlanPackage;
	}

	public void setPricePlanPackage(PricePlanPackageType pricePlanPackage) {
		this.pricePlanPackage = pricePlanPackage;
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
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://www.vzw.com/namespaces/scmplus}PricePlanPackage-Type">
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class PricePlanPackage
        extends PricePlanPackageType
    {


    }

}

