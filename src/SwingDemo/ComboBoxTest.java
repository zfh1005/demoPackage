/**
 * 
 */
package SwingDemo;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author zfh1005
 */
public class ComboBoxTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ComboBoxFrame frame = new ComboBoxFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

/*
 * A frame with a panel 
 * */
class ComboBoxFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8076093017234961962L;
	
	public ComboBoxFrame(){
		setTitle("ComboBoxTest");
		setSize(WIDTH, HEIGTH);
		
		//add the sample text label
		label = new JLabel("The quick brown for jumps over the dog!");
		label.setFont(new Font("Serif", Font.PLAIN, DEFAULT_SIZE));
		add(label, BorderLayout.CENTER);
		
		//make a combo box and add face names
		faceCombo = new JComboBox<String>();
		faceCombo.setEditable(true);
		faceCombo.addItem("Serif");
		faceCombo.addItem("ScanSerif");
		faceCombo.addItem("Monospaced");
		faceCombo.addItem("Dialog");
		faceCombo.addItem("DialogInput");
		
		//the combo box listener changes the label fone to selected face name
		faceCombo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				label.setFont(new Font((
						String)faceCombo.getSelectedItem(), Font.PLAIN, DEFAULT_SIZE));
			}			
		});
		//addd combo box to panel aat frame's sourthern border
		JPanel comboPanel = new JPanel();
		comboPanel.add(faceCombo);
		add(comboPanel, BorderLayout.SOUTH);
	}
	
	private int WIDTH = 300;
	private int HEIGTH = 300;
	private int DEFAULT_SIZE = 12;
	private JComboBox<String> faceCombo;
	private JLabel label;
	

}
