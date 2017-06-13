package edu.unq.desapp.groupA.backend.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.unq.desapp.groupA.backend.model.CashRegister;


@Service
public class CashRegisterManagement {

	private List<CashRegister> activeCashRegisters = new LinkedList<CashRegister>();
	
	public Boolean activateCashRegister(CashRegister cashRegister) {
		Boolean cashRegisterWithIdAdded = activeCashRegisters.stream().anyMatch(cr -> cr.getId().equals(cashRegister.getId()));
		if (!cashRegisterWithIdAdded) {
			activeCashRegisters.add(cashRegister);
		}
		return !cashRegisterWithIdAdded;
	}
	
	public Boolean deactivateCashRegister(CashRegister cashRegister) {
		Optional<CashRegister> possibleCashRegister = activeCashRegisters.stream().filter(cr -> cr.getId().equals(cashRegister.getId())).findFirst();
		if (possibleCashRegister.isPresent()) {
			return activeCashRegisters.remove(possibleCashRegister.get());
		} else {
			return false;
		}
	}
	
	public List<CashRegister> getAllActive() {
		return activeCashRegisters;
	}
	
}
