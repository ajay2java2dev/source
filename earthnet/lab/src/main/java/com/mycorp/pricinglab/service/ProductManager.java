package com.vzwcorp.pricinglab.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.core.IQueue;
import com.vzwcorp.pricinglab.aspects.LoggingAspect;
import com.vzwcorp.pricinglab.constants.DvsConstants;
import com.vzwcorp.pricinglab.constants.OneMessageType;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import com.vzwcorp.pricinglab.constants.SearchStrategy;
import com.vzwcorp.pricinglab.helper.ProductHelper;
import com.vzwcorp.pricinglab.loader.profile.lookup.repository.RefGridPlabCustRepository;
import com.vzwcorp.pricinglab.loader.profile.rbm.repository.AccExternalActionRepository;
import com.vzwcorp.pricinglab.loader.profile.rbm.repository.AccountRepository;
import com.vzwcorp.pricinglab.loader.profile.rbm.repository.ExternalActionMappingRepository;
import com.vzwcorp.pricinglab.loader.profile.ubsr.repository.SubCustAcctMdnRepository;
import com.vzwcorp.pricinglab.loader.profile.ubsr.repository.SubCustBlCycleRepository;
import com.vzwcorp.pricinglab.profile.lookup.vo.RefGridPlabCust;
import com.vzwcorp.pricinglab.profile.rbm.vo.AccExternalAction;
import com.vzwcorp.pricinglab.profile.rbm.vo.AccExternalActionPK;
import com.vzwcorp.pricinglab.profile.rbm.vo.Account;
import com.vzwcorp.pricinglab.profile.rbm.vo.ExternalActionMapping;
import com.vzwcorp.pricinglab.profile.vo.SubCustAcctMdn;
import com.vzwcorp.pricinglab.quartz.PLabJobScheduler;
import com.vzwcorp.pricinglab.utility.PricingLabUtility;
import com.vzwcorp.pricinglab.vo.Choice;
import com.vzwcorp.pricinglab.vo.ChoiceInstance;
import com.vzwcorp.pricinglab.vo.ChoiceInstanceHistory;
import com.vzwcorp.pricinglab.vo.Offer;
import com.vzwcorp.pricinglab.vo.OfferInstance;
import com.vzwcorp.pricinglab.vo.OfferMdnOption;
import com.vzwcorp.pricinglab.vo.PlabCust;
import com.vzwcorp.pricinglab.vo.Rule;
import com.vzwcorp.pricinglab.vo.RuleInstance;
import com.vzwcorp.pricinglab.vo.ServiceInstance;
import com.vzwcorp.pricinglab.vo.ServiceQuestion;
import com.vzwcorp.pricinglab.vo.SimplePage;
import com.vzwcorp.pricinglab.vo.Survey;
import com.vzwcorp.pricinglab.vo.SurveyAnswer;
import com.vzwcorp.pricinglab.vo.SurveyQuestion;
import com.vzwcorp.pricinglab.vo.VerizonEntity;
import com.vzwcorp.pricinglab.vo.repository.ApplicationRepository;
import com.vzwcorp.pricinglab.vo.repository.ChoiceInstanceHistoryRepository;
import com.vzwcorp.pricinglab.vo.repository.ChoiceInstanceRepository;
import com.vzwcorp.pricinglab.vo.repository.OfferInstanceRepository;
import com.vzwcorp.pricinglab.vo.repository.OfferRepository;
import com.vzwcorp.pricinglab.vo.repository.PlabCustRepository;
import com.vzwcorp.pricinglab.vo.repository.RuleInstanceRepository;
import com.vzwcorp.pricinglab.vo.repository.RuleRepository;
import com.vzwcorp.pricinglab.vo.repository.ServiceInstanceRepository;
import com.vzwcorp.pricinglab.vo.repository.ServiceRepository;
import com.vzwcorp.pricinglab.vo.repository.VerizonEntityRepository;

@Service
public class ProductManager {

	static Logger logger = LogManager.getLogger(ProductManager.class);

	@Value("${provisioning.spo}")
	String provisioningSpo;

	@Value("${fromLocal}")
	private Boolean fromLocal;

	@Value("${hostname.list:tdc,odc,sdc}")
	public String dcNameList;

	@Value("${rbm.timertask.delay:10000}")
	public long timerTaskDelay;

	@Autowired
	OfferInstanceRepository offerInstanceRepository;

	@Autowired
	ChoiceInstanceRepository choiceInstanceRepository;

	@Autowired
	PlabCustRepository plabCustRepository;

	@Autowired
	RefGridPlabCustRepository refGridPlabCustRepository;

	@Autowired
	VerizonEntityRepository verizonEntityRepository;

	@Autowired
	GridService gridService;

	@Autowired
	SubCustBlCycleRepository subCustBlCycleRepository;

	@Autowired
	PricingLabUtility pricingLabUtility;

	@Autowired
	VerizonEntityRepository vzwEntityRepository;

	@Autowired
	ProductHelper helper;

	@Autowired
	RuleRepository ruleRepository;

	@Autowired
	RuleInstanceRepository ruleInstanceRepository;

	@Autowired
	ServiceInstanceRepository serviceInstanceRepository;

	@Autowired
	ServiceManager serviceManager;

	@Autowired
	PLabJobScheduler jobScheduler;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	AccExternalActionRepository accExternalActionRepository;

	@Autowired
	ExternalActionMappingRepository externalActionMappingRepository;

	@Autowired
	ChoiceInstanceHistoryRepository choiceInstanceHistoryRepository;

	@Autowired
	ServiceRepository serviceRepository;

	@Autowired
	ProductHelper productHelper;

	@Autowired
	SubCustAcctMdnRepository subCustAcctMdnRepository;

	@Autowired
	PricingLabUtility utility;

	@Autowired
	ApplicationRepository appRepository;

	@Autowired
	OneMessageService oneMessageService;

	@Autowired
	DistributedService distributedService;

	@Autowired
	DvsManager dvsManager;

	@Autowired
	OfferRepository offerRepository;

	/**
	 * Update status for all account members
	 * 
	 * @param accountMembers
	 * @param status
	 * @param offer
	 * @return
	 */
	public void updateTermsAndConditionsForMembers(
			List<SubCustAcctMdn> accountMembers, Integer status, Offer offer,
			OfferInstance offerInstance) {
		Map<String, Object> receivedCustomerEntities = null;

		for (SubCustAcctMdn subCustAcctMdn : accountMembers) {

			// add to thread-context
			ThreadContext.put(LoggingAspect.EVENT_PROP_CUSTOMER_ID,
					subCustAcctMdn.getCustIdNo() != null ? subCustAcctMdn
							.getCustIdNo().toString() : null);
			ThreadContext.put(LoggingAspect.EVENT_PROP_ACCOUNT_NUMBER,
					subCustAcctMdn.getAcctNo() != null ? subCustAcctMdn
							.getAcctNo().toString() : null);
			ThreadContext.put(LoggingAspect.EVENT_PROP_MDN, subCustAcctMdn
					.getMdn() != null ? subCustAcctMdn.getMdn().toString()
					: null);

			VerizonEntity vzEntityNew = vzwEntityRepository
					.findByMdn(subCustAcctMdn.getMdn());
			if (vzEntityNew == null) {
				receivedCustomerEntities = createOrUpdatePricingLabCustomers(
						subCustAcctMdn.getCustIdNo(), subCustAcctMdn.acctNo,
						subCustAcctMdn.getMdn(), offer,
						subCustAcctMdn.getMtnEffDt());
				if (receivedCustomerEntities != null
						&& !receivedCustomerEntities.isEmpty()) {
					vzEntityNew = (VerizonEntity) receivedCustomerEntities
							.get(PlabConstants.VERIZON_ENTITY);
				}
			}
			if (offerInstance != null
					&& offerInstance.getVerizonEntity().getVerizonEntityId()
							.equals(vzEntityNew.getVerizonEntityId()))
				createNewOfferInstanceOrUpdateTcOfExisting(offer, vzEntityNew,
						status, offerInstance);
			else {
				List<OfferInstance> offerInstances = offerInstanceRepository
						.findByOfferAndVerizonEntity(offer.getOfferId(),
								vzEntityNew.getVerizonEntityId());
				if (offerInstances != null && !offerInstances.isEmpty()) {
					OfferInstance offerInstanceTemp = offerInstances.get(0);
					createNewOfferInstanceOrUpdateTcOfExisting(offer,
							vzEntityNew, status, offerInstanceTemp);
				} else {
					createNewOfferInstanceOrUpdateTcOfExisting(offer,
							vzEntityNew, status, null);
				}
			}
		}
	}

	/**
	 * Creates offerInstance
	 * 
	 * @param offer
	 * @param entity
	 * @return
	 */
	public OfferInstance createNewOfferInstanceOrUpdateTcOfExisting(
			Offer offer, VerizonEntity entity, int termsAndConditions,
			OfferInstance offerInstance) {

		OfferInstance newOfferInstance = null;

		if (offerInstance != null) {
			logger.debug("Updating instance for mdn : " + entity.getMdn());
			offerInstance.setTermsAndConditionsStatus(termsAndConditions);
			return offerInstance;

		} else {
			logger.debug("Creating instance for mdn : " + entity.getMdn());
			newOfferInstance = new OfferInstance();
			newOfferInstance.setOffer(offer);
			newOfferInstance.setDateCreated(new Date());
			newOfferInstance
					.setServiceInstances(new ArrayList<ServiceInstance>());
			newOfferInstance.setTermsAndConditionsStatus(termsAndConditions);
			newOfferInstance.setVerizonEntity(entity);
			newOfferInstance.setEndTmstamp(PricingLabUtility
					.getDefaultEndTimeStamp());
			offerInstanceRepository.saveAndFlush(newOfferInstance);

			logger.debug("Instance created for mdn : " + entity.getMdn()
					+ ", instance id : "
					+ newOfferInstance.getOfferInstanceId());

		}
		return newOfferInstance;
	}

	/**
	 * Create new Choice instance.
	 * 
	 * @param offerInstance
	 * @param choice
	 * @return
	 */
	public ChoiceInstance createNewChoiceInstance(OfferInstance offerInstance,
			Choice choice) {

		ChoiceInstance newChoiceInstance = new ChoiceInstance();
		newChoiceInstance.setOfferInstance(offerInstance); // mapped to mdn
		newChoiceInstance.setChoice(choice);
		newChoiceInstance.setDateCreated(new Date());
		newChoiceInstance.setSelected(choice.isSelected());
		newChoiceInstance.setEndTmstamp(PricingLabUtility
				.getDefaultEndTimeStamp());
		choiceInstanceRepository.saveAndFlush(newChoiceInstance);

		return newChoiceInstance;
	}

	/**
	 * Create or update pricing lab customers
	 * 
	 * @param customerId
	 * @param acctNum
	 * @param mdn
	 * @param offer
	 */
	public Map<String, Object> createOrUpdatePricingLabCustomers(
			Long customerId, Long acctNum, String mdn, Offer offer,
			Timestamp mtnEffDate) {

		Map<String, Object> returnObjects = new HashMap<>();

		VerizonEntity vzEntity = getOrCreateVerizonEntity(customerId, acctNum,
				mdn);

		PlabCust plabCust = plabCustRepository.findByOfferAndMdn(
				offer.getOfferId(), mdn);
		if (plabCust == null) {
			plabCust = createPlabCust(customerId, acctNum, mdn, offer);
			updateTotalInvitedCustomers(offer);
			returnObjects.put(PlabConstants.PLAB_CUST, plabCust);
		} else {
			plabCust.setIndicator(PlabConstants.INVITED_IND);
			plabCust.setPlStartDate(new Timestamp(new Date().getTime()));
			plabCust.setPlEndDate(PricingLabUtility.getDefaultEndTimeStamp());
			plabCustRepository.saveAndFlush(plabCust);
		}
		if (fromLocal) {
			createOrUpdateRefGridPlabCust(customerId, acctNum, mdn,
					PlabConstants.INVITED_IND, mtnEffDate, null);
			gridService
					.add(customerId, acctNum, mdn, PlabConstants.INVITED_IND);
		} else {
			createDataPOPAturAPIRefgrid(customerId, acctNum, mdn,
					PlabConstants.INVITED_IND, mtnEffDate, null);
			createDataPOPAturAPIGrid(customerId, acctNum, mdn,
					PlabConstants.INVITED_IND);
		}

		returnObjects.put(PlabConstants.VERIZON_ENTITY, vzEntity);

		return returnObjects;
	}

