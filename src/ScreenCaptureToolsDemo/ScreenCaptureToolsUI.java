package ScreenCaptureToolsDemo;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JWindow;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class ScreenCaptureToolsUI extends JWindow{
	
	private CaptureScreenTools parent;

	public ScreenCaptureToolsUI(CaptureScreenTools parent,int x,int y) {
		getContentPane().setFont(new Font("PMingLiU", Font.PLAIN, 100));
		this.parent=parent;
		this.init();
		this.setLocation(x, y);
		this.pack();
		this.setVisible(true);
	}

	private void init(){

		getContentPane().setLayout(new BorderLayout());
		JToolBar toolBar=new JToolBar("JavaCapture");
		toolBar.setToolTipText("Capture");
		toolBar.setFont(new Font("PMingLiU", Font.BOLD | Font.ITALIC, 30));

		//保存按键
		JButton saveButton=new JButton(new ImageIcon("images/save.gif"));
		saveButton.setToolTipText("Save");
		saveButton.setBackground(Color.GREEN);
		saveButton.setText("Save");
		saveButton.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					parent.saveImage();
				} 
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		toolBar.add(saveButton);

		//关闭按键
		JButton closeButton=new JButton(new ImageIcon("images/close.gif"));
		closeButton.setToolTipText("Close");
		closeButton.setBackground(Color.RED);
		closeButton.setText("Close");
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		toolBar.add(closeButton);

		getContentPane().add(toolBar,BorderLayout.NORTH);
		getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{toolBar, saveButton, closeButton}));
	}


}
