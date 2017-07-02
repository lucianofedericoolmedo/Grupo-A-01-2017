package edu.unq.desapp.groupA.backend.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "discounts_per_product")
public class PerProduct extends Discount {
	/**
	 * This Discount applies for each Product unity to be discounted
	 * i.e.: With three Products in the cart, each receives the Discount percentage.
	 */

	private static final long serialVersionUID = -4383470817005121486L;
	
	private static final String name = "Per Product";

	// Instance Variables
	@ManyToOne(cascade=CascadeType.MERGE)
	private Product productForDiscount;

	// Constructors
	public PerProduct() {

	}
	
	public PerProduct(Product product, Date startingDate, Date finishingDate, Double percentagePerProductToDicount, Priority priority) {
		super(startingDate, finishingDate, percentagePerProductToDicount, priority);
		this.productForDiscount = product;
	}

	// Getters and Setters
	public Product getProductForDiscount() {
		return productForDiscount;
	}

	public void setProductForDiscount(Product productForDiscount) {
		this.productForDiscount = productForDiscount;
	}

	// Logic
	@Override
	public Double valueToDiscount(ItemCart itemCart) {
		Double valueToDiscount = percentageValuePerProduct(productForDiscount.priceForQuantity(1));
		valueToDiscount *= itemCart.getQuantity();
		return valueToDiscount;
	}

	@Override
	public Boolean isItemValidForDiscount(ItemCart item) {
		return item.isProduct(productForDiscount);
	}

	@Override
	public String getName() {
		return name;
	}

}
