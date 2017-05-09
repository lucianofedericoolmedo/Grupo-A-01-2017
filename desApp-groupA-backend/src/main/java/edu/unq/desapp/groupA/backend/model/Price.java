package edu.unq.desapp.groupA.backend.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.joda.time.DateTime;

@Entity
@Table(name = "prices")
public class Price extends PersistenceEntity {

	private static final long serialVersionUID = 4954174396323406182L;

	// Instance Variables
	private Double price;
		
	private Date startingValidityDate;
	
	private Date finishingValidityDate;
	
	// Constructors
	public Price() {
		
	}

	public Price( Double price) {
		this.price = price;
		this.startingValidityDate = new Date();
	}

	// Getters and Setters
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getStartingValidityDate() {
		return startingValidityDate;
	}

	public void setStartingValidityDate(Date startingValidityDate) {
		this.startingValidityDate = startingValidityDate;
	}

	public Date getFinishingValidityDate() {
		return finishingValidityDate;
	}

	public void setFinishingValidityDate(Date finishingValidityDate) {
		this.finishingValidityDate = finishingValidityDate;
	}

	// Logic
	public Double priceForQuantity(Integer quantity) {
		return price * quantity;
	}

}
