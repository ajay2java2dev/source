package com.vzwcorp.pricinglab.actions;

import com.vzwcorp.pricinglab.vo.Event;
import com.vzwcorp.pricinglab.vo.RuleInstance;
import com.vzwcorp.pricinglab.vo.ServiceInstance;

public interface IActionExecutor {

	public Object execute(Event event, RuleInstance ruleInstance, ServiceInstance serviceInstance);

}
