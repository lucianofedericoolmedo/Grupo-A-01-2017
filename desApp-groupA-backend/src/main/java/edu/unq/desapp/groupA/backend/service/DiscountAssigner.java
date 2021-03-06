package edu.unq.desapp.groupA.backend.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.Discount;
import edu.unq.desapp.groupA.backend.model.ItemCart;
import edu.unq.desapp.groupA.backend.model.Priority;


@Service
public class DiscountAssigner {

	@Autowired
	private ItemCartService itemCartService;
	
	@Autowired
	private DiscountService discountService;
	
	@Transactional
	public List<Discount> findActiveDiscountsWithPriority(Priority priority) {
		return discountService.findActiveDiscountsByPriority(priority);
	}
	
	@Transactional
	public void assignDiscounts(Cart cart) {
		List<ItemCart> checkedItemsInCart =  cart.checkedItems();
		assignDiscounts(checkedItemsInCart);
		for (ItemCart itemCart : checkedItemsInCart) {
			itemCartService.update(itemCart);
		}
	}
	
	@Transactional
	public void assignDiscounts(List<ItemCart> itemsCart) {
		List<ItemCart> itemsToApply = new LinkedList<ItemCart>(itemsCart);
		Priority[] priorities = new Priority[]{Priority.HIGH, Priority.MEDIUM, Priority.LOW};
		for (Priority priority : priorities) {
			if (!findDiscountsWithPriorityAndAssigns(itemsToApply, priority)) {
				//break;
			}
		}
	}
	
	/**
	 * Finds the active discounts with the priority and assigns the found discounts.
	 * @param items : The items to assign discounts if corresponds.
	 * @param priority : The priority to find the discounts.
	 * @return A Boolean: true if there is any item cart without Discounts. false if every item
	 * cart has an applied discount.
	 */
	@Transactional
	public Boolean findDiscountsWithPriorityAndAssigns(List<ItemCart> items, Priority priority) {
		List<Discount> discountsWithHighPriority = findActiveDiscountsWithPriority(priority);
//		items.removeAll(assignSpecifiedDiscounts(items, discountsWithHighPriority));
		assignSpecifiedDiscounts(items, discountsWithHighPriority);
		return !items.isEmpty();
	}
	
	/**
	 * Assigns the discounts in the items.
	 * @param items : The items to assign, if corresponds, the discounts.
	 * @param discounts : The discounts to assign.
	 * @return A list of ItemCart containing the elements with not applied Discounts. 
	 */
	@Transactional
	public List<ItemCart> assignSpecifiedDiscounts(List<ItemCart> items, List<Discount> discounts) {
		List<ItemCart> itemsNotMatchingRequirements = new LinkedList<ItemCart>();
		for (ItemCart item : items) {
			Boolean hasAnyAppliedDiscount = false;
			for (Discount discount : discounts) {
				hasAnyAppliedDiscount |= discount.applyDiscountIfApplicable(item);
				if (hasAnyAppliedDiscount) {
					break;
				}
			}
			if (!hasAnyAppliedDiscount) {
				itemsNotMatchingRequirements.add(item);
			}
		}
		return itemsNotMatchingRequirements;
	}

}
