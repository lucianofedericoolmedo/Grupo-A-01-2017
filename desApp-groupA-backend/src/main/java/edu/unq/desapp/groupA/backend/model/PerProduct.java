package edu.unq.desapp.groupA.backend.model;

public class PerProduct extends Discount {
	/**
	 * This Discount applies for each Product unity to be discounted
	 * i.e.: With three Products in the cart, each receives the Discount percentage.
	 */

	// Instance Variables
	private Product productForDiscount;

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

}
