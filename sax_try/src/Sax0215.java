import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;


public class Sax0215 {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.printf("Usage: %s filename\n", Sax0215.class.getName());
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
			
			ErrorHandler handler = new ErrorHandlerImpl();
			reader.setErrorHandler(handler);
			reader.setFeature("http://xml.org/sax/features/validation", true);
			
			reader.parse(filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	static class ErrorHandlerImpl implements ErrorHandler {
		
		private static final String MSG_FORMAT = "%s at Line %d, Col %d: %s\n";
		
		public void fatalError(SAXParseException e) {
			System.out.printf(MSG_FORMAT, "Fatal Error", e.getLineNumber(), e.getColumnNumber(), e.getMessage());
		}
		
		public void error(SAXParseException e) {
			System.out.printf(MSG_FORMAT, "Error", e.getLineNumber(), e.getColumnNumber(), e.getMessage());
		}
		
		public void warning(SAXParseException e) {
			System.out.printf(MSG_FORMAT, "Warning", e.getLineNumber(), e.getColumnNumber(), e.getMessage());
		}
	}
}
