package edu.unq.desapp.groupA.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unq.desapp.groupA.backend.model.Brand;
import edu.unq.desapp.groupA.backend.repository.BrandRepository;


@Transactional
@Service
public class BrandService extends GenericService<Brand> {

	@Autowired
	private BrandRepository repository;
	
	public BrandService() {}
	
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

	@Transactional
	public Brand findByNameOrCreate(String brandName) {
		Brand brand = findByName(brandName);
		if (brand == null) {
			brand = this.save(new Brand(brandName));
		}
		return brand;
	}
	
	@Transactional
	private void validateBrandNameExistence(String brandName, Brand brandToCompare) {
		Brand brand = this.findByName(brandName);
		if (brand != null) {
			Boolean sameId = brandToCompare.getId() != null && brandToCompare.getId().equals(brand.getId());
			if (brand.getId() == null || !sameId) {
				throw new RuntimeException("A brand with the name " + brandName + " has already been created");
			}
		}
	}
	
	@Transactional
	public Brand update(Brand newBrand) {
		validateBrandNameExistence(newBrand.getName(), newBrand);
		return super.update(newBrand);
	}
	
	@Transactional
	public Brand save(Brand newBrand) {
		validateBrandNameExistence(newBrand.getName(), newBrand);
		return super.save(newBrand);
	}

}
