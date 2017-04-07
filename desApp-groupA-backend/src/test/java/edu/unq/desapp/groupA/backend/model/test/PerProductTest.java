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
import edu.unq.desapp.groupA.backend.model.Price;
import edu.unq.desapp.groupA.backend.model.Product;

public class PerProductTest {

	private ItemCart aItemCart;
	private Product aProduct;
	private Price aPrice;
	private Double percentageToDiscountTest;
	private Double priceForProduct;
	private DateTime startingDate;
	private DateTime finishingDate;
	private Product otherProduct;

	@Before
	public void setup() {
		percentageToDiscountTest = 10.00;
		priceForProduct = 20.00;
		
		aPrice = new Price();
		aPrice.setPrice(priceForProduct);
		
		aProduct = new Product();
		aProduct.setPrice(aPrice);
		
		otherProduct = new Product();
		otherProduct.setPrice(aPrice);
		
		aItemCart = new ItemCart();
		aItemCart.setProduct(aProduct);
		
		startingDate = new DateTime().withDayOfMonth(1).withMonthOfYear(1).withYear(2017);
		finishingDate = new DateTime().withDayOfMonth(1).withMonthOfYear(2).withYear(2017);
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
	public void testCanApplyDiscountToValidItemCartWithoutDiscount() {
		PerProduct newPerProduct = new PerProduct();
		
		newPerProduct.setPercentagePerProductToDiscount(10.00);
		newPerProduct.setProductForDiscount(aProduct);
		newPerProduct.setStartingDate(startingDate);
		newPerProduct.setFinishingDate(finishingDate);
		
		aItemCart.setQuantity(1);
		
		newPerProduct.applyDiscountIfApplicable(aItemCart);
		assertEquals(newPerProduct, aItemCart.getDiscount());
	}

	@Test
	public void testCanNotApplyDiscountToInvalidItemCartWithoutDiscount() {
		PerProduct newPerProduct = new PerProduct();
		
		newPerProduct.setPercentagePerProductToDiscount(10.00);
		newPerProduct.setProductForDiscount(aProduct);
		newPerProduct.setStartingDate(startingDate);
		newPerProduct.setFinishingDate(finishingDate);
		
		aItemCart.setQuantity(1);
		aItemCart.setProduct(otherProduct);
		
		newPerProduct.applyDiscountIfApplicable(aItemCart);
		assertFalse(newPerProduct.equals(aItemCart.getDiscount()));
	}

	@Test
	public void testCanNotApplyDiscountToValidItemCartWithDiscount() {
		PerProduct newPerProduct = new PerProduct();
		
		newPerProduct.setPercentagePerProductToDiscount(10.00);
		newPerProduct.setProductForDiscount(aProduct);
		newPerProduct.setStartingDate(startingDate);
		newPerProduct.setFinishingDate(finishingDate);
		
		Discount anotherDiscount = new PerProduct();
		
		aItemCart.setQuantity(1);
		aItemCart.setDiscount(anotherDiscount);
		
		newPerProduct.applyDiscountIfApplicable(aItemCart);
		assertEquals(anotherDiscount, aItemCart.getDiscount());
	}

	@Test
	public void testValidItemCartWithoutDiscount() {
		PerProduct newPerProduct = new PerProduct();
		
		newPerProduct.setPercentagePerProductToDiscount(10.00);
		newPerProduct.setProductForDiscount(aProduct);
		newPerProduct.setStartingDate(startingDate);
		newPerProduct.setFinishingDate(finishingDate);
		
		aItemCart.setQuantity(1);
		
		assertTrue(newPerProduct.isItemValidForDiscount(aItemCart));
	}

	@Test
	public void testInvalidItemCartWithoutDiscount() {
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
	public void testValueToDiscountForQuantityOneOfProductAndTenPercentDiscount() {
		PerProduct newPerProduct = new PerProduct();
		
		newPerProduct.setPercentagePerProductToDiscount(10.00);
		newPerProduct.setProductForDiscount(aProduct);
		newPerProduct.setStartingDate(startingDate);
		newPerProduct.setFinishingDate(finishingDate);
		
		aItemCart.setQuantity(1);
		
		// The price is 20.00 so 10% would be 2.00
		assertEquals((Double) 2.00, newPerProduct.valueToDiscount(aItemCart));
	}

	@Test
	public void testValueToDiscountForQuantityOneOfProductAndTwentyFivePercentDiscount() {
		PerProduct newPerProduct = new PerProduct();
		
		newPerProduct.setPercentagePerProductToDiscount(25.00);
		newPerProduct.setProductForDiscount(aProduct);
		newPerProduct.setStartingDate(startingDate);
		newPerProduct.setFinishingDate(finishingDate);
		
		aItemCart.setQuantity(1);
		
		// The price is 20.00 so 25% would be 5.00
		assertEquals((Double) 5.00, newPerProduct.valueToDiscount(aItemCart));
	}

	@Test
	public void testValueToDiscountForQuantityThreeOfProductAndFivePercentDiscount() {
		PerProduct newPerProduct = new PerProduct();
		
		newPerProduct.setPercentagePerProductToDiscount(5.00);
		newPerProduct.setProductForDiscount(aProduct);
		newPerProduct.setStartingDate(startingDate);
		newPerProduct.setFinishingDate(finishingDate);
		
		aItemCart.setQuantity(3);
		
		// The price is (20.00 + 20.00 + 20.00 = 60.00) so 5% would be 3.00
		assertEquals((Double) 3.00, newPerProduct.valueToDiscount(aItemCart));
	}

	@Test
	public void testValueToDiscountForQuantitySixteenOfProductAndFifteenPercentDiscount() {
		PerProduct newPerProduct = new PerProduct();
		
		newPerProduct.setPercentagePerProductToDiscount(15.00);
		newPerProduct.setProductForDiscount(aProduct);
		newPerProduct.setStartingDate(startingDate);
		newPerProduct.setFinishingDate(finishingDate);
		
		aItemCart.setQuantity(16);
		
		// The price is (20.00 * 16 = 320.00) so 15% would be 48.00
		assertEquals((Double) 48.00, newPerProduct.valueToDiscount(aItemCart));
	}

}
