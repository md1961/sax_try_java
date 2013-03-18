import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;


public class Sax0213 {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.printf("Usage: %s filename\n", Sax0213.class.getName());
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
			reader.setFeature("http://xml.org/sax/features/validation", true);
			reader.setFeature("http://xml.org/sax/features/namespaces", true);
			
			reader.parse(filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	static class ContentHandlerImpl implements ContentHandler {
		
		@Override
		public void startPrefixMapping(String prefix, String uri) throws SAXException {
			System.out.printf("startPrefixMapping: prefix = %s, uri = %s\n", prefix, uri);
		}
		
		@Override
		public void endPrefixMapping(String prefix) throws SAXException {
			System.out.printf("endPrefixMapping: prefix = %s\n", prefix);
		}
		
		@Override
		public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
			System.out.printf("startElement: <%s>\n", qName);
		}
		
		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			System.out.printf("endElement: </%s>\n", qName);
		}
		
		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {}
		
		@Override
		public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {}
		
		@Override
		public void startDocument() throws SAXException {}
		
		@Override
		public void endDocument() throws SAXException {}
		
		@Override
		public void skippedEntity(String name) throws SAXException {}
		
		@Override
		public void setDocumentLocator(Locator locator) {}
		
		@Override
		public void processingInstruction(String target, String data) throws SAXException {}
	}
}
