package com.captaindebug.xml.sax;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class CoffeeParser {

	public List<CoffeeBean> order(String xml) {

		// create a new Content Handler for this xml String
		CoffeeContentHandler handler = new CoffeeContentHandler();

		// do the parsing
		try {
			// Construct the parser by bolting together an XMLReader
			// and the ContentHandler
			XMLReader parser = XMLReaderFactory.createXMLReader();
			parser.setContentHandler(handler);

			// create an input source from the xml string
			InputSource source = new InputSource(new ByteArrayInputStream(xml.getBytes()));
			// Do the actual work
			parser.parse(source);

			return handler.getCoffeeBeans();
		} catch (Exception ex) {
			throw new RuntimeException("Exception parsing xml message. Message: " + ex.getMessage(), ex);
		}
	}

	static class CoffeeBean {

		private final String brand;
		private final String type;
		private final String sugars;

		CoffeeBean(String brand, String type, String sugars) {
			this.brand = brand;
			this.type = type;
			this.sugars = sugars;
		}

		public String getBrand() {
			return brand;
		}

		public String getType() {
			return type;
		}

		public String getSugars() {
			return sugars;
		}
	}

	/**
	 */
	class CoffeeContentHandler extends DefaultHandler {

		private String[] coffeeInfo;
		private int index;
		private List<CoffeeBean> outList;
		private boolean capture;

		/**
		 */
		@Override
		public void startDocument() {
			outList = new ArrayList<CoffeeBean>();
		}

		/**
		 */
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) {

			capture = true;
			if ("coffee".equals(qName)) {
				coffeeInfo = new String[3];
				capture = false;
			} else if ("brand".equals(qName)) {
				index = 0;
			} else if ("type".equals(qName)) {
				index = 1;
			} else if ("sugars".equals(qName)) {
				index = 2;
			}
		}

		/**
		 */
		@Override
		public void endElement(String uri, String localName, String qName) {

			if ("coffee".equals(qName)) {
				outList.add(new CoffeeBean(coffeeInfo[0], coffeeInfo[1], coffeeInfo[2]));
			}
		}

		@Override
		public void characters(char[] ch, int start, int length) {

			if (capture) {
				coffeeInfo[index] = new String(ch, start, length);
				capture = false;
			}
		}

		List<CoffeeBean> getCoffeeBeans() {
			return outList;
		}
	}

}
