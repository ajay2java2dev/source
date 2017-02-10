package com.vzwcorp.pricinglab.vo.repository;

import com.vzwcorp.pricinglab.vo.UsageRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsageRecordRepository extends JpaRepository<UsageRecord, Long> {

	@Query("select t from UsageRecord t where  t.mdn = :mdn and t.dateCreated = (select max(t.dateCreated) from UsageRecord t where t.mdn = :mdn)" )
	public UsageRecord findPrevUsageByMdn(@Param("mdn") String mdn);
}
