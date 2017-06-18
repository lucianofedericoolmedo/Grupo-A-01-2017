package edu.unq.desapp.groupA.backend.repository;

import org.springframework.stereotype.Repository;

import edu.unq.desapp.groupA.backend.model.PaymentTurn;

@Repository
public class PaymentTurnRepository extends HibernateGenericDAO<PaymentTurn> implements GenericRepository<PaymentTurn> {

	private static final long serialVersionUID = -4425722631916607857L;

	@Override
	public Class<PaymentTurn> getDomainClass() {
		return PaymentTurn.class;
	}

}