	public VerizonEntity getOrCreateVerizonEntity(Long customerId,
			Long acctNum, String mdn) {

		VerizonEntity vzEntity = verizonEntityRepository
				.findByCustIdNoAndAcctNoAndMdn(customerId, acctNum, mdn);

		if (vzEntity == null
				|| (vzEntity != null && (vzEntity.getCustIdNo() == null
						|| vzEntity.getAcctNo() == null || vzEntity.getMdn() == null))) {
			vzEntity = new VerizonEntity();
			vzEntity.setCustIdNo(customerId);
			vzEntity.setAcctNo(acctNum);
			vzEntity.setMdn(mdn);
			vzEntity.setDateCreated(new Date());
			vzEntity.setCreatedBy(PlabConstants.DEFAULT_CREATED_BY_CPI);
			VerizonEntity savedVzEntity = verizonEntityRepository
					.saveAndFlush(vzEntity);
			logger.debug("Created VerizonEntity for mdn : " + mdn);
			return savedVzEntity;
		}
		return vzEntity;
	}

	public PlabCust createPlabCust(Long customerId, Long acctNum, String mdn,
			Offer offer) {

		PlabCust plabCust = new PlabCust();
		plabCust.setCustIdNo(customerId);
		plabCust.setAcctNo(acctNum);
		plabCust.setMdn(mdn);
		plabCust.setCreatedBy(PlabConstants.CPI_USER);
		plabCust.setIndicator(PlabConstants.INVITED_IND);
		plabCust.setPlEndDate(PricingLabUtility.getDefaultEndTimeStamp());
		plabCust.setPlStartDate(new Timestamp(new Date().getTime()));
		plabCust.setCreateDtm(new Timestamp(new Date().getTime()));
		plabCust.setOffer(offer);
		logger.debug("Created PlabCust for mdn : " + mdn);
		PlabCust savedPlabCust = plabCustRepository.saveAndFlush(plabCust);

		return savedPlabCust;
	}

	public void updateTotalInvitedCustomers(Offer offer) {
		offer.setTotalInvitedCust(offer.getTotalInvitedCust() != null ? offer
				.getTotalInvitedCust() + 1 : 1);
	}

	/**
	 * Create or RefGrid Plab Cust as soon as the particular action is called.
	 * 
	 * @param customerId
	 * @param acctNum
	 * @param mdn
	 * @param indicator
	 * @param mtneffDate
	 * @param billCycleEndDt
	 * @return
	 */
	public RefGridPlabCust createOrUpdateRefGridPlabCust(Long customerId,
			Long acctNum, String mdn, Long indicator, Timestamp mtneffDate,
			Date billCycleEndDt) {

		RefGridPlabCust refGridCust = DBcreateOrUpdateRefGrid(customerId,
				acctNum, mdn, indicator, mtneffDate, billCycleEndDt,
				new Timestamp(Calendar.getInstance(TimeZone.getTimeZone("EST"))
						.getTime().getTime()));

		logger.debug("Publishing to REF_GRID_REQUEST for mdn : " + mdn
				+ " with indicator " + indicator);

		distributedService
				.getHazelcastInstance()
				.getMap("REF_GRID_MAP")
				.put(refGridCust.getCustIdNo() + refGridCust.getAcctNo()
						+ refGridCust.getMdn(), refGridCust);

		List<String> dcList = Arrays.asList(dcNameList.split(","));
		for (String dc : dcList) {
			distributedService.getHazelcastInstance()
					.getQueue("REF_GRID_REQUEST_" + dc).add(refGridCust);
		}

		return refGridCust;
	}

	/**
	 * Listeners will excecute the DBcreateOrUpdateRefGrid for current DC and
	 * also for other DC's.
	 * 
	 * @param customerId
	 * @param acctNum
	 * @param mdn
	 * @param indicator
	 * @param mtneffDate
	 * @param billCycleEndDt
	 * @param modifiedDate
	 * @return
	 */
	public RefGridPlabCust DBcreateOrUpdateRefGrid(Long customerId,
			Long acctNum, String mdn, Long indicator, Timestamp mtneffDate,
			Date billCycleEndDt, Timestamp modifiedDate) {

		RefGridPlabCust refGridPlabCust = refGridPlabCustRepository
				.findByCustIdNoAndAcctNoAndMdn(customerId, acctNum, mdn);

		if (refGridPlabCust == null) {
			logger.debug("Creating RefGridPlabCust for mdn : " + mdn);
			refGridPlabCust = new RefGridPlabCust();
			refGridPlabCust.setCustIdNo(customerId);
			refGridPlabCust.setAcctNo(acctNum);
			refGridPlabCust.setMdn(mdn);
			refGridPlabCust.setCreatedBy(PlabConstants.CPI_USER);
			refGridPlabCust.setMtnEffDt(mtneffDate != null ? mtneffDate
					: new Timestamp(new Date().getTime()));
			refGridPlabCust.setPlStartDate(new Timestamp(new Date().getTime()));
			refGridPlabCust.setPlEndDate(PricingLabUtility
					.getDefaultEndTimeStamp());
		} else {
			logger.debug("RefGridPlabCust already exists for mdn : " + mdn);
		}
		refGridPlabCust.setModifiedDate(modifiedDate);
		refGridPlabCust.setIndicator(indicator);
		if (indicator.equals(PlabConstants.REMOVING)
				|| indicator.equals(PlabConstants.REMOVED_PENDING_EOC)) {
			refGridPlabCust.setPlEndDate(new Timestamp(new Date().getTime()));
		} else if (indicator
				.equals(PlabConstants.EARLY_TERMINATE_VISION_REMOVED)
				|| indicator.equals(PlabConstants.EARLY_TERMINATE_PENDING_EOC)) {
			if (billCycleEndDt != null) {
				refGridPlabCust.setPlEndDate(new Timestamp(billCycleEndDt
						.getTime()));
			} else {
				logger.error("Bill Cycle end date is null; Hence ref_grid_plab_cust end date is not set correctly");
			}
		} else if (indicator.equals(PlabConstants.INVITED_IND)
				|| indicator.equals(PlabConstants.PRE_ACCEPT)
				|| indicator.equals(PlabConstants.ENROLLED)) {
			refGridPlabCust.setPlEndDate(PricingLabUtility
					.getDefaultEndTimeStamp());
		}

		RefGridPlabCust savedRefGridPlabCust = refGridPlabCustRepository
				.saveAndFlush(refGridPlabCust);
		logger.debug("Saved refGrid indicator is "
				+ savedRefGridPlabCust.getIndicator() + " for mdn : " + mdn);

		return refGridPlabCust;
	}

	public RefGridPlabCust DBcreateOrUpdateRefGrid(
			RefGridPlabCust refGridPlabCust) {
		try {
			if (refGridPlabCust != null
					&& refGridPlabCust.getPlEndDate() != null
					&& refGridPlabCust.getPlEndDate().after(
							PricingLabUtility.getDefaultEndTimeStamp())) {
				refGridPlabCust.setPlEndDate(PricingLabUtility
						.getDefaultEndTimeStamp());
			}
			RefGridPlabCust savedRefGridPlabCust = refGridPlabCustRepository
					.saveAndFlush(refGridPlabCust);
			logger.debug("Saved refGrid indicator is "
					+ savedRefGridPlabCust.getIndicator() + " for mdn : "
					+ refGridPlabCust.getMdn());
		} catch (Exception ex) {
			String issueMdn = null;
			if (refGridPlabCust != null && refGridPlabCust.getMdn() != null) {
				issueMdn = refGridPlabCust.getMdn();
			}
			logger.error(
					"Error persisting in UBSR.REF_GRID_PLAB_CUST for mdn : {}",
					issueMdn);
			logger.error("Exception : {}, {}", ex.getMessage(), ex);
		}
		return refGridPlabCust;
	}

	/**
	 * Update Pricing lab customer indicator.
	 * 
	 * @param mdn
	 * @param plabCustList
	 * @param indicator
	 * @param billCycleEndDt
	 */
	public void updateIndicatorInPlabcustAndRefgridAndGrid(String mdn,
			List<PlabCust> plabCustList, Long indicator, Date billCycleEndDt) {

		PlabCust plabCust = null;

		if (plabCustList != null && !plabCustList.isEmpty()) {
			for (PlabCust plabCustTemp : plabCustList) {
				if (plabCustTemp.getMdn().equals(mdn)) {
					plabCust = plabCustTemp;
					break;
				}
			}
		}

		if (plabCust != null) {
			List<SubCustAcctMdn> subCustAcctMdnList = subCustAcctMdnRepository
					.findByActiveMdn(plabCust.getMdn());
			SubCustAcctMdn subCustAcctMdn = null;
			Timestamp mtnEffDate = null;

			if (subCustAcctMdnList != null && !subCustAcctMdnList.isEmpty()) {
				subCustAcctMdn = subCustAcctMdnList.get(0);
				mtnEffDate = subCustAcctMdn.getMtnEffDt();
			}

			plabCust.setIndicator(indicator);

			if (indicator.equals(PlabConstants.REMOVING)
					|| indicator.equals(PlabConstants.REMOVED_PENDING_EOC)) { // FIXME
																				// ::
																				// confirm
																				// the
																				// condition
				plabCust.setPlEndDate(new Timestamp(new Date().getTime()));
			} else if (indicator
					.equals(PlabConstants.EARLY_TERMINATE_VISION_REMOVED)
					|| indicator
							.equals(PlabConstants.EARLY_TERMINATE_PENDING_EOC)) {
				if (billCycleEndDt != null) {
					plabCust.setPlEndDate(new Timestamp(billCycleEndDt
							.getTime()));
				} else {
					logger.error("Bill Cycle end date is null; Hence plab Cust end date is not set correctly");
				}
			}

			plabCustRepository.saveAndFlush(plabCust);

			// if(fromLocal){
			createOrUpdateRefGridPlabCust(plabCust.getCustIdNo(),
					plabCust.getAcctNo(), mdn, indicator, mtnEffDate,
					billCycleEndDt);
			gridService.update(plabCust.getCustIdNo(), plabCust.getAcctNo(),
					mdn, indicator);
			// }
			/*
			 * if (fromLocal) { // else{
			 * createDataPOPAturAPIRefgrid(plabCust.getCustIdNo(),
			 * plabCust.getAcctNo(),mdn,indicator,mtnEffDate,billCycleEndDt);
			 * //createDataPOPAturAPIGrid(plabCust.getCustIdNo(),
			 * plabCust.getAcctNo(), mdn,indicator); }
			 */
		}

	}

	public void updatePlabCustIndicator(String mdn,
			List<PlabCust> plabCustList, Long indicator) {
		PlabCust plabCust = null;

		if (plabCustList != null && !plabCustList.isEmpty()) {
			for (PlabCust plabCustTemp : plabCustList) {
				if (plabCustTemp.getMdn().equals(mdn)) {
					plabCust = plabCustTemp;
					break;
				}
			}
		}

		if (plabCust != null) {
			plabCust.setIndicator(indicator);
			plabCustRepository.saveAndFlush(plabCust);
		}
	}

