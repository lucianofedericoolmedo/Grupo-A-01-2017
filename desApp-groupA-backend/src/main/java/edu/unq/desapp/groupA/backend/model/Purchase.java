package edu.unq.desapp.groupA.backend.model;

import java.util.List;

public class Purchase {

	// Instance Variables
	private List<ItemPurchase> items;

	private PaymentType payment;

	// Getters and Setters
	public List<ItemPurchase> getItems() {
		return items;
	}

	public void setItems(List<ItemPurchase> items) {
		this.items = items;
	}

	public PaymentType getPayment() {
		return payment;
	}

	public void setPayment(PaymentType payment) {
		this.payment = payment;
	}

}
