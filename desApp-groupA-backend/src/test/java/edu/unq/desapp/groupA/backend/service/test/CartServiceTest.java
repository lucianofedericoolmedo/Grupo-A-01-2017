package edu.unq.desapp.groupA.backend.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.unq.desapp.groupA.backend.exceptions.ProductAlreadyInItemGroupException;
import edu.unq.desapp.groupA.backend.model.Cart;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "test")
@ContextConfiguration({ "/META-INF/spring-persistence-context.xml", "/META-INF/spring-services-context.xml" })
public class CartServiceTest {

	@Autowired
	private CartService cartService;
	@Autowired
	private ItemCartService itemCartService;
	@Autowired
	private ShoppingListService shoppingListService;
	@Autowired
	private ItemShoppingListService itemShoppingListService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;

	private User aUser;
	private Product product0;
	private ItemShoppingList itemShoppingList0;
	private Product product1;
	private ItemShoppingList itemShoppingList1;
	private Product product2;
	private ItemShoppingList itemShoppingList2;
	private Product product3;
	private ItemShoppingList itemShoppingList3;
	private ShoppingList aShoppingList;

	@Before
	public void setup() {
		//itemCartService = new ItemCartService(new ItemCartRepository());

		//cartService = new CartService(new CartRepository(), (long) (0));
		//cartService.setItemCartService(itemCartService);

		aUser = new User();
		userService.save(aUser);
		
		product0 = new Product();
		productService.save(product0);
		itemShoppingList0 = new ItemShoppingList();
		itemShoppingList0.setProduct(product0);
		itemShoppingList0.setQuantity(2);
		itemShoppingListService.save(itemShoppingList0);

		product1 = new Product();
		productService.save(product1);
		itemShoppingList1 = new ItemShoppingList();
		itemShoppingList1.setProduct(product1);
		itemShoppingList1.setQuantity(0);
		itemShoppingListService.save(itemShoppingList1);

		product2 = new Product();
		productService.save(product2);
		itemShoppingList2 = new ItemShoppingList();
		itemShoppingList2.setProduct(product2);
		itemShoppingList2.setQuantity(1);
		itemShoppingListService.save(itemShoppingList2);

		product3 = new Product();
		productService.save(product3);
		itemShoppingList3 = new ItemShoppingList();
		itemShoppingList3.setProduct(product3);
		itemShoppingList3.setQuantity(10);
		itemShoppingListService.save(itemShoppingList3);
		
		aShoppingList = new ShoppingList();
		aShoppingList.setUser(aUser);
		aShoppingList.addItems(itemShoppingList0);
		aShoppingList.addItems(itemShoppingList1);
		aShoppingList.addItems(itemShoppingList2);
		
		//deleteItemsInCartDatabase();
	}
	
	public void deleteItemsInCartDatabase() {
		System.out.println("Executed delete");
		for (Cart cart : cartService.findAll()) {
			System.out.println("Deleted " + cart.getId());
			cartService.delete(cart.getId());
		}
	}

	@Test
	public void test_GivenAnUser_CreateNewEmptyCart() {
//		Long newIdentifier = new Long(1);

		Cart newCart = cartService.createCart(aUser);

		assertTrue(newCart.getItems().isEmpty());
//		assertEquals(newIdentifier, newCart.getId());
		assertEquals(aUser, newCart.getUser());
	}

	@Test
	public void test_GivenAShoppingListWithThreeProducts_WhenCreatingACartWithTheShoppingList_ThenTheCreatedCartShouldHaveTheThreePRoducts() {
		
		assertTrue(aShoppingList.containsProduct(product0));
		assertTrue(aShoppingList.containsProduct(product1));
		assertTrue(aShoppingList.containsProduct(product2));
		assertFalse(aShoppingList.containsProduct(product3));
		
		shoppingListService.save(aShoppingList);
		Cart createdCart = cartService.createCartForShoppingList(aShoppingList);

		assertTrue(createdCart.containsProduct(product0));
		assertTrue(createdCart.containsProduct(product1));
		assertTrue(createdCart.containsProduct(product2));
		assertFalse(createdCart.containsProduct(product3));
		assertEquals(aShoppingList, createdCart.getUsedShoppingList());
	}
	
	@Test
	public void test_GivenAnEmptyCart_WhenAddingAProduct_ThenTheCartShouldHaveAnItemContainingThatProduct() {
		Cart aCart = cartService.createCart(aUser);
		
		assertFalse(aCart.containsProduct(product0));
		
		cartService.addProductInCartIfMissing(aCart, product0);
		
		assertTrue(aCart.containsProduct(product0));
	}

	@Test (expected = ProductAlreadyInItemGroupException.class)
	public void test_GivenACartWthAProducto_WhenAddingTheSameProduct_ThenTheCartShouldRaiseAnException() {
		Cart aCart = cartService.createCart(aUser);

		cartService.addProductInCartIfMissing(aCart, product0);
		assertTrue(aCart.containsProduct(product0));
		cartService.addProductInCartIfMissing(aCart, product0);
	}

}
