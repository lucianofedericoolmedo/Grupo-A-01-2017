package edu.unq.desapp.groupA.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import edu.unq.desapp.groupA.backend.model.Brand;
import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.CartState;
import edu.unq.desapp.groupA.backend.model.CashRegister;
import edu.unq.desapp.groupA.backend.model.ItemCart;
import edu.unq.desapp.groupA.backend.model.PaymentType;
import edu.unq.desapp.groupA.backend.model.Pedido;
import edu.unq.desapp.groupA.backend.model.Price;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.ProductCategory;
import edu.unq.desapp.groupA.backend.model.Purchase;
import edu.unq.desapp.groupA.backend.model.Usuario;

public class ComprandoALoLocoService {

	private BalancerService balancerService;
	private CashRegisterService cashRegisterService;
	public CashRegisterService getCashRegisterService() {
		return cashRegisterService;
	}

	public void setCashRegisterService(CashRegisterService cashRegisterService) {
		this.cashRegisterService = cashRegisterService;
	}

	private CartService cartService;
	private UserService userService;
	private ItemCartService itemCartService;
	private ProductService productService;
	private PurchaseService purchaseService;
	private PaymentTypeService paymentTypeService;
	
	
	/*
	 * Add all the other services ... 
	 */
	
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
		Map<String, Long> productosConCantidadDeRepetidos = getAllRepeatedProducts(producto);
		/* TODO: Establecer una cantidad minima de repetidos
		*/
		for (String prod : productosConCantidadDeRepetidos.keySet()){
			if (productosConCantidadDeRepetidos.get(prod) > 1){
				productos.add(prod);
			}
		}
		
		return productos;
	}

	private Map<String, Long> getAllRepeatedProducts(Product product){
		List<Product> prods = this.purchaseService.findByProduct(product);
		
		Map<String, Long> counts =
				prods.stream().map(p -> p.getName()).collect(Collectors.groupingBy(e -> e, Collectors.counting()));
		return counts;
	}
	

	public List<CashRegister> getCajas() {
		return this.cashRegisterService.getCajasHabilitadas();
	}

	public void atenderPedido(Pedido pedido) {
		pedido.setCartState(CartState.PURCHASE);
		
	}
	
	public CashRegister getCashRegister() {
		List<CashRegister> availableCashRegister = cashRegisterService.obtenerCajasDisponibles();
		if (! availableCashRegister.isEmpty()){
			return cashRegisterService.getAvailableCashRegister(availableCashRegister);
		}
		else {
			List<CashRegister> cajasHabilitadas = cashRegisterService.getCajasHabilitadas();
			return balancerService.sendCartToQueue(cajasHabilitadas);
		}
	}
	
	

	public Cart realizarPedido(Usuario user, Cart cart) {
		return balancerService.registrarPedido(user,cart);
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
		return this.cashRegisterService.findCajasByIndex(index);
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
		ItemCart itemCart = itemCartService.createItemCart(prod, cart);
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
			this.cashRegisterService.createCaja();
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

	
}
