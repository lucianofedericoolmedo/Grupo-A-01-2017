package edu.unq.desapp.groupA.backend.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.unq.desapp.groupA.backend.model.ItemCart;
import edu.unq.desapp.groupA.backend.model.PerProduct;
import edu.unq.desapp.groupA.backend.model.Price;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.ProductCategory;

public class ItemCartTest {

	private Product aProduct;
	private ProductCategory aProductCategory;
	private Integer quantityToTest;
	private Boolean checkedToTest;
	private PerProduct perProductDiscountToTest;

	@Before
	public void setup() {
		aProductCategory = new ProductCategory();
		aProduct = new Product();
		aProduct.addCategory(aProductCategory);
		
		quantityToTest = 10;
		checkedToTest = true;
		perProductDiscountToTest = new PerProduct();
	}
	
	@Test
	public void createItemCart() {
		ItemCart newItemCart = new ItemCart();
		
		assertEquals(ItemCart.class, newItemCart.getClass());
	}

	@Test
	public void testGettersAndSettersItemCart() {
		ItemCart newItemCart = new ItemCart();
		
		newItemCart.setProduct(aProduct);
		newItemCart.setQuantity(quantityToTest);
		newItemCart.setChecked(checkedToTest);
		newItemCart.setDiscount(perProductDiscountToTest);
		
		assertEquals(aProduct, newItemCart.getProduct());
		assertEquals(quantityToTest, newItemCart.getQuantity());
		assertEquals(checkedToTest, newItemCart.getChecked());
		assertEquals(perProductDiscountToTest, newItemCart.getDiscount());
	}
	
	@Test
	public void test_GivenAnItemCartWithoutDiscount_WhenAskingIfAppliedDiscount_TheShouldReturnFalse() {
		ItemCart anItemCart = new ItemCart();
		
		Boolean hasAppliedDiscount = anItemCart.hasAppliedDiscount();
		
		assertFalse(hasAppliedDiscount);
	}

	@Test
	public void test_GivenAnItemCartWithDiscount_WhenAskingIfAppliedDiscount_TheShouldReturnTrue() {
		ItemCart anItemCart = new ItemCart();
		anItemCart.setDiscount(perProductDiscountToTest);
		
		Boolean hasAppliedDiscount = anItemCart.hasAppliedDiscount();
		
		assertTrue(hasAppliedDiscount);
	}

	@Test
	public void test_GivenAnItemCartWithAProductWithASpecificCategory_WhenAskingIfIsThatCategory_ThenShouldReturnTrue() {
		ItemCart anItemCart = new ItemCart();
		anItemCart.setProduct(aProduct);
		
		Boolean isCategory = anItemCart.isCategory(aProductCategory);
		
		assertTrue(isCategory);
	}

	@Test
	public void test_GivenAnItemCartWithAProductWithASpecificCategory_WhenAskingIfIsOtherCategory_ThenShouldReturnTrue() {
		ItemCart anItemCart = new ItemCart();
		anItemCart.setProduct(aProduct);
		
		Boolean isCategory = anItemCart.isCategory(new ProductCategory());
		
		assertFalse(isCategory);
	}

	@Test
	public void test_GivenAnItemCartWithAProduct_WhenAskingIfIsThatProduct_ThenShouldReturnTrue() {
		ItemCart anItemCart = new ItemCart();
		anItemCart.setProduct(aProduct);
		
		Boolean isProduct = anItemCart.isProduct(aProduct);
		
		assertTrue(isProduct);
	}

	@Test
	public void test_GivenAnItemCartWithAProduct_WhenAskingIfIsOtherProduct_ThenShouldReturnFalse() {
		ItemCart anItemCart = new ItemCart();
		anItemCart.setProduct(aProduct);
		
		Boolean isProduct = anItemCart.isProduct(new Product());
		
		assertFalse(isProduct);
	}

	@Test
	public void test_GivenAnItemCartWithAProductOfValueFifteenAndQuantityOne_WhenCalculatingTotalValue_ThenShouldReturnFifteen() {
		ItemCart anItemCart = new ItemCart();
		aProduct.addPrice(new Price(15.00));
		anItemCart.setProduct(aProduct);
		anItemCart.setQuantity(1);
		
		Double expectedValue = 15.00;
		Double totalValue = anItemCart.totalValue();
		
		assertEquals(expectedValue, totalValue);
	}

	@Test
	public void test_GivenAnItemCartWithAProductOfValueTenAndQuantityThree_WhenCalculatingTotalValue_ThenShouldReturnThirty() {
		ItemCart anItemCart = new ItemCart();
		aProduct.addPrice(new Price(10.00));
		anItemCart.setProduct(aProduct);
		anItemCart.setQuantity(3);
		
		Double expectedValue = 30.00;
		Double totalValue = anItemCart.totalValue();
		
		assertEquals(expectedValue, totalValue);
	}

}
