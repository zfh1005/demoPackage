/**
 * 
 */
package StreamDemo.ZipDemo;

import java.awt.BorderLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

/**
 * @author zfh1005
 *
 */

/*
 * A frame with a text area to show the contents of a file inside a 'ZIP' archive, 
 * a combo box  different select different files in the archive, and menu to load a new archive.
 *  
 * */
public class ZipTestFrame extends JFrame{
	
	/**
	 * auto generation serialVersionUID
	 */
	private static final long serialVersionUID = -1645832524924882703L;
	
	/*
	 * the ZipTestFrame class instruct function
	 * */
	public ZipTestFrame(){
		setTitle("ZipTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		//add the menu and the open and exit menu items
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		
		JMenuItem openItem = new JMenuItem("Open");
		menu.add(openItem);
		openItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("."));
				int r = chooser.showOpenDialog(ZipTestFrame.this);
				if(r == JFileChooser.APPROVE_OPTION){
					zipname = chooser.getSelectedFile().getPath();
					fileComboBox.removeAllItems();
					scanZipFile();
				}				
			}
		});
		
		JMenuItem exitItem = new JMenuItem("Exit");
		menu.add(exitItem);
		exitItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
				
			}
		});
		
		menuBar.add(menu);
		setJMenuBar(menuBar);
		
		//add the text area and combo box
		fileTextArea = new JTextArea();
		fileComboBox = new JComboBox<Object>();
		fileComboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loadZipFile((String) fileComboBox.getSelectedItem());
				
			}
		});
		add(fileComboBox, BorderLayout.SOUTH);
		add(new JScrollPane(fileTextArea), BorderLayout.CENTER);
	}
	
	/*
	 * scans the contents of the ZIP archive and populates the combo box
	 * */
	public void scanZipFile(){
		new SwingWorker<Void, String>() {
			@Override
			protected Void doInBackground() throws IOException{
				ZipInputStream zInputStream = new ZipInputStream(new FileInputStream(zipname));
				ZipEntry entry;
				
				while((entry = zInputStream.getNextEntry()) != null){
					publish(entry.getName());
					zInputStream.closeEntry();
				}
				zInputStream.close();
				return null;				
			}
			
			/*
			protected void process(List<String> names){
				for(String name : names){
					fileComboBox.addItem(name);
				}
			}
			*/
		}.execute();
	}
	
	/*
	 * loads a file from the ZIP archive into the text area
	 * @param name the name of the file in the archive
	 * 
	 * */
	public void loadZipFile(final String name){
		fileComboBox.setEnabled(false);
		fileTextArea.setText("");
		new SwingWorker<Void, Void>(){
			@Override
			protected Void doInBackground() throws IOException{
				try{
					ZipInputStream zInputStream = new ZipInputStream(new FileInputStream(zipname));
					ZipEntry entry;
					
					//find entry with matching name in archive
					while((entry = zInputStream.getNextEntry()) != null){
						if(entry.getName().equals(name)){
							//read entry into test area
							Scanner in = new Scanner(zInputStream);
							while(in.hasNextLine()){
								fileTextArea.append(in.nextLine());
								fileTextArea.append("\n");
							}
							in.close();
						}
						zInputStream.close();
					}
				}
				catch(IOException e){
					e.printStackTrace();
				}			
				return null;
			}
			
			@Override
			protected void done() {
				fileComboBox.setEditable(true);
			}
		}.execute();
	}
	
	
	private final int DEFAULT_WIDTH = 300;
	private final int DEFAULT_HEIGHT = 200;
	private JComboBox<Object> fileComboBox;
	private JTextArea fileTextArea;
	private String zipname;	
}
