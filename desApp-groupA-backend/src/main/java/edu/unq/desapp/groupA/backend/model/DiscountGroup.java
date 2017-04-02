package edu.unq.desapp.groupA.backend.model;

import java.util.List;

public class DiscountGroup {
	
	// Instance Variables
	private List<Discount> discountsToApply;

	// Getters and Setters
	public List<Discount> getDiscountsToApply() {
		return discountsToApply;
	}

	public void setDiscountsToApply(List<Discount> discountsToApply) {
		this.discountsToApply = discountsToApply;
	}

}
