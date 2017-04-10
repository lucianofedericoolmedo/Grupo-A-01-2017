package edu.unq.desapp.groupA.backend.service;

import java.util.ArrayList;
import java.util.List;

import edu.unq.desapp.groupA.backend.model.ProductThresold;

public class ProductThresoldService {

	private List<ProductThresold> productThresolds;
	
	public ProductThresoldService(){
		this.productThresolds = new ArrayList<ProductThresold>();
	}

	public ProductThresold createProductThreshold() {
		ProductThresold productThresold = new ProductThresold();		
		this.productThresolds.add(productThresold);
		return productThresold;
	}
}
