package edu.unq.desapp.groupA.backend.repository.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.unq.desapp.groupA.backend.model.Brand;
import edu.unq.desapp.groupA.backend.model.Price;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.ProductCategory;
import edu.unq.desapp.groupA.backend.service.BrandService;
import edu.unq.desapp.groupA.backend.service.PriceService;
import edu.unq.desapp.groupA.backend.service.ProductCategoryService;
import edu.unq.desapp.groupA.backend.service.ProductService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/META-INF/spring-persistence-context.xml", "/META-INF/spring-services-context.xml" })
public class ProductServiceTest {
	
	@Autowired
    private ProductService productService;
	
	@Autowired
    private BrandService brandService;	
	
	@Autowired
    private PriceService priceService;
	
	@Autowired
    private ProductCategoryService productCategoryService;

	Brand aBrand;
	Price aPrice;
	ProductCategory aProductCategory;
	
	
	@Before
	public void setup() {
		
		
		// Brand savedBrand = System.out.println(service);
		try {
			aBrand = brandService.save(new Brand("Bagley"));
		} catch (RuntimeException re) {
			// do nothing
		}
		aPrice = priceService.save(new Price(new Double(60)));
		aProductCategory = productCategoryService.save(new ProductCategory("Gaseosa"));
		//System.out.println(savedBrand.getId());
	}
	
	
	@Test
    public void shouldBeGetAEventByID(){
    	Product product = new Product();    	
    	product.addPrice(aPrice);
    	product.setBrand(aBrand);
    	product.addCategory(aProductCategory);
    	productService.save(product);
    	Product retrivedProduct = productService.find(product.getId());
    	assertEquals(product.getId(), retrivedProduct.getId());
    	assertEquals(product.getBrand().getName(), retrivedProduct.getBrand().getName());
    	assertEquals(product.findCurrentPrice().getPrice(), retrivedProduct.findCurrentPrice().getPrice());
    	assertEquals(aProductCategory.getName() ,retrivedProduct.getCategories().iterator().next().getName());
    } 
	
	/*
	@After
    public void drop(){
		productService.deleteAll();
    }
    */
	
	
}
