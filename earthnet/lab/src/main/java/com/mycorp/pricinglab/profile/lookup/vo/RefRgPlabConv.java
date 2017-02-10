package com.vzwcorp.pricinglab.profile.lookup.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="REF_RG_PLAB_CONV")
public class RefRgPlabConv {
		
		@Column(name = "APN_NAME", nullable = false)
	    private String apnName;

		@Id
	    @Column(name = "APN_RT_GRP_NO_MIN", nullable = false)
	    private Long apnRtGrpNoMin;

	    @Column(name = "APN_RT_GRP_NO_MAX")
	    private Long apnRtGrpNoMax;

	    @Column(name = "DATA_CAT_CD", nullable = false)
	    private String dataCatCd;	
	    

	public String getApnName() {
		return apnName;
	}

	public void setApnName(String apnName) {
		this.apnName = apnName;
	}

	public Long getApnRtGrpNoMin() {
		return apnRtGrpNoMin;
	}

	public void setApnRtGrpNoMin(Long apnRtGrpNoMin) {
		this.apnRtGrpNoMin = apnRtGrpNoMin;
	}

	public Long getApnRtGrpNoMax() {
		return apnRtGrpNoMax;
	}

	public void setApnRtGrpNoMax(Long apnRtGrpNoMax) {
		this.apnRtGrpNoMax = apnRtGrpNoMax;
	}

	public String getDataCatCd() {
		return dataCatCd;
	}

	public void setDataCatCd(String dataCatCd) {
		this.dataCatCd = dataCatCd;
	}

	
}
