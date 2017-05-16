package edu.unq.desapp.groupA.backend.beans;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.CashRegister;
import edu.unq.desapp.groupA.backend.model.ItemCart;
import edu.unq.desapp.groupA.backend.model.ItemShoppingList;
import edu.unq.desapp.groupA.backend.model.PaymentType;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.ShoppingList;
import edu.unq.desapp.groupA.backend.model.User;
import edu.unq.desapp.groupA.backend.service.CartService;
import edu.unq.desapp.groupA.backend.service.CashRegisterService;
import edu.unq.desapp.groupA.backend.service.ItemCartService;
import edu.unq.desapp.groupA.backend.service.ItemShoppingListService;
import edu.unq.desapp.groupA.backend.service.PaymentTypeService;
import edu.unq.desapp.groupA.backend.service.ProductService;
import edu.unq.desapp.groupA.backend.service.PurchaseService;
import edu.unq.desapp.groupA.backend.service.ShoppingListService;
import edu.unq.desapp.groupA.backend.service.UserService;
import edu.unq.desapp.groupA.backend.service.provider.ProductFactory;

@Service
public class BeansInjectorService {
	
	@Autowired
    private ProductService productService;
	
	@Autowired
    private ItemCartService itemCartService;
	
	@Autowired
	private ShoppingListService shoppingListService;
	
	@Autowired
	private ItemShoppingListService itemShoppingListService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CashRegisterService cashRegisterService;
	
	@Autowired
	private PurchaseService purchaseService; 
	
	@Autowired
	private PaymentTypeService paymentTypeService;

    /**
     * En este método se deben inicializar todas las entidades básicas y
     * necesarias para comenzar.
     */
	
	@Transactional
	public Cart initializeItemsAndProducts(){
		Product product = ProductFactory.exampleNewElem("Rex");	
    	product = productService.save(product);
    	Product otherProduct = ProductFactory.exampleNewElem("Opera");	
    	otherProduct = productService.save(otherProduct);
    	User user = userService.createUser("fake", "fake123", "fake@gmail.com");
    	
    	/*
    	Cart cart = cartService.createCart(user);
    	itemCartService.createItemCart(product, 5, cart);
    	itemCartService.createItemCart(otherProduct, 5, cart);
    	*/
    	
    	

		ShoppingList aShoppingList = new ShoppingList();
		aShoppingList.setUser(user);
		shoppingListService.save(aShoppingList);
    	
		System.out.println(product);
		System.out.println(otherProduct);

		ItemShoppingList itemShoppingList1 = new ItemShoppingList();
		itemShoppingList1.setProduct(product);
		itemShoppingList1.setQuantity(1);
//		itemShoppingListService.save(itemShoppingList1);

		ItemShoppingList itemShoppingList2 = new ItemShoppingList();
		itemShoppingList2.setProduct(otherProduct);
		itemShoppingList2.setQuantity(1);
//		itemShoppingListService.save(itemShoppingList2);

		aShoppingList = shoppingListService.createItemForShoppingList(aShoppingList.getId(), itemShoppingList1);
		aShoppingList = shoppingListService.createItemForShoppingList(aShoppingList.getId(), itemShoppingList2);
		
		Cart createdCart = cartService.createCartForShoppingList(aShoppingList);
		System.out.println("POST CART SAVED");
    	for (ItemCart item : createdCart.getItems()){
//    		System.out.println(item.getProduct().getName());
    	}
    	
    	System.out.println("-------- item shopping list with id 1 -------");
    	System.out.println(itemShoppingListService.find(new Long(1)).getProduct());
    	System.out.println(itemShoppingListService.find(new Long(1)).getQuantity());

    	System.out.println("-------- item shopping list with id 2 -------");
    	System.out.println(itemShoppingListService.find(new Long(2)).getProduct());
    	System.out.println(itemShoppingListService.find(new Long(2)).getQuantity());

    	
    	return createdCart;
    	//itemCartService.save(itemCart);
    	//itemCartService.save(otherItemCart);
	}
	
	
	
    @PostConstruct
    @Transactional
    public void initialize() {
    	Cart cart = initializeItemsAndProducts();  	
    	
    	CashRegister cashRegister = cashRegisterService.createCashRegister();
    	
    	System.out.println("----------------");
    	System.out.println(cashRegister.getAvailable());
    	System.out.println("----------------");
    	
    	//cashRegister = cashRegisterService.getCashRegister();
    	cashRegister.requirePurchase(cart);
    	PaymentType paymentType = paymentTypeService.create("Credit Card", "Mastercard");
    	
    	purchaseService.createPurchase(cart,paymentType,cashRegister);	
    	
    	
    }
    
}
