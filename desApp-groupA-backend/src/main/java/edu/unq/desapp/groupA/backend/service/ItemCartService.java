package edu.unq.desapp.groupA.backend.service;

import java.util.ArrayList;
import java.util.List;

import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.ItemCart;
import edu.unq.desapp.groupA.backend.model.Product;

public class ItemCartService {

	private List<ItemCart> items;

	
	public List<ItemCart> getItems() {
		return items;
	}

	public void setItems(List<ItemCart> items) {
		this.items = items;
	}

	public ItemCartService(){
		this.setItems(new ArrayList<ItemCart>());
	}

	/**
	 * Creates a @ItemCart instance with the given parameters and adds itself to the @Cart.
	 * @param product : The @Product to create the @ItemCart with.
	 * @param quantity : The quantity to create the @ItemCart with.
	 * @param cart : The @Cart to add the new @ItemCart instance.
	 * @return The created @ItemCart instance.
	 */
	public ItemCart createItemCart(Product product, Integer quantity, Cart cart){
		ItemCart item = new ItemCart(product, quantity);
		cart.addItems(item);
		return item;
	}

	public void checkItemCart(ItemCart itemCart) {
		itemCart.setChecked(true);
	}

}
