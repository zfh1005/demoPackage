/**
 * 
 */
package SwingDemo;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.StringTokenizer;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.DocumentFilter;
import javax.swing.text.InternationalFormatter;
import javax.swing.text.MaskFormatter;

/**
 * @author zfh1005
 */
public class FormatTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		FormatFrame frame;
		try {
			frame = new FormatFrame();
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
class FormatFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9004722233631385220L;
	public FormatFrame() throws MalformedURLException{
		setTitle("ForamtTest");
		setSize(WIDTH, WEIGTH);

		//add button panel
		JPanel buttonPanel = new JPanel();
		okButton = new JButton("Ok");
		buttonPanel.add(okButton);
		add(buttonPanel, BorderLayout.SOUTH);

		//add mainPanel
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(0,3));
		add(mainPanel, BorderLayout.CENTER);

		//add NumberFormat
		JFormattedTextField intField = new JFormattedTextField(NumberFormat.getInstance());
		intField.setValue(new Integer(100));
		addRow("Number:", intField);

		//add NumberFormat with setFocusLostBehavior
		JFormattedTextField intField2 = new JFormattedTextField(NumberFormat.getInstance());
		intField2.setValue(new Integer(100));
		intField2.setFocusLostBehavior(JFormattedTextField.COMMIT);
		addRow("Number:", intField2);
		
		//add NumberFormat with InternationalFormatter
		JFormattedTextField intField3 = new JFormattedTextField
				(new InternationalFormatter(NumberFormat.getInstance()){

					/**
					 * 
					 */
					private static final long serialVersionUID = 4241766645822567605L;
					@Override
					protected DocumentFilter getDocumentFilter(){
						return filter;						
					}
					private DocumentFilter filter = new IntFilter();					
				});
		intField3.setValue(new Integer(100));
		intField3.setFocusLostBehavior(JFormattedTextField.COMMIT);
		addRow("Number:", intField3);

		//add NumberFormat with InternationalFormatter		
		JFormattedTextField intField4 = new JFormattedTextField(NumberFormat.getInstance());
		intField4.setValue(new Integer(100));
		intField4.setInputVerifier(new JFormattedTextFieldVerifier());
		addRow("Number:", intField4);

		//add NumberFormat with getCurrencyInstance($)
		JFormattedTextField currencyField = 
				new JFormattedTextField(NumberFormat.getCurrencyInstance());
		currencyField.setValue(new Double(10));
		addRow("Currency:", currencyField);

		//add DateFormat 
		JFormattedTextField dateField = 
				new JFormattedTextField(DateFormat.getInstance());
		dateField.setValue(new Date());
		addRow("Date(Default):", dateField);
		
		//add DateFormat in SHORT format
		DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
		format.setLenient(false);
		JFormattedTextField dateField2 = new JFormattedTextField(format);
		dateField2.setValue(new Date());
		addRow("Date(Short, not lenient):", dateField2);

		//add URL format
		try {
			DefaultFormatter formatter = new DefaultFormatter();
			formatter.setOverwriteMode(false);
			JFormattedTextField urlField = new JFormattedTextField(formatter);

			urlField.setValue(new URL("Http://java.sun.com"));
			addRow("URL:", urlField);
		} 
		catch (MalformedURLException e) {
			e.printStackTrace();
		}

		//add MaskFormatter
		try {
			MaskFormatter formatter = new MaskFormatter("###-##-####");
			formatter.setPlaceholderCharacter('0');
			JFormattedTextField ssnField = new JFormattedTextField(formatter);

			ssnField.setValue("078-05-1120");
			addRow("SSN MASK:", ssnField);
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}

		//add IPAddressFormatter
		JFormattedTextField ipField = new JFormattedTextField(new IPAddressFormatter());
		ipField.setValue(new byte[]{(byte)130, 63, 86,66});
		addRow("IP Address:", ipField);

	}



	/**
	 * add a label and a JFormattedTextField to mainPanel
	 * @param labelText
	 * @param field
	 */
	private void addRow(String labelText, final JFormattedTextField field) {

		mainPanel.add(new JLabel(labelText));
		mainPanel.add(field);
		final JLabel valueLabel = new JLabel();
		mainPanel.add(valueLabel);
		okButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object value = field.getValue();
				if(value.getClass().isArray()){
					StringBuilder builder = new StringBuilder();
					builder.append('{');
					for(int i = 0; i < Array.getLength(value); i++){
						if(i > 0){
							builder.append(",");
						}
						builder.append(Array.get(valueLabel,  i ).toString());
					}
					builder.append('}');
					valueLabel.setText(builder.toString());
				}
				else{
					valueLabel.setText(value.toString());
				}
			}
		});
	}

	private int WIDTH = 500;
	private int WEIGTH = 400;
	private JButton okButton;
	private JPanel mainPanel;
}


/*
 * A filter that restricts input to digits and a '-' sign
 * */
class IntFilter extends DocumentFilter{
	@Override
	public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException{
		StringBuilder builder = new StringBuilder(string);
		for(int i = builder.length() - 1; i >= 0; i--){
			int cp = builder.codePointAt(i);
			if(!Character.isDigit(cp) && cp != '-'){
				builder.deleteCharAt(i);
				if(Character.isSupplementaryCodePoint(cp)){
					i--;
					builder.deleteCharAt(i);
				}
			}
		}
		super.insertString(fb, offset, builder.toString(), attr);
	}
	
	@Override
	public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attr) throws BadLocationException{
		if(string != null){
			StringBuilder builder = new StringBuilder(string);
			for(int i = builder.length() - 1; i >= 0; i--){
				int cp = builder.codePointAt(i);
				if(!Character.isDigit(cp) && cp != '-'){
					builder.deleteCharAt(i);
					if(Character.isSupplementaryCodePoint(cp)){
						i--;
						builder.deleteCharAt(i);
					}
				}
				
			}
			string = builder.toString();
			
		}
		super.replace(fb, offset, length, string, attr);
	}
}

class JFormattedTextFieldVerifier extends InputVerifier{

	/* 
	 * @see javax.swing.InputVerifier#verify(javax.swing.JComponent)
	 */
	@Override
	public boolean verify(JComponent arg0) {
		JFormattedTextField field = (JFormattedTextField) arg0;
		return field.isEditValid();
	}
}

class IPAddressFormatter extends DefaultFormatter{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1631567198258342232L;

	@Override
	public String valueToString(Object value)throws ParseException{
		if(!(value instanceof byte[])){
			throw new ParseException("Not a byte[]", 0);
		}
		byte[] a = (byte[])value;
		if(a.length != 4){
			throw new ParseException("Length != 4", 0);
		}
		
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < 4; i++){
			int b = a[i];
			if(b < 0){
				b += 256;
			}
			builder.append(String.valueOf(b));
			if(i < 3){
				builder.append('.');
			}
		}
		return builder.toString();		
	}
	
	@Override
	public Object stringToValue(String text) throws ParseException{
		StringTokenizer tokenizer = new StringTokenizer(text, ".");
		byte[] a = new byte[4];
		for(int i = 0; i < 4; i++){
			int b = 0;
			if(!tokenizer.hasMoreTokens()){
				throw new ParseException("Too few bytes", 0);
			}
			b = Integer.parseInt(tokenizer.nextToken());
			if(b < 0 || b >= 256){
				throw new ParseException("out of range", 0);
			}
			a[i] = (byte)b;
		}
		
		if(tokenizer.hasMoreTokens()){
			throw new ParseException("too many bytes", 0);
		}
		return a;
		
	}
}



