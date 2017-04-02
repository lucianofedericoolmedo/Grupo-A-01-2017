package edu.unq.desapp.groupA.backend.model.test;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.unq.desapp.groupA.backend.model.ItemPurchase;
import edu.unq.desapp.groupA.backend.model.PaymentType;
import edu.unq.desapp.groupA.backend.model.Purchase;

public class PurchaseTest {

	private List<ItemPurchase> items;
	private PaymentType aPaymentType;

	@Before
	public void setup() {
		items = new LinkedList<ItemPurchase>();
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
		
		newPurchase.setItems(items);
		newPurchase.setPayment(aPaymentType);
		
		assertEquals(items, newPurchase.getItems());
		assertEquals(aPaymentType, newPurchase.getPayment());
	}

}
