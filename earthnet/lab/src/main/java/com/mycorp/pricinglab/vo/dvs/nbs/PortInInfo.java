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
 * <p>Java class for PortInInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PortInInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="portInMtn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="portInMin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currentMtn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currentMin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusReasonCodeDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="portReqNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="portReqType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="portReqStatusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="portReqStatusDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="portReqEffDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ssn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taxid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="spid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="areaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="areaCodeDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lrn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ospAcctNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ospPswrdPin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phoneUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cbrPhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="portInAuthName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="portInBillBsnsName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consentIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consentMethodCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consentMethDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consentTimeStamp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consentAuthType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="corrSentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderTimeStamp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ospName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ospAddress" type="{}Address" minOccurs="0"/>
 *         &lt;element name="portInStatusRowCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{}portInRequestStatusDetailsList" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PortInInfo", propOrder = {

})
public class PortInInfo {

    protected String portInMtn;
    protected String portInMin;
    protected String currentMtn;
    protected String currentMin;
    protected String statusReasonCode;
    protected String statusReasonCodeDesc;
    protected String portReqNumber;
    protected String portReqType;
    protected String portReqStatusCode;
    protected String portReqStatusDesc;
    protected String portReqEffDate;
    protected String accountNo;
    protected String ssn;
    protected String taxid;
    protected String spid;
    protected String areaCode;
    protected String areaCodeDesc;
    protected String lrn;
    protected String ospAcctNumber;
    protected String ospPswrdPin;
    protected String phoneUserName;
    protected String cbrPhoneNumber;
    protected String portInAuthName;
    protected String portInBillBsnsName;
    protected String consentIndicator;
    protected String consentMethodCode;
    protected String consentMethDescription;
    protected String consentTimeStamp;
    protected String consentAuthType;
    protected String corrSentCode;
    protected String orderTimeStamp;
    protected String ospName;
    protected Address ospAddress;
    protected String portInStatusRowCount;
    protected PortInRequestStatusDetailsList portInRequestStatusDetailsList;

