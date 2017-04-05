package edu.unq.desapp.groupA.backend.service;

import java.util.LinkedList;
import java.util.List;

import edu.unq.desapp.groupA.backend.model.Discount;
import edu.unq.desapp.groupA.backend.model.ItemCart;
import edu.unq.desapp.groupA.backend.model.Priority;

public class DiscountAssigner {

	public List<Discount> findActiveDiscountsWithPriority(Priority priority) {
		return new LinkedList<Discount>();
	}
	
	public void assignDiscounts(List<ItemCart> itemsCart) {
		List<ItemCart> itemsToApply = new LinkedList<ItemCart>(itemsCart);
		Priority[] priorities = new Priority[]{Priority.HIGH, Priority.MEDIUM, Priority.LOW};
		for (Priority priority : priorities) {
			findDiscountsWithPriorityAndAssigns(itemsToApply, priority);
		}
	}
	
	public void findDiscountsWithPriorityAndAssigns(List<ItemCart> items, Priority priority) {
		List<Discount> discountsWithHighPriority = findActiveDiscountsWithPriority(Priority.HIGH);
		items.removeAll(assignDiscounts(items, discountsWithHighPriority));
		if (items.isEmpty()) {
			return;
		}
	}
	
	/**
	 * Assigns the discounts in the items.
	 * @param items : The items to assign, if corresponds, the discounts.
	 * @param discounts : The discounts to assign.
	 * @return A list of ItemCart containing the elements with not applied Discounts. 
	 */
	public List<ItemCart> assignDiscounts(List<ItemCart> items, List<Discount> discounts) {
		List<ItemCart> itemsNotMatchingRequirements = new LinkedList<ItemCart>();
		for (Discount discount : discounts) {
			for (ItemCart item : items) {
				if (!discount.applyDiscountIfApplicable(item)) {
					itemsNotMatchingRequirements.add(item);
				}
			}
		}
		return itemsNotMatchingRequirements;
	}

}
