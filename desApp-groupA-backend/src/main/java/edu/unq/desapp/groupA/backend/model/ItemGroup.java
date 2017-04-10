package edu.unq.desapp.groupA.backend.model;

import java.util.LinkedList;
import java.util.List;

public abstract class ItemGroup<ItemType extends Item> {

	protected List<ItemType> items;
	
	protected Usuario user;

	private Long identifier;

	//Getters and Setters
	public List<ItemType> getItems() {
		return items;
	}

	public void setItems(List<ItemType> items) {
		this.items = items;
	}

	// Logic
	public Double totalValue() {
		Double totalValue = 0.00;
		for (ItemType item : items) {
			totalValue += item.totalValue();
		}
		return totalValue;
	}

	public List<ItemType> itemsCartWithCategory(ProductCategory categoryForDiscount) {
		List<ItemType> itemsCartWithCategory = new LinkedList<ItemType>();
		for (ItemType item : items) {
			if (item.isCategory(categoryForDiscount)) {
				itemsCartWithCategory.add(item);
			}
		}
		return itemsCartWithCategory;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
	
	public Usuario getUser(){
		return this.user;
	}

	public void setIdentifier(Long identifier) {
		this.identifier = identifier;
	}
	
	public Long getIdentifier(){
		return this.identifier;
	}

	public void addItems(ItemType item) {
		this.items.add(item);
	}

}
