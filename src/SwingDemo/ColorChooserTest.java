/**
 * 
 */
package SwingDemo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @author zfh1005
 */
public class ColorChooserTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ColorChooserFrame frame = new ColorChooserFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

/*
 * A frame 
 * */
class ColorChooserFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1249667570364887807L;
	
	public ColorChooserFrame(){
		setTitle("ColorChooser");
		setSize(WIDTH, HEIGTH);
		
		//add color chooser panel 
		ColorChooserPanel panel = new ColorChooserPanel();
		add(panel);
	}
	
	private final int WIDTH = 600;
	private final int HEIGTH = 400;
}

class ColorChooserPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8849649472150223157L;
	
	public ColorChooserPanel(){
		JButton modalButton = new JButton("Modal");
		modalButton.addActionListener(new modalButtonListener());
		add(modalButton);
		
		JButton modallessButton = new JButton("ModalLess");
		modallessButton.addActionListener(new modallessButtonListener());
		add(modallessButton);
		
		JButton immediateButton = new JButton("Immediate");
		immediateButton.addActionListener(new ImmediateListener());
		add(immediateButton);
	}
	
	private class modalButtonListener implements ActionListener{

		/* 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Color defaultColor = getBackground();
			Color selectedColor = JColorChooser.showDialog(
					ColorChooserPanel.this, 
					"set background", 
					defaultColor);
			if(selectedColor != null){
				setBackground(selectedColor);
			}
		}
	}
	
	
	private class modallessButtonListener implements ActionListener{

		/* 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			chooser.setColor(getBackground());
			dialog.setVisible(true);
		}
		
		public modallessButtonListener(){
			chooser = new JColorChooser();
			dialog = JColorChooser.createDialog(
					ColorChooserPanel.this, 
					"Background Color", 
					false, 
					chooser, 
					new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent arg0) {
							setBackground(chooser.getColor());							
						}						
					}, 
					null);
		}
		
		private JDialog dialog;
		private JColorChooser chooser;
	}
	
	
	private class ImmediateListener implements ActionListener{

		/* 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			chooser.setColor(getBackground());
			dialog.setVisible(true);
			
		}
		
		public ImmediateListener(){
			chooser = new JColorChooser();
			chooser.getSelectionModel().addChangeListener(new ChangeListener(){

				@Override
				public void stateChanged(ChangeEvent arg0) {
					setBackground(chooser.getColor());						
				}				
			});
			dialog = new JDialog((JFrame)null, false);
			dialog.add(chooser);
			dialog.pack();
		}
		
		private JDialog dialog;
		private JColorChooser chooser;
	}
	
	
	
}

















