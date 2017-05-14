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
import edu.unq.desapp.groupA.backend.model.ProductCategory;
import edu.unq.desapp.groupA.backend.model.ProductCategoryThreshold;
import edu.unq.desapp.groupA.backend.model.Purchase;

public class ProductCategoryThresholdTest {

	private ProductCategoryThreshold productCategoryThresold;

	private Cart aCart;

	private LinkedList<Purchase> purchasesList;

	private ProductCategory categoryBebidas;
	private ProductCategory categoryAlimentos;
	
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
		productCategoryThresold = new ProductCategoryThreshold();
		productCategoryThresold.setEnabled(true);

		aCart = new Cart();
		
		purchasesList = new LinkedList<Purchase>();

		categoryBebidas = new ProductCategory();
		categoryAlimentos = new ProductCategory();

		productWithValueTen0 = new Product();
		productWithValueTen0.addPrice(new Price( 10.00));
		productWithValueTen0.addCategory(categoryAlimentos);
		
		productWithValueTen1 = new Product();
		productWithValueTen1.addPrice(new Price(10.00));
		productWithValueTen1.addCategory(categoryBebidas);
		
		productWithValueFifteen0 = new Product();
		productWithValueFifteen0.addPrice(new Price(15.00));
		productWithValueFifteen0.addCategory(categoryAlimentos);
		
		productWithValueFifteen1 = new Product();
		productWithValueFifteen1.addPrice(new Price( 15.00));
		productWithValueFifteen1.addCategory(categoryBebidas);
		
		productWithValueTwenty = new Product();
		productWithValueTwenty.addPrice(new Price( 20.00));
		productWithValueTwenty.addCategory(categoryAlimentos);
		
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
	public void test_ACartWithOneProductOfValueTenWithCategoryAlimentosAndAListOfPurchaseWithOneProductOfSameCategoryWithValueFifteen_WhenCalculatingIfSurpassesWithAFiftyPercent_ThenTheThresholdShouldReturnFalse() {
		Double percentageToSurpass = 50.00;
		productCategoryThresold.setPercentageToSurpass(percentageToSurpass);
		productCategoryThresold.setProductCategory(categoryAlimentos);
		
		aCart.addItems(itemCartWithValueTen0);
		itemCartWithValueTen0.setChecked(true);
		
		cart0.addItems(itemCartWithValueFifteen0);
		itemCartWithValueFifteen0.setChecked(true);
		
		purchase0.setCart(cart0);
		purchasesList.add(purchase0);
		
		Boolean isSurpassed = productCategoryThresold.isCartSurpassed(aCart, purchasesList);
		
		assertFalse(isSurpassed);
	}

	@Test
	public void test_ACartWithOneProductOfValueTenWithCategoryAlimentosAndAListOfPurchaseWithOneProductOfOtherCategoryWithValueFifteen_WhenCalculatingIfSurpassesWithAFiftyPercent_ThenTheThresholdShouldReturnTrue() {
		Double percentageToSurpass = 50.00;
		productCategoryThresold.setPercentageToSurpass(percentageToSurpass);
		productCategoryThresold.setProductCategory(categoryAlimentos);

		aCart.addItems(itemCartWithValueTen0);
		itemCartWithValueTen0.setChecked(true);

		cart0.addItems(itemCartWithValueFifteen1);
		itemCartWithValueFifteen1.setChecked(true);

		purchase0.setCart(cart0);
		purchasesList.add(purchase0);

		Boolean isSurpassed = productCategoryThresold.isCartSurpassed(aCart, purchasesList);

		assertTrue(isSurpassed);
	}

	@Test
	public void test_ACartWithTwoProductOfValueTenAndTwentyWithCategoryAlimentosAndAListOfPurchaseWithOneProductOfSameCategoryWithValueFifteen_WhenCalculatingIfSurpassesWithAFiftyPercent_ThenTheThresholdShouldReturnTrue() {
		Double percentageToSurpass = 50.00;
		productCategoryThresold.setPercentageToSurpass(percentageToSurpass);
		productCategoryThresold.setProductCategory(categoryAlimentos);

		aCart.addItems(itemCartWithValueTen0);
		itemCartWithValueTen0.setChecked(true);
		
		aCart.addItems(itemCartWithValueTwenty);
		itemCartWithValueTwenty.setChecked(true);

		cart0.addItems(itemCartWithValueFifteen0);
		itemCartWithValueFifteen0.setChecked(true);

		purchase0.setCart(cart0);
		purchasesList.add(purchase0);

		Boolean isSurpassed = productCategoryThresold.isCartSurpassed(aCart, purchasesList);

		// 10 + 20 > (15 + 50% = 22.5)
		assertTrue(isSurpassed);
	}

	@Test
	public void test_ACartWithTwoProductOfValueTenCheckedAndTwentyNotCheckedWithCategoryAlimentosAndAListOfPurchaseWithOneProductOfSameCategoryWithValueFifteen_WhenCalculatingIfSurpassesWithAFiftyPercent_ThenTheThresholdShouldReturnFalse() {
		Double percentageToSurpass = 50.00;
		productCategoryThresold.setPercentageToSurpass(percentageToSurpass);
		productCategoryThresold.setProductCategory(categoryAlimentos);

		aCart.addItems(itemCartWithValueTen0);
		itemCartWithValueTen0.setChecked(true);
		
		aCart.addItems(itemCartWithValueTwenty);
		itemCartWithValueTwenty.setChecked(false);

		cart0.addItems(itemCartWithValueFifteen0);
		itemCartWithValueFifteen0.setChecked(true);

		purchase0.setCart(cart0);
		purchasesList.add(purchase0);

		Boolean isSurpassed = productCategoryThresold.isCartSurpassed(aCart, purchasesList);

		// 10 < (15 + 50% = 22.5)
		assertFalse(isSurpassed);
	}

}
