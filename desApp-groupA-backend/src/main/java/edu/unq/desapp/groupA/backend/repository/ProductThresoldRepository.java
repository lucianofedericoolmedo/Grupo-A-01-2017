package edu.unq.desapp.groupA.backend.repository;

import java.util.ArrayList;
import java.util.List;

import edu.unq.desapp.groupA.backend.model.ProductThresold;


public class ProductThresoldRepository {

	private List<ProductThresold> productThresolds;

	public List<ProductThresold> getProductThresolds() {
		return productThresolds;
	}

	public void setProductThresolds(List<ProductThresold> productThresolds) {
		this.productThresolds = productThresolds;
	}
	
	public ProductThresoldRepository(){
		this.productThresolds = new ArrayList<ProductThresold>();
	}
	
	public void save(ProductThresold productThresold){
		this.productThresolds.add(productThresold);
	}
}
