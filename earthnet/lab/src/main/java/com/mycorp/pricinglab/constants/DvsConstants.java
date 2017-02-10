package com.vzwcorp.pricinglab.constants;

public interface DvsConstants {
	 String PRODUCT_VALIDATION_PACKAGE="com.vzwcorp.pricinglab.vo.productvalidation";
	 String RETRIEVE_CUST_PROFILE_PACKAGE = "com.vzwcorp.pricinglab.vo.dvs.retrievecustomerprofile";
	 String NBS_PACKAGE = "com.vzwcorp.pricinglab.vo.dvs.nbs";
	 String MLMO_REQUEST = "com.vzwcorp.pricinglab.vo.dvs.multilinemixedorderservice";
	 String POUND_DATA_PACKAGE = "com.vzwcorp.pricinglab.vo.pounddata";
	 String BILLING_SYSTEM="VISION";
	 String CLIENT_ID="VZW-PLAB";
	/* String USER_ID="PATESA9";
	 String PASSWORD="01VISION";*/
	 String PRODUCT_VALIDATION_SERVICE_NAME="productValidation";
	 String PRODUCT_VALIDATION_SUBSERVICE_NAME = "validateSelectedAndAutoAttachProducts";
	 String BILL_PRORATE_CALC_SERVICE_NAME = "billProrateCalc";
	 String ORDER_TYPE = "C";
	 String CUSTOMER_TYPE_CODE="PE";
	 String ACTION_INDICATOR_DELETE="D";
	 String ACTION_INDICATOR_ACCEPT="A";
	 String MTN_ORDER_TYPE = "C";
	 String RETRIEVE_CUST_PROFILE_BILLING_SYSTEM="VISION";
	 String RETRIEVE_CUST_PROFILE_SERVICE_NAME="retrieveCustomerProfile";
	 String RETRIEVE_CUST_PROFILE_SUBSERVICE_NAME="byMtn";
	 String MULTILINEMIXEDORDER_SERVICE_NAME="MultiLineMixedOrder";
	 String ERROR_IN_PRODUCTVALIDATION="Error : productValidation";
	 String ERROR_IN_RETRIEVE_CUST_PROFILE="Error : retrieveCustomerProfile";
	 String ERROR_IN_MLMO_REQUEST="Error : issueMlmoRequest";
	 String RETRIEVE_CUSTINFO_KEY ="custProfInfo";
	 String MLMO_RESPONSE = "mlmo_response";
	 String INPUT_XML_REQUEST = "xmlreqdoc";
	 String ERROR_IN_BILL_PRORATE_CALC="Error : billProrateCalc";
	 String BILL_PRORATE_CALC_RESPONSE = "billProrateCalcResponse";
	 String BILL_PRORATE_CALC_NOT_FOUND_RESPONSE = "billProrateCalcNotFoundResponse";
	 String ERROR_IN_BILL_PRORATE_CALC_MOBILE_FIRST_TOTAL_NEXT="Error : MOBILE_FIRST_TOTAL_NEXT";
	 String NBS_SERVICE_NAME ="retrievePricingLabNBS";
}
