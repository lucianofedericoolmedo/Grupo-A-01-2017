package edu.unq.desapp.groupA.backend.repository;

import org.springframework.stereotype.Repository;

import edu.unq.desapp.groupA.backend.model.ShippingAddress;


@Repository
@SuppressWarnings("unchecked")
public class ShippingAddressRepository extends HibernateGenericDAO<ShippingAddress> implements GenericRepository<ShippingAddress> {

	private static final long serialVersionUID = -4425722631916607857L;

	@Override
	public Class<ShippingAddress> getDomainClass() {
		return ShippingAddress.class;
	}

}
