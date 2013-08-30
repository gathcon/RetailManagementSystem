package retailManagementSystem;

import java.awt.Color; 
import java.awt.Font;
import java.awt.GridBagConstraints; 
import java.awt.GridBagLayout; 
import java.awt.Insets; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 

import java.util.ArrayList;
import java.util.Arrays;

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
  
public class ProductListPanel extends JPanel implements ActionListener, ListSelectionListener{ 
      
    private Database database; 
          
    private String[] productNameList; 
          
    private JList<String> productList; 
    private ListModel<String> productListModel; 
    private DefaultListModel<String> updatedProductListModel; 
      
    private JScrollPane listScroller; 
    
    private JLabel productListLabel; 

    private JLabel nameLabel; 
    private JLabel typeLabel; 
    private JLabel quantityLabel; 
    private JLabel priceLabel; 
    private JLabel IDLabel;  
            
    private JTextField nameField; 
    private JTextField typeField; 
    private JTextField quantityField; 
    private JTextField priceField; 
    private JTextField IDField; 
      
    private JButton productAddButton; 
    private JButton productDeleteButton; 
    private JButton productEditSaveButton; 
    private JButton productCancelButton;

	private JPanel buttonPanel;
      
    public ProductListPanel() { 
		System.out.println("ProductListPanel created");

    } 
      
