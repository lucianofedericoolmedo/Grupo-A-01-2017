package edu.unq.desapp.groupA.backend.model;

import org.joda.time.DateTime;

public abstract class Discount extends Entity {
	
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
	/**
	 * Calculates the value to be discounted of an item cart
	 * @param itemCart : The item cart with the value to be calculated.
	 * @return A Double indicating the total value of the cart item.
	 */
	public abstract Double valueToDiscount(ItemCart itemCart);
	
	/**
	 * Evaluates a cart item to check if it is valid to apply the Discount. 
	 * @param item : The item to be evaluated.
	 * @return A Boolean indicating if the item meets the requirements. 
	 */
	public abstract Boolean isItemValidForDiscount(ItemCart item);
	
	/**
	 * Sets this discount to the item if the item does NOT have any applied discount and
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

	/**
	 * Calculates the percentage of a Double value using the percentage indicated
	 * in percentagePerProductToDiscount. 
	 * @param value : The value to obtain the percentage.
	 * @return A Double value of the percentage applied to the parameter.
	 */
	public Double percentageValuePerProduct(Double value) {
		return (value / 100) * percentagePerProductToDiscount;
	}

}
