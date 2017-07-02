package edu.unq.desapp.groupA.backend.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unq.desapp.groupA.backend.dto.DiscountDTO;
import edu.unq.desapp.groupA.backend.model.Discount;
import edu.unq.desapp.groupA.backend.model.PerProduct;
import edu.unq.desapp.groupA.backend.model.PerProductCategory;
import edu.unq.desapp.groupA.backend.model.PerProductQuantity;
import edu.unq.desapp.groupA.backend.model.Priority;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.ProductCategory;
import edu.unq.desapp.groupA.backend.repository.DiscountRepository;
import edu.unq.desapp.groupA.backend.repository.GenericRepository;


@Service
public class DiscountService extends GenericService<Discount> {

	// Repositories and Services
	@Autowired
	private DiscountRepository repository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	// Logic
	@Override
	public GenericRepository<Discount> getRepository() {
		return repository;
	}

	@Transactional
	public List<Discount> fetchAllDiscountsPossibles() {
		List<Discount> discounts = new LinkedList<Discount>();
		discounts.add(new PerProduct());
		discounts.add(new PerProductCategory());
		discounts.add(new PerProductQuantity());
		return discounts;
	}

	@Transactional
	public void createPerProduct(DiscountDTO discount) {
		Product product = productService.find(discount.getProduct().getId());
		PerProduct newDiscount = new PerProduct(product, 
												discount.getStartingDate(), 
												discount.getFinishingDate(), 
												discount.getPercentagePerProductToDiscount(), 
												discount.getPriority());
		super.save(newDiscount);
	}

	@Transactional
	public void createPerProductCategory(DiscountDTO discount) {
		ProductCategory productCategory = productCategoryService.find(discount.getProductCategory().getId());
		PerProductCategory newDiscount = new PerProductCategory(productCategory,
																discount.getStartingDate(), 
																discount.getFinishingDate(), 
																discount.getPercentagePerProductToDiscount(), 
																discount.getPriority());
		super.save(newDiscount);
	}

	@Transactional
	public void createPerProductQuantity(DiscountDTO discount) {
		Product product = productService.find(discount.getProduct().getId());
		PerProductQuantity newDiscount = new PerProductQuantity(product,
																discount.getQuantity(),
																discount.getStartingDate(), 
																discount.getFinishingDate(), 
																discount.getPercentagePerProductToDiscount(), 
																discount.getPriority());
		super.save(newDiscount);
	}

	@Transactional
	public List<Discount> findActiveDiscountsByPriority(Priority priority) {
		Date today = new Date();
		return repository.findActivesForDayWithPriority(today, priority);
	}

}
