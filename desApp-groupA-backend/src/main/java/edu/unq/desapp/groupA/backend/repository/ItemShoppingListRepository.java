package edu.unq.desapp.groupA.backend.repository;

import java.util.ArrayList;
import java.util.List;

import edu.unq.desapp.groupA.backend.model.ItemShoppingList;

public class ItemShoppingListRepository {

	private List<ItemShoppingList> items;

	public List<ItemShoppingList> getItems() {
		return items;
	}

	public void setItems(List<ItemShoppingList> items) {
		this.items = items;
	}

	public ItemShoppingListRepository(){
		this.items = new ArrayList<ItemShoppingList>();
	}
	
	public void save(ItemShoppingList itemShoppingList) {
		this.items.add(itemShoppingList);
	}

}
