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
  

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton; 
import javax.swing.JComboBox; 
import javax.swing.JComponent; 
import javax.swing.JLabel; 
import javax.swing.JPanel; 
import javax.swing.JScrollPane; 
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane; 
import javax.swing.JTable; 
import javax.swing.JTextArea;
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
      
    private DefaultTableModel invoiceTableModel; 
      
    private JTable tableOfInvoices; 
      
    private JScrollPane invoiceScrollPane; 
      
    private JLabel invoiceListLabel; 
    private JLabel newInvoiceListLabel; 
      
    private JButton newInvoiceButton; 
      
    private JPanel tablePanel; 
    private JPanel newInvoicePanel; 
    private JPanel mainPanel; 
    private JPanel dynamicPanel;
      
    private CreateNewInvoiceUI newInvoicePane; 
      
    private JLabel productLabel, quantityLabel, priceLabel; 
      
    private JLabel filterLabel; 
    private JComboBox filteredSelection; 
    private JTextField filterField; 
    private TableRowSorter<TableModel> sorter; 
    private String [] columnNames = {"Order ID", "Invoice Date","Delivery Date","Cost","Outstanding",}; 
    private Object[] data;
    private JSplitPane splitPane;
  
    private int filterIndex = 0;
    private InvoiceListPanel invoicePane;
    
    private ArrayList<JTextField> productFields  = new ArrayList<JTextField>();
    private ArrayList<JTextField> quantityFields = new ArrayList<JTextField>();
    private ArrayList<JTextField> priceFields    = new ArrayList<JTextField>();
	private static  int count1 = 0;
	
      
    public InvoiceListPanel() { 
    } 
      
     public void setTabbedPane(JTabbedPane tabbedPane) { 
        this.tabbedPane = tabbedPane; 
    } 
          
    public void buildPanel(JPanel panel, final Database database, InvoiceListPanel invoicePane, AccountingPanel accountingPane, ProductListPanel productPane) { 
          
        this.mainPanel = panel; 
        this.database = database;
        this.invoicePane = invoicePane;
        
        
        dynamicPanel = new JPanel();
        dynamicPanel.setLayout(new GridBagLayout());
        
        final JScrollPane scrollDynamicPanel = new JScrollPane(dynamicPanel);
        scrollDynamicPanel.setAutoscrolls(true);
        scrollDynamicPanel.setVisible(true);
        
        dynamicPanel.setAutoscrolls(true);
        dynamicPanel.setVisible(true);
                  
        DefaultTableModel invoiceTableModel = new DefaultTableModel() {

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
          
        tableOfInvoices = new JTable(invoiceTableModel);// JTABLE code 
        tableOfInvoices.setModel(invoiceTableModel); 
        invoiceScrollPane = new JScrollPane(tableOfInvoices); 
        invoiceTableModel.setColumnIdentifiers(columnNames); 
        int row = 0; 
        for(Invoice invoice : database.getInvoices()){
			data = new Object[] {
					invoice.getInvoiceID(),
					invoice.getInvoiceDate(),
					invoice.getInvoiceDeliveryDate(),
					invoice.getInvoiceCost(),
					new Boolean(invoice.isInvoiceOutstanding())
					};
			invoiceTableModel.addRow(data);
			
			row++;
		}
        tableOfInvoices.setVisible(true); 
        sorter = new TableRowSorter<TableModel>(tableOfInvoices.getModel()); 
        sorter.setSortable(1, false);
        sorter.setSortable(2, false);
        sorter.setSortable(3, false);
        tableOfInvoices.setRowSorter(sorter); 
          
        tableOfInvoices.addMouseListener(new MouseAdapter(){ 
            public void mouseClicked(MouseEvent e){ 
                for(Invoice invoice : database.getInvoices()){ 
                    if(invoice.getInvoiceID().equals(tableOfInvoices.getValueAt(tableOfInvoices.getSelectedRow(), 0).toString())){
                    	resetTextFields(); 
                        
                    	createConstraint(dynamicPanel, productLabel,      0, 0, 2, 1, 0, 0, 2, 20, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH); 
                        createConstraint(dynamicPanel, quantityLabel,     2, 0, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH); 
                        createConstraint(dynamicPanel, priceLabel,        3, 0, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
                        
                    	for(int productInInvoice=0; productInInvoice < invoice.getProducts().size() ; productInInvoice++){ 

                    		productFields.add(new JTextField());
                    		quantityFields.add(new JTextField());
                    		priceFields.add(new JTextField());
       	            	
                    		count1 =  count1 + 1;
                    		JTextField component = productFields.get(productFields.size()-1);
                    		component.setEditable(false);
                    		component.setBackground(new Color(255,255,220));
                    		component.setText(invoice.getProducts().get(productInInvoice).getProductName());
                    		createConstraint(dynamicPanel, component, 	0, count1, 2, 1, 0, 0, 2, 20, 2, 2, 2, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
                    	   
							JTextField component1 = quantityFields.get(quantityFields.size()-1);
							component1.setEditable(false);
							component1.setBackground(new Color(255,255,220));
							component1.setText(invoice.getProducts().get(productInInvoice).getProductQuantity());
							createConstraint(dynamicPanel, component1, 	2, count1, 1, 1, 0, 0, 2, 2, 2, 2, 1, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
							
							JTextField component2 = priceFields.get(priceFields.size()-1);
							component2.setEditable(false);
							component2.setBackground(new Color(255,255,220));
							component2.setText(Double.toString(invoice.getProducts().get(productInInvoice).getProductPrice()));
							createConstraint(dynamicPanel, component2, 	3, count1, 1, 1, 0, 0, 2, 2, 2, 20, 1, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
							
							dynamicPanel.setVisible(true);
							dynamicPanel.validate();
							dynamicPanel.repaint();
							scrollDynamicPanel.setVisible(true);
							scrollDynamicPanel.validate();
							scrollDynamicPanel.repaint();
                      }
                      
                       	JTextArea component3 = new JTextArea();
  					    component3.setEditable(false);
  					  	component3.setBackground(new Color(255,255,220));
  						component3.append(invoice.getComment());
  						component3.setBorder(BorderFactory.createLoweredBevelBorder());
  					    createConstraint(dynamicPanel, component3, 	0, count1 + 1, 4, 2, 0, 40, 2, 20, 2, 20, 1, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
                       
  						dynamicPanel.setVisible(true);
						dynamicPanel.validate();
						dynamicPanel.repaint();
						scrollDynamicPanel.setVisible(true);
						scrollDynamicPanel.validate();
						scrollDynamicPanel.repaint();
  					    
  					    count1 = 0;
                    } 
                } 
            } 
        }); 
                  
        invoiceListLabel = new JLabel("Invoice Control", SwingConstants.CENTER); 
        invoiceListLabel.setOpaque(true); 
        invoiceListLabel.setBackground(new Color(0,51,102)); 
        invoiceListLabel.setForeground(Color.WHITE); 
        invoiceListLabel.setFont(new Font("Helvetica", Font.BOLD, 20)); 
          
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
                        sorter.setSortable(1, false);
                        sorter.setSortable(2, false);
                        sorter.setSortable(3, false);
                        tableOfInvoices.setRowSorter(sorter); 
                        newFilter(); 
                    } 
                    public void insertUpdate(DocumentEvent e) { 
                        sorter = new TableRowSorter<TableModel>(tableOfInvoices.getModel()); 
                        sorter.setSortable(1, false);
                        sorter.setSortable(2, false);
                        sorter.setSortable(3, false);
                        tableOfInvoices.setRowSorter(sorter); 
                        newFilter(); 
                    } 
                    public void removeUpdate(DocumentEvent e) { 
                        sorter = new TableRowSorter<TableModel>(tableOfInvoices.getModel()); 
                        sorter.setSortable(1, false);
                        sorter.setSortable(2, false);
                        sorter.setSortable(3, false);
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
                else if(i == 3){ 
                    filterIndex = 3; 
                } 
                else{
                	filterIndex = 4;
                }
            } 
              
        }); 
        productLabel = new JLabel("Product: "); 
        quantityLabel = new JLabel("Quantity: "); 
        priceLabel = new JLabel("Price: "); 
          
          
        tablePanel = new JPanel(); 
        newInvoicePanel = new JPanel(); 
          
        newInvoicePane = new CreateNewInvoiceUI(); 
        newInvoicePane.buildPanel(newInvoicePanel, tablePanel, database, tableOfInvoices, invoicePane, accountingPane, productPane); 
          
        tablePanel.setLayout(new GridBagLayout()); 
        mainPanel.setLayout(new GridBagLayout());
        
              
       //Create a split pane with the two scroll panes in it.
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tablePanel, scrollDynamicPanel);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(300);
        
        createConstraint(tablePanel, invoiceListLabel,    0, 0, 3, 1, 0, 10, 0, 0, 0, 0, 1, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH); 
        createConstraint(tablePanel, newInvoiceButton,    0, 1, 1, 1, 41, 0, 2, 20, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.NONE); 
        createConstraint(tablePanel, invoiceScrollPane,   0, 2, 3, 1, 0, 0, 2, 20, 2, 20, 1, 0.5, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH); 
        createConstraint(tablePanel, filterLabel,       1, 1, 1, 4, 0, 0, 7, 45, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.NONE); 
        createConstraint(tablePanel, filteredSelection, 1, 1, 1, 1, 0, 0, 2, 100, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.NONE); 
        createConstraint(tablePanel, filterField,       1, 1, 1, 1, 192, 6, 2, 210, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.NONE); 
       
        createConstraint(mainPanel, splitPane,         0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 3, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH); 
        createConstraint(mainPanel, newInvoicePanel,      0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH); 
          
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
    	
    	for(int i = 0; i < productFields.size(); i++){
			productFields.remove(i);
			quantityFields.remove(i);
			priceFields.remove(i);
		}
    	
    	dynamicPanel.removeAll();
		dynamicPanel.setVisible(true);
		dynamicPanel.validate();
		dynamicPanel.repaint();
    } 
      
    private void newFilter() { 
    	
            RowFilter<TableModel, Object> rf = null; 
            //If current expression doesn't parse, don't update. 
            try { 
                 resetTextFields(); 
                rf = RowFilter.regexFilter(filterField.getText(), filterIndex ); 
            } catch (java.util.regex.PatternSyntaxException e) { 
                return; 
            } 
            sorter.setRowFilter(rf); 
    }

	public JTable getTableOfInvoices() {
		return tableOfInvoices;
	}

	public JPanel getTablePanel() {
		return tablePanel;
	}

	public JPanel getDynamicPanel() {
		return dynamicPanel;
	}

	public JSplitPane getSplitPane() {
		return splitPane;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	      if(e.getSource().equals(newInvoiceButton)) { 
              
	            //go to create Invoice view 
	            tablePanel.setVisible(false);
	            tablePanel.invalidate();
	            dynamicPanel.setVisible(false);
	            dynamicPanel.invalidate();
	            splitPane.setVisible(false);
	            splitPane.invalidate();
	            
	            newInvoicePanel.setVisible(true);
	            newInvoicePanel.validate();
	            mainPanel.repaint();
	              
	            //update the comboBox lists 
	            newInvoicePane.updateComboBoxData(); 
	              
	            //disable tabs 
	            tabbedPane.setEnabled(false); 
	            newInvoicePane.setTabbedPane(tabbedPane); 
	        } 
		
	}
  
}