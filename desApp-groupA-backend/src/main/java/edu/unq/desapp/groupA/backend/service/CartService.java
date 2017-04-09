package edu.unq.desapp.groupA.backend.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.ItemCart;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.Usuario;

public class CartService {

	private List<Cart> cartsAvailable;

	public CartService(){
		this.setCartsAvailable(new ArrayList<Cart>());
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
		cart.setUser(user);
		this.cartsAvailable.add(cart);
		return cart;
	}

	
	
	
}
