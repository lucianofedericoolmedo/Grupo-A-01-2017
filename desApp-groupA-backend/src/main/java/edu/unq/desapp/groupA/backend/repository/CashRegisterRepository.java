package edu.unq.desapp.groupA.backend.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import edu.unq.desapp.groupA.backend.model.CashRegister;

public class CashRegisterRepository {

	private List<CashRegister> registeredCashRegisters;
	
	public List<CashRegister> getRegisteredCashRegisters() {
		return registeredCashRegisters;
	}

	public void setRegisteredCashRegisters(List<CashRegister> registeredCashRegisters) {
		this.registeredCashRegisters = registeredCashRegisters;
	}
	
	public void save(CashRegister cashRegister){
		this.registeredCashRegisters.add(cashRegister);
	}
	
	public CashRegisterRepository(){
		this.registeredCashRegisters = new ArrayList<CashRegister>();
	}

	public CashRegister findCashRegisterByIndex(int index) {
		return registeredCashRegisters.get(index);
	}

	public List<CashRegister> getAvailableCashRegisters() {
		return registeredCashRegisters.stream().filter(caja -> caja.getAvailable()).collect(Collectors.toList());
	}
}
