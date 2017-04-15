package edu.unq.desapp.groupA.backend.model;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;

public class Cart extends ItemGroup<ItemCart> {

	// Instance Variables
	private ShoppingList usedShoppingList;
	private DateTime reservationTime;
	
	// Constructors
	public Cart() {
		this.items = new LinkedList<ItemCart>();
	}
	
	public boolean includesProduct(Product p){
		return this.items.stream().anyMatch(itemC -> itemC.getProduct().equals(p));
	}
	
	// Getters and Setters
	public ShoppingList getUsedShoppingList() {
		return usedShoppingList;
	}

	public void setUsedShoppingList(ShoppingList usedShoppingList) {
		this.usedShoppingList = usedShoppingList;
	}
	
	// Logic
	public List<ItemCart> checkedItems() {
		return items.stream().filter(item -> item.getChecked()).collect(Collectors.toList());
	}

	public Double totalValueOfCheckedItemsWithProductCategory(ProductCategory categoryForDiscount) {
		return totalValueOfItems(itemsCartWithCategory(categoryForDiscount, checkedItems()));
	}
	
	public Double totalValueOfCheckedItems() {
		return totalValueOfItems(checkedItems());
	}

	public Integer quantityOfItems() {
		return this.items.size();
	}

	public void setReservationTime(DateTime date) {
		this.reservationTime = date;
	}
	
	public DateTime getReservationTime() {
		return reservationTime;
	}
}
