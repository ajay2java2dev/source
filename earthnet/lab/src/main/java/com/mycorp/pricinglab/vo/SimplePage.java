package com.vzwcorp.pricinglab.vo;

import com.fasterxml.jackson.annotation.JsonView;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "SIMPLE_PAGE")
public class SimplePage {

    @Id
    @Column(name = "SIMPLE_PAGE_ID")
    @SequenceGenerator(name = "SIMPLE_PAGE_GEN", sequenceName = "SIMPLE_PAGE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIMPLE_PAGE_GEN")
    private Long simplePageId;

    @Column(name = "PAGE_TYPE",length = 20)
    String type;

    @Column(name = "PAGE_HEADING",length = 4000)
    String heading;

    @Column(name = "PAGE_HEADING_IMG_URL")
    String headingImgUrl;

    @Column(name = "PAGE_SUBHEADING",length = 4000)
    String subHeading;

    @Column(name = "PAGE_SUBHEADING_IMG_URL")
    String subHeadingImgUrl;

    @Column(name = "PAGE_BODY",length = 4000)
    String body;

    @Column(name = "PAGE_BODY_IMG_URL")
    String bodyImgUrl;

    @Column(name = "PAGE_FOOTER",length = 4000)
    String footer;

    @Column(name = "PAGE_FOOTER_IMG_URL")
    String footerImgUrl;

    @JsonView(Views.InternalView.class)
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "OFFER_ID")
    public Offer offer;

    @Column(name = "CREATE_DTM",nullable = false)
    @JsonView(Views.InternalView.class)
    private Date dateCreated = new Date();

    @Column(name = "CREATE_USR",nullable = false,length = 25)
    @JsonView(Views.InternalView.class)
    private String createdBy = PlabConstants.DEFAULT_CREATED_BY_USER;

    //Getters and Setters

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getHeadingImgUrl() {
        return headingImgUrl;
    }

    public void setHeadingImgUrl(String headingImgUrl) {
        this.headingImgUrl = headingImgUrl;
    }

    public String getSubHeading() {
        return subHeading;
    }

    public void setSubHeading(String subHeading) {
        this.subHeading = subHeading;
    }

    public String getSubHeadingImgUrl() {
        return subHeadingImgUrl;
    }

    public void setSubHeadingImgUrl(String subHeadingImgUrl) {
        this.subHeadingImgUrl = subHeadingImgUrl;
    }

    public Long getSimplePageId() {
        return simplePageId;
    }

    public void setSimplePageId(Long simplePageId) {
        this.simplePageId = simplePageId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBodyImgUrl() {
        return bodyImgUrl;
    }

    public void setBodyImgUrl(String bodyImgUrl) {
        this.bodyImgUrl = bodyImgUrl;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public String getFooterImgUrl() {
        return footerImgUrl;
    }

    public void setFooterImgUrl(String footerImgUrl) {
        this.footerImgUrl = footerImgUrl;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

}
