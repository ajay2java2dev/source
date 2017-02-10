package com.vzwcorp.pricinglab.constants;

/**
 * Created by menonka on 7/5/2016.
 */
public interface PlabConstants {

	// Date Format
	String STD_DATE_FORMAT = "yyyy-MM-dd";
	String STD_TIMESTAMP_FORMAT = "yyyy-MM-dd HH.mm.ss";
	String STD_VISP_TIMESTAMP_FORMAT = "yyyy-MM-dd hh:mm:ss";
	String MAPPER_DATE_FORMAT = "yyyy-MM-dd";
	public static final String END_DATE_TIME_STAMP = "9999-12-31 23.59.59";

	// MDN Status Message
	String MDN_ELIG = "MDN_ELIGIBLE"; // Valid Pilot MDN if hitting getOffers
	String MDN_NOT_ELIG = "MDN_NOT_ELIGIBLE"; // Valid Pilot MDN if hitting
												// getOffers
	String MDN_ENROL = "MDN_ENROLLED"; // Valid Pilot MDN but already enrolled
										// for Pilot
	String PILOT_EXP = "PILOT_EXPIRED"; // Valid Pilot MDN but Pilot Expired
	String PILOT_FULL = "PILOT_FULL"; // Pilot MDN but Pilot full for the quota
	String DEFAULT_VISP_SERVICE_INSTANCE_ID = "VISP_9999";

	// Custom Message
	String NO_PLAB_CUST_MSG = "Customer not found in pricing lab";
	String RESPONSE_CONTENT = "Content";
	String STATUS_SUCCESS = "Success";
	String STATUS_ERROR = "Error";
	String NOMDN_OR_NOOFFER_MSG = "MDN is invalid or there are no offers in plab.";
	String NO_OFFERS_MSG = "No offers available in plab";
	String ERROR_ACCEPT_OFFER_MSG = "Error accepting Offer";
	String ERROR_INSTANCE_NOT_SAVED_MSG = "Instance not saved";

	// FORMATS
	String END_DATE = "9999-12-31";
	String CPI_USER = "CPI_USER";
	Long INVITED_IND = new Long(-2);
	Long PRE_ACCEPT = new Long(-1);
	Long ENROLLED = new Long(1);
	Long REMOVED_PENDING_EOC = new Long(-4);
	Long EARLY_TERMINATE_PENDING_EOC = new Long(-6);
	Long EARLY_TERMINATE_VISION_REMOVED = new Long(-5);
	Long REMOVING = new Long(-3);
	Integer MAX_PILOT_SIZE = new Integer(2100);
	String SPEED_SELECTION_TYPE = "Slider";

	String QOS_RULETYPE_BANDWIDTH = "bandwidth";
	String QOS_RULETYPE_DV_FORMAT = "digital_video";

	String SIMPLE_PAGE_OFFER_OPTION_TYPE = "OfferOption";
	String SIMPLE_PAGE_OFFER_DETAIL_TYPE = "OfferDetail";
	String SIMPLE_PAGE_TANDC_TYPE = "Terms&Conditions";

	Character DELETE_OFFER_OPS = 'D';
	Character ACCEPT_CUST_OFFER_OPS = 'A';
	Character UPDATE_CUST_OFFER_OPS = 'U';
	Character REMOVE_CUST_PILOT_OPS = 'R';

	String DEFAULT_CREATED_BY_ADMIN = "PLAB_ADMIN";
	String DEFAULT_CREATED_BY_USER = "PLAB_USER";
	String DEFAULT_CREATED_BY_CPI = "CPI_USER";

	String TERM_COND_ACCEPT = "accept";
	String TERM_COND_DECLINE = "decline";

	byte ADD_SERVICE_TO_DEVICE = 1;
	byte UPDATE_SERVICE_FOR_DEVICE = 2;
	byte REMOVE_SERVICE_FROM_DEVICE = 3;

	String SPEED_FASTEST = "FASTEST";
	String SPEED_FASTER = "FASTER";
	String SPEED_FAST = "FAST";

	String RULE_EXPRESSION_NORMAL_MODE = "NORMALMODE";
	String RULE_EXPRESSION_SAFETY_MODE = "SAFETYMODE";
	String BASE_QOS_RULE_NAME = "BaseService-QOSRule";

