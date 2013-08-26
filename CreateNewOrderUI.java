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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class CreateNewOrderUI {
	
	private static double totalprice = 0;
	private static double price1 = 0, price2 = 0, price3 = 0, price4 = 0 , deliveryCost = 0;
	
	private JLabel orderLabel, unitPriceLabel, priceLabel, totalPriceLabel, commentLabel;
	private JLabel orderIDLabel, supplierNameLabel, orderDateLabel, productLabel, quantityLabel;
	private JLabel deliveryDaysLabel, deliveryCostLabel;
	private JTextArea  commentTextArea;
	
	private JTextField orderIDField, orderDateField, deliveryCostField;
	private JComboBox<String> supplierNameComboBox, deliveryDaysComboBox;
	private JComboBox<String> productComboBox1, productComboBox2, productComboBox3, productComboBox4;
	private JComboBox<String> quantityComboBox1, quantityComboBox2, quantityComboBox3, quantityComboBox4;
	private JTextField unitPriceField1, unitPriceField2, unitPriceField3, unitPriceField4;
	private JTextField priceField1, priceField2, priceField3, priceField4, totalPriceField;
	
	private JButton orderClearButton, orderSaveButton, orderCancelButton;
	private boolean updateCombobox1 = false;
	private boolean updateCombobox2 = false;
	private boolean updateCombobox3 = false;
	private boolean updateCombobox4 = false;
	private boolean updateOrderID = true;
	private Date orderDate;
	private String deliveryDate = "";
	
	private JPanel orderPanel;
	private JPanel tablePanel;
	private JTable tableOfOrders;
	private DefaultTableModel orderTableModel;
	
	public CreateNewOrderUI(){
		
	}
	
	public void buildPanel(JPanel orderPanel, JPanel tablePanel, Database database, JTable tableOfOrders) {
		
		this.orderPanel = orderPanel;
		this.tablePanel = tablePanel;
		this.tableOfOrders = tableOfOrders;
		
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
		
		ArrayList<String> supplierNames = new ArrayList<String>();
		
		for(Supplier supplier:database.getSuppliers()){
			supplierNames.add(supplier.getSupplierName());
		}
		 supplierNameLabel = new JLabel("Supplier Name:", SwingConstants.RIGHT);
		 supplierNameComboBox = new JComboBox<String>(supplierNames.toArray(new String[supplierNames.size()]));
		 supplierNameComboBox.addItem("Please Select");
		 supplierNameComboBox.setSelectedIndex(0);
		 supplierNameComboBox.setSelectedItem("Please Select");
		 supplierNameComboBox.setMaximumRowCount(7);
		 supplierNameComboBox.addActionListener(new ProductComboBoxListener(database));

		
		orderDateLabel = new JLabel("Order Date:", SwingConstants.RIGHT);
		
		orderDate = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");
		orderDateField = new JTextField(ft.format(orderDate));
		orderDateField.setBackground(Color.WHITE);
		orderDateField.setEditable(false);
		orderDateField.setHorizontalAlignment(SwingConstants.CENTER);
		
		commentLabel = new JLabel("Comments");
		commentTextArea = new JTextArea();
		commentTextArea.setBackground(new Color(255,255,200));
		commentTextArea.setBorder(BorderFactory.createLoweredBevelBorder());
		
		ArrayList<String> productNames = new ArrayList<String>();
		
		for(Product product:database.getProducts()){
			 productNames.add(product.getProductName());
		}
		
		productLabel = new JLabel("Product:");
		productComboBox1 = new JComboBox<String>(productNames.toArray(new String[productNames.size()]));
		productComboBox1.addItem("Please Select");
		productComboBox1.setSelectedIndex(0);
		productComboBox1.setSelectedItem("Please Select");
		productComboBox1.setMaximumRowCount(7);
		productComboBox1.addActionListener(new ProductComboBoxListener(database));
		
		productComboBox2 = new JComboBox<String>(productNames.toArray(new String[productNames.size()]));
		productComboBox2.addItem("Please Select");
		productComboBox2.setSelectedIndex(0);
		productComboBox2.setSelectedItem("Please Select");
		productComboBox2.setMaximumRowCount(7);
		productComboBox2.addActionListener(new ProductComboBoxListener(database));
		
		productComboBox3 = new JComboBox<String>(productNames.toArray(new String[productNames.size()]));
		productComboBox3.addItem("Please Select");
		productComboBox3.setSelectedIndex(0);
		productComboBox3.setSelectedItem("Please Select");
		productComboBox3.setMaximumRowCount(7);
		productComboBox3.addActionListener(new ProductComboBoxListener(database));
		
		productComboBox4 = new JComboBox<String>(productNames.toArray(new String[productNames.size()]));
		productComboBox4.addItem("Please Select");
		productComboBox4.setSelectedIndex(0);
		productComboBox4.setSelectedItem("Please Select");
		productComboBox4.setMaximumRowCount(7);
		productComboBox4.addActionListener(new ProductComboBoxListener(database));
		
		quantityLabel = new JLabel("Quantity:");
		quantityComboBox1 = new JComboBox<String>();
		quantityComboBox1.setMaximumRowCount(7);
		quantityComboBox1.addItem("0");
		quantityComboBox1.setSelectedIndex(0);
		quantityComboBox1.setSelectedItem("0");
		quantityComboBox1.addActionListener(new QuantityComboBoxListener(database));
		
		quantityComboBox2 = new JComboBox<String>();
		quantityComboBox2.setMaximumRowCount(7);
		quantityComboBox2.addItem("0");
		quantityComboBox2.setSelectedIndex(0);
		quantityComboBox2.setSelectedItem("0");
		quantityComboBox2.addActionListener(new QuantityComboBoxListener(database));
		
		quantityComboBox3 = new JComboBox<String>();
		quantityComboBox3.setMaximumRowCount(7);
		quantityComboBox3.addItem("0");
		quantityComboBox3.setSelectedIndex(0);
		quantityComboBox3.setSelectedItem("0");
		quantityComboBox3.addActionListener(new QuantityComboBoxListener(database));
		
		quantityComboBox4 = new JComboBox<String>();
		quantityComboBox4.setMaximumRowCount(7);
		quantityComboBox4.addItem("0");
		quantityComboBox4.setSelectedIndex(0);
		quantityComboBox4.setSelectedItem("0");
		quantityComboBox4.addActionListener(new QuantityComboBoxListener(database));

		
		unitPriceLabel = new JLabel("Unit Price:");
		unitPriceField1 = new JTextField();
		unitPriceField1.setText("€0.00");
		unitPriceField1.setEditable(false);
		unitPriceField1.setBackground(Color.WHITE);
		
		unitPriceField2 = new JTextField();
		unitPriceField2.setText("€0.00");
		unitPriceField2.setEditable(false);
		unitPriceField2.setBackground(Color.WHITE);
		
		unitPriceField3 = new JTextField();
		unitPriceField3.setText("€0.00");
		unitPriceField3.setEditable(false);
		unitPriceField3.setBackground(Color.WHITE);
		
		unitPriceField4 = new JTextField();
		unitPriceField4.setText("€0.00");
		unitPriceField4.setEditable(false);
		unitPriceField4.setBackground(Color.WHITE);
		
		
		priceLabel = new JLabel("Price:");
		priceField1 = new JTextField();
		priceField1.setEditable(false);
		priceField1.setText("€0.00");
		priceField1.setBackground(Color.WHITE);
		
		priceField2 = new JTextField();
		priceField2.setEditable(false);
		priceField2.setText("€0.00");
		priceField2.setBackground(Color.WHITE);
		
		priceField3 = new JTextField();
		priceField3.setText("€0.00");
		priceField3.setEditable(false);
		priceField3.setBackground(Color.WHITE);
		
		priceField4 = new JTextField();
		priceField4.setEditable(false);
		priceField4.setText("€0.00");
		priceField4.setBackground(Color.WHITE);
		
		deliveryCostLabel = new JLabel("Delivery Cost:", SwingConstants.RIGHT);
		deliveryCostField = new JTextField();
		deliveryCostField.setEditable(false);
		deliveryCostField.setText("€0.00");
		deliveryCostField.setBackground(Color.WHITE);
		
		totalPriceLabel = new JLabel("Total Price:", SwingConstants.RIGHT);
		totalPriceField = new JTextField();
		totalPriceField.setEditable(false);
		totalPriceField.setText("€0.00");
		totalPriceField.setBackground(Color.WHITE);
		
		deliveryDaysLabel = new JLabel("Delivery Days:", SwingConstants.RIGHT);
		deliveryDaysComboBox = new JComboBox<String>();
		for(int i = 0; i <=100 ;i++){
			deliveryDaysComboBox.addItem(Integer.toString(i));
		}
		deliveryDaysComboBox.setMaximumRowCount(7);
		deliveryDaysComboBox.setSelectedIndex(0);
		quantityComboBox1.setSelectedItem("0");
		deliveryDaysComboBox.addActionListener(new QuantityComboBoxListener(database));

		orderCancelButton = new JButton("Cancel");
		orderCancelButton.addActionListener(new OrderButtonListener(database, tablePanel));
		
		orderClearButton = new JButton("Clear");
		orderClearButton.addActionListener(new OrderButtonListener(database, tablePanel));
		
		orderSaveButton	= new JButton("Save");
		orderSaveButton.addActionListener(new OrderButtonListener(database, tablePanel));
		
		orderPanel.setLayout(new GridBagLayout());
		
		createConstraint(orderPanel, orderLabel,	 		0, 0, 6, 1, 0, 10, 2, 2, 2, 2, 1, 0);
		
		createConstraint(orderPanel, supplierNameLabel,	 	0, 1, 1, 1, 0, 0, 20, 20, 2, 2, 0, 0);
		createConstraint(orderPanel, supplierNameComboBox,	1, 1, 1, 1, 0, 0, 20, 20, 2, 2, 0, 0);
		
		createConstraint(orderPanel, orderIDLabel,	 		2, 1, 1, 1, 0, 0, 20, 2, 2, 2, 0, 0);
		createConstraint(orderPanel, orderIDField,	 		3, 1, 1, 1, 10, 0, 20, 2, 2, 2, 0, 0);
		
		createConstraint(orderPanel, orderDateLabel,	 	4, 1, 1, 1, 0, 0, 20, 2, 2, 2, 0, 0);
		createConstraint(orderPanel, orderDateField,	 	5, 1, 1, 1, 10, 0, 20, 2, 2, 20, 0, 0);
		
		createConstraint(orderPanel, productLabel,		 	0, 2, 3, 1, 0, 0, 20, 20, 2, 2, 0.7, 0);
		createConstraint(orderPanel, productComboBox1,	 	0, 3, 3, 1, 0, 0, 2, 20, 2, 2, 0, 0);
		createConstraint(orderPanel, productComboBox2,	 	0, 4, 3, 1, 0, 0, 2, 20, 2, 2, 0, 0);
		createConstraint(orderPanel, productComboBox3,	 	0, 5, 3, 1, 0, 0, 2, 20, 2, 2, 0, 0);
		createConstraint(orderPanel, productComboBox4,	 	0, 6, 3, 1, 0, 0, 2, 20, 2, 2, 0, 0);
		
		createConstraint(orderPanel, quantityLabel,		 	3, 2, 1, 1, 0, 0, 20, 2, 2, 2, 0, 0);
		createConstraint(orderPanel, quantityComboBox1,	 	3, 3, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0);
		createConstraint(orderPanel, quantityComboBox2,	 	3, 4, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0);
		createConstraint(orderPanel, quantityComboBox3,	 	3, 5, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0);
		createConstraint(orderPanel, quantityComboBox4,	 	3, 6, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0);
		
		createConstraint(orderPanel, unitPriceLabel,	 	4, 2, 1, 1, 0, 0, 20, 2, 2, 2, 0, 0);
		createConstraint(orderPanel, unitPriceField1,	 	4, 3, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0);
		createConstraint(orderPanel, unitPriceField2,	 	4, 4, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0);
		createConstraint(orderPanel, unitPriceField3,	 	4, 5, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0);
		createConstraint(orderPanel, unitPriceField4,	 	4, 6, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0);
		
		createConstraint(orderPanel, priceLabel,	 	 	5, 2, 1, 1, 0, 0, 20, 2, 2, 20, 0, 0);
		createConstraint(orderPanel, priceField1,	  	 	5, 3, 1, 1, 0, 0, 2, 2, 2, 20, 0, 0);
		createConstraint(orderPanel, priceField2,	     	5, 4, 1, 1, 0, 0, 2, 2, 2, 20, 0, 0);
		createConstraint(orderPanel, priceField3,	     	5, 5, 1, 1, 0, 0, 2, 2, 2, 20, 0, 0);
		createConstraint(orderPanel, priceField4,	     	5, 6, 1, 1, 0, 0, 2, 2, 2, 20, 0, 0);

		createConstraint(orderPanel, deliveryDaysLabel,	    2, 7, 1, 1, 0, 0, 20, 2, 2, 2, 0, 0);
		createConstraint(orderPanel, deliveryDaysComboBox,	3, 7, 1, 1, 0, 0, 20, 2, 2, 2, 0, 0);
		
		createConstraint(orderPanel, deliveryCostLabel,	    4, 7, 1, 1, 0, 0, 20, 2, 2, 2, 0, 0);
		createConstraint(orderPanel, deliveryCostField,	    5, 7, 1, 1, 0, 0, 20, 2, 2, 20, 0, 0);	
		
		createConstraint(orderPanel, totalPriceLabel,	    4, 8, 1, 1, 0, 0, 20, 2, 2, 2, 0, 0);
		createConstraint(orderPanel, totalPriceField,	    5, 8, 1, 1, 0, 0, 20, 2, 2, 20, 0, 0);
		
		createConstraint(orderPanel, orderClearButton,	    3, 9, 1, 1, 25, 0, 20, 2, 2, 2, 0, 0);
		createConstraint(orderPanel, orderCancelButton,	    4, 9, 1, 1, 20, 0, 20, 2, 2, 2, 0, 0);
		createConstraint(orderPanel, orderSaveButton,	    5, 9, 1, 1, 30, 0, 20, 2, 2, 20, 0, 0);
		
		createConstraint(orderPanel, commentLabel,	 		0, 10, 1, 1, 0, 0, 20, 20, 2, 2, 0, 0);
		createConstraint(orderPanel, commentTextArea,	 	0, 11, 6, 2, 0, 0, 2, 20, 20, 20, 0, 1);
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
        String OrderID ="";
        for(int i = 0; i<8;i++){
        	OrderID += newUUID[i];
        }       
        
        System.out.println(OrderID);
        
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
			
			if(e.getSource() == productComboBox1){	
				updateCombobox1 = false;
				quantityComboBox1.removeAllItems();
				for(Product product:database.getProducts()){
					if(productComboBox1.getSelectedItem().equals(product.getProductName())){
						unitPriceField1.setText("€"+product.getProductPrice());
						price1 = 0;
						priceField1.setText("€"+ String.valueOf(df.format(0)));	
						for(int i = 0; i <= 100;i++){
							int j = i;
							quantityComboBox1.addItem(Integer.toString(j*100));
						}	
						quantityComboBox1.setSelectedIndex(0);
						updateCombobox1 = true;
						break;
					}
				}
				
			}
			else if (e.getSource() == productComboBox2){
				updateCombobox2 = false;
				quantityComboBox2.removeAllItems();
				for(Product product:database.getProducts()){
					if(productComboBox2.getSelectedItem().equals(product.getProductName())){
						unitPriceField2.setText("€"+product.getProductPrice());
						price2 = 0;
						priceField2.setText("€"+ String.valueOf(df.format(0)));
						for(int i = 0; i <= 100;i++){
							int j = i;
							quantityComboBox2.addItem(Integer.toString(j*100));
						}
						quantityComboBox2.setSelectedIndex(0);
						updateCombobox2 = true;
						break;
					}
				}
				
			}
			else if (e.getSource() == productComboBox3){
				updateCombobox3 = false;
				quantityComboBox3.removeAllItems();
				for(Product product:database.getProducts()){
					if(productComboBox3.getSelectedItem().equals(product.getProductName())){
						unitPriceField3.setText("€"+product.getProductPrice());
						price3 = 0;
						priceField3.setText("€"+ String.valueOf(df.format(0)));
						for(int i = 0; i <= 100; i++){
							int j = i;
							quantityComboBox3.addItem(Integer.toString(j*100));
						}
						quantityComboBox3.setSelectedIndex(0);
						updateCombobox3 = true;
						break;
					}
				}
			}
			else if (e.getSource() == productComboBox4){
				updateCombobox4 = false;
				quantityComboBox4.removeAllItems();
				for(Product product:database.getProducts()){
					if(productComboBox4.getSelectedItem().equals(product.getProductName())){
						unitPriceField4.setText("€"+ product.getProductPrice());
						price4 = 0;
						priceField4.setText("€"+ String.valueOf(df.format(0)));
						for(int i = 0; i <= 100;i++){
							int j = i;
							quantityComboBox4.addItem(Integer.toString(j*100));
						}
						quantityComboBox4.setSelectedIndex(0);
						updateCombobox4 = true;
						break;
					}
				}
			}
			else if (e.getSource() == supplierNameComboBox && updateOrderID == true){
				orderIDField.setText(generateUniqueId());
				updateOrderID = false;
			}	
		}
	}
	
	private class QuantityComboBoxListener implements ActionListener{
		private Database database;
		
		private QuantityComboBoxListener (Database database){
			this.database = database;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			DecimalFormat df = new DecimalFormat("#####0.00"); 
						
			if(e.getSource().equals(quantityComboBox1) && updateCombobox1 == true){
				
				price1 = Double.parseDouble((String) quantityComboBox1.getSelectedItem())* 
						Double.parseDouble(unitPriceField1.getText().substring(1, unitPriceField1.getText().length()));
				priceField1.setText("€"+ String.valueOf(df.format(price1)));
				updateCombobox1 = false; 
			}
			else if(e.getSource().equals(quantityComboBox2) && updateCombobox2 == true){
				
				price2 = Double.parseDouble((String) quantityComboBox2.getSelectedItem())* 
						Double.parseDouble(unitPriceField2.getText().substring(1, unitPriceField2.getText().length()));
				priceField2.setText("€"+ String.valueOf(df.format(price2)));
				updateCombobox2= false;
			}
			else if(e.getSource().equals(quantityComboBox3) && updateCombobox3 == true){
				
				price3 = Double.parseDouble((String) quantityComboBox3.getSelectedItem())* 
						Double.parseDouble(unitPriceField3.getText().substring(1, unitPriceField3.getText().length()));
				priceField3.setText("€"+ String.valueOf(df.format(price3)));
				updateCombobox3 = false;
			}
			else if(e.getSource().equals(quantityComboBox4) && updateCombobox4 == true){
				
				price4 = Double.parseDouble((String) quantityComboBox4.getSelectedItem())* 
						Double.parseDouble(unitPriceField4.getText().substring(1, unitPriceField4.getText().length()));
				priceField4.setText("€"+ String.valueOf(df.format(price4)));
				updateCombobox4 = false;
			}
			else if(e.getSource().equals(deliveryDaysComboBox)){
				
				int days = Integer.parseInt((String) deliveryDaysComboBox.getSelectedItem());

				SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");
				Calendar calendar = Calendar.getInstance();
				String tempDate = ft.format(orderDate).toString();
				try {
					
					calendar.setTime(ft.parse(tempDate));
				} catch (ParseException e1) {
					
					e1.printStackTrace();
				}
				
				if(days == 0){
					
					calendar.add(Calendar.DATE, 0);
					deliveryDate = ft.format(calendar.getTime());
					System.out.println(deliveryDate);
					deliveryCost = 0;
					deliveryCostField.setText("€0.00");
				}
				else if(days >= 1 && days <= 5){
					
					calendar.add(Calendar.DATE, days);
					deliveryDate = ft.format(calendar.getTime());
					System.out.println(deliveryDate);
					deliveryCost = 10;
					deliveryCostField.setText("€10.00");
				}
				else if(days >= 6 && days <= 10){
					
					calendar.add(Calendar.DATE, days);
					deliveryDate = ft.format(calendar.getTime());
					System.out.println(deliveryDate);
					deliveryCost = 7.59;
					deliveryCostField.setText("€7.59");
				}
				else if(days >= 11 && days <= 15){
					
					calendar.add(Calendar.DATE, days);
					deliveryDate = ft.format(calendar.getTime());
					System.out.println(deliveryDate);
					deliveryCost = 5.55;
					deliveryCostField.setText("€5.55");
				}
				else{
					
					calendar.add(Calendar.DATE, days);
					deliveryDate = ft.format(calendar.getTime());
					System.out.println(deliveryDate);
					deliveryCost = 2.00;
					deliveryCostField.setText("€2.00");
				}
			}
		
			totalprice = price1 + price2 + price3 + price4 + deliveryCost;
			totalPriceField.setText("€"+  String.valueOf(df.format(totalprice)));
		}
	}
	
	private void clearOrderPanel(){ 
	
		orderIDField.setText("");
		supplierNameComboBox.setSelectedIndex(0);
		supplierNameComboBox.setSelectedItem("Please Select");
		 
		productComboBox1.setSelectedIndex(0);
		productComboBox1.setSelectedItem("Please Select");
		productComboBox2.setSelectedIndex(0);
		productComboBox2.setSelectedItem("Please Select");
		productComboBox3.setSelectedIndex(0);
		productComboBox3.setSelectedItem("Please Select");
		productComboBox4.setSelectedIndex(0);
		productComboBox4.setSelectedItem("Please Select");
		
		quantityComboBox1.addItem("0");
		quantityComboBox1.setSelectedIndex(0);
		quantityComboBox1.setSelectedItem("0");
		quantityComboBox2.addItem("0");
		quantityComboBox2.setSelectedIndex(0);
		quantityComboBox2.setSelectedItem("0");
		quantityComboBox3.addItem("0");
		quantityComboBox3.setSelectedIndex(0);
		quantityComboBox3.setSelectedItem("0");
		quantityComboBox4.addItem("0");
		quantityComboBox4.setSelectedIndex(0);
		quantityComboBox4.setSelectedItem("0");
		
		unitPriceField1.setText("€0.00");
		unitPriceField2.setText("€0.00");
		unitPriceField3.setText("€0.00");
		unitPriceField4.setText("€0.00");
		
		priceField1.setText("€0.00");
		priceField2.setText("€0.00");
		priceField3.setText("€0.00");
		priceField4.setText("€0.00");
		
		totalPriceField.setText("€0.00");
		deliveryCostField.setText("€0.00");
		
		deliveryDaysComboBox.setSelectedIndex(0);
		quantityComboBox1.setSelectedItem("0");
	}
	
	private class OrderButtonListener implements ActionListener{
		
		private Database database;
		private JPanel tablePanel;
		
		private OrderButtonListener(Database database, JPanel tablePanel){
			
			this.database = database;
			this.tablePanel = tablePanel;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(orderSaveButton)){
				
				if((productComboBox1.getSelectedItem().equals("Please Select")||quantityComboBox1.getSelectedItem().equals("0")) &&
				   (productComboBox2.getSelectedItem().equals("Please Select")||quantityComboBox2.getSelectedItem().equals("0")) &&
				   (productComboBox3.getSelectedItem().equals("Please Select")||quantityComboBox3.getSelectedItem().equals("0")) &&
				   (productComboBox4.getSelectedItem().equals("Please Select")||quantityComboBox4.getSelectedItem().equals("0")) || 
				   supplierNameComboBox.getSelectedItem().equals("Please Select")|| deliveryDaysComboBox.getSelectedItem().equals("0")){
					
					JOptionPane.showMessageDialog(null,"Choose a Supplier, Delivery Days and a Product to Progress with Order", "Input Warning",JOptionPane.WARNING_MESSAGE);
				}
				else{
					
					System.out.println("Save button was clicked");
					String orderID = orderIDField.getText();
					Supplier supplier = database.getSupplierByName((String)supplierNameComboBox.getSelectedItem());
					String orderCost = totalPriceField.getText().substring(1, totalPriceField.getText().length());
					String orderDescription = commentTextArea.getText();
					ArrayList<Product> orderedproducts = new ArrayList<Product>();
					
					if(productComboBox1.getSelectedItem().equals("Please Select") == false||quantityComboBox1.getSelectedItem().equals("0") == false){
						
						String productName = (String)productComboBox1.getSelectedItem();
						String productType = database.getProductTypeByName(productName);
						String productQuantity = (String) quantityComboBox1.getSelectedItem();
						String productPrice = priceField1.getText();
						Product product1 = new Product(productName, productType, productPrice, productQuantity);
						orderedproducts.add(product1);
						
					}
					if(productComboBox2.getSelectedItem().equals("Please Select") == false||quantityComboBox2.getSelectedItem().equals("0") == false){
						
						String productName = (String)productComboBox2.getSelectedItem();
						String productType = database.getProductTypeByName(productName);
						String productQuantity = (String) quantityComboBox2.getSelectedItem();
						String productPrice = priceField2.getText();
						Product product2 = new Product(productName, productType, productPrice, productQuantity);
						orderedproducts.add(product2);
					}
					if(productComboBox3.getSelectedItem().equals("Please Select") == false||quantityComboBox3.getSelectedItem().equals("0") == false){
						
						String productName = (String)productComboBox3.getSelectedItem();
						String productType = database.getProductTypeByName(productName);
						String productQuantity = (String) quantityComboBox3.getSelectedItem();
						String productPrice = priceField3.getText();
						Product product3 = new Product(productName, productType, productPrice, productQuantity);
						orderedproducts.add(product3);
					}
					if(productComboBox4.getSelectedItem().equals("Please Select") == false||quantityComboBox4.getSelectedItem().equals("0") == false){
						
						String productName = (String)productComboBox4.getSelectedItem();
						String productType = database.getProductTypeByName(productName);
						String productQuantity = (String) quantityComboBox4.getSelectedItem();
						String productPrice = priceField4.getText();
						Product product4 = new Product(productName, productType, productPrice, productQuantity);
						orderedproducts.add(product4);
					}
					
					//clear order field
					clearOrderPanel();
					
					//add order to database
					database.addOrder(orderID, supplier, orderDate.toString(), deliveryDate, orderCost, true, orderedproducts, orderDescription);
					updateOrderID = true;
					
					//update table
					orderTableModel =  new DefaultTableModel();
					tableOfOrders.setModel(orderTableModel);
					//orderScrollPane = new JScrollPane(tableOfOrders);
					orderTableModel.setColumnIdentifiers(new String [] {"Order ID", "Delivery Date","Cost","Outstanding"});
					for(Order order : database.getOrders()){
						orderTableModel.addRow(new String[] {
								order.getOrderID(),
								order.getOrderDeliveryDate(),
								order.getOrderCost(),
								String.valueOf(order.isOrderOutstanding())});
					}
					
					//go back to table view
					tablePanel.setVisible(true);
					orderPanel.setVisible(false);
					System.out.println("order panel visible");
					
					
				} 
			}
			else if(e.getSource() == orderCancelButton){
				
				//go back to table view
				tablePanel.setVisible(true);
				orderPanel.setVisible(false);
				System.out.println("order panel visible");
			}
			else if(e.getSource() == orderClearButton){
				
					clearOrderPanel();
					updateOrderID = true;
			}
		}
	}
}
