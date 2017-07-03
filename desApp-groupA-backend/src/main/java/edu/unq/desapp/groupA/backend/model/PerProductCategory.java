package edu.unq.desapp.groupA.backend.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "discounts_per_product_category")
@JsonIgnoreProperties(ignoreUnknown=true)
public class PerProductCategory extends Discount {
	/**
	 * This Discount applies for each Product with correct Category.
	 * i.e.: With two Products with the correct Category, each receives the 
	 * 		 Discount percentage.
	 */

	private static final long serialVersionUID = -7635636745291483929L;
	
	private static final String name = "Per Product Category";

	// Instance Variables
	@ManyToOne
	private ProductCategory categoryForDiscount;

	// Constructors
	public PerProductCategory() {

	}

	public PerProductCategory(ProductCategory productCategory, Date startingDate, Date finishingDate,
			Double percentagePerProductToDiscount, Priority priority) {
		super(startingDate, finishingDate, percentagePerProductToDiscount, priority);
		this.categoryForDiscount = productCategory;
	}

	// Getters and Setters
	public ProductCategory getCategoryForDiscount() {
		return categoryForDiscount;
	}
	
	public void setCategoryForDiscount(ProductCategory categoryForDiscount) {
		this.categoryForDiscount = categoryForDiscount;
	}

	@Override
	public Double valueToDiscount(ItemCart itemCart) {
		Double valueToDiscount = percentageValuePerProduct(itemCart.getProduct().priceForQuantity(1));
		valueToDiscount *= itemCart.getQuantity();
		return valueToDiscount;
	}

	@Override
	public Boolean isItemValidForDiscount(ItemCart item) {
		return item.isCategory(categoryForDiscount);
	}
	
	@Override
	public String getName() {
		return name;
	}

}
