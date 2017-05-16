package edu.unq.desapp.groupA.backend.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import edu.unq.desapp.groupA.backend.model.Discount;
import edu.unq.desapp.groupA.backend.model.ItemCart;
import edu.unq.desapp.groupA.backend.model.PerProduct;
import edu.unq.desapp.groupA.backend.model.Price;
import edu.unq.desapp.groupA.backend.model.Product;

public class PerProductTest {

	private ItemCart aItemCart;
	private Product aProduct;
	private Price aPrice;
	private Double percentageToDiscountTest;
	private Double priceForProduct;
	private Date startingDate;
	private Date finishingDate;
	private Product otherProduct;

	@Before
	public void setup() {
		percentageToDiscountTest = 10.00;
		priceForProduct = 20.00;
		
		aPrice = new Price();
		aPrice.setPrice(priceForProduct);
		
		aProduct = new Product();
		aProduct.addPrice(aPrice);
		
		otherProduct = new Product();
		otherProduct.addPrice(aPrice);
		
		aItemCart = new ItemCart();
		aItemCart.setProduct(aProduct);
		
		startingDate = new DateTime().withDayOfMonth(1).withMonthOfYear(1).withYear(2017).toDate();
		finishingDate = new DateTime().withDayOfMonth(1).withMonthOfYear(2).withYear(2017).toDate();
	}
	
	@Test
	public void createPerProduct() {
		PerProduct newPerProduct = new PerProduct();
		
		assertEquals(PerProduct.class, newPerProduct.getClass());
	}

	@Test
	public void testGettersAndSettersPerProduct() {
		PerProduct newPerProduct = new PerProduct();
		
		newPerProduct.setPercentagePerProductToDiscount(percentageToDiscountTest);
		newPerProduct.setProductForDiscount(aProduct);
		newPerProduct.setStartingDate(startingDate);
		newPerProduct.setFinishingDate(finishingDate);
		
		assertEquals(aProduct, newPerProduct.getProductForDiscount());
		assertEquals(percentageToDiscountTest, newPerProduct.getPercentagePerProductToDiscount());
		assertEquals(startingDate, newPerProduct.getStartingDate());
		assertEquals(finishingDate, newPerProduct.getFinishingDate());
	}

	@Test
	public void test_GivenAnItemWithoutDiscount_WhenApplyingDiscountToValidItem_ThenTheItemsShouldHaveTheDiscountApplied() {
		PerProduct newPerProduct = new PerProduct();
		
		newPerProduct.setPercentagePerProductToDiscount(10.00);
		newPerProduct.setProductForDiscount(aProduct);
		newPerProduct.setStartingDate(startingDate);
		newPerProduct.setFinishingDate(finishingDate);
		
		aItemCart.setQuantity(1);
		
		newPerProduct.applyDiscountIfApplicable(aItemCart);
		assertTrue(aItemCart.hasAppliedDiscount());
		assertEquals(newPerProduct, aItemCart.getDiscount());
	}

	@Test
	public void test_GivenAnItemWithoutDiscount_WhenApplyingDiscountToInvalidItem_ThenTheItemsShouldNotHaveTheDiscountApplied() {
		PerProduct newPerProduct = new PerProduct();
		
		newPerProduct.setPercentagePerProductToDiscount(10.00);
		newPerProduct.setProductForDiscount(aProduct);
		newPerProduct.setStartingDate(startingDate);
		newPerProduct.setFinishingDate(finishingDate);
		
		aItemCart.setQuantity(1);
		aItemCart.setProduct(otherProduct);
		
		newPerProduct.applyDiscountIfApplicable(aItemCart);
		assertFalse(aItemCart.hasAppliedDiscount());
		assertFalse(newPerProduct.equals(aItemCart.getDiscount()));
	}

	@Test
	public void test_GivenAnItemWithDiscount_WhenApplyingDiscountToValidItem_ThenTheItemShouldHaveTheDiscountAppliedAtFirst() {
		PerProduct newPerProduct = new PerProduct();
		
		newPerProduct.setPercentagePerProductToDiscount(10.00);
		newPerProduct.setProductForDiscount(aProduct);
		newPerProduct.setStartingDate(startingDate);
		newPerProduct.setFinishingDate(finishingDate);
		
		Discount anotherDiscount = new PerProduct();
		
		aItemCart.setQuantity(1);
		aItemCart.setDiscount(anotherDiscount);
		
		newPerProduct.applyDiscountIfApplicable(aItemCart);
		assertTrue(aItemCart.hasAppliedDiscount());
		assertEquals(anotherDiscount, aItemCart.getDiscount());
	}

