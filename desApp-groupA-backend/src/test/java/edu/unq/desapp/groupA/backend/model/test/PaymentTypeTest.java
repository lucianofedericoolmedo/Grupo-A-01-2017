package edu.unq.desapp.groupA.backend.model.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.unq.desapp.groupA.backend.model.PaymentType;

public class PaymentTypeTest {

	private String aPaymentTypeName;
	private String aPaymentTypeDescription;

	@Before
	public void setup() {
		aPaymentTypeName = "Efectivo";
		aPaymentTypeDescription = "Pago abonado en linea de cajas dentro del supermercado";
	}
	
	@Test
	public void createItemPurchase() {
		PaymentType newPaymentType = new PaymentType();
		
		assertEquals(PaymentType.class, newPaymentType.getClass());
	}

	@Test
	public void testGettersAndSettersItemPurchase() {
		PaymentType newPaymentType = new PaymentType();
		
		newPaymentType.setName(aPaymentTypeName);
		newPaymentType.setDescription(aPaymentTypeDescription);
		
		assertEquals(aPaymentTypeName, newPaymentType.getName());
		assertEquals(aPaymentTypeDescription, newPaymentType.getDescription());
	}

}
