package com.vzwcorp.pricinglab.vo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import com.vzwcorp.pricinglab.utility.PricingLabUtility;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "PROD_OFFER_INSTANCE")
public class OfferInstance {

    @Id
    @Column(name="OFFER_INSTANCE_ID")
    @SequenceGenerator(name = "PROD_OFFER_INSTANCE_GEN", sequenceName = "PROD_OFFER_INSTANCE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROD_OFFER_INSTANCE_GEN")
    @JsonView({Views.ManagedView.class, Views.PlabAdminView.class})
    private Long offerInstanceId;

    @OneToMany(mappedBy = "offerInstance", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonView(Views.ManagedView.class)
    private List<ServiceInstance> serviceInstances;

    @OneToMany(mappedBy = "offerInstance", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonView(Views.PlabAdminView.class)
    @JsonBackReference
    private List<ChoiceInstance> choiceInstances;

    @Column(name = "TERMS_AND_COND_STATUS")
    @JsonView(Views.ManagedView.class)
    private int termsAndConditionsStatus;

    @ManyToOne
    @JoinColumn(name = "VERIZON_ENTITY_ID",nullable = false)
    @JsonView({Views.InternalView.class, Views.PlabAdminView.class})
    private VerizonEntity verizonEntity;

    @JsonView({Views.InternalView.class})
    @ManyToOne
    @JoinColumn(name = "PROD_OFFER_ID")
    private Offer offer;

    @Column(name = "CREATE_DTM",nullable = false)
    @JsonView({Views.InternalView.class, Views.ManagedView.class, Views.PlabAdminView.class})
    private Date dateCreated = new Date();

    @Column(name = "CREATE_USR",nullable = false,length = 25)
    @JsonView(Views.InternalView.class)
    private String createdBy = PlabConstants.DEFAULT_CREATED_BY_USER;

    @Column(name = "END_DTM",nullable = false)
    @JsonView({Views.InternalView.class, Views.ManagedView.class, Views.PlabAdminView.class})
    private Timestamp endTmstamp = PricingLabUtility.getDefaultEndTimeStamp();
    
    @Transient
    @JsonView(Views.PlabAdminView.class)
    private boolean expired;

    //Constructor(s)
    public OfferInstance() {
    }

    public OfferInstance(Offer offer, VerizonEntity vzEntity) {
        this.offer = offer;
        this.verizonEntity = vzEntity;
    }

    //Getters and Setters
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Long getOfferInstanceId() {
        return offerInstanceId;
    }

    public void setOfferInstanceId(Long offerInstanceId) {
        this.offerInstanceId = offerInstanceId;
    }

    public List<ServiceInstance> getServiceInstances() {
        return serviceInstances;
    }

    public void setServiceInstances(List<ServiceInstance> serviceInstances) {
        this.serviceInstances = serviceInstances;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public VerizonEntity getVerizonEntity() {
        return verizonEntity;
    }

    public void setVerizonEntity(VerizonEntity verizonEntity) {
        this.verizonEntity = verizonEntity;
    }

    public int getTermsAndConditionsStatus() {
        return termsAndConditionsStatus;
    }

    public void setTermsAndConditionsStatus(int termsAndConditionsStatus) {
        this.termsAndConditionsStatus = termsAndConditionsStatus;
    }

    @JsonView({Views.ManagedView.class})
    public String getMdn(){
    	return this.verizonEntity.getMdn();
    }
    
	@JsonView(Views.InternalView.class)
    public String getUsage(){
    	long usage=0;
    	for ( ServiceInstance instance: serviceInstances)
    		usage+= instance.counter;
    	return Long.toString(usage);
    }

	@JsonView(Views.PlabAdminView.class)
    public String getOfferName(){
		return this.offer.getName();
    }

    public List<ChoiceInstance> getChoiceInstances() {
        return choiceInstances;
    }

    public void setChoiceInstances(List<ChoiceInstance> choiceInstances) {
        this.choiceInstances = choiceInstances;
    }

    public Timestamp getEndTmstamp() {
        return endTmstamp;
    }

    public void setEndTmstamp(Timestamp endTmstamp) {
        this.endTmstamp = endTmstamp;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    public boolean getExpired(){
    	return endTmstamp.before(new Date());
    }

	public void setExpired(boolean expired) {
		this.expired = expired;
	}
    
    

}
