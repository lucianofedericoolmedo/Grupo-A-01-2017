package edu.unq.desapp.groupA.backend.model.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.Stock;

public class StockTest {
	
	Product aProduct;
	
	@Before
	public void setup() {
		aProduct = new Product();
	}
	
	@Test
	public void createStock() {
		Stock newStock = new Stock();
		
		assertEquals(Stock.class, newStock.getClass());
	}
	
	@Test
	public void testGettersAndSettersStock() {
		Stock newStock = new Stock();
		newStock.setProduct(aProduct);
		newStock.setQuantity(10);
		
		assertEquals(aProduct, newStock.getProduct());
		assertEquals((Integer) 10, newStock.getQuantity());
	}

}
