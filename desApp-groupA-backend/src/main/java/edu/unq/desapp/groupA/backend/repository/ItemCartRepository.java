package edu.unq.desapp.groupA.backend.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.unq.desapp.groupA.backend.model.ItemCart;


@Repository
public class ItemCartRepository extends HibernateGenericDAO<ItemCart> {

	private List<ItemCart> items;

	
	public List<ItemCart> getItems() {
		return items;
	}


	public void setItems(List<ItemCart> items) {
		this.items = items;
	}
	
	public ItemCartRepository(){
		this.setItems(new ArrayList<ItemCart>());
	}

	@Override
	public Class<ItemCart> getDomainClass() {
		return ItemCart.class;
	}
}
