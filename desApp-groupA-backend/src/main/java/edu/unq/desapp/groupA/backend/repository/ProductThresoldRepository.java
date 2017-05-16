package edu.unq.desapp.groupA.backend.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.unq.desapp.groupA.backend.model.ProductCategoryThreshold;


@Repository
public class ProductThresoldRepository extends HibernateGenericDAO<ProductCategoryThreshold>{

	private List<ProductCategoryThreshold> productThresolds;

	public List<ProductCategoryThreshold> getProductThresolds() {
		return productThresolds;
	}

	public void setProductThresolds(List<ProductCategoryThreshold> productThresolds) {
		this.productThresolds = productThresolds;
	}
	
	public ProductThresoldRepository(){
		this.productThresolds = new ArrayList<ProductCategoryThreshold>();
	}
	
	@Override
	protected Class<ProductCategoryThreshold> getDomainClass() {
		return ProductCategoryThreshold.class;
	}
}
