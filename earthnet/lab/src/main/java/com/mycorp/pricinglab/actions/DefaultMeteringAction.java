package com.vzwcorp.pricinglab.actions;

import java.util.HashMap;
import java.util.Map;

import com.vzwcorp.pricinglab.vo.Event;
import com.vzwcorp.pricinglab.vo.ServiceInstance;
import com.vzwcorp.pricinglab.vo.RuleInstance;

public class DefaultMeteringAction implements IActionExecutor{

	@Override
	public Object execute(Event event, RuleInstance ruleInstance, ServiceInstance serviceInstance) {
		
/*		int multiplier= (Integer) ruleInstance.getAttributes().get("multiplier");
		long deltaUsage= (Long)event.getEventParameters().get("usageRecord");
		serviceInstance.counter += deltaUsage * multiplier;
		return multiplier;*/
		return null;
	}
	
}
