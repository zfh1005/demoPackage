/**
 * 
 */
package XMLDemo.GridBagTest;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.lang.reflect.Field;

import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * @author zfh1005
 *
 */
/*
 * this panel uses an XML file to describe its components and their grid bag layout position.
 * 
 * */
public class GridBagPane extends JPanel {
	
	private static final long serialVersionUID = -5948330877497179178L;
	
	/**
	 * Constructs a grid bag pane
	 * @param filename the name of the XML file that describe the pane's components and their positions
	 */
	public GridBagPane(String filename) {
		setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		
		try{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(true);
			
			if(filename.contains("-schema")){
				factory.setNamespaceAware(true);
				final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemalanguage";
				final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
				factory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
			}
			factory.setIgnoringElementContentWhitespace(true);
			
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(filename));
			
			if(filename.contains("-schema")){
				int count = removeElementContentWhitrspace(document.getDocumentElement());
				System.out.println(count + "whitespace nodes removed.");				
			}
			
			parseGridbag(document.getDocumentElement());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*
	 * removes all(heuristically determined) element content whitespace nodes
	 * @param e the root element
	 * @return the number of whitespace nodes that were removed
	 * */
	private int removeElementContentWhitrspace(Element e){
		NodeList childrenList = e.getChildNodes();
		int count = 0;
		boolean allTextChildrenAreWhiteSpace = true;
		int elements = 0;
		for(int i = 0; i < childrenList.getLength() && allTextChildrenAreWhiteSpace; i++){
			Node childNode  = childrenList.item(i);
			if(childNode instanceof org.w3c.dom.Text && ((org.w3c.dom.Text)childNode).getData().trim().length() > 0){
				allTextChildrenAreWhiteSpace = false;
			}
			else if(childNode instanceof org.w3c.dom.Element){
				elements ++;
				count += removeElementContentWhitrspace((Element) childNode);
			}
		}	
		//heuristics for element content
		if(elements > 0 && allTextChildrenAreWhiteSpace){
			for(int i = childrenList.getLength() - 1; i >= 0; i--){
				Node child = childrenList.item(i);
				if(child instanceof Text){
					e.removeChild(child);
					count++;
				}
			}
		}
		return count;		
	}
	
	/*
	 * Gets a component with a given name
	 * @param name a component name
	 * @return the component with the given name, 
	 * or null if no component in this grid bag pane has the given name
	 * */
	public Component get(String name){
		Component[] components = getComponents();
		for(int i = 0; i < components.length; i++ ){
			if(components[i].getName().equals(name)){
				return components[i];
			}
		}		
		return null;
	}
	
	/*
	 * parses a gridbag element.
	 * @param e a gridbag element
	 * */
	private void parseGridbag(Element e){
		NodeList rows = e.getChildNodes();
		for(int i = 0; i < rows.getLength(); i++){
			Element rowElement = (Element)rows.item(i);
			NodeList cells = rowElement.getChildNodes();
			for(int j = 0; j < cells.getLength(); j++){
				Element cell = (Element)cells.item(j);
				parseCell(cell, i, j);
			}
		}		
	}
	
	/*
	 * parse a cell element
	 * @param e a cell element
	 * @param r the row of the cell
	 * @param c the column of the cell
	 * */
	private void parseCell(Element e, int r, int c){
		//get attributes
		
		String valueString = e.getAttribute("gridx");
		//use default
		if(valueString.length() == 0){
			if(c == 0){
				constraints.gridx = 0;
			}
			else {
				constraints.gridx += constraints.gridwidth;
			}
		}
		else{
			constraints.gridx = Integer.parseInt(valueString);
		}
		
		valueString = e.getAttribute("gridy");
		//use default
		if(valueString.length() == 0){
			constraints.gridy = r;
		}
		else{
			constraints.gridy = Integer.parseInt(valueString);
		}
		
		constraints.gridwidth = Integer.parseInt(e.getAttribute("gridwidth"));
		constraints.gridheight = Integer.parseInt(e.getAttribute("gridheight"));
		constraints.weightx = Integer.parseInt(e.getAttribute("weightx"));
		constraints.weighty = Integer.parseInt(e.getAttribute("weighty"));
		constraints.ipadx = Integer.parseInt(e.getAttribute("ipadx"));
		constraints.ipady = Integer.parseInt(e.getAttribute("ipady"));
		
		//use reflection to get integer value of static fields
		Class<GridBagConstraints> C1 = GridBagConstraints.class;
		
		try{
			String nameString = e.getAttribute("fill");
			Field field = C1.getField(nameString);
			constraints.fill = field.getInt(C1);
			
			nameString = e.getAttribute(nameString);
			constraints.anchor = field.getInt(C1);
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		
		Component component = (Component)parseBean((Element) e.getFirstChild());
		add(component, constraints);		
	}
	
	/*
	 * parse a bean element.
	 * @param e a bean element 
	 * */
	private Object parseBean(Element e){
		return null;
	}
	
	/*
	 * parse a value element.
	 * @param e a value element 
	 * */
	private Object parseValue(Element e){
		return null;
	}
	
	private GridBagConstraints constraints;
	
}
