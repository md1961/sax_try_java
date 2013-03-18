import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.LexicalHandler;


public class Sax0217 {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.printf("Usage: %s filename\n", Sax0217.class.getName());
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
			
			LexicalHandler lexicalHandler = new LexicalHandlerImpl();
			reader.setProperty("http://xml.org/sax/properties/lexical-handler", lexicalHandler);
			ContentHandler contentHandler = new ContentHandlerImpl();
			reader.setContentHandler(contentHandler);
			reader.setFeature("http://xml.org/sax/features/validation", true);
			
			reader.parse(filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	static class LexicalHandlerImpl implements LexicalHandler {
		
		@Override
		public void startCDATA() throws SAXException {
			System.out.println("startCDATA");
		}
		
		@Override
		public void endCDATA() throws SAXException {
			System.out.println("endCDATA");
		}
		
		@Override
		public void comment(char[] ch, int start, int length) throws SAXException {}
		
		@Override
		public void startEntity(String name) throws SAXException {}
		
		@Override
		public void startDTD(String name, String publicId, String systemId) {}
		
		@Override
		public void endEntity(String name) throws SAXException {}
		
		@Override
		public void endDTD() throws SAXException {}
	}
	
	static class ContentHandlerImpl implements ContentHandler {
		
		@Override
		public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
			System.out.printf("startElement: <%s>\n", qName);
		}
		
		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			System.out.printf("endElement: </%s>\n", qName);
		}
		
		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			String text = new String(ch, start, length);
			System.out.printf("characters: [%s]\n", text);
		}
		
		@Override
		public void startDocument() throws SAXException {}
		
		@Override
		public void setDocumentLocator(Locator locator) {}
		
		@Override
		public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {}
		
		@Override
		public void endDocument() throws SAXException {}
		
		@Override
		public void startPrefixMapping(String prefix, String uri) throws SAXException {}
		
		@Override
		public void endPrefixMapping(String prefix) throws SAXException {}
		
		@Override
		public void skippedEntity(String name) throws SAXException {}
		
		@Override
		public void processingInstruction(String target, String data) throws SAXException {}
	}
}
