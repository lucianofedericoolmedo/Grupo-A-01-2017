package edu.unq.desapp.groupA.backend.repository;

import java.util.LinkedList;
import java.util.List;

import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.Stock;

public class StockRepository {

	private List<Stock> stocks;

	public StockRepository() {
		this.stocks = new LinkedList<Stock>();
	}
	
	public List<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

	public Stock save(Stock stock) {
		this.stocks.add(stock);
		return stock;
	}

	public Stock findByProduct(Product product) {
		return this.stocks.stream().filter(stock -> stock.getProduct() == product).findFirst().get();
	}

}
