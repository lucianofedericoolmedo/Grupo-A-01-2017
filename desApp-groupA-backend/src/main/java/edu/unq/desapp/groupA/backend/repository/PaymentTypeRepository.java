package edu.unq.desapp.groupA.backend.repository;

import java.util.ArrayList;
import java.util.List;

import edu.unq.desapp.groupA.backend.model.PaymentType;

public class PaymentTypeRepository {

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
	
	public void save(PaymentType p){
		this.paymentTypes.add(p);
	}
}
