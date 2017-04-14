package edu.unq.desapp.groupA.backend.repository;

import java.util.ArrayList;
import java.util.List;

import edu.unq.desapp.groupA.backend.model.ProductCategoryThresold;


public class ProductThresoldRepository {

	private List<ProductCategoryThresold> productThresolds;

	public List<ProductCategoryThresold> getProductThresolds() {
		return productThresolds;
	}

	public void setProductThresolds(List<ProductCategoryThresold> productThresolds) {
		this.productThresolds = productThresolds;
	}
	
	public ProductThresoldRepository(){
		this.productThresolds = new ArrayList<ProductCategoryThresold>();
	}
	
	public void save(ProductCategoryThresold productThresold){
		this.productThresolds.add(productThresold);
	}
}
