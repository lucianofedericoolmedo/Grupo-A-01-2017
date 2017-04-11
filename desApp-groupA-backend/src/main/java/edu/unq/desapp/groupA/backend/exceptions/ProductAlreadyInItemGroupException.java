package edu.unq.desapp.groupA.backend.exceptions;

import edu.unq.desapp.groupA.backend.model.Product;

public class ProductAlreadyInItemGroupException extends RuntimeException {

	private Product product;
	
	public ProductAlreadyInItemGroupException(Product product, String errorMsg) {
		super(errorMsg);
		this.setProduct(product);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
