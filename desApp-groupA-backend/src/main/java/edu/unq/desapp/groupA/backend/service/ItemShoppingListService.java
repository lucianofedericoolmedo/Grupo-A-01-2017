package edu.unq.desapp.groupA.backend.service;

import edu.unq.desapp.groupA.backend.model.ItemShoppingList;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.ShoppingList;
import edu.unq.desapp.groupA.backend.repository.ItemShoppingListRepository;

public class ItemShoppingListService {

	private ItemShoppingListRepository repository;

	public ItemShoppingListRepository getRepository() {
		return repository;
	}

	public void setRepository(ItemShoppingListRepository repository) {
		this.repository = repository;
	}
	
	public ItemShoppingListService(){
		this.repository = new ItemShoppingListRepository();
	}

	public ItemShoppingList createItemShoppingList(Product product, Integer quantity,ShoppingList shoppingList) {
		ItemShoppingList itemShoppingList = new ItemShoppingList();
		itemShoppingList.setProduct(product);
		itemShoppingList.setQuantity(quantity);
		shoppingList.addItem(itemShoppingList);
		repository.save(itemShoppingList);
		return itemShoppingList;
	}
}
