package edu.unq.desapp.groupA.backend.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.unq.desapp.groupA.backend.model.ProductCategory;

@Repository
public class ProductCategoryRepository extends HibernateGenericDAO<ProductCategory> implements GenericRepository<ProductCategory>{

	private static final long serialVersionUID = -1804561481016147344L;

	@Override
	public Class<ProductCategory> getDomainClass() {
		return ProductCategory.class;
	}

	public ProductCategory findByName(String nameToFind) {
		String query = "SELECT productCategory FROM " + this.persistentClass.getName() + " productCategory WHERE productCategory.name = '" + nameToFind + "'";
		List<ProductCategory> categories = (List<ProductCategory>) this.getHibernateTemplate().find(query);
		return categories.isEmpty() ? null : categories.get(0);
	}

}
