package edu.unq.desapp.groupA.backend.model.test;

import org.junit.Test;

import edu.unq.desapp.groupA.backend.model.Brand;
import edu.unq.desapp.groupA.backend.model.Price;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.ProductCategory;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;

public class ProductTest {

	private Brand aBrand;
	private ProductCategory aProductCategory;
	private List<ProductCategory> productCategories;
	private Price aPrice;

	@Before
	public void setup() {
		aBrand = new Brand();
		aPrice = new Price();
		aProductCategory = new ProductCategory();
		productCategories = new LinkedList<ProductCategory>();
		productCategories.add(aProductCategory);
	}
	
	@Test
	public void createProduct() {
		Product newProduct = new Product();
		
		assertEquals(Product.class, newProduct.getClass());
	}

	@Test
	public void testGettersAndSettersBrand() {
		Product newProduct = new Product();
		
		newProduct.setName("Surtidas");
		newProduct.setBrand(aBrand);
		newProduct.setPrice(aPrice);
		newProduct.setCategories(productCategories);
		
		assertEquals("Surtidas", newProduct.getName());
		assertEquals(aBrand, newProduct.getBrand());
		assertEquals(aPrice, newProduct.getPrice());
		assertEquals(productCategories, newProduct.getCategories());
	}

}
