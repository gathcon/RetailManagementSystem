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


public class CreateNewOrderUI {
	
	private static double totalPrice = 0;
	private static double  deliveryCost = 0;

	private boolean updateOrderID = true;
	
	private static  int count = 0, count1 = 0, count2 = 0, count3 = 0;
	private JPanel dynamicPanel;
	private JScrollPane scrollPane;

	
	private ArrayList<JComboBox> productComboBox = new ArrayList<JComboBox>();
	private ArrayList<JComboBox> quantityComboBox = new ArrayList<JComboBox>(); 
	private ArrayList<JTextField> unitPriceField = new ArrayList<JTextField>(); 
	private ArrayList<JTextField> priceField = new ArrayList<JTextField>();
	private ArrayList<Boolean> updateQuantityCombobox = new ArrayList<Boolean>();
	
	private AccountingPanel accountingPane;
	private JLabel orderLabel, unitPriceLabel, priceLabel, totalPriceLabel, commentLabel;
	private JLabel orderIDLabel, supplierNameLabel, orderDateLabel, productLabel, quantityLabel;
	private JLabel deliveryDaysLabel, deliveryCostLabel,  deliveryDateLabel;

	private JTextArea  commentTextArea;
	
	private JTextField orderIDField, orderDateField, deliveryCostField;
	private JTextField totalPriceField,  deliveryDateField;
	
	private Object deliveryOption1 = "Free, 10 day";
	private Object deliveryOption2 = "€10, 3 day";
	private Object deliveryOption3 = "€25, Next day";
	
	private JComboBox supplierNameComboBox, deliveryDaysComboBox;

	
	private JButton orderClearButton, orderSaveButton, addButton, orderCancelButton;

	
	
	private Date orderDate;
	
	private String deliveryDate = "";
	
	private JPanel orderPanel;
	private JPanel tablePanel;
	
	private JTable tableOfOrders;
	
	private DefaultTableModel orderTableModel;
	
	private TableRowSorter<TableModel> sorter;
	
	private Database database;
	
	private String[] supplierListNames;
	private String[] productListNames;
	private Object[] data;
	private OrderListPanel orderPane;
	private JTabbedPane tabbedPane;
	private ProductListPanel productPane;
	
