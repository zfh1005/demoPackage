/**
 * 
 */
package XMLDemo.GridBagTest;

import java.awt.Component;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;
import javax.swing.text.Element;

/**
 * @author zfh1005
 *
 */
public class GridBagPane extends JPanel {
	
	private static final long serialVersionUID = -5948330877497179178L;
	
	/**
	 * Constructs a grid bag pane
	 * @param filename the name of the XML file that describe the pane's components and their positions
	 */
	public GridBagPane(String filename) {
		
	}
	
	/*
	 * removes all(heuristically determined) element content whitespace nodes
	 * @param e the root element
	 * @return the number of whitespace nodes that were removed
	 * */
	private int removeElementContentWhitrspace(Element e){
		return 0;
		
	}
	
	/*
	 * Gets a component with a given name
	 * @param name a component name
	 * @return the component with the given name, 
	 * or null if no component in this grid bag pane has the given name
	 * */
	public Component get(String name){
		return null;
	}
	
	/*
	 * parses a gridbag element.
	 * @param e a gridbag element
	 * */
	private void parseGridbag(Element e){
		
	}
	
	/*
	 * parse a cell element
	 * @param e a cell element
	 * @param r the row of the cell
	 * @param c the column of the cell
	 * */
	private void parseCell(Element e, int r, int c){
		
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
