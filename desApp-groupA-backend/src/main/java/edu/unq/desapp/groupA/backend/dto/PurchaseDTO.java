package edu.unq.desapp.groupA.backend.dto;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import edu.unq.desapp.groupA.backend.model.Purchase;
import edu.unq.desapp.groupA.backend.utils.JSONDateDeserialize;
import edu.unq.desapp.groupA.backend.utils.JSONDateSerialize;

public class PurchaseDTO implements Serializable {
	
	private static final long serialVersionUID = -7793716106716003518L;

	// Instance Variables
	private Long id;
	
	@JsonDeserialize(using = JSONDateDeserialize.class)
    @JsonSerialize(using = JSONDateSerialize.class)
	protected Date purchaseDate;
	
//	private String shippingAddress;
	
	protected Long paidAtCashRegister;
	
	protected String paymentType;
	
	protected Long usedShoppingListId;
	
	protected String usedShoppingListName;
	
	// Constructors
	public PurchaseDTO() {

	}
	
	public PurchaseDTO(Purchase purchase) {
		this.id = purchase.getId();
		this.purchaseDate = purchase.getCreationDate();
//		this.shippingAddress = purchase.getShippingAddress();
		this.paidAtCashRegister = purchase.getTurn().getCashRegisterCode();
		this.paymentType = purchase.getPayment().paymentName();
		this.usedShoppingListId = purchase.getCart().getUsedShoppingList().getId();
		this.usedShoppingListName = purchase.getCart().getUsedShoppingList().getName();
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	/*
	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	*/

	public Long getPaidAtCashRegister() {
		return paidAtCashRegister;
	}

	public void setPaidAtCashRegister(Long paidAtCashRegister) {
		this.paidAtCashRegister = paidAtCashRegister;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Long getUsedShoppingListId() {
		return usedShoppingListId;
	}

	public void setUsedShoppingListId(Long usedShoppingListId) {
		this.usedShoppingListId = usedShoppingListId;
	}

	public String getUsedShoppingListName() {
		return usedShoppingListName;
	}

	public void setUsedShoppingListName(String usedShoppingListName) {
		this.usedShoppingListName = usedShoppingListName;
	}
	
}
