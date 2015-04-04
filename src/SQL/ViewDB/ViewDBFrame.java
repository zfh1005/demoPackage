/**
 * 
 */
package SQL.ViewDB;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.rowset.CachedRowSet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import com.sun.rowset.CachedRowSetImpl;  

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
		
		
		//tableNameBox define and listener
		tableNameBox = new JComboBox();
		tableNameBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showTable((String) tableNameBox.getSelectedItem());				
			}
		});		
		add(tableNameBox, BorderLayout.NORTH);
		
		
		
	}
	
	/*
	 * Prepares the text field for showing a new table, and shows the first row.
	 * @param tableName the name of the table to display
	 * 
	 * */
	@SuppressWarnings("restriction")
	public void showTable(String tableNameString){
		try{
			//open connection
			Connection connection = getConnection();
			try{
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableNameString);
				
				//copy into cached row set
				crs = new CachedRowSetImpl();
				crs.setTableName(tableNameString);
				crs.populate(resultSet);
			}
			finally{
				connection.close();
			}
			
			if(scrollPane != null){
				remove(scrollPane);
			}
			
			dataPanel = new DataPanel(crs);
			scrollPane = new JScrollPane(dataPanel);
			add(scrollPane, BorderLayout.CENTER);
			validate();
			showNextRow();			
			
		}
		catch(SQLException e){
			
		}
		
	}

	
	/*
	 * Gets a connection from the properties specified in the file database.properies.
	 * @return the database connection
	 * */
	public Connection getConnection() throws SQLException{
		String urlString = props.getProperty("jdbc.url");
		String userNameString = props.getProperty("jdbc.username");
		String passwordString = props.getProperty("jdbc.password");
		
		return DriverManager.getConnection(urlString, userNameString, passwordString);
	}
	
	
	/*
	 * Moves to the previous table row 
	 * */
	public void showPreviousRow(){
		try{
			if(crs == null || crs.isFirst()){
				return;
			}
			
			crs.previous();
			dataPanel.showRow(crs);	
		}
		catch(SQLException e){
			for(Throwable throwable : e){
				throwable.printStackTrace();
			}
		}
	}
	
	/*
	 * Delete current table row.
	 * */
	public void deleteRow(){
		try {
			Connection connection = getConnection();
			
			try {
				crs.deleteRow();
				crs.acceptChanges(connection);
				if(!crs.isLast()){
					crs.next();
				}
				else if(!crs.isFirst()){
					crs.previous();
				}
				else{
					crs = null;
				}
				dataPanel.showRow(crs);
			} 
			finally{
				connection.close();
			}
			
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e);
		}
	}
	
	/*
	 * Moves to the next table row
	 * */
	public void showNextRow(){
		try {
			if(crs == null || crs.isLast()){
				return;
			}
			crs.next();
			
			//reference the DataPanel.showRow to display the database's Row.
				
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e);
		}
	}
	
	/*
	 * Saves all changes.
	 * */
	public void saveChanges(){
		try {
			Connection connection = getConnection();
			
			try {
				dataPanel.setRow(crs);
				crs.acceptChanges(connection);
			} 
			finally{
				connection.close();
			}
			
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e);
		}
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
