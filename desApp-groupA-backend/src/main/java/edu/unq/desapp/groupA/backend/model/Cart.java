package edu.unq.desapp.groupA.backend.model;

import java.util.LinkedList;

public class Cart extends ItemGroup<ItemCart> {

	// Instance Variables
	private ShoppingList usedShoppingList;

	// Constructors
	public Cart() {
		this.items = new LinkedList<ItemCart>();
	}
	
	// Getters and Setters
	public ShoppingList getUsedShoppingList() {
		return usedShoppingList;
	}

	public void setUsedShoppingList(ShoppingList usedShoppingList) {
		this.usedShoppingList = usedShoppingList;
	}

}
