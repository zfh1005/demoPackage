/**
 * 
 */
package ExceptionDemo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;

/**
 * @author zfh1005
 *
 */
public class ImageViewerFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3053267249941453299L;

	public ImageViewerFrame(){
		logger.entering("ImageViewerFrame", "<init>");
		
		//set up menu bar
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
		
		JMenu menu = new JMenu("File");
		menubar.add(menu);
		
		JMenuItem openItem = new JMenuItem("Open");
		menu.add(openItem);
		openItem.addActionListener(new FileOpenListener());
		
		JMenuItem exitItem = new JMenuItem("Exit");
		menu.add(exitItem);
		exitItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				logger.fine("Exiting.");
				System.exit(0);				
			}			
		});
		
		//use a label display the images
		label = new JLabel();
		add(label);
		logger.exiting("ImageViewerFrame", "<init>");	
		
	}
	
	
	
	private JLabel label;
	private static Logger logger = Logger.getLogger("DemoProject.ExceptionDemo");
	
	
	public class FileOpenListener implements ActionListener{
		/* 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			logger.entering("ImageViewerFrame.FileOpenListener", "actionPerformed", e);
			
			//set file chooser
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("."));
			
			//accept all files ending with txt
			chooser.setFileFilter(new FileFilter(){

				@Override
				public boolean accept(File f) {				
					return f.getName().toLowerCase().endsWith(".txt") || f.isDirectory();
				}

				@Override
				public String getDescription() {
					return ".txt file";
				}			
			});
			
			//show file chooser dialog
			int r = chooser.showOpenDialog(ImageViewerFrame.this);		
			
			//if image file accepted, set it as icon of the label
			if(r == JFileChooser.APPROVE_OPTION){
				String name = chooser.getSelectedFile().getParent();
				logger.log(Level.FINE, "Reading file{0}", name);
				label.setIcon(new ImageIcon(name));
			}
			else{
				logger.fine("file open dialog conceled.");
			}
			logger.exiting("FileOpenListener", "actionPerformed");			
		}
	}	
	
}
