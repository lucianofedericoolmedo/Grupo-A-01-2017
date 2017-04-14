package edu.unq.desapp.groupA.backend.service;

import edu.unq.desapp.groupA.backend.model.Brand;
import edu.unq.desapp.groupA.backend.repository.BrandRepository;

public class BrandService {

	private BrandRepository repository;
	
	public BrandService(BrandRepository brandRepository) {
		this.setRepository(brandRepository);
	}

	public BrandRepository getRepository() {
		return repository;
	}

	public void setRepository(BrandRepository repository) {
		this.repository = repository;
	}

	public Brand findByName(String brand) {
		return this.getRepository().findByName(brand);
	}

	public Brand findByNameOrCreate(String brandName) {
		Brand brand = findByName(brandName);
		if (brand == null) {
			brand = this.getRepository().save(new Brand(brandName));
		}
		return brand;
	}

}
