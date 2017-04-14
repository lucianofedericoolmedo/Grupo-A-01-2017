package edu.unq.desapp.groupA.backend.service;

import java.util.List;
import java.util.stream.Stream;

import edu.unq.desapp.groupA.backend.model.CashRegister;

public class BalancerService {
	
	CashRegister sendCartToQueue(List<CashRegister> cajasHabilitadas) {
		Stream<CashRegister> crs = cajasHabilitadas.stream().filter(cr -> !cr.getAvailable())
				.sorted((c1, c2) -> c1.getProductsToProcess().compareTo(c2.getProductsToProcess()));
		
		CashRegister cashRegister = crs.findFirst().get();
		return cashRegister;
	}

	
}
