package edu.unq.desapp.groupA.backend.dto;

import java.util.LinkedList;
import java.util.List;

import edu.unq.desapp.groupA.backend.model.ItemCart;
import edu.unq.desapp.groupA.backend.model.Purchase;

public class PurchaseWithItemsDTO extends PurchaseDTO {

	private static final long serialVersionUID = -1393011880331652682L;

	// Instance Variables
	protected List<PurchaseItemHistoryDTO> items;
	
	// Constructors
	public PurchaseWithItemsDTO() {

	}
	
	public PurchaseWithItemsDTO(Purchase purchase) {
		super(purchase);
		this.items = convertToHistoryItemsDto(purchase.getCart().checkedItems());
	}
	
	// Getters and Setters
	public List<PurchaseItemHistoryDTO> getItems() {
		return items;
	}

	public void setItems(List<PurchaseItemHistoryDTO> items) {
		this.items = items;
	}
	
	// Logic
	public List<PurchaseItemHistoryDTO> convertToHistoryItemsDto(List<ItemCart> items) {
		List<PurchaseItemHistoryDTO> itemsDto = new LinkedList<PurchaseItemHistoryDTO>();
		for (ItemCart item : items) {
			itemsDto.add(new PurchaseItemHistoryDTO(item));
		}
		return itemsDto;
	}


}
