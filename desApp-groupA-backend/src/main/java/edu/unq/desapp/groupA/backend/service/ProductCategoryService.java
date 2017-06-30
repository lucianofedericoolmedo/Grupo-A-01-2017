package edu.unq.desapp.groupA.backend.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unq.desapp.groupA.backend.model.ProductCategory;
import edu.unq.desapp.groupA.backend.repository.ProductCategoryRepository;



@Service
public class ProductCategoryService extends GenericService<ProductCategory> {

	@Autowired
	private ProductCategoryRepository repository;

	@Override
	public ProductCategoryRepository getRepository() {
		return repository;
	}

	private ProductCategory findByName(String categoryName) {
		return this.getRepository().findByName(categoryName);
	}
	
	@Transactional
	private void validateCategoryNameExistence(String categoryName, ProductCategory categoryToCompare) {
		ProductCategory productCategory = this.findByName(categoryToCompare.getName());
		if (productCategory != null) {
			Boolean sameId = categoryToCompare.getId() != null && categoryToCompare.getId().equals(productCategory.getId());
			if (categoryToCompare.getId() == null || !sameId) {
				throw new RuntimeException("A category with the name " + categoryName + " has already been created");
			}
		}
	}
	
	@Transactional
	public ProductCategory update(ProductCategory productCategory) {
		validateCategoryNameExistence(productCategory.getName(), productCategory);
		return super.update(productCategory);
	}
	
	@Transactional
	public ProductCategory save(ProductCategory productCategory) {
		validateCategoryNameExistence(productCategory.getName(), productCategory);
		return super.save(productCategory);
	}
	
}
