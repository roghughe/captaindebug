package com.captaindebug.xml.jaxb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import com.captaindebug.jaxb.AddressType;
import com.captaindebug.jaxb.BaseType;
import com.captaindebug.jaxb.CustomerType;
import com.captaindebug.jaxb.NameType;
import com.captaindebug.jaxb.PizzaNameType;
import com.captaindebug.jaxb.PizzaOrder;
import com.captaindebug.jaxb.PizzaType;
import com.captaindebug.jaxb.Pizzas;

public class PizzaJaxbTest {

	@Test
	public void testLoadPizzaOrderXml() throws JAXBException, IOException {

		InputStream is = loadResource("/pizza-order1.xml");

		// Load the file
		JAXBContext context = JAXBContext.newInstance(PizzaOrder.class);
		Unmarshaller um = context.createUnmarshaller();
		PizzaOrder pizzaOrder = (PizzaOrder) um.unmarshal(is);

		String orderId = pizzaOrder.getOrderID();
		assertEquals("123w3454r5", orderId);

		// Check the customer details...
		CustomerType customerType = pizzaOrder.getCustomer();

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

		Pizzas pizzas = pizzaOrder.getPizzas();
		List<PizzaType> pizzasOrdered = pizzas.getPizza();

		assertEquals(3, pizzasOrdered.size());

		// Check the pizza order...
		for (PizzaType pizza : pizzasOrdered) {

			PizzaNameType pizzaName = pizza.getName();
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

	private InputStream loadResource(String filename) throws IOException {

		InputStream is = getClass().getResourceAsStream(filename);
		if (is == null) {
			throw new IOException("Can't find the file: " + filename);
		}

		return is;
	}
}
