package edu.unq.desapp.groupA.backend.model.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.unq.desapp.groupA.backend.model.ItemCart;
import edu.unq.desapp.groupA.backend.model.Product;

public class ItemPurchaseTest {

	private Product aProduct;
	private Integer quantityToTest;

	@Before
	public void setup() {
		aProduct = new Product();
		quantityToTest = 10;
	}
	
	@Test
	public void createItemPurchase() {
		ItemCart newItemPurchase = new ItemCart();
		
		assertEquals(ItemCart.class, newItemPurchase.getClass());
	}

	@Test
	public void testGettersAndSettersItemPurchase() {
		ItemCart newItemPurchase = new ItemCart();
		
		newItemPurchase.setProduct(aProduct);
		newItemPurchase.setQuantity(quantityToTest);
		
		assertEquals(aProduct, newItemPurchase.getProduct());
		assertEquals(quantityToTest, newItemPurchase.getQuantity());
	}

}
