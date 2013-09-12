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
  
public class OrderListPanel extends JPanel implements ActionListener{ 
      
    private Database database; 
      
    private JTabbedPane tabbedPane; 
      
    private DefaultTableModel orderTableModel; 
      
    private JTable tableOfOrders; 
      
    private JScrollPane orderScrollPane; 
      
    private JLabel orderListLabel; 
    private JLabel newOrderListLabel; 
      
    private JButton newOrderButton; 
      
    private JPanel tablePanel; 
    private JPanel newOrderPanel; 
    private JPanel mainPanel; 
    private JPanel dynamicPanel;
      
    private CreateNewOrderUI newOrderPane; 
      
    private JLabel productLabel, quantityLabel, priceLabel; 
      
    private JLabel filterLabel; 
    private JComboBox filteredSelection; 
    private JTextField filterField; 
    private TableRowSorter<TableModel> sorter; 
    private String [] columnNames = {"Order ID", "Order Date","Delivery Date","Cost","Outstanding",}; 
    private Object[] data;
    private JSplitPane splitPane;
  
    private int filterIndex = 0;
    private OrderListPanel orderPane;
    
    private ArrayList<JTextField> productFields  = new ArrayList<JTextField>();
    private ArrayList<JTextField> quantityFields = new ArrayList<JTextField>();
    private ArrayList<JTextField> priceFields    = new ArrayList<JTextField>();
	private static  int count1 = 0;
	
      
    public OrderListPanel() { 
    } 
      
    public void setTabbedPane(JTabbedPane tabbedPane) { 
        this.tabbedPane = tabbedPane; 
    } 
          
