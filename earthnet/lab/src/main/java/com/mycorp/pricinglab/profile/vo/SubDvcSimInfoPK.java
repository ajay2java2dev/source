package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubDvcSimInfoPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="DEVICE_ID_TYP")
	protected String	deviceIdTyp;

	@Column(name="DEVICE_ID")
	protected String	deviceId;

	public SubDvcSimInfoPK() {}

	public SubDvcSimInfoPK(String deviceIdTyp, String deviceId){
		 this.deviceIdTyp = deviceIdTyp;
		 this.deviceId = deviceId;
	}

	public String getDeviceIdTyp() {
		return this.deviceIdTyp;
	}

	public void setDeviceIdTyp(String deviceIdTyp) {
		this.deviceIdTyp = deviceIdTyp;
	}

	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SubDvcSimInfoPK)) {
			return false;
		}
		SubDvcSimInfoPK castOther = (SubDvcSimInfoPK)other;
		return this.deviceIdTyp.equals(castOther.deviceIdTyp) && this.deviceId.equals(castOther.deviceId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.deviceIdTyp.hashCode();
		hash = hash * prime + this.deviceId.hashCode();
		return hash;
	}

}
