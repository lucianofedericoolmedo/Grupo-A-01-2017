package edu.unq.desapp.groupA.backend.model;

import java.util.List;

public class Usuario {

	private Balancer balancer;
		
		
	public Pedido realizarPedido(List<Producto> listaDeProductos) {
		return this.balancer.registrarPedido(this,listaDeProductos);
	}

	public Balancer getBalancer() {
		return balancer;
	}



	public void setBalancer(Balancer balancer) {
		this.balancer = balancer;
	}

}
