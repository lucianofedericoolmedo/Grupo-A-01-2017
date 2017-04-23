package edu.unq.desapp.groupA.backend.model;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Product extends PersistenceEntity {

	private static final long serialVersionUID = 8727166810127029053L;

	// Instance Variables
	private String name;

	@ManyToMany
	private List<ProductCategory> categories;
	
	@ManyToOne
	private Brand brand;

	@ManyToOne
	private Price price;
	
	// Constructors
	public Product() {
		categories = new LinkedList<ProductCategory>();
	}
	
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
		categories.add(aProductCategory);
	}

}
