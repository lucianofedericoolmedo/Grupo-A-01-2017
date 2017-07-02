package edu.unq.desapp.groupA.backend.repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.unq.desapp.groupA.backend.model.Discount;
import edu.unq.desapp.groupA.backend.model.Priority;


@Repository
public class DiscountRepository extends HibernateGenericDAO<Discount> {

	private static final long serialVersionUID = 7255438698679596488L;
	
	@Override
	public Class<Discount> getDomainClass() {
		return Discount.class;
	}

	@SuppressWarnings("unchecked")
	public List<Discount> findActivesForDayWithPriority(Date today, Priority priority) {
		String query = "FROM " + persistentClass.getName() + " discount "
						+ "WHERE discount.priority = ? "
						+ "AND discount.startingDate <= ? "
						+ "AND discount.finishingDate >= ? ";
		return (List<Discount>) this.getHibernateTemplate().find(query, priority, today, today);
	}

}
