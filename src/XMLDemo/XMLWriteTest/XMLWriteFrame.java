/**
 * 
 */
package XMLDemo.XMLWriteTest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

/**
 * @author zfh1005
 *
 */

/*
 * A frame with a component for showing a modern drawing.
 * */

public class XMLWriteFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7294130677109081761L;
	public XMLWriteFrame(){
		setTitle("XMLWriteTest");
		setSize(DEFAUL_WIDTH, DEFAULT_HEIGHT);
		chooser = new JFileChooser();
		
		//add component to frame 
		comp = new RectangleComponent();
		add(comp);
		
		//set up menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("File");
		menuBar.add(menu);
		
		//new menu item
		JMenuItem newItem = new JMenuItem("New");
		menu.add(newItem );
		newItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				comp.newDrawing();
				
			}
		});
		
		//save menu item
		JMenuItem saveItem = new JMenuItem("Save with DOM/XSLT");
		menu.add(saveItem);
		saveItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					saveDocument();
				}
				catch(Exception exception){
					JOptionPane.showMessageDialog(XMLWriteFrame.this, exception.toString());
				}
			}
		});
				
		//save StAXItem menu item
		JMenuItem saveStAXItem = new JMenuItem("Save with StAX");
		menu.add(saveStAXItem);
		saveStAXItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					saveStAX();
				}
				catch(Exception exception){
					JOptionPane.showMessageDialog(XMLWriteFrame.this, exception.toString());
				}
			}
		});
		
		//exit menu item
		JMenuItem exitItem = new JMenuItem("Exit");
		menu.add(exitItem);
		exitItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
				
			}
		});
		
	}
	
	
	/*
	 * Saves the drawing in SVG format, using DOM/XSLT
	 * 
	 * */
	public void  saveDocument() throws TransformerException, IOException {
		if(chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION){
			return;
		}
		File file = chooser.getSelectedFile();
		Document document = comp.buildDocument();
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "http://www.w3.org/TR/2000/CR-SVG-20000802/DTD/svg-2000802.dtd");
		transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "-//W3C//DTD SVG 20000802//EN");
		transformer.setOutputProperty(OutputKeys.INDENT, "Yes");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		transformer.transform(new DOMSource(document), new StreamResult(new FileOutputStream(file)));
	}
	
	/*
	 * Svaes the drawing in SVG format, using StAX
	 * 
	 * */
	public void saveStAX() throws FileNotFoundException, XMLStreamException{
		if(chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION){
			return;
		}
		File file = chooser.getSelectedFile();
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		XMLStreamWriter writer = factory.createXMLStreamWriter(new FileOutputStream(file));
		comp.writeDocument(writer);
		writer.close();
	}
	
	private static final int DEFAUL_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	
	private RectangleComponent comp;
	private JFileChooser chooser;
}
