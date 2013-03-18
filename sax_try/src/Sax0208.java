import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;


public class Sax0208 {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.printf("Usage: %s filename\n", Sax0208.class.getName());
			System.exit(0);
		}
		
		String filename = args[0];
		if (! new File(filename).exists()) {
			System.err.printf("Cannot find file '%s'\n", filename);
			System.exit(0);
		}
		
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			XMLReader reader = parser.getXMLReader();
			
			ContentHandler handler = new ContentHandlerImpl();
			reader.setContentHandler(handler);
			reader.setFeature("http://xml.org/sax/features/namespaces", true);
			
			reader.parse(filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	static class ContentHandlerImpl implements ContentHandler {
		
		private int sum;
		private String text;
		
		@Override
		public void startDocument() throws SAXException {
			sum = 0;
		}
		
		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			text = new String(ch, start, length);
			text = text.trim();
		}
		
		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			if ("price".equals(qName)) {
				int price = 0;
				try {
					price = Integer.parseInt(text);
				} catch (NumberFormatException e) {
				}
				sum += price;
			}
		}
		
		@Override
		public void endDocument() throws SAXException {
			System.out.printf("Total price of the books is %d\n", sum);
		}
		
		@Override
		public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {}
		
		@Override
		public void startPrefixMapping(String prefix, String uri) throws SAXException {}
		
		@Override
		public void skippedEntity(String name) throws SAXException {}
		
		@Override
		public void setDocumentLocator(Locator locator) {}
		
		@Override
		public void processingInstruction(String target, String data) throws SAXException {}
		
		@Override
		public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {}
		
		@Override
		public void endPrefixMapping(String prefix) throws SAXException {}
	}
}
