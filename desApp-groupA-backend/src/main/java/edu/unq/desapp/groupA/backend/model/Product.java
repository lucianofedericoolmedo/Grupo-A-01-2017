package edu.unq.desapp.groupA.backend.model;

import java.util.List;

public class Product {

	// Instance Variables
	private String name;

	private List<ProductCategory> categories;
	
	private Brand brand;

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

}
