package edu.unq.desapp.groupA.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.Stock;
import edu.unq.desapp.groupA.backend.repository.StockRepository;


@Service
public class StockService extends GenericService<Stock> {

	@Autowired
	private StockRepository repository;

	public StockService() { }
	
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
			return super.save(new Stock(product, stockQuantity));
		} else {
			stock.setQuantity(stockQuantity);
			return super.update(stock);
		}
	}

	public Stock findForProductId(Long productId) {
		return this.repository.findforProductId(productId);
	}

}
