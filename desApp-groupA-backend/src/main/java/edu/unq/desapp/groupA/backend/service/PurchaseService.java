package edu.unq.desapp.groupA.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.CashRegister;
import edu.unq.desapp.groupA.backend.model.PaymentType;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.Purchase;

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
		// Armar un Cart
		purchase.setCashRegister(cashRegister);
		purchase.setCart(cart);
		purchase.setPayment(paymentType);
		return purchase;
	}

	public List<Cart> findByProduct(Product product) {
		List<Cart> res = new ArrayList<Cart>();
		for (Cart cart : this.purchases.stream().map(p -> p.getCart()).collect(Collectors.toList())){
			res.add(cart);
		}
		return res;
	}
	
	
	
}
