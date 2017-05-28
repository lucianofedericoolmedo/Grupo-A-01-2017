package edu.unq.desapp.groupA.backend.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="products")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Product extends PersistenceEntity {

	private static final long serialVersionUID = 8727166810127029053L;

	// Instance Variables
	private String name;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="products_products_categories", 
		joinColumns={@JoinColumn(name="products_id")}, 
		inverseJoinColumns={@JoinColumn(name="products_categories_id")})
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<ProductCategory> categories;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Brand brand;

	@OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
	private List<Price> prices;
	
	private String imageUrl;
	
	// Constructors
	public Product() {
		categories = new LinkedList<ProductCategory>();
		prices = new LinkedList<Price>();
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

	public List<Price> getPrices() {
		return prices;
	}

	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	// Logic
	public Double priceForQuantity(Integer quantity) {
		//TODO: Refactor for a given price ??
		return this.findCurrentPrice().priceForQuantity(quantity);
	}
	
	public Price findCurrentPrice(){
		Optional<Price> opt = prices.stream().filter(price -> price.getFinishingValidityDate() == null).findFirst();
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}

	public boolean isCategory(ProductCategory categoryForDiscount) {
		return categories.contains(categoryForDiscount);
	}

	public void addCategory(ProductCategory aProductCategory) {
		categories.add(aProductCategory);
	}

	public void addPrice(Price price) {
		this.prices.add(price);
	}
	
	@Override
	public String toString() {
		return "Product: " + name + ", id: " + getId();
	}

}
