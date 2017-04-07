package edu.unq.desapp.groupA.backend.model.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.unq.desapp.groupA.backend.model.ProductCategory;

public class ProductCategoryTest {
	
	@Test
	public void createProductCategory() {
		ProductCategory newProductCategory = new ProductCategory();
		
		assertEquals(ProductCategory.class, newProductCategory.getClass());
	}
	
	@Test
	public void testGettersAndSettersCategory() {
		ProductCategory newProductCategory = new ProductCategory();
		newProductCategory.setName("Bebidas");
		
		assertEquals("Bebidas", newProductCategory.getName());
	}

}
