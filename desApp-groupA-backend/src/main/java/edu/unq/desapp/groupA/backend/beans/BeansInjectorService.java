package edu.unq.desapp.groupA.backend.beans;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.ItemCart;
import edu.unq.desapp.groupA.backend.model.ItemShoppingList;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.ShoppingList;
import edu.unq.desapp.groupA.backend.model.User;
import edu.unq.desapp.groupA.backend.service.CartService;
import edu.unq.desapp.groupA.backend.service.ItemCartService;
import edu.unq.desapp.groupA.backend.service.ItemShoppingListService;
import edu.unq.desapp.groupA.backend.service.ProductService;
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

    /**
     * En este método se deben inicializar todas las entidades básicas y
     * necesarias para comenzar.
     */
	
	@Transactional
	public Cart initializeItemsAndProducts(){
		Product product = ProductFactory.exampleNewElem("Rex");	
    	productService.save(product);
    	Product otherProduct = ProductFactory.exampleNewElem("Opera");	
    	productService.save(otherProduct);
    	User user = userService.createUser("fake", "fake123", "fake@gmail.com");
    	
    	/*
    	Cart cart = cartService.createCart(user);
    	itemCartService.createItemCart(product, 5, cart);
    	itemCartService.createItemCart(otherProduct, 5, cart);
    	*/
		
		ItemShoppingList itemShoppingList1 = new ItemShoppingList();
		itemShoppingList1.setProduct(product);
		itemShoppingList1.setQuantity(1);
		itemShoppingListService.save(itemShoppingList1);

		ItemShoppingList itemShoppingList2 = new ItemShoppingList();
		itemShoppingList2.setProduct(otherProduct);
		itemShoppingList2.setQuantity(1);
		itemShoppingListService.save(itemShoppingList2);

		
		ShoppingList aShoppingList = new ShoppingList();
		aShoppingList.setUser(user);
		aShoppingList.addItems(itemShoppingList1);
		aShoppingList.addItems(itemShoppingList2);
		
		shoppingListService.save(aShoppingList);
		Cart createdCart = cartService.createCartForShoppingList(aShoppingList);
		System.out.println("POST CART SAVED");
    	for (ItemCart item : createdCart.getItems()){
    		System.out.println(item.getProduct().getName());
    	}
    	
    	
    	return createdCart;
    	//itemCartService.save(itemCart);
    	//itemCartService.save(otherItemCart);
	}
	
	
	
    @PostConstruct
    @Transactional
    public void initialize() {
    	initializeItemsAndProducts();
    	
    }
    
}
