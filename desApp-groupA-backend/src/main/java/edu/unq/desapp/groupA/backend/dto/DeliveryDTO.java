package edu.unq.desapp.groupA.backend.dto;

import edu.unq.desapp.groupA.backend.model.PaymentTurn;
import edu.unq.desapp.groupA.backend.model.ShippingAddress;

public class DeliveryDTO {

	public PaymentTurn turn;
	public ShippingAddress shippingAddress;
	
	public PaymentTurn getTurn() {
		return turn;
	}
	public void setTurn(PaymentTurn turn) {
		this.turn = turn;
	}
	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	
}
