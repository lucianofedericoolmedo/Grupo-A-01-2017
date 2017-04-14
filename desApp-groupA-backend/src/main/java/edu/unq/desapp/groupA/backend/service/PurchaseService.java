package edu.unq.desapp.groupA.backend.service;

import java.util.List;

import org.joda.time.DateTime;

import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.CashRegister;
import edu.unq.desapp.groupA.backend.model.PaymentType;
import edu.unq.desapp.groupA.backend.model.Purchase;
import edu.unq.desapp.groupA.backend.model.Usuario;
import edu.unq.desapp.groupA.backend.repository.PurchaseRepository;

public class PurchaseService {

	private PurchaseRepository repository;	
	
	public Purchase createPurchase(Cart cart, PaymentType paymentType, CashRegister cashRegister){
		Purchase purchase = new Purchase();
		purchase.setCashRegister(cashRegister);
		purchase.setCart(cart);
		purchase.setPayment(paymentType);
		this.repository.save(purchase);
		return purchase;
	}
	
	public PurchaseService(PurchaseRepository repository) {
		this.repository = repository;
	}

	public List<Purchase> getPurchasesByUser(Usuario user) {
		return this.repository.getPurchasesByUser(user);
	}

	public List<Cart> getAllCarts() {
		return this.repository.getAllCarts();
	}

	public List<Purchase> fetchLastPurchases(Integer quantityToFetch) {
		return repository.findLastPurchases(quantityToFetch);
	}

	public List<Purchase> fetchPurchasesFrom(DateTime dateFromToFetch) {
		return repository.findPurchasesFrom(dateFromToFetch);
	}
	
}
