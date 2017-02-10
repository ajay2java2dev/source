package com.vzwcorp.pricinglab.vo.repository;


import com.vzwcorp.pricinglab.vo.RatingGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by menonka on 6/23/2016.
 */
public interface RatingGroupRepository extends JpaRepository<RatingGroup, Long> {
    @Query(value = "insert into RATINGGROUP values (:ratingGroupId)", nativeQuery = true)
    public RatingGroup saveRatingGroupByID (@Param("ratingGroupId") Long ratingGroupId);
}
