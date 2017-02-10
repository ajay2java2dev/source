package com.vzwcorp.pricinglab.vo.repository;

import com.vzwcorp.pricinglab.vo.ChoiceInstance;
import com.vzwcorp.pricinglab.vo.OfferInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ChoiceInstanceRepository  extends JpaRepository<ChoiceInstance, Long> {
    @Query(name = "findByChoice",value = "select t from ChoiceInstance t where t.choice.id = :choiceId and sysdate <= t.endTmstamp")
    public ChoiceInstance findByChoice(@Param("choiceId") Long choiceId);

    @Query(name = "findByChoiceAndOfferInstance",value = "select t from ChoiceInstance t where t.choice.id = :choiceId and t.offerInstance.offerInstanceId = :offerInstanceId and sysdate <= t.endTmstamp")
    public List<ChoiceInstance> findByChoiceAndOfferInstance (@Param("choiceId") Long choiceId, @Param("offerInstanceId") Long offerInstanceId);

    @Query(name = "findByOfferInstance",value = "select t from ChoiceInstance t where t.offerInstance.offerInstanceId = :offerInstanceId  and sysdate <= t.endTmstamp")
    public List<ChoiceInstance> findByOfferInstance (@Param("offerInstanceId") Long offerInstanceId);
    
    @Query("select t from ChoiceInstance t where t.offerInstance.offerInstanceId = :offerInstanceId and t.endTmstamp >= :date")
    public List<ChoiceInstance> findByOfferInstanceAndEndTimestamp(@Param("offerInstanceId") Long offerInstanceId,@Param("date")Date date);


}
