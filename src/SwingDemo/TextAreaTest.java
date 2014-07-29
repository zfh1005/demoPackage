/**
 * 
 */
package SwingDemo;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author zfh1005
 */
public class TextAreaTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		TextAreaFrame frame = new TextAreaFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

/*
 * A frame with a panel for sketching a figure
 * */
class TextAreaFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9014625956011293135L;
	
	public TextAreaFrame(){
		setTitle("MouseTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);		

		buttonPanel = new JPanel();

		//add insert button into the text area
		JButton insertButton = new JButton("Insert");
		buttonPanel.add(insertButton);
		insertButton.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						textArea.append("theasjdaldjalsk alsjkdalksd");						
					}					
				}
			);		
		
		
		//add wrap button to turn line wrapping in and off
		wrapButton = new JButton("Wrap");
		buttonPanel.add(wrapButton);
		wrapButton.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						boolean wrap = !textArea.getLineWrap();
						textArea.setLineWrap(wrap);
						scrollPane.revalidate();
						wrapButton.setText(wrap ? "No Warp":"Wrap");						
					}					
				});
		
		//add save button to save file
		JButton saveButton = new JButton("Save");
		buttonPanel.add(saveButton);
		saveButton.addActionListener(
				new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						String tempString = textArea.getText();
						WriteFile("c:\\1.1111.txt", tempString);						
					}					
				});
		
		
		add(buttonPanel, BorderLayout.SOUTH);
		
		
		
		//add a text area with scrollbars
		textArea = new JTextArea(8, 40);
		scrollPane = new JScrollPane(textArea);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	public void WriteFile(String pathName, String dataString){
		File file = new File(pathName);
		if(!file.exists()){
			try {
				file.createNewFile();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			FileOutputStream out = new FileOutputStream(pathName);
			PrintStream p =new PrintStream(out);
			p.print(dataString);
			p.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
	}
	
	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 600;
	private JPanel buttonPanel;
	private JTextArea textArea;
	private JButton wrapButton;
	private JScrollPane scrollPane;
}
