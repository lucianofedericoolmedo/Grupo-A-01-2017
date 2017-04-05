package edu.unq.desapp.groupA.backend.model;

import org.joda.time.DateTime;

public abstract class Discount {
	
	// Instance Variables
	private DateTime startingDate;
	
	private DateTime finishingDate;
	
	private Double percentagePerProductToDiscount;
	
	private Priority priority;

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

	public Double getPercentagePerProductToDiscount() {
		return percentagePerProductToDiscount;
	}

	public void setPercentagePerProductToDiscount(Double percentagePerProductToDiscount) {
		this.percentagePerProductToDiscount = percentagePerProductToDiscount;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	// Logic
	public abstract Double valueToDiscount(ItemCart itemCart);
	
	public abstract Boolean isItemValidForDiscount(ItemCart item);
	
	/**
	 * Set this discount to the item if the item does NOT have any applied discount and
	 * if the item meets the required condition.
	 * @param item : The ItemCart to set the Discount.
	 * @return true if the Discount was applied. false if not.
	 */
	public Boolean applyDiscountIfApplicable(ItemCart item) {
		if (!item.hasAppliedDiscount() && isItemValidForDiscount(item)) {
			item.setDiscount(this);
			return true;
		}
		return false;
	}
	
	public Double percentageValuePerProduct(Double value) {
		return (value / 100) * percentagePerProductToDiscount;
	}

}
