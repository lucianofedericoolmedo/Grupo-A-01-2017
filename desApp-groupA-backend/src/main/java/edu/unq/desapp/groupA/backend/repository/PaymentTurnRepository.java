package edu.unq.desapp.groupA.backend.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.unq.desapp.groupA.backend.model.PaymentTurn;
import edu.unq.desapp.groupA.backend.model.PaymentTurnStatus;

@Repository
@SuppressWarnings("unchecked")
public class PaymentTurnRepository extends HibernateGenericDAO<PaymentTurn> implements GenericRepository<PaymentTurn> {

	private static final long serialVersionUID = -4425722631916607857L;

	@Override
	public Class<PaymentTurn> getDomainClass() {
		return PaymentTurn.class;
	}

	public PaymentTurn findByCartId(Long cartId) {
		String query = "SELECT turn FROM " + persistentClass.getName() + " turn "
						+ "WHERE turn.cartId = ?1 "; 
		List<PaymentTurn> turns = (List<PaymentTurn>) this.getHibernateTemplate().find(query, cartId);
		if (turns.isEmpty()) {
			return null;
		} else {
			return turns.get(0);
		}
	}
	
	public PaymentTurn findByCartIdWithState(Long cartId, PaymentTurnStatus status) {
		String query = "SELECT turn FROM " + persistentClass.getName() + " turn "
						+ "WHERE turn.cartId = ? "
						+ "AND turn.status = ? "; 
		List<PaymentTurn> turns = (List<PaymentTurn>) this.getHibernateTemplate().find(query, cartId, status);
		if (turns.isEmpty()) {
			return null;
		} else {
			return turns.get(0);
		}
	}

}
