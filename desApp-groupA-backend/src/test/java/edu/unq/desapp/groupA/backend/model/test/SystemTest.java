package edu.unq.desapp.groupA.backend.model.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;

import edu.unq.desapp.groupA.backend.model.Brand;
import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.PaymentType;
import edu.unq.desapp.groupA.backend.model.Price;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.ProductCategory;
import edu.unq.desapp.groupA.backend.model.Purchase;
import edu.unq.desapp.groupA.backend.model.ShoppingList;
import edu.unq.desapp.groupA.backend.model.User;
import edu.unq.desapp.groupA.backend.service.ComprandoALoLocoService;
import edu.unq.desapp.groupA.backend.service.provider.ComprandoALoLocoProvider;
import edu.unq.desapp.groupA.backend.service.provider.PriceFactory;
import edu.unq.desapp.groupA.backend.service.provider.ShoppingListFactory;

public class SystemTest {

		
	private ComprandoALoLocoService comprandoALoLocoService;
	private Product cicatricure;
	private Product heineken;
	private Product avon;
	private Purchase firstPurchase;
	private User user;
	private ProductCategory healthCare;
	private ProductCategory lotions;
	private ProductCategory beverage;
	private ProductCategory beer;
	private List<ProductCategory> healthCategory;
	private List<ProductCategory> drinkCategory;
	private Purchase secondPurchase;
	private Purchase purchase3;
	private Brand brand;
	private Price cicatricurePrice;
	private Price drinkPrice;
	private Price avonPrice;
	private PaymentType paymentType;
	
	@Before
	public void setup() {
		
		comprandoALoLocoService = ComprandoALoLocoProvider.settingComprandoALoLocoService();
		
		brand = new Brand();
		
		beer = new ProductCategory();
		healthCare = new ProductCategory();
		beverage = new ProductCategory();
		lotions = new ProductCategory();
		
		healthCategory = Arrays.asList(healthCare,lotions);
		drinkCategory = Arrays.asList(beverage,beer);
		
		
		drinkPrice = PriceFactory.exampleNewElem(10.00);
		cicatricurePrice = PriceFactory.exampleNewElem(90.00);
		avonPrice = PriceFactory.exampleNewElem(180.00);
		
		cicatricure = comprandoALoLocoService.createProduct(brand, healthCategory, "Cicatricure", cicatricurePrice);
		heineken = comprandoALoLocoService.createProduct(brand, drinkCategory, "Heineken", drinkPrice);
		avon = comprandoALoLocoService.createProduct(brand, drinkCategory, "Avon", avonPrice);
		
		user = comprandoALoLocoService.createUser("pochoLaPantera","elHijoDeCuca","pocho@gmail.com");
				
		ShoppingList shoppingList = ShoppingListFactory.
				exampleShoppingList(comprandoALoLocoService, user, Arrays.asList(heineken,cicatricure));
		
		
		ShoppingList shoppingList2 = ShoppingListFactory.
				exampleShoppingList(comprandoALoLocoService, user, Arrays.asList(heineken,avon));
				
		
		Cart aCartForPocho = comprandoALoLocoService.createCartForShoppingList(shoppingList);
		Cart anotherCartForPocho = comprandoALoLocoService.createCartForShoppingList(shoppingList2);
		
				
		
//		CashRegister cashRegister = comprandoALoLocoService.getCashRegister();
//		CashRegister anotherCashRegister = comprandoALoLocoService.getCashRegister();
		
		
		paymentType = PaymentType.CASH_REGISTER;
		
				
//		cashRegister = comprandoALoLocoService.requirePurchase(aCartForPocho,cashRegister);
//		anotherCashRegister = comprandoALoLocoService.requirePurchase(anotherCartForPocho,anotherCashRegister);
		
		
				
//		firstPurchase = comprandoALoLocoService.createPurchase(aCartForPocho,paymentType,cashRegister);		
//		secondPurchase = comprandoALoLocoService.createPurchase(anotherCartForPocho,paymentType, 
//				anotherCashRegister);
		
	}
	
	/*
	@Test
	public void testWhenUserDecidesToMakeAShippingHeShouldReceiveOne(){
		
		User otherUser = comprandoALoLocoService.createUser("Julio", "1234", "mail@gmail.com");
		
		ShoppingList shoppingList3 = comprandoALoLocoService.createShoppingListForUser(otherUser);
		Product brahma = comprandoALoLocoService.createProduct(brand, drinkCategory, "brahma", drinkPrice);
		Product guaymallen = comprandoALoLocoService.createProduct(brand, drinkCategory, "guaymallen", drinkPrice);
		
		comprandoALoLocoService.createItemShoppingList(brahma,1,shoppingList3);
		comprandoALoLocoService.createItemShoppingList(guaymallen,1,shoppingList3);
		
		
		Cart otherCart2 = comprandoALoLocoService.createCartForShoppingList(shoppingList3);
		CashRegister cashRegister3 = comprandoALoLocoService.getCashRegister();
		cashRegister3 = comprandoALoLocoService.requirePurchase(otherCart2,cashRegister3);
		
		purchase3 = comprandoALoLocoService.shipp(otherCart2, paymentType ,cashRegister3, new ShippingAddress());
		assertEquals(comprandoALoLocoService.getShippings().size(),1);
	}
	
	
	@Test
	public void testWhenUserPurchasesFromFirstCartThenProductsInPurchaseShouldBeHeinkenAnCicatricure() {		
		
		Set<String> expectedProductsInPurchase = Arrays.asList(heineken,cicatricure).
				stream().map(p -> p.getName()).collect(Collectors.toSet());
		Set<String> productsInPurchase = firstPurchase.namesOfProductsInPurchase();		
		assertEquals(expectedProductsInPurchase,productsInPurchase);
	}
	
	@Test
	public void testRecommendationsWhenHasOnlyOneCoincidenceForRecommendations(){
		List<String> expectedP = Arrays.asList("Heineken");		
		List<String> recomendacionesNombre = comprandoALoLocoService.getRecomendacionesPara(cicatricure).stream().collect(Collectors.toList());
		assertEquals( expectedP , recomendacionesNombre);
	}
	
	
	@Test
	public void testRecommendationsWhenHasMoreCoincidenceForRecommendations(){
		List<String> expectedP1 = Arrays.asList("Cicatricure", "Avon");		
		List<String> recomendacionesNombre2 = comprandoALoLocoService.getRecomendacionesPara(heineken).stream().collect(Collectors.toList());
		assertEquals( expectedP1 , recomendacionesNombre2);		
	}
	
	@Test 
	public void testAllPurchases(){
		assertEquals(comprandoALoLocoService.getAllPurchases().size() , 2);
	}
	
	
	@Test
	public void testPurchasesFromUser() {	
		List<Purchase> purchases = comprandoALoLocoService.getPurchasesByUser(user);		
		assertTrue( purchases.contains(firstPurchase) && purchases.contains(secondPurchase));	
		assertEquals( purchases.size(), 2);	
				
	}
	*/
}