	/**
	 * add default options.
	 * 
	 * @param offer
	 */
	public void addSimplePage(Offer offer, String type, String heading,
			String headingImgUrl, String subHeading, String subHeadingImgUrl,
			String body, String bodyImgUrl, String footer, String footerImgUrl) {
		if (offer != null) {
			SimplePage page = new SimplePage();
			page.setType(type);
			page.setHeading(heading);
			page.setHeadingImgUrl(headingImgUrl);
			page.setSubHeading(subHeading);
			page.setSubHeadingImgUrl(subHeadingImgUrl);
			page.setBody(body);
			page.setBodyImgUrl(bodyImgUrl);
			page.setFooter(footer);
			page.setFooterImgUrl(footerImgUrl);
			page.setDateCreated(new Date());
			page.setOffer(offer);
			List<SimplePage> list = new ArrayList<SimplePage>();
			list.add(page);

			if (offer.getSimplePages() != null
					&& !offer.getSimplePages().isEmpty()) {
				offer.getSimplePages().add(page);
			} else {
				offer.setSimplePages(list);
			}
		}
	}

	/**
	 * Update Survey Answers in Offers
	 * 
	 * @param offerInstances
	 */
	public void retreiveAndUpdateSurveyAnswers(
			List<OfferInstance> offerInstances) {
		if (offerInstances != null && !offerInstances.isEmpty()) {

			for (OfferInstance offerInstance : offerInstances) {

				// for each offer instance check the the choice instance
				List<ChoiceInstance> choiceInstances = offerInstance
						.getChoiceInstances();
				List<Survey> defaultSurveys = offerInstance.getOffer()
						.getSurveys();

				Map<Long, Choice> choiceMap = new HashMap<Long, Choice>();
				Map<Long, SurveyQuestion> questionMap = new HashMap<Long, SurveyQuestion>();

				for (Survey survey : defaultSurveys) {
					for (SurveyQuestion question : survey.getQuestions()) {
						questionMap
								.put(question.getSelectionPageId(), question);
						List<Choice> choices = question.getChoice();
						for (Choice choice : choices) {
							choiceMap.put(choice.getId(), choice);
						}
					}
				}

				if (choiceMap != null && !choiceMap.isEmpty()) {
					for (ChoiceInstance choiceInstance : choiceInstances) {
						Choice choice = choiceMap.get(choiceInstance
								.getChoice().getId());
						if (choice != null) {
							choice.setSelected(choiceInstance.getSelected());
							choiceMap.put(choiceInstance.getChoice().getId(),
									choice);
						}
					}
				}

				for (Survey survey : defaultSurveys) {
					for (SurveyQuestion question : survey.getQuestions()) {
						List<Choice> choices = question.getChoice();
						for (Choice choice : choices) {
							Choice choiceTemp = choiceMap.get(choice.getId());
							if (choiceTemp != null) {
								choice.setSelected(choiceTemp.isSelected());
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Get survey format
	 * 
	 * @param json
	 * @return
	 */
	public List checkSubmitSurveyFormat(String json) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(df);

		List<SurveyAnswer> surveyAnswersFromJson = null;
		List<Choice> choicesFromJson = null;
		List<SurveyQuestion> surveyQuestionsFromJson = null;

		try {
			surveyAnswersFromJson = mapper.readValue(json,
					new TypeReference<List<SurveyAnswer>>() {
					});
			return surveyAnswersFromJson;
		} catch (Exception ex) {
			logger.debug("Unable to parse surveyAnswers ! Continue to parse either choice or SurveyQuestion.");
		}

		try {
			choicesFromJson = mapper.readValue(json,
					new TypeReference<List<Choice>>() {
					});
			return choicesFromJson;
		} catch (Exception ex) {
			logger.debug("Unable to parse choices ! Continue to parse SurveyQuestion format.");
		}

		try {
			surveyQuestionsFromJson = mapper.readValue(json,
					new TypeReference<List<SurveyQuestion>>() {
					});

			return surveyQuestionsFromJson;
		} catch (Exception ex) {
			logger.debug("Unable to parse SurveyQuestions! Please enter a valid format");
		}

		return null;
	}

	/**
	 * create Mdn SPO Content for MF to parse.
	 * 
	 * @param offerInstance
	 * @param enrolledMdn
	 * @return
	 */
	public List<OfferMdnOption> createMdnSpoContent(
			OfferInstance offerInstance, List<String> enrolledMdn,
			List<String> mdnNoPlabSPO) {
		List<OfferMdnOption> mdnSpoContentList = null;

		try {
			Map<String, List<String>> mdnSpoMap = new HashMap<>();
			List<String> serviceSpoList = new ArrayList();
			List<String> provisionedSPOListOnly = new ArrayList();

			if (provisioningSpo != null && !provisioningSpo.isEmpty()) {
				provisionedSPOListOnly.add(provisioningSpo);
			}

			for (ServiceInstance serviceInstance : offerInstance
					.getServiceInstances()) {
				if (serviceInstance.getService().getSpoID() != null) {
					serviceSpoList.add(serviceInstance.getService().getSpoID());
				}
			}

			for (String eachEnrolledMdn : enrolledMdn) {
				mdnSpoMap.put(eachEnrolledMdn, serviceSpoList);
			}

			// format the content
			mdnSpoContentList = new ArrayList<>();
			for (Map.Entry entry : mdnSpoMap.entrySet()) {
				OfferMdnOption spoContent = new OfferMdnOption();
				spoContent.setMdn((String) entry.getKey());
				spoContent.setSpos((List<String>) entry.getValue());
				mdnSpoContentList.add(spoContent);
			}

			for (OfferMdnOption mdnOption : mdnSpoContentList) {
				if (provisioningSpo != null && !provisioningSpo.isEmpty()) {
					mdnOption.getSpos().add(provisioningSpo);
					break;
				}
			}

			for (String eachMdnNoPlabSPO : mdnNoPlabSPO) {
				OfferMdnOption spoContent = new OfferMdnOption();
				spoContent.setMdn(eachMdnNoPlabSPO);
				spoContent.setSpos(provisionedSPOListOnly);
				mdnSpoContentList.add(spoContent);
			}

			logger.debug("Offer instance saved successfully. MDN's : "
					+ Arrays.deepToString(mdnSpoMap.keySet().toArray())
					+ " - SPOS : "
					+ Arrays.deepToString(mdnSpoMap.values().toArray()));

		} catch (Exception ex) {
			logger.error("Error generating mdn spo content : ",
					ex.getMessage(), ex);
		}

		return mdnSpoContentList;
	}

	/**
	 * Sort Choices by ID.
	 * 
	 * @param offerMdnOptionList
	 */
	public void sortChoicesById(List<OfferMdnOption> offerMdnOptionList) {
		if (offerMdnOptionList != null && !offerMdnOptionList.isEmpty()) {
			for (OfferMdnOption offerMdnOption : offerMdnOptionList) {
				if (offerMdnOption.getOfferOptions() != null) {
					for (ServiceQuestion serviceQuestion : offerMdnOption
							.getOfferOptions()) {
						Collections.sort(serviceQuestion.getChoice(),
								OfferMdnOption.CHOICE_COMPARATOR);
					}
				}
			}
		}
	}

	public String getPlabCustDetails(Long customerId) {
		List<PlabCust> plabCustList = plabCustRepository
				.findByCustIdNo(customerId);
		StringBuilder buildPlabCustDetails = new StringBuilder();
		for (PlabCust cust : plabCustList) {
			buildPlabCustDetails.append("custid:").append(cust.getCustIdNo());
			buildPlabCustDetails.append(",mdn:").append(cust.getMdn());
			buildPlabCustDetails.append(",acctno:").append(cust.getAcctNo());
			buildPlabCustDetails.append(",indicator:").append(
					cust.getIndicator());
			buildPlabCustDetails.append(",enddate:")
					.append(cust.getPlEndDate());
			// end delimitor
			buildPlabCustDetails.append(";");
		}

		return buildPlabCustDetails.toString();
	}

	public void createJobsForCustomer(PlabCust plabCust,
			List<String> enrolledMdn) throws Exception {
		jobScheduler.createScheduledJobsForCustomer(plabCust, enrolledMdn);
		logger.debug("Created jobs for mdn : " + plabCust.getMdn());
	}

	public void createEarlyTerminationJobs(PlabCust cust) {
		jobScheduler.createEarlyTerminationJobs(cust);
	}

	/**
	 * Operation : Adding VISP service to device. Usability : Method helps in
	 * creating the BO object for VISP operations.
	 * 
	 * @param mdn
	 * @param spoId
	 * @param operation
	 * @return string : visp service instance id.
	 */
	public Integer executeVispServiceToDevice(String mdn, String spoId,
			byte operation) throws Exception {
		// 1. create bo //2. execute Visp call.
		Integer response = helper.executeVispCall(helper.createVispBO(mdn,
				spoId, operation));
		// Add Job
		if (response != -1) {
			List<PlabCust> custList = plabCustRepository.findByMdn(mdn);
			PlabCust cust = custList.get(0);
			// This will be called during accept offer
			// createNewJobForCustomerBilling(cust);
		}
		return response;
	}

	// FIXME: REMOVE IF NOT USER ?
	public boolean isValidOfferInstance(String billCycleDt,
			Timestamp offerInstanceDtTerminated) {

		SimpleDateFormat sdfTemp = new SimpleDateFormat("MM/dd/yyyy");

		try {
			Date billCycleStartDate = sdfTemp.parse((Calendar.getInstance()
					.get(Calendar.MONTH) + 1)
					+ "/"
					+ billCycleDt
					+ "/"
					+ Calendar.getInstance().get(Calendar.YEAR) + " 00:00:01");

			if (offerInstanceDtTerminated.before(billCycleStartDate)) {
				return false;
			} else {
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Get the latest offerInstance
	 * 
	 * @param mdn
	 * @return
	 */
	public OfferInstance getValidOfferInstance(String mdn) {

		OfferInstance offerInstance = null;

		List<OfferInstance> offerInstanceList = offerInstanceRepository
				.findLatestOfferInstanceByMdn(mdn);

		if (offerInstanceList != null && !offerInstanceList.isEmpty()) {
			offerInstance = offerInstanceList.get(0);
		}

		return offerInstance;

	}

	/**
	 * Get Customer Billing cycle dates. [0] Start date = current month date [1]
	 * and End Date is next month date.
	 * 
	 * @param custIDNo
	 * @param acctNo
	 * @return billcycleNo=9-->start dt = Aug =10;end dt =Sep 9--> but sep 10 is
	 *         generated here for end dt because to calc billable days, last day
	 *         is excluded
	 */
	public List<Date> getSubCustBillCycleDates(Long custIDNo, Long acctNo) {

		SimpleDateFormat sdfTemp = new SimpleDateFormat("MM/dd/yyyy");

		// String subCustBlCycleNo =
		// subCustBlCycleRepository.findBlCycNoByCustIdNo(custIDNo);
		String subCustBlCycleNo = gridService.getCustomerBillCycle(custIDNo,
				acctNo);
		List<Date> billCycleDates = new ArrayList<>();
		int billCycleNoTemp = Integer.parseInt(subCustBlCycleNo) + 1;
		Date billCycleStartDate = null;
		Date billCycleEndDate = null;

		try {
			billCycleStartDate = sdfTemp.parse((Calendar.getInstance().get(
					Calendar.MONTH) + 1)
					+ "/"
					+ billCycleNoTemp
					+ "/"
					+ Calendar.getInstance().get(Calendar.YEAR) + " 00:00:01");
			billCycleEndDate = sdfTemp.parse((Calendar.getInstance().get(
					Calendar.MONTH) + 2)
					+ "/"
					+ billCycleNoTemp
					+ "/"
					+ Calendar.getInstance().get(Calendar.YEAR) + " 00:00:01");

			billCycleDates.add(billCycleStartDate);
			billCycleDates.add(billCycleEndDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return billCycleDates;

	}

	/*
	 * get bill cycle dates for the given month and year month starts at 0
	 * billcycleNo=9-->start dt = Aug =10;end dt =Sep 9--> but sep 10 is
	 * generated here for end dt to calc billable days, last day is excluded
	 */
	public List<Date> getSubCustBillCycleDatesForGivenMonth(Long custIDNo,
			Long accountNum, String month, String year) {
		SimpleDateFormat sdfTemp = new SimpleDateFormat("MM/dd/yyyy");

		// String subCustBlCycleNo =
		// subCustBlCycleRepository.findBlCycNoByCustIdNo(custIDNo, accountNum);
		String subCustBlCycleNo = gridService.getCustomerBillCycle(custIDNo,
				accountNum);

		List<Date> billCycleDates = new ArrayList<>();

		Date billCycleStartDate = null;
		Date billCycleEndDate = null;
		int monthTemp = Integer.parseInt(month) + 1;
		int yearTemp = Integer.parseInt(year);
		int billCycleNoTemp = Integer.parseInt(subCustBlCycleNo) + 1;
		try {
			billCycleStartDate = sdfTemp.parse(monthTemp + "/"
					+ billCycleNoTemp + "/" + yearTemp);
			billCycleEndDate = sdfTemp.parse((monthTemp + 1) + "/"
					+ billCycleNoTemp + "/" + yearTemp);

			billCycleDates.add(billCycleStartDate);
			billCycleDates.add(billCycleEndDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return billCycleDates;

	}

	/**
	 * \ Add EOC Rule to a service Instance
	 * 
	 * @param serviceInstance
	 * @param custIdNo
	 * @param subCustBillCycleStartDt
	 * @return
	 */
	public ServiceInstance addEOCRuleInstance(ServiceInstance serviceInstance,
			Long custIdNo, Date subCustBillCycleStartDt) {

		logger.debug("Product Manager : addEOCRuleInstance. Customer ID : "
				+ custIdNo);

		try {
			// EOC Rule
			Date subCustBillCycleEndDt = null;
			if (subCustBillCycleStartDt == null) {
				List<Date> subCustbillCycleDtsList = getSubCustBillCycleDates(
						custIdNo, serviceInstance.getVerizonEntity()
								.getAcctNo());
				Calendar tempDate = Calendar.getInstance();
				tempDate.setTime(subCustbillCycleDtsList.get(1)); // TODO : Need
																	// to verify
																	// this.
				tempDate.add(Calendar.DATE, -1); // TODO : Need to verify this.
				subCustBillCycleEndDt = tempDate.getTime();
				logger.debug("endDt:" + subCustBillCycleEndDt);
			}

			List<Rule> serviceRules = serviceInstance.getService().getRules();

			Rule eocRule = null;
			for (Rule ruleEoc : serviceRules) {
				if (Rule.qosEOCRuleType.equalsIgnoreCase(ruleEoc.getRuleType())) {
					eocRule = ruleEoc;
				}
			}

			if (eocRule == null) {
				com.vzwcorp.pricinglab.vo.Service service = serviceInstance
						.getService();
				eocRule = new Rule();
				eocRule.setCreatedBy(PlabConstants.DEFAULT_CREATED_BY_USER);
				eocRule.setDateCreated(new Date());
				eocRule.setRuleType(Rule.qosEOCRuleType);
				eocRule.setExpressionType("byTime");
				// eocRule.setExpression(PricingLabUtility.getEOCExpression(subCustBillCycleStartDt));
				if (eocRule.getService() == null) {
					eocRule.setService(serviceInstance.getService());
				}
				ruleRepository.saveAndFlush(eocRule);
				service.addRule(eocRule);
				serviceRepository.saveAndFlush(service);
			}

			RuleInstance ruleInstance = new RuleInstance(serviceInstance,
					eocRule, null);
			ruleInstance.setEndDate(PricingLabUtility.getDefaultEndTimeStamp());

			// find biller id for customer to get the timezone info.
			String timeZone = helper.getCustomerTimeZone(custIdNo);

			ruleInstance.setExpression(PricingLabUtility.getEOCExpression(
					subCustBillCycleEndDt, timeZone));
			// ruleInstanceRepository.saveAndFlush(ruleInstance);

			serviceInstance.getRules().add(ruleInstance);

		} catch (Exception ex) {
			logger.error(
					"Exception in Product Manager : addEOCRuleInstance. Customer ID : "
							+ custIdNo, ex.getMessage(), ex);
		}
		return serviceInstance;
	}

	public ServiceInstance createServiceInstance(
			com.vzwcorp.pricinglab.vo.Service service, VerizonEntity vzEntity,
			OfferInstance offerInstance, Map<String, String> attributesMap) {

		ServiceInstance serviceInstance = null;

		if (attributesMap != null && !attributesMap.isEmpty()) {
			serviceInstance = new ServiceInstance(service, vzEntity,
					offerInstance, attributesMap);
		} else {
			serviceInstance = new ServiceInstance(service, vzEntity,
					offerInstance);
		}
		serviceInstance.setDateCreated(new Date());
		serviceInstance.setCreatedBy(PlabConstants.DEFAULT_CREATED_BY_USER);
		serviceInstance
				.setVispServiceInstanceId(PlabConstants.DEFAULT_VISP_SERVICE_INSTANCE_ID);
		serviceInstance.setEndTmstamp(PricingLabUtility
				.getDefaultEndTimeStamp());
		serviceInstance.setVispServiceID(service.getVispServiceID()); // FIXME:
																		// confirm

		return serviceInstance;
	}

	public ServiceInstance createServiceInstance(
			com.vzwcorp.pricinglab.vo.Service service, VerizonEntity vzEntity,
			OfferInstance offerInstance, Map<String, String> attributesMap,
			boolean isCustomerOnSafetyMode) {

		ServiceInstance serviceInstance = null;

		if (attributesMap != null && !attributesMap.isEmpty()) {
			serviceInstance = new ServiceInstance(service, vzEntity,
					offerInstance, attributesMap);
		} else {
			// Safety mode is applicable only for Night Surfer offer
			serviceInstance = new ServiceInstance(service, vzEntity,
					offerInstance, isCustomerOnSafetyMode);
		}
		serviceInstance.setDateCreated(new Date());
		serviceInstance.setCreatedBy(PlabConstants.DEFAULT_CREATED_BY_USER);
		serviceInstance
				.setVispServiceInstanceId(PlabConstants.DEFAULT_VISP_SERVICE_INSTANCE_ID);
		serviceInstance.setEndTmstamp(PricingLabUtility
				.getDefaultEndTimeStamp());
		serviceInstance.setVispServiceID(service.getVispServiceID()); // FIXME:
																		// confirm

		return serviceInstance;
	}

	public void createDataPOPAturAPIRefgrid(Long customerId, Long acctNum,
			String mdn, Long indicator, Timestamp mtnEffDate,
			Date billCycleEndDt) {
		receiveDataPopDataPOPAturAPIRefgrid(customerId, acctNum, mdn,
				indicator, mtnEffDate, billCycleEndDt);
	}

	public void createDataPOPAturAPIGrid(Long customerId, Long acctNum,
			String mdn, Long indicator) {
		receiveDataPOPAturAPIGrid(customerId, acctNum, mdn, indicator);
	}

	public void receiveDataPopDataPOPAturAPIRefgrid(Long customerId,
			Long acctNum, String mdn, Long indicator, Timestamp mtnEffDate,
			Date billCycleEndDt) {
		createOrUpdateRefGridPlabCust(customerId, acctNum, mdn, indicator,
				mtnEffDate, billCycleEndDt);
	}

	public void receiveDataPOPAturAPIGrid(Long customerId, Long acctNum,
			String mdn, Long indicator) {
		gridService.add(customerId, acctNum, mdn, indicator);
	}

	public Map<String, List<String>> getSpoOfMdn(List<PlabCust> plabCustList,
			boolean earlyTerminate) {
		logger.debug("In get spoOfMDn. early terminate : {}", earlyTerminate);
		Map<String, List<String>> mdnSpoList = new HashMap<>();
		if (plabCustList != null && !plabCustList.isEmpty()) {
			for (PlabCust plabCust : plabCustList) {
				if (plabCust.getIndicator() != null
						&& plabCust.getIndicator().equals(
								PlabConstants.INVITED_IND)) { // ONLY ENRolled
																// in base
																// service for
																// night surfer
					List<String> spoList = new ArrayList<>();
					if (!earlyTerminate) {
						spoList.add(provisioningSpo);
						mdnSpoList.put(plabCust.getMdn(), spoList);
					}
				} else if (plabCust.getIndicator() != null
						&& plabCust.getIndicator().equals(
								PlabConstants.ENROLLED)
						|| plabCust.getIndicator().equals(
								PlabConstants.PRE_ACCEPT)) {
					List<String> spoList = new ArrayList<>();
					// Change Date : 11/23/2016 - Ajay changed from
					// offer/service to offerinstance/serviceinstace
					List<OfferInstance> offerInstances = offerInstanceRepository
							.findLatestOfferInstanceByMdn(plabCust.getMdn());
					for (OfferInstance offerInstance : offerInstances) {
						List<ServiceInstance> serviceInstances = serviceInstanceRepository
								.findLatestByOfferInstance(offerInstance
										.getOfferInstanceId());
						for (ServiceInstance serviceInstance : serviceInstances) {
							if (serviceInstance.getSpoID() != null
									&& !serviceInstance.getSpoID().isEmpty()
									&& provisioningSpo != null
									&& !provisioningSpo.isEmpty()
									&& !provisioningSpo.trim().equals(
											serviceInstance.getSpoID().trim())) {
								spoList.add(serviceInstance.getSpoID());
							}
						}
					}

					if (!earlyTerminate) {
						spoList.add(provisioningSpo);
					}

					if (spoList != null && !spoList.isEmpty()) {
						mdnSpoList.put(plabCust.getMdn(), spoList);
					}
				} else if (plabCust.getIndicator() != null
						&& (plabCust.getIndicator().equals(
								PlabConstants.EARLY_TERMINATE_PENDING_EOC) || plabCust
								.getIndicator()
								.equals(PlabConstants.EARLY_TERMINATE_VISION_REMOVED))) {
					List<String> spoList = new ArrayList<>();
					spoList.add(provisioningSpo);
					mdnSpoList.put(plabCust.getMdn(), spoList);
				}
			}
		}
		if (mdnSpoList == null || mdnSpoList.isEmpty()) {
			logger.debug("MdnspoList is empty");
		} else {
			for (Map.Entry<String, List<String>> entry : mdnSpoList.entrySet()) {
				StringBuilder builder = new StringBuilder("mdn: ").append(entry
						.getKey());
				// logger.debug("mdn:"+entry.getKey());
				for (String spo : entry.getValue()) {
					// logger.debug("spo:"+spo);
					builder.append(",spo : ").append(spo);
				}
				logger.debug(builder.toString());
			}
		}

		return mdnSpoList;
	}

	public boolean terminateAllInstancesFromPlabAndVisp(
			List<OfferInstance> offerInstanceList, String mdn,
			boolean terminateVisp) {
		boolean plabDeletion = false;
		for (OfferInstance offerInstance : offerInstanceList) {

			// OfferInstance offerInstance = offerInstances.get(0); //only 1
			// offerInstance expected

			List<ServiceInstance> serviceInstances = offerInstance
					.getServiceInstances();
			List<ChoiceInstance> choiceInstances = offerInstance
					.getChoiceInstances();
			List<ChoiceInstanceHistory> choiceInstanceHistoryList = new ArrayList<>();

			if (choiceInstances != null && !choiceInstances.isEmpty()) {
				for (ChoiceInstance choiceInstance : choiceInstances) {
					choiceInstanceHistoryList.addAll(choiceInstance
							.getChoiceInstanceHistory());
					choiceInstance.setEndTmstamp(new Timestamp(new Date()
							.getTime()));
					choiceInstanceRepository.saveAndFlush(choiceInstance);
					logger.debug("terminateAllInstances() : End dated choiceinstance with id : "
							+ choiceInstance.getChoiceInstanceId()
							+ " for mdn = " + mdn);
				}
			}

			if (choiceInstanceHistoryList != null
					&& !choiceInstanceHistoryList.isEmpty()) {
				for (ChoiceInstanceHistory choiceInstanceHist : choiceInstanceHistoryList) {
					choiceInstanceHist.setEndTmstamp(new Timestamp(new Date()
							.getTime()));
					choiceInstanceHistoryRepository
							.saveAndFlush(choiceInstanceHist);
					logger.debug("terminateAllInstances() : End dated choiceinstancehistory with id : "
							+ choiceInstanceHist.getChoiceInstanceHistoryId()
							+ " for mdn = " + mdn);
				}
			}

			List<ServiceInstance> serviceInstancesNewList = new ArrayList<>();
			ServiceInstance nightSurferServiceInstance = null;
			ServiceInstance nonaddOnServiceInstance = null;
			if (serviceInstances != null && !serviceInstances.isEmpty()) {
				for (ServiceInstance si : serviceInstances) {
					if (si.getService().isAddOn()) {
						nightSurferServiceInstance = si;
					} else {
						nonaddOnServiceInstance = si;
					}
				}
				if (nightSurferServiceInstance != null) {
					serviceInstancesNewList.add(nightSurferServiceInstance);
				}
				if (nonaddOnServiceInstance != null) {
					serviceInstancesNewList.add(nonaddOnServiceInstance);
				}
			}
			Iterator<ServiceInstance> serviceInstanceIter = serviceInstancesNewList
					.iterator();

			while (serviceInstanceIter.hasNext()) {
				ServiceInstance serviceInstance = serviceInstanceIter.next();

				logger.debug("removeCustomerFromPilot() : Calling removeServiceFromDevice for serviceInstance with id : "
						+ serviceInstance.getServiceInstanceId()
						+ " for mdn = " + mdn);

				int vispResponse = 1;
				if (terminateVisp) {
					vispResponse = serviceManager
							.removeServiceFromDevice(serviceInstance);

					if (vispResponse != 0) { // serviceFromDevice is removed
												// successfully for all
												// serviceInstances
						logger.error("terminateAllInstances() : Could not successfully remove from visp for serviceInstance id : "
								+ serviceInstance.getServiceInstanceId()
								+ " for mdn = " + mdn);
						plabDeletion = false;
					} else {
						logger.debug("terminateAllInstances() : Removed service Instance from Visp : "
								+ serviceInstance.getServiceInstanceId());
						plabDeletion = true;
					}
				} else {
					logger.debug("Visp not invoke to terminate");
					plabDeletion = true;
				}

				for (RuleInstance ruleInstance : serviceInstance.getRules()) {
					ruleInstance
							.setEndDate(new Timestamp(new Date().getTime()));
					ruleInstanceRepository.saveAndFlush(ruleInstance);
					logger.debug("terminateAllInstances() : End dated ruleInstance with id : "
							+ ruleInstance.getRuleInstanceId()
							+ " for mdn = "
							+ mdn);
				}

				serviceInstance.setEndTmstamp(new Timestamp(new Date()
						.getTime()));
				serviceInstanceRepository.saveAndFlush(serviceInstance);
				logger.debug("terminateAllInstances() : End dated serviceInstance with id : "
						+ serviceInstance.getServiceInstanceId()
						+ " for mdn = " + mdn);

			}

			offerInstance.setEndTmstamp(new Timestamp(new Date().getTime()));
			offerInstanceRepository.saveAndFlush(offerInstance);
			logger.debug("terminateAllInstances() : End dated offerInstance with id : "
					+ offerInstance.getOfferInstanceId() + " for mdn = " + mdn);
		}
		return plabDeletion;
	}

	/**
	 * Save EXTERNALACTIONMAPPING table and avoid adding duplicate entries.
	 * 
	 * @return
	 */
	public void addCustomerToAccActionSummary(Long customerId, Long acctNum) {
		// add to queue
		if (customerId != null && acctNum != null) {
			try {
				String custIdStr = customerId.toString();
				String acctNumStr = acctNum.toString();
				String custIdAcctNumStr = custIdStr + "-" + acctNumStr;

				List<String> dcList = Arrays.asList(dcNameList.split(","));
				for (String dc : dcList) {
					IQueue<String> rbmQueue = distributedService
							.getHazelcastInstance().getQueue(
									"RBM_EXTERNAL_ACTION_QUEUE_" + dc);
					if (rbmQueue != null
							&& !rbmQueue.contains(custIdAcctNumStr)) {
						rbmQueue.add(custIdAcctNumStr);
					} else {
						logger.debug(
								"CustomerID-AcctNmm {} already present in queue.",
								custIdAcctNumStr);
					}
				}
			} catch (Exception ex) {
				logger.error("Exception in addCustomerToAccActionSummary. Unable to add to Queue : RBM_EXTERNAL_ACTION_QUEUE_"
						+ helper.findMyDataCenterSuffix());
			}
		}
	}

	/**
	 * QUEUE : "RBM_EXTERNAL_ACTION_QUEUE, HazelcastRbmListener to be called
	 * from Topic.
	 * 
	 * @param custIdAcctNumStr
	 */
	public void addCustomerToAccActionSummaryFromQueue(
			final String custIdAcctNumStr) {

		logger.debug(
				"Received {} from RBM_EXTERNAL_ACTION_QUEUE_"
						+ helper.findMyDataCenterSuffix(), custIdAcctNumStr);

		// Only used to print the queue name.
		IQueue<String> rbmQueue = distributedService.getHazelcastInstance()
				.getQueue(
						"RBM_EXTERNAL_ACTION_QUEUE_"
								+ helper.findMyDataCenterSuffix());

		try {
			if (custIdAcctNumStr != null && !custIdAcctNumStr.isEmpty()) {
				List<ExternalActionMapping> externalActionMappings = externalActionMappingRepository
						.findByAccountSpecificBoo(PlabConstants.ACC_ACTION_BOO);
				List<Account> accounts = accountRepository
						.findByAcctNum(custIdAcctNumStr);

				if (accounts != null && !accounts.isEmpty()) {
					// Only one account expected
					for (Account account : accounts) {
						if (externalActionMappings != null
								&& !externalActionMappings.isEmpty()) {
							for (ExternalActionMapping externalActionMapping : externalActionMappings) {

								// TODO : Is this check required for CRUD
								// Repository ? To be tested...
								AccExternalActionPK accExternalActionPK = new AccExternalActionPK();
								accExternalActionPK
										.setAccountNum(custIdAcctNumStr);
								accExternalActionPK.setDomainId(account
										.getDomainId());
								accExternalActionPK
										.setExternalAction(externalActionMapping
												.getExternalActionId() != null ? Long
												.parseLong(externalActionMapping
														.getExternalActionId())
												: null);
								accExternalActionPK
										.setMessageTypeId(externalActionMapping
												.getMessageTypeId() != null ? Long
												.parseLong(externalActionMapping
														.getMessageTypeId())
												: null);
								accExternalActionPK
										.setMessageSubTypeId(externalActionMapping
												.getMessageSubTypeId() != null ? Long
												.parseLong(externalActionMapping
														.getMessageSubTypeId())
												: null);

								AccExternalAction accExternalActionExisting = accExternalActionRepository
										.findOne(accExternalActionPK);

								if (accExternalActionExisting == null) {
									AccExternalAction accExternalAction = new AccExternalAction();
									accExternalAction
											.setAccountNum(custIdAcctNumStr);
									accExternalAction.setDomainId(account
											.getDomainId());
									accExternalAction
											.setExternalAction(externalActionMapping
													.getExternalActionId() != null ? Long
													.parseLong(externalActionMapping
															.getExternalActionId())
													: null);
									accExternalAction
											.setMessageTypeId(externalActionMapping
													.getMessageTypeId() != null ? Long
													.parseLong(externalActionMapping
															.getMessageTypeId())
													: null);
									accExternalAction
											.setMessageSubTypeId(externalActionMapping
													.getMessageSubTypeId() != null ? Long
													.parseLong(externalActionMapping
															.getMessageSubTypeId())
													: null);
									accExternalAction
											.setEnableBoo(externalActionMapping
													.getEnabledBoo());

									// save to AccExternalAction table.
									accExternalActionRepository
											.save(accExternalAction);
									logger.debug(
											"Transaction successfully committed to rbm database. AccNum : {} , DomainId {}, ExternalActionId : {}, Message Type : {} , Message SubType : {} , EnableBoo : {}",
											accExternalAction.getAccountNum(),
											accExternalAction.getDomainId(),
											accExternalAction
													.getExternalAction(),
											accExternalAction
													.getMessageTypeId(),
											accExternalAction
													.getMessageSubTypeId(),
											accExternalAction.getEnableBoo());
								}
							}
						} else {
							logger.info(
									"No entries added to ExternalActionMapping for Customer ID - Acct Num : {}",
									custIdAcctNumStr);
						}
					}
				} else {
					logger.info(
							"No entries added to ExternalActionMapping for Customer ID - Acct Num : {}",
							custIdAcctNumStr, rbmQueue.getName());
					// Modified 11/28/2106: As discussed with Amr, we don't need
					// to add it back to the queue as we already have 3
					// different queues.
					// addBackToRbmQueue(custIdAcctNumStr);
				}
			}
		} catch (Exception ex) {
			logger.error(
					"Exception in saveOrUpdateAccActionSummary. Queue size {}. Adding: Customer-Account :  {} back to queue {}",
					custIdAcctNumStr, rbmQueue.size(),
					helper.findMyDataCenterSuffix());
			logger.error("Exception : ", ex.getMessage(), ex);

			Timer timer = new Timer(true);
			TimerTask timerTask = null;
			try {
				timerTask = new TimerTask() {
					@Override
					public void run() {
						try {
							IQueue<String> rbmQueue = distributedService
									.getHazelcastInstance()
									.getQueue(
											"RBM_EXTERNAL_ACTION_QUEUE_"
													+ helper.findMyDataCenterSuffix());
							if (rbmQueue != null
									&& !rbmQueue.contains(custIdAcctNumStr)) {
								logger.debug("Adding to queue : {}",
										rbmQueue.getName());
								rbmQueue.put(custIdAcctNumStr);
							} else {
								logger.debug(
										"CustomerID-AcctNmm {} already present in queue.",
										custIdAcctNumStr);
							}
						} catch (Exception ex) {
							logger.error(
									"Exception in HazelcastRbmListener.HazelcastRbmRunner. Message : {}, Stacktrace : {} ",
									ex.getMessage(), ex);
						}
					}
				};

				timer.schedule(timerTask, timerTaskDelay);

			} catch (Exception exp) {
				logger.error(
						"Unable to add {} back to queue {}. Queue size : {} ",
						custIdAcctNumStr, rbmQueue.getName(), rbmQueue.size());
				logger.error("Exception : ", exp.getMessage(), exp);

				if (timerTask != null) {
					timerTask.cancel();
				}
				if (timer != null) {
					timer.cancel();
				}
			}
		}
	}

	/**
	 * @deprecated : The code has been moved to HazelCastRbmListerner. Common
	 *             functionality to add back to rbm queue
	 * @param custIdAcctNumStr
	 * @throws Exception
	 */
	/*
	 * public void addBackToRbmQueue (final String custIdAcctNumStr) throws
	 * Exception{ List<String> dcList = Arrays.asList(dcNameList.split(","));
	 * for (String dc : dcList) { if
	 * (!helper.findMyDataCenterSuffix().equalsIgnoreCase(dc)) { IQueue<String>
	 * rbmQueue = distributedService.getHazelcastInstance().getQueue(
	 * "RBM_EXTERNAL_ACTION_QUEUE_" + dc); if (rbmQueue != null &&
	 * !rbmQueue.contains(custIdAcctNumStr)) {
	 * logger.debug("Putting into queue : {}",rbmQueue.getName());
	 * rbmQueue.put(custIdAcctNumStr); } else {
	 * logger.debug("CustomerID-AcctNmm {} already present in queue."
	 * ,custIdAcctNumStr); } } else {
	 * logger.debug("Account {} doesn't belong to DC {} ",custIdAcctNumStr,dc);
	 * } } }
	 */

	/**
	 * Update survey selection and answers as per customers previous selections
	 * and update survey status as per the questions answered (complete)
	 * /unanswered (incomplete) / completely unanswered (new survey)
	 * 
	 * @param surveys
	 * @param choiceInstances
	 */
	public void updateCustomerSurvey(List<Survey> surveys,
			List<ChoiceInstance> choiceInstances) {

		if (surveys != null) {
			for (Survey s : surveys) {
				// update customer selection as per the choice instance's passed
				for (SurveyQuestion sQ : s.getQuestions()) {
					for (Choice c : sQ.getChoice()) {
						for (ChoiceInstance cI : choiceInstances) {
							if (cI.getChoice().getId() == c.getId()) {
								c.setSelected(cI.getSelected());
							}
						}
					}
				}
				// update survey status as per the questions answered (complete)
				// /unanswered (incomplete) / completely unanswered (new survey)
				// updateCustomerSurveyStatus(s); //this is done in Survey
				// itself

			}
		} else {
			logger.debug("GetOffers () : survey's were null or empty. No Survey's were updated.");
		}
	}

	/**
	 * update customer survey status
	 * 
	 * @param s
	 */
	public void updateCustomerSurveyStatus(Survey s) {
		if (s.getQuestions() != null) {
			if (s.getQuestions().size() == s.getQuestionsUnansweredCount()) {
				s.setSurveyStatus(PlabConstants.SURVEY_NEW);
			} else if (s.getQuestionsUnansweredCount() != 0
					&& s.getQuestions().size() != s
							.getQuestionsUnansweredCount()) {
				s.setSurveyStatus(PlabConstants.SURVEY_INCOMPLETE);
			} else {
				s.setSurveyStatus(PlabConstants.SURVEY_COMPLETE);
			}
		}
	}

	/**
	 * 
	 * @param offerInstanceId
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity postAcceptOfferForSuccess(String offerInstanceId) {

		logger.debug(
				"Executing OfferController postAcceptOfferForSuccess, offerinstance id :  {} ",
				offerInstanceId);


		try {

			// Check if the offer instance exists.
			OfferInstance offerInstance = offerInstanceRepository.findOne(Long
					.parseLong(offerInstanceId));

			if (offerInstance == null) {
				logger.error("No valid Offer Instance found for  offerinstance id :  "
						+ offerInstanceId);
				return new ResponseEntity(utility.createResponseInfo(
						HttpStatus.NOT_FOUND, "OfferInstance: "
								+ offerInstanceId + " not found",
						PlabConstants.STATUS_ERROR), HttpStatus.NOT_FOUND);
			}

			VerizonEntity vzEntityOrig = offerInstance.getVerizonEntity();
			String primaryMdn = vzEntityOrig.getMdn();
			Offer offer = offerInstance.getOffer();
			String selectedServiceType = null;

			String serviceType = null;

			boolean addOn = false;
			for (com.vzwcorp.pricinglab.vo.Service service : offer
					.getServices()) {
				if (service.isAddOn())
					addOn = true;

				if (selectedServiceType == null) {
					serviceType = serviceManager.getServiceType(service);
					if (!serviceType.equals("BaseService")) {
						selectedServiceType = serviceType;
					}
				}
			}

			List<OfferInstance> offerInstanceList = offerInstanceRepository
					.findOfferInstancesByCustomerAccount(offer.getOfferId(),
							vzEntityOrig.getCustIdNo(),
							vzEntityOrig.getAcctNo());

			if (offerInstanceList == null || offerInstanceList.isEmpty()) {
				logger.error(
						"No active Offer Instances found for CustID : {} , AcctNo : {} , OfferID : {} ",
						vzEntityOrig.getCustIdNo(), vzEntityOrig.getAcctNo(),
						offer.getOfferId());
				return new ResponseEntity(utility.createResponseInfo(
						HttpStatus.NOT_FOUND, "OfferInstance: "
								+ offerInstanceId + " not found",
						PlabConstants.STATUS_ERROR), HttpStatus.NOT_FOUND);
			}

			Long[] indicator = { PlabConstants.PRE_ACCEPT };
			List<PlabCust> plabCustList = plabCustRepository
					.findByOfferAndCustIdNoAndAcctNoAndIndicator(
							offer.getOfferId(), vzEntityOrig.getCustIdNo(),
							vzEntityOrig.getAcctNo(), indicator);

			if (plabCustList == null || plabCustList.isEmpty()) {
				logger.error(
						"No eligible PLAB Customers found for accepting offer with CustID : {} , AcctNo : {} , OfferID : {} ",
						vzEntityOrig.getCustIdNo(), vzEntityOrig.getAcctNo(),
						offer.getOfferId());
				return new ResponseEntity(utility.createResponseInfo(
						HttpStatus.NOT_FOUND, "OfferInstance: "
								+ offerInstanceId + " not found",
						PlabConstants.STATUS_ERROR), HttpStatus.NOT_FOUND);
			}

			logger.debug("No of account members invited : "
					+ plabCustList.size());

			List<String> enrolledMdn = new ArrayList<String>();

			for (OfferInstance offerInst : offerInstanceList) {

				String custMdn = offerInst.getVerizonEntity().getMdn();
				enrolledMdn.add(custMdn);

				logger.debug(
						"Updating Indicator for Customer with CustID : {} , AcctNo : {} , MDN : {} ",
						vzEntityOrig.getCustIdNo(), vzEntityOrig.getAcctNo(),
						custMdn);
				// Update Indicator in PLAB, GRID, REF GRID
				updateIndicatorInPlabcustAndRefgridAndGrid(custMdn,
						plabCustList, PlabConstants.ENROLLED, null);

				logger.debug(
						"Successfully Updated Indicator in PLAB GRID, REFGRID "
								+ "for Customer with CustID : {} , AcctNo : {} , MDN : {} ",
						vzEntityOrig.getCustIdNo(), vzEntityOrig.getAcctNo(),
						custMdn);

				// Provision to VISP
				List<ServiceInstance> serviceInstanceList = serviceInstanceRepository
						.findLatestByOfferInstance(offerInst
								.getOfferInstanceId());

				if (serviceInstanceList == null
						|| serviceInstanceList.isEmpty()) {
					logger.debug(
							"Could not find active Service Instance for Customer with CustID : {} , AcctNo : {} , MDN : {} , "
									+ "with Offerinstance id : {} , Continuing with next MDN in account",
							vzEntityOrig.getCustIdNo(),
							vzEntityOrig.getAcctNo(), custMdn,
							offerInst.getOfferInstanceId());
					continue;
				}

				// Sending BaseService before Night Surfer Service
				if (addOn) {
					ServiceInstance baseServInstance = null;
					for (ServiceInstance servInstance : serviceInstanceList) {
						if ("BaseService".equalsIgnoreCase(serviceManager
								.getServiceType(servInstance.getService()))) {
							baseServInstance = servInstance;
							break;
						}
					}

					if (baseServInstance != null) {

						logger.debug(
								"Provisioning Base service in VISP for Customer with "
										+ "CustID : {} , AcctNo : {} , MDN : {} , with ServiceInstance ID : {} ",
								vzEntityOrig.getCustIdNo(),
								vzEntityOrig.getAcctNo(), custMdn,
								baseServInstance.getServiceInstanceId());

						serviceManager.addVispServiceToDevice(baseServInstance);

						logger.debug(
								"Successfully Provisioned Base service in VISP for Customer with "
										+ "CustID : {} , AcctNo : {} , MDN : {} , with ServiceInstance ID : {} ",
								vzEntityOrig.getCustIdNo(),
								vzEntityOrig.getAcctNo(), custMdn,
								baseServInstance.getServiceInstanceId());

					} else {
						logger.error(
								"No Base Service instance found for Customer with "
										+ "CustID : {} , AcctNo : {} , MDN : {}  ",
								vzEntityOrig.getCustIdNo(),
								vzEntityOrig.getAcctNo(), custMdn);
						return new ResponseEntity(utility.createResponseInfo(
								HttpStatus.EXPECTATION_FAILED,
								"Base Service Instance not found",
								PlabConstants.STATUS_ERROR),
								HttpStatus.EXPECTATION_FAILED);
					}

					for (ServiceInstance servInstance : serviceInstanceList) {
						if (!"BaseService".equalsIgnoreCase(serviceManager
								.getServiceType(servInstance.getService()))) {
							logger.debug(
									"Provisioning Base service in VISP for Customer with "
											+ "CustID : {} , AcctNo : {} , MDN : {} , with ServiceInstance ID : {} ",
									vzEntityOrig.getCustIdNo(),
									vzEntityOrig.getAcctNo(), custMdn,
									servInstance.getServiceInstanceId());

							serviceManager.addVispServiceToDevice(servInstance);

							logger.debug(
									"Successfully Provisioned Base service in VISP for Customer with "
											+ "CustID : {} , AcctNo : {} , MDN : {} , with ServiceInstance ID : {} ",
									vzEntityOrig.getCustIdNo(),
									vzEntityOrig.getAcctNo(), custMdn,
									servInstance.getServiceInstanceId());
						}
					}
				} else {
					for (ServiceInstance servInstance : serviceInstanceList) {
						logger.debug(
								"Provisioning Base service in VISP for Customer with "
										+ "CustID : {} , AcctNo : {} , MDN : {} , with ServiceInstance ID : {} ",
								vzEntityOrig.getCustIdNo(),
								vzEntityOrig.getAcctNo(), custMdn,
								servInstance.getServiceInstanceId());

						serviceManager.addVispServiceToDevice(servInstance);

						logger.debug(
								"Successfully Provisioned Base service in VISP for Customer with "
										+ "CustID : {} , AcctNo : {} , MDN : {} , with ServiceInstance ID : {} ",
								vzEntityOrig.getCustIdNo(),
								vzEntityOrig.getAcctNo(), custMdn,
								servInstance.getServiceInstanceId());
					}
				}
			}

			// Update RBM
			// addCustomerToAccActionSummary : add entry to ACCEXTERNALACTION
			// table.

			logger.debug(
					"Updating RBM for Customer with CustID : {} , AcctNo : {} , MDN : {} ",
					vzEntityOrig.getCustIdNo(), vzEntityOrig.getAcctNo(),
					primaryMdn);

			addCustomerToAccActionSummary(vzEntityOrig.getCustIdNo(),
					vzEntityOrig.getAcctNo());

			logger.debug(
					"Successfully Updated RBM for Customer with CustID : {} , AcctNo : {} , MDN : {} ",
					vzEntityOrig.getCustIdNo(), vzEntityOrig.getAcctNo(),
					primaryMdn);
			// Schedule jobs for Primary Customer MDN

			logger.debug(
					"Scheduling the jobs for Customer with CustID : {} , AcctNo : {} , MDN : {} ",
					vzEntityOrig.getCustIdNo(), vzEntityOrig.getAcctNo(),
					primaryMdn);
			// Find primary plab customer
			PlabCust primaryCustomer = null;
			for (PlabCust plabCust : plabCustList) {
				if (plabCust.getMdn().equals(primaryMdn)) {
					primaryCustomer = plabCust;
					break;
				}
			}

			if (primaryCustomer == null) {
				primaryCustomer = plabCustRepository.findByOfferAndMdn(
						offer.getOfferId(), primaryMdn);
			}
			createJobsForCustomer(primaryCustomer, enrolledMdn);

			logger.debug(
					"Scheduling jobs completed for Customer with CustID : {} , AcctNo : {} , MDN : {} ",
					vzEntityOrig.getCustIdNo(), vzEntityOrig.getAcctNo(),
					primaryMdn);

			// Send plan welcome message by One message
			if (selectedServiceType != null) {

				String oneMessageType = null;
				if ("NightSurfer".equalsIgnoreCase(selectedServiceType)) {
					oneMessageType = OneMessageType.NightSurferWelcome
							.toString();
				} else if ("SpeedTier".equalsIgnoreCase(selectedServiceType)) {
					oneMessageType = OneMessageType.SelectYourSpeedWelcome
							.toString();
				}
				StringBuilder mdnString = new StringBuilder();
				for (String mdn1 : enrolledMdn) {
					mdnString.append("," + mdn1);
				}

				logger.debug(
						"Sending {} One Message for Customer with CustID : {} , AcctNo : {} , MDN : {} ",
						oneMessageType, vzEntityOrig.getCustIdNo(),
						vzEntityOrig.getAcctNo(), primaryMdn);

				oneMessageService.sendMessage(oneMessageType, vzEntityOrig
						.getCustIdNo(), vzEntityOrig.getAcctNo(), vzEntityOrig
						.getMdn(), mdnString.toString().replaceFirst(",", ""));

				logger.debug(
						"One Message call completed for Customer with CustID : {} , AcctNo : {} , MDN : {} ",
						vzEntityOrig.getCustIdNo(), vzEntityOrig.getAcctNo(),
						primaryMdn);
			}

			logger.debug(
					"Accepted the offer successfully  for Customer with CustID : {} , AcctNo : {} , MDN : {} , Offer InstanceID : {} ",
					vzEntityOrig.getCustIdNo(), vzEntityOrig.getAcctNo(),
					primaryMdn, offerInstanceId);

			return new ResponseEntity(utility.createResponseInfo(HttpStatus.OK,
					"Successfully accepted the offer for Offer InstanceID : "
							+ offerInstanceId + " , MDNs : " + enrolledMdn,
					PlabConstants.STATUS_SUCCESS), HttpStatus.OK);

		} catch (Exception ex) {
			logger.error(
					"Exception while accepting offer for  Offer InstanceID : "
							+ offerInstanceId + " : " + ex.getMessage(), ex);
			return new ResponseEntity(utility.createResponseInfo(
					HttpStatus.INTERNAL_SERVER_ERROR,
					PlabConstants.ERROR_ACCEPT_OFFER_MSG,
					PlabConstants.STATUS_ERROR),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity postAcceptOfferForFailure(String offerInstanceId) {

		logger.debug(
				"Terminating all PLAB instances for Offerinstance id :  {} ",
				offerInstanceId);
		
		try {

			// Check if the offer instance exists.
			OfferInstance offerInstance = offerInstanceRepository.findOne(Long
					.parseLong(offerInstanceId));

			if (offerInstance == null) {
				logger.error("No valid Offer Instance found for  offerinstance id :  "
						+ offerInstanceId);
				return new ResponseEntity(utility.createResponseInfo(
						HttpStatus.NOT_FOUND, "OfferInstance: "
								+ offerInstanceId + " not found",
						PlabConstants.STATUS_ERROR), HttpStatus.NOT_FOUND);
			}

			VerizonEntity vzEntityOrig = offerInstance.getVerizonEntity();
			String primaryMdn = vzEntityOrig.getMdn();
			Offer offer = offerInstance.getOffer();

			List<OfferInstance> offerInstanceList = offerInstanceRepository
					.findOfferInstancesByCustomerAccount(offer.getOfferId(),
							vzEntityOrig.getCustIdNo(),
							vzEntityOrig.getAcctNo());

			if (offerInstanceList == null || offerInstanceList.isEmpty()) {
				logger.error(
						"No active Offer Instances found for CustID : {} , AcctNo : {} , OfferID : {} ",
						vzEntityOrig.getCustIdNo(), vzEntityOrig.getAcctNo(),
						offer.getOfferId());
				return new ResponseEntity(utility.createResponseInfo(
						HttpStatus.NOT_FOUND, "OfferInstance: "
								+ offerInstanceId + " not found",
						PlabConstants.STATUS_ERROR), HttpStatus.NOT_FOUND);
			}

			logger.debug(
					"Invoking terminateAllInstancesFromPlabAndVisp for  CustID : {} , AcctNo : {} , MDN : {} , Offerinstance id :  {} ",
					vzEntityOrig.getCustIdNo(), vzEntityOrig.getAcctNo(),
					primaryMdn, offerInstanceId);

			boolean status = terminateAllInstancesFromPlabAndVisp(
					offerInstanceList, primaryMdn, false);

			logger.debug(
					"Completed terminateAllInstancesFromPlabAndVisp for  CustID : {} , AcctNo : {} , MDN : {} , Offerinstance id :  {} ",
					vzEntityOrig.getCustIdNo(), vzEntityOrig.getAcctNo(),
					primaryMdn, offerInstanceId);

			// Update PLAB indicator to -2

			Long[] indicator = { PlabConstants.PRE_ACCEPT };
			List<PlabCust> plabCustList = plabCustRepository
					.findByOfferAndCustIdNoAndAcctNoAndIndicator(
							offer.getOfferId(), vzEntityOrig.getCustIdNo(),
							vzEntityOrig.getAcctNo(), indicator);

			if (plabCustList != null && !plabCustList.isEmpty()) {
				for (PlabCust plabCust : plabCustList) {
					plabCust.setIndicator(PlabConstants.INVITED_IND);
					plabCustRepository.saveAndFlush(plabCust);
				}
			}

			if (status) {
				logger.debug(
						"Customer account has been successfully reset for  CustID : {} , AcctNo : {} , MDN : {} , Offerinstance id :  {} ",
						vzEntityOrig.getCustIdNo(), vzEntityOrig.getAcctNo(),
						primaryMdn, offerInstanceId);
				return new ResponseEntity(
						utility.createResponseInfo(HttpStatus.OK,
								"Successfully reset customer account for Offerinstance id : "
										+ offerInstanceId,
								PlabConstants.STATUS_SUCCESS), HttpStatus.OK);
			} else {
				logger.debug(
						"Unable to fully reset Customer account for  CustID : {} , AcctNo : {} , MDN : {} , Offerinstance id :  {} ",
						vzEntityOrig.getCustIdNo(), vzEntityOrig.getAcctNo(),
						primaryMdn, offerInstanceId);

				return new ResponseEntity(utility.createResponseInfo(
						HttpStatus.INTERNAL_SERVER_ERROR,
						"Unable to fully reset Customer account for Offerinstance id : "
								+ offerInstanceId, PlabConstants.STATUS_ERROR),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception ex) {
			logger.error(
					"Exception while reseting offer for  Offer InstanceID : "
							+ offerInstanceId + " : " + ex.getMessage(), ex);
			return new ResponseEntity(utility.createResponseInfo(
					HttpStatus.INTERNAL_SERVER_ERROR,
					PlabConstants.ERROR_ACCEPT_OFFER_MSG,
					PlabConstants.STATUS_ERROR),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}

	public boolean customerWithActiveOfferInstance(Long custId, Long acctNo) {

		boolean enrolled = false;

		List<VerizonEntity> vzEntityList = verizonEntityRepository
				.findByCustIdNoAndAcctNo(custId, acctNo); // //this is to
															// prevent
															// enrollment when
															// the cust has
															// already enrolled
		if (vzEntityList != null && !vzEntityList.isEmpty()) {
			for (VerizonEntity vzEntityTemp : vzEntityList) {
				List<OfferInstance> activeOfferInstancesList = offerInstanceRepository
						.findLatestOfferInstanceByVerizonEntity(vzEntityTemp
								.getVerizonEntityId());
				if (activeOfferInstancesList != null
						&& !activeOfferInstancesList.isEmpty()) {
					enrolled = true;
					break;
				}
			}
			return enrolled;
		} else {
			return false;
		}
	}

	public List<Choice> getChoiceList(Offer offer) {
		List<Choice> choiceList = null;
		;
		List<ServiceQuestion> serviceQList = offer.getOfferOptions();
		if (serviceQList != null && !serviceQList.isEmpty()) {
			ServiceQuestion serviceQuestion = serviceQList.get(0); // only 1
																	// service
																	// Question
																	// expected
			choiceList = serviceQuestion.getChoice();
			return choiceList;
		} else {
			return null;
		}
	}

	public void createDefaultChoiceInstance(Offer offer,
			OfferInstance offerInstance,
			com.vzwcorp.pricinglab.vo.Service service, String serviceType) {

		List<Choice> choiceList = getChoiceList(offer);

		if (choiceList != null && !choiceList.isEmpty()) {
			List<ChoiceInstance> choiceInstanceList = new ArrayList<>();
			offerInstance.setChoiceInstances(choiceInstanceList);
			for (Choice choice : choiceList) {
				ChoiceInstance choiceInstance = new ChoiceInstance();
				if (serviceType != null && !serviceType.isEmpty()) {
					if (serviceType
							.equals(PlabConstants.NIGHT_SURFER_SERVICE_TYPE)) {
						choiceInstance.setSelected(false);
					} else if (serviceType
							.equals(PlabConstants.SPEED_TIER_SERVICE_TYPE)) {
						if (choice.getTitle().equalsIgnoreCase(
								PlabConstants.SPEED_FAST)) {
							choiceInstance.setSelected(true);
						} else {
							choiceInstance.setSelected(false);
						}
					}
				}
				choiceInstance.setChoice(choice);
				choiceInstance.setOfferInstance(offerInstance);
				choiceInstance.setDateCreated(new Timestamp(new Date()
						.getTime()));
				offerInstance.getChoiceInstances().add(choiceInstance);
				choiceInstanceRepository.saveAndFlush(choiceInstance);
			}

		}

	}

	public com.vzwcorp.pricinglab.vo.Service getService(Offer offer) {
		com.vzwcorp.pricinglab.vo.Service service = null;
		List<com.vzwcorp.pricinglab.vo.Service> serviceList = offer
				.getServices();
		if (serviceList != null && !serviceList.isEmpty()) {
			service = serviceList.get(0);
		}
		return service;
	}

	public ResponseEntity issueMlmo(String custId, String acctNo,
			List<PlabCust> plabCustList, Map<String, List<String>> mdnSpoList,
			String spoToBeRemoved, String actionIndicator) {

		Map<String, String> pvResponse = dvsManager.productValidation(custId,
				acctNo, plabCustList, mdnSpoList, spoToBeRemoved,
				actionIndicator);

		if (pvResponse != null
				&& !pvResponse.isEmpty()
				&& pvResponse.get(DvsConstants.ERROR_IN_RETRIEVE_CUST_PROFILE) != null) {
			logger.debug(DvsConstants.ERROR_IN_RETRIEVE_CUST_PROFILE
					+ " : "
					+ pvResponse
							.get(DvsConstants.ERROR_IN_RETRIEVE_CUST_PROFILE));
			return new ResponseEntity(
					utility.createResponseInfo(
							HttpStatus.MULTI_STATUS,
							DvsConstants.ERROR_IN_RETRIEVE_CUST_PROFILE
									+ " : "
									+ pvResponse
											.get(DvsConstants.ERROR_IN_RETRIEVE_CUST_PROFILE),
							PlabConstants.STATUS_SUCCESS),
					HttpStatus.MULTI_STATUS);
		} else if (pvResponse != null
				&& !pvResponse.isEmpty()
				&& pvResponse.get(DvsConstants.ERROR_IN_PRODUCTVALIDATION) != null) {
			logger.debug(DvsConstants.ERROR_IN_PRODUCTVALIDATION + " : "
					+ pvResponse.get(DvsConstants.ERROR_IN_PRODUCTVALIDATION));
			return new ResponseEntity(
					utility.createResponseInfo(
							HttpStatus.MULTI_STATUS,
							DvsConstants.ERROR_IN_PRODUCTVALIDATION
									+ " : "
									+ pvResponse
											.get(DvsConstants.ERROR_IN_PRODUCTVALIDATION),
							PlabConstants.STATUS_SUCCESS),
					HttpStatus.MULTI_STATUS);
		} else {
			Map<String, String> mlmoResponse = dvsManager.issueMlmoRequest(
					pvResponse, custId.toString(), acctNo.toString());
			if (mlmoResponse != null
					&& !mlmoResponse.isEmpty()
					&& mlmoResponse.get(DvsConstants.ERROR_IN_MLMO_REQUEST) != null) {
				logger.debug(DvsConstants.ERROR_IN_MLMO_REQUEST + " : "
						+ mlmoResponse.get(DvsConstants.ERROR_IN_MLMO_REQUEST));
				return new ResponseEntity(
						utility.createResponseInfo(
								HttpStatus.MULTI_STATUS,
								DvsConstants.ERROR_IN_MLMO_REQUEST
										+ " : "
										+ mlmoResponse
												.get(DvsConstants.ERROR_IN_MLMO_REQUEST),
								PlabConstants.STATUS_SUCCESS),
						HttpStatus.MULTI_STATUS);
			} else {
				logger.debug("Vision mlmo and plab activation processed successfully");
				return new ResponseEntity(
						utility.createResponseInfo(
								HttpStatus.OK,
								"Vision mlmo and plab activation processed successfully",
								PlabConstants.STATUS_SUCCESS), HttpStatus.OK);
			}
		}
	}

	public ResponseEntity issueMlmoAddOrRemoveSpo(String custId, String acctNo,
			List<PlabCust> plabCustList, Map<String, List<String>> mdnSpoList,
			String spoToBeRemoved, String actionIndicator) {

		Map<String, String> pvResponse = dvsManager.productValidation(custId,
				acctNo, plabCustList, mdnSpoList, spoToBeRemoved,
				actionIndicator);

		if (pvResponse != null
				&& !pvResponse.isEmpty()
				&& pvResponse.get(DvsConstants.ERROR_IN_RETRIEVE_CUST_PROFILE) != null) {
			logger.debug(DvsConstants.ERROR_IN_RETRIEVE_CUST_PROFILE
					+ " : "
					+ pvResponse
							.get(DvsConstants.ERROR_IN_RETRIEVE_CUST_PROFILE));
			return new ResponseEntity(
					utility.createResponseInfo(
							HttpStatus.INTERNAL_SERVER_ERROR,
							DvsConstants.ERROR_IN_RETRIEVE_CUST_PROFILE
									+ " : "
									+ pvResponse
											.get(DvsConstants.ERROR_IN_RETRIEVE_CUST_PROFILE),
							PlabConstants.STATUS_ERROR),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} else if (pvResponse != null
				&& !pvResponse.isEmpty()
				&& pvResponse.get(DvsConstants.ERROR_IN_PRODUCTVALIDATION) != null) {
			logger.debug(DvsConstants.ERROR_IN_PRODUCTVALIDATION + " : "
					+ pvResponse.get(DvsConstants.ERROR_IN_PRODUCTVALIDATION));
			return new ResponseEntity(
					utility.createResponseInfo(
							HttpStatus.INTERNAL_SERVER_ERROR,
							DvsConstants.ERROR_IN_PRODUCTVALIDATION
									+ " : "
									+ pvResponse
											.get(DvsConstants.ERROR_IN_PRODUCTVALIDATION),
							PlabConstants.STATUS_ERROR),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			Map<String, String> mlmoResponse = dvsManager.issueMlmoRequest(
					pvResponse, custId.toString(), acctNo.toString());
			if (mlmoResponse != null
					&& !mlmoResponse.isEmpty()
					&& mlmoResponse.get(DvsConstants.ERROR_IN_MLMO_REQUEST) != null) {
				logger.debug(DvsConstants.ERROR_IN_MLMO_REQUEST + " : "
						+ mlmoResponse.get(DvsConstants.ERROR_IN_MLMO_REQUEST));
				return new ResponseEntity(
						utility.createResponseInfo(
								HttpStatus.INTERNAL_SERVER_ERROR,
								DvsConstants.ERROR_IN_MLMO_REQUEST
										+ " : "
										+ mlmoResponse
												.get(DvsConstants.ERROR_IN_MLMO_REQUEST),
								PlabConstants.STATUS_ERROR),
						HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				logger.debug("Vision mlmo and plab activation processed successfully");
				return new ResponseEntity(
						utility.createResponseInfo(
								HttpStatus.OK,
								"Vision mlmo and plab activation processed successfully",
								PlabConstants.STATUS_SUCCESS), HttpStatus.OK);
			}
		}
	}

	public Timestamp parseStringToTimestamp(String inputDate, String format) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			Date parsedDate = dateFormat.parse(inputDate);
			Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
			return timestamp;
		} catch (Exception ex) {

			logger.error("Exception while parsing string to timestamp "
					+ inputDate + " : " + ex.getMessage(), ex);
			return null;
		}
	}

	/**
	 * Get Account Details from Grid or from memory whereever it can be
	 * retrieved from.
	 * 
	 * @param strategy
	 * @param custId
	 * @param acctNo
	 * @return
	 */
	public List<SubCustAcctMdn> getCustomerAccountDetails(
			SearchStrategy strategy, Long custId, Long acctNo) {

		List<SubCustAcctMdn> accountMembers = null;

		try {

			if (strategy != null && custId != null && acctNo != null) {
				if (strategy == SearchStrategy.FIND_ACTIVE_CUSTOMER_DETAILS_FROM_GRID) {

					// implementation for replacing SubCustAcctMdn with Grid
					// call.
					logger.info(
							"Attempting grid search for cust {} , acctNo : {} ",
							custId, acctNo);

					String gridResponseXml = gridService
							.getCustomerAccountDetails(custId.toString(),
									acctNo.toString());
					if (gridResponseXml != null && !gridResponseXml.isEmpty()) {

						Document doc = gridService
								.getParsedDocument(gridResponseXml);

						if (doc != null) {
							NodeList nList = doc
									.getElementsByTagName("ReverseCustomerUnique");
							for (int i = 0; i < nList.getLength(); i++) {
								SubCustAcctMdn subCustAcctMdn = new SubCustAcctMdn();
								// TE is terminated, SU - Suspended, SB -
								// Suspended but Blocked, OK - Active
								String status = null;

								Node nNode = nList.item(i);

								NodeList childNodes = nNode.getChildNodes();

								for (int j = 0; j < childNodes.getLength(); j++) {

									Node nChildNode = childNodes.item(j);

									if ("bsCustId".equalsIgnoreCase(nChildNode
											.getNodeName())) {
										subCustAcctMdn.setCustIdNo(Long
												.parseLong(nChildNode
														.getFirstChild()
														.getNodeValue()));
									} else if ("bsAcctNum"
											.equalsIgnoreCase(nChildNode
													.getNodeName())) {
										subCustAcctMdn.setAcctNo(Long
												.parseLong(nChildNode
														.getFirstChild()
														.getNodeValue()));
									} else if ("mdn"
											.equalsIgnoreCase(nChildNode
													.getNodeName())) {
										subCustAcctMdn
												.setMdn(nChildNode
														.getFirstChild()
														.getNodeValue());
									} else if ("mtnEffDt"
											.equalsIgnoreCase(nChildNode
													.getNodeName())) {
										SimpleDateFormat sdf = new SimpleDateFormat(
												PlabConstants.STD_DATE_FORMAT);
										Date parsedDate = sdf
												.parse(nChildNode
														.getFirstChild()
														.getNodeValue());
										subCustAcctMdn
												.setMtnEffDt(new Timestamp(
														parsedDate.getTime()));
									} else if ("updatedTime"
											.equalsIgnoreCase(nChildNode
													.getNodeName())) {
										subCustAcctMdn.setEffDt(new Timestamp(
												Long.parseLong(nChildNode
														.getFirstChild()
														.getNodeValue())));
									} else if ("status"
											.equalsIgnoreCase(nChildNode
													.getNodeName())) {
										// if status = OK, set end
										// SubCustAcctMdn END Timestamp =
										// Default 9999=12-31 value
										subCustAcctMdn
												.setEndDt(PricingLabUtility
														.getDefaultEndTimeStamp());
										status = nChildNode.getFirstChild()
												.getNodeValue();
									}
								}
								// new array list allocation only when fist "OK"
								// status is received.
								if (status != null
										&& "OK".equalsIgnoreCase(status)) {
									if (accountMembers == null) {
										accountMembers = new ArrayList<SubCustAcctMdn>();
									}
									accountMembers.add(subCustAcctMdn);
								}
							}
						}
					}
				} else if (strategy == SearchStrategy.FIND_ACTIVE_CUSTOMER_DETAILS_FROM_DB) {
					logger.info(
							"Attempting DB search for cust {} , acctNo : {} ",
							custId, acctNo);
					accountMembers = subCustAcctMdnRepository
							.findByCustomerAndAccountAndEnddate(custId, acctNo);
				}
			} else {
				logger.debug(
						"Account details could not be retrieved for custId : {}, acctNum : {}. Search Type : {}",
						custId, acctNo, strategy.getSearchLocation());
			}

		} catch (Exception ex) {
			logger.error("Exception while reading from grid. {} , {} ",
					ex.getMessage(), ex);
			// try reading from the table again.
			accountMembers = subCustAcctMdnRepository
					.findByCustomerAndAccountAndEnddate(custId, acctNo);
		}

		if (accountMembers == null
				|| (accountMembers != null && accountMembers.isEmpty())) {
			logger.debug(
					"Account details could not be retrieved for custId : {}, acctNum : {} with Search Type : {}",
					custId, acctNo, strategy.getSearchLocation());
		}
		return accountMembers;
	}

	/**
	 * Added new method to accommodate new PLAB changes. It's used to return the
	 * invitee details associated with offers.
	 */
	public List<PlabCust> getCustomerDetails(Long offerId) {
		logger.info("ProductManager getOfferDetails() - start");
		List<PlabCust> plabcustomers = plabCustRepository
				.findByOfferAndIndicator(offerId, new Long[] { -2L, -1L });
		if (plabcustomers != null) {
			logger.debug("PlabCust list size : " + plabcustomers.size());
			for (PlabCust plabCust : plabcustomers) {

				// set offer accepted
				if (plabCust.getIndicator() < 1) {
					plabCust.setOfferAccepted(PlabConstants.NO);
				} else {
					plabCust.setOfferAccepted(PlabConstants.YES);
				}

				OfferInstance offerInstance = getValidOfferInstance(plabCust
						.getMdn());
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				if (offerInstance != null) {
					Date startDate = offerInstance.getDateCreated();

					if (startDate != null) {

						// Set pilot start date
						plabCust.setPilotStartDate(formatter.format(startDate));

						// Set pilot end date
						Calendar c = Calendar.getInstance();
						c.setTime(startDate);
						c.add(Calendar.MONTH, 3);
						plabCust.setPilotEndDate(formatter.format(c.getTime()));
					}

					// set feature status
					Offer offer = offerInstance.getOffer();
					List<ServiceQuestion> offerOptions = offer
							.getOfferOptions();
					if (offerOptions != null && !offerOptions.isEmpty()) {
						for (ServiceQuestion serviceQuestion : offerOptions) {
							List<Choice> choices = serviceQuestion.getChoice();
							if (choices != null && !choices.isEmpty()) {
								for (Choice choice : choices) {
									if (choice.getTitle() != null
											&& PlabConstants.TOGGLE
													.equalsIgnoreCase(choice
															.getTitle())) {
										if (choice.isSelected()) {
											plabCust.setFeatureStatus(PlabConstants.ON);
										} else {
											plabCust.setFeatureStatus(PlabConstants.OFF);
										}
										break;
									}
								}
							}
						}
					}

					// set exited date
					plabCust.setExitedDate(offerInstance.getEndTmstamp());
				}
			}
		}
		logger.info("ProductManager getOfferDetails() - end");
		return plabcustomers;
	}
}
