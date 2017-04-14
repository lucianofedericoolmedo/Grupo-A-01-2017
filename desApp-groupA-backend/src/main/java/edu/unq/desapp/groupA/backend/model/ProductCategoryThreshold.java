package edu.unq.desapp.groupA.backend.model;

import java.util.List;
import java.util.stream.Collectors;

public class ProductCategoryThreshold  extends Threshold{

	// Instance Variables
	private ProductCategory productCategory;

	// Getters and Setters
	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	// Logic
	@Override
	public List<Double> purchasesToEvaluateValues(List<Purchase> purchasesToEvaluate) {
		return purchasesToEvaluate.stream().map(p -> p.totalValueOfCheckedItemsWithProductCategory(productCategory)).collect(Collectors.toList());
	}

	@Override
	public Double currentCartValue(Cart cart) {
		return cart.totalValueOfCheckedItemsWithProductCategory(productCategory);
	}

}
