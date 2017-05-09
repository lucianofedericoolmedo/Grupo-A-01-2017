package edu.unq.desapp.groupA.backend.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import edu.unq.desapp.groupA.backend.model.Discount;
import edu.unq.desapp.groupA.backend.model.ItemCart;
import edu.unq.desapp.groupA.backend.model.PerProduct;
import edu.unq.desapp.groupA.backend.model.PerProductCategory;
import edu.unq.desapp.groupA.backend.model.Price;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.ProductCategory;

public class PerProductCategoryTest {

	private ProductCategory aProductCategory;
	private Double percentageToDiscountTest;
	private DateTime startingDate;
	private DateTime finishingDate;
	private Double priceForProduct;
	private Price aPrice;
	private Product aProduct;
	private ItemCart aItemCart;

	@Before
	public void setup() {
		percentageToDiscountTest = 10.00;
		priceForProduct = 20.00;
		
		aPrice = new Price();
		aPrice.setPrice(priceForProduct);
		
		aProductCategory = new ProductCategory();
		aProduct = new Product();
		//aProduct.setPrice(aPrice);
		aProduct.addCategory(aProductCategory);
		
		aItemCart = new ItemCart();
		aItemCart.setProduct(aProduct);
		
		startingDate = new DateTime().withDayOfMonth(1).withMonthOfYear(1).withYear(2017);
		finishingDate = new DateTime().withDayOfMonth(1).withMonthOfYear(2).withYear(2017);
	}
	
	@Test
	public void createPerProductCategory() {
		PerProductCategory newProductCategory = new PerProductCategory();
		
		assertEquals(PerProductCategory.class, newProductCategory.getClass());
	}

	@Test
	public void testGettersAndSettersPerProductCategory() {
		PerProductCategory newPerProductCategory = new PerProductCategory();
		
		newPerProductCategory.setPercentagePerProductToDiscount(percentageToDiscountTest);
		newPerProductCategory.setCategoryForDiscount(aProductCategory);
		newPerProductCategory.setStartingDate(startingDate);
		newPerProductCategory.setFinishingDate(finishingDate);
		
		assertEquals(aProductCategory, newPerProductCategory.getCategoryForDiscount());
		assertEquals(percentageToDiscountTest, newPerProductCategory.getPercentagePerProductToDiscount());
		assertEquals(startingDate, newPerProductCategory.getStartingDate());
		assertEquals(finishingDate, newPerProductCategory.getFinishingDate());
	}

	@Test
	public void test_GivenAnItemWithoutDiscount_WhenApplyingDiscountToValidItem_ThenTheItemsShouldHaveTheDiscountApplied() {
		PerProductCategory newPerProductCategory = new PerProductCategory();
		
		newPerProductCategory.setPercentagePerProductToDiscount(10.00);
		newPerProductCategory.setCategoryForDiscount(aProductCategory);
		newPerProductCategory.setStartingDate(startingDate);
		newPerProductCategory.setFinishingDate(finishingDate);
		
		aItemCart.setQuantity(1);
		
		newPerProductCategory.applyDiscountIfApplicable(aItemCart);
		assertTrue(aItemCart.hasAppliedDiscount());
		assertEquals(newPerProductCategory, aItemCart.getDiscount());
	}

	@Test
	public void test_GivenAnItemWithoutDiscount_WhenApplyingDiscountToInvalidItem_ThenTheItemsShouldNotHaveTheDiscountApplied() {
		PerProductCategory newPerProductCategory = new PerProductCategory();
		
		newPerProductCategory.setPercentagePerProductToDiscount(10.00);
		newPerProductCategory.setCategoryForDiscount(aProductCategory);
		newPerProductCategory.setStartingDate(startingDate);
		newPerProductCategory.setFinishingDate(finishingDate);
		
		Product anotherProduct = new Product();
		anotherProduct.addCategory(new ProductCategory());
		aItemCart.setProduct(anotherProduct);
		aItemCart.setQuantity(1);
		
		newPerProductCategory.applyDiscountIfApplicable(aItemCart);
		assertFalse(aItemCart.hasAppliedDiscount());
		assertFalse(newPerProductCategory.equals(aItemCart.getDiscount()));
	}

	@Test
	public void test_GivenAnItemWithDiscount_WhenApplyingDiscountToValidItem_ThenTheItemShouldHaveTheDiscountAppliedAtFirst() {
		PerProductCategory newPerProductCategory = new PerProductCategory();
		
		newPerProductCategory.setPercentagePerProductToDiscount(10.00);
		newPerProductCategory.setCategoryForDiscount(aProductCategory);
		newPerProductCategory.setStartingDate(startingDate);
		newPerProductCategory.setFinishingDate(finishingDate);
		
		Discount anotherDiscount = new PerProduct();
		
		aItemCart.setQuantity(1);
		aItemCart.setDiscount(anotherDiscount);
		
		newPerProductCategory.applyDiscountIfApplicable(aItemCart);
		assertTrue(aItemCart.hasAppliedDiscount());
		assertEquals(anotherDiscount, aItemCart.getDiscount());
	}
	
