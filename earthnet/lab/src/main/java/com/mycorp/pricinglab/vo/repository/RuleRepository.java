package com.vzwcorp.pricinglab.vo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.vzwcorp.pricinglab.vo.Rule;

public interface RuleRepository extends JpaRepository<Rule, Long> {

	public Rule findByRuleId(@Param("ruleId") Long ruleId);
	
	public List<Rule> findByRuleTypeAndExpressionType(@Param("ruleType") String ruleType,@Param("expressionType") String expressionType);
}
