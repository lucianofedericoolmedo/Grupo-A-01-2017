package edu.unq.desapp.groupA.backend.model;

import java.util.List;

import edu.unq.desapp.groupA.backend.service.PurchaseService;

public abstract class Threshold extends Entity {

	// Instance Variables
	protected Double percentageToSurpass;
	
	protected Boolean enabled;
	
	protected ThresholdCriteria criteria;
	
	protected User user;
	
	// Getters and Setters
	public Double getPercentageToSurpass() {
		return percentageToSurpass;
	}

	public void setPercentageToSurpass(Double percentageToSurpass) {
		this.percentageToSurpass = percentageToSurpass;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public ThresholdCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(ThresholdCriteria criteria) {
		this.criteria = criteria;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// Logic
	public abstract List<Double> purchasesToEvaluateValues(List<Purchase> purchasesToEvaluate);
	
	public abstract Double currentCartValue(Cart cart);
	
	public Boolean isCartSurpassed(Cart cart, List<Purchase> purchasesToEvaluate) {
		// Calculate historical
		List<Double> purchasesTotals = purchasesToEvaluateValues(purchasesToEvaluate);
		Double historicalTotalValue = purchasesTotals.stream().reduce(0.00, (x,y) -> x + y);
		historicalTotalValue /= purchasesTotals.size();
		// Calculate current
		Double currentTotalValue = currentCartValue(cart);
		
		Double diference = (historicalTotalValue / 100) * getPercentageToSurpass();
		Double quantityToSurpass = historicalTotalValue +  diference;
		return currentTotalValue > quantityToSurpass;
	}
	
	public List<Purchase> fetchPurchases(PurchaseService purchaseService){
		return this.getCriteria().fetchPurchasesCriteria(purchaseService);
	}
	
}