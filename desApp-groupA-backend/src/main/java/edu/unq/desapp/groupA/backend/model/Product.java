package edu.unq.desapp.groupA.backend.model;

import java.util.LinkedList;
import java.util.List;

public class Product {

	// Instance Variables
	private String name;

	private List<ProductCategory> categories;
	
	private Brand brand;

	private Price price;
	
	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProductCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<ProductCategory> categories) {
		this.categories = categories;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	// Logic
	public Double priceForQuantity(Integer quantity) {
		return price.priceForQuantity(quantity);
	}

	public boolean isCategory(ProductCategory categoryForDiscount) {
		return categories.contains(categoryForDiscount);
	}

	public void addCategory(ProductCategory aProductCategory) {
		if (categories == null) {
			categories = new LinkedList<ProductCategory>();
		}
		categories.add(aProductCategory);
	}

}
