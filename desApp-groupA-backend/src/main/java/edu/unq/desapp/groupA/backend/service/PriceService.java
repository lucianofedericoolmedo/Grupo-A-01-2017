package edu.unq.desapp.groupA.backend.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	private void finishPriceValidity(Price price) {
		price.setFinishingValidityDate(new Date());
		this.getRepository().save(price);
	}
	
	@Transactional
	public void finishPriceValidityForProductIfAny(Product product) {
		Price lastPrice = product.getCurrentPrice();
		// TODO : Maybe refac for using an Exception.
		if (lastPrice != null) {
			finishPriceValidity(lastPrice);
		}
	}

	@Transactional
	public Price updatePriceForProduct(Product product, Double price) {
		if (!currentPriceEqualsNewPrice(product, price)) {
			finishPriceValidityForProductIfAny(product);
			Price newPrice = save(new Price(price));
			product.addPrice(newPrice);
			productService.update(product);
			return newPrice;
		} else {
			return product.getCurrentPrice();
		}
	}

	@Transactional
	private boolean currentPriceEqualsNewPrice(Product product, Double price) {
		Price lastPrice = product.getCurrentPrice();
		if (lastPrice == null) {
			return false;
		} else {
			return lastPrice.getPrice().equals(price);
		}
	}

}
