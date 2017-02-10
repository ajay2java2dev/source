package com.vzwcorp.pricinglab.loader.util;

public class LoaderQueries {


	public static final String DLT_SUSPEND_SUB_CUST_ACCT_MDN = "DELETE FROM SubCustAcctMdn t WHERE trunc(t.effDt) = :effDt "
			+ " AND t.custIdNo  =  :custIdNo AND t.acctNo =:acctNo  AND t.mdn=:mdn "
			+ " AND t.currPriInd = 'C' AND t.custMtnStatCd IN('S','B')";

	public static final String SUSPEND_MDN_DETAILS = "SELECT t  FROM SubCustAcctMdn t WHERE "
			+ " t.custIdNo  =  :custIdNo AND t.acctNo =:acctNo  AND t.mdn=:mdn "
			+ " AND t.currPriInd = 'C' AND t.custMtnStatCd IN('S','B')";


	public static final String PRORATE_GET_SUSPENDED_MDN_DETAILS = "SELECT OUTERSUBCUSTACCTMDN FROM SubCustAcctMdn OUTERSUBCUSTACCTMDN WHERE custIdNo  =  :custIdNo  AND acctNo = :acctNo  AND mdn=:mdn "
			+ "AND currPriInd = 'P' AND deleteInd = 'N' AND TO_CHAR(endTimestamp, 'YYYY-MM-DD') != '9999-12-31' AND effTimestamp = ( SELECT MAX(effTimestamp) FROM SubCustAcctMdn"
			+ " WHERE custIdNo = OUTERSUBCUSTACCTMDN.custIdNo AND acctNo = OUTERSUBCUSTACCTMDN.acctNo AND mdn = OUTERSUBCUSTACCTMDN.mdn AND  currPriInd = OUTERSUBCUSTACCTMDN.currPriInd)"; 		

	
	// w641601
	public static final String GET_OLD_MDN_EFF_DATE = " SELECT t  FROM SubCustAcctMdn t WHERE custIdNo  =  :custIdNo  AND acctNo = :acctNo  AND mdn = :mdn "
			+ "AND t.currPriInd = 'C' AND t.custMtnStatCd  IN ('A', 'AT', 'AR', 'AC', 'S', 'B') AND TO_CHAR(t.endTimestamp, 'YYYY-MM-DD') = '9999-12-31' )";
		
	
	// w641601
		public static final String GET_OLD_MDN_ROW_TO_ARCHIVE = "SELECT t  FROM SubCustAcctMdn t WHERE custIdNo  =  :custIdNo  AND acctNo = :acctNo  AND mdn=:mdn "
				+ "AND TRUNC(t.mtnEffDt) = TRUNC( :mtnEffDt) )";
	
	// w641601
	public static final String GET_OLD_MDN_ROW_TO_UPDATE = "SELECT t  FROM SubCustAcctMdn t WHERE custIdNo  =  :custIdNo  AND acctNo = :acctNo  AND mdn=:mdn "
						+ "AND TRUNC(t.mtnEffDt) = TRUNC( :mtnEffDt) AND TO_CHAR(t.endTimestamp, 'YYYY-MM-DD') = '9999-12-31' )";
	
	// w641601
	public static final String GET_ACTIVE_SUB_SF_MDN = "SELECT t  FROM SubSfMdn t WHERE custIdNo  =  :custIdNo  AND acctNo = :acctNo  AND mdn= :mdn "
			+ " AND sfOfferingId = :sfOfferingId  AND TO_CHAR(t.endTmstamp , 'YYYY-MM-DD') = '9999-12-31' )";
	
	
	// w641601
		public static final String GET_SUB_SF_MDN_TO_ARCHIVE = "SELECT t  FROM SubSfMdn t WHERE custIdNo  =  :custIdNo  AND acctNo = :acctNo  AND mdn = :mdn ";
		
		// w641601
		public static final String GET_SUB_XLT_BL_RBM_TO_ARCHIVE = "SELECT t FROM SubXltBlRbm t where t.custIdNo = :custIdNo AND t.acctNo = :acctNo AND t.mdn = :mdn "
				+ " AND t.blIdTyp not in ('NAF', 'SPOPM')";
			
