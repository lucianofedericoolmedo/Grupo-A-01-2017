package edu.unq.desapp.groupA.backend.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import edu.unq.desapp.groupA.backend.service.PurchaseService;

@Entity
@Table(name = "thresholds_criterias_date")
public class DateThresholdCriteria extends ThresholdCriteria {

	private static final long serialVersionUID = 8095828204213938673L;

	public static final String description = "Por compras desde cierta fecha";
	
	// Instance Variables
	private Date dateFromToFetch;

	// Getters and Setters
	public Date getDateFromToFetch() {
		return dateFromToFetch;
	}

	public void setDateFromToFetch(Date dateFromToFetch) {
		this.dateFromToFetch = dateFromToFetch;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public List<Purchase> fetchPurchasesCriteria(PurchaseService purchaseService, Long userId) {
		return purchaseService.fetchPurchasesFrom(dateFromToFetch, userId);
	}

}
