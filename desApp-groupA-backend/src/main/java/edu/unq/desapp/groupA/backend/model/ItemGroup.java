package edu.unq.desapp.groupA.backend.model;

import java.util.List;
import java.util.stream.Collectors;

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
		return items.stream().map(item -> item.totalValue()).reduce(0.00, (x,y) -> x + y);
	}

	public List<ItemType> itemsCartWithCategory(ProductCategory categoryForDiscount) {
		return items.stream().filter(item -> item.isCategory(categoryForDiscount)).collect(Collectors.toList());
	}
	
	public Boolean containsProduct(Product product) {
		return items.stream().anyMatch(item -> item.isProduct(product));
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
