package edu.unq.desapp.groupA.backend.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.joda.time.DateTime;

@Entity
@Table(name = "prices")
public class Price extends PersistenceEntity {

	private static final long serialVersionUID = 4954174396323406182L;

	// Instance Variables
	private Double price;
	
	@ManyToOne
	private Product product;
	
	private DateTime startingValidityDate;
	
	private DateTime finishingValidityDate;
	
	// Constructors
	public Price() {
		
	}

	public Price(Product product, Double price) {
		this.product = product;
		this.price = price;
		this.startingValidityDate = DateTime.now();
	}

	// Getters and Setters
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}

	public DateTime getStartingValidityDate() {
		return startingValidityDate;
	}

	public void setStartingValidityDate(DateTime startingValidityDate) {
		this.startingValidityDate = startingValidityDate;
	}

	public DateTime getFinishingValidityDate() {
		return finishingValidityDate;
	}

	public void setFinishingValidityDate(DateTime finishingValidityDate) {
		this.finishingValidityDate = finishingValidityDate;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	// Logic
	public Double priceForQuantity(Integer quantity) {
		return price * quantity;
	}

}
