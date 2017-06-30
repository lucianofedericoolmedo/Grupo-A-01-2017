package edu.unq.desapp.groupA.backend.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unq.desapp.groupA.backend.model.ItemShoppingList;
import edu.unq.desapp.groupA.backend.model.ShoppingList;
import edu.unq.desapp.groupA.backend.model.UserCredential;
import edu.unq.desapp.groupA.backend.repository.ShoppingListRepository;
import edu.unq.desapp.groupA.backend.repository.pagination.PageResponse;


@Service
@Transactional
public class ShoppingListService extends GenericService<ShoppingList> {
	
	@Autowired
	private ShoppingListRepository repository;
	
	@Autowired
	private ItemShoppingListService itemShoppingListService;
	
	@Autowired
	private UserService userService;
	
	private Long identifier;

	public ShoppingListService() { }

	public Long getIdentifier() {
		return identifier;
	}
	
	public void setIdentifier(Long id) {
		this.identifier = id;
	}

	@Transactional
	public ShoppingList createShoppingList(UserCredential user) {
		ShoppingList shoppingList = new ShoppingList();
		shoppingList.setItems(new ArrayList<ItemShoppingList>());
		shoppingList.setIdentifier(this.insertID());
		shoppingList.setUser(user);
		this.repository.save(shoppingList);
		return shoppingList;
	}

	@Transactional
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

	@Transactional
	public ItemShoppingList createItemForShoppingList(Long shoppingListId, ItemShoppingList itemShoppingList) {
		ShoppingList shoppingList = super.find(shoppingListId);
		itemShoppingList.setParent(shoppingList);
		shoppingList.addItem(itemShoppingListService.save(itemShoppingList));
		return itemShoppingList;
	}
	
	@Transactional
	public void removeItemFromShoppingList(Long itemId, Long shoppingListId) {
		itemShoppingListService.delete(itemId);
		ShoppingList shoppingList = super.find(shoppingListId);
		shoppingList.deleteItemWithId(itemId);
		super.update(shoppingList);
	}

	@Transactional
	public ShoppingList createFor(Long userId, ShoppingList shoppingList) {
		UserCredential user = userService.find(userId);
		if (user == null) {
			throw new RuntimeException("Invalid user");
		}
		shoppingList.setUser(user);
		return super.save(shoppingList);
	}

	public PageResponse<ShoppingList> findPageByUserId(Integer pageNumber, Integer pageSize, Long userId) {
		return repository.findPageByUserId(pageNumber, pageSize, userId);
	}
	
}