	@Test
	public void test_GivenAnItem_WhenVerifyingTheValidItem_ThenTheDiscountShouldReturnTrue() {
		PerProduct newPerProduct = new PerProduct();
		
		newPerProduct.setPercentagePerProductToDiscount(10.00);
		newPerProduct.setProductForDiscount(aProduct);
		newPerProduct.setStartingDate(startingDate);
		newPerProduct.setFinishingDate(finishingDate);
		
		aItemCart.setQuantity(1);
		
		assertTrue(newPerProduct.isItemValidForDiscount(aItemCart));
	}

	@Test
	public void test_GivenAnItem_WhenVerifyingTheInvalidItem_ThenTheDiscountShouldReturnFalse() {
		PerProduct newPerProduct = new PerProduct();
		
		newPerProduct.setPercentagePerProductToDiscount(10.00);
		newPerProduct.setProductForDiscount(aProduct);
		newPerProduct.setStartingDate(startingDate);
		newPerProduct.setFinishingDate(finishingDate);
		
		aItemCart.setProduct(otherProduct);
		aItemCart.setQuantity(1);
		
		assertFalse(newPerProduct.isItemValidForDiscount(aItemCart));
	}

	@Test
	public void test_GivenAnItemWithOneProductOfValueTwentyAndADiscountOfTenPercent_WhenCalculatingTheValueToDiscount_ThenTheDiscountShouldReturnTwo() {
		PerProduct newPerProduct = new PerProduct();
		
		newPerProduct.setPercentagePerProductToDiscount(10.00);
		newPerProduct.setProductForDiscount(aProduct);
		newPerProduct.setStartingDate(startingDate);
		newPerProduct.setFinishingDate(finishingDate);
		
		aItemCart.setQuantity(1);
		
		Double valueToDiscount = newPerProduct.valueToDiscount(aItemCart);
		// The price is 20.00 so 10% would be 2.00
		Double expected = 2.00;
		assertEquals(expected, valueToDiscount);
	}

	@Test
	public void test_GivenAnItemWithOneProductOfValueTwentyAndADiscountOfTwentyFivePercent_WhenCalculatingTheValueToDiscount_ThenTheDiscountShouldReturnFive() {
		PerProduct newPerProduct = new PerProduct();
		
		newPerProduct.setPercentagePerProductToDiscount(25.00);
		newPerProduct.setProductForDiscount(aProduct);
		newPerProduct.setStartingDate(startingDate);
		newPerProduct.setFinishingDate(finishingDate);
		
		aItemCart.setQuantity(1);
		
		Double valueToDiscount = newPerProduct.valueToDiscount(aItemCart);
		// The price is 20.00 so 25% would be 5.00
		Double expected = 5.00;
		assertEquals(expected, valueToDiscount);
	}

	@Test
	public void test_GivenAnItemWithThreeProductOfValueTwentyAndADiscountOfFivePercent_WhenCalculatingTheValueToDiscount_ThenTheDiscountShouldReturnThree() {
		PerProduct newPerProduct = new PerProduct();
		
		newPerProduct.setPercentagePerProductToDiscount(5.00);
		newPerProduct.setProductForDiscount(aProduct);
		newPerProduct.setStartingDate(startingDate);
		newPerProduct.setFinishingDate(finishingDate);
		
		aItemCart.setQuantity(3);
		
		Double valueToDiscount = newPerProduct.valueToDiscount(aItemCart);
		Double expected = 3.00;
		// The price is (20.00 + 20.00 + 20.00 = 60.00) so 5% would be 3.00
		assertEquals(expected, valueToDiscount);
	}

	@Test
	public void test_GivenAnItemWithSixteenProductOfValueTwentyAndADiscountOfFifteenPercent_WhenCalculatingTheValueToDiscount_ThenTheDiscountShouldReturnFortyEight() {
		PerProduct newPerProduct = new PerProduct();
		
		newPerProduct.setPercentagePerProductToDiscount(15.00);
		newPerProduct.setProductForDiscount(aProduct);
		newPerProduct.setStartingDate(startingDate);
		newPerProduct.setFinishingDate(finishingDate);
		
		aItemCart.setQuantity(16);
		
		Double valueToDiscount = newPerProduct.valueToDiscount(aItemCart);
		// The price is (20.00 * 16 = 320.00) so 15% would be 48.00
		Double expected = 48.00;
		assertEquals(expected, valueToDiscount);
	}

}
