package edu.unq.desapp.groupA.backend.repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.unq.desapp.groupA.backend.model.Brand;

@Repository
public class BrandRepository extends HibernateGenericDAO<Brand> {

	private List<Brand> brands;
	
	public BrandRepository() {
		this.brands = new LinkedList<Brand>();
	}
	
	public Brand findByName(String brand) {
		return brands.stream().filter(b -> b.getName().equals(brand)).findFirst().get();
	}

	/*
	public Brand save(Brand brand) {
		this.brands.add(brand);
		return brand;
	}
	*/

	@Override
	protected Class<Brand> getDomainClass() {
		return Brand.class;
	}

}
