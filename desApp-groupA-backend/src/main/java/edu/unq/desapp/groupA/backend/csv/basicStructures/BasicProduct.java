package edu.unq.desapp.groupA.backend.csv.basicStructures;

import java.util.List;

public class BasicProduct extends BasicStructure {

	// Instance Variables
	private String id;
	
	private String name;
	
	private String brand;
	
	private String stock;

	private String price;

	private String image;

	// Constructors
	public BasicProduct() {

	}

	public BasicProduct(List<String> csvResult) {
		this.id = csvResult.get(0);
		this.name = csvResult.get(1);
		this.brand = csvResult.get(2);
		this.stock = csvResult.get(3);
		this.price = csvResult.get(4);
		this.image = csvResult.get(5);
	}

	// Getters and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
