import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.LexicalHandler;


public class Sax0219 {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.printf("Usage: %s filename\n", Sax0219.class.getName());
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
			reader.setFeature("http://xml.org/sax/features/validation", true);
			
			reader.parse(filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	static class LexicalHandlerImpl implements LexicalHandler {
		
		@Override
		public void startEntity(String name) throws SAXException {
			System.out.printf("startEntity: name = '%s'\n", name);
		}
		
		@Override
		public void endEntity(String name) throws SAXException {
			System.out.println("endEntity");
		}
		
		@Override
		public void startCDATA() throws SAXException {}
		
		@Override
		public void endCDATA() throws SAXException {}
		
		@Override
		public void comment(char[] ch, int start, int length) throws SAXException {}
		
		@Override
		public void startDTD(String name, String publicId, String systemId) {}
		
		@Override
		public void endDTD() throws SAXException {}
	}
}
