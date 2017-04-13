package edu.unq.desapp.groupA.backend.service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import edu.unq.desapp.groupA.backend.csv.CSVFileParser;
import edu.unq.desapp.groupA.backend.csv.CsvResultBasicProductBuilder;
import edu.unq.desapp.groupA.backend.csv.basicStructures.BasicProduct;
import edu.unq.desapp.groupA.backend.model.Brand;
import edu.unq.desapp.groupA.backend.model.Price;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.ProductCategory;

public class ProductService {

	private List<Product> products;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public ProductService(){
		this.products = new ArrayList<Product>();
	}
	
	public Product createProduct(Brand brand, List<ProductCategory> categories,
			String name, Price price){
		Product product = new Product();
		product.setBrand(brand);
		product.setCategories(categories);
		product.setName(name);
		product.setPrice(price);
		products.add(product);
		return product;
	}
	
	public void updateProductsVisCSVFile(String filePath) throws FileNotFoundException {
		List<BasicProduct> basicProducts = CSVFileParser.parseCSVFile(filePath, new CsvResultBasicProductBuilder());
		for (BasicProduct basicProduct : basicProducts) {
			updateFromBasicProduct(basicProduct);
		}
	}

	public void updateFromBasicProduct(BasicProduct basicProduct) {
		
	}

}
