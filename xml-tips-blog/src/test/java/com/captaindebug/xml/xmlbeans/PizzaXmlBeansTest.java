/**
 * 
 */
package com.captaindebug.xml.xmlbeans;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.xmlbeans.XmlException;
import org.junit.Test;

import com.petesperfectpizza.PizzaOrderDocument;
import com.petesperfectpizza.PizzaOrderDocument.PizzaOrder;

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

		CustomerType customerType = order.getCustomer();

		NameType nameType = customerType.getName();
		String firstName = nameType.getFirstName();
		assertEquals("John", firstName);

		String lastName = nameType.getLastName();
		assertEquals("Miggins", lastName);

	}

	private String loadResource(String filename) throws IOException {

		InputStream is = getClass().getResourceAsStream(filename);
		if (is == null) {
			throw new IOException("Can't find lib file: " + filename);
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
