package edu.unq.desapp.groupA.backend.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Caja {
	
	private Boolean disponible;
	private List<Pedido> listaDePedidos;
	public List<Pedido> getListaDePedidos() {
		return listaDePedidos;
	}

	public void setListaDePedidos(List<Pedido> listaDePedidos) {
		this.listaDePedidos = listaDePedidos;
	}


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
		p.setCartState(CartState.INQUEUE);
		this.listaDePedidos.add(p);
	}
	
	public List<Pedido> getPedidosPendientes(){
		List<Pedido> pedidos = this.listaDePedidos.stream().filter(p -> p.getCartState() == CartState.INQUEUE).collect(Collectors.toList());
		return pedidos;
		
	}
	
	public List<Pedido> getPedidosConcretados(){
		List<Pedido> pedidos = this.listaDePedidos.stream().filter(p -> p.getCartState() == CartState.PURCHASE).collect(Collectors.toList());
		return pedidos;
		
	}

	public Integer getProductosParaProcesar() {
		Integer res = 0; 
		for (Pedido p : this.getPedidosPendientes()){
			res += p.getListaDeProductos().size();
		}
		return res;
	}

	public void atenderPedido(Pedido pedido) {
		pedido.setCartState(CartState.PURCHASE);
		
	}

}