	String MCS_PRICEPLAN_PACKAGE_TYPE = "One Time";
	String MCS_CONTENT_TYPE = "DATA";
	String MCS_NIGHT_SURFER_CONTENT_ID = "BEAKER_ITEM_DATA"; // for night surfer
	String MCS_PACKAGE_INFO = "com.vzwcorp.pricinglab.vo.mcs.ezcheckout";
	String MCS_FAST_BUNDLE_ID = "BEAKER_BUNDLE_BASE";
	String MCS_FASTER_BUNDLE_ID = "BEAKER_BUNDLE_DELUX";
	String MCS_FASTEST_BUNDLE_ID = "BEAKER_BUNDLE_PREMIUM";
	String SPEED_DATA_USAGE_DESC = "Here's your data usage by line";
	String SPEED_DATA_USAGE_BYSPEED_DESC = "Here's your data usage by Speed";
	String SPEED_TAP_TO_CHANGE_MESSAGE = "You're on Select Your Speed.";
	String SPEED_TAP_TO_CHANGE_SUB_MESSAGE = "Set the speeds you want";
	String SPEED_DATA_HUB_DESCRIPTION = "All the data you can use.Pick your speed and price.";
	String SPEED_DATA_HUB_PLAN_DESCRIPTION = "of your plan";
	String SPEED_DATA_HUB_UPDATE_DESCRIPTION = "Update Select your Speed";
	String NIGHTSURFER_DATA_USAGE_DESC = "Here's your data usage from";
	String NIGHTSURFER_TAP_TO_CHANGE_MESSAGE = "You're on Night Surfer.";
	String NIGHTSURFER_TAP_TO_CHANGE_SUB_MESSAGE = "Set up night surfing";
	String NIGHTSURFER_DATA_HUB_DESCRIPTION = "Surf all night.No worries of data overage.";
	String NIGHTSURFER_DATA_HUB_UPDATE_DESCRIPTION = "Update NightSurfer";

	String VERIZON_ENTITY = "verizon entity";
	String PLAB_CUST = "plab_cust";
	String REF_GRID_PLAB_CUST = "ref_grid_plab_cust";

	String INSERT_OPS = "I";
	String UPDATE_OPS = "U";
	String DELETE_OPS = "D";

	String GRID_ADD = "ADD";
	String GRID_UPDATE = "UPDATE";
	String GRID_DELETE = "DELETE";

	String GRID_ADD_TRANSID = "23456700";
	String GRID_UPDATE_TRANSID = "23456700";
	String GRID_DELETE_TRANSID = "2345679";

	String ACC_ACTION_BOO = "T";

	String SURVEY_INCOMPLETE = "INCOMPLETE";
	String SURVEY_COMPLETE = "COMPLETE";
	String SURVEY_NEW = "NEW SURVEY";
	String NIGHT_SURFER_SERVICE_TYPE = "NightSurfer";

	String TZ_DEFAULT_CDT = "CDT"; // daylight enabled
	String TZ_DEFAULT_EDT = "EDT"; // daylight enabled
	String TZ_DEFAULT_PDT = "PDT"; // daylight enabled
	String TZ_DEFAULT_VISP_CDT = "CST6CDT"; // visp return
	String TZ_DEFAULT_VISP_EDT = "EST5EDT"; // visp return
	String TZ_DEFAULT_VISP_PDT = "PST8PDT"; // visp return
	String TZ_DEFAULT_CST = "CST";
	String TZ_DEFAULT_EST = "EST";
	String TZ_DEFAULT_PST = "PST";
	String PLAB = "PLAB";
	String VZWINTERNET = "VZWINTERNET";
	String LINE_ACTIVATE = "ACT";
	String LINE_DE_ACTIVATE = "DEA";
	String SUSPEND = "SUS";
	String RE_ASSIGN = "REA";
	String DP_CUST_BL_CYCLE = "DP-CUST_BL_CYCLE";
	String DP_LN_SVC_PROD = "DP-LN_SVC_PROD";
	String TRANSFER = "TRN";
	String CHANGE = "CHG";
	String RECONNECT = "REC";

	String SPEED_TIER_SERVICE_TYPE = "SPEED_TIER_SERVICE_TYPE";

	String RESIDUAL_USAGE = "residualUsage";

	String YES = "Yes";
	String NO = "No";
	String ON = "On";
	String OFF = "Off";
	String TOGGLE = "Toggle";
}
