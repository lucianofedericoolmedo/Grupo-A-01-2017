package edu.unq.desapp.groupA.backend.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stocks")
public class Stock extends PersistenceEntity {

	private static final long serialVersionUID = -5951486096677461006L;

	// Instance Variables
	@ManyToOne
	private Product product;
	
	private Integer quantity;

	// Constructors
	public Stock() {
		
	}

	public Stock(Product product, Integer quantity) {
		this.product = product;
		this.quantity = quantity;
	}

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
