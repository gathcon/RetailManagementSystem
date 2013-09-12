package retailManagementSystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CustomerListPanel extends JPanel implements ActionListener, ListSelectionListener{
	
	private Database database;
		
	private String[] customerNameList;
		
	private JList<String> customerList;
	private ListModel<String> customerListModel;
	private DefaultListModel<String> updatedCustomerListModel;
	
	private JScrollPane listScroller;
	private JSplitPane splitPane;
	
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
	private JPanel infoPanel;
	
	public CustomerListPanel() {
		
	}
	
	public void buildPanel(JPanel mainPanel, final Database database) {
		
		this.database = database;
		mainPanel.setLayout(new BorderLayout());
		
		customerNameList = database.getCustomerList();	//array of type String[]
		
		customerList = new JList<String>(customerNameList);
		customerListModel = customerList.getModel();
		customerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		customerList.setLayoutOrientation(JList.VERTICAL);
		customerList.setVisibleRowCount(100);
		customerList.addListSelectionListener(this);
		

		listScroller = new JScrollPane(customerList);
		Dimension minimumSize = new Dimension(100, 50);
        listScroller.setMinimumSize(minimumSize);
		
		IDLabel = new JLabel("ID: ", SwingConstants.RIGHT);
		nameLabel = new JLabel("Name: ", SwingConstants.RIGHT);
		addressLabel= new JLabel("Address: ", SwingConstants.RIGHT);
		phoneNoLabel= new JLabel("Phone Number: ", SwingConstants.RIGHT);
		emailLabel = new JLabel("Email: ", SwingConstants.RIGHT);
		
		IDField = new JTextField("", 26);
		nameField = new JTextField("", 26);
		addressField = new JTextField("", 26);
		phoneNoField = new JTextField("", 26);
		emailField = new JTextField("", 26);

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
		customerListLabel.setPreferredSize(new Dimension(150, 35)); 
		
		infoPanel = new JPanel();
		buttonPanel = new JPanel();
		
		infoPanel.setLayout(new GridBagLayout());
		buttonPanel.setLayout(new GridBagLayout());
		
		JPanel basePanel = new JPanel();
		basePanel.setLayout(new BorderLayout());
	
		createConstraint(infoPanel, IDLabel, 		0, 1, 1, 1, 0, 0, 20, 2, 2, 2, 1, 0, GridBagConstraints.FIRST_LINE_END, GridBagConstraints.VERTICAL);
		createConstraint(infoPanel, nameLabel, 		0, 2, 1, 1, 0, 0, 2, 2, 2, 2, 1, 0, GridBagConstraints.FIRST_LINE_END, GridBagConstraints.VERTICAL);
		createConstraint(infoPanel, addressLabel, 	0, 3, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_END, GridBagConstraints.VERTICAL);
		createConstraint(infoPanel, phoneNoLabel, 	0, 4, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_END, GridBagConstraints.VERTICAL);
		createConstraint(infoPanel, emailLabel, 	0, 5, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_END, GridBagConstraints.VERTICAL);
	
		createConstraint(infoPanel, IDField, 		1, 1, 2, 1, 0, 5, 20, 2, 0, 2, 1, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.VERTICAL);
		createConstraint(infoPanel, nameField, 		1, 2, 2, 1, 0, 5, 2, 2, 0, 2, 1, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.VERTICAL);
		createConstraint(infoPanel, addressField, 	1, 3, 2, 1, 0, 5, 2, 2, 0, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.VERTICAL);
		createConstraint(infoPanel, phoneNoField, 	1, 4, 2, 1, 0, 5, 2, 2, 0, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.VERTICAL);
		createConstraint(infoPanel, emailField, 	1, 5, 2, 1, 0, 5, 2, 2, 0, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.VERTICAL);
		
		createConstraint(buttonPanel, customerAddButton, 		1, 0, 1, 1, 50, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(buttonPanel, customerDeleteButton, 	2, 0, 1, 1, 50, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(buttonPanel, customerCancelButton, 	3, 0, 1, 1, 50, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(buttonPanel, customerEditSaveButton,	4, 0, 1, 1, 50, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);

		createConstraint(infoPanel, buttonPanel, 	0, 6, 3, 1, 0, 0, 20, 100, 0, 0, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL);
		
		basePanel.add(infoPanel, BorderLayout.PAGE_START);
		
	    splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScroller, basePanel);
        splitPane.setDividerLocation(200);
        splitPane.setOneTouchExpandable(true);
        splitPane.setContinuousLayout(true);
		        
	    mainPanel.add(splitPane, BorderLayout.CENTER);
	    mainPanel.add(customerListLabel, BorderLayout.PAGE_START);
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
		updatedCustomerListModel = new DefaultListModel<String>();
		for(Customer customer: database.getCustomers()) {
			
			updatedCustomerListModel.addElement(customer.getCustomerName());
			
		}

		// update JLists
		customerList.setModel(updatedCustomerListModel);
		
		// update String[] arrays
		customerNameList = database.getCustomerList();		
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
				
		if(e.getActionCommand().equals("Add")) {
			
			//remove listSelectionListener
			customerList.removeListSelectionListener(this);
				    	
			//change text fields to editable
			nameField.setEditable(true);
			//IDField.setEditable(true);	
			addressField.setEditable(true);
	    	phoneNoField.setEditable(true);
	    	emailField.setEditable(true);
			
	    	// reset textFields
	    	resetTextFields();
	    	
	    	//generate ID
	    	IDField.setEditable(false);	
	    	IDField.setText(generateCustomerUniqueId());
	    	
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
        	        					        			
        	if(name.equals("") || id.equals("") || address.equals("") || phoneNumber.equals("") || email.equals("")){
        		
        		JOptionPane.showMessageDialog(null, "Please fill all fields", "Input Warning", JOptionPane.WARNING_MESSAGE);
        	
        	}
        	
        	else {
        		
        		if(JOptionPane.showConfirmDialog(null, "Are you sure you want to save these changes?",
    					"WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
        			
        			if(database.checkCustomerID(id) == true) {
            			
            			// overwrite customer details
            			Customer customer = database.getCustomerByID(id);
            			customer.setCustomerName(name);
            			customer.setCustomerAddress(address);
            			customer.setCustomerPhoneNumber(phoneNumber);
            			customer.setCustomerEmail(email);	        					
            			
            		}
            				
            		else {

    		        	// add new customer to database
    		        	database.addCustomer(id, name, email, phoneNumber, address);
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
	
    public static String generateCustomerUniqueId() {      
        String uuidChars = "" + UUID.randomUUID().toString();
        String uid = uuidChars.replaceAll("-", "");    
        int myuid = uid.hashCode();
        
        uid = Integer.toString(myuid);
        uid = uuidChars.replaceAll("-", "");
        
        char[] newUUID = uid.toCharArray();
        String customerID = "";
        char  temp;
        
        for(int i = 0; i<8;i++){       	
        	temp = newUUID[i];    	
        	if(Character.getType(newUUID[i]) == Character.LOWERCASE_LETTER){     		
        		temp = Character.toUpperCase(newUUID[i]);
        	} 
        	 customerID += temp;
        }       
        
        return customerID;
    }

}
