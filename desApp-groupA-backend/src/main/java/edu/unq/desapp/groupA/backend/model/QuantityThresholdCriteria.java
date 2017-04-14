package edu.unq.desapp.groupA.backend.model;

import java.util.List;

import edu.unq.desapp.groupA.backend.service.PurchaseService;

public class QuantityThresholdCriteria extends ThresholdCriteria {

	public static final String description = "Por cantidad de ultimas compras";
	
	// Instance Variables
	protected Integer quantityToFetch;

	// Getters and Setters
	public Integer getQuantityToFetch() {
		return quantityToFetch;
	}

	public void setQuantityToFetch(Integer quantityToFetch) {
		this.quantityToFetch = quantityToFetch;
	}

	@Override
	public String getDescription() {
		return description;
	}

	// Logic
	@Override
	public List<Purchase> fetchPurchasesCriteria(PurchaseService purchaseService) {
		return purchaseService.fetchLastPurchases(quantityToFetch);
	}

}
