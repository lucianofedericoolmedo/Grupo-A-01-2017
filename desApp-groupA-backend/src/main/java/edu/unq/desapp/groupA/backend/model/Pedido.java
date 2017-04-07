package edu.unq.desapp.groupA.backend.model;

import java.util.Date;
import java.util.List;

public class Pedido {

	private Usuario usuario;
	private List<Producto> listaDeProductos;
	private Caja cajaAsignada;
	private Date horarioAsignacion;

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
		this.setHorarioAsignacion(new Date());		
	}

	public Caja getCajaAsignada() {
		return cajaAsignada;
	}

	public void setCajaAsignada(Caja cajaAsignada) {
		this.cajaAsignada = cajaAsignada;
	}

	public Date getHorarioAsignacion() {
		return horarioAsignacion;
	}

	public void setHorarioAsignacion(Date horarioAsignacion) {
		this.horarioAsignacion = horarioAsignacion;
	}

	

}
