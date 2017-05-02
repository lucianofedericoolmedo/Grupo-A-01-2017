package edu.unq.desapp.groupA.backend.repository.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import edu.unq.desapp.groupA.backend.model.Brand;
import edu.unq.desapp.groupA.backend.repository.BrandRepository;


@ContextConfiguration({ "/META-INF/spring-persistence-context.xml" })
public class BrandPersistenceTest {

	private BrandRepository repository;
	
	private Brand aBrand;
	
	@Before
	public void setup() {
		repository = new BrandRepository();
		aBrand = new Brand("Bagley");
		
		Brand savedBrand = repository.save(aBrand);
		System.out.println(savedBrand.getId());
	}
	
	@Test
	public void test_PersistANewBrand() {
		Long idToFind = new Long(1);
		Brand fetchedBrand = repository.find(idToFind);
		System.out.println(fetchedBrand.getName());
		System.out.println(fetchedBrand.getId());
	}
	
}
