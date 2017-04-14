package edu.unq.desapp.groupA.backend.model;

import java.util.List;

import edu.unq.desapp.groupA.backend.service.PurchaseService;

public abstract class ThresholdCriteria extends Entity{

	public abstract String getDescription();

	public abstract List<Purchase> fetchPurchasesCriteria(PurchaseService purchaseService);

}
