package edu.unq.desapp.groupA.backend.dto;

import edu.unq.desapp.groupA.backend.model.ItemCart;

public class PurchaseItemHistoryDTO {

	// Instance Variables
	private String product;
	
	private Integer quantity;

	private Double historyProductPrice;

	// Constructors
	public PurchaseItemHistoryDTO() {
		
	}

	public PurchaseItemHistoryDTO(ItemCart item) {
		this.product = item.getProduct().getName();
		this.quantity = item.getQuantity();
		this.historyProductPrice = item.getProduct().findPriceForDate(item.getCreationDate()).getPrice();
	}

	// Getters and Setters
	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getHistoryProductPrice() {
		return historyProductPrice;
	}

	public void setHistoryProductPrice(Double historyProductPrice) {
		this.historyProductPrice = historyProductPrice;
	}
	
}
