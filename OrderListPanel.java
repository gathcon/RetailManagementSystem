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
  
import javax.swing.BorderFactory;
import javax.swing.JButton; 
import javax.swing.JComboBox; 
import javax.swing.JComponent; 
import javax.swing.JLabel; 
import javax.swing.JPanel; 
import javax.swing.JScrollPane; 
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
    private JTextField productField1, productField2, productField3, productField4; 
    private JTextField quantityField1, quantityField2, quantityField3, quantityField4; 
    private JTextField priceField1,  priceField2,  priceField3,  priceField4; 
    private JTextArea productTextArea;
      
    private JLabel filterLabel; 
    private JComboBox<String> filteredSelection; 
    private JTextField filterField; 
    private TableRowSorter<TableModel> sorter; 
    private String [] columnNames = {"Order ID", "Delivery Date","Cost","Outstanding"}; 
  
    private int filterIndex = 0; 
      
    public OrderListPanel() { 
        System.out.println("OrderListPanel created"); 
    } 
      
    public void setTabbedPane(JTabbedPane tabbedPane) { 
        this.tabbedPane = tabbedPane; 
    } 
          
    public void buildPanel(JPanel panel, final Database database) { 
          
        this.mainPanel = panel; 
        this.database = database; 
                  
        DefaultTableModel orderTableModel = new DefaultTableModel() { 
  
            @Override
            public boolean isCellEditable(int row, int column) { 
               return false; 
            } 
        }; 
          
        tableOfOrders = new JTable(orderTableModel);// JTABLE code 
        tableOfOrders.setModel(orderTableModel); 
        orderScrollPane = new JScrollPane(tableOfOrders); 
        orderTableModel.setColumnIdentifiers(columnNames); 
        for(Order order : database.getOrders()){ 
            orderTableModel.addRow(new String[] { 
                    order.getOrderID(), 
                    order.getOrderDeliveryDate(), 
                    order.getOrderCost(), 
                    String.valueOf(order.isOrderOutstanding())}); 
        } 
        tableOfOrders.setVisible(true);
                  
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
        filteredSelection = new JComboBox<String>(columnNames); 
        filteredSelection.setSelectedIndex(0); //default setting is InvoiceID 
        filteredSelection.addActionListener(this); 
        filterField = new JTextField(); 
          
        filterField.getDocument().addDocumentListener(new DocumentListener() { 
                public void changedUpdate(DocumentEvent e) { 
                        sorter = new TableRowSorter<TableModel>(tableOfOrders.getModel()); 
                        tableOfOrders.setRowSorter(sorter); 
                        newFilter(); 
                    } 
                    public void insertUpdate(DocumentEvent e) { 
                        sorter = new TableRowSorter<TableModel>(tableOfOrders.getModel()); 
                        tableOfOrders.setRowSorter(sorter); 
                        newFilter(); 
                    } 
                    public void removeUpdate(DocumentEvent e) { 
                        sorter = new TableRowSorter<TableModel>(tableOfOrders.getModel()); 
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
                else{ 
                    filterIndex = 3; 
                } 
            } 
              
        }); 
        productLabel = new JLabel("Product:"); 
        quantityLabel = new JLabel("Quantity:"); 
        priceLabel = new JLabel("Price:");
          
        tablePanel = new JPanel(); 
        newOrderPanel = new JPanel(); 
          
        newOrderPane = new CreateNewOrderUI(); 
        newOrderPane.buildPanel(newOrderPanel, tablePanel, database, tableOfOrders); 
          
        tablePanel.setLayout(new GridBagLayout()); 
        mainPanel.setLayout(new GridBagLayout());
        
        dynamicPanel = new JPanel();
        dynamicPanel.setLayout(new GridBagLayout()); 
        
        createConstraint(tablePanel, orderListLabel,    0, 0, 3, 1, 0, 10, 0, 0, 0, 0, 1, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH); 
        createConstraint(tablePanel, newOrderButton,    0, 1, 1, 1, 50, 0, 2, 20, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.NONE);
        createConstraint(tablePanel, filterLabel,       1, 1, 1, 1, 0, 0, 7, 45, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.NONE); 
        createConstraint(tablePanel, filteredSelection, 1, 1, 1, 1, 0, 0, 2, 100, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.NONE); 
        createConstraint(tablePanel, filterField,       1, 1, 1, 1, 100, 6, 2, 210, 2, 2, 0, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.NONE); 
        createConstraint(tablePanel, orderScrollPane,   0, 2, 3, 1, 0, 0, 2, 20, 2, 20, 1, 0.5, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH); 
        
        createConstraint(tablePanel, dynamicPanel,      0, 4, 3, 1, 0, 0, 20, 20, 20, 20, 1, 0.5, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH); 
          
        createConstraint(mainPanel, tablePanel,         0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 3, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH); 
        createConstraint(mainPanel, newOrderPanel,      0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH); 
        
        tableOfOrders.addMouseListener(new MouseAdapter(){ 
            public void mouseClicked(MouseEvent e){
            	
            	String selectedOrderID = tableOfOrders.getValueAt(tableOfOrders.getSelectedRow(), 0).toString();
            	
            	Order order = database.getOrderByID(selectedOrderID);
            	
            	dynamicPanel.removeAll();
            	
            	createConstraint(dynamicPanel, productLabel,      0, 0, 1, 1, 0, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH); 
                createConstraint(dynamicPanel, quantityLabel,     1, 0, 1, 1, 0, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH); 
                createConstraint(dynamicPanel, priceLabel,        2, 0, 1, 1, 0, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH); 
                
            	for(int i = 0; i < order.getProducts().size(); i++){
            		System.out.println(order.getProducts().get(i).getProductName());
            		
            		JLabel productNameLabel = new JLabel(order.getProducts().get(i).getProductName());
            		JLabel productQuantityLabel = new JLabel(order.getProducts().get(i).getProductQuantity());
            		JLabel productPriceLabel = new JLabel(String.valueOf(order.getProducts().get(i).getProductPrice()));
                    
                	createConstraint(dynamicPanel, productNameLabel,		0, i+1, 1, 1, 0, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
                	createConstraint(dynamicPanel, productQuantityLabel,	1, i+1, 1, 1, 0, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
                	createConstraint(dynamicPanel, productPriceLabel,		2, i+1, 1, 1, 0, 0, 2, 2, 2, 2, 0.3, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH);
            	}
            	
				mainPanel.validate();
				mainPanel.repaint();
            } 
        });
        
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
      
    private void newFilter() { 
           // RowFilter<? super TableModel, ? super Integer> rf = null; 
            RowFilter<TableModel, Object> rf = null; 
            //If current expression doesn't parse, don't update. 
            try { 
                rf = RowFilter.regexFilter(filterField.getText(), filterIndex ); 
                System.out.println("filtering   " + filterIndex + filterField.getText()); 
            } catch (java.util.regex.PatternSyntaxException e) { 
                return; 
            } 
            sorter.setRowFilter(rf); 
   } 
      
    public void actionPerformed(ActionEvent e) { 
          
        System.out.println(e.paramString()); 
          
        if(e.getActionCommand().equals("Create new order")) { 
              
            //go to create order view 
            tablePanel.setVisible(false); 
            newOrderPanel.setVisible(true);  
            System.out.println("order panel invisible"); 
              
            //update the comboBox lists 
            newOrderPane.updateComboBoxData(); 
              
            //disable tabs 
            tabbedPane.setEnabled(false); 
            newOrderPane.setTabbedPane(tabbedPane); 
            System.out.println("tabs disabled"); 
        } 
          
    } 
}