package com.vzwcorp.pricinglab.vo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vzwcorp.pricinglab.vo.BillingInfo;

public interface BillingInfoRepository extends JpaRepository<BillingInfo, String> {

	@Query(value = "SELECT t FROM BillingInfo t WHERE t.serviceInstance.offerInstance.offerInstanceId = :offerInstanceId")
	List<BillingInfo> findByOfferInstanceId(@Param("offerInstanceId")Long offerInstanceId);
	
	@Query(value = "SELECT t FROM BillingInfo t WHERE t.serviceInstance.offerInstance.offerInstanceId = :offerInstanceId AND t.firsttimeofUsage >= :startTime AND t.lasttimeofUsage <= :endTime")
	List<BillingInfo> findByOfferInstanceIdAndStartTimeAndEndTime(@Param("offerInstanceId")Long offerInstanceId,@Param("startTime")String startTime,@Param("endTime")String endTime);
	
	@Query("SELECT b FROM BillingInfo b WHERE b.serviceInstance.serviceInstanceId = :serviceInstanceId and b.dateCreated = (select max(t.dateCreated) from BillingInfo t where t.serviceInstance.serviceInstanceId = :serviceInstanceId)" )
	BillingInfo findLatestByServiceInstanceId(@Param("serviceInstanceId")Long serviceInstanceId);
	
}
