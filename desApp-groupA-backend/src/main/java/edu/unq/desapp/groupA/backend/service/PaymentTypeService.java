package edu.unq.desapp.groupA.backend.service;

import edu.unq.desapp.groupA.backend.model.PaymentType;
import edu.unq.desapp.groupA.backend.repository.PaymentTypeRepository;

public class PaymentTypeService {

	PaymentTypeRepository repository;
	
		
	public PaymentTypeService(PaymentTypeRepository repository) {
		this.repository = repository;
	}


	public PaymentType create(String name, String description){
		PaymentType paymentType = new PaymentType();
		paymentType.setDescription(description);
		paymentType.setName(name);
		repository.save(paymentType);
		return paymentType;
	}
	
	
	
}
