package com.vzwcorp.pricinglab.vo;

import com.fasterxml.jackson.annotation.JsonView;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="VERIZON_ENTITY",uniqueConstraints = @UniqueConstraint(columnNames ={"CUST_ID_NO","ACCT_NO","MDN"}))
public class VerizonEntity implements Serializable{

    @Id
    @Column(name = "VERIZON_ENTITY_ID")
    @SequenceGenerator(name = "VERIZON_ENTITY_GEN", sequenceName = "VERIZON_ENTITY_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VERIZON_ENTITY_GEN")
    private Long verizonEntityId;

    @OneToMany(mappedBy = "verizonEntity")
    @JsonView(Views.InternalView.class)
    private List<OfferInstance> offerInstances;

    @Column(name = "CUST_ID_NO", nullable = false)
    private Long custIdNo;

    @Column(name = "ACCT_NO", nullable = false)
    public Long acctNo;

    @Column(name = "MDN", nullable = false, length = 10)
    public String mdn;

    @Column(name = "CREATE_DTM",nullable = false)
    @JsonView(Views.InternalView.class)
    private Date dateCreated = new Date();

    @Column(name = "CREATE_USR",nullable = false,length = 25)
    @JsonView(Views.InternalView.class)
    private String createdBy= PlabConstants.DEFAULT_CREATED_BY_USER;

    //Constructor
    public VerizonEntity() {
    }

    public VerizonEntity(String mdn) {
        this.mdn = mdn;
    }

    public VerizonEntity(Long custIdNo, Long acctNo, String mdn) {
        this.custIdNo = custIdNo;
        this.acctNo = acctNo;
        this.mdn = mdn;
    }

    //Getters and Setters
    public Long getVerizonEntityId() {
        return verizonEntityId;
    }

    public void setVerizonEntityId(Long verizonEntityId) {
        this.verizonEntityId = verizonEntityId;
    }

    public List<OfferInstance> getOfferInstances() {
        return offerInstances;
    }

    public void setOfferInstances(List<OfferInstance> offerInstances) {
        this.offerInstances = offerInstances;
    }

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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

}
