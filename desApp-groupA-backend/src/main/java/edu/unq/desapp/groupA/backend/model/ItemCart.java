package edu.unq.desapp.groupA.backend.model;

public class ItemCart extends Item {

	// Instance Variables
	private Discount discount;

	private Boolean checked;

	private Product product;

	// Constructors
	public ItemCart() {
		this.checked = false;
	}

	public ItemCart(Product product, Integer quantity) {
		this.product = product;
		this.quantity = quantity;
		this.checked = false;
	}

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

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
