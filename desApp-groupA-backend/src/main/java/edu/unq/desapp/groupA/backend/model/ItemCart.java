package edu.unq.desapp.groupA.backend.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "items_cart")
public class ItemCart extends Item {

	private static final long serialVersionUID = -2357797967770914119L;

	// Instance Variables
	private Discount discount;

	private Boolean checked;

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

}
