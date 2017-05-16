package edu.unq.desapp.groupA.backend.repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.Stock;


@Repository
public class StockRepository extends HibernateGenericDAO<Stock> {

	private static final long serialVersionUID = -2793867362203643967L;

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

	public Stock findByProduct(Product product) {
		String prodId = product.getId().toString();
		return (Stock) getHibernateTemplate().find("FROM Stock stock WHERE stock.product.id = " + prodId);
	}

	@Override
	protected Class<Stock> getDomainClass() {
		return Stock.class;
	}

}
