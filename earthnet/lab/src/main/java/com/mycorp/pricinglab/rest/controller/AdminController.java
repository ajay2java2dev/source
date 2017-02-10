package com.vzwcorp.pricinglab.rest.controller;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.hazelcast.core.IMap;
import com.vzwcorp.pricinglab.quartz.CustomerJobDetails;
import com.vzwcorp.pricinglab.quartz.PLabJobScheduler;
import com.vzwcorp.pricinglab.service.DistributedService;
import com.vzwcorp.pricinglab.service.ProductManager;
import com.vzwcorp.pricinglab.utility.PricingLabUtility;
import com.vzwcorp.pricinglab.utility.VariableDepthCopier;
import com.vzwcorp.pricinglab.vo.*;
import com.vzwcorp.pricinglab.vo.repository.OfferRepository;
import com.vzwcorp.pricinglab.vo.repository.PlabCustRepository;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@Transactional("blTransactionManager")
public class AdminController {

	@Autowired
	OfferRepository offerRepository;

	@Autowired
	PricingLabUtility utility;

	@Autowired
	PLabJobScheduler jobScheduler;

	@Autowired
	Scheduler scheduler;

	@Autowired
	DistributedService distributedService;

	@Autowired
	PlabCustRepository plabCustRepository;

	@Autowired
	ProductManager productManager;

	ObjectMapper mapper = new ObjectMapper();

	public AdminController() {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
	}

	static Logger logger = LogManager.getLogger(OfferController.class);

	public Offer createNewOffer(Offer offer) {
		Offer newOffer = null;
		if (offer != null) {
			VariableDepthCopier copier = new VariableDepthCopier();
			newOffer = (Offer) copier.copy(offer);

			String newSuffix = "_" + RandomStringUtils.randomAlphanumeric(3);
			newOffer.setName(newOffer.getName() + newSuffix);
			newOffer.setOfferId(null);
			newOffer.setOfferInstances(null);

			// Add Services
			List<Service> newServices = new ArrayList<>();
			for (Service service : offer.getServices()) {
				Service newService = (Service) copier.copy(service);
				newService.setName(service.getName() + newSuffix);
				newService.setServiceId(null);
				newService.setServiceInstances(null);
				newService.setOffer(newOffer);

				// Add Rules for the given Services.
				List<Rule> newRules = new ArrayList<Rule>();
				for (Rule rule : service.getRules()) {
					Rule newRule = (Rule) copier.copy(rule);
					newRule.setRuleId(null);
					newRule.setRuleInstances(null);
					newRules.add(newRule);
				}
				newService.setRules(newRules);

				// Add Choices
				Set<ServiceQuestion> newServiceQuestions = new HashSet<ServiceQuestion>();
				for (ServiceQuestion question : service.getOptions()) {
					ServiceQuestion newServiceQuestion = (ServiceQuestion) copier
							.copy(question);
					newServiceQuestion.setSelectionPageId(null);
					newServiceQuestion.setServiceAnswers(null);

					List<Choice> newChoices = new ArrayList<Choice>();
					for (Choice choice : question.getChoice()) {
						Choice newChoice = (Choice) copier.copy(choice);
						newChoice.setId(null);
						newChoice.setChoice(null); // Choice Instances
						newChoices.add(newChoice);
					}

					newServiceQuestion.setChoice(newChoices);
					newServiceQuestions.add(newServiceQuestion);
				}
				newService.setOptions(newServiceQuestions);

				newServices.add(newService); // join service and offer
			}

			// ADD Surveys
			List<Survey> newSurveys = new ArrayList<Survey>();
			for (Survey survey : offer.getSurveys()) {
				Survey newSurvey = (Survey) copier.copy(survey);
				newSurvey.setSurveyId(null);
				newSurvey.setOffer(newOffer);
				newSurveys.add(newSurvey);
			}
			newOffer.setSurveys(newSurveys);

			// ADD Simple Pages (T&C, OfferOptions)
			List<SimplePage> newSimplePages = new ArrayList<SimplePage>();
			for (SimplePage simplePage : offer.getSimplePages()) {
				SimplePage newSimplePage = (SimplePage) copier.copy(simplePage);
				newSimplePage.setSimplePageId(null);
				newSimplePage.setOffer(newOffer);
				newSimplePages.add(newSimplePage);
			}
			newOffer.setSimplePages(newSimplePages);

			newOffer.setServices(newServices);

		}
		return newOffer;
	}

	@RequestMapping(value = "/admin/duplicate/offer", method = RequestMethod.POST)
	public ResponseEntity duplicateOffer(
			@RequestParam(value = "offerId") Long fromOfferId) {
		if (fromOfferId != null) {
			// copy to offer
			Offer offer = offerRepository.findByOfferId(fromOfferId);
			if (offer != null) {
				Offer newOffer = createNewOffer(offer);
				// copy to new service (not the base service) and copy visp
				// service id as well.
				offerRepository.saveAndFlush(newOffer);
				// copy choices
				logger.info("New Offer Created, Offer ID {} ",
						newOffer.getOfferId());
			} else {
				logger.info(
						"Duplicate offer from offer id : {} failed. Offer ID doesn't exist ",
						fromOfferId);
				return new ResponseEntity("Offer ID doesn't exist",
						HttpStatus.BAD_REQUEST);
			}
		}
		return null;
	}

	@RequestMapping(value = "/admin/jobs/cust", method = RequestMethod.DELETE)
	public ResponseEntity removeJobForCustomer(
			@RequestParam("customerId") String customerId) {
		List<PlabCust> plabCustList = plabCustRepository.findByCustIdNo(Long
				.parseLong(customerId));
		for (PlabCust plabCust : plabCustList) {
			jobScheduler.deleteJobsForCustomer(plabCust);
		}
		return new ResponseEntity("Attempted to remove jobs for customer : "
				+ customerId, HttpStatus.OK);
	}

