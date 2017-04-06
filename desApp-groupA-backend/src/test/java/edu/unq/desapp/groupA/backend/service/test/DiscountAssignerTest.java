package edu.unq.desapp.groupA.backend.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.unq.desapp.groupA.backend.model.Discount;
import edu.unq.desapp.groupA.backend.model.ItemCart;
import edu.unq.desapp.groupA.backend.model.PerProduct;
import edu.unq.desapp.groupA.backend.model.PerProductCategory;
import edu.unq.desapp.groupA.backend.model.Priority;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.ProductCategory;
import edu.unq.desapp.groupA.backend.service.DiscountAssigner;

public class DiscountAssignerTest {

	private DiscountAssigner discountAssigner;
	private ItemCart itemCartForDiscountPerProduct;
	private Product aProductForDiscountPerProduct;
	private PerProduct perProductDiscountHighPriority;
	private ProductCategory aCategoryForDiscountPerCategory;
	private PerProductCategory perCategoryDiscountMediumPriority;
	private Product aNonValidForDiscountProduct0;
	private Product aNonValidForDiscountProduct1;
	private Product aNonValidForDiscountProduct2;
	private ItemCart itemCartForDiscountPerCategory;
	private Product aProductForDiscountPerCategory;
	private ItemCart itemCartWithNonValidProduct0;
	private ItemCart itemCartWithNonValidProduct1;
	private ItemCart itemCartWithNonValidProduct2;
	private Product aProductForDiscountPerProductAndPerCategory;
	private ItemCart itemCartForDiscountPerProductAndPerCategory;

	@Before
	public void setup() {
		discountAssigner = new DiscountAssigner();
		
		aProductForDiscountPerProduct = new Product();
		perProductDiscountHighPriority = new PerProduct();
		perProductDiscountHighPriority.setProductForDiscount(aProductForDiscountPerProduct);
		perProductDiscountHighPriority.setPriority(Priority.HIGH);

		itemCartForDiscountPerProduct = new ItemCart();
		itemCartForDiscountPerProduct.setProduct(aProductForDiscountPerProduct);

		aProductForDiscountPerCategory = new Product();
		aCategoryForDiscountPerCategory = new ProductCategory();
		aProductForDiscountPerCategory.addCategory(aCategoryForDiscountPerCategory);
		perCategoryDiscountMediumPriority = new PerProductCategory();
		perCategoryDiscountMediumPriority.setCategoryForDiscount(aCategoryForDiscountPerCategory);
		perCategoryDiscountMediumPriority.setPriority(Priority.MEDIUM);
		
		aProductForDiscountPerProductAndPerCategory = new Product();
		aProductForDiscountPerProductAndPerCategory.addCategory(aCategoryForDiscountPerCategory);
		itemCartForDiscountPerProductAndPerCategory = new ItemCart();
		itemCartForDiscountPerProductAndPerCategory.setProduct(aProductForDiscountPerProductAndPerCategory);

		itemCartForDiscountPerCategory = new ItemCart();
		itemCartForDiscountPerCategory.setProduct(aProductForDiscountPerCategory);

		aNonValidForDiscountProduct0 = new Product();
		itemCartWithNonValidProduct0 = new ItemCart();
		itemCartWithNonValidProduct0.setProduct(aNonValidForDiscountProduct0);
		
		aNonValidForDiscountProduct1 = new Product();
		itemCartWithNonValidProduct1 = new ItemCart();
		itemCartWithNonValidProduct1.setProduct(aNonValidForDiscountProduct1);

		aNonValidForDiscountProduct2 = new Product();
		itemCartWithNonValidProduct2 = new ItemCart();
		itemCartWithNonValidProduct2.setProduct(aNonValidForDiscountProduct2);		
	}
	
