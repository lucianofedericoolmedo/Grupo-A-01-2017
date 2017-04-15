package edu.unq.desapp.groupA.backend.service.provider;

import java.util.List;

import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.ShoppingList;
import edu.unq.desapp.groupA.backend.model.User;
import edu.unq.desapp.groupA.backend.service.ComprandoALoLocoService;

public class ShoppingListFactory {

	public static ShoppingList exampleShoppingList(ComprandoALoLocoService comprandoALoLocoService, User user,
			List<Product> products){
		ShoppingList shoppingList = comprandoALoLocoService.createShoppingListForUser(user);
		for (Product product : products){
			comprandoALoLocoService.createItemShoppingList(product,1,shoppingList);
		}
		return shoppingList;
	}
	
}
