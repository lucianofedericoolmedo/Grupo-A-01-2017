package edu.unq.desapp.groupA.backend.dto;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import edu.unq.desapp.groupA.backend.model.Product;


@JsonIgnoreProperties(ignoreUnknown=true)
public class ProductCrud implements Serializable {

	private static final long serialVersionUID = 7532386347606189968L;

	// Instance Variables
	private Product product;

	private Double price;

	private Integer stock;

	// Constructors
	public ProductCrud() {
		
	}

	// Getters and Setters
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
