package edu.unq.desapp.groupA.backend.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ItemGroup<ItemType extends Item> extends PersistenceEntity {

	private static final long serialVersionUID = -7139614401053628294L;

	// Instance Variables
	@ManyToOne
	protected List<ItemType> items;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	protected User user;

	private Long identifier;

	//Getters and Setters
	public List<ItemType> getItems() {
		return items;
	}

	public void setItems(List<ItemType> items) {
		this.items = items;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser(){
		return this.user;
	}

	public void setIdentifier(Long identifier) {
		this.identifier = identifier;
	}
	
	public Long getIdentifier(){
		return this.identifier;
	}

	// Logic
	protected Double totalValueOfItems(List<ItemType> items) {
		return items.stream().map(item -> item.totalValue()).reduce(0.00, (x,y) -> x + y);
	}
	
	public Double totalValue() {
		return totalValueOfItems(items);
	}

	public List<ItemType> itemsCartWithCategory(ProductCategory categoryForDiscount) {
		return itemsCartWithCategory(categoryForDiscount, items);
	}

	public List<ItemType> itemsCartWithCategory(ProductCategory categoryForDiscount, List<ItemType> items) {
		return items.stream().filter(item -> item.isCategory(categoryForDiscount)).collect(Collectors.toList());
	}

	public Double totalValueOfProductCategory(ProductCategory productCategory) {
		return totalValueOfItems(itemsCartWithCategory(productCategory));
	}

	public Boolean containsProduct(Product product) {
		return items.stream().anyMatch(item -> item.isProduct(product));
	}

	public void addItems(ItemType item) {
		this.items.add(item);
	}

}
