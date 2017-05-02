package edu.unq.desapp.groupA.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unq.desapp.groupA.backend.model.PaymentType;
import edu.unq.desapp.groupA.backend.repository.PaymentTypeRepository;


@Service
public class PaymentTypeService {

	@Autowired
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
