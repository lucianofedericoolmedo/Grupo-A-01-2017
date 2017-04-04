package edu.unq.desapp.groupA.backend.model;

public class Caja {
	
	private Boolean disponible;
	private Integer productosParaProcesar;

	public Caja(){
		this.productosParaProcesar = 0;
		this.disponible = true;
	}
	
	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}

	public Boolean isDisponible() {
		// TODO Auto-generated method stub
		return disponible;
	}

	public void procesar(int cantidadProductos) {
		this.setProductosParaProcesar(cantidadProductos);
		this.setDisponible(false);
		
	}

	public Integer getProductosParaProcesar() {
		return productosParaProcesar;
	}

	public void setProductosParaProcesar(Integer productosParaProcesar) {
		this.productosParaProcesar = productosParaProcesar;
	}

}
