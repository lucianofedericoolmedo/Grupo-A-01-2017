package edu.unq.desapp.groupA.backend.repository;

import java.util.ArrayList;
import java.util.List;

import edu.unq.desapp.groupA.backend.model.Product;

public class ProductRepository {

	private List<Product> products;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public void save(Product product){
		this.products.add(product);
	}
	
	public ProductRepository(){
		this.products = new ArrayList<Product>();
	}
}
