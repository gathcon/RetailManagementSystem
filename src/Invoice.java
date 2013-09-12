package retailManagementSystem;

import java.util.ArrayList;

public class Invoice {
	
	private String invoiceID;
	private String invoiceDate;
	private String invoiceDeliveryDate;
	private String invoiceCost;
	private boolean isInvoiceOutstanding;
	private ArrayList<Product> products = new ArrayList<Product>();
	private Customer customer;
	private String comment;
	
	public Invoice(String invoiceID, Customer customer, String invoiceDate, String invoiceDeliveryDate, String invoiceCost,
    		boolean isInvoiceOutstanding, ArrayList<Product> products, String invoiceComment){
		this.invoiceID = invoiceID;
		this.invoiceDate = invoiceDate;
		this.invoiceDeliveryDate = invoiceDeliveryDate;
		this.invoiceCost = invoiceCost;
		this.isInvoiceOutstanding = isInvoiceOutstanding;
		this.products = products;
		this.customer = customer;
		this.comment = invoiceComment;
	}
	
	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void addProductToOrder(Product product) {
	products.add(product);
	}

	public void removeProductFromOrder(Product product) {
	products.remove(product);
	}


	public String getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(String invoiceID) {
		this.invoiceID = invoiceID;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getInvoiceDeliveryDate() {
		return invoiceDeliveryDate;
	}

	public void setInvoiceDeliveryDate(String invoiceDeliveryDate) {
		this.invoiceDeliveryDate = invoiceDeliveryDate;
	}

	public String getInvoiceCost() {
		return invoiceCost;
	}

	public void setInvoiceCost(String invoiceCost) {
		this.invoiceCost = invoiceCost;
	}

	public boolean isInvoiceOutstanding() {
		return isInvoiceOutstanding;
	}

	public void setInvoiceOutstanding(boolean isInvoiceOutstanding) {
		this.isInvoiceOutstanding = isInvoiceOutstanding;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	
}

