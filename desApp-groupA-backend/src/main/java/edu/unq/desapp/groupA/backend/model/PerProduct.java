package edu.unq.desapp.groupA.backend.model;

public class PerProduct extends Discount {

	// Instance Variables
	private Product productForDiscount;

	// Getters and Setters
	public Product getProductForDiscount() {
		return productForDiscount;
	}

	public void setProductForDiscount(Product productForDiscount) {
		this.productForDiscount = productForDiscount;
	}

}
