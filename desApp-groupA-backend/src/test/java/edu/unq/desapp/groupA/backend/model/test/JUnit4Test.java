package edu.unq.desapp.groupA.backend.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import edu.unq.desapp.groupA.backend.model.Brand;
import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.CashRegister;
import edu.unq.desapp.groupA.backend.model.ItemShoppingList;
import edu.unq.desapp.groupA.backend.model.PaymentType;
import edu.unq.desapp.groupA.backend.model.Price;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.ProductCategory;
import edu.unq.desapp.groupA.backend.model.ProductThresold;
import edu.unq.desapp.groupA.backend.model.Purchase;
import edu.unq.desapp.groupA.backend.model.ShoppingList;
import edu.unq.desapp.groupA.backend.model.UserProfile;
import edu.unq.desapp.groupA.backend.model.Usuario;
import edu.unq.desapp.groupA.backend.repository.CartRepository;
import edu.unq.desapp.groupA.backend.repository.CashRegisterRepository;
import edu.unq.desapp.groupA.backend.repository.ItemCartRepository;
import edu.unq.desapp.groupA.backend.repository.PaymentTypeRepository;
import edu.unq.desapp.groupA.backend.repository.ProductRepository;
import edu.unq.desapp.groupA.backend.repository.ProductThresoldRepository;
import edu.unq.desapp.groupA.backend.repository.PurchaseRepository;
import edu.unq.desapp.groupA.backend.repository.UserProfileRepository;
import edu.unq.desapp.groupA.backend.repository.UserRepository;
import edu.unq.desapp.groupA.backend.service.BalancerService;
import edu.unq.desapp.groupA.backend.service.CartService;
import edu.unq.desapp.groupA.backend.service.CashRegisterService;
import edu.unq.desapp.groupA.backend.service.ComprandoALoLocoService;
import edu.unq.desapp.groupA.backend.service.ItemCartService;
import edu.unq.desapp.groupA.backend.service.PaymentTypeService;
import edu.unq.desapp.groupA.backend.service.ProductService;
import edu.unq.desapp.groupA.backend.service.ProductThresoldService;
import edu.unq.desapp.groupA.backend.service.PurchaseService;
import edu.unq.desapp.groupA.backend.service.UserProfileService;
import edu.unq.desapp.groupA.backend.service.UserService;

public class JUnit4Test {

	//TODO: Coverage ... pasar a hacer test mas interesantes, separ los test segun categorias, vease
	// que involucra , si son unitarios, funcionales ... y encima tmb que testean , o sea ..,
	// si son de model quedarian en model/unittests model/functional ... (?)
	
		
	/*
	 * ESTOS DEBERIAN TEST END-TO-END
	 * 
	 * HACER UN TEST PERO CON UN PURCHASE YA ARMADO PARA EL BALANCEADOR DE CARGA
	 * UN TEST PARA EL TIEMPO DE RESPUESTA (?)
	 * UN TEST PARA LA CANCELACION Y CAMBIO EN LA FORMA DE ENVIO
	 * OTROS PARA LAS SUGERENCIAS DE PREFERENCIAS DE USUARIO,
	 * OTROS PARA MANTENER LAS ESTADISTICAS ...
	 * 
	*/
	
	private ComprandoALoLocoService comprandoALoLocoService;
	private Product cicatricure;
	private Product heineken;
	private Product avon;
	private Purchase purchase;
	private Usuario user;
	private ProductCategory cuidadoPersonal;
	private ProductCategory cremas;
	private ProductCategory bebidas;
	private ProductCategory cervezas;
	private List<ProductCategory> categories1;
	private List<ProductCategory> categories2;
	private Purchase purchase2;
	

