package com.vzwcorp.pricinglab.vo;

import com.fasterxml.jackson.annotation.JsonView;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "QOS")
public class QoS {

    @Id
    @Column(name = "QOS_ID")
    @SequenceGenerator(name = "QOS_GEN", sequenceName = "QOS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QOS_GEN")
    @JsonView(Views.InternalView.class)
    private Long qosId;

    @Column(name = "QOS_NAME",length = 25)
    @JsonView({Views.CPIView.class, Views.InternalView.class})
    private String name;

    @Column(name = "QOS_SPEED",length = 25)
    @JsonView({Views.CPIView.class, Views.InternalView.class})
    private String speed;

    @Column(name = "CHARGE_PER_DAY")
    @JsonView({Views.CPIView.class, Views.InternalView.class})
    private BigDecimal chargePerDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TIMEFRAME_ID")
    @JsonView(Views.InternalView.class)
    public TimeFrame timeFrame;

    @ManyToMany(mappedBy = "qos", fetch = FetchType.LAZY)
    @JsonView(Views.InternalView.class)
    private List<Service> services;

    @Column(name = "CREATE_DTM",nullable = false)
    @JsonView(Views.InternalView.class)
    private Date dateCreated=new Date();

    @Column(name = "CREATE_USR",nullable = false,length = 25)
    @JsonView(Views.InternalView.class)
    private String createdBy= PlabConstants.DEFAULT_CREATED_BY_CPI;

    //constructor(s)
    public QoS() {
    }

    public QoS(String speed, String from, String to) {
        this.speed = speed;
        this.name = speed;
        this.timeFrame = new TimeFrame(from, to);
    }

    public QoS(String speed, String name, BigDecimal chargePerDay, String from, String to) {
        this.speed = speed;
        this.name = name;
        this.chargePerDay = chargePerDay;
        this.timeFrame = new TimeFrame(from, to);
    }

    //Getters and Setters

    public Long getQosId() {
        return qosId;
    }

    public void setQosId(Long qosId) {
        this.qosId = qosId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public BigDecimal getChargePerDay() {
        return chargePerDay;
    }

    public void setChargePerDay(BigDecimal chargePerDay) {
        this.chargePerDay = chargePerDay;
    }

    public TimeFrame getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(TimeFrame timeFrame) {
        this.timeFrame = timeFrame;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
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
