package edu.unq.desapp.groupA.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unq.desapp.groupA.backend.model.ItemShoppingList;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.ShoppingList;
import edu.unq.desapp.groupA.backend.repository.ItemShoppingListRepository;


@Service
@Transactional
public class ItemShoppingListService extends GenericService<ItemShoppingList> {

	@Autowired
	private ItemShoppingListRepository repository;

	public ItemShoppingListRepository getRepository() {
		return repository;
	}

	public void setRepository(ItemShoppingListRepository repository) {
		this.repository = repository;
	}

	public ItemShoppingListService(){ }

	public ItemShoppingList createItemShoppingList(Product product, Integer quantity,ShoppingList shoppingList) {
		ItemShoppingList itemShoppingList = new ItemShoppingList();
		itemShoppingList.setProduct(product);
		itemShoppingList.setQuantity(quantity);
		shoppingList.addItem(itemShoppingList);
		repository.save(itemShoppingList);
		return itemShoppingList;
	}
}
