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

public class SupplierListPanel extends JPanel implements ActionListener, ListSelectionListener{

	private Database database;
		
	private String[] suppliers;
		
	private JList<String> supplierList;
	private ListModel<String> supplierListModel;
	private DefaultListModel<String> updatedSupplierListModel;
	
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
	
	private JSplitPane splitPane;

	private JPanel buttonPanel;
	private JPanel infoPanel;
	
	public SupplierListPanel() {

	}
	
	public void buildPanel(JPanel mainPanel, final Database database) {
		
		this.database = database;
		mainPanel.setLayout(new BorderLayout());
		
		suppliers = database.getSupplierList();	//array of type String[]
		
		supplierList = new JList<String>(suppliers);
		supplierListModel = supplierList.getModel();
		supplierList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		supplierList.setLayoutOrientation(JList.VERTICAL);
		supplierList.setVisibleRowCount(100);
		supplierList.addListSelectionListener(this);
		
		listScroller = new JScrollPane(supplierList);
		Dimension minimumSize = new Dimension(100, 50);
        listScroller.setMinimumSize(minimumSize);
		
		IDLabel = new JLabel("ID: ", SwingConstants.RIGHT);
		nameLabel = new JLabel("Name: ", SwingConstants.RIGHT);
		addressLabel= new JLabel("Address: ", SwingConstants.RIGHT);
		phoneNoLabel= new JLabel("Phone Number: ", SwingConstants.RIGHT);
		emailLabel = new JLabel("Email: ", SwingConstants.RIGHT);
		
		nameField = new JTextField("",26);
		IDField = new JTextField("",26);
		addressField = new JTextField("",26);
		phoneNoField = new JTextField("",26);
		emailField = new JTextField("",26);

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
		supplierListLabel.setPreferredSize(new Dimension(150, 35)); 

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
		
		createConstraint(buttonPanel, supplierAddButton, 		1, 0, 1, 1, 50, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(buttonPanel, supplierDeleteButton, 	2, 0, 1, 1, 50, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(buttonPanel, supplierCancelButton, 	3, 0, 1, 1, 50, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(buttonPanel, supplierEditSaveButton,	4, 0, 1, 1, 50, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);

		createConstraint(infoPanel, buttonPanel, 	0, 6, 3, 1, 0, 0, 20, 100, 0, 0, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL);
		
		basePanel.add(infoPanel, BorderLayout.PAGE_START);
		
	    splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScroller, basePanel);
        splitPane.setDividerLocation(200);
        splitPane.setOneTouchExpandable(true);
        splitPane.setContinuousLayout(true);
		
	    mainPanel.add(splitPane, BorderLayout.CENTER);
	    mainPanel.add(supplierListLabel, BorderLayout.PAGE_START);
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
		updatedSupplierListModel = new DefaultListModel<String>();
		
		for(Supplier supplier: database.getSuppliers()) {
			
			updatedSupplierListModel.addElement(supplier.getSupplierName());
			
		}

		// update JLists
		supplierList.setModel(updatedSupplierListModel);
		
		// update String[] arrays
		suppliers = database.getSupplierList();
				
	}

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
			supplierList.removeListSelectionListener(this);
				    	
			//change text fields to editable
			nameField.setEditable(true);
	    	IDField.setEditable(true);
	    	addressField.setEditable(true);
	    	phoneNoField.setEditable(true);
	    	emailField.setEditable(true);
	    			
			// reset textFields
	    	resetTextFields();
	    	
	    	//generate ID
	    	IDField.setEditable(false);	
	    	IDField.setText(generateCustomerUniqueId());
	    	
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
			if(JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this supplier?",
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
			
			//add listSelectionListener
			supplierList.addListSelectionListener(this);
		}
		
		else if(e.getActionCommand().equals("Edit")) {
			
			//remove listSelectionListener
			supplierList.removeListSelectionListener(this);
			
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
            			Supplier supplier = database.getSupplierByID(id);
            			supplier.setSupplierName(name);
            			supplier.setSupplierAddress(address);
            			supplier.setSupplierPhoneNumber(phoneNumber);
            			supplier.setSupplierEmail(email);	        					
            			
            		}
            				
            		else {
    		        			
    		        	// add new supplier to database
    		        	database.addSupplier(id, name, email, phoneNumber, address);
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
        	
        	//add listSelectionListener
			supplierList.addListSelectionListener(this);
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
