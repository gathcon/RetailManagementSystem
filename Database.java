package retailManagementSystem; 
  
import java.text.SimpleDateFormat;
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.table.DefaultTableModel;
  
public class Database { 
      
    private ArrayList<Customer> customers = new ArrayList<Customer>(); 
    private ArrayList<Supplier> suppliers = new ArrayList<Supplier>(); 
    private ArrayList<Product> products = new ArrayList<Product>();
    private ArrayList<Order> orders = new ArrayList<Order>();
	private ArrayList<Invoice> invoices = new ArrayList<Invoice>();
    private ArrayList<UserAccount> userAccounts = new ArrayList<UserAccount>();
    
    private ArrayList<Product> invoiceProducts = new ArrayList<Product>();
    
     
    /*
     * database constructor
     */
    public Database() {
    	
//    	int [] stockLevelsIpod = {120,130,110,90,70,110,140,120,95,75,100,100};
//        int [] stockLevelsEnvy = {40,50,30,70,80,40,50,30,50,60,70,40};
//        int [] stockLevelsGalaxy = {75,80,60,55,60,40,50,30,75,60,50,75};
        
        int [] stockLevelsIpod = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int [] stockLevelsEnvy = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int [] stockLevelsGalaxy = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};  
          
        this.addCustomer("09118553", "Conor Gath", "conor@gmail.com", "1092736492", "Meath");
        this.addCustomer("SIXC2938", "Joseph Uman", "joe@gmail.com", "3759264367", "Dublin");
        this.addCustomer("DU38F8E9", "Ian Murray", "ian@gmail.com", "2579874356", "Louth");
        this.addCustomer("02MMUH39", "Muireann Walsh", "muireann@gmail.com", "1234567890", "Wicklow");
        this.addCustomer("AO9U8NNE", "James Mc Manus", "james@gmail.com", "0987654321", "Cork");
          
        this.addSupplier("09118553", "John", "john@gmail.com", "1092736492", "Meath"); 
        this.addSupplier("SIXC2938", "Mary", "mary@gmail.com", "3759264367", "Dublin"); 
        this.addSupplier("DU38F8E9", "Jack", "jack@gmail.com", "2579874356", "Louth"); 
        this.addSupplier("02MMUH39", "Sean", "sean@gmail.com", "1234567890", "Wicklow"); 
        this.addSupplier("AO9U8NNE", "Bill", "bill@gmail.com", "0987654321", "Cork");
        
        this.addProduct("iPod", "mp3", "100", 330, "AP0001", stockLevelsIpod);
        this.addProduct("HP Envy", "Laptop", "40", 550, "HP0001", stockLevelsEnvy);
        this.addProduct("Samsung Galaxy", "Phone", "75", 400, "SM0001", stockLevelsGalaxy);
        
        this.addUserAccount("Test", "Test", "1", "1".toCharArray(), 0);
        this.addUserAccount("admin", "admin", "Administrator", "admin".toCharArray(), 0);
        this.addUserAccount("General", "Manager", "Manager", "1234".toCharArray(), 1);
        this.addUserAccount("Teller", "Teller", "Teller", "1234".toCharArray(), 2);
        this.addUserAccount("Stock", "Checker", "Stock Checker", "1234".toCharArray(), 3);
        

