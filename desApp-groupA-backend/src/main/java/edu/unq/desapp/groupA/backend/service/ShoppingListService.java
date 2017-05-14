package edu.unq.desapp.groupA.backend.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unq.desapp.groupA.backend.model.ItemShoppingList;
import edu.unq.desapp.groupA.backend.model.ShoppingList;
import edu.unq.desapp.groupA.backend.model.User;
import edu.unq.desapp.groupA.backend.repository.ShoppingListRepository;


@Service
public class ShoppingListService extends GenericService<ShoppingList> {
	
	@Autowired
	private ShoppingListRepository repository;
	
	private Long identifier;

	public ShoppingListService() { }

	public Long getIdentifier() {
		return identifier;
	}
	
	public void setIdentifier(Long id) {
		this.identifier = id;
	}

	public ShoppingList createShoppingList(User user) {
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
