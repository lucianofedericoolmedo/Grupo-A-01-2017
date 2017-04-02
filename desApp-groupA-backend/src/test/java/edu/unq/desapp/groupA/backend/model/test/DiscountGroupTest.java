package edu.unq.desapp.groupA.backend.model.test;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.unq.desapp.groupA.backend.model.Discount;
import edu.unq.desapp.groupA.backend.model.DiscountGroup;

public class DiscountGroupTest {

	private List<Discount> discounts;

	@Before
	public void setup() {
		discounts = new LinkedList<Discount>();
	}
	
	@Test
	public void createDiscountGroup() {
		DiscountGroup newDiscountGroup = new DiscountGroup();
		
		assertEquals(DiscountGroup.class, newDiscountGroup.getClass());
	}

	@Test
	public void testGettersAndSettersDiscountGroup() {
		DiscountGroup newDiscountGroup = new DiscountGroup();
		
		newDiscountGroup.setDiscountsToApply(discounts);
		
		assertEquals(discounts, newDiscountGroup.getDiscountsToApply());
	}

}
