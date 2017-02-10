package com.vzwcorp.pricinglab.loader.profile.ubsr.repository;

import com.vzwcorp.pricinglab.loader.util.LoaderQueries;
import com.vzwcorp.pricinglab.profile.vo.SubXltBlRbm;
import com.vzwcorp.pricinglab.profile.vo.SubXltBlRbmPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface SubXltBlRbmRepository extends CrudRepository<SubXltBlRbm, SubXltBlRbmPK> {

	List<SubXltBlRbm> findByMdn(@Param("mdn") String mdn);

	List<SubXltBlRbm> findByCustIdNoAndAcctNo(@Param("custIdNo") Long custIdNo, @Param("acctNo") Long acctNo);

	@Query("SELECT t FROM SubXltBlRbm t where t.custIdNo = :custIdNo AND t.acctNo = :acctNo AND t.blIdTyp = :blIdTyp AND t.blIdValue = :blIdValue AND t.rbmIdTyp = :rbmIdTyp AND (t.mdn IN (' ', 'N/A') or t.mdn is NULL) AND  TO_CHAR(t.endTmstamp, 'YYYY-MM-DD') = '9999-12-31' ")
	SubXltBlRbm findBycustIdAndacctIdAndblIdtypeAndblIdValue(@Param("custIdNo")  Long custIdNo, @Param("acctNo") Long acctNo,@Param("blIdTyp") String blIdTyp,@Param("blIdValue") String blIdValue,@Param("rbmIdTyp") String rbmIdTyp);
	
	@Query("SELECT t FROM SubXltBlRbm t where t.custIdNo = :custIdNo AND t.acctNo = :acctNo AND t.mdn = :mdn "
			+ "AND TO_CHAR(t.endTmstamp, 'YYYY-MM-DD') = '9999-12-31' AND t.deleteInd = 'N' ")
	List<SubXltBlRbm> findBycustIdAndacctIdAndMdn(@Param("custIdNo")  Long custIdNo, @Param("acctNo") Long acctNo, @Param("mdn") String mdn);
	
	@Query("SELECT t FROM SubXltBlRbm t where t.custIdNo = :custIdNo AND t.acctNo = :acctNo AND  t.blIdTyp = 'SPLAN' AND t.rbmIdTyp = 'PD' "
			+ " AND t.effTmstamp <=  :effTmstamp  AND  t.endTmstamp > :effTmstamp ")
	SubXltBlRbm findSplanBycustIdAndacctIdAndeffectiveDate(@Param("custIdNo")  Long custIdNo, @Param("acctNo") Long acctNo, @Param("effTmstamp") Timestamp effTmstamp);
	
	
	
	@Query("SELECT t FROM SubXltBlRbm t where t.custIdNo = :custIdNo AND t.acctNo = :acctNo AND t.mdn = :mdn "
			+ " AND TO_CHAR(t.endTmstamp, 'YYYY-MM-DD') = '9999-12-31' AND t.blIdTyp in ( 'SFO', 'SPOPM') ")
	List<SubXltBlRbm> findActiveSFoBycustIdAndacctIdAndMdn(@Param("custIdNo")  Long custIdNo, @Param("acctNo") Long acctNo, @Param("mdn") String mdn);
	

	
	@Query("SELECT t FROM SubXltBlRbm t where t.custIdNo = :custIdNo AND t.acctNo = :acctNo AND t.mdn = :mdn "
			+ " AND TO_CHAR(t.endTmstamp, 'YYYY-MM-DD') <> '9999-12-31' AND t.blIdTyp in ( 'SFO') ")
	List<SubXltBlRbm> findEndedSFoBycustIdAndacctIdAndMdn(@Param("custIdNo")  Long custIdNo, @Param("acctNo") Long acctNo, @Param("mdn") String mdn);
	
	
	//w641601
	@Query(LoaderQueries.GET_SUB_XLT_BL_RBM_TO_ARCHIVE)
	List<SubXltBlRbm> findSubXltBlRbmToArchive(@Param("custIdNo")  Long custIdNo, @Param("acctNo") Long acctNo, @Param("mdn") String mdn);
	
	
	//w641601
	@Query(LoaderQueries.GET_SUB_XLT_BL_RBM_TO_UN_END)
	SubXltBlRbm findSubXltBlRbmToUnEnd(@Param("custIdNo")  Long custIdNo, @Param("acctNo") Long acctNo, @Param("mdn") String mdn , @Param("blIdValue") String blIdValue, @Param("endTmstamp") Timestamp endTmstamp );

	//w641601
	@Query(LoaderQueries.GET_RBM_SEQ_ID_TO_DELETED_RBM)
	List<SubXltBlRbm> findSubXltBlRbmTodeleteFromRBM(@Param("custIdNo")  Long custIdNo, @Param("acctNo") Long acctNo, @Param("mdn") String mdn , @Param("effTmstamp") Timestamp effTmstamp );

	//w641601
	@Query(LoaderQueries.GET_SPLAN_DETAIL)
	SubXltBlRbm findSubXltBlRbmSplanDetail(@Param("custIdNo")  Long custIdNo, @Param("acctNo") Long acctNo );

	//w641601
	@Query(LoaderQueries.GET_SXBR_ROW_CANTENNA_END_DT)
	SubXltBlRbm findSubXltBlRbmCantennaEndDt(@Param("custIdNo")  Long custIdNo, @Param("acctNo") Long acctNo ,  @Param("mdn") String mdn ,  @Param("blIdValue") String blIdValue );
	
	//w641601
	@Query(LoaderQueries.GET_SXBR_LATEST_CANTENNA_ROW)
	SubXltBlRbm findSubXltBlRbmLatestCantennaRow(@Param("custIdNo")  Long custIdNo, @Param("acctNo") Long acctNo ,  @Param("mdn") String mdn ,  @Param("blIdValue") String blIdValue );
	
	//w641601
	@Query(LoaderQueries.GET_SXBR_CANTENNA_TO_UNEND)
	SubXltBlRbm findSubXltBlRbmCantennaToUnEnd(@Param("custIdNo")  Long custIdNo, @Param("acctNo") Long acctNo ,  @Param("mdn") String mdn ,  @Param("blIdValue") String blIdValue);
	
	/**
	 * @author w641127
	 * @param custIdNo
	 * @param acctNo
	 * @return
	 */
	@Query(LoaderQueries.GET_PEER_PROD_SEQ)
	SubXltBlRbm findPeerGroupProdSequeceForAccount(@Param("custIdNo")  Long custIdNo, @Param("acctNo") Long acctNo);
	
	/**
	 * @author w641127
	 * @param custIdNo
	 * @param acctNo
	 * @param mdn
	 * @param blIdValue
	 * @return
	 */
	@Query(LoaderQueries.GET_SUB_XLT_BL_RBM_PROD_SEQ_VAL)
	SubXltBlRbm findSubXltBlRbmToGetRbmPordSeqValue(@Param("custIdNo")  Long custIdNo, @Param("acctNo") Long acctNo, @Param("mdn") String mdn , @Param("blIdValue") String blIdValue);
	
	/**
	 * @author w641127
	 * @param custIdNo
	 * @param acctNo
	 * @return
	 */
	@Query(LoaderQueries.GET_SPLAN_DETAILS)
	SubXltBlRbm findSubXltBlRbmToGetSplanDetails(@Param("custIdNo")  Long custIdNo, @Param("acctNo") Long acctNo);
	
	//w641601
	/**
	 * @param custIdNo
	 * @param acctNo
	 * @param mdn
	 * @param endTmstamp
	 * @return
	 */
	@Query(LoaderQueries.GET_SXBR_TO_ADJUST_END_DATE)
	List<SubXltBlRbm> findSubXltBlRbmListAdjustEndDate(@Param("custIdNo")  Long custIdNo, @Param("acctNo") Long acctNo ,  @Param("mdn") String mdn ,  @Param("endTmstamp") Timestamp endTmstamp);
		
	//w641601
	/**
	 * @param custIdNo
	 * @param acctNo
	 * @param mdn
	 * @param blIdValue
	 * @param endTmstamp
	 * @return
	 */
	@Query(LoaderQueries.GET_SXBR_ROW_TO_ADJUST_END_DATE)
	SubXltBlRbm findSubXltBlRbmAdjustEndDate(@Param("custIdNo")  Long custIdNo, @Param("acctNo") Long acctNo ,  @Param("mdn") String mdn , @Param("blIdValue") String blIdValue,  @Param("endTmstamp") Timestamp endTmstamp);

	/**
	 * 
	 * @author w641127
	 * @param custIdNo
	 * @param acctNo
	 * @param mdn
	 * @param blIdValue
	 * @return
	 */
	@Query(LoaderQueries.GET_SUB_XLT_BL_RBM_END_DATE)
	SubXltBlRbm findSubXltBlRbmEndDate(@Param("custIdNo")  Long custIdNo, @Param("acctNo") Long acctNo ,  @Param("mdn") String mdn , @Param("blIdValue") String blIdValue);

	/**
	 * @author w641127
	 * @param custIdNo
	 * @param acctNo
	 * @param mdn
	 * @param blIdValue
	 * @param effTmstamp
	 * @return
	 */
	@Query(LoaderQueries.GET_SUB_XLT_BL_RBM_END_DATE_1)
	SubXltBlRbm findSubXltBlRbmEndDate(@Param("custIdNo")  Long custIdNo, @Param("acctNo") Long acctNo ,  @Param("mdn") String mdn , @Param("blIdValue") String blIdValue, @Param("effTmstamp") Timestamp effTmstamp);
	
	/**
	 * @author w641127
	 * @param custIdNo
	 * @param acctNo
	 * @param mdn
	 * @param blIdValue
	 * @param endTmstamp
	 * @return
	 */
	@Query(LoaderQueries.GET_SXBR_ROW_TO_ADJUST_END_DATE_1)
	SubXltBlRbm findSubXltBlRbmCantennaEndDate(@Param("custIdNo")  Long custIdNo, @Param("acctNo") Long acctNo ,  @Param("mdn") String mdn , @Param("blIdValue") String blIdValue,  @Param("endTmstamp") Timestamp endTmstamp);

	/**
	 * @author w641127
	 * @param custIdNo
	 * @param acctNo
	 * @param mdn
	 * @param blIdValue
	 * @return
	 */
	@Query(LoaderQueries.GET_LATEST_PROD_INFO)
	SubXltBlRbm findSubXltBlRbmCantennaLatestBProd(@Param("custIdNo")  Long custIdNo, @Param("acctNo") Long acctNo ,  @Param("mdn") String mdn , @Param("blIdValue") String blIdValue);


}


