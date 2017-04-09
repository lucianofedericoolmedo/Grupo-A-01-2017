package edu.unq.desapp.groupA.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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


	public ItemCart createItemCart(Product product, Cart cart){
		ItemCart item = new ItemCart();
		item.setCart(cart);
		item.setProduct(product);
		cart.addItems(item);	
		return item;
		
	}

	public List<Cart> findByProduct(Product product) {
		List<Cart> result = new ArrayList<Cart>();
		for (ItemCart itemCart : this.items){
			if (itemCart.getProduct().getName().equals(product.getName())){
				result.add(itemCart.getCart());
			}
		}
		return result;
	}


	public List<ItemCart> findByCart(Cart cart) {
		return items.stream().filter(i -> i.getCart().getIdentifier().equals(cart.getIdentifier()))
				.collect(Collectors.toList());
	}
	
	
}
