package edu.unq.desapp.groupA.backend.model;

import org.joda.time.DateTime;

public class Price {

	// Instance Variables
	private Double price;
	
	private DateTime startingValidityDate;
	
	private DateTime finishingValidityDate;
	
	// Getters and Setters
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}

	public DateTime getStartingValidityDate() {
		return startingValidityDate;
	}

	public void setStartingValidityDate(DateTime startingValidityDate) {
		this.startingValidityDate = startingValidityDate;
	}

	public DateTime getFinishingValidityDate() {
		return finishingValidityDate;
	}

	public void setFinishingValidityDate(DateTime finishingValidityDate) {
		this.finishingValidityDate = finishingValidityDate;
	}

	// Logic
	public Double priceForQuantity(Integer quantity) {
		return price * quantity;
	}

}