    public void buildPanel(JPanel panel, final Database database, OrderListPanel orderPane, AccountingPanel accountingPane, ProductListPanel productPane) { 
          
        this.mainPanel = panel; 
        this.database = database;
        this.orderPane = orderPane;
        
        dynamicPanel = new JPanel();
        //dynamicPanel.setBackground(Color.BLUE);
        dynamicPanel.setLayout(new GridBagLayout());
        
        final JScrollPane scrollDynamicPanel = new JScrollPane(dynamicPanel);
        scrollDynamicPanel.setAutoscrolls(true);
        scrollDynamicPanel.setVisible(true);
        
        dynamicPanel.setAutoscrolls(true);
        dynamicPanel.setVisible(true);
                  
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
          
        tableOfOrders = new JTable(orderTableModel);// JTABLE code 
        tableOfOrders.setModel(orderTableModel); 
        orderScrollPane = new JScrollPane(tableOfOrders); 
        orderTableModel.setColumnIdentifiers(columnNames); 
        int row = 0; 
        for(Order order : database.getOrders()){
			data = new Object[] {
					order.getOrderID(),
					order.getOrderDate(),
					order.getOrderDeliveryDate(),
					order.getOrderCost(),
					new Boolean(order.isOrderOutstanding())
					};
			orderTableModel.addRow(data);
			
			row++;
		}
        tableOfOrders.setVisible(true); 
        sorter = new TableRowSorter<TableModel>(tableOfOrders.getModel()); 
        sorter.setSortable(1, false);
        sorter.setSortable(2, false);
        sorter.setSortable(3, false);
        tableOfOrders.setRowSorter(sorter); 
          
        tableOfOrders.addMouseListener(new MouseAdapter(){ 
            public void mouseClicked(MouseEvent e){ 
                for(Order order : database.getOrders()){ 
                    if(order.getOrderID().equals(tableOfOrders.getValueAt(tableOfOrders.getSelectedRow(), 0).toString())){ 
                        resetTextFields(); 
                        
                        createConstraint(dynamicPanel, productLabel,      0, 0, 2, 1, 0, 0, 2, 20, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH); 
                        createConstraint(dynamicPanel, quantityLabel,     2, 0, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH); 
                        createConstraint(dynamicPanel, priceLabel,        3, 0, 1, 1, 0, 0, 2, 2, 2, 20, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);

                        for(int productInOrder=0; productInOrder < order.getProducts().size() ; productInOrder++){ 

	       	            	productFields.add(new JTextField());
	                    	quantityFields.add(new JTextField());
	       	            	priceFields.add(new JTextField());
	       	            	
	       	            	count1 =  count1 + 1;
							JTextField component = productFields.get(productFields.size()-1);
							component.setEditable(false);
							component.setBackground(new Color(255,255,220));
							component.setText(order.getProducts().get(productInOrder).getProductName());
							createConstraint(dynamicPanel, component, 	0, count1, 2, 1, 0, 0, 2, 20, 2, 2, 2, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
	                    	   
							JTextField component1 = quantityFields.get(quantityFields.size()-1);
							component1.setEditable(false);
							component1.setBackground(new Color(255,255,220));
							component1.setText(order.getProducts().get(productInOrder).getProductQuantity());
							createConstraint(dynamicPanel, component1, 	2, count1, 1, 1, 0, 0, 2, 2, 2, 2, 1, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
							
							JTextField component2 = priceFields.get(priceFields.size()-1);
							component2.setEditable(false);
							component2.setBackground(new Color(255,255,220));
							component2.setText(Double.toString(order.getProducts().get(productInOrder).getProductPrice()));
							createConstraint(dynamicPanel, component2, 	3, count1, 1, 1, 0, 0, 2, 2, 2, 20, 1, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
						
                        }
                        
                        JTextArea component3 = new JTextArea();
   					    component3.setEditable(false);
   					  	component3.setBackground(new Color(255,255,220));
   						component3.append(order.getOrderDescription());
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
                  
        orderListLabel = new JLabel("Order Control", SwingConstants.CENTER); 
        orderListLabel.setOpaque(true); 
        orderListLabel.setBackground(new Color(0,51,102)); 
        orderListLabel.setForeground(Color.WHITE); 
        orderListLabel.setFont(new Font("Helvetica", Font.BOLD, 20)); 
          
        newOrderListLabel = new JLabel("New Order", SwingConstants.CENTER); 
        newOrderListLabel.setOpaque(true); 
        newOrderListLabel.setBackground(new Color(0,51,102)); 
        newOrderListLabel.setForeground(Color.WHITE); 
        newOrderListLabel.setFont(new Font("Helvetica", Font.BOLD, 20)); 
                  
        newOrderButton = new JButton("Create new order"); 
        newOrderButton.addActionListener(this); 
        filterLabel = new JLabel("Filter by:"); 
        filteredSelection = new JComboBox(columnNames); 
        filteredSelection.setSelectedIndex(0); //default setting is InvoiceID 
        filteredSelection.addActionListener(this); 
        filterField = new JTextField(); 
          
        filterField.getDocument().addDocumentListener(new DocumentListener() { 
                public void changedUpdate(DocumentEvent e) { 
                        sorter = new TableRowSorter<TableModel>(tableOfOrders.getModel()); 
                        sorter.setSortable(1, false);
                        sorter.setSortable(2, false);
                        sorter.setSortable(3, false);
                        tableOfOrders.setRowSorter(sorter); 
                        newFilter(); 
                    } 
                    public void insertUpdate(DocumentEvent e) { 
                        sorter = new TableRowSorter<TableModel>(tableOfOrders.getModel()); 
                        sorter.setSortable(1, false);
                        sorter.setSortable(2, false);
                        sorter.setSortable(3, false);
                        tableOfOrders.setRowSorter(sorter); 
                        newFilter(); 
                    } 
                    public void removeUpdate(DocumentEvent e) { 
                        sorter = new TableRowSorter<TableModel>(tableOfOrders.getModel()); 
                        sorter.setSortable(1, false);
                        sorter.setSortable(2, false);
                        sorter.setSortable(3, false);
                        tableOfOrders.setRowSorter(sorter); 
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
        newOrderPanel = new JPanel(); 
          
        newOrderPane = new CreateNewOrderUI(); 
        newOrderPane.buildPanel(newOrderPanel, tablePanel, database, tableOfOrders, orderPane, accountingPane, productPane); 
          
        tablePanel.setLayout(new GridBagLayout()); 
        mainPanel.setLayout(new GridBagLayout());
        
              
       //Create a split pane with the two scroll panes in it.
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tablePanel, scrollDynamicPanel);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(300);
        
        createConstraint(tablePanel, orderListLabel,    0, 0, 3, 1, 0, 10, 0, 0, 0, 0, 1, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH); 
        createConstraint(tablePanel, newOrderButton,    0, 1, 1, 1, 50, 0, 2, 20, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.NONE); 
        createConstraint(tablePanel, orderScrollPane,   0, 2, 3, 1, 0, 0, 2, 20, 2, 20, 1, 0.5, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH); 
        createConstraint(tablePanel, filterLabel,       1, 1, 1, 4, 0, 0, 7, 45, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.NONE); 
        createConstraint(tablePanel, filteredSelection, 1, 1, 1, 1, 0, 0, 2, 100, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.NONE); 
        createConstraint(tablePanel, filterField,       1, 1, 2, 1, 192, 6, 2, 210, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.NONE); 
       
        createConstraint(mainPanel, splitPane,         0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 3, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH); 
        createConstraint(mainPanel, newOrderPanel,     0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH); 
          
        newOrderPanel.setVisible(false); 
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
           // RowFilter<? super TableModel, ? super Integer> rf = null; 
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
      
    public void actionPerformed(ActionEvent e) { 
                    
        if(e.getActionCommand().equals("Create new order")) { 
              
            //go to create order view 
            tablePanel.setVisible(false);
            tablePanel.invalidate();
            dynamicPanel.setVisible(false);
            dynamicPanel.invalidate();
            splitPane.setVisible(false);
            splitPane.invalidate();
            
            newOrderPanel.setVisible(true);
            newOrderPanel.validate();
            mainPanel.repaint();
              
            //update the comboBox lists 
            newOrderPane.updateComboBoxData(); 
              
            //disable tabs 
            tabbedPane.setEnabled(false); 
            newOrderPane.setTabbedPane(tabbedPane); 
        } 
          
    }

	public JTable getTableOfOrders() {
		return tableOfOrders;
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
  
}