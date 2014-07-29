/**
 * 
 */
package SwingDemo;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author zfh1005
 */
public class CheckBoxTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		CheckBoxFrame frame = new CheckBoxFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

/*
 * A frame with a panel for sketching a figure
 * */
class CheckBoxFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4810864721290217029L;
	
	public CheckBoxFrame(){
		setTitle("CheckBoxText");
		setSize(WIDTH, HEIGTH);
		
		//add the sample text label
		label = new JLabel("The quick brown fox jumps over the dog!");
		label.setFont(new Font("Serif", Font.PLAIN, FONTSIZE));
		add(label, BorderLayout.CENTER);
		
		//this listener sets the font attribute of the label to the checkbox state
		ActionListener listener = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int mode = 0;
				if(bold.isSelected()){
					mode += Font.BOLD;
				}
				if(italic.isSelected()){
					mode += Font.ITALIC;
				}
				label.setFont(new Font("Serif", mode, FONTSIZE));				
			}			
		};
		
		//add the panel 
		JPanel buttonPanel = new JPanel();
		
		//add "Bold" CheckBox to panel
		bold = new JCheckBox("Bold");
		bold.addActionListener(listener);
		buttonPanel.add(bold);
		
		//add "Italic" CheckBox to panel
		italic = new JCheckBox("Italic");
		italic.addActionListener(listener);
		buttonPanel.add(italic);

		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	private int WIDTH = 600;
	private int HEIGTH = 600;
	private JLabel label;
	private JCheckBox bold;
	private JCheckBox italic;
	private static final int FONTSIZE = 40;
	
}