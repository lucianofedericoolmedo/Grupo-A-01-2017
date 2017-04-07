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
import edu.unq.desapp.groupA.backend.model.PerProductQuantity;
import edu.unq.desapp.groupA.backend.model.Price;
import edu.unq.desapp.groupA.backend.model.Product;

public class PerProductQuantityTest {

	private Product aProduct;
	private Double percentageToDiscountTest;
	private Integer quantityToApply;
	private DateTime startingDate;
	private DateTime finishingDate;
	private Price aPrice;
	private Double priceForProduct;
	private ItemCart aItemCart;

	@Before
	public void setup() {
		percentageToDiscountTest = 10.00;
		priceForProduct = 20.00;
		quantityToApply = 2;
		
		aPrice = new Price();
		aPrice.setPrice(priceForProduct);
		
		aProduct = new Product();
		aProduct.setPrice(aPrice);
		
		aItemCart = new ItemCart();
		aItemCart.setProduct(aProduct);
		
		startingDate = new DateTime().withDayOfMonth(1).withMonthOfYear(1).withYear(2017);
		finishingDate = new DateTime().withDayOfMonth(1).withMonthOfYear(2).withYear(2017);
	}
	
	@Test
	public void createPerProductQuantity() {
		PerProductQuantity newPerProductQuantity = new PerProductQuantity();
		
		assertEquals(PerProductQuantity.class, newPerProductQuantity.getClass());
	}

	@Test
	public void testGettersAndSettersPerProductQuantity() {
		PerProductQuantity newPerProductQantity = new PerProductQuantity();
		
		newPerProductQantity.setPercentagePerProductToDiscount(percentageToDiscountTest);
		newPerProductQantity.setQuantityToApply(quantityToApply);
		newPerProductQantity.setProductForDiscount(aProduct);
		newPerProductQantity.setStartingDate(startingDate);
		newPerProductQantity.setFinishingDate(finishingDate);
		
		assertEquals(aProduct, newPerProductQantity.getProductForDiscount());
		assertEquals(percentageToDiscountTest, newPerProductQantity.getPercentagePerProductToDiscount());
		assertEquals(quantityToApply, newPerProductQantity.getQuantityToApply());
		assertEquals(startingDate, newPerProductQantity.getStartingDate());
		assertEquals(finishingDate, newPerProductQantity.getFinishingDate());
	}

	@Test
	public void testCanApplyDiscountToValidItemCartWithoutDiscount() {
		PerProductQuantity newPerProductQuantity = new PerProductQuantity();
		
		newPerProductQuantity.setPercentagePerProductToDiscount(10.00);
		newPerProductQuantity.setProductForDiscount(aProduct);
		newPerProductQuantity.setQuantityToApply(3);
		newPerProductQuantity.setStartingDate(startingDate);
		newPerProductQuantity.setFinishingDate(finishingDate);
		
		aItemCart.setQuantity(5);
		
		newPerProductQuantity.applyDiscountIfApplicable(aItemCart);
		assertEquals(newPerProductQuantity, aItemCart.getDiscount());
	}

	@Test
	public void testCanNotApplyDiscountToInvalidItemCartWithoutDiscount() {
		PerProductQuantity newPerProductQuantity = new PerProductQuantity();
		
		newPerProductQuantity.setPercentagePerProductToDiscount(10.00);
		newPerProductQuantity.setProductForDiscount(aProduct);
		newPerProductQuantity.setQuantityToApply(3);
		newPerProductQuantity.setStartingDate(startingDate);
		newPerProductQuantity.setFinishingDate(finishingDate);
		
		aItemCart.setQuantity(1);
		
		newPerProductQuantity.applyDiscountIfApplicable(aItemCart);
		assertFalse(newPerProductQuantity.equals(aItemCart.getDiscount()));
	}

	@Test
	public void testCanNotApplyDiscountToValidItemCartWithDiscount() {
		PerProductQuantity newPerProductQuantity = new PerProductQuantity();
		
		newPerProductQuantity.setPercentagePerProductToDiscount(10.00);
		newPerProductQuantity.setProductForDiscount(aProduct);
		newPerProductQuantity.setQuantityToApply(6);
		newPerProductQuantity.setStartingDate(startingDate);
		newPerProductQuantity.setFinishingDate(finishingDate);
		
		Discount anotherDiscount = new PerProduct();
		
		aItemCart.setQuantity(10);
		aItemCart.setDiscount(anotherDiscount);
		
		newPerProductQuantity.applyDiscountIfApplicable(aItemCart);
		assertEquals(anotherDiscount, aItemCart.getDiscount());
	}

