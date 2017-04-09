package edu.unq.desapp.groupA.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.CashRegister;
import edu.unq.desapp.groupA.backend.model.Pedido;

public class CashRegisterService {

	private List<CashRegister> cajasHabilitadas;
	
	public CashRegisterService(){
		this.cajasHabilitadas = new ArrayList<CashRegister>();
	}

	public void setCajasHabilitadas(List<CashRegister> cajasHabilitadas) {
		this.cajasHabilitadas = cajasHabilitadas;
	}

	public List<CashRegister> getCajasHabilitadas() {
		return this.cajasHabilitadas;
	}

	public void createCaja() {
		CashRegister nuevaCaja = new CashRegister();
		cajasHabilitadas.add(nuevaCaja);		
	}

		
	CashRegister getAvailableCashRegister(List<CashRegister> cajasDisponibles){
		CashRegister caja = cajasDisponibles.get(0);
		return caja;
	}

	public List<CashRegister> obtenerCajasDisponibles() {
		return this.getCajasHabilitadas().stream().filter(caja -> caja.isDisponible()).collect(Collectors.toList());

	}

	public CashRegister findCajasByIndex(int index) {
		return this.getCajasHabilitadas().get(index);
	}
	
	
	
}
