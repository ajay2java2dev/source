package com.vzwcorp.pricinglab.vo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonView;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import com.vzwcorp.pricinglab.utility.PricingLabUtility;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "PROD_SERVICE_INSTANCE")
public class ServiceInstance {

    @Id
    @Column(name = "SERVICE_INSTANCE_ID")
    @SequenceGenerator(name = "SERVICE_INSTANCE_GEN", sequenceName = "SERVICE_INSTANCE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVICE_INSTANCE_GEN")
    @JsonView({Views.InternalView.class, Views.PlabAdminView.class})
    private Long serviceInstanceId;

    @Transient
    @JsonView(Views.InternalView.class)
    private Set<ServiceQuestion> options;

    @JsonView({Views.CPIView.class, Views.VispView.class, Views.PlabAdminView.class})
    @Transient
    long allowance;

    @Transient
    String name;

    @JsonView({Views.CPIView.class, Views.VispView.class, Views.PlabAdminView.class})
    @Transient
    String vispServiceID;

    @Column(name = "COUNTER")
    @JsonView(Views.PlabAdminView.class)
    public long counter;

    @Column(name = "LAST_TIME_OF_USAGE")
    @JsonView(Views.InternalView.class)
    private Date lastTimeOfUsage ;

    @Column(name = "FAST_CTR")
    @JsonView(Views.PlabAdminView.class)
    public long fastCounter;
    
    @Column(name = "FASTER_CTR")
    @JsonView(Views.PlabAdminView.class)
    public long fasterCounter;
    
    @Column(name = "FASTEST_CTR")
    @JsonView(Views.PlabAdminView.class)
    public long fastestCounter;

    @Column(name = "PRIORITY")
    @JsonView(Views.VispView.class)
    public int priority;

    @Column(name = "VISP_SERVICE_INSTANCE_ID",length = 50)
    @JsonView({Views.VispView.class,Views.PlabAdminView.class})
    public String vispServiceInstanceId;

    @Column(name = "CREATE_DTM")
    @JsonView(Views.InternalView.class)
    private Date dateCreated = new Date();

    @Column(name = "CREATE_USR",length = 25)
    @JsonView(Views.InternalView.class)
    private String createdBy = PlabConstants.DEFAULT_CREATED_BY_USER;

    @Column(name = "END_DTM")
    @JsonView({Views.InternalView.class,Views.PlabAdminView.class})
    private Timestamp endTmstamp = PricingLabUtility.getDefaultEndTimeStamp();

