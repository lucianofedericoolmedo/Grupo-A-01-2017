package edu.unq.desapp.groupA.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import edu.unq.desapp.groupA.backend.model.CashRegister;

public class CashRegisterService {

	private List<CashRegister> registeredCashRegisters;
	
	public List<CashRegister> getRegisteredCashRegisters() {
		return registeredCashRegisters;
	}

	public void setRegisteredCashRegisters(List<CashRegister> registeredCashRegisters) {
		this.registeredCashRegisters = registeredCashRegisters;
	}


	public CashRegisterService(){
		this.registeredCashRegisters = new ArrayList<CashRegister>();
	}


	public void createCashRegister() {
		CashRegister cr = new CashRegister();
		registeredCashRegisters.add(cr);		
	}

		
	public CashRegister getAvailableCashRegister(List<CashRegister> cajasDisponibles){
		CashRegister caja = cajasDisponibles.get(0);
		return caja;
	}

	public List<CashRegister> getAvailableCashRegisters() {
		return this.registeredCashRegisters.stream().filter(caja -> caja.getAvailable()).collect(Collectors.toList());

	}

	public CashRegister findCashRegisterByIndex(int index) {
		return this.registeredCashRegisters.get(index);
	}
	
	
	
}
