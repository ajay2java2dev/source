package com.vzwcorp.pricinglab.vo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "SERVICE_RULE")
public class Rule {

    public static String classficationRuleType = "Classification";
    public static String reportingRuleType = "Reporting";
    public static String qosRuleType = "QOS";
    public static String qosEOCRuleType = "EOC";
    public static String qosExpressionTypeStr = "expressionType";

    public static String byApplicationExpressionType = "byApplication";
    public static String byVolumeExpressionType = "byVolume";
    public static String byTimeExpressionType = "byTime";
    public static String byLocationExpressionType = "byLocation";
    public static String bySpeedExpressionType = "bySpeed";

    @Id
    @Column(name = "RULE_ID")
    @SequenceGenerator(name = "RULE_GEN", sequenceName = "RULE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RULE_GEN")
    @JsonView({Views.InternalView.class, Views.VispSupportView.class})
    private Long ruleId;

    @Column(name = "RULE_NAME",length = 50)
    @JsonView(Views.InternalView.class)
    private String name;

    @Column(name = "RULE_TYPE",length = 20)
    @JsonView({Views.VispView.class, Views.VispSupportView.class})
    private String ruleType; // Classification, Reporting, QOS

    @Column(name = "EXPRESSION_TYPE",length = 20)
    @JsonView({Views.VispView.class, Views.VispSupportView.class})
    private String expressionType; // byApplication, byTime, byVolume, byLocation, byAllowance

    @Column(name = "EXPRESSION",length = 50)
    @JsonView({Views.VispView.class, Views.VispSupportView.class})
    private String expression; // "at 6 pm"/ every 10 minutes//		// app=facebook

    @JsonView(Views.InternalView.class)
    @OneToMany(mappedBy = "rule", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RuleInstance> ruleInstances;

    @Column(name = "JSON_ATTRIBUTES",length = 1000)
    @JsonView(Views.InternalView.class)
    private String attributesJson;

    //@Transient
    @ManyToOne
    @JoinColumn(name = "SERVICE_ID")
    @JsonBackReference
    @JsonView(Views.InternalView.class)
    private Service service;

    @Column(name = "CREATE_DTM",nullable = false)
    @JsonView(Views.InternalView.class)
    private Date dateCreated = new Date();

    @Column(name = "CREATE_USR",nullable = false,length = 25)
    @JsonView({Views.InternalView.class, Views.CPIView.class})
    private String createdBy = PlabConstants.DEFAULT_CREATED_BY_USER;

    //Constructor(s)
    public Rule() {
    }

    public Rule(String name, String ruleType, String expressionType, String expression) {
        this.ruleType = ruleType;
        this.expression = expression;
        this.expressionType = expressionType;
        this.name = name;
    }

    //Getters and Setters
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getName() {
        return name;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String filterExpression) {
        this.expression = filterExpression;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String eventType) {
        this.ruleType = eventType;
    }

    public String getExpressionType() {
        return expressionType;
    }

    public void setExpressionType(String expressionType) {
        this.expressionType = expressionType;
    }

    @JsonView(Views.VispView.class)
    public Map<String, String> getAttributes() {
        if (attributesJson == null)
            return new HashMap<String, String>();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = new HashMap<String, String>();

        // convert JSON string to Map
        try {
            map = mapper.readValue(attributesJson, new TypeReference<Map<String, String>>() {
            });
            return map;
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setAttributes(Map<String, String> attributes) {
        ObjectMapper mapper = new ObjectMapper();
        // convert JSON string to Map
        try {
            attributesJson = mapper.writeValueAsString(attributes);
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getAttributesJson() {
        return attributesJson;
    }

    public void setAttributesJson(String attributesJson) {
        this.attributesJson = attributesJson;
    }

    public void addAttribute(String key, String value) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = new HashMap<String, String>();
        // convert JSON string to Map
        try {
            if (attributesJson != null)
                map = mapper.readValue(attributesJson, new TypeReference<Map<String, String>>() {
                });
            map.put(key, value);
            attributesJson = mapper.writeValueAsString(map);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeAttribute(String key) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = new HashMap<String, String>();

        // convert JSON string to Map
        try {
            map = mapper.readValue(attributesJson, new TypeReference<Map<String, String>>() {
            });
            map.remove(key);
            attributesJson = mapper.writeValueAsString(map);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public List<RuleInstance> getRuleInstances() {
        return ruleInstances;
    }

    public void setRuleInstances(List<RuleInstance> ruleInstances) {
        this.ruleInstances = ruleInstances;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }



}
