package edu.unq.desapp.groupA.backend.model;

public class Purchase {

	// Instance Variables
	private Cart cart;
	
	private PaymentType payment;

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

}
