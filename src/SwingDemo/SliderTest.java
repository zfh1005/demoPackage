/**
 * 
 */
package SwingDemo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.Dictionary;
import java.util.Hashtable;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @author zfh1005
 */
public class SliderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SliderTestFrame frame = new SliderTestFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

/*
 * A frame with a panel 
 * */
class SliderTestFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2178629686962205720L;
	
	public SliderTestFrame(){
		setTitle("SliderTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGTH);
		
		//init sliderPanel 
		sliderPanel = new JPanel();
		sliderPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		//common listener for all slider
		listener = new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				//update text field when slider value change
				JSlider source = (JSlider) arg0.getSource();
				textField.setText("" + source.getValue());				
			}			
		};
		//add a plain slider
		JSlider slider = new JSlider();
		addSlider(slider, "Plain");

		//add a slider with major and minor ticks
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		addSlider(slider, "Ticks");

		//add a slider that snaps ti ticks
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setSnapToTicks(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		addSlider(slider, "Snap to ticks");

		//add a slider with no track
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		slider.setPaintTicks(false);
		addSlider(slider, "No track");

		//add an inverted slider
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		slider.setInverted(true);
		addSlider(slider, "Intverted");

		//add a slider with numeric labels
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		addSlider(slider, "Labels");	

		//add a slider with alphabetic label
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		
		Dictionary<Integer, Component> labelTabel = new Hashtable<Integer, Component>();
		labelTabel.put(0, new JLabel("A"));
		labelTabel.put(20, new JLabel("B"));
		labelTabel.put(40, new JLabel("C"));
		labelTabel.put(60, new JLabel("D"));
		labelTabel.put(80, new JLabel("E"));
		labelTabel.put(100, new JLabel("F"));
		
		slider.setLabelTable(labelTabel);
		addSlider(slider, "Custom labels");
		
		//add a slider with icon labels
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setSnapToTicks(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(20);		
		
		//add the field that displays the slider value
		textField = new JTextField();
		add(sliderPanel, BorderLayout.CENTER);
		add(textField, BorderLayout.SOUTH);
		
		
	}

	/*
	 * adds a slider to the slider panel and hooks up the listener
	 * @param s the slider
	 * @param description the slider description
	 * 
	 * 
	 * */	
	public void addSlider(JSlider s, String description){
		s.addChangeListener(listener);
		JPanel panel = new JPanel();
		panel.add(s);
		panel.add(new JLabel(description));
		sliderPanel.add(panel);		
	}


	private int DEFAULT_WIDTH = 350;
	private int DEFAULT_HEIGTH = 450;

	private JPanel sliderPanel;
	private JTextField textField;
	private ChangeListener listener;
}
