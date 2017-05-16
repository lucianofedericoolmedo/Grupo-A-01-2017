package edu.unq.desapp.groupA.backend.service.provider;

import edu.unq.desapp.groupA.backend.model.Brand;
import edu.unq.desapp.groupA.backend.model.Price;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.ProductCategory;

public class ProductFactory {

	public static Product exampleNewElem(String name){
		Price price = PriceFactory.exampleNewElem(20.0);
		Brand aBrand = BrandFactory.exampleNewElem();
		ProductCategory productCategory = new ProductCategory("Candy");
		return newElem(name,price, aBrand, productCategory);
	}
	
	public static Product newElem(String name,Price price, Brand aBrand, ProductCategory productCategory){
		Product newProduct = new Product();
		newProduct.setName(name);
		newProduct.addPrice(price);
		newProduct.setBrand(aBrand);;
		newProduct.addCategory(productCategory);
		return newProduct;
	}
}
