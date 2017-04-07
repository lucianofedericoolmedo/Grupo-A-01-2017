package edu.unq.desapp.groupA.backend.model;

import java.util.ArrayList;
import java.util.List;

public class Caja {
	
	private Boolean disponible;
	private List<Pedido> listaDePedidos;


	public Boolean getDisponible() {
		return disponible;
	}

	public Caja(){
		this.disponible = true;
		this.listaDePedidos = new ArrayList<Pedido>();
	}
	
	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}

	public Boolean isDisponible() {
		return disponible;
	}

	public void procesar(Pedido pedido) {
		pedido.asignarCaja(this);
		this.setDisponible(false);
	}

	public void agregarPedido(Pedido p) {
		this.listaDePedidos.add(p);
	}

	public Integer getProductosParaProcesar() {
		Integer res = 0; 
		for (Pedido p : this.listaDePedidos){
			res += p.getListaDeProductos().size();
		}
		return res;
	}
}
