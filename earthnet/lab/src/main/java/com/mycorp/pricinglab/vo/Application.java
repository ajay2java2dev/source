package com.vzwcorp.pricinglab.vo;

import com.fasterxml.jackson.annotation.JsonView;
import com.vzwcorp.pricinglab.constants.PlabConstants;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/*--
  TABLE NAME            : REF_APPLICATION
  MAXIMUM ROWS IN TABLE : 500
  ROWS INSERTED DAILY   : N/A (Ref Table)
  PARTITION COLUMN(S)   : CREATE_DTM
  DATA RETENTION PERIOD : 7 YEARS
--*/

/**
 * Reference Entity refers to certain applications set for a given offer.
 * Offers which target certain social application like Facebook, Netflix etc.
 *
 * @author Pricing Lab
 * @since 1.0
 *
 */

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name = "REF_APPLICATION"
        , uniqueConstraints = @UniqueConstraint(columnNames = {"APPLICATION_NAME", "VISP_NAME"}))
public class Application {

    @Id
    @Column(name = "REF_APPLICATION_ID")
    @SequenceGenerator(name = "REF_APPLICATION_GEN", sequenceName = "REF_APPLICATION_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REF_APPLICATION_GEN")
    @JsonView(Views.InternalView.class)
    private Long applicationId;

    @Column(name = "VISP_NAME",nullable = false,length = 25)
    @JsonView({Views.CPIView.class,Views.InternalView.class})
    private String vispName;

    @Column(name = "APPLICATION_NAME",nullable = false,length = 25)
    @JsonView({Views.CPIView.class,Views.InternalView.class})
    private String name;

    @Column(name = "CREATE_DTM",nullable = false)
    @JsonView(Views.InternalView.class)
    private Date dateCreated = new Date();

    @Column(name = "CREATE_USR",nullable = false,length = 25)
    @JsonView(Views.InternalView.class)
    private String createdBy = PlabConstants.DEFAULT_CREATED_BY_USER;

    @JsonView(Views.InternalView.class)
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.REFRESH,CascadeType.MERGE}, mappedBy = "appsClassification")
    private List<Service> services;

    //constructor(s)
    public Application() {
    }

    public Application(String applicationName, String vispName) {
        this.name = applicationName;
        this.vispName = vispName;
    }

    //Getters and Setters

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public String getVispName() {
        return vispName;
    }

    public void setVispName(String vispName) {
        this.vispName = vispName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
