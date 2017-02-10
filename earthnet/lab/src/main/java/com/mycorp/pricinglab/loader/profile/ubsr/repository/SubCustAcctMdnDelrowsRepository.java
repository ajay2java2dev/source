package com.vzwcorp.pricinglab.loader.profile.ubsr.repository;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vzwcorp.pricinglab.loader.util.LoaderQueries;
import com.vzwcorp.pricinglab.profile.vo.SubCustAcctMdn;
import com.vzwcorp.pricinglab.profile.vo.SubCustAcctMdnDelrows;
import com.vzwcorp.pricinglab.profile.vo.SubCustAcctMdnPK;

public interface SubCustAcctMdnDelrowsRepository extends CrudRepository<SubCustAcctMdn, SubCustAcctMdnPK> {
	
	/**
	 * w641127
	 * method to get Deactivated Sfos.
	 * @param custIdNo
	 * @param acctNo
	 * @param mdn
	 * @return
	 */
	@Query(LoaderQueries.GET_DEACTIVATED_SFO)
	public SubCustAcctMdnDelrows findDeactivatedSfo(@Param("custIdNo") Long custIdNo, @Param("acctNo") Long acctNo, @Param("mdn") String mdn);

	
	
}
