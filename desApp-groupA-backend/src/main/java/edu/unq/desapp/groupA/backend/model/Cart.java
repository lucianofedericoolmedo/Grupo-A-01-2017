package edu.unq.desapp.groupA.backend.model;

public class Cart extends ItemGroup<ItemCart> {

	// Instance Variables
	private ShoppingList usedShoppingList;

	// Getters and Setters
	public ShoppingList getUsedShoppingList() {
		return usedShoppingList;
	}

	public void setUsedShoppingList(ShoppingList usedShoppingList) {
		this.usedShoppingList = usedShoppingList;
	}

}
