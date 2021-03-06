package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.ResultSet;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

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
			if(numRows == 0)
				JOptionPane.showMessageDialog(null, "No Results Found");
			else
			{
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
				
				//Creates the Scroll table with the data from the ResultSets
				//Special formatting for book report if books are overdue
				if(numCols == 3)
				{
					if(columnHeader[0].contentEquals("CALL_NUMBER") && columnHeader[1].contentEquals("OUTDATE") &&
							columnHeader[2].contentEquals("INDATE"))
					{
						JTable table2 = new JTable(dataArray, columnHeader)
						{
							public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
							{
								Component c = super.prepareRenderer(renderer, row, column);
								//  Color row based on a dueDate value
								if (!isRowSelected(row)) {
									c.setBackground(getBackground());
									int modelRow = convertRowIndexToModel(row);
									String strDate = (String) getModel().getValueAt(modelRow, 2);
									SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
									Date date = null;
									try {
										date = format.parse(strDate);
									} catch (ParseException e) {
										e.printStackTrace();
									}
									if(date.before(new Date()))
									{
										c.setBackground(Color.blue);
										c.setForeground(Color.white);
									}
									else {
										c.setBackground(getBackground());
										c.setForeground(Color.black);
									}
								}
								return c;
							}
						};
						scroll = new JScrollPane(table2);
					}
				}
				else {
					JTable table = new JTable(dataArray, columnHeader);
					scroll = new JScrollPane(table);
				}
			}
			} catch (SQLException e) {}
	
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
