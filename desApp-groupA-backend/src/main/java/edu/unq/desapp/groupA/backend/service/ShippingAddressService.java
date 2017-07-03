package edu.unq.desapp.groupA.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unq.desapp.groupA.backend.model.ShippingAddress;
import edu.unq.desapp.groupA.backend.repository.ShippingAddressRepository;


@Transactional
@Service
public class ShippingAddressService extends GenericService<ShippingAddress> {

	@Autowired
	private ShippingAddressRepository repository;
	
	public ShippingAddressService() {}
	
	public ShippingAddressService(ShippingAddressRepository repo) {
		this.setRepository(repo);
	}

	public ShippingAddressRepository getRepository() {
		return repository;
	}

	public void setRepository(ShippingAddressRepository repository) {
		this.repository = repository;
	}

	/*
	public ShippingAddress findByName(String shipping) {
		return this.getRepository().findByName(shipping);
	}
	*/
	
	
	@Transactional
	public ShippingAddress update(ShippingAddress sAddress) {
		return super.update(sAddress);
	}
	
	@Transactional
	public ShippingAddress save(ShippingAddress sAddress) {
		return super.save(sAddress);
	}

}