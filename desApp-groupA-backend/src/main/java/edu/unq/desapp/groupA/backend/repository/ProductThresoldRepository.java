package edu.unq.desapp.groupA.backend.repository;

import java.util.ArrayList;
import java.util.List;

import edu.unq.desapp.groupA.backend.model.ProductCategoryThreshold;


public class ProductThresoldRepository {

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
	
	public void save(ProductCategoryThreshold productThresold){
		this.productThresolds.add(productThresold);
	}
}
