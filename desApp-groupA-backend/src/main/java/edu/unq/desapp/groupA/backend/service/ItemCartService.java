package edu.unq.desapp.groupA.backend.service;

import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.ItemCart;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.repository.ItemCartRepository;

public class ItemCartService {

	private ItemCartRepository repository;


	public ItemCartService(ItemCartRepository repository) {
		this.repository = repository;
	}

	public ItemCart createItemCart(Product product, Cart cart){
		ItemCart item = new ItemCart();
		item.setProduct(product);
		cart.addItems(item);	
		this.repository.save(item);
		return item;
	}

	public ItemCartRepository getRepository() {
		return repository;
	}


	public void setRepository(ItemCartRepository repository) {
		this.repository = repository;
	}

	
}
