package edu.unq.desapp.groupA.backend.repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.unq.desapp.groupA.backend.model.Brand;

@Repository
public class BrandRepository extends HibernateGenericDAO<Brand> implements GenericRepository<Brand> {

	private static final long serialVersionUID = -4425722631916607857L;

	private List<Brand> brands;
	
	public BrandRepository() {
		this.brands = new LinkedList<Brand>();
	}
	
	public Brand findByName(String brand) {
		String query = "FROM Brand brand WHERE brand.name = " + brand;
		return (Brand) this.getHibernateTemplate().find(query);
	}

	@Override
	protected Class<Brand> getDomainClass() {
		return Brand.class;
	}

}
