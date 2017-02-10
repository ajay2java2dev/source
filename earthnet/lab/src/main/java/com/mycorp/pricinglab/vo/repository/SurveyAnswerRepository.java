package com.vzwcorp.pricinglab.vo.repository;


import com.vzwcorp.pricinglab.vo.SurveyAnswer;
import com.vzwcorp.pricinglab.vo.SurveyQuestion;
import com.vzwcorp.pricinglab.vo.VerizonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by menonka on 7/4/2016.
 */
public interface SurveyAnswerRepository extends JpaRepository<SurveyAnswer, String> {
    public List<SurveyAnswer> findByVerizonEntity (@Param("verizonEntity")VerizonEntity verizonEntity);
    
    public List<SurveyAnswer> findByVerizonEntityAndSurveyQuestion (VerizonEntity verizonEntity,SurveyQuestion surveyQuestion);
}
