package edu.unq.desapp.groupA.backend.service;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unq.desapp.groupA.backend.csv.CSVFileParser;
import edu.unq.desapp.groupA.backend.csv.CsvResultBasicProductBuilder;
import edu.unq.desapp.groupA.backend.csv.basicStructures.BasicProduct;
import edu.unq.desapp.groupA.backend.model.Brand;
import edu.unq.desapp.groupA.backend.model.Price;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.ProductCategory;
import edu.unq.desapp.groupA.backend.repository.ProductRepository;


@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private PriceService priceService;

	public ProductService() { }

	public ProductService(ProductRepository repository) {
		this.repository = repository;
	}
	
	public ProductService(ProductRepository repository, BrandService brandService, StockService stockService,
						  PriceService priceService) {
		this.repository = repository;
		this.setBrandService(brandService);
		this.setStockService(stockService);
		this.setPriceService(priceService);
	}

	public Product createProduct(Brand brand, List<ProductCategory> categories,
			String name, Price price){
		Product product = new Product();
		product.setBrand(brand);
		product.setCategories(categories);
		product.setName(name);
		product.setPrice(price);
		repository.save(product);
		return product;
	}
	
	public void updateProductsViaCSVFile(String filePath) throws FileNotFoundException {
		List<BasicProduct> basicProducts = CSVFileParser.parseCSVFile(filePath, new CsvResultBasicProductBuilder());
		for (BasicProduct basicProduct : basicProducts) {
			updateOrCreateFromBasicProduct(basicProduct);
		}
	}

	public Product updateOrCreateFromBasicProduct(BasicProduct basicProduct) {
		Product product = this.getRepository().find(basicProduct.getId());
		if (product == null) {
//			product = this.getRepository().save(new Product());
		}
		product.setName(basicProduct.getName());
		Brand brand = getBrandService().findByNameOrCreate(basicProduct.getBrand());
		product.setBrand(brand);
		Price price = getPriceService().updatePriceForProduct(product, basicProduct.getPrice());
		product.setPrice(price);
		getStockService().updateStockForProduct(product, basicProduct.getStock());
//		return this.getRepository().save(product);
		return product;
	}

	// Services
	public BrandService getBrandService() {
		return brandService;
	}

	public void setBrandService(BrandService brandService) {
		this.brandService = brandService;
	}

	public StockService getStockService() {
		return stockService;
	}

	public void setStockService(StockService stockService) {
		this.stockService = stockService;
	}

	public PriceService getPriceService() {
		return priceService;
	}

	public void setPriceService(PriceService priceService) {
		this.priceService = priceService;
	}

	public ProductRepository getRepository() {
		return repository;
	}

	public void setRepository(ProductRepository repository) {
		this.repository = repository;
	}

}
