package com.vzwcorp.pricinglab.vo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vzwcorp.pricinglab.vo.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback,Long> {
	
	@Query("select f from Feedback f where f.custom_var.loggedInMdn in ( select vze.mdn from VerizonEntity vze inner join vze.offerInstances oi where oi.offer.offerId = :offerId)")
	public List<Feedback> findByOffer(@Param("offerId") Long offerId);
	
	/**
	 * This method returns List of Object[]. Object[0] - Feedback, Object[1] - OfferName
	 * @return
	 */
	@Query("select distinct f, oi.offer.name from Feedback f, VerizonEntity vze inner join vze.offerInstances oi where f.custom_var.loggedInMdn = vze.mdn")
	public List<Object> findForAllOffers();	

}
