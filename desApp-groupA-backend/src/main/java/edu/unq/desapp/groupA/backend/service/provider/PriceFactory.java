package edu.unq.desapp.groupA.backend.service.provider;

import org.joda.time.DateTime;

import edu.unq.desapp.groupA.backend.model.Price;

public class PriceFactory {

	public static Price exampleNewElem(Double price){
		DateTime firstDayOfMonthOne = new DateTime().withDayOfMonth(1).withMonthOfYear(1).withYear(2017);
		DateTime tenthDayOfMonthOne = new DateTime().withDayOfMonth(10).withMonthOfYear(1).withYear(2017);		
		return newElem(price, firstDayOfMonthOne, tenthDayOfMonthOne);
	}
	
	public static Price newElem(Double price, DateTime startingValidityDate, DateTime finishingValidityDate){
		Price newPrice = new Price();
		newPrice.setPrice(price);
		newPrice.setStartingValidityDate(startingValidityDate);
		newPrice.setFinishingValidityDate(finishingValidityDate);
		return newPrice;
	}
	
}
