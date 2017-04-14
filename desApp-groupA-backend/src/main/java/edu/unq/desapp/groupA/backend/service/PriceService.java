package edu.unq.desapp.groupA.backend.service;

import org.joda.time.DateTime;

import edu.unq.desapp.groupA.backend.model.Price;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.repository.PriceRepository;

public class PriceService {

	private PriceRepository repository;
	
	public PriceService(PriceRepository priceRepository) {
		this.setRepository(priceRepository);
	}

	public PriceRepository getRepository() {
		return repository;
	}

	public void setRepository(PriceRepository repository) {
		this.repository = repository;
	}
	
	public void finishPriceValidityForProduct(Product product) {
		Price lastPrice = this.getRepository().findLastPriceByProduct(product);
		lastPrice.setFinishingValidityDate(DateTime.now());
		this.getRepository().save(lastPrice);		
	}
	
	public Price updatePriceForProduct(Product product, Double price) {
		finishPriceValidityForProduct(product);
		Price newPrice = new Price(product, price);
		return this.getRepository().save(newPrice);
	}


}
