package edu.unq.desapp.groupA.backend.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.unq.desapp.groupA.backend.model.ItemShoppingList;


@Repository
public class ItemShoppingListRepository extends HibernateGenericDAO<ItemShoppingList> {

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
	
	@Override
	protected Class<ItemShoppingList> getDomainClass() {
		return ItemShoppingList.class;
	}

}
