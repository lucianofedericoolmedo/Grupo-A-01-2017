package edu.unq.desapp.groupA.backend.model.test;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import edu.unq.desapp.groupA.backend.model.PerProductCategory;
import edu.unq.desapp.groupA.backend.model.ProductCategory;

public class PerProductCategoryTest {

	private ProductCategory aProductCategory;
	private Integer percentageToDiscountTest;
	private DateTime startingDate;
	private DateTime finishingDate;

	@Before
	public void setup() {
		aProductCategory = new ProductCategory();
		percentageToDiscountTest = 10;
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

}
