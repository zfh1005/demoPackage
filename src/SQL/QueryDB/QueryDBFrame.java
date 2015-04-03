/**
 * 
 */
package SQL.QueryDB;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import SwingDemo.GBC;

/**
 * @author zfh1005
 * @date 2015/03/31
 * @version v0.0.1
 *
 */
public class QueryDBFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7996420834616566497L;

	public QueryDBFrame(){
		//frame setting
		setTitle("QueryDB");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLayout(new GridBagLayout());
		
		//add control into frame
		authorsJComboBox = new JComboBox<>();
		authorsJComboBox.setEditable(false);
		authorsJComboBox.addItem("Any");
		
		publishersJComboBox = new JComboBox<>();
		publishersJComboBox.setEditable(false);
		publishersJComboBox.addItem("Any");
		
		resulJTextArea = new JTextArea(4, 50);
		resulJTextArea.setEditable(false);
		
		priceChangeJTextField = new JTextField(8);
		priceChangeJTextField.setText("-5.00");
		
		//Database operation
		try {
			connection = getConnection();
			Statement statement = connection.createStatement();
			
			String queryString = "SELECT Name FROM Authors";
			ResultSet rSet = statement.executeQuery(queryString);
			while(rSet.next()){
				authorsJComboBox.addItem(rSet.getString(1));
			}
			rSet.close();
			
			queryString = "SELECT Name FROM Publishers";			
			rSet = statement.executeQuery(queryString);
			while(rSet.next()){
				publishersJComboBox.addItem(rSet.getString(1));
			}
			rSet.close();
			
			statement.close();
			
		} 
		catch(SQLException e){
			for(Throwable t : e){
				resulJTextArea.append(t.getMessage());
			}
		}
		catch (IOException e) {
			resulJTextArea.append("" + e);
		}
		
		add(authorsJComboBox, new GBC(0, 0, 2, 1));
		add(publishersJComboBox, new GBC(2, 0, 2, 1));
		
		JButton queryButton = new JButton("Query");
		queryButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				executeQuery();				
			}
		});		
		add(queryButton, new GBC(0, 1, 1, 1).setInsets(3));
		
		JButton changeButton = new JButton("Change prices");
		changeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				changePrice();				
			}
		});
		add(queryButton, new GBC(2, 1, 1, 1).setInsets(3));
		
		add(priceChangeJTextField, new GBC(3, 1, 1, 1).setFill(GBC.HORIZONTAL));
		
		add(new JScrollPane(resulJTextArea), new GBC(0, 2, 4, 1).setFill(GBC.BOTH).setWeight(100, 100));
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				try {
					if(connection != null){
						connection.close();
					}
					
				} catch (SQLException e) {
					for(Throwable t : e){
						e.printStackTrace();
					}
				}
			}
		});		
	}
	
	
	/*
	 * Gets a connection from the properties specified in the file database.properies.
	 * @return the database connection
	 * */
	public static Connection getConnection() throws SQLException, IOException{
		Properties props = new Properties();
		FileInputStream inputStream = new FileInputStream("database.properties");
		props.load(inputStream);
		inputStream.close();
		
		String driversString = props.getProperty("jdbc.drivers");
		if(driversString != null){
			System.setProperty("jdbc.drivers", driversString);
		}
		
		String urlString = props.getProperty("jdbc.url");
		String userNameString = props.getProperty("jdbc.username");
		String passwordString = props.getProperty("jdbc.password");
		
		return DriverManager.getConnection(urlString, userNameString, passwordString);
	}
	
	/*
	 * Executes the selected query
	 * */
	private void executeQuery() {
		ResultSet rSet = null;
		try{
			String author = (String) authorsJComboBox.getSelectedItem();
			String publisher = (String) publishersJComboBox.getSelectedItem();
			
			//run the database query
			if(!author.equals("Any") && !publisher.equals("Any")){
				if(authorPublisherQueryStatement == null){
					authorPublisherQueryStatement = connection.prepareStatement(authorPublisherQuery);
				}
				authorPublisherQueryStatement.setString(1, author);
				authorPublisherQueryStatement.setString(2, publisher);
				rSet = authorPublisherQueryStatement.executeQuery();			
			}
			else if(!author.equals("Any") && publisher.equals("Any")){
				if(authorQueryStatement == null){
					authorQueryStatement = connection.prepareStatement(authorQuery);
				}
				authorQueryStatement.setString(1, author);
				rSet = authorQueryStatement.executeQuery();
			}
			else if (author.equals("Any") && !publisher.equals("Any")){
				if (publisherQueryStatement == null) {
					publisherQueryStatement = connection.prepareStatement(publisherQuery);
				}
				publisherQueryStatement.setString(1, publisher);
				rSet = publisherQueryStatement.executeQuery();
			}
			else {
				if(allQueryStatement == null){
					allQueryStatement = connection.prepareStatement(allQuery);
				}
				rSet = allQueryStatement.executeQuery();
			}
			
			//display the result
			resulJTextArea.setText("");
			while (rSet.next()) {
				resulJTextArea.append(rSet.getString(1));
				resulJTextArea.append(", ");
				resulJTextArea.append(rSet.getString(2));
				resulJTextArea.append("\n");
			}
			rSet.close();
		}
		catch(SQLException e){
			for(Throwable t : e){
				resulJTextArea.append(t.getMessage());
			}
		}
	}
	
	/*
	 * Executes an update statement to change price
	 * */
	public void changePrice(){
		String publisher = (String) publishersJComboBox.getSelectedItem();
		if(publisher.equals("Any")){
			resulJTextArea.setText("I am sorry, but I cannot do that");
			return;
		}
		
		try{
			if (priceUpdateStatement == null){
				priceUpdateStatement = connection.prepareStatement(publisher);
			}
			priceUpdateStatement.setString(1, priceChangeJTextField.getText());
			priceUpdateStatement.setString(2, publisher);
			
			int r = priceUpdateStatement.executeUpdate();
			resulJTextArea.setText(r + " record uodated. ");
		}
		catch(SQLException e){
			for(Throwable t : e){
				resulJTextArea.append(t.getMessage());
			}
		}
	}
	
	//date define	
	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 400;
	
	private JComboBox<Object> authorsJComboBox;
	private JComboBox<Object> publishersJComboBox;
	private JTextField priceChangeJTextField;
	private JTextArea resulJTextArea;
	private Connection connection;
	private PreparedStatement authorQueryStatement;
	private PreparedStatement authorPublisherQueryStatement;
	private PreparedStatement publisherQueryStatement;
	private PreparedStatement allQueryStatement;
	private PreparedStatement priceUpdateStatement;
	
	private static final String authorPublisherQuery = 
			"SELECT Books.Price, Books.Title FROM Books, BooksAuthors, Authors, Publishers"
			+ "WHERE Authors.Author_Id = BooksAuthors.Author_Id"
			+ "AND BooksAuthors.ISBN = Books.ISBN"
			+ "AND Books.Publisher_Id = Publishers.Publisher_Id"
			+ "AND Authors.Name = ?"
			+ "AND Publishers.Name = ?";
			
	private static final String authorQuery = 
			"SELECT Books.Price, Books.Title FROM Books, BooksAuthors, Authors"
			+ "WHERE Author.Author_Id = BooksAuthors.Author_Id"
			+ "AND Books BooksAuthors.ISBN = Books.ISBN"
			+ "Authors.Name = ?";
	
	private static final String publisherQuery = 
			"SELECT Books.Price, Books.Title FROM Books, Publishers"
			+ "WHERE Author.Author_Id = BooksAuthors.Author_Id"
			+ "Authors.Name = ?";
	
	private static final String allQuery = 
			"SELECT Books.Price, Books.Title FROM Books";
	
	private static final String priceUpdate = 
			"UPDATE Books "
			+ "SET Price = Price + ?"
			+ "WHERE Books.Publisher_Id = (SELECT Publisher_Id FROME Publishers WHERE Name = ? )";
	
	
	
	
	
	
}