	public CreateNewOrderUI(){
		
	}
	
	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}
	
	public void updateComboBoxData() {
		supplierListNames = database.getSupplierList();
		productListNames = database.getProductList();
		
		supplierNameComboBox.setModel(new DefaultComboBoxModel(supplierListNames));
		supplierNameComboBox.addItem("Please Select");
		supplierNameComboBox.setSelectedItem("Please Select");
		
		for(JComboBox tempComboBox : productComboBox){
			tempComboBox.setModel(new DefaultComboBoxModel(productListNames));
			tempComboBox.addItem("Please Select");
			tempComboBox.setSelectedItem("Please Select");
		}
		
		for(JComboBox tempComboBox : quantityComboBox){
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
	}

	
	public void buildPanel(final JPanel orderPanel, final JPanel tablePanel, final Database database, JTable tableOfOrders,
			final OrderListPanel orderPane, AccountingPanel accountingPane, ProductListPanel productPane) {
		
		this.orderPanel = orderPanel;
		this.tablePanel = tablePanel;
		this.database = database;
		this.tableOfOrders = tableOfOrders;
		this.orderPane = orderPane;
		this.accountingPane = accountingPane;
		this.productPane = productPane;
		//this.splitPane = splitPane;

		
		orderLabel = new JLabel("Create New Order", SwingConstants.CENTER);
		orderLabel.setOpaque(true);
		orderLabel.setBackground(new Color(0,51,102));
		orderLabel.setForeground(Color.WHITE);
		orderLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
		
		orderIDLabel = new JLabel("OrderID:", SwingConstants.RIGHT);
		orderIDField = new JTextField();
		orderIDField.setEditable(false);
		orderIDField.setBackground(Color.WHITE);
		orderIDField.setHorizontalAlignment(SwingConstants.CENTER);	
	
		supplierListNames = database.getSupplierList();	
		supplierNameLabel = new JLabel("Supplier Name:", SwingConstants.RIGHT);
		supplierNameComboBox = new JComboBox(supplierListNames);
		supplierNameComboBox.addItem("Please Select");
		supplierNameComboBox.setSelectedIndex(0);
		supplierNameComboBox.setSelectedItem("Please Select");
		supplierNameComboBox.setMaximumRowCount(7);
		supplierNameComboBox.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				 if (e.getSource().equals(supplierNameComboBox) && updateOrderID == true){
						orderIDField.setText(generateUniqueId());
						updateOrderID = false;
				 }
			}		 
		 });


		
		orderDateLabel = new JLabel("Order Date:", SwingConstants.RIGHT);
		
		orderDate = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yy");
		orderDateField = new JTextField(ft.format(orderDate));
		orderDateField.setBackground(Color.WHITE);
		orderDateField.setEditable(false);
		orderDateField.setHorizontalAlignment(SwingConstants.CENTER);
		
		commentLabel = new JLabel("Comments");
		commentTextArea = new JTextArea();
		commentTextArea.setBackground(new Color(255,255,200));
		commentTextArea.setBorder(BorderFactory.createLoweredBevelBorder());
		commentTextArea.setLineWrap(true);
		JScrollPane scrollTextPane = new JScrollPane(commentTextArea);
		scrollTextPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		
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
		
		deliveryDaysLabel = new JLabel("Delivery Options:", SwingConstants.RIGHT);
		deliveryDaysComboBox = new JComboBox();
		
		deliveryDaysComboBox.addItem(deliveryOption1);
		deliveryDaysComboBox.addItem(deliveryOption2);
		deliveryDaysComboBox.addItem(deliveryOption3);
		
		deliveryDaysComboBox.setMaximumRowCount(7);
		deliveryDaysComboBox.setSelectedIndex(0);
		deliveryDaysComboBox.setSelectedItem("0");
		deliveryDaysComboBox.addActionListener(new QuantityComboBoxListener(database));
		
		deliveryDateLabel = new JLabel("Delivery Date:", SwingConstants.RIGHT);
		deliveryDateField = new JTextField();
		deliveryDateField.setEditable(false);
		deliveryDateField.setBackground(Color.WHITE);

		orderCancelButton = new JButton("Cancel");
		orderCancelButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == orderCancelButton){
					
					//go back to table view

					orderPanel.setVisible(false);
					orderPanel.invalidate();
					
					orderPane.getSplitPane().setVisible(true);
					orderPane.getSplitPane().validate();
					orderPane.getSplitPane().setDividerLocation(300);
					orderPane.getTablePanel().setVisible(true);
					orderPane.getTablePanel().validate();
					orderPane.getDynamicPanel().setVisible(true);
					orderPane.getDynamicPanel().validate();
					orderPane.getMainPanel().setVisible(true);
				
					orderPane.getMainPanel().repaint();
										
					//enable tabs
					tabbedPane.setEnabled(true);
				}
			}
		});
		
	
		
		orderSaveButton	= new JButton("Save");
		orderSaveButton.addActionListener(new OrderButtonListener(database));	
		
		orderClearButton = new JButton("Clear");
		orderClearButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(orderClearButton)){
					clearOrderPanel();
					updateOrderID = true;
				}
			}
		});	
		
		orderSaveButton	= new JButton("Save");
		orderSaveButton.addActionListener(new OrderButtonListener(database));
		
		
		addButton = new JButton("Add Product");
		
		orderPanel.setLayout(new GridBagLayout());
	    final JScrollPane scrollOrder = new JScrollPane(orderPanel);
	    dynamicPanel = new JPanel();
	    dynamicPanel.setLayout(new GridBagLayout());
	    scrollPane = new JScrollPane(dynamicPanel);
	    
	    orderPanel.setVisible(true);
	    scrollOrder.setVisible(true);
		
		createConstraint(orderPanel, orderLabel,	 		0, 0, 6, 1, 0, 10, 2, 2, 2, 2, 1, 0);
		
		createConstraint(orderPanel, supplierNameLabel,	 	0, 1, 1, 1, 0, 0, 20, 20, 2, 2, 0, 0);
		createConstraint(orderPanel, supplierNameComboBox,	1, 1, 1, 1, 0, 0, 20, 20, 2, 2, 0, 0);
		
		createConstraint(orderPanel, orderIDLabel,	 		2, 1, 1, 1, 0, 0, 20, 2, 2, 2, 0, 0);
		createConstraint(orderPanel, orderIDField,	 		3, 1, 1, 1, 10, 0, 20, 2, 2, 2, 0, 0);
		
		createConstraint(orderPanel, orderDateLabel,	 	4, 1, 1, 1, 0, 0, 20, 2, 2, 2, 0, 0);
		createConstraint(orderPanel, orderDateField,	 	5, 1, 1, 1, 10, 0, 20, 2, 2, 20, 0, 0);
		
		createConstraint(orderPanel, productLabel,		 	0, 2, 2, 1, 0, 0, 2,20,2,2, 1, 1);
		createConstraint(orderPanel, quantityLabel,			2, 2, 1, 1, 0, 0, 2,2,2,2, 1, 1);
		createConstraint(orderPanel, unitPriceLabel,	 	3, 2, 1, 1, 0, 0, 2,2,2,2, 1, 1);
		createConstraint(orderPanel, priceLabel,	 	 	5, 2, 1, 1, 0, 0, 2,2,2,20, 1, 1);
		
		
		createConstraint(orderPanel, scrollPane,	 	 	0, 3, 6, 3, 0, 0, 2,10,2,10, 1, 20);
		
		createProductField();
		
		  addButton.addActionListener(new ActionListener() { 
	            public void actionPerformed(ActionEvent e) { 
	            	
	            	productComboBox.add(new JComboBox(productListNames));
	            	quantityComboBox.add(new JComboBox());
	            	unitPriceField.add(new JTextField());
	            	priceField.add(new JTextField());
	            	updateQuantityCombobox.add(false);  
	            	
						count =  count + 1;
						JComboBox component = productComboBox.get(productComboBox.size()-1);
						component.addItem("Please Select");
						component.setSelectedItem("Please Select");
						component.setMaximumRowCount(7);
						component.setBackground(Color.WHITE);
						component.addActionListener(new ProductComboBoxListener(database));
						createConstraint(dynamicPanel, component,	 0, 1 + count, 2, 1, 0, 0, 2,10,2,2, 1, 0);
				
				
						count1 =  count1 + 1;
						JComboBox component1 = quantityComboBox.get(quantityComboBox.size()-1);
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
		
		createConstraint(orderPanel, addButton,	    		5, 7 + count3, 1, 1, 0, 0, 20, 2, 2, 20, 0, 0);
		createConstraint(orderPanel, deliveryDaysLabel,	    2, 8 + count3, 1, 1, 0, 0, 20, 2, 2, 2, 0, 0);
		createConstraint(orderPanel, deliveryDaysComboBox,	3, 8 + count3, 1, 1, 0, 0, 20, 2, 2, 2, 0, 0);
		
		
		createConstraint(orderPanel, deliveryDateLabel,	    2, 9 + count3, 1, 1, 0, 0, 20, 2, 2, 2, 0, 0);
		createConstraint(orderPanel, deliveryDateField,		3, 9 + count3, 1, 1, 0, 0, 20, 2, 2, 2, 0, 0);
		
		createConstraint(orderPanel, deliveryCostLabel,	    4, 8 + count3, 1, 1, 0, 0, 20, 2, 2, 2, 0, 0);
		createConstraint(orderPanel, deliveryCostField,	    5, 8 + count3, 1, 1, 0, 0, 20, 2, 2, 20, 0, 0);	
		
		createConstraint(orderPanel, totalPriceLabel,	    4, 9 + count3, 1, 1, 0, 0, 20, 2, 2, 2, 0, 0);
		createConstraint(orderPanel, totalPriceField,	    5, 9 + count3, 1, 1, 0, 0, 20, 2, 2, 20, 0, 0);
		
		createConstraint(orderPanel, orderClearButton,	    3, 10 + count3, 1, 1, 25, 0, 20, 2, 2, 2, 0, 0);
		createConstraint(orderPanel, orderCancelButton,	    4, 10 + count3, 1, 1, 20, 0, 20, 2, 2, 2, 0, 0);
		createConstraint(orderPanel, orderSaveButton,	    5, 10 + count3, 1, 1, 30, 0, 20, 2, 2, 20, 0, 0);
		
		createConstraint(orderPanel, commentLabel,	 		0, 11 + count3, 1, 1, 0, 0, 20, 20, 2, 2, 0, 0);
		createConstraint(orderPanel, scrollTextPane,	 	0, 12 + count3, 6, 2, 0, 0, 2, 20, 20, 20, 0, 1);
		

		orderPanel.setAutoscrolls(true);
		orderPanel.validate();
		orderPanel.repaint();
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
        String OrderID = "";
        char  temp;
        
        for(int i = 0; i<8;i++){       	
        	temp = newUUID[i];    	
        	if(Character.getType(newUUID[i]) == Character.LOWERCASE_LETTER){     		
        		temp = Character.toUpperCase(newUUID[i]);
        	} 
        	 OrderID += temp;
        }       
        
        return OrderID;
    }
	
    private class ProductComboBoxListener implements ActionListener{
		private Database database;
		
		private ProductComboBoxListener (Database database){
			this.database = database;
		}
		
		@Override
		public void actionPerformed(ActionEvent e){
			DecimalFormat df = new DecimalFormat("####0.00");
			 
			 for(JComboBox  tempComboBox: productComboBox){
				  int position = productComboBox.indexOf(e.getSource());
				 if (e.getSource().equals(productComboBox.get(position))){
					 totalPrice = 0;
					 updateQuantityCombobox.set(position,false);
					 JComboBox  tempquantityComboBox = quantityComboBox.get(position);
					 
					 tempquantityComboBox.removeAllItems();
					 
					 for(Product product: database.getProducts()){
						 if(productComboBox.get(position).getSelectedItem().equals(product.getProductName())){
							 unitPriceField.get(position).setText(Double.toString(product.getProductPrice()));
							 priceField.get(position).setText(String.valueOf(df.format(0)));
							 
							 for(int i = 0; i <= 50;i++){
								 tempquantityComboBox.addItem(Integer.toString(i*10));
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
	
			 if(!e.getSource().equals(deliveryDaysComboBox)){
			 for(JComboBox  tempComboBox: productComboBox){
				 
				 int position = quantityComboBox.indexOf(e.getSource());
				 JComboBox  tempquantityComboBox = quantityComboBox.get(position);
				 
				 if (e.getSource().equals(tempquantityComboBox) && updateQuantityCombobox.get(position) == true){
					 
						double price = Double.parseDouble((String) quantityComboBox.get(position).getSelectedItem())* 
						Double.parseDouble(unitPriceField.get(position).getText());
					 
					priceField.get(position).setText(String.valueOf(df.format(price))); 
					break;
				 }		
			 }
			}
			if(e.getSource().equals(deliveryDaysComboBox)){
				
				Object option = deliveryDaysComboBox.getSelectedItem();

				SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yy");
				Calendar c = Calendar.getInstance();
				 String tempDate = ft.format(orderDate).toString();
				try {
					c.setTime(ft.parse(tempDate));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				if(option.equals(deliveryOption1)){
					c.add(Calendar.DATE, 10);
					deliveryDate = ft.format(c.getTime());
					deliveryCost = 0;
					deliveryCostField.setText("0.00");
					deliveryDateField.setText(deliveryDate);
				}else if(option.equals(deliveryOption2)){
					c.add(Calendar.DATE, 3);
					deliveryDate = ft.format(c.getTime());
					deliveryCost = 10.00;
					deliveryCostField.setText("10.00");
					deliveryDateField.setText(deliveryDate);
				}else if(option.equals(deliveryOption3)){
					c.add(Calendar.DATE, 1);
					deliveryDate = ft.format(c.getTime());
					deliveryCost = 25.00;
					deliveryCostField.setText("25.00");
					deliveryDateField.setText(deliveryDate);
				}
			}
			
			totalPrice = 0;	
			for (JTextField tempTextField: priceField){	
				totalPrice = totalPrice + Double.parseDouble(tempTextField.getText());
			}
		
			totalPrice = totalPrice + deliveryCost;
			totalPriceField.setText(String.valueOf(df.format(totalPrice)));
			//deliveryCost = 0;	
		}
    }

	private void clearOrderPanel(){ 
		
		orderIDField.setText("");
		supplierNameComboBox.setSelectedIndex(0);
		supplierNameComboBox.setSelectedItem("Please Select");
		commentTextArea.setText(null);
		for(JTextField temp:priceField){
			temp.setText("0.00");
		}
		
		for(int i = 0; i <= productComboBox.size()-1; i++){
			productComboBox.remove(i);
			quantityComboBox.remove(i);
			unitPriceField.remove(i);
			priceField.remove(i);
		}	
				
		dynamicPanel.removeAll();
		createProductField();
		dynamicPanel.setVisible(true);
		dynamicPanel.validate();
		dynamicPanel.repaint();
		orderPanel.setVisible(true);
		orderPanel.validate();
		orderPanel.repaint();	
		
		totalPrice = 0;
		deliveryCost = 0;
		totalPriceField.setText("0.00");
		deliveryCostField.setText("0.00");	
		deliveryDaysComboBox.setSelectedIndex(0);
		deliveryDaysComboBox.setSelectedItem("0");
	}
	
	
	private class OrderButtonListener implements ActionListener{
		
		private Database database;
		
		private OrderButtonListener(Database database){
			this.database = database;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(orderSaveButton)){
				
				boolean itemSelectedFlag = false;
				 for(int i = 0; i <= productComboBox.size()-1; i++){
					 
					if (!productComboBox.get(i).getSelectedItem().equals("Please Select") && !((String)quantityComboBox.get(i).getSelectedItem()).equals("0")){
						itemSelectedFlag = true;
						break;
					}
				}
				 if(itemSelectedFlag == false || supplierNameComboBox.getSelectedItem().equals("Please Select") ||
					 deliveryDaysComboBox.getSelectedItem().equals("0")){
					 JOptionPane.showMessageDialog(null,"Choose a Supplier, Delivery Days and a Product to Progress with Order", "Input Warning",JOptionPane.WARNING_MESSAGE);
				 
				 }else{
					 
					 String orderID = orderIDField.getText();
					 Supplier supplier = database.getSupplierByName((String)supplierNameComboBox.getSelectedItem());
					 String orderCost = totalPriceField.getText();
					 String orderDescription = commentTextArea.getText();
					 ArrayList<Product> orderedproducts = new ArrayList<Product>();
					 int increaseStock = 0;
					 for(int i = 0; i <= productComboBox.size()-1; i++){
						 
						if (!productComboBox.get(i).getSelectedItem().equals("Please Select") && !quantityComboBox.get(i).getSelectedItem().equals(null)
								&&!quantityComboBox.get(i).getSelectedItem().equals("0")){
							String productName = (String)productComboBox.get(i).getSelectedItem();
							String productType = database.getProductTypeByName(productName);
							String productQuantity = (String) quantityComboBox.get(i).getSelectedItem();
							Double productPrice = Double.parseDouble(priceField.get(i).getText());
							Product product = new Product(productName, productType, productQuantity, productPrice);
							orderedproducts.add(product);
							increaseStock = Integer.parseInt(database.getProductByName(productName).getProductQuantity()) + Integer.parseInt(productQuantity) ;
							database.getProductByName(productName).setProductQuantity(Integer.toString(increaseStock));
							System.out.println("the product increase"+database.getProductByName(productName).getProductQuantity());
						}
					 }
					 
					 
					 //update ProductList
					 productPane.updateproductLists();
					 SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yy");
					
					//add order to database
					database.addOrder(orderID, supplier, ft.format(orderDate).toString(), deliveryDate, orderCost, true, orderedproducts, orderDescription);
					accountingPane.refreshAccount();
					clearOrderPanel(); 
					totalPrice = 0;
					deliveryCost = 0;
					updateOrderID = true;
					
					//update table
					DefaultTableModel orderTableModel = new DefaultTableModel() {

						@Override
					    public boolean isCellEditable(int row, int column) {
					    	return false;
					    }
					    
					    @Override
			            public Class getColumnClass(int column) {
			                switch (column) {
			                    case 0:
			                        return String.class;
			                    case 1:
			                        return String.class;
			                    case 2:
			                    	return String.class;
			                    case 3:
			                    	return String.class;
			                    default:
			                        return Boolean.class;
			                }
					    }
					};
					
					//orderScrollPane = new JScrollPane(tableOfOrders);
				    String [] columnNames = {"Order ID", "Order Date", "Delivery Date","Cost","Outstanding"};
					orderTableModel.setColumnIdentifiers(columnNames);
					
					ArrayList<Order> tempGetOrder = database.getOrders();
					for(Order order : database.getOrders()){
						data = new Object[] {
								order.getOrderID(),
								order.getOrderDate(),
								order.getOrderDeliveryDate(),
								order.getOrderCost(),
								new Boolean(order.isOrderOutstanding())
								};
						
						orderTableModel.addRow(data);
					}
					tableOfOrders.setModel(orderTableModel);
					sorter = new TableRowSorter<TableModel>(tableOfOrders.getModel());
					sorter.setSortable(1, false);
                    sorter.setSortable(2, false);
                    sorter.setSortable(3, false);
				    tableOfOrders.setRowSorter(sorter); //
					            
			         
					//go back to table view
					orderPanel.setVisible(false);
					orderPanel.invalidate();
					
					orderPane.getSplitPane().setVisible(true);
					orderPane.getSplitPane().validate();
					orderPane.getSplitPane().setDividerLocation(300);
					orderPane.getTablePanel().setVisible(true);
					orderPane.getTablePanel().validate();
					orderPane.getDynamicPanel().setVisible(true);
					orderPane.getDynamicPanel().validate();
					orderPane.getMainPanel().setVisible(true);
				
					orderPane.getMainPanel().repaint();
								
					//enable tabs
					tabbedPane.setEnabled(true);
				} 
			}
		}
	}
	
	
	private void createProductField(){
		
		productComboBox.add(new JComboBox(productListNames));
    	quantityComboBox.add(new JComboBox());
    	unitPriceField.add(new JTextField());
    	priceField.add(new JTextField());
    	updateQuantityCombobox.add(false);
    	
    	JComboBox component = productComboBox.get(productComboBox.size()-1);
		component.addItem("Please Select");
		component.setSelectedItem("Please Select");
		component.setMaximumRowCount(7);
		component.setBackground(Color.WHITE);
		component.addActionListener(new ProductComboBoxListener(database));
		

		JComboBox component1 = quantityComboBox.get(quantityComboBox.size()-1);
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
