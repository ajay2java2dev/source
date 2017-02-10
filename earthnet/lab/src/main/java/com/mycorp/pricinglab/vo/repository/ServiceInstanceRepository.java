package com.vzwcorp.pricinglab.vo.repository;

import com.vzwcorp.pricinglab.vo.OfferInstance;
import com.vzwcorp.pricinglab.vo.ServiceInstance;
import com.vzwcorp.pricinglab.vo.VerizonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ServiceInstanceRepository extends JpaRepository<ServiceInstance, Long> {

	@Query("select t from ServiceInstance t where  t.vispServiceInstanceId = :vispServiceInstanceId  and sysdate <= t.endTmstamp order by t.dateCreated desc")
	List<ServiceInstance> findLatestByVispServiceInstanceId(@Param("vispServiceInstanceId") String vispServiceInstanceId);

	@Query("select t from ServiceInstance t where  t.verizonEntity.verizonEntityId = :verizonEntityId and sysdate <= t.endTmstamp" +
			" and t.service.spoID = :spoID" +
			" and rownum = 1")
	public ServiceInstance findLatestByEntityIdAndSpoId(@Param("verizonEntityId")Long verizonEntityId,@Param("spoID")String spoID);

	@Query("select t from ServiceInstance t where  t.verizonEntity.verizonEntityId = :verizonEntityId and sysdate <= t.endTmstamp" +
			" and rownum = 1")
	public ServiceInstance findLatestDefaultByEntityId(@Param("verizonEntityId")Long verizonEntityId);
	
	public List<ServiceInstance> findByVispServiceInstanceIdIsNull();
	
	public List<ServiceInstance> findByVerizonEntityAndOfferInstance(VerizonEntity vzEntity,OfferInstance offerInstance);
	
	@Query("select t from ServiceInstance t where t.offerInstance.offerInstanceId=:offerInstanceId and t.endTmstamp >= :date")
	public List<ServiceInstance> findByOfferInstanceAndEndTimeStamp(@Param("offerInstanceId")Long offerInstanceId,@Param("date")Date date);
	
	@Query("select t from ServiceInstance t where t.offerInstance.offerInstanceId=:offerInstanceId and sysdate <= t.endTmstamp")
	public List<ServiceInstance> findLatestByOfferInstance(@Param("offerInstanceId")Long offerInstanceId);
	
	@Query(value = "SELECT t FROM ServiceInstance t WHERE t.offerInstance.endTmstamp = TO_TIMESTAMP('12-31-9999 23:59:59', 'MM-DD-YYYY HH24:MI:SS')")
	public List<ServiceInstance> findByActiveOfferInstance();
	
	@Query("select t from ServiceInstance t where t.offerInstance.offerInstanceId=:offerInstanceId and t.service.serviceId=:serviceId order by t.serviceInstanceId desc")
	public List<ServiceInstance> findByOfferInstanceAndService(@Param("offerInstanceId")Long offerInstanceId,@Param("serviceId")Long serviceId);
}
