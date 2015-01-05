/**
 * 
 */
package SQL.ExecSQL;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author zfh1005
 *
 */
public class ExecSQL {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Scanner in;
			if(args.length == 0){
				in = new Scanner(System.in);
			}
			else{
				in = new Scanner(new File(args[0]));
			}
			
			Connection connection = getConnection();
			
			try {
				Statement statement = connection.createStatement();
				while(true){
					if(args.length == 0){
						System.out.println("Entry command or EXIT to exit");
					}
					if(!in.hasNext()){
						return ;
					}
					String line = in.nextLine();
					if(line.equalsIgnoreCase("EXIT")){
						return;
					}
					if(line.trim().endsWith(";")){ //remove trailing semicolon
						line = line.trim();
						line = line.substring(0, line.length() - 1);
					}
					
					try{
						boolean hasResultSet = statement.execute(line);
						if(hasResultSet){
							showResultSet(statement);
						}
					}
					catch(SQLException exception){
						for(Throwable eThrowable : exception){
							eThrowable.printStackTrace();
						}
					}
					
				}
				
			} 
			finally{
				connection.close();
			}
			
		}
		catch (SQLException e) {
			for(Throwable throwable : e){
				throwable.printStackTrace();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Gets a connection from the properties specified in the file database.properties
	 * @return the database connection
	 * 
	 * */
	public static Connection getConnection()throws SQLException, IOException{
		Properties properties = new Properties();
		FileInputStream inputStream = new FileInputStream("database.properties");
		properties.load(inputStream);
		inputStream.close();
		
		String driversString = properties.getProperty("jdbc.drivers");
		if(driversString != null){
			System.setProperty("jdbc.drivers", driversString);
		}
		String urlString = properties.getProperty("jdbc.url");
		String usernameString = properties.getProperty("jdbc.username");
		String passwordString = properties.getProperty("jdbc.password");
		
		return DriverManager.getConnection(urlString, usernameString, passwordString);
				
	}
	
	/*
	 * Prints a result set
	 * @param stat the statement whose result set should be printed
	 * */
	public static void showResultSet(Statement statement)throws SQLException{
		ResultSet resultSet = statement.getResultSet();
		ResultSetMetaData metData = resultSet.getMetaData();
		int columnCount = metData.getColumnCount();
		for(int i = 1; i <= columnCount; i++){
			if(i > 1){
				System.out.print(", ");
			}
			System.out.print(metData.getColumnLabel(i));
		}
		System.out.println();
		
		while(resultSet.next()){
			for(int i = 1; i <= columnCount; i++){
				if(i > 1){
					System.out.print(", ");
				}
				System.out.print(resultSet.getString(i));
			}
			System.out.println();
		}
		
		resultSet.close();
	}
	

}
