package edu.unq.desapp.groupA.backend.service;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.PaymentType;
import edu.unq.desapp.groupA.backend.model.Purchase;
import edu.unq.desapp.groupA.backend.model.ShippingAddress;
import edu.unq.desapp.groupA.backend.model.User;
import edu.unq.desapp.groupA.backend.repository.PurchaseRepository;

public class PurchaseService {

	private PurchaseRepository repository;	
	
	public Purchase createPurchase(Cart cart, PaymentType paymentType){
		Purchase purchase = new Purchase();
		purchase.setCart(cart);
		purchase.setPayment(paymentType);
		purchase.setCreationDate(new Date());
		this.repository.save(purchase);
		return purchase;
	}
	
	public PurchaseService(PurchaseRepository repository) {
		this.repository = repository;
	}

	public List<Purchase> getPurchasesByUser(User user) {
		return this.repository.getPurchasesByUser(user);
	}

	public List<Cart> getAllCarts() {
		return this.repository.getAllCarts();
	}

	public Purchase createPurchase(Cart cart, PaymentType paymentType, ShippingAddress shippingAddress) {
		Purchase purchase = new Purchase();
		purchase.setCart(cart);
		purchase.setPayment(paymentType);
		purchase.setCreationDate(new Date());
		purchase.setShippingAddress(shippingAddress);
		this.repository.save(purchase);
		return purchase;
		
	}

	public List<Purchase> getPurchases() {
		return this.repository.getPurchases();
	}
	
	public List<Purchase> fetchLastPurchases(Integer quantityToFetch) {
		return repository.findLastPurchases(quantityToFetch);
	}

	public List<Purchase> fetchPurchasesFrom(DateTime dateFromToFetch) {
		return repository.findPurchasesFrom(dateFromToFetch);
	}
	
}
