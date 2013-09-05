package retailManagementSystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

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

public class SupplierListPanel extends JPanel implements ActionListener, ListSelectionListener{

	private Database database;
		
	private String[] suppliers;
		
	private JList supplierList;
	private ListModel supplierListModel;
	private DefaultListModel updatedSupplierListModel;
	
	private JScrollPane listScroller;
	
	private JLabel supplierListLabel;

	private JLabel nameLabel;
	private JLabel IDLabel;
	private JLabel addressLabel;
	private JLabel phoneNoLabel;
	private JLabel emailLabel; 
		
	private JTextField nameField;
	private JTextField IDField;
	private JTextField addressField;
	private JTextField phoneNoField;
	private JTextField emailField;
	
	private JButton supplierAddButton;
	private JButton supplierDeleteButton;
	private JButton supplierEditSaveButton;
	private JButton supplierCancelButton;

	private JPanel buttonPanel;
	
	public SupplierListPanel() {
		System.out.println("SupplierListPanel created");

	}
	
	public void buildPanel(JPanel panel, final Database database) {
		
		this.database = database;
		suppliers = database.getSupplierList();	//array of type String[]
		
		supplierList = new JList(suppliers);
		supplierListModel = supplierList.getModel();
		supplierList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		supplierList.setLayoutOrientation(JList.VERTICAL);
		supplierList.setVisibleRowCount(100);
		supplierList.addListSelectionListener(this);
		
		listScroller = new JScrollPane(supplierList);
		
		IDLabel = new JLabel("Id: ", SwingConstants.RIGHT);
		nameLabel = new JLabel("Name: ", SwingConstants.RIGHT);
		addressLabel= new JLabel("Address: ", SwingConstants.RIGHT);
		phoneNoLabel= new JLabel("Phone Number: ", SwingConstants.RIGHT);
		emailLabel = new JLabel("Email: ", SwingConstants.RIGHT);
		
		nameField = new JTextField();
		IDField = new JTextField();
		addressField = new JTextField();
		phoneNoField = new JTextField();
		emailField = new JTextField();

		nameField.setEditable(false);
		nameField.setBackground(new Color(255,255,170));
		IDField.setEditable(false);
		IDField.setBackground(new Color(255,255,170));
		addressField.setEditable(false);
		addressField.setBackground(new Color(255,255,170));
		phoneNoField.setEditable(false);
		phoneNoField.setBackground(new Color(255,255,170));
		emailField.setEditable(false);
		emailField.setBackground(new Color(255,255,170));
		
		//Buttons
		supplierAddButton = new JButton("Add");
		supplierDeleteButton = new JButton("Delete");
		supplierEditSaveButton = new JButton("Edit");
		supplierCancelButton = new JButton("Cancel");
		
		supplierEditSaveButton.setVisible(false);
		supplierCancelButton.setVisible(false);
		supplierDeleteButton.setVisible(false);
				
		// addButton listener
		supplierAddButton.addActionListener(this);
				
		// deleteButton listener
		supplierDeleteButton.addActionListener(this);
				
		// editSaveButton listener
		supplierEditSaveButton.addActionListener(this);
		
		// cancelButton Listener
		supplierCancelButton.addActionListener(this);
		
		
		supplierListLabel = new JLabel("Supplier Control", SwingConstants.CENTER);
		supplierListLabel.setOpaque(true);
		supplierListLabel.setBackground(new Color(0,51,102));
		supplierListLabel.setForeground(Color.WHITE);
		supplierListLabel.setFont(new Font("Helvetica", Font.BOLD, 20));

		buttonPanel = new JPanel();
		
		panel.setLayout(new GridBagLayout());
		buttonPanel.setLayout(new GridBagLayout());

		createConstraint(panel, supplierListLabel,	0, 0, 3, 1, 100, 10, 0, 0, 0, 0, 1, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(panel, listScroller, 		0, 1, 1, 7, 0, 0, 2, 2, 2, 2, 0.3, 1, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		
		createConstraint(panel, IDLabel, 		1, 1, 1, 1, 0, 0, 20, 2, 2, 2, 0.4, 0, GridBagConstraints.FIRST_LINE_END, GridBagConstraints.VERTICAL);
		createConstraint(panel, nameLabel, 		1, 2, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_END, GridBagConstraints.VERTICAL);
		createConstraint(panel, addressLabel, 	1, 3, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_END, GridBagConstraints.VERTICAL);
		createConstraint(panel, phoneNoLabel, 	1, 4, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_END, GridBagConstraints.VERTICAL);
		createConstraint(panel, emailLabel, 	1, 5, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_END, GridBagConstraints.VERTICAL);

		createConstraint(panel, IDField, 		2, 1, 1, 1, 200, 0, 20, 2, 2, 2, 0.4, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.VERTICAL);
		createConstraint(panel, nameField, 		2, 2, 1, 1, 200, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.VERTICAL);
		createConstraint(panel, addressField, 	2, 3, 1, 1, 200, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.VERTICAL);
		createConstraint(panel, phoneNoField, 	2, 4, 1, 1, 200, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.VERTICAL);
		createConstraint(panel, emailField, 	2, 5, 1, 1, 200, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.VERTICAL);

		createConstraint(buttonPanel, supplierAddButton, 		0, 0, 1, 1, 50, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(buttonPanel, supplierDeleteButton, 	1, 0, 1, 1, 50, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(buttonPanel, supplierCancelButton, 	1, 0, 1, 1, 50, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(buttonPanel, supplierEditSaveButton,	2, 0, 1, 1, 50, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);

		createConstraint(panel, buttonPanel, 	1, 6, 2, 1, 0, 0, 20, 0, 0, 0, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE);		
	}
	
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
	
	public void updateSupplierLists() {
		
		//update the list of names etc
		updatedSupplierListModel = new DefaultListModel();
		
		for(Supplier supplier: database.getSuppliers()) {
			
			updatedSupplierListModel.addElement(supplier.getSupplierName());
			
		}

		// update JLists
		supplierList.setModel(updatedSupplierListModel);
		
		// update String[] arrays
		suppliers = database.getSupplierList();
		
		System.out.println("Supplier list updated");
		
	}

	public void resetTextFields() {
	
		nameField.setText("");
		IDField.setText("");
		addressField.setText("");
		phoneNoField.setText("");
		emailField.setText("");
	
	}
	
	public static String generateUniqueId() {      
        String uuidChars = "" + UUID.randomUUID().toString();
        String uid = uuidChars.replaceAll("-", "");    
        int myuid = uid.hashCode();
        
        uid = Integer.toString(myuid);
        uid = uuidChars.replaceAll("-", "");
        
        char[] newUUID = uid.toCharArray();
        String ID = "";
        char  temp;
        
        for(int i = 0; i < 8; i++){       	
        	temp = newUUID[i];    	
        	if(Character.getType(newUUID[i]) == Character.LOWERCASE_LETTER){     		
        		temp = Character.toUpperCase(newUUID[i]);
        	} 
        	 ID += temp;
        }
        System.out.println(ID);
        return ID;
    }
	
	// Button handler
	public void actionPerformed(ActionEvent e) {
		
		System.out.println(e.paramString());
		
		if(e.getActionCommand().equals("Add")) {
				    	
			//change text fields to editable
			nameField.setEditable(true);
	    	IDField.setEditable(true);
	    	addressField.setEditable(true);
	    	phoneNoField.setEditable(true);
	    	emailField.setEditable(true);
	    			
			// reset textFields
	    	resetTextFields();
	    	
	    	IDField.setText(generateUniqueId());
	    	IDField.setEditable(false);
	    	
	    	//change editSaveButton label to "Save"
	    	supplierEditSaveButton.setText("Save");
	    	
        	//set visibility of buttons
	    	supplierAddButton.setVisible(false);
	    	supplierDeleteButton.setVisible(false);
	    	supplierCancelButton.setVisible(true);
	    	supplierEditSaveButton.setVisible(true);
	    	
		}
		
		else if(e.getActionCommand().equals("Delete")) {
	    	
	    	// pop up dialog to confirm delete
			if(JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this customer?",
					"WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				
				//remove supplier from database
		    	String id = IDField.getText();
		    	Supplier supplier = database.getSupplierByID(id);
		    	database.deleteSupplier(supplier);
		    	
		    	// update lists
				updateSupplierLists();
						
				// reset textFields
	        	resetTextFields();
	        	
	        	//set visibility of buttons
	        	supplierAddButton.setVisible(true);
		    	supplierDeleteButton.setVisible(true);
		    	supplierCancelButton.setVisible(false);
		    	supplierEditSaveButton.setVisible(false);
		    	
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
			supplierEditSaveButton.setText("Edit");
			
        	//set visibility of buttons
			supplierAddButton.setVisible(true);
			supplierDeleteButton.setVisible(false);
			supplierCancelButton.setVisible(false);
			supplierEditSaveButton.setVisible(false);
	    	
			//make text fields non-editable
			nameField.setEditable(false);
			IDField.setEditable(false);
			addressField.setEditable(false);
			phoneNoField.setEditable(false);
			emailField.setEditable(false);
		}
		
		else if(e.getActionCommand().equals("Edit")) {
			
	    	//make text fields editable
	    	nameField.setEditable(true);
	    	IDField.setEditable(false);
	    	addressField.setEditable(true);
	    	phoneNoField.setEditable(true);
	    	emailField.setEditable(true);
	    	
	    	//change editSaveButton label to "Save"
	    	supplierEditSaveButton.setText("Save");
	    	
        	//set visibility of buttons
	    	supplierAddButton.setVisible(false);
	    	supplierDeleteButton.setVisible(false);
	    	supplierCancelButton.setVisible(true);
			
		}
		
		else if(e.getActionCommand().equals("Save")) {
			
			//save contents of text fields to relevant supplier
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
        			
        			if(database.checkSupplierID(id) == true) {
            			
            			// overwrite supplier details
            			System.out.println("supplier " + id + " already exists. Updating details.");

            			Supplier supplier = database.getSupplierByID(id);
            			supplier.setSupplierName(name);
            			supplier.setSupplierAddress(address);
            			supplier.setSupplierPhoneNumber(phoneNumber);
            			supplier.setSupplierEmail(email);	        					
            			
            		}
            				
            		else {
    		        			
    		        	// add new supplier to database
    		        	database.addSupplier(id, name, email, phoneNumber, address);
    		        		
    			    	System.out.println("New supplier created.");
            		}
            		
            		// update lists
        			updateSupplierLists();
        					
        			// reset textFields
    	        	resetTextFields();
    	        	
    		    	//change editSaveButton label to "Edit"
    	        	supplierEditSaveButton.setText("Edit");
    	        	
    	        	//set visibility of buttons
    		    	supplierAddButton.setVisible(true);
    		    	supplierDeleteButton.setVisible(true);
    		    	supplierCancelButton.setVisible(false);
    		    	supplierEditSaveButton.setVisible(false);
    		    	
    		    	//make text fields non-editable
    		    	nameField.setEditable(false);
    		    	IDField.setEditable(false);
    		    	addressField.setEditable(false);
    		    	phoneNoField.setEditable(false);
    		    	emailField.setEditable(false);
    		    	
        		}
        	}
		}
   }
	
	public void valueChanged(ListSelectionEvent e) {
		
		// JList supplierList, String[] suppliers
	        if (e.getValueIsAdjusting() == false) {

	        	if (e.getSource() == supplierList) {
	        		
	        		supplierEditSaveButton.setVisible(true);
	        		supplierDeleteButton.setVisible(true);
	            	
	        		int noOfsuppliers = database.getSuppliers().size();
	                
	            	for(int i = 0; i < noOfsuppliers; i++) {		//loop through suppliers
	            		
	                    if (supplierList.getSelectedIndex() == i) {
	                    
	                    	System.out.println("supplier selected: " + suppliers[i]);
	                    	
	                    	nameField.setText(database.getSuppliers().get(i).getSupplierName());
	                    	IDField.setText(database.getSuppliers().get(i).getSupplierID());
	                    	addressField.setText(database.getSuppliers().get(i).getSupplierAddress());
	                    	phoneNoField.setText(database.getSuppliers().get(i).getSupplierPhoneNumber());
	                    	emailField.setText(database.getSuppliers().get(i).getSupplierEmail());
	                    	
	                    }

	                }
	            }
	        }
	}
}
