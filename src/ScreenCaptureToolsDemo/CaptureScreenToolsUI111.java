package ScreenCaptureToolsDemo;

import java.awt.BorderLayout;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class CaptureScreenToolsUI111 {

	public static void main(String[] args) {
		String userDir = System.getProperty("user.dir");
		File tempFile = new File("E:\\Java\\Output\\ScreenCaptureTools", "temp.png");
		//CaptureScreen capture = CaptureScreen.getInstance();
		//capture.CaptureScreen();
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JLabel imageBox = new JLabel();
		panel.add(BorderLayout.CENTER, imageBox);
		//imageBox.setIcon(capture.getPickedIcon);
		

	}

}
