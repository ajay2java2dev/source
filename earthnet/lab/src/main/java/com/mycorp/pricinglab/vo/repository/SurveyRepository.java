package com.vzwcorp.pricinglab.vo.repository;

import com.vzwcorp.pricinglab.vo.Survey;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by menonka on 7/4/2016.
 */
public interface SurveyRepository extends JpaRepository<Survey, Long> {

	@Query(value = "SELECT s FROM Survey s WHERE s.offer.offerId = :offerId")
	List<Survey> findByOffer(@Param(value = "offerId") Long offerId);

	@Query(value = "SELECT s FROM Survey s WHERE s.offer.offerId = :offerId")
	List<String> findSurveyNamesByOffer(@Param(value = "offerId") Long offerId);

	List<Survey> findBySurveyname(String name);

}
