package com.captaindebug.xml.sax;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class PizzaParser {

	public List<PizzaOrder> order(InputStream xml) {

		PizzaContentHandler handler = new PizzaContentHandler();

		// do the parsing
		try {
			// Construct the parser by bolting together an XMLReader
			// and the ContentHandler
			XMLReader parser = XMLReaderFactory.createXMLReader();
			parser.setContentHandler(handler);

			// create an input source from the XML input stream
			InputSource source = new InputSource(xml);
			// Do the actual work
			parser.parse(source);

			return handler.getPizzaOrder();
		} catch (Exception ex) {
			throw new RuntimeException("Exception parsing xml message. Message: " + ex.getMessage(), ex);
		}
	}

	static class PizzaOrder {

		private final String pizzaName;
		private final String base;
		private final String quantity;

		PizzaOrder(String pizzaName, String base, String quantity) {
			this.pizzaName = pizzaName;
			this.base = base;
			this.quantity = quantity;
		}

		public String getPizzaName() {
			return pizzaName;
		}

		public String getBase() {
			return base;
		}

		public String getQuantity() {
			return quantity;
		}
	}

	/**
	 * Use this class the handle the SAX events
	 */
	class PizzaContentHandler extends DefaultHandler {

		private String[] pizzaInfo;
		private int index;
		private List<PizzaOrder> outList;
		private boolean capture;

		/**
		 * Set things up at the start of the document.
		 */
		@Override
		public void startDocument() {
			outList = new ArrayList<PizzaOrder>();
		}

		/**
		 * Handle the startElement event
		 */
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) {

			capture = true;
			if ("pizzas".equals(qName)) {
				capture = false;
			} else if ("pizza".equals(qName)) {
				pizzaInfo = new String[3];
				capture = false;
			} else if ("name".equals(qName)) {
				index = 0;
			} else if ("base".equals(qName)) {
				index = 1;
			} else if ("quantity".equals(qName)) {
				index = 2;
			}
		}

		/**
		 * Handle the endElement event
		 */
		@Override
		public void endElement(String uri, String localName, String qName) {

			if ("pizza".equals(qName)) {
				outList.add(new PizzaOrder(pizzaInfo[0], pizzaInfo[1], pizzaInfo[2]));
			}
		}

		/**
		 * Grab hold of incoming character data
		 */
		@Override
		public void characters(char[] ch, int start, int length) {

			if (capture) {
				pizzaInfo[index] = new String(ch, start, length);
				capture = false;
			}
		}

		List<PizzaOrder> getPizzaOrder() {
			return outList;
		}
	}

}
