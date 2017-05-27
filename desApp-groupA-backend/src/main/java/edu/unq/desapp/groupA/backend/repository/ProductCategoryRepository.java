package edu.unq.desapp.groupA.backend.repository;

import org.springframework.stereotype.Repository;

import edu.unq.desapp.groupA.backend.model.ProductCategory;

@Repository
public class ProductCategoryRepository extends HibernateGenericDAO<ProductCategory> implements GenericRepository<ProductCategory>{

	private static final long serialVersionUID = -1804561481016147344L;
	
	@Override
	public Class<ProductCategory> getDomainClass() {
		return ProductCategory.class;
	}
	
}
