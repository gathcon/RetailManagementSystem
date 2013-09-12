package retailManagementSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MainUI implements ActionListener{
	
	private JFrame frame;
	
	private Database database;
	
	private JTabbedPane tabbedPane;
	
	private JPanel loginPanel;
	private JPanel bannerPanel;
	private JPanel inputPanel;
	
	private JPanel customerPanel;
	private JPanel supplierPanel;
	private JPanel productPanel;
	private JPanel orderPanel;
	private JPanel invoicePanel;
	private JPanel accountingPanel;
	private JPanel userAccountPanel;
	
	private CustomerListPanel customerPane;
	private SupplierListPanel supplierPane;
	private ProductListPanel productPane;
	private OrderListPanel orderPane;
	private InvoiceListPanel invoicePane;
	private AccountingPanel accountingPane;
	private UserAccountListPanel userAccountPane;
	
	private JLabel loginLabel;
	private JLabel userNameLabel;
	private JLabel passwordLabel;
	private JLabel errorLabel;
	
	private JButton loginButton;
	
	private JTextField userNameField;
	private JPasswordField passwordField;
		
	public MainUI() {
		
		frame = new JFrame();
		
		database = new Database();
				
		customerPanel = new JPanel();
		supplierPanel = new JPanel();
		productPanel = new JPanel();
		orderPanel = new JPanel();
		invoicePanel = new JPanel();
		accountingPanel = new JPanel();
		userAccountPanel = new JPanel();
				
		customerPane = new CustomerListPanel();
		supplierPane = new SupplierListPanel();
		productPane = new ProductListPanel();
		orderPane = new OrderListPanel();
		invoicePane = new InvoiceListPanel();
		accountingPane = new AccountingPanel();
		userAccountPane = new UserAccountListPanel();
		
		customerPane.buildPanel(customerPanel, database);
		supplierPane.buildPanel(supplierPanel, database);
		productPane.buildPanel(productPanel, database);
		orderPane.buildPanel(orderPanel, database, orderPane, accountingPane, productPane);
		invoicePane.buildPanel(invoicePanel, database, invoicePane, accountingPane, productPane);
		accountingPane.buildPanel(accountingPanel, database);
		userAccountPane.buildPanel(userAccountPanel, database);
		
		tabbedPane = new JTabbedPane();
		
		buildLoginPanel();
		
		frame.setTitle("Retail Management System | User Login");
		frame.setBackground(new Color(0,51,102));

		frame.setSize(300, 200);
		frame.setPreferredSize(new Dimension(800, 600));
		frame.setResizable(true);
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(loginPanel);
		frame.getRootPane().setDefaultButton(loginButton);
	   
		frame.validate(); 
		frame.repaint();
		frame.setVisible(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
		
	}
	
	private void buildLoginPanel(){
		
		loginPanel = new JPanel();
		bannerPanel = new JPanel();
		inputPanel = new JPanel();
		
		loginLabel = new JLabel("User Login", SwingConstants.CENTER);
		loginLabel.setOpaque(true);
		loginLabel.setForeground(Color.WHITE);
		loginLabel.setBackground(new Color(0,51,102));
		loginLabel.setFont(new Font("Helvetica", Font.BOLD , 20));
		
		errorLabel = new JLabel("!! Incorrect username or password", SwingConstants.CENTER);
		errorLabel.setForeground(Color.RED);
		errorLabel.setVisible(false);

		userNameLabel = new JLabel("User Name:", SwingConstants.RIGHT);
		passwordLabel = new JLabel("Password:", SwingConstants.RIGHT);
		
		userNameField = new JTextField();
		passwordField = new JPasswordField();
		
		loginButton = new JButton("Log in");
		loginButton.addActionListener(this);
						
		loginPanel.setLayout(new GridBagLayout());
		bannerPanel.setLayout(new GridBagLayout());
		inputPanel.setLayout(new GridBagLayout());
		
		createConstraint(bannerPanel, loginLabel, 		0, 0, 2, 1, 0	, 10, 0, 0, 0, 0, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		createConstraint(inputPanel, errorLabel, 		0, 0, 2, 1, 0	, 0, 20, 2, 2, 2, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		createConstraint(inputPanel, userNameLabel, 	0, 1, 1, 1, 0	, 0, 20, 2, 2, 2, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		createConstraint(inputPanel, userNameField, 	1, 1, 1, 1, 200	, 0, 20, 2, 2, 2, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		createConstraint(inputPanel, passwordLabel, 	0, 2, 1, 1, 0	, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		createConstraint(inputPanel, passwordField, 	1, 2, 1, 1, 200	, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		createConstraint(inputPanel, loginButton, 		0, 3, 2, 1, 0	, 0, 2, 2, 2, 2, 0, 0, GridBagConstraints.LINE_END, GridBagConstraints.NONE);
		
		createConstraint(loginPanel, bannerPanel, 		0, 0, 1, 1, 0	, 0, 0, 0, 0, 0, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		createConstraint(loginPanel, inputPanel, 		0, 1, 1, 1, 0	, 0, 0, 0, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
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
	
	public void actionPerformed(ActionEvent e){
		 if(e.getActionCommand() == "Log in") {
			 
			 //take in values
			 String userName = userNameField.getText();
			 char[] password = passwordField.getPassword();
			 
			 if(database.userAuthenticated(userName, password)) {
				 
				 int userLevel = database.getUserAccountByUserName(userName).getUserAccountUserLevel();
            	 
            	 if(userLevel == 0){
            		 
            		 tabbedPane.add("Customers", customerPanel);
            	     tabbedPane.add("Suppliers", supplierPanel);
            	     tabbedPane.add("Products", productPanel);
            	     tabbedPane.add("Orders", orderPanel);
            	     tabbedPane.add("Invoices", invoicePanel);
            	     tabbedPane.add("Accounting", accountingPanel);
            	     tabbedPane.add("User Accounts", userAccountPanel);
            	     
            	     orderPane.setTabbedPane(tabbedPane);
            	     invoicePane.setTabbedPane(tabbedPane);
            	     productPane.setTabbedPane(tabbedPane);

            	     frame.setTitle("Retail Management System");
            	     frame.setPreferredSize(new Dimension(800, 600));
            	     frame.getContentPane().removeAll();
            	     frame.getContentPane().add(tabbedPane);
            	     frame.validate();
            	     frame.repaint();
            	   
            	 }
            	 
            	 else if(userLevel == 1){
            		 
            		 tabbedPane.add("Customers", customerPanel);
            	     tabbedPane.add("Suppliers", supplierPanel);
            	     tabbedPane.add("Products", productPanel);
            	     tabbedPane.add("Orders", orderPanel);
            	     tabbedPane.add("Invoices", invoicePanel);
            	     tabbedPane.add("Accounting", accountingPanel);
            	     
            	     orderPane.setTabbedPane(tabbedPane);
            	     invoicePane.setTabbedPane(tabbedPane);
            	     productPane.setTabbedPane(tabbedPane);

            	     frame.setTitle("Retail Management System");
            	     frame.setPreferredSize(new Dimension(800, 600));
            	     frame.getContentPane().removeAll();
            	     frame.getContentPane().add(tabbedPane);
            	     frame.validate();
            	     frame.repaint();
            	 }
            	 
            	 else if(userLevel == 2){
            	
            		 tabbedPane.add("Invoices", invoicePanel);
            		 
            	     invoicePane.setTabbedPane(tabbedPane);

            		 frame.setTitle("Retail Management System");
            	     frame.setPreferredSize(new Dimension(800, 600));
            	     frame.getContentPane().removeAll();
            	     frame.getContentPane().add(tabbedPane);
            	     frame.validate();
            	     frame.repaint();
                }
            	else if(userLevel == 3) {

           	     	tabbedPane.add("Products", productPanel);

           	     	frame.setTitle("Retail Management System");
           	     	frame.setPreferredSize(new Dimension(800, 600));
           	     	frame.getContentPane().removeAll();
           	     	frame.getContentPane().add(tabbedPane);
           	     	frame.validate();
           	     	frame.repaint();
            	}
            }
			else {

				 errorLabel.setVisible(true);
				 
			}
			 
		}
    }

}