	@Before
	public void setup() {
		comprandoALoLocoService = new ComprandoALoLocoService();
		comprandoALoLocoService.setBalancerService(new BalancerService());
		
		comprandoALoLocoService.setCashRegisterService(new CashRegisterService(new CashRegisterRepository()));
		
		comprandoALoLocoService.setUserService(new UserService(new UserRepository()));
		comprandoALoLocoService.setCartService(new CartService(new CartRepository(), (long) 0));
		comprandoALoLocoService.setItemCartService(new ItemCartService(new ItemCartRepository()));
		comprandoALoLocoService.setProductService(new ProductService(new ProductRepository()));
		comprandoALoLocoService.setPaymentTypeService(new PaymentTypeService( new PaymentTypeRepository()));
		comprandoALoLocoService.setPurchaseService(new PurchaseService( new PurchaseRepository()));
		comprandoALoLocoService.setProductThresoldService(new ProductThresoldService(new ProductThresoldRepository()));
		comprandoALoLocoService.setUserProfileService(new UserProfileService( new UserProfileRepository()));
		
		
		comprandoALoLocoService.createCashRegisters(2);
		
		Brand brand = new Brand();
		
		cervezas = new ProductCategory();
		cuidadoPersonal = new ProductCategory();
		bebidas = new ProductCategory();
		cremas = new ProductCategory();
		
		categories1 = Arrays.asList(cuidadoPersonal,cremas);
		categories2 = Arrays.asList(bebidas,cervezas);
		
		DateTime firstDayOfMonthOne = new DateTime().withDayOfMonth(1).withMonthOfYear(1).withYear(2017);
		DateTime tenthDayOfMonthOne = new DateTime().withDayOfMonth(10).withMonthOfYear(1).withYear(2017);
		
		Price newPrice = new Price();
		newPrice.setPrice(10.00);
		newPrice.setStartingValidityDate(firstDayOfMonthOne);
		newPrice.setFinishingValidityDate(tenthDayOfMonthOne);
		
		Price newPrice2 = new Price();
		newPrice2.setPrice(90.00);
		newPrice2.setStartingValidityDate(firstDayOfMonthOne);
		newPrice2.setFinishingValidityDate(tenthDayOfMonthOne);
		
		Price newPrice3 = new Price();
		newPrice3.setPrice(180.00);
		newPrice3.setStartingValidityDate(firstDayOfMonthOne);
		newPrice3.setFinishingValidityDate(tenthDayOfMonthOne);
		
		cicatricure = comprandoALoLocoService.createProduct(brand, categories1, "Cicatricure", newPrice2);
		heineken = comprandoALoLocoService.createProduct(brand, categories2, "Heineken", newPrice);
		avon = comprandoALoLocoService.createProduct(brand, categories2, "Avon", newPrice3);
		
		user = comprandoALoLocoService.createUser("pochoLaPantera","elHijoDeCuca","pocho@gmail.com");
				
		//Cart cart = comprandoALoLocoService.createCartForUser(user);
		
		// ACA CREE LA SHOPPING LIST
		ShoppingList shoppingList = comprandoALoLocoService.createShoppingListForUser(user);
		ItemShoppingList heinekenItem = comprandoALoLocoService.createItemShoppingList(heineken,1,shoppingList);
		ItemShoppingList cicatricureItem = comprandoALoLocoService.createItemShoppingList(cicatricure,1,shoppingList);
		
		
		// ACA CREE LA SHOPPING LIST
		ShoppingList shoppingList2 = comprandoALoLocoService.createShoppingListForUser(user);
		ItemShoppingList heinekenItem2 = comprandoALoLocoService.createItemShoppingList(heineken,1,shoppingList2);
		ItemShoppingList cicatricureItem2 = comprandoALoLocoService.createItemShoppingList(cicatricure,1,shoppingList2);
		
		//ACA CREO EL CARRITO
		
		Cart cart = comprandoALoLocoService.createCartForShoppingList(shoppingList);
		Cart otherCart = comprandoALoLocoService.createCartForShoppingList(shoppingList2);
		
		
		// POSTA NECESITAMOS USAR BUILDERS ....
		
		
		comprandoALoLocoService.createItemCart(cart,cicatricure);
		comprandoALoLocoService.createItemCart(cart,heineken);
		
		comprandoALoLocoService.createItemCart(otherCart,avon);
		comprandoALoLocoService.createItemCart(otherCart,heineken);
		
		CashRegister cashRegister = comprandoALoLocoService.getCashRegister();
		CashRegister cashRegister2 = comprandoALoLocoService.getCashRegister();
		
		PaymentType paymentType = comprandoALoLocoService.createPaymentType("unNombre", 
				"unaDescripcion");
		
		purchase = comprandoALoLocoService.createPurchase(cart,paymentType, 
				cashRegister);
		
		purchase2 = comprandoALoLocoService.createPurchase(otherCart,paymentType, 
				cashRegister2);
		
	}
	
	//TESTS DE UMBRALES
	
	
	@Test
	public void testXXX1() {
		// REFAC Using BUILDERS
		
		// Habria que ser más especifico con los thresold que podria crear...
		
		ProductThresold pt = comprandoALoLocoService.createProductThreshold();		
		UserProfile userProfile = comprandoALoLocoService.createUserProfile(user,pt);
		
		
		
		// OBTENE ESTADISTICAS DE COMPRAS SOBRE EL PRODUCTO
		/*
		 * Deberia tener mas de una compra, deberia ademas comparar con los elementos
		 * que tenga un carrito en este momento particular
		 */
		
		//Usuario user = new Usuario();
	}
	
	
	
	@Test
	public void testXXX2() {
		
		
		// TENGO ESTOS PRODUCTOS
		Set<String> expectedProductsInPurchase = Arrays.asList(heineken,cicatricure).
				stream().map(p -> p.getName()).collect(Collectors.toSet());
		Set<String> productsInPurchase = comprandoALoLocoService.getProductsInPurchase(purchase).
				stream().map(p -> p.getName()).collect(Collectors.toSet());
		
		assertEquals(expectedProductsInPurchase,productsInPurchase);
		
		//TODO: DivideInTwoTest
		
		List<String> expectedP = Arrays.asList("Heineken");		
		List<String> recomendacionesNombre = comprandoALoLocoService.getRecomendacionesPara(cicatricure).stream().collect(Collectors.toList());
				
		assertEquals( expectedP , recomendacionesNombre);
		
		List<String> expectedP1 = Arrays.asList("Cicatricure", "Avon");		
		List<String> recomendacionesNombre2 = comprandoALoLocoService.getRecomendacionesPara(heineken).stream().collect(Collectors.toList());
				
		assertEquals( expectedP1 , recomendacionesNombre2);
		
		
		List<Purchase> purchases = comprandoALoLocoService.getPurchasesByUser(user);
		
		assertTrue( purchases.contains(purchase) && purchases.contains(purchase2));
		
				
	}
	
}
