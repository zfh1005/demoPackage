/**
 * 
 */
package XMLDemo.XPathTest;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

/**
 * @author zfh1005
 *
 */

/*
 * This frame shows an XML documents, a panel to type an XPath exression, and a text field
 * to display the result 
 * */
public class XPathFrame extends JFrame{

	/**
	 * default serialVersionUID
	 */
	private static final long serialVersionUID = -1101976601372598633L;
	
	public XPathFrame(){
		setTitle("XPathTest");
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem openItem = new JMenuItem("Open");
		openItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openFile();				
			}
		});
		fileMenu.add(openItem);
		
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		fileMenu.add(exitItem);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
		
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				evaluate();
			}
		};
		
		expressionField = new JTextField(20);
		expressionField.addActionListener(listener);
		JButton evaluateButton = new JButton("Evaluate");
		evaluateButton.addActionListener(listener);
		
		typeComboBox = new JComboBox<Object>(new Object[]{
				"STRING", "NODE", "NODESET", "NUMBER", "BOOLEAN"				
				});
		typeComboBox.setSelectedItem("STRING");
		
		JPanel panel = new JPanel();
		panel.add(expressionField);
		panel.add(typeComboBox);
		panel.add(evaluateButton);
		docTextArea = new JTextArea(10, 40);
		resulTextField = new JTextField();
		resulTextField.setBorder(new TitledBorder("Result"));
		
		add(panel, BorderLayout.NORTH);
		add(new JScrollPane(docTextArea), BorderLayout.CENTER);
		add(resulTextField, BorderLayout.SOUTH);
		
		try{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
		}
		catch(ParserConfigurationException e){
			JOptionPane.showMessageDialog(this, e);
		}
		
		XPathFactory xPathFactory = XPathFactory.newInstance();
		path = xPathFactory.newXPath();
		pack();
	}
	
	/*
	 * Open a file and Load the document
	 * */
	public void openFile() {
		
	}
	
	public void evaluate() {
		
	}
	
	private DocumentBuilder builder;
	private Document document;
	private XPath path;
	private JTextField expressionField;
	private JTextField resulTextField;
	private JTextArea docTextArea;
	private JComboBox<Object> typeComboBox;
}
