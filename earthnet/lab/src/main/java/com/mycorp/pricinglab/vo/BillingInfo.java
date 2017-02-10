package com.vzwcorp.pricinglab.vo;

import com.fasterxml.jackson.annotation.JsonView;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Entity refers to customer billing information and used along with usage information from VISP.
 *
 * @author Pricing Lab
 * @since 1.0
 */
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "BILLING_INFO")
public class BillingInfo {

    @Id
    @Column(name="BILLING_INFO_ID")
    @SequenceGenerator(name = "BILLING_INFO_GEN", sequenceName = "BILLING_INFO_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BILLING_INFO_GEN")
    @JsonView({Views.InternalView.class, Views.ManagedView.class,Views.PlabAdminView.class})
    private Long billingInfoId;

    @Column(name = "RATING_GROUP",nullable = false)
    @JsonView({Views.MobileFirstView.class, Views.ManagedView.class,Views.PlabAdminView.class})
    private Long ratingGroup;

    @Column(name = "ROAMING_INDICATOR",nullable = false)
    @JsonView({Views.MobileFirstView.class, Views.ManagedView.class})
    private Long roamingIndicator;

//    @JsonView(Views.InternalView.class)
//    @ManyToOne
//    @JoinColumn(name = "SERVICE_ID", insertable = true, updatable = true)
//    private Service service;

    @JsonView({Views.InternalView.class,Views.PlabAdminView.class})
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "SERVICE_INSTANCE_ID", insertable = true, updatable = true)
    private ServiceInstance serviceInstance;

    @Column(name = "FIRST_TIME_OF_USAGE",nullable = false)
    @JsonView({Views.MobileFirstView.class, Views.ManagedView.class,Views.PlabAdminView.class})
    private Date firsttimeofUsage;

    @Column(name = "LAST_TIME_OF_USAGE",nullable = false)
    @JsonView({Views.MobileFirstView.class, Views.ManagedView.class,Views.PlabAdminView.class})
    private Date lasttimeofUsage;

    @Column(name = "TOTAL_BYTES",nullable = false)
    @JsonView({Views.MobileFirstView.class, Views.ManagedView.class,Views.PlabAdminView.class})
    private Long totalBytes;

    @Column(name = "DELTA_BYTES",nullable = false)
    @JsonView({Views.ManagedView.class,Views.PlabAdminView.class})
    private Long deltaBytes;

    @Column(name = "TOTAL_PACKETS",nullable = false)
    @JsonView({Views.MobileFirstView.class, Views.ManagedView.class,Views.PlabAdminView.class})
    private Long totalPackets;

    @Transient
    @JsonView({Views.MobileFirstView.class})
    private Long uplinkBytes;

    @Transient
    @JsonView({Views.MobileFirstView.class})
    private Long downlinkBytes;

    @JsonView({Views.InternalView.class, Views.ManagedView.class,Views.PlabAdminView.class})
    @OneToOne(mappedBy = "billingInfo", cascade = CascadeType.ALL)
    private UsageRecord usageRecord;

    @Column(name = "CREATE_DTM",nullable = false)
    @JsonView({Views.InternalView.class, Views.PlabAdminView.class})
    private Date dateCreated = new Date();

    @Column(name = "CREATE_USR",nullable = false,length = 25)
    @JsonView(Views.InternalView.class)
    private String createdBy = PlabConstants.DEFAULT_CREATED_BY_USER;

    @Transient
    @JsonView(Views.MobileFirstView.class)
    private String vispServiceID;

    @Transient
    @JsonView(Views.MobileFirstView.class)
    private String vispServiceInstanceID;

    //Getters and Setters
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Long getBillingInfoId() {
        return billingInfoId;
    }

    public void setBillingInfoId(Long billingInfoId) {
        this.billingInfoId = billingInfoId;
    }

    public Long getRatingGroup() {
        return ratingGroup;
    }

    public void setRatingGroup(Long ratingGroup) {
        this.ratingGroup = ratingGroup;
    }

    public Long getRoamingIndicator() {
        return roamingIndicator;
    }

    public void setRoamingIndicator(Long roamingIndicator) {
        this.roamingIndicator = roamingIndicator;
    }


    public Date getFirsttimeofUsage() {
        return firsttimeofUsage;
    }

    public void setFirsttimeofUsage(Date firsttimeofUsage) {
        this.firsttimeofUsage = firsttimeofUsage;
    }

    public Date getLasttimeofUsage() {
        return lasttimeofUsage;
    }

    public void setLasttimeofUsage(Date lasttimeofUsage) {
        this.lasttimeofUsage = lasttimeofUsage;
    }

    public Long getTotalBytes() {
        return totalBytes;
    }

    public void setTotalBytes(Long totalBytes) {
        this.totalBytes = totalBytes;
    }

    public Long getTotalPackets() {
        return totalPackets;
    }

    public void setTotalPackets(Long totalPackets) {
        this.totalPackets = totalPackets;
    }

//    public Service getService() {
//        return service;
//    }
//
//    public void setService(Service service) {
//        this.service = service;
//    }

    public ServiceInstance getServiceInstance() {
        return serviceInstance;
    }

    public void setServiceInstance(ServiceInstance serviceInstance) {
        this.serviceInstance = serviceInstance;
    }

    public UsageRecord getUsageRecord() {
        return usageRecord;
    }

    public void setUsageRecord(UsageRecord usageRecord) {
        this.usageRecord = usageRecord;
    }

    public String getVispServiceID() {
        return vispServiceID;
    }

    public void setVispServiceID(String vispServiceID) {
        this.vispServiceID = vispServiceID;
    }

    public String getVispServiceInstanceID() {
        return vispServiceInstanceID;
    }

    public void setVispServiceInstanceID(String vispServiceInstanceID) {
        this.vispServiceInstanceID = vispServiceInstanceID;
    }

    public Long getDeltaBytes() {
        return deltaBytes;
    }

    public void setDeltaBytes(Long deltaBytes) {
        this.deltaBytes = deltaBytes;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUplinkBytes() {
        return uplinkBytes;
    }

    public void setUplinkBytes(Long uplinkBytes) {
        this.uplinkBytes = uplinkBytes;
    }

    public Long getDownlinkBytes() {
        return downlinkBytes;
    }

    public void setDownlinkBytes(Long downlinkBytes) {
        this.downlinkBytes = downlinkBytes;
    }
}