	@Test
	public void test_GivenAnItem_WhenVerifyingTheValidItem_ThenTheDiscountShouldReturnTrue() {
		PerProductCategory newPerProductCategory = new PerProductCategory();
		
		newPerProductCategory.setPercentagePerProductToDiscount(10.00);
		newPerProductCategory.setCategoryForDiscount(aProductCategory);
		newPerProductCategory.setStartingDate(startingDate);
		newPerProductCategory.setFinishingDate(finishingDate);
		
		aItemCart.setQuantity(1);
		
		assertTrue(newPerProductCategory.isItemValidForDiscount(aItemCart));
	}

	@Test
	public void test_GivenAnItem_WhenVerifyingTheInvalidItem_ThenTheDiscountShouldReturnFalse() {
		PerProductCategory newPerProductCategory = new PerProductCategory();
		
		newPerProductCategory.setPercentagePerProductToDiscount(10.00);
		newPerProductCategory.setCategoryForDiscount(aProductCategory);
		newPerProductCategory.setStartingDate(startingDate);
		newPerProductCategory.setFinishingDate(finishingDate);
		
		Product aProductWithOtherCategory = new Product();
		aProductWithOtherCategory.addCategory(new ProductCategory());
		aItemCart.setProduct(aProductWithOtherCategory);
		aItemCart.setQuantity(1);
		
		assertFalse(newPerProductCategory.isItemValidForDiscount(aItemCart));
	}
	
	@Test
	public void test_GivenAnItemWithOneProductOfValueTwentyAndADiscountOfTenPercent_WhenCalculatingTheValueToDiscount_ThenTheDiscountShouldReturnTwo() {
		PerProductCategory newPerProductCategory = new PerProductCategory();
		
		newPerProductCategory.setPercentagePerProductToDiscount(10.00);
		newPerProductCategory.setCategoryForDiscount(aProductCategory);
		newPerProductCategory.setStartingDate(startingDate);
		newPerProductCategory.setFinishingDate(finishingDate);
		
		aItemCart.setQuantity(1);
		
		Double valueToDiscount = newPerProductCategory.valueToDiscount(aItemCart);
		// The price is 20.00 so 10% would be 2.00
		Double expected = 2.00;
		assertEquals(expected, valueToDiscount);
	}

	@Test
	public void test_GivenAnItemWithOneProductOfValueTwentyAndADiscountOfTwentyFivePercent_WhenCalculatingTheValueToDiscount_ThenTheDiscountShouldReturnFive() {
		PerProductCategory newPerProductCategory = new PerProductCategory();
		
		newPerProductCategory.setPercentagePerProductToDiscount(25.00);
		newPerProductCategory.setCategoryForDiscount(aProductCategory);
		newPerProductCategory.setStartingDate(startingDate);
		newPerProductCategory.setFinishingDate(finishingDate);
		
		aItemCart.setQuantity(1);
		
		Double valueToDiscount = newPerProductCategory.valueToDiscount(aItemCart);
		// The price is 20.00 so 25% would be 5.00
		Double expected = 5.00;
		assertEquals(expected, valueToDiscount);
	}

	@Test
	public void test_GivenAnItemWithThreeProductOfValueTwentyAndADiscountOfFivePercent_WhenCalculatingTheValueToDiscount_ThenTheDiscountShouldReturnThree() {
		PerProductCategory newPerProductCategory = new PerProductCategory();
		
		newPerProductCategory.setPercentagePerProductToDiscount(5.00);
		newPerProductCategory.setCategoryForDiscount(aProductCategory);
		newPerProductCategory.setStartingDate(startingDate);
		newPerProductCategory.setFinishingDate(finishingDate);
		
		aItemCart.setQuantity(3);

		Double valueToDiscount = newPerProductCategory.valueToDiscount(aItemCart);
		// The price is (20.00 + 20.00 + 20.00 = 60.00) so 5% would be 3.00
		Double expected = 3.00;
		assertEquals(expected, valueToDiscount);
	}

	@Test
	public void test_GivenAnItemWithSixteenProductOfValueTwentyAndADiscountOfFifteenPercent_WhenCalculatingTheValueToDiscount_ThenTheDiscountShouldReturnFortyEight() {
		PerProductCategory newPerProductCategory = new PerProductCategory();
		
		newPerProductCategory.setPercentagePerProductToDiscount(15.00);
		newPerProductCategory.setCategoryForDiscount(aProductCategory);
		newPerProductCategory.setStartingDate(startingDate);
		newPerProductCategory.setFinishingDate(finishingDate);
		
		aItemCart.setQuantity(16);

		Double valueToDiscount = newPerProductCategory.valueToDiscount(aItemCart);
		// The price is (20.00 * 16 = 320.00) so 15% would be 48.00
		Double expected = 48.00;
		assertEquals(expected, valueToDiscount);
	}

}