	@Test
	public void test_AssignSpecifiedDiscounts_TwoValidItems() {
		List<ItemCart> items = new LinkedList<ItemCart>();
		items.add(itemCartForDiscountPerCategory);
		items.add(itemCartForDiscountPerProduct);
		
		List<Discount> discounts = new LinkedList<Discount>();
		discounts.add(perProductDiscountHighPriority);
		discounts.add(perCategoryDiscountMediumPriority);
		
		List<ItemCart> itemsWithoutDiscounts = discountAssigner.assignSpecifiedDiscounts(items, discounts);
		
		assertTrue(itemsWithoutDiscounts.isEmpty());
		assertEquals(perCategoryDiscountMediumPriority, itemCartForDiscountPerCategory.getDiscount());
		assertEquals(perProductDiscountHighPriority, itemCartForDiscountPerProduct.getDiscount());
	}

	@Test
	public void test_AssignSpecifiedDiscounts_TwoValidItems_OneNonValid() {
		List<ItemCart> items = new LinkedList<ItemCart>();
		items.add(itemCartForDiscountPerCategory);
		items.add(itemCartForDiscountPerProduct);
		items.add(itemCartWithNonValidProduct0);
		
		List<Discount> discounts = new LinkedList<Discount>();
		discounts.add(perProductDiscountHighPriority);
		discounts.add(perCategoryDiscountMediumPriority);
		
		List<ItemCart> itemsWithoutDiscounts = discountAssigner.assignSpecifiedDiscounts(items, discounts);
		
		assertEquals(1, itemsWithoutDiscounts.size());
		assertEquals(itemCartWithNonValidProduct0, itemsWithoutDiscounts.get(0));
		assertEquals(perCategoryDiscountMediumPriority, itemCartForDiscountPerCategory.getDiscount());
		assertEquals(perProductDiscountHighPriority, itemCartForDiscountPerProduct.getDiscount());
		assertFalse(itemCartWithNonValidProduct0.hasAppliedDiscount());
		
	}

	@Test
	public void test_AssignSpecifiedDiscounts_OneValidItemWithBothDiscountsApplicables() {
		perProductDiscountHighPriority.setProductForDiscount(aProductForDiscountPerProductAndPerCategory);

		List<ItemCart> items = new LinkedList<ItemCart>();
		items.add(itemCartForDiscountPerProductAndPerCategory);

		List<Discount> discounts = new LinkedList<Discount>();
		discounts.add(perProductDiscountHighPriority);
		discounts.add(perCategoryDiscountMediumPriority);

		List<ItemCart> itemsWithoutDiscounts = discountAssigner.assignSpecifiedDiscounts(items, discounts);

		assertTrue(itemsWithoutDiscounts.isEmpty());
		// It is the discount per product because it is first in the list.
		assertEquals(perProductDiscountHighPriority, itemCartForDiscountPerProductAndPerCategory.getDiscount());

	}

	@Test
	public void test_AssignSpecifiedDiscounts_TwoValidItems_ThreeNonValid() {
		List<ItemCart> items = new LinkedList<ItemCart>();
		items.add(itemCartForDiscountPerCategory);
		items.add(itemCartForDiscountPerProduct);
		items.add(itemCartWithNonValidProduct0);
		items.add(itemCartWithNonValidProduct1);
		items.add(itemCartWithNonValidProduct2);
		
		List<Discount> discounts = new LinkedList<Discount>();
		discounts.add(perProductDiscountHighPriority);
		discounts.add(perCategoryDiscountMediumPriority);
		
		List<ItemCart> itemsWithoutDiscounts = discountAssigner.assignSpecifiedDiscounts(items, discounts);
		
		assertEquals(3, itemsWithoutDiscounts.size());
		assertEquals(perCategoryDiscountMediumPriority, itemCartForDiscountPerCategory.getDiscount());
		assertEquals(perProductDiscountHighPriority, itemCartForDiscountPerProduct.getDiscount());
		assertFalse(itemCartWithNonValidProduct0.hasAppliedDiscount());
		assertFalse(itemCartWithNonValidProduct1.hasAppliedDiscount());
		assertFalse(itemCartWithNonValidProduct2.hasAppliedDiscount());
		
	}

}
