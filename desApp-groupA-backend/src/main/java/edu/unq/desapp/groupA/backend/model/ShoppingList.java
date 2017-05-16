package edu.unq.desapp.groupA.backend.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "shopping_lists")
public class ShoppingList extends ItemGroup<ItemShoppingList> {

	private static final long serialVersionUID = 8643357050037176048L;

	// Instance Variables
	@OneToMany(mappedBy="parent", cascade=CascadeType.MERGE, orphanRemoval=true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<ItemShoppingList> items;
	
	// Constructors
	public ShoppingList() {
		this.items = new LinkedList<ItemShoppingList>();
	}

	public void addItem(ItemShoppingList itemShoppingList) {
		itemShoppingList.setParent(this);
		this.addItems(itemShoppingList);
		//this.items.add(itemShoppingList);
	}

	// Getters and Setters
	@Override
	public List<ItemShoppingList> getItems() {
		return items;
	}

	@Override
	public void setItems(List<ItemShoppingList> items) {
		this.items = items;
	}

}
