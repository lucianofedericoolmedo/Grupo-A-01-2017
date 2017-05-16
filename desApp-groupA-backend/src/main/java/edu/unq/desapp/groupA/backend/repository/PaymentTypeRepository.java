package edu.unq.desapp.groupA.backend.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.unq.desapp.groupA.backend.model.PaymentType;


@Repository
public class PaymentTypeRepository extends HibernateGenericDAO<PaymentType> {

	private List<PaymentType> paymentTypes;
	
	public PaymentTypeRepository(){
		this.paymentTypes = new ArrayList<PaymentType>();
	}

	public List<PaymentType> getPaymentTypes() {
		return paymentTypes;
	}

	public void setPaymentTypes(List<PaymentType> paymentTypes) {
		this.paymentTypes = paymentTypes;
	}
	
	@Override
	protected Class<PaymentType> getDomainClass() {
		return PaymentType.class;
	}
}
