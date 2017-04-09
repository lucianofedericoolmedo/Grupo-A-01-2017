package edu.unq.desapp.groupA.backend.service;

import java.util.ArrayList;
import java.util.List;

import edu.unq.desapp.groupA.backend.model.PaymentType;

public class PaymentTypeService {

	private List<PaymentType> paymentTypes;

	public List<PaymentType> getPaymentTypes() {
		return paymentTypes;
	}

	public void setPaymentTypes(List<PaymentType> paymentTypes) {
		this.paymentTypes = paymentTypes;
	}
	
	public PaymentTypeService(){
		this.paymentTypes = new ArrayList<PaymentType>();
	}
	
	public PaymentType create(String name, String description){
		PaymentType paymentType = new PaymentType();
		paymentType.setDescription(description);
		paymentType.setName(name);
		paymentTypes.add(paymentType);
		return paymentType;
	}
	
	
	
}
