package edu.unq.desapp.groupA.backend.repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.unq.desapp.groupA.backend.model.Brand;

@Repository
@SuppressWarnings("unchecked")
public class BrandRepository extends HibernateGenericDAO<Brand> implements GenericRepository<Brand> {

	private static final long serialVersionUID = -4425722631916607857L;

	private List<Brand> brands;
	
	public BrandRepository() {
		this.brands = new LinkedList<Brand>();
	}
	
	@Transactional
	public Brand findByName(String brand) {
		String query = "SELECT brand FROM " + this.persistentClass.getName() + " brand WHERE brand.name = '" + brand + "'";
		List<Brand> brands = (List<Brand>) this.getHibernateTemplate().find(query);
		return brands.isEmpty() ? null : brands.get(0);
	}

	@Override
	protected Class<Brand> getDomainClass() {
		return Brand.class;
	}

}
