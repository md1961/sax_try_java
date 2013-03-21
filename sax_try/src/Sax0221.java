import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;


public class Sax0221 {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.printf("Usage: %s filename\n", Sax0221.class.getName());
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

			DefaultHandler handler = new DefaultHandlerImpl();
			reader.setContentHandler(handler);
			reader.setErrorHandler(handler);
			reader.setFeature("http://xml.org/sax/features/validation", true);
			
			reader.parse(filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	static class DefaultHandlerImpl extends DefaultHandler {
		
		public void startElement(String uri, String localName, String qName, Attributes atts)
				throws SAXException {
			System.out.printf("startElement: <%s>\n", qName);
		}
		
		public void error(SAXParseException exception) throws SAXException {
			System.out.printf("error: %s\n", exception);
		}
	}
}
