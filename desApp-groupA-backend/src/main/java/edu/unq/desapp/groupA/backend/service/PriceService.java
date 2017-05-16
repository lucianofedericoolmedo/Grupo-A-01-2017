package edu.unq.desapp.groupA.backend.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unq.desapp.groupA.backend.model.Price;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.repository.PriceRepository;


@Service
public class PriceService extends GenericService<Price> {

	@Autowired
	private PriceRepository repository;
	
	@Autowired
	private ProductService productService;

	public PriceService() { }

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
		Price lastPrice = product.findCurrentPrice();
		lastPrice.setFinishingValidityDate(new Date());
		this.getRepository().save(lastPrice);		
	}

	public Price updatePriceForProduct(Product product, Double price) {
		finishPriceValidityForProduct(product);
		Price newPrice = save(new Price(price));
		product.addPrice(newPrice);
		productService.update(product);
		return newPrice;
	}

}
