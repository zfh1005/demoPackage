/**
 * 
 */
package XMLDemo.SAX_StAXTest;

import java.io.InputStream;

import java.net.URL;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

/**
 * @author zfh1005
 *
 */

/*
 * This program demonstrates how to use a StAX parser.
 * This program prints all hyperlinks links of an XHTML web page.
 */

public class StAXTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		String urlString ;
		if(args.length == 0){
			urlString = "http://www.w3c.org";
			System.out.println("Using : " + urlString );
		}
		else {
			urlString = args[0];
		}
		
		URL url = new URL(urlString);
		InputStream inputStream = url.openStream();
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader parserReader = factory.createXMLStreamReader(inputStream);
		while(parserReader.hasNext()){
			int event = parserReader.next();
			if(event == XMLStreamConstants.START_ELEMENT){
				if(parserReader.getLocalName().equals("a")){
					String href = parserReader.getAttributeValue(null, "href");
					if(href != null){
						System.out.println(href);
					}
				}
			}
		}
	}
}
