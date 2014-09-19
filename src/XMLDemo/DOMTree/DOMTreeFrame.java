/**
 * 
 */
package XMLDemo.DOMTree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingWorker;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

/**
 * @author zfh1005
 *
 */

/*
 * this frame contains a tree that displays the contents of an XML document.
 * 
 * */
public class DOMTreeFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 704373134769168032L;
	
	public DOMTreeFrame(){
		setTitle("DOMTreeTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		JMenu fileJMenu = new JMenu("File");
		JMenuItem openItem = new JMenuItem("Open");
		openItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				openFile();
				
			}
		});
		fileJMenu.add(openItem);
		
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}
		});
		fileJMenu.add(exitItem);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileJMenu);
		setJMenuBar(menuBar);		
	}
	
	/*
	 * Open a file and load the document
	 * 
	 * */
	public void openFile(){
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		
		chooser.setFileFilter(new javax.swing.filechooser.FileFilter(){
			@Override
			public boolean accept(File f){
				return f.isDirectory() || f.getName().toLowerCase().endsWith(".xml");
			}

			@Override
			public String getDescription() {
				return "XML File";
			}
			
		});
		
		int r = chooser.showOpenDialog(this);
		if(r != JFileChooser.APPROVE_OPTION){
			return;
		}
		final File file = chooser.getSelectedFile();
		
		
		new SwingWorker<Document, Void>() {
			/*
			 * @see javax.swing.SwingWorker#done()		 * 
			 */
			@Override
			protected Document doInBackground() throws Exception{
				if(builder == null){
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					builder = factory.newDocumentBuilder();
				}
				return builder.parse(file);
			}
			
			/* 
			 * @see javax.swing.SwingWorker#done()
			 */
			@Override
			protected void done() {
				try{
					Document document = get();
					JTree tree = new JTree(new DOMTreeModel(document));
					tree.setCellRenderer(new DOMTreeCellRenderer());
					
					setContentPane(new JScrollPane(tree));
					validate();
				}
				catch(Exception exception){
					JOptionPane.showMessageDialog(DOMTreeFrame.this, exception);
				}
				super.done();
			}
		}.execute();
	}
	
	private DocumentBuilder builder;
	private static int DEFAULT_WIDTH = 300;
	private static int DEFAULT_HEIGHT = 400;
}
