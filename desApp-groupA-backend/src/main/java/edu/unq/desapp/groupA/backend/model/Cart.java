package edu.unq.desapp.groupA.backend.model;

import java.util.LinkedList;
import java.util.List;

public class Cart {

	// Instance Variables
	private List<ItemCart> items;

	//Getters and Setters
	public List<ItemCart> getItems() {
		return items;
	}

	public void setItems(List<ItemCart> items) {
		this.items = items;
	}

	// Logic
	public Double totalValue() {
		Double totalValue = 0.00;
		for (ItemCart item : items) {
			totalValue += item.totalValue();
		}
		return totalValue;
	}

	public List<ItemCart> itemsCartWithCategory(ProductCategory categoryForDiscount) {
		List<ItemCart> itemsCartWithCategory = new LinkedList<ItemCart>();
		for (ItemCart item : items) {
			if (item.isCategory(categoryForDiscount)) {
				itemsCartWithCategory.add(item);
			}
		}
		return itemsCartWithCategory;
	}
	
}