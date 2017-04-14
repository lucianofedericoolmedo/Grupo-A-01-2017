package edu.unq.desapp.groupA.backend.service;

import java.util.List;

import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.Purchase;
import edu.unq.desapp.groupA.backend.model.Threshold;
import edu.unq.desapp.groupA.backend.repository.ThresholdRepository;

public class ThresholdService {

	// Repositories and Services
	private ThresholdRepository repository;
	private PurchaseService purchaseService;

	public ThresholdService(ThresholdRepository thresholdRepository) {
		this.repository = thresholdRepository;
	}

	public ThresholdRepository getRepository() {
		return repository;
	}

	public void setRepository(ThresholdRepository thresholdRepository) {
		this.repository = thresholdRepository;
	}

	public PurchaseService getPurchaseService() {
		return purchaseService;
	}

	public void setPurchaseService(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}

	// Logic
	public Boolean cartSurpassesAnyThreshold(Cart cart) {
		List<Threshold> enabledThresholdsOfUser = this.getRepository().findEnabledThresholdOfUser(cart.getUser());

		for (Threshold threshold : enabledThresholdsOfUser) {
			List<Purchase> purchasesToEvaluate = threshold.fetchPurchases(purchaseService);
			Boolean isSurpassed = threshold.isCartSurpassed(cart, purchasesToEvaluate); 
			if (isSurpassed) {
				return true;
			}
		}
		return false;
	}

}
