package edu.unq.desapp.groupA.backend.model;

import java.io.Serializable;
import java.util.Date;


public class CashRegister implements Serializable {
	
	private static final long serialVersionUID = 2557711174710524531L;
	
	private static final Integer stimatedSecondsPerProduct = 5;
	
	private static final Integer stimatedSecondsPerPurchasePayment = 60;

	// Instance Variables
	private Long code;
	private Integer productsToProcess;
	
	// Getters and Setters
	public Integer getProductsToProcess() {
		return productsToProcess;
	}

	public CashRegister(Long code){
		this.setCode(code);
		this.productsToProcess = 0;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public void requirePurchase(Cart cart) {
		cart.setReservationTime(new Date());
		this.setProductsToProcess(this.productsToProcess + cart.quantityOfcheckedItems());		
	}

	public void setProductsToProcess(Integer productosPorProcesar) {
		this.productsToProcess = productosPorProcesar;
	}

	public void removeItems(Integer quantityOfItems) {
		this.setProductsToProcess(this.productsToProcess - quantityOfItems);
	}

	public Integer getStimatedTime() {
		return (this.productsToProcess * stimatedSecondsPerProduct) + stimatedSecondsPerPurchasePayment;
	}

	public void removecheckedItemsFrom(Cart cart) {
		this.productsToProcess -= cart.quantityOfcheckedItems();
	}
	
}
