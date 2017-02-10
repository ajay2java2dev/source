package com.vzwcorp.pricinglab.profile.rbm.vo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Pricinglab on 10/4/2016.
 * Key Columns : {MESSAGE_TYPE_ID,MESSAGE_SUB_TYPE_ID,EXTERNAL_ACTION_ID,ACTION_ID,ENABLED_BOO,OFFSET_DAYS,ACCOUNT_SPECIFIC_BOO}
 */
@Entity
@Table(name="EXTERNALACTIONMAPPING")
@IdClass(ExternalActionMappingPK.class)
public class ExternalActionMapping implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="MESSAGE_TYPE_ID")
    private String messageTypeId;

    @Id
    @Column(name="MESSAGE_SUB_TYPE_ID")
    private String messageSubTypeId;

    @Id
    @Column(name="EXTERNAL_ACTION_ID")
    private String externalActionId;

    @Column(name="ACTION_ID")
    private String actionId;

    @Column(name="ENABLED_BOO")
    private String enabledBoo;

    @Column(name="OFFSET_DAYS")
    private Long offsetDays;

    @Column(name="ACCOUNT_SPECIFIC_BOO")
    private String accountSpecificBoo;

    public String getMessageTypeId() {
        return messageTypeId;
    }

    public void setMessageTypeId(String messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    public String getMessageSubTypeId() {
        return messageSubTypeId;
    }

    public void setMessageSubTypeId(String messageSubTypeId) {
        this.messageSubTypeId = messageSubTypeId;
    }

    public String getExternalActionId() {
        return externalActionId;
    }

    public void setExternalActionId(String externalActionId) {
        this.externalActionId = externalActionId;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getEnabledBoo() {
        return enabledBoo;
    }

    public void setEnabledBoo(String enabledBoo) {
        this.enabledBoo = enabledBoo;
    }

    public Long getOffsetDays() {
        return offsetDays;
    }

    public void setOffsetDays(Long offsetDays) {
        this.offsetDays = offsetDays;
    }

    public String getAccountSpecificBoo() {
        return accountSpecificBoo;
    }

    public void setAccountSpecificBoo(String accountSpecificBoo) {
        this.accountSpecificBoo = accountSpecificBoo;
    }
}
