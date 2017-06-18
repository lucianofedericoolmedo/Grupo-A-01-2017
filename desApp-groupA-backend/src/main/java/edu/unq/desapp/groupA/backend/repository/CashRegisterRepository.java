package edu.unq.desapp.groupA.backend.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.unq.desapp.groupA.backend.model.CashRegister;


@Repository
public class CashRegisterRepository extends HibernateGenericDAO<CashRegister> {

	
	private static final long serialVersionUID = -8172055643638973267L;
	
	private List<CashRegister> registeredCashRegisters;
	
	public List<CashRegister> getRegisteredCashRegisters() {
		return registeredCashRegisters;
	}

	public void setRegisteredCashRegisters(List<CashRegister> registeredCashRegisters) {
		this.registeredCashRegisters = registeredCashRegisters;
	}
	
	public CashRegisterRepository(){
		this.registeredCashRegisters = new ArrayList<CashRegister>();
	}

	public CashRegister findCashRegisterByIndex(int index) {
		return registeredCashRegisters.get(index);
	}

	@Override
	public Class<CashRegister> getDomainClass() {
		return CashRegister.class;
	}
}
