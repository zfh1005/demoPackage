/**
 * 
 */
package XMLDemo.TransformTest;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.InputSource;

/**
 * This program demonstrates XSL transformations. 
 * It applies a transformation to a set of employee records.
 * The records are stored in  the fill employee.dat and turned into XML format.
 * Specify the style sheet on the command line, e.g. java TransformTest makeprop.xsl.
 * @version 1.0.0.1 2014-11-24
 * @author zfh1005
 *
 */
public class TransformTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String fileNamesString;
		if(args.length > 0){
			fileNamesString = args[0];			
		}
		else{
			fileNamesString = "makehtml.xsl";
		}
		File styleSheetFile = new File(fileNamesString);
		StreamSource stylesSource = new StreamSource(styleSheetFile);
		
		Transformer transformer = TransformerFactory.newInstance().newTransformer(stylesSource);
		transformer.setOutputProperty(OutputKeys.INDENT, "Yes");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.setOutputProperty("{http://xml.apache.org/xsly}index-amount", "2");
		transformer.transform(new SAXSource(new EmployeeReader(), new InputSource(new FileInputStream("employee.dat"))), new StreamResult(System.out));
		


	}

}
