package edu.unq.desapp.groupA.backend.service;

import java.util.List;
import java.util.stream.Stream;

import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.CashRegister;
import edu.unq.desapp.groupA.backend.model.Pedido;
import edu.unq.desapp.groupA.backend.model.Usuario;

public class BalancerService {

	
	
	
	/*
	private void solicitarCaja(Pedido pedido){
		Caja cajaAsignada = this.obtenerCaja(pedido);
		pedido.asignarCaja(cajaAsignada);
	}

	
	private Caja obtenerCaja(Pedido pedido) {
		List<Caja> cajasDisponibles = this.getCajasHabilitadas().stream().filter(caja -> caja.isDisponible()).collect(Collectors.toList());
		if (! cajasDisponibles.isEmpty()){
			return this.obtenerCajaDisponible(cajasDisponibles,pedido);
		}
		else {
			

			return this.enviarPedidoACola(pedido);
		}
	}
	*/
	
	CashRegister sendCartToQueue(List<CashRegister> cajasHabilitadas) {
		Stream<CashRegister> cajas = cajasHabilitadas.stream().filter(caja -> !caja.isDisponible())
				.sorted((c1, c2) -> c1.getProductosParaProcesar().compareTo(c2.getProductosParaProcesar()));
		
		CashRegister caja = cajas.findFirst().get();
		return caja;
	}

	private CashRegister obtenerCajaDisponible(List<CashRegister> cajasDisponibles, Pedido pedido){
		CashRegister caja = cajasDisponibles.get(0);
		//caja.procesar(pedido);
		return caja;
	}

	public Cart registrarPedido(Usuario user, Cart cart) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
