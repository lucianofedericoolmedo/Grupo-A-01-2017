package edu.unq.desapp.groupA.backend.dto;

import edu.unq.desapp.groupA.backend.model.PerProduct;
import edu.unq.desapp.groupA.backend.model.PerProductCategory;
import edu.unq.desapp.groupA.backend.model.PerProductQuantity;

public class DiscountItemHistoryDTO {

	// Instance Variables
	private String name;
	
	private String product;
	
	private Integer quantity;
	
	private String productCategory;
	
	private Double totalDiscount;

	// Constructors
	public DiscountItemHistoryDTO() {

	}
	
	public DiscountItemHistoryDTO(PerProduct discount, Double totalDiscount) {
		this.name = discount.getName();
		this.product = discount.getProductForDiscount().getName();
		this.totalDiscount = totalDiscount;
	}
	
	public DiscountItemHistoryDTO(PerProductQuantity discount, Double totalDiscount) {
		this.name = discount.getName();
		this.product = discount.getProductForDiscount().getName();
		this.quantity = discount.getQuantityToApply();
		this.totalDiscount = totalDiscount;
	}
	
	public DiscountItemHistoryDTO(PerProductCategory discount, Double totalDiscount) {
		this.name = discount.getName();
		this.productCategory = discount.getCategoryForDiscount().getName();
		this.totalDiscount = totalDiscount;
	}
	
	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public Double getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(Double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

}
