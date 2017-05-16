package edu.unq.desapp.groupA.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unq.desapp.groupA.backend.model.PaymentType;
import edu.unq.desapp.groupA.backend.repository.GenericRepository;
import edu.unq.desapp.groupA.backend.repository.PaymentTypeRepository;


@Service
public class PaymentTypeService extends GenericService<PaymentType> {

	@Autowired
	PaymentTypeRepository repository;

	public PaymentTypeService() { }

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

	@Override
	public GenericRepository<PaymentType> getRepository() {
		return repository;
	}
	
}
