package edu.unq.desapp.groupA.backend.service.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.unq.desapp.groupA.backend.csv.basicStructures.BasicProduct;
import edu.unq.desapp.groupA.backend.model.Brand;
import edu.unq.desapp.groupA.backend.model.Price;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.Stock;
import edu.unq.desapp.groupA.backend.service.BrandService;
import edu.unq.desapp.groupA.backend.service.PriceService;
import edu.unq.desapp.groupA.backend.service.ProductService;
import edu.unq.desapp.groupA.backend.service.StockService;


@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "test")
@ContextConfiguration({ "/META-INF/spring-persistence-context.xml", "/META-INF/spring-services-context.xml" })
public class ProductServiceTest {

	@Autowired
	private ProductService productService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private StockService stockService;
	@Autowired
	private PriceService priceService;

	private BasicProduct basicProduct;
	
	private Brand aBrand;
	private Stock aStock;
	private Integer stockQuantity;
	private Price aPrice;
	private Product aProduct;
	private Double priceValue;
	private String productName;
	private String brandName;

	@Before
	public void setup() {
		productName = "Surtidas";
		brandName = "Bagley";
		stockQuantity = 10;
		priceValue = 15.00;
		
		aProduct = new Product();
		aBrand = new Brand();
		
		brandService.save(aBrand);
		
		aBrand.setName(brandName);
		aStock = new Stock();
		aStock.setQuantity(stockQuantity);
		aStock.setProduct(aProduct);
		aPrice = new Price(priceValue);
		
		aProduct.setName(productName);
		aProduct.setBrand(aBrand);
		aProduct.addPrice(aPrice);
		
		productService.save(aProduct);

		basicProduct = new BasicProduct();
		/*
		productRepository = mock(ProductRepository.class);
		when(productRepository.findById(anyLong())).thenReturn(aProduct);
		when(productRepository.save(aProduct)).thenReturn(aProduct);

		brandService = mock(BrandService.class);
		when(brandService.findByNameOrCreate(anyString())).thenReturn(aBrand);
		stockService = mock(StockService.class);
		when(stockService.updateStockForProduct(aProduct, stockQuantity)).thenReturn(aStock);
		priceService = mock(PriceService.class);
		when(priceService.updatePriceForProduct(aProduct, priceValue)).thenReturn(aPrice);
	*/

	}

	@Test
	public void test_BuildAProductFromABasicProduct() {
		basicProduct.setId(new Long(0));
		basicProduct.setName(productName);
		basicProduct.setBrand(brandName);
		basicProduct.setPrice(priceValue);
		basicProduct.setStock(stockQuantity);
		basicProduct.setImage("image-url");
		
		Product resultingProduct = productService.updateOrCreateFromBasicProduct(basicProduct);
		assertEquals(productName, resultingProduct.getName());
		assertEquals(brandName, resultingProduct.getBrand().getName());
		assertEquals(priceValue, resultingProduct.getCurrentPrice().getPrice());
	}
}
