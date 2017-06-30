package edu.unq.desapp.groupA.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	public PaymentType create(PaymentType paymentType){
		PaymentType foundPayment = repository.findByName(paymentType.name());
		if (foundPayment != null) {
			throw new RuntimeException("A payment type with the given name has already been created");
		}
		return super.save(paymentType);
	}
	
	@Override
	public GenericRepository<PaymentType> getRepository() {
		return repository;
	}

}