		// w641601
		public static final String GET_CUSTEVENTSOURCE_RBM = "SELECT t FROM Custeventsource t where t.customerRef = :customerRef AND t.productSeq = :productSeq AND t.eventSource = :eventSource ";
		
		
		// w641601
		public static final String GET_SUB_SF_MDN_TO_UNEND = "SELECT t  FROM SubSfMdn t WHERE custIdNo  =  :custIdNo  AND acctNo = :acctNo  AND mdn= :mdn "
				+ " AND sfOfferingId = :sfOfferingId  AND TO_CHAR(t.endDt , 'YYYY-MM-DD') = TO_CHAR(:endDt , 'YYYY-MM-DD' ) AND TO_CHAR(t.mtnEffDt , 'YYYY-MM-DD') = TO_CHAR(:mtnEffDt , 'YYYY-MM-DD' )";
		
		// w641601
		public static final String GET_SUB_XLT_BL_RBM_TO_UN_END = "SELECT t FROM SubXltBlRbm t where t.custIdNo = :custIdNo AND t.acctNo = :acctNo AND t.mdn = :mdn "
			+ "  AND t.blIdValue = :blIdValue AND TO_CHAR(t.endTmstamp, 'YYYY-MM-DD') = TO_CHAR( :endTmstamp, 'YYYY-MM-DD') ";
		
		/**
		 * w641127
		 * Query constant to fetch latest deactivated effective date.
		 */			

		public static final String GET_LATEST_DEACTIVATED_EFFT_DATE = "SELECT TO_CHAR(MAX(t.effDt),'YYYY-MM-DD') FROM SubCustAcctMdn t  "
				+ "WHERE t.custIdNo = :custIdNo AND t.acctNo = :acctNo  AND t.mdn = :mdn AND t.custMtnStatCd = 'D'";

		
		/**
		 * w641127
		 * Query to get same day deactivated record count.
		 */
		public static final String GET_SAMEDAY_DEACTIVATED_COUNT = "SELECT COUNT(*) FROM SubCustAcctMdn t where custIdNo = :custIdNo "
				+" and t.acctNo = :acctNo and t.mdn = :mdn and TO_CHAR(t.mtnEffDt, 'YYYY-MM-DD') = :mtnEffDt and  TO_CHAR(t.effDt, 'YYYY-MM-DD') = :effDt "
				+" and t.custMtnStatCd = 'D' and t.currPriInd = 'C'";	
		
		/**
		 * w641127
		 * Query to get deactivated mdn records
		 */
		public static final String GET_DEACTIVATED_MDN_RECORD = "SELECT t FROM SubCustAcctMdn t WHERE TO_CHAR(t.effDt, 'YYYY-MM-DD') = :effDt "
				+"AND custIdNo = :custIdNo AND acctNo = :acctNo AND mdn = :mdn AND TO_CHAR(mtnEffDt, 'YYYY-MM-DD') = :mtnEffDt AND "
				+ "t.currPriInd = 'C' AND t.custMtnStatCd = 'D' ";

		/**
		 * w641127
		 * Query for delete deactiavated mdn record
		 */
		public static final String DEL_DEACTIVATED_MDN_RECORD =  "DELETE from SubCustAcctMdn t WHERE TO_CHAR(t.effDt, 'YYYY-MM-DD')  = :effDt " 
				+" AND custIdNo = :custIdNo  AND acctNo = :acctNo  AND mdn = :mdn AND  TO_CHAR(mtnEffDt, 'YYYY-MM-DD') = :mtnEffDt "
			    +" AND currPriInd = 'C' AND custMtnStatCd = 'D'";
		
		/**
		 * w641127
		 * Query to fetch deactivate mdn records
		 */
		public static final String DEACTIVATE_MDN_DETAILS = "SELECT t  FROM SubCustAcctMdn t WHERE  t.custIdNo  =  :custIdNo AND t.acctNo =:acctNo  AND t.mdn=:mdn "
				+ " AND TRUNC(t.mtnEffDt) = TO_DATE(:mtnEffDt,'YYYY-MM-DD') AND TO_CHAR(t.endTimestamp, 'YYYY-MM-DD') = '9999-12-31' ) AND t.custMtnStatCd = 'D' ";
			
		/**
		 *  w641601 Deactivate Mdn Queries
		 */
		
