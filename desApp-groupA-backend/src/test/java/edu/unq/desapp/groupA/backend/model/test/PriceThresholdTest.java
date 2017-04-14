package edu.unq.desapp.groupA.backend.model.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.ItemCart;
import edu.unq.desapp.groupA.backend.model.Price;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.Purchase;
import edu.unq.desapp.groupA.backend.model.PurchasePriceThreshold;

public class PriceThresholdTest {

	private PurchasePriceThreshold purchasePriceThreshold;

	private Cart aCart;

	private LinkedList<Purchase> purchasesList;

	private Product productWithValueTen0;
	private Product productWithValueTen1;
	private Product productWithValueFifteen0;
	private Product productWithValueFifteen1;
	private Product productWithValueTwenty;
	
	private ItemCart itemCartWithValueTen0;
	private ItemCart itemCartWithValueTen1;
	private ItemCart itemCartWithValueFifteen0;
	private ItemCart itemCartWithValueFifteen1;
	private ItemCart itemCartWithValueTwenty;
	
	private Cart cart0;
	private Cart cart1;
	private Cart cart2;
	private Cart cart3;
	private Cart cart4;
	
	private Purchase purchase0;
	private Purchase purchase1;
	private Purchase purchase2;
	private Purchase purchase3;
	private Purchase purchase4;

	@Before
	public void setup() {
		purchasePriceThreshold = new PurchasePriceThreshold();
		purchasePriceThreshold.setEnabled(true);

		aCart = new Cart();
		
		purchasesList = new LinkedList<Purchase>();
		
		productWithValueTen0 = new Product();
		productWithValueTen0.setPrice(new Price(productWithValueTen0, 10.00));
		productWithValueTen1 = new Product();
		productWithValueTen1.setPrice(new Price(productWithValueTen1, 10.00));
		productWithValueFifteen0 = new Product();
		productWithValueFifteen0.setPrice(new Price(productWithValueFifteen0, 15.00));
		productWithValueFifteen1 = new Product();
		productWithValueFifteen1.setPrice(new Price(productWithValueFifteen1, 15.00));
		productWithValueTwenty = new Product();
		productWithValueTwenty.setPrice(new Price(productWithValueTwenty, 20.00));
		
		itemCartWithValueTen0 = new ItemCart(productWithValueTen0, 1);
		itemCartWithValueTen1 = new ItemCart(productWithValueTen1, 1);
		itemCartWithValueFifteen0 = new ItemCart(productWithValueFifteen0, 1);
		itemCartWithValueFifteen1 = new ItemCart(productWithValueFifteen1, 1);
		itemCartWithValueTwenty = new ItemCart(productWithValueTwenty, 1);
		
		cart0 = new Cart();
		cart1 = new Cart();
		cart2 = new Cart();
		cart3 = new Cart();
		cart4 = new Cart();
		
		purchase0 = new Purchase();
		purchase1 = new Purchase();
		purchase2 = new Purchase();
		purchase3 = new Purchase();
		purchase4 = new Purchase();
	}
	
	@Test
	public void test_GivenAPurchaseWithOneProductOfValueTenWithQuantityOneCheckedAndAListOfPurchaseOfOneElementWithValueTenChecked_WhenAskingIfIsSurpassed_ThenTheThresholdShouldReturnFalse() {
		Double percentageToSurpass = 10.00;
		purchasePriceThreshold.setPercentageToSurpass(percentageToSurpass);
		
		aCart.addItems(itemCartWithValueTen0);
		itemCartWithValueTen0.setChecked(true);
		
		cart0.addItems(itemCartWithValueTen1);
		itemCartWithValueTen1.setChecked(true);
		
		purchase0.setCart(cart0);
		purchasesList.add(purchase0);
		
		Boolean isSurpassed = purchasePriceThreshold.isCartSurpassed(aCart, purchasesList);
		
		assertFalse(isSurpassed);
	}

	@Test
	public void test_GivenAPurchaseWithOneProductOfValueFifteenWithQuantityOneCheckedAndAListOfPurchaseOfOneElementWithValueTenChecked_WhenAskingIfIsSurpassed_ThenTheThresholdShouldReturnTrue() {
		Double percentageToSurpass = 10.00;
		purchasePriceThreshold.setPercentageToSurpass(percentageToSurpass);

		aCart.addItems(itemCartWithValueFifteen0);
		itemCartWithValueFifteen0.setChecked(true);

		cart0.addItems(itemCartWithValueTen0);
		itemCartWithValueTen0.setChecked(true);

		purchase0.setCart(cart0);
		purchasesList.add(purchase0);

		Boolean isSurpassed = purchasePriceThreshold.isCartSurpassed(aCart, purchasesList);

		assertTrue(isSurpassed);
	}

