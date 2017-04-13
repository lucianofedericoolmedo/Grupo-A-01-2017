package edu.unq.desapp.groupA.backend.service;

import java.util.ArrayList;

import edu.unq.desapp.groupA.backend.model.ItemShoppingList;
import edu.unq.desapp.groupA.backend.model.ShoppingList;
import edu.unq.desapp.groupA.backend.model.Usuario;
import edu.unq.desapp.groupA.backend.repository.ShoppingListRepository;

public class ShoppingListService {
	
	private ShoppingListRepository repository;
	private Long identifier;

	public ShoppingListService() {
		this.setRepository(new ShoppingListRepository());
		this.identifier = new Long(0);
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
		this.repository.save(shoppingList);
		return shoppingList;
	}

	private Long insertID(){
		Long value = this.getIdentifier();
		this.identifier = this.identifier + (long) 1;
		return value;
	}



	public ShoppingListRepository getRepository() {
		return repository;
	}



	public void setRepository(ShoppingListRepository repository) {
		this.repository = repository;
	}
	
}
