package edu.unq.desapp.groupA.backend.model.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import edu.unq.desapp.groupA.backend.model.Brand;
import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.CashRegister;
import edu.unq.desapp.groupA.backend.model.PaymentType;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.Purchase;
import edu.unq.desapp.groupA.backend.model.Usuario;
import edu.unq.desapp.groupA.backend.service.BalancerService;
import edu.unq.desapp.groupA.backend.service.CartService;
import edu.unq.desapp.groupA.backend.service.CashRegisterService;
import edu.unq.desapp.groupA.backend.service.ComprandoALoLocoService;
import edu.unq.desapp.groupA.backend.service.ItemCartService;
import edu.unq.desapp.groupA.backend.service.PaymentTypeService;
import edu.unq.desapp.groupA.backend.service.ProductService;
import edu.unq.desapp.groupA.backend.service.PurchaseService;
import edu.unq.desapp.groupA.backend.service.UserService;

public class JUnit4Test {

	
	
	
	//TODO: Coverage ... pasar a hacer test mas interesantes, separ los test segun categorias, vease
	// que involucra , si son unitarios, funcionales ... y encima tmb que testean , o sea ..,
	// si son de model quedarian en model/unittests model/functional ... (?)
	
	
	/*
	 * TODO: Change ExampleQueryManager to a service for a specific task,
	 *  O sea que sea un service particular con funciones particulares asi queda facil el
	 *  refac a un service ALASPRING ...
	 */
	
	//TODO: HOY FINALIZAR REFAC DE SERVICES Y TAREAS PENDIENTES; MERGE CON LO DE SERGIO.
	
	
	/*
	 * ESTOS DEBERIAN TEST END-TO-END
	 * 
	 * HACER UN TEST PERO CON UN PURCHASE YA ARMADO ,
	 * OTRO TEST PARA OBTENER LOS PURCHASES DE UN USUARIO
	 * 
	@Test
	public void testXXX() {

		
		ExampleQueryManager queryManager = new ExampleQueryManager();
		
		queryManager.setBalancerService(new BalancerService());
		BalancerService balancerService = queryManager.getBalancerService();
		
		queryManager.createCajas(2);
		
		CajaService cajaService = queryManager.getCajaService();
		
		List<Producto> listaDeProductos = new ArrayList<Producto>();
		listaDeProductos.add(new Producto("Cicatricure"));
		listaDeProductos.add(new Producto("Heineken"));
		
		
		Usuario user = queryManager.createUser();
		
		// TODO: Encapsular en carrito ...
		Pedido pedido = queryManager.realizarPedido(user,listaDeProductos);
		
		assertEquals(caja,pedido.getCajaAsignada());
	}
	
	*/
	
	@Test
	public void testXXX2() {
		
		
		/*
		 * TODO: Prepare factory to inject queryManager service dependencies ..., 
		 * but not using constructors injection
		 */
		
		ComprandoALoLocoService comprandoALoLocoService = new ComprandoALoLocoService();
		
		comprandoALoLocoService.setBalancerService(new BalancerService());
		
		comprandoALoLocoService.setCashRegisterService(new CashRegisterService());
		
		comprandoALoLocoService.setUserService(new UserService());
		comprandoALoLocoService.setCartService(new CartService());
		comprandoALoLocoService.setItemCartService(new ItemCartService());
		comprandoALoLocoService.setProductService(new ProductService());
		comprandoALoLocoService.setPaymentTypeService(new PaymentTypeService());
		comprandoALoLocoService.setPurchaseService(new PurchaseService());
		
		comprandoALoLocoService.createCashRegisters(2);
		
				
		Brand brand = new Brand();
		
		Product cicatricure = comprandoALoLocoService.createProduct(brand, null, "Cicatricure", null);
		Product heineken = comprandoALoLocoService.createProduct(brand, null, "Heineken", null);
		
		
		Usuario user = new Usuario();
		
		Cart cart = comprandoALoLocoService.createCartForUser(user);
		comprandoALoLocoService.createItemCart(cart,cicatricure);
		comprandoALoLocoService.createItemCart(cart,heineken);
		
		CashRegister cashRegister = comprandoALoLocoService.getCashRegister();
		
		PaymentType paymentType = comprandoALoLocoService.createPaymentType("unNombre", 
				"unaDescripcion");
		
		Purchase purchase = comprandoALoLocoService.createPurchase(cart,paymentType, 
				cashRegister);
		
		
		// TENGO ESTOS PRODUCTOS
		Set<String> expectedProductsInPurchase = Arrays.asList(heineken,cicatricure).
				stream().map(p -> p.getName()).collect(Collectors.toSet());
		Set<String> productsInPurchase = comprandoALoLocoService.getProductsInPurchase(purchase).
				stream().map(p -> p.getName()).collect(Collectors.toSet());
		
		assertEquals(expectedProductsInPurchase,productsInPurchase);
				
		
		//TODO: Make this work ...
		
		List<String> expectedP = Arrays.asList("heineken");		
		List<String> recomendacionesNombre = comprandoALoLocoService.getRecomendacionesPara(cicatricure).stream().collect(Collectors.toList());
				
		assertEquals( expectedP , recomendacionesNombre);
		
		
		
	}
	
	
	
	
	
	
	
	
}
