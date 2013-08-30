package retailManagementSystem;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


public class AccountingPanel extends JPanel{

	private Database database;

	private JTable tableOfAccounts;
	private DefaultTableModel model;
	
	private void createConstraint(JPanel panel, JComponent component,
			int gridx, int gridy, int width, int height, int ipadx, int ipady,
			int top, int left, int bottom, int right, double weightx,
			double weighty) {
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
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.fill = GridBagConstraints.BOTH;
		panel.add(component, constraints);
	}
	
	
	public void buildPanel(JPanel panel, final Database db){
		this.database = db;

		DecimalFormat df = new DecimalFormat("#.##"); 
		double totalOrderCost=0;
		double totalInvoiceCost=0;
		tableOfAccounts = new JTable();// JTABLE code
		model =  new DefaultTableModel();
		tableOfAccounts.setModel(model);
		JScrollPane scrollPane = new JScrollPane(tableOfAccounts);
		model.setColumnIdentifiers(new String [] {"Order/Invoice ID", "Supplier/Customer Name","Cost"});
		model.addRow(new String[]{"Order ID","Supplier Name","Cost"});
		for(Order p: database.getOrders()){
			int row = 0;
			if (row == 0){
				setBackground(Color.RED);
			}
			model.addRow(new String[]{p.getOrderID(),p.getSupplier().getSupplierName(),p.getOrderCost()});
			
			totalOrderCost = Double.valueOf(df.format(totalOrderCost+Double.parseDouble(p.getOrderCost())));
			
		}
		String totalOrderCostS = Double.toString(totalOrderCost);
		model.addRow(new String[]{"Total Order Cost",null,totalOrderCostS});
		model.addRow(new String[]{});
		model.addRow(new String[]{"Invoice ID","Customer Name","Cost"});
		for(Invoice p: database.getInvoices()){
			setBackground(Color.GREEN);
			model.addRow(new String[]{p.getInvoiceID(),p.getCustomer().getCustomerName(),p.getInvoiceCost()});
			
			totalInvoiceCost = Double.valueOf(df.format(totalInvoiceCost+Double.parseDouble(p.getInvoiceCost())));
		}
		String totalInvoiceCostS = Double.toString(totalInvoiceCost);
		model.addRow(new String[]{"Total Invoice Cost",null,totalInvoiceCostS});
		Double total = Double.valueOf(df.format(totalInvoiceCost - totalOrderCost));
		String totalS = Double.toString(total);
		model.addRow(new String[]{});
	
		model.addRow(new String[]{"Total",null,totalS});
		
		
		panel.setLayout(new GridBagLayout());

		createConstraint(panel, tableOfAccounts.getTableHeader(),	0, 0, 5, 1, 0, 0, 2, 2, 2, 2, 1, 1);
		createConstraint(panel, scrollPane, 						0, 1, 5, 1, 0, 0, 2, 2, 2, 2, 1, 7);
	}	
}

