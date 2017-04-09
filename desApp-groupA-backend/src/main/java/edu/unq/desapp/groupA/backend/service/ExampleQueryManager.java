package edu.unq.desapp.groupA.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import edu.unq.desapp.groupA.backend.model.Balancer;
import edu.unq.desapp.groupA.backend.model.Caja;
import edu.unq.desapp.groupA.backend.model.Pedido;
import edu.unq.desapp.groupA.backend.model.Producto;

public class ExampleQueryManager {

	private Balancer balancer;
	
	public List<String> getRecomendacionesPara(Producto producto){
		List<String> productos = new ArrayList<String>();
		Map<String, Long> productosConCantidadDeRepetidos = getAllRepeatedProducts(producto);
		/* TODO: Establecer una cantidad minima de repetidos
		*/
		for (String prod : productosConCantidadDeRepetidos.keySet()){
			if (productosConCantidadDeRepetidos.get(prod) > 1){
				productos.add(prod);
			}
		}
		
		return productos;
	}

	private Map<String, Long> getAllRepeatedProducts(Producto producto){
		List<String> productos = new ArrayList<String>();
		for (Caja caja : this.balancer.getCajasHabilitadas()){
			for (Pedido p : caja.getPedidosConcretados()){
				if (p.hasProduct(producto)){
					for (Producto proc : p.getListaDeProductos()){
						if(proc.getNombre() != producto.getNombre()){
							productos.add(proc.getNombre());
						}
					}
				}
			}
		}
		Map<String, Long> counts =
			    productos.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
		return counts;
	}

	public Balancer getBalancer() {
		return this.balancer;
	}

	public List<Caja> getCajas() {
		return this.balancer.getCajasHabilitadas();
	}

	public void setBalancer(Balancer balancer) {
		this.balancer = balancer;		
	}
}
