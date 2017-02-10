package com.vzwcorp.pricinglab.vo;

import com.fasterxml.jackson.annotation.JsonView;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import com.vzwcorp.pricinglab.vo.key.RefQoSPK;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/*--
  TABLE NAME            : REF_QOS
  MAXIMUM ROWS IN TABLE : 500
  ROWS INSERTED DAILY   : N/A (REF DATA)
  PARTITION COLUMN(S)   : CREATE_DTM
  DATA RETENTION PERIOD : 7 YEARS
--*/

/**
 * Reference Entity refers to certain Quality of Service's offered for a given
 * offer. Certain offers can provide features like improving service Quality from
 * a fast service to a faster service or have the video quality upgraded from HD to UHD etc.
 *
 * @author Pricing Lab
 * @since 1.0
 */
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name = "REF_QOS", uniqueConstraints =
@UniqueConstraint(columnNames = {"QOS_NAME", "VISP_NAME"}))
public class RefQoS extends RefQoSPK {

    @Id
    @Column(name = "REF_QOS_ID")
    @SequenceGenerator(name = "REF_QOS_GEN", sequenceName = "REF_QOS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REF_QOS_GEN")
    @JsonView({Views.InternalView.class, Views.CPIView.class})
    private Long qosId;

    @Column(name = "QOS_NAME",length = 25)
    @JsonView({Views.InternalView.class, Views.CPIView.class})
    private String name;

    @Column(name = "VISP_NAME",length = 25)
    @JsonView({Views.InternalView.class, Views.CPIView.class})
    private String vispName;

    @Column(name = "QOS_TYPE",length = 25)
    @JsonView({Views.InternalView.class, Views.CPIView.class})
    private String type;

    @Column(name = "DEFAULT_COLOR_CODE_HEX",length = 25)
    @JsonView({Views.InternalView.class, Views.CPIView.class})
    private String defaultColorCodeHex;

    @Column(name = "QOS_SPEED",length = 100)
    @JsonView({Views.InternalView.class, Views.CPIView.class})
    private String speed;

    @Column(name = "CREATE_DTM",nullable = false)
    @JsonView({Views.InternalView.class, Views.CPIView.class})
    private Date createDate = new Date();

    @Column(name = "CREATE_USR",nullable = false)
    @JsonView({Views.InternalView.class, Views.CPIView.class})
    private String createdBy  = PlabConstants.DEFAULT_CREATED_BY_USER;

    //constructor(s)
    public RefQoS() {
    }

    public RefQoS(String name, String vispName, String type) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(PlabConstants.STD_DATE_FORMAT);
            this.name = name;
            this.vispName = vispName;
            this.type = type;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //Getters and Setters
    public Long getQosId() {
        return qosId;
    }

    public void setQosId(Long qosId) {
        this.qosId = qosId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVispName() {
        return vispName;
    }

    public void setVispName(String vispName) {
        this.vispName = vispName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefaultColorCodeHex() {
        return defaultColorCodeHex;
    }

    public void setDefaultColorCodeHex(String defaultColorCodeHex) {
        this.defaultColorCodeHex = defaultColorCodeHex;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }
}
