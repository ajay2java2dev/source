package com.vzwcorp.pricinglab.vo;

import com.fasterxml.jackson.annotation.JsonView;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "RATING_GROUP")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RatingGroup {

    @Id
    @Column(name = "RATING_GROUP_ID")
    @SequenceGenerator(name = "RATING_GROUP_GEN", sequenceName = "RATING_GROUP_SEQ", initialValue = 10000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RATING_GROUP_GEN")
    @JsonView({Views.CPIView.class, Views.VispView.class})
    public Long ratingGroupId;

    @Column(name = "CREATE_DTM",nullable = false)
    @JsonView({Views.InternalView.class, Views.CPIView.class})
    private Date createDate = new Date();

    @Column(name = "CREATE_USR",nullable = false,length = 25)
    @JsonView({Views.InternalView.class, Views.CPIView.class})
    private String createdBy = PlabConstants.DEFAULT_CREATED_BY_CPI;

    public Long getRatingGroupId() {
        return ratingGroupId;
    }

    public void setRatingGroupId(Long ratingGroupId) {
        this.ratingGroupId = ratingGroupId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

}
