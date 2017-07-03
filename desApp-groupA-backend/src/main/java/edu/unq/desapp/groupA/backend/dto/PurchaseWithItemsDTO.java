package edu.unq.desapp.groupA.backend.dto;

import java.util.LinkedList;
import java.util.List;

import edu.unq.desapp.groupA.backend.model.ItemCart;
import edu.unq.desapp.groupA.backend.model.PerProduct;
import edu.unq.desapp.groupA.backend.model.PerProductCategory;
import edu.unq.desapp.groupA.backend.model.PerProductQuantity;
import edu.unq.desapp.groupA.backend.model.Purchase;

public class PurchaseWithItemsDTO extends PurchaseDTO {

	private static final long serialVersionUID = -1393011880331652682L;

	// Instance Variables
	protected List<PurchaseItemHistoryDTO> items;
	
	private List<DiscountItemHistoryDTO> discounts;
	
	// Constructors
	public PurchaseWithItemsDTO() {
		this.items = new LinkedList<PurchaseItemHistoryDTO>();
		this.setDiscounts(new LinkedList<DiscountItemHistoryDTO>());
	}
	
	public PurchaseWithItemsDTO(Purchase purchase) {
		super(purchase);
		this.setDiscounts(addDiscountsIfAny(purchase.getCart().checkedItems()));
		this.items = convertToHistoryItemsDto(purchase.getCart().checkedItems());
	}
	
	// Getters and Setters
	public List<PurchaseItemHistoryDTO> getItems() {
		return items;
	}

	public void setItems(List<PurchaseItemHistoryDTO> items) {
		this.items = items;
	}

	public List<DiscountItemHistoryDTO> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(List<DiscountItemHistoryDTO> discounts) {
		this.discounts = discounts;
	}

	// Logic
	public List<PurchaseItemHistoryDTO> convertToHistoryItemsDto(List<ItemCart> items) {
		List<PurchaseItemHistoryDTO> itemsDto = new LinkedList<PurchaseItemHistoryDTO>();
		for (ItemCart item : items) {
			itemsDto.add(new PurchaseItemHistoryDTO(item));
		}
		return itemsDto;
	}
	
	public List<DiscountItemHistoryDTO> addDiscountsIfAny(List<ItemCart> items) {
		List<DiscountItemHistoryDTO> discounts = new LinkedList<DiscountItemHistoryDTO>();
		for (ItemCart item : items) {
			if (item.getDiscount() != null) {
				DiscountItemHistoryDTO discountDto = null;
				
				if (item.getDiscount() instanceof PerProduct) {
					PerProduct appliedDiscount = (PerProduct) item.getDiscount();
					discountDto = new DiscountItemHistoryDTO(appliedDiscount, appliedDiscount.valueToDiscount(item)); 
				} else if (item.getDiscount() instanceof PerProductQuantity) {
					PerProductQuantity appliedDiscount = (PerProductQuantity) item.getDiscount();
					discountDto = new DiscountItemHistoryDTO(appliedDiscount, appliedDiscount.valueToDiscount(item));
				} else {
					PerProductCategory appliedDiscount = (PerProductCategory) item.getDiscount();
					discountDto = new DiscountItemHistoryDTO(appliedDiscount, appliedDiscount.valueToDiscount(item));
				}
				
				discounts.add(discountDto);
			}
		}
		return discounts;
	}

}
