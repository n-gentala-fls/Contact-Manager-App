package Application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class FileManager {


	public static void addToCSV(JTable table) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Set save file");
		int userSelection = fileChooser.showSaveDialog(fileChooser);
		if(userSelection == JFileChooser.APPROVE_OPTION) {
			File fileSave = fileChooser.getSelectedFile();
			
			try{//write to file
			FileWriter fileWriter = new FileWriter(fileSave);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			for(int i = 0; i < table.getRowCount(); ++i) {
				Boolean isSelected = (Boolean) table.getValueAt(i, 4);
				
				if(isSelected) {
				for (int j = 0; j < table.getColumnCount() -1; ++j) {
					bufferedWriter.write(table.getValueAt(i, j).toString() + ",");
				}
				bufferedWriter.newLine();
			}
		}
			bufferedWriter.close();
			fileWriter.close();
			} catch (IOException ex) {
				JOptionPane.showInputDialog(fileChooser, "ERROR", "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
			}
			
			
		}
	}
	
	
	
}
