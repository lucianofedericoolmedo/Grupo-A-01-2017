package edu.unq.desapp.groupA.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unq.desapp.groupA.backend.exceptions.ProductAlreadyInItemGroupException;
import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.ItemCart;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.ShoppingList;
import edu.unq.desapp.groupA.backend.model.User;
import edu.unq.desapp.groupA.backend.repository.CartRepository;


@Service
@Transactional
public class CartService extends GenericService<Cart> {

	@Autowired
	private CartRepository repository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ItemCartService itemCartService;

	private Long identifier;

	public CartService() { }
	
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

	public Cart createCart(User user) {
		Cart cart = new Cart();
		cart.setUser(user);
		return super.save(cart);
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
		super.save(cart);
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
		ItemCart itemCart = getItemCartService().createItemCart(product, 0, cart);
		cart.addItems(itemCart);
		return itemCart;
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

	public ItemCartService getItemCartService() {
		return itemCartService;
	}

	public void setItemCartService(ItemCartService itemCartService) {
		this.itemCartService = itemCartService;
	}

	public Cart create(Long userId, Cart cart) {
		User user = userService.find(userId);
		cart.setUser(user);
		return super.save(cart);
	}
	
}
