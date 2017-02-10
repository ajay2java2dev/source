package com.vzwcorp.pricinglab.vo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResponseInfo {

	private static Logger logger = LogManager.getLogger(ResponseInfo.class);
	private String locale = "EN";
	private String message;
	private String userMessage;
	private String code;
	private String type;

	public ResponseInfo () {
		logger.trace("Response info created.");
	}

	public ResponseInfo(String code, String message, String userMessage, String type){
		this.code = code;
		this.message = message;
		this.userMessage = userMessage;
		this.type = type;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
