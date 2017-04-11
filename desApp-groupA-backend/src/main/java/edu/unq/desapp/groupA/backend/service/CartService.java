package edu.unq.desapp.groupA.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import edu.unq.desapp.groupA.backend.exceptions.ProductAlreadyInItemGroupException;
import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.ItemCart;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.ShoppingList;
import edu.unq.desapp.groupA.backend.model.Usuario;

public class CartService {

	private List<Cart> cartsAvailable;
	private Long identifier;
	private ItemCartService itemCartService;

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

	/**
	 * Creates a @Cart instance using the information and @ItemCart of a @ShoppingList.
	 * @param shoppingList : The @ShoppingList used to construct a @Cart.
	 * @return A @Cart instance.
	 */
	public Cart createCartForShoppingList(ShoppingList shoppingList) {
		Cart cart = createCart(shoppingList.getUser());
		List<ItemCart> items = shoppingList.getItems().stream().map(item -> new ItemCart(item.getProduct(), item.getQuantity())).collect(Collectors.toList());
		cart.setItems(items);
		cart.setUsedShoppingList(shoppingList);
		return cart;
	}

	/**
	 * Create and adds an ItemCart with the product given as parameter if
	 * the Cart does not contains the Product.
	 * @param cart : The Cart to add the new ItemCart.
	 * @param product : The Product to create the ItemCart with.
	 * @throws ProductAlreadyInItemGroupException : If the Product is in the Cart.
	 */
	public ItemCart addProductInCartIfMissing(Cart cart, Product product) {
		if (cart.containsProduct(product)) {
			throw new ProductAlreadyInItemGroupException(product, "The cart already has and item with the product");
		}
		return itemCartService.createItemCart(product, 0, cart);
	}

	private Long insertID(){
		Long value = this.getIdentifier();
		this.identifier = this.identifier + (long) 1;
		return value;
	}

}
