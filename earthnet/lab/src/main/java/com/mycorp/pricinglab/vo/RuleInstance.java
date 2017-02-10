package com.vzwcorp.pricinglab.vo;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import com.vzwcorp.pricinglab.utility.PricingLabUtility;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "SERVICE_RULE_INSTANCE")
public class RuleInstance {

	@Id
	@Column(name = "RULE_INSTANCE_ID")
	@SequenceGenerator(name = "RULE_INSTANCE_GEN", sequenceName = "RULE_INSTANCE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RULE_INSTANCE_GEN")
	@JsonView(Views.InternalView.class)	
	private Long ruleInstanceId;

	@Column(name="RULE_INSTANCE_NAME",length = 50)
	@JsonView(Views.InternalView.class)	
	private String name;

	@Column(name="RULE_TYPE",length = 20)
	private String ruleType;

	@Column(name="EXPRESSION",length = 20)
	private String expression; // "at 6 pm"/ every 10 minutes//
				
	@Column(name="EXPRESSION_TYPE",length = 50)
	private String expressionType; // byApplication, byTime, byVolume, byLocation, byAllowance

	@Column(name = "JSON_ATTRIBUTE",length =1000)
	@JsonView(Views.InternalView.class)	
	private String	attributesJson;	
	
	@ManyToOne
	@JoinColumn(name = "SERVICE_INSTANCE_ID")
	@JsonView(Views.InternalView.class)	
	private ServiceInstance serviceInstance;

	@ManyToOne
	@JoinColumn(name = "RULE_ID")
	@JsonView(Views.InternalView.class)	
	private Rule rule;

	@Column(name = "CREATE_DTM",nullable = false)
	@JsonView(Views.InternalView.class)
	private Date dateCreated = new Date();

	@Column(name = "CREATE_USR",nullable = false,length = 25)
	@JsonView({Views.InternalView.class, Views.CPIView.class})
	private String createdBy = PlabConstants.DEFAULT_CREATED_BY_USER;

	@Column(name = "END_DTM",nullable = false)
	@JsonView({Views.InternalView.class, Views.CPIView.class})
	private Timestamp endDate = PricingLabUtility.getDefaultEndTimeStamp();
	
	public RuleInstance() {
	}

	public RuleInstance(Rule rule, ServiceInstance serviceInstance) {
		this.serviceInstance= serviceInstance;
		this.attributesJson = rule.getAttributesJson();
		this.expression = rule.getExpression();
		this.expressionType = rule.getExpressionType();
		this.ruleType = rule.getRuleType();
		this.name = rule.getName() + "_Instance";
		this.rule = rule;
	}
	public RuleInstance(ServiceInstance serviceInstance,Rule rule,Map<String,String> attributesJsonChoice) {
		this.serviceInstance= serviceInstance;
		if(rule.getRuleType().equals(Rule.qosRuleType) && rule.getExpressionType().equals(Rule.bySpeedExpressionType)){
			setAttributes(attributesJsonChoice);
			this.expression = attributesJsonChoice.get(attributesJsonChoice.keySet().toArray()[0]);
		}
		else if(rule.getRuleType().equals(Rule.classficationRuleType) && rule.getExpressionType().equals(Rule.byApplicationExpressionType))
			this.expression=attributesJsonChoice.get(attributesJsonChoice.keySet().toArray()[0]);
		else{
			this.attributesJson = rule.getAttributesJson();
			this.expression = rule.getExpression();
		}
		this.expressionType = rule.getExpressionType();
		this.ruleType = rule.getRuleType();
		this.name = rule.getName() + "_Instance";
		this.rule = rule;
	}

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}



	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}



	public String getRuleType() {
		return ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getExpressionType() {
		return expressionType;
	}

	public void setExpressionType(String expressionType) {
		this.expressionType = expressionType;
	}

	@JsonView(Views.VispView.class)
	public Map<String, String> getAttributes() {
		if ( attributesJson == null)
			return new HashMap<String,String>();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> map = new HashMap<String, String>();

		// convert JSON string to Map
		try {
			map = mapper.readValue(attributesJson, new TypeReference<Map<String, String>>(){});
			return map;
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
		
		return null;
	}

	public void setAttributes(Map<String, String> attributes) {
		ObjectMapper mapper = new ObjectMapper();
		// convert JSON string to Map
		try {
			attributesJson= mapper.writeValueAsString(attributes);
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


	public ServiceInstance getServiceInstance() {
		return serviceInstance;
	}

	public void setServiceInstance(ServiceInstance serviceInstance) {
		this.serviceInstance = serviceInstance;
	}
	public void addAttribute(String key, String value) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> map = new HashMap<String, String>();

		// convert JSON string to Map
		try {
			if ( attributesJson != null)
				map = mapper.readValue(attributesJson, new TypeReference<Map<String, String>>(){});
			map.put(key, value);
			attributesJson= mapper.writeValueAsString(map);
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

	public void removeAttribute(String key) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> map = new HashMap<String, String>();

		// convert JSON string to Map
		try {
			map = mapper.readValue(attributesJson, new TypeReference<Map<String, String>>(){});
			map.remove(key);
			attributesJson= mapper.writeValueAsString(map);
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


	public Long getRuleInstanceId() {
		return ruleInstanceId;
	}

	public void setRuleInstanceId(Long ruleInstanceId) {
		this.ruleInstanceId = ruleInstanceId;
	}

	public String getAttributesJson() {
		return attributesJson;
	}

	public void setAttributesJson(String attributesJson) {
		this.attributesJson = attributesJson;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
}