    /**
     * Gets the value of the portInMtn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortInMtn() {
        return portInMtn;
    }

    /**
     * Sets the value of the portInMtn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortInMtn(String value) {
        this.portInMtn = value;
    }

    /**
     * Gets the value of the portInMin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortInMin() {
        return portInMin;
    }

    /**
     * Sets the value of the portInMin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortInMin(String value) {
        this.portInMin = value;
    }

    /**
     * Gets the value of the currentMtn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentMtn() {
        return currentMtn;
    }

    /**
     * Sets the value of the currentMtn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentMtn(String value) {
        this.currentMtn = value;
    }

    /**
     * Gets the value of the currentMin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentMin() {
        return currentMin;
    }

    /**
     * Sets the value of the currentMin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentMin(String value) {
        this.currentMin = value;
    }

    /**
     * Gets the value of the statusReasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusReasonCode() {
        return statusReasonCode;
    }

    /**
     * Sets the value of the statusReasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusReasonCode(String value) {
        this.statusReasonCode = value;
    }

    /**
     * Gets the value of the statusReasonCodeDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusReasonCodeDesc() {
        return statusReasonCodeDesc;
    }

    /**
     * Sets the value of the statusReasonCodeDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusReasonCodeDesc(String value) {
        this.statusReasonCodeDesc = value;
    }

    /**
     * Gets the value of the portReqNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortReqNumber() {
        return portReqNumber;
    }

    /**
     * Sets the value of the portReqNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortReqNumber(String value) {
        this.portReqNumber = value;
    }

    /**
     * Gets the value of the portReqType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortReqType() {
        return portReqType;
    }

    /**
     * Sets the value of the portReqType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortReqType(String value) {
        this.portReqType = value;
    }

    /**
     * Gets the value of the portReqStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortReqStatusCode() {
        return portReqStatusCode;
    }

    /**
     * Sets the value of the portReqStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortReqStatusCode(String value) {
        this.portReqStatusCode = value;
    }

    /**
     * Gets the value of the portReqStatusDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortReqStatusDesc() {
        return portReqStatusDesc;
    }

    /**
     * Sets the value of the portReqStatusDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortReqStatusDesc(String value) {
        this.portReqStatusDesc = value;
    }

    /**
     * Gets the value of the portReqEffDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortReqEffDate() {
        return portReqEffDate;
    }

    /**
     * Sets the value of the portReqEffDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortReqEffDate(String value) {
        this.portReqEffDate = value;
    }

    /**
     * Gets the value of the accountNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * Sets the value of the accountNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountNo(String value) {
        this.accountNo = value;
    }

    /**
     * Gets the value of the ssn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSsn() {
        return ssn;
    }

    /**
     * Sets the value of the ssn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSsn(String value) {
        this.ssn = value;
    }

    /**
     * Gets the value of the taxid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxid() {
        return taxid;
    }

    /**
     * Sets the value of the taxid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxid(String value) {
        this.taxid = value;
    }

    /**
     * Gets the value of the spid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpid() {
        return spid;
    }

    /**
     * Sets the value of the spid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpid(String value) {
        this.spid = value;
    }

    /**
     * Gets the value of the areaCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * Sets the value of the areaCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaCode(String value) {
        this.areaCode = value;
    }

    /**
     * Gets the value of the areaCodeDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaCodeDesc() {
        return areaCodeDesc;
    }

    /**
     * Sets the value of the areaCodeDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaCodeDesc(String value) {
        this.areaCodeDesc = value;
    }

    /**
     * Gets the value of the lrn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLrn() {
        return lrn;
    }

    /**
     * Sets the value of the lrn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLrn(String value) {
        this.lrn = value;
    }

    /**
     * Gets the value of the ospAcctNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOspAcctNumber() {
        return ospAcctNumber;
    }

    /**
     * Sets the value of the ospAcctNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOspAcctNumber(String value) {
        this.ospAcctNumber = value;
    }

    /**
     * Gets the value of the ospPswrdPin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOspPswrdPin() {
        return ospPswrdPin;
    }

    /**
     * Sets the value of the ospPswrdPin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOspPswrdPin(String value) {
        this.ospPswrdPin = value;
    }

    /**
     * Gets the value of the phoneUserName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneUserName() {
        return phoneUserName;
    }

    /**
     * Sets the value of the phoneUserName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneUserName(String value) {
        this.phoneUserName = value;
    }

    /**
     * Gets the value of the cbrPhoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCbrPhoneNumber() {
        return cbrPhoneNumber;
    }

    /**
     * Sets the value of the cbrPhoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCbrPhoneNumber(String value) {
        this.cbrPhoneNumber = value;
    }

    /**
     * Gets the value of the portInAuthName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortInAuthName() {
        return portInAuthName;
    }

    /**
     * Sets the value of the portInAuthName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortInAuthName(String value) {
        this.portInAuthName = value;
    }

    /**
     * Gets the value of the portInBillBsnsName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortInBillBsnsName() {
        return portInBillBsnsName;
    }

    /**
     * Sets the value of the portInBillBsnsName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortInBillBsnsName(String value) {
        this.portInBillBsnsName = value;
    }

    /**
     * Gets the value of the consentIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsentIndicator() {
        return consentIndicator;
    }

    /**
     * Sets the value of the consentIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsentIndicator(String value) {
        this.consentIndicator = value;
    }

    /**
     * Gets the value of the consentMethodCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsentMethodCode() {
        return consentMethodCode;
    }

    /**
     * Sets the value of the consentMethodCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsentMethodCode(String value) {
        this.consentMethodCode = value;
    }

    /**
     * Gets the value of the consentMethDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsentMethDescription() {
        return consentMethDescription;
    }

    /**
     * Sets the value of the consentMethDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsentMethDescription(String value) {
        this.consentMethDescription = value;
    }

    /**
     * Gets the value of the consentTimeStamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsentTimeStamp() {
        return consentTimeStamp;
    }

    /**
     * Sets the value of the consentTimeStamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsentTimeStamp(String value) {
        this.consentTimeStamp = value;
    }

    /**
     * Gets the value of the consentAuthType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsentAuthType() {
        return consentAuthType;
    }

    /**
     * Sets the value of the consentAuthType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsentAuthType(String value) {
        this.consentAuthType = value;
    }

    /**
     * Gets the value of the corrSentCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrSentCode() {
        return corrSentCode;
    }

    /**
     * Sets the value of the corrSentCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrSentCode(String value) {
        this.corrSentCode = value;
    }

    /**
     * Gets the value of the orderTimeStamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderTimeStamp() {
        return orderTimeStamp;
    }

    /**
     * Sets the value of the orderTimeStamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderTimeStamp(String value) {
        this.orderTimeStamp = value;
    }

    /**
     * Gets the value of the ospName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOspName() {
        return ospName;
    }

    /**
     * Sets the value of the ospName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOspName(String value) {
        this.ospName = value;
    }

    /**
     * Gets the value of the ospAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getOspAddress() {
        return ospAddress;
    }

    /**
     * Sets the value of the ospAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setOspAddress(Address value) {
        this.ospAddress = value;
    }

    /**
     * Gets the value of the portInStatusRowCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortInStatusRowCount() {
        return portInStatusRowCount;
    }

    /**
     * Sets the value of the portInStatusRowCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortInStatusRowCount(String value) {
        this.portInStatusRowCount = value;
    }

    /**
     * Gets the value of the portInRequestStatusDetailsList property.
     * 
     * @return
     *     possible object is
     *     {@link PortInRequestStatusDetailsList }
     *     
     */
    public PortInRequestStatusDetailsList getPortInRequestStatusDetailsList() {
        return portInRequestStatusDetailsList;
    }

    /**
     * Sets the value of the portInRequestStatusDetailsList property.
     * 
     * @param value
     *     allowed object is
     *     {@link PortInRequestStatusDetailsList }
     *     
     */
    public void setPortInRequestStatusDetailsList(PortInRequestStatusDetailsList value) {
        this.portInRequestStatusDetailsList = value;
    }

}