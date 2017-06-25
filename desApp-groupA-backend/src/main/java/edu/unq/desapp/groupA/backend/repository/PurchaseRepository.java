package edu.unq.desapp.groupA.backend.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.Purchase;
import edu.unq.desapp.groupA.backend.model.UserCredential;
import edu.unq.desapp.groupA.backend.repository.pagination.PageRequest;
import edu.unq.desapp.groupA.backend.repository.pagination.PageRequestBuilder;
import edu.unq.desapp.groupA.backend.repository.pagination.PageResponse;


@Repository
@SuppressWarnings("unchecked")
public class PurchaseRepository extends HibernateGenericDAO<Purchase> {

	private static final long serialVersionUID = 1295896909645683123L;

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
	
	public List<Purchase> getPurchasesByUser(UserCredential user) {
		return this.purchases.stream().filter(p -> p.getUser().isSameUser(user)).collect(Collectors.toList());
	}

	public List<Cart> getAllCarts() {
		return this.purchases.stream().map(p -> p.getCart()).collect(Collectors.toList());
	}

	public List<Purchase> findLastPurchases(Integer quantityToFetch, Long userId) {
		String query = "FROM " + this.persistentClass.getName() + " purchase "
				+ " WHERE purchase.cart.user.id = ? "
				+ " ORDER BY purchase.creationDate DESC "
				+ quantityToFetch.toString();
		return (List<Purchase>) getHibernateTemplate().find(query, userId);
	}

	public List<Purchase> findPurchasesFrom(Date dateFromToFetch, Long userId) {
		String query = "FROM " + this.persistentClass.getName() + " purchase "
				+ " WHERE purchase.cart.user.id = ? "
				+ " AND purchase.creationDate > ? "
				+ " ORDER BY purchase.creationDate DESC ";
		return (List<Purchase>) getHibernateTemplate().find(query, userId, dateFromToFetch);
	}
	
	public PageResponse<Purchase> findPageByUserId(Integer pageNumber, Integer pageSize, Long userId) {
		String query = "FROM Purchase purchase "
						+ "WHERE purchase.cart.user.id = " + userId;
		PageRequest<Purchase> pageRequest = new PageRequestBuilder<Purchase>(getDomainClass())
				.setPageNumber(pageNumber)
				.setPageSize(pageSize)
				.setQuery(query)
				.build();
		return findByPage(pageRequest);
	}
	
	public List<Purchase> getShippings() {
		return this.purchases.stream().filter(p -> p.getShippingAddress() != null).collect(Collectors.toList());
	}

	@Override
	public Class<Purchase> getDomainClass() {
		return Purchase.class;
	}
}
