package Application;
import javax.swing.table.DefaultTableModel;



public class Operations {
	  private static DefaultTableModel tableModel;
	  //setter
	  public static void setTableModel(DefaultTableModel model) {
	        tableModel = model;
	    }
	  //Method for adding data to row
	public static void addNewContact(String firstName, String lastName, String email, String phone) {
		if (tableModel != null) {
		tableModel.addRow(new Object[] {firstName, lastName, email, phone, false});
	} else {
		System.err.println("Table model is not set. Cannot add contact.");
    	}
	}
	
	public static boolean isRowSelected(int row) {
		Boolean isSelected = (Boolean) tableModel.getValueAt(row, 4);
		return isSelected != null && isSelected;
	}
	
	public static void clearContact() {
		for (int row = tableModel.getRowCount() - 1; row >=0; --row) {
			if (isRowSelected(row)) {
				tableModel.removeRow(row);
			}
		}
		
	}
	public static void editContact() {
		//Logic implementation coming soon.
		
	}



}
