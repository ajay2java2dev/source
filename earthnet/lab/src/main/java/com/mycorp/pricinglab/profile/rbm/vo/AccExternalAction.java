package com.vzwcorp.pricinglab.profile.rbm.vo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Pricing Lab on 9/27/2016.
 * Table Columns as below :
 * <ol>
 *      <li>"ACCOUNT_NUM" VARCHAR2(20) NOT NULL ENABLE,</li>
 *      <li>"DOMAIN_ID" NUMBER(9,0) NOT NULL ENABLE,</li>
 *      <li>"MESSAGE_TYPE_ID" NUMBER(9,0) NOT NULL ENABLE,</li>
 *      <li>"MESSAGE_SUB_TYPE_ID" NUMBER(9,0) NOT NULL ENABLE,</li>
 *      <li>"EXTERNAL_ACTION_ID" NUMBER(9,0) NOT NULL ENABLE,</li>
 *      <li>"ENABLED_BOO" VARCHAR2(1) NOT NULL ENABLE</li>
 * </ol>
 */
@Table(name = "ACCEXTERNALACTION")
@Entity
@IdClass(AccExternalActionPK.class)
public class AccExternalAction implements Serializable{

    @Id
    @Column(name = "ACCOUNT_NUM")
    private String accountNum;

    @Id
    @Column(name = "DOMAIN_ID")
    private Long domainId;

    @Id
    @Column(name = "MESSAGE_TYPE_ID")
    private Long messageTypeId;

    @Id
    @Column(name = "MESSAGE_SUB_TYPE_ID")
    private Long messageSubTypeId;

    @Id
    @Column(name = "EXTERNAL_ACTION_ID")
    private Long externalAction;

    @Column(name = "ENABLED_BOO")
    private String enableBoo = "F";

    //Constructor(s)
    public AccExternalAction() {
    }

    public AccExternalAction(String accountNum, Long domainId, Long messageTypeId, Long messageSubTypeId, Long externalAction, String enableBoo) {
        this.accountNum = accountNum;
        this.domainId = domainId;
        this.messageTypeId = messageTypeId;
        this.messageSubTypeId = messageSubTypeId;
        this.externalAction = externalAction;
        this.enableBoo = enableBoo;
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

    public String getEnableBoo() {
        return enableBoo;
    }

    public void setEnableBoo(String enableBoo) {
        this.enableBoo = enableBoo;
    }
}
