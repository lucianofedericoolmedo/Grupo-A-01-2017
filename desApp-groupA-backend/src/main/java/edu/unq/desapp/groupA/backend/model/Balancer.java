package edu.unq.desapp.groupA.backend.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Balancer {

	private List<Caja> cajasHabilitadas;
	
	public Balancer(){
		this.cajasHabilitadas = new ArrayList<Caja>();
	}

	public void setCajasHabilitadas(List<Caja> cajasHabilitadas) {
		this.cajasHabilitadas = cajasHabilitadas;
	}

	public List<Caja> getCajasHabilitadas() {
		return cajasHabilitadas;
	}

	public void setCantidadCajasHabilitadas(int cajasHabilitadas) {
		for (int i = 0; i < cajasHabilitadas ; i++){
			this.cajasHabilitadas.add(new Caja());
		}
	}
		

	public Caja solicitarCaja(int cantidadProductos) {
		List<Caja> cajasDisponibles = this.cajasHabilitadas.stream().filter(caja -> caja.isDisponible()).collect(Collectors.toList());
		if (! cajasDisponibles.isEmpty()){
			return this.solicitarCajaDisponible(cajasDisponibles,cantidadProductos);
		}
		else {
			return this.enviarPedido(this.cajasHabilitadas,cantidadProductos);
		}
	}
	
	private Caja enviarPedido(List<Caja> cajasHabilitadas, int cantidadProductos) {
		Caja caja = cajasHabilitadas.stream().min((p1, p2) -> Integer.compare(p1.getProductosParaProcesar(), p2.getProductosParaProcesar())).get();
		if (caja != null){
			caja.procesar(cantidadProductos);
		}
		return caja;
	}

	public Caja solicitarCajaDisponible(List<Caja> cajasDisponibles,int cantidadProductos){
		Caja caja = cajasDisponibles.get(0);
		caja.procesar(cantidadProductos);
		return caja;
	}

}
