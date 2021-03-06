package edu.unq.desapp.groupA.backend.service;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unq.desapp.groupA.backend.model.CashRegister;

@Service
@Transactional
public class BalancerService {

	@Autowired
	private CashRegisterManagement cashRegisterManagement;

	/*
	public CashRegister sendCartToQueue(List<CashRegister> cashRegisters) {
		Stream<CashRegister> crs = unAvailableCashRegisters(cashRegisters);
		crs = sortByProductsToProcess(crs);
		CashRegister cashRegister = crs.findFirst().get();
		return cashRegister;
	}
	*/
	
	public CashRegister getCashRegisterToQueue() {
		Stream<CashRegister> crs = cashRegisterManagement.getAllActive().stream();
		crs = sortByProductsToProcess(crs);
		CashRegister cashRegister = crs.findFirst().get();
		return cashRegister;
	}

	private Stream<CashRegister> sortByProductsToProcess(Stream<CashRegister> cashRegisters){
		return cashRegisters.sorted((c1, c2) -> c1.getProductsToProcess().compareTo(c2.getProductsToProcess()));
	}

	/*
	private Stream<CashRegister> unAvailableCashRegisters(List<CashRegister> cashRegisters){
		return cashRegisters.stream().filter(cr -> !cr.getAvailable());
	}
	*/
	
}
