/**
 * 
 */
package SwingDemo;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Spring;
import javax.swing.SpringLayout;



/**
 * @author zfh1005
 */
public class SpringLayoutTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SpringLayoutFrame frame = new SpringLayoutFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

/*
 * A frame with a panel
 * */
class SpringLayoutFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5498044071733417573L;
	
	public SpringLayoutFrame(){
		setTitle("FontDialog");
		setSize(WIDTH, HEIGTH);
		JPanel panel = new JPanel();
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		
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
		
		panel.add(faceLabel);
		panel.add(sizeLabel);
		panel.add(face);
		panel.add(size);
		panel.add(bold);
		panel.add(italic);
		panel.add(sample);
		
		Spring strut = Spring.constant(10);
		Spring labelsEast = Spring.sum(strut, 
				Spring.max(layout.getConstraints(faceLabel).getWidth(), 
						layout.getConstraints(sizeLabel).getWidth()));
		layout.putConstraint(SpringLayout.EAST, faceLabel, labelsEast, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.EAST, sizeLabel, labelsEast, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, faceLabel, strut, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.NORTH, face, strut, SpringLayout.NORTH, panel);
		
		Spring secondRowNorth = Spring.sum(strut, 
				Spring.max(layout.getConstraint(SpringLayout.SOUTH, faceLabel),
						layout.getConstraint(SpringLayout.SOUTH, face)));
		layout.putConstraint(SpringLayout.NORTH, sizeLabel, secondRowNorth, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.NORTH, size, secondRowNorth, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, face, strut, SpringLayout.EAST, faceLabel);
		layout.putConstraint(SpringLayout.WEST, size, strut, SpringLayout.EAST, sizeLabel);
		layout.putConstraint(SpringLayout.WEST, bold, strut, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.WEST, italic, strut, SpringLayout.WEST, panel);
		
		Spring s = Spring.constant(10, 10000, 10000);
		Spring thirdRowNorth = Spring.sum(strut, 
				Spring.max(layout.getConstraint(SpringLayout.SOUTH, sizeLabel),
						layout.getConstraint(SpringLayout.SOUTH, size)));
		layout.putConstraint(SpringLayout.NORTH, bold, thirdRowNorth, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.NORTH, italic, s, SpringLayout.NORTH, bold);
		layout.putConstraint(SpringLayout.SOUTH, panel, s, SpringLayout.SOUTH, italic);
		
		
		Spring secondColumnWest = Spring.sum(strut, 
				Spring.max(layout.getConstraint(SpringLayout.SOUTH, face),
						layout.getConstraint(SpringLayout.SOUTH, size)));
		layout.putConstraint(SpringLayout.WEST, sample, secondColumnWest, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.SOUTH, sample, Spring.minus(strut), SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.NORTH, sample, strut, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.EAST, panel, strut, SpringLayout.EAST, sample);
		
		add(panel);	
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
