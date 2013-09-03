package retailManagementSystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CustomerListPanel extends JPanel implements ActionListener, ListSelectionListener{
	
	private Database database;
		
	private String[] customerNameList;
		
	private JList customerList;
	private ListModel customerListModel;
	private DefaultListModel updatedCustomerListModel;
	
	private JScrollPane listScroller;
	
	private JLabel customerListLabel;
	
	private JLabel IDLabel;
	private JLabel nameLabel;
	private JLabel addressLabel;
	private JLabel phoneNoLabel;
	private JLabel emailLabel;
	
	private JTextField nameField;
	private JTextField IDField;
	private JTextField addressField;
	private JTextField phoneNoField;
	private JTextField emailField;
	
	private JButton customerAddButton;
	private JButton customerDeleteButton;
	private JButton customerEditSaveButton;
	private JButton customerCancelButton;
	
	private JPanel buttonPanel;
	
	public CustomerListPanel() {
		System.out.println("CustomerListPanel created");

	}
	
	public void buildPanel(JPanel panel, final Database database) {
		
		this.database = database;
		customerNameList = database.getCustomerList();	//array of type String[]
		
		customerList = new JList(customerNameList);
		customerListModel = customerList.getModel();
		customerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		customerList.setLayoutOrientation(JList.VERTICAL);
		customerList.setVisibleRowCount(100);
		customerList.addListSelectionListener(this);
		
		listScroller = new JScrollPane(customerList);
		
		IDLabel = new JLabel("Id: ", SwingConstants.RIGHT);
		nameLabel = new JLabel("Name: ", SwingConstants.RIGHT);
		addressLabel= new JLabel("Address: ", SwingConstants.RIGHT);
		phoneNoLabel= new JLabel("Phone Number: ", SwingConstants.RIGHT);
		emailLabel = new JLabel("Email: ", SwingConstants.RIGHT);
		
		IDField = new JTextField();
		nameField = new JTextField();
		addressField = new JTextField();
		phoneNoField = new JTextField();
		emailField = new JTextField();

		IDField.setEditable(false);
		IDField.setBackground(new Color(255,255,170));
		nameField.setEditable(false);
		nameField.setBackground(new Color(255,255,170));
		addressField.setEditable(false);
		addressField.setBackground(new Color(255,255,170));
		phoneNoField.setEditable(false);
		phoneNoField.setBackground(new Color(255,255,170));
		emailField.setEditable(false);
		emailField.setBackground(new Color(255,255,170));
		
		//Buttons
		customerAddButton = new JButton("Add");
		customerDeleteButton = new JButton("Delete");
		customerEditSaveButton = new JButton("Edit");
		customerCancelButton = new JButton("Cancel");
		
		customerEditSaveButton.setVisible(false);
		customerCancelButton.setVisible(false);
		customerDeleteButton.setVisible(false);

				
		// addButton listener
		customerAddButton.addActionListener(this);
				
		// deleteButton listener
		customerDeleteButton.addActionListener(this);
				
		// editSaveButton listener
		customerEditSaveButton.addActionListener(this);
		
		// cancelButton Listener
		customerCancelButton.addActionListener(this);		
		
		customerListLabel = new JLabel("Customer Control", SwingConstants.CENTER);
		customerListLabel.setOpaque(true);
		customerListLabel.setBackground(new Color(0,51,102));
		customerListLabel.setForeground(Color.WHITE);
		customerListLabel.setFont(new Font("Helvetica", Font.BOLD, 20));

		buttonPanel = new JPanel();
		
		panel.setLayout(new GridBagLayout());
		buttonPanel.setLayout(new GridBagLayout());

		createConstraint(panel, customerListLabel,0, 0, 3, 1, 0, 10, 0, 0, 0, 0, 1, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(panel, listScroller, 	0, 1, 1, 7, 0, 0, 2, 2, 2, 2, 0.3, 1, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		
		createConstraint(panel, IDLabel, 			1, 1, 1, 1, 0, 0, 20, 2, 2, 2, 0.4, 0, GridBagConstraints.FIRST_LINE_END, GridBagConstraints.VERTICAL);
		createConstraint(panel, nameLabel, 		1, 2, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_END, GridBagConstraints.VERTICAL);
		createConstraint(panel, addressLabel, 	1, 3, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_END, GridBagConstraints.VERTICAL);
		createConstraint(panel, phoneNoLabel, 	1, 4, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_END, GridBagConstraints.VERTICAL);
		createConstraint(panel, emailLabel, 		1, 5, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_END, GridBagConstraints.VERTICAL);

		createConstraint(panel, IDField, 			2, 1, 1, 1, 200, 0, 20, 2, 2, 2, 0.4, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.VERTICAL);
		createConstraint(panel, nameField, 		2, 2, 1, 1, 200, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.VERTICAL);
		createConstraint(panel, addressField, 	2, 3, 1, 1, 200, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.VERTICAL);
		createConstraint(panel, phoneNoField, 	2, 4, 1, 1, 200, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.VERTICAL);
		createConstraint(panel, emailField, 		2, 5, 1, 1, 200, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.VERTICAL);

		createConstraint(buttonPanel, customerAddButton, 		0, 0, 1, 1, 50, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(buttonPanel, customerDeleteButton, 	1, 0, 1, 1, 50, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(buttonPanel, customerCancelButton, 	1, 0, 1, 1, 50, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(buttonPanel, customerEditSaveButton,	2, 0, 1, 1, 50, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);

		createConstraint(panel, buttonPanel, 	1, 6, 2, 1, 0, 0, 20, 0, 0, 0, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE);		
	}
	
	//Create GridBagLayout constraints and add component to a panel using these constraints
	private void createConstraint(JPanel panel, JComponent component, int gridx, int gridy, int width, int height, int ipadx, int ipady,
			int top, int left, int bottom, int right, double weightx, double weighty, int anchor, int fill){
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = gridx;
			constraints.gridy = gridy;
			constraints.gridwidth = width;
			constraints.gridheight = height;
			constraints.ipadx = ipadx;
			constraints.ipady = ipady;
			constraints.weightx = weightx;
			constraints.weighty = weighty;
			constraints.insets = new Insets(top, left, bottom, right);
			constraints.anchor = anchor;
			constraints.fill = fill;
			panel.add(component, constraints);
	}
	
	// Update the list of customers from the database
	public void updateCustomerLists() {
		
		//update the list of names etc
		updatedCustomerListModel = new DefaultListModel();
		for(Customer customer: database.getCustomers()) {
			
			updatedCustomerListModel.addElement(customer.getCustomerName());
			
		}

		// update JLists
		customerList.setModel(updatedCustomerListModel);
		
		// update String[] arrays
		customerNameList = database.getCustomerList();
		System.out.println("Customer list updated");
		
	}
	
	// Reset text fields to empty
	public void resetTextFields() {
	
		nameField.setText("");
		IDField.setText("");
		addressField.setText("");
		phoneNoField.setText("");
		emailField.setText("");
	
	}
	
	// Button handler
	public void actionPerformed(ActionEvent e) {
		
		System.out.println(e.paramString());
		
		if(e.getActionCommand().equals("Add")) {
			
			//remove listSelectionListener
			customerList.removeListSelectionListener(this);
				    	
			//change text fields to editable
			nameField.setEditable(true);
	    	IDField.setEditable(true);
	    	addressField.setEditable(true);
	    	phoneNoField.setEditable(true);
	    	emailField.setEditable(true);
			
	    	// reset textFields
	    	resetTextFields();
	    	
	    	//change editSaveButton label to "Save"
	    	customerEditSaveButton.setText("Save");
	    	
        	//set visibility of buttons
	    	customerAddButton.setVisible(false);
	    	customerDeleteButton.setVisible(false);
	    	customerCancelButton.setVisible(true);
	    	customerEditSaveButton.setVisible(true);	    	
		}
		
		else if(e.getActionCommand().equals("Delete")) {
	    	
	    	// pop up dialog to confirm delete			
			if(JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this customer?",
					"WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				
				//remove customer from database
		    	String id = IDField.getText();
		    	Customer customer = database.getCustomerByID(id);
		    	database.deleteCustomer(customer);
		    	
		    	// update lists
				updateCustomerLists();
						
				// reset textFields
	        	resetTextFields();
	        	
	        	//set visibility of buttons
	        	customerAddButton.setVisible(true);
		    	customerDeleteButton.setVisible(true);
		    	customerCancelButton.setVisible(false);
		    	customerEditSaveButton.setVisible(false);
		    	
		    	//make text fields non-editable
		    	nameField.setEditable(false);
		    	IDField.setEditable(false);
		    	addressField.setEditable(false);
		    	phoneNoField.setEditable(false);
		    	emailField.setEditable(false);
		    	
		    	System.out.println("Customer " + id + " removed from database");
		    	
			}
		}
		
		else if(e.getActionCommand().equals("Cancel")) {
			
			// reset textFields
			resetTextFields();
			
			//change save button to edit button
			customerEditSaveButton.setText("Edit");
			
        	//set visibility of buttons
			customerAddButton.setVisible(true);
			customerDeleteButton.setVisible(false);
			customerCancelButton.setVisible(false);
			customerEditSaveButton.setVisible(false);
	    	
			//make text fields non-editable
			nameField.setEditable(false);
			IDField.setEditable(false);
			addressField.setEditable(false);
			phoneNoField.setEditable(false);
			emailField.setEditable(false);
			
			//add listSelectionListener
			customerList.addListSelectionListener(this);
		}
		
		else if(e.getActionCommand().equals("Edit")) {
			
			//remove listSelectionListener
			customerList.removeListSelectionListener(this);
			
	    	//make text fields editable
	    	nameField.setEditable(true);
	    	IDField.setEditable(false);
	    	addressField.setEditable(true);
	    	phoneNoField.setEditable(true);
	    	emailField.setEditable(true);
	    	
	    	//change editSaveButton label to "Save"
	    	customerEditSaveButton.setText("Save");
	    	
        	//set visibility of buttons
	    	customerAddButton.setVisible(false);
	    	customerDeleteButton.setVisible(false);
	    	customerCancelButton.setVisible(true);
			
		}
		
		else if(e.getActionCommand().equals("Save")) {
			
			//save contents of text fields to relevant customer
        	String name = nameField.getText();
        	String id = IDField.getText();
        	String address = addressField.getText();
        	String phoneNumber = phoneNoField.getText();
        	String email = emailField.getText();
        	
        	System.out.println(id);
        					        			
        	if(name.equals("") || id.equals("") || address.equals("") || phoneNumber.equals("") || email.equals("")){
        		
        		JOptionPane.showMessageDialog(null, "Please fill all fields", "Input Warning", JOptionPane.WARNING_MESSAGE);
        	
        	}
        	
        	else {
        		
        		if(JOptionPane.showConfirmDialog(null, "Are you sure you want to save these changes?",
    					"WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
        			
        			if(database.checkCustomerID(id) == true) {
            			
            			// overwrite customer details
            			System.out.println("Customer " + id + " already exists. Updating details.");

            			Customer customer = database.getCustomerByID(id);
            			customer.setCustomerName(name);
            			customer.setCustomerAddress(address);
            			customer.setCustomerPhoneNumber(phoneNumber);
            			customer.setCustomerEmail(email);	        					
            			
            		}
            				
            		else {

    		        	// add new customer to database
    		        	database.addCustomer(id, name, email, phoneNumber, address);
    		        		
    			    	System.out.println("New customer created.");
            		}
            		
            		// update lists
        			updateCustomerLists();
        					
        			// reset textFields
    	        	resetTextFields();
    	        	
    		    	//change editSaveButton label to "Edit"
    	        	customerEditSaveButton.setText("Edit");
    	        	
    	        	//set visibility of buttons
    		    	customerAddButton.setVisible(true);
    		    	customerDeleteButton.setVisible(true);
    		    	customerCancelButton.setVisible(false);
    		    	customerEditSaveButton.setVisible(false);
    		    	
    		    	//make text fields non-editable
    		    	nameField.setEditable(false);
    		    	IDField.setEditable(false);
    		    	addressField.setEditable(false);
    		    	phoneNoField.setEditable(false);
    		    	emailField.setEditable(false);
        		}
        	}
        	
        	//add listSelectionListener
			customerList.addListSelectionListener(this);
		}
   }
	
	// List Handler
	public void valueChanged(ListSelectionEvent e) {
		
		// JList customerList, String[] customers
	        if (e.getValueIsAdjusting() == false) {

	        	if (e.getSource() == customerList) {
	        		
	        		customerEditSaveButton.setVisible(true);
	        		customerDeleteButton.setVisible(true);
	            	
	        		ArrayList<Customer> customers = database.getCustomers();
	        		int noOfCustomers = customers.size();
	                
	            	for(int i = 0; i < noOfCustomers; i++) {		//loop through customers
	            		
	                    if (customerList.getSelectedIndex() == i) {
	                    
	                    	System.out.println("customer selected: " + customerNameList[i]);
	                    	
	                    	nameField.setText(customers.get(i).getCustomerName());
	                    	IDField.setText(customers.get(i).getCustomerID());
	                    	addressField.setText(customers.get(i).getCustomerAddress());
	                    	phoneNoField.setText(customers.get(i).getCustomerPhoneNumber());
	                    	emailField.setText(customers.get(i).getCustomerEmail());
	                    	
	                    }

	                }
	            }
	        }
	}

}
