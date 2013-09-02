package retailManagementSystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class CreateNewInvoiceUI {
	
	private static double totalPrice = 0;
	private static double  deliveryCost = 0;

	private boolean updateInvoiceID = true;
	
	private static  int count = 0, count1 = 0, count2 = 0, count3 = 0;
	private JPanel dynamicPanel;
	private JScrollPane scrollPane;

	
	private ArrayList<JComboBox<String>> productComboBox = new ArrayList<JComboBox<String>>();
	private ArrayList<JComboBox<String>> quantityComboBox = new ArrayList<JComboBox<String>>(); 
	private ArrayList<JTextField> unitPriceField = new ArrayList<JTextField>(); 
	private ArrayList<JTextField> priceField = new ArrayList<JTextField>();
	private ArrayList<Boolean> updateQuantityCombobox = new ArrayList<Boolean>();
	
	
	private JLabel invoiceLabel, unitPriceLabel, priceLabel, totalPriceLabel, commentLabel;
	private JLabel invoiceIDLabel, customerNameLabel, invoiceDateLabel, productLabel, quantityLabel;
	private JLabel deliveryDaysLabel, deliveryCostLabel, deliveryDateLabel;
	private JTextArea  commentTextArea;
	
	private JTextField invoiceIDField, invoiceDateField, deliveryCostField;
	private JTextField totalPriceField, deliveryDateField;
	
	private JComboBox<String> customerNameComboBox, deliveryDaysComboBox;

	
	private JButton invoiceClearButton, invoiceSaveButton, addButton, invoiceCancelButton;

	
	
	private Date invoiceDate;
	
	private String deliveryDate = "";
	
	private JPanel invoicePanel;
	private JPanel tablePanel;
	
	private JTable tableOfInvoices;
	
	private DefaultTableModel invoiceTableModel;
	
	private TableRowSorter<TableModel> sorter;
	
	private Database database;
	
	private String[] customerListNames;
	private String[] productListNames;
	private InvoiceListPanel invoicePane;
	private JTabbedPane tabbedPane;
	
	public CreateNewInvoiceUI(){
		
	}
	
	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}
	
	public void updateComboBoxData() {
		customerListNames = database.getCustomerList();
		productListNames = database.getProductList();
		
		customerNameComboBox.setModel(new DefaultComboBoxModel<String>(customerListNames));
		customerNameComboBox.addItem("Please Select");
		customerNameComboBox.setSelectedItem("Please Select");
		
		for(JComboBox<String> tempComboBox : productComboBox){
			tempComboBox.setModel(new DefaultComboBoxModel<String>(productListNames));
			tempComboBox.addItem("Please Select");
			tempComboBox.setSelectedItem("Please Select");
		}
		
		for(JComboBox<String> tempComboBox : quantityComboBox){
			tempComboBox.addItem("0");
			tempComboBox.setSelectedItem("0");
		}
		
		for(JTextField tempField: unitPriceField){
			tempField.setText("0.00");
		}
		
		for(JTextField tempField: priceField){
			tempField.setText("0.00");
		}
		
		deliveryCostField.setText("0.00");

		deliveryDaysComboBox.setSelectedIndex(0);

		totalPriceField.setText("0.00");

		System.out.println("Product and customer list updated.");
	}

	
	public void buildPanel(final JPanel invoicePanel, final JPanel tablePanel, final Database database, JTable tableOfInvoices,
			final InvoiceListPanel invoicePane) {
		
		this.invoicePanel = invoicePanel;
		this.tablePanel = tablePanel;
		this.database = database;
		this.tableOfInvoices = tableOfInvoices;
		this.invoicePane = invoicePane;
		
		//this.splitPane = splitPane;
		
		invoiceLabel = new JLabel("Create New Invoice", SwingConstants.CENTER);
		invoiceLabel.setOpaque(true);
		invoiceLabel.setBackground(new Color(0,51,102));
		invoiceLabel.setForeground(Color.WHITE);
		invoiceLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
		
		invoiceIDLabel = new JLabel("InvoiceID:", SwingConstants.RIGHT);
		invoiceIDField = new JTextField();
		invoiceIDField.setEditable(false);
		invoiceIDField.setBackground(Color.WHITE);
		invoiceIDField.setHorizontalAlignment(SwingConstants.CENTER);	
	
		customerListNames = database.getCustomerList();	
		customerNameLabel = new JLabel("Customer Name:", SwingConstants.RIGHT);
		customerNameComboBox = new JComboBox<String>(customerListNames);
		customerNameComboBox.addItem("Please Select");
		customerNameComboBox.setSelectedIndex(0);
		customerNameComboBox.setSelectedItem("Please Select");
		customerNameComboBox.setMaximumRowCount(7);
		customerNameComboBox.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				 if (e.getSource().equals(customerNameComboBox) && updateInvoiceID == true){
						invoiceIDField.setText(generateUniqueId());
						updateInvoiceID = false;
				 }
			}		 
		 });


		
		invoiceDateLabel = new JLabel("Invoice Date:", SwingConstants.RIGHT);
		
		invoiceDate = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");
		invoiceDateField = new JTextField(ft.format(invoiceDate));
		invoiceDateField.setBackground(Color.WHITE);
		invoiceDateField.setEditable(false);
		invoiceDateField.setHorizontalAlignment(SwingConstants.CENTER);
		
		commentLabel = new JLabel("Comments");
		commentTextArea = new JTextArea();
		commentTextArea.setBackground(new Color(255,255,200));
		commentTextArea.setBorder(BorderFactory.createLoweredBevelBorder());
		
		productListNames = database.getProductList();
		
		productLabel = new JLabel("Product:");
		quantityLabel = new JLabel("Quantity:");
		unitPriceLabel = new JLabel("Unit Price:", SwingConstants.CENTER);
		priceLabel = new JLabel("Price:");

		
		deliveryCostLabel = new JLabel("Delivery Cost:", SwingConstants.RIGHT);
		deliveryCostField = new JTextField();
		deliveryCostField.setEditable(false);
		deliveryCostField.setText("0.00");
		deliveryCostField.setBackground(Color.WHITE);
		
		totalPriceLabel = new JLabel("Total Price:", SwingConstants.RIGHT);
		totalPriceField = new JTextField();
		totalPriceField.setEditable(false);
		totalPriceField.setText("0.00");
		totalPriceField.setBackground(Color.WHITE);
		
		deliveryDaysLabel = new JLabel("Delivery Days:", SwingConstants.RIGHT);
		deliveryDaysComboBox = new JComboBox<String>();
		for(int i = 0; i <=100 ;i++){
			deliveryDaysComboBox.addItem(Integer.toString(i));
		}
		deliveryDaysComboBox.setMaximumRowCount(7);
		deliveryDaysComboBox.setSelectedIndex(0);
		deliveryDaysComboBox.setSelectedItem("0");
		deliveryDaysComboBox.addActionListener(new QuantityComboBoxListener(database));
		
		deliveryDateLabel = new JLabel("Delivery Date:", SwingConstants.RIGHT);
		deliveryDateField = new JTextField();
		deliveryDateField.setEditable(false);
		deliveryDateField.setBackground(Color.WHITE);
		
		
		invoiceCancelButton = new JButton("Cancel");
		invoiceCancelButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == invoiceCancelButton){
					
					//go back to table view
					

					invoicePanel.setVisible(false);
					invoicePanel.invalidate();
					
					invoicePane.getSplitPane().setVisible(true);
					invoicePane.getSplitPane().validate();
					invoicePane.getSplitPane().setDividerLocation(300);
					invoicePane.getTablePanel().setVisible(true);
					invoicePane.getTablePanel().validate();
					invoicePane.getDynamicPanel().setVisible(true);
					invoicePane.getDynamicPanel().validate();
					invoicePane.getMainPanel().setVisible(true);
				
					invoicePane.getMainPanel().repaint();
					
					System.out.println("invoice panel visible");
					
					//enable tabs
					tabbedPane.setEnabled(true);
					System.out.println("tabs enabled");
				}
			}
		});
		
	
		
		invoiceSaveButton	= new JButton("Save");
		invoiceSaveButton.addActionListener(new InvoiceButtonListener(database));	
		
		invoiceClearButton = new JButton("Clear");
		invoiceClearButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(invoiceClearButton)){
					clearInvoicePanel();
					updateInvoiceID = true;
				}
			}
		});	
		
		invoiceSaveButton	= new JButton("Save");
		invoiceSaveButton.addActionListener(new InvoiceButtonListener(database));
		
		
		addButton = new JButton("Add Product");
		
		invoicePanel.setLayout(new GridBagLayout());
	    final JScrollPane scrollInvoice = new JScrollPane(invoicePanel);
	    dynamicPanel = new JPanel();
	    dynamicPanel.setLayout(new GridBagLayout());
	    scrollPane = new JScrollPane(dynamicPanel);
	    
	    invoicePanel.setVisible(true);
	    scrollInvoice.setVisible(true);
		
		createConstraint(invoicePanel, invoiceLabel,	 		0, 0, 6, 1, 0, 10, 2, 2, 2, 2, 1, 0);
		
		createConstraint(invoicePanel, customerNameLabel,	 	0, 1, 1, 1, 0, 0, 20, 20, 2, 2, 0, 0);
		createConstraint(invoicePanel, customerNameComboBox,	1, 1, 1, 1, 0, 0, 20, 20, 2, 2, 0, 0);
		
		createConstraint(invoicePanel, invoiceIDLabel,	 		2, 1, 1, 1, 0, 0, 20, 2, 2, 2, 0, 0);
		createConstraint(invoicePanel, invoiceIDField,	 		3, 1, 1, 1, 10, 0, 20, 2, 2, 2, 0, 0);
		
		createConstraint(invoicePanel, invoiceDateLabel,	 	4, 1, 1, 1, 0, 0, 20, 2, 2, 2, 0, 0);
		createConstraint(invoicePanel, invoiceDateField,	 	5, 1, 1, 1, 10, 0, 20, 2, 2, 20, 0, 0);
		
		createConstraint(invoicePanel, productLabel,		 	0, 2, 2, 1, 0, 0, 2,20,2,2, 1, 1);
		createConstraint(invoicePanel, quantityLabel,			2, 2, 1, 1, 0, 0, 2,2,2,2, 1, 1);
		createConstraint(invoicePanel, unitPriceLabel,	 	3, 2, 1, 1, 0, 0, 2,2,2,2, 1, 1);
		createConstraint(invoicePanel, priceLabel,	 	 	5, 2, 1, 1, 0, 0, 2,2,2,20, 1, 1);
		
		
		createConstraint(invoicePanel, scrollPane,	 	 	0, 3, 6, 3, 0, 0, 2,10,2,10, 1, 20);
		
		createProductField();
		
		  addButton.addActionListener(new ActionListener() { 
	            public void actionPerformed(ActionEvent e) { 
	            	
	            	System.out.println("add button was pressed");

	            	productComboBox.add(new JComboBox<String>(productListNames));
	            	quantityComboBox.add(new JComboBox<String>());
	            	unitPriceField.add(new JTextField());
	            	priceField.add(new JTextField());
	            	updateQuantityCombobox.add(false);  
	            	
						count =  count + 1;
						JComboBox<String> component = productComboBox.get(productComboBox.size()-1);
						component.addItem("Please Select");
						component.setSelectedItem("Please Select");
						component.setMaximumRowCount(7);
						component.setBackground(Color.WHITE);
						component.addActionListener(new ProductComboBoxListener(database));
						createConstraint(dynamicPanel, component,	 0, 1 + count, 2, 1, 0, 0, 2,10,2,2, 1, 0);
				
				
						count1 =  count1 + 1;
						JComboBox<String> component1 = quantityComboBox.get(quantityComboBox.size()-1);
						component1.addItem("0");
						component1.setSelectedIndex(0);
						component1.setMaximumRowCount(7);
						component1.setBackground(Color.WHITE);
						component1.addActionListener(new QuantityComboBoxListener(database));
						createConstraint(dynamicPanel, component1, 2, 1 + count1,  1, 1, 0, 0, 2,2,2,2, 1, 0);
				
					
						JTextField component2 = unitPriceField.get(unitPriceField.size()-1);
						count2 =  count2 + 1;
						component2.setEditable(false);
						component2.setText("0.00");
						component2.setBackground(Color.WHITE);
						createConstraint(dynamicPanel, component2,	3, 1 + count2,  1, 1, 0, 0, 2,2,2,2, 1, 0);
					 
					 
						JTextField component3 = priceField.get(priceField.size()-1);
						count3 =  count3 + 1;
						component3.setEditable(false);
						component3.setText("0.00");
						component3.setBackground(Color.WHITE);
						createConstraint(dynamicPanel, component3,	4, 1 + count3,  1, 1, 0, 0, 2,2,2,10, 1, 0);

						
						dynamicPanel.setVisible(true);
						dynamicPanel.validate();
						dynamicPanel.repaint();
						scrollPane.setVisible(true);
						scrollPane.validate();
						scrollPane.repaint();	
					 		
	            } 
	     }); 
		
		createConstraint(invoicePanel, addButton,	    		5, 7 + count3, 1, 1, 0, 0, 20, 2, 2, 20, 0, 0);
		createConstraint(invoicePanel, deliveryDaysLabel,	    2, 8 + count3, 1, 1, 0, 0, 20, 2, 2, 2, 0, 0);
		createConstraint(invoicePanel, deliveryDaysComboBox,	3, 8 + count3, 1, 1, 0, 0, 20, 2, 2, 2, 0, 0);
		
		createConstraint(invoicePanel, deliveryDateLabel,	    2, 9 + count3, 1, 1, 0, 0, 20, 2, 2, 2, 0, 0);
		createConstraint(invoicePanel, deliveryDateField,		3, 9 + count3, 1, 1, 0, 0, 20, 2, 2, 2, 0, 0);
		
		createConstraint(invoicePanel, deliveryCostLabel,	    4, 8 + count3, 1, 1, 0, 0, 20, 2, 2, 2, 0, 0);
		createConstraint(invoicePanel, deliveryCostField,	    5, 8 + count3, 1, 1, 0, 0, 20, 2, 2, 20, 0, 0);	
		
		createConstraint(invoicePanel, totalPriceLabel,	    	4, 9 + count3, 1, 1, 0, 0, 20, 2, 2, 2, 0, 0);
		createConstraint(invoicePanel, totalPriceField,	    	5, 9 + count3, 1, 1, 0, 0, 20, 2, 2, 20, 0, 0);
		
		createConstraint(invoicePanel, invoiceClearButton,	    3, 10 + count3, 1, 1, 25, 0, 20, 2, 2, 2, 0, 0);
		createConstraint(invoicePanel, invoiceCancelButton,	    4, 10 + count3, 1, 1, 20, 0, 20, 2, 2, 2, 0, 0);
		createConstraint(invoicePanel, invoiceSaveButton,	    5, 10 + count3, 1, 1, 30, 0, 20, 2, 2, 20, 0, 0);
		
		createConstraint(invoicePanel, commentLabel,	 		0, 11 + count3, 1, 1, 0, 0, 20, 20, 2, 2, 0, 0);
		createConstraint(invoicePanel, commentTextArea,	 		0, 12 + count3, 6, 2, 0, 0, 2, 20, 20, 20, 0, 1);
		

		invoicePanel.setAutoscrolls(true);
		invoicePanel.validate();
		invoicePanel.repaint();
	}

	private void createConstraint(JPanel panel, JComponent c, int gridx, int gridy, int width, int height, 
			int ipadx, int ipady, int top, int left, int buttom, int right, double weightx, double weighty){
		
			GridBagConstraints gc = new GridBagConstraints();
			gc.gridx = gridx;
			gc.gridy = gridy ;
			gc.gridwidth = width;
			gc.gridheight = height ;
			gc.ipadx = ipadx;
			gc.ipady = ipady;
			gc.weightx = weightx;
			gc.weighty = weighty;
			gc.insets = new Insets(top, left, buttom, right);
			gc.anchor = GridBagConstraints.FIRST_LINE_START;
			gc.fill = GridBagConstraints.BOTH;
			panel.add(c, gc);
	}

	
    public static String generateUniqueId() {      
        String uuidChars = "" + UUID.randomUUID().toString();
        String uid = uuidChars.replaceAll("-", "");    
        int myuid = uid.hashCode();
        
        uid = Integer.toString(myuid);
        uid = uuidChars.replaceAll("-", "");
        
        char[] newUUID = uid.toCharArray();
        String invoiceID = "";
        char  temp;
        
        for(int i = 0; i<8;i++){       	
        	temp = newUUID[i];    	
        	if(Character.getType(newUUID[i]) == Character.LOWERCASE_LETTER){     		
        		temp = Character.toUpperCase(newUUID[i]);
        	} 
        	 invoiceID += temp;
        }       
        System.out.println(invoiceID);
        
        return invoiceID;
    }
	
    private class ProductComboBoxListener implements ActionListener{
		private Database database;
		
		private ProductComboBoxListener (Database database){
			this.database = database;
		}
		
		@Override
		public void actionPerformed(ActionEvent e){
			DecimalFormat df = new DecimalFormat("####0.00");
			 
			 for(JComboBox <String> tempComboBox: productComboBox){
				  int position = productComboBox.indexOf(e.getSource());
				 if (e.getSource().equals(productComboBox.get(position))){
					 totalPrice = 0;
					 System.out.println("i an an event handler " + position);
					 updateQuantityCombobox.set(position,false);
					 JComboBox <String> tempquantityComboBox = quantityComboBox.get(position);
					 
					 tempquantityComboBox.removeAllItems();
					 
					 for(Product product: database.getProducts()){
						 if(productComboBox.get(position).getSelectedItem().equals(product.getProductName())){
							 unitPriceField.get(position).setText(Double.toString(product.getProductPrice()));
							 priceField.get(position).setText(String.valueOf(df.format(0)));
							 
							 for(int i = 0; i <= Integer.parseInt(product.getProductQuantity());i++){
								 tempquantityComboBox.addItem(Integer.toString(i));
							 }
							
							 tempquantityComboBox.setSelectedIndex(0);
						 } 
					 }
					 
					 updateQuantityCombobox.set(position,true);
					 tempquantityComboBox.addActionListener(new QuantityComboBoxListener(database));
					 
					 break;
				 }
			 } 
		}
	 }

    
    private class QuantityComboBoxListener implements ActionListener{
		private Database database;
		
		private QuantityComboBoxListener (Database database){
			this.database = database;
		}

		@Override
		public void actionPerformed(ActionEvent e){
			 DecimalFormat df = new DecimalFormat("####0.00");
	
			 System.out.println( "I am Combo" + quantityComboBox.indexOf(e.getSource()));
			 if(!e.getSource().equals(deliveryDaysComboBox)){
			 for(JComboBox <String> tempComboBox: productComboBox){
				 
				 int position = quantityComboBox.indexOf(e.getSource());
				 JComboBox <String> tempquantityComboBox = quantityComboBox.get(position);
				 
				 if (e.getSource().equals(tempquantityComboBox) && updateQuantityCombobox.get(position) == true){
					 
					 System.out.println("i an an event quantity handler " + position);
						double price = Double.parseDouble((String) quantityComboBox.get(position).getSelectedItem())* 
						Double.parseDouble(unitPriceField.get(position).getText());
					 
					priceField.get(position).setText(String.valueOf(df.format(price))); 
					break;
				 }		
			 }
			}
			if(e.getSource().equals(deliveryDaysComboBox)){
				
				int days = Integer.parseInt((String) deliveryDaysComboBox.getSelectedItem());

				SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");
				Calendar c = Calendar.getInstance();
				 String tempDate = ft.format(invoiceDate).toString();
				try {
					c.setTime(ft.parse(tempDate));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				if(days == 0){
					c.add(Calendar.DATE, 0);
					deliveryDate = ft.format(c.getTime());
					System.out.println(deliveryDate);
					deliveryCost = 0;
					deliveryCostField.setText("0.00");
					deliveryDateField.setText(deliveryDate);
				}else if(days >= 1 && days <= 5){
					c.add(Calendar.DATE, days);
					deliveryDate = ft.format(c.getTime());
					System.out.println(deliveryDate);
					deliveryCost = 100;
					deliveryCostField.setText("100.00");
					deliveryDateField.setText(deliveryDate);
				}else if(days >= 6 && days <= 10){
					c.add(Calendar.DATE, days);
					deliveryDate = ft.format(c.getTime());
					System.out.println(deliveryDate);
					deliveryCost = 70.59;
					deliveryCostField.setText("70.59");
					deliveryDateField.setText(deliveryDate);
				}else if(days >= 11 && days <= 15){
					c.add(Calendar.DATE, days);
					deliveryDate = ft.format(c.getTime());
					System.out.println(deliveryDate);
					deliveryCost = 50.55;
					deliveryCostField.setText("50.55");
					deliveryDateField.setText(deliveryDate);
				}else{
					c.add(Calendar.DATE, days);
					deliveryDate = ft.format(c.getTime());
					System.out.println(deliveryDate);
					deliveryCost = 2.00;
					deliveryCostField.setText("20.00");
					deliveryDateField.setText(deliveryDate);
				}
			}
			
			totalPrice = 0;	
			for (JTextField tempTextField: priceField){	
				totalPrice = totalPrice + Double.parseDouble(tempTextField.getText());
			}
		
			totalPrice = totalPrice + deliveryCost;
			totalPriceField.setText(String.valueOf(df.format(totalPrice)));
			deliveryCost = 0;	
		}
    }

	private void clearInvoicePanel(){ 
		
		invoiceIDField.setText("");
		customerNameComboBox.setSelectedIndex(0);
		customerNameComboBox.setSelectedItem("Please Select");
		
		for(JTextField temp:priceField){
			temp.setText("0.00");
		}
		
		for(int i = 0; i <= productComboBox.size()-1; i++){
			productComboBox.remove(i);
			quantityComboBox.remove(i);
			unitPriceField.remove(i);
			priceField.remove(i);
		}	
		
		System.out.println("object has been removed");
		
		dynamicPanel.removeAll();
		createProductField();
		dynamicPanel.setVisible(true);
		dynamicPanel.validate();
		dynamicPanel.repaint();
		invoicePanel.setVisible(true);
		invoicePanel.validate();
		invoicePanel.repaint();	
		
		totalPrice = 0;
		deliveryCost = 0;
		totalPriceField.setText("0.00");
		deliveryCostField.setText("0.00");	
		deliveryDaysComboBox.setSelectedIndex(0);
		deliveryDaysComboBox.setSelectedItem("0");
	}
	
	
	private class InvoiceButtonListener implements ActionListener{
		
		private Database database;
		
		private InvoiceButtonListener(Database database){
			this.database = database;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(invoiceSaveButton)){
				
				boolean itemSelectedFlag = false;
				for(int i = 0; i <= productComboBox.size()-1; i++){
					 
					if (!productComboBox.get(i).getSelectedItem().equals("Please Select") && !((String)quantityComboBox.get(i).getSelectedItem()).equals("0")){
						itemSelectedFlag = true;
						System.out.println("an item was selected");
						break;
					}
				}
				 if(itemSelectedFlag == false || customerNameComboBox.getSelectedItem().equals("Please Select") ||
					 deliveryDaysComboBox.getSelectedItem().equals("0")){
					 JOptionPane.showMessageDialog(null,"Choose a Customer, Delivery Days and a Product to Progress with Invoice", "Input Warning",JOptionPane.WARNING_MESSAGE);
				 }else{
					 
					 System.out.println("Save button was clicked");
					 String invoiceID = invoiceIDField.getText();
					 Customer customer = database.getCustomerByName((String)customerNameComboBox.getSelectedItem());
					 String invoiceCost = totalPriceField.getText();
					 String comment = commentTextArea.getText();
					 ArrayList<Product> invoiceproducts = new ArrayList<Product>();
					 
					 for(int i = 0; i <= productComboBox.size()-1; i++){
						 
						if (!productComboBox.get(i).getSelectedItem().equals("Please Select") && !quantityComboBox.get(i).getSelectedItem().equals(null)
								&&!quantityComboBox.get(i).getSelectedItem().equals("0")){
							String productName = (String)productComboBox.get(i).getSelectedItem();
							String productType = database.getProductTypeByName(productName);
							String productQuantity = (String) quantityComboBox.get(i).getSelectedItem();
							Double productPrice = Double.parseDouble(priceField.get(i).getText());
							Product product = new Product(productName, productType, productQuantity, productPrice);
							invoiceproducts.add(product);
						}
					 }
					 
					 SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");
					//add invoice to database
					 
					database.addInvoice(invoiceID, customer, ft.format(invoiceDate).toString(), deliveryDate, invoiceCost, true, invoiceproducts, comment);
					clearInvoicePanel(); 
					totalPrice = 0;
					deliveryCost = 0;
					updateInvoiceID = true;
					
					//update table
					invoiceTableModel =  new DefaultTableModel();
					tableOfInvoices.setModel(invoiceTableModel);
					sorter = new TableRowSorter<TableModel>(tableOfInvoices.getModel());
				    tableOfInvoices.setRowSorter(sorter); 
					//invoiceScrollPane = new JScrollPane(tableOfInvoices);
				    String [] columnNames = {"Invoice ID", "Delivery Date","Cost","Outstanding"};
					invoiceTableModel.setColumnIdentifiers(columnNames);
					
					ArrayList<Invoice> tempGetInvoice = database.getInvoices();
					for(Invoice invoice : database.getInvoices()){
						invoiceTableModel.addRow(new String[] {
						invoice.getInvoiceID(),
								invoice.getInvoiceDeliveryDate(),
								invoice.getInvoiceCost(),
								String.valueOf(invoice.isInvoiceOutstanding())});
					}
					            
			         
					//go back to table view
					invoicePanel.setVisible(false);
					invoicePanel.invalidate();
					
					invoicePane.getSplitPane().setVisible(true);
					invoicePane.getSplitPane().validate();
					invoicePane.getSplitPane().setDividerLocation(300);
					invoicePane.getTablePanel().setVisible(true);
					invoicePane.getTablePanel().validate();
					invoicePane.getDynamicPanel().setVisible(true);
					invoicePane.getDynamicPanel().validate();
					invoicePane.getMainPanel().setVisible(true);
				
					invoicePane.getMainPanel().repaint();
			
					System.out.println("invoice panel visible");
					
					//enable tabs
					tabbedPane.setEnabled(true);
					
					System.out.println("tabs enabled");					
				} 
			}
		}
	}
	
	
	private void createProductField(){
		
		productComboBox.add(new JComboBox<String>(productListNames));
    	quantityComboBox.add(new JComboBox<String>());
    	unitPriceField.add(new JTextField());
    	priceField.add(new JTextField());
    	updateQuantityCombobox.add(false);
    	
    	JComboBox<String> component = productComboBox.get(productComboBox.size()-1);
		component.addItem("Please Select");
		component.setSelectedItem("Please Select");
		component.setMaximumRowCount(7);
		component.setBackground(Color.WHITE);
		component.addActionListener(new ProductComboBoxListener(database));
		

		JComboBox<String> component1 = quantityComboBox.get(quantityComboBox.size()-1);
		component1.addItem("0");
		component1.setSelectedIndex(0);
		component1.setMaximumRowCount(7);
		component1.setBackground(Color.WHITE);
		component1.addActionListener(new QuantityComboBoxListener(database));

	
		JTextField component2 = unitPriceField.get(unitPriceField.size()-1);
		component2.setEditable(false);
		component2.setText("0.00");
		component2.setBackground(Color.WHITE);
	 
	 
		JTextField component3 = priceField.get(priceField.size()-1);
		component3.setEditable(false);
		component3.setText("0.00");
		component3.setBackground(Color.WHITE);
		
		createConstraint(dynamicPanel, component,	 	0, 0, 2, 1, 0, 0, 2,10,2,2, 1, 0);
		createConstraint(dynamicPanel, component1,		2, 0, 1, 1, 0, 0, 2,2,2,2, 1, 0);
		createConstraint(dynamicPanel, component2,	 	3, 0, 1, 1, 0, 0, 2,2,2,2, 1, 0);
		createConstraint(dynamicPanel, component3,	  	4, 0, 1, 1, 0, 0, 2,2,2,10, 1,0);		
	}
}