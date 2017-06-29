package edu.unq.desapp.groupA.backend.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "thresholds_product_category")
public class ProductCategoryThreshold  extends Threshold{

	private static final long serialVersionUID = 4868683580506959194L;
	
	private static final String name = "Per Product Category";
	
	// Instance Variables
	@ManyToOne
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

	@Override
	public String getName() {
		return name;
	}

}
