package com.vzwcorp.pricinglab.vo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "TIMEFRAME")
public class TimeFrame {

    @Id
    @SequenceGenerator(name = "TIMEFRAME_GEN", sequenceName = "TIMEFRAME_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIMEFRAME_GEN")
    @Column(name = "TIMEFRAME_ID")
    @JsonView(Views.InternalView.class)
    private Long timeFrameId;

    @JsonView(Views.CPIView.class)
    @Column(name = "TIME_FROM", length = 32)
    private String from;

    @JsonView(Views.CPIView.class)
    @Column(name = "TIME_TO", length = 32)
    private String to;

    @JsonView(Views.CPIView.class)
    @Column(name = "TIME_AT", length = 32)
    private String at;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "timeClassification")
    @JsonView(Views.InternalView.class)
    private List<Service> services;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "timeFrame")
    @JsonView(Views.InternalView.class)
    private List<QoS> qoss;

    @Column(name = "CREATE_DTM",nullable = false)
    @JsonView(Views.InternalView.class)
    private Date dateCreated;

    @Column(name = "CREATE_USR",nullable = false,length = 25)
    @JsonView(Views.InternalView.class)
    private String createdBy = PlabConstants.DEFAULT_CREATED_BY_USER;

    //constructor(s)
    public TimeFrame() {
    }

    public TimeFrame(String at) {
        this.at = at;
    }

    public TimeFrame(String from, String to) {
        this.from = from;
        this.to = to;
    }

    //Getters and Setters
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Long getTimeFrameId() {
        return timeFrameId;
    }

    public void setTimeFrameId(Long timeFrameId) {
        this.timeFrameId = timeFrameId;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<QoS> getQoss() {
        return qoss;
    }

    public void setQoss(List<QoS> qoss) {
        this.qoss = qoss;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

}
