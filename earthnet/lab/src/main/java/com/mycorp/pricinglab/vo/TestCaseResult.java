/**
 * 
 */
package com.vzwcorp.pricinglab.vo;

/**
 * @author kovelde
 *
 */
public class TestCaseResult {

	private String step;
	private String response;
	private String status;
	
	public TestCaseResult(String step, String response, String status) {
		this.step = step;
		this.response = response;
		this.status = status;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
