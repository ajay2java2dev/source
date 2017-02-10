package com.vzwcorp.pricinglab.vo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Pricing Lab
 * @since 1.0
 */
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "PROD_SERVICE")
public class Service {

    @Id
    @Column(name = "SERVICE_ID")
    @SequenceGenerator(name = "SERVICE_GEN", sequenceName = "SERVICE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVICE_GEN")
    @JsonView(Views.CPIView.class)
    private Long serviceId;

    @JsonView(Views.CPIView.class)
    @Column(name = "SERVICE_STATE",length = 25)
    private String state;

    @JsonView({Views.CPIView.class, Views.VispView.class})
    @Column(name = "SERVICE_NAME", length = 25)
    private String name;

    @JsonView({Views.CPIView.class, Views.VispView.class})
    @Column(name = "SERVICE_PRIORITY",length = 8)
    private int priority;

    @JsonView({Views.CPIView.class, Views.VispView.class})
    @Column(name = "SERVICE_ALLOWANCE")
    private long allowance;

    @JsonView({Views.CPIView.class, Views.VispView.class})
    @Column(name = "HOST_URL")
    private String hostURL;

    @JsonView(Views.CPIView.class)
    @Column(name = "SERVICE_CHARGE")
    private float charge;

    @JsonView(Views.CPIView.class)
    @Column(name = "SERVICE_OVRG_CHARGE",length = 20)
    private String overageCharge;

    @JsonView(Views.CPIView.class)
    @Column(name = "VOICE_SELECTED")
    private boolean voiceSelected;

    @JsonView(Views.CPIView.class)
    @Column(name = "DATA_SELECTED")
    private boolean dataSelected;

    @JsonView(Views.CPIView.class)
    @Column(name = "TEST_SELECTED")
    private boolean testSelected;

    @JsonView({Views.CPIView.class, Views.VispView.class,Views.PlabAdminView.class})
    @Column(name = "VISP_SERVICE_ID",length = 50)
    private String vispServiceID;

    @JsonView(Views.CPIView.class)
    @Column(name = "SPO_ID",length = 20)
    private String spoID;

