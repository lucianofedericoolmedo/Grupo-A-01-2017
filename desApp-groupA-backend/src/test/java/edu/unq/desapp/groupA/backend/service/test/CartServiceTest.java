package edu.unq.desapp.groupA.backend.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.unq.desapp.groupA.backend.exceptions.ProductAlreadyInItemGroupException;
import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.ItemShoppingList;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.ShoppingList;
import edu.unq.desapp.groupA.backend.model.User;
import edu.unq.desapp.groupA.backend.repository.CartRepository;
import edu.unq.desapp.groupA.backend.service.CartService;

public class CartServiceTest {

	private CartService cartService;
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
		cartService = new CartService(new CartRepository(), (long) (0));
		
		aUser = new User();
		
		product0 = new Product();
		itemShoppingList0 = new ItemShoppingList();
		itemShoppingList0.setProduct(product0);
		itemShoppingList0.setQuantity(2);

		product1 = new Product();
		itemShoppingList1 = new ItemShoppingList();
		itemShoppingList1.setProduct(product1);
		itemShoppingList1.setQuantity(0);

		product2 = new Product();
		itemShoppingList2 = new ItemShoppingList();
		itemShoppingList2.setProduct(product2);
		itemShoppingList2.setQuantity(1);

		product3 = new Product();
		itemShoppingList3 = new ItemShoppingList();
		itemShoppingList3.setProduct(product3);
		itemShoppingList3.setQuantity(10);
		
		aShoppingList = new ShoppingList();
		aShoppingList.setUser(aUser);
		aShoppingList.addItems(itemShoppingList0);
		aShoppingList.addItems(itemShoppingList1);
		aShoppingList.addItems(itemShoppingList2);
		
	}

	@Test
	public void test_GivenAnUser_CreateNewEmptyCart() {
		Long newIdentifier = new Long(0);

		Cart newCart = cartService.createCart(aUser);

		assertTrue(newCart.getItems().isEmpty());
		assertEquals(newIdentifier, newCart.getIdentifier());
		assertEquals(aUser, newCart.getUser());
	}

	@Test
	public void test_GivenAShoppingListWithThreeProducts_WhenCreatingACartWithTheShoppingList_ThenTheCreatedCartShouldHaveTheThreePRoducts() {
		assertTrue(aShoppingList.containsProduct(product0));
		assertTrue(aShoppingList.containsProduct(product1));
		assertTrue(aShoppingList.containsProduct(product2));
		assertFalse(aShoppingList.containsProduct(product3));
		
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
