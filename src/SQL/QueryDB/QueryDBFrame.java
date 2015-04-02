/**
 * 
 */
package SQL.QueryDB;

import java.awt.GridBagLayout;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.rowset.JdbcRowSet;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
		} 
		catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
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
