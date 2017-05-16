package edu.unq.desapp.groupA.backend.model.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import edu.unq.desapp.groupA.backend.model.Price;

public class PriceTest {

	private Date firstDayOfMonthOne;
	private Date tenthDayOfMonthOne;

	@Before
	public void setup() {
		firstDayOfMonthOne = new DateTime().withDayOfMonth(1).withMonthOfYear(1).withYear(2017).toDate();
		tenthDayOfMonthOne = new DateTime().withDayOfMonth(10).withMonthOfYear(1).withYear(2017).toDate();
	}
	
	@Test
	public void createPrice() {
		Price newPrice = new Price();

		assertEquals(Price.class, newPrice.getClass());
	}

	@Test
	public void testGettersAndSettersCategory() {
		Price newPrice = new Price();
		newPrice.setPrice(10.00);
		newPrice.setStartingValidityDate(firstDayOfMonthOne);
		newPrice.setFinishingValidityDate(tenthDayOfMonthOne);

		assertEquals((Double) 10.00, newPrice.getPrice());
		assertEquals(firstDayOfMonthOne, newPrice.getStartingValidityDate());
		assertEquals(tenthDayOfMonthOne, newPrice.getFinishingValidityDate());
	}

}