    @JsonView(Views.CPIView.class)
    @Column(name = "RBM_PRODUCT_ID",length = 20)
    private String rbmProductID;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "RATING_GROUP_ID")
    @JsonView(Views.CPIView.class)
    private RatingGroup ratingGroup;

    // These are rules triggered to decide if this service instance should
    // process a specific usage record
    @JsonView(Views.VispView.class)
    @OneToMany(mappedBy = "service", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Rule> rules;

    @JsonView(Views.InternalView.class)
    @OneToMany(mappedBy = "service", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ServiceInstance> serviceInstances;

    @JsonView(Views.InternalView.class)
    @ManyToOne
    @JoinColumn(name = "OFFER_ID")
    private Offer offer;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.REFRESH,CascadeType.MERGE})
    @JoinTable(name = "SERVICE_APPLICATION",
            joinColumns = {@JoinColumn(name = "SERVICE_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "REF_APPLICATION_ID", nullable = false, updatable = false)},
            uniqueConstraints = {@UniqueConstraint(columnNames ={"SERVICE_ID","REF_APPLICATION_ID"})})
    @JsonView(Views.CPIView.class)
    private Set<Application> appsClassification;

    @JsonView(Views.CPIView.class)
    @Column(name = "MAX_NUMBER_APPS")
    private int maxNumberOfApps;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "SERVICE_TIMEFRAME", joinColumns = {@JoinColumn(name = "SERVICE_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "TIMEFRAME_ID",nullable = false, updatable = false)},
            uniqueConstraints ={@UniqueConstraint(columnNames = {"SERVICE_ID","TIMEFRAME_ID"})})
    @JsonView(Views.CPIView.class)
    private Set<TimeFrame> timeClassification;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "SERVICE_QOS", joinColumns = {
            @JoinColumn(name = "SERVICE_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "QOS_ID",nullable = false, updatable = false)},
            uniqueConstraints ={@UniqueConstraint(columnNames = {"SERVICE_ID","QOS_ID"})})
    @JsonView(Views.CPIView.class)
    private Set<QoS> qos;

    @JsonView(Views.CPIView.class)
    @Column(name="USER_NAME",length = 20)
    private String userName;

    @JsonView(Views.CPIView.class)
    @Column(name = "ADD_ON")
    private boolean addOn;

    @JsonView(Views.CPIView.class)
    @OneToMany(mappedBy = "service", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ServiceQuestion> options;

    //FIXME : Needs to be removed if not having a use case.
    @Transient
    @JsonView(Views.CPIView.class)
    private Date lastUpdate;

    @Column(name = "CREATE_DTM",nullable = false)
    @JsonView({Views.InternalView.class})
    private Date dateCreated = new Date();

    @Column(name = "CREATE_USR",nullable = false,length = 25)
    @JsonView(Views.InternalView.class)
    private String createdBy = PlabConstants.DEFAULT_CREATED_BY_USER;

    @Transient
    String serviceType;

/*
    @JsonView(Views.MobileFirstView.class)
    @OneToMany(mappedBy = "service", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BillingInfo> billingInfo;
*/

/*
    public List<BillingInfo> getBillingInfo() {
        return billingInfo;
    }

    public void setBillingInfo(List<BillingInfo> billingInfo) {
        this.billingInfo = billingInfo;
    }
*/

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getMaxNumberOfApps() {
        return maxNumberOfApps;
    }

    public void setMaxNumberOfApps(int maxNumberOfApps) {
        this.maxNumberOfApps = maxNumberOfApps;
    }

    public boolean isAddOn() {
        return addOn;
    }

    public void setAddOn(boolean addOn) {
        this.addOn = addOn;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAllowance() {
        return allowance;
    }

    @JsonView(Views.VispView.class)
    public String getServiceType(){
        return serviceType;
    }

    public void setServiceType(String type){
        this.serviceType= type;
    }

    public void setAllowance(long allowance) {
        this.allowance = allowance;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public void addRule(Rule rule) {
        if (this.rules == null)
            this.rules = new ArrayList<Rule>();
        this.rules.add(rule);
    }

    public void removeRule(Rule rule) {
        if (this.rules == null)
            this.rules = new ArrayList<Rule>();
        this.rules.remove(rule);
    }

    @JsonView(Views.InternalView.class)
    public List<ServiceInstance> getServiceInstances() {
        return serviceInstances;
    }

    public void setServiceInstances(List<ServiceInstance> serviceInstances) {
        this.serviceInstances = serviceInstances;
    }

    public void addServiceInstances(ServiceInstance serviceInstance) {
        if (this.serviceInstances == null)
            this.serviceInstances = new ArrayList<ServiceInstance>();
        this.serviceInstances.add(serviceInstance);
    }

    public void removeServiceInstance(ServiceInstance serviceInstance) {
        if (this.serviceInstances == null)
            this.serviceInstances = new ArrayList<ServiceInstance>();
        this.serviceInstances.remove(serviceInstance);
    }


    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    @JsonView(Views.InternalView.class)
    public Offer getOffer() {
        return offer;
    }

    @JsonView(Views.InternalView.class)
    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public float getCharge() {
        return charge;
    }

    public void setCharge(float charge) {
        this.charge = charge;
    }

    public String getVispServiceID() {
        return vispServiceID;
    }

    public void setVispServiceID(String vispServiceID) {
        this.vispServiceID = vispServiceID;
    }

    public String getSpoID() {
        return spoID;
    }

    public void setSpoID(String spoID) {
        this.spoID = spoID;
    }

    public String getRbmProductID() {
        return rbmProductID;
    }

    public void setRbmProductID(String rbmProductID) {
        this.rbmProductID = rbmProductID;
    }

    public Set<Application> getAppsClassification() {
        return appsClassification;
    }

    public void setAppsClassification(Set<Application> appsClassification) {
        this.appsClassification = appsClassification;
    }

    public Set<TimeFrame> getTimeClassification() {
        return timeClassification;
    }

    public void setTimeClassification(Set<TimeFrame> timeClassification) {
        this.timeClassification = timeClassification;
    }

    public Set<QoS> getQos() {
        return qos;
    }

    public void setQos(Set<QoS> qos) {
        this.qos = qos;
    }

    public Set<ServiceQuestion> getOptions() {
        return options;
    }

    public void setOptions(Set<ServiceQuestion> options) {
        this.options = options;
    }

    public String getOverageCharge() {
        return overageCharge;
    }

    public void setOverageCharge(String overageCharge) {
        this.overageCharge = overageCharge;
    }

    public boolean isVoiceSelected() {
        return voiceSelected;
    }

    public void setVoiceSelected(boolean voiceSelected) {
        this.voiceSelected = voiceSelected;
    }

    public boolean isDataSelected() {
        return dataSelected;
    }

    public void setDataSelected(boolean dataSelected) {
        this.dataSelected = dataSelected;
    }

    public boolean isTestSelected() {
        return testSelected;
    }

    public void setTestSelected(boolean testSelected) {
        this.testSelected = testSelected;
    }

    public RatingGroup getRatingGroup() {
        return ratingGroup;
    }

    public void setRatingGroup(RatingGroup ratingGroup) {
        this.ratingGroup = ratingGroup;
    }

    public String getHostURL() {
        return hostURL;
    }

    public void setHostURL(String hostURL) {
        this.hostURL = hostURL;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }



}
