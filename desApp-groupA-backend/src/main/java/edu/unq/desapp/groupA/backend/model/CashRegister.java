package edu.unq.desapp.groupA.backend.model;

import java.util.ArrayList;
import java.util.List;

public class CashRegister extends Entity {
	
	private Boolean available;
	private List<Cart> carts;
	

	public CashRegister(){
		this.setAvailable(true);
		this.setCarts(new ArrayList<Cart>());
	}


	public void procesar(Pedido pedido) {
		pedido.asignarCaja(this);
		this.setAvailable(false);
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


	public Boolean getAvailable() {
		return available;
	}


	public void setAvailable(Boolean available) {
		this.available = available;
	}

	

}
