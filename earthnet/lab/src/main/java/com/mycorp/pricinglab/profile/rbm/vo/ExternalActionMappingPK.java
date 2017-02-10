package com.vzwcorp.pricinglab.profile.rbm.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by PricingLab on 10/4/2016.
 * Composite Key as per the table : {"MESSAGE_TYPE_ID", "MESSAGE_SUB_TYPE_ID", "EXTERNAL_ACTION_ID"}
 */
@Embeddable
public class ExternalActionMappingPK implements Serializable{
    private static final long serialVersionUID = 1L;

    @Column(name="MESSAGE_TYPE_ID")
    private String messageTypeId;

    @Column(name="MESSAGE_SUB_TYPE_ID")
    private String messageSubTypeId;

    @Column(name="EXTERNAL_ACTION_ID")
    private String externalActionId;

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
}
