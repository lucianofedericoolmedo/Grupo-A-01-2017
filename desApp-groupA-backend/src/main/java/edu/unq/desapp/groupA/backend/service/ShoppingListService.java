package edu.unq.desapp.groupA.backend.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import edu.unq.desapp.groupA.backend.model.ItemShoppingList;
import edu.unq.desapp.groupA.backend.model.ShoppingList;
import edu.unq.desapp.groupA.backend.model.Usuario;

public class ShoppingListService {
	
	private List<ShoppingList> shoppingLists;
	private Long identifier;

	public ShoppingListService() {
		this.shoppingLists = new LinkedList<ShoppingList>();
		this.identifier = new Long(0);
	}

	public List<ShoppingList> getShoppingLists() {
		return shoppingLists;
	}

	public void setShoppingLists(List<ShoppingList> shoppingLists) {
		this.shoppingLists = shoppingLists;
	}

	public Long getIdentifier() {
		return identifier;
	}
	
	public void setIdentifier(Long id) {
		this.identifier = id;
	}

	public ShoppingList createShoppingList(Usuario user) {
		ShoppingList shoppingList = new ShoppingList();
		shoppingList.setItems(new ArrayList<ItemShoppingList>());
		shoppingList.setIdentifier(this.insertID());
		shoppingList.setUser(user);
		return shoppingList;
	}

	private Long insertID(){
		Long value = this.getIdentifier();
		this.identifier = this.identifier + (long) 1;
		return value;
	}
	
}
