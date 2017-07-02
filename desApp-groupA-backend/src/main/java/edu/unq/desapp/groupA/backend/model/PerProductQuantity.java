package edu.unq.desapp.groupA.backend.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "discounts_per_product_quantity")
public class PerProductQuantity extends Discount {

	/**
	 * This Discount applies for one Product in the quantity group that applies.
	 * i.e.: With three products in the cart, and the Discount quantity to apply
	 * 		 has been set to three, the third product receives the Discount percentage.
	 */

	private static final long serialVersionUID = -6073547991687988709L;
	
	private static final String name = "Per Product Quantity";

	// Instance Variables
	@ManyToOne(cascade=CascadeType.MERGE)
	private Product productForDiscount;
	
	private Integer quantityToApply;

	// Constructors
	public PerProductQuantity() {

	}
	
	public PerProductQuantity(Product product, Integer quantity, Date startingDate, Date finishingDate,
			Double percentagePerProductToDiscount, Priority priority) {
		super(startingDate, finishingDate, percentagePerProductToDiscount, priority);
		this.productForDiscount = product;
		this.quantityToApply = quantity;
	}

	// Getters and Setters
	public Product getProductForDiscount() {
		return productForDiscount;
	}

	public void setProductForDiscount(Product productForDiscount) {
		this.productForDiscount = productForDiscount;
	}

	public Integer getQuantityToApply() {
		return quantityToApply;
	}

	public void setQuantityToApply(Integer quantityToApply) {
		this.quantityToApply = quantityToApply;
	}

	// Logic
	@Override
	public Double valueToDiscount(ItemCart itemCart) {
		Double valueToDiscount = percentageValuePerProduct(productForDiscount.priceForQuantity(1));
		valueToDiscount *= Math.floor(itemCart.getQuantity() / quantityToApply);
		return valueToDiscount;
	}

	@Override
	public Boolean isItemValidForDiscount(ItemCart item) {
		return item.isProduct(productForDiscount) && item.getQuantity() >= quantityToApply;
	}
	
	@Override
	public String getName() {
		return name;
	}

}
