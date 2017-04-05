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
		aProduct.setPrice(aPrice);
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
	public void testCanApplyDiscountToValidItemCartWithoutDiscount() {
		PerProductCategory newPerProductCategory = new PerProductCategory();
		
		newPerProductCategory.setPercentagePerProductToDiscount(10.00);
		newPerProductCategory.setCategoryForDiscount(aProductCategory);
		newPerProductCategory.setStartingDate(startingDate);
		newPerProductCategory.setFinishingDate(finishingDate);
		
		aItemCart.setQuantity(1);
		
		newPerProductCategory.applyDiscountIfApplicable(aItemCart);
		assertEquals(newPerProductCategory, aItemCart.getDiscount());
	}

	@Test
	public void testCanNotApplyDiscountToInvalidItemCartWithoutDiscount() {
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
		assertFalse(newPerProductCategory.equals(aItemCart.getDiscount()));
	}

	@Test
	public void testCanNotApplyDiscountToValidItemCartWithDiscount() {
		PerProductCategory newPerProductCategory = new PerProductCategory();
		
		newPerProductCategory.setPercentagePerProductToDiscount(10.00);
		newPerProductCategory.setCategoryForDiscount(aProductCategory);
		newPerProductCategory.setStartingDate(startingDate);
		newPerProductCategory.setFinishingDate(finishingDate);
		
		Discount anotherDiscount = new PerProduct();
		
		aItemCart.setQuantity(1);
		aItemCart.setDiscount(anotherDiscount);
		
		newPerProductCategory.applyDiscountIfApplicable(aItemCart);
		assertEquals(anotherDiscount, aItemCart.getDiscount());
	}
	
	@Test
	public void testValidItemCartWithoutDiscount() {
		PerProductCategory newPerProductCategory = new PerProductCategory();
		
		newPerProductCategory.setPercentagePerProductToDiscount(10.00);
		newPerProductCategory.setCategoryForDiscount(aProductCategory);
		newPerProductCategory.setStartingDate(startingDate);
		newPerProductCategory.setFinishingDate(finishingDate);
		
		aItemCart.setQuantity(1);
		
		assertTrue(newPerProductCategory.isItemValidForDiscount(aItemCart));
	}

	@Test
	public void testInvalidItemCartWithoutDiscount() {
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
	public void testValueToDiscountForQuantityOneOfProductAndTenPercentDiscount() {
		PerProductCategory newPerProductCategory = new PerProductCategory();
		
		newPerProductCategory.setPercentagePerProductToDiscount(10.00);
		newPerProductCategory.setCategoryForDiscount(aProductCategory);
		newPerProductCategory.setStartingDate(startingDate);
		newPerProductCategory.setFinishingDate(finishingDate);
		
		aItemCart.setQuantity(1);
		
		// The price is 20.00 so 10% would be 2.00
		assertEquals((Double) 2.00, newPerProductCategory.valueToDiscount(aItemCart));
	}

	@Test
	public void testValueToDiscountForQuantityOneOfProductAndTwentyFivePercentDiscount() {
		PerProductCategory newPerProductCategory = new PerProductCategory();
		
		newPerProductCategory.setPercentagePerProductToDiscount(25.00);
		newPerProductCategory.setCategoryForDiscount(aProductCategory);
		newPerProductCategory.setStartingDate(startingDate);
		newPerProductCategory.setFinishingDate(finishingDate);
		
		aItemCart.setQuantity(1);
		
		// The price is 20.00 so 25% would be 5.00
		assertEquals((Double) 5.00, newPerProductCategory.valueToDiscount(aItemCart));
	}

	@Test
	public void testValueToDiscountForQuantityThreeOfProductAndFivePercentDiscount() {
		PerProductCategory newPerProductCategory = new PerProductCategory();
		
		newPerProductCategory.setPercentagePerProductToDiscount(5.00);
		newPerProductCategory.setCategoryForDiscount(aProductCategory);
		newPerProductCategory.setStartingDate(startingDate);
		newPerProductCategory.setFinishingDate(finishingDate);
		
		aItemCart.setQuantity(3);
		
		// The price is (20.00 + 20.00 + 20.00 = 60.00) so 5% would be 3.00
		assertEquals((Double) 3.00, newPerProductCategory.valueToDiscount(aItemCart));
	}

	@Test
	public void testValueToDiscountForQuantitySixteenOfProductAndFifteenPercentDiscount() {
		PerProductCategory newPerProductCategory = new PerProductCategory();
		
		newPerProductCategory.setPercentagePerProductToDiscount(15.00);
		newPerProductCategory.setCategoryForDiscount(aProductCategory);
		newPerProductCategory.setStartingDate(startingDate);
		newPerProductCategory.setFinishingDate(finishingDate);
		
		aItemCart.setQuantity(16);
		
		// The price is (20.00 * 16 = 320.00) so 15% would be 48.00
		assertEquals((Double) 48.00, newPerProductCategory.valueToDiscount(aItemCart));
	}

}
