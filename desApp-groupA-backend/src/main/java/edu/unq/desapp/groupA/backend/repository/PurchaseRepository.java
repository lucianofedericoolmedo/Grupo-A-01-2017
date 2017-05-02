package edu.unq.desapp.groupA.backend.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.Purchase;
import edu.unq.desapp.groupA.backend.model.User;


@Repository
public class PurchaseRepository extends HibernateGenericDAO<Purchase> {

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
	
	public List<Purchase> getPurchasesByUser(User user) {
		return this.purchases.stream().filter(p -> p.getUser().isSameUser(user)).collect(Collectors.toList());
	}

	public List<Cart> getAllCarts() {
		return this.purchases.stream().map(p -> p.getCart()).collect(Collectors.toList());
	}

	
	public List<Purchase> findLastPurchases(Integer quantityToFetch) {
		return this.purchases.stream().sorted((c1, c2) -> (c1.getCreationDate().isAfter(c2.getCreationDate())) ? 1 : 0).
			collect(Collectors.toList()).subList(0, quantityToFetch);
	}

	public List<Purchase> findPurchasesFrom(DateTime dateFromToFetch) {
		return this.purchases.stream().filter(p -> p.getCreationDate().isAfter(dateFromToFetch)).
				collect(Collectors.toList());
	}
	
	public List<Purchase> getShippings() {
		return this.purchases.stream().filter(p -> p.getShippingAddress() != null).collect(Collectors.toList());
	}

	@Override
	protected Class<Purchase> getDomainClass() {
		return Purchase.class;
	}
}
