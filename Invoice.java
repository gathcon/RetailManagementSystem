package retailManagementSystem;

import java.util.ArrayList;

public class Invoice {
	
	private String invoiceID;
	private String invoiceDeliveryDate;
	private String invoiceCost;
	private String isInvoiceOutstanding;
	private String invoiceQuantity;
	
	private ArrayList<Product> products = new ArrayList<Product>();
	
	private Customer customer;
	
	public Invoice(String invoiceID, String invoiceDeliveryDate, String invoiceCost, String isInvoiceOutstanding, String invoiceQuantity,
			Customer customer){
		this.invoiceID = invoiceID;
		this.invoiceDeliveryDate = invoiceDeliveryDate;
		this.invoiceCost = invoiceCost;
		this.isInvoiceOutstanding = isInvoiceOutstanding;
		this.invoiceQuantity = invoiceQuantity;
		this.customer = customer;
	}
	
public String calculateInvoiceCost(){
		
		for(Product product: products){
		int cost = (Integer.parseInt(invoiceQuantity))*(Integer.parseInt(product.getProductPrice()));
		invoiceCost = Integer.parseInt(invoiceCost)  + Integer.toString(cost);
		}
		return invoiceCost;
	}

	public void addProductToOrder(Product product) {
	products.add(product);
	}

	public void removeProductFromOrder(Product product) {
	products.remove(product);
	}

	
	
	public String getInvoiceQuantity() {
		return invoiceQuantity;
	}



	public void setInvoiceQuantity(String invoiceQuantity) {
		this.invoiceQuantity = invoiceQuantity;
	}



	public String getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(String invoiceID) {
		this.invoiceID = invoiceID;
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

	public String getIsInvoiceOutstanding() {
		return isInvoiceOutstanding;
	}

	public void setInvoiceOutstanding(String isInvoiceOutstanding) {
		this.isInvoiceOutstanding = isInvoiceOutstanding;
	}

}