    //10:15 AM 9/22/2016: DO NOT CHANGE RULES TO RULE INSTANCE AS IT IMPACT VISP CALLS
    @JsonView({Views.InternalView.class,Views.ManagedView.class,Views.CPIView.class,Views.MobileFirstView.class,Views.VispView.class})
    @OneToMany(mappedBy = "serviceInstance", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<RuleInstance> rules = new ArrayList<RuleInstance>();

    @JsonView({Views.MobileFirstView.class, Views.ManagedView.class})
    @OneToMany(mappedBy = "serviceInstance", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BillingInfo> billingInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VERIZON_ENTITY_ID")
    @JsonView(Views.InternalView.class)
    public VerizonEntity verizonEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SERVICE_ID")
    @JsonView({Views.InternalView.class, Views.ManagedView.class})
    public Service service;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OFFER_INSTANCE_ID")
    @JsonView({Views.InternalView.class})
    public OfferInstance offerInstance;

    public ServiceInstance() {
    }

    public ServiceInstance(Service service, VerizonEntity verizonEntity, OfferInstance offerInstance) {
        this.service = service;
        this.verizonEntity = verizonEntity;
        this.offerInstance = offerInstance;
        this.rules = new ArrayList<RuleInstance>();
        for (Rule rule : service.getRules()) {
        	if(!(Rule.qosEOCRuleType.equalsIgnoreCase(rule.getRuleType()))){
            RuleInstance ruleInstance = new RuleInstance(rule, this);
            ruleInstance.setEndDate(PricingLabUtility.getDefaultEndTimeStamp());
            this.rules.add(ruleInstance);
        	}
        }
    }
    
    public ServiceInstance(Service service, VerizonEntity verizonEntity, OfferInstance offerInstance, boolean safetyMode) {
        this.service = service;
        this.verizonEntity = verizonEntity;
        this.offerInstance = offerInstance;
        this.rules = new ArrayList<RuleInstance>();
        for (Rule rule : service.getRules()) {
        	if (rule.getRuleType().equals(Rule.qosRuleType)
					&& rule.getExpressionType().equals(Rule.bySpeedExpressionType)) {
                RuleInstance ruleInstance = new RuleInstance(rule, this);
                ruleInstance.setEndDate(PricingLabUtility.getDefaultEndTimeStamp());
                if(safetyMode){
                	ruleInstance.setExpression(PlabConstants.RULE_EXPRESSION_SAFETY_MODE);
                }
                this.rules.add(ruleInstance);        		
        	} else if(!(Rule.qosEOCRuleType.equalsIgnoreCase(rule.getRuleType()))){
	            RuleInstance ruleInstance = new RuleInstance(rule, this);
	            ruleInstance.setEndDate(PricingLabUtility.getDefaultEndTimeStamp());
	            this.rules.add(ruleInstance);
        	}
        }
    }

    public ServiceInstance(Service service, VerizonEntity verizonEntity, OfferInstance offerInstance, Map<String, String> map) {
        this.service = service;
        this.verizonEntity = verizonEntity;
        this.offerInstance = offerInstance;
        this.priority=service.getPriority();
        this.rules = new ArrayList<RuleInstance>();
        for (Rule rule : service.getRules()) {
        	if(!(Rule.qosEOCRuleType.equalsIgnoreCase(rule.getRuleType()))){
        		  RuleInstance ruleInstance = new RuleInstance(this, rule, map);
                  ruleInstance.setEndDate(PricingLabUtility.getDefaultEndTimeStamp());

                  //Update Speed Tier rule expression type and expression
                  if (Rule.qosRuleType.equalsIgnoreCase(rule.getRuleType()) &&
                          rule.getExpressionType()!=null && Rule.byTimeExpressionType.equalsIgnoreCase(rule.getExpressionType())
                          && service.getName()!=null && service.getName().toLowerCase().contains("speed")) {
                      ruleInstance.setExpressionType(Rule.bySpeedExpressionType);					
                      String bandwidth = map.get(PlabConstants.QOS_RULETYPE_BANDWIDTH);
                      ruleInstance.setExpression(bandwidth!=null?bandwidth.toUpperCase():null);
                      String bandwidthAttr = ruleInstance.getAttributes().get("bandwidth");
                      if (bandwidthAttr!=null && !bandwidthAttr.isEmpty()) {
                          ruleInstance.getAttributes().get("bandwidth").toUpperCase();
                      }
                  }
                  this.rules.add(ruleInstance);
        	}     	
        }
    }

    public Set<ServiceQuestion> getOptions() {
        return service.getOptions();
    }

    public void setOptions(Set<ServiceQuestion> options) {
        this.options = options;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public String getVispServiceInstanceId() {
        return vispServiceInstanceId;
    }

    public void setVispServiceInstanceId(String string) {
        this.vispServiceInstanceId = string;
    }

    public List<BillingInfo> getBillingInfo() {
        return billingInfo;
    }

    public void setBillingInfo(List<BillingInfo> billingInfo) {
        this.billingInfo = billingInfo;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    //10:15 AM 9/22/2016: DO NOT CHANGE RULES TO RULE INSTANCE AS IT IMPACT VISP CALLS
    public List<RuleInstance> getRules() {
        return rules;
    }
    //10:15 AM 9/22/2016: DO NOT CHANGE RULES TO RULE INSTANCE AS IT IMPACT VISP CALLS
    public void setRules(List<RuleInstance> rules) {
        this.rules = rules;
    }

    public void setVispServiceID(String vispServiceID) {
        this.vispServiceID = vispServiceID;
    }

    public OfferInstance getOfferInstance() {
        return offerInstance;
    }

    public void setOfferInstance(OfferInstance offerInstance) {
        this.offerInstance = offerInstance;
    }

    public long getCounter() {
        return counter;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void IncrementCounter(long deltaUsage) {
        this.counter += deltaUsage;
    }

    public Long getServiceInstanceId() {
        return serviceInstanceId;
    }

    public void setServiceInstanceId(Long serviceInstanceId) {
        this.serviceInstanceId = serviceInstanceId;
    }

    public VerizonEntity getVerizonEntity() {
        return verizonEntity;
    }

    public void setVerizonEntity(VerizonEntity verizonEntity) {
        this.verizonEntity = verizonEntity;
    }

    public long getAllowance() {
        return service.getAllowance();
    }

    public void setAllowance(long allowance) {
        this.allowance = allowance;
    }
    
    @JsonView(Views.PlabAdminView.class)
    public String getName() {
        return service.getName();
    }

    @JsonView({Views.VispView.class,Views.PlabAdminView.class})
    public String getServiceType(){
        return this.service.getServiceType();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVispServiceID() {
        return service.getVispServiceID();
    }

    @JsonView(Views.MobileFirstView.class)
    public String getServiceName() {
        return this.service.getName();
    }

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

    @JsonView(Views.PlabAdminView.class)
    public String getSpoID(){
    	return service.getSpoID();
    }

    @JsonView(Views.PlabAdminView.class)
    public String getMdn(){
    	return offerInstance.getMdn();
    }

    public Date getLastTimeOfUsage() {
        return lastTimeOfUsage;
    }

    public void setLastTimeOfUsage(Date lastTimeOfUsage) {
        this.lastTimeOfUsage = lastTimeOfUsage;
    }

	public long getFastCounter() {
		return fastCounter;
	}

	public void setFastCounter(long fastCounter) {
		this.fastCounter = fastCounter;
	}

	public long getFasterCounter() {
		return fasterCounter;
	}

	public void setFasterCounter(long fasterCounter) {
		this.fasterCounter = fasterCounter;
	}

	public long getFastestCounter() {
		return fastestCounter;
	}

	public void setFastestCounter(long fastestCounter) {
		this.fastestCounter = fastestCounter;
	}
    
    
}
