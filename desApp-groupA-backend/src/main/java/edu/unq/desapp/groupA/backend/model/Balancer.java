package edu.unq.desapp.groupA.backend.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
		

	public Caja obtenerCaja(Pedido pedido) {
		List<Caja> cajasDisponibles = this.cajasHabilitadas.stream().filter(caja -> caja.isDisponible()).collect(Collectors.toList());
		if (! cajasDisponibles.isEmpty()){
			return this.obtenerCajaDisponible(cajasDisponibles,pedido);
		}
		else {
			return this.enviarPedidoACola(this.cajasHabilitadas,pedido);
		}
	}
	
	private Caja enviarPedidoACola(List<Caja> cajasHabilitadas, Pedido pedido) {
		List<Caja> elems = cajasHabilitadas;
		Caja caja = elems.stream().sorted((c1, c2) -> c1.getProductosParaProcesar().compareTo(c2.getProductosParaProcesar())).findFirst().get();
		caja.procesar(pedido);
		return caja;
	}

	public Caja obtenerCajaDisponible(List<Caja> cajasDisponibles, Pedido pedido){
		Caja caja = cajasDisponibles.get(0);
		caja.procesar(pedido);
		return caja;
	}
	
	public void solicitarCaja(Pedido pedido){
		Caja cajaAsignada = this.obtenerCaja(pedido);
		pedido.asignarCaja(cajaAsignada);
	}

	
	public Pedido registrarPedido(Usuario usuario, List<Producto> listaDeProductos) {
		//TODO: Refac en armar el pedido con un builder o algo mas intension-reavealing
		Pedido pedido =  new Pedido();
		pedido.setUsuario(usuario);
		pedido.setItems(listaDeProductos);
		this.solicitarCaja(pedido);
		return pedido;
	}


}
