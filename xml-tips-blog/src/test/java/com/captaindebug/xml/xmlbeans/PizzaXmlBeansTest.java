/**
 * 
 */
package com.captaindebug.xml.xmlbeans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;

import org.apache.xmlbeans.XmlException;
import org.junit.Test;

import com.petesperfectpizza.BaseType;
import com.petesperfectpizza.PizzaNameType;
import com.petesperfectpizza.PizzaOrderDocument;
import com.petesperfectpizza.PizzaOrderDocument.PizzaOrder;
import com.petesperfectpizza.PizzaType;
import com.petesperfectpizza.PizzasDocument.Pizzas;

import dets.customer.AddressType;
import dets.customer.CustomerType;
import dets.customer.NameType;

/**
 * @author Roger
 * 
 *         Created 6:44:22 PM Dec 28, 2011
 * 
 */
public class PizzaXmlBeansTest {

	private PizzaOrderDocument instance;

	@Test
	public void testLoadPizzaOrderXml() throws IOException, XmlException {

		String xml = loadResource("/pizza-order1.xml");

		instance = PizzaOrderDocument.Factory.parse(xml);

		PizzaOrder order = instance.getPizzaOrder();

		String orderId = order.getOrderID();
		assertEquals("123w3454r5", orderId);

		// Check the customer details...
		CustomerType customerType = order.getCustomer();

		NameType nameType = customerType.getName();
		String firstName = nameType.getFirstName();
		assertEquals("John", firstName);
		String lastName = nameType.getLastName();
		assertEquals("Miggins", lastName);

		AddressType address = customerType.getAddress();
		assertEquals(new BigInteger("15"), address.getHouseNumber());
		assertEquals("Credability Street", address.getStreet());
		assertEquals("Any Town", address.getTown());
		assertEquals("Any Where", address.getArea());
		assertEquals("AW12 3WS", address.getPostCode());

		Pizzas pizzas = order.getPizzas();
		PizzaType[] pizzasOrdered = pizzas.getPizzaArray();

		assertEquals(3, pizzasOrdered.length);

		// Check the pizza order...
		for (PizzaType pizza : pizzasOrdered) {

			PizzaNameType.Enum pizzaName = pizza.getName();
			if ((PizzaNameType.CAPRICCIOSA == pizzaName) || (PizzaNameType.MARINARA == pizzaName)) {
				assertEquals(BaseType.THICK, pizza.getBase());
				assertEquals(new BigInteger("1"), pizza.getQuantity());
			} else if (PizzaNameType.PROSCIUTTO_E_FUNGHI == pizzaName) {
				assertEquals(BaseType.THIN, pizza.getBase());
				assertEquals(new BigInteger("2"), pizza.getQuantity());
			} else {
				fail("Whoops, can't find pizza type");
			}
		}
	}

	private String loadResource(String filename) throws IOException {

		InputStream is = getClass().getResourceAsStream(filename);
		if (is == null) {
			throw new IOException("Can't find the file: " + filename);
		}

		return toString(is);
	}

	private String toString(InputStream is) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		copyStreams(is, bos);

		return bos.toString();
	}

	private void copyStreams(InputStream is, OutputStream os) throws IOException {
		byte[] buf = new byte[1024];

		int c;
		while ((c = is.read(buf, 0, 1024)) != -1) {
			os.write(buf, 0, c);
			os.flush();
		}
	}

}