	@Test
	public void testValidItemCartWithoutDiscount() {
		PerProductQuantity newPerProductQuantity = new PerProductQuantity();
		
		newPerProductQuantity.setPercentagePerProductToDiscount(10.00);
		newPerProductQuantity.setProductForDiscount(aProduct);
		newPerProductQuantity.setQuantityToApply(5);
		newPerProductQuantity.setStartingDate(startingDate);
		newPerProductQuantity.setFinishingDate(finishingDate);
		
		aItemCart.setQuantity(7);
		
		assertTrue(newPerProductQuantity.isItemValidForDiscount(aItemCart));
	}

	@Test
	public void testInvalidItemCartWithoutDiscount() {
		PerProductQuantity newPerProductQuantity = new PerProductQuantity();
		
		newPerProductQuantity.setPercentagePerProductToDiscount(10.00);
		newPerProductQuantity.setProductForDiscount(aProduct);
		newPerProductQuantity.setQuantityToApply(5);
		newPerProductQuantity.setStartingDate(startingDate);
		newPerProductQuantity.setFinishingDate(finishingDate);
		
		aItemCart.setQuantity(1);
		
		assertFalse(newPerProductQuantity.isItemValidForDiscount(aItemCart));
	}
	
	@Test
	public void testValueToDiscountForQuantityTwoInCartFiftyPercentDiscountForTwoProducts() {
		PerProductQuantity newPerProductQuantity = new PerProductQuantity();

		newPerProductQuantity.setPercentagePerProductToDiscount(50.00);
		newPerProductQuantity.setQuantityToApply(2);
		newPerProductQuantity.setProductForDiscount(aProduct);
		newPerProductQuantity.setStartingDate(startingDate);
		newPerProductQuantity.setFinishingDate(finishingDate);

		aItemCart.setQuantity(2);

		// The price is 20.00 so 50% would be 10.00
		assertEquals((Double) 10.00, newPerProductQuantity.valueToDiscount(aItemCart));
	}

	@Test
	public void testValueToDiscountForQuantityThreeInCartTwentyFivePercentDiscountForTwoProducts() {
		PerProductQuantity newPerProductQuantity = new PerProductQuantity();
		
		newPerProductQuantity.setPercentagePerProductToDiscount(25.00);
		newPerProductQuantity.setQuantityToApply(2);
		newPerProductQuantity.setProductForDiscount(aProduct);
		newPerProductQuantity.setStartingDate(startingDate);
		newPerProductQuantity.setFinishingDate(finishingDate);
		
		aItemCart.setQuantity(3);
		
		// The price is 20.00 so 25% would be 5.00
		assertEquals((Double) 5.00, newPerProductQuantity.valueToDiscount(aItemCart));
	}

	@Test
	public void testValueToDiscountForQuantityFiveInCartAndFivePercentDiscountForTwoProducts() {
		PerProductQuantity newPerProductQuantity = new PerProductQuantity();
		
		newPerProductQuantity.setPercentagePerProductToDiscount(5.00);
		newPerProductQuantity.setQuantityToApply(2);
		newPerProductQuantity.setProductForDiscount(aProduct);
		newPerProductQuantity.setStartingDate(startingDate);
		newPerProductQuantity.setFinishingDate(finishingDate);
		
		aItemCart.setQuantity(5);
		
		// Having two discounts in cart (20.00 * 2 = 40.00) so 5% would be 2.00
		assertEquals((Double) 2.00, newPerProductQuantity.valueToDiscount(aItemCart));
	}

	@Test
	public void testValueToDiscountForQuantitySixteenOfProductAndTwentyPercentDiscountForTheeProducts() {
		PerProductQuantity newPerProductQuantity = new PerProductQuantity();
		
		newPerProductQuantity.setPercentagePerProductToDiscount(20.00);
		newPerProductQuantity.setQuantityToApply(3);
		newPerProductQuantity.setProductForDiscount(aProduct);
		newPerProductQuantity.setStartingDate(startingDate);
		newPerProductQuantity.setFinishingDate(finishingDate);
		
		aItemCart.setQuantity(16);
		
		// Having five discounts in cart (20.00 * 5 = 100.00) so 20% would be 20.00
		assertEquals((Double) 20.00, newPerProductQuantity.valueToDiscount(aItemCart));
	}

}
