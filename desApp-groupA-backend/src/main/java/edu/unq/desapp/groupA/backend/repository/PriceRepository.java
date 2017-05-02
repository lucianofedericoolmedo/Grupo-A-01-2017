package edu.unq.desapp.groupA.backend.repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.unq.desapp.groupA.backend.model.Price;
import edu.unq.desapp.groupA.backend.model.Product;


@Repository
public class PriceRepository extends HibernateGenericDAO<Price> {

	private List<Price> prices;
	
	public PriceRepository() {
		this.setPrices(new LinkedList<Price>());
	}

	public List<Price> getPrices() {
		return prices;
	}

	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}

	public Price findLastPriceByProduct(Product product) {
		return prices.stream().filter(price -> price.getProduct() == product).max((priceA, priceB) -> priceA.getStartingValidityDate().compareTo(priceB.getStartingValidityDate())).get();
	}

	@Override
	protected Class<Price> getDomainClass() {
		return Price.class;
	}
	
}
