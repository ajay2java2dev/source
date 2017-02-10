package com.vzwcorp.pricinglab.vo.dvs.nbs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"correlationId",
		"serviceHeader",
		"serviceBody",
		"serviceResponse",
		"billProrateCalc_NONE"
})

@XmlRootElement(name = "service")
public class Service {
	
	protected String correlationId;
	protected ServiceHeader serviceHeader;
	protected ServiceBody serviceBody;
	protected ServiceResponse serviceResponse;
	protected BillProrateCalcNONE billProrateCalc_NONE;
	
	public String getCorrelationId() {
		return correlationId;
	}
	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
	public ServiceHeader getServiceHeader() {
		return serviceHeader;
	}
	public void setServiceHeader(ServiceHeader serviceHeader) {
		this.serviceHeader = serviceHeader;
	}
	public ServiceBody getServiceBody() {
		return serviceBody;
	}
	public void setServiceBody(ServiceBody serviceBody) {
		this.serviceBody = serviceBody;
	}
	public ServiceResponse getServiceResponse() {
		return serviceResponse;
	}
	public void setServiceResponse(ServiceResponse serviceResponse) {
		this.serviceResponse = serviceResponse;
	}
	public BillProrateCalcNONE getBillProrateCalc_NONE() {
		return billProrateCalc_NONE;
	}
	public void setBillProrateCalc_NONE(BillProrateCalcNONE billProrateCalc_NONE) {
		this.billProrateCalc_NONE = billProrateCalc_NONE;
	}

}
