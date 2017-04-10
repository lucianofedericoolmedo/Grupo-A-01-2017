package edu.unq.desapp.groupA.backend.model;

public class ItemCart extends Item {

	// Instance Variables
	private Discount discount;

	// Getters and Setters
	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	// Logic
	public Boolean hasAppliedDiscount() {
		return discount != null;
	}
	
}
