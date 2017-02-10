package com.vzwcorp.pricinglab.vo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vzwcorp.pricinglab.vo.OfferInstance;

public interface OfferInstanceRepository extends JpaRepository<OfferInstance, Long> {

	@Query(name = "findOfferInstanceByOfferAndVerizonEntity", value = "SELECT t from OfferInstance t WHERE t.offer.offerId = :offerId AND t.verizonEntity.verizonEntityId = :verizonEntityId and sysdate <= t.endTmstamp")
	List<OfferInstance> findByOfferAndVerizonEntity( @Param(value = "offerId") Long offerId, @Param(value = "verizonEntityId")Long verizonEntityId);

	@Query(name = "findOfferInstancesByCustomerAccount", value = "SELECT t from OfferInstance t WHERE t.offer.offerId = :offerId AND t.verizonEntity.custIdNo = :custId AND t.verizonEntity.acctNo = :acctNo and sysdate <= t.endTmstamp")
	List<OfferInstance> findOfferInstancesByCustomerAccount(@Param(value = "offerId") Long offerId, @Param(value = "custId") Long custId,@Param(value = "acctNo") Long acctNo);
	
	@Query(name = "findOfferInstancesByCustomer", value = "SELECT t from OfferInstance t WHERE t.verizonEntity.custIdNo = :custId AND t.verizonEntity.acctNo = :acctNo and sysdate <= t.endTmstamp")
	List<OfferInstance> findOfferInstancesByCustomer(@Param(value = "custId") Long custId,@Param(value = "acctNo") Long acctNo);
	
	//List<OfferInstance> findByOfferAndVerizonEntityAndEndTmstampIsNull( Offer offer, VerizonEntity vzEntity);
	
	@Query(name = "findOfferInstanceByOfferAndVerizonEntityAndEndTmstamp", value = "SELECT t from OfferInstance t WHERE t.offer.offerId = :offerId AND t.verizonEntity.verizonEntityId = :verizonEntityId and t.endTmstamp >=:billCycleStartDate")
	List<OfferInstance> findByOfferAndVerizonEntityAndEndTmstamp( @Param(value = "offerId") Long offerId, @Param(value = "verizonEntityId")Long verizonEntityId,@Param(value="billCycleStartDate")Date billCycleStartDate);
	
	@Query(value = "SELECT o FROM OfferInstance o WHERE endTmstamp IS NULL AND termsAndConditionsStatus = 1 AND verizonEntity.verizonEntityId = :vzEntity")
	List<OfferInstance> findByVerizonEntityAndDateTerminatedIsNull(@Param("vzEntity")Long vzEntity);

	@Query(name = "findByOfferInstance", value = "SELECT count (t) from OfferInstance t WHERE t.offer.offerId = :offerId AND sysdate <= t.endTmstamp")
	Integer findTotalInstancesForOffer( @Param(value = "offerId") Long offerId);

	@Query(name = "findLatestOfferInstanceByVerizonEntity", value = "select t from OfferInstance t WHERE t.verizonEntity.verizonEntityId = :verizonEntityId and sysdate <= t.endTmstamp order by t.offerInstanceId desc")
	List<OfferInstance> findLatestOfferInstanceByVerizonEntity(@Param(value = "verizonEntityId") Long verizonEntityId);
	
	@Query(name = "findLatestOfferInstanceByMdn", value = "select t from OfferInstance t WHERE t.verizonEntity.mdn = :mdn and sysdate <= t.endTmstamp order by t.offerInstanceId desc")
	List<OfferInstance> findLatestOfferInstanceByMdn(@Param(value = "mdn") String mdn);
	
	@Query(value = "SELECT t FROM OfferInstance t WHERE t.offer.offerId = :offerId")
	List<OfferInstance> findByOffer(@Param(value = "offerId")Long offerId);
	
	@Query(value = "SELECT t FROM OfferInstance t WHERE t.verizonEntity.mdn = :mdn AND termsAndConditionsStatus = 1")
	List<OfferInstance> findByMdn(@Param(value = "mdn") String mdn);
	
	@Query(value = "SELECT oi.offerInstanceId as offerInstanceId, ofr.offerId as offerId, ofr.name as offerName, vze.custIdNo as custIdNo, vze.acctNo as acctNo, vze.mdn as mdn, oi.dateCreated as dateCreated, oi.endTmstamp as endTmstamp FROM OfferInstance oi inner join oi.verizonEntity vze inner join oi.offer ofr order by oi.endTmstamp desc, oi.dateCreated desc ")
	Page<Object> findAllForAdmin(Pageable pageable);
	
	@Query(value = "SELECT oi.offerInstanceId as offerInstanceId, ofr.offerId as offerId, ofr.name as offerName, vze.custIdNo as custIdNo, vze.acctNo as acctNo, vze.mdn as mdn, oi.dateCreated as dateCreated, oi.endTmstamp as endTmstamp FROM OfferInstance oi inner join oi.verizonEntity vze inner join oi.offer ofr WHERE ofr.offerId = :offerId order by oi.endTmstamp desc, oi.dateCreated desc ")
	Page<Object> findByOfferForAdmin(@Param(value = "offerId")Long offerId, Pageable pageable);
	
	@Query(value = "SELECT oi.offerInstanceId as offerInstanceId, ofr.offerId as offerId, ofr.name as offerName, vze.custIdNo as custIdNo, vze.acctNo as acctNo, vze.mdn as mdn, oi.dateCreated as dateCreated, oi.endTmstamp as endTmstamp FROM OfferInstance oi inner join oi.verizonEntity vze inner join oi.offer ofr WHERE vze.mdn = :mdn order by oi.endTmstamp desc, oi.dateCreated desc ")
	Page<Object> findAllByMdnForAdmin(@Param(value = "mdn")String mdn, Pageable pageable);
	
	@Query(value = "SELECT oi.offerInstanceId as offerInstanceId, ofr.offerId as offerId, ofr.name as offerName, vze.custIdNo as custIdNo, vze.acctNo as acctNo, vze.mdn as mdn, oi.dateCreated as dateCreated, oi.endTmstamp as endTmstamp FROM OfferInstance oi inner join oi.verizonEntity vze inner join oi.offer ofr WHERE ofr.offerId = :offerId and vze.mdn = :mdn order by oi.endTmstamp desc, oi.dateCreated desc ")
	Page<Object> findByOfferAndMdnForAdmin(@Param(value = "offerId")Long offerId, @Param(value = "mdn")String mdn, Pageable pageable);
}
