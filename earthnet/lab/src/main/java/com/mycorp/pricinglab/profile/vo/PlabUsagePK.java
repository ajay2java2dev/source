package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PlabUsagePK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="GLOBAL_RECORD_ID")
	protected String globalRecordId;
	
	public PlabUsagePK() {}

	public PlabUsagePK(String globalRecordId){
		 this.globalRecordId = globalRecordId;
	}

	public String getGlobalRecordId() {
		return globalRecordId;
	}

	public void setGlobalRecordId(String globalRecordId) {
		this.globalRecordId = globalRecordId;
	}
}
