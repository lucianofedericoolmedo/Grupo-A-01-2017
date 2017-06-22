package edu.unq.desapp.groupA.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unq.desapp.groupA.backend.exceptions.ProductAlreadyInItemGroupException;
import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.CashRegister;
import edu.unq.desapp.groupA.backend.model.ItemCart;
import edu.unq.desapp.groupA.backend.model.PaymentTurn;
import edu.unq.desapp.groupA.backend.model.PaymentTurnStatus;
import edu.unq.desapp.groupA.backend.model.PaymentType;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.Purchase;
import edu.unq.desapp.groupA.backend.model.ShippingAddress;
import edu.unq.desapp.groupA.backend.model.ShoppingList;
import edu.unq.desapp.groupA.backend.model.UserCredential;
import edu.unq.desapp.groupA.backend.repository.CartRepository;
import edu.unq.desapp.groupA.backend.worker.PaymentCountdownThread;


@Service
@Transactional
public class CartService extends GenericService<Cart> {

	@Autowired
	private CartRepository repository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ItemCartService itemCartService;
	
	@Autowired
	private ShoppingListService shoppingListService;
	
	@Autowired
	private BalancerService balancerService;
	
	@Autowired
	private CashRegisterManagement cashRegisterManagement;
	
	@Autowired
	private PaymentTurnService paymentTurnService;
	
	@Autowired
	private PurchaseService purchaseService;

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

	public Cart createCart(UserCredential user) {
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
		cart.setUsedShoppingList(shoppingList);
		super.save(cart);

		List<ItemCart> items = shoppingList.getItems().stream().map(item -> new ItemCart(item.getProduct(), item.getQuantity())).collect(Collectors.toList());
		
		for (ItemCart item : items){
			cart.addItems(item);
		}
		return super.update(cart);
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
		UserCredential user = userService.find(userId);
		cart.setUser(user);
		return super.save(cart);
	}

	public Cart create(Long shoppingListId, Long userId) {
		UserCredential fetchedUser = userService.find(userId);
		ShoppingList shoppingList = shoppingListService.find(shoppingListId);
		Cart createdCart = this.createCartForShoppingList(shoppingList);
		createdCart.setUsedShoppingList(shoppingList);
		createdCart.setUser(fetchedUser);
		return super.update(createdCart);
	}

	public PaymentTurn requestTurnToPay(Long cartId) {
		paymentTurnService.deleteRequestedForCartId(cartId);
		CashRegister cashRegister = balancerService.getCashRegisterToQueue();
		PaymentTurn turn = new PaymentTurn(cartId, cashRegister.getCode(), cashRegister.getStimatedTime());
		return paymentTurnService.save(turn);
	}

	public void queueForPurchase(PaymentTurn turn) {
		turn.setStatus(PaymentTurnStatus.CONFIRMED);
		Cart cart  = super.find(turn.getCartId());
		CashRegister cashRegister = cashRegisterManagement.getCashRegisterWithCode(turn.getCashRegisterCode());
		cashRegister.requirePurchase(cart);
		paymentTurnService.save(turn);
		PaymentCountdownThread countdownThread = new PaymentCountdownThread(this, turn);
		countdownThread.start();
	}

	public void setValueToItem(Long itemCartId, Boolean checked, Integer newQuantity) {
		itemCartService.setValueToItem(itemCartId, checked, newQuantity);
	}

	public void createCashRegisterPurchaseFor(PaymentTurn turnParam) {
		this.createPurchaseFor(turnParam, PaymentType.CASH_REGISTER, null);
	}

	public void createHomeDeliveryPurchaseFor(PaymentTurn turnParam, ShippingAddress shippingAddress) {
		this.createPurchaseFor(turnParam, PaymentType.HOME_DELIVERY, shippingAddress);
	}

	public void createPurchaseFor(PaymentTurn turnParam, PaymentType paymentType, ShippingAddress shippingAddress) {
		PaymentTurn turn = paymentTurnService.find(turnParam.getId());
		Cart cart = this.find(turnParam.getCartId());
		CashRegister cashRegister = cashRegisterManagement.getCashRegisterWithCode(turn.getCashRegisterCode());
		cashRegister.removecheckedItemsFrom(cart);
		Purchase newPurchase = new Purchase(cart, paymentType, turn, shippingAddress);
		purchaseService.save(newPurchase);
	}

	public Long findUnattendedCartByUserId(Long userId) {
		return this.repository.findUnattendedCartByUserId(userId);
	}

}
