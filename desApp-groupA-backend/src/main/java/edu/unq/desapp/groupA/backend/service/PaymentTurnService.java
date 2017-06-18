package edu.unq.desapp.groupA.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unq.desapp.groupA.backend.model.PaymentTurn;
import edu.unq.desapp.groupA.backend.model.PaymentTurnStatus;
import edu.unq.desapp.groupA.backend.repository.GenericRepository;
import edu.unq.desapp.groupA.backend.repository.PaymentTurnRepository;


@Service
@Transactional
public class PaymentTurnService extends GenericService<PaymentTurn> {

	@Autowired
	private PaymentTurnRepository paymentTurnRepository;

	@Override
	public GenericRepository<PaymentTurn> getRepository() {
		return paymentTurnRepository;
	}

	public void notifyTurnIsNextToPay(Long turnId) {
		PaymentTurn turn = super.find(turnId);
		if (!turn.getStatus().equals(PaymentTurnStatus.CANCELED)) {
			turn.setStatus(PaymentTurnStatus.NEXT_TO_PAY);
			super.save(turn);
		}
	}

}
