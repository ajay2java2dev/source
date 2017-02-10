package com.vzwcorp.pricinglab.vo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.vzwcorp.pricinglab.vo.Rule;
import com.vzwcorp.pricinglab.vo.RuleInstance;
import com.vzwcorp.pricinglab.vo.ServiceInstance;

public interface RuleInstanceRepository extends JpaRepository<RuleInstance, Long> {

	public RuleInstance findByRuleInstanceId(@Param("ruleInstanceId") Long ruleInstanceId);
	
	public List<RuleInstance> findByServiceInstance(ServiceInstance serviceInstance);
	
	public RuleInstance findByServiceInstanceAndRule(ServiceInstance serviceInstance,Rule rule);
}