        this.addOrder("0001", suppliers.get(0), "02/01/13", "04/01/13", "300", false, products, "comment");
        this.addOrder("0002", suppliers.get(1), "06/02/13", "08/02/13", "1267", false, products, "comment");
        this.addOrder("0003", suppliers.get(2), "10/03/13", "12/03/13", "4775", false, products, "comment");
        this.addOrder("0004", suppliers.get(3), "14/04/13", "16/04/13", "3568", false, products, "comment");
        this.addOrder("0005", suppliers.get(4), "18/05/13", "20/05/13", "625", false, products, "comment");
        this.addOrder("0006", suppliers.get(0), "02/06/13", "04/06/13", "300", false, products, "comment");
        this.addOrder("0007", suppliers.get(1), "06/08/13", "08/08/13", "1267", false, products, "comment");
        this.addOrder("0008", suppliers.get(2), "10/03/13", "12/03/13", "4775", true, products, "comment");
        this.addOrder("0009", suppliers.get(3), "14/04/13", "16/04/13", "3568", true, products, "comment");
        this.addOrder("0010", suppliers.get(4), "18/02/13", "20/02/13", "625", true, products, "comment");
//        this.addOrder("0011", suppliers.get(0), "2/8/13", "4/8/13", "300", true, products, "comment");
//        this.addOrder("0012", suppliers.get(1), "6/8/13", "8/8/13", "1267", true, products, "comment");
//        this.addOrder("0013", suppliers.get(2), "10/8/13", "12/8/13", "4775", true, products, "comment");
//        this.addOrder("0014", suppliers.get(3), "14/8/13", "16/8/13", "3568", true, products, "comment");
//        this.addOrder("0015", suppliers.get(4), "18/8/13", "20/8/13", "625", true, products, "comment");
//        this.addOrder("0016", suppliers.get(0), "2/8/13", "4/8/13", "300", true, products, "comment");
//        this.addOrder("0017", suppliers.get(1), "6/8/13", "8/8/13", "1267", true, products, "comment");
//        this.addOrder("0018", suppliers.get(2), "10/8/13", "12/8/13", "4775", true, products, "comment");
//        this.addOrder("0019", suppliers.get(3), "14/8/13", "16/8/13", "3568", true, products, "comment");
//        this.addOrder("0020", suppliers.get(4), "18/8/13", "20/8/13", "625", true, products, "comment");
        
//        this.addInvoice("INV01", customers.get(0), "05/01/13", "09/01/13", "600", false, products, "comment");
//        this.addInvoice("INV02", customers.get(1), "07/02/13", "10/02/13", "400", false, products, "comment");
//        this.addInvoice("INV03", customers.get(2), "14/03/13", "16/03/13", "1000", false, products, "comment");
        
                
        updateStockLevels(stockLevelsIpod, "iPod");
        updateStockLevels(stockLevelsEnvy, "HP Envy");
        updateStockLevels(stockLevelsGalaxy, "Samsung Galaxy");
        
        
    }

    public void updateStockLevels(int [] arrayToBeUpdated, String productName) {
    	
    	for(int i = 0; i < 12; i++) {
        	int j = i + 1;
        	arrayToBeUpdated[i] = 0;
        	// inspect each order
        	for(Order order : orders) {
        		
        		String date = order.getOrderDeliveryDate();
        		int month = Integer.parseInt(date.substring(3, 5));
        		if(month == j) {
        			
        			//inspect all products in order
        			for(Product product : order.getProducts()) {
        				
        				if (productName.equals(product.getProductName())) {
        					
            				arrayToBeUpdated[i] = arrayToBeUpdated[i] + Integer.parseInt(product.getProductQuantity());
        				}
        			}
        		}
        	}
        	
        	//inspect each invoice
        	for(Invoice invoice : invoices) {
        		
        		String date = invoice.getInvoiceDate();
        		int month = Integer.parseInt(date.substring(3, 5));
        		
        		if(month == j) {
        			
        			//inspect all products in order
        			for(Product product : invoice.getProducts()) {
        				
        				if (productName.equals(product.getProductName())) {
        					
        					arrayToBeUpdated[i] = arrayToBeUpdated[i] - Integer.parseInt(product.getProductQuantity());
        				}
        			}
        		}
        	}
        	
        	//add on previous month stock
    		int k = i - 1;
    		if(k >= 0){
    				
    			arrayToBeUpdated[i] = arrayToBeUpdated[i] + arrayToBeUpdated[k];
    		}
        	
        	System.out.println("Stock level for month " + j + ": " + arrayToBeUpdated[i]);
        }
    }
    
    
      
    /*
     * customer methods
     */
    public void addCustomer(String customerID, String customerName, String customerEmail, String customerPhoneNumber, 
			String customerAddress) {
    	
    	Customer customer = new Customer(customerID, customerName, customerEmail, customerPhoneNumber, customerAddress);
        customers.add(customer);
    } 
      
    public void deleteCustomer(Customer customer) {
    	
        customers.remove(customer);
    } 
      
    public ArrayList<Customer> getCustomers() {
    	
        return customers;
    }
    
    public String[] getCustomerList() { 
        
        int noOfCustomers = customers.size(); 
        String[] arrayOfNames = new String[noOfCustomers]; 
        int i = 0; 
        for(Customer customer: customers) {
        	
            arrayOfNames[i] = customer.getCustomerName(); 
            i++;
        } 
          
        return arrayOfNames;
    }
    
    public Customer getCustomerByID(String id) { 
        int i = 0; 
        for(Customer customer: customers) { 
        	
            if(customer.getCustomerID().equals(id)) { 
                  
                break; 
            } 
            
            i++; 
        }
        
        return customers.get(i); 
    }
    

    public Customer getCustomerByName(String customerName) {
    	
		int i = 0;
		for(Customer customer: customers) {
			
			if(customer.getCustomerName().equals(customerName)) {	
				
				break;		
			}
			
			i++;
		}
		
		return customers.get(i);
	}
    
    public boolean checkCustomerID(String id) {
    	
        boolean validId = false; 
        for(Customer customer: customers) {
        	
            if(customer.getCustomerID().equals(id)) { 
                validId = true;
                break;
            } 
            else{ 
                validId = false; 
            }
        }
        
        return validId;
    }
    
    
      
    /*
     * supplier methods
     */
    public void addSupplier(String supplierID, String supplierName, String supplierEmail, String supplierPhoneNumber, 
			String supplierAddress) {
    	
    	Supplier supplier = new Supplier(supplierID, supplierName, supplierEmail, supplierPhoneNumber, supplierAddress);
        suppliers.add(supplier);
    } 
      
    public void deleteSupplier(Supplier supplier) {
    	
        suppliers.remove(supplier);
    } 
      
    public ArrayList<Supplier> getSuppliers() {
    	
        return suppliers;
    }
    
    public String[] getSupplierList() { 
    	  
        int noOfSuppliers = suppliers.size(); 
        String[] arrayOfNames = new String[noOfSuppliers]; 
        int i = 0; 
        for (Supplier supplier : suppliers) { 
            arrayOfNames[i] = supplier.getSupplierName(); 
            i++; 
        } 
  
        return arrayOfNames; 
    } 
    
    public Supplier getSupplierByID(String id) { 
    	
        int i = 0; 
        for(Supplier supplier: suppliers) { 
        	
            if(supplier.getSupplierID().equals(id)) { 
                  
                break; 
            }
            
            i++; 
        } 
        
        return suppliers.get(i); 
    }
    
    public Supplier getSupplierByName(String supplierName) {
    	
		int i = 0;
		for(Supplier supplier: suppliers) {
			
			if(supplier.getSupplierName().equals(supplierName)) {	
				
				break;		
			}
			
			i++;
		}
		
		return suppliers.get(i);
	}
    
    public boolean checkSupplierID(String id) {
    	
        boolean validId = false; 
        for(Supplier supplier: suppliers) {
        	
            if(supplier.getSupplierID().equals(id)) { 
                validId = true;
                break;
            } 
            else{ 
                validId = false; 
            }
        }
        
        return validId;
    }
    
    
    
    /*
     * product methods
     */
    public void addProduct(String productName, String productType, String productQuantity, double productPrice,
    		String productID, int [] stockLevels) { 
        
    	Product product = new Product(productName, productType, productQuantity, productPrice, productID, stockLevels);
    	products.add(product);
    }
    
    public void addInvoiceProduct(String productName, String productType, String productQuantity, double productPrice,
    		String productID, int [] stockLevels) { 
        
    	Product product = new Product(productName, productType, productQuantity, productPrice, productID, stockLevels);
    	products.add(product);
    }
      
    public void deleteProduct(Product product) { 
    	
        products.remove(product); 
    } 
      
    public ArrayList<Product> getProducts() { 
    	
        return products; 
    }
    
    public String[] getProductList() { 
    	  
        int noOfProducts = products.size(); 
        String[] arrayOfNames = new String[noOfProducts]; 
        int i = 0; 
        for (Product product : products) { 
            arrayOfNames[i] = product.getProductName(); 
            i++; 
        } 
        return arrayOfNames; 
  
    }
    
    public Product getProductByID(String ID) { 
    	
        int i = 0; 
        for(Product product: products) { 
        	
            if(product.getProductID().equals(ID)) { 
                  
                break; 
            } 
            
            i++; 
        } 
        
        return products.get(i); 
    }
    
    public boolean checkProductID(String ID) {
    	
        boolean validId = false; 
        for(Product product: products) { 
        	
            if(product.getProductID().equals(ID)) { 
                validId = true;
                break;
            } 
            else{ 
                validId = false; 
            } 
        } 
        
        return validId; 
    }
    
    public String getProductTypeByName(String productName) {
		String productType = null;
		for(Product product: products) {
			
			if(product.getProductName().equals(productName)) {
				
				productType = product.getProductType();
				break;		
			}
		}
		
		return productType;
	}
    
    
    
    /*
     * order methods
     */
    public void addOrder(String orderID, Supplier supplier, String orderDate, String orderDeliveryDate, String orderCost,
    		boolean isOrderOutstanding, ArrayList<Product> products, String orderComment){
    	
    	Order order = new Order(orderID, supplier, orderDate, orderDeliveryDate, orderCost, isOrderOutstanding, products, orderComment);
		orders.add(order);
	}
	
	public void deleteOrder(Order order){
		
		orders.remove(order);
	}
	
	public ArrayList<Order> getOrders() {
		
		return orders;
	}
	
	public Order getOrderByID(String ID) { 
    	
        int i = 0; 
        for(Order order: orders) { 
        	
            if(order.getOrderID().equals(ID)) { 
                  
                break; 
            } 
            
            i++; 
        } 
        
        return orders.get(i); 
    }

	public void setOrders(ArrayList<Order> orders) {
		
		this.orders = orders;
	}
	
	public DefaultTableModel getOrdersData() {
		
		int rows = orders.size();
		//int columns = 4;
		//String[][] data = new String[rows][columns];
		Object[] columnNames = {"ID", "Date", "Cost", "Supplier"};
		DefaultTableModel ordersData = new DefaultTableModel(columnNames, rows);

		for(int i = 0; i < rows; i++) {
			
//			for(int j = 0; j < columns; j++) {
//				
//				if(j == 0) { data[i][j] = orders.get(i).getOrderID(); }
//				else if (j == 1) { data[i][j] = orders.get(i).getOrderDate(); }
//				else if (j == 2) { data[i][j] = orders.get(i).getOrderCost(); }
//				else if (j == 3) { data[i][j] = orders.get(i).getSupplier().getSupplierName(); }
//				
//			}
			
			ordersData.setValueAt(orders.get(i).getOrderID(), i, 0);
			ordersData.setValueAt(orders.get(i).getOrderDate(), i, 1);
			ordersData.setValueAt(orders.get(i).getOrderCost(), i, 2);
			ordersData.setValueAt(orders.get(i).getSupplier().getSupplierName(), i, 3);
			
//			Object[] orderData = {
//					orders.get(i).getOrderID(),
//					orders.get(i).getOrderDate(), 
//					orders.get(i).getOrderCost(),
//					orders.get(i).getSupplier().getSupplierName()
//			};
//				
			//add row
//			ordersTableModel.addRow(orderData);
				
		}
		//System.out.println(ordersTableModel.getValueAt(0, 0));
		return ordersData;
		//return data;
	}
	
    public String[] getOrderIDList(){
    	
		int noOfOrders = orders.size();
		String[] arrayOfIDs = new String[noOfOrders];
		int i = 0;
		for(Order order: orders){
			
			arrayOfIDs[i] = order.getOrderID();
			i++;
		}
		
		return arrayOfIDs;
	}
	
	public String[] getOrderDateList(){
		
		int noOfOrders = orders.size();
		String[] arrayOfDates = new String[noOfOrders];
		int i = 0;
		for(Order order: orders){
			
			arrayOfDates[i] = order.getOrderDeliveryDate();
			i++;
		}
		
		return arrayOfDates;
	}
	
	public String[] getOrderCostList(){
		
		int noOfOrders = orders.size();
		String[] arrayOfCosts = new String[noOfOrders];
		int i = 0;
		for(Order order: orders){
			
			arrayOfCosts[i] = order.getOrderCost();
			i++;
		}
		
		return arrayOfCosts;
	}
	
	public String[] getOrderSupplierList(){
		
		int noOfOrders = orders.size();
		String[] arrayOfIDs = new String[noOfOrders];
		int i = 0;
		for(Order order: orders){
			
			String supplier = order.getSupplier().toString();
			arrayOfIDs[i] = supplier;
			i++;
		}
		
		return arrayOfIDs;
	}
	
    
    
    /*
     * invoice methods
     */
	
	public void addInvoice(String invoiceID, Customer customer, String invoiceDate, String invoiceDeliveryDate, String invoiceCost,
    		boolean isInvoiceOutstanding, ArrayList<Product> products, String invoiceComment){
    	
    	Invoice invoice = new Invoice(invoiceID, customer, invoiceDate, invoiceDeliveryDate, invoiceCost, isInvoiceOutstanding, products, invoiceComment);
		invoices.add(invoice);
	}
	
	public ArrayList<Invoice> getInvoices(){
		return invoices;
	}
	
	public String calculateInvoiceCost(ArrayList<Product> products){
		double cost = 0;
		for(Product product : products){
			cost = cost + product.getProductPrice();
		}
		cost = cost*1.2;
		return String.format("%.2f", cost); //Double.toString(cost);
	}
    
    
    /*
     * user account methods
     */
    public void addUserAccount(String firstName, String lastName, String userName, char[] password, int userLevel) {
    	
    	UserAccount userAccount = new UserAccount(firstName, lastName, userName, password, userLevel); 
    	userAccounts.add(userAccount);
    } 
      
    public void deleteUserAccount(UserAccount userAccount) { 
    	
        userAccounts.remove(userAccount); 
    } 
      
    public ArrayList<UserAccount> getUserAccounts() { 

        return userAccounts; 
    }
    
    public String[] getUserAccountList() { 
        
        int noOfUserAccounts = userAccounts.size(); 
        String[] arrayOfUserNames = new String[noOfUserAccounts]; 
        int i = 0; 
        for(UserAccount userAccount: userAccounts) { 
        	
            arrayOfUserNames[i] = userAccount.getUserAccountUserName(); 
            i++; 
        } 
          
        return arrayOfUserNames; 
    }
    
    public boolean checkUserName(String userName) { 
    	
        boolean validUserName = false; 
        for(UserAccount userAccount: userAccounts) { 
        	
            if(userAccount.getUserAccountUserName().equals(userName)) { 
            	
                validUserName = true; 
            } 
            else{ 
            	
                validUserName = false; 
            } 
        } 
        
        return validUserName; 
    }
    
    public UserAccount getUserAccountByUserName(String userName) { 
    	
        int i = 0; 
        for(UserAccount userAccount: userAccounts) { 
        	
            if(userAccount.getUserAccountUserName().equals(userName)) { 
                  
                break; 
            } 
            
            i++; 
        } 
        
        return userAccounts.get(i); 
    }
    
    public boolean userAuthenticated(String userName, char[] password){ 
        
        boolean isAuthenticated = false; 
        String user = null; 
        char[] correctPassword = null; 
          
        for(UserAccount userAccount: userAccounts){ 
              
            user = userAccount.getUserAccountUserName(); 
            correctPassword = userAccount.getUserAccountPassword(); 
              
            if(user.equals(userName) && Arrays.equals(password, correctPassword)){ 
            	
                 isAuthenticated = true; 
                 break; 
            }
            
            else{ 
            	
                isAuthenticated = false; 
            } 
        }    
          
        return isAuthenticated; 
    } 
}