package edu.unq.desapp.groupA.backend.service.provider;

import edu.unq.desapp.groupA.backend.model.Discount;
import edu.unq.desapp.groupA.backend.model.ItemCart;
import edu.unq.desapp.groupA.backend.model.Product;

public class ItemFactory {

		
	public static ItemCart exampleNewElem(Product product){
		Integer quantity = 5;
		//Discount discount = DiscountFactory.exampleNewPerProductQuantityElem(product);
		return newElem(product,quantity);
	}
	
	private static ItemCart newElem(Product product,Integer quantity) {
		ItemCart itemCart =  new ItemCart();
		itemCart.setChecked(false);
		//itemCart.setDiscount(discount);
		itemCart.setProduct(product);
		itemCart.setQuantity(quantity);
		return itemCart;
	}

	
}
