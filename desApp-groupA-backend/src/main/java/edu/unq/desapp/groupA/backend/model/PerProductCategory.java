package edu.unq.desapp.groupA.backend.model;

public class PerProductCategory extends Discount {

	// Instance Variables
	private ProductCategory categoryForDiscount;

	// Getters and Setters
	public ProductCategory getCategoryForDiscount() {
		return categoryForDiscount;
	}
	
	public void setCategoryForDiscount(ProductCategory categoryForDiscount) {
		this.categoryForDiscount = categoryForDiscount;
	}

}
