package edu.unq.desapp.groupA.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unq.desapp.groupA.backend.model.CashRegister;
import edu.unq.desapp.groupA.backend.repository.CashRegisterRepository;


@Service
@Transactional
public class CashRegisterService extends GenericService<CashRegister> {

	@Autowired
	private CashRegisterRepository repository;
	
	@Autowired
	private BalancerService balancerService;

	public CashRegisterService() { }

	public CashRegisterService(CashRegisterRepository repository) {
		this.repository = repository;
	}

	public void createCashRegister() {
		CashRegister cr = new CashRegister();
		repository.save(cr);		
	}

	public List<CashRegister> getAvailableCashRegisters() {
		return this.repository.getAvailableCashRegisters();

	}

	public CashRegister findCashRegisterByIndex(int index) {
		return this.repository.findCashRegisterByIndex(index);				
	}


	public CashRegisterRepository getRepository() {
		return repository;
	}


	public void setRepository(CashRegisterRepository repository) {
		this.repository = repository;
	}


	public List<CashRegister> getRegisteredCashRegisters() {
		return this.repository.getRegisteredCashRegisters();
	}
	
	public CashRegister getCashRegister() {
		List<CashRegister> availableCashRegister = this.getAvailableCashRegisters();
		if (! availableCashRegister.isEmpty()){
			return availableCashRegister.get(0);
		}
		else {
			List<CashRegister> cajasHabilitadas = this.getRegisteredCashRegisters();
			return balancerService.sendCartToQueue(cajasHabilitadas);
		}
	}
	
}
