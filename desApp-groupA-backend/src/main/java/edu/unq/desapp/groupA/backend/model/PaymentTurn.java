package edu.unq.desapp.groupA.backend.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "payment_turns")
public class PaymentTurn extends PersistenceEntity {

	private static final long serialVersionUID = 6857193399134457911L;

	// Instance Variables
	private Long cartId;

	private Long cashRegisterCode;

	private Date requestTimestamp;

	@Enumerated(EnumType.STRING)
	private PaymentTurnStatus status;
	
	private Integer stimatedTime;

	// Constructors
	public PaymentTurn() {
		this.requestTimestamp = new Date();
		this.setStatus(PaymentTurnStatus.REQUESTED);
	}

	public PaymentTurn(Long cartId, Long cashRegisterCode, Integer stimatedTime) {
		this.requestTimestamp = new Date();
		this.cartId = cartId;
		this.cashRegisterCode = cashRegisterCode;
		this.stimatedTime = stimatedTime;
		this.setStatus(PaymentTurnStatus.REQUESTED);
	}

	// Getters and Setters
	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Long getCashRegisterCode() {
		return cashRegisterCode;
	}

	public void setCashRegisterId(Long cashRegisterCode) {
		this.cashRegisterCode = cashRegisterCode;
	}

	public Date getRequestTimestamp() {
		return requestTimestamp;
	}

	public void setRequestTimestamp(Date requestTimestamp) {
		this.requestTimestamp = requestTimestamp;
	}

	public Integer getStimatedTime() {
		return stimatedTime;
	}

	public void setStimatedTime(Integer stimatedTime) {
		this.stimatedTime = stimatedTime;
	}

	public PaymentTurnStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentTurnStatus status) {
		this.status = status;
	}

}
