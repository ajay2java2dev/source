package com.vzwcorp.pricinglab.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "PROD_OFFER")
public class Offer {

    @Id
    @Column(name = "OFFER_ID")
    @SequenceGenerator(name = "PROD_OFFER_GEN", sequenceName = "PROD_OFFER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROD_OFFER_GEN")
    private Long offerId;

    @Column(name = "OFFER_NAME",length = 30)
    @JsonView({Views.MobileFirstView.class, Views.CPIView.class, Views.PlabAdminView.class})
    private String name;

//    @Column(name = "OFFER_TITLE",length = 30)
//    @JsonView({Views.MobileFirstView.class, Views.CPIView.class, Views.PlabAdminView.class})
//    private String title;

    @Column(name = "OFFER_DESC",length = 1000)
    @JsonView({Views.MobileFirstView.class, Views.CPIView.class, Views.PlabAdminView.class})
    private String shortDescription;

    @Transient
    @JsonView({Views.MobileFirstView.class, Views.CPIView.class, Views.PlabAdminView.class})
    private int charge;

    @Transient
    @JsonView(Views.MobileFirstView.class)
    private String isEnrolled = PlabConstants.MDN_ELIG;

    @OneToMany(mappedBy = "offer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonView(Views.VispView.class)
    private List<com.vzwcorp.pricinglab.vo.Service> services;

    @OneToMany(mappedBy = "offer", fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    @JsonView({Views.CPIView.class, Views.InternalView.class})
    @JsonManagedReference
    private List<PlabCust> plabcustomers;

    @Transient
    @JsonView({Views.MobileFirstView.class, Views.PlabAdminView.class})
    private List<OfferMdnOption> offerMdnOptions;


    @OneToMany(mappedBy = "offer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonView({Views.MobileFirstView.class, Views.CPIView.class})
    private List<ServiceQuestion> offerOptions;


    @OneToMany(mappedBy = "offer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonView({Views.MobileFirstView.class, Views.CPIView.class})
    private List<SimplePage> simplePages;


    @OneToMany(mappedBy = "offer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonView(Views.InternalView.class)
    private List<OfferInstance> offerInstances;

    @OneToMany(mappedBy = "offer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonView({Views.MobileFirstView.class, Views.CPIView.class})
    private List<Survey> surveys;

    @Column(name = "OFFER_STATE")
    @JsonView({Views.MobileFirstView.class, Views.CPIView.class, Views.InternalView.class, Views.PlabAdminView.class})
    private String state;

    @Column(name = "TOTAL_INVITED_CUST")
    @JsonView({Views.CPIView.class, Views.InternalView.class, Views.PlabAdminView.class})
    private Integer totalInvitedCust;

    @Column(name = "TOTAL_ACTIVE_CUST")
    @JsonView({Views.CPIView.class, Views.InternalView.class, Views.PlabAdminView.class})
    private Integer totalActiveCust;

    @Column(name = "MDN_OPTION_ENABLED")
    @JsonView({Views.CPIView.class, Views.InternalView.class, Views.PlabAdminView.class})
    private Boolean mdnOptionEnabled;

    @Column(name = "MDN_SELECT_OPTIONAL")
    @JsonView({Views.InternalView.class, Views.PlabAdminView.class})
    private Boolean mdnSelectOptional;

    @Column(name = "EFF_DTM",nullable = false)
    @JsonView({Views.MobileFirstView.class, Views.CPIView.class, Views.PlabAdminView.class})
    private Date startDate;

    @Column(name = "END_DTM",nullable = false)
    @JsonView({Views.MobileFirstView.class, Views.CPIView.class, Views.PlabAdminView.class})
    private Date endDate;

    @Column(name = "CREATE_DTM",nullable = false)
    @JsonView({Views.InternalView.class})
    private Date dateCreated = new Date();

    @Column(name = "CREATE_USR",nullable = false)
    @JsonView(Views.InternalView.class)
    private String createdBy = PlabConstants.DEFAULT_CREATED_BY_USER;

    @Transient
    @JsonView({Views.MobileFirstView.class})
    private Integer newSurveyCount;

    public List<com.vzwcorp.pricinglab.vo.Service> getServices() {
        return services;
    }

    public void setServices(List<com.vzwcorp.pricinglab.vo.Service> services) {
        this.services = services;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public List<OfferInstance> getOfferInstances() {
        return offerInstances;
    }

    public void setOfferInstances(List<OfferInstance> offerInstances) {
        this.offerInstances = offerInstances;
    }

//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @JsonView({Views.MobileFirstView.class, Views.CPIView.class, Views.PlabAdminView.class})
    public int getCharge() {
        int charge = 0;
        if (services != null)
            for (Service service : services)
                charge += service.getCharge();
        return charge;
    }

    @JsonView({Views.MobileFirstView.class, Views.CPIView.class})
    public List<ServiceQuestion> getOfferOptions() {
        List<ServiceQuestion> offerOptions = new ArrayList<ServiceQuestion>();

        if (services == null) {
            return null;
        }

        for (Service service : services) {
            offerOptions.addAll(service.getOptions());
            for (ServiceQuestion serviceQuestion : offerOptions) {
                serviceQuestion.setMaxSelectedOptions(service.getMaxNumberOfApps());
                //For QoS option enabled display charge per day
                for (QoS qos : service.getQos()) {
                    for (Choice choice : serviceQuestion.getChoice()) {
                        if (qos.getName() != null && choice.getTitle() != null && qos.getName().equalsIgnoreCase(choice.getTitle())) {
                            choice.setChargePerDay(qos.getChargePerDay());
                        }
                    }
                }
            }

        }

        if ((offerOptions != null && offerOptions.isEmpty()) || offerMdnOptions != null) {
            return null;
        }

        return offerOptions;
    }

    public void setQuestions(List<ServiceQuestion> questions) {
        this.offerOptions = questions;
    }

    public List<SimplePage> getSimplePages() {
        return simplePages;
    }

    public void setSimplePages(List<SimplePage> simplePages) {
        this.simplePages = simplePages;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public List<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }

    public void setOfferOptions(List<ServiceQuestion> offerOptions) {
        this.offerOptions = offerOptions;
    }

    public String isEnrolled() {
        return isEnrolled;
    }

    public void setEnrolled(String isEnrolled) {
        this.isEnrolled = isEnrolled;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIsEnrolled() {
        return isEnrolled;
    }

    public void setIsEnrolled(String isEnrolled) {
        this.isEnrolled = isEnrolled;
    }

    public List<PlabCust> getPlabcustomers() {
        return plabcustomers;
    }

    public void setPlabcustomers(List<PlabCust> plabcustomers) {
        this.plabcustomers = plabcustomers;
    }

    public Integer getTotalInvitedCust() {
        return totalInvitedCust;
    }

    public void setTotalInvitedCust(Integer totalInvitedCust) {
        this.totalInvitedCust = totalInvitedCust;
    }

    public Integer getTotalActiveCust() {
        return totalActiveCust;
    }

    public void setTotalActiveCust(Integer totalActiveCust) {
        this.totalActiveCust = totalActiveCust;
    }

    public Boolean isMdnOptionEnabled() {
        return mdnOptionEnabled;
    }

    public void setMdnOptionEnabled(Boolean mdnOptionEnabled) {
        this.mdnOptionEnabled = mdnOptionEnabled;
    }

    public List<OfferMdnOption> getOfferMdnOptions() {
        return offerMdnOptions;
    }

    public void setOfferMdnOptions(List<OfferMdnOption> offerMdnOptions) {
        this.offerMdnOptions = offerMdnOptions;
    }

    public Boolean getMdnOptionEnabled() {
        return mdnOptionEnabled;
    }

    public Boolean getMdnSelectOptional() {
        return mdnSelectOptional;
    }

    public void setMdnSelectOptional(Boolean mdnSelectOptional) {
        this.mdnSelectOptional = mdnSelectOptional;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getNewSurveyCount() {
        return newSurveyCount;
    }

    public void setNewSurveyCount(Integer newSurveyCount) {
        this.newSurveyCount = newSurveyCount;
    }
}
