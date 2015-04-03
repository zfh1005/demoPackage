/**
 * 
 */
package SQL.ViewDB;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.sql.RowSet;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author zfh1005
 *
 */

/*
 * This panel displays the contents of a result set.
 * */

public class DataPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3706776035748986715L;
	
	public DataPanel(RowSet rSet) throws SQLException{
		
		fields = new ArrayList<JTextField>();
		
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		
		ResultSetMetaData rsmdData = rSet.getMetaData();
		for(int i = 1; i <= rsmdData.getColumnCount(); i ++){
			gbc.gridy = i -1;
			
			String columnName = rsmdData.getColumnLabel(i);
			gbc.gridx = 0;
			gbc.anchor = GridBagConstraints.EAST;
			add(new JLabel(columnName), gbc);
			
			int columnWidth = rsmdData.getColumnDisplaySize(i);
			JTextField tb = new JTextField(columnWidth);
			if(!rsmdData.getColumnClassName(i).equals("java.lang.String")){
				tb.setEditable(false);
			}
			fields.add(tb);
			
			gbc.gridx = 1;
			gbc.anchor = GridBagConstraints.WEST;
			add(tb, gbc);
		}		
		
	}
	
	/*
	 * shows a database row by populating all next text fields with the colum values.
	 * */
	public void showRow(ResultSet rSet) throws SQLException{
		for (int i = 1; i <= fields.size(); i++){
			String fieldString =rSet.getString(i);
			JTextField tb = (JTextField) fields.get(i -1);
			tb.setText(fieldString);
		}
	}
	
	/*
	 * Updates changed data into the current row of the row set
	 * */
	public void setRow(RowSet rSet) throws SQLException{
		for (int i = 1; i <= fields.size(); i++){
			
			String fieldString = rSet.getString(i);
			JTextField tb = (JTextField) fields.get(i - 1);
			
			if(!fieldString.equals(tb.getText())){
				rSet.updateString(i, tb.getText());
			}			
		}
		rSet.updateRow();
	}
	
	//data define
	private ArrayList<JTextField> fields;
}
