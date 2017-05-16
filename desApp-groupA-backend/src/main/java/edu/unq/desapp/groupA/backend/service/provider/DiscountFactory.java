package edu.unq.desapp.groupA.backend.service.provider;

import java.util.Date;

import org.joda.time.DateTime;

import edu.unq.desapp.groupA.backend.model.PerProductQuantity;
import edu.unq.desapp.groupA.backend.model.Priority;
import edu.unq.desapp.groupA.backend.model.Product;

public class DiscountFactory {

	public static PerProductQuantity exampleNewPerProductQuantityElem(Product product) {
		PerProductQuantity discount = new PerProductQuantity();
		Date firstDayOfMonthOne = new DateTime().withDayOfMonth(1).withMonthOfYear(1).withYear(2017).toDate();
		Date tenthDayOfMonthOne = new DateTime().withDayOfMonth(10).withMonthOfYear(1).withYear(2017).toDate();
		discount.setStartingDate(firstDayOfMonthOne);
		discount.setFinishingDate(tenthDayOfMonthOne);
		discount.setPercentagePerProductToDiscount(10.0);
		discount.setPriority(Priority.HIGH);
		discount.setProductForDiscount(product);
		return discount;
	}
	
	

}