    public void buildPanel(JPanel panel, final Database database) { 
          
        this.database = database; 
        productNameList = database.getProductList(); //array of type String[] 
        Arrays.sort(productNameList, String.CASE_INSENSITIVE_ORDER);
        
        productList = new JList<String>(productNameList);
        productListModel = productList.getModel();
        productList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productList.setLayoutOrientation(JList.VERTICAL);
        productList.setVisibleRowCount(100);
        productList.addListSelectionListener(this);
          
        listScroller = new JScrollPane(productList);
        
        typeLabel = new JLabel("Type: ", SwingConstants.RIGHT);
        nameLabel = new JLabel("Name: ", SwingConstants.RIGHT);
        quantityLabel= new JLabel("Quantity: ", SwingConstants.RIGHT);
        priceLabel= new JLabel("Price: ", SwingConstants.RIGHT);
        IDLabel = new JLabel("ID: ", SwingConstants.RIGHT);
                  
        nameField = new JTextField();
        typeField = new JTextField();
        quantityField = new JTextField(); 
        priceField = new JTextField(); 
        IDField = new JTextField(); 
  
        nameField.setEditable(false); 
        nameField.setBackground(new Color(255,255,170)); 
        typeField.setEditable(false); 
        typeField.setBackground(new Color(255,255,170)); 
        quantityField.setEditable(false); 
        quantityField.setBackground(new Color(255,255,170)); 
        priceField.setEditable(false); 
        priceField.setBackground(new Color(255,255,170)); 
        IDField.setEditable(false); 
        IDField.setBackground(new Color(255,255,170)); 
          
        //Buttons 
        productAddButton = new JButton("Add"); 
        productDeleteButton = new JButton("Delete"); 
        productEditSaveButton = new JButton("Edit"); 
        productCancelButton = new JButton("Cancel"); 
          
        productEditSaveButton.setVisible(false); 
        productCancelButton.setVisible(false);
        productDeleteButton.setVisible(false);
                  
        // addButton listener 
        productAddButton.addActionListener(this); 
                  
        // deleteButton listener 
        productDeleteButton.addActionListener(this); 
                  
        // editSaveButton listener 
        productEditSaveButton.addActionListener(this); 
          
        // cancelButton Listener 
        productCancelButton.addActionListener(this); 
          
        productListLabel = new JLabel("Product Control", SwingConstants.CENTER); 
        productListLabel.setOpaque(true); 
        productListLabel.setBackground(new Color(0,51,102)); 
        productListLabel.setForeground(Color.WHITE);
        productListLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
                  
		buttonPanel = new JPanel();
		
        panel.setLayout(new GridBagLayout());
		buttonPanel.setLayout(new GridBagLayout());

		createConstraint(panel, productListLabel,	0, 0, 3, 1,	0, 10, 0, 0, 0, 0, 1, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(panel, listScroller, 		0, 1, 1, 7,	0, 0, 2, 2, 2, 2, 0.3, 1, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		
		createConstraint(panel, IDLabel, 		1, 1, 1, 1, 0, 0, 20, 2, 2, 2, 0.4, 0, GridBagConstraints.FIRST_LINE_END, GridBagConstraints.VERTICAL);
		createConstraint(panel, nameLabel, 		1, 2, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_END, GridBagConstraints.VERTICAL);
		createConstraint(panel, quantityLabel, 	1, 3, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_END, GridBagConstraints.VERTICAL);
		createConstraint(panel, priceLabel, 	1, 4, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_END, GridBagConstraints.VERTICAL);
		createConstraint(panel, typeLabel,		1, 5, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_END, GridBagConstraints.VERTICAL);

		createConstraint(panel, IDField, 		2, 1, 1, 1, 200, 0, 20, 2, 2, 2, 0.4, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.VERTICAL);
		createConstraint(panel, nameField, 		2, 2, 1, 1, 200, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.VERTICAL);
		createConstraint(panel, quantityField, 	2, 3, 1, 1, 200, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.VERTICAL);
		createConstraint(panel, priceField, 	2, 4, 1, 1, 200, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.VERTICAL);
		createConstraint(panel, typeField,		2, 5, 1, 1, 200, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.VERTICAL);

		createConstraint(buttonPanel, productAddButton, 		0, 0, 1, 1, 50, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(buttonPanel, productDeleteButton, 		1, 0, 1, 1, 50, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(buttonPanel, productCancelButton, 		1, 0, 1, 1, 50, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(buttonPanel, productEditSaveButton,	2, 0, 1, 1, 50, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);

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
    
    public void updateproductLists() { 
        
        //update the list of names etc 
        updatedProductListModel = new DefaultListModel<String>(); 
          
        productNameList = database.getProductList();
        Arrays.sort(productNameList, String.CASE_INSENSITIVE_ORDER);
        for(int i = 0; i < productNameList.length; i++ ){
        	updatedProductListModel.addElement(productNameList[i]);
        }
  
        // update JLists 
        productList.setModel(updatedProductListModel); 
          
//        update String[] arrays 
//        productNameList = database.getProductList(); 
          
        System.out.println("Product list updated"); 
    } 
  
    public void resetTextFields() { 
      
    	nameField.setText(""); 
    	typeField.setText(""); 
    	quantityField.setText(""); 
    	priceField.setText(""); 
    	IDField.setText("");
    	
    }
    
	// Button handler
	public void actionPerformed(ActionEvent e) {
		
		System.out.println(e.paramString());
		
		if(e.getActionCommand().equals("Add")) {
				    	
			//change text fields to editable
			nameField.setEditable(true);
	    	typeField.setEditable(true);
	    	quantityField.setEditable(true);
	    	priceField.setEditable(true);
	    	IDField.setEditable(true);
			
	    	// reset textFields
	    	resetTextFields();
	    	
	    	//change editSaveButton label to "Save"
	    	productEditSaveButton.setText("Save");
	    	
        	//set visibility of buttons
	    	productAddButton.setVisible(false);
	    	productDeleteButton.setVisible(false);
	    	productCancelButton.setVisible(true);
	    	productEditSaveButton.setVisible(true);
	    	
		}
		
		else if(e.getActionCommand().equals("Delete")) {
	    	
	    	// pop up dialog to confirm delete
			if(JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this customer?",
					"WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				
				//remove product from database
		    	String ID = IDField.getText();
		    	Product product = database.getProductByID(ID);
		    	database.deleteProduct(product);
		    	
		    	// update lists
				updateproductLists();
						
				// reset textFields
	        	resetTextFields();
	        	
	        	//set visibility of buttons
	        	productAddButton.setVisible(true);
		    	productDeleteButton.setVisible(true);
		    	productCancelButton.setVisible(false);
		    	productEditSaveButton.setVisible(false);
		    	
		    	//make text fields non-editable
		    	nameField.setEditable(false);
		    	typeField.setEditable(false);
		    	quantityField.setEditable(false);
		    	priceField.setEditable(false);
		    	IDField.setEditable(false);
		    	
			}
		}
		
		else if(e.getActionCommand().equals("Cancel")) {
			
			// reset textFields
			resetTextFields();
			
			//change save button to edit button
			productEditSaveButton.setText("Edit");
			
        	//set visibility of buttons
			productAddButton.setVisible(true);
			productDeleteButton.setVisible(false);
			productCancelButton.setVisible(false);
			productEditSaveButton.setVisible(false);
	    	
			//make text fields non-editable
			nameField.setEditable(false);
			typeField.setEditable(false);
			quantityField.setEditable(false);
			priceField.setEditable(false);
			IDField.setEditable(false);
		}
		
		else if(e.getActionCommand().equals("Edit")) {
			
	    	//make text fields editable
	    	nameField.setEditable(true);
	    	typeField.setEditable(true);
	    	quantityField.setEditable(true);
	    	priceField.setEditable(true);
	    	IDField.setEditable(false);
	    	
	    	//change editSaveButton label to "Save"
	    	productEditSaveButton.setText("Save");
	    	
        	//set visibility of buttons
	    	productAddButton.setVisible(false);
	    	productDeleteButton.setVisible(false);
	    	productCancelButton.setVisible(true);
			
		}
		
		else if(e.getActionCommand().equals("Save")) {
			
			//save contents of text fields to relevant product
        	String name = nameField.getText();
        	String type = typeField.getText();
        	String quantity = quantityField.getText();
        	String price = priceField.getText();
        	String ID = IDField.getText();
        	
        	boolean numberError = false;
        					        			
        	if(name.equals("") || type.equals("") || quantity.equals("") || price.equals("") || ID.equals("")){
        		
        		JOptionPane.showMessageDialog(null, "Please fill all fields", "Input Warning", JOptionPane.WARNING_MESSAGE);
        	}
        	
        	else {
        		
        		try {
            		
            		Double num = new Double(Double.parseDouble(price));
    				
    			} catch (Exception e2) {
    			
    				numberError = true;
    				JOptionPane.showMessageDialog(null, "Please enter a number for price", "Input Warning", JOptionPane.WARNING_MESSAGE);
    			}
        		
        		if(numberError == false) {
        			
        			if(JOptionPane.showConfirmDialog(null, "Are you sure you want to save these changes?",
        					"WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            		
            			if(database.checkProductID(ID) == true) {
                			
                			// overwrite product details
                			System.out.println("product " + ID + " already exists. Updating details.");

                			Product product = database.getProductByID(ID);
                			product.setProductName(name);
                			product.setProductType(type);
                			product.setProductQuantity(quantity);
                			product.setProductPrice(Double.parseDouble(price));	        					
                			
                		}
                				
                		else {

        		        	// add new product to database
        		        	database.addProduct(name, type, quantity, Double.parseDouble(price), ID);
        		        		
        			    	System.out.println("New product created.");  			
                		}
                		
                		// update lists
            			updateproductLists();
            					
            			// reset textFields
        	        	resetTextFields();
        	        	
        		    	//change editSaveButton label to "Edit"
        	        	productEditSaveButton.setText("Edit");
        	        	
        	        	//set visibility of buttons
        		    	productAddButton.setVisible(true);
        		    	productDeleteButton.setVisible(true);
        		    	productCancelButton.setVisible(false);
        		    	productEditSaveButton.setVisible(false);
        		    	
        		    	//make text fields non-editable
        		    	nameField.setEditable(false);
        		    	typeField.setEditable(false);
        		    	quantityField.setEditable(false);
        		    	priceField.setEditable(false);
        		    	IDField.setEditable(false);
        		    	
            		}
        		}
        	}
		}
   }
      
    public void valueChanged(ListSelectionEvent e) { 
          
        // JList productList, String[] products 
            if (e.getValueIsAdjusting() == false) { 
  
                if (e.getSource() == productList) { 
                      
                    productEditSaveButton.setVisible(true); 
	        		productDeleteButton.setVisible(true);
                    
	        		ArrayList<Product> products = database.getProducts();
                    int noOfproducts = products.size(); 
                      
                    for(int i = 0; i < noOfproducts; i++) {     //loop through products 
                          
                        if (productList.getSelectedIndex() == i) { 
                          
                            System.out.println("product selected: " + productNameList[i]); 
                              
                            nameField.setText(products.get(i).getProductName()); 
                            typeField.setText(products.get(i).getProductType()); 
                            quantityField.setText(products.get(i).getProductQuantity()); 
                            priceField.setText(String.valueOf(products.get(i).getProductPrice())); 
                            IDField.setText(products.get(i).getProductID()); 
                              
                        } 
  
                    } 
                } 
            } 
    }  
}