package edu.unq.desapp.groupA.backend.service.test;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import edu.unq.desapp.groupA.backend.csv.basicStructures.BasicProduct;
import edu.unq.desapp.groupA.backend.model.Brand;
import edu.unq.desapp.groupA.backend.model.Price;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.Stock;
import edu.unq.desapp.groupA.backend.repository.ProductRepository;
import edu.unq.desapp.groupA.backend.service.BrandService;
import edu.unq.desapp.groupA.backend.service.PriceService;
import edu.unq.desapp.groupA.backend.service.ProductService;
import edu.unq.desapp.groupA.backend.service.StockService;

public class ProductServiceTest {

	private ProductService productService;
	private ProductRepository productRepository;
	private BrandService brandService;
	private StockService stockService;
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
		aBrand.setName(brandName);
		aStock = new Stock();
		aStock.setQuantity(stockQuantity);
		aPrice = new Price(priceValue);

		basicProduct = new BasicProduct();

		productRepository = mock(ProductRepository.class);
		when(productRepository.find(anyLong())).thenReturn(aProduct);
//		when(productRepository.save(aProduct)).thenReturn(aProduct);

		brandService = mock(BrandService.class);
		when(brandService.findByNameOrCreate(anyString())).thenReturn(aBrand);
		stockService = mock(StockService.class);
		when(stockService.updateStockForProduct(aProduct, stockQuantity)).thenReturn(aStock);
		priceService = mock(PriceService.class);
		when(priceService.updatePriceForProduct(aProduct, priceValue)).thenReturn(aPrice);

		productService = new ProductService(productRepository);
		productService.setBrandService(brandService);
		productService.setPriceService(priceService);
		productService.setStockService(stockService);
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
		//assertEquals(priceValue, resultingProduct.getPrice().getPrice());
	}
}
