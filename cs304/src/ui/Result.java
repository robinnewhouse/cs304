package ui;

import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Result extends JScrollPane {
	
	ResultSet rs;
	String dataArray[][];
	String columnHeader[];
	JScrollPane scroll;
	
	public Result(ResultSet rs) {
		this.rs = rs;
		createPanel();
	}
	
	public void createPanel() {
		try {
			// get info on ResultSet
			ResultSetMetaData rsmd = rs.getMetaData();
			// get number of columns
			int numCols = rsmd.getColumnCount();
			// get number of rows
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
				System.out.printf("%s\n",columnHeader[i]);
			}
			
			//Populate 2d array with all the results
			int k = 0;
			while(rs.next())
			{
				for(int i = 0; i < numCols; i++)
				{
					String blah = rs.getString(columnHeader[i].toLowerCase());
					dataArray[k][i] = blah;
				}
				k++;
			}
			
			for(int i = 0; i < numRows; i++)
			{
				for(int p = 0; p < numCols; p++)
				{
					System.out.printf("%s", dataArray[i][p]);
				}
				System.out.println("");
			}
		} catch (SQLException e) {}
		
		
		JTable table = new JTable(dataArray, columnHeader);
		scroll = new JScrollPane(table);
		//table.setFillsViewportHeight(true);	
	}
	
	public JScrollPane getPanel() {
		return scroll;
	}

}
