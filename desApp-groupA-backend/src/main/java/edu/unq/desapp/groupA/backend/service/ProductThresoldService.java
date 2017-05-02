package edu.unq.desapp.groupA.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unq.desapp.groupA.backend.model.ProductCategoryThreshold;
import edu.unq.desapp.groupA.backend.repository.ProductThresoldRepository;


@Service
public class ProductThresoldService {

	@Autowired
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

	public ProductCategoryThreshold createProductThreshold() {
		ProductCategoryThreshold productThresold = new ProductCategoryThreshold();		
		this.repository.save(productThresold);
		return productThresold;
	}
}
