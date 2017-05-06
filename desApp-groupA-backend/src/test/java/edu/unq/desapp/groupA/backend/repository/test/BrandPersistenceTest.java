package edu.unq.desapp.groupA.backend.repository.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.unq.desapp.groupA.backend.model.Brand;
import edu.unq.desapp.groupA.backend.service.BrandService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/META-INF/spring-persistence-context.xml", "/META-INF/spring-services-context.xml" })
public class BrandPersistenceTest {

	@Autowired
	private BrandService service;
	
	private Brand aBrand;
	
	@Before
	public void setup() {
		aBrand = new Brand("Bagley");
		
		// Brand savedBrand =
		System.out.println(service);
		service.save(aBrand);
		//System.out.println(savedBrand.getId());
	}
	
	@Test
	public void test_PersistANewBrand() {
		Long idToFind = new Long(1);
		Brand fetchedBrand = service.find(idToFind);
		System.out.println(fetchedBrand.getName());
		System.out.println(fetchedBrand.getId());
	}
	
}
