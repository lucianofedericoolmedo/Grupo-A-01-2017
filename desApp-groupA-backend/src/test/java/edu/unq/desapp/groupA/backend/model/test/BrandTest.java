package edu.unq.desapp.groupA.backend.model.test;

import org.junit.Test;

import edu.unq.desapp.groupA.backend.model.Brand;
import static org.junit.Assert.assertEquals;

public class BrandTest {

	@Test
	public void testCreateBrand() {
		Brand newBrand = new Brand();
		
		assertEquals(Brand.class, newBrand.getClass());
	}

	@Test
	public void testGettersAndSettersBrand() {
		Brand newBrand = new Brand();
		newBrand.setName("Coca Cola");
		
		assertEquals("Coca Cola", newBrand.getName());
	}

}
