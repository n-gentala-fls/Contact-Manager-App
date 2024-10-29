package Application;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Main {
    // Declare mainPanel and tableModel as public static for access in Operations
    public static JPanel mainPanel;
    private static DefaultTableModel list;
    private static JTextField nameFirstTextField, nameLastTextField, numberTextField, emailTextField;

    public static void main(String[] args) {
        // Create main window
        JFrame frame = new JFrame("Connect Vault");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 550);
        frame.setLayout(null);

        // Create main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.GRAY);

        // Define columns for table and initialize DefaultTableModel
        String[] columns = {"FirstName", "Last Name", "Email", "Phone", "Select"};
        list = new DefaultTableModel(columns, 0) {
            @Override
            public Class<?> getColumnClass(int column) {
                return (column == 4) ? Boolean.class : String.class;
            }
        };

        // Pass table model to Operations class
        Operations.setTableModel(list);
        //initialize table
        JTable table = new JTable(list);
        JScrollPane scroller = new JScrollPane(table);
        scroller.setBounds(10, 10, 460, 400);

        // Set up text fields and labels
        JLabel nameFirstInput = new JLabel("Enter First:");
        nameFirstTextField = new JTextField();
        nameFirstTextField.setBounds(90, 415, 150, 18);
        nameFirstInput.setBounds(10, 415, 100, 18);

        JLabel nameLastInput = new JLabel("Enter Last:");
        nameLastTextField = new JTextField();
        nameLastTextField.setBounds(90, 440, 150, 18);
        nameLastInput.setBounds(10, 440, 100, 18);

        JLabel numberInput = new JLabel("Enter Phone:");
        numberTextField = new JTextField();
        numberTextField.setBounds(90, 465, 150, 18);
        numberInput.setBounds(10, 465, 100, 18);

        JLabel emailInput = new JLabel("Enter Email:");
        emailTextField = new JTextField();
        emailTextField.setBounds(90, 490, 150, 18);
        emailInput.setBounds(10, 490, 100, 18);

        // Create Add button with debugging and error handling
        JButton addButton = new JButton("Add");
        addButton.setBounds(350, 415, 90, 18);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String firstName = nameFirstTextField.getText();
                    String lastName = nameLastTextField.getText();
                    String email = emailTextField.getText();
                    String phone = numberTextField.getText();

                    // Check if fields are empty and give feedback if necessary
                    int num = phone.length();
                    if (num > 10) {
                    	JOptionPane.showMessageDialog(frame, "Phone number must be ten digits.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if	
                    (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // Add new contact through Operations
                        Operations.addNewContact(firstName, lastName, email, phone);

                        // Clear fields after adding
                        nameFirstTextField.setText("");
                        nameLastTextField.setText("");
                        emailTextField.setText("");
                        numberTextField.setText("");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "An error occurred while adding the contact.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Create Clear button
        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(350, 435, 90, 18);
        clearButton.addActionListener(e -> Operations.clearContact());
        
        

        // Create Edit button (functionality to be implemented)
        JButton editButton = new JButton("Edit");
        editButton.setBounds(350, 455, 90, 18);
        editButton.addActionListener(e -> Operations.editContact());
        
        JButton csvButton = new JButton("Export");
        csvButton.setBounds(350, 475, 90, 32);
        csvButton.addActionListener(e -> FileManager.addToCSV(table));

        // Add components to frame
        frame.add(scroller);
        frame.add(addButton);
        frame.add(clearButton);
        frame.add(editButton);
        frame.add(csvButton);

        frame.add(nameFirstTextField);
        frame.add(nameFirstInput);

        frame.add(nameLastTextField);
        frame.add(nameLastInput);

        frame.add(numberTextField);
        frame.add(numberInput);

        frame.add(emailTextField);
        frame.add(emailInput);

        frame.setVisible(true);
    }
}
