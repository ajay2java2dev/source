package com.vzwcorp.pricinglab.vo;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;

//@Entity
public class Event {

	// Event Types
	public static final String UsageEvent = "usageEvent";
	public static final String UsageThresholdEvent = "usageThresholdEvent";
	public static final String TimerEvent = "timerEvent";
	public static final String PeriodicUpdate = "periodicUpdateEvent";

	//@Id
	private String ID;
	
	private String	type;
	private int	subType;

	private Object eventObject;
	
	private Map<String,String> keys;
	private Map<String,String> attributes;

	private Map<String,Object> eventParameters=new HashMap<String,Object>();

	public Event(String type) {
		this.type= type;
	}

	
	public Object getEventObject() {
		return eventObject;
	}


	public void setEventObject(Object eventObject) {
		this.eventObject = eventObject;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getSubType() {
		return subType;
	}
	public void setSubType(int subType) {
		this.subType = subType;
	}
	public Map<String, String> getKeys() {
		return keys;
	}
	public void setKeys(Map<String, String> keys) {
		this.keys = keys;
	}
	public void addKey(String key, String value) {
		if ( this.keys == null )
			this.keys= new HashMap<String,String>();
		this.keys.put(key, value);
	}
	
	public Map<String, String> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}	
	
	public void addAttribute(String key, String value) {
		if ( this.attributes == null )
			this.attributes= new HashMap<String,String>();
		this.attributes.put(key, value);
	}
	
	public Map<String, Object> getEventParameters() {
		return eventParameters;
	}


	public void setEventParameters(Map<String, Object> eventParameters) {
		this.eventParameters = eventParameters;
	}


	public void addEventParameter(String key, Object value) {
		this.eventParameters.put(key, value);
	}

	public void removeEventParameter(String key) {
		this.eventParameters.remove(key);
	}
}
