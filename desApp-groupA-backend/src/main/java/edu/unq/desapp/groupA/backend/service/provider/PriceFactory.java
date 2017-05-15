package edu.unq.desapp.groupA.backend.service.provider;

import java.util.Date;

import org.joda.time.DateTime;

import edu.unq.desapp.groupA.backend.model.Price;

public class PriceFactory {

	public static Price exampleNewElem(Double price){
		Date firstDayOfMonthOne = new DateTime().withDayOfMonth(1).withMonthOfYear(1).withYear(2017).toDate();
		Date tenthDayOfMonthOne = new DateTime().withDayOfMonth(10).withMonthOfYear(1).withYear(2017).toDate();		
		return newElem(price, firstDayOfMonthOne, tenthDayOfMonthOne);
	}
	
	public static Price newElem(Double price, Date firstDayOfMonthOne, Date tenthDayOfMonthOne){
		Price newPrice = new Price();
		newPrice.setPrice(price);
		newPrice.setStartingValidityDate(firstDayOfMonthOne);
		newPrice.setFinishingValidityDate(tenthDayOfMonthOne);
		return newPrice;
	}
	
}
