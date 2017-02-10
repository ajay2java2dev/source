package com.vzwcorp.pricinglab.loader.profile.ubsr.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vzwcorp.pricinglab.loader.util.LoaderQueries;
import com.vzwcorp.pricinglab.profile.vo.SubSfMdn;
import com.vzwcorp.pricinglab.profile.vo.SubSfMdnPK;

public interface SubSfMdnRepository extends CrudRepository<SubSfMdn, SubSfMdnPK> {
	
	// w641601
	@Query(LoaderQueries.GET_ACTIVE_SUB_SF_MDN)
	public SubSfMdn findActiveRows(@Param("custIdNo") Long custIdNo, @Param("acctNo") Long acctNo,@Param("mdn") String mdn, @Param("sfOfferingId") Long sfOfferingId);
	
	// w641601
	@Query(LoaderQueries.GET_SUB_SF_MDN_TO_ARCHIVE)
	public List<SubSfMdn> findSubSfMdnRowsToArchive(@Param("custIdNo") Long custIdNo, @Param("acctNo") Long acctNo, @Param("mdn") String mdn);
	
	// w641601
		@Query(LoaderQueries.GET_SUB_SF_MDN_TO_UNEND)
		public SubSfMdn findSfoToBeUnend(@Param("custIdNo") Long custIdNo, @Param("acctNo") Long acctNo, @Param("mdn") String mdn , @Param("sfOfferingId") String sfOfferingId , 
				@Param("endDt") Timestamp endDt , @Param("mtnEffDt") Timestamp mtnEffDt);
	
	
	/**
	 * w641127
	 * method to get SFO ended rows.
	 * @param custIdNo
	 * @param acctNo
	 * @param mdn
	 * @param sfOfferingId
	 * @return
	 */
	@Query(LoaderQueries.GET_SFO_ENDED_MDN)
	public SubSfMdn findSfoEnedRows(@Param("custIdNo") Long custIdNo, @Param("acctNo") Long acctNo,@Param("mdn") String mdn, @Param("sfOfferingId") Long sfOfferingId);
		
}
