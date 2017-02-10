package com.vzwcorp.pricinglab.loader.profile.ubsr.repository;

import com.vzwcorp.pricinglab.profile.vo.PlabUsage;
import com.vzwcorp.pricinglab.profile.vo.PlabUsagePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlabUsageRepository extends JpaRepository<PlabUsage, PlabUsagePK> {
	@Query("SELECT t FROM PlabUsage t where t.mdn = :mdn and rownum=1 order by t.ubsrTS desc ")
	public PlabUsage findByMdn(@Param("mdn") String mdn);
}
