package edu.unq.desapp.groupA.backend.service;

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
		
		
	}
