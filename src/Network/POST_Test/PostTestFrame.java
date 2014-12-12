/**
 * 
 */
package Network.POST_Test;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

/**
 * Program UI frame
 * @author zfh1005
 *
 */
public class PostTestFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3655975987319552883L;

	/*
	 * Makes a POST request and returns the server response.
	 * 
	 * @param urlString the URL to post to 
	 * @param nameValuePairs is a map of name/value pairs to supply in the request.
	 * @return the server reply(either from the input stream or the error stream)
	 *
	 * */
	public static String doPost (String urlString, Map<String, String> nameValuePairs)
	throws IOException{
		URL url = new URL(urlString);
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		
		PrintWriter outPrintWriter = new PrintWriter(connection.getOutputStream());
		boolean firstBoolean = true;
		for(Map.Entry<String, String> pair : nameValuePairs.entrySet()){
			if(firstBoolean){
				firstBoolean = false;
			}
			else{
				outPrintWriter.print("&");
			}
			String name = pair.getKey();
			String value = pair.getValue();
			outPrintWriter.print(name);
			outPrintWriter.print('=');
			outPrintWriter.print(URLEncoder.encode(value, "UTF-8"));
		}
		
		outPrintWriter.close();
		Scanner in;
		StringBuilder response = new StringBuilder();
		try{
			in = new Scanner(connection.getInputStream());			
		}
		catch(IOException IoException){
			if(!(connection instanceof HttpURLConnection)){
				throw IoException;
			}
			InputStream errInputStream = ((HttpURLConnection) connection).getErrorStream();
			if(errInputStream == null){
				throw IoException;
			}
			in = new Scanner(errInputStream);
		}
		
		while(in.hasNextLine()){
			response.append(in.nextLine());
			response.append("\n");
		}
		
		in.close();
		return response.toString();
	}
	
	public PostTestFrame(){
		setTitle("PostTest");
		
		northPanel = new JPanel();
		add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new GridLayout(0, 2));
		northPanel.add(new JLabel("Host: ", SwingConstants.TRAILING));
		
		final JTextField hostField = new JTextField();
		northPanel.add(hostField);
		northPanel.add(new JLabel("Action: ", SwingConstants.TRAILING));
		
		final JTextField actionField = new JTextField();
		northPanel.add(actionField);
		for(int i = 1; i <= 8; i++){
			northPanel.add(new JTextField());
		}
		
		final JTextArea resulTextArea = new JTextArea(20, 40);
		add(new JScrollPane(resulTextArea));
		
		JPanel southPanel = new JPanel();
		add(southPanel, BorderLayout.SOUTH);
		
		JButton addButton = new JButton("More");
		southPanel.add(addButton);
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				northPanel.add(new JTextField());
				northPanel.add(new JTextField());
				pack();				
			}
		});
		
		JButton getButton = new JButton("Get");
		southPanel.add(getButton);
		getButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				resulTextArea.setText("");
				
				final Map<String, String> post = new HashMap<String, String>();
				for(int i = 4; i <= northPanel.getComponentCount(); i += 2){
					String name = ((JTextField) northPanel.getComponent(i)).getText();
					if(name.length() > 0){
						String valueString = ((JTextField) northPanel.getComponent(i)).getText();
						post.put(name, valueString);						
					}
				}
				
				new SwingWorker<Void, Void>() {
					protected Void doInBackground() throws Exception {
						try{
							String urlString = hostField.getText() + "/" + actionField.getText();
							resulTextArea.setText(doPost(urlString, post));
						}
						catch(IOException exception){
							resulTextArea.setText("" + exception);
						}
						return null;						
					}
				}.execute();
			}
		});
		pack();		
	}
	
	private JPanel northPanel;
}
