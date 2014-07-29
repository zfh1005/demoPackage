/**
 * 
 */
package SwingDemo;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.AbstractSpinnerModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;

/**
 * @author zfh1005
 */
public class SpinnerTest {
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SpinnerTestFrame frame;
		try {
			frame = new SpinnerTestFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
			frame.setVisible(true);
		} 
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}

/*
 * A frame with a panel for sketching a figure
 * */
class SpinnerTestFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9004722233631385220L;
	
	public SpinnerTestFrame() throws MalformedURLException{
		setTitle("ForamtTest");
		setSize(WIDTH, HEIGTH);

		//add button panel
		JPanel buttonPanel = new JPanel();
		okButton = new JButton("Ok");
		buttonPanel.add(okButton);
		add(buttonPanel, BorderLayout.SOUTH);

		//add mainPanel
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(0,3));
		add(mainPanel, BorderLayout.CENTER);
		
		//add Default JSpinner
		JSpinner defaultSpinner = new JSpinner();
		addRow("Default", defaultSpinner);
		
		//add Bounded JSpinner
		JSpinner boundSpinner = new JSpinner(new SpinnerNumberModel(5, 0, 10, 0.5));
		addRow("Bounded", boundSpinner);
		
		//add List JSpinner
		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		JSpinner listSpinner = new JSpinner(new SpinnerListModel(fonts));
		addRow("List", listSpinner);
		
		//add Reverse List JSpinner
		JSpinner reverseListSpinner = new JSpinner(
				new SpinnerListModel(fonts){
					/**
					 * 
					 */
					private static final long serialVersionUID = 5551138980371403128L;
					@Override
					public Object getNextValue(){
						return super.getPreviousValue();
					}
					@Override
					public Object getPreviousValue(){
						return super.getNextValue();
					}
				});
		addRow("Reverse List", reverseListSpinner);
		
		//add Date JSpinner
		JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
		addRow("Date", dateSpinner);
		
		//add better Date JSpinner
		JSpinner betterDateSpinner = new JSpinner(new SpinnerDateModel());
		String pattern = ((SimpleDateFormat)(DateFormat.getDateInstance())).toPattern();
		betterDateSpinner.setEditor(new JSpinner.DateEditor(betterDateSpinner, pattern));
		addRow("Better Date", betterDateSpinner);
		
		//add time JSpinner
		JSpinner timeSpinner = new JSpinner(new SpinnerDateModel(
				new GregorianCalendar(2000, Calendar.JANUARY, 1, 12, 0, 0).getTime(), null, null, Calendar.HOUR));
		addRow("Time", timeSpinner);
		
		JSpinner permSpinner = new JSpinner(new PremutationSpinnerModel("meat"));
		addRow("Word", permSpinner);
	}
	
	/**
	 * add a label and a JFormattedTextField to mainPanel
	 * @param labelText
	 * @param field
	 */
	private void addRow(String labelText, final JSpinner spinner) {
		mainPanel.add(new JLabel(labelText));
		mainPanel.add(spinner);
		final JLabel valueLabel = new JLabel();
		mainPanel.add(valueLabel);
		okButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object value = spinner.getValue();
				valueLabel.setText(value.toString());
			}
		});
	}
	private int WIDTH = 800;
	private int HEIGTH = 400;
	private JButton okButton;
	private JPanel mainPanel;
}


class PremutationSpinnerModel extends AbstractSpinnerModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5177545005429942128L;

	public PremutationSpinnerModel(String s){
		word = s;
	}

	/* 
	 * @see javax.swing.SpinnerModel#getNextValue()
	 */
	@Override
	public Object getNextValue() {
		int[] codePoints = toCodePointArray(word);
		for(int i = codePoints.length - 1; i > 0; i--){
			if(codePoints[i - 1] < codePoints[i]){
				int j = codePoints.length - 1;
				while(codePoints[i - 1] > codePoints[j]){
					j--;
				}
				swap(codePoints, i - 1, j);
				reverse(codePoints, i, codePoints.length -1);
				return new String(codePoints, 0, codePoints.length);
			}
		}
		reverse(codePoints, 0, codePoints.length -1);
		return new String(codePoints, 0, codePoints.length);
	}
	
	
	/* 
	 * @see javax.swing.SpinnerModel#getPreviousValue()
	 */
	@Override
	public Object getPreviousValue() {
		int[] codePoints = toCodePointArray(word);		
		for(int i = codePoints.length - 1; i > 0; i--){
			if(codePoints[i - 1] > codePoints[i]){
				int j = codePoints.length - 1;
				while(codePoints[i - 1] < codePoints[j]){
					j--;
				}
				swap(codePoints, i - 1, j);
				reverse(codePoints, i, codePoints.length -1);
				return new String(codePoints, 0, codePoints.length);
			}
		}
		reverse(codePoints, 0, codePoints.length -1);
		return new String(codePoints, 0, codePoints.length);
	}

	/* 
	 * @see javax.swing.SpinnerModel#getValue()
	 */
	@Override
	public Object getValue() {
		return word;
	}

	/* 
	 * @see javax.swing.SpinnerModel#setValue(java.lang.Object)
	 */
	@Override
	public void setValue(Object arg0) {
		if(!(arg0 instanceof String)){
			throw new IllegalArgumentException();
		}
		word = (String)arg0;
		fireStateChanged();
		
	}
	
	/**
	 * @param a
	 * @param i
	 * @param j
	 */
	private void reverse(int[] a, int i, int j) {		
		while(i < j){
			swap(a, i, j);
			i++;
			j--;
		}
	}


	/**
	 * swap Array a's elements(a[i] --- a[j]) 
	 * @param a need swap string
	 * @param i 
	 * @param j
	 */
	private void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;		
	}


	/**
	 * @param str
	 * @return
	 */
	private int[] toCodePointArray(String str) {
		int[] codePoints = new int[str.codePointCount(0, str.length())];
		for(int i = 0, j = 0; i < str.length(); i++, j++){
			int cp = str.codePointAt(i);
			if(Character.isSupplementaryCodePoint(cp)){
				i ++;
			}
			codePoints[j] = cp;
		}
		return codePoints;
	}
	
	private String word;
	
}
