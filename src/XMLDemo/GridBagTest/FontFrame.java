/**
 * 
 */
package XMLDemo.GridBagTest;

import java.awt.Font;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * This frame contains a font selection dialog that is described by an XML file.
 * @author zfh1005
 *
 */
public class FontFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2096262744083557223L;
	
	/*
	 * @param filename the file containing the user interface components for the dialog. 
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FontFrame(String filename){
		setSize(DEFAULT_WIDTH, DEFAULT_LENGHT);
		setTitle("GridBagTest");
		
		gridbagPane = new GridBagPane(filename);
		add(gridbagPane);
		
		faceBox = (JComboBox<?>)gridbagPane.get("face");
		sizeBox = (JComboBox<?>)gridbagPane.get("size");
		boldBox = (JCheckBox)gridbagPane.get("bold");
		italicBox = (JCheckBox)gridbagPane.get("italic");
		
		faceBox.setModel(new DefaultComboBoxModel(new Object[]{
			"Serif", "SansSerif", "Monospaced", "Dialog", "DialogInput"
		}));
		
		sizeBox.setModel(new DefaultComboBoxModel(new Object[]{
			"8", "10", "12", "15", "18", "24", "36", "48"
		}));
		
		
	}
	
	/*
	 * This method sets the text sample to the selected font.
	 * */
	public void setSample(){
		String fontFace = (String)faceBox.getSelectedItem();
		int fontSize = Integer.parseInt((String) sizeBox.getSelectedItem());
		JTextArea sampleArea = (JTextArea) gridbagPane.get("sample");
		int fontStyle = (boldBox.isSelected() ? Font.BOLD : 0)
				+ (italicBox.isSelected() ? Font.ITALIC : 0);
		sampleArea.setFont(new Font(fontFace, fontStyle, fontSize));
		sampleArea.repaint();
		
	}
	
	private GridBagPane gridbagPane;
	private JComboBox<?> faceBox;
	private JComboBox<?> sizeBox;
	private JCheckBox boldBox;
	private JCheckBox italicBox;
	
	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_LENGHT = 300;
	

}
