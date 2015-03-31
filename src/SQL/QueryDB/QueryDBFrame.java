/**
 * 
 */
package SQL.QueryDB;

import java.sql.Connection;
import java.sql.PreparedStatement;

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

	
	//date define
	
	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 400;
	
	private JComboBox authorsJComboBox;
	private JComboBox publishersJComboBox;
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
