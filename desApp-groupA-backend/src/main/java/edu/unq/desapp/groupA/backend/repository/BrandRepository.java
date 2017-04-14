package edu.unq.desapp.groupA.backend.repository;

import java.util.LinkedList;
import java.util.List;

import edu.unq.desapp.groupA.backend.model.Brand;

public class BrandRepository {

	private List<Brand> brands;
	
	public BrandRepository() {
		this.brands = new LinkedList<Brand>();
	}
	
	public Brand findByName(String brand) {
		return brands.stream().filter(b -> b.getName().equals(brand)).findFirst().get();
	}

	public Brand save(Brand brand) {
		this.brands.add(brand);
		return brand;
	}

}
