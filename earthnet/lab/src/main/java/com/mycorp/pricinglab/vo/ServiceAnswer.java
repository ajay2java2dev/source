package com.vzwcorp.pricinglab.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import com.vzwcorp.pricinglab.utility.PricingLabUtility;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "SERVICE_ANSWER")
public class ServiceAnswer {

    @Id
    @Column(name = "SERVICE_ANSWER_ID")
    @SequenceGenerator(name = "SERVICE_ANSWER_GEN", sequenceName = "SERVICE_ANSWER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVICE_ANSWER_GEN")
    private Long serviceAnswerId;

    @ManyToOne
    @JoinColumn(name = "VERIZON_ENTITY_ID")
    public VerizonEntity verizonEntity;

    @ManyToOne
    @JoinColumn(name = "SERVICE_QUESTION_ID")
    public ServiceQuestion serviceQuestion;

    @ManyToOne
    @JoinColumn(name = "SERVICE_INSTANCE_ID")
    public ServiceInstance serviceInstance;

//    @ElementCollection
//    private List<String> selected;

    @Column(name = "CREATE_DTM",nullable = false)
    @JsonView(Views.InternalView.class)
    private Date dateCreated = new Date();

    @Column(name = "CREATE_USR",nullable = false,length = 25)
    @JsonView(Views.InternalView.class)
    private String createdBy = PlabConstants.DEFAULT_CREATED_BY_USER;

    @Column(name = "END_DTM",nullable = false)
    @JsonView(Views.InternalView.class)
    private Timestamp endTmstamp = PricingLabUtility.getDefaultEndTimeStamp();

    public ServiceInstance getServiceInstance() {
        return serviceInstance;
    }

    public void setServiceInstance(ServiceInstance serviceInstance) {
        this.serviceInstance = serviceInstance;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Long getServiceAnswerId() {
        return serviceAnswerId;
    }

    public void setServiceAnswerId(Long serviceAnswerId) {
        this.serviceAnswerId = serviceAnswerId;
    }

    public VerizonEntity getVerizonEntity() {
        return verizonEntity;
    }

    public void setVerizonEntity(VerizonEntity verizonEntity) {
        this.verizonEntity = verizonEntity;
    }

    public ServiceQuestion getServiceQuestion() {
        return serviceQuestion;
    }

    public void setServiceQuestion(ServiceQuestion ServiceQuestion) {
        this.serviceQuestion = ServiceQuestion;
    }

//    public List<String> getSelected() {
//        return selected;
//    }
//
//    public void setSelected(List<String> selected) {
//        this.selected = selected;
//    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getEndTmstamp() {
        return endTmstamp;
    }

    public void setEndTmstamp(Timestamp endTmstamp) {
        this.endTmstamp = endTmstamp;
    }
}
