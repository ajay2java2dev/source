package com.vzwcorp.pricinglab.rest.controller;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vzwcorp.pricinglab.vo.VerizonEntity;
import com.vzwcorp.pricinglab.vo.repository.ServiceInstanceRepository;
import com.vzwcorp.pricinglab.vo.repository.ServiceRepository;
import com.vzwcorp.pricinglab.vo.repository.VerizonEntityRepository;

@RestController
@Transactional("blTransactionManager")
public class VerizonEntityController {

	static Logger logger = LogManager.getLogger(VerizonEntityController.class);

	@Autowired
	VerizonEntityRepository repository;

	@Autowired
	ServiceRepository serviceRepository;

	@Autowired
	ServiceInstanceRepository serviceInstanceRepository;

	ObjectMapper mapper = new ObjectMapper();

	// Get an entity
	@RequestMapping("/entity")
	public VerizonEntity getEntity(@RequestParam(value = "id", defaultValue = "0") String entityId) {

		logger.debug("VerizonEntityController getEntity entityId " + entityId);

		VerizonEntity entity = repository.findByVerizonEntityId(new Long(entityId));

		return entity;
	}

	// List all entities
	@RequestMapping("/entities")
	public List<VerizonEntity> getEntities() {

		logger.debug("VerizonEntityController getEntities ");
		List<VerizonEntity> entities = repository.findAll();
		return entities;
	}

	/*
	 * // Create a Entity
	 * 
	 * @RequestMapping(value="/entity", method=RequestMethod.POST) public
	 * VerizonEntity addEntity(
	 * 
	 * @RequestParam(value = "customer", defaultValue ="0") String customer,
	 * 
	 * @RequestParam(value = "account", defaultValue ="0") String account,
	 * 
	 * @RequestParam(value = "mdn", defaultValue ="0") String mdn) {
	 * 
	 * VerizonEntity entity= new VerizonEntity(); entity.setCustomer(customer);
	 * entity.setAccount(account); entity.setMdn(mdn);
	 * repository.saveAndFlush(entity); return entity; }
	 */
	@RequestMapping(value = "/entity", method = RequestMethod.POST)
	public VerizonEntity addEntity(@RequestParam(value = "entityObject", defaultValue = "0") String jsonEntity) {

		logger.debug("VerizonEntityController addEntity ");

		VerizonEntity entity;
		try {
			entity = mapper.readValue(jsonEntity, VerizonEntity.class);

			entity.setDateCreated(new Date());
			entity = repository.saveAndFlush(entity);
			return entity;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	// Delete an action
	@RequestMapping(value = "/entity", method = RequestMethod.DELETE)
	public VerizonEntity removeEntity(@RequestParam(value = "entityid", defaultValue = "0") String entityId) {

		VerizonEntity entity = repository.findOne(Long.parseLong(entityId));
		if (entity != null) {
			repository.delete(entity);
		}
		return entity;
	}

	/*
	 * // Associate a service to the entity
	 * 
	 * @RequestMapping(value="/entity/service", method=RequestMethod.PUT) public
	 * VerizonEntity addProductToEntity(
	 * 
	 * @RequestParam(value = "productid", defaultValue ="0") String productId,
	 * 
	 * @RequestParam(value = "entityid", defaultValue ="0") String entityId) {
	 * >>>>>>> .r72
	 * 
	 * // Delete an action
	 * 
	 * @RequestMapping(value = "/entity", method = RequestMethod.DELETE) public
	 * VerizonEntity removeEntity(@RequestParam(value = "entityid", defaultValue
	 * = "0") String entityId) {
	 * 
	 * // VerizonEntity entity = repository.findOne(entityId);
	 * 
	 * logger.debug("VerizonEntityController removeEntity entityId "+entityId);
	 * 
	 * VerizonEntity entity = repository.findByVerizonEntityId(new
	 * Long(entityId));
	 * 
	 * if (entity != null) { repository.delete(entity); } return entity; }
	 * 
	 * /* // Associate a service to the entity
	 * 
	 * @RequestMapping(value="/entity/service", method=RequestMethod.PUT) public
	 * VerizonEntity addProductToEntity(
	 * 
	 * @RequestParam(value = "productid", defaultValue ="0") String productId,
	 * 
	 * @RequestParam(value = "entityid", defaultValue ="0") String entityId) {
	 * 
	 * VerizonEntity entity=repository.findOne(entityId); Service service =
	 * serviceRepository.findOne(productId); ServiceInstance serviceInstance =
	 * new ServiceInstance( service, entity);
	 * //entity.addServiceInstance(serviceInstance);
	 * serviceInstanceRepository.save(serviceInstance);
	 * repository.saveAndFlush(entity);
	 * 
	 * // send init event to service instance // Notify VISION/ SCM/ VISP
	 * 
	 * 
	 * // return entity; }
	 */

	// Associate a service to the entity
	/*
	 * @RequestMapping(value="/entity/service", method=RequestMethod.DELETE)
	 * public VerizonEntity removeProductToEntity(
	 * 
	 * @RequestParam(value = "productid", defaultValue ="0") String productId,
	 * 
	 * @RequestParam(value = "entityid", defaultValue ="0") String entityId) {
	 * 
	 * VerizonEntity entity=repository.findOne(entityId); Service service =
	 * serviceRepository.findOne(productId); ServiceInstance serviceInstance =
	 * new ServiceInstance( service, entity);
	 * //entity.removeServiceInstance(serviceInstance);
	 * service.removeServiceInstance(serviceInstance);
	 * serviceInstanceRepository.save(serviceInstance); repository.save(entity);
	 * 
	 * // send stop event to productinstance // Notify VISP and maybe VISION/
	 * SCM
	 * 
	 * 
	 * // return entity; }
	 */

}
