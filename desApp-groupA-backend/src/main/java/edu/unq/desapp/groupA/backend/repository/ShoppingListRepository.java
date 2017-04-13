package edu.unq.desapp.groupA.backend.repository;

import java.util.ArrayList;
import java.util.List;

import edu.unq.desapp.groupA.backend.model.ShoppingList;

public class ShoppingListRepository {

	private List<ShoppingList> shoppingLists;

	public ShoppingListRepository() {
		this.shoppingLists = new ArrayList<ShoppingList>();
	}

	public List<ShoppingList> getShoppingLists() {
		return shoppingLists;
	}

	public void setShoppingLists(List<ShoppingList> shoppingLists) {
		this.shoppingLists = shoppingLists;
	}

	public void save(ShoppingList shoppingList) {
		this.shoppingLists.add(shoppingList);
	}
	
}
