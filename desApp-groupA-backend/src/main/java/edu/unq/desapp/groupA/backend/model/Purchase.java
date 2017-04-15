package edu.unq.desapp.groupA.backend.model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Purchase extends Entity {

	// Instance Variables
	private Cart cart;
	
	private ShippingAddress shippingAddress;
		
	private PaymentType payment;

	private CashRegister cashRegister;

	// Getters and Setters
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public PaymentType getPayment() {
		return payment;
	}

	public void setPayment(PaymentType payment) {
		this.payment = payment;
	}

	public void setCashRegister(CashRegister cashRegister) {
		this.cashRegister = cashRegister;
	}
	
	public CashRegister getCashRegister(){
		return this.cashRegister;
	}

	// Logic
	/**
	 * Calculates the total value of the purchase.
	 * @return : A Double representing the total value.
	 */
	public Double totalValue() {
		return cart.totalValue();
	}
	
	public Double totalValueOfCheckedItem() {
		return cart.totalValueOfCheckedItems();
	}

	public Double totalValueOfProductCategory(ProductCategory productCategory) {
		return cart.totalValueOfProductCategory(productCategory);
	}

	public Double totalValueOfCheckedItemsWithProductCategory(ProductCategory productCategory) {
		return cart.totalValueOfCheckedItemsWithProductCategory(productCategory);
	}

	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	
	public User getUser(){
		return getCart().getUser();
	}
	
	
	public Set<String> namesOfProductsInPurchase(){
		return this.getProducts().stream().map(p -> p.getName()).collect(Collectors.toSet());
	}
	

	private List<Product> getProducts() {
		// TODO Auto-generated method stub
		return this.cart.items.stream().map(p-> p.getProduct()).collect(Collectors.toList());
	}

}
