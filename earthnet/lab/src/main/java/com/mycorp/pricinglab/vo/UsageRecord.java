package com.vzwcorp.pricinglab.vo;

import com.fasterxml.jackson.annotation.JsonView;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "USAGE_RECORD")
public class UsageRecord {


    @Id
    @Column(name = "USAGE_RECORD_ID")
    @SequenceGenerator(name = "USAGE_RECORD_GEN", sequenceName = "USAGE_RECORD_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USAGE_RECORD_GEN")
    @JsonView({Views.InternalView.class, Views.ManagedView.class})
    private Long usageRecordId;

    @Column(name = "MDN",length = 10)
    @JsonView({Views.MobileFirstView.class, Views.ManagedView.class})
    private String mdn;

    @Column(name = "EVENT_TYPE",length = 50)
    @JsonView({Views.MobileFirstView.class, Views.ManagedView.class})
    private String eventType;

    @Column(name = "CHANGE_TIME")
    @JsonView({Views.MobileFirstView.class, Views.ManagedView.class})
    private Date changeTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BILLING_INFO_ID")
    @JsonView(Views.MobileFirstView.class)
    private BillingInfo billingInfo;

    @Column(name = "VISP_SEQ_NUM",length = 50)
    @JsonView({Views.InternalView.class, Views.ManagedView.class})
    private String seqNum;

    @Column(name = "CREATE_DTM",nullable = false)
    @JsonView(Views.InternalView.class)
    private Date dateCreated = new Date();

    @Column(name = "CREATE_USR",nullable = false,length = 25)
    @JsonView(Views.InternalView.class)
    private String createdBy = PlabConstants.DEFAULT_CREATED_BY_USER;

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Long getUsageRecordId() {
        return usageRecordId;
    }

    public void setUsageRecordId(Long usageRecordId) {
        this.usageRecordId = usageRecordId;
    }

    public String getMdn() {
        return mdn;
    }

    public void setMdn(String mdn) {
        this.mdn = mdn;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public BillingInfo getBillingInfo() {
        return billingInfo;
    }

    public void setBillingInfo(BillingInfo billingInfo) {
        this.billingInfo = billingInfo;
    }

    public String getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(String seqNum) {
        this.seqNum = seqNum;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

}
