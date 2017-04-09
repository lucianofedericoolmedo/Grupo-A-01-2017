package edu.unq.desapp.groupA.backend.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CashRegister {
	
	private Boolean disponible;
	private List<Cart> carts;
	


	public Boolean getDisponible() {
		return disponible;
	}

	public CashRegister(){
		this.disponible = true;
		this.setCarts(new ArrayList<Cart>());
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

	public void agregarPedido(Cart p) {
		//p.setCartState(CartState.INQUEUE);
		this.carts.add(p);
	}
	
	public List<Cart> getPedidosPendientes(){
		List<Cart> carts = this.carts;
		return carts;
		
	}
	
	public List<Cart> getPedidosConcretados(){
		/*
		List<Pedido> pedidos = this.listaDePedidos.stream().filter(p -> p.getCartState() == CartState.PURCHASE).collect(Collectors.toList());
		return pedidos;
		*/
		return this.carts;
		
	}

	public Integer getProductosParaProcesar() {
		Integer res = 0; 
		for (Cart p : this.getPedidosPendientes()){
			res += p.getItems().size();
		}
		return res;
	}

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	

}
