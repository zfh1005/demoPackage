/**
 * 
 */
package functionDemo;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * This function is demo get Image from server
 * @author zfh1005
 *
 */

public class GetImageDemo extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2452839280801510197L;
	
	JTextField jtfUrl;  //Image address URL
	JButton jbGetImage;  //Get image button
	Image image; //Get image
	Toolkit toolKit;  //Toolkit class, used for get image
	
	public GetImageDemo(){
		super("FromServerGetImage");  //call father's struts function.
		
		Container container = getContentPane();	//get container
		jtfUrl=new JTextField(18); 
		jbGetImage = new JButton("GetImage");  
		container.setLayout(new FlowLayout()); //layout setting
		container.add(jtfUrl);  //add comp to container
		container.add(jbGetImage);
		toolKit = getToolkit(); //get toolkit class
		
		jbGetImage.addActionListener(new ActionListener(){  //button action
			public void actionPerformed(ActionEvent ent){
				try{
					String urlStr = jtfUrl.getText();    //get image URL address
					URL url = new URL(urlStr);
					image = toolKit.getImage(url); //get image
					repaint(); //repaint frame
				}
				catch(MalformedURLException ex){
					ex.printStackTrace(); //output error information
				}
			}
		});
	
		setSize(320,160);  //setting frame size
		setVisible(true);  //setting frame visible
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		if (image != null){
			g.drawImage(image,100,70,this); 
		}
	}
	
	public static void main(String[] args){
		new GetImageDemo();
	}
}