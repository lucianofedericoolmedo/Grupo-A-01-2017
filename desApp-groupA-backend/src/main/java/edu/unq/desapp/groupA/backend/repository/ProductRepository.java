package edu.unq.desapp.groupA.backend.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.unq.desapp.groupA.backend.model.Product;


@Repository
public class ProductRepository extends HibernateGenericDAO<Product> {

	private static final long serialVersionUID = 7677688192480365999L;

	private List<Product> products;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public ProductRepository(){
		this.products = new ArrayList<Product>();
	}

	@Override
	protected Class<Product> getDomainClass() {
		return Product.class;
	}

}
