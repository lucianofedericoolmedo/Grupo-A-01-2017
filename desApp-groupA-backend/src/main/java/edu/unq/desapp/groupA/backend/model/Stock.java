package edu.unq.desapp.groupA.backend.model;

public class Stock {

	// Instance Variables
	private Product product;
	
	private Integer quantity;

	// Getters and Setters
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}