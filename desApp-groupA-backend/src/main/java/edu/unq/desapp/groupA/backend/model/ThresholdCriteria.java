package edu.unq.desapp.groupA.backend.model;

import java.util.List;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import edu.unq.desapp.groupA.backend.service.PurchaseService;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ThresholdCriteria extends PersistenceEntity{

	private static final long serialVersionUID = -3309629306620369096L;

	public abstract String getDescription();

	public abstract List<Purchase> fetchPurchasesCriteria(PurchaseService purchaseService);

}
