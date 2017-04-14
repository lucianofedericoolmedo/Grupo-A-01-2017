package edu.unq.desapp.groupA.backend.model;

import java.util.List;
import java.util.stream.Collectors;

public class PurchasePriceThreshold extends Threshold {

	// Instance Variables
	private Double priceToSurpass;

	// Getters and Setters
	public Double getPriceToSurpass() {
		return priceToSurpass;
	}

	public void setPriceToSurpass(Double priceToSurpass) {
		this.priceToSurpass = priceToSurpass;
	}

	// Logic
	@Override
	public List<Double> purchasesToEvaluateValues(List<Purchase> purchasesToEvaluate) {
		return purchasesToEvaluate.stream().map(p -> p.totalValueOfCheckedItem()).collect(Collectors.toList());
	}

	@Override
	public Double currentCartValue(Cart cart) {
		return cart.totalValueOfCheckedItems();
	}

}
