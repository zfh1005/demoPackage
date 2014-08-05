/**
 * 
 */
package ExceptionDemo;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author zfh1005
 *
 */
public class ConsoleWindow {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

	}
	
	public static void init(){
		JFrame frame = new JFrame();
		frame.setTitle("ConsoleWindow");
		final JTextArea output = new JTextArea();
		frame.add(new JScrollPane(output));
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocation(LEFT, TOP);
		frame.setFocusableWindowState(false);
		frame.setVisible(true);
		
		//define a PrintStream that sends its bytes to the output text area
		PrintStream consoleStream = new PrintStream(new OutputStream(){
		
			@Override
			public void write(int b) throws IOException {			
			}
			
			@Override
			public void write(byte[] b, int off, int len){
				output.append(new String(b, off, len));
			}
		});
		
		//define a PrintStream that sends its bytes to the output text area
		//BufferedInputStream inputStream = new BufferedInputStream(System.in);

		//set both System.out and System.err to that stream
		System.setOut(consoleStream);
		System.setErr(consoleStream);	
		//System.setIn(inputStream);
	}
	
	private static final int WIDTH = 300;
	private static final int HEIGHT = 200;
	private static final int TOP = 300;
	private static final int LEFT = 300;

}
