package edu.unq.desapp.groupA.backend.model;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item extends PersistenceEntity {

	private static final long serialVersionUID = -454916293375732956L;

	// Instance Variables
	@ManyToOne
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
