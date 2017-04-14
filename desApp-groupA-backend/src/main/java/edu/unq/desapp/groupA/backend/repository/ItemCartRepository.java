package edu.unq.desapp.groupA.backend.repository;

import java.util.ArrayList;
import java.util.List;

import edu.unq.desapp.groupA.backend.model.ItemCart;

public class ItemCartRepository {

	private List<ItemCart> items;

	
	public List<ItemCart> getItems() {
		return items;
	}


	public void setItems(List<ItemCart> items) {
		this.items = items;
	}
	
	public ItemCartRepository(){
		this.setItems(new ArrayList<ItemCart>());
	}


	public void save(ItemCart item) {
		this.items.add(item);
	}
}