		public static final String GET_OLD_MDN_ROW_TO_DEACTIVATE = "SELECT t  FROM SubCustAcctMdn t WHERE custIdNo  =  :custIdNo  AND acctNo = :acctNo  AND mdn=:mdn "
				+ "AND TRUNC(t.mtnEffDt) = TRUNC( :mtnEffDt) AND trim(t.custMtnStatCd) NOT IN ('D', 'C', 'R', 'T') AND TO_CHAR(t.endTimestamp, 'YYYY-MM-DD') = '9999-12-31' )";
		
		
		public static final String GET_DEACTIVATE_MDN_ROW_SAME_DAY = "SELECT t  FROM SubCustAcctMdn t WHERE custIdNo  =  :custIdNo  AND acctNo = :acctNo  AND mdn=:mdn "
				+ "AND TRUNC(t.mtnEffDt) = TRUNC( :mtnEffDt) AND trunc(t.effDt) = trunc(:effDt) ) AND trim(t.custMtnStatCd) in ('A') AND t.currPriInd = 'C' " ;
		
		
		public static final String GET_DEACTIVATE_MDN_ROW_TO_ARCHIVE = "SELECT t  FROM SubCustAcctMdn t WHERE custIdNo  =  :custIdNo  AND acctNo = :acctNo  AND mdn = :mdn "
				+ "AND TRUNC(t.mtnEffDt) = TRUNC( :mtnEffDt) AND trunc(t.effDt) >= trunc(:effDt) ) AND t.custMtnStatCd in ('A' ) AND t.currPriInd = 'C' ";
		
		
		public static final String GET_RBM_SEQ_ID_TO_DELETED_RBM = "SELECT t FROM SubXltBlRbm t where t.custIdNo = :custIdNo AND t.acctNo = :acctNo AND t.mdn = :mdn "
				+ " AND t.blIdTyp in ( 'SFO') AND t.rbmIdTyp = 'PD'  AND TO_CHAR(t.endTmstamp, 'YYYY-MM-DD') = '9999-12-31' AND t.effTmstamp > :effTmstamp ";

		
		/*
		 * @author w648100
		 * Get Peer Group Product Sequence
		 * 
		 */		
		public static final String GET_PEER_PROD_SEQ = "SELECT t FROM SubXltBlRbm t where t.custIdNo = :custIdNo AND t.acctNo = :acctNo AND t.blIdTyp = 'PGP' "
				+ "AND t.blIdValue = 'PRGRP' AND t.rbmIdTyp = 'SQ' AND (t.mdn IN (' ', 'N/A') or t.mdn is NULL) AND  TO_CHAR(t.endTmstamp, 'YYYY-MM-DD') = '9999-12-31' ";
		
		/**
		 * @author w648100
		 * Get SUB_SF_MDN with effective date and end date
		 *  
		 */
		
		public static final String GET_SUB_SF_MDN = "SELECT t  FROM SubSfMdn t WHERE custIdNo  =  :custIdNo  AND acctNo = :acctNo  AND mdn= :mdn "
		+ " AND sfOfferingId = :sfOfferingId  AND TO_CHAR(t.endTmstamp , 'YYYY-MM-DD') = TO_CHAR(:endTmstamp , 'YYYY-MM-DD' ) AND TO_CHAR(t.mtnEffDt , 'YYYY-MM-DD') = TO_CHAR(:mtnEffDt , 'YYYY-MM-DD' )";
		
		
		/**
		 * @author w648100
		 * Get latest cantenns product info
		 */
		
		public static final String GET_LATEST_PROD_INFO = "SELECT t FROM SubXltBlRbm t where t.custIdNo = :custIdNo AND t.acctNo = :acctNo AND t.mdn = :mdn "	+ "AND t.blIdValue = :blIdValue AND t.rbmEndTmstamp =  " + 
				"(select max(t2.rbmEndTmstamp) from SubXltBlRbm t2 "+
				" where  t2.custIdNo = :custIdNo AND t2.acctNo = :acctNo AND t2.mdn = :mdn AND t2.blIdValue = :blIdValue )";
		
		
		/**
		 * @author w648100
		 * Get Rbm Effective date Of Product
		 * 
		 */
		public static final String GET_RBM_PROD_EFF_DATE = "SELECT t FROM SubXltBlRbm t where t.custIdNo = :custIdNo AND t.rbmSequenceId = :rbmSequenceId AND t.rbmIdTyp = 'PD'" ;
		
