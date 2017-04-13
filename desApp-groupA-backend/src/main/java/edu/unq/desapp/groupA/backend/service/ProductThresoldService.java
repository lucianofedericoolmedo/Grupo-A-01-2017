package edu.unq.desapp.groupA.backend.service;

import edu.unq.desapp.groupA.backend.model.ProductThresold;
import edu.unq.desapp.groupA.backend.repository.ProductThresoldRepository;

public class ProductThresoldService {

	private ProductThresoldRepository repository;
	
	public ProductThresoldService(ProductThresoldRepository repository) {
		this.repository = repository;
	}

	public ProductThresoldRepository getRepository() {
		return repository;
	}

	public void setRepository(ProductThresoldRepository repository) {
		this.repository = repository;
	}

	public ProductThresold createProductThreshold() {
		ProductThresold productThresold = new ProductThresold();		
		this.repository.save(productThresold);
		return productThresold;
	}
}
