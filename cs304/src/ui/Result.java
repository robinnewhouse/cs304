package ui;

import java.sql.ResultSet;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Result class responsible for parsing the ResultSet returned from a 
 * query and creating a Scrollable JPanel to display the results on
 * the GUI.
 * 
 * ResultSet's can be dynamic in size, in both number of variables and
 * the number of results
 * 
 * @author Abe Friesen
 *
 */
public class Result extends JScrollPane {
	
	ResultSet rs;
	String dataArray[][];
	String columnHeader[];
	JScrollPane scroll;
	
	public Result(ResultSet rs) {
		this.rs = rs;
		createPanel();
	}
	
	/**
	 * Parses the ResultSet and creates a Table from the results
	 * to pass to the GUI
	 */
	public void createPanel() {
		try {
			//Get info on ResultSet
			ResultSetMetaData rsmd = rs.getMetaData();
			//Get number of columns
			int numCols = rsmd.getColumnCount();
			if(numCols == 0)
				JOptionPane.showMessageDialog(null, "No Results Found");
			//Get number of rows(results)
			int numRows = 0;
			try {
			    rs.last();
			    numRows = rs.getRow();
			    rs.beforeFirst();
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
			dataArray = new String[numRows][numCols];
			columnHeader = new String[numCols];

			//Add column names to a column header
			for (int i = 0; i < numCols; i++)
			{
				columnHeader[i] = rsmd.getColumnName(i+1);
			}
			
			//Populate 2d array with all the results
			int k = 0;
			while(rs.next())
			{
				for(int i = 0; i < numCols; i++)
				{
					String columnTitle = rs.getString(columnHeader[i].toLowerCase());
					dataArray[k][i] = columnTitle;
				}
				k++;
			}
			
		} catch (SQLException e) {}
		
		//Creates the Scroll table with the data from the ResultSets
		JTable table = new JTable(dataArray, columnHeader);
		scroll = new JScrollPane(table);
	}
	
	/**
	 * Returns the scroll pane containing the table with the results
	 * @return
	 * 		JScrollPane with table containing results from ResultSet
	 */
	public JScrollPane getPanel() {
		return scroll;
	}

}
