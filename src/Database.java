package retailManagementSystem; 
  
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.swing.table.DefaultTableModel;
  
public class Database { 
      
    private ArrayList<Customer> customers = new ArrayList<Customer>(); 
    private ArrayList<Supplier> suppliers = new ArrayList<Supplier>(); 
    private ArrayList<Product> products = new ArrayList<Product>();
    private ArrayList<Product> orderProductsList1 = new ArrayList<Product>();
    private ArrayList<Order> orders = new ArrayList<Order>();
	private ArrayList<Invoice> invoices = new ArrayList<Invoice>();
    private ArrayList<UserAccount> userAccounts = new ArrayList<UserAccount>();
    private int[] emptyArray = {0,0,0,0,0,0,0,0,0,0,0,0};
     
    /*
     * database constructor
     */
    public Database() {
          
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

        this.addUserAccount("admin", "admin", "1", "1".toCharArray(), 0);
        this.addUserAccount("admin", "admin", "Administrator", "admin".toCharArray(), 0);
        this.addUserAccount("General", "Manager", "Manager", "1234".toCharArray(), 1);
        this.addUserAccount("Teller", "Teller", "Teller", "1234".toCharArray(), 2);
        this.addUserAccount("Stock", "Checker", "Stock Checker", "1234".toCharArray(), 3);
        
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
        					
            				arrayToBeUpdated[i] += Integer.parseInt(product.getProductQuantity());
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
        					
        					arrayToBeUpdated[i] -= Integer.parseInt(product.getProductQuantity());
        				}
        				
        			}
        		}
        	}
        	
        	//add on previous month stock
    		int k = i - 1;
    		if(k >= 0){

    			arrayToBeUpdated[i] += arrayToBeUpdated[k];
    		}
        }
    	
    	int[] newArray = {100, 200, 456, 377, 279, 134, 689, 499};
    	for(int i = 0; i < 8; i++) {
    		arrayToBeUpdated[i] = newArray[i];
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

    public void addInvoiceProduct() { 
        
    	Product product = new Product();
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
    
    public Product getProductByName(String productName){
    	Product returnProduct = null;
    	for(Product product: products) {
			
			if(product.getProductName().equals(productName)) {
				
				returnProduct = product;
				break;		
			}
		}
    	return returnProduct;
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
	
	public String calculateOrderCost(ArrayList<Product> products){
		double cost = 0;
		for(Product product : products){
			cost = cost + product.getProductPrice();
		}
		return String.format("%.2f", cost); 
	}
	
	public double sumOrderCosts(){

		ArrayList<Order> orders = getOrders();
    
		double sum = 0;
		for(int i = 0; i < orders.size(); i++){
			sum = sum + Double.parseDouble(orders.get(i).getOrderCost());
		}
		return sum;
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
		cost = cost*1.5;
		return String.format("%.2f", cost); //Double.toString(cost);
	}
	
	public double sumInvoiceCosts(){
		ArrayList<Invoice> invoices = getInvoices();
    
		double sum = 0;
		for(int i = 0; i < invoices.size(); i++){
			sum = sum + Double.parseDouble(invoices.get(i).getInvoiceCost());
		}
		return sum;
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
    
    public boolean isDateOutstanding(String orderDeliveryDate){
		Date todaysDate = new Date();
		Date deliveryDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		try {
			deliveryDate = sdf.parse(orderDeliveryDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(deliveryDate.after(todaysDate)){
			return true;
		}
		if(deliveryDate.equals(todaysDate)){
			return true;
		}
		else{
			return false;
		}
	}
    
    public static String generateUniqueId() {      
        String uuidChars = "" + UUID.randomUUID().toString();
        String uid = uuidChars.replaceAll("-", "");    
        int myuid = uid.hashCode();
        
        uid = Integer.toString(myuid);
        uid = uuidChars.replaceAll("-", "");
        
        char[] newUUID = uid.toCharArray();
        String ID = "";
        char  temp;
        
        for(int i = 0; i<8;i++){       	
        	temp = newUUID[i];    	
        	if(Character.getType(newUUID[i]) == Character.LOWERCASE_LETTER){     		
        		temp = Character.toUpperCase(newUUID[i]);
        	} 
        	 ID += temp;
        }       
        
        return ID;
    }
}
