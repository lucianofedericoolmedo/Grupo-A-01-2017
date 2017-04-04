/*
 * Copyright 2015-2016 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */

package edu.unq.desapp.groupA.backend.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import edu.unq.desapp.groupA.backend.model.Balancer;
import edu.unq.desapp.groupA.backend.model.Caja;

public class JUnit4Test {

	@Test
	public void test() {
		assertEquals(3, 1 + 2);
	}
	
	@Test
	public void testXXX() {
		Balancer balancer = new Balancer();
		balancer.setCantidadCajasHabilitadas(1);
		Caja caja = balancer.getCajasHabilitadas().get(0);
		int cantidadProductos = 5;
		Caja cajaSolicitada = balancer.solicitarCaja(cantidadProductos);
		assertEquals(caja,cajaSolicitada);
	}
	
	@Test
	public void testXXX2() {
		Balancer balancer = new Balancer();
		balancer.setCantidadCajasHabilitadas(2);
		Caja caja1 = balancer.getCajasHabilitadas().get(0);
		Caja caja2 = balancer.getCajasHabilitadas().get(1);
		caja1.procesar(2);
		caja2.procesar(1);
		int cantidadProductos = 5;
		Caja cajaSolicitada = balancer.solicitarCaja(cantidadProductos);
		assertEquals(caja2,cajaSolicitada);
		assertFalse(caja1.equals(cajaSolicitada));
	}
	
	
	
}
