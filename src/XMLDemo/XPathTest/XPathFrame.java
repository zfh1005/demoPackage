/**
 * 
 */
package XMLDemo.XPathTest;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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
import javax.swing.filechooser.FileFilter;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
	@SuppressWarnings("resource")
	public void openFile() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));//current directory is root folder
		
		chooser.setFileFilter(new FileFilter() {			
			@Override
			public String getDescription() {
				return "XML Files.";
			}		
			
			@Override
			public boolean accept(File f) {
				return f.isDirectory() || f.getName().toLowerCase().endsWith(".xml");
			}
		});
		int r = chooser.showOpenDialog(this);
		if(r != JFileChooser.APPROVE_OPTION){
			return;
		}
		File file = chooser.getSelectedFile();
		
		try{
			byte[] bytes = new byte[(int)file.length()];
			new FileInputStream(file).read(bytes);
			docTextArea.setText(new String(bytes));
			document = builder.parse(file);
		}
		catch(IOException e){
			JOptionPane.showMessageDialog(this, e);
		}
		catch (SAXException e) {
			JOptionPane.showMessageDialog(this, e);
		}
	}
	
	
	
	public void evaluate() {
		try{
			String typeName = (String) typeComboBox.getSelectedItem();
			QName returnTypeName = (QName)XPathConstants.class.getField(typeName).get(null);
			Object evalResult = path.evaluate(expressionField.getText(), document, returnTypeName);
			if(typeName.equals("NODESET")){
				NodeList list = (NodeList)evalResult;
				StringBuilder builder = new StringBuilder();
				builder.append("{");
				for(int i = 0; i < list.getLength(); i++){
					if(i > 0){
						builder.append(",");
					}
					builder.append("" + list.item(i));
				}
				builder.append("}");
				resulTextField.setText("" + builder);
			}
			else{
				resulTextField.setText("" + evalResult);
			}
		}
		catch(XPathExpressionException exception){
			resulTextField.setText("" + exception);
		}
		catch(Exception e){ //refelection exception
			e.printStackTrace();
		}
	}
	
	private DocumentBuilder builder;
	private Document document;
	private XPath path;
	private JTextField expressionField;
	private JTextField resulTextField;
	private JTextArea docTextArea;
	private JComboBox<Object> typeComboBox;
}