	@RequestMapping(value = "/admin/jobs/jobkey", method = RequestMethod.DELETE)
	public ResponseEntity removeAllJobs(
			@RequestParam("jobKeyName") String jobKeyName) {

		try {
			for (String jobGroupName : scheduler.getJobGroupNames()) {
				GroupMatcher<JobKey> groupMatcher = GroupMatcher
						.groupEquals(jobGroupName);
				Set<JobKey> jobKeys = scheduler.getJobKeys(groupMatcher);
				for (JobKey jobKey : jobKeys) {
					if (jobKey.getName().contains(jobKeyName)) {
						scheduler.deleteJob(jobKey);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return new ResponseEntity("Attempted to remove all jobs", HttpStatus.OK);
	}

	@RequestMapping(value = "/admin/jobs", method = RequestMethod.GET)
	public ResponseEntity getAllJobDetails() {

		List<CustomerJobDetails> jobList = jobScheduler.listAllJobs();

		if (jobList != null && !jobList.isEmpty()) {
			ObjectMapper writer = new ObjectMapper();
			try {
				Collections.sort(jobList);
				String jsonInString = writer.writeValueAsString(jobList);
				return new ResponseEntity(jsonInString, HttpStatus.OK);
			} catch (Exception e) {
				logger.error("getAllJobDetails parsing error : "
						+ e.getMessage());
				return new ResponseEntity(
						"Error parsing job details list to json ",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity("No Scheduled Jobs found !",
					HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/admin/jobs/{custid}/{acctnbr}", method = RequestMethod.GET)
	public ResponseEntity getJobDetails(
			@PathVariable("custid") String customerID,
			@PathVariable("acctnbr") String accountNumber) {
		logger.debug("getJobDetails() custid: {} , accntNo: {}", customerID,
				accountNumber);
		List<CustomerJobDetails> jobList = jobScheduler.getJobDetailsFor(
				customerID, accountNumber);

		ObjectMapper writer = new ObjectMapper();
		try {
			if (jobList != null && !jobList.isEmpty()) {
				Collections.sort(jobList);
			}
			String jsonInString = writer.writeValueAsString(jobList);
			return new ResponseEntity(jsonInString, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("getJobDetails parsing error : " + e.getMessage());
			return new ResponseEntity(
					"Error parsing job details list to json ",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/admin/hazelcast/map/{name}", method = RequestMethod.POST)
	public ResponseEntity getHazelCastMaps(
			@PathVariable("mapName") String mapName) {

		/*
		 * IMap m1=
		 * distributedService.getHazelcastInstance().getMap("REG_GRID_MAP");
		 * IMap m2=
		 * distributedService.getHazelcastInstance().getMap("REG_GRID_MAP");
		 * IMap m2=
		 * distributedService.getHazelcastInstance().getMap("PLAB_USAGE");
		 */

		IMap m1 = distributedService.getHazelcastInstance().getMap(
				"REG_GRID_MAP");

		return new ResponseEntity(m1, HttpStatus.OK);
	}

	@RequestMapping(value = "/admin/hazelcast/lock/{lockName}", method = RequestMethod.POST)
	public ResponseEntity getLockStatus(
			@PathVariable("lockName") String lockName) {
		Boolean b = distributedService.getHazelcastInstance().getLock(lockName)
				.isLocked();
		return new ResponseEntity(b, HttpStatus.OK);
	}

	@RequestMapping(value = "/admin/hazelcast/unlock/{lockName}", method = RequestMethod.POST)
	public ResponseEntity unlockLock(@PathVariable("lockName") String lockName) {
		distributedService.getHazelcastInstance().getLock(lockName)
				.forceUnlock();
		Boolean b = distributedService.getHazelcastInstance().getLock(lockName)
				.isLocked();
		return new ResponseEntity(b, HttpStatus.OK);
	}

	@RequestMapping(value = "/admin/hazelcast/long/{longName}", method = RequestMethod.POST)
	public ResponseEntity getLongValue(@PathVariable("longName") String longName) {
		long b = distributedService.getHazelcastInstance()
				.getAtomicLong(longName).get();
		return new ResponseEntity(b, HttpStatus.OK);
	}

	@RequestMapping(value = "/admin/hazelcast/long/{longName}/set/{longValue}", method = RequestMethod.POST)
	public ResponseEntity setLongValue(
			@PathVariable("longName") String longName,
			@PathVariable("longValue") Long longValue) {
		distributedService.getHazelcastInstance().getAtomicLong(longName)
				.set(longValue);
		return new ResponseEntity(distributedService.getHazelcastInstance()
				.getAtomicLong(longName).get(), HttpStatus.OK);
	}

	/**
	 * Added newly to accommodate new changes of PLAB
	 */
	@RequestMapping(value = "/admin/offer", method = RequestMethod.GET)
	public ResponseEntity getCustomerDetail(
			@RequestParam(value = "id") String id) {
		logger.debug("AdminController getOfferDetails() offer id is " + id);
		List<PlabCust> invtDetailsList = productManager.getCustomerDetails(Long
				.parseLong(id));
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.setDateFormat(df);
		ObjectWriter writer = mapper.writerWithView(Views.PlabAdminView.class);
		String jsonInString = null;
		try {
			jsonInString = writer.writeValueAsString(invtDetailsList);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(), e);
		}
		return new ResponseEntity(jsonInString, HttpStatus.OK);
	}
}
