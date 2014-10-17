/**
 * 
 */
package XMLDemo.DOMTree;


import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.xml.soap.Node;
import javax.xml.soap.Text;
import javax.xml.stream.events.Comment;

import org.w3c.dom.CDATASection;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

/**
 * @author zfh1005
 *
 */

/*
 * This class renders an XML node.
 * */
public class DOMTreeCellRenderer extends DefaultTreeCellRenderer {
	
	private static final long serialVersionUID = 6294767770490949730L;

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object valueObject, boolean selected,
			boolean expanded, boolean leaf, int row, boolean hasFocus){
		Node node = (Node) valueObject;
		if (node instanceof Element) {
			return elementPanel((Element)node);
		}
		
		super.getTreeCellRendererComponent(tree, valueObject, selected, expanded, leaf, row, hasFocus);
		if(node instanceof CharacterData){
			setText(characterString((CharacterData)node));
		}
		else{
			setText(node.getClass() + ": " + node.toString());
		}
		return this;		
	}
	
	public static JPanel elementPanel(Element element){
		JPanel panel = new JPanel();
		panel.add(new JLabel("Element: " + element.getTagName()));
		final NamedNodeMap map = element.getAttributes();
		panel.add(new JTable(new AbstractTableModel() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Object getValueAt(int arg0, int arg1) {
				return arg1 == 0 ? map.item(arg0).getNodeName() : map.item(arg0).getNodeName();
			}
			
			@Override
			public int getRowCount() {
				return map.getLength();
			}
			
			@Override
			public int getColumnCount() {
				return 2;
			}
		}));
		return panel;
	}
	
	public static String characterString(CharacterData node){
		StringBuilder builder = new StringBuilder(node.getData());
		for(int i = 0; i < builder.length(); i++){
			if(builder.charAt(i) == '\r'){
				builder.replace(i, i + 1, "\\r");
				i++;
			}
			else if(builder.charAt(i) == '\n'){
				builder.replace(i, i + 1, "\\n");
				i++;
			}
			else if(builder.charAt(i) == '\t'){
				builder.replace(i, i + 1, "\\t");
				i++;
			}
		}
		
		if(node instanceof CDATASection){
			builder.insert(0, "CDATASection: ");
		}
		else if(node instanceof Text){
			builder.insert(0, "Test: ");
		}
		else if(node instanceof Comment){
			builder.insert(0, "Comment: ");
		}
		
		return builder.toString();
	}
	
	
}
