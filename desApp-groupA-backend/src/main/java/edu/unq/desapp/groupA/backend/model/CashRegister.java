package edu.unq.desapp.groupA.backend.model;

import java.util.Date;

public class CashRegister {
	
	private Boolean available;
	private Integer productsToProcess;
	

	public Integer getProductsToProcess() {
		return productsToProcess;
	}


	public CashRegister(){
		this.productsToProcess = 0;
		this.setAvailable(true);
	}



	public Boolean getAvailable() {
		return available;
	}


	public void setAvailable(Boolean available) {
		this.available = available;
	}


	public void requirePurchase(Cart cart) {
		cart.setReservationTime(new Date());
		this.available = false;
		this.setProductsToProcess(this.productsToProcess + cart.quantityOfItems());		
	}

	public void setProductsToProcess(Integer productosPorProcesar) {
		this.productsToProcess = productosPorProcesar;
	}


	public void removeItems(Integer quantityOfItems) {
		this.setProductsToProcess(this.productsToProcess - quantityOfItems);
		if (this.productsToProcess == 0){
			this.available = true;
		}
		
	}
	
	

	

}