		/**
		 * 
		 * @author w648100
		 * 
		 */
		public static final String GET_RBM_PROD_GRT_SYSDATE = "SELECT t FROM SubXltBlRbm t where t.custIdNo = :custIdNo AND t.blIdValue = :blIdValue AND t.mdn = :mdn AND t.acctNo = :acctNo AND t.endTmstamp >:endTmstamp " ;
		
		/**
		 * 
		 * @author w648100
		 * 
		 */
		public static final String GET_RBM_PROD_EFF_END_DATE = "SELECT t FROM SubXltBlRbm t where t.custIdNo = :custIdNo AND t.blIdValue = :blIdValue AND t.mdn = :mdn AND t.acctNo = :acctNo AND "
				+ " TO_CHAR(t.effTmstamp , 'YYYY-MM-DD') = TO_CHAR(:effTmstamp , 'YYYY-MM-DD' ) AND TO_CHAR(t.endTmstamp, 'YYYY-MM-DD') = '9999-12-31'" ;
		
		/**
		 * w641127
		 * Query to fetch sfo ended records.
		 */
		public static final String GET_SFO_ENDED_MDN = "SELECT count(*)  FROM SubSfMdn t WHERE custIdNo  =  :custIdNo  AND acctNo = :acctNo  AND mdn= :mdn "
				+ " AND sfOfferingId = :sfOfferingId  AND TO_CHAR(t.endTmstamp , 'YYYY-MM-DD') != '9999-12-31' )";
		
		/**
		 * @author w641127
		 * Query to fetch deactivated sfo count.
		 */
		public static final String GET_DEACTIVATED_SFO = "Select TO_CHAR(Max(t.effDt),'YYYY-MM-DD')  from SubCustAcctMdnDelrows t "
                     +"where t.custIdNo = :custIdNo and aacctNo = :acctNo and mdn= :mdn and t.custMtnStatCd = 'D'";		
		
		
		/**
		 * @auther w641601
		 */
		public static final String GET_SPLAN_DETAIL = "SELECT t FROM SubXltBlRbm t where t.custIdNo = :custIdNo AND  t.acctNo = :acctNo AND t.blIdTyp = 'SPLAN' AND t.rbmIdTyp = 'PD' "
				+ " AND t.deleteInd = 'N'  AND TO_CHAR(t.endTmstamp, 'YYYY-MM-DD') = '9999-12-31'" ;
		
		
		public static final String GET_CUSTPRODADDONRATEDETAILS_DETAIL = " SELECT t FROM Custprodaddonratedetails t where  t.customerRef = :customerRef and t.productSeq = :productSeq and t.domainId = :domainId "
				+ " and t.eventSource = :eventSource  and TRUNC(t.startDtm) = TRUNC(:startDtm) AND TRUNC(t.endDtm) != TO_DATE('9999-12-31', 'YYYY-MM-DD') ";
		
		/**
		 * @auther w641601
		 */
		public static final String GET_SXBR_ROW_CANTENNA_END_DT = "SELECT t FROM SubXltBlRbm t where t.custIdNo = :custIdNo AND  t.acctNo = :acctNo AND t.mdn = :mdn AND t.blIdValue = :blIdValue " ;
		
		
		
		public static final String GET_ACTIVE_MDN_ROW = "SELECT t  FROM SubCustAcctMdn t WHERE custIdNo  =  :custIdNo  AND acctNo = :acctNo  AND mdn = :mdn "
				+ "AND t.custMtnStatCd in ('A', 'AT', 'AR', 'AC', 'S', 'B') AND t.currPriInd = 'C'  AND TO_CHAR(t.endTimestamp, 'YYYY-MM-DD') = '9999-12-31' AND t.deleteInd = 'N' ";
		
		
		/**
		 * @auther w641601
		 */
		public static final String GET_SXBR_LATEST_CANTENNA_ROW = "SELECT t FROM SubXltBlRbm t where t.custIdNo = :custIdNo AND  t.acctNo = :acctNo AND t.mdn = :mdn AND t.blIdValue = :blIdValue "
				+ " AND t.rbmEndTmstamp  = ( SELECT MAX(t1.rbmEndTmstamp) from SubXltBlRbm t1 where t1.custIdNo = :custIdNo AND  t1.acctNo = :acctNo AND t1.mdn = :mdn AND t1.blIdValue = :blIdValue ) " ;
		
		
		public static final String GET_SXBR_CANTENNA_TO_UNEND = "SELECT t FROM SubXltBlRbm t where t.custIdNo = :custIdNo AND  t.acctNo = :acctNo AND t.mdn = :mdn AND t.blIdValue = :blIdValue "
				+ " AND TO_CHAR(t.endTmstamp, 'YYYY-MM-DD') = '9999-12-31'  " ;
		

