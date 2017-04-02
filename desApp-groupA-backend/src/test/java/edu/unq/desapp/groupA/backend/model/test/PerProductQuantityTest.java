package edu.unq.desapp.groupA.backend.model.test;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import edu.unq.desapp.groupA.backend.model.PerProductQuantity;
import edu.unq.desapp.groupA.backend.model.Product;

public class PerProductQuantityTest {

	private Product aProduct;
	private Integer percentageToDiscountTest;
	private Integer quantityToApply;
	private DateTime startingDate;
	private DateTime finishingDate;

	@Before
	public void setup() {
		aProduct = new Product();
		percentageToDiscountTest = 10;
		quantityToApply = 2;
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

}
