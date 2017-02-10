package com.vzwcorp.pricinglab.profile.rbm.vo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by PricingLab on 9/27/2016.
 * The composite key is based on PRIMARY KEY ("ACCOUNT_NUM", "DOMAIN_ID", "MESSAGE_TYPE_ID", "MESSAGE_SUB_TYPE_ID", "EXTERNAL_ACTION_ID")
 */
@Embeddable
public class AccExternalActionPK implements Serializable{
    private static final long serialVersionUID = 1L;

    @Column(name = "ACCOUNT_NUM")
    private String accountNum;

    @Column(name = "DOMAIN_ID")
    private Long domainId;

    @Column(name = "MESSAGE_TYPE_ID")
    private Long messageTypeId;

    @Column(name = "MESSAGE_SUB_TYPE_ID")
    private Long messageSubTypeId;

    @Column(name = "EXTERNAL_ACTION_ID")
    private Long externalAction;

    //Constructor(s)
    public AccExternalActionPK() {
    }

    public AccExternalActionPK(String accountNum, Long domainId, Long messageTypeId, Long messageSubTypeId, Long externalAction) {
        this.accountNum = accountNum;
        this.domainId = domainId;
        this.messageTypeId = messageTypeId;
        this.messageSubTypeId = messageSubTypeId;
        this.externalAction = externalAction;
    }

    //Getters and Setters
    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public Long getDomainId() {
        return domainId;
    }

    public void setDomainId(Long domainId) {
        this.domainId = domainId;
    }

    public Long getMessageTypeId() {
        return messageTypeId;
    }

    public void setMessageTypeId(Long messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    public Long getMessageSubTypeId() {
        return messageSubTypeId;
    }

    public void setMessageSubTypeId(Long messageSubTypeId) {
        this.messageSubTypeId = messageSubTypeId;
    }

    public Long getExternalAction() {
        return externalAction;
    }

    public void setExternalAction(Long externalAction) {
        this.externalAction = externalAction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof AccExternalActionPK)) return false;

        AccExternalActionPK that = (AccExternalActionPK) o;

        return new EqualsBuilder()
                .append(getAccountNum(), that.getAccountNum())
                .append(getDomainId(), that.getDomainId())
                .append(getMessageTypeId(), that.getMessageTypeId())
                .append(getMessageSubTypeId(), that.getMessageSubTypeId())
                .append(getExternalAction(), that.getExternalAction())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getAccountNum())
                .append(getDomainId())
                .append(getMessageTypeId())
                .append(getMessageSubTypeId())
                .append(getExternalAction())
                .toHashCode();
    }
}
