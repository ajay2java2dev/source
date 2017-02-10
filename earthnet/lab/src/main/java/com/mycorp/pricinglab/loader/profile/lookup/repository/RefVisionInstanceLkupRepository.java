package com.vzwcorp.pricinglab.loader.profile.lookup.repository;

import java.math.BigDecimal;

import com.vzwcorp.pricinglab.profile.lookup.vo.RefVisionInstanceLkup;
import com.vzwcorp.pricinglab.profile.lookup.vo.RefVisionInstanceLkupPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RefVisionInstanceLkupRepository extends CrudRepository<RefVisionInstanceLkup,RefVisionInstanceLkupPK> {
	
	@Query("SELECT t FROM RefVisionInstanceLkup t where t.billingSystemId = :billingSystemId ")
	public RefVisionInstanceLkup findByBillSysId(@Param("billingSystemId") BigDecimal billingSystemId);
	
	@Query("SELECT t FROM RefVisionInstanceLkup t where t.jitrInstance = :jitrInstance ")
	public RefVisionInstanceLkup findByJitrInstance(@Param("jitrInstance") String jitrInstance);

	@Query("SELECT t FROM RefVisionInstanceLkup t where t.billerId = :billerId ")
	public RefVisionInstanceLkup findByBillerId(@Param("billerId") String billerId);

}
