package com.vzwcorp.pricinglab.vo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vzwcorp.pricinglab.vo.Offer;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {

	public Offer findByOfferId(@Param("offerId") Long offerId);

	@Query("select t from Offer t where t.state like :state")
	public List<Offer> findByState(@Param("state") String state);

}
