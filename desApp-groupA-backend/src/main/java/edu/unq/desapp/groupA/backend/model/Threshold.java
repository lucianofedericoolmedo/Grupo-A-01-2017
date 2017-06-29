package edu.unq.desapp.groupA.backend.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import edu.unq.desapp.groupA.backend.service.PurchaseService;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Threshold extends PersistenceEntity {

	private static final long serialVersionUID = 3955350844177425893L;

	// Instance Variables
	protected Double percentageToSurpass;

	protected Boolean enabled;

	@ManyToOne
	protected ThresholdCriteria criteria;

	@ManyToOne
	protected UserProfile userProfile;

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

	public UserProfile getUser() {
		return userProfile;
	}

	public void setUser(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	// Logic
	public abstract List<Double> purchasesToEvaluateValues(List<Purchase> purchasesToEvaluate);
	
	public abstract Double currentCartValue(Cart cart);
	
	public abstract String getName();
	
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
	
	public List<Purchase> fetchPurchases(PurchaseService purchaseService, Long userId){
		return this.getCriteria().fetchPurchasesCriteria(purchaseService, userId);
	}
	
}
