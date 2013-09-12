import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import retailManagementSystem.*;


public class TestRetailProject {

	Database database;
	Customer customer;
	Supplier supplier;
	Product product;
	UserAccount userAccount;
	
	
	@Before
	public void setUp() throws Exception {
		database = new Database();
		customer = new Customer("AO9U8NNE", "James Mc Manus", "james@gmail.com", "0987654321", "Cork");
		supplier = new Supplier("09118553", "John", "john@gmail.com", "1092736492", "Meath");
		int[] stockLevelsIpod = null;
		product = new Product("iPod", "mp3", "100", 330, "AP0001", stockLevelsIpod );
		userAccount = new UserAccount("Stock", "Checker", "stock", "1234".toCharArray(), 3);
		
	}
	
	@Test //test to add customers, the addCustomer method is called in the Database class
	public void testAddCustomer() {
		Customer expectedCustomerobject = customer;
		Customer actualCustomerobject = null;
		
		for(Customer listOfCustomerObjects : database.getCustomers()){//loops through database of customers
			if(listOfCustomerObjects.getCustomerID().equals(expectedCustomerobject.getCustomerID())){ //if primary keys match, then assign the instance
				actualCustomerobject = listOfCustomerObjects;
			}
		}
		//assertNotSame(expectedCustomerobject.getCustomerID(),actualCustomerobject.getCustomerID()); test if false
		
		assertEquals(expectedCustomerobject.getCustomerID(),actualCustomerobject.getCustomerID());
	}

	@Test
	public void testDeleteCustomer() {
		database.deleteCustomer(customer);
		Customer expectedCustomerobject = customer;
		Customer actualCustomerobject = null;
		
		for(Customer listOfCustomerObjects : database.getCustomers()){
			if(listOfCustomerObjects.getCustomerID().equals(expectedCustomerobject.getCustomerID())){
				actualCustomerobject = listOfCustomerObjects;
			}
		}
		// assertEquals(expectedCustomerobject, actualCustomerobject);  test if false
		assertNotSame(expectedCustomerobject, actualCustomerobject ); 
	}

	@Test
	public void testGetCustomersList() {
		Customer expectedCustomerobject = customer;
		String [] expectedArray = {customer.getCustomerName()};
		String [] actualArray = null;
		for(Customer listOfCustomerObjects : database.getCustomers()){
			if(listOfCustomerObjects.getCustomerID().equals(expectedCustomerobject.getCustomerID())){
				actualArray = new String[]{listOfCustomerObjects.getCustomerName()};
			}
		}
		//assertFalse(Arrays.equals(expectedArray, actualArray));  test if false
		assertArrayEquals(expectedArray, actualArray);
	}
	

	@Test
	public void testAddSupplier() {
		Supplier expectedSupplierObject = supplier;
		Supplier actualSupplierObject = null;
		
		for(Supplier listOfSupplierObjects : database.getSuppliers()){
			if(listOfSupplierObjects.getSupplierID().equals(expectedSupplierObject.getSupplierID())){
				actualSupplierObject = listOfSupplierObjects;
			}
		}
		//assertNotSame(expectedSupplierObject.getSupplierID(),actualSupplierObject.getSupplierID()); test if false
		
		assertEquals(expectedSupplierObject.getSupplierID(),actualSupplierObject.getSupplierID());
	}

	@Test
	public void testDeleteSupplier() {
		database.deleteSupplier(supplier);
		Supplier expectedSupplierObject = supplier;
		Supplier actualSupplierObject = null;
		
		for(Supplier listOfSupplierObjects : database.getSuppliers()){
			if(listOfSupplierObjects.getSupplierID().equals(expectedSupplierObject.getSupplierID())){
				actualSupplierObject = listOfSupplierObjects;
			}
		}
		//assertEquals(expectedSupplierObject,actualSupplierObject); test for failure
		assertNotSame(expectedSupplierObject, actualSupplierObject);
	}
	
	@Test
	public void testGetSupplierList() {
		Supplier expectedSupplierObject = supplier;
		String [] expectedArray = {supplier.getSupplierName()};
		String [] actualArray = null;
		
		for(Supplier listOfSupplierObjects : database.getSuppliers()){
			if(listOfSupplierObjects.getSupplierID().equals(expectedSupplierObject.getSupplierID())){
				actualArray = new String[]{expectedSupplierObject.getSupplierName()};
			}
		}
		//assertFalse(Arrays.equals(expectedArray, actualArray)); test for failure
		assertArrayEquals(expectedArray, actualArray);
	}

	@Test
	public void testAddProducts() {
		Product expectedProductObject = product;
		Product actualProductObject = null;
		
		for(Product listOfProductObjects : database.getProducts()){
			if(listOfProductObjects.getProductName().equals(expectedProductObject.getProductName())){
				actualProductObject = listOfProductObjects;
			}
		}
		//assertNotSame(expectedProductObject.getProductName(),actualProductObject.getProductName()); test for failure
		assertEquals(expectedProductObject.getProductName(),actualProductObject.getProductName());
	}
	

	@Test
	public void testDeleteProduct() {
		database.deleteProduct(product);
		Product expectedProductObject = product;
		Product actualProductObject = null;
		
		for(Product listOfProductObjects : database.getProducts()){
			if(listOfProductObjects.getProductID().equals(expectedProductObject.getProductID())){
				actualProductObject = listOfProductObjects;
			}
		}
		//assertEquals(expectedProductObject,actualProductObject);  test for failure
		assertNotSame(expectedProductObject, actualProductObject);
	}
		
	@Test
	public void testGetProductList() {
		Product expectedProductObject = product;
		String [] expectedArray = {product.getProductName()};
		String [] actualArray = null;
		
		for(Product listOfProductObjects : database.getProducts()){
			if(listOfProductObjects.getProductName().equals(expectedProductObject.getProductName())){
				actualArray = new String[]{expectedProductObject.getProductName()};
			}
		}
		//assertFalse(Arrays.equals(expectedArray, actualArray));  test for failure
		assertArrayEquals(expectedArray, actualArray);
	}
	
	@Test
	public void testAddUserAccount(){
		UserAccount expectedUserAccountObject = userAccount;
		UserAccount actualUserAccountObject = null;
		
		for(UserAccount listOfUserAccountObjects : database.getUserAccounts()){
			if(listOfUserAccountObjects.getUserAccountFirstName().equals(expectedUserAccountObject.getUserAccountFirstName())){
				actualUserAccountObject = listOfUserAccountObjects;
			}
		}
		// assertNotSame(expectedUserAccountObject.getUserAccountFirstName(),actualUserAccountObject.getUserAccountFirstName());
		
		assertEquals(expectedUserAccountObject.getUserAccountFirstName(),actualUserAccountObject.getUserAccountFirstName());
	}
	
	
	@Test
	public void testDeleteUserAccount() {
		database.deleteUserAccount(userAccount);
		UserAccount expectedUserAccountObject = userAccount;
		UserAccount actualUserAccountObject = null;
		
		for(UserAccount listOfUserAccountObjects : database.getUserAccounts()){
			if(listOfUserAccountObjects.getUserAccountFirstName().equals(expectedUserAccountObject.getUserAccountFirstName())){
				actualUserAccountObject = listOfUserAccountObjects;
			}
		}
		assertNotSame(expectedUserAccountObject, actualUserAccountObject);
	}
}