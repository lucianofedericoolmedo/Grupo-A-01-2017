package edu.unq.desapp.groupA.backend.service;

import java.util.ArrayList;
import java.util.List;

import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.ItemCart;
import edu.unq.desapp.groupA.backend.model.Usuario;

public class CartService {

	private List<Cart> cartsAvailable;
	private Long identifier;

	
	public CartService(){
		this.setCartsAvailable(new ArrayList<Cart>());
		this.identifier = (long) 0;
	}
	
	public Long getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Long id) {
		this.identifier = id;
	}
	
	public List<Cart> getCartsAvailable() {
		return cartsAvailable;
	}

	public void setCartsAvailable(List<Cart> cartsAvailable) {
		this.cartsAvailable = cartsAvailable;
	}

	public Cart createCart(Usuario user) {
		Cart cart = new Cart();
		cart.setItems(new ArrayList<ItemCart>());
		cart.setIdentifier(this.insertID());
		cart.setUser(user);
		this.cartsAvailable.add(cart);
		return cart;
	}
	
	private Long insertID(){
		Long value = this.getIdentifier();
		this.identifier = this.identifier + (long) 1;
		return value;
	}

	
}
