/**
 * 
 */
package SwingDemo;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;

/**
 * @author zfh1005
 */
public class FileChooserTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ImageVewerFrame frame = new ImageVewerFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

/*
 * a JFrame 
 * */

class ImageVewerFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5053483452849711348L;
	public ImageVewerFrame(){
		setTitle("Dialog Test");
		setSize(WIDTH, HEIGHT);
		
		//set up menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("File");
		menuBar.add(menu);
		
		JMenuItem openItem = new JMenuItem("Open");
		menu.add(openItem);
		openItem.addActionListener(new FileOpenListener());
		
		JMenuItem exitItem = new JMenuItem("Exit");
		menu.add(exitItem);
		exitItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
				
			}
		});
		
		//use a label to dispaly the image
		label = new JLabel();
		add(label);
		
		//set up file chooser
		chooser = new JFileChooser();
		
		//accept all image files ending with .jpg
		final ExtensionFileFilter filter = new ExtensionFileFilter();
		filter.addExtension(".jpg");
		filter.addExtension(".ipeg");
		filter.addExtension(".gif");
		filter.setDescription("Image Files");
		
		chooser.setFileFilter(filter);
		chooser.setAccessory(new ImagePreviewer(chooser));
		chooser.setFileView(new FileIconView(filter, new ImageIcon("blue.gif")));
		
	}
	
	
	/*
	 * this is the listener for the File->Open menu item
	 * */
	private class FileOpenListener implements ActionListener{

		/* 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			chooser.setCurrentDirectory(new File("."));
			
			//show file chooser dialog
			int result = chooser.showOpenDialog(ImageVewerFrame.this);
			if(result == JFileChooser.APPROVE_OPTION){
				String name = chooser.getSelectedFile().getPath();
				label.setIcon(new ImageIcon(name));
			}			
		}		
	}
	
	
	private int WIDTH = 300;
	private int HEIGHT = 200;	
	private JLabel label;
	private JFileChooser chooser;

}

/*
 * this file filter mattches all files with given set of extendsions
 * 
 * */

class ExtensionFileFilter extends FileFilter{

	/* 
	 * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File arg0) {
		if(arg0.isDirectory()){
			return true;
		}
		String name = arg0.getName().toLowerCase();
		
		//check if the file name ends with any of the description
		for(String extension : extensions ){
			if(name.endsWith(extension)){
				return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.swing.filechooser.FileFilter#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}
	
	//sets a description for the file set that this file filter recognizes
	public void setDescription(String sDescription) {
		description = sDescription;
	}
	
	//adds an extension that this file filter recognizes
	public void addExtension(String extension){
		if(!extension.startsWith(".")){
			extension = "." + extension;
		}
		extensions.add(extension.toLowerCase());
	}
	
	private String description = "";
	private ArrayList <String> extensions = new ArrayList<String>();
}

/*
 * a file view that displays an icon for all files that match a file fiter
 * */
class FileIconView extends FileView{
	
	//constructs a FileIconView
	public FileIconView(FileFilter aFilter, Icon anIcon){
		filter = aFilter;
		icon = anIcon;
	}
	
	@Override
	public Icon getIcon(File f){
		if(f.isDirectory() && filter.accept(f)){
			return icon;
		}
		else{
			return null;
		}
	}
	
	
	private FileFilter filter;
	private Icon icon;
	
}


/*
 * a file chooser accesory that previews image
 * 
 * */
class ImagePreviewer extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -733931106381062930L;

	//constructs an IamgePreviewer
	public ImagePreviewer(JFileChooser chooser){
		setPreferredSize(new Dimension(100, 100));
		setBorder(BorderFactory.createEtchedBorder());
		
		chooser.addPropertyChangeListener(new PropertyChangeListener(){

			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				if(arg0.getPropertyName() == JFileChooser.SELECTED_FILE_CHANGED_PROPERTY){
					//the user has selected a new file
					File f = (File) arg0.getNewValue();
					if(f == null){
						setIcon(null);
						return;
					}
					
					//read the iamge into an icon
					ImageIcon icon = new ImageIcon(f.getPath());
					
					//if icon is too large to fit, scale it
					if(icon.getIconHeight() > getWidth()){
						icon = new ImageIcon(icon.getImage().
								getScaledInstance(getWidth(), -1, Image.SCALE_DEFAULT));
					}
					setIcon(icon);					
				}				
			}			
		});
		
		
	}
	
	
	
}







