package edu.unq.desapp.groupA.backend.repository;

import java.util.ArrayList;
import java.util.List;

import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.Product;

public class CartRepository {

	private List<Cart> cartsAvailable;

	public List<Cart> getCartsAvailable() {
		return cartsAvailable;
	}
	
	public CartRepository(){
		this.cartsAvailable = new ArrayList<Cart>();
	}

	public void setCartsAvailable(List<Cart> cartsAvailable) {
		this.cartsAvailable = cartsAvailable;
	}

	public void save(Cart cart) {
		this.cartsAvailable.add(cart);		
	}

	public boolean isCartIncludingProduct(Cart cart, Product product) {
		return cart.getItems().stream().anyMatch(itemC -> itemC.getProduct() == product);
	}
	
	
}
