package edu.unq.desapp.groupA.backend.model;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Pedido {

	private Usuario usuario;
	private List<Producto> listaDeProductos;
	private Caja cajaAsignada;
	private Date dateCreated;

	private CartState cartState;

	public List<Producto> getListaDeProductos() {
		return listaDeProductos;
	}

	public void setListaDeProductos(List<Producto> listaDeProductos) {
		this.listaDeProductos = listaDeProductos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;		
	}

	public void setItems(List<Producto> listaDeProductos) {
		this.listaDeProductos = listaDeProductos;
		
	}

	public void asignarCaja(Caja caja) {
		this.setCajaAsignada(caja);
		caja.agregarPedido(this);
		this.setDateCreated(new Date());		
	}

	public Caja getCajaAsignada() {
		return cajaAsignada;
	}

	public void setCajaAsignada(Caja cajaAsignada) {
		this.cajaAsignada = cajaAsignada;
	}


	public CartState getCartState() {
		return cartState;
	}

	public void setCartState(CartState cartState) {
		this.cartState = cartState;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public boolean hasProduct(Producto producto) {
		/*
		 * TODO: Quizas sea mejor redefinir el equals del pedido con el nombre...
		 */
		List<String> nombresDeProductos=  this.listaDeProductos.stream().map(p -> p.getNombre()).collect(Collectors.toList());
		return nombresDeProductos.contains(producto.getNombre());
	}

	

}
