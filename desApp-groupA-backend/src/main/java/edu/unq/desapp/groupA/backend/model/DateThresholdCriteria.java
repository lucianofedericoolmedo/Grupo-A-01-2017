package edu.unq.desapp.groupA.backend.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.joda.time.DateTime;

import edu.unq.desapp.groupA.backend.service.PurchaseService;

@Entity
@Table(name = "thresholds_criterias_date")
public class DateThresholdCriteria extends ThresholdCriteria {

	private static final long serialVersionUID = 8095828204213938673L;

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

	@Override
	public List<Purchase> fetchPurchasesCriteria(PurchaseService purchaseService) {
		return purchaseService.fetchPurchasesFrom(dateFromToFetch);
	}

}
