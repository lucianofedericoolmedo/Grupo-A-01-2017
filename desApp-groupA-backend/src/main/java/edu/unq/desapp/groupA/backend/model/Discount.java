package edu.unq.desapp.groupA.backend.model;

import org.joda.time.DateTime;

public abstract class Discount {
	
	// Instance Variables
	private DateTime startingDate;
	
	private DateTime finishingDate;
	
	private Integer percentagePerProductToDiscount;

	// Getters and Setters
	public DateTime getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(DateTime startingDate) {
		this.startingDate = startingDate;
	}

	public DateTime getFinishingDate() {
		return finishingDate;
	}

	public void setFinishingDate(DateTime finishingDate) {
		this.finishingDate = finishingDate;
	}

	public Integer getPercentagePerProductToDiscount() {
		return percentagePerProductToDiscount;
	}

	public void setPercentagePerProductToDiscount(Integer percentagePerProductToDiscount) {
		this.percentagePerProductToDiscount = percentagePerProductToDiscount;
	}

}
