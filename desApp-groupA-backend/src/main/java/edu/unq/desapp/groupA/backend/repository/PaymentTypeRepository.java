package edu.unq.desapp.groupA.backend.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.unq.desapp.groupA.backend.model.PaymentType;


@Repository
@SuppressWarnings("unchecked")
public class PaymentTypeRepository extends HibernateGenericDAO<PaymentType> {

	private static final long serialVersionUID = 2452202520983780950L;

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

	public PaymentType findByName(String name) {
		String query = "SELECT type FROM " + this.persistentClass.getName() + " type WHERE type.name = " + name;
		List<PaymentType> payments = (List<PaymentType>) this.getHibernateTemplate().find(query);
		if (payments.isEmpty()) {
			return null;
		} else {
			return payments.get(0);
		}
	}

	@Override
	public Class<PaymentType> getDomainClass() {
		return PaymentType.class;
	}

}
