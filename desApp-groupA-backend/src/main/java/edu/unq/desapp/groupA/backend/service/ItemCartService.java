package edu.unq.desapp.groupA.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.ItemCart;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.repository.ItemCartRepository;


@Service
@Transactional
public class ItemCartService extends GenericService<ItemCart> {

	@Autowired
	private ItemCartRepository repository;

	@Autowired
	private CartService cartService;

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

	public ItemCartService() { }

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
	
	public ItemCart createForCartId(Long cartId, ItemCart itemCart) {
		Cart cart = cartService.find(cartId);
		itemCart = super.save(itemCart);
		cart.addItems(itemCart);
		cartService.update(cart);
		return itemCart;
	}

}
