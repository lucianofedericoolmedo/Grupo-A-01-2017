package edu.unq.desapp.groupA.backend.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.Product;


@Repository
public class CartRepository extends HibernateGenericDAO<Cart> implements GenericRepository<Cart> {

	private static final long serialVersionUID = 3498881987084611507L;
	
	private List<Cart> cartsAvailable;

	public List<Cart> getCartsAvailable() {
		return cartsAvailable;
	}
	
	public CartRepository(){
		this.cartsAvailable = new ArrayList<Cart>();
	}

	public void setCartsAvailable(List<Cart> cartsAvailable) {
		this.cartsAvailable = cartsAvailable;
	}

	public boolean isCartIncludingProduct(Cart cart, Product product) {
		return cart.getItems().stream().anyMatch(itemC -> itemC.getProduct() == product);
	}

	@Override
	public Class<Cart> getDomainClass() {
		return Cart.class;
	}
	
	
}
