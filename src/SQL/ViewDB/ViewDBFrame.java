/**
 * 
 */
package SQL.ViewDB;

import java.awt.Component;
import java.util.Properties;

import javax.sql.rowset.CachedRowSet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * @author zfh1005
 *
 */

/*
 * This frame that holds the data panel and the navigation buttons
 * */
public class ViewDBFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3077875055654780889L;
	
	public ViewDBFrame(){
		setTitle("ViewDB");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		
	}

	
	//data define	
	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 400;
	
	private JButton priviousButton;
	private JButton nextButton;
	private JButton deleteButton;
	private JButton savebButton;
	private DataPanel dataPanel;
	private Component scrollPane;
	private JComboBox tableNameBox;
	private Properties props;
	private CachedRowSet crs;
	
	
}
