import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;


public class Sax0201 {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage: Sax0201 filename");
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
			
			reader.parse(filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static final String INDENT = "    ";
	
	static class ContentHandlerImpl implements ContentHandler {
		
		@Override
		public void startDocument() throws SAXException {
			System.out.println("startDocument call");
		}
		
		@Override
		public void endDocument() throws SAXException {
			System.out.println("endDocument call");
		}
		
		@Override
		public void startElement(String uri, String localName, String qName, Attributes atts)
				throws SAXException {
			int countAtts = atts.getLength();
			
			System.out.printf("startElement call:\n"
					+ "%suri: [%s], localName: [%s], qName: [%s]\n", INDENT, uri, localName, qName);
			if (countAtts == 0) {
				System.out.printf("%sNo attributes\n", INDENT);
			} else {
				for (int i = 0; i < countAtts; i++) {
					System.out.printf("%sAttribute '%s' => '%s'\n", INDENT, atts.getQName(i), atts.getValue(i));
				}
			}
		}
		
		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			System.out.printf("endElement call:\n"
					+ "    uri: [%s], localName: [%s], qName: [%s]\n", uri, localName, qName);
		}
		
		@Override
		public void startPrefixMapping(String prefix, String uri)
				throws SAXException {}
		
		@Override
		public void skippedEntity(String name) throws SAXException {}
		
		@Override
		public void setDocumentLocator(Locator locator) {}
		
		@Override
		public void processingInstruction(String target, String data)
				throws SAXException {}
		
		@Override
		public void ignorableWhitespace(char[] ch, int start, int length)
				throws SAXException {}
		
		@Override
		public void endPrefixMapping(String prefix) throws SAXException {}
		
		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {}
	}
}
