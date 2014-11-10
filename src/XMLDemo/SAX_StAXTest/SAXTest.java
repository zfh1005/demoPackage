/**
 * 
 */
package XMLDemo.SAX_StAXTest;

import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author zfh1005
 *
 */
public class SAXTest {

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
		
		DefaultHandler handler = new DefaultHandler(){
			
			@Override
			public void startElement(String namespaceURL, String lname, String qname, Attributes attrs){
				if(lname.equals("a") && attrs != null){
					for(int i = 0; i < attrs.getLength(); i++){
						String anameString = attrs.getLocalName(i);
						if(anameString.equals("href")){
							System.out.println(attrs.getValue(i));
						}
					}
				}
			}
		};
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(true);
		SAXParser saxParser = factory.newSAXParser();
		InputStream inputStream = new URL(urlString).openStream();
		saxParser.parse(inputStream, handler );
	}

}
