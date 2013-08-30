package retailManagementSystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class InvoiceListPanel extends JPanel implements ActionListener{
	
	private Database database;
	
	private JTabbedPane tabbedPane;
	
	private DefaultTableModel InvoiceTableModel;
	
	private JTable tableOfInvoices;
	
	private JScrollPane InvoiceScrollPane;
	
	private JLabel InvoiceListLabel;
	private JLabel newInvoiceListLabel;
	
	private JButton newInvoiceButton;
	
	private JPanel tablePanel;
	private JPanel newInvoicePanel;
	private JPanel mainPanel;
	
	//private CreateNewInvoiceUI newInvoicePane;
	
	private JLabel productLabel, quantityLabel, priceLabel;
	private JTextField productField1, productField2, productField3, productField4;
	private JTextField quantityField1, quantityField2, quantityField3, quantityField4;
	private JTextField priceField1,  priceField2,  priceField3,  priceField4;
	
	private JLabel filterLabel;
	private JComboBox filteredSelection;
	private JTextField filterField;
	private TableRowSorter<TableModel> sorter;
	private String [] columnNames = {"Invoice ID", "Delivery Date","Cost","Outstanding"};

	private int filterIndex = 0;
	
	public InvoiceListPanel() {
		System.out.println("InvoiceListPanel created");
	}
	
	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}
		
	public void buildPanel(JPanel panel, final Database database) {
		
		this.mainPanel = panel;
		this.database = database;
				
		InvoiceTableModel =  new DefaultTableModel();
		tableOfInvoices = new JTable(InvoiceTableModel);// JTABLE code
		tableOfInvoices.setModel(InvoiceTableModel);
		InvoiceScrollPane = new JScrollPane(tableOfInvoices);
		InvoiceTableModel.setColumnIdentifiers(columnNames);
		int row = 0;
		for(Invoice Invoice : database.getInvoices()){
			InvoiceTableModel.addRow(new String[] {
					Invoice.getInvoiceID(),
					Invoice.getInvoiceDeliveryDate(),
					Invoice.getInvoiceCost(),
					String.valueOf(Invoice.isInvoiceOutstanding())});
			row++;
		}
		tableOfInvoices.setVisible(true);
		
		tableOfInvoices.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				for(Invoice Invoice : database.getInvoices()){
					if(Invoice.getInvoiceID().equals(tableOfInvoices.getValueAt(tableOfInvoices.getSelectedRow(), 0).toString())){
						resetTextFields();
						for(int productInInvoice=0; productInInvoice < Invoice.getProducts().size(); productInInvoice++){
							if(productInInvoice == 0){
								productField1.setText(Invoice.getProducts().get(productInInvoice).getProductName());
								quantityField1.setText(Invoice.getProducts().get(productInInvoice).getProductQuantity());
								priceField1.setText(String.valueOf(Invoice.getProducts().get(productInInvoice).getProductPrice()));
							}
							if(productInInvoice == 1){
								productField2.setText(Invoice.getProducts().get(productInInvoice).getProductName());
								quantityField2.setText(Invoice.getProducts().get(productInInvoice).getProductQuantity());
								priceField2.setText(String.valueOf(Invoice.getProducts().get(productInInvoice).getProductPrice()));
							}
							if(productInInvoice == 2){
								productField3.setText(Invoice.getProducts().get(productInInvoice).getProductName());
								quantityField3.setText(Invoice.getProducts().get(productInInvoice).getProductQuantity());
								priceField3.setText(String.valueOf(Invoice.getProducts().get(productInInvoice).getProductPrice()));
							}
							if(productInInvoice == 3){
								productField4.setText(Invoice.getProducts().get(productInInvoice).getProductName());
								quantityField4.setText(Invoice.getProducts().get(productInInvoice).getProductQuantity());
								priceField4.setText(String.valueOf(Invoice.getProducts().get(productInInvoice).getProductPrice()));
							}
						}
					}
				}
			}
		});
				
		InvoiceListLabel = new JLabel("Invoice Control", SwingConstants.CENTER);
		InvoiceListLabel.setOpaque(true);
		InvoiceListLabel.setBackground(new Color(0,51,102));
		InvoiceListLabel.setForeground(Color.WHITE);
		InvoiceListLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
		
		newInvoiceListLabel = new JLabel("New Invoice", SwingConstants.CENTER);
		newInvoiceListLabel.setOpaque(true);
		newInvoiceListLabel.setBackground(new Color(0,51,102));
		newInvoiceListLabel.setForeground(Color.WHITE);
		newInvoiceListLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
				
		newInvoiceButton = new JButton("Create new Invoice");
		newInvoiceButton.addActionListener(this);
		filterLabel = new JLabel("Filter by:");
		filteredSelection = new JComboBox(columnNames);
		filteredSelection.setSelectedIndex(0); //default setting is InvoiceID
		filteredSelection.addActionListener(this);
		filterField = new JTextField();
		
		filterField.getDocument().addDocumentListener(new DocumentListener() {
		    	public void changedUpdate(DocumentEvent e) {
			    		sorter = new TableRowSorter<TableModel>(tableOfInvoices.getModel());
			    		tableOfInvoices.setRowSorter(sorter);
			    		newFilter();
		            }
		            public void insertUpdate(DocumentEvent e) {
		            	sorter = new TableRowSorter<TableModel>(tableOfInvoices.getModel());
		            	tableOfInvoices.setRowSorter(sorter);
		            	newFilter();
		            }
		            public void removeUpdate(DocumentEvent e) {
		            	sorter = new TableRowSorter<TableModel>(tableOfInvoices.getModel());
		            	tableOfInvoices.setRowSorter(sorter);
		                newFilter();
		            }
		 });
		
		filteredSelection.addActionListener(new ActionListener(){ // code when drop down menu is changed

			public void actionPerformed(ActionEvent e) {
				int i = filteredSelection.getSelectedIndex();
				if(i == 0 ){
					filterIndex = 0;
				}
				else if(i == 1){
					filterIndex = 1;
				}
				else if(i == 2){
					filterIndex = 2;
				}
				else{
					filterIndex = 3;
				}
			}
			
		});
		productLabel = new JLabel("Product: ");
		quantityLabel = new JLabel("Quantity: ");
		priceLabel = new JLabel("Price: ");
		
	    productField1 = new JTextField();
	    productField1.setEditable(false);
	    productField1.setBackground(Color.WHITE);
	    productField2 = new JTextField();
	    productField2.setEditable(false);
	    productField2.setBackground(Color.WHITE);
	    productField3 = new JTextField();
	    productField3.setEditable(false);
	    productField3.setBackground(Color.WHITE);
	    productField4 = new JTextField();
	    productField4.setEditable(false);
	    productField4.setBackground(Color.WHITE);
	    
		quantityField1 = new JTextField();
		quantityField1.setEditable(false);
		quantityField1.setBackground(Color.WHITE);
		quantityField2 = new JTextField();
		quantityField2.setEditable(false);
		quantityField2.setBackground(Color.WHITE);
		quantityField3 = new JTextField();
		quantityField3.setEditable(false);
		quantityField3.setBackground(Color.WHITE);
		quantityField4 = new JTextField();
		quantityField4.setEditable(false);
		quantityField4.setBackground(Color.WHITE);
		
		priceField1 = new JTextField();
		priceField1.setEditable(false);
		priceField1.setBackground(Color.WHITE);
		priceField2 = new JTextField();
		priceField2.setEditable(false);
		priceField2.setBackground(Color.WHITE);
		priceField3 = new JTextField();
		priceField3.setEditable(false);
		priceField3.setBackground(Color.WHITE);
		priceField4 = new JTextField();
		priceField4.setEditable(false);
		priceField4.setBackground(Color.WHITE);
		
		tablePanel = new JPanel();
		newInvoicePanel = new JPanel();
		
		//newInvoicePane = new CreateNewInvoiceUI();
		//newInvoicePane.buildPanel(newInvoicePanel, tablePanel, database, tableOfInvoices);
		
		tablePanel.setLayout(new GridBagLayout());
		mainPanel.setLayout(new GridBagLayout());
		
		createConstraint(tablePanel, InvoiceListLabel,	0, 0, 3, 1, 0, 10, 0, 0, 0, 0, 1, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(tablePanel, newInvoiceButton, 	0, 1, 1, 1, 41, 0, 2, 20, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.NONE);
		createConstraint(tablePanel, InvoiceScrollPane, 0, 2, 3, 1, 0, 0, 2, 20, 2, 20, 1, 0.5, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(tablePanel, filterLabel, 		1, 1, 1, 4, 0, 0, 7, 45, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.NONE);
		createConstraint(tablePanel, filteredSelection, 1, 1, 1, 1, 0, 0, 2, 100, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.NONE);
		createConstraint(tablePanel, filterField, 		1, 1, 1, 1, 100, 6, 2, 210, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.NONE);

		
		createConstraint(tablePanel, productLabel, 		0, 3, 1, 1, 0, 0, 2, 20, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(tablePanel, quantityLabel, 	1, 3, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(tablePanel, priceLabel, 		2, 3, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		
		createConstraint(tablePanel, productField1, 	0, 4, 1, 1, 0, 0, 2, 20, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(tablePanel, productField2, 	0, 5, 1, 1, 0, 0, 2, 20, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(tablePanel, productField3, 	0, 6, 1, 1, 0, 0, 2, 20, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(tablePanel, productField4, 	0, 7, 1, 1, 0, 0, 2, 20, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		
		createConstraint(tablePanel, quantityField1, 	1, 4, 1, 1, 0, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(tablePanel, quantityField2, 	1, 5, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(tablePanel, quantityField3, 	1, 6, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(tablePanel, quantityField4, 	1, 7, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		
		createConstraint(tablePanel, priceField1, 		2, 4, 1, 1, 0, 0, 2, 2, 2, 20, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(tablePanel, priceField2, 		2, 5, 1, 1, 0, 0, 2, 2, 2, 20, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(tablePanel, priceField3, 		2, 6, 1, 1, 0, 0, 2, 2, 2, 20, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(tablePanel, priceField4, 		2, 7, 1, 1, 0, 0, 2, 2, 2, 20, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		
		createConstraint(mainPanel, tablePanel,			0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		createConstraint(mainPanel, newInvoicePanel,		0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
		
		newInvoicePanel.setVisible(false);
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
	
	public void resetTextFields() {
		productField1.setText("");
		quantityField1.setText("");
		priceField1.setText("");
		productField2.setText("");
		quantityField2.setText("");
		priceField2.setText("");
		productField3.setText("");
		quantityField3.setText("");
		priceField3.setText("");
		productField4.setText("");
		quantityField4.setText("");
		priceField4.setText("");
	}
	
	private void newFilter() {
	       // RowFilter<? super TableModel, ? super Integer> rf = null;
			RowFilter<TableModel, Object> rf = null;
	        //If current expression doesn't parse, don't update.
	        try {
	        	 resetTextFields();
	            rf = RowFilter.regexFilter(filterField.getText(), filterIndex );
	            System.out.println("filtering   " + filterIndex + filterField.getText());
	        } catch (java.util.regex.PatternSyntaxException e) {
	            return;
	        }
	        sorter.setRowFilter(rf);
	    }
	
	public void actionPerformed(ActionEvent e) {
		
		System.out.println(e.paramString());
		
		if(e.getActionCommand().equals("Create new Invoice")) {
			
			//go to create Invoice view
			tablePanel.setVisible(false);
			newInvoicePanel.setVisible(true); 
			System.out.println("Invoice panel invisible");
			
			//update the comboBox lists
			//newInvoicePane.updateComboBoxData();
			
			//disable tabs
			tabbedPane.setEnabled(false);
			//newInvoicePane.setTabbedPane(tabbedPane);
			System.out.println("tabs disabled");
		}
		
	}
}