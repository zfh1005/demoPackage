/**
 * 
 */
package XMLDemo.TransformTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;



/**
 * @author zfh1005
 *
 */
public class EmployeeReader implements XMLReader {

	/* (non-Javadoc)
	 * @see org.xml.sax.XMLReader#getContentHandler()
	 */
	@Override
	public ContentHandler getContentHandler() {
		return handler;
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.XMLReader#getDTDHandler()
	 */
	@Override
	public DTDHandler getDTDHandler() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.XMLReader#getEntityResolver()
	 */
	@Override
	public EntityResolver getEntityResolver() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.XMLReader#getErrorHandler()
	 */
	@Override
	public ErrorHandler getErrorHandler() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.XMLReader#getFeature(java.lang.String)
	 */
	@Override
	public boolean getFeature(String arg0) throws SAXNotRecognizedException, SAXNotSupportedException {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.XMLReader#getProperty(java.lang.String)
	 */
	@Override
	public Object getProperty(String arg0) throws SAXNotRecognizedException,
			SAXNotSupportedException {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.XMLReader#parse(org.xml.sax.InputSource)
	 */
	@Override
	public void parse(InputSource arg0) throws IOException, SAXException {
		InputStream stream = arg0.getByteStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(stream));
		String rootElementString = "staff";
		AttributesImpl atts = new AttributesImpl();
		
		if(handler == null){
			throw new SAXException("No content handle.");
		}
		
		handler.startElement("", rootElementString, rootElementString, atts);
		String lineString;
		while((lineString = in.readLine()) != null){
			handler.startElement("", "employee", "employee", atts);
			StringTokenizer tokenizer = new StringTokenizer(lineString, "|");
			
			handler.startElement("", "name", "name", atts);
			String string = tokenizer.nextToken();
			handler.characters(string.toCharArray(), 0, string.length());
			handler.endElement("", "name", "name");
			
			handler.startElement("", "salary", "salary", atts);
			string = tokenizer.nextToken();
			handler.characters(string.toCharArray(), 0, string.length());
			handler.endElement("", "salary", "salary");
			
			atts.addAttribute("", "year", "year", "CDATA", tokenizer.nextToken());
			atts.addAttribute("", "month", "month", "CDATA", tokenizer.nextToken());
			atts.addAttribute("", "day", "day", "CDATA", tokenizer.nextToken());
			handler.startElement("", "hireday", "hireday", atts);
			handler.endElement("", "hireday", "hireday");
			atts.clear();
			
			handler.endElement("", "employee", "employee");
		}
		handler.endElement("", rootElementString, rootElementString);
		handler.endDocument();
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.XMLReader#parse(java.lang.String)
	 */
	@Override
	public void parse(String arg0) throws IOException, SAXException {
		
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.XMLReader#setContentHandler(org.xml.sax.ContentHandler)
	 */
	@Override
	public void setContentHandler(ContentHandler arg0) {
		handler = arg0;		
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.XMLReader#setDTDHandler(org.xml.sax.DTDHandler)
	 */
	@Override
	public void setDTDHandler(DTDHandler arg0) {
		
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.XMLReader#setEntityResolver(org.xml.sax.EntityResolver)
	 */
	@Override
	public void setEntityResolver(EntityResolver arg0) {
		
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.XMLReader#setErrorHandler(org.xml.sax.ErrorHandler)
	 */
	@Override
	public void setErrorHandler(ErrorHandler arg0) {
		
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.XMLReader#setFeature(java.lang.String, boolean)
	 */
	@Override
	public void setFeature(String arg0, boolean arg1)
			throws SAXNotRecognizedException, SAXNotSupportedException {
		
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.XMLReader#setProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public void setProperty(String arg0, Object arg1)
			throws SAXNotRecognizedException, SAXNotSupportedException {
		
	}
	
	private ContentHandler handler;

}
