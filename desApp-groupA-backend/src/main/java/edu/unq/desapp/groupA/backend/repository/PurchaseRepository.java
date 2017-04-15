package edu.unq.desapp.groupA.backend.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;

import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.Purchase;
import edu.unq.desapp.groupA.backend.model.User;

public class PurchaseRepository {

	private List<Purchase> purchases;

	public PurchaseRepository() {
		this.purchases = new ArrayList<Purchase>();
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}
	
	public void save(Purchase p){
		this.purchases.add(p);
	}

	public List<Purchase> getPurchasesByUser(User user) {
		return this.purchases.stream().filter(p -> p.getUser().isSameUser(user)).collect(Collectors.toList());
	}

	public List<Cart> getAllCarts() {
		return this.purchases.stream().map(p -> p.getCart()).collect(Collectors.toList());
	}

	public List<Purchase> findLastPurchases(Integer quantityToFetch) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Purchase> findPurchasesFrom(DateTime dateFromToFetch) {
		// TODO Auto-generated method stub
		return null;
	}
}
