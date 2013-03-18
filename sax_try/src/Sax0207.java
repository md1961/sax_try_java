import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;


public class Sax0207 {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.printf("Usage: %s filename\n", Sax0207.class.getName());
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
	
	private static final String INDENT = "    ";
	
	static class ContentHandlerImpl implements ContentHandler {
		
		@Override
		public void startElement(String uri, String localName, String qName, Attributes atts)
				throws SAXException {
			System.out.printf("startElement call:\n"
					+ "%suri: [%s], localName: [%s], qName: [%s]\n", INDENT, uri, localName, qName);
		}
		
		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			String str = new String(ch, start, length);
			System.out.printf("characters call: '%s'\n", str);
		}
		
		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {}
		
		@Override
		public void startDocument() throws SAXException {}
		
		@Override
		public void endDocument() throws SAXException {}
		
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