package edu.unq.desapp.groupA.backend.model;

import java.util.LinkedList;

public class ShoppingList extends ItemGroup<ItemShoppingList> {

	// Constructors
	public ShoppingList() {
		this.items = new LinkedList<ItemShoppingList>();
	}

}
