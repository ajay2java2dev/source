package com.vzwcorp.pricinglab.vo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.vzwcorp.pricinglab.vo.TimeFrame;

public interface TimeFrameRepository extends JpaRepository<TimeFrame, Long> {

	public TimeFrame findByTimeFrameId(@Param("timeFrameId") Long timeFrameId);
}
