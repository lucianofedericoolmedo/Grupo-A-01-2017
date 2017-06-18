package edu.unq.desapp.groupA.backend.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import edu.unq.desapp.groupA.backend.model.CashRegister;


@Service
public class CashRegisterManagement {

	private List<CashRegister> activeCashRegisters = new LinkedList<CashRegister>();

	public Boolean activateCashRegister(Long cashRegisterCode) {
		CashRegister cashRegister = getCashRegisterWithCode(cashRegisterCode);
		if (cashRegister != null) {
			throw new RuntimeException("A cash register with the given code has already been activated");
		} else {
			return activeCashRegisters.add(new CashRegister(cashRegisterCode));
		}
	}

	public Boolean deactivateCashRegister(Long cashRegisterCode) {
		CashRegister cashRegister = getCashRegisterWithCode(cashRegisterCode);
		if (cashRegister == null) {
			throw new RuntimeException("Could not find any active cash register with the given code");
		} else {
			return activeCashRegisters.remove(new CashRegister(cashRegisterCode));
		}
	}

	public CashRegister getCashRegisterWithCode(Long cashRegisterCode) {
		Optional<CashRegister> possibleCashRegister = activeCashRegisters.stream().filter(cr -> cr.getCode().equals(cashRegisterCode)).findFirst();
		if (possibleCashRegister.isPresent()) {
			return possibleCashRegister.get();
		} else {
			return null;
		}
	}

	public List<CashRegister> getAllActive() {
		return activeCashRegisters;
	}
	
	@PostConstruct
	public void activateSomeCashRegister() {
		List<Long> codes = new LinkedList<Long>();
		codes.add(1l);
		codes.add(2l);
		codes.add(3l);
		for (Long code : codes) {
			activateCashRegister(code);
		}
	}

}
