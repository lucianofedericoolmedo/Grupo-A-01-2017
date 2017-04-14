package edu.unq.desapp.groupA.backend.model;

import java.util.Date;

public class Purchase {

	// Instance Variables
	private Cart cart;
	
	private ShippingAddress shippingAddress;
		
	private PaymentType payment;

	private CashRegister cashRegister;

	private Date creationDate;

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

	// Logic
	/**
	 * Calculates the total value of the purchase.
	 * @return : A Double representing the total value.
	 */
	public Double totalValue() {
		return cart.totalValue();
	}

	public void setCashRegister(CashRegister cashRegister) {
		this.cashRegister = cashRegister;
	}
	
	public CashRegister getCashRegister(){
		return this.cashRegister;
	}

	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public void setCreationDate(Date date) {
		this.creationDate = date;		
	}

	public Date getCreationDate() {
		return creationDate;
	}

}
