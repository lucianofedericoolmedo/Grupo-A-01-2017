package edu.unq.desapp.groupA.backend.repository;

import org.springframework.stereotype.Repository;

import edu.unq.desapp.groupA.backend.model.PerProductQuantity;

@Repository
public class PerProductQuantityRepository extends HibernateGenericDAO<PerProductQuantity> implements GenericRepository<PerProductQuantity> {

	private static final long serialVersionUID = 5223275347846529204L;

	@Override
	protected Class<PerProductQuantity> getDomainClass() {
		return PerProductQuantity.class;
	}
}
