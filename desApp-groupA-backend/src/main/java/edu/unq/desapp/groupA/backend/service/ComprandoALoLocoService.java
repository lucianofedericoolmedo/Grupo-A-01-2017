package edu.unq.desapp.groupA.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import edu.unq.desapp.groupA.backend.model.Brand;
import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.CashRegister;
import edu.unq.desapp.groupA.backend.model.ItemCart;
import edu.unq.desapp.groupA.backend.model.ItemShoppingList;
import edu.unq.desapp.groupA.backend.model.PaymentType;
import edu.unq.desapp.groupA.backend.model.Price;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.ProductCategory;
import edu.unq.desapp.groupA.backend.model.ProductThresold;
import edu.unq.desapp.groupA.backend.model.Purchase;
import edu.unq.desapp.groupA.backend.model.ShoppingList;
import edu.unq.desapp.groupA.backend.model.Thresold;
import edu.unq.desapp.groupA.backend.model.UserProfile;
import edu.unq.desapp.groupA.backend.model.Usuario;

public class ComprandoALoLocoService {

	private BalancerService balancerService;
	private CashRegisterService cashRegisterService;
	private CartService cartService;
	private UserService userService;
	private ItemCartService itemCartService;
	private ProductService productService;
	private PurchaseService purchaseService;
	private PaymentTypeService paymentTypeService;
	private ProductThresoldService productThresoldService;
	private UserProfileService userProfileService;
	private ShoppingListService shoppingListService;	
	private ItemShoppingListService itemShoppingListService;
	
	/*
	 * Add all the other services ... 
	 */
	
	public ShoppingListService getShoppingListService() {
		return shoppingListService;
	}

	public void setShoppingListService(ShoppingListService shoppingListService) {
		this.shoppingListService = shoppingListService;
	}

	public ItemShoppingListService getItemShoppingListService() {
		return itemShoppingListService;
	}

	public void setItemShoppingListService(ItemShoppingListService itemShoppingListService) {
		this.itemShoppingListService = itemShoppingListService;
	}

	
	public ProductThresoldService getProductThresoldService() {
		return productThresoldService;
	}
	
	public CashRegisterService getCashRegisterService() {
		return cashRegisterService;
	}

	public void setCashRegisterService(CashRegisterService cashRegisterService) {
		this.cashRegisterService = cashRegisterService;
	}

	public void setProductThresoldService(ProductThresoldService productThresoldService) {
		this.productThresoldService = productThresoldService;
	}

	public PurchaseService getPurchaseService() {
		return purchaseService;
	}

	public void setPurchaseService(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}

	public BalancerService getBalancerService() {
		return balancerService;
	}

	public void setBalancerService(BalancerService balancerService) {
		this.balancerService = balancerService;
	}

	public List<String> getRecomendacionesPara(Product producto){
		List<String> productos = new ArrayList<String>();
		Map<String, Long> productosConCantidadDeRepetidos = getProductsAndRepetitions(producto);
		/* TODO: Establecer una cantidad minima de repetidos para hacer una recomendacion
		*/
		for (String prod : productosConCantidadDeRepetidos.keySet()){
			/*
			if (productosConCantidadDeRepetidos.get(prod) > 1){
				productos.add(prod);
			}
			*/
			productos.add(prod);
		}
		
		return productos;
	}

	private Map<String, Long> getProductsAndRepetitions(Product product){
		List<Cart> allCarts = this.purchaseService.getAllCarts();
		List<Product> prods = new ArrayList<Product>();
		for (Cart cart : allCarts ){
			if(this.cartService.isCartIncludingProduct(cart,product)){
				prods.addAll(cart.getItems().stream().
						filter(itemC -> 
						itemC.getProduct() != product).					
						map(itemC -> 
						itemC.getProduct()).collect(Collectors.toList()));
			}
		}
		
		Map<String, Long> counts =
				prods.stream().map(p -> p.getName()).
					collect(Collectors.groupingBy(e -> e, Collectors.counting()));
		return counts;
	}
	

	public List<CashRegister> getCajas() {
		return this.cashRegisterService.getRegisteredCashRegisters();
	}

	
	public CashRegister getCashRegister() {
		List<CashRegister> availableCashRegister = cashRegisterService.getAvailableCashRegisters();
		if (! availableCashRegister.isEmpty()){
			return availableCashRegister.get(0);
		}
		else {
			List<CashRegister> cajasHabilitadas = cashRegisterService.getRegisteredCashRegisters();
			return balancerService.sendCartToQueue(cajasHabilitadas);
		}
	}
	
	public List<Purchase> getPurchasesByUser(Usuario user){
		return purchaseService.getPurchasesByUser(user);
	}
	
	public CartService getCartService() {
		return cartService;
	}

	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ItemCartService getItemCartService() {
		return itemCartService;
	}

	public void setItemCartService(ItemCartService itemCartService) {
		this.itemCartService = itemCartService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	public ProductService getProductService(){
		return this.productService;
	}


	public CashRegister findCashRegisterByIndex(int index) {
		return this.cashRegisterService.findCashRegisterByIndex(index);
	}

	public Product createProduct(Brand brand, List<ProductCategory> categories,
			String name, Price price) {
		return this.productService.createProduct(brand, categories, name, price);
	}

	public Cart createCartForUser(Usuario user) {
		Cart cart = cartService.createCart(user);
		return cart;
	}

	public ItemCart createItemCart(Cart cart, Product prod) {
		ItemCart itemCart = itemCartService.createItemCart(prod,0, cart);
		return itemCart;
	}

	public Purchase createPurchase(Cart cart,PaymentType paymentType, CashRegister cashRegister) {
		Purchase purchase = purchaseService.createPurchase(cart, paymentType, cashRegister);
		return purchase;
	}

	public PaymentType createPaymentType(String name, String description) {
		PaymentType paymentType = paymentTypeService.create(name,description);
		return paymentType;
	}

	public void createCashRegisters(int index) {
		for (int i = 0 ; i < index ; i ++){
			this.cashRegisterService.createCashRegister();
		}
	}

	public void setPaymentTypeService(PaymentTypeService paymentTypeService) {
		this.paymentTypeService = paymentTypeService;
	}
	
	public PaymentTypeService getPaymentTypeService(){
		return this.paymentTypeService;
	}

	public List<Product> getProductsInPurchase(Purchase purchase) {
		List<ItemCart> items = purchase.getCart().getItems();
		return items.stream().map(i -> i.getProduct()).collect(Collectors.toList());
	}

	public Usuario createUser(String username, String password, String email) {
		return userService.createUser(username, password, email);
	}

	public ProductThresold createProductThreshold() {
		// TODO Auto-generated method stub
		return productThresoldService.createProductThreshold();
	}

	public UserProfile createUserProfile(Usuario user, Thresold pt) {
		return userProfileService.createUserProfile(user, pt);
	}

	public UserProfileService getUserProfileService() {
		return userProfileService;
	}

	public void setUserProfileService(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}

	public ShoppingList createShoppingListForUser(Usuario user) {
		return shoppingListService.createShoppingList(user);
	}

	public ItemShoppingList createItemShoppingList(Product product, Integer quantity, ShoppingList shoppingList) {
		return itemShoppingListService.createItemShoppingList(product,quantity,shoppingList);
	}

	public Cart createCartForShoppingList(ShoppingList shoppingList) {
		return cartService.createCartForShoppingList(shoppingList);
	}

	
}
