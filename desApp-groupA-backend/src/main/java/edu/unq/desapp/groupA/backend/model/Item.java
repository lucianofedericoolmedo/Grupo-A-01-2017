package edu.unq.desapp.groupA.backend.model;

public abstract class Item extends Entity {

	// Instance Variables
	protected Product product;

	protected Integer quantity;
	
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

	// Logic
	public Double totalValue() {
		return product.priceForQuantity(quantity);
	}

	public boolean isCategory(ProductCategory categoryForDiscount) {
		return product.isCategory(categoryForDiscount);
	}

	public Boolean isProduct(Product productForDiscount) {
		return product.equals(productForDiscount);
	}

}
