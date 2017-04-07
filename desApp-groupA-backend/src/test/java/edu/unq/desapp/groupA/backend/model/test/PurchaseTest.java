package edu.unq.desapp.groupA.backend.model.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.PaymentType;
import edu.unq.desapp.groupA.backend.model.Purchase;

public class PurchaseTest {

	private Cart cart;
	private PaymentType aPaymentType;

	@Before
	public void setup() {
		cart = new Cart();
		aPaymentType = new PaymentType();
	}
	
	@Test
	public void createPurchase() {
		Purchase newPurchase = new Purchase();
		
		assertEquals(Purchase.class, newPurchase.getClass());
	}

	@Test
	public void testGettersAndSettersPurchase() {
		Purchase newPurchase = new Purchase();
		
		newPurchase.setCart(cart);
		newPurchase.setPayment(aPaymentType);
		
		assertEquals(cart, newPurchase.getCart());
		assertEquals(aPaymentType, newPurchase.getPayment());
	}

}
