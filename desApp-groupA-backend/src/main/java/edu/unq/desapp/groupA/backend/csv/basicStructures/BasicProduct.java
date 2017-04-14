package edu.unq.desapp.groupA.backend.csv.basicStructures;

import java.util.List;

public class BasicProduct extends BasicStructure {

	// Instance Variables
	private Long id;
	
	private String name;
	
	private String brand;
	
	private Integer stock;

	private Double price;

	private String image;

	// Constructors
	public BasicProduct() {

	}

	public BasicProduct(List<String> csvResult) {
		this.id = secureLong(csvResult.get(0));
		this.name = csvResult.get(1);
		this.brand = csvResult.get(2);
		this.stock = secureInteger(csvResult.get(3));
		this.price = secureDouble(csvResult.get(4));
		this.image = csvResult.get(5);
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		String string = "Id: " + this.getId() + "; name: " + this.getName() + "; brand: "
						+ this.getBrand() + "; stock: " + this.getStock().toString() + "; price: "
						+ this.getPrice().toString() + "; image: " + this.getImage();
		return string;
	}

	private Long secureLong(String possibleLong) {
		try {
			return Long.valueOf(possibleLong);
		} catch (Exception e) {
			return null;
		}
	}

	private Integer secureInteger(String possibleInteger) {
		try {
			return Integer.valueOf(possibleInteger);
		} catch (Exception e) {
			return null;
		}
	}

	private Double secureDouble(String possibleDouble) {
		try {
			return Double.valueOf(possibleDouble);
		} catch (Exception e) {
			return null;
		}
	}

}