	@Test
	public void test_GivenAPurchaseWithAProductOfValueTenCheckedWithQuantityOneAndAProductOfValueFifteenNotCheckWithQuantityOneAndAListOfPurchaseOfOneElementWithValueTenChecked_WhenAskingIfIsSurpassed_ThenTheThresholdShouldReturnFalse() {
		Double percentageToSurpass = 10.00;
		purchasePriceThreshold.setPercentageToSurpass(percentageToSurpass);

		aCart.addItems(itemCartWithValueFifteen0);
		itemCartWithValueFifteen0.setChecked(false);
		
		aCart.addItems(itemCartWithValueTen0);
		itemCartWithValueTen0.setChecked(true);

		cart0.addItems(itemCartWithValueTen0);
		itemCartWithValueTen0.setChecked(true);

		purchase0.setCart(cart0);
		purchasesList.add(purchase0);

		Boolean isSurpassed = purchasePriceThreshold.isCartSurpassed(aCart, purchasesList);

		assertFalse(isSurpassed);
	}

	@Test
	public void test_GivenAPurchaseWithAProductOfValueTwentyCheckedWithQuantityOneAndAndAListOfPurchaseOfThreeElementWithValuesTenFifteenTwentyChecked_WhenAskingIfIsSurpassed_ThenTheThresholdShouldReturnFalse() {
		Double percentageToSurpass = 10.00;
		purchasePriceThreshold.setPercentageToSurpass(percentageToSurpass);

		aCart.addItems(itemCartWithValueTwenty);
		itemCartWithValueTwenty.setChecked(true);
		
		cart0.addItems(itemCartWithValueTen0);
		itemCartWithValueTen0.setChecked(true);

		cart1.addItems(itemCartWithValueFifteen0);
		itemCartWithValueFifteen0.setChecked(true);

		cart2.addItems(itemCartWithValueTwenty);
		itemCartWithValueTwenty.setChecked(true);

		purchase0.setCart(cart0);
		purchase1.setCart(cart1);
		purchase2.setCart(cart2);
		
		purchasesList.add(purchase0);
		purchasesList.add(purchase1);
		purchasesList.add(purchase2);

		// Total historial value = 10 + 15 + 20 = 45
		// Average historical value = 45 / 3 (purchases) = 15
		// 15 plus the 10 percent = 16.5
		// Current cart value is 20
		
		Boolean isSurpassed = purchasePriceThreshold.isCartSurpassed(aCart, purchasesList);

		assertTrue(isSurpassed);
	}

	@Test
	public void test_GivenAPurchaseWithAProductOfValueTwentyCheckedWithQuantityOneAndAndAListOfPurchaseOfThreeElementWithValuesTenFifteenTwentyChecked_WhenAskingIfIsSurpassedWithAFiftyPercent_ThenTheThresholdShouldReturnFalse() {
		Double percentageToSurpass = 50.00;
		purchasePriceThreshold.setPercentageToSurpass(percentageToSurpass);

		aCart.addItems(itemCartWithValueTwenty);
		itemCartWithValueTwenty.setChecked(true);
		
		cart0.addItems(itemCartWithValueTen0);
		itemCartWithValueTen0.setChecked(true);

		cart1.addItems(itemCartWithValueFifteen0);
		itemCartWithValueFifteen0.setChecked(true);

		cart2.addItems(itemCartWithValueTwenty);
		itemCartWithValueTwenty.setChecked(true);

		purchase0.setCart(cart0);
		purchase1.setCart(cart1);
		purchase2.setCart(cart2);
		
		purchasesList.add(purchase0);
		purchasesList.add(purchase1);
		purchasesList.add(purchase2);

		// Total historial value = 10 + 15 + 20 = 45
		// Average historical value = 45 / 3 (purchases) = 15
		// 15 plus the 50 percent = 22.50
		// Current cart value is 20
		
		Boolean isSurpassed = purchasePriceThreshold.isCartSurpassed(aCart, purchasesList);

		assertFalse(isSurpassed);
	}

}