		/**
		 * @author w641127
		 * method to get prod sequence value.
		 */
		public static final String GET_SUB_XLT_BL_RBM_PROD_SEQ_VAL = "SELECT t from SubXltBlRbm t  where t.custIdNo = :custIdNo and acctNo = :acctNo "
				+ "and mdn= :mdn and t.blIdValue = :blIdValue and TO_CHAR(t.endTmstamp, 'YYYY-MM-DD') = '9999-12-31'";
		

		/**
		 * method to get Splan details.
		 * @author w641127
		 */
		public static final String GET_SPLAN_DETAILS = "select t from SubXltBlRbm t where t.custIdNo = :custIdNo and acctNo = :acctNo  and t.blIdTyp ='SPLAN' "
				+"and t.rbmIdTyp= 'PD' and TO_CHAR(t.endTmstamp, 'YYYY-MM-DD') = '9999-12-31' and t.deleteInd = 'N'";
		
		/**
		 * @author w641601
		 */
		public static final String GET_SXBR_TO_ADJUST_END_DATE = "SELECT t FROM SubXltBlRbm t where t.custIdNo = :custIdNo AND  t.acctNo = :acctNo AND t.mdn = :mdn AND t.blIdTyp in ('SFO') "
				+ " AND t.rbmIdTyp in ('PD') AND (TRUNC(t.endTmstamp) >= TRUNC(:endTmstamp) ) AND (TO_CHAR(t.endTmstamp, 'YYYY-MM-DD') != '9999-12-31') " ;
		
		public static final String GET_SXBR_ROW_TO_ADJUST_END_DATE = "SELECT t FROM SubXltBlRbm t where t.custIdNo = :custIdNo AND  t.acctNo = :acctNo AND t.mdn = :mdn AND t.blIdValue = :blIdValue "
				+ " AND TRUNC(t.endTmstamp) = TRUNC(:endTmstamp ) " ;
		
		/**
		 * @author w641127
		 * method to end date records.
		 */
		public static final String GET_SUB_XLT_BL_RBM_END_DATE = "SELECT  t from SubXltBlRbm t  where t.custIdNo = :custIdNo and t.acctNo = :acctNo and"
				+ " t.mdn = :mdn and  t.blIdValue = :blIdValue and TRUNC(t.endTmstamp) != TO_DATE('9999-12-31 00:00:00', 'YYYY-MM-DD HH24:MI:SS')";
		
		/**
		 * @author w641127
		 */
		public static final String GET_SUB_XLT_BL_RBM_END_DATE_1 = "SELECT  t from SubXltBlRbm t  where t.custIdNo = :custIdNo and t.acctNo = :acctNo and"
				+ " t.mdn = :mdn and  t.blIdValue = :blIdValue and TRUNC(t.endTmstamp) != TO_DATE('9999-12-31 00:00:00', 'YYYY-MM-DD HH24:MI:SS') "
				+ " AND TRUNC(t.effTmstamp) = TRUNC(TO_TIMESTAMP( :effTmstamp, 'YYYY-MM-DD.HH24.MI.SS.FF'))";
		
		/**
		 * @author w641127
		 */
		public static final String GET_SXBR_ROW_TO_ADJUST_END_DATE_1 = "SELECT t FROM SubXltBlRbm t where t.custIdNo = :custIdNo AND  t.acctNo = :acctNo AND t.mdn = :mdn AND t.blIdValue = :blIdValue "
				+ " AND TRUNC(t.endTmstamp) != TRUNC(:endTmstamp ) " ;
		
		public static final String GET_CUST_ACCT_MDN = "SELECT t FROM SubCustAcctMdn t where t.mdn = :mdn";
				
}



