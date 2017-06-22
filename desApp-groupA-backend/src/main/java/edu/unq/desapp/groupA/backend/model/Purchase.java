package edu.unq.desapp.groupA.backend.model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "purchases")
public class Purchase extends PersistenceEntity {

	private static final long serialVersionUID = 8013837936064742125L;

	// Instance Variables
	@OneToOne
	private Cart cart;
		
	@ManyToOne
	private ShippingAddress shippingAddress;

	@ManyToOne
	private PaymentTurn turn;
	
	@Enumerated(EnumType.STRING)
	private PaymentType payment;

	// Constructors
	public Purchase() {
	}
	
	public Purchase(Cart cart, PaymentTurn turn) {
		this.cart = cart;
		this.turn = turn;
	}
	
	public Purchase(Cart cart, PaymentType paymentType, PaymentTurn turn) {
		this.cart = cart;
		this.payment = paymentType;
		this.turn = turn;
	}

	public Purchase(Cart cart, PaymentType paymentType, PaymentTurn turn, ShippingAddress shippingAddress) {
		this.cart = cart;
		this.payment = paymentType;
		this.turn = turn;
		this.shippingAddress = shippingAddress;
	}

	// Getters and Setters
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
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
	
	public UserCredential getUser(){
		return getCart().getUser();
	}
	
	
	public Set<String> namesOfProductsInPurchase(){
		return this.getProducts().stream().map(p -> p.getName()).collect(Collectors.toSet());
	}

	private List<Product> getProducts() {
		return this.cart.getItems().stream().map(p-> p.getProduct()).collect(Collectors.toList());
	}

	public PaymentTurn getTurn() {
		return turn;
	}

	public void setTurn(PaymentTurn turn) {
		this.turn = turn;
	}

	public PaymentType getPayment() {
		return payment;
	}

	public void setPayment(PaymentType paymentType) {
		this.payment = paymentType;
	}

}
