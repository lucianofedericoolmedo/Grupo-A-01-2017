package edu.unq.desapp.groupA.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.ItemCart;
import edu.unq.desapp.groupA.backend.model.Product;
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
	
	public Cart create(List<ItemCart> items, Usuario usuario) {
		Cart cart =  new Cart();
		cart.setItems(items);
		/*
		pedido.setCartState(CartState.UNATTENDED);
		pedido.setUsuario(usuario);
		pedido.setItems(listaDeProductos);
		*/
		cartsAvailable.add(cart);
		return cart;
	}



	public List<Cart> getCartsAvailable() {
		return cartsAvailable;
	}



	public void setCartsAvailable(List<Cart> cartsAvailable) {
		this.cartsAvailable = cartsAvailable;
	}

	public List<Product> getProducts(Cart cart) {
		return cart.getItems().stream().map(itemC -> itemC.getProduct()).collect(Collectors.toList());
	}

	public List<Product> getProductsDistinctFrom(Cart cart, Product product) {
		// TODO Auto-generated method stub
		return this.getProducts(cart).stream().filter(p -> !p.getName().equals(product.getName())).collect(Collectors.toList());
	}

	public Cart createCart(Usuario user) {
		Cart cart = new Cart();
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
