package retailManagementSystem;

public class Supplier {
	
	private String supplierID;
	private String supplierName;
	private String supplierEmail;
	private String supplierPhoneNumber;
	private String supplierAddress;
	
	public Supplier(String supplierID, String supplierName, String supplierEmail, String supplierPhoneNumber, 
			String supplierAddress){
		
		this.supplierID = supplierID;
		this.supplierName = supplierName;
		this.supplierEmail = supplierEmail;
		this.supplierPhoneNumber = supplierPhoneNumber;
		this.supplierAddress = supplierAddress;
		
	}

	

	public String getSupplierID() {
		return supplierID;
	}



	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}



	public String getSupplierPhoneNumber() {
		return supplierPhoneNumber;
	}



	public void setSupplierPhoneNumber(String supplierPhoneNumber) {
		this.supplierPhoneNumber = supplierPhoneNumber;
	}



	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierEmail() {
		return supplierEmail;
	}

	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}


	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}
	
	

}
