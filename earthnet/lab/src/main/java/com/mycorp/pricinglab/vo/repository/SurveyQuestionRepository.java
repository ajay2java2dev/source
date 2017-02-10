package com.vzwcorp.pricinglab.vo.repository;

import com.vzwcorp.pricinglab.vo.Survey;
import com.vzwcorp.pricinglab.vo.SurveyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by menonka on 7/4/2016.
 */
public interface SurveyQuestionRepository extends JpaRepository<SurveyQuestion, Long> {
    public List<SurveyQuestion> findBySurvey(@Param(value = "survey")Survey survey);

    public List<SurveyQuestion> findByName(@Param(value = "name")String name);
}
