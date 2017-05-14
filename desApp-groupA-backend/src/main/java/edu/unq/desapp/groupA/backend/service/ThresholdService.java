package edu.unq.desapp.groupA.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.Purchase;
import edu.unq.desapp.groupA.backend.model.Threshold;
import edu.unq.desapp.groupA.backend.repository.ThresholdRepository;


@Service
public class ThresholdService {

	// Repositories and Services
	@Autowired
	private ThresholdRepository repository;
	
	@Autowired
	private PurchaseService purchaseService;

	public ThresholdService() { }

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
	public Boolean cartSurpassesAnyThreshold(Cart cart, Long userId) {
		List<Threshold> enabledThresholdsOfUser = this.getRepository().findEnabledThresholdOfUser(cart.getUser());

		for (Threshold threshold : enabledThresholdsOfUser) {
			List<Purchase> purchasesToEvaluate = threshold.fetchPurchases(purchaseService, userId);
			Boolean isSurpassed = threshold.isCartSurpassed(cart, purchasesToEvaluate); 
			if (isSurpassed) {
				return true;
			}
		}
		return false;
	}

}
