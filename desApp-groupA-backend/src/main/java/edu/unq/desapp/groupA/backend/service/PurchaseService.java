package edu.unq.desapp.groupA.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.CashRegister;
import edu.unq.desapp.groupA.backend.model.PaymentType;
import edu.unq.desapp.groupA.backend.model.Purchase;
import edu.unq.desapp.groupA.backend.model.Usuario;

public class PurchaseService {

	private List<Purchase> purchases;

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}
	
	public PurchaseService(){
		this.purchases = new ArrayList<Purchase>();
	}
	
	public Purchase createPurchase(Cart cart, PaymentType paymentType, CashRegister cashRegister){
		Purchase purchase = new Purchase();
		purchase.setCashRegister(cashRegister);
		purchase.setCart(cart);
		purchase.setPayment(paymentType);
		this.purchases.add(purchase);
		return purchase;
	}
	
	public List<Purchase> getPurchasesByUser(Usuario user) {
		return this.purchases.stream().filter(p -> p.getCart().getUser().equals(user)).collect(Collectors.toList());
	}

	public List<Cart> getAllCarts() {
		return this.purchases.stream().map(p -> p.getCart()).collect(Collectors.toList());
	}
	
	
	
}
