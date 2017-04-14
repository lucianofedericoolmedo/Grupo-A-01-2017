package edu.unq.desapp.groupA.backend.model;

import java.util.List;

import org.joda.time.DateTime;

import edu.unq.desapp.groupA.backend.service.PurchaseService;

public class DateThresholdCriteria extends ThresholdCriteria {

	public static final String description = "Por compras desde cierta fecha";
	
	// Instance Variables
	private DateTime dateFromToFetch;

	// Getters and Setters
	public DateTime getDateFromToFetch() {
		return dateFromToFetch;
	}

	public void setDateFromToFetch(DateTime dateFromToFetch) {
		this.dateFromToFetch = dateFromToFetch;
	}

	@Override
	public String getDescription() {
		return description;
	}

	// Logic
	@Override
	public List<Purchase> fetchPurchasesCriteria(PurchaseService purchaseService) {
		return purchaseService.fetchPurchasesFrom(dateFromToFetch);
	}

}
