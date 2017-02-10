package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="SUB_SU_ALLOCATION")
@IdClass(SubSuAllocationPK.class)
public class SubSuAllocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SU_SPECIFICATION_ID")
	public Long suSpecificationId;

	public Long getSuSpecificationId() {
		return suSpecificationId;
}
	public void setSuSpecificationId(Long suSpecificationId) {
		this.suSpecificationId = suSpecificationId;
	}

	@Id
	@Column(name="SERIAL_NUMBER")
	public String serialNumber;

	public String getSerialNumber() {
		return serialNumber;
}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Column(name="STATUS")
	public String status;

	public String getStatus() {
		return status;
}
	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name="MDN")
	public String mdn;

	public String getMdn() {
		return mdn;
}
	public void setMdn(String mdn) {
		this.mdn = mdn;
	}

	@Column(name="GRI")
	public String gri;

	public String getGri() {
		return gri;
}
	public void setGri(String gri) {
		this.gri = gri;
	}

	@Column(name="TOTAL_BYTES_STRIPPED")
	public Long totalBytesStripped;

	public Long getTotalBytesStripped() {
		return totalBytesStripped;
}
	public void setTotalBytesStripped(Long totalBytesStripped) {
		this.totalBytesStripped = totalBytesStripped;
	}

	@Column(name="DELETE_IND")
	public String deleteInd;

	public String getDeleteInd() {
		return deleteInd;
}
	public void setDeleteInd(String deleteInd) {
		this.deleteInd = deleteInd;
	}

	@Column(name="INSERTDATE")
	public String insertdate;

	public String getInsertdate() {
		return insertdate;
}
	public void setInsertdate(String insertdate) {
		this.insertdate = insertdate;
	}

	@Column(name="MODIFYDATE")
	public String modifydate;

	public String getModifydate() {
		return modifydate;
}
	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}

}