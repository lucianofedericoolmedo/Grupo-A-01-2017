package edu.unq.desapp.groupA.backend.model.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.unq.desapp.groupA.backend.model.Balancer;
import edu.unq.desapp.groupA.backend.model.Caja;
import edu.unq.desapp.groupA.backend.model.Pedido;
import edu.unq.desapp.groupA.backend.model.Producto;
import edu.unq.desapp.groupA.backend.model.Usuario;

public class JUnit4Test {

	@Test
	public void test() {
		assertEquals(3, 1 + 2);
	}
	
	//TODO: Merging and prepare for recommendations, el pedido es el cart ... por ejemplo
	
	@Test
	public void testXXX() {
		Balancer balancer = new Balancer();
		balancer.setCantidadCajasHabilitadas(2);
		Caja caja = balancer.getCajasHabilitadas().get(0);
		
		List<Producto> listaDeProductos = new ArrayList<Producto>();
		listaDeProductos.add(new Producto("Cicatricure"));
		listaDeProductos.add(new Producto("Heineken"));
		
		Usuario user = new Usuario();
		user.setBalancer(balancer);
		
		// TODO: Encapsular en carrito ...
		Pedido pedido = user.realizarPedido(listaDeProductos);
		
		assertEquals(caja,pedido.getCajaAsignada());
	}
	
	@Test
	public void testXXX2() {
		Balancer balancer = new Balancer();
		balancer.setCantidadCajasHabilitadas(2);
		Caja caja1 = balancer.getCajasHabilitadas().get(0);
		Caja caja2 = balancer.getCajasHabilitadas().get(1);
		
		List<Producto> listaDeProductos = new ArrayList<Producto>();
		listaDeProductos.add(new Producto("Cicatricure"));
		listaDeProductos.add(new Producto("Heineken"));
		
		
		Usuario user = new Usuario();
		user.setBalancer(balancer);
		
		Pedido pedido2 = new Pedido();
		pedido2.setListaDeProductos(listaDeProductos);
		
		caja2.agregarPedido(pedido2);
		
		Pedido pedido = user.realizarPedido(listaDeProductos);
		
		
		assertEquals(caja1,pedido.getCajaAsignada());
	}
	
	
	
}
