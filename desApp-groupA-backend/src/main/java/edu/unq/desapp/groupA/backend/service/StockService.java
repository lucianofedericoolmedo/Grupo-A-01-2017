package edu.unq.desapp.groupA.backend.service;

import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.Stock;
import edu.unq.desapp.groupA.backend.repository.StockRepository;

public class StockService {

	private StockRepository repository;

	public StockService(StockRepository stockRepository) {
		this.repository = stockRepository;
	}
	
	public StockRepository getRepository() {
		return repository;
	}

	public void setRepository(StockRepository repository) {
		this.repository = repository;
	}

	public Stock findByProduct(Product product) {
		return this.getRepository().findByProduct(product);
	}

	public Stock updateStockForProduct(Product product, Integer stockQuantity) {
		Stock stock = findByProduct(product);
		if (stock == null) {
			stock = this.getRepository().save(new Stock(product, stockQuantity));
		}
		return stock;
	}

}
