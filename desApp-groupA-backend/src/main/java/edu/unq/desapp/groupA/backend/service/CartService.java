package edu.unq.desapp.groupA.backend.service;

import java.util.ArrayList;

import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.ItemCart;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.Usuario;
import edu.unq.desapp.groupA.backend.repository.CartRepository;

public class CartService {

	private CartRepository repository;
	private Long identifier;
	
	public CartService(CartRepository repository, Long identifier) {
		this.repository = repository;
		this.identifier = identifier;
	}

	public Long getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Long id) {
		this.identifier = id;
	}
	
	public Cart createCart(Usuario user) {
		Cart cart = new Cart();
		cart.setItems(new ArrayList<ItemCart>());
		cart.setIdentifier(this.insertID());
		cart.setUser(user);
		this.repository.save(cart);
		return cart;
	}
	
	private Long insertID(){
		Long value = this.getIdentifier();
		this.identifier = this.identifier + (long) 1;
		return value;
	}

	public boolean isCartIncludingProduct(Cart cart, Product product) {
		return this.repository.isCartIncludingProduct(cart,product);
	}

	public CartRepository getRepository() {
		return repository;
	}

	public void setRepository(CartRepository repository) {
		this.repository = repository;
	}

	
}
