package edu.unq.desapp.groupA.backend.model;

import java.util.Date;
import java.util.LinkedList;

public class Cart extends ItemGroup<ItemCart> {

	// Instance Variables
	private ShoppingList usedShoppingList;
	private Date reservationTime;
	
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

	public Integer quantityOfItems() {
		return this.items.size();
	}

	public void setReservationTime(Date date) {
		this.reservationTime = date;
	}
	
	public Date getReservationTime() {
		return reservationTime;
	}
}
