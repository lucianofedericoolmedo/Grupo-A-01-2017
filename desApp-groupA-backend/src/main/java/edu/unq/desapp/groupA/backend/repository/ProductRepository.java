package edu.unq.desapp.groupA.backend.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.repository.pagination.PageRequest;
import edu.unq.desapp.groupA.backend.repository.pagination.PageRequestBuilder;
import edu.unq.desapp.groupA.backend.repository.pagination.PageResponse;


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

	public PageResponse<Product> findByPageProductsNotInShoppingList(Integer pageNumber, Integer pageSize, Integer shoppingListId) {
		String query = "from Product product "
				+ "WHERE product NOT IN (SELECT item.product FROM ItemShoppingList item "
				+ "							JOIN item.parent cart "
				+ "							WHERE cart.id = " + shoppingListId + ") ";
		PageRequest<Product> pageRequest = new PageRequestBuilder<Product>(getDomainClass())
				.setPageNumber(pageNumber)
				.setPageSize(pageSize)
				.setQuery(query)
				.build();
		return findByPage(pageRequest);
	}

	@Override
	public Class<Product> getDomainClass() {
		return Product.class;
	}

}
