package edu.unq.desapp.groupA.backend.worker;

import edu.unq.desapp.groupA.backend.model.PaymentTurn;
import edu.unq.desapp.groupA.backend.service.CartService;

public class PaymentCountdownThread extends Thread {

	private static final Integer milisecondsInASecond = 1000;

	private CartService cartService;

	private PaymentTurn turn;

	public PaymentCountdownThread(CartService cartService, PaymentTurn turn) {
		this.cartService = cartService;
		this.turn = turn;
	}

	@Override
	public void run() {
		try {
			Integer secondsToSleep = turn.getStimatedTime();
			Integer milisecondsToSleep = secondsToSleep * milisecondsInASecond;
			Thread.sleep(milisecondsToSleep);
			cartService.createCashRegisterPurchaseFor(turn);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
