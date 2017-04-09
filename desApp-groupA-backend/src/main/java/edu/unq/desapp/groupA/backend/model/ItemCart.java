package edu.unq.desapp.groupA.backend.model;

public class ItemCart {

	// Instance Variables
	private Product product;

	private Integer quantity;
	
	private Discount discount;

	private Cart cart;

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

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	// Logic
	public Double totalValue() {
		return product.priceForQuantity(quantity);
	}

	public boolean isCategory(ProductCategory categoryForDiscount) {
		return product.isCategory(categoryForDiscount);
	}

	public Boolean hasAppliedDiscount() {
		return discount != null;
	}

	public Boolean isProduct(Product productForDiscount) {
		return product.equals(productForDiscount);
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Cart getCart(){
		return this.cart;
	}
	
}
