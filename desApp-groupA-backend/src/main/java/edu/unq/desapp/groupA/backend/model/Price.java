package edu.unq.desapp.groupA.backend.model;

import org.joda.time.DateTime;

public class Price extends Entity {

	// Instance Variables
	private Double price;
	
	private Product product;
	
	private DateTime startingValidityDate;
	
	private DateTime finishingValidityDate;
	
	// Constructors
	public Price() {
		
	}

	public Price(Product product, Double price) {
		this.product = product;
		this.price = price;
		this.startingValidityDate = DateTime.now();
	}

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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	// Logic
	public Double priceForQuantity(Integer quantity) {
		return price * quantity;
	}

}
