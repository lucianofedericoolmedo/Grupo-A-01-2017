package edu.unq.desapp.groupA.backend.model;

import java.util.LinkedList;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "shopping_lists")
public class ShoppingList extends ItemGroup<ItemShoppingList> {

	private static final long serialVersionUID = 8643357050037176048L;

	// Constructors
	public ShoppingList() {
		this.items = new LinkedList<ItemShoppingList>();
	}

	public void addItem(ItemShoppingList itemShoppingList) {
		this.items.add(itemShoppingList);
	}


}
