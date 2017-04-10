package edu.unq.desapp.groupA.backend.service;

import java.util.List;
import java.util.stream.Stream;

import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.CashRegister;
import edu.unq.desapp.groupA.backend.model.Pedido;
import edu.unq.desapp.groupA.backend.model.Usuario;

public class BalancerService {
	
	CashRegister sendCartToQueue(List<CashRegister> cajasHabilitadas) {
		Stream<CashRegister> cajas = cajasHabilitadas.stream().filter(caja -> !caja.isDisponible())
				.sorted((c1, c2) -> c1.getProductosParaProcesar().compareTo(c2.getProductosParaProcesar()));
		
		CashRegister caja = cajas.findFirst().get();
		return caja;
	}

	private CashRegister obtenerCajaDisponible(List<CashRegister> cajasDisponibles, Pedido pedido){
		CashRegister caja = cajasDisponibles.get(0);
		return caja;
	}


	
}
