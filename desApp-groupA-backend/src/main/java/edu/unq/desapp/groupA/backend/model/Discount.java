package edu.unq.desapp.groupA.backend.model;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Discount extends PersistenceEntity {
	
	private static final long serialVersionUID = 1266654534266762877L;

	// Instance Variables
	private Date startingDate;
	
	private Date finishingDate;
	
	private Double percentagePerProductToDiscount;
	
	@Enumerated(value = EnumType.STRING)
	private Priority priority;

	// Getters and Setters
	public Date getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}

	public Date getFinishingDate() {
		return finishingDate;
	}

	public void setFinishingDate(Date finishingDate) {
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
