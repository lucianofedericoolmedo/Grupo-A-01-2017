package edu.unq.desapp.groupA.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unq.desapp.groupA.backend.model.PaymentTurn;
import edu.unq.desapp.groupA.backend.model.PaymentTurnStatus;
import edu.unq.desapp.groupA.backend.repository.GenericRepository;
import edu.unq.desapp.groupA.backend.repository.PaymentTurnRepository;


@Service
public class PaymentTurnService extends GenericService<PaymentTurn> {

	@Autowired
	private PaymentTurnRepository paymentTurnRepository;

	@Override
	public GenericRepository<PaymentTurn> getRepository() {
		return paymentTurnRepository;
	}

	@Transactional
	public void notifyTurnIsNextToPay(Long turnId) {
		PaymentTurn turn = super.find(turnId);
		if (!turn.getStatus().equals(PaymentTurnStatus.CANCELED)) {
			turn.setStatus(PaymentTurnStatus.NEXT_TO_PAY);
			super.save(turn);
		}
	}

	@Transactional
	public PaymentTurn findByCartIdWithState(Long cartId, PaymentTurnStatus status) {
		return paymentTurnRepository.findByCartIdWithState(cartId, status);
	}

	@Transactional
	public void deleteRequestedForCartId(Long cartId) {
		PaymentTurn turn = findByCartIdWithState(cartId, PaymentTurnStatus.REQUESTED);
		if (turn != null) {
			super.delete(turn);
		}
	}

}
