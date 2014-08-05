/**
 * 
 */
package SwingDemo;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * @author zfh1005
 */
public class FontDialog {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		FontDialogFrame frame = new FontDialogFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

/*
 * A frame with a panel
 * */
class FontDialogFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4288441738262210235L;
	
	public FontDialogFrame(){
		setTitle("FontDialog");
		setSize(WIDTH, HEIGTH);
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		
		ActionListener listener = new FontAction();
		JLabel faceLabel = new JLabel("Face");
		face = new JComboBox<Object>(new String[]{
				"Serif", "ScanSerif", "Monospaced", "Dialog"
		});
		face.addActionListener(listener);
		
		JLabel sizeLabel = new JLabel("Size");
		size = new JComboBox<Object>(new String[]{
				"8", "10", "12", "14", "18", "24", "36", "48"});
		size.addActionListener(listener);
		
		bold = new JCheckBox("Bold");
		bold.addActionListener(listener);
		
		italic = new JCheckBox("Italic");
		italic.addActionListener(listener);
		
		sample = new JTextArea();
		sample.setText("The quick brown fox jumps over the dog!");
		sample.setEditable(false);
		sample.setLineWrap(true);
		sample.setBorder(BorderFactory.createEtchedBorder());
		
		add(faceLabel, new GBC(0,0).setAnchor(GBC.EAST));
		add(face, new GBC(1,0).setFill(GBC.HORIZONTAL).setWeight(100, 0).setInsets(1));
		add(sizeLabel, new GBC(0, 1).setAnchor(GBC.EAST));
		add(size, new GBC(1, 1).setFill(GBC.HORIZONTAL).setWeight(100, 0).setInsets(1));
		add(bold, new GBC(0, 2, 2, 1).setAnchor(GBC.CENTER).setWeight(100, 100));
		add(italic, new GBC(0, 3, 2, 1).setAnchor(GBC.CENTER).setWeight(100, 100));
		add(sample, new GBC(2, 0, 1, 4).setFill(GBC.BOTH).setWeight(100, 100));
		
	}
	
	class FontAction implements ActionListener{

		/* 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String fontFace = (String)face.getSelectedItem();
			int fontStyle = (bold.isSelected() ? Font.BOLD : 0) +
					(italic.isSelected() ? Font.ITALIC : 0);
			int fontSize = Integer.parseInt((String) size.getSelectedItem());
			Font font = new Font(fontFace, fontStyle, fontSize);
			sample.setFont(font);
			sample.repaint();
		}
		
	}
	
	private int WIDTH = 300;
	private int HEIGTH = 200;
	private JComboBox<Object> face;
	private JComboBox<Object> size;
	private JCheckBox bold;
	private JCheckBox italic;
	private JTextArea sample;
	
	
	
}
