package edu.unq.desapp.groupA.backend.model.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import edu.unq.desapp.groupA.backend.model.Brand;
import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.CashRegister;
import edu.unq.desapp.groupA.backend.model.ItemCart;
import edu.unq.desapp.groupA.backend.model.PaymentType;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.Purchase;
import edu.unq.desapp.groupA.backend.model.Usuario;
import edu.unq.desapp.groupA.backend.service.BalancerService;
import edu.unq.desapp.groupA.backend.service.CartService;
import edu.unq.desapp.groupA.backend.service.CashRegisterService;
import edu.unq.desapp.groupA.backend.service.ExampleQueryManager;
import edu.unq.desapp.groupA.backend.service.ItemCartService;
import edu.unq.desapp.groupA.backend.service.PaymentTypeService;
import edu.unq.desapp.groupA.backend.service.ProductService;
import edu.unq.desapp.groupA.backend.service.PurchaseService;
import edu.unq.desapp.groupA.backend.service.UserService;

public class JUnit4Test {

	
	//TODO: Merging and prepare for el pedido es el cart ... por ejemplo
	
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
		
		ExampleQueryManager queryManager = new ExampleQueryManager();
		
		queryManager.setBalancerService(new BalancerService());
		
		queryManager.setCashRegisterService(new CashRegisterService());
		
		queryManager.setUserService(new UserService());
		queryManager.setCartService(new CartService());
		queryManager.setItemCartService(new ItemCartService());
		queryManager.setProductService(new ProductService());
		queryManager.setPaymentTypeService(new PaymentTypeService());
		queryManager.setPurchaseService(new PurchaseService());
		
		queryManager.createCashRegisters(2);
		
				
		Brand brand = new Brand();
		
		Product cicatricure = queryManager.createProduct(brand, null, "Cicatricure", null);
		Product heineken = queryManager.createProduct(brand, null, "Heineken", null);
		
		/*
		List<Product> listaDeProductos = new ArrayList<Product>();
		listaDeProductos.add(cicatricure);
		listaDeProductos.add(heineken);
		
		
		List<Product> listaDeProductos2 = new ArrayList<Product>();
		
		Product okebon = queryManager.createProduct(brand, null, "Okebon", null);
		*/		
		Usuario user = new Usuario();
		
		Cart cart = queryManager.createCartForUser(user);
		ItemCart itemCart1 = queryManager.createItemCart(cart,cicatricure);
		ItemCart itemCart2 = queryManager.createItemCart(cart,heineken);
		
		/*
		Pedido pedido2 = new Pedido();
		pedido2.setListaDeProductos(listaDeProductos);
		
		//TODO: Delete this line below ...
		caja2.agregarPedido(pedido2);
		*/
		
		CashRegister cashRegister = queryManager.getCashRegister();
		
		PaymentType paymentType = queryManager.createPaymentType("unNombre", 
				"unaDescripcion");
		
		Purchase purchase = queryManager.createPurchase(cart,paymentType, 
				cashRegister);
		
		
		// TENGO ESTOS PRODUCTOS
		Set<String> expectedProductsInPurchase = Arrays.asList(heineken,cicatricure).
				stream().map(p -> p.getName()).collect(Collectors.toSet());
		Set<String> productsInPurchase = queryManager.getProductsInPurchase(purchase).
				stream().map(p -> p.getName()).collect(Collectors.toSet());
		
		assertEquals(expectedProductsInPurchase,productsInPurchase);
				
		
		
		
		/*
		assertEquals(caja,pedido.getCajaAsignada());
		assertEquals(CartState.INQUEUE, pedido.getCartState());
		*/
		/*
		 * importa el cartState del pedido (?)
		 */
		
		
		/*
		 * TODO:Paso el estado a ser un PURCHASE, DEBERIA CREAR UN PURCHASE OBJ,
		 * Y DEBERIA PODER PEDIR RECOMENDACIONES
		 * 
		 * TODO: REFAC "QUERYS" PARA RECOMENDACIONES, 
		 */
		
		/*
		 * TODO: Definir cuando ya podria ser recomendable un objeto.
		 */
		
		/*
		 * TODO: User profiles !!!
		 */
		
		/*
		 * 
		 * DONE: RECOMENDACIONES, TODAVIA NO CREE EL PURCHASE OBJ.
		 * 		 DEBERIA REFACTOREAR EL CODIGO PARA HACER FUNCIONES ATOMICAS ... 
		 * 		 Y USAR EL MANAGER PARA HACER LOS PEDIDOS Y NO ENLAZAR COSAS DE UNA PARA EL PASAJE 
		 * 		DE ESTADOS DEL PEDIDO Y COSAS ASI...
		 * 
		 
		
		queryManager.atenderPedido(pedido);
		
		assertEquals(CartState.PURCHASE, pedido.getCartState());
		
		List<String> expected = Arrays.asList("Heineken");
		
		
		List<String> recomendacionesNombre = queryManager.getRecomendacionesPara(cicatricure).stream().collect(Collectors.toList());
		
		assertEquals( recomendacionesNombre , expected);
		*/
		
	}
	
	
	
	
	
	
	
	
}
