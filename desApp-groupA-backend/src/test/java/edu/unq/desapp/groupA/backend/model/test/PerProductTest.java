package edu.unq.desapp.groupA.backend.model.test;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import edu.unq.desapp.groupA.backend.model.PerProduct;
import edu.unq.desapp.groupA.backend.model.Product;

public class PerProductTest {

	private Product aProduct;
	private Integer percentageToDiscountTest;
	private DateTime startingDate;
	private DateTime finishingDate;

	@Before
	public void setup() {
		aProduct = new Product();
		percentageToDiscountTest = 10;
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

}
