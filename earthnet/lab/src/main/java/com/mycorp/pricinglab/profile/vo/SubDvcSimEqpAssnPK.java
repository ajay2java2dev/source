package com.vzwcorp.pricinglab.profile.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubDvcSimEqpAssnPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="DEVICE_ID_SIM")
	protected String	deviceIdSim;

	@Column(name="DEVICE_ASSN_EFF_DT")
	protected Timestamp	deviceAssnEffDt;

	@Column(name="DEVICE_ID_TYP_SIM")
	protected String	deviceIdTypSim;

	public SubDvcSimEqpAssnPK() {}

	public SubDvcSimEqpAssnPK(String deviceIdSim, Timestamp deviceAssnEffDt, String deviceIdTypSim){
		 this.deviceIdSim = deviceIdSim;
		 this.deviceAssnEffDt = deviceAssnEffDt;
		 this.deviceIdTypSim = deviceIdTypSim;
	}

	public String getDeviceIdSim() {
		return this.deviceIdSim;
	}

	public void setDeviceIdSim(String deviceIdSim) {
		this.deviceIdSim = deviceIdSim;
	}

	public Timestamp getDeviceAssnEffDt() {
		return this.deviceAssnEffDt;
	}

	public void setDeviceAssnEffDt(Timestamp deviceAssnEffDt) {
		this.deviceAssnEffDt = deviceAssnEffDt;
	}

	public String getDeviceIdTypSim() {
		return this.deviceIdTypSim;
	}

	public void setDeviceIdTypSim(String deviceIdTypSim) {
		this.deviceIdTypSim = deviceIdTypSim;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SubDvcSimEqpAssnPK)) {
			return false;
		}
		SubDvcSimEqpAssnPK castOther = (SubDvcSimEqpAssnPK)other;
		return this.deviceIdSim.equals(castOther.deviceIdSim) && this.deviceAssnEffDt.equals(castOther.deviceAssnEffDt) && this.deviceIdTypSim.equals(castOther.deviceIdTypSim);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.deviceIdSim.hashCode();
		hash = hash * prime + this.deviceAssnEffDt.hashCode();
		hash = hash * prime + this.deviceIdTypSim.hashCode();
		return hash;
	}

}
