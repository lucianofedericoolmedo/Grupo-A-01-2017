package edu.unq.desapp.groupA.backend.service;

import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.ItemCart;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.repository.ItemCartRepository;

public class ItemCartService {

	private ItemCartRepository repository;


	/**
	 * Creates a @ItemCart instance with the given parameters and adds itself to the @Cart.
	 * @param product : The @Product to create the @ItemCart with.
	 * @param quantity : The quantity to create the @ItemCart with.
	 * @param cart : The @Cart to add the new @ItemCart instance.
	 * @return The created @ItemCart instance.
	 */
	public ItemCart createItemCart(Product product, Integer quantity, Cart cart){
		ItemCart item = new ItemCart(product, quantity);
		this.repository.save(item);
		return item;
	}

	public ItemCartService(ItemCartRepository repository) {
		this.repository = repository;
	}

	public ItemCart createItemCart(Product product, Cart cart){
		ItemCart item = new ItemCart();
		item.setProduct(product);
		this.repository.save(item);
		return item;
	}

	public ItemCartRepository getRepository() {
		return repository;
	}


	public void setRepository(ItemCartRepository repository) {
		this.repository = repository;
	}

	public void checkItemCart(ItemCart itemCart) {
		itemCart.setChecked(true);
		this.getRepository().save(itemCart);
	}

}
