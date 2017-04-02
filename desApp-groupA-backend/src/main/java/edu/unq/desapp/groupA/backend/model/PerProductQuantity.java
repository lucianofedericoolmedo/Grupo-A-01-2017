package edu.unq.desapp.groupA.backend.model;

public class PerProductQuantity extends Discount {

	// Instance Variables
	private Product productForDiscount;
	
	private Integer quantityToApply;

	// Getters and Setters
	public Product getProductForDiscount() {
		return productForDiscount;
	}

	public void setProductForDiscount(Product productForDiscount) {
		this.productForDiscount = productForDiscount;
	}

	public Integer getQuantityToApply() {
		return quantityToApply;
	}

	public void setQuantityToApply(Integer quantityToApply) {
		this.quantityToApply = quantityToApply;
	}

}
