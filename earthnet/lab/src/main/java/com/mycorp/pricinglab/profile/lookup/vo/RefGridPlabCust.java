package com.vzwcorp.pricinglab.profile.lookup.vo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * REF_GRID_PLAB_CUST
 * @author : PricingLab
 * @since : July 2016
 */
@Table(name="REF_GRID_PLAB_CUST")
@Entity
@IdClass(RefGridPlabCustPK.class)
public class RefGridPlabCust implements Serializable{

    @Id
    @Column(name = "CUST_ID_NO", nullable = false)
    private Long custIdNo;

    @Id
    @Column(name = "ACCT_NO", nullable = false)
    private Long acctNo;

    @Id
    @Column(name = "MDN", nullable = false)
    private String mdn;

    @Column(name = "MTN_EFF_DT", nullable = false)
    private Timestamp mtnEffDt;

    @Column(name = "INDICATOR", nullable = false)
    private Long indicator;

    @Column(name = "MODIFIED_DATE")
    private Timestamp modifiedDate = new Timestamp(new Date().getTime());

    @Column(name = "PL_START_DATE", nullable = false)
    private Timestamp plStartDate;

    @Column(name = "PL_END_DATE")
    private Timestamp plEndDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "MODIFIED_BY")
    private String modifiedBy;

    public Long getCustIdNo() {
        return custIdNo;
    }

    public void setCustIdNo(Long custIdNo) {
        this.custIdNo = custIdNo;
    }

    public Long getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(Long acctNo) {
        this.acctNo = acctNo;
    }

    public String getMdn() {
        return mdn;
    }

    public void setMdn(String mdn) {
        this.mdn = mdn;
    }

    public Timestamp getMtnEffDt() {
        return mtnEffDt;
    }

    public void setMtnEffDt(Timestamp mtnEffDt) {
        this.mtnEffDt = mtnEffDt;
    }

    public Long getIndicator() {
        return indicator;
    }

    public void setIndicator(Long indicator) {
        this.indicator = indicator;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Timestamp getPlStartDate() {
        return plStartDate;
    }

    public void setPlStartDate(Timestamp plStartDate) {
        this.plStartDate = plStartDate;
    }

    public Timestamp getPlEndDate() {
        return plEndDate;
    }

    public void setPlEndDate(Timestamp plEndDate) {
        this.plEndDate = plEndDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
