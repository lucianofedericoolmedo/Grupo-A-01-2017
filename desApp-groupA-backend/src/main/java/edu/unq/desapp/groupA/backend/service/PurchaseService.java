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
		purchase.setCashRegister(cashRegister);
		purchase.setCart(cart);
		purchase.setPayment(paymentType);
		this.purchases.add(purchase);
		return purchase;
	}

	private List<Product> getProductsByCart(Cart cart){
		return cart.getItems().stream().map(p -> p.getProduct()).collect(Collectors.toList());
	}
	
	private List<Product> getDistinct(Product product, Cart cart){
		List<Product> products = this.getProductsByCart(cart);
		List<Product> result = new ArrayList<Product>();
		for (Product prod : products){
			if (! prod.getName().equals(product.getName())){
				result.add(prod);
			}
		}
		return result;
	}
	
	public List<Product> findByProduct(Product product) {
		List<Product> prods = new ArrayList<Product>();
		List<Cart> carts = this.purchases.stream().map(p -> p.getCart()).collect(Collectors.toList());
		for (Cart cart : carts) {
			prods.addAll(this.getDistinct(product,cart));
		}		
		return prods;
	}
	
	
	
}
