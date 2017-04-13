package edu.unq.desapp.groupA.backend.service;

import java.util.List;

import edu.unq.desapp.groupA.backend.model.Brand;
import edu.unq.desapp.groupA.backend.model.Price;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.ProductCategory;
import edu.unq.desapp.groupA.backend.repository.ProductRepository;

public class ProductService {

	private ProductRepository repository;
		
	public ProductService(ProductRepository repository) {
		this.repository = repository;
	}


	public Product createProduct(Brand brand, List<ProductCategory> categories,
			String name, Price price){
		Product product = new Product();
		product.setBrand(brand);
		product.setCategories(categories);
		product.setName(name);
		product.setPrice(price);
		repository.save(product);
		return product;
	}

	public ProductRepository getRepository() {
		return repository;
	}

	public void setRepository(ProductRepository repository) {
		this.repository = repository;
	}
}
