package com.vzwcorp.pricinglab.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vzwcorp.pricinglab.vo.Rule;
import com.vzwcorp.pricinglab.vo.repository.RuleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class RuleController {

	static Logger logger = LogManager.getLogger(RuleController.class);

	@Autowired
	RuleRepository repository;

	// Get a rule
	@RequestMapping("/rule")
	public Rule getRule(@RequestParam(value = "id", defaultValue = "0") String ruleId) {

		logger.debug("RuleController ruleId is " + ruleId);
		// Rule rule = repository.findOne(ruleId);

		Rule rule = repository.findByRuleId(Long.valueOf(ruleId));

		return rule;
	}

	// List all rules
	@RequestMapping("/rules")
	public List<Rule> getRules() {

		logger.debug("RuleController getRules ");

		ObjectMapper mapper = new ObjectMapper();
		List<Rule> rules = repository.findAll();
		for (Rule rule : rules) {
			logger.debug("RuleController getRules ruleid  "+rule.getRuleId());
			try {
				String jsonInString = mapper.writeValueAsString(rule);
				logger.debug("RuleController getRules jsonInString  "+jsonInString);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return rules;
	}

	// Create a rule
	@RequestMapping(value = "/rule", method = RequestMethod.POST)
	public Rule addrule(@RequestParam(value = "name", defaultValue = "0") String name,
			@RequestParam(value = "eventType", defaultValue = "0") String eventType,
			@RequestParam(value = "trigger", defaultValue = "0") String triggerExpression) {

		logger.debug("RuleController addrule ");

		Rule rule = new Rule();
		rule.setName(name);
		rule.setRuleType(eventType);
		rule.setExpression(triggerExpression);
		rule.setDateCreated(new Date());
		repository.saveAndFlush(rule);
		return rule;
	}

	// Delete a rule
	@RequestMapping(value = "/rule", method = RequestMethod.DELETE)
	public Rule removerule(@RequestParam(value = "ruleid", defaultValue = "0") String ruleId) {

		logger.debug("RuleController removerule ruleId " + ruleId);

		// Rule rule = repository.findOne(ruleId);
		Rule rule = repository.findByRuleId(Long.valueOf(ruleId));

		if (rule != null) {
			repository.delete(rule);
		}
		return rule;
	}

	// Associate an Action to a rule
	@RequestMapping(value = "/rule/action", method = RequestMethod.PUT)
	public Rule addActionToRule(@RequestParam(value = "ruleid", defaultValue = "0") String ruleId,
			@RequestParam(value = "actionid", defaultValue = "0") String actionId) {

		logger.debug("RuleController addActionToRule ruleId " + ruleId + " actionId " + actionId);

		Rule rule = repository.findByRuleId(Long.valueOf(ruleId));

		repository.saveAndFlush(rule);
		return rule;
	}

	// Deassociate a Rule to a rule
	@RequestMapping(value = "/rule/action", method = RequestMethod.DELETE)
	public Rule removeActionFromRule(@RequestParam(value = "ruleid", defaultValue = "0") String ruleId,
			@RequestParam(value = "actionid", defaultValue = "0") String actionId) {

		logger.debug("RuleController removeActionFromRule ruleId " + ruleId + " actionId " + actionId);

		Rule rule = repository.findByRuleId(Long.valueOf(ruleId));

		// Rule rule = repository.findOne(ruleId);

		// rule.removeAction(action);
		repository.saveAndFlush(rule);
		return rule;
	}

}
