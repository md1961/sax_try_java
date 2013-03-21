import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.LexicalHandler;


public class Sax0220 {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.printf("Usage: %s filename\n", Sax0220.class.getName());
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
		
		private static final String INDENT = "    ";
		
		@Override
		public void startDTD(String name, String publicId, String systemId) {
			System.out.println("startDTD");
			System.out.printf("%sname = %s\n"    , INDENT, name);
			System.out.printf("%spublicId = %s\n", INDENT, publicId);
			System.out.printf("%ssystemId = %s\n", INDENT, systemId);
		}
		
		@Override
		public void endDTD() throws SAXException {
			System.out.println("endDTD");
		}
		
		@Override
		public void startEntity(String name) throws SAXException {}
		
		@Override
		public void endEntity(String name) throws SAXException {}
		
		@Override
		public void startCDATA() throws SAXException {}
		
		@Override
		public void endCDATA() throws SAXException {}
		
		@Override
		public void comment(char[] ch, int start, int length) throws SAXException {}
	}
}
