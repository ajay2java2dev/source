package com.vzwcorp.pricinglab.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vzwcorp.pricinglab.vo.RuleInstance;
import com.vzwcorp.pricinglab.vo.repository.RuleInstanceRepository;
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
public class RuleInstanceController {

	static Logger logger = LogManager.getLogger(RuleInstanceController.class);

	@Autowired
	RuleInstanceRepository repository;

	// Get a rule
	@RequestMapping("/ruleinstance")
	public RuleInstance getRule(@RequestParam(value = "id", defaultValue = "0") String ruleInstanceId) {
		logger.debug("RuleInstanceController getRule  ruleInstanceId " + ruleInstanceId);
		RuleInstance ruleInstance = repository.findByRuleInstanceId(Long.valueOf(ruleInstanceId));

		return ruleInstance;
	}

	// List all rules
	@RequestMapping("/ruleinstances")
	public List<RuleInstance> getRuleInstances() {

		logger.debug("RuleInstanceController getRuleInstances");

		ObjectMapper mapper = new ObjectMapper();
		List<RuleInstance> ruleInstances = repository.findAll();
		for (RuleInstance ruleInstance : ruleInstances) {
			logger.debug(ruleInstance.getRuleInstanceId());
			try {
				String jsonInString = mapper.writeValueAsString(ruleInstance);
				logger.debug("RuleInstanceController getRuleInstances jsonInString " + jsonInString);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return ruleInstances;
	}

	// Create a rule
	@RequestMapping(value = "/ruleinstance", method = RequestMethod.POST)
	public RuleInstance addrule(@RequestParam(value = "name", defaultValue = "0") String name,
			@RequestParam(value = "eventType", defaultValue = "0") String eventType,
			@RequestParam(value = "trigger", defaultValue = "0") String triggerExpression) {

		logger.debug("RuleInstanceController addrule");

		RuleInstance ruleInstance = new RuleInstance();
		ruleInstance.setName(name);
		/*ruleInstance.setEventType(eventType);
		ruleInstance.setFilterExpression(triggerExpression);*/
		ruleInstance.setDateCreated(new Date());
		repository.saveAndFlush(ruleInstance);
		return ruleInstance;
	}

	// Delete a rule
	@RequestMapping(value = "/ruleinstance", method = RequestMethod.DELETE)
	public RuleInstance removerule(@RequestParam(value = "ruleinstanceid", defaultValue = "0") String ruleInstanceId) {

		logger.debug("RuleInstanceController removerule ruleInstanceId " + ruleInstanceId);

		RuleInstance ruleInstance = repository.findByRuleInstanceId(Long.valueOf(ruleInstanceId));

		// RuleInstance ruleInstance = repository.findOne(ruleInstanceId);

		if (ruleInstance != null) {
			repository.delete(ruleInstance);
		}
		return ruleInstance;
	}

	// Associate an Action to a rule
	@RequestMapping(value = "/ruleinstance/action", method = RequestMethod.PUT)
	public RuleInstance addActionToRule(
			@RequestParam(value = "ruleinstanceid", defaultValue = "0") String ruleInstanceId,
			@RequestParam(value = "actionid", defaultValue = "0") String actionId) {

		logger.debug(
				"RuleInstanceController addActionToRule ruleInstanceId " + ruleInstanceId + " actionId " + actionId);

		// RuleInstance ruleInstance = repository.findOne(ruleInstanceId);
		RuleInstance ruleInstance = repository.findByRuleInstanceId(Long.valueOf(ruleInstanceId));

		// rule.addAction(action);
		repository.saveAndFlush(ruleInstance);
		return ruleInstance;
	}

	// Deassociate a Rule to a rule
	@RequestMapping(value = "/ruleinstance/action", method = RequestMethod.DELETE)
	public RuleInstance removeActionFromRule(
			@RequestParam(value = "ruleinstanceid", defaultValue = "0") String ruleInstanceId,
			@RequestParam(value = "actionid", defaultValue = "0") String actionId) {

		logger.debug("RuleInstanceController removeActionFromRule ruleInstanceId " + ruleInstanceId + " actionId "
				+ actionId);

		// RuleInstance ruleInstance = repository.findOne(ruleInstanceId);

		RuleInstance ruleInstance = repository.findByRuleInstanceId(Long.valueOf(ruleInstanceId));

		// rule.removeAction(action);
		repository.saveAndFlush(ruleInstance);
		return ruleInstance;
	}

}
