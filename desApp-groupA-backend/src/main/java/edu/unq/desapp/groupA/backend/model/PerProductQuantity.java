package edu.unq.desapp.groupA.backend.model;

public class PerProductQuantity extends Discount {
	/**
	 * This Discount applies for one Product in the quantity group that applies.
	 * i.e.: With three products in the cart, and the Discount quantity to apply
	 * 		 has been set to three, the third product receives the Discount percentage.
	 */

	// Instance Variables
	private Product productForDiscount;
	
	private Integer quantityToApply;

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

}
