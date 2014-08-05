/**
 * 
 */
package ExceptionDemo;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author zfh1005
 *
 */
public class WindowHandler extends StreamHandler{
	public WindowHandler(){
		frame = new JFrame();
		final JTextArea output = new JTextArea();
		output.setEditable(false);
		frame.setSize(200, 200);
		frame.setTitle("runInfo");
		frame.add(new JScrollPane(output));
		frame.setFocusableWindowState(false);
		frame.setVisible(true);
		setOutputStream(new OutputStream(){
			@Override
			public void write(int b) throws IOException {			
			}
			
			@Override
			public void write(byte[] b, int off, int len){
				output.append(new String(b, off, len));
			}			
		});	
	}
	
	@Override
	public void publish(LogRecord record){
		if(!frame.isVisible()){
			return;
		}
		super.publish(record);
		flush();
	}
	
	private JFrame frame;
}
