package edu.unq.desapp.groupA.backend.worker;

import edu.unq.desapp.groupA.backend.model.PaymentTurn;
import edu.unq.desapp.groupA.backend.service.PaymentTurnService;

public class PaymentCountdownThread extends Thread {

	private static final Integer milisecondsInASecond = 1000;

	private PaymentTurnService paymentTurnService;

	private PaymentTurn turn;

	public PaymentCountdownThread(PaymentTurnService paymentTurnService, PaymentTurn turn) {
		this.paymentTurnService = paymentTurnService;
		this.turn = turn;
	}

	@Override
	public void run() {
		try {
			Integer secondsToSleep = turn.getStimatedTime();
			Integer milisecondsToSleep = secondsToSleep * milisecondsInASecond;
			Thread.sleep(milisecondsToSleep);
			paymentTurnService.notifyTurnIsNextToPay(this.turn.getId());
			return;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
