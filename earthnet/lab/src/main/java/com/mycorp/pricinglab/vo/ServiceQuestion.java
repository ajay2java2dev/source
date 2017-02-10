package com.vzwcorp.pricinglab.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@DiscriminatorValue("SERVICE")
public class ServiceQuestion extends SelectionPage{

	
	@JsonView(Views.InternalView.class)
	@ManyToOne
	@JoinColumn(name = "SERVICE_ID")
	public Service service;

	@Column(name = "DISCRIMINATOR_DESC",length = 50)
	@JsonView(Views.InternalView.class)
	private String name;

	@JsonView(Views.InternalView.class)
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "OFFER_ID")
	public Offer  offer;
	
	@JsonView(Views.InternalView.class)
	@OneToMany(mappedBy = "serviceQuestion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ServiceAnswer> serviceAnswers;	

	@JsonView({Views.MobileFirstView.class,Views.CPIView.class})
	@OneToMany(mappedBy = "serviceQuestion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Choice> choice;
	
	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public List<ServiceAnswer> getServiceAnswers() {
		return serviceAnswers;
	}

	public void setServiceAnswers(List<ServiceAnswer> serviceAnswers) {
		this.serviceAnswers = serviceAnswers;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public List<Choice> getChoice() {
		return choice;
	}

	public void setChoice(List<Choice> choice) {
		this.choice = choice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
		
}
