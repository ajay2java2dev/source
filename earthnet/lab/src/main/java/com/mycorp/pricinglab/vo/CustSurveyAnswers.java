package com.vzwcorp.pricinglab.vo;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CustSurveyAnswers{

    @Column(name = "ANSWERS", nullable = false,length = 2000)
    @JsonView({Views.InternalView.class,Views.MobileFirstView.class})
    private String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

