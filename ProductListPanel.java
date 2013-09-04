package retailManagementSystem;

import java.awt.Color; 
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints; 
import java.awt.GridBagLayout; 
import java.awt.Insets; 
import java.awt.RenderingHints;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
  


import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
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
  
public class ProductListPanel extends JPanel implements ActionListener, ListSelectionListener{ 
      
    private Database database; 
          
    private String[] productNameList; 
    // check      
    private JList productList; 
    private ListModel productListModel; 
    private DefaultListModel updatedProductListModel; 
      
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
    private JButton productStockGraphButton;
    private JButton graphBackButton;

	private JPanel buttonPanel;
	private JPanel graphPanel;
	private JPanel panel;
	private JPanel newPanel;
      
    public ProductListPanel() { 
		System.out.println("ProductListPanel created");

    }
    
    private static final int maxStock = 1000;
    private static final int border = 50;
    private static final int yHatchCount = 15;
    private static final int graphPointWidth = 12;
    
    
    int [] stockLevels  = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    double [] predictedStockLevels = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
      
    public void buildPanel(JPanel panel, final Database database) { 
          
        this.database = database;
        this.panel = panel;
        productNameList = database.getProductList(); //array of type String[] 
          
        productList = new JList(productNameList);
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
        IDLabel = new JLabel("Id: ", SwingConstants.RIGHT);
                  
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
        productStockGraphButton = new JButton("Stock");
        graphBackButton = new JButton("Back");
          
        productEditSaveButton.setVisible(false); 
        productCancelButton.setVisible(false);
        productDeleteButton.setVisible(false);
        productStockGraphButton.setVisible(false);
        graphBackButton.setVisible(false);
                  
        // addButton listener 
        productAddButton.addActionListener(this); 
                  
        // deleteButton listener 
        productDeleteButton.addActionListener(this); 
                  
        // editSaveButton listener 
        productEditSaveButton.addActionListener(this); 
          
        // cancelButton Listener 
        productCancelButton.addActionListener(this); 
        
        // stockGraphButton Listener
        productStockGraphButton.addActionListener(this);
        
        // graphBackButton Listener
        graphBackButton.addActionListener(this);
          
        productListLabel = new JLabel("Product Control", SwingConstants.CENTER); 
        productListLabel.setOpaque(true); 
        productListLabel.setBackground(new Color(0,51,102)); 
        productListLabel.setForeground(Color.WHITE);
        productListLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
                  
		buttonPanel = new JPanel();
		graphPanel = new JPanel();
		newPanel = new JPanel();
		
        panel.setLayout(new GridBagLayout());
		buttonPanel.setLayout(new GridBagLayout());
		newPanel.setLayout(new GridBagLayout());
		
		//newGraphPane = new CreateStockGraph();
		//newGraphPane.buildPanel(graphPanel, panel, database);
		
		
		graphPanel = new graph();
		

		createConstraint(newPanel, productListLabel,	0, 0, 3, 1,	0, 10, 0, 0, 0, 0, 1, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(newPanel, listScroller, 		0, 1, 1, 7,	0, 0, 2, 2, 2, 2, 0.3, 1, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		
		createConstraint(newPanel, IDLabel, 		1, 1, 1, 1, 0, 0, 20, 2, 2, 2, 0.4, 0, GridBagConstraints.FIRST_LINE_END, GridBagConstraints.VERTICAL);
		createConstraint(newPanel, nameLabel, 		1, 2, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_END, GridBagConstraints.VERTICAL);
		createConstraint(newPanel, quantityLabel, 	1, 3, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_END, GridBagConstraints.VERTICAL);
		createConstraint(newPanel, priceLabel, 	1, 4, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_END, GridBagConstraints.VERTICAL);
		createConstraint(newPanel, typeLabel,		1, 5, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_END, GridBagConstraints.VERTICAL);

		createConstraint(newPanel, IDField, 		2, 1, 1, 1, 200, 0, 20, 2, 2, 2, 0.4, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.VERTICAL);
		createConstraint(newPanel, nameField, 		2, 2, 1, 1, 200, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.VERTICAL);
		createConstraint(newPanel, quantityField, 	2, 3, 1, 1, 200, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.VERTICAL);
		createConstraint(newPanel, priceField, 		2, 4, 1, 1, 200, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.VERTICAL);
		createConstraint(newPanel, typeField,		2, 5, 1, 1, 200, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.VERTICAL);

		createConstraint(buttonPanel, productAddButton, 		0, 0, 1, 1, 50, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(buttonPanel, productDeleteButton, 		1, 0, 1, 1, 50, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(buttonPanel, productCancelButton, 		1, 0, 1, 1, 50, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(buttonPanel, productEditSaveButton,	2, 0, 1, 1, 50, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(buttonPanel, productStockGraphButton,	3, 0, 1, 1, 50, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		
		createConstraint(newPanel, buttonPanel, 	1, 6, 2, 1, 0, 0, 20, 0, 0, 0, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE); 
		
		createConstraint(panel, newPanel,		0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(panel, graphPanel,	0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		
		graphPanel.setBackground(Color.WHITE);
		graphPanel.setVisible(false);
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
        updatedProductListModel = new DefaultListModel(); 
          
        for(Product product: database.getProducts()) { 
              
            updatedProductListModel.addElement(product.getProductName()); 
              
        } 
  
        // update JLists 
        productList.setModel(updatedProductListModel); 
          
        // update String[] arrays 
        productNameList = database.getProductList(); 
          
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
	    	productStockGraphButton.setVisible(true);
	    	
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
		    	productStockGraphButton.setVisible(false);
		    	
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
                			product.setStockLevels(null);
            			
            		}
            				
            		else {

    		        	// add new product to database
    		        	database.addProduct(name, type, quantity, Double.parseDouble(price), ID, null);
    		        		
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
		
		else if(e.getActionCommand().equals("Stock")){
			graphPanel.add(graphBackButton);
			graphBackButton.setVisible(true);
			newPanel.setVisible(false);
			graphPanel.setVisible(true);
			
		}
		
		else if(e.getActionCommand().equals("Back")) {
			newPanel.setVisible(true);
			graphPanel.setVisible(false);
		}
		
   }
      
    public void valueChanged(ListSelectionEvent e) { 
          
        // JList productList, String[] products 
            if (e.getValueIsAdjusting() == false) { 
  
                if (e.getSource() == productList) { 
                      
                    productEditSaveButton.setVisible(true); 
	        		productDeleteButton.setVisible(true);
	        		productStockGraphButton.setVisible(true);
                    
	        		ArrayList<Product> products = database.getProducts();
                    int noOfproducts = products.size(); 
                    ArrayList<Order> orders = database.getOrders();
                      
                    for(int i = 0; i < noOfproducts; i++) {     //loop through products 
                          
                        if (productList.getSelectedIndex() == i) { 
                          
                            System.out.println("product selected: " + productNameList[i]); 
                              
                            nameField.setText(products.get(i).getProductName()); 
                            typeField.setText(products.get(i).getProductType()); 
                            quantityField.setText(products.get(i).getProductQuantity()); 
                            priceField.setText(String.valueOf(products.get(i).getProductPrice())); 
                            IDField.setText(products.get(i).getProductID()); 
                    	
                            	
                            int stock [] = products.get(i).getStockLevels();  
                            stockLevels = stock;
                           
                            	int average1 = stock[0];
                            	int average2 = (stock[0]+stock[1])/2;
                            	int average3 = (stock[0]+stock[1]+stock[2])/3;
                                int average4 = (stock[0]+stock[1]+stock[2]+stock[3])/4;
                                int average5 = (stock[0]+stock[1]+stock[2]+stock[3]+stock[4])/5;
                                int average6 = (stock[0]+stock[1]+stock[2]+stock[3]+stock[4]+stock[5])/6;
                                int average7 = (stock[0]+stock[1]+stock[2]+stock[3]+stock[4]+stock[5]+stock[6])/7;
                                int average8 = (stock[0]+stock[1]+stock[2]+stock[3]+stock[4]+stock[5]+stock[6]+stock[7])/8;
                                int average9 = (stock[0]+stock[1]+stock[2]+stock[3]+stock[4]+stock[5]+stock[6]+stock[7]+stock[8])/9;
                                int average10 = (stock[0]+stock[1]+stock[2]+stock[3]+stock[4]+stock[5]+stock[6]+stock[7]+stock[8]+stock[9])/10;
                                int average11 = (stock[0]+stock[1]+stock[2]+stock[3]+stock[4]+stock[5]+stock[6]+stock[7]+stock[8]+stock[9]+stock[10])/11;
                                int average12 = (stock[0]+stock[1]+stock[2]+stock[3]+stock[4]+stock[5]+stock[6]+stock[7]+stock[8]+stock[9]+stock[10]+stock[11])/12;
                            	double [] stockPredictedData = {average1, average2, average3, average4, average5, average6, average7, average8, average9, average10, average11, average12}; 
                                predictedStockLevels = stockPredictedData;
                              
                        } 
  
                    } 
                } 
            } 
    }
    
    class graph extends JPanel{
        
    	protected void paintComponent(Graphics g) { 
    		
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Draw x and y axis
            g2.draw(new Line2D.Double(border, border, border, getHeight()-border));
            g2.draw(new Line2D.Double(border, getHeight()-border, getWidth()-border, getHeight()-border));
            
            // draw opaque grid
            g2.setColor(new Color(255, 0, 0, 64));
            int width = getWidth() - border * 2;
            int height = getHeight() - border * 2;
            g2.draw(new Line2D.Double(border, border, border, (getHeight()-border)));
            g2.draw(new Line2D.Double((border + width/11), border, (border + width/11), (getHeight()-border)));
            g2.draw(new Line2D.Double((border + width*2/11), border, (border + width*2/11), (getHeight()-border)));
            g2.draw(new Line2D.Double((border + width*3/11), border, (border + width*3/11), (getHeight()-border)));
            g2.draw(new Line2D.Double((border + width*4/11), border, (border + width*4/11), (getHeight()-border)));
            g2.draw(new Line2D.Double((border + width*5/11), border, (border + width*5/11), (getHeight()-border)));
            g2.draw(new Line2D.Double((border + width*6/11), border, (border + width*6/11), (getHeight()-border)));
            g2.draw(new Line2D.Double((border + width*7/11), border, (border + width*7/11), (getHeight()-border)));
            g2.draw(new Line2D.Double((border + width*8/11), border, (border + width*8/11), (getHeight()-border)));
            g2.draw(new Line2D.Double((border + width*9/11), border, (border + width*9/11), (getHeight()-border)));
            g2.draw(new Line2D.Double((border + width*10/11), border, (border + width*10/11), (getHeight()-border)));
            g2.draw(new Line2D.Double((border + width), border, (border + width), (getHeight()-border)));
            
            g2.draw(new Line2D.Double(border, border, getWidth() - border, border));
            g2.draw(new Line2D.Double(border, (border + height/15), getWidth() - border, (border + height/15)));
            g2.draw(new Line2D.Double(border, (border + height*2/15), getWidth() - border, (border + height*2/15)));
            g2.draw(new Line2D.Double(border, (border + height*3/15), getWidth() - border, (border + height*3/15)));
            g2.draw(new Line2D.Double(border, (border + height*4/15), getWidth() - border, (border + height*4/15)));
            g2.draw(new Line2D.Double(border, (border + height*5/15), getWidth() - border, (border + height*5/15)));
            g2.draw(new Line2D.Double(border, (border + height*6/15), getWidth() - border, (border + height*6/15)));
            g2.draw(new Line2D.Double(border, (border + height*7/15), getWidth() - border, (border + height*7/15)));
            g2.draw(new Line2D.Double(border, (border + height*8/15), getWidth() - border, (border + height*8/15)));
            g2.draw(new Line2D.Double(border, (border + height*9/15), getWidth() - border, (border + height*9/15)));
            g2.draw(new Line2D.Double(border, (border + height*10/15), getWidth() - border, (border + height*10/15)));
            g2.draw(new Line2D.Double(border, (border + height*11/15), getWidth() - border, (border + height*11/15)));
            g2.draw(new Line2D.Double(border, (border + height*12/15), getWidth() - border, (border + height*12/15)));
            g2.draw(new Line2D.Double(border, (border + height*13/15), getWidth() - border, (border + height*13/15)));
            g2.draw(new Line2D.Double(border, (border + height*14/15), getWidth() - border, (border + height*14/15)));
            g2.draw(new Line2D.Double(border, (border + height), getWidth() - border, (border + height)));
            
            g2.setPaint(Color.black);
            
            
             
            for (int i = 0; i < yHatchCount; i++) {
                 int x0 = border;
                 int x1 = graphPointWidth + border;
                 int y0 = getHeight() - (((i) * (getHeight() - border * 2)) / yHatchCount + border);
                 int y1 = y0;
                 g2.drawLine(x0, y0, x1, y1);
                 FontMetrics fm = g2.getFontMetrics();
                 String [] values = {"", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100", "110", "120", "130", "140", "150"};
                 g2.drawString(values[i], x0 - fm.stringWidth(values[i]), y0 + (fm.getAscent() / 2));
                 
              }
             
         // and for x axis
              for (int i = 0; i < 12; i++) { 
                 int x0 = (i) * (getWidth() - border * 2) / (12 - 1) + border;
                 int x1 = x0;
                 int y0 = getHeight() - border;
                 int y1 = y0 - graphPointWidth;
                 g2.drawLine(x0, y0, x1, y1);
                 FontMetrics fm = g2.getFontMetrics();
                 String [] months = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};
                 g2.drawString(months[i], x0 - (fm.stringWidth(months[i]) / 2), y0 + fm.getAscent());
              }
             
            // Draw labels
            Font font = g2.getFont();
            FontRenderContext frc = g2.getFontRenderContext();
            LineMetrics lm = font.getLineMetrics("0", frc);
            float sh = lm.getAscent() + lm.getDescent(); 
             
            // Ordinate label.
            String Label = "STOCK";
            float sy = border + ((getHeight() - 2*border) - Label.length()*sh)/2 + lm.getAscent();
            
            for(int i = 0; i < Label.length(); i++) {
                String letter = String.valueOf(Label.charAt(i));
                float sw = (float)font.getStringBounds(letter, frc).getWidth();
                float sx = (border - sw)/4;
                g2.drawString(letter, sx, sy);
                sy += sh;
            }
             
            // Abcissa label.
            Label = "MONTHS";
            sy = getHeight() - border + (border - sh)/2 + lm.getAscent();
            float sw = (float)font.getStringBounds(Label, frc).getWidth();
            float sx = (getWidth() - sw)/2;
            g2.drawString(Label, sx, sy);
            
            // legend stock levels
            Label = "Stock Levels";
            g2.setPaint(Color.green.darker());
            sy = getHeight() - (getHeight() - border/4);
            float sx1 = getWidth() - (border*3);
            g2.drawString(Label, sx1, sy);
            
            // legend predicted stock levels
            Label = "Predicted Stock Levels";
            g2.setPaint(Color.red.darker());
            sy = getHeight() - (getHeight() - border/2);
            float sx2 = getWidth() - (border*3);
            g2.drawString(Label, sx1, sy);
            
             
            // Draw stock level lines.
            double xInc = (double)(getWidth() - 2*border)/(12-1);
            double scale = (double)(getHeight() - 2*border)/maxStock;
            g2.setPaint(Color.green.darker());
            for(int i = 0; i < stockLevels.length-1; i++) {
            double x1 = border + i*xInc;
            double y1 = getHeight() - border - scale*stockLevels[i];
            double x2 = border + (i+1)*xInc;
            double y2 = getHeight() - border - scale*stockLevels[i+1];
            g2.draw(new Line2D.Double(x1, y1, x2, y2));
            }
             
            // Draw predicted stock level lines.
            double xInc2 = (double)(getWidth() - 2*border)/(12-1);
            double scale2 = (double)(getHeight() - 2*border)/maxStock;
            g2.setPaint(Color.red.darker());
            for(int i = 0; i < 11; i++) {
                double a1 = border + i*xInc2;
                double b1 = getHeight() - border - scale*predictedStockLevels[i];
                double a2 = border + (i+1)*xInc2;
                double b2 = getHeight() - border - scale2*predictedStockLevels[i+1];
                g2.draw(new Line2D.Double(a1, b1, a2, b2));
            }
             
            // Mark data points.
            g2.setPaint(Color.blue);
            for(int i = 0; i < stockLevels.length; i++) {
                double x = border + i*xInc;
                double y = getHeight() - border - scale*stockLevels[i];
                g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
            }
             
            // Mark predicted data points.
            g2.setPaint(Color.black);
            for(int i = 0; i < 12; i++) {
                double x = border + i*xInc2;
                double y = getHeight() - border - scale2*predictedStockLevels[i];
                g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
            }
           
        }
    	
    	
    }
}
