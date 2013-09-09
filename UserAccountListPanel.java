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
import java.util.Arrays;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class UserAccountListPanel extends JPanel implements ActionListener, ListSelectionListener{

	private Database database;
		
	private String[] userAccounts;
		
	private JList<String> userAccountList;
	private ListModel<String> userAccountListModel;
	private DefaultListModel<String> updatedUserAccountListModel;
	
	private JScrollPane listScroller;
	
	private JLabel userAccountListLabel;
	
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel userNameLabel;
	private JLabel passwordLabel;
	private JLabel confirmPasswordLabel;
	private JLabel userLevelLabel;
		
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField userNameField;
	private JPasswordField passwordField1;
	private JPasswordField passwordField2;
	private JComboBox userLevelField;
	
	private JButton userAccountAddButton;
	private JButton userAccountDeleteButton;
	private JButton userAccountEditSaveButton;
	private JButton userAccountCancelButton;

	private JSplitPane splitPane;
	private JPanel buttonPanel;
	private JPanel mainPanel;
	private JPanel panel;
	
	public UserAccountListPanel() {
	}
	
	public void buildPanel(JPanel mainPanel, final Database database) {
		
		this.database = database;
		this.mainPanel = mainPanel;
		mainPanel.setLayout(new BorderLayout());
		panel = new JPanel();
		
		userAccounts = database.getUserAccountList();	//array of type String[]
		
		userAccountList = new JList(userAccounts);
		userAccountListModel = userAccountList.getModel();
		userAccountList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		userAccountList.setLayoutOrientation(JList.VERTICAL);
		userAccountList.setVisibleRowCount(100);
		userAccountList.addListSelectionListener(this);
		
		listScroller = new JScrollPane(userAccountList);
		Dimension minimumSize = new Dimension(100, 50);
        listScroller.setMinimumSize(minimumSize);
		
		firstNameLabel = new JLabel("First name:", SwingConstants.RIGHT);
		lastNameLabel = new JLabel("Last name:", SwingConstants.RIGHT);
		userNameLabel = new JLabel("User name:", SwingConstants.RIGHT);
		passwordLabel = new JLabel("Password:", SwingConstants.RIGHT);
		confirmPasswordLabel = new JLabel("Confirm Password:", SwingConstants.RIGHT);
		userLevelLabel = new JLabel("User level:", SwingConstants.RIGHT);
		
		firstNameField = new JTextField("",26);
		lastNameField = new JTextField("",26);
		userNameField = new JTextField("",26);
		passwordField1 = new JPasswordField("",26);
		passwordField2 = new JPasswordField("",26);
		userLevelField = new JComboBox();
		
		userLevelField.addItem("Administrator");
		userLevelField.addItem("Manager");
		userLevelField.addItem("Teller");
		userLevelField.addItem("Stock check");
		userLevelField.addItem("Please select");
		userLevelField.setSelectedItem("Please select");
		userLevelField.setPreferredSize(new Dimension(290,20));

		firstNameField.setEditable(false);
		firstNameField.setBackground(new Color(255,255,170));
		lastNameField.setEditable(false);
		lastNameField.setBackground(new Color(255,255,170));
		userNameField.setEditable(false);
		userNameField.setBackground(new Color(255,255,170));
		passwordField1.setEditable(false);
		passwordField1.setBackground(new Color(255,255,170));
		passwordField2.setEditable(true);
		passwordField2.setBackground(new Color(255,255,170));
		userLevelField.setEnabled(false);
		userLevelField.setBackground(new Color(255,255,170));
		
		//Buttons
		userAccountAddButton = new JButton("Add");
		userAccountDeleteButton = new JButton("Delete");
		userAccountEditSaveButton = new JButton("Edit");
		userAccountCancelButton = new JButton("Cancel");
		
		userAccountEditSaveButton.setVisible(false);
		userAccountCancelButton.setVisible(false);
		userAccountDeleteButton.setVisible(false);
		
		confirmPasswordLabel.setVisible(false);
		passwordField2.setVisible(false);
				
		// addButton listener
		userAccountAddButton.addActionListener(this);
				
		// deleteButton listener
		userAccountDeleteButton.addActionListener(this);
				
		// editSaveButton listener
		userAccountEditSaveButton.addActionListener(this);
		
		// cancelButton Listener
		userAccountCancelButton.addActionListener(this);
		
		userAccountListLabel = new JLabel("User Account Control", SwingConstants.CENTER);
		userAccountListLabel.setOpaque(true);
		userAccountListLabel.setBackground(new Color(0,51,102));
		userAccountListLabel.setForeground(Color.WHITE);
		userAccountListLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
		userAccountListLabel.setPreferredSize(new Dimension(150, 35));
		
		buttonPanel = new JPanel();
		
		panel.setLayout(new GridBagLayout());
		buttonPanel.setLayout(new GridBagLayout());
		
		JPanel basePanel = new JPanel();
		basePanel.setLayout(new BorderLayout());


		createConstraint(panel, firstNameLabel, 		0, 1, 1, 1, 0, 0, 20, 2, 2, 2, 1, 0, GridBagConstraints.FIRST_LINE_END, GridBagConstraints.VERTICAL);
		createConstraint(panel, lastNameLabel, 			0, 2, 1, 1, 0, 0, 2, 2, 2, 2, 1, 0, GridBagConstraints.FIRST_LINE_END, GridBagConstraints.VERTICAL);
		createConstraint(panel, userNameLabel, 			0, 3, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_END, GridBagConstraints.VERTICAL);
		createConstraint(panel, passwordLabel, 			0, 4, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_END, GridBagConstraints.VERTICAL);
		createConstraint(panel, confirmPasswordLabel, 	0, 5, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_END, GridBagConstraints.VERTICAL);
		createConstraint(panel, userLevelLabel, 		0, 6, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_END, GridBagConstraints.VERTICAL);
		
		
		createConstraint(panel, firstNameField, 	1, 1, 2, 1, 0, 5, 20, 2, 0, 2, 1, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.VERTICAL);
		createConstraint(panel, lastNameField, 		1, 2, 2, 1, 0, 5, 2, 2, 0, 2, 1, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.VERTICAL);
		createConstraint(panel, userNameField, 		1, 3, 2, 1, 0, 5, 2, 2, 0, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.VERTICAL);
		createConstraint(panel, passwordField1, 	1, 4, 2, 1, 0, 5, 2, 2, 0, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.VERTICAL);
		createConstraint(panel, passwordField2, 	1, 5, 2, 1, 0, 5, 2, 2, 0, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.VERTICAL);
		createConstraint(panel, userLevelField, 	1, 6, 2, 1, 0, 5, 2, 2, 0, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.VERTICAL);
		
		createConstraint(buttonPanel, userAccountAddButton, 		1, 0, 1, 1, 50, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(buttonPanel, userAccountDeleteButton, 		2, 0, 1, 1, 50, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(buttonPanel, userAccountCancelButton, 		3, 0, 1, 1, 50, 0, 2, 50, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(buttonPanel, userAccountEditSaveButton,	4, 0, 1, 1, 50, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);

		createConstraint(panel, buttonPanel, 	0, 7, 3, 1, 0, 0, 20, 70, 0, 0, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL);
				
		basePanel.add(panel, BorderLayout.PAGE_START);
		
	    splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScroller, basePanel);
        splitPane.setDividerLocation(200);
        splitPane.setOneTouchExpandable(true);
        splitPane.setContinuousLayout(true);
        
        mainPanel.add(splitPane, BorderLayout.CENTER);
	    mainPanel.add(userAccountListLabel, BorderLayout.PAGE_START);
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
	
	public void updateUserAccountLists() {
		
		//update the list of names etc
		updatedUserAccountListModel = new DefaultListModel();
		
		for(UserAccount userAccount: database.getUserAccounts()) {
			
			updatedUserAccountListModel.addElement(userAccount.getUserAccountUserName());
			
		}

		// update JLists
		userAccountList.setModel(updatedUserAccountListModel);
		
		// update String[] arrays
		userAccounts = database.getUserAccountList();
	}

	public void resetTextFields() {
	
		lastNameField.setText("");
		firstNameField.setText("");
		userNameField.setText("");
		passwordField1.setText("");
		passwordField2.setText("");
		//userLevelField.setText("");
	}
	
	// Button handler
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Add")) {
			//remove listSelectionListener
			userAccountList.removeListSelectionListener(this);
					    	
			//change text fields to editable
			firstNameField.setEditable(true);
		    lastNameField.setEditable(true);
		    userNameField.setEditable(true);
		    passwordField1.setEditable(true);
		    passwordField2.setEditable(true);
		    userLevelField.setEnabled(true);
				
		    // reset textFields
		    resetTextFields();
		    	
		    //change editSaveButton label to "Save"
		    userAccountEditSaveButton.setText("Save");
		    	
	        //set visibility of buttons
		    userAccountAddButton.setVisible(false);
		    userAccountDeleteButton.setVisible(false);
		    userAccountCancelButton.setVisible(true);
		    userAccountEditSaveButton.setVisible(true);
		    
		    //set visibility of second password field
		    confirmPasswordLabel.setVisible(true);
			passwordField2.setVisible(true);
		    	
		}
			
		else if(e.getActionCommand().equals("Delete")) {
			
			// pop up dialog to confirm delete			
			if(JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this User Account?",
					"WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				
				//remove userAccount from database
			    String userName = userNameField.getText();
			    UserAccount userAccount = database.getUserAccountByUserName(userName);
			    database.deleteUserAccount(userAccount);
			    	
			    // update lists
				updateUserAccountLists();
							
				// reset textFields
		        resetTextFields();
		        	
		        //set visibility of buttons
		        userAccountAddButton.setVisible(true);
			    userAccountDeleteButton.setVisible(true);
			    userAccountCancelButton.setVisible(false);
			    userAccountEditSaveButton.setVisible(false);
			    	
			    //make text fields non-editable
			    firstNameField.setEditable(false);
			    lastNameField.setEditable(false);
			    userNameField.setEditable(false);
			    passwordField1.setEditable(false);
			    passwordField2.setEditable(false);
			    userLevelField.setEnabled(false);
			    				    	
			}
		}
			
		else if(e.getActionCommand().equals("Cancel")) {
				
			// reset textFields
			resetTextFields();
				
			//change save button to edit button
			userAccountEditSaveButton.setText("Edit");
				
	        //set visibility of buttons
			userAccountAddButton.setVisible(true);
			userAccountDeleteButton.setVisible(false);
			userAccountCancelButton.setVisible(false);
			userAccountEditSaveButton.setVisible(false);
		    	
			//make text fields non-editable
			firstNameField.setEditable(false);
			lastNameField.setEditable(false);
			userNameField.setEditable(false);
			passwordField1.setEditable(false);
			passwordField2.setEditable(false);
			userLevelField.setEnabled(false);
			
			confirmPasswordLabel.setVisible(false);
			passwordField2.setVisible(false);
				
			//add listSelectionListener
			userAccountList.addListSelectionListener(this);
		}
			
		else if(e.getActionCommand().equals("Edit")) {
				
			//remove listSelectionListener
			userAccountList.removeListSelectionListener(this);
				
		    //make text fields editable
		    firstNameField.setEditable(true);
		    lastNameField.setEditable(true);
		    userNameField.setEditable(false);
		    passwordField1.setEditable(true);
		    passwordField2.setEditable(true);
		    userLevelField.setEnabled(true);
		    	
		    //change editSaveButton label to "Save"
		    userAccountEditSaveButton.setText("Save");
		    	
	        //set visibility of buttons
		    userAccountAddButton.setVisible(false);
		    userAccountDeleteButton.setVisible(false);
		    userAccountCancelButton.setVisible(true);
		    
		    //set visibility of second password field
		    confirmPasswordLabel.setVisible(true);
			passwordField2.setVisible(true);
				
		}
			
		else if(e.getActionCommand().equals("Save")) {
				
			//save contents of text fields to relevant userAccount
	        String firstName = firstNameField.getText();
	        String lastName = lastNameField.getText();
	        String userName = userNameField.getText();
	        char[] password1 = passwordField1.getPassword();
	        char[] password2 = passwordField2.getPassword();
	        String userLevel = (String) userLevelField.getSelectedItem();
	        int accessLevel = 0;
	        if(userLevel.equals("Administrator")) {
	        	accessLevel = 0;
	        }
	        else if(userLevel.equals("Manager")) {
	        	accessLevel = 1;
	        }
	        else if(userLevel.equals("Teller")) {
	        	accessLevel = 2;
	        }
	        else if(userLevel.equals("Stock check")) {
	        	accessLevel = 3;
	        }
	        					        			
	        if(firstName.equals("") || lastName.equals("") || userName.equals("") || password1.equals("") || password2.equals("")
	        		|| userLevel.equals("Please select")){
	        		
	        	JOptionPane.showMessageDialog(null, "Please fill all fields", "Input Warning", JOptionPane.WARNING_MESSAGE);
	        }
	        	
	        else {
	        	if(Arrays.equals(password1, password2)) {
	        		
	        		if(JOptionPane.showConfirmDialog(null, "Are you sure you want to save these changes?",
		    				"WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
		        			
		        		if(database.checkUserName(userName) == true) {
		            				        				
		        			// overwrite userAccount details
			            	UserAccount userAccount = database.getUserAccountByUserName(userName);
			            	userAccount.setUserAccountFirstName(firstName);
			            	userAccount.setUserAccountLastName(lastName);
			            	userAccount.setUserAccountPassword(password1);
			            	userAccount.setUserAccountUserLevel(accessLevel);
		            	}
		            				
		            	else {

		            		// add new userAccount to database
			    		    database.addUserAccount(firstName, lastName, userName, password1, accessLevel);
		            	}
		            		
		            	// update lists
		        		updateUserAccountLists();
		        					
		        		// reset textFields
		    	        resetTextFields();
		    	        	
		    		    //change editSaveButton label to "Edit"
		    	        userAccountEditSaveButton.setText("Edit");
		    	        	
		    	        //set visibility of buttons
		    		    userAccountAddButton.setVisible(true);
		    		    userAccountDeleteButton.setVisible(true);
		    		    userAccountCancelButton.setVisible(false);
		    		    userAccountEditSaveButton.setVisible(false);
		    		    	
		    		    //make text fields non-editable
		    		    firstNameField.setEditable(false);
		    		    lastNameField.setEditable(false);
		    		    userNameField.setEditable(false);
		    		    passwordField1.setEditable(false);
		    		    passwordField2.setEditable(false);
		    		    userLevelField.setEnabled(false);
		        	}
	        	}
	        	else {
	        		
	        		JOptionPane.showMessageDialog(null, "Passwords do not match", "Input Warning", JOptionPane.WARNING_MESSAGE);
	        	}
	        }
	        	
	        //add listSelectionListener
			userAccountList.addListSelectionListener(this);
		}
	}
	
	public void valueChanged(ListSelectionEvent e) {
		
		// JList userAccountList, String[] userAccounts
	        if (e.getValueIsAdjusting() == false) {

	        	if (e.getSource() == userAccountList) {
	        		
	        		userAccountEditSaveButton.setVisible(true);
	        		userAccountDeleteButton.setVisible(true);
	            	
	        		int noOfUserAccounts = database.getUserAccounts().size();
	                
	            	for(int i = 0; i < noOfUserAccounts; i++) {		//loop through userAccounts
	            		
	                    if (userAccountList.getSelectedIndex() == i) {
	                    	                    	
	                    	lastNameField.setText(database.getUserAccounts().get(i).getUserAccountLastName());
	                    	firstNameField.setText(database.getUserAccounts().get(i).getUserAccountFirstName());
	                    	userNameField.setText(database.getUserAccounts().get(i).getUserAccountUserName());
	                    	
	                    	char[] password = database.getUserAccounts().get(i).getUserAccountPassword();
	                    	passwordField1.setText(password.toString());
	                    	
	                    	int userLevel = database.getUserAccounts().get(i).getUserAccountUserLevel();
	                    	userLevelField.setSelectedIndex(userLevel);
	                    }

	                }
	            }
	        }
	}

}